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
public class CLProductType {

  protected final UtilityLogger logger;

  public CLProductType(UtilityLogger logger) {
    this.logger = logger;
  }

  //<editor-fold defaultstate="collapsed" desc="get cl_action_criteria">
  public class ProductTypeInfo {

    public ProductTypeInfo() {
    }
    private BigDecimal productTypeId;
    private String productTypeCode;
    private String productTypeDesc;
    private Character mobilePrefixBoo;

    public BigDecimal getProductTypeId() {
      return productTypeId;
    }

    public void setProductTypeId(BigDecimal productTypeId) {
      this.productTypeId = productTypeId;
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

  public class ProductTypeResponse extends DBTemplatesResponse<HashMap<String, ProductTypeInfo>> {

    @Override
    protected HashMap<String, ProductTypeInfo> createResponse() {
      return new HashMap<>();
    }
  }

  protected class GetProductType extends DBTemplatesExecuteQuery<ProductTypeResponse, UtilityLogger, DBConnectionPools> {

    public GetProductType(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected ProductTypeResponse createResponse() {
      return new ProductTypeResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append(" SELECT PRODUCT_TYPE_ID, PRODUCT_TYPE_CODE, PRODUCT_TYPE_DESC, MOBILE_PREFIX_BOO");
      sql.append(" FROM dbo.CL_PRODUCT_TYPE");
      sql.append(" WHERE RECORD_STATUS = 1");
      return sql;
    }

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      ProductTypeInfo temp = new ProductTypeInfo();
      temp.setProductTypeId(resultSet.getBigDecimal("PRODUCT_TYPE_ID"));
      temp.setProductTypeCode(resultSet.getString("PRODUCT_TYPE_CODE"));
      temp.setProductTypeDesc(resultSet.getString("PRODUCT_TYPE_DESC"));
      temp.setMobilePrefixBoo(toCharcter(resultSet.getString("MOBILE_PREFIX_BOO"), 'N'));
      response.getResponse().put(temp.getProductTypeId().toPlainString(), temp);
    }

    protected ProductTypeResponse execute() {
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public ProductTypeResponse getProductTypeActive() {
    return new GetProductType(logger).execute();
  }

  //</editor-fold>
  //
}
