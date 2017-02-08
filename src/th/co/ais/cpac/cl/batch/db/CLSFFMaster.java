/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.db;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import th.co.ais.cpac.cl.batch.Constants;
import th.co.ais.cpac.cl.common.UtilityLogger;
import th.co.ais.cpac.cl.template.database.DBConnectionPools;
import th.co.ais.cpac.cl.template.database.DBTemplatesExecuteQuery;
import th.co.ais.cpac.cl.template.database.DBTemplatesResponse;

/**
 *
 * @author Optimus
 */
public class CLSFFMaster {

  protected final UtilityLogger logger;

  public CLSFFMaster(UtilityLogger logger) {
    this.logger = logger;
  }

  //<editor-fold defaultstate="collapsed" desc="Get CL_SFF_ORDER_TYPE">
  public class SFFOrderTypeInfo {

    protected SFFOrderTypeInfo() {
    }
    private BigDecimal orderTypeId;
    private String orderType;
    private String orderTypeDesc;
    private BigDecimal orderTypeGroup;
    private Character letterBoo;
    private String sffCfgLovId;

    public BigDecimal getOrderTypeId() {
      return orderTypeId;
    }

    public void setOrderTypeId(BigDecimal orderTypeId) {
      this.orderTypeId = orderTypeId;
    }

    public String getOrderType() {
      return orderType;
    }

    public void setOrderType(String orderType) {
      this.orderType = orderType;
    }

    public String getOrderTypeDesc() {
      return orderTypeDesc;
    }

    public void setOrderTypeDesc(String orderTypeDesc) {
      this.orderTypeDesc = orderTypeDesc;
    }

    public BigDecimal getOrderTypeGroup() {
      return orderTypeGroup;
    }

    public void setOrderTypeGroup(BigDecimal orderTypeGroup) {
      this.orderTypeGroup = orderTypeGroup;
    }

    public Character getLetterBoo() {
      return letterBoo;
    }

    public void setLetterBoo(Character letterBoo) {
      this.letterBoo = letterBoo;
    }

    public String getSffCfgLovId() {
      return sffCfgLovId;
    }

    public void setSffCfgLovId(String sffCfgLovId) {
      this.sffCfgLovId = sffCfgLovId;
    }

  }

  public class SFFOrderTypeResponse extends DBTemplatesResponse<HashMap<String, SFFOrderTypeInfo>> {

    @Override
    protected HashMap<String, SFFOrderTypeInfo> createResponse() {
      return new HashMap<>();
    }

  }

  protected class GetSFFOrderType extends DBTemplatesExecuteQuery<SFFOrderTypeResponse, UtilityLogger, DBConnectionPools> {

    public GetSFFOrderType(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected SFFOrderTypeResponse createResponse() {
      return new SFFOrderTypeResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append(" SELECT ORDER_TYPE_ID, ORDER_TYPE, ORDER_TYPE_DESC, ORDER_TYPE_GROUP, LETTER_BOO, SFF_CFG_LOV_ID ").append(Constants.END_LINE);
      sql.append(" FROM dbo.CL_SFF_ORDER_TYPE ").append(Constants.END_LINE);
      sql.append(" WHERE RECORD_STATUS = 1 ").append(Constants.END_LINE);
      return sql;
    }

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      SFFOrderTypeInfo temp = new SFFOrderTypeInfo();
      temp.setOrderTypeId(resultSet.getBigDecimal("ORDER_TYPE_ID"));
      temp.setOrderType(resultSet.getString("ORDER_TYPE"));
      temp.setOrderTypeDesc(resultSet.getString("ORDER_TYPE_DESC"));
      temp.setOrderTypeGroup(resultSet.getBigDecimal("ORDER_TYPE_GROUP"));
      temp.setLetterBoo(toCharcter(resultSet.getString("LETTER_BOO"), 'N'));
      temp.setSffCfgLovId(resultSet.getString("SFF_CFG_LOV_ID"));

      response.getResponse().put(temp.getOrderTypeId().toPlainString(), temp);
    }

    protected SFFOrderTypeResponse execute() {
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public SFFOrderTypeResponse querySFFOrderTypeActive() {
    return new GetSFFOrderType(logger).execute();
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="Get CL_SFF_ORDER_REASON">
  public class SFFOrderResonInfo {

    protected SFFOrderResonInfo() {
    }
    private BigDecimal orderReasonId;
    private String orderReasonCode;
    private String orderReasonDesc;
    private String sffCfgLovId;

    public BigDecimal getOrderReasonId() {
      return orderReasonId;
    }

    public void setOrderReasonId(BigDecimal orderReasonId) {
      this.orderReasonId = orderReasonId;
    }

    public String getOrderReasonCode() {
      return orderReasonCode;
    }

    public void setOrderReasonCode(String orderReasonCode) {
      this.orderReasonCode = orderReasonCode;
    }

    public String getOrderReasonDesc() {
      return orderReasonDesc;
    }

    public void setOrderReasonDesc(String orderReasonDesc) {
      this.orderReasonDesc = orderReasonDesc;
    }

    public String getSffCfgLovId() {
      return sffCfgLovId;
    }

    public void setSffCfgLovId(String sffCfgLovId) {
      this.sffCfgLovId = sffCfgLovId;
    }

  }

  public class SFFOrderResonResponse extends DBTemplatesResponse<HashMap<String, SFFOrderResonInfo>> {

    @Override
    protected HashMap<String, SFFOrderResonInfo> createResponse() {
      return new HashMap<>();
    }

  }

  protected class GetSFFOrderReson extends DBTemplatesExecuteQuery<SFFOrderResonResponse, UtilityLogger, DBConnectionPools> {

    public GetSFFOrderReson(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected SFFOrderResonResponse createResponse() {
      return new SFFOrderResonResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append(" SELECT ORDER_REASON_ID, ORDER_REASON_CODE, ORDER_REASON_DESC, SFF_CFG_LOV_ID ").append(Constants.END_LINE);
      sql.append(" FROM dbo.CL_SFF_ORDER_REASON ").append(Constants.END_LINE);
      sql.append(" WHERE RECORD_STATUS = 1 ").append(Constants.END_LINE);
      return sql;
    }

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      SFFOrderResonInfo temp = new SFFOrderResonInfo();
      temp.setOrderReasonId(resultSet.getBigDecimal("ORDER_REASON_ID"));
      temp.setOrderReasonCode(resultSet.getString("ORDER_REASON_CODE"));
      temp.setOrderReasonDesc(resultSet.getString("ORDER_REASON_DESC"));
      temp.setSffCfgLovId(resultSet.getString("SFF_CFG_LOV_ID"));
      response.getResponse().put(temp.getOrderReasonId().toPlainString(), temp);
    }

    protected SFFOrderResonResponse execute() {
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public SFFOrderResonResponse querySFFOrderResonActive() {
    return new GetSFFOrderReson(logger).execute();
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="Get CL_SFF_SUSPEND_TYPE">
  public class SFFSuspendTypeInfo {

    protected SFFSuspendTypeInfo() {
    }
    private BigDecimal suspendTypeId;
    private String suspendType;
    private String suspendTypeDesc;
    private String suspendTypeAbrv;
    private String sffCfgLovId;

    public BigDecimal getSuspendTypeId() {
      return suspendTypeId;
    }

    public void setSuspendTypeId(BigDecimal suspendTypeId) {
      this.suspendTypeId = suspendTypeId;
    }

    public String getSuspendType() {
      return suspendType;
    }

    public void setSuspendType(String suspendType) {
      this.suspendType = suspendType;
    }

    public String getSuspendTypeDesc() {
      return suspendTypeDesc;
    }

    public void setSuspendTypeDesc(String suspendTypeDesc) {
      this.suspendTypeDesc = suspendTypeDesc;
    }

    public String getSuspendTypeAbrv() {
      return suspendTypeAbrv;
    }

    public void setSuspendTypeAbrv(String suspendTypeAbrv) {
      this.suspendTypeAbrv = suspendTypeAbrv;
    }

    public String getSffCfgLovId() {
      return sffCfgLovId;
    }

    public void setSffCfgLovId(String sffCfgLovId) {
      this.sffCfgLovId = sffCfgLovId;
    }

  }

  public class SFFSuspendTypeResponse extends DBTemplatesResponse<HashMap<String, SFFSuspendTypeInfo>> {

    @Override
    protected HashMap<String, SFFSuspendTypeInfo> createResponse() {
      return new HashMap<>();
    }

  }

  protected class GetSFFSuspendType extends DBTemplatesExecuteQuery<SFFSuspendTypeResponse, UtilityLogger, DBConnectionPools> {

    public GetSFFSuspendType(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected SFFSuspendTypeResponse createResponse() {
      return new SFFSuspendTypeResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append(" SELECT SUSPEND_TYPE_ID, SUSPEND_TYPE, SUSPEND_TYPE_DESC, SUSPEND_TYPE_ABRV, SFF_CFG_LOV_ID ").append(Constants.END_LINE);
      sql.append(" FROM dbo.CL_SFF_SUSPEND_TYPE ").append(Constants.END_LINE);
      sql.append(" WHERE RECORD_STATUS = 1 ").append(Constants.END_LINE);
      return sql;
    }

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      SFFSuspendTypeInfo temp = new SFFSuspendTypeInfo();
      temp.setSuspendTypeId(resultSet.getBigDecimal("SUSPEND_TYPE_ID"));
      temp.setSuspendType(resultSet.getString("SUSPEND_TYPE"));
      temp.setSuspendTypeDesc(resultSet.getString("SUSPEND_TYPE_DESC"));
      temp.setSuspendTypeAbrv(resultSet.getString("SUSPEND_TYPE_ABRV"));
      temp.setSffCfgLovId(resultSet.getString("SFF_CFG_LOV_ID"));
      response.getResponse().put(temp.getSuspendTypeId().toPlainString(), temp);
    }

    protected SFFSuspendTypeResponse execute() {

      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public SFFSuspendTypeResponse querySFFSuspendTypeActive() {
    return new GetSFFSuspendType(logger).execute();
  }
  //</editor-fold>
  //

  //<editor-fold defaultstate="collapsed" desc="Get CL_SFF_BLACKLIST_TYPE">
  public class SFFBlacklistTypeInfo {

    protected SFFBlacklistTypeInfo() {
    }
    private String blacklistType;
    private String blacklistTypeDesc;
    private String sffCfgLovId;

    public String getBlacklistType() {
      return blacklistType;
    }

    public void setBlacklistType(String blacklistType) {
      this.blacklistType = blacklistType;
    }

    public String getBlacklistTypeDesc() {
      return blacklistTypeDesc;
    }

    public void setBlacklistTypeDesc(String blacklistTypeDesc) {
      this.blacklistTypeDesc = blacklistTypeDesc;
    }

    public String getSffCfgLovId() {
      return sffCfgLovId;
    }

    public void setSffCfgLovId(String sffCfgLovId) {
      this.sffCfgLovId = sffCfgLovId;
    }

  }

  public class SFFBlacklistTypeResponse extends DBTemplatesResponse<HashMap<String, SFFBlacklistTypeInfo>> {

    @Override
    protected HashMap<String, SFFBlacklistTypeInfo> createResponse() {
      return new HashMap<>();
    }

  }

  protected class GetSFFBlacklistType extends DBTemplatesExecuteQuery<SFFBlacklistTypeResponse, UtilityLogger, DBConnectionPools> {

    public GetSFFBlacklistType(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected SFFBlacklistTypeResponse createResponse() {
      return new SFFBlacklistTypeResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append(" SELECT BLACKLIST_TYPE, BLACKLIST_TYPE_DESC, SFF_CFG_LOV_ID ").append(Constants.END_LINE);
      sql.append(" FROM dbo.CL_SFF_BLACKLIST_TYPE ").append(Constants.END_LINE);
      sql.append(" WHERE RECORD_STATUS = 1  ").append(Constants.END_LINE);
      return sql;
    }

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      SFFBlacklistTypeInfo temp = new SFFBlacklistTypeInfo();
      temp.setBlacklistType(resultSet.getString("BLACKLIST_TYPE"));
      temp.setBlacklistTypeDesc(resultSet.getString("BLACKLIST_TYPE_DESC"));
      temp.setSffCfgLovId(resultSet.getString("SFF_CFG_LOV_ID"));
      response.getResponse().put(temp.getBlacklistType(), temp);
    }

    protected SFFBlacklistTypeResponse execute() {
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public SFFBlacklistTypeResponse querySFFBlacklistTypeActive() {
    return new GetSFFBlacklistType(logger).execute();
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="Get CL_SFF_BLACKLIST_SUBTYPE">
  public class SFFBlacklistSubtypeInfo {

    protected SFFBlacklistSubtypeInfo() {
    }
    private String blacklistSubtype;
    private String blacklistSubtypeDesc;
    private String blacklistCode;
    private String blacklistType;
    private String sffCfgLovId;

    public String getBlacklistSubtype() {
      return blacklistSubtype;
    }

    public void setBlacklistSubtype(String blacklistSubtype) {
      this.blacklistSubtype = blacklistSubtype;
    }

    public String getBlacklistSubtypeDesc() {
      return blacklistSubtypeDesc;
    }

    public void setBlacklistSubtypeDesc(String blacklistSubtypeDesc) {
      this.blacklistSubtypeDesc = blacklistSubtypeDesc;
    }

    public String getBlacklistCode() {
      return blacklistCode;
    }

    public void setBlacklistCode(String blacklistCode) {
      this.blacklistCode = blacklistCode;
    }

    public String getBlacklistType() {
      return blacklistType;
    }

    public void setBlacklistType(String blacklistType) {
      this.blacklistType = blacklistType;
    }

    public String getSffCfgLovId() {
      return sffCfgLovId;
    }

    public void setSffCfgLovId(String sffCfgLovId) {
      this.sffCfgLovId = sffCfgLovId;
    }

  }

  public class SFFBlacklistSubtypeResponse extends DBTemplatesResponse<HashMap<String, SFFBlacklistSubtypeInfo>> {

    @Override
    protected HashMap<String, SFFBlacklistSubtypeInfo> createResponse() {
      return new HashMap<>();
    }

  }

  protected class GetSFFBlacklistSubType extends DBTemplatesExecuteQuery<SFFBlacklistSubtypeResponse, UtilityLogger, DBConnectionPools> {

    public GetSFFBlacklistSubType(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected SFFBlacklistSubtypeResponse createResponse() {
      return new SFFBlacklistSubtypeResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append(" SELECT BLACKLIST_SUBTYPE, BLACKLIST_SUBTYPE_DESC, BLACKLIST_CODE, BLACKLIST_TYPE, SFF_CFG_LOV_ID ").append(Constants.END_LINE);
      sql.append(" FROM dbo.CL_SFF_BLACKLIST_SUBTYPE ").append(Constants.END_LINE);
      sql.append(" WHERE RECORD_STATUS = 1  ").append(Constants.END_LINE);

      return sql;
    }

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      SFFBlacklistSubtypeInfo temp = new SFFBlacklistSubtypeInfo();
      temp.setBlacklistSubtype(resultSet.getString("BLACKLIST_SUBTYPE"));
      temp.setBlacklistSubtypeDesc(resultSet.getString("BLACKLIST_SUBTYPE_DESC"));
      temp.setBlacklistCode(resultSet.getString("BLACKLIST_CODE"));
      temp.setBlacklistType(resultSet.getString("BLACKLIST_TYPE"));
      temp.setSffCfgLovId(resultSet.getString("SFF_CFG_LOV_ID"));
      response.getResponse().put(temp.getBlacklistSubtype(), temp);
    }

    protected SFFBlacklistSubtypeResponse execute() {

      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public SFFBlacklistSubtypeResponse querySFFBlacklistSubTypeActive() {
    return new GetSFFBlacklistSubType(logger).execute();
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="Get CL_SFF_BLACKLIST_REASON">
  public class SFFBlacklistReasonInfo {

    protected SFFBlacklistReasonInfo() {
    }
    private BigDecimal blacklistReasonType;
    private String blacklistReasonCode;
    private String blacklistReasonDesc;
    private String sffCfgLovId;

    public BigDecimal getBlacklistReasonType() {
      return blacklistReasonType;
    }

    public void setBlacklistReasonType(BigDecimal blacklistReasonType) {
      this.blacklistReasonType = blacklistReasonType;
    }

    public String getBlacklistReasonCode() {
      return blacklistReasonCode;
    }

    public void setBlacklistReasonCode(String blacklistReasonCode) {
      this.blacklistReasonCode = blacklistReasonCode;
    }

    public String getBlacklistReasonDesc() {
      return blacklistReasonDesc;
    }

    public void setBlacklistReasonDesc(String blacklistReasonDesc) {
      this.blacklistReasonDesc = blacklistReasonDesc;
    }

    public String getSffCfgLovId() {
      return sffCfgLovId;
    }

    public void setSffCfgLovId(String sffCfgLovId) {
      this.sffCfgLovId = sffCfgLovId;
    }

  }

  public class SFFBlacklistReasonResponse extends DBTemplatesResponse<HashMap<String, SFFBlacklistReasonInfo>> {

    @Override
    protected HashMap<String, SFFBlacklistReasonInfo> createResponse() {
      return new HashMap<>();
    }

  }

  protected class GetSFFBlacklistReason extends DBTemplatesExecuteQuery<SFFBlacklistReasonResponse, UtilityLogger, DBConnectionPools> {

    public GetSFFBlacklistReason(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected SFFBlacklistReasonResponse createResponse() {
      return new SFFBlacklistReasonResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append(" SELECT BLACKLIST_REASON_TYPE, BLACKLIST_REASON_CODE, BLACKLIST_REASON_DESC, SFF_CFG_LOV_ID ").append(Constants.END_LINE);
      sql.append(" FROM dbo.CL_SFF_BLACKLIST_REASON ").append(Constants.END_LINE);
      sql.append(" WHERE RECORD_STATUS = 1 ").append(Constants.END_LINE);
      return sql;
    }

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      SFFBlacklistReasonInfo temp = new SFFBlacklistReasonInfo();
      temp.setBlacklistReasonType(resultSet.getBigDecimal("BLACKLIST_REASON_TYPE"));
      temp.setBlacklistReasonCode(resultSet.getString("BLACKLIST_REASON_CODE"));
      temp.setBlacklistReasonDesc(resultSet.getString("BLACKLIST_REASON_DESC"));
      temp.setSffCfgLovId(resultSet.getString("SFF_CFG_LOV_ID"));
      response.getResponse().put(temp.getBlacklistReasonType().toPlainString(), temp);
    }

    protected SFFBlacklistReasonResponse execute() {
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public SFFBlacklistReasonResponse querySFFBlacklistReasonActive() {
    return new GetSFFBlacklistReason(logger).execute();
  }
  //</editor-fold>
  //
  
}
