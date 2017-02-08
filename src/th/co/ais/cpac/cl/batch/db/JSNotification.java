/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.db;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import th.co.ais.cpac.cl.batch.Constants;
import th.co.ais.cpac.cl.common.UtilityLogger;
import th.co.ais.cpac.cl.template.database.DBConnectionPools;
import th.co.ais.cpac.cl.template.database.DBTemplateCallableStatement;
import th.co.ais.cpac.cl.template.database.DBTemplatesResponse;

/**
 *
 * @author Optimus
 */
public class JSNotification {

  protected final UtilityLogger logger;

  public JSNotification(UtilityLogger logger) {
    this.logger = logger;
  }

  //
  //<editor-fold defaultstate="collapsed" desc="Notify SMS">
  public class SMSNotify {

    protected SMSNotify() {
    }

    private BigDecimal notificationId;
    private String message;

    public BigDecimal getNotificationId() {
      return notificationId;
    }

    public void setNotificationId(BigDecimal notificationId) {
      this.notificationId = notificationId;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

  }

  public class SMSNotifyResponse extends DBTemplatesResponse<SMSNotify> {

    @Override
    protected SMSNotify createResponse() {
      return new SMSNotify();
    }

  }

  protected class NotifySMS extends DBTemplateCallableStatement<SMSNotifyResponse, UtilityLogger, DBConnectionPools> {

    private Date sendDateTime;
    private String notificationType;
    private String requestBy;
    private char requestSystem;
    private String msisdn;
    private String requestId;
    private String lotId;
    private String smsMessage;
    private String smsSender;

    public NotifySMS(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected void setParameter() throws SQLException {
      statement.setQueryTimeout(1800);

      statement.setDate(1, toSqlDate(sendDateTime, new java.sql.Date(System.currentTimeMillis())));
      statement.setString(2, notificationType);
      statement.setString(3, requestBy);
      statement.setString(4, "" + requestSystem);
      statement.setString(5, msisdn);
      statement.setString(6, requestId);
      statement.setString(7, lotId);
      statement.setString(8, smsMessage);
      statement.setString(9, smsSender);

      statement.registerOutParameter(10, java.sql.Types.NUMERIC);
      statement.registerOutParameter(11, java.sql.Types.VARCHAR);
    }

    @Override
    protected void setReturnValue() throws SQLException {
      BigDecimal notificationId = (BigDecimal) statement.getObject(10);
      String notificationMsg = statement.getString(11);

      response.getResponse().setNotificationId(new BigDecimal(notificationId.toBigInteger()));
      response.getResponse().setMessage(notificationMsg);

      countRecord();
    }

    @Override
    protected SMSNotifyResponse createResponse() {
      return new SMSNotifyResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      return new StringBuilder("{call JSDB..NT_S_CREATE_SMS_REQUEST (?,?,?,?,?,?,?,?,?,?,?)}");
    }

    protected SMSNotifyResponse execute(Date sendDateTime, String notificationType, String requestBy, char requestSystem, String msisdn, String requestId, String lotId, String smsMessage, String smsSender) {
      this.sendDateTime = sendDateTime;
      this.notificationType = notificationType;
      this.requestBy = requestBy;
      this.requestSystem = requestSystem;
      this.msisdn = msisdn;
      this.requestId = requestId;
      this.lotId = lotId;
      this.smsMessage = smsMessage;
      this.smsSender = smsSender;

      return execute(Constants.getDBConnectionPools(logger), true);
    }

  }

  public SMSNotifyResponse sendSms(Date sendDateTime, String notificationType, String requestBy, String msisdn, String requestId, String lotId, String smsMessage, String smsSender) {
    return new NotifySMS(logger).execute(sendDateTime, notificationType, requestBy, 'C', msisdn, requestId, lotId, smsMessage, smsSender);
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="Create Lot ID">
  public class LotIdNotify {

    protected LotIdNotify() {
    }

    private String lotTypeId;
    private String message;

    public String getLotTypeId() {
      return lotTypeId;
    }

    public void setLotTypeId(String lotTypeId) {
      this.lotTypeId = lotTypeId;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

  }

  public class LotIdNotifyResponse extends DBTemplatesResponse<LotIdNotify> {

    @Override
    protected LotIdNotify createResponse() {
      return new LotIdNotify();
    }

  }

  protected class CreateLotIdNotify extends DBTemplateCallableStatement<LotIdNotifyResponse, UtilityLogger, DBConnectionPools> {

    private String lotTypeCode;

    public CreateLotIdNotify(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected void setParameter() throws SQLException {
      statement.setQueryTimeout(1800);
      statement.setString(1, lotTypeCode);
      statement.registerOutParameter(2, java.sql.Types.VARCHAR);
      statement.registerOutParameter(3, java.sql.Types.VARCHAR);
    }

    @Override
    protected void setReturnValue() throws SQLException {
      String notificationId = statement.getString(2);
      String notificationMsg = statement.getString(3);

      response.getResponse().setLotTypeId(notificationId);
      response.getResponse().setMessage(notificationMsg);

      countRecord();
    }

    @Override
    protected LotIdNotifyResponse createResponse() {
      return new LotIdNotifyResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      return new StringBuilder("{call JSDB..NT_S_GENERATE_LOT_ID (?,?,? )}");
    }

    protected LotIdNotifyResponse execute(String lotTypeCode) {
      this.lotTypeCode = lotTypeCode;
      return execute(Constants.getDBConnectionPools(logger), true);
    }

  }

  public LotIdNotifyResponse getLotId(String lotTypeCode) {
    return new CreateLotIdNotify(logger).execute(lotTypeCode);
  }

  //</editor-fold>
  //
}
