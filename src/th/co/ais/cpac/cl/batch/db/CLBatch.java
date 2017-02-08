/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.db;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import th.co.ais.cpac.cl.batch.Constants;
import th.co.ais.cpac.cl.batch.cnf.CNFDatabase;
import th.co.ais.cpac.cl.common.Context;
import th.co.ais.cpac.cl.common.UtilityLogger;
import th.co.ais.cpac.cl.template.database.DBConnectionPools;
import th.co.ais.cpac.cl.template.database.DBTemplateCallableStatement;
import th.co.ais.cpac.cl.template.database.DBTemplatesExecuteQuery;
import th.co.ais.cpac.cl.template.database.DBTemplatesInsert;
import th.co.ais.cpac.cl.template.database.DBTemplatesResponse;

/**
 *
 * @author Optimus
 */
public class CLBatch {

  protected final UtilityLogger logger;

  public CLBatch(UtilityLogger logger) {
    this.logger = logger;
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

  //<editor-fold defaultstate="collapsed" desc="Insert CLBatch ">
  public class InsertCLBatchResponse extends DBTemplatesResponse<BigDecimal> {

    @Override
    protected BigDecimal createResponse() {
      return BigDecimal.ZERO;
    }

    @Override
    public void setResponse(BigDecimal boo) {
      response = new BigDecimal(boo.toPlainString());
    }
  }

  protected class InsertCLBatch extends DBTemplateCallableStatement<InsertCLBatchResponse, UtilityLogger, DBConnectionPools> {

    private BigDecimal batchTypeId;

    public InsertCLBatch(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected void setParameter() throws SQLException {
      statement.setQueryTimeout(1800);
      statement.setBigDecimal(1, batchTypeId);
      statement.registerOutParameter(2, java.sql.Types.NUMERIC);
      statement.registerOutParameter(3, java.sql.Types.INTEGER);
    }

    @Override
    protected void setReturnValue() throws SQLException {
      BigDecimal identiry = (BigDecimal) statement.getObject(2);
      logger.debug("identiry = " + identiry.toPlainString());

      Integer code = (Integer) statement.getObject(3);
      logger.debug("code = " + code);

      response.setResponse(identiry);

      countRecord();
    }

    @Override
    protected InsertCLBatchResponse createResponse() {
      return new InsertCLBatchResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      return new StringBuilder("{call INSERT_CL_BATCH (?,? ,?)}");
    }

    protected InsertCLBatchResponse execute(BigDecimal batchTypeId) {
      this.batchTypeId = batchTypeId;
      return execute(Constants.getDBConnectionPools(logger), true);
    }

  }

  public InsertCLBatchResponse insertCLBatch(BigDecimal batchTypeId) {
    return new InsertCLBatch(logger).execute(batchTypeId);
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="Get CL_BATCH_TYPE Info">
  public class CLBatchTypeInfo {

    protected CLBatchTypeInfo() {
    }

    private String batchTypeName;
    private String batchTypeDesc;
    private BigDecimal batchGroup;
    private BigDecimal batchSystemId;
    private String batchRuntime;
    private Character batchService;
    private Character responseBoo;

    public String getBatchTypeName() {
      return batchTypeName;
    }

    public void setBatchTypeName(String batchTypeName) {
      this.batchTypeName = batchTypeName;
    }

    public String getBatchTypeDesc() {
      return batchTypeDesc;
    }

    public void setBatchTypeDesc(String batchTypeDesc) {
      this.batchTypeDesc = batchTypeDesc;
    }

    public BigDecimal getBatchGroup() {
      return batchGroup;
    }

    public void setBatchGroup(BigDecimal batchGroup) {
      this.batchGroup = batchGroup;
    }

    public BigDecimal getBatchSystemId() {
      return batchSystemId;
    }

    public void setBatchSystemId(BigDecimal batchSystemId) {
      this.batchSystemId = batchSystemId;
    }

    public String getBatchRuntime() {
      return batchRuntime;
    }

    public void setBatchRuntime(String batchRuntime) {
      this.batchRuntime = batchRuntime;
    }

    public Character getBatchService() {
      return batchService;
    }

    public void setBatchService(Character batchService) {
      this.batchService = batchService;
    }

    public Character getResponseBoo() {
      return responseBoo;
    }

    public void setResponseBoo(Character responseBoo) {
      this.responseBoo = responseBoo;
    }

  }

  public class CLBatchTypeResponse extends DBTemplatesResponse<CLBatchTypeInfo> {

    @Override
    protected CLBatchTypeInfo createResponse() {
      return new CLBatchTypeInfo();
    }
  }

  protected class GetCLBatchTypeInfo extends DBTemplatesExecuteQuery<CLBatchTypeResponse, UtilityLogger, DBConnectionPools> {

    public GetCLBatchTypeInfo(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected CLBatchTypeResponse createResponse() {
      return new CLBatchTypeResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append(" SELECT BATCH_TYPE_ID, BATCH_TYPE_NAME, BATCH_TYPE_DESC, BATCH_GROUP, BATCH_SYSTEM_ID, BATCH_RUNTIME, BATCH_SERVICE, RESPONSE_BOO ");
      sql.append(" FROM dbo.CL_BATCH_TYPE ");
      sql.append(" WHERE BATCH_TYPE_ID = ").append(batchTypeId.toPlainString()).append(" and RECORD_STATUS = 1 ");
      return sql;
    }

    private BigDecimal batchTypeId;

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      CLBatchTypeInfo temp = response.getResponse();
      temp.setBatchTypeName(resultSet.getString("BATCH_TYPE_NAME"));
      temp.setBatchTypeDesc(resultSet.getString("BATCH_TYPE_DESC"));
      temp.setBatchGroup(resultSet.getBigDecimal("BATCH_GROUP"));
      temp.setBatchSystemId(resultSet.getBigDecimal("BATCH_SYSTEM_ID"));
      temp.setBatchRuntime(resultSet.getString("BATCH_RUNTIME"));
      temp.setBatchService(toCharcter(resultSet.getString("BATCH_SERVICE"), 'N'));
      temp.setResponseBoo(toCharcter(resultSet.getString("RESPONSE_BOO"), 'N'));
    }

    protected CLBatchTypeResponse execute(BigDecimal batchTypeId) {
      this.batchTypeId = batchTypeId;
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public CLBatchTypeResponse getCLBatchTypeInfo(BigDecimal batchTypeId) {
    return new GetCLBatchTypeInfo(logger).execute(batchTypeId);
  }

  //</editor-fold>
  //  
  //<editor-fold defaultstate="collapsed" desc="Get CL_BATCH_VERSION Info">
  public class CLBatchVersionInfo {

    protected CLBatchVersionInfo() {
    }
    private BigDecimal batchTypeId;
    private BigDecimal batchVersionNo;
    private String batchNameFormat;
    private String batchFileType;
    private String batchEncoding;
    private String batchDelimiter;
    private BigDecimal limitPerFile;
    private BigDecimal limitPerDay;

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

    public String getBatchNameFormat() {
      return batchNameFormat;
    }

    public void setBatchNameFormat(String batchNameFormat) {
      this.batchNameFormat = batchNameFormat;
    }

    public String getBatchFileType() {
      return batchFileType;
    }

    public void setBatchFileType(String batchFileType) {
      this.batchFileType = batchFileType;
    }

    public String getBatchEncoding() {
      return batchEncoding;
    }

    public void setBatchEncoding(String batchEncoding) {
      this.batchEncoding = batchEncoding;
    }

    public String getBatchDelimiter() {
      return batchDelimiter;
    }

    public void setBatchDelimiter(String batchDelimiter) {
      this.batchDelimiter = batchDelimiter;
    }

    public BigDecimal getLimitPerFile() {
      return limitPerFile;
    }

    public void setLimitPerFile(BigDecimal limitPerFile) {
      this.limitPerFile = limitPerFile;
    }

    public BigDecimal getLimitPerDay() {
      return limitPerDay;
    }

    public void setLimitPerDay(BigDecimal limitPerDay) {
      this.limitPerDay = limitPerDay;
    }

  }

  public class GetCLBatchVersionResponse extends DBTemplatesResponse<CLBatchVersionInfo> {

    @Override
    protected CLBatchVersionInfo createResponse() {
      return new CLBatchVersionInfo();
    }
  }

  protected class GetCLBatchVersion extends DBTemplatesExecuteQuery<GetCLBatchVersionResponse, UtilityLogger, DBConnectionPools> {

    public GetCLBatchVersion(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected GetCLBatchVersionResponse createResponse() {
      return new GetCLBatchVersionResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append(Constants.END_LINE);
      sql.append(" SELECT BATCH_TYPE_ID, BATCH_VERSION_NO, BATCH_NAME_FORMAT, BATCH_FILE_TYPE, BATCH_ENCODING, BATCH_DELIMITER, LIMIT_PER_FILE,LIMIT_PER_DAY ").append(Constants.END_LINE);
      sql.append(" FROM dbo.CL_BATCH_VERSION").append(Constants.END_LINE);
      sql.append(" WHERE BATCH_TYPE_ID = ").append(batchTypeId.toPlainString()).append(" and getdate() between EFFECT_START_DATE and isnull(EFFECT_END_DATE,getdate())").append(Constants.END_LINE);;
      return sql;
    }

    private BigDecimal batchTypeId;

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      CLBatchVersionInfo temp = response.getResponse();
      temp.setBatchTypeId(resultSet.getBigDecimal("BATCH_TYPE_ID"));
      temp.setBatchVersionNo(resultSet.getBigDecimal("BATCH_VERSION_NO"));
      temp.setBatchNameFormat(resultSet.getString("BATCH_NAME_FORMAT"));
      temp.setBatchFileType(resultSet.getString("BATCH_FILE_TYPE"));
      temp.setBatchEncoding(resultSet.getString("BATCH_ENCODING"));
      temp.setBatchDelimiter(resultSet.getString("BATCH_DELIMITER"));
      temp.setLimitPerFile(resultSet.getBigDecimal("LIMIT_PER_FILE"));
      temp.setLimitPerDay(resultSet.getBigDecimal("LIMIT_PER_DAY"));
    }

    protected GetCLBatchVersionResponse execute(BigDecimal batchTypeId) {
      this.batchTypeId = batchTypeId;
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public GetCLBatchVersionResponse getCLBatchVersion(BigDecimal batchTypeId) {
    return new GetCLBatchVersion(logger).execute(batchTypeId);
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="Get CL_BATCH_PATH Info">
  public class CLBatchPathInfo {

    protected CLBatchPathInfo() {
    }
    private BigDecimal batchTypeId;
    private Constants.Environment environment;
    private String pathOutbound;
    private String pathInbound;

    public BigDecimal getBatchTypeId() {
      return batchTypeId;
    }

    public void setBatchTypeId(BigDecimal batchTypeId) {
      this.batchTypeId = batchTypeId;
    }

    public Constants.Environment getEnvironment() {
      return environment;
    }

    public void setEnvironment(Constants.Environment environment) {
      this.environment = environment;
    }

    public String getPathOutbound() {
      return pathOutbound;
    }

    public void setPathOutbound(String pathOutbound) {
      this.pathOutbound = pathOutbound;
    }

    public String getPathInbound() {
      return pathInbound;
    }

    public void setPathInbound(String pathInbound) {
      this.pathInbound = pathInbound;
    }

  }

  public class CLBatchPathResponse extends DBTemplatesResponse< CLBatchPathInfo> {

    @Override
    protected CLBatchPathInfo createResponse() {
      return new CLBatchPathInfo();
    }

  }

  protected class GetCLBatchPath extends DBTemplatesExecuteQuery<CLBatchPathResponse, UtilityLogger, DBConnectionPools> {

    public GetCLBatchPath(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected CLBatchPathResponse createResponse() {
      return new CLBatchPathResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append(" SELECT BATCH_TYPE_ID, ENVIRONMENT, PATH_OUTBOUND, PATH_INBOUND, RECORD_STATUS ");
      sql.append(" FROM dbo.CL_BATCH_PATH ");
      sql.append(" WHERE BATCH_TYPE_ID = ").append(batchTypeId.toPlainString()).append(" and RECORD_STATUS = 1 ");
      return sql;
    }

    private BigDecimal batchTypeId;

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      CLBatchPathInfo temp = response.getResponse();
      temp.setBatchTypeId(resultSet.getBigDecimal("BATCH_TYPE_ID"));
      temp.setEnvironment(Constants.mapEnvironment(resultSet.getBigDecimal("ENVIRONMENT").intValue()));
      temp.setPathOutbound(resultSet.getString("PATH_OUTBOUND"));
      temp.setPathInbound(resultSet.getString("PATH_INBOUND"));
    }

    protected CLBatchPathResponse execute(BigDecimal batchTypeId) {
      this.batchTypeId = batchTypeId;
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public CLBatchPathResponse getCLBatchPath(BigDecimal batchTypeId) {
    return new GetCLBatchPath(logger).execute(batchTypeId);
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
      sql.append("INSERT INTO CL_BATCH(").append(column).append(")");
      sql.append("VALUES(").append(value).append(")");

      return sql;
    }

    private CLBatcInfo batchInfo;

    protected ExecuteResponse execute(CLBatcInfo batchInfo) {
      this.batchInfo = batchInfo;
      return super.executeUpdateGetIdentity(Constants.getDBConnectionPools(logger), true);
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

  public static void main(String[] args) {
    long k = System.currentTimeMillis();
    CNFDatabase cnf = new CNFDatabase("D:\\Work\\Ais\\Apps\\Batch\\configuration\\database.properties");

    Context ctx = new Context();
    ctx.initailLogger("LoggerMasterBatchInfo", "XXXX|YYYYY");

    new DBConnectionPools<>(cnf, ctx.getLogger()).buildeDataSource();

    CLBatch tbl = new CLBatch(ctx.getLogger());

    CLBatch.CLBatcInfo dd = new CLBatch(ctx.getLogger()).new CLBatcInfo();

    dd.setBatchTypeId(new BigDecimal(System.currentTimeMillis()));
    dd.setBatchVersionNo(BigDecimal.ONE);
    dd.setOutboundStatus(Constants.OutboundStatus.NoOutboundResponse);
    dd.setOutboundStatusDtm(new Date());
    dd.setInboundStatus(Constants.InboundStatus.NoInboundResponse);
    dd.setOutboundStatusDtm(new Date());
    dd.setCreated(new Date());
    dd.setCreatedBy("system");
    dd.setLastUpd(new Date());
    dd.setLastUpdBy("system");

    ExecuteResponse re = tbl.insertCLBatch(dd);
    System.out.println(re.info());
    System.out.println(re.getIdentity());

    ctx.getLogger().debug("ress = " + re.info());
  }
}
