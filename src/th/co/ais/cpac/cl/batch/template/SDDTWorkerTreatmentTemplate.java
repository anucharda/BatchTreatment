/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.template;

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
import th.co.ais.cpac.cl.batch.db.CLTempTreatment;
import th.co.ais.cpac.cl.batch.db.PMCompany;
import th.co.ais.cpac.cl.common.Context;

/**
 *
 * @author Optimus
 */
public class SDDTWorkerTreatmentTemplate extends TreatmentWorkerTemplate {

  protected final String END_LINE_ORDER_MESSAGE = "|";

  // sequence parameter suspend , terminate
  protected final ArrayList<CLCriteriaAttribute.CriteriaAttributeInfo> listParamter;

  //sequence parameter blacklist
  protected final ArrayList<CLCriteriaAttribute.CriteriaAttributeInfo> listParamterBlacklist;

  // String key = temp.getActionCriteriaId().toPlainString() + "_" + temp.getActionId().toPlainString() + "_" + temp.getCriteriaId().toPlainString();
  protected final HashMap<String, CLActionCriteria.ActionOrderCriteria> listActionOrderCriteria;

  //key = productTypeId 
  protected final HashMap<String, CLProductType.ProductTypeInfo> listProductType;

  //key = temp.getCompanyId().toPlainString() + "_" + temp.getProductTypeId() + "_" + temp.getMobilePrefix();
  //key = temp.getCompanyId().toPlainString() + "_" + temp.getProductTypeId();
  protected final HashMap<String, CLNetworkType.NetworkTypeInfo> listCLNetworkType;

  //list MOBILE_STATUS_ID  
  protected final ArrayList<CLCFGPriority.CFGPriorityInfo> listCLCFGPriority;
  //
  /*
  //1 
  protected final HashMap<String, CLSFFMaster.SFFOrderTypeInfo> listSFFOrderType;
  //2
  protected final HashMap<String, CLSFFMaster.SFFOrderResonInfo> listSFFOrderReson;
  //3
  protected final HashMap<String, CLSFFMaster.SFFSuspendTypeInfo> listSFFSuspendType;
  //4
  protected final HashMap<String, CLSFFMaster.SFFBlacklistTypeInfo> listBlacklistType;
  //5
  protected final HashMap<String, CLSFFMaster.SFFBlacklistSubtypeInfo> listBlalistSubType;
  //6
  protected final HashMap<String, CLSFFMaster.SFFBlacklistReasonInfo> listBlacklistReson;
   */
  // company information  
  protected final HashMap<String, PMCompany.PMCompanyInfo> listCompany;

  public SDDTWorkerTreatmentTemplate(
    ArrayList<CLCriteriaAttribute.CriteriaAttributeInfo> listParamter,
    ArrayList<CLCriteriaAttribute.CriteriaAttributeInfo> listParamterBlacklist,
    HashMap<String, CLActionCriteria.ActionOrderCriteria> listActionOrderCriteria,
    HashMap<String, CLProductType.ProductTypeInfo> listProductType,
    HashMap<String, CLNetworkType.NetworkTypeInfo> listCLNetworkType,
    ArrayList<CLCFGPriority.CFGPriorityInfo> listCLCFGPriority,
    HashMap<String, PMCompany.PMCompanyInfo> listCompany,
    Context context,
    ArrayList<CLTempTreatment.TempTreatment> listTempTreatment) {
    super(context, listTempTreatment);
    this.listParamter = listParamter;
    this.listParamterBlacklist = listParamterBlacklist;
    this.listActionOrderCriteria = listActionOrderCriteria;
    this.listProductType = listProductType;
    this.listCLNetworkType = listCLNetworkType;
    this.listCLCFGPriority = listCLCFGPriority;
    //this.listSFFOrderType = listSFFOrderType;
    //this.listSFFOrderReson = listSFFOrderReson;
    //this.listSFFSuspendType = listSFFSuspendType;
    //this.listBlacklistType = listBlacklistType;
    //this.listBlalistSubType = listBlalistSubType;
    //this.listBlacklistReson = listBlacklistReson;
    this.listCompany = listCompany;
  }

  private CLActionCriteria.ActionOrderCriteria actionOrderCriteria = null;

  protected CLActionCriteria.ActionOrderCriteria getActionOrderCriteria() {
    if (actionOrderCriteria != null) {
      return actionOrderCriteria;
    }
    CLTempTreatment.TempTreatment baReff = getBaRefference();

    String keyActionOrderCriteria = baReff.getActionCriteriaId().toPlainString() + "_" + baReff.getActionId().toPlainString() + "_" + baReff.getCriteriaId().toPlainString();
    actionOrderCriteria = listActionOrderCriteria.get(keyActionOrderCriteria);
    return actionOrderCriteria;
  }

  protected ArrayList<CLMobileInformation.CLMobileInfo> getListMobileProcess() {
    ArrayList<String> mobileStatusID = new ArrayList<>();
    for (int i = 0; i < listCLCFGPriority.size(); i++) {
      mobileStatusID.add(listCLCFGPriority.get(i).getMobileStatus());
    }
    CLMobileInformation mbif = new CLMobileInformation(context.getLogger());
    CLMobileInformation.CLMobileInfoResponse listMobileResp = mbif.getListMobileByMobileStatus(getListBaNumber(), mobileStatusID);
    context.getLogger().debug(listMobileResp.info().toString());
    ArrayList<CLMobileInformation.CLMobileInfo> listMobile = listMobileResp.getResponse();
    return listMobile;
  }

  protected CLNetworkType.NetworkTypeInfo getNetworkType(CLMobileInformation.CLMobileInfo mobileInfo) {
    CLProductType.ProductTypeInfo productTypeId = listProductType.get(mobileInfo.getProductTypeId().toPlainString());
    String keyNetWorkType = getBaRefference().getCompanyCode() + "_" + mobileInfo.getProductTypeId().toPlainString();
    if (productTypeId.getMobilePrefixBoo() == 'Y') {
      keyNetWorkType += "_";
      keyNetWorkType += mobileInfo.getMobileNumber();
      //??? mobileInfo.getMobileNumber().substring(0,2);
    }
    CLNetworkType.NetworkTypeInfo networkType = listCLNetworkType.get(keyNetWorkType);
    return networkType;
  }

  protected String getMessageOrderSuspendOrTerminate(CLMobileInformation.CLMobileInfo mobileInfo) {
    CLNetworkType.NetworkTypeInfo networkType = getNetworkType(mobileInfo);
    if(networkType == null){
      context.getLogger().debug("NetworkType = "+getBaRefference().getCompanyCode()+"_"+mobileInfo.getProductTypeId().toPlainString());
      return null;
    }
    String messageOrder = "";
    for (int j = 0; j < listParamter.size(); j++) {
      CLCriteriaAttribute.CriteriaAttributeInfo paramInfo = listParamter.get(j);
      Constants.ParameterTemplateSuspendAndTerminate paramIndex = Constants.mapParameterTemplateSuspendAndTerminate(paramInfo.getAttributeId());

      if (j > 0) {
        messageOrder += END_LINE_ORDER_MESSAGE;
      }
      switch (paramIndex) {
        case RecordType: {
          messageOrder += Constants.RecordTypeInterface.Body.getCode();
          break;
        }
        case MobileNumber: {
          messageOrder += mobileInfo.getMobileNumber();
          break;
        }
        case OrderType: {
          // messageSuspend += actionOrderCriteria.get
          messageOrder += actionOrderCriteria.getOrderType();
          break;
        }
        case SuspendType: {
          // messageSuspend += 
          messageOrder += actionOrderCriteria.getSuspendType();
          break;
        }
        case ActionReason: {
          //messageSuspend += actionOrderCriteria.get
          messageOrder += actionOrderCriteria.getOrderReason();
          break;
        }
        case ReconnectionDate: {
          messageOrder += "";
          break;
        }
        case NetworkType: {
          messageOrder += networkType.getProductTypeCode();
          break;
        }
        default: {
          messageOrder += "not-found";
          break;
        }
      }
    }
    return messageOrder;
  }

  protected String getMessageBlacklist(CLTempTreatment.TempTreatment tempTreatment) {
    return getMessageBlacklistOrDeBlacklist(tempTreatment);
  }

  protected String getMessageDeBlacklist(CLTempTreatment.TempTreatment tempTreatment) {
    return getMessageBlacklistOrDeBlacklist(tempTreatment);
  }

  private String getMessageBlacklistOrDeBlacklist(CLTempTreatment.TempTreatment tempTreatment) {

    String message = "";
    for (int j = 0; j < listParamterBlacklist.size(); j++) {

      CLCriteriaAttribute.CriteriaAttributeInfo paramInfo = listParamterBlacklist.get(j);
      Constants.ParameterTemplateBlacklist paramIndex = Constants.mapParameterTemplateBlacklist(paramInfo.getAttributeId());
      if (j > 0) {
        message += END_LINE_ORDER_MESSAGE;
      }
      switch (paramIndex) {
        case RecordType: {
          message += Constants.RecordTypeInterface.Body.getCode();
          break;
        }
        case CANumber: {
          message += tempTreatment.getCaNumber();
          break;
        }
        case BANumber: {
          message += tempTreatment.getBaNumber();
          break;
        }
        case FRMobileNumber: {
          message += "";
          break;
        }
        case BlacklistDateTime: {
          message += "";
          //yyyymmdd000000
          //20150829000000
          break;
        }
        case BlacklistType: {
          message += actionOrderCriteria.getBlacklistType();
          break;
        }
        case BlacklistSubType: {
          //
          message += actionOrderCriteria.getBlacklistSubtype();
          break;
        }
        case Source: {
          PMCompany.PMCompanyInfo company = listCompany.get(tempTreatment.getCompanyCode());
          if ("AWN".equals(company.getCompanyCode())) {
            message += "AWN";
          } else {
            message += "AIS";
          }
          break;
        }
        case DLFlag: {
          message += "";
          break;
        }
        case DLReason: {
          message += "";
          break;
        }
        case BlacklistEndDate: {
          message += "";
          break;
        }
        case Remark: {
          message += "";
          break;
        }
        case DMSCount: {
          message += "DMS";
          break;
        }
        case Reason: {
          message += actionOrderCriteria.getBlacklistReason();
          break;
        }
        default: {
          message += "not-found";
        }
      }
    }
    return message;
  }

  protected boolean canBlacklist() {
    /*
SELECT * 
FROM CPDB..SFF_BLACKLIST
WHERE BA_INFO.SFF_ACCOUNT_ID = SFF_BLACKLIST.BILLING_ACCNT_ID and BLACKLIST_TYPE  = 'xxx' and  BLACKLIST_SUBTYPE = 'yyyy' and BLACKLIST_END_DT is null     
     */

    return true;
  }

//list Object CL_ORDER 
  protected ObjectCLOrder buildObjectCLOrder(String messageOrder, CLTempTreatment.TempTreatment treatment, CLMobileInformation.CLMobileInfo mobileInfo, CLNetworkType.NetworkTypeInfo networkType) {
    ObjectCLOrder objectClOrder = new ObjectCLOrder();
    objectClOrder.setObjectCLOrder(messageOrder, treatment, mobileInfo, getActionOrderCriteria(), networkType);
    return objectClOrder;
  }

  public ObjectBlacklist buildObjectBlacklist(String messageOrder, CLTempTreatment.TempTreatment treatment) {
    ObjectBlacklist temp = new ObjectBlacklist();
    temp.setObjectBlacklist(messageOrder, treatment, getActionOrderCriteria());
    return temp;
  }

  public class ObjectCLOrder {

    // private final CLOrder tbl = new CLOrder(context.getLogger());
    private CLOrder.CLOrderInfo clOrder = new CLOrder(context.getLogger()).buildCLOrderInfo();
    private BigDecimal treatmentId;
    private String messageOrder;

    public CLOrder.CLOrderInfo getClOrder() {
      return clOrder;
    }

    public void setClOrder(CLOrder.CLOrderInfo clOrder) {
      this.clOrder = clOrder;
    }

    public void setObjectCLOrder(String messageOrder, CLTempTreatment.TempTreatment treatment, CLMobileInformation.CLMobileInfo mobileInfo, CLActionCriteria.ActionOrderCriteria actionOrderCriteria, CLNetworkType.NetworkTypeInfo networkType) {
      clOrder.setBaNumber(mobileInfo.getBaNumber());
      clOrder.setMobileNumber(mobileInfo.getMobileNumber());
      clOrder.setOrderActionId(treatment.getActionId());
      
      clOrder.setOrderCriteriaId(null);
      
      clOrder.setOrderType(actionOrderCriteria.getOrderType());
      clOrder.setOrderReason(actionOrderCriteria.getOrderReason());
      clOrder.setSuspendType(actionOrderCriteria.getSuspendType());
      clOrder.setNetworkType(networkType.getNetworkType());
      clOrder.setActionStatus(Constants.ActionStatus.InProgress.getBigDecimalCode());
      clOrder.setActionStatusDtm(new Date());
      clOrder.setActionRemark(null);
      clOrder.setNegoId(null);
      clOrder.setBatchId(null);
      clOrder.setSffOrderNumber(null);
      clOrder.setEndRequestBoo('N');
      clOrder.setCreated(new Date());
      clOrder.setCreatedBy("system");
      clOrder.setLastUpd(new Date());
      clOrder.setLastUpdBy("system");
      this.treatmentId = treatment.getTreatmentId();
      this.messageOrder = messageOrder;
    }

    public BigDecimal getTreatmentId() {
      return treatmentId;
    }

    public void setTreatmentId(BigDecimal treatmentId) {
      this.treatmentId = treatmentId;
    }

    public String getMessageOrder() {
      return messageOrder;
    }

    public void setMessageOrder(String messageOrder) {
      this.messageOrder = messageOrder;
    }

  }

  public class ObjectBlacklist {

    // private final CLOrder tbl = new CLOrder(context.getLogger());
    private CLOrder.CLBlacklistInfo clBlacklist = null;
    private BigDecimal treatmentId;
    private String messageBlacklist;

    public ObjectBlacklist() {

    }

    public void setObjectBlacklist(String messageBlacklist, CLTempTreatment.TempTreatment treatment, CLActionCriteria.ActionOrderCriteria actionOrderCriteria) {

      clBlacklist = new CLOrder(context.getLogger()).buildCLBlacklistInfo();
      clBlacklist.setBaNumber(treatment.getBaNumber());
      clBlacklist.setBlacklistActionId(treatment.getActionId());
      clBlacklist.setBlacklistCriteriaId(actionOrderCriteria.getOrderCriteriaId());
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
      clBlacklist.setBlacklistReasonCode(actionOrderCriteria.getBlacklistReason());
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

}
