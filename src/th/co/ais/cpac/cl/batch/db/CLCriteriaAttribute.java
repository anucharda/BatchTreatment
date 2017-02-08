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
public class CLCriteriaAttribute {

  protected final UtilityLogger logger;

  public CLCriteriaAttribute(UtilityLogger logger) {
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

  //<editor-fold defaultstate="collapsed" desc="Get CL_BATCH_TYPE Info">
  public class CriteriaAttributeInfo {

    protected CriteriaAttributeInfo() {
    }
    private BigDecimal criteriaAttributeId;
    private BigDecimal criteriaId;
    private BigDecimal attributeId;
    private String attributeAlias;
    private String attributeLabel;
    private BigDecimal attributeIndex;
    private BigDecimal orderByIndex;
    private Character descBoo;
    private BigDecimal displayOption;
    private BigDecimal attributeType;
    private String attributeName;
    private String attrAttributeAlias;
    private String attrAttributeLabel;
    private String attributeDesc;
    private BigDecimal tableId;
    private String columnName;
    private String expression;
    private BigDecimal dataType;
    private String tableName;
    private String tableAlias;
    private String tableOwner;
    private String tableDesc;

    public BigDecimal getCriteriaAttributeId() {
      return criteriaAttributeId;
    }

    public void setCriteriaAttributeId(BigDecimal criteriaAttributeId) {
      this.criteriaAttributeId = criteriaAttributeId;
    }

    public BigDecimal getCriteriaId() {
      return criteriaId;
    }

    public void setCriteriaId(BigDecimal criteriaId) {
      this.criteriaId = criteriaId;
    }

    public BigDecimal getAttributeId() {
      return attributeId;
    }

    public void setAttributeId(BigDecimal attributeId) {
      this.attributeId = attributeId;
    }

    public String getAttributeAlias() {
      return attributeAlias;
    }

    public void setAttributeAlias(String attributeAlias) {
      this.attributeAlias = attributeAlias;
    }

    public String getAttributeLabel() {
      return attributeLabel;
    }

    public void setAttributeLabel(String attributeLabel) {
      this.attributeLabel = attributeLabel;
    }

    public BigDecimal getAttributeIndex() {
      return attributeIndex;
    }

    public void setAttributeIndex(BigDecimal attributeIndex) {
      this.attributeIndex = attributeIndex;
    }

    public BigDecimal getOrderByIndex() {
      return orderByIndex;
    }

    public void setOrderByIndex(BigDecimal orderByIndex) {
      this.orderByIndex = orderByIndex;
    }

    public Character getDescBoo() {
      return descBoo;
    }

    public void setDescBoo(Character descBoo) {
      this.descBoo = descBoo;
    }

    public BigDecimal getDisplayOption() {
      return displayOption;
    }

    public void setDisplayOption(BigDecimal displayOption) {
      this.displayOption = displayOption;
    }

    public BigDecimal getAttributeType() {
      return attributeType;
    }

    public void setAttributeType(BigDecimal attributeType) {
      this.attributeType = attributeType;
    }

    public String getAttributeName() {
      return attributeName;
    }

    public void setAttributeName(String attributeName) {
      this.attributeName = attributeName;
    }

    public String getAttrAttributeAlias() {
      return attrAttributeAlias;
    }

    public void setAttrAttributeAlias(String attrAttributeAlias) {
      this.attrAttributeAlias = attrAttributeAlias;
    }

    public String getAttrAttributeLabel() {
      return attrAttributeLabel;
    }

    public void setAttrAttributeLabel(String attrAttributeLabel) {
      this.attrAttributeLabel = attrAttributeLabel;
    }

    public String getAttributeDesc() {
      return attributeDesc;
    }

    public void setAttributeDesc(String attributeDesc) {
      this.attributeDesc = attributeDesc;
    }

    public BigDecimal getTableId() {
      return tableId;
    }

    public void setTableId(BigDecimal tableId) {
      this.tableId = tableId;
    }

    public String getColumnName() {
      return columnName;
    }

    public void setColumnName(String columnName) {
      this.columnName = columnName;
    }

    public String getExpression() {
      return expression;
    }

    public void setExpression(String expression) {
      this.expression = expression;
    }

    public BigDecimal getDataType() {
      return dataType;
    }

    public void setDataType(BigDecimal dataType) {
      this.dataType = dataType;
    }

    public String getTableName() {
      return tableName;
    }

    public void setTableName(String tableName) {
      this.tableName = tableName;
    }

    public String getTableAlias() {
      return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
      this.tableAlias = tableAlias;
    }

    public String getTableOwner() {
      return tableOwner;
    }

    public void setTableOwner(String tableOwner) {
      this.tableOwner = tableOwner;
    }

    public String getTableDesc() {
      return tableDesc;
    }

    public void setTableDesc(String tableDesc) {
      this.tableDesc = tableDesc;
    }

  }

  public class CriteriaAttributeInfoResponse extends DBTemplatesResponse<ArrayList<CriteriaAttributeInfo>> {

    @Override
    protected ArrayList<CriteriaAttributeInfo> createResponse() {
      return new ArrayList<>();
    }

  }

  protected class GetCriteriaAttributeInfo extends DBTemplatesExecuteQuery<CriteriaAttributeInfoResponse, UtilityLogger, DBConnectionPools> {

    public GetCriteriaAttributeInfo(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected CriteriaAttributeInfoResponse createResponse() {
      return new CriteriaAttributeInfoResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append(" SELECT ").append(Constants.END_LINE);
      sql.append("     t1.CRITERIA_ATTRIBUTE_ID, t1.ATTRIBUTE_ID, t1.ATTRIBUTE_ALIAS, t1.ATTRIBUTE_LABEL, t1.ATTRIBUTE_INDEX, t1.ORDER_BY_INDEX, t1.DESC_BOO, t1.DISPLAY_OPTION, ").append(Constants.END_LINE);
      sql.append("     t1.ATTRIBUTE_TYPE, t1.ATTRIBUTE_NAME, t1.ATTR_ATTRIBUTE_ALIAS, t1.ATTR_ATTRIBUTE_LABEL, t1.ATTRIBUTE_DESC, t1.TABLE_ID, t1.COLUMN_NAME, t1.EXPRESSION, t1.DATA_TYPE, ").append(Constants.END_LINE);
      sql.append("     t2.TABLE_NAME, t2.TABLE_ALIAS, t2.TABLE_OWNER, t2.TABLE_DESC ").append(Constants.END_LINE);
      sql.append(" from ( ").append(Constants.END_LINE);
      sql.append("   select  ").append(Constants.END_LINE);
      sql.append("     t1.CRITERIA_ATTRIBUTE_ID, t1.ATTRIBUTE_ID, t1.ATTRIBUTE_ALIAS, t1.ATTRIBUTE_LABEL, t1.ATTRIBUTE_INDEX, t1.ORDER_BY_INDEX, t1.DESC_BOO, t1.DISPLAY_OPTION, ").append(Constants.END_LINE);
      sql.append("     t2.ATTRIBUTE_TYPE, t2.ATTRIBUTE_NAME, t2.ATTRIBUTE_ALIAS  ATTR_ATTRIBUTE_ALIAS , t2.ATTRIBUTE_LABEL ATTR_ATTRIBUTE_LABEL , t2.ATTRIBUTE_DESC, t2.TABLE_ID, t2.COLUMN_NAME, t2.EXPRESSION, t2.DATA_TYPE ").append(Constants.END_LINE);
      sql.append("   from ( ").append(Constants.END_LINE);
      sql.append("     SELECT CRITERIA_ATTRIBUTE_ID, ATTRIBUTE_ID, ATTRIBUTE_ALIAS, ATTRIBUTE_LABEL, ATTRIBUTE_INDEX, ORDER_BY_INDEX, DESC_BOO, DISPLAY_OPTION ").append(Constants.END_LINE);
      sql.append("     FROM dbo.CL_CRITERIA_ATTRIBUTE ").append(Constants.END_LINE);
      sql.append("     WHERE CRITERIA_ID = ").append(criteriaId.toPlainString()).append(" and getdate() between RECORD_START_DTM and isnull(RECORD_END_DTM ,getdate()) ").append(Constants.END_LINE);
      sql.append("   ) t1 left join ( ").append(Constants.END_LINE);
      sql.append("     SELECT ATTRIBUTE_ID, ATTRIBUTE_TYPE, ATTRIBUTE_NAME, ATTRIBUTE_ALIAS, ATTRIBUTE_LABEL, ATTRIBUTE_DESC, TABLE_ID, COLUMN_NAME, EXPRESSION, DATA_TYPE ").append(Constants.END_LINE);
      sql.append("     FROM dbo.CL_ATTRIBUTE ").append(Constants.END_LINE);
      sql.append("     WHERE RECORD_STATUS = 1 ").append(Constants.END_LINE);
      sql.append("   ) t2 on t1.ATTRIBUTE_ID = t2.ATTRIBUTE_ID  ").append(Constants.END_LINE);
      sql.append(" )t1 left join ( ").append(Constants.END_LINE);
      sql.append("   SELECT TABLE_ID, TABLE_NAME, TABLE_ALIAS, TABLE_OWNER, TABLE_DESC ").append(Constants.END_LINE);
      sql.append("   FROM dbo.CL_TABLE ").append(Constants.END_LINE);
      sql.append("   WHERE RECORD_STATUS = 1 ").append(Constants.END_LINE);
      sql.append(" )t2 on t1.TABLE_ID = t2.TABLE_ID ").append(Constants.END_LINE);
      sql.append(" order by ORDER_BY_INDEX ").append(Constants.END_LINE);
      return sql;
    }

    private BigDecimal criteriaId;

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {

      CriteriaAttributeInfo temp = new CriteriaAttributeInfo();
      temp.setCriteriaAttributeId(resultSet.getBigDecimal("CRITERIA_ATTRIBUTE_ID"));
      //temp.setCriteriaId(resultSet.getBigDecimal("CRITERIA_ID"));
      temp.setAttributeId(resultSet.getBigDecimal("ATTRIBUTE_ID"));
      temp.setAttributeAlias(resultSet.getString("ATTRIBUTE_ALIAS"));
      temp.setAttributeLabel(resultSet.getString("ATTRIBUTE_LABEL"));
      temp.setAttributeIndex(resultSet.getBigDecimal("ATTRIBUTE_INDEX"));
      temp.setOrderByIndex(resultSet.getBigDecimal("ORDER_BY_INDEX"));
      temp.setDescBoo(toCharcter(resultSet.getString("DESC_BOO"), 'N'));
      temp.setDisplayOption(resultSet.getBigDecimal("DISPLAY_OPTION"));
      temp.setAttributeType(resultSet.getBigDecimal("ATTRIBUTE_TYPE"));
      temp.setAttributeName(resultSet.getString("ATTRIBUTE_NAME"));
      temp.setAttrAttributeAlias(resultSet.getString("ATTR_ATTRIBUTE_ALIAS"));
      temp.setAttrAttributeLabel(resultSet.getString("ATTR_ATTRIBUTE_LABEL"));
      temp.setAttributeDesc(resultSet.getString("ATTRIBUTE_DESC"));
      temp.setTableId(resultSet.getBigDecimal("TABLE_ID"));
      temp.setColumnName(resultSet.getString("COLUMN_NAME"));
      temp.setExpression(resultSet.getString("EXPRESSION"));
      temp.setDataType(resultSet.getBigDecimal("DATA_TYPE"));
      temp.setTableName(resultSet.getString("TABLE_NAME"));
      temp.setTableAlias(resultSet.getString("TABLE_ALIAS"));
      temp.setTableOwner(resultSet.getString("TABLE_OWNER"));
      temp.setTableDesc(resultSet.getString("TABLE_DESC"));

      response.getResponse().add(temp);

    }

    protected CriteriaAttributeInfoResponse execute(BigDecimal criteriaId) {
      this.criteriaId = criteriaId;
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public CriteriaAttributeInfoResponse getCriteriaAttribute(BigDecimal batchTypeId) {
    return new GetCriteriaAttributeInfo(logger).execute(batchTypeId);
  }

  //</editor-fold>
  //  
}
