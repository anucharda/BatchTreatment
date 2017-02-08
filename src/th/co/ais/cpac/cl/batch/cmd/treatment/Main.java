/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.cmd.treatment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import th.co.ais.cpac.cl.batch.cmd.treatment.worker.ManagerDriveAction;
import th.co.ais.cpac.cl.batch.db.CLAction;
import th.co.ais.cpac.cl.batch.db.CLCollectionPath;
import th.co.ais.cpac.cl.batch.cmd.treatment.worker.WorkerCopyPath;
import th.co.ais.cpac.cl.batch.cmd.treatment.worker.WorkerCopyTreatment;
import th.co.ais.cpac.cl.batch.cmd.treatment.worker.WorkerUpdateActionCriteria;
import th.co.ais.cpac.cl.batch.cnf.CNFDatabase;
import th.co.ais.cpac.cl.batch.db.CLTempHisBaAccountBalance;
import th.co.ais.cpac.cl.template.database.DBConnectionPools;

import th.co.ais.cpac.cl.common.*;

/**
 * @author Optimus
 */
public class Main {

  protected static DBConnectionPools getConnection(Context ctx) {
    CNFDatabase cnf = new CNFDatabase();
    DBConnectionPools<CNFDatabase, UtilityLogger> dd = new DBConnectionPools<>(cnf, ctx.getLogger());
    return dd;
  }

  public static void main(String[] args) {
    CNFDatabase cnf = new CNFDatabase("D:\\Work\\Ais\\Apps\\Batch\\configuration\\database.properties");

    Context ctx = new Context();
    String logheader = "" + System.currentTimeMillis();
    ctx.initailLogger("LoggerMasterBatchInfo", logheader);

    new DBConnectionPools<>(cnf, ctx.getLogger()).buildeDataSource();
    CLTempHisBaAccountBalance mmmm = new CLTempHisBaAccountBalance(ctx.getLogger());

    /**
     * * Start Process ***
     */
    //<editor-fold defaultstate="collapsed" desc="Truncate CL_TEMP_HIS_BA_ACCOUNT_BALANCE">
    // ??? CLTempHisBaAccountBalance mmmm = new CLTempHisBaAccountBalance(ctx.getLogger());
    CLTempHisBaAccountBalance.ExecuteResponse truncate = mmmm.truncate();
    ctx.getLogger().debug(truncate.info().toString());
    switch (truncate.getStatusCode()) {
      case CLTempHisBaAccountBalance.ExecuteResponse.STATUS_COMPLETE: {
        break;
      }
      default: {
        return;
      }
    }
    //</editor-fold>
    //
    //<editor-fold defaultstate="collapsed" desc="Get List Collection Path Active">
    CLCollectionPath path = new CLCollectionPath(ctx.getLogger());
    CLCollectionPath.PathInfoResponse listPath = path.queryListPathInfo();
    ctx.getLogger().debug(listPath.info().toString());
    switch (listPath.getStatusCode()) {
      case CLCollectionPath.PathInfoResponse.STATUS_COMPLETE: {
        break;
      }
      default: {
        return;
      }
    }
    //</editor-fold>
    //

    //<editor-fold defaultstate="collapsed" desc="INSERT INTO CL_TEMP_HIS_BA_ACCOUNT_BALANCE">
    //??? ติดปัญหาว่า จะมีการ lock หากมีการ run พร้อม ๆ ๆ กันหลายๆ ๆ ๆ thread ..
    ExecutorService executor = Executors.newFixedThreadPool(2);
    for (int i = 0; i < listPath.getRecordCount(); i++) {
      CLCollectionPath.PathInfo pathInfo = listPath.getResponse().get(i);
      executor.execute(new WorkerCopyPath(ctx, pathInfo));
    }
    executor.shutdown();

    while (!executor.isTerminated()) {
      try {
        Thread.sleep(cnf.get("notify.addon.active.number.wait.end", 50L));
      } catch (InterruptedException ex) {
      }
    }

    //</editor-fold>
    //

    //<editor-fold defaultstate="collapsed" desc="COUNT PATH & FIND PRIORITY PATH">    
    CLTempHisBaAccountBalance.ExecuteResponse upPath = mmmm.updatePathRunning();
    ctx.getLogger().debug(upPath.info().toString());
    switch (upPath.getStatusCode()) {
      case CLTempHisBaAccountBalance.ExecuteResponse.STATUS_COMPLETE: {
        break;
      }
      default: {
        return;
      }
    }
    //</editor-fold>
    //    

    //<editor-fold defaultstate="collapsed" desc="Get List Path and Action">
    CLAction actDetail = new CLAction(ctx.getLogger());
    CLAction.ActionDetailResponse listAction = actDetail.queryActionDetail();
    ctx.getLogger().debug(listAction.info().toString());
    switch (listAction.getStatusCode()) {
      case CLAction.ActionDetailResponse.STATUS_COMPLETE: {
        break;
      }
      case CLAction.ActionDetailResponse.STATUS_DATA_NOT_FOUND: {
        return;
      }
      default: {
        return;
      }
    }
    //</editor-fold>
    //

    //<editor-fold defaultstate="collapsed" desc="Find Action">
    // ??? เจอว่า priority_status_id = null or ไม่สามารถ join CL_PRIORITY_STATUS ได้
    ExecutorService executor1 = Executors.newFixedThreadPool(2);
    for (int i = 0; i < listPath.getRecordCount(); i++) {
      CLCollectionPath.PathInfo pathInfo = listPath.getResponse().get(i);
      CLAction.ActionDetail actionInfo = listAction.getResponse().get(pathInfo.getPathId());
      executor1.execute(new ManagerDriveAction(ctx, actionInfo));
    }
    executor1.shutdown();

    while (!executor1.isTerminated()) {
      try {
        Thread.sleep(cnf.get("notify.addon.active.number.wait.end", 50L));
      } catch (InterruptedException ex) {
      }
    }
    //</editor-fold>
    //
    //<editor-fold defaultstate="collapsed" desc="find and update action criteria to temp table">
    ctx.getLogger().info("Start ......................... find action criteria");
    CLTempHisBaAccountBalance.ActionCriteriaResponse responseActionCriteria = mmmm.queryActionCriteria();
    ctx.getLogger().debug(responseActionCriteria.info().toString());
    switch (responseActionCriteria.getStatusCode()) {
      case CLTempHisBaAccountBalance.ActionCriteriaResponse.STATUS_COMPLETE: {
        break;
      }
      case CLTempHisBaAccountBalance.ActionCriteriaResponse.STATUS_DATA_NOT_FOUND: {
        return;
      }
      default: {
        return;
      }
    }

    ExecutorService executor2 = Executors.newFixedThreadPool(5);

    Iterator<BigDecimal> listActionCriteria = responseActionCriteria.getResponse().keySet().iterator();
    while (listActionCriteria.hasNext()) {

      BigDecimal key = listActionCriteria.next();
      ArrayList<CLTempHisBaAccountBalance.ActionCriteriaInfo> actionCriteria = responseActionCriteria.getResponse().get(key);

      executor2.execute(new WorkerUpdateActionCriteria(ctx, actionCriteria));
    }

    executor2.shutdown();

    while (!executor2.isTerminated()) {
      try {
        Thread.sleep(cnf.get("notify.addon.active.number.wait.end", 50L));
      } catch (InterruptedException ex) {
      }
    }
    //</editor-fold>
    //

    //<editor-fold defaultstate="collapsed" desc="Copy CLTreatment">
    while (true) {
      boolean run = true;
      CLTempHisBaAccountBalance dbTemp = new CLTempHisBaAccountBalance(ctx.getLogger());
      CLTempHisBaAccountBalance.TreatmentResponse list = dbTemp.queryTempAccount(new BigDecimal(System.nanoTime()));
      ctx.getLogger().debug(list.info().toString());

      switch (list.getStatusCode()) {
        case CLTempHisBaAccountBalance.TempAccountBalanceResponse.STATUS_COMPLETE: {

          ExecutorService executor14 = Executors.newFixedThreadPool(5);
          Iterator<ArrayList<CLTempHisBaAccountBalance.TempAccountBalance>> values = list.getResponse().values().iterator();
          while (values.hasNext()) {
            executor14.execute(new WorkerCopyTreatment(ctx, values.next()));
          }

          executor14.shutdown();

          while (!executor14.isTerminated()) {
            try {
              Thread.sleep(1000L);
            } catch (InterruptedException ex) {
            }
          }
          break;
        }
        case CLTempHisBaAccountBalance.TempAccountBalanceResponse.STATUS_DATA_NOT_FOUND: {
          //end while true
          run = false;
          break;
        }
        default: {
          //sleep and retry
        }
      }
      if (!run) {
        break;
      }
    }
    //</editor-fold>

    new DBConnectionPools<>(cnf, ctx.getLogger()).closeDataSource();

  }

}

/*

  public static void mainxxx(String[] args) {
    CNFDatabase cnf = new CNFDatabase("D:\\Work\\Ais\\Apps\\Batch\\configuration\\database.properties");

    Context ctx = new Context();
    ctx.initailLogger("LoggerMasterBatchInfo", "XXXX|YYYYY");

    new DBConnectionPools<>(cnf, ctx.getLogger()).buildeDataSource();
    DBConnectionPools<CNFDatabase, UtilityLogger> dd = new DBConnectionPools<>(cnf, ctx.getLogger());

    Account account = new Account(ctx.getLogger());
    Account.ExecuteResponse r1 = account.truncate();
    ctx.getLogger().debug(r1.info().toString());
    switch (r1.getStatusCode()) {
      case Account.ExecuteResponse.STATUS_COMPLETE:
      case Account.ExecuteResponse.STATUS_DATA_NOT_FOUND: {
        break;
      }
      default: {
        return;
      }
    }

    CLCollectionPath path = new CLCollectionPath(ctx.getLogger());
    CLCollectionPath.PathInfoResponse r2 = path.queryListPathInfo();
    ctx.getLogger().debug(r2.info().toString());
    switch (r2.getStatusCode()) {
      case CLCollectionPath.PathInfoResponse.STATUS_COMPLETE: {
        break;
      }
      default: {
        return;
      }
    }

    ExecutorService executor = Executors.newFixedThreadPool(2);
    for (int i = 0; i < r2.getRecordCount(); i++) {
      CLCollectionPath.PathInfo pathInfo = r2.getResponse().get(i);
      executor.execute(new WorkerCopyPath(ctx, pathInfo));
    }
    executor.shutdown();

    while (!executor.isTerminated()) {
      try {
        Thread.sleep(cnf.get("notify.addon.active.number.wait.end", 1000L));
      } catch (InterruptedException ex) {
      }
    }

    Action actDetail = new Action(ctx.getLogger());
    Action.ActionDetailResponse rsAD = actDetail.queryActionDetail();
    switch (rsAD.getStatusCode()) {
      case Action.ActionDetailResponse.STATUS_COMPLETE: {
        break;
      }
      case Action.ActionDetailResponse.STATUS_DATA_NOT_FOUND: {
        return;
      }
      default: {
        return;
      }
    }

    ExecutorService executor1 = Executors.newFixedThreadPool(2);
    for (int i = 0; i < r2.getRecordCount(); i++) {
      CLCollectionPath.PathInfo pathInfo = r2.getResponse().get(i);
      executor1.execute(new WorkerCopyPath(ctx, pathInfo));
    }
    executor1.shutdown();

    while (!executor1.isTerminated()) {
      try {
        Thread.sleep(cnf.get("notify.addon.active.number.wait.end", 1000L));
      } catch (InterruptedException ex) {
      }
    }

    new DBConnectionPools<>(cnf, ctx.getLogger()).closeDataSource();

  }
 */
