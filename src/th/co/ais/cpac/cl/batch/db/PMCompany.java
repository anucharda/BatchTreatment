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
public class PMCompany {

  protected final UtilityLogger logger;

  public PMCompany(UtilityLogger logger) {
    this.logger = logger;
  }

  //
  //<editor-fold defaultstate="collapsed" desc="Get Parameter SMS From PM_INVOICE">
  public class PMCompanyInfo {

    protected PMCompanyInfo() {
    }

    private BigDecimal companyId;
    private String companyCode;
    private String companyAbbr;
    private String companyName;
    private String companyNameTh;
    private String sapCompanyCode;

    public BigDecimal getCompanyId() {
      return companyId;
    }

    public void setCompanyId(BigDecimal companyId) {
      this.companyId = companyId;
    }

    public String getCompanyCode() {
      return companyCode;
    }

    public void setCompanyCode(String companyCode) {
      this.companyCode = companyCode;
    }

    public String getCompanyAbbr() {
      return companyAbbr;
    }

    public void setCompanyAbbr(String companyAbbr) {
      this.companyAbbr = companyAbbr;
    }

    public String getCompanyName() {
      return companyName;
    }

    public void setCompanyName(String companyName) {
      this.companyName = companyName;
    }

    public String getCompanyNameTh() {
      return companyNameTh;
    }

    public void setCompanyNameTh(String companyNameTh) {
      this.companyNameTh = companyNameTh;
    }

    public String getSapCompanyCode() {
      return sapCompanyCode;
    }

    public void setSapCompanyCode(String sapCompanyCode) {
      this.sapCompanyCode = sapCompanyCode;
    }

  }

  public class PMCompanyResponse extends DBTemplatesResponse< HashMap<String, PMCompanyInfo>> {

    @Override
    protected HashMap<String, PMCompanyInfo> createResponse() {
      return new HashMap<>();
    }

  }

  protected class GetPMCompany extends DBTemplatesExecuteQuery<PMCompanyResponse, UtilityLogger, DBConnectionPools> {

    public GetPMCompany(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected PMCompanyResponse createResponse() {
      return new PMCompanyResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append(" SELECT COMPANY_ID, COMPANY_CODE, COMPANY_ABBR, COMPANY_NAME, COMPANY_NAME_TH ,SAP_COMPANY_CODE").append(Constants.END_LINE);
      sql.append(" FROM PMDB..PM_COMPANY").append(Constants.END_LINE);
      sql.append(" WHERE ACTIVE_BOO = 'Y'").append(Constants.END_LINE);
      return sql;
    }

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      PMCompanyInfo temp = new PMCompanyInfo();
      temp.setCompanyId(resultSet.getBigDecimal("COMPANY_ID"));
      temp.setCompanyCode(resultSet.getString("COMPANY_CODE"));
      temp.setCompanyAbbr(resultSet.getString("COMPANY_ABBR"));
      temp.setCompanyName(resultSet.getString("COMPANY_NAME"));
      temp.setCompanyNameTh(resultSet.getString("COMPANY_NAME_TH"));
      temp.setSapCompanyCode(resultSet.getString("SAP_COMPANY_CODE"));
      response.getResponse().put(temp.getCompanyAbbr(), temp);
    }

    protected PMCompanyResponse execute() {
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public PMCompanyResponse getCompanyActive() {
    return new GetPMCompany(logger).execute();
  }

  //</editor-fold>
  //
}
