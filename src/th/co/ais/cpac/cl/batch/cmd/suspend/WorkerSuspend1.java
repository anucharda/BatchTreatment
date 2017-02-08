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
import th.co.ais.cpac.cl.batch.Constants;
import th.co.ais.cpac.cl.batch.db.CLActionCriteria;
import th.co.ais.cpac.cl.batch.db.CLCFGPriority;
import th.co.ais.cpac.cl.batch.db.CLCriteriaAttribute;
import th.co.ais.cpac.cl.batch.db.CLMobileInformation;
import th.co.ais.cpac.cl.batch.db.CLNetworkType;
import th.co.ais.cpac.cl.batch.db.CLOrder;
import th.co.ais.cpac.cl.batch.db.CLProductType;
import th.co.ais.cpac.cl.batch.db.CLSFFMaster;
import th.co.ais.cpac.cl.batch.db.CLTempTreatment;
import th.co.ais.cpac.cl.batch.db.PMCompany;
import th.co.ais.cpac.cl.common.Context;

/**
 *
 * @author Optimus
 */
public class WorkerSuspend1 extends Thread {

  private final String END_LINE_SUSPEND = "|";

  private final Context context;
  // detail ba 
  private final ArrayList<CLTempTreatment.TempTreatment> listBaInfo;

  // sequence parameter suspend
  private final ArrayList<CLCriteriaAttribute.CriteriaAttributeInfo> listParamterSuspend1;

  //sequence parameter blacklist
  private final ArrayList<CLCriteriaAttribute.CriteriaAttributeInfo> listParamterBlacklist;

  // String key = temp.getActionCriteriaId().toPlainString() + "_" + temp.getActionId().toPlainString() + "_" + temp.getCriteriaId().toPlainString();
  private final HashMap<String, CLActionCriteria.ActionOrderCriteria> listActionOrderCriteria;

  //key = productTypeId 
  private final HashMap<String, CLProductType.ProductTypeInfo> listProductType;

  //key = temp.getCompanyId().toPlainString() + "_" + temp.getProductTypeId() + "_" + temp.getMobilePrefix();
  //key = temp.getCompanyId().toPlainString() + "_" + temp.getProductTypeId();
  private final HashMap<String, CLNetworkType.NetworkTypeInfo> listCLNetworkType;

  //list MOBILE_STATUS_ID  
  private final ArrayList<CLCFGPriority.CFGPriorityInfo> listCLCFGPriority;
  //
  //1 
  private final HashMap<String, CLSFFMaster.SFFOrderTypeInfo> listSFFOrderType;
  //2
  private final HashMap<String, CLSFFMaster.SFFOrderResonInfo> listSFFOrderReson;
  //3
  private final HashMap<String, CLSFFMaster.SFFSuspendTypeInfo> listSFFSuspendType;
  //4
  private final HashMap<String, CLSFFMaster.SFFBlacklistTypeInfo> listBlacklistType;
  //5
  private final HashMap<String, CLSFFMaster.SFFBlacklistSubtypeInfo> listBlalistSubType;
  //6
  private final HashMap<String, CLSFFMaster.SFFBlacklistReasonInfo> listBlacklistReson;
  // company information
  private final HashMap<String, PMCompany.PMCompanyInfo> listCompany;

  public WorkerSuspend1(Context context, ArrayList<CLTempTreatment.TempTreatment> listBaInfo, ArrayList<CLCriteriaAttribute.CriteriaAttributeInfo> listParamterSuspend1, ArrayList<CLCriteriaAttribute.CriteriaAttributeInfo> listParamterBlacklist, HashMap<String, CLActionCriteria.ActionOrderCriteria> listActionOrderCriteria, HashMap<String, CLProductType.ProductTypeInfo> listProductType, HashMap<String, CLNetworkType.NetworkTypeInfo> listCLNetworkType, ArrayList<CLCFGPriority.CFGPriorityInfo> listCLCFGPriority, HashMap<String, CLSFFMaster.SFFOrderTypeInfo> listSFFOrderType, HashMap<String, CLSFFMaster.SFFOrderResonInfo> listSFFOrderReson, HashMap<String, CLSFFMaster.SFFSuspendTypeInfo> listSFFSuspendType, HashMap<String, CLSFFMaster.SFFBlacklistTypeInfo> listBlacklistType, HashMap<String, CLSFFMaster.SFFBlacklistSubtypeInfo> listBlalistSubType, HashMap<String, CLSFFMaster.SFFBlacklistReasonInfo> listBlacklistReson, HashMap<String, PMCompany.PMCompanyInfo> listCompany) {
    this.context = context;
    this.listBaInfo = listBaInfo;
    this.listParamterSuspend1 = listParamterSuspend1;
    this.listParamterBlacklist = listParamterBlacklist;
    this.listActionOrderCriteria = listActionOrderCriteria;
    this.listProductType = listProductType;
    this.listCLNetworkType = listCLNetworkType;
    this.listCLCFGPriority = listCLCFGPriority;
    this.listSFFOrderType = listSFFOrderType;
    this.listSFFOrderReson = listSFFOrderReson;
    this.listSFFSuspendType = listSFFSuspendType;
    this.listBlacklistType = listBlacklistType;
    this.listBlalistSubType = listBlalistSubType;
    this.listBlacklistReson = listBlacklistReson;
    this.listCompany = listCompany;
  }

  @Override
  public void run() {

    String mobileStatusID = "";
    ArrayList<String> listBaNo = new ArrayList<>();
    HashMap<String, CLTempTreatment.TempTreatment> mapBaInfo = new HashMap<>();

    for (int i = 0; i < listBaInfo.size(); i++) {
      CLTempTreatment.TempTreatment tt = listBaInfo.get(i);
      listBaNo.add(tt.getBaNumber());
      mapBaInfo.put(tt.getBaNumber(), tt);
    }

    CLTempTreatment.TempTreatment baInfoReff = listBaInfo.get(0);

    String keyActionOrderCriteria = baInfoReff.getActionCriteriaId().toPlainString() + "_" + baInfoReff.getActionId().toPlainString() + "_" + baInfoReff.getCriteriaId().toPlainString();
    CLActionCriteria.ActionOrderCriteria actionOrderCriteria = listActionOrderCriteria.get(keyActionOrderCriteria);

    for (int i = 0; i < listCLCFGPriority.size(); i++) {
      if (i > 0) {
        mobileStatusID += ",";
      }
      mobileStatusID += listCLCFGPriority.get(i).getMobileStatus();
    }

    CLMobileInformation mbif = new CLMobileInformation(context.getLogger());
    CLMobileInformation.CLMobileInfoResponse listMobileResp = mbif.getListMobileByMobileStatus(listBaNo, mobileStatusID);
    ArrayList<CLMobileInformation.CLMobileInfo> listMobile = listMobileResp.getResponse();

    for (int i = 0; i < listMobile.size(); i++) {
      CLMobileInformation.CLMobileInfo mobileInfo = listMobile.get(i);

      // check exampt mobile status 
      CLProductType.ProductTypeInfo productTypeId = listProductType.get(mobileInfo.getProductTypeId().toPlainString());
      String keyNetWorkType = baInfoReff.getCompanyCode()+ "_" + mobileInfo.getProductTypeId().toPlainString();
      if (productTypeId.getMobilePrefixBoo() == 'Y') {
        keyNetWorkType += "_";
        keyNetWorkType += mobileInfo.getMobileNumber();
        //mobileInfo.getMobileNumber().substring(0,2);
      }
      CLNetworkType.NetworkTypeInfo networkType = listCLNetworkType.get(keyNetWorkType);
      String messageSuspend = "";
      for (int j = 0; j < listParamterSuspend1.size(); j++) {
        CLCriteriaAttribute.CriteriaAttributeInfo paramInfo = listParamterSuspend1.get(j);
        Constants.ParameterTemplateSuspendAndTerminate paramIndex = Constants.mapParameterTemplateSuspendAndTerminate(paramInfo.getAttributeId());

        if (j > 0) {
          messageSuspend += END_LINE_SUSPEND;
        }
        switch (paramIndex) {
          case RecordType: {
            messageSuspend += Constants.RecordTypeInterface.Body.getCode();
            break;
          }
          case MobileNumber: {
            messageSuspend += mobileInfo.getMobileNumber();
            break;
          }
          case OrderType: {
            // messageSuspend += actionOrderCriteria.get
            //messageSuspend += listSFFOrderType.get(actionOrderCriteria.getOrderTypeId().toPlainString());
            break;
          }
          case SuspendType: {
            // messageSuspend += 
            //messageSuspend += listSFFSuspendType.get(actionOrderCriteria.getSuspendTypeId().toPlainString());
            break;
          }
          case ActionReason: {
            //messageSuspend += actionOrderCriteria.get
            //messageSuspend += listSFFOrderReson.get(actionOrderCriteria.getOrderReasonId().toPlainString());
            break;
          }
          case ReconnectionDate: {
            messageSuspend += "";
            break;
          }
          case NetworkType: {
            messageSuspend += networkType.getProductTypeCode();
            break;
          }
          default: {
            messageSuspend += "not-found";
            break;
          }
        }
      }
      objectSuspend1.addObjectCLOrder(messageSuspend, mapBaInfo, mobileInfo, actionOrderCriteria, networkType);
    }

    Constants.BlackListOption blacklistOption = Constants.mapBlackListOption(actionOrderCriteria.getBlacklistOption());
    if (blacklistOption == Constants.BlackListOption.Blacklist) {
      String messageBlacklist = "";
      for (int i = 0; i < listBaInfo.size(); i++) {
        CLTempTreatment.TempTreatment tempTreatment = listBaInfo.get(i);

        for (int j = 0; j < listParamterBlacklist.size(); j++) {
          CLCriteriaAttribute.CriteriaAttributeInfo paramInfo = listParamterSuspend1.get(j);
          Constants.ParameterTemplateBlacklist paramIndex = Constants.mapParameterTemplateBlacklist(paramInfo.getAttributeId());
          if (j > 0) {
            messageBlacklist += END_LINE_SUSPEND;
          }
          switch (paramIndex) {
            case RecordType: {
              messageBlacklist += Constants.RecordTypeInterface.Body.getCode();
              break;
            }
            case CANumber: {
              messageBlacklist += tempTreatment.getCaNumber();
              break;
            }
            case BANumber: {
              messageBlacklist += tempTreatment.getBaNumber();
              break;
            }
            case FRMobileNumber: {
              messageBlacklist += "";
              break;
            }
            case BlacklistDateTime: {
              messageBlacklist += "";
              //yyyymmdd000000
              //20150829000000
              break;
            }
            case BlacklistType: {
              messageBlacklist += actionOrderCriteria.getBlacklistType();
              break;
            }
            case BlacklistSubType: {
              //
              messageBlacklist += actionOrderCriteria.getBlacklistSubtype();
              break;
            }
            case Source: {
              PMCompany.PMCompanyInfo company = listCompany.get(tempTreatment.getCompanyCode());
              if ("AWN".equals(company.getCompanyCode())) {
                messageBlacklist += "AWN";
              } else {
                messageBlacklist += "AIS";
              }
              break;
            }
            case DLFlag: {
              messageBlacklist += "";
              break;
            }
            case DLReason: {
              messageBlacklist += "";
              break;
            }
            case BlacklistEndDate: {
              messageBlacklist += "";
              break;
            }
            case Remark: {
              messageBlacklist += "";
              break;
            }
            case DMSCount: {
              messageBlacklist += "DMS";
              break;
            }
            case Reason: {
              //messageBlacklist += actionOrderCriteria.getBlacklistReasonCode();
              break;
            }
            default: {
              messageBlacklist += "not-found";
            }
          }
        }
        objectSuspend1.addObjectBlacklist(messageBlacklist, tempTreatment, actionOrderCriteria);
      }
    }

  }

  //list Object CL_ORDER 
  public ObjectSuspend1 getObjectSuspend1() {
    return objectSuspend1;
  }
  private final ObjectSuspend1 objectSuspend1 = new ObjectSuspend1();

  public class ObjectCLOrder {

    private final CLOrder tbl = new CLOrder(context.getLogger());

    private CLOrder.CLOrderInfo clOrder = tbl.buildCLOrderInfo();
    private BigDecimal treatmentId;
    private String messageSuspend;

    public CLOrder.CLOrderInfo getClOrder() {
      return clOrder;
    }

    public void setClOrder(CLOrder.CLOrderInfo clOrder) {
      this.clOrder = clOrder;
    }

    public void setObjectCLOrder(String messageSuspend, CLTempTreatment.TempTreatment treatment, CLMobileInformation.CLMobileInfo mobileInfo, CLActionCriteria.ActionOrderCriteria actionOrderCriteria, CLNetworkType.NetworkTypeInfo networkType) {
      clOrder.setBaNumber(mobileInfo.getBaNumber());
      clOrder.setMobileNumber(mobileInfo.getMobileNumber());
      clOrder.setOrderActionId(treatment.getActionId());

      clOrder.setOrderCriteriaId(actionOrderCriteria.getOrderCriteriaId());
      //clOrder.setOrderTypeId(actionOrderCriteria.getOrderTypeId());
      //clOrder.setOrderReasonId(actionOrderCriteria.getOrderReasonId());
      //clOrder.setSuspendTypeId(actionOrderCriteria.getSuspendTypeId());
      clOrder.setNetworkType(networkType.getNetworkType());
      //temp.setActionStatus(Constants.ActionStatus.InProgress.getCode());
      clOrder.setActionStatusDtm(new Date());
      clOrder.setActionRemark(null);
      clOrder.setNegoId(null);
      clOrder.setBatchId(null);
      clOrder.setSffOrderNumber(null);
      clOrder.setCreated(new Date());
      clOrder.setCreatedBy("system");
      clOrder.setLastUpd(new Date());
      clOrder.setLastUpdBy("system");
      this.treatmentId = treatment.getTreatmentId();
      this.messageSuspend = messageSuspend;
    }

    public BigDecimal getTreatmentId() {
      return treatmentId;
    }

    public void setTreatmentId(BigDecimal treatmentId) {
      this.treatmentId = treatmentId;
    }

    public String getMessageSuspend() {
      return messageSuspend;
    }

    public void setMessageSuspend(String messageSuspend) {
      this.messageSuspend = messageSuspend;
    }

  }

  public class ObjectBlacklist {

    private final CLOrder tbl = new CLOrder(context.getLogger());
    private CLOrder.CLBlacklistInfo clBlacklist = null;
    private BigDecimal treatmentId;
    private String messageBlacklist;

    public ObjectBlacklist() {

    }

    public void setObjectBlacklist(String messageBlacklist, CLTempTreatment.TempTreatment treatment, CLActionCriteria.ActionOrderCriteria actionOrderCriteria) {

      clBlacklist = tbl.buildCLBlacklistInfo();
      clBlacklist.setBaNumber(treatment.getBaNumber());
      clBlacklist.setBlacklistActionId(treatment.getActionId());
      clBlacklist.setBlacklistCriteriaId(null);
      clBlacklist.setBlacklistOption(actionOrderCriteria.getBlacklistOption());
      clBlacklist.setBlacklistType(actionOrderCriteria.getBlacklistType());
      clBlacklist.setBlacklistSubtype(actionOrderCriteria.getBlacklistSubtype());
      PMCompany.PMCompanyInfo company = listCompany.get(treatment.getCompanyCode());
      if ("AWN".equals(company.getCompanyCode())) {
        messageBlacklist += "AWN";
        clBlacklist.setBlacklistSource("AWN");
      } else {
        clBlacklist.setBlacklistSource("AIS");
      }
      //clBlacklist.setBlacklistReasonCode(actionOrderCriteria.getBlacklistReasonCode());
      clBlacklist.setBlacklistRequestDate(new Date());
      //??? temp.setActionStatus();
      clBlacklist.setActionStatusDtm(new Date());
      clBlacklist.setActionRemark(null);
      clBlacklist.setOrderCriteriaId(actionOrderCriteria.getOrderCriteriaId());
      clBlacklist.setNegoId(null);
      clBlacklist.setBatchId(null);
      clBlacklist.setSffBlacklistId(null);
      clBlacklist.setCreated(new Date());
      clBlacklist.setCreatedBy("system");
      clBlacklist.setLastUpd(new Date());
      clBlacklist.setLastUpdBy("system");

      this.treatmentId = treatment.getTreatmentId();
      this.messageBlacklist = messageBlacklist;
    }

    public BigDecimal getTreatmentId() {
      return treatmentId;
    }

    public void setTreatmentId(BigDecimal treatmentId) {
      this.treatmentId = treatmentId;
    }

    public String getMessageBlacklist() {
      return messageBlacklist;
    }

    public void setMessageBlacklist(String messageBlacklist) {
      this.messageBlacklist = messageBlacklist;
    }

    public CLOrder.CLBlacklistInfo getClBlacklist() {
      return clBlacklist;
    }

    public void setClBlacklist(CLOrder.CLBlacklistInfo clBlacklist) {
      this.clBlacklist = clBlacklist;
    }

  }

  public class ObjectSuspend1 {

    public ObjectSuspend1() {
    }

    private final ArrayList<ObjectCLOrder> listObjectCLOrder = new ArrayList<>();
    private final ArrayList<ObjectBlacklist> listObjectBlacklist = new ArrayList<>();

    public ArrayList<ObjectCLOrder> getListObjectCLOrder() {
      return listObjectCLOrder;
    }

    public ArrayList<ObjectBlacklist> getListObjectBlacklist() {
      return listObjectBlacklist;
    }

    public void addObjectCLOrder(String messageSuspend, HashMap<String, CLTempTreatment.TempTreatment> mapBaInfo, CLMobileInformation.CLMobileInfo mobileInfo, CLActionCriteria.ActionOrderCriteria actionOrderCriteria, CLNetworkType.NetworkTypeInfo networkType) {
      ObjectCLOrder temp = new ObjectCLOrder();
      CLTempTreatment.TempTreatment treatment = mapBaInfo.get(mobileInfo.getBaNumber());
      temp.setObjectCLOrder(messageSuspend, treatment, mobileInfo, actionOrderCriteria, networkType);
      listObjectCLOrder.add(temp);
    }

    public void addObjectBlacklist(String messageSuspend, CLTempTreatment.TempTreatment treatment, CLActionCriteria.ActionOrderCriteria actionOrderCriteria) {
      ObjectBlacklist temp = new ObjectBlacklist();
      temp.setObjectBlacklist(messageSuspend, treatment, actionOrderCriteria);
      listObjectBlacklist.add(temp);
    }
  }

}
