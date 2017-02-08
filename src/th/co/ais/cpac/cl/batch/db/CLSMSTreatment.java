/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.db;

import java.math.BigDecimal;
import java.util.Date;
import th.co.ais.cpac.cl.batch.Constants;
import th.co.ais.cpac.cl.batch.cnf.CNFDatabase;
import th.co.ais.cpac.cl.common.UtilityLogger;
import th.co.ais.cpac.cl.template.database.DBConnectionPools;
import th.co.ais.cpac.cl.template.database.DBTemplatesInsert;
import th.co.ais.cpac.cl.template.database.DBTemplatesResponse;
import th.co.ais.cpac.cl.template.database.DBTemplatesUpdate;

/**
 *
 * @author Optimus
 */
public class CLSMSTreatment {

  protected final UtilityLogger logger;

  public CLSMSTreatment(UtilityLogger logger) {
    this.logger = logger;
  }

  public boolean buildConnection() {
    CNFDatabase cnf = new CNFDatabase();
    connection = new DBConnectionPools<>(cnf, logger);
    connection.getConnection();
    return connection.ready();
  }
  DBConnectionPools<CNFDatabase, UtilityLogger> connection;

  public DBConnectionPools getDBConnection() {
    return connection;
  }

  public class ExecuteResponse extends DBTemplatesResponse<Boolean> {

    @Override
    protected Boolean createResponse() {
      return false;
    }

    @Override
    public void setResponse(Boolean boo) {
      response = boo;
    }
  }

  //<editor-fold defaultstate="collapsed" desc="Insert CL_MESSAGE">
  public class CLMessage {

    protected CLMessage() {
    }
    private BigDecimal messageId;
    private String caNumber;
    private String baNumber;
    private BigDecimal templateId;
    private BigDecimal templateCriteriaId;
    private BigDecimal messageActionId;
    private String messageCompanyCode;
    private Date messageDate;
    private String messageSender;
    private String messageValue;
    private String messageAttachment;
    private Date created;
    private String createdBy;
    private Date lastUpd;
    private String lastUpdBy;

    public BigDecimal getMessageId() {
      return messageId;
    }

    public void setMessageId(BigDecimal messageId) {
      this.messageId = messageId;
    }

    public String getCaNumber() {
      return caNumber;
    }

    public void setCaNumber(String caNumber) {
      this.caNumber = caNumber;
    }

    public String getBaNumber() {
      return baNumber;
    }

    public void setBaNumber(String baNumber) {
      this.baNumber = baNumber;
    }

    public BigDecimal getTemplateId() {
      return templateId;
    }

    public void setTemplateId(BigDecimal templateId) {
      this.templateId = templateId;
    }

    public BigDecimal getTemplateCriteriaId() {
      return templateCriteriaId;
    }

    public void setTemplateCriteriaId(BigDecimal templateCriteriaId) {
      this.templateCriteriaId = templateCriteriaId;
    }

    public BigDecimal getMessageActionId() {
      return messageActionId;
    }

    public void setMessageActionId(BigDecimal messageActionId) {
      this.messageActionId = messageActionId;
    }

    public String getMessageCompanyCode() {
      return messageCompanyCode;
    }

    public void setMessageCompanyCode(String messageCompanyCode) {
      this.messageCompanyCode = messageCompanyCode;
    }

 
    public Date getMessageDate() {
      return messageDate;
    }

    public void setMessageDate(Date messageDate) {
      this.messageDate = messageDate;
    }

    public String getMessageSender() {
      return messageSender;
    }

    public void setMessageSender(String messageSender) {
      this.messageSender = messageSender;
    }

    public String getMessageValue() {
      return messageValue;
    }

    public void setMessageValue(String messageValue) {
      this.messageValue = messageValue;
    }

    public String getMessageAttachment() {
      return messageAttachment;
    }

    public void setMessageAttachment(String messageAttachment) {
      this.messageAttachment = messageAttachment;
    }

    public Date getCreated() {
      return created;
    }

    public void setCreated(Date created) {
      this.created = created;
    }

    public String getCreatedBy() {
      return createdBy;
    }

    public void setCreatedBy(String createdBy) {
      this.createdBy = createdBy;
    }

    public Date getLastUpd() {
      return lastUpd;
    }

    public void setLastUpd(Date lastUpd) {
      this.lastUpd = lastUpd;
    }

    public String getLastUpdBy() {
      return lastUpdBy;
    }

    public void setLastUpdBy(String lastUpdBy) {
      this.lastUpdBy = lastUpdBy;
    }

  }

  protected class InsertCLMessage extends DBTemplatesInsert<ExecuteResponse, UtilityLogger, DBConnectionPools> {

    public InsertCLMessage(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected ExecuteResponse createResponse() {
      return new ExecuteResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder column = new StringBuilder();
      StringBuilder value = new StringBuilder();

      genNumber("MESSAGE_ID", request.getMessageId(), null, column, value, false);
      genString("CA_NO", request.getCaNumber(), null, column, value, false);
      genString("BA_NO", request.getBaNumber(), null, column, value, false);
      genNumber("TEMPLATE_ID", request.getTemplateId(), null, column, value, false);
      genNumber("TEMPLATE_CRITERIA_ID", request.getTemplateCriteriaId(), null, column, value, false);
      genNumber("MESSAGE_ACTION_ID", request.getMessageActionId(), null, column, value, false);
      genString("MESSAGE_COMPANY", request.getMessageCompanyCode(), null, column, value, false);
      genDateTime("MESSAGE_DATE", request.getMessageDate(), null, column, value, false);
      genString("MESSAGE_SENDER", request.getMessageSender(), null, column, value, false);
      genString("MESSAGE_VALUE", request.getMessageValue(), null, column, value, false);
      genString("MESSAGE_ATTACHMENT", request.getMessageAttachment(), null, column, value, false);
      genMethod("CREATED", "getdate()", column, value, false);
      genString("CREATED_BY", request.getCreatedBy(), column, value, false);
      genMethod("LAST_UPD", "getdate()", column, value, false);
      genString("LAST_UPD_BY", request.getLastUpdBy(), column, value, false);

      StringBuilder sql = new StringBuilder();
      sql.append("INSERT INTO dbo.CL_MESSAGE(").append(column).append(")");
      sql.append("VALUES(").append(value).append(")");

      return sql;
    }

    private CLMessage request;

    protected ExecuteResponse execute(CLMessage request) {
      this.request = request;
      return super.executeUpdateGetIdentity(connection, false);
    }

  }

  public ExecuteResponse insertCLMessage(CLMessage request) {
    return new InsertCLMessage(logger).execute(request);
  }

  public CLMessage buildCLMessage() {
    return new CLMessage();
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="Insert CL_MESSAGE_TREATMENT">
  public class CLMessageTreatment {

    protected CLMessageTreatment() {
    }
    private BigDecimal treatmentId;
    private BigDecimal messageId;
    private Date created;
    private String createdBy;
    private Date lastUpd;
    private String lastUpdBy;

    public BigDecimal getTreatmentId() {
      return treatmentId;
    }

    public void setTreatmentId(BigDecimal treatmentId) {
      this.treatmentId = treatmentId;
    }

    public BigDecimal getMessageId() {
      return messageId;
    }

    public void setMessageId(BigDecimal messageId) {
      this.messageId = messageId;
    }

    public Date getCreated() {
      return created;
    }

    public void setCreated(Date created) {
      this.created = created;
    }

    public String getCreatedBy() {
      return createdBy;
    }

    public void setCreatedBy(String createdBy) {
      this.createdBy = createdBy;
    }

    public Date getLastUpd() {
      return lastUpd;
    }

    public void setLastUpd(Date lastUpd) {
      this.lastUpd = lastUpd;
    }

    public String getLastUpdBy() {
      return lastUpdBy;
    }

    public void setLastUpdBy(String lastUpdBy) {
      this.lastUpdBy = lastUpdBy;
    }

  }

  protected class InsertCLMessageTreatment extends DBTemplatesInsert<ExecuteResponse, UtilityLogger, DBConnectionPools> {

    public InsertCLMessageTreatment(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected ExecuteResponse createResponse() {
      return new ExecuteResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder column = new StringBuilder();
      StringBuilder value = new StringBuilder();

      genNumber("TREATMENT_ID", request.getTreatmentId(), null, column, value, false);
      genNumber("MESSAGE_ID", request.getMessageId(), null, column, value, false);
      genMethod("CREATED", "getdate()", column, value, false);
      genString("CREATED_BY", request.getCreatedBy(), column, value, false);
      genMethod("LAST_UPD", "getdate()", column, value, false);
      genString("LAST_UPD_BY", request.getLastUpdBy(), column, value, false);

      StringBuilder sql = new StringBuilder();
      sql.append("INSERT INTO CL_MESSAGE_TREATMENT(").append(column).append(")");
      sql.append("VALUES(").append(value).append(")");

      return sql;
    }

    private CLMessageTreatment request;

    protected ExecuteResponse execute(CLMessageTreatment request) {
      this.request = request;
      return super.executeUpdate(connection, false);
    }

  }

  public ExecuteResponse insertCLMessageTreatment(CLMessageTreatment request) {
    return new InsertCLMessageTreatment(logger).execute(request);
  }

  public CLMessageTreatment buildCLMessageTreatment() {
    return new CLMessageTreatment();
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="Insert CL_SMS">
  public class CLSms {

    protected CLSms() {
    }

    private BigDecimal messageId;
    private String baNumber;
    private String mobileNumber;
    private BigDecimal templateLanguageId;
    private BigDecimal actionStatus;
    private Date actionStatusDtm;
    private String actionRemark;
    private BigDecimal batchId;
    private BigDecimal notificationId;
    private String messageValue;
    private Date created;
    private String createdBy;
    private Date lastUpd;
    private String lastUpdBy;

    public BigDecimal getMessageId() {
      return messageId;
    }

    public void setMessageId(BigDecimal messageId) {
      this.messageId = messageId;
    }

    public String getBaNumber() {
      return baNumber;
    }

    public void setBaNumber(String baNumber) {
      this.baNumber = baNumber;
    }

    public String getMobileNumber() {
      return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
      this.mobileNumber = mobileNumber;
    }

    public BigDecimal getTemplateLanguageId() {
      return templateLanguageId;
    }

    public void setTemplateLanguageId(BigDecimal templateLanguageId) {
      this.templateLanguageId = templateLanguageId;
    }

    public BigDecimal getActionStatus() {
      return actionStatus;
    }

    public void setActionStatus(BigDecimal actionStatus) {
      this.actionStatus = actionStatus;
    }

    public Date getActionStatusDtm() {
      return actionStatusDtm;
    }

    public void setActionStatusDtm(Date actionStatusDtm) {
      this.actionStatusDtm = actionStatusDtm;
    }

    public String getActionRemark() {
      return actionRemark;
    }

    public void setActionRemark(String actionRemark) {
      this.actionRemark = actionRemark;
    }

    public BigDecimal getBatchId() {
      return batchId;
    }

    public void setBatchId(BigDecimal batchId) {
      this.batchId = batchId;
    }

    public BigDecimal getNotificationId() {
      return notificationId;
    }

    public void setNotificationId(BigDecimal notificationId) {
      this.notificationId = notificationId;
    }

    public String getMessageValue() {
      return messageValue;
    }

    public void setMessageValue(String messageValue) {
      this.messageValue = messageValue;
    }

    public Date getCreated() {
      return created;
    }

    public void setCreated(Date created) {
      this.created = created;
    }

    public String getCreatedBy() {
      return createdBy;
    }

    public void setCreatedBy(String createdBy) {
      this.createdBy = createdBy;
    }

    public Date getLastUpd() {
      return lastUpd;
    }

    public void setLastUpd(Date lastUpd) {
      this.lastUpd = lastUpd;
    }

    public String getLastUpdBy() {
      return lastUpdBy;
    }

    public void setLastUpdBy(String lastUpdBy) {
      this.lastUpdBy = lastUpdBy;
    }

  }

  protected class InsertCLSms extends DBTemplatesInsert<ExecuteResponse, UtilityLogger, DBConnectionPools> {

    public InsertCLSms(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected ExecuteResponse createResponse() {
      return new ExecuteResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder column = new StringBuilder();
      StringBuilder value = new StringBuilder();

      genNumber("MESSAGE_ID", request.getMessageId(), null, column, value, false);
      genString("BA_NO", request.getBaNumber(), null, column, value, false);
      genString("MOBILE_NO", request.getMobileNumber(), null, column, value, false);
      genNumber("TEMPLATE_LANGUAGE_ID", request.getTemplateLanguageId(), null, column, value, false);
      genNumber("ACTION_STATUS", request.getActionStatus(), null, column, value, false);
      genDateTime("ACTION_STATUS_DTM", request.getActionStatusDtm(), null, column, value, false);
      genString("ACTION_REMARK", request.getActionRemark(), null, column, value, false);
      genNumber("BATCH_ID", request.getBatchId(), null, column, value, false);
      genNumber("NOTIFICATION_ID", request.getNotificationId(), null, column, value, false);
      genString("SMS_MESSAGE", request.getMessageValue(), null, column, value, false);

      genMethod("CREATED", "getdate()", column, value, false);
      genString("CREATED_BY", request.getCreatedBy(), column, value, false);
      genMethod("LAST_UPD", "getdate()", column, value, false);
      genString("LAST_UPD_BY", request.getLastUpdBy(), column, value, false);

      StringBuilder sql = new StringBuilder();
      sql.append("INSERT INTO CL_SMS(").append(column).append(")");
      sql.append("VALUES(").append(value).append(")");

      return sql;
    }

    private CLSms request;

    protected ExecuteResponse execute(CLSms request) {
      this.request = request;
      return super.executeUpdateGetIdentity(connection, false);
    }

  }

  public ExecuteResponse insertCLSms(CLSms request) {
    return new InsertCLSms(logger).execute(request);
  }

  public CLSms buildCLSms() {
    return new CLSms();
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="UpdateNotificationId  table CL_SMS ">
  protected class UpdateNotificationId extends DBTemplatesUpdate<ExecuteResponse, UtilityLogger, DBConnectionPools> {

    private BigDecimal smsId;

    private Constants.ActionStatus actionStatus;
    private Date actionStatusDate;
    private BigDecimal notificationId;

    public UpdateNotificationId(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append("UPDATE dbo.CL_SMS ").append(Constants.END_LINE);
      sql.append("SET LAST_UPD= getdate() , LAST_UPD_BY='system' ");
      if (actionStatus != null) {
        sql.append(",ACTION_STATUS = ").append(actionStatus.getCode());
      }
      if (actionStatus != null) {
        sql.append(", ACTION_STATUS_DTM = getdate() ").append(Constants.END_LINE);
      }
      if (notificationId != null) {
        sql.append(", NOTIFICATION_ID = ").append(notificationId.toPlainString()).append(Constants.END_LINE);
      }

//      sql.append("ACTION_STATUS=0, ACTION_STATUS_DTM='2017-1-12 22:30:13', NOTIFICATION_ID=0 , LAST_UPD='2017-1-12 22:30:13', LAST_UPD_BY='' ").append(Constants.END_LINE);
      sql.append(" WHERE SMS_ID = ").append(smsId.toPlainString()).append(Constants.END_LINE);
      return sql;
    }

    @Override
    protected ExecuteResponse createResponse() {
      return new ExecuteResponse();
    }

    protected ExecuteResponse execute(BigDecimal smsId, Constants.ActionStatus actionStatus, Date actionStatusDate, BigDecimal notificationId) {
      this.actionStatus = actionStatus;
      this.actionStatusDate = actionStatusDate;
      this.notificationId = notificationId;
      this.smsId = smsId;
      return executeUpdate(connection, false);
    }
  }

  public ExecuteResponse updateNotificationId(BigDecimal smsId, Constants.ActionStatus actionStatus, Date actionStatusDate, BigDecimal notificationId) {
    return new UpdateNotificationId(logger).execute(smsId, actionStatus, actionStatusDate, notificationId);
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="UpdateNotificationId  table CL_SMS ">
  protected class UpdateTreatmentStatus extends DBTemplatesUpdate<ExecuteResponse, UtilityLogger, DBConnectionPools> {

    private BigDecimal treatmentId;
    private Constants.ActionStatus actionStatus;
    private Date actionStatusDate;

    public UpdateTreatmentStatus(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();

      sql.append("UPDATE dbo.CL_TREATMENT ").append(Constants.END_LINE);
      sql.append("SET  ").append(Constants.END_LINE);
      sql.append("LAST_UPD = getdate() ").append(Constants.END_LINE);
      sql.append(",LAST_UPD_BY = 'system' ").append(Constants.END_LINE);

      sql.append(",ACTION_STATUS = ").append(actionStatus.getCode()).append(Constants.END_LINE);

      sql.append(",ACTION_STATUS_DTM = CONVERT( datetime , '").append(toString(actionStatusDate, "yyyy/MM/dd HH:mm:ss", "")).append("' ,121 ) ").append(Constants.END_LINE);
      sql.append("WHERE TREATMENT_ID = ").append(treatmentId.toPlainString()).append(Constants.END_LINE);

      return sql;
    }

    @Override
    protected ExecuteResponse createResponse() {
      return new ExecuteResponse();
    }

    protected ExecuteResponse execute(BigDecimal treatmentId, Constants.ActionStatus actionStatus, Date actionStatusDate) {
      this.treatmentId = treatmentId;
      this.actionStatus = actionStatus;
      this.actionStatusDate = actionStatusDate;
      return executeUpdate(connection, false);
    }
  }

  public ExecuteResponse updateStatusCLTreatment(BigDecimal treatmentId, Constants.ActionStatus actionStatus, Date actionStatusDate) {
    return new UpdateTreatmentStatus(logger).execute(treatmentId, actionStatus, actionStatusDate);
  }
  //</editor-fold>

}
