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
import th.co.ais.cpac.cl.common.UtilityLogger;
import th.co.ais.cpac.cl.template.database.DBConnectionPools;
import th.co.ais.cpac.cl.template.database.DBTemplatesExecuteQuery;
import th.co.ais.cpac.cl.template.database.DBTemplatesResponse;

/**
 *
 * @author Optimus
 */
public class CLCAInfo {

  protected final UtilityLogger logger;

  public CLCAInfo(UtilityLogger logger) {
    this.logger = logger;
  }

  //
  //<editor-fold defaultstate="collapsed" desc="Get CL_CA_INFO Info">
  public class CAInfomation {

    protected CAInfomation() {
    }
    private String caNumber;
    private String caTitle;
    private String caName;
    private BigDecimal caStatus;
    private Date caStatusDtm;
    private String category;
    private String subcategory;
    private Date registerDate;
    private BigDecimal registerLocation;
    private String industryType;
    private String accountSegment;
    private String taxIdNumber;
    private String vatAddressName;
    private String vatAddressLine1;
    private String vatAddressLine2;
    private String vatAddressLine3;
    private String vatAddressLine4;
    private String vatAddressLine5;
    private String vatZipcode;
    private String sffAccountId;
    private String sbAccountId;

    public String getCaNumber() {
      return caNumber;
    }

    public void setCaNumber(String caNumber) {
      this.caNumber = caNumber;
    }

    public String getCaTitle() {
      return caTitle;
    }

    public void setCaTitle(String caTitle) {
      this.caTitle = caTitle;
    }

    public String getCaName() {
      return caName;
    }

    public void setCaName(String caName) {
      this.caName = caName;
    }

    public BigDecimal getCaStatus() {
      return caStatus;
    }

    public void setCaStatus(BigDecimal caStatus) {
      this.caStatus = caStatus;
    }

    public Date getCaStatusDtm() {
      return caStatusDtm;
    }

    public void setCaStatusDtm(Date caStatusDtm) {
      this.caStatusDtm = caStatusDtm;
    }

    public String getCategory() {
      return category;
    }

    public void setCategory(String category) {
      this.category = category;
    }

    public String getSubcategory() {
      return subcategory;
    }

    public void setSubcategory(String subcategory) {
      this.subcategory = subcategory;
    }

    public Date getRegisterDate() {
      return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
      this.registerDate = registerDate;
    }

    public BigDecimal getRegisterLocation() {
      return registerLocation;
    }

    public void setRegisterLocation(BigDecimal registerLocation) {
      this.registerLocation = registerLocation;
    }

    public String getIndustryType() {
      return industryType;
    }

    public void setIndustryType(String industryType) {
      this.industryType = industryType;
    }

    public String getAccountSegment() {
      return accountSegment;
    }

    public void setAccountSegment(String accountSegment) {
      this.accountSegment = accountSegment;
    }

    public String getTaxIdNumber() {
      return taxIdNumber;
    }

    public void setTaxIdNumber(String taxIdNumber) {
      this.taxIdNumber = taxIdNumber;
    }

    public String getVatAddressName() {
      return vatAddressName;
    }

    public void setVatAddressName(String vatAddressName) {
      this.vatAddressName = vatAddressName;
    }

    public String getVatAddressLine1() {
      return vatAddressLine1;
    }

    public void setVatAddressLine1(String vatAddressLine1) {
      this.vatAddressLine1 = vatAddressLine1;
    }

    public String getVatAddressLine2() {
      return vatAddressLine2;
    }

    public void setVatAddressLine2(String vatAddressLine2) {
      this.vatAddressLine2 = vatAddressLine2;
    }

    public String getVatAddressLine3() {
      return vatAddressLine3;
    }

    public void setVatAddressLine3(String vatAddressLine3) {
      this.vatAddressLine3 = vatAddressLine3;
    }

    public String getVatAddressLine4() {
      return vatAddressLine4;
    }

    public void setVatAddressLine4(String vatAddressLine4) {
      this.vatAddressLine4 = vatAddressLine4;
    }

    public String getVatAddressLine5() {
      return vatAddressLine5;
    }

    public void setVatAddressLine5(String vatAddressLine5) {
      this.vatAddressLine5 = vatAddressLine5;
    }

    public String getVatZipcode() {
      return vatZipcode;
    }

    public void setVatZipcode(String vatZipcode) {
      this.vatZipcode = vatZipcode;
    }

    public String getSffAccountId() {
      return sffAccountId;
    }

    public void setSffAccountId(String sffAccountId) {
      this.sffAccountId = sffAccountId;
    }

    public String getSbAccountId() {
      return sbAccountId;
    }

    public void setSbAccountId(String sbAccountId) {
      this.sbAccountId = sbAccountId;
    }

    @Override
    public String toString() {
      return "CAInfomation{" + "caNumber=" + caNumber + ", caTitle=" + caTitle + ", caName=" + caName + ", caStatus=" + caStatus + ", caStatusDtm=" + caStatusDtm + ", category=" + category + ", subcategory=" + subcategory + ", registerDate=" + registerDate + ", registerLocation=" + registerLocation + ", industryType=" + industryType + ", accountSegment=" + accountSegment + ", taxIdNumber=" + taxIdNumber + ", vatAddressName=" + vatAddressName + ", vatAddressLine1=" + vatAddressLine1 + ", vatAddressLine2=" + vatAddressLine2 + ", vatAddressLine3=" + vatAddressLine3 + ", vatAddressLine4=" + vatAddressLine4 + ", vatAddressLine5=" + vatAddressLine5 + ", vatZipcode=" + vatZipcode + ", sffAccountId=" + sffAccountId + ", sbAccountId=" + sbAccountId + '}';
    }

  }

  public CAInfomation buildCAInfomation() {
    return new CAInfomation();
  }

  public class CAInfomationResponse extends DBTemplatesResponse<CAInfomation> {

    @Override
    protected CAInfomation createResponse() {
      return new CAInfomation();
    }

  }

  protected class GetCAInfomation extends DBTemplatesExecuteQuery<CAInfomationResponse, UtilityLogger, DBConnectionPools> {

    public GetCAInfomation(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected CAInfomationResponse createResponse() {
      return new CAInfomationResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append(Constants.END_LINE);
      sql.append(" SELECT").append(Constants.END_LINE);
      sql.append("   CA_NO, CA_TITLE, CA_NAME, CA_STATUS, CA_STATUS_DTM, CATEGORY, SUBCATEGORY, ").append(Constants.END_LINE);
      sql.append("   REGISTER_DATE, REGISTER_LOCATION,INDUSTRY_TYPE, ACCOUNT_SEGMENT, TAX_ID_NUMBER, ").append(Constants.END_LINE);
      sql.append("   VAT_ADDRESS_NAME, VAT_ADDRESS_LINE_1, VAT_ADDRESS_LINE_2, VAT_ADDRESS_LINE_3, VAT_ADDRESS_LINE_4, VAT_ADDRESS_LINE_5, ").append(Constants.END_LINE);
      sql.append("   VAT_ZIPCODE, SFF_ACCOUNT_ID, SB_ACCOUNT_ID").append(Constants.END_LINE);
      sql.append(" FROM dbo.CL_CA_INFO").append(Constants.END_LINE);
      sql.append(" WHERE CA_NO = '").append(caNo).append("'").append(Constants.END_LINE);
      return sql;
    }

    private String caNo;

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      CAInfomation temp = response.getResponse();
      temp.setCaNumber(resultSet.getString("CA_NO"));
      temp.setCaTitle(resultSet.getString("CA_TITLE"));
      temp.setCaName(resultSet.getString("CA_NAME"));
      temp.setCaStatus(resultSet.getBigDecimal("CA_STATUS"));
      temp.setCaStatusDtm(toDate(resultSet.getTimestamp("CA_STATUS_DTM"), null));
      temp.setCategory(resultSet.getString("CATEGORY"));
      temp.setSubcategory(resultSet.getString("SUBCATEGORY"));
      temp.setRegisterDate(toDate(resultSet.getTimestamp("REGISTER_DATE"), null));
      temp.setRegisterLocation(resultSet.getBigDecimal("REGISTER_LOCATION"));
      temp.setIndustryType(resultSet.getString("INDUSTRY_TYPE"));
      temp.setAccountSegment(resultSet.getString("ACCOUNT_SEGMENT"));
      temp.setTaxIdNumber(resultSet.getString("TAX_ID_NUMBER"));
      temp.setVatAddressName(resultSet.getString("VAT_ADDRESS_NAME"));
      temp.setVatAddressLine1(resultSet.getString("VAT_ADDRESS_LINE_1"));
      temp.setVatAddressLine2(resultSet.getString("VAT_ADDRESS_LINE_2"));
      temp.setVatAddressLine3(resultSet.getString("VAT_ADDRESS_LINE_3"));
      temp.setVatAddressLine4(resultSet.getString("VAT_ADDRESS_LINE_4"));
      temp.setVatAddressLine5(resultSet.getString("VAT_ADDRESS_LINE_5"));
      temp.setVatZipcode(resultSet.getString("VAT_ZIPCODE"));
      temp.setSffAccountId(resultSet.getString("SFF_ACCOUNT_ID"));
      temp.setSbAccountId(resultSet.getString("SB_ACCOUNT_ID"));

    }

    protected CAInfomationResponse execute(String caNo) {
      this.caNo = caNo;
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public CAInfomationResponse getCAInfomation(String caNo) {
    return new GetCAInfomation(logger).execute(caNo);
  }

  //</editor-fold>
  //
}
