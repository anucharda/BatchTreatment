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
import java.util.Iterator;
import th.co.ais.cpac.cl.batch.Constants;
import th.co.ais.cpac.cl.batch.db.CLCriteriaTemplate;
import th.co.ais.cpac.cl.batch.db.CLTempTreatment;
import th.co.ais.cpac.cl.batch.db.JSNotification;
import th.co.ais.cpac.cl.common.Context;

/**
 *
 * @author Optimus
 */
public class SMSLetterWorkerTreatmentTemplate extends TreatmentWorkerTemplate {

  public SMSLetterWorkerTreatmentTemplate(Context context, ArrayList<CLTempTreatment.TempTreatment> listTempTreatment) {
    super(context, listTempTreatment);
  }

  protected CLCriteriaTemplate.CriteriaTemplateInfo getTemplate(HashMap<BigDecimal, CLCriteriaTemplate.CriteriaTemplateInfo> templateInfo, String lang) {
    
    th.co.ais.cpac.cl.template.configuration.Constants.Language language = Constants.mapLanguage(lang, th.co.ais.cpac.cl.template.configuration.Constants.Language.THAI);
    
    CLCriteriaTemplate.CriteriaTemplateInfo template = templateInfo.get(language.getLanguageId());

    if (template != null) {
      return template;
    }

    CLCriteriaTemplate.CriteriaTemplateInfo templateDefault = null;
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

    return templateDefault;
  }

  protected CLCriteriaTemplate.CLSenderInfo getSenderSMSInfo(ArrayList<String> listBaNumb, String companyCode,String listMobileStatus) {
    CLCriteriaTemplate criteriaTemplate = new CLCriteriaTemplate(context.getLogger());
    CLCriteriaTemplate.CLSenderResponse sender = criteriaTemplate.getSenderSMSInfo(companyCode, listBaNumb,listMobileStatus);
    context.getLogger().debug(sender.info().toString());
    switch (sender.getStatusCode()) {
      case CLCriteriaTemplate.CLSenderResponse.STATUS_COMPLETE: {
        return sender.getResponse().get("SENDER");
      }
      case CLCriteriaTemplate.CLSenderResponse.STATUS_DATA_NOT_FOUND: {
        return null;
      }
      default: {
        return null;
      }
    }
  }



  protected JSNotification.SMSNotify sendSms(Date sendDateTime, String notificationType, String requestBy, String msisdn, String requestId, String lotId, String smsMessage, String smsSender) {
    JSNotification noti = new JSNotification(context.getLogger());
    JSNotification.SMSNotifyResponse rs = noti.sendSms(sendDateTime, notificationType, requestBy, msisdn, requestId, lotId, smsMessage, smsSender);
    context.getLogger().debug(rs.info().toString());
    switch (rs.getStatusCode()) {
      case JSNotification.SMSNotifyResponse.STATUS_COMPLETE: {
        return rs.getResponse();
      }
      case JSNotification.SMSNotifyResponse.STATUS_DATA_NOT_FOUND: {
        return null;
      }
      default: {
        return null;
      }
    }
  }

}
