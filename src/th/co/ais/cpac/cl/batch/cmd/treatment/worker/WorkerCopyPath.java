/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.cmd.treatment.worker;

import th.co.ais.cpac.cl.batch.db.CLCollectionPath;
import th.co.ais.cpac.cl.batch.cnf.CNFDatabase;
import th.co.ais.cpac.cl.batch.db.CLTempHisBaAccountBalance;
import th.co.ais.cpac.cl.common.Context;
import th.co.ais.cpac.cl.common.UtilityLogger;
import th.co.ais.cpac.cl.common.criteria.Criteria;

import th.co.ais.cpac.cl.template.database.DBConnectionPools;

/**
 *
 * @author Optimus
 */
public class WorkerCopyPath extends Thread {

  private static final Object xxx = new Object();
  private final Context ctx;
  private final th.co.ais.cpac.cl.batch.db.CLCollectionPath.PathInfo pathInfo;

  public WorkerCopyPath(Context ctx, CLCollectionPath.PathInfo pathInfo) {
    String xx = "{copy path:" + pathInfo.getPathId().toPlainString() + "}";
    this.ctx = ctx.clone(xx);
    this.pathInfo = pathInfo;
  }

  protected static DBConnectionPools getConnection(Context ctx) {
    CNFDatabase cnf = new CNFDatabase();
    DBConnectionPools<CNFDatabase, UtilityLogger> dd = new DBConnectionPools<>(cnf, ctx.getLogger());
    return dd;
  }

  @Override
  public void run() {
    if (ctx == null) {
      return;
    }
    if (pathInfo == null) {
      return;
    }

    DBConnectionPools connection = getConnection(ctx);
    CLTempHisBaAccountBalance ac = new CLTempHisBaAccountBalance(ctx.getLogger());

    String[] sql;
    try {
      //synchronized (xxx) {
      Criteria cri = new Criteria(ctx);
      //}
      ctx.getLogger().debug("-------------------------> " + pathInfo.getCriteriaId().toPlainString());
      //sql = cri.GenQueryStatement(pathInfo.getCriteriaId(), connection.getConnection());
      sql = new String[3];
      sql[0] = "SELECT CL_BA_INFO.CA_NO ,CL_BA_INFO.SA_NO ,CL_BA_INFO.BA_NO ,CL_BA_INFO.COLLECTION_PATH_ID ,CL_BA_INFO.PRIORITY_STATUS_ID ,CL_BA_INFO.PRIORITY_STATUS_DATE ,CL_BA_INFO.BILL_ZIPCODE ,CL_BA_INFO.BILL_PROVINCE_ID ,CL_BA_INFO.BILL_REGION_ID ,CL_HIS_BA_ACCOUNT_BALANCE.BA_ACCOUNT_BALANCE_ID ,CL_HIS_BA_ACCOUNT_BALANCE.AR_MOVEMENT_ID ,CL_HIS_BA_ACCOUNT_BALANCE.MIN_DUE_DATE ";
      sql[1] = "FROM CL_HIS_BA_ACCOUNT_BALANCE noholdlock , CL_BA_INFO noholdlock , CL_CA_INFO noholdlock ";
      //sql[2] = "WHERE CL_BA_INFO.BA_NO=CL_HIS_BA_ACCOUNT_BALANCE.BA_NO AND CL_CA_INFO.CA_NO=CL_BA_INFO.CA_NO AND (CL_CA_INFO.ACCOUNT_SEGMENT_ID = 1 OR CL_CA_INFO.ACCOUNT_SEGMENT_ID = 3 OR CL_CA_INFO.ACCOUNT_SEGMENT_ID = 5) AND CL_BA_INFO.COMPANY_ID = 1";
      sql[2] = "WHERE CL_BA_INFO.BA_NO=CL_HIS_BA_ACCOUNT_BALANCE.BA_NO AND CL_CA_INFO.CA_NO=CL_BA_INFO.CA_NO ";
    } catch (Exception ex) {
      ctx.getLogger().debug("copy path : ", ex);
      return;
    }
    connection.close();

    StringBuffer sqlAll = new StringBuffer();
    sqlAll.append(sql[0]).append(",");

    sqlAll.append(pathInfo.getPathId().toPlainString()).append(",");
    sqlAll.append("'").append(pathInfo.getPrimaryBoo()).append("',");
    sqlAll.append(pathInfo.getCriteriaId().toPlainString()).append(",");
    sqlAll.append(pathInfo.getCriteriaPriority().toPlainString()).append(",");
    sqlAll.append(pathInfo.getLastUpdateCriteria()).append("");
    sqlAll.append(" ");
    sqlAll.append(sql[1]);
    sqlAll.append(" ");
    sqlAll.append(sql[2]);

    CLTempHisBaAccountBalance.ExecuteResponse rs = ac.copyPath(sqlAll);
    ctx.getLogger().debug(rs.info().toString());
    switch (rs.getStatusCode()) {
      case CLTempHisBaAccountBalance.ExecuteResponse.STATUS_COMPLETE:
      case CLTempHisBaAccountBalance.ExecuteResponse.STATUS_DATA_NOT_FOUND: {
        break;
      }
      default: {

      }
    }

  }
}
/*
    CLTempHisBaAccountBalance.InsertCriteriaLogResponse resultLog = ac.InsertCriteriaLog(Constants.LogCriteriaType.Path, Constants.LogCriteriaOption.VerifyAndRun, Constants.LogCriteriaStatus.Processing);
    ctx.getLogger().debug(resultLog.info().toString());

    switch (resultLog.getStatusCode()) {
      case CLTempHisBaAccountBalance.InsertCriteriaLogResponse.STATUS_COMPLETE: {

        break;
      }
      default: {
        return;
      }
    }
    BigDecimal logCriteriaId = resultLog.getResponse();
 */
