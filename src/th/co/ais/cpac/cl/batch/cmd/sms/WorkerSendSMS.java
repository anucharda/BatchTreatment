/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.cmd.sms;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import th.co.ais.cpac.cl.batch.Constants;
import th.co.ais.cpac.cl.batch.db.CLAction;
import th.co.ais.cpac.cl.batch.db.CLContactInfo;
import th.co.ais.cpac.cl.batch.db.CLCriteriaTemplate;
import th.co.ais.cpac.cl.batch.db.CLMobileInformation;
import th.co.ais.cpac.cl.batch.db.CLSMSTreatment;
import th.co.ais.cpac.cl.batch.db.CLTempTreatment;
import th.co.ais.cpac.cl.batch.db.JSNotification;
import th.co.ais.cpac.cl.batch.db.PMInvoice;
import th.co.ais.cpac.cl.batch.db.PMInvoice.SMSParameterValue;
import th.co.ais.cpac.cl.batch.template.SMSLetterWorkerTreatmentTemplate;
import th.co.ais.cpac.cl.batch.util.UtilityDate;
import th.co.ais.cpac.cl.common.Context;

/**
 *
 * @author Optimus
 */
public class WorkerSendSMS extends SMSLetterWorkerTreatmentTemplate {

  private final String KEY_MOBILE_REFF = "PARAM_MOBILE_REFFERENCE";
  private final String KEY_BA_REFF = "PARAM_BA_REFFERENCE";
  private final String KEY_BALANCE_MNY = "PARAM_BALANCE_MNY";
  private final String KEY_CRM_DUE_DAT = "PARAM_CRM_DUE_DAT";
  private final String KEY_BILL_DATE = "PARAM_BILL_DATE";
  private final String KEY_SUSPEND_DATE = "PARAM_SUSPEND_DATE";
  private final String KEY_BILL_NUM = "PARAM_BILL_NUM";
  private final String KEY_COUNT_INVOICE = "PARAM_COUNT_INVOICE";
  private final String KEY_BILLING_CYCLE = "PARAM_BILLING_CYCLE";
  private final String KEY_INVOICE_MONTH = "PARAM_INVOICE_MONTH";
  private final String KEY_INVOICE_AMOUNT = "PARAM_INVOICE_AMOUNT";

  private final HashMap<String, Object> commonParam = new HashMap<>();

  private final BigDecimal batchIdSms;
  private final BigDecimal batchIdEmail;
  private final String lotTypeId;
  // actionid , criteriaid 
  private final HashMap<BigDecimal, HashMap<BigDecimal, HashMap<String, HashMap<BigDecimal, CLCriteriaTemplate.CriteriaTemplateInfo>>>> criteriaInfo;
  // companyid+"_"+product_type_id
  private final HashMap<String, CLCriteriaTemplate.CLSenderInfo> senderInfo;
  // pathid
  private final HashMap<BigDecimal, CLAction.ActionDetail> actionInfo;

  //templateId , TemplateLanguageId
  private final HashMap<BigDecimal, HashMap<BigDecimal, ArrayList<CLCriteriaTemplate.ParameterTemplate>>> parameter;

  //list status for sms 
  String listStatusMobile;

  public WorkerSendSMS(
    Context context, ArrayList<CLTempTreatment.TempTreatment> listTempTreatment, BigDecimal batchIdSms,
    BigDecimal batchIdEmail,
    String lotTypeId, HashMap<BigDecimal, HashMap<BigDecimal, HashMap<String, HashMap<BigDecimal, CLCriteriaTemplate.CriteriaTemplateInfo>>>> criteriaInfo,
    HashMap<String, CLCriteriaTemplate.CLSenderInfo> senderInfo, HashMap<BigDecimal, CLAction.ActionDetail> actionInfo,
    HashMap<BigDecimal, HashMap<BigDecimal, ArrayList<CLCriteriaTemplate.ParameterTemplate>>> parameter, String listStatusMobile) {
    super(context, listTempTreatment);
    this.batchIdSms = batchIdSms;
    this.batchIdEmail = batchIdEmail;
    this.criteriaInfo = criteriaInfo;
    this.senderInfo = senderInfo;
    this.actionInfo = actionInfo;
    this.parameter = parameter;
    this.lotTypeId = lotTypeId;
    this.listStatusMobile = listStatusMobile;
  }

  @Override
  public void run() {
    context.getLogger().debug("Start WorkerSendSMS ..........");
    if (context == null || batchIdSms == null || batchIdEmail == null || criteriaInfo == null || senderInfo == null || actionInfo == null || listTempTreatment == null) {
      return;
    }

    setListBaNumberAndMapBaNumber();

    CLTempTreatment.TempTreatment tempBA = listTempTreatment.get(0);
    HashMap<BigDecimal, CLCriteriaTemplate.CriteriaTemplateInfo> sms_criteria = criteriaInfo.get(tempBA.getActionId()).get(tempBA.getCriteriaId()).get(Constants.TemplateType.SMS.getKeys());
    HashMap<BigDecimal, CLCriteriaTemplate.CriteriaTemplateInfo> email_criteria = criteriaInfo.get(tempBA.getActionId()).get(tempBA.getCriteriaId()).get(Constants.TemplateType.EMAIL.getKeys());

    if (sms_criteria != null) {
      context.getLogger().debug("sms_template");
      smsMessage(getListBaNumber(), sms_criteria);
    }

    if (email_criteria != null) {
      // emailMessage(listMobile.getResponse(), email_criteria);
    }

  }

  private void smsMessage(ArrayList<String> listBaNo, HashMap<BigDecimal, CLCriteriaTemplate.CriteriaTemplateInfo> templateInfo) {
    CLCriteriaTemplate.CriteriaTemplateInfo templateDefault = null;

    //<editor-fold defaultstate="collapsed" desc="Find Template Default">    
    Iterator<CLCriteriaTemplate.CriteriaTemplateInfo> list = templateInfo.values().iterator();
    while (list.hasNext()) {
      CLCriteriaTemplate.CriteriaTemplateInfo temp = list.next();
      if (temp.getDefaultBoo() == 'Y') {
        templateDefault = temp;
        break;
      }
      if (templateDefault == null) {
        templateDefault = temp;
        continue;
      }
      if (temp.getLanguage().isThaiLanguage()) {
        templateDefault = temp;
      }
    }
    if (templateDefault == null) {
      context.getLogger().debug("template default not found.");
      return;
    }
    context.getLogger().debug(templateDefault.toString());
    //</editor-fold>
    //
    CLTempTreatment.TempTreatment baReff = getBaRefference();

    Constants.CriteriaLevel criteriaLevel = baReff.getCriteriaLevel();
    Constants.ContactLevel contactLevel = templateDefault.getContactLevel();

    Constants.ContactOptionSMS contactOption = Constants.mapContactOptionSMS(templateDefault.getContactOptionId().intValue());
    int contactQty = 99999;
    if ('Y' == templateDefault.getContactQtyBoo()) {
      contactQty = templateDefault.getContactQty().intValue();
    }
    //
    //<editor-fold defaultstate="collapsed" desc="get list mobile send sms">

    CLMobileInformation.CLMobileInfoResponse listMobile;
    switch (contactOption) {
      case Contact_Number: {
        String contactNo;
        switch (contactLevel) {
          case CA: {
            contactNo = baReff.getCaNumber();
            break;
          }
          case BA: {
            contactNo = baReff.getBaNumber();
            break;
          }
          case SA: {
            contactNo = baReff.getSaNumber();
            break;
          }
          default: {
            contactNo = "00000";
          }
        }
        context.getLogger().debug("find context " + contactLevel.name() + ":" + contactNo);
        CLMobileInformation mbif = new CLMobileInformation(context.getLogger());
        listMobile = mbif.getListMobileByContact(contactLevel, contactNo);
        context.getLogger().debug(listMobile.info().toString());
        break;
      }

      case Mobile_Number: {
        context.getLogger().debug("find mobile");
        CLMobileInformation mbif = new CLMobileInformation(context.getLogger());
        listMobile = mbif.getListMobileByMobileStatus(listBaNo, listStatusMobile);
        context.getLogger().debug(listMobile.info().toString());
        break;
      }
      default: {
        context.getLogger().debug("contactOption : " + contactOption.name());
        return;
      }
    }

    ArrayList<CLMobileInformation.CLMobileInfo> listMoblieSMS = null;
    switch (listMobile.getStatusCode()) {
      case CLMobileInformation.CLMobileInfoResponse.STATUS_COMPLETE: {
        listMoblieSMS = listMobile.getResponse();
        break;
      }
      case CLMobileInformation.CLMobileInfoResponse.STATUS_DATA_NOT_FOUND: {
        return;
      }
      default: {
        return;
      }
    }
    if (listMoblieSMS == null || listMoblieSMS.isEmpty()) {
      context.getLogger().debug("mobile not found.");
      return;
    }
    //</editor-fold>
    //
    HashMap<BigDecimal, SMSMessage> messageList = new HashMap<>();

    int indexMobile = 0;
    int mobileNumber = 0;

    //<editor-fold defaultstate="collapsed" desc="Prepare SMS Message">
    while (true) {
      if (indexMobile < listMoblieSMS.size()) {

        CLMobileInformation.CLMobileInfo mobileInfo = listMoblieSMS.get(indexMobile);
        indexMobile++;
        //pass exempt
        //if(passExempt){
        //  continue;
        //}
        mobileNumber++;
        if (mobileNumber > contactQty) {
          break;
        }
        Constants.Language language = Constants.mapLanguage(mobileInfo.getSmsLanguage(), th.co.ais.cpac.cl.template.configuration.Constants.Language.THAI);

        if (messageList.get(language.getLanguageId()) != null) {
          SMSMessage lll = messageList.get(language.getLanguageId());
          lll.mobileInfo.add(mobileInfo);
          continue;
        }
        
        //<editor-fold defaultstate="collapsed" desc="Map Parameter SMS">
        CLCriteriaTemplate.CriteriaTemplateInfo template = templateInfo.get(language.getLanguageId());
        ArrayList<CLCriteriaTemplate.ParameterTemplate> listParm = parameter.get(template.getTemplateId()).get(template.getTemplateLanguageId());

        SMSMessage temp = new SMSMessage();
        temp.languageId = language.getLanguageId();
        temp.template = template;
        temp.mobileInfo.add(mobileInfo);
        messageList.put(language.getLanguageId(), temp);

        SMSParameterValue parameterPMInvoice = null;

        for (int i = 0; i < listParm.size(); i++) {
          CLCriteriaTemplate.ParameterTemplate param = listParm.get(i);
          Constants.ParameterTemplateSMS paramIndex = Constants.mapParameterTemplateSMS(param.getAttributeId().intValue());
          context.getLogger().debug(paramIndex.name());
          switch (paramIndex) {
            case MOBILE_NUM: {
              temp.mapParameter.put(param.getAttributeLabel(), getMobileReff(criteriaLevel, contactLevel, baReff));
              break;
            }
            case BALANCE_MNY: {
              temp.mapParameter.put(param.getAttributeLabel(), getBalanceMny(criteriaLevel, contactLevel, baReff));
              break;
            }
            case CRM_DUE_DAT: {
              temp.mapParameter.put(param.getAttributeLabel(), getCrmDueDat(criteriaLevel, contactLevel, baReff));
              break;
            }
            case BILL_DATE: {
              temp.mapParameter.put(param.getAttributeLabel(), getBillDate(criteriaLevel, contactLevel, baReff));
              break;
            }
            case SUSPEND_DATE: {
              temp.mapParameter.put(param.getAttributeLabel(), getSuspendDate(criteriaLevel, contactLevel, baReff));
              break;
            }
            case BILL_NUM: {
              temp.mapParameter.put(param.getAttributeLabel(), getBillNum(criteriaLevel, contactLevel, baReff));
              break;
            }
            case COUNT_INVOICE: {
              temp.mapParameter.put(param.getAttributeLabel(), getCountInvoice(criteriaLevel, contactLevel, baReff));
              break;
            }
            case INVOICE_MONTH: {
              temp.mapParameter.put(param.getAttributeLabel(), getInvoiceMonth(criteriaLevel, contactLevel, baReff));
              break;
            }
            case BILLING_CYCLE:
            case INVOICE_AMOUNT: {
              String smsvalue;
              if (parameterPMInvoice == null) {
                PMInvoice pmInvoice = new PMInvoice(context.getLogger());
                PMInvoice.SMSParameterValueResponse pm = pmInvoice.getSMSParameterValue(baReff.getMinInvoiceId());
                context.getLogger().debug(pm.info().toString());
                switch (pm.getStatusCode()) {
                  case PMInvoice.SMSParameterValueResponse.STATUS_COMPLETE: {
                    parameterPMInvoice = pm.getResponse();
                    break;
                  }
                  case PMInvoice.SMSParameterValueResponse.STATUS_DATA_NOT_FOUND: {
                    return;
                  }
                  default: {
                    return;
                  }
                }
              }
              if (paramIndex == Constants.ParameterTemplateSMS.BILLING_CYCLE) {
                smsvalue = parameterPMInvoice.getBillStartDate().toString() + parameterPMInvoice.getBillEndDate().toString();
              } else {
                smsvalue = parameterPMInvoice.getInvoiceTotalAmt().toPlainString();
              }
              temp.mapParameter.put(param.getAttributeLabel(), smsvalue);
              break;
            }
          }
        }
        //</editor-fold>

      } else {
        break;
      }
    }
    //</editor-fold>
    //
    /* Insert Database & call notify */
    //<editor-fold defaultstate="collapsed" desc="Insert Database">
    String senderSMS = "";
    //???
    CLCriteriaTemplate.CLSenderInfo sender = getSenderSMSInfo(getListBaNumber(), baReff.getCompanyCode(), listStatusMobile);
    if (sender == null) {
      senderSMS = "AIS.";
    } else {
      senderSMS = sender.getSmsSender();
    }

    CLSMSTreatment tbl = new CLSMSTreatment(context.getLogger());
    if (!tbl.buildConnection()) {
      return;
    }
    tbl.getDBConnection().setManualCommit();
    try {

      CLSMSTreatment.CLMessage clMessageReqt = tbl.buildCLMessage();
      clMessageReqt.setCaNumber(baReff.getCaNumber());
      if (baReff.getCriteriaLevel().isBALevel()) {
        clMessageReqt.setBaNumber(baReff.getBaNumber());
      }
      clMessageReqt.setTemplateId(templateDefault.getTemplateId());
      clMessageReqt.setTemplateCriteriaId(templateDefault.getTemplateCriteriaId());
      clMessageReqt.setMessageActionId(templateDefault.getActionId());
      clMessageReqt.setMessageCompanyCode(baReff.getCompanyCode());
      clMessageReqt.setMessageDate(new Date());
      clMessageReqt.setMessageSender(senderSMS);
      clMessageReqt.setMessageAttachment(null);
      Date now = new Date();
      clMessageReqt.setCreated(now);
      clMessageReqt.setCreatedBy("system");
      clMessageReqt.setLastUpd(now);
      clMessageReqt.setLastUpdBy("system");

      CLSMSTreatment.ExecuteResponse step1 = tbl.insertCLMessage(clMessageReqt);
      context.getLogger().debug(step1.info().toString());
      switch (step1.getStatusCode()) {
        case CLSMSTreatment.ExecuteResponse.STATUS_COMPLETE: {
          break;
        }
        default: {
          return;
        }
      }

      BigDecimal messageId = step1.getIdentity();
      for (int i = 0; i < listTempTreatment.size(); i++) {
        CLTempTreatment.TempTreatment tempBa = listTempTreatment.get(i);
        CLSMSTreatment.CLMessageTreatment clMessageTreatmentReqt = tbl.buildCLMessageTreatment();
        clMessageTreatmentReqt.setMessageId(messageId);
        clMessageTreatmentReqt.setTreatmentId(tempBa.getTreatmentId());
        clMessageTreatmentReqt.setCreated(new Date());
        clMessageTreatmentReqt.setCreatedBy("system");
        clMessageTreatmentReqt.setLastUpd(new Date());
        clMessageTreatmentReqt.setLastUpdBy("system");
        CLSMSTreatment.ExecuteResponse step2 = tbl.insertCLMessageTreatment(clMessageTreatmentReqt);
        context.getLogger().debug(step2.info().toString());
        switch (step2.getStatusCode()) {
          case CLSMSTreatment.ExecuteResponse.STATUS_COMPLETE: {
            break;
          }
          default: {
          }
        }
      }

      Iterator<SMSMessage> listMessage = messageList.values().iterator();

      while (listMessage.hasNext()) {

        SMSMessage temp = listMessage.next();
        context.getLogger().debug("templateMsg1 : " + temp.template.getTemplateMsg1());

        for (int i = 0; i < temp.mobileInfo.size(); i++) {
          context.getLogger().debug("mobileInfo : " + temp.mobileInfo.get(i).toString());
        }

        String smsMessage = temp.template.getTemplateMsg1();

        Iterator<String> keys = temp.mapParameter.keySet().iterator();
        while (keys.hasNext()) {
          String key = keys.next();
          String param = temp.mapParameter.get(key);
          context.getLogger().debug(key + ":" + param);
          key = "\\{" + key + "\\}";

          smsMessage = smsMessage.replaceAll(key, param);

        }
        context.getLogger().debug("sms message : " + smsMessage);
        for (int numberMobile = 0; numberMobile < temp.mobileInfo.size(); numberMobile++) {
          CLMobileInformation.CLMobileInfo mobileTemp = temp.mobileInfo.get(numberMobile);
          CLSMSTreatment.CLSms clSmsReqt = tbl.buildCLSms();
          clSmsReqt.setMessageId(messageId);
          clSmsReqt.setBaNumber(mobileTemp.getBaNumber());
          clSmsReqt.setMobileNumber(mobileTemp.getMobileNumber());
          clSmsReqt.setTemplateLanguageId(temp.languageId);
          clSmsReqt.setActionStatus(new BigDecimal(Constants.ActionStatus.InProgress.getCode()));
          clSmsReqt.setActionStatusDtm(new Date());
          clSmsReqt.setActionRemark(null);
          clSmsReqt.setBatchId(batchIdSms);
          clSmsReqt.setNotificationId(null);

          clSmsReqt.setMessageValue(smsMessage);

          clSmsReqt.setCreated(new Date());
          clSmsReqt.setCreatedBy("system");
          clSmsReqt.setLastUpd(new Date());
          clSmsReqt.setLastUpdBy("system");
          CLSMSTreatment.ExecuteResponse step3 = tbl.insertCLSms(clSmsReqt);
          context.getLogger().debug(step3.info().toString());
          switch (step3.getStatusCode()) {
            case CLSMSTreatment.ExecuteResponse.STATUS_COMPLETE: {
              //** for notification **/collection
              //mobileTemp.getBaNumber()
              JSNotification.SMSNotify smsResult = sendSms(now, "12345", "collection", mobileTemp.getMobileNumber(), "CR" + step3.getIdentity().toPlainString(), "", smsMessage, senderSMS);
              context.getLogger().debug(smsResult.getNotificationId().toPlainString());

              ///??? update notificationID
              CLSMSTreatment.ExecuteResponse rsUpdateNoti = tbl.updateNotificationId(step3.getIdentity(), Constants.ActionStatus.Complete, now, smsResult.getNotificationId());
              context.getLogger().debug(rsUpdateNoti.info().toString());
              //** for notification

              // ??? update status CL_TREATMENT              
              continue;
            }
            default: {
              return;
            }
          }
        }
      }
      for (int i = 0; i < listTempTreatment.size(); i++) {
        CLSMSTreatment.ExecuteResponse updateStatusTreatment = tbl.updateStatusCLTreatment(listTempTreatment.get(i).getTreatmentId(), Constants.ActionStatus.Complete, now);
        context.getLogger().debug(updateStatusTreatment.info().toString());
      }

    } catch (Exception ex) {
      context.getLogger().error("", ex);
    } finally {
      tbl.getDBConnection().commit();
      tbl.getDBConnection().setAutoCommit();
      tbl.getDBConnection().close();
    }
    //</editor-fold>


  }

  private void emailMessage(ArrayList<String> listBaNo, HashMap<BigDecimal, CLCriteriaTemplate.CriteriaTemplateInfo> templateInfo) {
    CLCriteriaTemplate.CriteriaTemplateInfo templateDefault = null;
    //<editor-fold defaultstate="collapsed" desc="Find Template Default">    
    Iterator<CLCriteriaTemplate.CriteriaTemplateInfo> list = templateInfo.values().iterator();
    while (list.hasNext()) {
      CLCriteriaTemplate.CriteriaTemplateInfo temp = list.next();
      if (temp.getDefaultBoo() == 'Y') {
        templateDefault = temp;
        break;
      }
      if (templateDefault == null) {
        templateDefault = temp;
        continue;
      }
      if (temp.getLanguage().isThaiLanguage()) {
        templateDefault = temp;
      }
    }
    if (templateDefault == null) {
      return;
    }
    //</editor-fold>
    //
    CLTempTreatment.TempTreatment baReff = getBaRefference();
    Constants.CriteriaLevel criteriaLevel = baReff.getCriteriaLevel();
    Constants.ContactLevel contactLevel = templateDefault.getContactLevel();
    Constants.ContactOptionEMail contactOption = Constants.mapContactOptionEMail(templateDefault.getContactOptionId().intValue());
    int contactQty = 99999;
    if ('Y' == templateDefault.getContactQtyBoo()) {
      contactQty = templateDefault.getContactQty().intValue();
    }
    //
    //<editor-fold defaultstate="collapsed" desc="get list mobile send sms">

    CLContactInfo.CLContactEMailInfoResponse dbContact = null;
    switch (contactOption) {
      case Contact_EMail: {
        String contactNo;
        switch (contactLevel) {
          case CA: {
            contactNo = baReff.getCaNumber();
            break;
          }
          case BA: {
            contactNo = baReff.getBaNumber();
            break;
          }
          case SA: {
            contactNo = baReff.getSaNumber();
            break;
          }
          default: {
            contactNo = "00000";
          }
        }
        CLContactInfo contact = new CLContactInfo(context.getLogger());
        dbContact = contact.getContactEMail(contactLevel, contactNo);
        break;
      }
      default: {
        return;
      }
    }
    if (dbContact == null) {
      return;
    }
    ArrayList<CLContactInfo.CLContactEMailInfo> listEmail = null;
    switch (dbContact.getStatusCode()) {
      case CLMobileInformation.CLMobileInfoResponse.STATUS_COMPLETE: {
        listEmail = dbContact.getResponse();
        break;
      }
      case CLMobileInformation.CLMobileInfoResponse.STATUS_DATA_NOT_FOUND: {
        return;
      }
      default: {
        return;
      }
    }
    if (listEmail == null || listEmail.isEmpty()) {
      return;
    }
    //</editor-fold>
    //
    HashMap<BigDecimal, EMailMessage> messageList = new HashMap<>();

    int indexEmail = 0;
    int emailNo = 0;

    //<editor-fold defaultstate="collapsed" desc="Prepare Email Message">
    while (true) {
      if (indexEmail < listEmail.size()) {

        CLContactInfo.CLContactEMailInfo emailInfo = listEmail.get(indexEmail);
        indexEmail++;
        //pass exempt
        //if(passExempt){
        //  continue;
        //}
        emailNo++;
        if (emailNo > contactQty) {
          break;
        }

        //<editor-fold defaultstate="collapsed" desc="Map Parameter Email">
        CLCriteriaTemplate.CriteriaTemplateInfo template = templateDefault;
        ArrayList<CLCriteriaTemplate.ParameterTemplate> listParm = parameter.get(baReff.getTreatmentId()).get(template.getTemplateLanguageId());

        EMailMessage temp = new EMailMessage();
        temp.languageId = template.getLanguageId();
        temp.template = template;
        temp.email.add(emailInfo);
        messageList.put(template.getTemplateLanguageId(), temp);

        SMSParameterValue parameterPMInvoice = null;

        for (int i = 0; i < listParm.size(); i++) {
          CLCriteriaTemplate.ParameterTemplate param = listParm.get(i);
          Constants.ParameterTemplateSMS paramIndex = Constants.mapParameterTemplateSMS(param.getAttributeId().intValue());
          switch (paramIndex) {
            case MOBILE_NUM: {
              temp.mapParameter.put(param.getAttributeLabel(), getMobileReff(criteriaLevel, contactLevel, baReff));
              break;
            }
            case BALANCE_MNY: {
              temp.mapParameter.put(param.getAttributeLabel(), getBalanceMny(criteriaLevel, contactLevel, baReff));
              break;
            }
            case CRM_DUE_DAT: {
              temp.mapParameter.put(param.getAttributeLabel(), getCrmDueDat(criteriaLevel, contactLevel, baReff));
              break;
            }
            case BILL_DATE: {
              temp.mapParameter.put(param.getAttributeLabel(), getBillDate(criteriaLevel, contactLevel, baReff));
              break;
            }
            case SUSPEND_DATE: {
              temp.mapParameter.put(param.getAttributeLabel(), getSuspendDate(criteriaLevel, contactLevel, baReff));
              break;
            }
            case BILL_NUM: {
              temp.mapParameter.put(param.getAttributeLabel(), getBillNum(criteriaLevel, contactLevel, baReff));
              break;
            }
            case COUNT_INVOICE: {
              temp.mapParameter.put(param.getAttributeLabel(), getCountInvoice(criteriaLevel, contactLevel, baReff));
              break;
            }
            case INVOICE_MONTH: {
              temp.mapParameter.put(param.getAttributeLabel(), getInvoiceMonth(criteriaLevel, contactLevel, baReff));
              break;
            }
            case BILLING_CYCLE:
            case INVOICE_AMOUNT: {
              String smsvalue;
              if (parameterPMInvoice == null) {
                PMInvoice pmInvoice = new PMInvoice(context.getLogger());
                PMInvoice.SMSParameterValueResponse pm = pmInvoice.getSMSParameterValue(baReff.getMinInvoiceId());
                switch (pm.getStatusCode()) {
                  case PMInvoice.SMSParameterValueResponse.STATUS_COMPLETE: {
                    parameterPMInvoice = pm.getResponse();
                    break;
                  }
                  case PMInvoice.SMSParameterValueResponse.STATUS_DATA_NOT_FOUND: {
                    return;
                  }
                  default: {
                    return;
                  }
                }
              }
              if (paramIndex == Constants.ParameterTemplateSMS.BILLING_CYCLE) {
                smsvalue = parameterPMInvoice.getBillStartDate().toString() + parameterPMInvoice.getBillEndDate().toString();
              } else {
                smsvalue = parameterPMInvoice.getInvoiceTotalAmt().toPlainString();
              }
              temp.mapParameter.put(param.getAttributeLabel(), smsvalue);
              break;
            }
          }
        }
        //</editor-fold>

      } else {
        break;
      }
    }
    //</editor-fold>
    //
    /* Insert Database & call notify */
    //<editor-fold defaultstate="collapsed" desc="Insert Database">

    CLSMSTreatment tbl = new CLSMSTreatment(context.getLogger());
    if (!tbl.buildConnection()) {
      return;
    }
    tbl.getDBConnection().setManualCommit();
    CLSMSTreatment.CLMessage clMessageReqt = tbl.buildCLMessage();
    clMessageReqt.setCaNumber(baReff.getCaNumber());
    if (baReff.getCriteriaLevel().isBALevel()) {
      clMessageReqt.setBaNumber(baReff.getBaNumber());
    }
    clMessageReqt.setTemplateId(templateDefault.getTemplateId());
    clMessageReqt.setTemplateCriteriaId(templateDefault.getTemplateCriteriaId());
    clMessageReqt.setMessageActionId(templateDefault.getActionId());
    clMessageReqt.setMessageCompanyCode(baReff.getCompanyCode());
    clMessageReqt.setMessageDate(new Date());
    clMessageReqt.setMessageSender("sender_method");
    clMessageReqt.setMessageAttachment(null);
    Date now = new Date();
    clMessageReqt.setCreated(now);
    clMessageReqt.setCreatedBy("system");
    clMessageReqt.setLastUpd(now);
    clMessageReqt.setCreatedBy("system");

    CLSMSTreatment.ExecuteResponse step1 = tbl.insertCLMessage(clMessageReqt);

    switch (step1.getStatusCode()) {
      case CLSMSTreatment.ExecuteResponse.STATUS_COMPLETE: {
        break;
      }
      default: {
        return;
      }
    }

    BigDecimal messageId = step1.getIdentity();
    for (int i = 0; i < listTempTreatment.size(); i++) {
      CLTempTreatment.TempTreatment tempBa = listTempTreatment.get(i);
      CLSMSTreatment.CLMessageTreatment clMessageTreatmentReqt = tbl.buildCLMessageTreatment();
      clMessageTreatmentReqt.setMessageId(messageId);
      clMessageTreatmentReqt.setTreatmentId(tempBa.getTreatmentId());
      clMessageTreatmentReqt.setCreated(new Date());
      clMessageTreatmentReqt.setCreatedBy("system");
      clMessageTreatmentReqt.setLastUpd(new Date());
      clMessageTreatmentReqt.setCreatedBy("system");
      CLSMSTreatment.ExecuteResponse step2 = tbl.insertCLMessageTreatment(clMessageTreatmentReqt);
      switch (step2.getStatusCode()) {
        case CLSMSTreatment.ExecuteResponse.STATUS_COMPLETE: {
          break;
        }
        default: {
        }
      }
    }

    Iterator<EMailMessage> listMessage = messageList.values().iterator();
    while (listMessage.hasNext()) {
      EMailMessage temp = listMessage.next();
      //insert email
    }
    //</editor-fold>

    /*call notify gable*/
    //
  }

  //<editor-fold defaultstate="collapsed" desc="Method get value parameter ..">
  private String getMobileReff(Constants.CriteriaLevel criteriaLevel, Constants.ContactLevel contactLevel, CLTempTreatment.TempTreatment baReff) {
    if (commonParam.get(KEY_MOBILE_REFF) != null) {
      return ((ArrayList<CLMobileInformation.CLMobileInfo>) commonParam.get(KEY_MOBILE_REFF)).get(0).getMobileNumber();
    }
    CLMobileInformation tbl = new CLMobileInformation(context.getLogger());
    CLMobileInformation.CLMobileInfoResponse resp = tbl.getMobileRefference(baReff.getBaNumber(), baReff.getBaStatus(), 1);
    context.getLogger().debug(resp.info().toString());
    switch (resp.getStatusCode()) {
      case CLMobileInformation.CLMobileInfoResponse.STATUS_COMPLETE: {
        commonParam.put(KEY_MOBILE_REFF, resp.getResponse());
        return resp.getResponse().get(0).getMobileNumber();
      }
      default: {
        return "";
      }
    }
  }

  private String getBalanceMny(Constants.CriteriaLevel criteriaLevel, Constants.ContactLevel contactLevel, CLTempTreatment.TempTreatment baReff) {
    // check criteriaLevel CA , select or change business treatment
    // มีสองวิธี เปลี่ยน Business login การทำ treatment เพื่อ ยกเว้นการหา exampt sms , letter หรื
    // ใช้วิธี query data อีกรอบ จาก CL_BA_INFO + CL_HIS_BA_ACCOUNT_BALANCE
    float baTotal = 0;
    float dueTotal = 0;

    for (int i = 0; i < listTempTreatment.size(); i++) {
      baTotal += listTempTreatment.get(i).getBaTotalBal().floatValue();
      dueTotal += listTempTreatment.get(i).getDueTotalBal().floatValue();
    }
    if (baReff.getActionType() == Constants.ActionType.PreDue) {
      return "" + baTotal;
    } else {
      return "" + dueTotal;
    }
  }

  private String getCrmDueDat(Constants.CriteriaLevel criteriaLevel, Constants.ContactLevel contactLevel, CLTempTreatment.TempTreatment baReff) {
    return baReff.getMinDueDate().toString();
  }

  private String getBillDate(Constants.CriteriaLevel criteriaLevel, Constants.ContactLevel contactLevel, CLTempTreatment.TempTreatment baReff) {
    return baReff.getMinInvoiceDate().toString();
  }

  private String getBillNum(Constants.CriteriaLevel criteriaLevel, Constants.ContactLevel contactLevel, CLTempTreatment.TempTreatment baReff) {
    return baReff.getBaNumber();
  }

  private String getCountInvoice(Constants.CriteriaLevel criteriaLevel, Constants.ContactLevel contactLevel, CLTempTreatment.TempTreatment baReff) {
    // check criteriaLevel CA , select or change business treatment
    // มีสองวิธี เปลี่ยน Business login การทำ treatment เพื่อ ยกเว้นการหา exampt sms , letter หรื
    // ใช้วิธี query data อีกรอบ จาก CL_BA_INFO + CL_HIS_BA_ACCOUNT_BALANCE
    int baTotal = 0;
    for (int i = 0; i < listTempTreatment.size(); i++) {
      if (listTempTreatment.get(i).getBaInvoiceCount() == null) {
        continue;
      }
      baTotal += listTempTreatment.get(i).getBaInvoiceCount().intValue();
    }
    return "" + baTotal;
  }

  private String getInvoiceMonth(Constants.CriteriaLevel criteriaLevel, Constants.ContactLevel contactLevel, CLTempTreatment.TempTreatment baReff) {
    return baReff.getMinInvoiceDate().toString(); // format yyMM
  }

  private String getSuspendDate(Constants.CriteriaLevel criteriaLevel, Constants.ContactLevel contactLevel, CLTempTreatment.TempTreatment baReff) {
    ArrayList<CLAction.ActionInfo> listAction = actionInfo.get(baReff.getCollectionPathId()).getListActionInfo();
    String suspendDate = null;
    for (CLAction.ActionInfo action : listAction) {
      if (action.getActionType() == Constants.ActionType.Suspend1) {
        Date sdDate = UtilityDate.addDate(context, baReff.getMinDueDate(), action.getActionNumberDay().intValue(), Calendar.DATE);
        if (new Date().after(sdDate)) {
          suspendDate = sdDate.toString();
          break;
        } else {
          suspendDate = UtilityDate.addDate(context, new Date(), 1, Calendar.DATE).toString();
          break;
        }
      }
    }
    if (suspendDate == null) {
      suspendDate = UtilityDate.addDate(context, new Date(), 1, Calendar.DATE).toString();
    }
    return suspendDate;
  }
//</editor-fold>

  private class SMSMessage {

    BigDecimal languageId;
    //label , value
    HashMap<String, String> mapParameter = new HashMap<>();
    ArrayList<CLMobileInformation.CLMobileInfo> mobileInfo = new ArrayList<>();
    CLCriteriaTemplate.CriteriaTemplateInfo template;
  }

  private class EMailMessage {

    BigDecimal languageId;
    //label , value
    HashMap<String, String> mapParameter = new HashMap<>();
    ArrayList<CLContactInfo.CLContactEMailInfo> email = new ArrayList<>();
    CLCriteriaTemplate.CriteriaTemplateInfo template;
  }

}
