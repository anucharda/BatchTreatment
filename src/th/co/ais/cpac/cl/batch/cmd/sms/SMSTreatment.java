/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.cmd.sms;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import th.co.ais.cpac.cl.batch.Constants;
import th.co.ais.cpac.cl.batch.db.CLAction;

import th.co.ais.cpac.cl.batch.db.CLBatch;
import th.co.ais.cpac.cl.batch.db.CLCFGMaster;
import th.co.ais.cpac.cl.batch.db.CLCriteriaTemplate;
import th.co.ais.cpac.cl.batch.db.JSNotification;
import th.co.ais.cpac.cl.batch.template.SMSLetterTreatmentTemplate;
import th.co.ais.cpac.cl.common.Context;

/**
 *
 * @author Optimus
 */
public class SMSTreatment extends SMSLetterTreatmentTemplate {

  /*
------
---- 1001001000
--- 1 = batch treatment  
--- 001 = batch id 1    sms treatment   
--- 001 = function id 1  sms message , 002  sms email message
--- 000 = other 
   */
  private static final BigDecimal BATCH_TYPE_SMS = new BigDecimal("1001001000");
  private static final BigDecimal BATCH_TYPE_SMS_EMAIL = new BigDecimal("1001002000");
  private BigDecimal batchSmsId;
  private BigDecimal batchEmailId;

  public SMSTreatment(String[] args) {
    super(args);
  }

  @Override
  protected String getPathDatabase() {
    return "C:\\cpac\\database.properties";
  }

  @Override
  protected String getTreatmentName() {
    return "SMS";
  }

  @Override
  protected boolean business() {
    //<editor-fold defaultstate="collapsed" desc="master">
    //<editor-fold defaultstate="collapsed" desc="insert batchsmsid , batchletterid">
    CLBatch.CLBatchVersionInfo batchIdSMSInfo = getBatchInfo(BATCH_TYPE_SMS);
    if (batchIdSMSInfo == null) {
      context.getLogger().debug("Batch SMS Info not found. (" + BATCH_TYPE_SMS + ")");
      return false;
    }
    CLBatch.CLBatchVersionInfo batchIdEmailInfo = getBatchInfo(BATCH_TYPE_SMS_EMAIL);
    if (batchIdEmailInfo == null) {
      context.getLogger().debug("Batch EMAIL SMS Info not found. (" + BATCH_TYPE_SMS_EMAIL + ")");
      return false;
    }

    batchSmsId = insertBatchIdSMS(batchIdSMSInfo);
    batchEmailId = insertBatchIdEmail(batchIdEmailInfo);
    //</editor-fold>
    //
    //<editor-fold defaultstate="collapsed" desc="getTemplateInfo">
    //action_id , criteria_id , template_type(sms ,letter) , language_id
    HashMap<BigDecimal, HashMap<BigDecimal, HashMap<String, HashMap<BigDecimal, CLCriteriaTemplate.CriteriaTemplateInfo>>>> templateInfo = getTemplateInfo();
    if (templateInfo == null) {
      // ???? error เธ•เน�เธญเธ�เธกเธตเธ�เธฒเธฃ update cl_batch เน€เธ�เธทเน�เธญเน€เธ�เธฅเธตเน�เธขเธ� status
      return false;
    }
    //</editor-fold>
    //
    //<editor-fold defaultstate="collapsed" desc="getParameterInfo">
    //template_id , template_language_id
    HashMap<BigDecimal, HashMap<BigDecimal, ArrayList<CLCriteriaTemplate.ParameterTemplate>>> parameterInfo = getParameterInfo();
    if (parameterInfo == null) {
      return false;
    }
    //</editor-fold>
    //
    //<editor-fold defaultstate="collapsed" desc="getSenderInfo">
    //COMPANY_ID_PRODUCT_TYPE_ID
    HashMap<String, CLCriteriaTemplate.CLSenderInfo> senderInfo = getSenderInfo();
    if (senderInfo == null) {
      return false;
    }
    //</editor-fold>
    //
    //<editor-fold defaultstate="collapsed" desc="getPathActionInfo">
    //COLLECTION_PATH_ID
    HashMap<BigDecimal, CLAction.ActionDetail> pathActionInfo = getActionInfo();
    if (pathActionInfo == null) {
      return false;
    }
    //</editor-fold>
    //
    //<editor-fold defaultstate="collapsed" desc="get LotId send SMS">
    JSNotification.LotIdNotify lotId = getLotId();
    if (lotId == null) {
      context.getLogger().debug("get Lot Id fail.");
      return false;
    } else if (lotId.getLotTypeId() == null || lotId.getLotTypeId().isEmpty()) {
      context.getLogger().debug("get Lot Id fail. " + lotId.getMessage());
      return false;
    } else {
      context.getLogger().debug("get LotTypeId success -> " + lotId.getLotTypeId() + ":" + lotId.getMessage());
    }
    //</editor-fold>
    //    
    //<editor-fold defaultstate="collapsed" desc="getCLCFGPrioritySMS">
    ArrayList<CLCFGMaster.CLCFGPriorityInfo> listStatusMobile = getCLCFGPrioritySMS();
    if (listStatusMobile == null || listStatusMobile.isEmpty()) {
      context.getLogger().debug("list mobile status not found.");
      return false;
    }
    String stringMobileStatus = "";
    for (int i = 0; i < listStatusMobile.size(); i++) {
      if (i == 0) {
        stringMobileStatus = "'" + listStatusMobile.get(i).getMobileStatus() + "'";
      } else {
        stringMobileStatus += ",";
        stringMobileStatus += "'" + listStatusMobile.get(i).getMobileStatus() + "'";
      }
    }
    //</editor-fold>
    //</editor-fold>
    //
    //<editor-fold defaultstate="collapsed" desc="Worker">    

    if (!canPrepareTempTreatment()) {
      context.getLogger().debug("canPrepareTempTreatment end.");
      return false;
    }

    if (!canPrepareCriteriaLevel()) {
      return false;
    }

    while (true) {
      getListGroupCriteriaTempTreatment();

      switch (tempTreatmentStatus) {
        case READY: {
          ExecutorService executor1 = Executors.newFixedThreadPool(1);
          for (int i = 0; i < listTempTreatment.size(); i++) {
            Context tempContext = context.clone(listTempTreatment.get(i).get(0).getCaNumber());
            executor1.execute(new WorkerSendSMS(tempContext, listTempTreatment.get(i), batchSmsId, batchEmailId, lotId.getLotTypeId(), templateInfo, senderInfo, pathActionInfo, parameterInfo, stringMobileStatus));
          }
          executor1.shutdown();
          while (!executor1.isTerminated()) {
            try {
              Thread.sleep(50L);
            } catch (InterruptedException ex) {
            }
          }
          break;
        }
      }
      if (endLoopTempTreatment()) {
        context.getLogger().info("Data Empty...");
        break;
      }
      if (endLoopRetry()) {
        context.getLogger().info("Error Max..");
        break;
      }
    }

    //</editor-fold>
    //
    return true;
  }

  private BigDecimal insertBatchIdSMS(CLBatch.CLBatchVersionInfo batchInfo) {
    CLBatch clBatch = new CLBatch(context.getLogger());
    Date current = new Date();
    CLBatch.CLBatcInfo clBatchInfo = clBatch.buildCLBatcInfo();
    clBatchInfo.setBatchTypeId(BATCH_TYPE_SMS);
    clBatchInfo.setBatchVersionNo(batchInfo.getBatchVersionNo());
    clBatchInfo.setBatchStartDtm(current);
    clBatchInfo.setOutboundStatus(Constants.OutboundStatus.NoOutboundResponse);
    clBatchInfo.setOutboundStatusDtm(current);
    clBatchInfo.setInboundStatus(Constants.InboundStatus.NoInboundResponse);
    clBatchInfo.setOutboundStatusDtm(current);
    clBatchInfo.setCreated(current);
    clBatchInfo.setCreatedBy("system");
    clBatchInfo.setLastUpd(current);
    clBatchInfo.setLastUpdBy("system");

    CLBatch.ExecuteResponse clBatchResp = clBatch.insertCLBatch(clBatchInfo);
    switch (clBatchResp.getStatusCode()) {
      case CLBatch.ExecuteResponse.STATUS_COMPLETE: {
        return clBatchResp.getIdentity();
      }
      default: {
        return null;
      }
    }
  }

  private BigDecimal insertBatchIdEmail(CLBatch.CLBatchVersionInfo batchInfo) {
    CLBatch clBatch = new CLBatch(context.getLogger());
    Date current = new Date();
    CLBatch.CLBatcInfo clBatchInfo = clBatch.buildCLBatcInfo();
    clBatchInfo.setBatchTypeId(BATCH_TYPE_SMS_EMAIL);
    clBatchInfo.setBatchVersionNo(batchInfo.getBatchVersionNo());
    clBatchInfo.setBatchStartDtm(current);
    clBatchInfo.setOutboundStatus(Constants.OutboundStatus.NoOutboundResponse);
    clBatchInfo.setOutboundStatusDtm(current);
    clBatchInfo.setInboundStatus(Constants.InboundStatus.NoInboundResponse);
    clBatchInfo.setOutboundStatusDtm(current);
    clBatchInfo.setCreated(current);
    clBatchInfo.setCreatedBy("system");
    clBatchInfo.setLastUpd(current);
    clBatchInfo.setLastUpdBy("system");

    CLBatch.ExecuteResponse clBatchResp = clBatch.insertCLBatch(clBatchInfo);
    switch (clBatchResp.getStatusCode()) {
      case CLBatch.ExecuteResponse.STATUS_COMPLETE: {
        return clBatchResp.getIdentity();
      }
      default: {
        return null;
      }
    }
  }

  protected ArrayList< CLCFGMaster.CLCFGPriorityInfo> getCLCFGPrioritySMS() {
    CLCFGMaster tbl = new CLCFGMaster(context.getLogger());
    CLCFGMaster.CLCFGPriorityResponse rs = tbl.getCLCFGPrioritySMS();
    context.getLogger().debug(rs.info().toString());
    switch (rs.getStatusCode()) {
      case CLCFGMaster.CLCFGPriorityResponse.STATUS_COMPLETE: {
        return rs.getResponse();
      }
      case CLCriteriaTemplate.CLSenderResponse.STATUS_DATA_NOT_FOUND: {
        return new ArrayList();
      }
      default: {
        return null;
      }
    }
  }

  public static void main(String[] args) {
    System.out.println(args.length);
    System.out.println(Arrays.asList(args));
    String[] xx = {"1", "6"};

    new SMSTreatment(xx).execute();
  }

}
