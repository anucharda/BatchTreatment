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
public class CLNetworkType {

  protected final UtilityLogger logger;

  public CLNetworkType(UtilityLogger logger) {
    this.logger = logger;
  }

  //<editor-fold defaultstate="collapsed" desc="get cl_action_criteria">
  public class NetworkTypeInfo {

    public NetworkTypeInfo() {
    }
    private String companyCode;
    private BigDecimal productTypeId;
    private String mobilePrefix;
    private String networkType;
    private String remark;
    private String productTypeCode;
    private String productTypeDesc;
    //**
    private Character mobilePrefixBoo = 'N';

    public String getCompanyCode() {
      return companyCode;
    }

    public void setCompanyCode(String companyCode) {
      this.companyCode = companyCode;
    }
    
    public BigDecimal getProductTypeId() {
      return productTypeId;
    }

    public void setProductTypeId(BigDecimal productTypeId) {
      this.productTypeId = productTypeId;
    }

    public String getMobilePrefix() {
      return mobilePrefix;
    }

    public void setMobilePrefix(String mobilePrefix) {
      this.mobilePrefix = mobilePrefix;
    }

    public String getNetworkType() {
      return networkType;
    }

    public void setNetworkType(String networkType) {
      this.networkType = networkType;
    }

    public String getRemark() {
      return remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }

    public String getProductTypeCode() {
      return productTypeCode;
    }

    public void setProductTypeCode(String productTypeCode) {
      this.productTypeCode = productTypeCode;
    }

    public String getProductTypeDesc() {
      return productTypeDesc;
    }

    public void setProductTypeDesc(String productTypeDesc) {
      this.productTypeDesc = productTypeDesc;
    }

    public Character getMobilePrefixBoo() {
      return mobilePrefixBoo;
    }

    public void setMobilePrefixBoo(Character mobilePrefixBoo) {
      this.mobilePrefixBoo = mobilePrefixBoo;
    }

  }

  public class ListNetworkTypeResponse extends DBTemplatesResponse<HashMap<String, NetworkTypeInfo>> {

    @Override
    protected HashMap<String, NetworkTypeInfo> createResponse() {
      return new HashMap<>();
    }

  }

  protected class GetListNetworkType extends DBTemplatesExecuteQuery<ListNetworkTypeResponse, UtilityLogger, DBConnectionPools> {

    public GetListNetworkType(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected ListNetworkTypeResponse createResponse() {
      return new ListNetworkTypeResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append(" SELECT ").append(Constants.END_LINE);
      sql.append("   t1.COMPANY_CODE, t1.PRODUCT_TYPE_ID, t1.MOBILE_PREFIX, t1.NETWORK_TYPE, t1.NETWORK_REMARK, ").append(Constants.END_LINE);
      sql.append("   t2.PRODUCT_TYPE_CODE, t2.MOBILE_PREFIX_BOO ,t2.PRODUCT_TYPE_DESC ").append(Constants.END_LINE);
      sql.append(" FROM ( ").append(Constants.END_LINE);
      sql.append("   SELECT COMPANY_CODE, PRODUCT_TYPE_ID, MOBILE_PREFIX, NETWORK_TYPE, NETWORK_REMARK  ").append(Constants.END_LINE);
      sql.append("   FROM dbo.CL_NETWORK_TYPE ").append(Constants.END_LINE);
      sql.append("   WHERE getdate() between EFFECT_START_DATE and isnull(EFFECT_END_DATE,getdate()) ").append(Constants.END_LINE);
      sql.append(" ) t1 left join ( ").append(Constants.END_LINE);
      sql.append("   SELECT PRODUCT_TYPE_ID , PRODUCT_TYPE_CODE,MOBILE_PREFIX_BOO, PRODUCT_TYPE_DESC ").append(Constants.END_LINE);
      sql.append("   FROM CL_PRODUCT_TYPE  ").append(Constants.END_LINE);
      sql.append("   WHERE RECORD_STATUS = 1 ").append(Constants.END_LINE);
      sql.append(" ) t2 on t1.PRODUCT_TYPE_ID = t2.PRODUCT_TYPE_ID ").append(Constants.END_LINE);
      return sql;
    }

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      NetworkTypeInfo temp = new NetworkTypeInfo();
      temp.setCompanyCode(resultSet.getString("COMPANY_CODE"));
      temp.setProductTypeId(resultSet.getBigDecimal("PRODUCT_TYPE_ID"));
      temp.setMobilePrefix(resultSet.getString("MOBILE_PREFIX"));
      temp.setNetworkType(resultSet.getString("NETWORK_TYPE"));
      temp.setRemark(resultSet.getString("NETWORK_REMARK"));
      temp.setProductTypeCode(resultSet.getString("PRODUCT_TYPE_CODE"));
      temp.setMobilePrefixBoo(toCharcter(resultSet.getString("MOBILE_PREFIX_BOO"), 'N'));
      temp.setProductTypeDesc(resultSet.getString("PRODUCT_TYPE_DESC"));
      String key = "";
      if (temp.getMobilePrefixBoo() == 'Y') {
        key = temp.getCompanyCode()+ "_" + temp.getProductTypeId() + "_" + temp.getMobilePrefix();
      } else {
        key = temp.getCompanyCode()  + "_" + temp.getProductTypeId();
      }
      response.getResponse().put(key, temp);
    }

    protected ListNetworkTypeResponse execute() {
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public ListNetworkTypeResponse getListNetworkTypeActive() {
    return new GetListNetworkType(logger).execute();
  }

  //</editor-fold>
  //
}
