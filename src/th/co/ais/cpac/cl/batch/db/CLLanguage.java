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
import th.co.ais.cpac.cl.common.UtilityLogger;
import th.co.ais.cpac.cl.template.configuration.Constants;
import th.co.ais.cpac.cl.template.database.DBConnectionPools;
import th.co.ais.cpac.cl.template.database.DBTemplatesExecuteQuery;
import th.co.ais.cpac.cl.template.database.DBTemplatesResponse;

/**
 *
 * @author Optimus
 */
public class CLLanguage {

  protected final UtilityLogger logger;

  public CLLanguage(UtilityLogger logger) {
    this.logger = logger;
  }

  //
  //<editor-fold defaultstate="collapsed" desc="Get CLLanguage">
  public class CLLanguagsInfo {

    protected CLLanguagsInfo() {
    }
 
    private BigDecimal languageId;
    private String languageCode;
    private String languageName;
    private String languageDesc;
    private String sffBillLanguage;
    private String sffSmsLanguage;
    private String sffEmailLanguage;
    private String sffPreferLanguage;
    private BigDecimal smsMaxChar;

    public BigDecimal getLanguageId() {
      return languageId;
    }

    public void setLanguageId(BigDecimal languageId) {
      this.languageId = languageId;
    }

    public String getLanguageCode() {
      return languageCode;
    }

    public void setLanguageCode(String languageCode) {
      this.languageCode = languageCode;
    }

    public String getLanguageName() {
      return languageName;
    }

    public void setLanguageName(String languageName) {
      this.languageName = languageName;
    }

    public String getLanguageDesc() {
      return languageDesc;
    }

    public void setLanguageDesc(String languageDesc) {
      this.languageDesc = languageDesc;
    }

    public String getSffBillLanguage() {
      return sffBillLanguage;
    }

    public void setSffBillLanguage(String sffBillLanguage) {
      this.sffBillLanguage = sffBillLanguage;
    }

    public String getSffSmsLanguage() {
      return sffSmsLanguage;
    }

    public void setSffSmsLanguage(String sffSmsLanguage) {
      this.sffSmsLanguage = sffSmsLanguage;
    }

    public String getSffEmailLanguage() {
      return sffEmailLanguage;
    }

    public void setSffEmailLanguage(String sffEmailLanguage) {
      this.sffEmailLanguage = sffEmailLanguage;
    }

    public String getSffPreferLanguage() {
      return sffPreferLanguage;
    }

    public void setSffPreferLanguage(String sffPreferLanguage) {
      this.sffPreferLanguage = sffPreferLanguage;
    }

    public BigDecimal getSmsMaxChar() {
      return smsMaxChar;
    }

    public void setSmsMaxChar(BigDecimal smsMaxChar) {
      this.smsMaxChar = smsMaxChar;
    }

  }

  public class LanguagsInfoResponse extends DBTemplatesResponse< HashMap<BigDecimal, CLLanguagsInfo>> {

    @Override
    protected HashMap<BigDecimal, CLLanguagsInfo> createResponse() {
      return new HashMap<>();
    }

  }

  protected class GetLanguagsInfoResponse extends DBTemplatesExecuteQuery<LanguagsInfoResponse, UtilityLogger, DBConnectionPools> {

    public GetLanguagsInfoResponse(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected LanguagsInfoResponse createResponse() {
      return new LanguagsInfoResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append(" SELECT LANGUAGE_ID, LANGUAGE_CODE, LANGUAGE_NAME, LANGUAGE_DESC, SFF_BILL_LANGUAGE, SFF_SMS_LANGUAGE, SFF_EMAIL_LANGUAGE, SFF_PREFER_LANGUAGE, SMS_MAX_CHAR ").append(Constants.END_LINE);
      sql.append(" FROM dbo.CL_LANGUAGE").append(Constants.END_LINE);
      sql.append(" WHERE RECORD_STATUS = 1").append(Constants.END_LINE);
      return sql;
    }

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      CLLanguagsInfo temp = new CLLanguagsInfo();
      temp.setLanguageId(resultSet.getBigDecimal("LANGUAGE_ID"));
      temp.setLanguageCode(resultSet.getString("LANGUAGE_CODE"));
      temp.setLanguageName(resultSet.getString("LANGUAGE_NAME"));
      temp.setLanguageDesc(resultSet.getString("LANGUAGE_DESC"));
      temp.setSffBillLanguage(resultSet.getString("SFF_BILL_LANGUAGE"));
      temp.setSffSmsLanguage(resultSet.getString("SFF_SMS_LANGUAGE"));
      temp.setSffEmailLanguage(resultSet.getString("SFF_EMAIL_LANGUAGE"));
      temp.setSffPreferLanguage(resultSet.getString("SFF_PREFER_LANGUAGE"));
      temp.setSmsMaxChar(resultSet.getBigDecimal("SMS_MAX_CHAR"));
      response.getResponse().put(temp.getLanguageId(), temp);
    }

    protected LanguagsInfoResponse execute() {
      return executeQuery(th.co.ais.cpac.cl.batch.Constants.getDBConnectionPools(logger), true);
    }

  }

  public LanguagsInfoResponse getLanguagsInfo() {
    return new GetLanguagsInfoResponse(logger).execute();
  }

  //</editor-fold>
  //
}
