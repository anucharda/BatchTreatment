/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.db;

import java.math.BigDecimal;
import java.util.Date;
import th.co.ais.cpac.cl.batch.cnf.CNFDatabase;
import th.co.ais.cpac.cl.common.UtilityLogger;
import th.co.ais.cpac.cl.template.database.DBConnectionPools;
import th.co.ais.cpac.cl.template.database.DBTemplatesInsert;
import th.co.ais.cpac.cl.template.database.DBTemplatesResponse;

/**
 *
 * @author Optimus
 */
public class CLLetterTreatment {

  protected final UtilityLogger logger;

  public CLLetterTreatment(UtilityLogger logger) {
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

      //genNumber("MESSAGE_ID", request.getMessageId(), null, column, value, false);
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
  //<editor-fold defaultstate="collapsed" desc="Insert CL_LETTER">
  public class CLLetter {

    protected CLLetter() {
    }

    private BigDecimal messageId;
    private BigDecimal templateLanguageId;
    private BigDecimal addressType;
    private String addressName;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String zipcode;
    private String letterMessage;
    private String letterPath;
    private String letterEmailAddress;
    private BigDecimal letterEmailId;
    private String letterCode;
    private String trackingNumber;
    private BigDecimal trackingStatus;
    private Date trackingDtm;
    private BigDecimal trackingSeq;
    private BigDecimal actionStatus;
    private Date actionStatusDtm;
    private String actionRemark;
    private BigDecimal batchId;
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

    public BigDecimal getTemplateLanguageId() {
      return templateLanguageId;
    }

    public void setTemplateLanguageId(BigDecimal templateLanguageId) {
      this.templateLanguageId = templateLanguageId;
    }

    public BigDecimal getAddressType() {
      return addressType;
    }

    public void setAddressType(BigDecimal addressType) {
      this.addressType = addressType;
    }

    public String getAddressName() {
      return addressName;
    }

    public void setAddressName(String addressName) {
      this.addressName = addressName;
    }

    public String getAddressLine1() {
      return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
      this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
      return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
      this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
      return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
      this.addressLine3 = addressLine3;
    }

    public String getAddressLine4() {
      return addressLine4;
    }

    public void setAddressLine4(String addressLine4) {
      this.addressLine4 = addressLine4;
    }

    public String getAddressLine5() {
      return addressLine5;
    }

    public void setAddressLine5(String addressLine5) {
      this.addressLine5 = addressLine5;
    }

    public String getZipcode() {
      return zipcode;
    }

    public void setZipcode(String zipcode) {
      this.zipcode = zipcode;
    }

    public String getLetterMessage() {
      return letterMessage;
    }

    public void setLetterMessage(String letterMessage) {
      this.letterMessage = letterMessage;
    }

    public String getLetterPath() {
      return letterPath;
    }

    public void setLetterPath(String letterPath) {
      this.letterPath = letterPath;
    }

    public String getLetterEmailAddress() {
      return letterEmailAddress;
    }

    public void setLetterEmailAddress(String letterEmailAddress) {
      this.letterEmailAddress = letterEmailAddress;
    }

    public BigDecimal getLetterEmailId() {
      return letterEmailId;
    }

    public void setLetterEmailId(BigDecimal letterEmailId) {
      this.letterEmailId = letterEmailId;
    }

    public String getLetterCode() {
      return letterCode;
    }

    public void setLetterCode(String letterCode) {
      this.letterCode = letterCode;
    }

    public String getTrackingNumber() {
      return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
      this.trackingNumber = trackingNumber;
    }

    public BigDecimal getTrackingStatus() {
      return trackingStatus;
    }

    public void setTrackingStatus(BigDecimal trackingStatus) {
      this.trackingStatus = trackingStatus;
    }

    public Date getTrackingDtm() {
      return trackingDtm;
    }

    public void setTrackingDtm(Date trackingDtm) {
      this.trackingDtm = trackingDtm;
    }

    public BigDecimal getTrackingSeq() {
      return trackingSeq;
    }

    public void setTrackingSeq(BigDecimal trackingSeq) {
      this.trackingSeq = trackingSeq;
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

  protected class InsertCLLetter extends DBTemplatesInsert<ExecuteResponse, UtilityLogger, DBConnectionPools> {

    public InsertCLLetter(UtilityLogger logger) {
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
      genNumber("TEMPLATE_LANGUAGE_ID", request.getTemplateLanguageId(), null, column, value, false);
      genNumber("ADDRESS_TYPE", request.getAddressType(), null, column, value, false);
      genString("ADDRESS_NAME", request.getAddressName(), null, column, value, false);
      genString("ADDRESS_LINE_1", request.getAddressLine1(), null, column, value, false);
      genString("ADDRESS_LINE_2", request.getAddressLine2(), null, column, value, false);
      genString("ADDRESS_LINE_3", request.getAddressLine3(), null, column, value, false);
      genString("ADDRESS_LINE_4", request.getAddressLine4(), null, column, value, false);
      genString("ADDRESS_LINE_5", request.getAddressLine5(), null, column, value, false);
      genString("ZIPCODE", request.getZipcode(), null, column, value, false);
      genString("LETTER_MESSAGE", request.getLetterMessage(), null, column, value, false);
      genString("LETTER_PATH", request.getLetterPath(), null, column, value, false);
      genString("LETTER_EMAIL_ADDRESS", request.getLetterEmailAddress(), null, column, value, false);
      genNumber("LETTER_EMAIL_ID", request.getLetterEmailId(), null, column, value, false);
      genString("LETTER_CODE", request.getLetterCode(), null, column, value, false);
      genString("TRACKING_NUMBER", request.getTrackingNumber(), null, column, value, false);
      genNumber("TRACKING_STATUS", request.getTrackingStatus(), null, column, value, false);
      genDateTime("TRACKING_DTM", request.getTrackingDtm(), null, column, value, false);
      genNumber("TRACKING_SEQ", request.getTrackingSeq(), null, column, value, false);
      genNumber("ACTION_STATUS", request.getActionStatus(), null, column, value, false);
      genDateTime("ACTION_STATUS_DTM", request.getActionStatusDtm(), null, column, value, false);
      genString("ACTION_REMARK", request.getActionRemark(), null, column, value, false);
      genNumber("BATCH_ID", request.getBatchId(), null, column, value, false);

      genMethod("CREATED", "getdate()", column, value, false);
      genString("CREATED_BY", request.getCreatedBy(), column, value, false);
      genMethod("LAST_UPD", "getdate()", column, value, false);
      genString("LAST_UPD_BY", request.getLastUpdBy(), column, value, false);

      StringBuilder sql = new StringBuilder();
      sql.append("INSERT INTO CL_LETTER(").append(column).append(")");
      sql.append("VALUES(").append(value).append(")");

      return sql;
    }

    private CLLetter request;

    protected ExecuteResponse execute(CLLetter request) {
      this.request = request;
      return super.executeUpdateGetIdentity(connection, false);
    }

  }

  public ExecuteResponse insertCLLetter(CLLetter request) {
    return new InsertCLLetter(logger).execute(request);
  }

  public CLLetter buildCLLetter() {
    return new CLLetter();
  }

  //</editor-fold>
  //
}
