/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.cmd.treatment.worker;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import th.co.ais.cpac.cl.batch.db.CLAction;
import th.co.ais.cpac.cl.batch.cnf.CNFDatabase;
import th.co.ais.cpac.cl.batch.db.CLTempHisBaAccountBalance;
import th.co.ais.cpac.cl.common.Context;
import th.co.ais.cpac.cl.common.UtilityLogger;
import th.co.ais.cpac.cl.template.database.DBConnectionPools;

/**
 *
 * @author Optimus
 */
public class ManagerDriveAction extends Thread {

  private final Context ctx;
  private final th.co.ais.cpac.cl.batch.db.CLAction.ActionDetail actionDetail;

  public ManagerDriveAction(Context ctx, CLAction.ActionDetail actionDetail) {
    String xx = "{PathInfo:" + actionDetail.getCollectionPathID().toPlainString() + "}";
    this.ctx = ctx.clone(xx);
    this.actionDetail = actionDetail;
  }

  protected static DBConnectionPools getConnection(Context ctx) {
    CNFDatabase cnf = new CNFDatabase();
    DBConnectionPools<CNFDatabase, UtilityLogger> dd = new DBConnectionPools<>(cnf, ctx.getLogger());
    return dd;
  }

  @Override
  public void run() {
    ctx.getLogger().debug("actionDetail.getCollectionPathID().toPlainString() : " + actionDetail.getCollectionPathID().toPlainString());

    //<editor-fold defaultstate="collapsed" desc="prepare data">
    if (ctx == null) {
      return;
    }
    if (actionDetail == null) {
      return;
    }
    //ctx.getLogger().debug("xxx ");
    HashMap<String, ArrayList<BigDecimal>> day = new HashMap<>();
    HashMap<String, ArrayList<CLAction.ActionInfo>> action = new HashMap<>();
    ArrayList<CLAction.ActionInfo> listAction = actionDetail.getListActionInfo();

    for (int i = 0; i < listAction.size(); i++) {

      CLAction.ActionInfo info = listAction.get(i);
      //ctx.getLogger().debug("action info : "+info.toString());
      if (info.getActionTrigger() == null) {
        ctx.getLogger().error("getActionTrigger error : " + info.getActionID());
        continue;
      }
      //???? update 20161229 
      String key = info.getActionTriggerType().getKeyActionTrigerType();
      ctx.getLogger().debug("key action triger : " + key + "{" + info.getActionID().toPlainString() + "}");
      ArrayList<BigDecimal> objDay = day.get(key);
      ArrayList<CLAction.ActionInfo> objAction = action.get(key);

      if (objDay == null) {
        day.put(key, new ArrayList<>());
        action.put(key, new ArrayList<>());
        objDay = day.get(key);
        objAction = action.get(key);
      }
      objDay.add(info.getActionNumberDay());
      objAction.add(info);
    }

    //</editor-fold>
    //
    //<editor-fold defaultstate="collapsed" desc="Get List Action Skip">
    CLAction.ActionSkipDetailResponse actionSkip = new CLAction(ctx.getLogger()).queryQueryActionSkipDetail();
    ctx.getLogger().debug(actionSkip.info().toString());
    switch (actionSkip.getStatusCode()) {
      case CLAction.ActionSkipDetailResponse.STATUS_COMPLETE: {
        //HashMap<BigDecimal, CLAction.ActionSkipDetail> hh = actionSkip.getResponse();
        break;
      }
      case CLAction.ActionSkipDetailResponse.STATUS_DATA_NOT_FOUND: {
        break;
      }
      default: {
        return;
      }
    }
    //</editor-fold>
    //

    WorkerDriveAction mm = new WorkerDriveAction();
    WorkerDriveAction.CommonBean commonBean = mm.buildWorkBean(actionDetail, day, action, actionSkip.getResponse());
    commonBean.debugDay(ctx);
    commonBean.debugAction(ctx);
    commonBean.debugActionDetail(ctx);
    commonBean.debugActionSkip(ctx);

    while (true) {
      CLTempHisBaAccountBalance dbTemp = new CLTempHisBaAccountBalance(ctx.getLogger());
      CLTempHisBaAccountBalance.TempAccountBalanceResponse list = dbTemp.queryTempAccount(actionDetail.getCollectionPathID(), new BigDecimal(System.nanoTime()));
      ctx.getLogger().debug(list.info().toString());
      switch (list.getStatusCode()) {
        case CLTempHisBaAccountBalance.TempAccountBalanceResponse.STATUS_COMPLETE: {
          // ???? ติดปัญหาว่า deadlock ณ update CL_TM_UPDATE_TEMP เป็นบ้างครั้ง 
          ExecutorService executor1 = Executors.newFixedThreadPool(10);
          for (int i = 0; i < list.getRecordCount(); i++) {
            CLTempHisBaAccountBalance.TempAccountBalance baInfo = list.getResponse().get(i);
            executor1.execute(new WorkerDriveAction(ctx, commonBean, baInfo));
          }
          executor1.shutdown();

          while (!executor1.isTerminated()) {
            try {
              Thread.sleep(1000L);
            } catch (InterruptedException ex) {
            }
          }
          break;
        }
        case CLTempHisBaAccountBalance.TempAccountBalanceResponse.STATUS_DATA_NOT_FOUND: {          
          //end while true
          ctx.getLogger().info("Action Drive End.");
          return;
        }
        default: {
          //sleep and retry
        }
      }

    }
  }
}
/*

    for (int i = 0; i < listAction.size(); i++) {
      CLAction.ActionInfo info = listAction.get(i);
      if (info.getActionTriggerType() == null) {
        ctx.getLogger().error("mapActionTriggerType error : " + info.getActionID());
        continue;
      }
      switch (info.getActionTriggerType()) {
        case DebtAge: {
          listDebtAges.add(info.getActionDay());
          listDebtAgesAction.add(info);
          break;
        }
        case StatusAge: {
          String key = info.getActionTrigger().getKeyActionTriger();
          if (listSttsAges.get(key) == null) {
            listSttsAges.put(key, new ArrayList<>());
            listSttsAgesAction.put(key, new ArrayList<>());
          }
          listSttsAges.get(key).add(info.getActionDay());
          listSttsAgesAction.get(key).add(info);
          break;
        }
        case ActionAge: {
          String key = info.getActionTrigger().getKeyActionTriger();
          if (listActnAges.get(key) == null) {
            listActnAges.put(key, new ArrayList<>());
            listActnAgesAction.put(key, new ArrayList<>());
          }
          listActnAges.get(key).add(info.getActionDay());
          listActnAgesAction.get(key).add(info);
          break;
        }
      }

    }
 */
