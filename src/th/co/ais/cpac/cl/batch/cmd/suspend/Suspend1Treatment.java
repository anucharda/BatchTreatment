/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.cmd.suspend;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import th.co.ais.cpac.cl.batch.db.CLActionCriteria;
import th.co.ais.cpac.cl.batch.db.CLBatch;
import th.co.ais.cpac.cl.batch.db.CLCFGPriority;
import th.co.ais.cpac.cl.batch.db.CLCriteriaAttribute;
import th.co.ais.cpac.cl.batch.db.CLNetworkType;
import th.co.ais.cpac.cl.batch.db.CLOrder;
import th.co.ais.cpac.cl.batch.db.CLProductType;
import th.co.ais.cpac.cl.batch.db.CLSFFMaster;
import th.co.ais.cpac.cl.batch.db.CLTempTreatment;
import th.co.ais.cpac.cl.batch.db.PMCompany;
import th.co.ais.cpac.cl.batch.template.TreatmentTemplate;

/**
 *
 * @author Optimus
 */
public class Suspend1Treatment extends TreatmentTemplate {

  public static final BigDecimal batch_type_suspend1 = new BigDecimal("10000000000000001");
  public static final BigDecimal criteriaId_suspend1 = new BigDecimal("10000000000000001");
  public static final BigDecimal criteriaId_blacklist = new BigDecimal("10000000000000001");

  public Suspend1Treatment(String[] args) {
    super(args);
  }

  @Override
  protected String getPathDatabase() {
    return "D:\\Work\\Ais\\Apps\\Batch\\configuration\\database.properties";
  }

  @Override
  protected String getTreatmentName() {
    return "SUSPEND1";
  }

  @Override
  protected boolean business() {
    //<editor-fold defaultstate="collapsed" desc="get Batch Info">
    CLBatch.CLBatchVersionInfo batchIdInfo = getBatchInfo(batch_type_suspend1);
    //batchSmsId = insertBatchIdLetter(batchIdLetterInfo);
    //batchIdLetterInfo.getLimitPerDay();
    //batchIdLetterInfo.getLimitPerFile();
   

    //<editor-fold defaultstate="collapsed" desc="Get List Parameter">
    CLCriteriaAttribute tblCriteriaAttribute = new CLCriteriaAttribute(context.getLogger());

    CLCriteriaAttribute.CriteriaAttributeInfoResponse criteriaAttSuspend1 = tblCriteriaAttribute.getCriteriaAttribute(criteriaId_suspend1);
    ArrayList<CLCriteriaAttribute.CriteriaAttributeInfo> listParamterSuspend1 = criteriaAttSuspend1.getResponse();

    CLCriteriaAttribute.CriteriaAttributeInfoResponse criteriaAttBlacklist = tblCriteriaAttribute.getCriteriaAttribute(criteriaId_blacklist);
    ArrayList<CLCriteriaAttribute.CriteriaAttributeInfo> listParamterBlacklist = criteriaAttBlacklist.getResponse();
    //**     
    CLActionCriteria tblCLActionCriteria = new CLActionCriteria(context.getLogger());
    CLActionCriteria.ActionOrderCriteriaResponse actionOrder = tblCLActionCriteria.getActionOrderCriteriaByActionId(listActionId);
    HashMap<String, CLActionCriteria.ActionOrderCriteria> listActionOrderCriteria = actionOrder.getResponse();
    //**
    CLNetworkType tblClNetworkType = new CLNetworkType(context.getLogger());
    CLNetworkType.ListNetworkTypeResponse clNetworkType = tblClNetworkType.getListNetworkTypeActive();
    HashMap<String, CLNetworkType.NetworkTypeInfo> listCLNetworkType = clNetworkType.getResponse();
    //**
    CLCFGPriority tblCLCFGPriority = new CLCFGPriority(context.getLogger());
    CLCFGPriority.CFGPriorityInfoResponse clcfgPriority = tblCLCFGPriority.getCFGPrioritySuspend1();
    ArrayList<CLCFGPriority.CFGPriorityInfo> listCLCFGPriority = clcfgPriority.getResponse();
    //**
    CLProductType tblCLProductType = new CLProductType(context.getLogger());
    CLProductType.ProductTypeResponse clProductTypeResp = tblCLProductType.getProductTypeActive();
    HashMap<String, CLProductType.ProductTypeInfo> listProductType = clProductTypeResp.getResponse();
    //**
    CLSFFMaster tblSFFMaster = new CLSFFMaster(context.getLogger());
    //1
    CLSFFMaster.SFFOrderTypeResponse sffOrderTypeResponse = tblSFFMaster.querySFFOrderTypeActive();
    HashMap<String, CLSFFMaster.SFFOrderTypeInfo> listSFFOrderType = sffOrderTypeResponse.getResponse();
    //2
    CLSFFMaster.SFFOrderResonResponse sffOrderResonRes = tblSFFMaster.querySFFOrderResonActive();
    HashMap<String, CLSFFMaster.SFFOrderResonInfo> listSFFOrderReson = sffOrderResonRes.getResponse();
    //3
    CLSFFMaster.SFFSuspendTypeResponse sffSuspendTypeResp = tblSFFMaster.querySFFSuspendTypeActive();
    HashMap<String, CLSFFMaster.SFFSuspendTypeInfo> listSFFSuspendType = sffSuspendTypeResp.getResponse();
    //4
    CLSFFMaster.SFFBlacklistTypeResponse sffBlacklistTypeResp = tblSFFMaster.querySFFBlacklistTypeActive();
    HashMap<String, CLSFFMaster.SFFBlacklistTypeInfo> listBlacklistType = sffBlacklistTypeResp.getResponse();
    //5
    CLSFFMaster.SFFBlacklistSubtypeResponse sffBlacklistSubTypeResp = tblSFFMaster.querySFFBlacklistSubTypeActive();
    HashMap<String, CLSFFMaster.SFFBlacklistSubtypeInfo> listBlalistSubType = sffBlacklistSubTypeResp.getResponse();
    //6
    CLSFFMaster.SFFBlacklistReasonResponse sffBlacklistResonResp = tblSFFMaster.querySFFBlacklistReasonActive();
    HashMap<String, CLSFFMaster.SFFBlacklistReasonInfo> listBlacklistReson = sffBlacklistResonResp.getResponse();
    //
    PMCompany tblPMCompany = new PMCompany(context.getLogger());
    PMCompany.PMCompanyResponse companyResp = tblPMCompany.getCompanyActive();
    //.... HashMap<BigDecimal, PMCompany.PMCompanyInfo> listCompany = companyResp.getResponse();
    //</editor-fold>
    
    CLTempTreatment tempTable = new CLTempTreatment(context.getLogger());
    try {
      tempTable.buildConnection();
      // check company_id 
      CLTempTreatment.ExecuteResponse prepareResult = tempTable.prepareDataTreatment(listActionId);
      switch (prepareResult.getStatusCode()) {
        case CLTempTreatment.ExecuteResponse.STATUS_COMPLETE:
        case CLTempTreatment.ExecuteResponse.STATUS_DATA_NOT_FOUND: {
          break;
        }
        default: {
          return false;
        }
      }

      while (true) {

        boolean nextRound = true;
        CLTempTreatment.TempTreatmentResponse tblLetter = tempTable.getListTempTreatment(System.nanoTime());
        switch (tblLetter.getStatusCode()) {
          case CLTempTreatment.TempTreatmentResponse.STATUS_COMPLETE: {
            ExecutorService executor1 = Executors.newFixedThreadPool(1000);
            ArrayList<ArrayList<CLTempTreatment.TempTreatment>> listBaInfo = tblLetter.getResponse();

            ArrayList<WorkerSuspend1> list = new ArrayList<>();
            for (int i = 0; i < listBaInfo.size(); i++) {
              // ... list.add(new WorkerSuspend1(context, listBaInfo.get(i), listParamterSuspend1, listParamterBlacklist, listActionOrderCriteria, listProductType, listCLNetworkType, listCLCFGPriority, listSFFOrderType, listSFFOrderReson, listSFFSuspendType, listBlacklistType, listBlalistSubType, listBlacklistReson, listCompany));
            }
            for (int i = 0; i < list.size(); i++) {
              executor1.execute(list.get(i));
            }
            executor1.shutdown();
            while (!executor1.isTerminated()) {
              try {
                Thread.sleep(1000L);
              } catch (InterruptedException ex) {
              }
            }
            for (int i = 0; i < list.size(); i++) {
              suspend.add(list.get(i).getObjectSuspend1().getListObjectCLOrder());
              if (list.get(i).getObjectSuspend1().getListObjectBlacklist() != null && !list.get(i).getObjectSuspend1().getListObjectBlacklist().isEmpty()) {
                blacklist.add(list.get(i).getObjectSuspend1().getListObjectBlacklist());
              }
            }

            break;
          }
          case CLTempTreatment.TempTreatmentResponse.STATUS_DATA_NOT_FOUND: {
            nextRound = false;
            break;
          }
          default: {
            break;
          }
        }

        if (!nextRound) {
          break;
        }
      }

      ArrayList<ArrayList<WorkerSuspend1.ObjectCLOrder>> temp = new ArrayList<>();

      CLOrder db = new CLOrder(context.getLogger());
      if (!db.buildConnection()) {
        return false;
      }
      try {
        for (int i = 0; i < suspend.listCLOrder.size(); i++) {
          ArrayList<WorkerSuspend1.ObjectCLOrder> temp1 = suspend.listCLOrder.get(i);
          int size = temp.size() + temp1.size();

          if (size > batchIdInfo.getLimitPerFile().intValue()) {

            CLOrder.CLBatcInfo clBatchInfo = db.buildCLBatcInfo();
            CLOrder.ExecuteResponse rsClBatch = db.insertCLBatch(clBatchInfo);
            switch (rsClBatch.getStatusCode()) {
              case CLOrder.ExecuteResponse.STATUS_COMPLETE: {
                break;
              }
              default: {
                return false;
              }
            }
            //create file;

            BigDecimal batchIdentity = rsClBatch.getIdentity();

            // create file 
            // write header 
            // 
            for (int j = 0; j < temp.size(); j++) {
              ArrayList<WorkerSuspend1.ObjectCLOrder> listclorder = temp.get(j);
              for (int k = 0; k < listclorder.size(); k++) {
                WorkerSuspend1.ObjectCLOrder clorderRequest = listclorder.get(k);
                CLOrder.CLOrderInfo order = clorderRequest.getClOrder();
                order.setBatchId(batchIdentity);
                CLOrder.ExecuteResponse respOrder = db.insertCLOrder(order);

                CLOrder.CLOrderTreatmentInfo clorderTreatment = db.buildCLOrderTreatmentInfo();
                clorderTreatment.setOrderId(respOrder.getIdentity());
                clorderTreatment.setTreatmentId(clorderRequest.getTreatmentId());
                clorderTreatment.setCreated(new Date());
                clorderTreatment.setCreatedBy("system");
                clorderTreatment.setLastUpd(new Date());
                clorderTreatment.setLastUpdBy("system");
                db.insertCLOrderTreatment(clorderTreatment);

                //write file 
                clorderRequest.getMessageSuspend();

              }
            }
            //write footer file 
            temp = null;
            temp = new ArrayList<>();
            temp.add(temp1);
          } else if (size == batchIdInfo.getLimitPerFile().intValue()) {
            temp.add(temp1);
            for (int j = 0; j < batchIdInfo.getLimitPerFile().intValue(); j++) {
              //insert db , create file

            }
            temp = null;
            temp = new ArrayList<>();
          } else {
            temp.add(temp1);
          }
        }
        if (!temp.isEmpty()) {
          //save suspend and create file 
        }

        for (int i = 0; i < blacklist.size; i++) {
          //blacklist.listBlacklist.size()
          ArrayList<WorkerSuspend1.ObjectBlacklist> temp1 = blacklist.listBlacklist.get(i);
          for (int j = 0; j < temp1.size(); j++) {
            //insert db , create file
            WorkerSuspend1.ObjectBlacklist blacklistReqt = temp1.get(j);
            CLOrder.CLBlacklistInfo objReq = blacklistReqt.getClBlacklist();

            CLOrder.CLBatcInfo clBatchInfo = db.buildCLBatcInfo();
            CLOrder.ExecuteResponse rsClBatch = db.insertCLBatch(clBatchInfo);
            switch (rsClBatch.getStatusCode()) {
              case CLOrder.ExecuteResponse.STATUS_COMPLETE: {
                break;
              }
              default: {
                return false;
              }
            }
            //create file;

            BigDecimal batchIdentity = rsClBatch.getIdentity();
            objReq.setBatchId(batchIdentity);
            CLOrder.ExecuteResponse blackResp = db.insertCLOrder(objReq);

   
            CLOrder.CLBlacklistTreatmentInfo clBlackTreatment = db.buildCLBlacklistTreatmentInfo();
            clBlackTreatment.setBlacklistId(blackResp.getIdentity());
            clBlackTreatment.setTreatmentId(blacklistReqt.getTreatmentId());
            clBlackTreatment.setCreated(new Date());
            clBlackTreatment.setCreatedBy("system");
            clBlackTreatment.setLastUpd(new Date());
            clBlackTreatment.setLastUpdBy("system");
            db.insertCLBlacklistTreatment(clBlackTreatment);

            blacklistReqt.getMessageBlacklist();
            
            
          }
        }

      } finally {
        db.getDBConnection().close();
        db.getDBConnection().commit();
      }

    } finally {
      tempTable.getDBConnection().commit();
      tempTable.getDBConnection().setAutoCommit();
      tempTable.getDBConnection().close();
    }

    return false;
  }

  private Suspend suspend = new Suspend();
  private Blacklist blacklist = new Blacklist();

  private class Suspend {

    int size = 0;
    private ArrayList<ArrayList<WorkerSuspend1.ObjectCLOrder>> listCLOrder = new ArrayList<>();

    public void add(ArrayList<WorkerSuspend1.ObjectCLOrder> listCLOrder) {
      size += listCLOrder.size();
      this.listCLOrder.add(listCLOrder);
    }

  }

  private class Blacklist {

    int size = 0;
    private ArrayList<ArrayList<WorkerSuspend1.ObjectBlacklist>> listBlacklist = new ArrayList<>();

    public void add(ArrayList<WorkerSuspend1.ObjectBlacklist> listBlacklist) {
      size += listBlacklist.size();
      this.listBlacklist.add(listBlacklist);
    }

  }


}
