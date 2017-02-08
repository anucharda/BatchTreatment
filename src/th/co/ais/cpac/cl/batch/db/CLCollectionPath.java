/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.db;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import th.co.ais.cpac.cl.batch.Constants;
import th.co.ais.cpac.cl.batch.cnf.CNFDatabase;
import th.co.ais.cpac.cl.common.Context;
import th.co.ais.cpac.cl.common.UtilityLogger;
import th.co.ais.cpac.cl.template.database.DBConnectionPools;
import th.co.ais.cpac.cl.template.database.DBTemplateCallableStatement;
import th.co.ais.cpac.cl.template.database.DBTemplatesResponse;

/**
 *
 * @author Optimus
 */
public class CLCollectionPath {

  protected final UtilityLogger logger;

  public CLCollectionPath(UtilityLogger logger) {
    this.logger = logger;
  }

  //<editor-fold defaultstate="collapsed" desc="Get List Path Active">
  public class PathInfo {

    // t1.COLLECTION_PATH_ID , t1.PRIMARY_BOO , t2.CRITERIA_ID,t2.CRITERIA_PRIORITY ,t2.LAST_UPD
    public PathInfo() {
    }
    private BigDecimal pathId;
    private Character primaryBoo;

    private BigDecimal criteriaId;
    private BigDecimal criteriaPriority;
    private Long lastUpdateCriteria;

    public BigDecimal getPathId() {
      return pathId;
    }

    public void setPathId(BigDecimal pathId) {
      this.pathId = pathId;
    }

    public BigDecimal getCriteriaId() {
      return criteriaId;
    }

    public void setCriteriaId(BigDecimal criteriaId) {
      this.criteriaId = criteriaId;
    }

    public Character getPrimaryBoo() {
      return primaryBoo;
    }

    public void setPrimaryBoo(Character primaryBoo) {
      this.primaryBoo = primaryBoo;
    }

    public BigDecimal getCriteriaPriority() {
      return criteriaPriority;
    }

    public void setCriteriaPriority(BigDecimal criteriaPriority) {
      this.criteriaPriority = criteriaPriority;
    }

    public Long getLastUpdateCriteria() {
      return lastUpdateCriteria;
    }

    public void setLastUpdateCriteria(Long lastUpdateCriteria) {
      this.lastUpdateCriteria = lastUpdateCriteria;
    }

    @Override
    public String toString() {
      return "PathInfo{" + "pathId=" + pathId + ", primaryBoo=" + primaryBoo + ", criteriaId=" + criteriaId + ", criteriaPriority=" + criteriaPriority + ", lastUpdateCriteria=" + lastUpdateCriteria + '}';
    }

  }

  public class PathInfoResponse extends DBTemplatesResponse<ArrayList<PathInfo>> {

    @Override
    protected ArrayList<PathInfo> createResponse() {
      return new ArrayList<>();
    }
  }

  protected class QueryListPathInfo extends DBTemplateCallableStatement<PathInfoResponse, UtilityLogger, DBConnectionPools> {

    public QueryListPathInfo(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected void setParameter() throws SQLException {
      statement.setQueryTimeout(1800);
      statement.registerOutParameter(1, java.sql.Types.INTEGER);
    }

    @Override
    protected void setReturnValue() throws SQLException {
      if (resultSet != null) {
        while (resultSet.next()) {
          countRecord();
          PathInfo temp = new PathInfo();
          temp.setPathId(resultSet.getBigDecimal("COLLECTION_PATH_ID"));
          temp.setPrimaryBoo(toCharcter(resultSet.getString("PRIMARY_BOO"), 'Y'));
          temp.setCriteriaId(resultSet.getBigDecimal("CRITERIA_ID"));
          temp.setCriteriaPriority(resultSet.getBigDecimal("CRITERIA_PRIORITY"));
          // logger.debug(resultSet.getDate("LAST_UPD").toString());
          // logger.debug(resultSet.getTimestamp("LAST_UPD").toString());
          temp.setLastUpdateCriteria(toLong(resultSet.getTimestamp("LAST_UPD"), "yyyyMMddHHmmssSSS", null));
          response.getResponse().add(temp);
        }
      }
      Integer code = (Integer) statement.getObject(1);
      //logger.debug("code = " + code);
    }

    @Override
    protected PathInfoResponse createResponse() {
      return new PathInfoResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      return new StringBuilder("{call dbo.CL_TM_GET_PATH (?)}");
    }

    protected PathInfoResponse query() {
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public PathInfoResponse queryListPathInfo() {
    return new QueryListPathInfo(logger).query();
  }

  //</editor-fold>
  //
  public static void main(String[] args) {
    long k = System.currentTimeMillis();
    CNFDatabase cnf = new CNFDatabase("D:\\Work\\Ais\\Apps\\Batch\\configuration\\database.properties");

    Context ctx = new Context();
    ctx.initailLogger("LoggerMasterBatchInfo", "XXXX|YYYYY");

    new DBConnectionPools<>(cnf, ctx.getLogger()).buildeDataSource();

    CLCollectionPath mmmm = new CLCollectionPath(ctx.getLogger());
    PathInfoResponse mm = mmmm.queryListPathInfo();
    ctx.getLogger().debug("record = " + mm.info());
    ctx.getLogger().debug(mm.info().toString());
    for (int i = 0; i < mm.getRecordCount(); i++) {
      ctx.getLogger().debug(mm.getResponse().get(i).getPathId().toPlainString());
    }

    new DBConnectionPools<>(cnf, ctx.getLogger()).closeDataSource();
    ctx.getLogger().debug("time : " + (System.currentTimeMillis() - k));
  }

}

/*

  protected class QueryListPathInfo extends DBTemplatesExecuteQuery<PathInfoResponse, UtilityLogger> {

    public QueryListPathInfo(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected void setReturnValue(ResultSet rs) throws SQLException {
      PathInfo temp = new PathInfo();
      temp.setPathId(rs.getBigDecimal("COLLECTION_PATH_ID"));
      temp.setCriteriaId(rs.getBigDecimal("CRITERIA_ID"));
      response.getResponse().add(temp);
    }

    @Override
    protected PathInfoResponse createResponse() {
      return new PathInfoResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append(" select t1.COLLECTION_PATH_ID ,t1.COLLECTION_PATH_OWNER , t2.COLLECTION_CRITERIA_ID , t2.CRITERIA_ID , t2.CRITERIA_PRIORITY , t3.CRITERIA_OWNER , t3.CRITERIA_GROUP , t3.CRITERIA_TYPE , t3.RECORD_STATUS");
      sql.append(" from (");
      sql.append("    select COLLECTION_PATH_ID , COLLECTION_PATH_OWNER");
      sql.append("    from CL_COLLECTION_PATH ");
      sql.append("    where   getdate() between EFFECT_START_DATE and isnull(EFFECT_END_DATE,getdate())");
      sql.append(" ) t1 ,  (");
      sql.append("    select COLLECTION_CRITERIA_ID , COLLECTION_PATH_ID , CRITERIA_ID, CRITERIA_PRIORITY");
      sql.append("    from CL_COLLECTION_CRITERIA ");
      sql.append("    where getdate() between EFFECT_START_DATE and isnull(EFFECT_END_DATE,getdate())");
      sql.append(" ) t2  , (");
      sql.append("    SELECT CRITERIA_ID, CRITERIA_OWNER, CRITERIA_GROUP, CRITERIA_TYPE,   RECORD_STATUS ");
      sql.append("    FROM CL_CRITERIA");
      sql.append("    where RECORD_STATUS = 1");
      sql.append(" ) t3");
      sql.append(" where t1.COLLECTION_PATH_ID = t2.COLLECTION_PATH_ID and t2.CRITERIA_ID = t3.CRITERIA_ID");
      return sql;
    }

    protected PathInfoResponse query() {
      return executeQuery(getConnection(), true);
    }
  }

 */
