/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.template;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import th.co.ais.cpac.cl.batch.db.CLCriteriaTemplate;
import th.co.ais.cpac.cl.batch.db.JSNotification;

/**
 *
 * @author Optimus
 */
public abstract class SMSLetterTreatmentTemplate extends TreatmentTemplate {

  public SMSLetterTreatmentTemplate(String[] args) {
    super(args);
  }

  protected HashMap<BigDecimal, HashMap<BigDecimal, HashMap<String, HashMap<BigDecimal, CLCriteriaTemplate.CriteriaTemplateInfo>>>> getTemplateInfo() {
    CLCriteriaTemplate criteriaTemplate = new CLCriteriaTemplate(context.getLogger());
    CLCriteriaTemplate.CriteriaTemplateInfoResponse criteriaId = criteriaTemplate.getCriteriaTemplateInfo(listActionId);
    context.getLogger().debug(criteriaId.info().toString());
    switch (criteriaId.getStatusCode()) {
      case CLCriteriaTemplate.CriteriaTemplateInfoResponse.STATUS_COMPLETE: {
        return criteriaId.getResponse();
      }
      case CLCriteriaTemplate.CriteriaTemplateInfoResponse.STATUS_DATA_NOT_FOUND: {
        return null;
      }
      default: {
        return null;
      }
    }
  }

  protected HashMap<BigDecimal, HashMap<BigDecimal, ArrayList<CLCriteriaTemplate.ParameterTemplate>>> getParameterInfo() {
    CLCriteriaTemplate criteriaTemplate = new CLCriteriaTemplate(context.getLogger());
    CLCriteriaTemplate.ParameterSMSResponse tempateParameter = criteriaTemplate.getListParameter(listActionId);
    context.getLogger().debug(tempateParameter.info().toString());
    switch (tempateParameter.getStatusCode()) {
      case CLCriteriaTemplate.ParameterSMSResponse.STATUS_COMPLETE: {
        return tempateParameter.getResponse();
      }
      case CLCriteriaTemplate.ParameterSMSResponse.STATUS_DATA_NOT_FOUND: {
        return null;
      }
      default: {
        return null;
      }
    }
  }

  protected HashMap<String, CLCriteriaTemplate.CLSenderInfo> getSenderInfo() {
    CLCriteriaTemplate criteriaTemplate = new CLCriteriaTemplate(context.getLogger());
    CLCriteriaTemplate.CLSenderResponse sender = criteriaTemplate.getCLSenderInfo();
    context.getLogger().debug(sender.info().toString());
    switch (sender.getStatusCode()) {
      case CLCriteriaTemplate.CLSenderResponse.STATUS_COMPLETE: {
        return sender.getResponse();
      }
      case CLCriteriaTemplate.CLSenderResponse.STATUS_DATA_NOT_FOUND: {
        return new HashMap<>();
      }
      default: {
        return null;
      }
    }
  }

  protected JSNotification.LotIdNotify getLotId() {
    JSNotification noti = new JSNotification(context.getLogger());
    JSNotification.LotIdNotifyResponse rs = noti.getLotId("12345");
    context.getLogger().debug(rs.info().toString());
    switch (rs.getStatusCode()) {
      case JSNotification.LotIdNotifyResponse.STATUS_COMPLETE: {
        return rs.getResponse();
      }
      case JSNotification.LotIdNotifyResponse.STATUS_DATA_NOT_FOUND: {
        return null;
      }
      default: {
        return null;
      }
    }
  }

  

}
