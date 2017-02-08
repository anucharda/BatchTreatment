/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.cmd.terminate;

import java.io.BufferedWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import th.co.ais.cpac.cl.batch.Constants;
import th.co.ais.cpac.cl.batch.db.CLActionCriteria;
import th.co.ais.cpac.cl.batch.db.CLBatch;
import th.co.ais.cpac.cl.batch.db.CLCFGPriority;
import th.co.ais.cpac.cl.batch.db.CLCriteriaAttribute;
import th.co.ais.cpac.cl.batch.db.CLNetworkType;
import th.co.ais.cpac.cl.batch.db.CLOrder;
import th.co.ais.cpac.cl.batch.db.CLProductType;
import th.co.ais.cpac.cl.batch.db.PMCompany;
import th.co.ais.cpac.cl.batch.template.SDDTTreatmentTemplate;
import th.co.ais.cpac.cl.batch.template.SDDTWorkerTreatmentTemplate;

/**
 *
 * @author Optimus
 */
public class TerminateTreatment extends SDDTTreatmentTemplate {

  private static final BigDecimal BATCH_TYPE_ID_MOBILE_TERMINATE = new BigDecimal("1004001000");
  private static final BigDecimal CRITERIA_ID_MOBILE_TERMINATE = new BigDecimal("1040101");
  //
  private static final BigDecimal BATCH_TYPE_ID_BA_BLACKLIST = new BigDecimal("1004002000");
  private static final BigDecimal CRITERIA_ID_BA_BACKLIST = new BigDecimal("1100101");
  //
  private static final BigDecimal BATCH_TYPE_ID_BA_INACTIVE = new BigDecimal("1004003000");
  //private static final BigDecimal CRITERIA_ID_BA_INACTIVE = new BigDecimal("1040103");

  //
  public TerminateTreatment(String[] args) {
    super(args);
  }

  @Override
  protected String getPathDatabase() {
    return "D:\\Work\\Ais\\Apps\\Batch\\configuration\\database.properties";
  }

  @Override
  protected String getTreatmentName() {
    return "TERMINATE";
  }

  @Override
  protected boolean business() {

    //<editor-fold defaultstate="collapsed" desc="Data master">
    CLBatch.CLBatchVersionInfo terminateBatchVersionInfo = getBatchInfo(BATCH_TYPE_ID_MOBILE_TERMINATE);
    if (terminateBatchVersionInfo == null) {
      context.getLogger().debug("!!!! Exit terminateBatchVersionInfo ");
      return false;
    }

    CLBatch.CLBatchPathInfo terminateBatchPathInfo = getBatchPathInfo(BATCH_TYPE_ID_MOBILE_TERMINATE);
    if (terminateBatchPathInfo == null) {
      context.getLogger().debug("!!!! Exit terminateBatchPathInfo ");
      return false;
    }

    CLBatch.CLBatchVersionInfo baInactiveBatchVersionInfo = getBatchInfo(BATCH_TYPE_ID_BA_INACTIVE);
    if (baInactiveBatchVersionInfo == null) {
      context.getLogger().debug("!!!! Exit baInactiveBatchVersionInfo ");
      return false;
    }

    CLBatch.CLBatchPathInfo baInactiveBatchPathInfo = getBatchPathInfo(BATCH_TYPE_ID_BA_INACTIVE);
    if (baInactiveBatchPathInfo == null) {
      context.getLogger().debug("!!!! Exit baInactiveBatchPathInfo ");
      return false;
    }

    CLBatch.CLBatchVersionInfo baBlacklistBatchVersionInfo = getBatchInfo(BATCH_TYPE_ID_BA_BLACKLIST);
    if (baBlacklistBatchVersionInfo == null) {
      context.getLogger().debug("!!!! Exit baBlacklistBatchVersionInfo ");
      return false;
    }

    CLBatch.CLBatchPathInfo baBlacklistBatchPathInfo = getBatchPathInfo(BATCH_TYPE_ID_BA_BLACKLIST);
    if (baBlacklistBatchPathInfo == null) {
      context.getLogger().debug("!!!! Exit baBlacklistBatchPathInfo ");
      return false;
    }
    // parameter to worker
    ArrayList<CLCriteriaAttribute.CriteriaAttributeInfo> listParamterTerminate = getCriteriaAttribute(CRITERIA_ID_MOBILE_TERMINATE);
    if (listParamterTerminate == null) {
      context.getLogger().debug("!!!! Exit listParamterTerminate ");
      return false;
    }

    ArrayList<CLCriteriaAttribute.CriteriaAttributeInfo> listParamterBlacklist = getCriteriaAttribute(CRITERIA_ID_BA_BACKLIST);
    if (listParamterBlacklist == null) {
      context.getLogger().debug("!!!! Exit listParamterBlacklist ");
      return false;
    }

//    ArrayList<CLCriteriaAttribute.CriteriaAttributeInfo> listParamterBaInactive = getCriteriaAttribute(CRITERIA_ID_BA_INACTIVE);
//    if (listParamterBaInactive == null) {
//      context.getLogger().debug("!!!! Exit listParamterBaInactive ");
//      return false;
//    }
    HashMap<String, CLActionCriteria.ActionOrderCriteria> listActionOrderCriteria = getActionOrderCriteriaByActionId(listActionId);
    if (listActionOrderCriteria == null) {
      context.getLogger().debug("!!!! Exit listActionOrderCriteria ");
      return false;
    }

    HashMap<String, CLNetworkType.NetworkTypeInfo> listNetworkType = getListNetworkTypeActive();
    if (listNetworkType == null) {
      context.getLogger().debug("!!!! Exit listNetworkType ");
      return false;
    }

    ArrayList<CLCFGPriority.CFGPriorityInfo> listCFGPriority = getCFGPrioritySuspend1();
    if (listCFGPriority == null) {
      context.getLogger().debug("!!!! Exit listCFGPriority ");
      return false;
    }

    HashMap<String, CLProductType.ProductTypeInfo> listProductType = getProductTypeActive();
    if (listProductType == null) {
      context.getLogger().debug("!!!! Exit listProductType ");
      return false;
    }
    /*
    HashMap<String, CLSFFMaster.SFFOrderTypeInfo> listSFFOrderType = getSFFOrderTypeActive();
    if (listSFFOrderType == null) {
      context.getLogger().debug("!!!! Exit listSFFOrderType ");
      return false;
    }
    
    HashMap<String, CLSFFMaster.SFFOrderResonInfo> listSFFOrderReson = getSFFOrderResonActive();
    if (listSFFOrderReson == null) {
      context.getLogger().debug("!!!! Exit listSFFOrderReson ");
      return false;
    }
   
    HashMap<String, CLSFFMaster.SFFSuspendTypeInfo> listSFFSuspendType = getSFFSuspendTypeActive();
    if (listSFFSuspendType == null) {
      context.getLogger().debug("!!!! Exit listSFFSuspendType ");
      return false;
    }

    HashMap<String, CLSFFMaster.SFFBlacklistTypeInfo> listBlacklistType = getSFFBlacklistTypeActive();
    if (listBlacklistType == null) {
      context.getLogger().debug("!!!! Exit listBlacklistType ");
      return false;
    }
    
    HashMap<String, CLSFFMaster.SFFBlacklistSubtypeInfo> listBlalistSubType = getSFFBlacklistSubTypeActive();
    if (listBlalistSubType == null) {
      context.getLogger().debug("!!!! Exit listBlalistSubType ");
      return false;
    } 
    

    HashMap<String, CLSFFMaster.SFFBlacklistReasonInfo> listBlacklistReson = getSFFBlacklistReasonActive();
    if (listBlacklistReson == null) {
      context.getLogger().debug("!!!! Exit listBlacklistReson ");
      return false;
    }
     */
    HashMap<String, PMCompany.PMCompanyInfo> listCompany = getCompanyActive();
    if (listCompany == null) {
      context.getLogger().debug("!!!! Exit listCompany ");
      return false;
    }

    //</editor-fold>
    //
    //<editor-fold defaultstate="collapsed" desc="Create Data Process">
    if (!canPrepareTempTreatment()) {
      return false;
    }

    while (true) {
      getListTempTreatment();
      switch (tempTreatmentStatus) {
        case READY: {
          ExecutorService executor1 = Executors.newFixedThreadPool(1);
          ArrayList<WorkerTerminate> list = new ArrayList<>();
          for (int i = 0; i < listTempTreatment.size(); i++) {
            list.add(new WorkerTerminate(listParamterTerminate, listParamterBlacklist, listActionOrderCriteria, listProductType, listNetworkType, listCFGPriority, listCompany, context, listTempTreatment.get(i)));
          }
          for (int i = 0; i < list.size(); i++) {
            executor1.execute(list.get(i));
          }
          executor1.shutdown();
          while (!executor1.isTerminated()) {
            try {
              Thread.sleep(500L);
            } catch (InterruptedException ex) {
            }
          }

          for (int i = 0; i < list.size(); i++) {
            objectJob.setObjectJob(list.get(i).getDataProcess());
          }
          break;
        }
      }
      if (endLoopTempTreatment()) {
        break;
      }
      if (endLoopRetry()) {
        break;
      }
    }
    if (endLoopRetry()) {
      return false;
    }
    //</editor-fold>
    //
    //<editor-fold defaultstate="collapsed" desc="CREATE ORDER AND FILE">
    ///??? กรณีที่ 1 BA มีจำนวน mobile มากกว่า ข้อจำกัด file จะ error 
    boolean exit = false;
    while (true) {
      ArrayList<WorkerTerminate.ObjectTerminate> listJobTerminate = new ArrayList<>();

      int recordTerminate = 0;
      int recordBaInactive = 0;
      int recordBlacklist = 0;
      boolean oneBaMoreOneFile = false;
      while (true) {

        if (objectJob.getObjectJob() == null || objectJob.getObjectJob().isEmpty()) {
          //createOrderTerminate(terminateBatchVersionInfo, terminateBatchPathInfo, recordTerminate, baInactiveBatchVersionInfo, baInactiveBatchPathInfo, recordBaInactive, baBlacklistBatchVersionInfo, baBlacklistBatchPathInfo, recordBlacklist, listJobTerminate);
          exit = true;
          break;
        }

        int size = recordTerminate + objectJob.getObjectJob().get(0).getListObjectCLOrder().size();

        if (overLimitPerDay(terminateBatchVersionInfo, size)) {
          //createOrderTerminate(terminateBatchVersionInfo, terminateBatchPathInfo, recordTerminate, baInactiveBatchVersionInfo, baInactiveBatchPathInfo, recordBaInactive, baBlacklistBatchVersionInfo, baBlacklistBatchPathInfo, recordBlacklist, listJobTerminate);
          exit = true;
          break;
        }

        if (overLimitPerFile(terminateBatchVersionInfo, objectJob.getObjectJob().get(0).getListObjectCLOrder().size()) && listJobTerminate.isEmpty()) {
          oneBaMoreOneFile = true;

          WorkerTerminate.ObjectTerminate temp = objectJob.getObjectJob().remove(0);

          listJobTerminate.add(temp);

          recordTerminate += temp.getListObjectCLOrder().size();

          if (temp.isAddBaNumberInactive()) {
            recordBaInactive++;
          }
          if (temp.getListObjectBlacklist() != null && !temp.getListObjectBlacklist().isEmpty()) {
            recordBlacklist += temp.getListObjectBlacklist().size();
          }
          break;
        }

        if (overLimitPerFile(terminateBatchVersionInfo, size)) {
          //createOrderTerminate(terminateBatchVersionInfo, terminateBatchPathInfo, recordTerminate, baInactiveBatchVersionInfo, baInactiveBatchPathInfo, recordBaInactive, baBlacklistBatchVersionInfo, baBlacklistBatchPathInfo, recordBlacklist, listJobTerminate);
          //exit = true;
          break;
        }

        WorkerTerminate.ObjectTerminate temp = objectJob.getObjectJob().remove(0);

        listJobTerminate.add(temp);
        recordTerminate += temp.getListObjectCLOrder().size();
        if (temp.isAddBaNumberInactive()) {
          recordBaInactive++;
        }
        if (temp.getListObjectBlacklist() != null && !temp.getListObjectBlacklist().isEmpty()) {
          recordBlacklist += temp.getListObjectBlacklist().size();
        }
      }

      createOrderTerminate(terminateBatchVersionInfo, terminateBatchPathInfo, recordTerminate, baInactiveBatchVersionInfo, baInactiveBatchPathInfo, recordBaInactive, baBlacklistBatchVersionInfo, baBlacklistBatchPathInfo, recordBlacklist, listJobTerminate, false);

      if (exit) {
        context.getLogger().info("Terminate Treatment Successfully........");
        break;
      }
    }
    //</editor-fold>
    //
    return true;
  }

  private boolean createOrderTerminate(
    CLBatch.CLBatchVersionInfo batchDTVersion,
    CLBatch.CLBatchPathInfo terminateBatchPathInfo,
    int recordTerminate,
    CLBatch.CLBatchVersionInfo batchBaInactiveVersion,
    CLBatch.CLBatchPathInfo baInactiveBatchPathInfo,
    int recordBaInactive,
    CLBatch.CLBatchVersionInfo batchBLVersion,
    CLBatch.CLBatchPathInfo baBlacklistBatchPathInfo,
    int recordBlacklist,
    ArrayList<WorkerTerminate.ObjectTerminate> listJobTerminate,
    boolean oneBaMoreOneFile) {

    if (listJobTerminate == null || listJobTerminate.isEmpty()) {
      return true;
    }

    CLOrder db = new CLOrder(context.getLogger());
    db.buildConnection();

    boolean terminate = recordTerminate > 0;
    boolean baInactive = recordBaInactive > 0;
    boolean blacklist = recordBlacklist > 0;

    BufferedWriter writerTerminate = null;
    BufferedWriter writerBaInactive = null;
    BufferedWriter writerBlacklist = null;

    BigDecimal batchIdTerminate = null;
    BigDecimal batchIdBlacklist = null;
    //    
    //<editor-fold defaultstate="collapsed" desc="init">

    if (terminate) {
      getFileName();
      String fileName = "D:\\cPAC\\terminate\\terminate.txt";

      CLOrder.CLBatcInfo clBatchInfo = db.buildCLBatcInfo();
      clBatchInfo.setBatchFileName(fileName);
      clBatchInfo.setBatchTypeId(batchDTVersion.getBatchTypeId());
      clBatchInfo.setBatchVersionNo(batchDTVersion.getBatchVersionNo());
      clBatchInfo.setInboundStatus(Constants.InboundStatus.Pending);
      clBatchInfo.setInboundStatusDtm(new Date());
      clBatchInfo.setOutboundStatus(Constants.OutboundStatus.Generating);
      clBatchInfo.setOutboundStatusDtm(new Date());
      clBatchInfo.setResponseFileName(null);
      clBatchInfo.setBatchStartDtm(new Date());
      clBatchInfo.setBatchEndDtm(null);
      clBatchInfo.setCreated(new Date());
      clBatchInfo.setCreatedBy("system");
      clBatchInfo.setLastUpd(new Date());
      clBatchInfo.setLastUpdBy("system");

      CLOrder.ExecuteResponse rsClBatch = db.insertCLBatch(clBatchInfo);
      context.getLogger().debug(rsClBatch.info().toString());
      switch (rsClBatch.getStatusCode()) {
        case CLOrder.ExecuteResponse.STATUS_COMPLETE: {
          batchIdTerminate = rsClBatch.getIdentity();
          break;
        }
        default: {
          return false;
        }
      }
      writerTerminate = getWriter(fileName);
    }

    if (baInactive) {
      String fileName = "D:\\cPAC\\terminate\\baInactive.txt";
      CLOrder.CLBatcInfo clBatchInfo = db.buildCLBatcInfo();
      clBatchInfo.setBatchFileName(fileName);
      clBatchInfo.setBatchTypeId(batchBaInactiveVersion.getBatchTypeId());
      clBatchInfo.setBatchVersionNo(batchBaInactiveVersion.getBatchVersionNo());
      clBatchInfo.setInboundStatus(Constants.InboundStatus.Pending);
      clBatchInfo.setInboundStatusDtm(new Date());
      clBatchInfo.setOutboundStatus(Constants.OutboundStatus.Generating);
      clBatchInfo.setOutboundStatusDtm(new Date());
      clBatchInfo.setResponseFileName(null);
      clBatchInfo.setBatchStartDtm(new Date());
      clBatchInfo.setBatchEndDtm(null);
      clBatchInfo.setCreated(new Date());
      clBatchInfo.setCreatedBy("system");
      clBatchInfo.setLastUpd(new Date());
      clBatchInfo.setLastUpdBy("system");

      CLOrder.ExecuteResponse rsClBatch = db.insertCLBatch(clBatchInfo);
      context.getLogger().debug(rsClBatch.info().toString());
      switch (rsClBatch.getStatusCode()) {
        case CLOrder.ExecuteResponse.STATUS_COMPLETE: {
          batchIdTerminate = rsClBatch.getIdentity();
          break;
        }
        default: {
          return false;
        }
      }
      writerBaInactive = getWriter(fileName);
    }

    if (blacklist) {

      String fileName = "D:\\cPAC\\terminate\\blacklist.txt";

      CLOrder.CLBatcInfo clBatchInfo = db.buildCLBatcInfo();
      clBatchInfo.setBatchFileName(fileName);
      clBatchInfo.setBatchTypeId(batchBLVersion.getBatchTypeId());
      clBatchInfo.setBatchVersionNo(batchBLVersion.getBatchVersionNo());
      clBatchInfo.setInboundStatus(Constants.InboundStatus.Pending);
      clBatchInfo.setInboundStatusDtm(new Date());
      clBatchInfo.setOutboundStatus(Constants.OutboundStatus.Generating);
      clBatchInfo.setOutboundStatusDtm(new Date());
      clBatchInfo.setResponseFileName(null);
      clBatchInfo.setBatchStartDtm(new Date());
      clBatchInfo.setBatchEndDtm(null);
      clBatchInfo.setCreated(new Date());
      clBatchInfo.setCreatedBy("system");
      clBatchInfo.setLastUpd(new Date());
      clBatchInfo.setLastUpdBy("system");

      CLOrder.ExecuteResponse rsClBatch = db.insertCLBatch(clBatchInfo);
      context.getLogger().debug(rsClBatch.info().toString());
      switch (rsClBatch.getStatusCode()) {
        case CLOrder.ExecuteResponse.STATUS_COMPLETE: {
          batchIdBlacklist = rsClBatch.getIdentity();
          break;
        }
        default: {
          return false;
        }
      }
      writerBlacklist = getWriter(fileName);
    }
    //</editor-fold>
    //
    try {

      for (int i = 0; i < listJobTerminate.size(); i++) {
        WorkerTerminate.ObjectTerminate job = listJobTerminate.get(i);

        //<editor-fold defaultstate="collapsed" desc="CL_ORDER">
        for (int k = 0; k < job.getListObjectCLOrder().size(); k++) {
          SDDTWorkerTreatmentTemplate.ObjectCLOrder clorderRequest = job.getListObjectCLOrder().get(k);
          CLOrder.CLOrderInfo order = clorderRequest.getClOrder();
          order.setBatchId(batchIdTerminate);
          order.setActionStatus(Constants.ActionStatus.InProgress.getBigDecimalCode());
          CLOrder.ExecuteResponse respOrder = db.insertCLOrder(order);
          context.getLogger().debug(respOrder.info().toString());
          switch (respOrder.getStatusCode()) {
            case CLOrder.ExecuteResponse.STATUS_COMPLETE: {
              break;
            }
            default: {
              return false;
            }
          }

          CLOrder.CLOrderTreatmentInfo clorderTreatment = db.buildCLOrderTreatmentInfo();
          clorderTreatment.setOrderId(respOrder.getIdentity());
          clorderTreatment.setTreatmentId(clorderRequest.getTreatmentId());
          clorderTreatment.setCreated(new Date());
          clorderTreatment.setCreatedBy("system");
          clorderTreatment.setLastUpd(new Date());
          clorderTreatment.setLastUpdBy("system");

          CLOrder.ExecuteResponse respOrderTreatment = db.insertCLOrderTreatment(clorderTreatment);
          context.getLogger().debug(respOrderTreatment.info().toString());
          switch (respOrderTreatment.getStatusCode()) {
            case CLOrder.ExecuteResponse.STATUS_COMPLETE: {
              break;
            }
            default: {
              return false;
            }
          }
          write(writerTerminate, clorderRequest.getMessageOrder());
        }
        //</editor-fold>
        //
        //<editor-fold defaultstate="collapsed" desc="WRIET File BA Inactive">
        if (job.isAddBaNumberInactive()) {
          int last = job.getListObjectCLOrder().size() - 1;
          SDDTWorkerTreatmentTemplate.ObjectCLOrder clorderRequest = job.getListObjectCLOrder().get(last);
          write(writerBaInactive, clorderRequest.getMessageOrder());
        }
        //</editor-fold>
        //        
        //<editor-fold defaultstate="collapsed" desc="CL_BLACKLIST">
        for (int k = 0; k < job.getListObjectBlacklist().size(); k++) {
          SDDTWorkerTreatmentTemplate.ObjectBlacklist clorderRequest = job.getListObjectBlacklist().get(k);
          CLOrder.CLBlacklistInfo order = clorderRequest.getClBlacklist();
          order.setBatchId(batchIdBlacklist);
          order.setActionStatus(Constants.ActionStatus.InProgress.getBigDecimalCode());

          CLOrder.ExecuteResponse respOrder = db.insertCLOrder(order);
          context.getLogger().debug(respOrder.info().toString());
          switch (respOrder.getStatusCode()) {
            case CLOrder.ExecuteResponse.STATUS_COMPLETE: {
              break;
            }
            default: {
              return false;
            }
          }
          CLOrder.CLBlacklistTreatmentInfo clorderTreatment = db.buildCLBlacklistTreatmentInfo();
          clorderTreatment.setBlacklistId(respOrder.getIdentity());
          clorderTreatment.setTreatmentId(clorderRequest.getTreatmentId());
          clorderTreatment.setCreated(new Date());
          clorderTreatment.setCreatedBy("system");
          clorderTreatment.setLastUpd(new Date());
          clorderTreatment.setLastUpdBy("system");

          CLOrder.ExecuteResponse respOrderTreatment = db.insertCLBlacklistTreatment(clorderTreatment);
          context.getLogger().debug(respOrderTreatment.info().toString());
          switch (respOrderTreatment.getStatusCode()) {
            case CLOrder.ExecuteResponse.STATUS_COMPLETE: {
              break;
            }
            default: {
              return false;
            }
          }
          write(writerBlacklist, clorderRequest.getMessageBlacklist());
        }
        //</editor-fold>
        //
      }
      if (terminate) {
        write(writerTerminate, "footter");
      }
      if (baInactive) {
        write(writerBaInactive, "footter");
      }
      if (blacklist) {
        write(writerBlacklist, "footter");
      }

    } catch (Exception ex) {
      context.getLogger().error("e", ex);
    } finally {
      close(writerTerminate);
      close(writerBaInactive);
      close(writerBlacklist);
      db.getDBConnection().close();
      db.getDBConnection().commit();
    }

    return true;
  }

  private boolean createOrderTerminate1(
    CLBatch.CLBatchVersionInfo batchDTVersion,
    CLBatch.CLBatchPathInfo terminateBatchPathInfo,
    int recordTerminate,
    CLBatch.CLBatchVersionInfo batchBaInactiveVersion,
    CLBatch.CLBatchPathInfo baInactiveBatchPathInfo,
    int recordBaInactive,
    CLBatch.CLBatchVersionInfo batchBLVersion,
    CLBatch.CLBatchPathInfo baBlacklistBatchPathInfo,
    int recordBlacklist,
    ArrayList<WorkerTerminate.ObjectTerminate> listJobTerminate,
    ArrayList<String> bodyTerminate,
    ArrayList<String> bodyBaInactive,
    ArrayList<String> bodyBlacklist) {

    if (listJobTerminate == null || listJobTerminate.isEmpty()) {
      return true;
    }

    CLOrder db = new CLOrder(context.getLogger());
    db.buildConnection();

    boolean terminate = recordTerminate > 0;
    boolean baInactive = recordBaInactive > 0;
    boolean blacklist = recordBlacklist > 0;

    BufferedWriter writerTerminate = null;
    BufferedWriter writerBaInactive = null;
    BufferedWriter writerBlacklist = null;

    BigDecimal batchIdTerminate = null;
    BigDecimal batchIdBlacklist = null;
    //    
    //<editor-fold defaultstate="collapsed" desc="init">

    if (terminate) {
      getFileName();
      String fileName = "D:\\cPAC\\terminate\\terminate.txt";

      CLOrder.CLBatcInfo clBatchInfo = db.buildCLBatcInfo();
      clBatchInfo.setBatchFileName(fileName);
      clBatchInfo.setBatchTypeId(batchDTVersion.getBatchTypeId());
      clBatchInfo.setBatchVersionNo(batchDTVersion.getBatchVersionNo());
      clBatchInfo.setInboundStatus(Constants.InboundStatus.Pending);
      clBatchInfo.setInboundStatusDtm(new Date());
      clBatchInfo.setOutboundStatus(Constants.OutboundStatus.Generating);
      clBatchInfo.setOutboundStatusDtm(new Date());
      clBatchInfo.setResponseFileName(null);
      clBatchInfo.setBatchStartDtm(new Date());
      clBatchInfo.setBatchEndDtm(null);
      clBatchInfo.setCreated(new Date());
      clBatchInfo.setCreatedBy("system");
      clBatchInfo.setLastUpd(new Date());
      clBatchInfo.setLastUpdBy("system");

      CLOrder.ExecuteResponse rsClBatch = db.insertCLBatch(clBatchInfo);
      context.getLogger().debug(rsClBatch.info().toString());
      switch (rsClBatch.getStatusCode()) {
        case CLOrder.ExecuteResponse.STATUS_COMPLETE: {
          batchIdTerminate = rsClBatch.getIdentity();
          break;
        }
        default: {
          return false;
        }
      }
      writerTerminate = getWriter(fileName);
    }

    if (baInactive) {
      String fileName = "D:\\cPAC\\terminate\\baInactive.txt";
      CLOrder.CLBatcInfo clBatchInfo = db.buildCLBatcInfo();
      clBatchInfo.setBatchFileName(fileName);
      clBatchInfo.setBatchTypeId(batchBaInactiveVersion.getBatchTypeId());
      clBatchInfo.setBatchVersionNo(batchBaInactiveVersion.getBatchVersionNo());
      clBatchInfo.setInboundStatus(Constants.InboundStatus.Pending);
      clBatchInfo.setInboundStatusDtm(new Date());
      clBatchInfo.setOutboundStatus(Constants.OutboundStatus.Generating);
      clBatchInfo.setOutboundStatusDtm(new Date());
      clBatchInfo.setResponseFileName(null);
      clBatchInfo.setBatchStartDtm(new Date());
      clBatchInfo.setBatchEndDtm(null);
      clBatchInfo.setCreated(new Date());
      clBatchInfo.setCreatedBy("system");
      clBatchInfo.setLastUpd(new Date());
      clBatchInfo.setLastUpdBy("system");

      CLOrder.ExecuteResponse rsClBatch = db.insertCLBatch(clBatchInfo);
      context.getLogger().debug(rsClBatch.info().toString());
      switch (rsClBatch.getStatusCode()) {
        case CLOrder.ExecuteResponse.STATUS_COMPLETE: {
          batchIdTerminate = rsClBatch.getIdentity();
          break;
        }
        default: {
          return false;
        }
      }
      writerBaInactive = getWriter(fileName);
    }

    if (blacklist) {

      String fileName = "D:\\cPAC\\terminate\\blacklist.txt";

      CLOrder.CLBatcInfo clBatchInfo = db.buildCLBatcInfo();
      clBatchInfo.setBatchFileName(fileName);
      clBatchInfo.setBatchTypeId(batchBLVersion.getBatchTypeId());
      clBatchInfo.setBatchVersionNo(batchBLVersion.getBatchVersionNo());
      clBatchInfo.setInboundStatus(Constants.InboundStatus.Pending);
      clBatchInfo.setInboundStatusDtm(new Date());
      clBatchInfo.setOutboundStatus(Constants.OutboundStatus.Generating);
      clBatchInfo.setOutboundStatusDtm(new Date());
      clBatchInfo.setResponseFileName(null);
      clBatchInfo.setBatchStartDtm(new Date());
      clBatchInfo.setBatchEndDtm(null);
      clBatchInfo.setCreated(new Date());
      clBatchInfo.setCreatedBy("system");
      clBatchInfo.setLastUpd(new Date());
      clBatchInfo.setLastUpdBy("system");

      CLOrder.ExecuteResponse rsClBatch = db.insertCLBatch(clBatchInfo);
      context.getLogger().debug(rsClBatch.info().toString());
      switch (rsClBatch.getStatusCode()) {
        case CLOrder.ExecuteResponse.STATUS_COMPLETE: {
          batchIdBlacklist = rsClBatch.getIdentity();
          break;
        }
        default: {
          return false;
        }
      }
      writerBlacklist = getWriter(fileName);
    }
    //</editor-fold>
    //
    try {

      for (int i = 0; i < listJobTerminate.size(); i++) {
        WorkerTerminate.ObjectTerminate job = listJobTerminate.get(i);

        //<editor-fold defaultstate="collapsed" desc="CL_ORDER">
        for (int k = 0; k < job.getListObjectCLOrder().size(); k++) {
          SDDTWorkerTreatmentTemplate.ObjectCLOrder clorderRequest = job.getListObjectCLOrder().get(k);
          CLOrder.CLOrderInfo order = clorderRequest.getClOrder();
          order.setBatchId(batchIdTerminate);
          order.setActionStatus(Constants.ActionStatus.InProgress.getBigDecimalCode());
          CLOrder.ExecuteResponse respOrder = db.insertCLOrder(order);
          context.getLogger().debug(respOrder.info().toString());
          switch (respOrder.getStatusCode()) {
            case CLOrder.ExecuteResponse.STATUS_COMPLETE: {
              break;
            }
            default: {
              return false;
            }
          }

          CLOrder.CLOrderTreatmentInfo clorderTreatment = db.buildCLOrderTreatmentInfo();
          clorderTreatment.setOrderId(respOrder.getIdentity());
          clorderTreatment.setTreatmentId(clorderRequest.getTreatmentId());
          clorderTreatment.setCreated(new Date());
          clorderTreatment.setCreatedBy("system");
          clorderTreatment.setLastUpd(new Date());
          clorderTreatment.setLastUpdBy("system");

          CLOrder.ExecuteResponse respOrderTreatment = db.insertCLOrderTreatment(clorderTreatment);
          context.getLogger().debug(respOrderTreatment.info().toString());
          switch (respOrderTreatment.getStatusCode()) {
            case CLOrder.ExecuteResponse.STATUS_COMPLETE: {
              break;
            }
            default: {
              return false;
            }
          }
          write(writerTerminate, clorderRequest.getMessageOrder());
        }
        //</editor-fold>
        //
        //<editor-fold defaultstate="collapsed" desc="WRIET File BA Inactive">
        if (job.isAddBaNumberInactive()) {
          int last = job.getListObjectCLOrder().size() - 1;
          SDDTWorkerTreatmentTemplate.ObjectCLOrder clorderRequest = job.getListObjectCLOrder().get(last);
          write(writerBaInactive, clorderRequest.getMessageOrder());
        }
        //</editor-fold>
        //        
        //<editor-fold defaultstate="collapsed" desc="CL_BLACKLIST">
        for (int k = 0; k < job.getListObjectBlacklist().size(); k++) {
          SDDTWorkerTreatmentTemplate.ObjectBlacklist clorderRequest = job.getListObjectBlacklist().get(k);
          CLOrder.CLBlacklistInfo order = clorderRequest.getClBlacklist();
          order.setBatchId(batchIdBlacklist);
          order.setActionStatus(Constants.ActionStatus.InProgress.getBigDecimalCode());

          CLOrder.ExecuteResponse respOrder = db.insertCLOrder(order);
          context.getLogger().debug(respOrder.info().toString());
          switch (respOrder.getStatusCode()) {
            case CLOrder.ExecuteResponse.STATUS_COMPLETE: {
              break;
            }
            default: {
              return false;
            }
          }
          CLOrder.CLBlacklistTreatmentInfo clorderTreatment = db.buildCLBlacklistTreatmentInfo();
          clorderTreatment.setBlacklistId(respOrder.getIdentity());
          clorderTreatment.setTreatmentId(clorderRequest.getTreatmentId());
          clorderTreatment.setCreated(new Date());
          clorderTreatment.setCreatedBy("system");
          clorderTreatment.setLastUpd(new Date());
          clorderTreatment.setLastUpdBy("system");

          CLOrder.ExecuteResponse respOrderTreatment = db.insertCLBlacklistTreatment(clorderTreatment);
          context.getLogger().debug(respOrderTreatment.info().toString());
          switch (respOrderTreatment.getStatusCode()) {
            case CLOrder.ExecuteResponse.STATUS_COMPLETE: {
              break;
            }
            default: {
              return false;
            }
          }
          write(writerBlacklist, clorderRequest.getMessageBlacklist());
        }
        //</editor-fold>
        //
      }
      if (terminate) {
        write(writerTerminate, "footter");
      }
      if (baInactive) {
        write(writerBaInactive, "footter");
      }
      if (blacklist) {
        write(writerBlacklist, "footter");
      }

    } catch (Exception ex) {
      context.getLogger().error("e", ex);
    } finally {
      close(writerTerminate);
      close(writerBaInactive);
      close(writerBlacklist);
      db.getDBConnection().close();
      db.getDBConnection().commit();
    }

    return true;
  }

  private final ObjectJob objectJob = new ObjectJob();

  private class ObjectJob {

    private int counterTerminate = 0;
    private int counterBlacklist = 0;
    private int counterBaNumberInactive = 0;
    private final ArrayList<WorkerTerminate.ObjectTerminate> objectJob = new ArrayList<>();

    public void setObjectJob(WorkerTerminate.ObjectTerminate list) {

      if (list.getListObjectCLOrder() == null || list.getListObjectCLOrder().isEmpty()) {
        return;
      }

      objectJob.add(list);
      counterTerminate += list.getListObjectCLOrder().size();

      if (list.isAddBaNumberInactive()) {
        counterBaNumberInactive++;
      }

      if (list.getListObjectBlacklist() != null || !list.getListObjectBlacklist().isEmpty()) {
        counterBlacklist += list.getListObjectBlacklist().size();
      }
    }

    public int getCounterTerminate() {
      return counterTerminate;
    }

    public int getCounterBlacklist() {
      return counterBlacklist;
    }

    public int getCounterBaNumberInactive() {
      return counterBaNumberInactive;
    }

    public ArrayList<WorkerTerminate.ObjectTerminate> getObjectJob() {
      return objectJob;
    }

  }

  public static void main(String[] args) {
    System.out.println(args.length);
    System.out.println(Arrays.asList(args));
    String[] xx = {"111", "10"};

    new TerminateTreatment(xx).execute();
  }
}
