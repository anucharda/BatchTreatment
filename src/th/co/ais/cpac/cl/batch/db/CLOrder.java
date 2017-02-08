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

/**
 *
 * @author Optimus
 */
public class CLOrder {

  public CLOrder(UtilityLogger logger) {
    this.logger = logger;
  }

  protected final UtilityLogger logger;

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

  //<editor-fold defaultstate="collapsed" desc="Insert CL_ORDER">
  public class CLOrderInfo {

    protected CLOrderInfo() {
    }
    private BigDecimal orderId;
    private String baNumber;
    private String mobileNumber;
    private BigDecimal orderActionId;
    private BigDecimal orderCriteriaId;

    private String orderType;
    private String orderReason;
    private String suspendType;

    private String networkType;
    private BigDecimal actionStatus;
    private Date actionStatusDtm;
    private String actionRemark;
    private BigDecimal negoId;
    private BigDecimal batchId;
    private String sffOrderNumber;

    private Character endRequestBoo;

    private Date created;
    private String createdBy;
    private Date lastUpd;
    private String lastUpdBy;

    public BigDecimal getOrderId() {
      return orderId;
    }

    public void setOrderId(BigDecimal orderId) {
      this.orderId = orderId;
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

    public BigDecimal getOrderActionId() {
      return orderActionId;
    }

    public void setOrderActionId(BigDecimal orderActionId) {
      this.orderActionId = orderActionId;
    }

    public BigDecimal getOrderCriteriaId() {
      return orderCriteriaId;
    }

    public void setOrderCriteriaId(BigDecimal orderCriteriaId) {
      this.orderCriteriaId = orderCriteriaId;
    }

    public String getOrderType() {
      return orderType;
    }

    public void setOrderType(String orderType) {
      this.orderType = orderType;
    }

    public String getOrderReason() {
      return orderReason;
    }

    public void setOrderReason(String orderReason) {
      this.orderReason = orderReason;
    }

    public String getSuspendType() {
      return suspendType;
    }

    public void setSuspendType(String suspendType) {
      this.suspendType = suspendType;
    }

    public String getNetworkType() {
      return networkType;
    }

    public void setNetworkType(String networkType) {
      this.networkType = networkType;
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

    public BigDecimal getNegoId() {
      return negoId;
    }

    public void setNegoId(BigDecimal negoId) {
      this.negoId = negoId;
    }

    public BigDecimal getBatchId() {
      return batchId;
    }

    public void setBatchId(BigDecimal batchId) {
      this.batchId = batchId;
    }

    public String getSffOrderNumber() {
      return sffOrderNumber;
    }

    public void setSffOrderNumber(String sffOrderNumber) {
      this.sffOrderNumber = sffOrderNumber;
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

    public Character getEndRequestBoo() {
      return endRequestBoo;
    }

    public void setEndRequestBoo(Character endRequestBoo) {
      this.endRequestBoo = endRequestBoo;
    }

  }

  protected class InsertCLOrder extends DBTemplatesInsert<ExecuteResponse, UtilityLogger, DBConnectionPools> {

    public InsertCLOrder(UtilityLogger logger) {
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
      genNumber("ORDER_ID", request.getOrderId(), null, column, value, false);
      genString("BA_NO", request.getBaNumber(), null, column, value, false);
      genString("MOBILE_NO", request.getMobileNumber(), null, column, value, false);
      genNumber("ORDER_ACTION_ID", request.getOrderActionId(), null, column, value, false);
      genNumber("ORDER_CRITERIA_ID", request.getOrderCriteriaId(), null, column, value, false);
      genString("ORDER_TYPE", request.getOrderType(), null, column, value, false);
      genString("ORDER_REASON", request.getOrderReason(), null, column, value, false);
      genString("SUSPEND_TYPE", request.getSuspendType(), null, column, value, false);
      genString("NETWORK_TYPE", request.getNetworkType(), null, column, value, false);
      genNumber("ACTION_STATUS", request.getActionStatus(), null, column, value, false);
      genDateTime("ACTION_STATUS_DTM", request.getActionStatusDtm(), null, column, value, false);
      genString("ACTION_REMARK", request.getActionRemark(), null, column, value, false);
      genNumber("NEGO_ID", request.getNegoId(), null, column, value, false);
      genNumber("BATCH_ID", request.getBatchId(), null, column, value, false);
      genString("SFF_ORDER_NO", request.getSffOrderNumber(), null, column, value, false);

      genString("END_REQUEST_BOO", "" + request.getEndRequestBoo(), null, column, value, false);

      genDateTime("CREATED", request.getCreated(), null, column, value, false);
      genString("CREATED_BY", request.getCreatedBy(), null, column, value, false);
      genDateTime("LAST_UPD", request.getLastUpd(), null, column, value, false);
      genString("LAST_UPD_BY", request.getLastUpdBy(), null, column, value, false);

      StringBuilder sql = new StringBuilder();
      sql.append("INSERT INTO dbo.CL_ORDER(").append(column).append(")");
      sql.append("VALUES(").append(value).append(")");

      return sql;
    }

    private CLOrderInfo request;

    protected ExecuteResponse execute(CLOrderInfo request) {
      this.request = request;
      return super.executeUpdateGetIdentity(connection, false);
    }

  }

  public ExecuteResponse insertCLOrder(CLOrderInfo request) {
    return new InsertCLOrder(logger).execute(request);
  }

  public CLOrderInfo buildCLOrderInfo() {
    return new CLOrderInfo();
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="Insert CL_ORDER_TREATMENT">
  public class CLOrderTreatmentInfo {

    protected CLOrderTreatmentInfo() {
    }
    private BigDecimal treatmentId;
    private BigDecimal orderId;
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

    public BigDecimal getOrderId() {
      return orderId;
    }

    public void setOrderId(BigDecimal orderId) {
      this.orderId = orderId;
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

  protected class InsertCLOrderTreatment extends DBTemplatesInsert<ExecuteResponse, UtilityLogger, DBConnectionPools> {

    public InsertCLOrderTreatment(UtilityLogger logger) {
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
      genNumber("ORDER_ID", request.getOrderId(), null, column, value, false);
      genDateTime("CREATED", request.getCreated(), null, column, value, false);
      genString("CREATED_BY", request.getCreatedBy(), null, column, value, false);
      genDateTime("LAST_UPD", request.getLastUpd(), null, column, value, false);
      genString("LAST_UPD_BY", request.getLastUpdBy(), null, column, value, false);

      StringBuilder sql = new StringBuilder();
      sql.append("INSERT INTO CL_ORDER_TREATMENT(").append(column).append(")");
      sql.append("VALUES(").append(value).append(")");

      return sql;
    }

    private CLOrderTreatmentInfo request;

    protected ExecuteResponse execute(CLOrderTreatmentInfo request) {
      this.request = request;
      return super.executeUpdate(connection, false);
    }

  }

  public ExecuteResponse insertCLOrderTreatment(CLOrderTreatmentInfo request) {
    return new InsertCLOrderTreatment(logger).execute(request);
  }

  public CLOrderTreatmentInfo buildCLOrderTreatmentInfo() {
    return new CLOrderTreatmentInfo();
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="Insert CL_BATCH">
  public class CLBatcInfo {

    protected CLBatcInfo() {
    }
    private BigDecimal batchId;
    private BigDecimal batchTypeId;
    private BigDecimal batchVersionNo;
    private Date batchStartDtm;
    private Date batchEndDtm;
    private String batchFileName;
    private String responseFileName;
    private Constants.OutboundStatus outboundStatus;
    private Date outboundStatusDtm;
    private Constants.InboundStatus inboundStatus;
    private Date inboundStatusDtm;
    private Date created;
    private String createdBy;
    private Date lastUpd;
    private String lastUpdBy;

    public BigDecimal getBatchId() {
      return batchId;
    }

    public void setBatchId(BigDecimal batchId) {
      this.batchId = batchId;
    }

    public BigDecimal getBatchTypeId() {
      return batchTypeId;
    }

    public void setBatchTypeId(BigDecimal batchTypeId) {
      this.batchTypeId = batchTypeId;
    }

    public BigDecimal getBatchVersionNo() {
      return batchVersionNo;
    }

    public void setBatchVersionNo(BigDecimal batchVersionNo) {
      this.batchVersionNo = batchVersionNo;
    }

    public Date getBatchStartDtm() {
      return batchStartDtm;
    }

    public void setBatchStartDtm(Date batchStartDtm) {
      this.batchStartDtm = batchStartDtm;
    }

    public Date getBatchEndDtm() {
      return batchEndDtm;
    }

    public void setBatchEndDtm(Date batchEndDtm) {
      this.batchEndDtm = batchEndDtm;
    }

    public String getBatchFileName() {
      return batchFileName;
    }

    public void setBatchFileName(String batchFileName) {
      this.batchFileName = batchFileName;
    }

    public String getResponseFileName() {
      return responseFileName;
    }

    public void setResponseFileName(String responseFileName) {
      this.responseFileName = responseFileName;
    }

    public Date getOutboundStatusDtm() {
      return outboundStatusDtm;
    }

    public void setOutboundStatusDtm(Date outboundStatusDtm) {
      this.outboundStatusDtm = outboundStatusDtm;
    }

    public Date getInboundStatusDtm() {
      return inboundStatusDtm;
    }

    public void setInboundStatusDtm(Date inboundStatusDtm) {
      this.inboundStatusDtm = inboundStatusDtm;
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

    public Constants.OutboundStatus getOutboundStatus() {
      return outboundStatus;
    }

    public void setOutboundStatus(Constants.OutboundStatus outboundStatus) {
      this.outboundStatus = outboundStatus;
    }

    public Constants.InboundStatus getInboundStatus() {
      return inboundStatus;
    }

    public void setInboundStatus(Constants.InboundStatus inboundStatus) {
      this.inboundStatus = inboundStatus;
    }

  }

  protected class InsertCLBatchProcess extends DBTemplatesInsert<ExecuteResponse, UtilityLogger, DBConnectionPools> {

    public InsertCLBatchProcess(UtilityLogger logger) {
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

      genNumber("BATCH_TYPE_ID", batchInfo.getBatchTypeId(), null, column, value, false);
      genNumber("BATCH_VERSION_NO", batchInfo.getBatchVersionNo(), null, column, value, false);
      genDateTime("BATCH_START_DTM", batchInfo.getBatchStartDtm(), column, value, false);
      genDateTime("BATCH_END_DTM", batchInfo.getBatchEndDtm(), column, value, false);
      genString("BATCH_FILE_NAME", batchInfo.getBatchFileName(), column, value, false);
      genString("RESPONSE_FILE_NAME", batchInfo.getResponseFileName(), column, value, false);
      genNumber("OUTBOUND_STATUS", batchInfo.getOutboundStatus().getCode(), null, column, value, false);
      genDateTime("OUTBOUND_STATUS_DTM", batchInfo.getOutboundStatusDtm(), column, value, false);
      genNumber("INBOUND_STATUS", batchInfo.getInboundStatus().getCode(), null, column, value, false);
      genDateTime("INBOUND_STATUS_DTM", batchInfo.getInboundStatusDtm(), column, value, false);
      genMethod("CREATED", "getdate()", column, value, false);
      genString("CREATED_BY", batchInfo.getCreatedBy(), column, value, false);
      genMethod("LAST_UPD", "getdate()", column, value, false);
      genString("LAST_UPD_BY", batchInfo.getLastUpdBy(), column, value, false);

      StringBuilder sql = new StringBuilder();
      sql.append("INSERT INTO dbo.CL_BATCH(").append(column).append(")");
      sql.append("VALUES(").append(value).append(")");

      return sql;
    }

    private CLBatcInfo batchInfo;

    protected ExecuteResponse execute(CLBatcInfo batchInfo) {
      this.batchInfo = batchInfo;
      return super.executeUpdateGetIdentity(connection, false);
    }

  }

  public ExecuteResponse insertCLBatch(CLBatcInfo batchInfo) {
    return new InsertCLBatchProcess(logger).execute(batchInfo);
  }

  public CLBatcInfo buildCLBatcInfo() {
    return new CLBatcInfo();
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="Insert CL_BLACKLIST_TREATMENT">
  public class CLBlacklistTreatmentInfo {

    protected CLBlacklistTreatmentInfo() {
    }
    private BigDecimal treatmentId;
    private BigDecimal blacklistId;
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

    public BigDecimal getBlacklistId() {
      return blacklistId;
    }

    public void setBlacklistId(BigDecimal blacklistId) {
      this.blacklistId = blacklistId;
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

  protected class InsertCLBlacklistTreatment extends DBTemplatesInsert<ExecuteResponse, UtilityLogger, DBConnectionPools> {

    public InsertCLBlacklistTreatment(UtilityLogger logger) {
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
      genNumber("BLACKLIST_ID", request.getBlacklistId(), null, column, value, false);
      genDateTime("CREATED", request.getCreated(), null, column, value, false);
      genString("CREATED_BY", request.getCreatedBy(), null, column, value, false);
      genDateTime("LAST_UPD", request.getLastUpd(), null, column, value, false);
      genString("LAST_UPD_BY", request.getLastUpdBy(), null, column, value, false);

      StringBuilder sql = new StringBuilder();
      sql.append("INSERT INTO CL_BLACKLIST_TREATMENT(").append(column).append(")");
      sql.append("VALUES(").append(value).append(")");

      return sql;
    }

    private CLBlacklistTreatmentInfo request;

    protected ExecuteResponse execute(CLBlacklistTreatmentInfo request) {
      this.request = request;
      return super.executeUpdate(connection, false);
    }

  }

  public ExecuteResponse insertCLBlacklistTreatment(CLBlacklistTreatmentInfo request) {
    return new InsertCLBlacklistTreatment(logger).execute(request);
  }

  public CLBlacklistTreatmentInfo buildCLBlacklistTreatmentInfo() {
    return new CLBlacklistTreatmentInfo();
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="Insert CL_BLACKLIST">
  public class CLBlacklistInfo {

    protected CLBlacklistInfo() {
    }
    private BigDecimal blacklistId;
    private String baNumber;
    private BigDecimal blacklistActionId;
    private BigDecimal blacklistCriteriaId;
    private BigDecimal blacklistOption;
    private String blacklistType;
    private String blacklistSubtype;
    private String blacklistSource;
    private String blacklistReasonCode;
    private Date blacklistRequestDate;
    private BigDecimal actionStatus;
    private Date actionStatusDtm;
    private String actionRemark;
    private BigDecimal orderCriteriaId;
    private BigDecimal negoId;
    private BigDecimal batchId;
    private String sffBlacklistId;
    private Date created;
    private String createdBy;
    private Date lastUpd;
    private String lastUpdBy;

    public BigDecimal getBlacklistId() {
      return blacklistId;
    }

    public void setBlacklistId(BigDecimal blacklistId) {
      this.blacklistId = blacklistId;
    }

    public String getBaNumber() {
      return baNumber;
    }

    public void setBaNumber(String baNumber) {
      this.baNumber = baNumber;
    }

    public BigDecimal getBlacklistActionId() {
      return blacklistActionId;
    }

    public void setBlacklistActionId(BigDecimal blacklistActionId) {
      this.blacklistActionId = blacklistActionId;
    }

    public BigDecimal getBlacklistCriteriaId() {
      return blacklistCriteriaId;
    }

    public void setBlacklistCriteriaId(BigDecimal blacklistCriteriaId) {
      this.blacklistCriteriaId = blacklistCriteriaId;
    }

    public BigDecimal getBlacklistOption() {
      return blacklistOption;
    }

    public void setBlacklistOption(BigDecimal blacklistOption) {
      this.blacklistOption = blacklistOption;
    }

    public String getBlacklistType() {
      return blacklistType;
    }

    public void setBlacklistType(String blacklistType) {
      this.blacklistType = blacklistType;
    }

    public String getBlacklistSubtype() {
      return blacklistSubtype;
    }

    public void setBlacklistSubtype(String blacklistSubtype) {
      this.blacklistSubtype = blacklistSubtype;
    }

    public String getBlacklistSource() {
      return blacklistSource;
    }

    public void setBlacklistSource(String blacklistSource) {
      this.blacklistSource = blacklistSource;
    }

    public String getBlacklistReasonCode() {
      return blacklistReasonCode;
    }

    public void setBlacklistReasonCode(String blacklistReasonCode) {
      this.blacklistReasonCode = blacklistReasonCode;
    }

    public Date getBlacklistRequestDate() {
      return blacklistRequestDate;
    }

    public void setBlacklistRequestDate(Date blacklistRequestDate) {
      this.blacklistRequestDate = blacklistRequestDate;
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

    public BigDecimal getOrderCriteriaId() {
      return orderCriteriaId;
    }

    public void setOrderCriteriaId(BigDecimal orderCriteriaId) {
      this.orderCriteriaId = orderCriteriaId;
    }

    public BigDecimal getNegoId() {
      return negoId;
    }

    public void setNegoId(BigDecimal negoId) {
      this.negoId = negoId;
    }

    public BigDecimal getBatchId() {
      return batchId;
    }

    public void setBatchId(BigDecimal batchId) {
      this.batchId = batchId;
    }

    public String getSffBlacklistId() {
      return sffBlacklistId;
    }

    public void setSffBlacklistId(String sffBlacklistId) {
      this.sffBlacklistId = sffBlacklistId;
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

  protected class InsertCLBlacklist extends DBTemplatesInsert<ExecuteResponse, UtilityLogger, DBConnectionPools> {

    public InsertCLBlacklist(UtilityLogger logger) {
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
      genNumber("BLACKLIST_ID", request.getBlacklistId(), null, column, value, false);
      genString("BA_NO", request.getBaNumber(), null, column, value, false);
      genNumber("BLACKLIST_ACTION_ID", request.getBlacklistActionId(), null, column, value, false);
      genNumber("BLACKLIST_CRITERIA_ID", request.getBlacklistCriteriaId(), null, column, value, false);
      genNumber("BLACKLIST_OPTION", request.getBlacklistOption(), null, column, value, false);
      genString("BLACKLIST_TYPE", request.getBlacklistType(), null, column, value, false);
      genString("BLACKLIST_SUBTYPE", request.getBlacklistSubtype(), null, column, value, false);
      genString("BLACKLIST_SOURCE", request.getBlacklistSource(), null, column, value, false);
      genString("BLACKLIST_REASON", request.getBlacklistReasonCode(), null, column, value, false);
      genDateTime("BLACKLIST_REQUEST_DATE", request.getBlacklistRequestDate(), null, column, value, false);
      genNumber("ACTION_STATUS", request.getActionStatus(), null, column, value, false);
      genDateTime("ACTION_STATUS_DTM", request.getActionStatusDtm(), null, column, value, false);
      genString("ACTION_REMARK", request.getActionRemark(), null, column, value, false);
      genNumber("ORDER_CRITERIA_ID", request.getOrderCriteriaId(), null, column, value, false);
      genNumber("NEGO_ID", request.getNegoId(), null, column, value, false);
      genNumber("BATCH_ID", request.getBatchId(), null, column, value, false);
      genString("SFF_BLACKLIST_ID", request.getSffBlacklistId(), null, column, value, false);
      genDateTime("CREATED", request.getCreated(), null, column, value, false);
      genString("CREATED_BY", request.getCreatedBy(), null, column, value, false);
      genDateTime("LAST_UPD", request.getLastUpd(), null, column, value, false);
      genString("LAST_UPD_BY", request.getLastUpdBy(), null, column, value, false);

      StringBuilder sql = new StringBuilder();
      sql.append("INSERT INTO dbo.CL_BLACKLIST(").append(column).append(")");
      sql.append("VALUES(").append(value).append(")");

      return sql;
    }

    private CLBlacklistInfo request;

    protected ExecuteResponse execute(CLBlacklistInfo request) {
      this.request = request;
      return super.executeUpdateGetIdentity(connection, false);
    }

  }

  public ExecuteResponse insertCLOrder(CLBlacklistInfo request) {
    return new InsertCLBlacklist(logger).execute(request);
  }

  public CLBlacklistInfo buildCLBlacklistInfo() {
    return new CLBlacklistInfo();
  }

  //</editor-fold>
  //
}
