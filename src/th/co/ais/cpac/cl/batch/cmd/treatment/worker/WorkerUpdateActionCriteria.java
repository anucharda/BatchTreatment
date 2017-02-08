/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.cmd.treatment.worker;

import java.util.ArrayList;
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
public class WorkerUpdateActionCriteria extends Thread {

  private static final Object xxx = new Object();
  private final Context ctx;
  private final ArrayList<CLTempHisBaAccountBalance.ActionCriteriaInfo> listcriteria;

  public WorkerUpdateActionCriteria(Context ctx, ArrayList<CLTempHisBaAccountBalance.ActionCriteriaInfo> listcriteria) {
    this.ctx = ctx;
    this.listcriteria = listcriteria;
  }

  protected static DBConnectionPools getConnection(Context ctx) {
    CNFDatabase cnf = new CNFDatabase();
    DBConnectionPools<CNFDatabase, UtilityLogger> dd = new DBConnectionPools<>(cnf, ctx.getLogger());

    return dd;
  }

  @Override
  public void run() {
    ctx.getLogger().debug("Start WorkerUpdateActionCriteria ..........");
    if (listcriteria == null) {

      return;
    }
    CLTempHisBaAccountBalance ac = new CLTempHisBaAccountBalance(ctx.getLogger());
    if (!ac.buildConnection()) {
      ctx.getLogger().debug("get connection db error.");
      return;
    }
    
    try {
      for (int i = 0; i < listcriteria.size(); i++) {
        CLTempHisBaAccountBalance.ActionCriteriaInfo criteria = listcriteria.get(i);
        ctx.getLogger().debug("action=" + criteria.getActionId() + ",criteriaId=" + criteria.getCriteriaId());
        StringBuilder sqlWhere = new StringBuilder();
        try {

          synchronized (xxx) {
            Criteria cri = new Criteria(ctx);
          }

          String[] sql;
          /// ??? sql = cri.GenQueryStatement(criteria.getCriteriaId(), connection.getConnection());
          sql = new String[3];

          sql[0] = "SELECT CL_BA_INFO.CA_NO ,CL_BA_INFO.SA_NO ,CL_BA_INFO.BA_NO ,CL_BA_INFO.COLLECTION_PATH_ID ,CL_BA_INFO.PRIORITY_STATUS_ID ,CL_BA_INFO.PRIORITY_STATUS_DATE ,CL_BA_INFO.BILL_ZIPCODE ,CL_BA_INFO.BILL_PROVINCE_ID ,CL_BA_INFO.BILL_REGION_ID ,CL_HIS_BA_ACCOUNT_BALANCE.BA_ACCOUNT_BALANCE_ID ,CL_HIS_BA_ACCOUNT_BALANCE.AR_MOVEMENT_ID ,CL_HIS_BA_ACCOUNT_BALANCE.MIN_DUE_DATE ";
          sql[1] = "FROM CL_HIS_BA_ACCOUNT_BALANCE, CL_BA_INFO, CL_CA_INFO";
          sql[2] = "WHERE CL_BA_INFO.BA_NO=CL_HIS_BA_ACCOUNT_BALANCE.BA_NO AND CL_CA_INFO.CA_NO=CL_BA_INFO.CA_NO ";

          sqlWhere.append("SELECT CL_HIS_BA_ACCOUNT_BALANCE.BA_ACCOUNT_BALANCE_ID ");
          sqlWhere.append(sql[1]).append(" ");
          sqlWhere.append(sql[2]).append(" ");

        } catch (Exception ex) {
          ac.getDBConnection().rollback();
          ctx.getLogger().debug("", ex);
          return;
        }

        CLTempHisBaAccountBalance.ExecuteResponse rs1 = ac.prepareActionCriteria(sqlWhere.toString(), criteria.getActionId());

        ctx.getLogger().debug(rs1.info().toString());
        switch (rs1.getStatusCode()) {
          case CLTempHisBaAccountBalance.ExecuteResponse.STATUS_COMPLETE:
          case CLTempHisBaAccountBalance.ExecuteResponse.STATUS_DATA_NOT_FOUND: {
            break;
          }
          default: {
            ac.getDBConnection().rollback();
          }
        }

        CLTempHisBaAccountBalance.ExecuteResponse rs2 = ac.updateActionCriteria(criteria);
        ctx.getLogger().debug(rs2.info().toString());
        switch (rs2.getStatusCode()) {
          case CLTempHisBaAccountBalance.ExecuteResponse.STATUS_COMPLETE: {
            break;
          }
          case CLTempHisBaAccountBalance.ExecuteResponse.STATUS_DATA_NOT_FOUND: {
            break;
          }
          default: {
            //error 
          }
        }

        CLTempHisBaAccountBalance.ExecuteResponse rs3 = ac.clearActionCriteria();
        ctx.getLogger().debug(rs3.info().toString());

      }
    } catch (Exception ex) {
      ctx.getLogger().error("", ex);
    } finally {
      ac.getDBConnection().commit();
      ac.getDBConnection().setAutoCommit();
      ac.getDBConnection().close();
    }

  }

}
