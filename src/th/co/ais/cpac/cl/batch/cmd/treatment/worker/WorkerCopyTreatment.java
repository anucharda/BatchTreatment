/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.cmd.treatment.worker;

import java.math.BigDecimal;
import java.util.ArrayList;
import th.co.ais.cpac.cl.batch.Constants;
import th.co.ais.cpac.cl.batch.cnf.CNFDatabase;
import th.co.ais.cpac.cl.batch.db.CLTempHisBaAccountBalance;
import th.co.ais.cpac.cl.common.Context;
import th.co.ais.cpac.cl.common.UtilityLogger;
import th.co.ais.cpac.cl.template.database.DBConnectionPools;

/**
 *
 * @author Optimus
 */
public class WorkerCopyTreatment extends Thread {

  private final Context ctx;
  private final ArrayList<CLTempHisBaAccountBalance.TempAccountBalance> info;

  public WorkerCopyTreatment(Context ctx, ArrayList<CLTempHisBaAccountBalance.TempAccountBalance> info) {
    this.ctx = ctx;
    this.info = info;
  }

  protected static DBConnectionPools getConnection(Context ctx) {
    CNFDatabase cnf = new CNFDatabase();
    DBConnectionPools<CNFDatabase, UtilityLogger> dd = new DBConnectionPools<>(cnf, ctx.getLogger());
    return dd;
  }

  @Override
  public void run() {
    if (info == null) {
      return;
    }

    CLTempHisBaAccountBalance globleExc = new CLTempHisBaAccountBalance(ctx.getLogger());
    try {
      if (!globleExc.buildConnection()) {
        ctx.getLogger().debug("cannot get database connection.");
        return;
      }
      globleExc.getDBConnection().setManualCommit();

      for (int i = 0; i < info.size(); i++) {
        if (info.get(i).getPathRunning().intValue() == 1) {
          if (info.get(i).getPathPrimaryBoo() == 'Y') {
            executePrimaryPath(info.get(i), globleExc);
            break;
          }
        }
      }

      ArrayList<CLTempHisBaAccountBalance.CurrentTreatment> currentTreatment = null;
      boolean needFindCurrent = true;

      for (int i = 0; i < info.size(); i++) {
        if (info.get(i).getPathRunning().intValue() == 1) {
          if (info.get(i).getPathPrimaryBoo() == 'N') {
            CLTempHisBaAccountBalance.TempAccountBalance data = info.get(i);
            if (needFindCurrent) {
              CLTempHisBaAccountBalance db = new CLTempHisBaAccountBalance(ctx.getLogger());
              CLTempHisBaAccountBalance.CurrentTreatmentResponse treatment = db.queryCurrentPrimaryTreatmentRunning(data.getCaNumber(), data.getBaNumber());
              switch (treatment.getStatusCode()) {
                case CLTempHisBaAccountBalance.CurrentTreatmentResponse.STATUS_COMPLETE: {
                  needFindCurrent = false;
                  currentTreatment = treatment.getResponse();
                  break;
                }
                case CLTempHisBaAccountBalance.CurrentTreatmentResponse.STATUS_DATA_NOT_FOUND: {
                  needFindCurrent = false;
                  break;
                }
                default: {
                  return;
                }
              }
            }
            executeOptionalPath(data, currentTreatment);
          }
        }
      }
    } finally {
      globleExc.getDBConnection().commit();
      globleExc.getDBConnection().setAutoCommit();
      globleExc.getDBConnection().close();
    }
  }

  private void setActionCriteriaId(CLTempHisBaAccountBalance.TempAccountBalance data) {
    String[] temp = data.getActionCriteriaId().split("\\|");
    data.setActionCriteriaIdRun(new BigDecimal(temp[1]));

    temp = data.getCriteriaId().split("\\|");
    data.setCriteriaIdRun(new BigDecimal(temp[1]));

    temp = data.getAcUnqualifyBoo().split("\\|");
    data.setAcUnqualifyBooRun(temp[1].charAt(0));

    if (data.getAcUnqualifyBooRun() == 'Y') {
      if (data.getActionStatus() == Constants.ActionStatus.WaitingtoConfirm) {
        data.setActionStatus(Constants.ActionStatus.UnqualifiedNoTreatment);
      }
    }
    // manual mode for check here ????? 
  }

  private boolean executePrimaryPath(CLTempHisBaAccountBalance.TempAccountBalance data, CLTempHisBaAccountBalance db) {

    CLTempHisBaAccountBalance.CurrentTreatmentResponse treatment = db.queryCurrentPrimaryTreatmentRunning(data.getCaNumber(), data.getBaNumber());

    ctx.getLogger().debug(treatment.info().toString());
    CLTempHisBaAccountBalance.CurrentTreatment currentTreatment = null;
    switch (treatment.getStatusCode()) {
      case CLTempHisBaAccountBalance.CurrentTreatmentResponse.STATUS_COMPLETE: {
        currentTreatment = treatment.getResponse().get(0);
        break;
      }
      case CLTempHisBaAccountBalance.CurrentTreatmentResponse.STATUS_DATA_NOT_FOUND: {
        break;
      }
      default: {
        return false;
      }
    }
    setActionCriteriaId(data);
    if (currentTreatment == null) {
      CLTempHisBaAccountBalance.ExecuteResponse resp = db.executeTreatment(data);
      ctx.getLogger().debug(resp.info().toString());
      switch (resp.getStatusCode()) {
        case CLTempHisBaAccountBalance.ExecuteResponse.STATUS_COMPLETE: {
          return true;
        }
      }
      return false;
    }

    int updateBaInfo = 0;
    int updateActionStatus = 0;
    int insertClTreatment = 0;
    Constants.ActionStatus actionChange = Constants.ActionStatus.NULL;

    //ctx.getLogger().debug("data = "+data.toString());
    //ctx.getLogger().debug("curr = "+currentTreatment.toString());
    if (!data.getCollectionPathId().equals(currentTreatment.getCollectionPathId())) {
      updateBaInfo = 1;
    }

    if (currentTreatment.getActionStatus() == Constants.ActionStatus.WaitingtoProcess) {
      updateActionStatus = 1;
      if (!data.getCollectionPathId().equals(currentTreatment.getCollectionPathId())) {
        actionChange = Constants.ActionStatus.UnqualifiedChangePath;
      } else if (!data.getActionId().equals(currentTreatment.getActionId())) {
        actionChange = Constants.ActionStatus.UnqualifiedChangeAction;
      } else if (!data.getActionCriteriaIdRun().equals(currentTreatment.getActionCriteriaId())) {
        actionChange = Constants.ActionStatus.UnqualifiedChangeActionCriteria;
      } else if (!data.getActionScheduleDtm().equals(currentTreatment.getActionScheduleDtm())) {
        actionChange = Constants.ActionStatus.UnqualifiedChangeSchedule;
      } else if (data.getActionStatus() == Constants.ActionStatus.UnqualifiedExemption) {
        actionChange = Constants.ActionStatus.UnqualifiedExemption;
      } else {
        updateActionStatus = 0;
      }
    }
    if (actionChange != Constants.ActionStatus.NULL) {
      insertClTreatment = 1;
    }

    if (updateBaInfo == 0 && updateActionStatus == 0 && insertClTreatment == 0) {
      ctx.getLogger().debug("no change .");
      return true;
    } else {
      ctx.getLogger().debug("updateBaInfo=" + updateBaInfo + ",updateActionStatus=" + updateActionStatus + ",insertClTreatment=" + insertClTreatment);
    }
    CLTempHisBaAccountBalance.ExecuteResponse resp = db.executeTreatment(updateBaInfo, updateActionStatus, insertClTreatment, currentTreatment.getTreatmentId(), actionChange, data);
    ctx.getLogger().debug(resp.info().toString());
    switch (resp.getStatusCode()) {
      case CLTempHisBaAccountBalance.ExecuteResponse.STATUS_COMPLETE: {
        return true;
      }
    }
    return false;
  }

  private void executeOptionalPath(CLTempHisBaAccountBalance.TempAccountBalance data, ArrayList<CLTempHisBaAccountBalance.CurrentTreatment> treatmentList) {

    CLTempHisBaAccountBalance.CurrentTreatment currentTreatment = null;
    if (treatmentList != null) {
      for (int i = 0; i < treatmentList.size(); i++) {
        if (treatmentList.get(i).getCollectionPathId() == data.getCollectionPathId()) {
          currentTreatment = treatmentList.get(i);
        }
      }
    }

    CLTempHisBaAccountBalance db = new CLTempHisBaAccountBalance(ctx.getLogger());

    setActionCriteriaId(data);
    if (currentTreatment == null) {
      db.executeTreatment(data);
      return;
    }

    int updateBaInfo = 0;
    int updateActionStatus = 0;
    int insertClTreatment = 0;
    Constants.ActionStatus actionChange = Constants.ActionStatus.NULL;

    if (currentTreatment.getActionStatus() == Constants.ActionStatus.WaitingtoProcess) {
      updateActionStatus = 1;
      if (data.getCollectionPathId() != currentTreatment.getCollectionPathId()) {
        actionChange = Constants.ActionStatus.UnqualifiedChangePath;
      } else if (data.getActionId() != currentTreatment.getActionId()) {
        actionChange = Constants.ActionStatus.UnqualifiedChangeAction;
      } else if (data.getActionCriteriaIdRun() != currentTreatment.getActionCriteriaId()) {
        actionChange = Constants.ActionStatus.UnqualifiedChangeActionCriteria;
      } else if (data.getActionScheduleDtm() != currentTreatment.getActionScheduleDtm()) {
        actionChange = Constants.ActionStatus.UnqualifiedChangeSchedule;
      } else if (data.getActionStatus() == Constants.ActionStatus.UnqualifiedExemption) {
        actionChange = Constants.ActionStatus.UnqualifiedExemption;
      } else {
        updateActionStatus = 0;
      }
    }
    if (actionChange != Constants.ActionStatus.NULL) {
      insertClTreatment = 1;
    }
    db.executeTreatment(updateBaInfo, updateActionStatus, insertClTreatment, currentTreatment.getTreatmentId(), actionChange, data);
  }
}

/*

    String caNo = info.get(0).getCaNumber();
    String baNo = info.get(0).getBaNumber();
    CLTempHisBaAccountBalance db = new CLTempHisBaAccountBalance(ctx.getLogger());
    CLTempHisBaAccountBalance.CurrentTreatmentResponse treatment = db.queryCurrentTreatmentRunning(caNo, baNo);
    
    
    ArrayList<CLTempHisBaAccountBalance.CurrentTreatment> listCurretTreatment = null;
    switch (treatment.getStatusCode()) {
      case CLTempHisBaAccountBalance.CurrentTreatmentResponse.STATUS_COMPLETE: {
        listCurretTreatment = treatment.getResponse();
        break;
      }
      case CLTempHisBaAccountBalance.CurrentTreatmentResponse.STATUS_DATA_NOT_FOUND: {
        break;
      }
      default: {
        //error 
        return;
      }
    }

 */
