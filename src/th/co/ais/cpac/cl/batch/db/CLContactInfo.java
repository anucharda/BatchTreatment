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
import th.co.ais.cpac.cl.batch.Constants;
import th.co.ais.cpac.cl.common.UtilityLogger;
import th.co.ais.cpac.cl.template.database.DBConnectionPools;
import th.co.ais.cpac.cl.template.database.DBTemplatesExecuteQuery;
import th.co.ais.cpac.cl.template.database.DBTemplatesResponse;

/**
 *
 * @author Optimus
 */
public class CLContactInfo {
  //

  public CLContactInfo(UtilityLogger logger) {
    this.logger = logger;
  }
  protected final UtilityLogger logger;

  //.. 
  //<editor-fold defaultstate="collapsed" desc="Get CL_CONTACT ADDRESS">
  public class CLContactAddressInfo {

    private BigDecimal contactId;
    private String contactName;
    private BigDecimal contactAddressId;
    private String contactAddrLine1;
    private String contactAddrLine2;
    private String contactAddrLine3;
    private String contactAddrLine4;
    private String contactAddrLine5;
    private String contactZipcode;

    public BigDecimal getContactId() {
      return contactId;
    }

    public void setContactId(BigDecimal contactId) {
      this.contactId = contactId;
    }

    public String getContactName() {
      return contactName;
    }

    public void setContactName(String contactName) {
      this.contactName = contactName;
    }

    public BigDecimal getContactAddressId() {
      return contactAddressId;
    }

    public void setContactAddressId(BigDecimal contactAddressId) {
      this.contactAddressId = contactAddressId;
    }

    public String getContactAddrLine1() {
      return contactAddrLine1;
    }

    public void setContactAddrLine1(String contactAddrLine1) {
      this.contactAddrLine1 = contactAddrLine1;
    }

    public String getContactAddrLine2() {
      return contactAddrLine2;
    }

    public void setContactAddrLine2(String contactAddrLine2) {
      this.contactAddrLine2 = contactAddrLine2;
    }

    public String getContactAddrLine3() {
      return contactAddrLine3;
    }

    public void setContactAddrLine3(String contactAddrLine3) {
      this.contactAddrLine3 = contactAddrLine3;
    }

    public String getContactAddrLine4() {
      return contactAddrLine4;
    }

    public void setContactAddrLine4(String contactAddrLine4) {
      this.contactAddrLine4 = contactAddrLine4;
    }

    public String getContactAddrLine5() {
      return contactAddrLine5;
    }

    public void setContactAddrLine5(String contactAddrLine5) {
      this.contactAddrLine5 = contactAddrLine5;
    }

    public String getContactZipcode() {
      return contactZipcode;
    }

    public void setContactZipcode(String contactZipcode) {
      this.contactZipcode = contactZipcode;
    }

  }

  public class CLContactAddressInfoResponse extends DBTemplatesResponse< ArrayList<CLContactAddressInfo>> {

    @Override
    protected ArrayList<CLContactAddressInfo> createResponse() {
      return new ArrayList<>();
    }

  }

  protected class GetListCLContactAddressInfo extends DBTemplatesExecuteQuery<CLContactAddressInfoResponse, UtilityLogger, DBConnectionPools> {

    public GetListCLContactAddressInfo(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected CLContactAddressInfoResponse createResponse() {
      return new CLContactAddressInfoResponse();
    }
    private Constants.ContactLevel contactLevel;
    private String contactNo;

    @Override
    protected StringBuilder createSqlProcess() {

      StringBuilder sql = new StringBuilder();

      sql.append(" SELECT ").append(Constants.END_LINE);
      sql.append("   t1.CONTACT_ID, t1.CONTACT_NAME , ").append(Constants.END_LINE);
      sql.append("   t2.CONTACT_ADDRESS_ID,  ").append(Constants.END_LINE);
      sql.append("   t2.CONTACT_ADDR_LINE_1, t2.CONTACT_ADDR_LINE_2, t2.CONTACT_ADDR_LINE_3, t2.CONTACT_ADDR_LINE_4, t2.CONTACT_ADDR_LINE_5,  ").append(Constants.END_LINE);
      sql.append("   t2.CONTACT_ZIPCODE ").append(Constants.END_LINE);
      sql.append(" FROM ( ").append(Constants.END_LINE);
      sql.append("   SELECT  ").append(Constants.END_LINE);
      sql.append("     CONTACT_ID, CONTACT_LEVEL, CONTACT_ACCOUNT_NO, CONTACT_NAME ").append(Constants.END_LINE);
      sql.append("   FROM dbo.CL_CONTACT ").append(Constants.END_LINE);
      sql.append("   WHERE  CONTACT_ACCOUNT_NO = '").append(contactNo).append("' and CONTACT_LEVEL = ").append(contactLevel.getCode()).append(" and getdate() between RECORD_START_DTM and isnull(RECORD_END_DTM , getdate())  ").append(Constants.END_LINE);
      sql.append(" ) t1 left join CL_CONTACT_ADDRESS t2 on t1.CONTACT_ID = t2.CONTACT_ID ").append(Constants.END_LINE);
      sql.append(" WHERE getdate() between RECORD_START_DTM and isnull(RECORD_END_DTM,getdate()) ").append(Constants.END_LINE);

      return sql;
    }

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      CLContactAddressInfo temp = new CLContactAddressInfo();
      temp.setContactId(resultSet.getBigDecimal("CONTACT_ID"));
      temp.setContactName(resultSet.getString("CONTACT_NAME"));
      temp.setContactAddressId(resultSet.getBigDecimal("CONTACT_ADDRESS_ID"));
      temp.setContactAddrLine1(resultSet.getString("CONTACT_ADDR_LINE_1"));
      temp.setContactAddrLine2(resultSet.getString("CONTACT_ADDR_LINE_2"));
      temp.setContactAddrLine3(resultSet.getString("CONTACT_ADDR_LINE_3"));
      temp.setContactAddrLine4(resultSet.getString("CONTACT_ADDR_LINE_4"));
      temp.setContactAddrLine5(resultSet.getString("CONTACT_ADDR_LINE_5"));
      temp.setContactZipcode(resultSet.getString("CONTACT_ZIPCODE"));
      response.getResponse().add(temp);
    }

    protected CLContactAddressInfoResponse execute(Constants.ContactLevel contactLevel, String contactNo) {
      this.contactLevel = contactLevel;
      this.contactNo = contactNo;
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public CLContactAddressInfoResponse getContactAddress(Constants.ContactLevel contactLevel, String contactNo) {
    return new GetListCLContactAddressInfo(logger).execute(contactLevel, contactNo);
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="Get CL_CONTACT ADDRESS">
  public class CLContactEMailInfo {

    private BigDecimal contactId;
    private String contactName;
    private BigDecimal contactEmailId;
    private String emailAddress;

    public BigDecimal getContactId() {
      return contactId;
    }

    public void setContactId(BigDecimal contactId) {
      this.contactId = contactId;
    }

    public String getContactName() {
      return contactName;
    }

    public void setContactName(String contactName) {
      this.contactName = contactName;
    }

    public BigDecimal getContactEmailId() {
      return contactEmailId;
    }

    public void setContactEmailId(BigDecimal contactEmailId) {
      this.contactEmailId = contactEmailId;
    }

    public String getEmailAddress() {
      return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
      this.emailAddress = emailAddress;
    }

  }

  public class CLContactEMailInfoResponse extends DBTemplatesResponse< ArrayList<CLContactEMailInfo>> {

    @Override
    protected ArrayList<CLContactEMailInfo> createResponse() {
      return new ArrayList<>();
    }
  }

  protected class GetListCLContactEMailInfo extends DBTemplatesExecuteQuery<CLContactEMailInfoResponse, UtilityLogger, DBConnectionPools> {

    public GetListCLContactEMailInfo(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected CLContactEMailInfoResponse createResponse() {
      return new CLContactEMailInfoResponse();
    }
    private Constants.ContactLevel contactLevel;
    private String contactNo;

    @Override
    protected StringBuilder createSqlProcess() {

      StringBuilder sql = new StringBuilder();

      sql.append(" SELECT ").append(Constants.END_LINE);
      sql.append("   t1.CONTACT_ID, t1.CONTACT_NAME , ").append(Constants.END_LINE);
      sql.append("   t2.CONTACT_EMAIL_ID, t2.EMAIL_ADDRESS").append(Constants.END_LINE);
      sql.append(" FROM ( ").append(Constants.END_LINE);
      sql.append("   SELECT  ").append(Constants.END_LINE);
      sql.append("     CONTACT_ID, CONTACT_LEVEL, CONTACT_ACCOUNT_NO, CONTACT_NAME ").append(Constants.END_LINE);
      sql.append("   FROM dbo.CL_CONTACT ").append(Constants.END_LINE);
      sql.append("   WHERE  CONTACT_ACCOUNT_NO = ").append(contactNo).append(" and CONTACT_LEVEL =  ").append(contactLevel.getCode()).append("  and getdate() between RECORD_START_DTM and isnull(RECORD_END_DTM , getdate())  ").append(Constants.END_LINE);
      sql.append(" ) t1 left join dbo.CL_CONTACT_EMAIL t2 on t1.CONTACT_ID = t2.CONTACT_ID ").append(Constants.END_LINE);
      return sql;
    }

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      CLContactEMailInfo temp = new CLContactEMailInfo();
      temp.setContactId(resultSet.getBigDecimal("CONTACT_ID"));
      temp.setContactName(resultSet.getString("CONTACT_NAME"));
      temp.setContactEmailId(resultSet.getBigDecimal("CONTACT_EMAIL_ID"));
      temp.setEmailAddress(resultSet.getString("EMAIL_ADDRESS"));

      response.getResponse().add(temp);
    }

    protected CLContactEMailInfoResponse execute(Constants.ContactLevel contactLevel, String contactNo) {
      this.contactLevel = contactLevel;
      this.contactNo = contactNo;
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public CLContactEMailInfoResponse getContactEMail(Constants.ContactLevel contactLevel, String contactNo) {
    return new GetListCLContactEMailInfo(logger).execute(contactLevel, contactNo);
  }

  //</editor-fold>
}
