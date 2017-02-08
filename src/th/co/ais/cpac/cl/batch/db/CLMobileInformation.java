/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.db;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import th.co.ais.cpac.cl.batch.Constants;
import th.co.ais.cpac.cl.common.UtilityLogger;
import th.co.ais.cpac.cl.template.database.DBConnectionPools;
import th.co.ais.cpac.cl.template.database.DBTemplateCallableStatement;
import th.co.ais.cpac.cl.template.database.DBTemplatesExecuteQuery;
import th.co.ais.cpac.cl.template.database.DBTemplatesResponse;

/**
 *
 * @author Optimus
 */
public class CLMobileInformation {

  //
  public CLMobileInformation(UtilityLogger logger) {
    this.logger = logger;
  }
  protected final UtilityLogger logger;

  //<editor-fold defaultstate="collapsed" desc="Get CL_MOBILE_INFO mobile active">
  public class CLMobileInfo {

    private String baNumber;
    private String mobileNumber;
    private String mobileSegment;
    private String mobileStatus;
    private String mobileStatusReason;
    private Date mobileStatusDtm;
    private String orderNumber;
    private String orderType;
    private String suspendType;
    private String sffOrderId;
    private Date registerDate;
    private BigDecimal productTypeId;
    private String smsLanguage;
    private String emailLanguage;
    private Character prepaidBoo;
    private Character contractBoo;
    private Character contractPhoneBoo;
    private String sffAssetInstanceId;
    private Date created;
    private String createdBy;
    private Date lastUpd;
    private String lastUpdBy;

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

    public String getMobileSegment() {
      return mobileSegment;
    }

    public void setMobileSegment(String mobileSegment) {
      this.mobileSegment = mobileSegment;
    }

    public String getMobileStatus() {
      return mobileStatus;
    }

    public void setMobileStatus(String mobileStatus) {
      this.mobileStatus = mobileStatus;
    }

    public String getMobileStatusReason() {
      return mobileStatusReason;
    }

    public void setMobileStatusReason(String mobileStatusReason) {
      this.mobileStatusReason = mobileStatusReason;
    }

    public Date getMobileStatusDtm() {
      return mobileStatusDtm;
    }

    public void setMobileStatusDtm(Date mobileStatusDtm) {
      this.mobileStatusDtm = mobileStatusDtm;
    }

    public String getOrderNumber() {
      return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
      this.orderNumber = orderNumber;
    }

    public String getOrderType() {
      return orderType;
    }

    public void setOrderType(String orderType) {
      this.orderType = orderType;
    }

    public String getSuspendType() {
      return suspendType;
    }

    public void setSuspendType(String suspendType) {
      this.suspendType = suspendType;
    }

    public String getSffOrderId() {
      return sffOrderId;
    }

    public void setSffOrderId(String sffOrderId) {
      this.sffOrderId = sffOrderId;
    }

    public Date getRegisterDate() {
      return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
      this.registerDate = registerDate;
    }

    public BigDecimal getProductTypeId() {
      return productTypeId;
    }

    public void setProductTypeId(BigDecimal productTypeId) {
      this.productTypeId = productTypeId;
    }

    public String getSmsLanguage() {
      return smsLanguage;
    }

    public void setSmsLanguage(String smsLanguage) {
      this.smsLanguage = smsLanguage;
    }

    public String getEmailLanguage() {
      return emailLanguage;
    }

    public void setEmailLanguage(String emailLanguage) {
      this.emailLanguage = emailLanguage;
    }

    public Character getPrepaidBoo() {
      return prepaidBoo;
    }

    public void setPrepaidBoo(Character prepaidBoo) {
      this.prepaidBoo = prepaidBoo;
    }

    public Character getContractBoo() {
      return contractBoo;
    }

    public void setContractBoo(Character contractBoo) {
      this.contractBoo = contractBoo;
    }

    public Character getContractPhoneBoo() {
      return contractPhoneBoo;
    }

    public void setContractPhoneBoo(Character contractPhoneBoo) {
      this.contractPhoneBoo = contractPhoneBoo;
    }

    public String getSffAssetInstanceId() {
      return sffAssetInstanceId;
    }

    public void setSffAssetInstanceId(String sffAssetInstanceId) {
      this.sffAssetInstanceId = sffAssetInstanceId;
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

    @Override
    public String toString() {
      return "CLMobileInfo{" + "baNumber=" + baNumber + ", mobileNumber=" + mobileNumber + ", mobileSegment=" + mobileSegment + ", mobileStatus=" + mobileStatus + ", mobileStatusReason=" + mobileStatusReason + ", mobileStatusDtm=" + mobileStatusDtm + ", orderNumber=" + orderNumber + ", orderType=" + orderType + ", suspendType=" + suspendType + ", sffOrderId=" + sffOrderId + ", registerDate=" + registerDate + ", productTypeId=" + productTypeId + ", smsLanguage=" + smsLanguage + ", emailLanguage=" + emailLanguage + ", prepaidBoo=" + prepaidBoo + ", contractBoo=" + contractBoo + ", contractPhoneBoo=" + contractPhoneBoo + ", sffAssetInstanceId=" + sffAssetInstanceId + ", created=" + created + ", createdBy=" + createdBy + ", lastUpd=" + lastUpd + ", lastUpdBy=" + lastUpdBy + '}';
    }

    
  }

  public class CLMobileInfoResponse extends DBTemplatesResponse< ArrayList<CLMobileInfo>> {

    @Override
    protected ArrayList<CLMobileInfo> createResponse() {
      return new ArrayList<>();
    }

  }

  protected class GetListCLMobileInfo extends DBTemplatesExecuteQuery<CLMobileInfoResponse, UtilityLogger, DBConnectionPools> {

    static final int SMS_BOO = 10;
    static final int SD1_BOO = 20;
    static final int SD2_BOO = 30;

    public GetListCLMobileInfo(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected CLMobileInfoResponse createResponse() {
      return new CLMobileInfoResponse();
    }

    private ArrayList<String> listBa;
    private String listMobileStatus;

    @Override
    protected StringBuilder createSqlProcess() {
      String list_ba = arrayListStringToStringQuery(listBa);
       

      StringBuilder sql = new StringBuilder();
      sql.append(" SELECT").append(Constants.END_LINE);
      sql.append("   BA_NO, MOBILE_NO, MOBILE_SEGMENT, MOBILE_STATUS, MOBILE_STATUS_REASON, MOBILE_STATUS_DTM, ORDER_NO, ORDER_TYPE, ").append(Constants.END_LINE);
      sql.append("   SUSPEND_TYPE, SFF_ORDER_ID, REGISTER_DATE, PRODUCT_TYPE_ID, SMS_LANGUAGE, EMAIL_LANGUAGE, PREPAID_BOO, ").append(Constants.END_LINE);
      sql.append("   CONTRACT_BOO, CONTRACT_PHONE_BOO, SFF_ASSET_INSTANCE_ID, ").append(Constants.END_LINE);
      sql.append("   CREATED, CREATED_BY, LAST_UPD, LAST_UPD_BY ").append(Constants.END_LINE);
      sql.append(" FROM CL_MOBILE_INFO").append(Constants.END_LINE);
      sql.append(" WHERE BA_NO in (").append(list_ba).append(") ").append(Constants.END_LINE);
      sql.append("   and MOBILE_STATUS in (").append(listMobileStatus).append(")").append(Constants.END_LINE);
      sql.append(" ORDER BY REGISTER_DATE").append(Constants.END_LINE);

      return sql;
    }

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      CLMobileInfo temp = new CLMobileInfo();
      temp.setBaNumber(resultSet.getString("BA_NO"));
      temp.setMobileNumber(resultSet.getString("MOBILE_NO"));
      temp.setMobileSegment(resultSet.getString("MOBILE_SEGMENT"));
      temp.setMobileStatus(resultSet.getString("MOBILE_STATUS"));
      temp.setMobileStatusReason(resultSet.getString("MOBILE_STATUS_REASON"));
      temp.setMobileStatusDtm(toDate(resultSet.getTimestamp("MOBILE_STATUS_DTM"), null));
      temp.setOrderNumber(resultSet.getString("ORDER_NO"));
      temp.setOrderType(resultSet.getString("ORDER_TYPE"));
      temp.setSuspendType(resultSet.getString("SUSPEND_TYPE"));
      temp.setSffOrderId(resultSet.getString("SFF_ORDER_ID"));
      temp.setRegisterDate(toDate(resultSet.getTimestamp("REGISTER_DATE"), null));
      temp.setProductTypeId(resultSet.getBigDecimal("PRODUCT_TYPE_ID"));
      temp.setSmsLanguage(resultSet.getString("SMS_LANGUAGE"));
      temp.setEmailLanguage(resultSet.getString("EMAIL_LANGUAGE"));
      temp.setPrepaidBoo(toCharcter(resultSet.getString("PREPAID_BOO"), 'N'));
      temp.setContractBoo(toCharcter(resultSet.getString("CONTRACT_BOO"), 'N'));
      temp.setContractPhoneBoo(toCharcter(resultSet.getString("CONTRACT_PHONE_BOO"), 'N'));
      temp.setSffAssetInstanceId(resultSet.getString("SFF_ASSET_INSTANCE_ID"));
      temp.setCreated(toDate(resultSet.getTimestamp("CREATED"), null));
      temp.setCreatedBy(resultSet.getString("CREATED_BY"));
      temp.setLastUpd(toDate(resultSet.getTimestamp("LAST_UPD"), null));
      temp.setLastUpdBy(resultSet.getString("LAST_UPD_BY"));

      response.getResponse().add(temp);
    }

    protected CLMobileInfoResponse execute(ArrayList<String> listBa, String listMobileStatus) {
      this.listBa = listBa;
      this.listMobileStatus = listMobileStatus;
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

    protected CLMobileInfoResponse execute(ArrayList<String> listBa, ArrayList<String> listMobileStatus) {      
      this.listBa = listBa;
      this.listMobileStatus = arrayListStringToStringQuery(listMobileStatus);
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }
  }

  //
  public CLMobileInfoResponse getListMobileByMobileStatus(ArrayList<String> baNo, String listMobileStatus) {
    return new GetListCLMobileInfo(logger).execute(baNo, listMobileStatus);
  }

  public CLMobileInfoResponse getListMobileByMobileStatus(ArrayList<String> baNo, ArrayList<String> listMobileStatus) {
    return new GetListCLMobileInfo(logger).execute(baNo, listMobileStatus);
  }

  //</editor-fold>
  //.. 
  //<editor-fold defaultstate="collapsed" desc="Get Contact mobile active">
  protected class GetListCLMobileInfoByContact extends DBTemplatesExecuteQuery<CLMobileInfoResponse, UtilityLogger, DBConnectionPools> {

    public GetListCLMobileInfoByContact(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected CLMobileInfoResponse createResponse() {
      return new CLMobileInfoResponse();
    }
    String contactNo;
    Constants.ContactLevel contactLevel;

    @Override
    protected StringBuilder createSqlProcess() {

      StringBuilder sql = new StringBuilder();

      sql.append(" SELECT ").append(Constants.END_LINE);
      sql.append("   BA_NO, MOBILE_NO, MOBILE_SEGMENT_ID, MOBILE_STATUS_ID, MOBILE_STATUS_REASON, MOBILE_STATUS_DTM, ").append(Constants.END_LINE);
      sql.append("   ORDER_NO, ORDER_TYPE_ID, ORDER_REASON_ID, SUSPEND_TYPE_ID, SFF_ORDER_ID, REGISTER_DATE, PRODUCT_TYPE_ID, ").append(Constants.END_LINE);
      sql.append("   LANGUAGE_ID_SMS, LANGUAGE_ID_EMAIL, ").append(Constants.END_LINE);
      sql.append("   PREPAID_BOO, CONTRACT_BOO, CONTRACT_PHONE_BOO, SFF_ASSET_INSTANCE_ID").append(Constants.END_LINE);
      sql.append(" FROM (").append(Constants.END_LINE);
      sql.append("   SELECT CONTACT_NUMBER").append(Constants.END_LINE);
      sql.append("   FROM dbo.CL_CONTACT_NUMBER").append(Constants.END_LINE);
      sql.append("   WHERE CONTACT_ID in (").append(Constants.END_LINE);
      sql.append("     SELECT CONTACT_ID").append(Constants.END_LINE);
      sql.append("     FROM dbo.CL_CONTACT").append(Constants.END_LINE);
      sql.append("     WHERE CONTACT_ACCOUNT_NO = '").append(contactNo).append("' and CONTACT_LEVEL = ").append(contactLevel.getCode()).append(Constants.END_LINE);
      sql.append("       and getdate() between RECORD_END_DTM and isnull(RECORD_END_DTM , getdate())").append(Constants.END_LINE);
      sql.append("   ) AND  CONTACT_TYPE = 1 ").append(Constants.END_LINE);
      sql.append(" )t1  left join dbo.CL_MOBILE_INFO t2").append(Constants.END_LINE);
      sql.append(" on t1.CONTACT_NUMBER = t2.MOBILE_NO").append(Constants.END_LINE);
      sql.append(" order by REGISTER_DATE").append(Constants.END_LINE);
      return sql;
    }

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      CLMobileInfo temp = new CLMobileInfo();
      temp.setBaNumber(resultSet.getString("BA_NO"));
      temp.setMobileNumber(resultSet.getString("MOBILE_NO"));
      temp.setMobileSegment(resultSet.getString("MOBILE_SEGMENT"));
      temp.setMobileStatus(resultSet.getString("MOBILE_STATUS"));
      temp.setMobileStatusReason(resultSet.getString("MOBILE_STATUS_REASON"));
      temp.setMobileStatusDtm(toDate(resultSet.getTimestamp("MOBILE_STATUS_DTM"), null));
      temp.setOrderNumber(resultSet.getString("ORDER_NO"));
      temp.setOrderType(resultSet.getString("ORDER_TYPE"));
      temp.setSuspendType(resultSet.getString("SUSPEND_TYPE"));
      temp.setSffOrderId(resultSet.getString("SFF_ORDER_ID"));
      temp.setRegisterDate(toDate(resultSet.getTimestamp("REGISTER_DATE"), null));
      temp.setProductTypeId(resultSet.getBigDecimal("PRODUCT_TYPE_ID"));
      temp.setSmsLanguage(resultSet.getString("SMS_LANGUAGE"));
      temp.setEmailLanguage(resultSet.getString("EMAIL_LANGUAGE"));
      temp.setPrepaidBoo(toCharcter(resultSet.getString("PREPAID_BOO"), 'N'));
      temp.setContractBoo(toCharcter(resultSet.getString("CONTRACT_BOO"), 'N'));
      temp.setContractPhoneBoo(toCharcter(resultSet.getString("CONTRACT_PHONE_BOO"), 'N'));
      temp.setSffAssetInstanceId(resultSet.getString("SFF_ASSET_INSTANCE_ID"));
      temp.setCreated(toDate(resultSet.getTimestamp("CREATED"), null));
      temp.setCreatedBy(resultSet.getString("CREATED_BY"));
      temp.setLastUpd(toDate(resultSet.getTimestamp("LAST_UPD"), null));
      temp.setLastUpdBy(resultSet.getString("LAST_UPD_BY"));
      response.getResponse().add(temp);
    }

    protected CLMobileInfoResponse execute(Constants.ContactLevel contactLevel, String contactNo) {
      this.contactLevel = contactLevel;
      this.contactNo = contactNo;
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public CLMobileInfoResponse getListMobileByContact(Constants.ContactLevel contactLevel, String contactNo) {
    return new GetListCLMobileInfoByContact(logger).execute(contactLevel, contactNo);
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="GetMobileRefference">
  protected class GetMobileRefference extends DBTemplateCallableStatement<CLMobileInfoResponse, UtilityLogger, DBConnectionPools> {

    private String baNumber;
    private String baStatus;
    private int rowcount = 1;

    public GetMobileRefference(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected void setParameter() throws SQLException {
      statement.setQueryTimeout(1800);
      statement.setString(1, baNumber);
      statement.setString(2, baStatus);
      statement.setInt(3, rowcount);
      statement.registerOutParameter(4, java.sql.Types.INTEGER);
    }

    @Override
    protected void setReturnValue() throws SQLException {
      if (resultSet != null) {
        while (resultSet.next()) {
          countRecord();
          CLMobileInfo temp = new CLMobileInfo();
          temp.setBaNumber(resultSet.getString("BA_NO"));
          temp.setMobileNumber(resultSet.getString("MOBILE_NO"));
          temp.setMobileSegment(resultSet.getString("MOBILE_SEGMENT"));
          temp.setMobileStatus(resultSet.getString("MOBILE_STATUS"));
          temp.setMobileStatusReason(resultSet.getString("MOBILE_STATUS_REASON"));
          temp.setMobileStatusDtm(toDate(resultSet.getTimestamp("MOBILE_STATUS_DTM"), null));
          temp.setOrderNumber(resultSet.getString("ORDER_NO"));
          temp.setOrderType(resultSet.getString("ORDER_TYPE"));
          temp.setSuspendType(resultSet.getString("SUSPEND_TYPE"));
          temp.setSffOrderId(resultSet.getString("SFF_ORDER_ID"));
          temp.setRegisterDate(toDate(resultSet.getTimestamp("REGISTER_DATE"), null));
          temp.setProductTypeId(resultSet.getBigDecimal("PRODUCT_TYPE_ID"));
          temp.setSmsLanguage(resultSet.getString("SMS_LANGUAGE"));
          temp.setEmailLanguage(resultSet.getString("EMAIL_LANGUAGE"));
          temp.setPrepaidBoo(toCharcter(resultSet.getString("PREPAID_BOO"), 'N'));
          temp.setContractBoo(toCharcter(resultSet.getString("CONTRACT_BOO"), 'N'));
          temp.setContractPhoneBoo(toCharcter(resultSet.getString("CONTRACT_PHONE_BOO"), 'N'));
          temp.setSffAssetInstanceId(resultSet.getString("SFF_ASSET_INSTANCE_ID"));
          temp.setCreated(toDate(resultSet.getTimestamp("CREATED"), null));
          temp.setCreatedBy(resultSet.getString("CREATED_BY"));
          temp.setLastUpd(toDate(resultSet.getTimestamp("LAST_UPD"), null));
          temp.setLastUpdBy(resultSet.getString("LAST_UPD_BY"));
          response.getResponse().add(temp);
        }
      }
      Integer code = (Integer) statement.getObject(4);
      //logger.debug("code = " + code);
    }

    @Override
    protected CLMobileInfoResponse createResponse() {
      return new CLMobileInfoResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      return new StringBuilder("{call CL_CM_GET_REFF_MOBILE (?,?,?,?)}");

    }

    protected CLMobileInfoResponse execute(String baNumber, String baStatus, int rowcount) {
      this.baNumber = baNumber;
      this.baStatus = baStatus;
      this.rowcount = rowcount;
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public CLMobileInfoResponse getMobileRefference(String baNumber, String baStatus, int rowcount) {
    return new GetMobileRefference(logger).execute(baNumber, baStatus, rowcount);
  }
  //</editor-fold>
  //
}
