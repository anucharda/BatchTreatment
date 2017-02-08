/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.cmd.letter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import th.co.ais.cpac.cl.batch.Constants;
import th.co.ais.cpac.cl.batch.db.CLAction;
import th.co.ais.cpac.cl.batch.db.CLCAInfo;
import th.co.ais.cpac.cl.batch.db.CLContactInfo;
import th.co.ais.cpac.cl.batch.db.CLCriteriaTemplate;
import th.co.ais.cpac.cl.batch.db.CLLanguage;
import th.co.ais.cpac.cl.batch.db.CLLetterTreatment;
import th.co.ais.cpac.cl.batch.db.CLMobileInformation;
import th.co.ais.cpac.cl.batch.db.CLTempTreatment;
import th.co.ais.cpac.cl.batch.db.PMCompany;
import th.co.ais.cpac.cl.batch.template.SMSLetterWorkerTreatmentTemplate;
import th.co.ais.cpac.cl.batch.util.UtilityDate;
import th.co.ais.cpac.cl.common.Context;

/**
 *
 * @author Optimus
 */
public class WorkerSendLetter extends SMSLetterWorkerTreatmentTemplate {

  private final String END_LINE_LETTER = "๚";
  private final HashMap<String, Object> commonParam = new HashMap<>();

  private final String assignId;

  // actionid , criteriaid 
  private final HashMap<BigDecimal, HashMap<BigDecimal, HashMap<String, HashMap<BigDecimal, CLCriteriaTemplate.CriteriaTemplateInfo>>>> criteriaInfo;
  // companyid+"_"+product_type_id
  private final HashMap<String, CLCriteriaTemplate.CLSenderInfo> senderInfo;
  // pathid
  private final HashMap<BigDecimal, HashMap<BigDecimal, CLAction.ActionInfo>> actionInfo;

  //templateId , TemplateLanguageId
  private final HashMap<BigDecimal, HashMap<BigDecimal, ArrayList<CLCriteriaTemplate.ParameterTemplate>>> parameter;
  //
  private final HashMap<String, PMCompany.PMCompanyInfo> companyInfo;
  //
  private final HashMap<BigDecimal, CLLanguage.CLLanguagsInfo> languageInfo;
  //
  private Constants.ContactOptionLetter contactOption;
  private Constants.ContactLevel contactLevel;
  private Constants.CriteriaLevel criteriaLevel;

  //
  public WorkerSendLetter(Context context, ArrayList<CLTempTreatment.TempTreatment> listTempTreatment, String assignId, HashMap<BigDecimal, HashMap<BigDecimal, HashMap<String, HashMap<BigDecimal, CLCriteriaTemplate.CriteriaTemplateInfo>>>> criteriaInfo, HashMap<String, CLCriteriaTemplate.CLSenderInfo> senderInfo, HashMap<BigDecimal, HashMap<BigDecimal, CLAction.ActionInfo>> actionInfo, HashMap<BigDecimal, HashMap<BigDecimal, ArrayList<CLCriteriaTemplate.ParameterTemplate>>> parameter, HashMap<String, PMCompany.PMCompanyInfo> companyInfo, HashMap<BigDecimal, CLLanguage.CLLanguagsInfo> languageInfo) {
    super(context, listTempTreatment);
    this.assignId = assignId;
    this.criteriaInfo = criteriaInfo;
    this.senderInfo = senderInfo;
    this.actionInfo = actionInfo;
    this.parameter = parameter;
    this.companyInfo = companyInfo;
    this.languageInfo = languageInfo;
  }

  @Override
  public void run() {
    context.getLogger().debug("\n\n\n\n\n");
    context.getLogger().debug("WorkerSendLetter ..... ");
    if (context == null || criteriaInfo == null || senderInfo == null || actionInfo == null || listTempTreatment == null) {
      return;
    }

    setListBaNumberAndMapBaNumber();

    CLTempTreatment.TempTreatment baReff = getBaRefference();

    HashMap<BigDecimal, CLCriteriaTemplate.CriteriaTemplateInfo> letter_template = criteriaInfo.get(baReff.getActionId()).get(baReff.getCriteriaId()).get(Constants.TemplateType.LETTER.getKeys());

    if (letter_template != null) {
      letterMessage(letter_template);
    }

  }

  private void letterMessage(HashMap<BigDecimal, CLCriteriaTemplate.CriteriaTemplateInfo> templateInfo) {
    context.getLogger().debug("letterMessage...");
    CLCriteriaTemplate.CriteriaTemplateInfo template = getTemplate(templateInfo, getBaRefference().getBillLanguage());

    if (template == null) {
      context.getLogger().error("Template not found.");
      return;
    }

    CLTempTreatment.TempTreatment baReff = getBaRefference();
    criteriaLevel = getBaRefference().getCriteriaLevel();
    contactLevel = template.getContactLevel();
    contactOption = Constants.mapContactOptionLetter(template.getContactOptionId().intValue());

    int contactQty;
    if ('Y' == template.getContactQtyBoo()) {
      contactQty = template.getContactQty().intValue();
    } else {
      contactQty = template.getContactQtyDefault().intValue();
    }
    //

    ArrayList<CLCriteriaTemplate.ParameterTemplate> listParm = parameter.get(template.getTemplateId()).get(template.getTemplateLanguageId());

    for (int i = 0; i < listParm.size(); i++) {
      objectLetter.addLabelValue(listParm.get(i).getAttributeAlias());
    }

    objectLetter.setFileName(getFileName(baReff, template));
    objectLetter.setCLMessage(baReff, template);
    objectLetter.setCLMessageTreatment(listTempTreatment);
    objectLetter.setLanguageCode(template.getLanguageCode());
    objectLetter.setLanguage(template.getLanguage());
    objectLetter.setLetterType(getLetterType(baReff, template));

    switch (contactOption) {
      case VAT_Address: {
        String letterMessage = buildMessageLetter(baReff, null, listParm);
        objectLetter.addCLLetter(letterMessage, baReff, null, template);
        break;
      }
      case Billing_Address: {
        //???? exampt ต้องคิดแบบไหน ต้องคิดเป็นรายบุคคลเลยหรือเปล่า
        if (contactQty > listTempTreatment.size()) {
          contactQty = listTempTreatment.size();
        }
        for (int i = 0; i < contactQty; i++) {
          CLTempTreatment.TempTreatment tempBaInfo = listTempTreatment.get(i);
          String letterMessage = buildMessageLetter(listTempTreatment.get(i), null, listParm);
          objectLetter.addCLLetter(letterMessage, tempBaInfo, null, template);
        }
        break;
      }
      case Contact_Address: {
        // ???? exampt ต้องคิดแบบไหน ต้องคิดเป็นรายบุคคลเลยหรือเปล่า
        //<editor-fold defaultstate="collapsed" desc="get list contact">
        ArrayList<CLContactInfo.CLContactAddressInfo> listContact = getListContactAddress();
        //</editor-fold>
        if (contactQty > listContact.size()) {
          contactQty = listContact.size();
        }
        for (int i = 0; i < contactQty; i++) {
          CLContactInfo.CLContactAddressInfo tempContact = listContact.get(i);
          String letterMessage = buildMessageLetter(baReff, listContact.get(i), listParm);
          objectLetter.addCLLetter(letterMessage, baReff, tempContact, template);
        }
        break;
      }

      default: {
      }
    }
    context.getLogger().debug("letterMessage...");
  }

  private String getLetterType(CLTempTreatment.TempTreatment baReff, CLCriteriaTemplate.CriteriaTemplateInfo template) {
    Constants.DataOwner owner = Constants.mapDataOwner(actionInfo.get(baReff.getCollectionPathId()).get(baReff.getActionId()).getActionOwner());
    if (owner.isCorporate()) {
      return template.getLetterType().getStringCodeCorporate();
    }
    return template.getLetterType().getStringCode();
  }

  private String getFileName(CLTempTreatment.TempTreatment baReff, CLCriteriaTemplate.CriteriaTemplateInfo template) {
    //v_CompanyCode_ModeID_v_AssignID_v_TemplateID.txt (จดหมายติดตามหนี้แบบไม่รวมซอง)
    //v_CompanyCode_ModeID_v_AssignID_v_TemplateID_v_Language_HC.txt (จดหมายติดตามหนี้แบบรวมซอง)
    //AIS_TLC_57010027_18_TH_HC.txt

    String fileName = "";
    fileName += baReff.getCompanyCode();
    fileName += "_";
    fileName += getLetterType(baReff, template);
    fileName += "_";
    fileName += assignId;
    fileName += "_";
    fileName += template.getLetterTmpId();
    if (template.getLetterPrintType() == Constants.LetterPrintType.PrintingHouseIncludingEnvelopes) {
      fileName += "_";
      fileName += languageInfo.get(template.getLanguage().getLanguageId()).getLanguageCode();
      fileName += "_HC";
    }
    return fileName;
  }

  private String buildMessageLetter(CLTempTreatment.TempTreatment baInfo, CLContactInfo.CLContactAddressInfo contactAddress, ArrayList<CLCriteriaTemplate.ParameterTemplate> listParm) {

    StringBuffer messageLetter = new StringBuffer();
    for (int i = 0; i < listParm.size(); i++) {
      CLCriteriaTemplate.ParameterTemplate param = listParm.get(i);
      Constants.ParameterTemplateLetter paramIndex = Constants.mapParameterTemplateLetter(param.getAttributeId().intValue());

      switch (paramIndex) {
        case ADDRESS1: {
          //1
          param__ADDRESS1(messageLetter, baInfo, contactAddress);
          break;
        }
        case ADDRESS2: {
          //2
          param__ADDRESS2(messageLetter, baInfo, contactAddress);
          break;
        }
        case BARCODE: {
          //3
          param__BARCODE(messageLetter, baInfo);
          break;
        }
        case BARCODE_AR: {
          //4
          param__BARCODE_AR(messageLetter, baInfo);
          break;
        }
        case BILLING_ACC_NAME: {
          //5
          param__BILLING_ACC_NAME(messageLetter, baInfo);
          break;
        }
        case BILLING_ACC_NUM: {
          //6
          param__BILLING_ACC_NUM(messageLetter, baInfo);
          break;
        }
        case COMPANY_NAME: {
          //7
          param__COMPANY_NAME(messageLetter, baInfo);
          break;
        }
        case CONTACT_NAME: {
          //8
          param__CONTACT_NAME(messageLetter, baInfo, contactAddress);
          break;
        }
        case CONTACT_NUMBER_DESC: {
          //9
          param__CONTACT_NUMBER_DESC(messageLetter, baInfo);
          break;
        }
        case COST_MNY: {
          //10
          param__COST_MNY(messageLetter, baInfo);
          break;
        }
        case GEN_SEQ: {
          //11 
          param__GEN_SEQ(messageLetter, baInfo);
          break;
        }
        case GRAND_TOTAL_BALANCE: {
          //12 
          param__GRAND_TOTAL_BALANCE(messageLetter, baInfo);
          break;
        }
        case LAST_AR_DAT: {
          //13
          param__LAST_AR_DAT(messageLetter, baInfo);
          break;
        }
        case LETTER_DAT: {
          //14
          param__LETTER_DAT(messageLetter, baInfo);
          break;
        }
        case LETTER_DAT_REF: {
          //15;
          param__LETTER_DAT_REF(messageLetter, baInfo);
          break;
        }
        case MOBILE_NUM: {
          //16
          param__MOBILE_NUM(messageLetter, baInfo);
          break;
        }
        case MOBILE_NUM_LIST: {
          //17
          param__MOBILE_NUM_LIST(messageLetter, baInfo);
          break;
        }
        case MODE_ASSIGN: {
          //18
          param__MODE_ASSIGN(messageLetter);
          break;
        }
        case MODE_ASSIGN_REF: {
          //19
          param__MODE_ASSIGN_REF(messageLetter, baInfo);
          break;
        }
        case NEW_BILLING_ACC_NUM: {
          //20
          param__NEW_BILLING_ACC_NUM(messageLetter, baInfo);
          break;
        }
        case OWNER_NAME: {
          //21
          param__OWNER_NAME(messageLetter, baInfo);
          break;
        }
        case PARTY_DESC: {
          //22 
          param__PARTY_DESC(messageLetter, baInfo);
          break;
        }
        case PARTY_TELEPHONE: {
          //23
          param__PARTY_TELEPHONE(messageLetter, baInfo);
          break;
        }
        case PAYMENT_DUE_DAT: {
          //24
          param__PAYMENT_DUE_DAT(messageLetter, baInfo);
          break;
        }
        case PROVINCE_CODE: {
          //25
          param__PROVINCE_CODE(messageLetter, baInfo, contactAddress);
          break;
        }
        case REGISTER_DAT: {
          //26
          param__REGISTER_DAT(messageLetter, baInfo);
          break;
        }
        case SERVICE_NAME: {
          //27 
          param__SERVICE_NAME(messageLetter, baInfo);
          break;
        }
        case STATUS_DAT: {
          //28
          param__STATUS_DAT(messageLetter, baInfo);
          break;
        }
        case TERMINATE_MONTH: {
          //29
          param__TERMINATE_MONTH(messageLetter, baInfo);
          break;
        }
        case TOTAL_BALANCE: {
          //30
          param__TOTAL_BALANCE(messageLetter, baInfo);
          break;
        }
        case ZIP_CODE: {
          //31
          param__ZIP_CODE(messageLetter, baInfo, contactAddress);
          break;
        }
        default: {
          context.getLogger().error("map parameter error[" + paramIndex.getCode() + "]=" + paramIndex.name());
          messageLetter.append("").append(END_LINE_LETTER);
        }
      }
      context.getLogger().debug(paramIndex.name() + "::ทดสอบ::" + messageLetter.toString());
    }
    return messageLetter.toString();
  }

  //<editor-fold defaultstate="collapsed" desc="Get Value Parameter">
  private CLCAInfo.CAInfomation getCAInfoAddress(String number) {
    CLCAInfo.CAInfomation temp = (CLCAInfo.CAInfomation) commonParam.get("CLCAInfo.CAInfomation");
    if (temp == null) {
      CLCAInfo caTbl = new CLCAInfo(context.getLogger());
      CLCAInfo.CAInfomationResponse caInfo = caTbl.getCAInfomation(number);
      context.getLogger().debug(caInfo.info().toString());
      switch (caInfo.getStatusCode()) {
        case CLCAInfo.CAInfomationResponse.STATUS_COMPLETE: {
          commonParam.put("CLCAInfo.CAInfomation", caInfo.getResponse());
          return caInfo.getResponse();
        }
        case CLCAInfo.CAInfomationResponse.STATUS_DATA_NOT_FOUND: {
          commonParam.put("CLCAInfo.CAInfomation", caTbl.buildCAInfomation());
          return caTbl.buildCAInfomation();
        }
        default: {
          return null;
        }
      }
    } else {
      return temp;
    }
  }

  private ArrayList<CLContactInfo.CLContactAddressInfo> getListContactAddress() {
    switch (contactOption) {
      case Contact_Address: {
        String contactNo;
        switch (contactLevel) {
          case CA: {
            contactNo = listTempTreatment.get(0).getCaNumber();
            break;
          }
          case BA: {
            contactNo = getBaRefference().getBaNumber();
            break;
          }
          case SA: {
            contactNo = getBaRefference().getSaNumber();
            break;
          }
          default: {
            contactNo = "00000";
          }
        }
        CLContactInfo tblContact = new CLContactInfo(context.getLogger());
        CLContactInfo.CLContactAddressInfoResponse rsContact = tblContact.getContactAddress(contactLevel, contactNo);
        switch (rsContact.getStatusCode()) {
          case CLContactInfo.CLContactAddressInfoResponse.STATUS_COMPLETE: {
            return rsContact.getResponse();
          }
          case CLContactInfo.CLContactAddressInfoResponse.STATUS_DATA_NOT_FOUND: {
            return new ArrayList<>();
          }
          default: {
            return null;
          }
        }
      }
      default: {
        return null;
      }
    }
  }

  private boolean param__ADDRESS1(StringBuffer data, CLTempTreatment.TempTreatment baInfo, CLContactInfo.CLContactAddressInfo contactAddress) {
    String a1 = null;
    switch (contactOption) {
      case VAT_Address: {
        CLCAInfo.CAInfomation temp = getCAInfoAddress(baInfo.getCaNumber());

        a1 = temp.getVatAddressLine1() + " " + temp.getVatAddressLine2();
        break;
      }
      case Billing_Address: {
        a1 = baInfo.getBillAddressLine1() + " " + baInfo.getBillAddressLine2();
        break;
      }
      case Contact_Address: {
        a1 = contactAddress.getContactAddrLine1() + " " + contactAddress.getContactAddrLine2();
        break;
      }

      default: {
        return false;
      }
    }
    data.append(a1).append(END_LINE_LETTER);
    return true;
  }

  private boolean param__ADDRESS2(StringBuffer data, CLTempTreatment.TempTreatment baInfo, CLContactInfo.CLContactAddressInfo contactAddress) {
    String a1 = null;
    switch (contactOption) {
      case VAT_Address: {
        CLCAInfo.CAInfomation temp = getCAInfoAddress(baInfo.getCaNumber());
        a1 = temp.getVatAddressLine3() + " " + temp.getVatAddressLine4();
        break;
      }
      case Billing_Address: {
        a1 = baInfo.getBillAddressLine3() + " " + baInfo.getBillAddressLine4();
        break;
      }
      case Contact_Address: {
        a1 = contactAddress.getContactAddrLine3() + " " + contactAddress.getContactAddrLine4();
        break;
      }
      default: {
        return false;
      }
    }
    data.append(a1).append(END_LINE_LETTER);
    return true;
  }

  private String getSequence() {
    if (commonParam.get("SEQUENCE_NUMBER") != null) {
      return (String) commonParam.get("SEQUENCE_NUMBER");
    }
    String a1 = Constants.buildSequence();
    commonParam.put("SEQUENCE_NUMBER", a1);
    return a1;
  }

  private String getBarCode(CLTempTreatment.TempTreatment baInfo) {
    if (commonParam.get("LETTER_BARCODE") != null) {
      return (String) commonParam.get("LETTER_BARCODE");
    }
    String a1 = baInfo.getActionType().getCodeString() + "" + assignId + "" + getSequence();
    commonParam.put("LETTER_BARCODE", a1);
    return a1;
  }

  private String getBarCodeAR(CLTempTreatment.TempTreatment baInfo) {
    if (commonParam.get("LETTER_BARCODE_AR") != null) {
      return (String) commonParam.get("LETTER_BARCODE_AR");
    }
    String a1 = getBarCode(baInfo) + "" + baInfo.getCompanyCode();
    commonParam.put("LETTER_BARCODE_AR", a1);
    return a1;
  }

  private boolean param__BARCODE(StringBuffer data, CLTempTreatment.TempTreatment baInfo) {
    data.append(getBarCode(baInfo)).append(END_LINE_LETTER);
    return true;
  }

  private boolean param__BARCODE_AR(StringBuffer data, CLTempTreatment.TempTreatment baInfo) {
    data.append(getBarCodeAR(baInfo)).append(END_LINE_LETTER);
    return true;
  }

  private boolean param__BILLING_ACC_NAME(StringBuffer data, CLTempTreatment.TempTreatment baInfo) {
    String a1 = baInfo.getBaTitle() + " " + baInfo.getBaName();
    data.append(a1).append(END_LINE_LETTER);
    return true;
  }

  private boolean param__BILLING_ACC_NUM(StringBuffer data, CLTempTreatment.TempTreatment baInfo) {
    String a1 = baInfo.getBaNumber();
    data.append(a1).append(END_LINE_LETTER);
    return true;
  }

  private boolean param__COMPANY_NAME(StringBuffer data, CLTempTreatment.TempTreatment baInfo) {
    String a1 = null;
    a1 = companyInfo.get(baInfo.getCompanyCode()).getCompanyName();
    data.append(a1).append(END_LINE_LETTER);
    return true;
  }

  private boolean param__CONTACT_NAME(StringBuffer data, CLTempTreatment.TempTreatment baInfo, CLContactInfo.CLContactAddressInfo contactAddress) {
    String a1 = null;
    switch (contactOption) {
      case VAT_Address: {
        CLCAInfo.CAInfomation temp = getCAInfoAddress(baInfo.getCaNumber());
        a1 = temp.getCaTitle() + " " + temp.getCaName();
        break;
      }
      case Billing_Address: {
        a1 = baInfo.getBaTitle() + " " + baInfo.getBaName();
        break;
      }
      case Contact_Address: {
        a1 = contactAddress.getContactName();
        break;
      }
      default: {
        break;
      }
    }
    data.append(a1).append(END_LINE_LETTER);
    return true;

  }

  private boolean param__CONTACT_NUMBER_DESC(StringBuffer data, CLTempTreatment.TempTreatment baInfo) {
    Constants.Language language = Constants.mapLanguage(baInfo.getBillLanguage(), Constants.Language.THAI);
    String a1 = null;
    if (language.isEngLanguage()) {
      a1 = "AIS Call Center โทร. 1175 ทุกวัน ตลอด 24 ชั่วโมง";
    } else {
      a1 = "please contact 1175 AIS Call Center, every day, 24 hours.";
    }
    data.append(a1).append(END_LINE_LETTER);
    return true;
  }

  private boolean param__COST_MNY(StringBuffer data, CLTempTreatment.TempTreatment baInfo) {
    if (commonParam.get("COST_MNY") == null) {
      baInfo.getCompanyCode();
      baInfo.getProductGroupId();
      commonParam.put("COST_MNY", new BigDecimal("4000"));
    } else {
      commonParam.get("COST_MNY");
    }
    String a1 = "";
    data.append(a1).append(END_LINE_LETTER);
    return true;
  }

  private boolean param__GEN_SEQ(StringBuffer data, CLTempTreatment.TempTreatment baInfo) {
    data.append(Constants.buildSequence()).append(END_LINE_LETTER);

    return true;
  }

  private boolean param__GRAND_TOTAL_BALANCE(StringBuffer data, CLTempTreatment.TempTreatment baInfo) {

    if (commonParam.get("COST_MNY") == null) {
      baInfo.getCompanyCode();
      baInfo.getProductGroupId();
      commonParam.put("COST_MNY", new BigDecimal("4000"));
    } else {
      commonParam.get("COST_MNY");
    }
    return true;
  }

  private boolean param__LAST_AR_DAT(StringBuffer data, CLTempTreatment.TempTreatment baInfo) {
    String a1 = baInfo.getMaxInvoiceDate().toString();
    data.append(a1).append(END_LINE_LETTER);
    return true;
  }

  private boolean param__LETTER_DAT(StringBuffer data, CLTempTreatment.TempTreatment baInfo) {
    Constants.Language language = Constants.mapLanguage(baInfo.getBillLanguage(), Constants.Language.THAI);
    String a1 = null;
    if (language.isEngLanguage()) {
      baInfo.getActionScheduleDtm();
    } else {
      baInfo.getActionScheduleDtm();
    }
    data.append(a1).append(END_LINE_LETTER);
    return true;
  }

  private ArrayList<CLAction.ActionTLNLHistory> getActionTLNLHistory(CLTempTreatment.TempTreatment baInfo) {
    ArrayList<CLAction.ActionTLNLHistory> temp = (ArrayList<CLAction.ActionTLNLHistory>) commonParam.get("ArrayList<CLAction.ActionTLNLHistory>");
    if (temp == null) {
      CLAction.ActionTLNLHistoryResponse tlnlResponse = new CLAction(context.getLogger()).getActionTLNLHistory(baInfo.getCaNumber(), baInfo.getBaNumber(), baInfo.getMinDueDate());
      context.getLogger().debug(tlnlResponse.info().toString());
      switch (tlnlResponse.getStatusCode()) {
        case CLAction.ActionTLNLHistoryResponse.STATUS_COMPLETE: {
          ArrayList<CLAction.ActionTLNLHistory> tm = new ArrayList<>();
          ArrayList<CLAction.ActionTLNLHistory> list = tlnlResponse.getResponse();
          for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getActionType() == Constants.ActionType.TerminateLetter) {
              tm.add(list.get(i));
              commonParam.put("ArrayList<CLAction.ActionTLNLHistory>", tm);
              return tm;
            }
          }
          commonParam.put("ArrayList<CLAction.ActionTLNLHistory>", tm);
          return tm;
        }
        case CLAction.ActionTLNLHistoryResponse.STATUS_DATA_NOT_FOUND: {
          ArrayList<CLAction.ActionTLNLHistory> tm = new ArrayList<>();
          commonParam.put("ArrayList<CLAction.ActionTLNLHistory>", tm);
          return tm;
        }
        default: {
          return null;
        }
      }
    } else {
      return temp;
    }

  }

  private boolean param__LETTER_DAT_REF(StringBuffer data, CLTempTreatment.TempTreatment baInfo) {
    String a1 = null;
    ArrayList<CLAction.ActionTLNLHistory> action = getActionTLNLHistory(baInfo);
    if (action == null) {
      data.append("").append(END_LINE_LETTER);
      return false;
    } else if (action.isEmpty()) {
      data.append("").append(END_LINE_LETTER);
    } else {
      a1 = action.get(0).getMessageDate().toString();
      data.append(a1).append(END_LINE_LETTER);
    }
    return true;
  }

  private ArrayList<CLMobileInformation.CLMobileInfo> getMobileReff(CLTempTreatment.TempTreatment baInfo) {
    if (commonParam.get("ArrayList<CLMobileInformation.CLMobileInfo>") != null) {
      return (ArrayList<CLMobileInformation.CLMobileInfo>) commonParam.get("ArrayList<CLMobileInformation.CLMobileInfo>");
    }
    CLMobileInformation tbl = new CLMobileInformation(context.getLogger());
    CLMobileInformation.CLMobileInfoResponse resp = tbl.getMobileRefference(baInfo.getBaNumber(), baInfo.getBaStatus(), 10);
    context.getLogger().debug("getMobileRefference:" + baInfo.getBaNumber() + ":" + baInfo.getBaStatus() + ":" + resp.info().toString());

    switch (resp.getStatusCode()) {
      case CLMobileInformation.CLMobileInfoResponse.STATUS_COMPLETE: {
        commonParam.put("ArrayList<CLMobileInformation.CLMobileInfo>", resp.getResponse());
        return resp.getResponse();
      }
      case CLMobileInformation.CLMobileInfoResponse.STATUS_DATA_NOT_FOUND: {
        ArrayList<CLMobileInformation.CLMobileInfo> empty = new ArrayList<>();
        commonParam.put("ArrayList<CLMobileInformation.CLMobileInfo>", empty);
        return empty;
      }
      default: {
        return null;
      }
    }
  }

  private boolean param__MOBILE_NUM(StringBuffer data, CLTempTreatment.TempTreatment baInfo) {
    ArrayList<CLMobileInformation.CLMobileInfo> ll = getMobileReff(baInfo);
    if (ll == null) {
      data.append("").append(END_LINE_LETTER);
      return false;
    } else if (ll.isEmpty()) {
      data.append("").append(END_LINE_LETTER);
      return true;
    } else {
      String a1 = ll.get(0).getMobileNumber();
      data.append(a1).append(END_LINE_LETTER);
      return true;
    }
  }

  private boolean param__MOBILE_NUM_LIST(StringBuffer data, CLTempTreatment.TempTreatment baInfo) {
    ArrayList<CLMobileInformation.CLMobileInfo> ll = getMobileReff(baInfo);
    if (ll == null) {
      data.append("").append(END_LINE_LETTER);
      return false;
    } else if (ll.isEmpty()) {
      data.append("").append(END_LINE_LETTER);
      return true;
    } else {
      String a1 = "";
      int round = 5;
      if (round > ll.size()) {
        round = ll.size();
      }
      for (int i = 0; i < round; i++) {
        if (i > 0) {
          a1 += ",";
        }
        a1 += ll.get(i).getMobileNumber();
      }

      data.append(a1).append(END_LINE_LETTER);
      return true;
    }
  }

  private boolean param__MODE_ASSIGN(StringBuffer data) {
    data.append(assignId).append(END_LINE_LETTER);
    return true;
  }

  private boolean param__MODE_ASSIGN_REF(StringBuffer data, CLTempTreatment.TempTreatment baInfo) {
    String a1 = null;
    ArrayList<CLAction.ActionTLNLHistory> action = getActionTLNLHistory(baInfo);
    if (action == null) {
      data.append("").append(END_LINE_LETTER);
      return false;
    } else if (action.isEmpty()) {
      data.append("").append(END_LINE_LETTER);
    } else {
      a1 = action.get(0).getActionMode().getCodeString() + "" + action.get(0).getBatchId().toPlainString();
      data.append(a1).append(END_LINE_LETTER);
    }
    return true;
  }

  private boolean param__NEW_BILLING_ACC_NUM(StringBuffer data, CLTempTreatment.TempTreatment baInfo) {
    data.append(baInfo.getRefBaNumber()).append(END_LINE_LETTER);
    return true;
  }

  private boolean param__OWNER_NAME(StringBuffer data, CLTempTreatment.TempTreatment baInfo) {
    CLCAInfo.CAInfomation temp = getCAInfoAddress(baInfo.getCaNumber());
    String a1 = temp.getCaTitle() + " " + temp.getCaName();
    data.append(a1).append(END_LINE_LETTER);
    return true;
  }

  private boolean param__PARTY_DESC(StringBuffer data, CLTempTreatment.TempTreatment baInfo) {
    data.append("").append(END_LINE_LETTER);
    return true;
  }

  private boolean param__PARTY_TELEPHONE(StringBuffer data, CLTempTreatment.TempTreatment baInfo) {
    data.append("").append(END_LINE_LETTER);
    return true;
  }

  private boolean param__PAYMENT_DUE_DAT(StringBuffer data, CLTempTreatment.TempTreatment baInfo) {
    //baDetail.getMinDueDate() + date from lov
    Date date = UtilityDate.addDate(context, baInfo.getMinDueDate(), 60, Calendar.DATE);
    data.append(date.toString()).append(END_LINE_LETTER);
    return true;
  }

  private boolean param__PROVINCE_CODE(StringBuffer data, CLTempTreatment.TempTreatment baInfo, CLContactInfo.CLContactAddressInfo contactAddress) {
    String a1 = null;
    switch (contactOption) {
      case VAT_Address: {
        CLCAInfo.CAInfomation temp = getCAInfoAddress(baInfo.getCaNumber());
        a1 = temp.getVatAddressLine5();
        break;
      }
      case Billing_Address: {
        a1 = baInfo.getBillAddressLine5();
        break;
      }
      case Contact_Address: {
        a1 = contactAddress.getContactAddrLine5();
        break;
      }

      default: {
        return false;
      }
    }
    data.append(a1).append(END_LINE_LETTER);
    return true;
  }

  private boolean param__REGISTER_DAT(StringBuffer data, CLTempTreatment.TempTreatment baInfo) {
    ArrayList<CLMobileInformation.CLMobileInfo> ll = getMobileReff(baInfo);
    if (ll == null) {
      data.append("").append(END_LINE_LETTER);
      return false;
    } else if (ll.isEmpty()) {
      data.append("").append(END_LINE_LETTER);
      return true;
    } else {
      Constants.Language language = Constants.mapLanguage(baInfo.getBillLanguage(), Constants.Language.THAI);
      String a1 = null;
      if (language.isEngLanguage()) {
        a1 = ll.get(0).getRegisterDate().toString();
      } else {
        a1 = ll.get(0).getRegisterDate().toString();
      }
      data.append(a1).append(END_LINE_LETTER);
    }
    return true;
  }

  private boolean param__SERVICE_NAME(StringBuffer data, CLTempTreatment.TempTreatment baInfo) {
    data.append("ผู้ให้บริการอินเตอร์เน็ต").append(END_LINE_LETTER);
    return true;
  }

  private boolean param__STATUS_DAT(StringBuffer data, CLTempTreatment.TempTreatment baInfo) {
    ArrayList<CLMobileInformation.CLMobileInfo> ll = getMobileReff(baInfo);
    if (ll == null) {
      data.append("").append(END_LINE_LETTER);
      return false;
    } else if (ll.isEmpty()) {
      data.append("").append(END_LINE_LETTER);
      return true;
    } else {
      Constants.Language language = Constants.mapLanguage(baInfo.getBillLanguage(), Constants.Language.THAI);
      String a1 = null;
      if (language.isEngLanguage()) {
        a1 = ll.get(0).getMobileStatusDtm().toString();
      } else {
        a1 = ll.get(0).getMobileStatusDtm().toString();
      }
      data.append(a1).append(END_LINE_LETTER);
      return true;
    }
  }

  private boolean param__TERMINATE_MONTH(StringBuffer data, CLTempTreatment.TempTreatment baInfo) {
    ArrayList<CLMobileInformation.CLMobileInfo> ll = getMobileReff(baInfo);

    if (ll == null) {
      data.append("").append(END_LINE_LETTER);
      return false;
    } else if (ll.isEmpty()) {
      data.append("").append(END_LINE_LETTER);
      return true;
    } else {
      Constants.Language language = Constants.mapLanguage(baInfo.getBillLanguage(), Constants.Language.THAI);
      String a1 = null;
      Calendar startCalendar = new GregorianCalendar();
      startCalendar.setTime(ll.get(0).getRegisterDate());
      Calendar endCalendar = new GregorianCalendar();
      endCalendar.setTime(ll.get(0).getMobileStatusDtm());
      if (endCalendar.get(Calendar.DATE) == startCalendar.get(Calendar.DATE)
        && endCalendar.get(Calendar.MONTH) == startCalendar.get(Calendar.MONTH)
        && endCalendar.get(Calendar.YEAR) == startCalendar.get(Calendar.YEAR)) {
        data.append("0").append(END_LINE_LETTER);
        return true;
      }
      int diff = (endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR)) * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH) + 1;
      data.append(diff).append(END_LINE_LETTER);
      return true;
    }
  }

  private boolean param__TOTAL_BALANCE(StringBuffer data, CLTempTreatment.TempTreatment baInfo) {
    // check criteriaLevel CA , select or change business treatment 
    // มีสองวิธี เปลี่ยน Business login การทำ treatment เพื่อ ยกเว้นการหา exampt sms , letter หรือ
    // ใช้วิธี query data อีกรอบ จาก CL_BA_INFO + CL_HIS_BA_ACCOUNT_BALANCE

    double all = 0.0;
    for (int i = 0; i < listTempTreatment.size(); i++) {
      all += listTempTreatment.get(i).getDueTotalBal().doubleValue();
    }
    data.append(all).append(END_LINE_LETTER);
    return true;
  }

  private boolean param__ZIP_CODE(StringBuffer data, CLTempTreatment.TempTreatment baInfo, CLContactInfo.CLContactAddressInfo contactAddress) {
    String a1 = null;
    switch (contactOption) {
      case VAT_Address: {
        CLCAInfo.CAInfomation temp = getCAInfoAddress(baInfo.getCaNumber());
        a1 = temp.getVatZipcode();
        break;
      }
      case Billing_Address: {
        a1 = baInfo.getBillZipcode();
        break;
      }
      case Contact_Address: {
        a1 = contactAddress.getContactZipcode();
        break;
      }

      default: {
        return false;
      }
    }
    data.append(a1).append(END_LINE_LETTER);
    return true;
  }
  //</editor-fold>

  private final ObjectLetter objectLetter = new ObjectLetter();

  public ObjectLetter getObjectLetter() {
    return objectLetter;
  }

  public class ObjectLetter {

    private String fileName;
    private BigDecimal rowId;
    private Constants.Language language;
    private String languageCode;
    private StringBuffer labelValue;
    private String letterType;

    private final CLLetterTreatment tbl = new CLLetterTreatment(context.getLogger());

    private final CLLetterTreatment.CLMessage clMessage = tbl.buildCLMessage();
    private final ArrayList<CLLetterTreatment.CLMessageTreatment> listTreatmentId = new ArrayList<>();
    private final ArrayList<CLLetterTreatment.CLLetter> listClLetter = new ArrayList<>();

    public String getLetterType() {
      return letterType;
    }

    public void setLetterType(String letterType) {
      this.letterType = letterType;
    }

    public String getFileName() {
      return fileName;
    }

    public void setFileName(String fileName) {
      this.fileName = fileName;
    }

    public CLLetterTreatment.CLMessage getClMessage() {
      return clMessage;
    }

    public ArrayList<CLLetterTreatment.CLMessageTreatment> getListTreatmentId() {
      return listTreatmentId;
    }

    public ArrayList<CLLetterTreatment.CLLetter> getListClLetter() {
      return listClLetter;
    }

    public void setCLMessage(CLTempTreatment.TempTreatment ba, CLCriteriaTemplate.CriteriaTemplateInfo template) {

      clMessage.setCaNumber(ba.getCaNumber());
      if (getBaRefference().getCriteriaLevel().isBALevel()) {
        clMessage.setBaNumber(ba.getBaNumber());
      }
      clMessage.setTemplateId(template.getTemplateId());
      clMessage.setTemplateCriteriaId(template.getTemplateCriteriaId());
      clMessage.setMessageActionId(template.getActionId());
      clMessage.setMessageCompanyCode(ba.getCompanyCode());
      clMessage.setMessageDate(new Date());
      clMessage.setMessageSender(null);
      clMessage.setMessageAttachment(null);
      Date now = new Date();
      clMessage.setCreated(now);
      clMessage.setCreatedBy("system");
      clMessage.setLastUpd(now);
      clMessage.setLastUpdBy("system");
    }

    public void setCLMessageTreatment(ArrayList<CLTempTreatment.TempTreatment> listBa) {
      for (int i = 0; i < listBa.size(); i++) {
        CLTempTreatment.TempTreatment tempBa = listBa.get(i);
        CLLetterTreatment.CLMessageTreatment clMessageTreatmentReqt = tbl.buildCLMessageTreatment();
        clMessageTreatmentReqt.setTreatmentId(tempBa.getTreatmentId());
        clMessageTreatmentReqt.setCreated(new Date());
        clMessageTreatmentReqt.setCreatedBy("system");
        clMessageTreatmentReqt.setLastUpd(new Date());
        clMessageTreatmentReqt.setLastUpdBy("system");
        listTreatmentId.add(clMessageTreatmentReqt);
      }
    }

    public void addCLLetter(String letterMessage, CLTempTreatment.TempTreatment ba, CLContactInfo.CLContactAddressInfo contactAddress, CLCriteriaTemplate.CriteriaTemplateInfo template) {
      CLLetterTreatment.CLLetter tempLetter = tbl.buildCLLetter();
      
      tempLetter.setMessageId(null);
      tempLetter.setTemplateLanguageId(template.getTemplateLanguageId());
      tempLetter.setAddressType(new BigDecimal(contactOption.getCode()));

      switch (contactOption) {
        case VAT_Address: {
          CLCAInfo.CAInfomation temp = getCAInfoAddress(ba.getCaNumber());
          tempLetter.setAddressName(temp.getVatAddressName());
          tempLetter.setAddressLine1(temp.getVatAddressLine1());
          tempLetter.setAddressLine2(temp.getVatAddressLine2());
          tempLetter.setAddressLine3(temp.getVatAddressLine3());
          tempLetter.setAddressLine4(temp.getVatAddressLine4());
          tempLetter.setAddressLine5(temp.getVatAddressLine5());
          tempLetter.setZipcode(temp.getVatZipcode());
          break;
        }
        case Billing_Address: {
          tempLetter.setAddressName(ba.getBillAddressName());
          tempLetter.setAddressLine1(ba.getBillAddressLine1());
          tempLetter.setAddressLine2(ba.getBillAddressLine2());
          tempLetter.setAddressLine3(ba.getBillAddressLine3());
          tempLetter.setAddressLine4(ba.getBillAddressLine4());
          tempLetter.setAddressLine5(ba.getBillAddressLine5());
          tempLetter.setZipcode(ba.getBillZipcode());
          break;
        }
        case Contact_Address: {
          tempLetter.setAddressName(contactAddress.getContactName());
          tempLetter.setAddressLine1(contactAddress.getContactAddrLine1());
          tempLetter.setAddressLine2(contactAddress.getContactAddrLine2());
          tempLetter.setAddressLine3(contactAddress.getContactAddrLine3());
          tempLetter.setAddressLine4(contactAddress.getContactAddrLine4());
          tempLetter.setAddressLine5(contactAddress.getContactAddrLine5());
          tempLetter.setZipcode(contactAddress.getContactZipcode());
          break;
        }
        default: {

        }
      }
      tempLetter.setLetterMessage(letterMessage);
      tempLetter.setLetterPath(null);
      tempLetter.setLetterEmailAddress(null);
      tempLetter.setLetterEmailId(null);
      tempLetter.setLetterCode(getBarCode(ba));
      if (template.getLetterRegisBoo() == 'Y') {
        tempLetter.setTrackingNumber(getBarCodeAR(ba));
        tempLetter.setTrackingStatus(BigDecimal.ONE);
      } else {
        tempLetter.setTrackingNumber(null);
        tempLetter.setTrackingStatus(BigDecimal.ZERO);
      }
      tempLetter.setTrackingDtm(null);
      tempLetter.setTrackingSeq(null);
      tempLetter.setActionStatus(new BigDecimal(Constants.ActionStatus.InProgress.getCode()));
      tempLetter.setActionStatusDtm(new Date());
      tempLetter.setActionRemark(null);
      tempLetter.setBatchId(null);
      tempLetter.setCreated(new Date());
      tempLetter.setCreatedBy("system");
      tempLetter.setLastUpd(new Date());
      tempLetter.setLastUpdBy("system");
      listClLetter.add(tempLetter);
      /**/
    }

    public BigDecimal getRowId() {
      return rowId;
    }

    public void setRowId(BigDecimal rowId) {
      this.rowId = rowId;
    }

    public th.co.ais.cpac.cl.template.configuration.Constants.Language getLanguage() {
      return language;
    }

    public void setLanguage(th.co.ais.cpac.cl.template.configuration.Constants.Language language) {
      this.language = language;
    }

    public String getLanguageCode() {
      return languageCode;
    }

    public void setLanguageCode(String languageCode) {
      this.languageCode = languageCode;
    }

    public StringBuffer getLabelValue() {
      return labelValue;
    }

    public void setLabelValue(StringBuffer labelValue) {
      this.labelValue = labelValue;
    }

    public void addLabelValue(String label) {
      if (labelValue == null) {
        labelValue = new StringBuffer();
        labelValue.append(label);
      } else {
        labelValue.append(END_LINE_LETTER);
        labelValue.append(label);
      }
    }

    public void setFileName(String companyCode, String actionType) {
      //v_CompanyCode_ModeID_v_AssignID_v_TemplateID.txt (จดหมายติดตามหนี้แบบไม่รวมซอง)
      //v_CompanyCode_ModeID_v_AssignID_v_TemplateID_v_Language_HC.txt (จดหมายติดตามหนี้แบบรวมซอง)

      this.fileName = "";

    }

  }

}
