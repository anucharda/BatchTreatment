/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.template;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import th.co.ais.cpac.cl.batch.Constants;
import th.co.ais.cpac.cl.batch.db.CLActionCriteria;
import th.co.ais.cpac.cl.batch.db.CLBatch;
import th.co.ais.cpac.cl.batch.db.CLCFGPriority;
import th.co.ais.cpac.cl.batch.db.CLNetworkType;
import th.co.ais.cpac.cl.batch.db.CLOrder;
import th.co.ais.cpac.cl.batch.db.CLProductType;
import th.co.ais.cpac.cl.batch.db.CLSFFMaster;

/**
 *
 * @author Optimus
 */
public abstract class SDDTTreatmentTemplate extends TreatmentTemplate {

  public SDDTTreatmentTemplate(String[] args) {
    super(args);
  }

  protected HashMap<String, CLActionCriteria.ActionOrderCriteria> getActionOrderCriteriaByActionId(ArrayList<BigDecimal> actionId) {
    CLActionCriteria tblCLActionCriteria = new CLActionCriteria(context.getLogger());
    CLActionCriteria.ActionOrderCriteriaResponse response = tblCLActionCriteria.getActionOrderCriteriaByActionId(actionId);
    context.getLogger().info(response.info().toString());
    switch (response.getStatusCode()) {
      case CLActionCriteria.ActionOrderCriteriaResponse.STATUS_COMPLETE: {
        HashMap<String, CLActionCriteria.ActionOrderCriteria> t = response.getResponse();
        return t;
      }
      default: {
        return null;
      }
    }
  }

  protected HashMap<String, CLNetworkType.NetworkTypeInfo> getListNetworkTypeActive() {
    CLNetworkType tblClNetworkType = new CLNetworkType(context.getLogger());
    CLNetworkType.ListNetworkTypeResponse response = tblClNetworkType.getListNetworkTypeActive();
    context.getLogger().info(response.info().toString());
    switch (response.getStatusCode()) {
      case CLActionCriteria.ActionOrderCriteriaResponse.STATUS_COMPLETE: {
        HashMap<String, CLNetworkType.NetworkTypeInfo> t = response.getResponse();
        return t;
      }
      default: {
        return null;
      }
    }
  }

  protected ArrayList<CLCFGPriority.CFGPriorityInfo> getCFGPrioritySuspend1() {
    CLCFGPriority tblCLCFGPriority = new CLCFGPriority(context.getLogger());
    CLCFGPriority.CFGPriorityInfoResponse response = tblCLCFGPriority.getCFGPrioritySuspend1();
    context.getLogger().info(response.info().toString());
    switch (response.getStatusCode()) {
      case CLActionCriteria.ActionOrderCriteriaResponse.STATUS_COMPLETE: {
        ArrayList<CLCFGPriority.CFGPriorityInfo> t = response.getResponse();
        return t;
      }
      default: {
        return null;
      }
    }
  }

  protected ArrayList<CLCFGPriority.CFGPriorityInfo> getCFGPrioritySuspend2() {
    CLCFGPriority tblCLCFGPriority = new CLCFGPriority(context.getLogger());
    CLCFGPriority.CFGPriorityInfoResponse response = tblCLCFGPriority.getCFGPrioritySuspend2();
    context.getLogger().info(response.info().toString());
    switch (response.getStatusCode()) {
      case CLActionCriteria.ActionOrderCriteriaResponse.STATUS_COMPLETE: {
        ArrayList<CLCFGPriority.CFGPriorityInfo> t = response.getResponse();
        return t;
      }
      default: {
        return null;
      }
    }
  }

  protected ArrayList<CLCFGPriority.CFGPriorityInfo> getCFGPrioritySuspend3() {
    CLCFGPriority tblCLCFGPriority = new CLCFGPriority(context.getLogger());
    CLCFGPriority.CFGPriorityInfoResponse response = tblCLCFGPriority.getCFGPrioritySuspend3();
    context.getLogger().info(response.info().toString());
    switch (response.getStatusCode()) {
      case CLActionCriteria.ActionOrderCriteriaResponse.STATUS_COMPLETE: {
        ArrayList<CLCFGPriority.CFGPriorityInfo> t = response.getResponse();
        return t;
      }
      default: {
        return null;
      }
    }
  }

  protected ArrayList<CLCFGPriority.CFGPriorityInfo> getCFGPriorityTerminate() {
    CLCFGPriority tblCLCFGPriority = new CLCFGPriority(context.getLogger());
    CLCFGPriority.CFGPriorityInfoResponse response = tblCLCFGPriority.getCFGPriorityTerminate();
    context.getLogger().info(response.info().toString());
    switch (response.getStatusCode()) {
      case CLActionCriteria.ActionOrderCriteriaResponse.STATUS_COMPLETE: {
        ArrayList<CLCFGPriority.CFGPriorityInfo> t = response.getResponse();
        return t;
      }
      default: {
        return null;
      }
    }
  }

  protected HashMap<String, CLProductType.ProductTypeInfo> getProductTypeActive() {
    CLProductType tblCLProductType = new CLProductType(context.getLogger());
    CLProductType.ProductTypeResponse response = tblCLProductType.getProductTypeActive();
    context.getLogger().info(response.info().toString());
    switch (response.getStatusCode()) {
      case CLActionCriteria.ActionOrderCriteriaResponse.STATUS_COMPLETE: {
        HashMap<String, CLProductType.ProductTypeInfo> t = response.getResponse();
        return t;
      }
      default: {
        return null;
      }
    }
  }

  protected HashMap<String, CLSFFMaster.SFFOrderTypeInfo> getSFFOrderTypeActive() {
    CLSFFMaster tblSFFMaster = new CLSFFMaster(context.getLogger());
    CLSFFMaster.SFFOrderTypeResponse response = tblSFFMaster.querySFFOrderTypeActive();
    context.getLogger().info(response.info().toString());
    switch (response.getStatusCode()) {
      case CLActionCriteria.ActionOrderCriteriaResponse.STATUS_COMPLETE: {
        HashMap<String, CLSFFMaster.SFFOrderTypeInfo> t = response.getResponse();
        return t;
      }
      default: {
        return null;
      }
    }
  }

  protected HashMap<String, CLSFFMaster.SFFOrderResonInfo> getSFFOrderResonActive() {
    CLSFFMaster tblSFFMaster = new CLSFFMaster(context.getLogger());
    CLSFFMaster.SFFOrderResonResponse response = tblSFFMaster.querySFFOrderResonActive();
    context.getLogger().info(response.info().toString());
    switch (response.getStatusCode()) {
      case CLActionCriteria.ActionOrderCriteriaResponse.STATUS_COMPLETE: {
        HashMap<String, CLSFFMaster.SFFOrderResonInfo> t = response.getResponse();
        return t;
      }
      default: {
        return null;
      }
    }
  }

  protected HashMap<String, CLSFFMaster.SFFSuspendTypeInfo> getSFFSuspendTypeActive() {
    CLSFFMaster tblSFFMaster = new CLSFFMaster(context.getLogger());
    CLSFFMaster.SFFSuspendTypeResponse response = tblSFFMaster.querySFFSuspendTypeActive();
    context.getLogger().info(response.info().toString());
    switch (response.getStatusCode()) {
      case CLActionCriteria.ActionOrderCriteriaResponse.STATUS_COMPLETE: {
        HashMap<String, CLSFFMaster.SFFSuspendTypeInfo> t = response.getResponse();
        return t;
      }
      default: {
        return null;
      }
    }
  }

  protected HashMap<String, CLSFFMaster.SFFBlacklistTypeInfo> getSFFBlacklistTypeActive() {
    CLSFFMaster tblSFFMaster = new CLSFFMaster(context.getLogger());
    CLSFFMaster.SFFBlacklistTypeResponse response = tblSFFMaster.querySFFBlacklistTypeActive();
    context.getLogger().info(response.info().toString());
    switch (response.getStatusCode()) {
      case CLActionCriteria.ActionOrderCriteriaResponse.STATUS_COMPLETE: {
        HashMap<String, CLSFFMaster.SFFBlacklistTypeInfo> t = response.getResponse();
        return t;
      }
      default: {
        return null;
      }
    }
  }

  protected HashMap<String, CLSFFMaster.SFFBlacklistSubtypeInfo> getSFFBlacklistSubTypeActive() {
    CLSFFMaster tblSFFMaster = new CLSFFMaster(context.getLogger());
    CLSFFMaster.SFFBlacklistSubtypeResponse response = tblSFFMaster.querySFFBlacklistSubTypeActive();
    context.getLogger().info(response.info().toString());
    switch (response.getStatusCode()) {
      case CLActionCriteria.ActionOrderCriteriaResponse.STATUS_COMPLETE: {
        HashMap<String, CLSFFMaster.SFFBlacklistSubtypeInfo> t = response.getResponse();
        return t;
      }
      default: {
        return null;
      }
    }
  }

  protected HashMap<String, CLSFFMaster.SFFBlacklistReasonInfo> getSFFBlacklistReasonActive() {
    CLSFFMaster tblSFFMaster = new CLSFFMaster(context.getLogger());
    CLSFFMaster.SFFBlacklistReasonResponse response = tblSFFMaster.querySFFBlacklistReasonActive();
    context.getLogger().info(response.info().toString());
    switch (response.getStatusCode()) {
      case CLActionCriteria.ActionOrderCriteriaResponse.STATUS_COMPLETE: {
        HashMap<String, CLSFFMaster.SFFBlacklistReasonInfo> t = response.getResponse();
        return t;
      }
      default: {
        return null;
      }
    }
  }

  protected boolean createOrderTerminate(CLOrder db, CLBatch.CLBatchVersionInfo batchVersionInfo, String fileName, ArrayList<ArrayList<SDDTWorkerTreatmentTemplate.ObjectCLOrder>> listOrder) {
    return createOrder(db, batchVersionInfo, fileName, listOrder, true);
  }

  protected boolean createOrderSuspend(CLOrder db, CLBatch.CLBatchVersionInfo batchVersionInfo, String fileName, ArrayList<ArrayList<SDDTWorkerTreatmentTemplate.ObjectCLOrder>> listOrder) {
    return createOrder(db, batchVersionInfo, fileName, listOrder, false);
  }

  private boolean createOrder(CLOrder db, CLBatch.CLBatchVersionInfo batchVersionInfo, String fileName, ArrayList<ArrayList<SDDTWorkerTreatmentTemplate.ObjectCLOrder>> listOrder, boolean terminate) {
    BufferedWriter writerTerminate = null;
    BufferedWriter writerBaInactive = null;
    try {
      CLOrder.CLBatcInfo clBatchInfo = db.buildCLBatcInfo();
      clBatchInfo.setBatchFileName(fileName);
      clBatchInfo.setBatchTypeId(batchVersionInfo.getBatchTypeId());
      clBatchInfo.setBatchVersionNo(batchVersionInfo.getBatchVersionNo());
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
      switch (rsClBatch.getStatusCode()) {
        case CLOrder.ExecuteResponse.STATUS_COMPLETE: {
          break;
        }
        default: {
          return false;
        }
      }

      String absoluteFile1 = "D:\\cPAC\\letter\\TERMINATE_" + fileName + ".txt";
      writerTerminate = getWriter(absoluteFile1);
      write(writerTerminate, "01|20150817_232541|52141");
      if (terminate) {
        String absoluteFile2 = "D:\\cPAC\\letter\\BAINACTIVE_" + fileName + ".txt";
        writerBaInactive = getWriter(absoluteFile2);
        write(writerBaInactive, "01|20150817_232541|52141");
      }

      BigDecimal batchId = rsClBatch.getIdentity();
      for (int i = 0; i < listOrder.size(); i++) {
        for (int k = 0; k < listOrder.size(); k++) {
          SDDTWorkerTreatmentTemplate.ObjectCLOrder clorderRequest = listOrder.get(i).get(k);
          CLOrder.CLOrderInfo order = clorderRequest.getClOrder();
          order.setBatchId(batchId);
          CLOrder.ExecuteResponse respOrder = db.insertCLOrder(order);
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
          switch (respOrderTreatment.getStatusCode()) {
            case CLOrder.ExecuteResponse.STATUS_COMPLETE: {
              break;
            }
            default: {
              return false;
            }
          }
          write(writerTerminate, clorderRequest.getMessageOrder());
          
          //??? write file 
        }
      }
      write(writerTerminate, "footter");
      write(writerBaInactive,"footter");
    } catch (Exception ex) {
      context.getLogger().error("e", ex);
    } finally {
      close(writerBaInactive);
      close(writerTerminate);
    }
    return true;
  }

  protected boolean createBlacklistOrder(CLOrder db, CLBatch.CLBatchVersionInfo batchVersionInfo, String fileName, ArrayList<ArrayList<SDDTWorkerTreatmentTemplate.ObjectBlacklist>> listOrder) {

    CLOrder.CLBatcInfo clBatchInfo = db.buildCLBatcInfo();
    clBatchInfo.setBatchFileName(fileName);
    clBatchInfo.setBatchTypeId(batchVersionInfo.getBatchTypeId());
    clBatchInfo.setBatchVersionNo(batchVersionInfo.getBatchVersionNo());
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
    switch (rsClBatch.getStatusCode()) {
      case CLOrder.ExecuteResponse.STATUS_COMPLETE: {
        break;
      }
      default: {
        return false;
      }
    }
    BigDecimal batchId = rsClBatch.getIdentity();
    for (int i = 0; i < listOrder.size(); i++) {
      for (int k = 0; k < listOrder.size(); k++) {
        SDDTWorkerTreatmentTemplate.ObjectBlacklist clorderRequest = listOrder.get(i).get(k);
        CLOrder.CLBlacklistInfo order = clorderRequest.getClBlacklist();
        order.setBatchId(batchId);
        CLOrder.ExecuteResponse respOrder = db.insertCLOrder(order);
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
        switch (respOrderTreatment.getStatusCode()) {
          case CLOrder.ExecuteResponse.STATUS_COMPLETE: {
            break;
          }
          default: {
            return false;
          }
        }
      }
    }
    return true;
  }


 
  protected void write(BufferedWriter writer, String content) {
    try {
      writer.write(content);
      writer.write(Constants.END_LINE);
    } catch (IOException ex) {
      context.getLogger().error("", ex);
    }
  }

  protected BufferedWriter getWriter(String absoluteFile) {
    Path path = Paths.get(absoluteFile);
    try {
      BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("TIS-620"));
      return writer;
    } catch (IOException ex) {
      context.getLogger().error("", ex);
    }
    return null;
  }

  protected void close(BufferedWriter writer) {
    if (writer != null) {
      try {
        writer.flush();
        writer.close();
      } catch (IOException ex) {
        context.getLogger().error("", ex);
      }
    }
  }
}
