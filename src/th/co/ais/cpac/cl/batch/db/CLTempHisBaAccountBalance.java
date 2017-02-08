/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.db;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import th.co.ais.cpac.cl.batch.Constants;
import th.co.ais.cpac.cl.batch.cnf.CNFDatabase;
import th.co.ais.cpac.cl.common.Context;
import th.co.ais.cpac.cl.common.UtilityLogger;
import th.co.ais.cpac.cl.template.database.DBConnectionPools;
import th.co.ais.cpac.cl.template.database.DBTemplateCallableStatement;
import th.co.ais.cpac.cl.template.database.DBTemplatesExecuteUpdate;
import th.co.ais.cpac.cl.template.database.DBTemplatesResponse;
import th.co.ais.cpac.cl.template.database.DBTemplatesUpdate;

/**
 *
 * @author Optimus
 */
public class CLTempHisBaAccountBalance {

  protected final UtilityLogger logger;

  private DBConnectionPools<CNFDatabase, UtilityLogger> connection;

  public boolean buildConnection() {
    CNFDatabase cnf = new CNFDatabase();
    connection = new DBConnectionPools<>(cnf, logger);
    connection.getConnection();
    return connection.ready();
  }

  public DBConnectionPools getDBConnection() {
    return connection;
  }

  public CLTempHisBaAccountBalance(UtilityLogger logger) {
    this.logger = logger;
  }

  public class ExecuteResponse extends DBTemplatesResponse<Boolean> {

    @Override
    protected Boolean createResponse() {
      return false;
    }

    public void setResponse(Boolean boo) {
      response = boo;
    }
  }

  //<editor-fold defaultstate="collapsed" desc="TruncateTable">
  protected class TruncateTable extends DBTemplateCallableStatement<ExecuteResponse, UtilityLogger, DBConnectionPools> {

    public TruncateTable(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected void setParameter() throws SQLException {
      statement.setQueryTimeout(1800);
      statement.registerOutParameter(1, java.sql.Types.INTEGER);
    }

    @Override
    protected void setReturnValue() throws SQLException {
      Integer code = (Integer) statement.getObject(1);
      //logger.debug("code = " + code);
      response.setResponse(Boolean.TRUE);
      countRecord();
    }

    @Override
    protected ExecuteResponse createResponse() {
      return new ExecuteResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      return new StringBuilder("{call CL_TM_CLEAR_TEMP (?)}");
    }

    protected ExecuteResponse truncate() {
      return execute(Constants.getDBConnectionPools(logger), true);
    }

  }

  public ExecuteResponse truncate() {
    return new TruncateTable(logger).truncate();
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="CopyPath">
  protected class CopyPath extends DBTemplatesUpdate<ExecuteResponse, UtilityLogger, DBConnectionPools> {

    private StringBuffer sqlPath;

    public CopyPath(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append("INSERT INTO CL_TEMP_CAL_TREATMENT( ");
      sql.append("CA_NO, ");
      sql.append("SA_NO, ");
      sql.append("BA_NO, ");
      sql.append("COLLECTION_PATH_ID_CURR,  ");
      sql.append("PRIORITY_STATUS_ID,  ");
      sql.append("PRIORITY_STATUS_DATE,  ");
      sql.append("BILL_ZIPCODE,  ");
      sql.append("BILL_PROVINCE_ID,  ");
      sql.append("BILL_REGION_ID,  ");
      sql.append("BA_ACCOUNT_BALANCE_ID,  ");
      sql.append("AR_MOVEMENT_ID,  ");
      sql.append("MIN_DUE_DATE,  ");

      sql.append("COLLECTION_PATH_ID , ");
      sql.append("P_PRIMARY_BOO, ");
      sql.append("P_CRITERIA_ID, ");
      sql.append("P_CRITERIA_PRIORITY, ");
      sql.append("P_LAST_UPD");
      sql.append(") ");
      sql.append(sqlPath);
      return sql;
    }

    @Override
    protected ExecuteResponse createResponse() {
      return new ExecuteResponse();
    }

    protected ExecuteResponse copy(StringBuffer sql) {
      sqlPath = sql;
      return executeUpdate(Constants.getDBConnectionPools(logger), true);
    }

  }

  public ExecuteResponse copyPath(StringBuffer sql) {
    return new CopyPath(logger).copy(sql);
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="Update Path For running">
  protected class CountAndFindPathRunning extends DBTemplateCallableStatement<ExecuteResponse, UtilityLogger, DBConnectionPools> {

    public CountAndFindPathRunning(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected void setParameter() throws SQLException {
      statement.setQueryTimeout(1800);
      statement.registerOutParameter(1, java.sql.Types.INTEGER);
    }

    @Override
    protected void setReturnValue() throws SQLException {
      Integer code = (Integer) statement.getObject(1);
      //logger.debug("code = " + code);
      response.setResponse(Boolean.TRUE);
      countRecord();
    }

    @Override
    protected ExecuteResponse createResponse() {
      return new ExecuteResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      return new StringBuilder("{call dbo.CL_TM_COUNT_UPDATE_PATH_RUNNING (?)}");
    }

    protected ExecuteResponse update() {
      return execute(Constants.getDBConnectionPools(logger), true);
    }

  }

  public ExecuteResponse updatePathRunning() {
    return new CountAndFindPathRunning(logger).update();
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="Get List Account For treatment">
  public class TempAccountBalance {

    public TempAccountBalance() {
    }

    private BigDecimal transId;
    private BigDecimal runningId;
    private BigDecimal runningStts;
    private BigDecimal collectionPathId;
    private Character pathPrimaryBoo;
    private BigDecimal pathCriteriaId;
    private BigDecimal pathCriteriaPriority;
    private BigDecimal pathLastUpd;
    private BigDecimal pathCounter;
    private BigDecimal pathRunning;
    private BigDecimal actionId;
    private BigDecimal actionPathId;
    private Date actionPathDtm;
    private Date actionScheduleDtm;
    private String criteriaId;
    private String actionCriteriaId;
    private Constants.ActionStatus actionStatus;
    private Date actionStatusDtm;
    private String acUnqualifyBoo;
    private String caNumber;
    private String saNumber;
    private String baNumber;
    private BigDecimal collectionPathIdCurr;
    private BigDecimal priorityStatusId;
    private Date priorityStatusDate;
    private String billZipcode;
    private BigDecimal billProvinceId;
    private BigDecimal billRegionId;
    private BigDecimal baAccountBalanceId;
    private BigDecimal arMovementId;
    private Date minDueDate;
    private BigDecimal minDueDateCount;
    private BigDecimal priorityStatusDateCount;
    private Constants.PriorityStatusGroup priorityStatusGroup;
    private Constants.PriorityStatusGroupStatus priorityStatusGroupStatus;

    private BigDecimal criteriaIdRun;
    private BigDecimal actionCriteriaIdRun;
    private Character acUnqualifyBooRun;

    private BigDecimal countCriteriaId;

    public BigDecimal getTransId() {
      return transId;
    }

    public void setTransId(BigDecimal transId) {
      this.transId = transId;
    }

    public BigDecimal getRunningId() {
      return runningId;
    }

    public void setRunningId(BigDecimal runningId) {
      this.runningId = runningId;
    }

    public BigDecimal getRunningStts() {
      return runningStts;
    }

    public void setRunningStts(BigDecimal runningStts) {
      this.runningStts = runningStts;
    }

    public BigDecimal getCollectionPathId() {
      return collectionPathId;
    }

    public void setCollectionPathId(BigDecimal collectionPathId) {
      this.collectionPathId = collectionPathId;
    }

    public Character getPathPrimaryBoo() {
      return pathPrimaryBoo;
    }

    public void setPathPrimaryBoo(Character pathPrimaryBoo) {
      this.pathPrimaryBoo = pathPrimaryBoo;
    }

    public BigDecimal getPathCriteriaId() {
      return pathCriteriaId;
    }

    public void setPathCriteriaId(BigDecimal pathCriteriaId) {
      this.pathCriteriaId = pathCriteriaId;
    }

    public BigDecimal getPathCriteriaPriority() {
      return pathCriteriaPriority;
    }

    public void setPathCriteriaPriority(BigDecimal pathCriteriaPriority) {
      this.pathCriteriaPriority = pathCriteriaPriority;
    }

    public BigDecimal getPathLastUpd() {
      return pathLastUpd;
    }

    public void setPathLastUpd(BigDecimal pathLastUpd) {
      this.pathLastUpd = pathLastUpd;
    }

    public BigDecimal getPathCounter() {
      return pathCounter;
    }

    public void setPathCounter(BigDecimal pathCounter) {
      this.pathCounter = pathCounter;
    }

    public BigDecimal getPathRunning() {
      return pathRunning;
    }

    public void setPathRunning(BigDecimal pathRunning) {
      this.pathRunning = pathRunning;
    }

    public BigDecimal getActionId() {
      return actionId;
    }

    public void setActionId(BigDecimal actionId) {
      this.actionId = actionId;
    }

    public BigDecimal getActionPathId() {
      return actionPathId;
    }

    public void setActionPathId(BigDecimal actionPathId) {
      this.actionPathId = actionPathId;
    }

    public Date getActionPathDtm() {
      return actionPathDtm;
    }

    public void setActionPathDtm(Date actionPathDtm) {
      this.actionPathDtm = actionPathDtm;
    }

    public Date getActionScheduleDtm() {
      return actionScheduleDtm;
    }

    public void setActionScheduleDtm(Date actionScheduleDtm) {
      this.actionScheduleDtm = actionScheduleDtm;
    }

    public String getCriteriaId() {
      return criteriaId;
    }

    public void setCriteriaId(String criteriaId) {
      this.criteriaId = criteriaId;
    }

    public String getActionCriteriaId() {
      return actionCriteriaId;
    }

    public void setActionCriteriaId(String actionCriteriaId) {
      this.actionCriteriaId = actionCriteriaId;
    }

    public Constants.ActionStatus getActionStatus() {
      return actionStatus;
    }

    public void setActionStatus(Constants.ActionStatus actionStatus) {
      this.actionStatus = actionStatus;
    }

    public Date getActionStatusDtm() {
      return actionStatusDtm;
    }

    public void setActionStatusDtm(Date actionStatusDtm) {
      this.actionStatusDtm = actionStatusDtm;
    }

    public String getAcUnqualifyBoo() {
      return acUnqualifyBoo;
    }

    public void setAcUnqualifyBoo(String acUnqualifyBoo) {
      this.acUnqualifyBoo = acUnqualifyBoo;
    }

    public BigDecimal getCriteriaIdRun() {
      return criteriaIdRun;
    }

    public void setCriteriaIdRun(BigDecimal criteriaIdRun) {
      this.criteriaIdRun = criteriaIdRun;
    }

    public BigDecimal getActionCriteriaIdRun() {
      return actionCriteriaIdRun;
    }

    public void setActionCriteriaIdRun(BigDecimal actionCriteriaIdRun) {
      this.actionCriteriaIdRun = actionCriteriaIdRun;
    }

    public Character getAcUnqualifyBooRun() {
      return acUnqualifyBooRun;
    }

    public void setAcUnqualifyBooRun(Character acUnqualifyBooRun) {
      this.acUnqualifyBooRun = acUnqualifyBooRun;
    }

    public String getCaNumber() {
      return caNumber;
    }

    public void setCaNumber(String caNumber) {
      this.caNumber = caNumber;
    }

    public String getSaNumber() {
      return saNumber;
    }

    public void setSaNumber(String saNumber) {
      this.saNumber = saNumber;
    }

    public String getBaNumber() {
      return baNumber;
    }

    public void setBaNumber(String baNumber) {
      this.baNumber = baNumber;
    }

    public BigDecimal getCollectionPathIdCurr() {
      return collectionPathIdCurr;
    }

    public void setCollectionPathIdCurr(BigDecimal collectionPathIdCurr) {
      this.collectionPathIdCurr = collectionPathIdCurr;
    }

    public BigDecimal getPriorityStatusId() {
      return priorityStatusId;
    }

    public void setPriorityStatusId(BigDecimal priorityStatusId) {
      this.priorityStatusId = priorityStatusId;
    }

    public Date getPriorityStatusDate() {
      return priorityStatusDate;
    }

    public void setPriorityStatusDate(Date priorityStatusDate) {
      this.priorityStatusDate = priorityStatusDate;
    }

    public String getBillZipcode() {
      return billZipcode;
    }

    public void setBillZipcode(String billZipcode) {
      this.billZipcode = billZipcode;
    }

    public BigDecimal getBillProvinceId() {
      return billProvinceId;
    }

    public void setBillProvinceId(BigDecimal billProvinceId) {
      this.billProvinceId = billProvinceId;
    }

    public BigDecimal getBillRegionId() {
      return billRegionId;
    }

    public void setBillRegionId(BigDecimal billRegionId) {
      this.billRegionId = billRegionId;
    }

    public BigDecimal getBaAccountBalanceId() {
      return baAccountBalanceId;
    }

    public void setBaAccountBalanceId(BigDecimal baAccountBalanceId) {
      this.baAccountBalanceId = baAccountBalanceId;
    }

    public BigDecimal getArMovementId() {
      return arMovementId;
    }

    public void setArMovementId(BigDecimal arMovementId) {
      this.arMovementId = arMovementId;
    }

    public Date getMinDueDate() {
      return minDueDate;
    }

    public void setMinDueDate(Date minDueDate) {
      this.minDueDate = minDueDate;
    }

    public BigDecimal getMinDueDateCount() {
      return minDueDateCount;
    }

    public void setMinDueDateCount(BigDecimal minDueDateCount) {
      this.minDueDateCount = minDueDateCount;
    }

    public BigDecimal getPriorityStatusDateCount() {
      return priorityStatusDateCount;
    }

    public void setPriorityStatusDateCount(BigDecimal priorityStatusDateCount) {
      this.priorityStatusDateCount = priorityStatusDateCount;
    }

    public Constants.PriorityStatusGroup getPriorityStatusGroup() {
      return priorityStatusGroup;
    }

    public void setPriorityStatusGroup(Constants.PriorityStatusGroup priorityStatusGroup) {
      this.priorityStatusGroup = priorityStatusGroup;
    }

    public Constants.PriorityStatusGroupStatus getPriorityStatusGroupStatus() {
      return priorityStatusGroupStatus;
    }

    public void setPriorityStatusGroupStatus(Constants.PriorityStatusGroupStatus priorityStatusGroupStatus) {
      this.priorityStatusGroupStatus = priorityStatusGroupStatus;
    }

    public BigDecimal getCountCriteriaId() {
      return countCriteriaId;
    }

    public void setCountCriteriaId(BigDecimal countCriteriaId) {
      this.countCriteriaId = countCriteriaId;
    }

    @Override
    public String toString() {
      return "TempAccountBalance{" + "transId=" + transId + ", runningId=" + runningId + ", runningStts=" + runningStts + ", collectionPathId=" + collectionPathId + ", pathPrimaryBoo=" + pathPrimaryBoo + ", pathCriteriaId=" + pathCriteriaId + ", pathCriteriaPriority=" + pathCriteriaPriority + ", pathLastUpd=" + pathLastUpd + ", pathCounter=" + pathCounter + ", pathRunning=" + pathRunning + ", actionId=" + actionId + ", actionPathId=" + actionPathId + ", actionPathDtm=" + actionPathDtm + ", actionScheduleDtm=" + actionScheduleDtm + ", criteriaId=" + criteriaId + ", actionCriteriaId=" + actionCriteriaId + ", actionStatus=" + actionStatus + ", actionStatusDtm=" + actionStatusDtm + ", acUnqualifyBoo=" + acUnqualifyBoo + ", caNumber=" + caNumber + ", saNumber=" + saNumber + ", baNumber=" + baNumber + ", collectionPathIdCurr=" + collectionPathIdCurr + ", priorityStatusId=" + priorityStatusId + ", priorityStatusDate=" + priorityStatusDate + ", billZipcode=" + billZipcode + ", billProvinceId=" + billProvinceId + ", billRegionId=" + billRegionId + ", baAccountBalanceId=" + baAccountBalanceId + ", arMovementId=" + arMovementId + ", minDueDate=" + minDueDate + ", minDueDateCount=" + minDueDateCount + ", priorityStatusDateCount=" + priorityStatusDateCount + ", priorityStatusGroup=" + priorityStatusGroup + ", priorityStatusGroupStatus=" + priorityStatusGroupStatus + ", criteriaIdRun=" + criteriaIdRun + ", actionCriteriaIdRun=" + actionCriteriaIdRun + ", acUnqualifyBooRun=" + acUnqualifyBooRun + ", countCriteriaId=" + countCriteriaId + '}';
    }

  }

  public class TempAccountBalanceResponse extends DBTemplatesResponse<ArrayList<TempAccountBalance>> {

    @Override
    protected ArrayList<TempAccountBalance> createResponse() {
      return new ArrayList<>();
    }
  }

  protected class GetListDriveAction extends DBTemplateCallableStatement<TempAccountBalanceResponse, UtilityLogger, DBConnectionPools> {

    private BigDecimal pathId;
    private BigDecimal runningId;

    public GetListDriveAction(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected void setParameter() throws SQLException {
      statement.setQueryTimeout(1800);
      statement.setBigDecimal(1, pathId);
      statement.setBigDecimal(2, runningId);
      statement.registerOutParameter(3, java.sql.Types.INTEGER);
    }

    @Override
    protected void setReturnValue() throws SQLException {
      if (resultSet != null) {
        while (resultSet.next()) {
          countRecord();
          TempAccountBalance temp = new TempAccountBalance();
          temp.setTransId(resultSet.getBigDecimal("TRANS_ID"));
          temp.setRunningId(resultSet.getBigDecimal("RUNNING_ID"));

          temp.setCollectionPathId(resultSet.getBigDecimal("COLLECTION_PATH_ID"));
          temp.setPathPrimaryBoo(toCharcter(resultSet.getString("P_PRIMARY_BOO"), 'N'));
          temp.setPathCriteriaId(resultSet.getBigDecimal("P_CRITERIA_ID"));
          temp.setPathCriteriaPriority(resultSet.getBigDecimal("P_CRITERIA_PRIORITY"));
          temp.setPathLastUpd(resultSet.getBigDecimal("P_LAST_UPD"));
          temp.setPathCounter(resultSet.getBigDecimal("P_COUNTER"));
          temp.setPathRunning(resultSet.getBigDecimal("P_RUNNING"));

          temp.setBaNumber(resultSet.getString("BA_NO"));
          temp.setCaNumber(resultSet.getString("CA_NO"));
          temp.setSaNumber(resultSet.getString("SA_NO"));
          temp.setCollectionPathIdCurr(resultSet.getBigDecimal("COLLECTION_PATH_ID_CURR"));
          temp.setPriorityStatusId(resultSet.getBigDecimal("PRIORITY_STATUS_ID"));
          temp.setPriorityStatusGroup(Constants.mapPriorityStatusGroup(toInteger(resultSet.getBigDecimal("PRIORITY_STATUS_GROUP"), -9999)));
          temp.setPriorityStatusGroupStatus(Constants.mapPriorityStatusGroupStatus(toInteger(resultSet.getBigDecimal("GROUP_STATUS"), -9999)));
          temp.setPriorityStatusDate(toDate(resultSet.getDate("PRIORITY_STATUS_DATE"), null));
          temp.setBillZipcode(resultSet.getString("BILL_ZIPCODE"));
          temp.setBillProvinceId(resultSet.getBigDecimal("BILL_PROVINCE_ID"));
          temp.setBillRegionId(resultSet.getBigDecimal("BILL_REGION_ID"));
          temp.setBaAccountBalanceId(resultSet.getBigDecimal("BA_ACCOUNT_BALANCE_ID"));
          temp.setArMovementId(resultSet.getBigDecimal("AR_MOVEMENT_ID"));
          temp.setMinDueDate(toDate(resultSet.getTimestamp("MIN_DUE_DATE"), null));

          temp.setMinDueDateCount(resultSet.getBigDecimal("MIN_DUE_DATE_COUNT"));
          temp.setPriorityStatusDateCount(resultSet.getBigDecimal("PRIORITY_STATUS_DATE_COUNT"));

          //logger.debug(temp.getRunningId().toString());
          response.getResponse().add(temp);
        }
      }
      Integer code = (Integer) statement.getObject(3);
      //logger.debug("code = " + code);
    }

    @Override
    protected TempAccountBalanceResponse createResponse() {
      return new TempAccountBalanceResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      return new StringBuilder("{call CL_TM_GET_DATA_DRIVE_ACTION (?, ?, ?)}");

    }

    protected TempAccountBalanceResponse query(BigDecimal pathId, BigDecimal runningId) {
      this.pathId = pathId;
      this.runningId = runningId;
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public TempAccountBalanceResponse queryTempAccount(BigDecimal pathId, BigDecimal runningId) {
    return new GetListDriveAction(logger).query(pathId, runningId);
  }

  public class TreatmentResponse extends DBTemplatesResponse< HashMap<String, ArrayList<TempAccountBalance>>> {

    @Override
    protected HashMap<String, ArrayList<TempAccountBalance>> createResponse() {
      return new HashMap<>();
    }
  }

  protected class GetTreatment extends DBTemplateCallableStatement<TreatmentResponse, UtilityLogger, DBConnectionPools> {

    private BigDecimal runningId;

    public GetTreatment(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected void setParameter() throws SQLException {
      statement.setQueryTimeout(1800);
      statement.setBigDecimal(1, runningId);
      statement.registerOutParameter(2, java.sql.Types.INTEGER);
    }

    @Override
    protected void setReturnValue() throws SQLException {
      if (resultSet != null) {
        while (resultSet.next()) {
          TempAccountBalance temp = new TempAccountBalance();

          temp.setTransId(resultSet.getBigDecimal("TRANS_ID"));
          temp.setRunningId(resultSet.getBigDecimal("RUNNING_ID"));
          temp.setRunningStts(resultSet.getBigDecimal("RUNNING_STTS"));
          temp.setCollectionPathId(resultSet.getBigDecimal("COLLECTION_PATH_ID"));

          temp.setPathPrimaryBoo(toCharcter(resultSet.getString("P_PRIMARY_BOO"), 'N'));
          temp.setPathCriteriaId(resultSet.getBigDecimal("P_CRITERIA_ID"));
          temp.setPathCriteriaPriority(resultSet.getBigDecimal("P_CRITERIA_PRIORITY"));
          temp.setPathLastUpd(resultSet.getBigDecimal("P_LAST_UPD"));
          temp.setPathCounter(resultSet.getBigDecimal("P_COUNTER"));
          temp.setPathRunning(resultSet.getBigDecimal("P_RUNNING"));

          temp.setActionId(resultSet.getBigDecimal("ACTION_ID"));
          temp.setActionPathId(resultSet.getBigDecimal("ACTION_PATH_ID"));
          temp.setActionPathDtm(toDate(resultSet.getTimestamp("ACTION_PATH_DTM"), null));
          temp.setActionScheduleDtm(toDate(resultSet.getTimestamp("ACTION_SCHEDULE_DTM"), null));
          temp.setCriteriaId(resultSet.getString("CRITERIA_ID"));
          temp.setActionCriteriaId(resultSet.getString("ACTION_CRITERIA_ID"));
          temp.setActionStatus(Constants.mapActionStatus(resultSet.getBigDecimal("ACTION_STATUS")));
          temp.setActionStatusDtm(toDate(resultSet.getTimestamp("ACTION_STATUS_DTM"), null));
          temp.setAcUnqualifyBoo(resultSet.getString("AC_UNQUALIFY_BOO"));
          temp.setCountCriteriaId(resultSet.getBigDecimal("COUNTER_CRITERIA_ID"));
          temp.setCaNumber(resultSet.getString("CA_NO"));
          temp.setSaNumber(resultSet.getString("SA_NO"));
          temp.setBaNumber(resultSet.getString("BA_NO"));
          temp.setCollectionPathIdCurr(resultSet.getBigDecimal("COLLECTION_PATH_ID_CURR"));
          temp.setPriorityStatusId(resultSet.getBigDecimal("PRIORITY_STATUS_ID"));
          temp.setPriorityStatusDate(toDate(resultSet.getTimestamp("PRIORITY_STATUS_DATE"), null));
          temp.setBillZipcode(resultSet.getString("BILL_ZIPCODE"));
          temp.setBillProvinceId(resultSet.getBigDecimal("BILL_PROVINCE_ID"));
          temp.setBillRegionId(resultSet.getBigDecimal("BILL_REGION_ID"));
          temp.setBaAccountBalanceId(resultSet.getBigDecimal("BA_ACCOUNT_BALANCE_ID"));
          temp.setArMovementId(resultSet.getBigDecimal("AR_MOVEMENT_ID"));
          temp.setMinDueDate(toDate(resultSet.getTimestamp("MIN_DUE_DATE"), null));

          //logger.debug(temp.getRunningId().toString());
          ArrayList<TempAccountBalance> obj = response.getResponse().get(temp.getBaNumber());
          if (obj == null) {
            countRecord();
            obj = new ArrayList<>();
            obj.add(temp);
            response.getResponse().put(temp.getBaNumber(), obj);
          } else {
            obj.add(temp);
          }

        }
      }
      Integer code = (Integer) statement.getObject(2);
      //logger.debug("code = " + code);
    }

    @Override
    protected TreatmentResponse createResponse() {
      return new TreatmentResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      return new StringBuilder("{call CL_TM_GET_DATA_TO_CL_TREATMENT ( ?, ?)}");

    }

    protected TreatmentResponse query(BigDecimal runningId) {
      this.runningId = runningId;
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public TreatmentResponse queryTempAccount(BigDecimal runningId) {
    return new GetTreatment(logger).query(runningId);
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="GET LIST HISTORY TL NL ACTION">
  public class HistoryActionTLNL {

    private Constants.ActionType actionType;
    private Constants.ActionMode actionMode;
    private Date messageDate;
    private BigDecimal messageDateCount;

    public Constants.ActionType getActionType() {
      return actionType;
    }

    public void setActionType(Constants.ActionType actionType) {
      this.actionType = actionType;
    }

    public Constants.ActionMode getActionMode() {
      return actionMode;
    }

    public void setActionMode(Constants.ActionMode actionMode) {
      this.actionMode = actionMode;
    }

    public Date getMessageDate() {
      return messageDate;
    }

    public void setMessageDate(Date messageDate) {
      this.messageDate = messageDate;
    }

    public BigDecimal getMessageDateCount() {
      return messageDateCount;
    }

    public void setMessageDateCount(BigDecimal messageDateCount) {
      this.messageDateCount = messageDateCount;
    }

  }

  public class HistoryActionTLNLResponse extends DBTemplatesResponse<ArrayList<HistoryActionTLNL>> {

    @Override
    protected ArrayList<HistoryActionTLNL> createResponse() {
      return new ArrayList<>();
    }
  }

  protected class GetListActionTLNL extends DBTemplateCallableStatement<HistoryActionTLNLResponse, UtilityLogger, DBConnectionPools> {

    private String baNumber;
    private String caNumber;
    private Date minDueDate;

    public GetListActionTLNL(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected void setParameter() throws SQLException {
      statement.setQueryTimeout(1800);
      statement.setString(1, caNumber);
      statement.setString(2, baNumber);
      statement.setDate(3, toSqlDate(minDueDate, null));
      statement.registerOutParameter(4, java.sql.Types.INTEGER);
    }

    @Override
    protected void setReturnValue() throws SQLException {
      if (resultSet != null) {
        while (resultSet.next()) {
          countRecord();
          HistoryActionTLNL temp = new HistoryActionTLNL();
          temp.setActionMode(Constants.mapActionMode(resultSet.getBigDecimal("ACTION_MODE").intValue()));
          temp.setActionType(Constants.mapActionType(resultSet.getBigDecimal("ACTION_TYPE").intValue()));
          temp.setMessageDate(toDate(resultSet.getDate("MESSAGE_DATE"), null));
          temp.setMessageDateCount(resultSet.getBigDecimal("MESSAGE_DATE_COUNT"));
          response.getResponse().add(temp);
        }
      }
      Integer code = (Integer) statement.getObject(4);
      //logger.debug("code = " + code);
    }

    @Override
    protected HistoryActionTLNLResponse createResponse() {
      return new HistoryActionTLNLResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      return new StringBuilder("{call CL_TM_GET_HIS_TREATMENT_TL_NL (?,?, ?, ?)}");

    }

    protected HistoryActionTLNLResponse query(String caNumber, String baNumber, Date minDueDate) {
      this.caNumber = caNumber;
      this.baNumber = baNumber;
      this.minDueDate = minDueDate;
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public HistoryActionTLNLResponse queryHistoryTLNL(String caNumber, String baNumber, Date minDueDate) {
    return new GetListActionTLNL(logger).query(caNumber, baNumber, minDueDate);
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="GET LIST HISTORY ACTION">
  public class HistoryAction {

    //t1.TREATMENT_ID ,  t2.ACTION_ID ,t2.ACTION_MODE,  t2.ACTION_TYPE
    private BigDecimal treatmentId;
    private BigDecimal actionId;
    private Constants.ActionType actionType;
    private Constants.ActionMode actionMode;

    public BigDecimal getTreatmentId() {
      return treatmentId;
    }

    public void setTreatmentId(BigDecimal treatmentId) {
      this.treatmentId = treatmentId;
    }

    public BigDecimal getActionId() {
      return actionId;
    }

    public void setActionId(BigDecimal actionId) {
      this.actionId = actionId;
    }

    public Constants.ActionType getActionType() {
      return actionType;
    }

    public void setActionType(Constants.ActionType actionType) {
      this.actionType = actionType;
    }

    public Constants.ActionMode getActionMode() {
      return actionMode;
    }

    public void setActionMode(Constants.ActionMode actionMode) {
      this.actionMode = actionMode;
    }

  }

  public class HistoryActionResponse extends DBTemplatesResponse<ArrayList<HistoryAction>> {

    @Override
    protected ArrayList<HistoryAction> createResponse() {
      return new ArrayList<>();
    }
  }

  protected class GetListHistoryAction extends DBTemplateCallableStatement<HistoryActionResponse, UtilityLogger, DBConnectionPools> {

    private String baNumber;
    private String caNumber;
    private Date minDueDate;

    public GetListHistoryAction(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected void setParameter() throws SQLException {
      statement.setQueryTimeout(1800);
      statement.setString(1, caNumber);
      statement.setString(2, baNumber);
      statement.setDate(3, toSqlDate(minDueDate, null));
      statement.registerOutParameter(4, java.sql.Types.INTEGER);
    }

    @Override
    protected void setReturnValue() throws SQLException {
      if (resultSet != null) {
        while (resultSet.next()) {
          countRecord();
          HistoryAction temp = new HistoryAction();
          temp.setTreatmentId(resultSet.getBigDecimal("TREATMENT_ID"));
          temp.setActionId(resultSet.getBigDecimal("ACTION_ID"));
          temp.setActionMode(Constants.mapActionMode(resultSet.getBigDecimal("ACTION_MODE").intValue()));
          response.getResponse().add(temp);
        }
      }
      Integer code = (Integer) statement.getObject(4);
      //logger.debug("code = " + code);
    }

    @Override
    protected HistoryActionResponse createResponse() {
      return new HistoryActionResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      return new StringBuilder("{call CL_TM_GET_HIS_TREATMENT (?, ?, ?, ?)}");

    }

    protected HistoryActionResponse query(String caNumber, String baNumber, Date minDueDate) {
      this.caNumber = caNumber;
      this.baNumber = baNumber;
      this.minDueDate = minDueDate;
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public HistoryActionResponse queryHistoryAction(String caNumber, String baNumber, Date minDueDate) {
    return new GetListHistoryAction(logger).query(caNumber, baNumber, minDueDate);
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="GET LIST Current Treatment Running (Primary , Optinal)">
  public class CurrentTreatment {

    //t1.TREATMENT_ID ,  t2.ACTION_ID ,t2.ACTION_MODE,  t2.ACTION_TYPE
    private BigDecimal treatmentId;
    private String caNumber;
    private String baNumber;
    private BigDecimal collectionPathId;
    private BigDecimal actionId;
    private BigDecimal actionPathId;
    private Date actionPathDtm;
    private Date actionScheduleDtm;
    private BigDecimal criteriaId;
    private BigDecimal actionCriteriaId;
    private Constants.ActionStatus actionStatus;
    private Date actionStatusDtm;
    private Date minDueDate;
    private Character primaryBoo;

    public BigDecimal getTreatmentId() {
      return treatmentId;
    }

    public void setTreatmentId(BigDecimal treatmentId) {
      this.treatmentId = treatmentId;
    }

    public String getCaNumber() {
      return caNumber;
    }

    public void setCaNumber(String caNumber) {
      this.caNumber = caNumber;
    }

    public String getBaNumber() {
      return baNumber;
    }

    public void setBaNumber(String baNumber) {
      this.baNumber = baNumber;
    }

    public BigDecimal getCollectionPathId() {
      return collectionPathId;
    }

    public void setCollectionPathId(BigDecimal collectionPathId) {
      this.collectionPathId = collectionPathId;
    }

    public BigDecimal getActionId() {
      return actionId;
    }

    public void setActionId(BigDecimal actionId) {
      this.actionId = actionId;
    }

    public BigDecimal getActionPathId() {
      return actionPathId;
    }

    public void setActionPathId(BigDecimal actionPathId) {
      this.actionPathId = actionPathId;
    }

    public Date getActionPathDtm() {
      return actionPathDtm;
    }

    public void setActionPathDtm(Date actionPathDtm) {
      this.actionPathDtm = actionPathDtm;
    }

    public Date getActionScheduleDtm() {
      return actionScheduleDtm;
    }

    public void setActionScheduleDtm(Date actionScheduleDtm) {
      this.actionScheduleDtm = actionScheduleDtm;
    }

    public BigDecimal getCriteriaId() {
      return criteriaId;
    }

    public void setCriteriaId(BigDecimal criteriaId) {
      this.criteriaId = criteriaId;
    }

    public BigDecimal getActionCriteriaId() {
      return actionCriteriaId;
    }

    public void setActionCriteriaId(BigDecimal actionCriteriaId) {
      this.actionCriteriaId = actionCriteriaId;
    }

    public Constants.ActionStatus getActionStatus() {
      return actionStatus;
    }

    public void setActionStatus(Constants.ActionStatus actionStatus) {
      this.actionStatus = actionStatus;
    }

    public Date getActionStatusDtm() {
      return actionStatusDtm;
    }

    public void setActionStatusDtm(Date actionStatusDtm) {
      this.actionStatusDtm = actionStatusDtm;
    }

    public Date getMinDueDate() {
      return minDueDate;
    }

    public void setMinDueDate(Date minDueDate) {
      this.minDueDate = minDueDate;
    }

    public Character getPrimaryBoo() {
      return primaryBoo;
    }

    public void setPrimaryBoo(Character primaryBoo) {
      this.primaryBoo = primaryBoo;
    }

    @Override
    public String toString() {
      return "CurrentTreatment{" + "treatmentId=" + treatmentId + ", caNumber=" + caNumber + ", baNumber=" + baNumber + ", collectionPathId=" + collectionPathId + ", actionId=" + actionId + ", actionPathId=" + actionPathId + ", actionPathDtm=" + actionPathDtm + ", actionScheduleDtm=" + actionScheduleDtm + ", criteriaId=" + criteriaId + ", actionCriteriaId=" + actionCriteriaId + ", actionStatus=" + actionStatus + ", actionStatusDtm=" + actionStatusDtm + ", minDueDate=" + minDueDate + ", primaryBoo=" + primaryBoo + '}';
    }

  }

  public class CurrentTreatmentResponse extends DBTemplatesResponse<ArrayList<CurrentTreatment>> {

    @Override
    protected ArrayList<CurrentTreatment> createResponse() {
      return new ArrayList<>();
    }
  }

  protected class GetListCurrentTreatmentRunning extends DBTemplateCallableStatement<CurrentTreatmentResponse, UtilityLogger, DBConnectionPools> {

    private String baNumber;
    private String caNumber;
    private int caseQuery;

    public GetListCurrentTreatmentRunning(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected void setParameter() throws SQLException {
      statement.setQueryTimeout(1800);
      statement.setString(1, caNumber);
      statement.setString(2, baNumber);

      statement.registerOutParameter(3, java.sql.Types.INTEGER);
    }

    @Override
    protected void setReturnValue() throws SQLException {
      if (resultSet != null) {
        while (resultSet.next()) {
          countRecord();
          CurrentTreatment temp = new CurrentTreatment();
          temp.setTreatmentId(resultSet.getBigDecimal("TREATMENT_ID"));
          temp.setCaNumber(resultSet.getString("CA_NO"));
          temp.setBaNumber(resultSet.getString("BA_NO"));
          temp.setCollectionPathId(resultSet.getBigDecimal("COLLECTION_PATH_ID"));
          temp.setActionId(resultSet.getBigDecimal("ACTION_ID"));
          temp.setActionPathId(resultSet.getBigDecimal("ACTION_PATH_ID"));

          temp.setActionPathDtm(toDate(resultSet.getTimestamp("ACTION_PATH_DTM"), null));
          temp.setActionScheduleDtm(toDate(resultSet.getTimestamp("ACTION_SCHEDULE_DTM"), null));

          temp.setCriteriaId(resultSet.getBigDecimal("CRITERIA_ID"));
          temp.setActionCriteriaId(resultSet.getBigDecimal("ACTION_CRITERIA_ID"));

          temp.setActionStatus(Constants.mapActionStatus(resultSet.getBigDecimal("ACTION_STATUS").intValue()));
          temp.setActionStatusDtm(toDate(resultSet.getTimestamp("ACTION_STATUS_DTM"), null));
          temp.setMinDueDate(toDate(resultSet.getTimestamp("MIN_DUE_DATE"), null));

          temp.setPrimaryBoo(toCharcter(resultSet.getString("PRIMARY_BOO"), 'N'));

          response.getResponse().add(temp);
        }
      }
      Integer code = (Integer) statement.getObject(3);
      //logger.debug("code = " + code);
    }

    @Override
    protected CurrentTreatmentResponse createResponse() {
      return new CurrentTreatmentResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      if (caseQuery == 10) {
        return new StringBuilder("{call CL_TM_GET_CURRENT_PRIMARY_TREATMENT_RUNNING ( ?, ?, ?)}");
      } else if (caseQuery == 20) {
        return new StringBuilder("{call CL_TM_GET_CURRENT_OPTIONAL_TREATMENT_RUNNING ( ?, ?, ?)}");
      }
      return null;
    }

    protected CurrentTreatmentResponse query(String caNumber, String baNumber, int caseQuery) {
      this.caNumber = caNumber;
      this.baNumber = baNumber;
      this.caseQuery = caseQuery;

      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public CurrentTreatmentResponse queryCurrentPrimaryTreatmentRunning(String caNumber, String baNumber) {
    return new GetListCurrentTreatmentRunning(logger).query(caNumber, baNumber, 10);
  }

  //
  public CurrentTreatmentResponse queryCurrentOptionalTreatmentRunning(String caNumber, String baNumber) {
    return new GetListCurrentTreatmentRunning(logger).query(caNumber, baNumber, 20);
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="Update Action value to TEMP Table">
  protected class UpdateActionToTempTable extends DBTemplateCallableStatement<ExecuteResponse, UtilityLogger, DBConnectionPools> {

    private TempAccountBalance baInfo;
    private CLAction.ActionInfo action;
    private Constants.ActionStatus actionstatus;
    private Date jobDate;
    private Date runDate;
    private int copyType;

    public UpdateActionToTempTable(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected void setParameter() throws SQLException {
      statement.setQueryTimeout(1800);
      if (copyType == 2) {
        statement.setBigDecimal(1, baInfo.getTransId());
        statement.setBigDecimal(2, null);
        statement.setBigDecimal(3, null);
        statement.setTimestamp(4, null);
        statement.setTimestamp(5, null);
        statement.setInt(6, Constants.ActionStatus.Failed.getCode());
        statement.setDate(7, null);
        statement.setInt(8, copyType);
      } else {
        statement.setBigDecimal(1, baInfo.getTransId());
        statement.setBigDecimal(2, action.getActionID());
        statement.setBigDecimal(3, action.getActionPathID());
        statement.setTimestamp(4, toSqlTimestamp(jobDate, null));
        statement.setTimestamp(5, toSqlTimestamp(runDate, null));
        statement.setInt(6, Constants.ActionStatus.WaitingtoProcess.getCode());
        statement.setDate(7, toSqlDate(new Date(), null));
        statement.setInt(8, copyType);
      }
      statement.registerOutParameter(9, java.sql.Types.NUMERIC);
      statement.registerOutParameter(10, java.sql.Types.INTEGER);
    }

    @Override
    protected void setReturnValue() throws SQLException {
      BigDecimal identiry = (BigDecimal) statement.getObject(9);      
      response.setIdentity(new BigDecimal(identiry.toBigInteger()));      
      logger.debug("identiry = " + identiry.toPlainString());      
      Integer code = (Integer) statement.getObject(10);
      logger.debug("code = " + code);
      response.setResponse(Boolean.TRUE);
      countRecord();
    }

    @Override
    protected ExecuteResponse createResponse() {
      return new ExecuteResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      logger.debug(baInfo.getTransId() +",");
      return new StringBuilder("{call CL_TM_UPDATE_TEMP (?,?,?,?,?,?,?,?,?,?)}");
    }

    protected ExecuteResponse update(TempAccountBalance baInfo, CLAction.ActionInfo action, Date jobDate, Date runDate, Constants.ActionStatus actionstatus, int copyType) {
      this.baInfo = baInfo;
      this.action = action;
      this.jobDate = jobDate;
      this.runDate = runDate;
      this.actionstatus = actionstatus;
      this.copyType = copyType;
      return execute(connection, false);
    }

  }

  public ExecuteResponse updateActionToTempTable(TempAccountBalance baInfo, CLAction.ActionInfo action, Date jobDate, Date runDate, Constants.ActionStatus actionstatus) {
    return new UpdateActionToTempTable(logger).update(baInfo, action, jobDate, runDate, actionstatus, 0);
  }

  public ExecuteResponse insertAndupdateActionToTempTable(TempAccountBalance baInfo, CLAction.ActionInfo action, Date jobDate, Date runDate, Constants.ActionStatus actionstatus) {
    return new UpdateActionToTempTable(logger).update(baInfo, action, jobDate, runDate, actionstatus, 1);
  }

  public ExecuteResponse updateRunnigStatusTempTable(TempAccountBalance baInfo) {
    return new UpdateActionToTempTable(logger).update(baInfo, null, null, null, null, 2);
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="GET LIST Action Criteria">
  public class ActionCriteriaInfo {

    private BigDecimal actionId;
    private BigDecimal actionCriteriaId;
    private BigDecimal criteriaId;
    private BigDecimal criteriaPriority;
    private Character unqualifyBoo;
    private BigDecimal confirmMode;

    public BigDecimal getActionId() {
      return actionId;
    }

    public void setActionId(BigDecimal actionId) {
      this.actionId = actionId;
    }

    public BigDecimal getActionCriteriaId() {
      return actionCriteriaId;
    }

    public void setActionCriteriaId(BigDecimal actionCriteriaId) {
      this.actionCriteriaId = actionCriteriaId;
    }

    public BigDecimal getCriteriaId() {
      return criteriaId;
    }

    public void setCriteriaId(BigDecimal criteriaId) {
      this.criteriaId = criteriaId;
    }

    public BigDecimal getCriteriaPriority() {
      return criteriaPriority;
    }

    public void setCriteriaPriority(BigDecimal criteriaPriority) {
      this.criteriaPriority = criteriaPriority;
    }

    public Character getUnqualifyBoo() {
      return unqualifyBoo;
    }

    public void setUnqualifyBoo(Character unqualifyBoo) {
      this.unqualifyBoo = unqualifyBoo;
    }

    public BigDecimal getConfirmMode() {
      return confirmMode;
    }

    public void setConfirmMode(BigDecimal confirmMode) {
      this.confirmMode = confirmMode;
    }

  }

  public class ActionCriteriaResponse extends DBTemplatesResponse<HashMap<BigDecimal, ArrayList<ActionCriteriaInfo>>> {

    @Override
    protected HashMap<BigDecimal, ArrayList<ActionCriteriaInfo>> createResponse() {
      return new HashMap<>();
    }

  }

  protected class GetListCriteriaAction extends DBTemplateCallableStatement<ActionCriteriaResponse, UtilityLogger, DBConnectionPools> {

    public GetListCriteriaAction(UtilityLogger logger) {
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

          BigDecimal actionId = resultSet.getBigDecimal("ACTION_ID");
          ActionCriteriaInfo temp = new ActionCriteriaInfo();
          temp.setActionId(actionId);
          temp.setActionCriteriaId(resultSet.getBigDecimal("ACTION_CRITERIA_ID"));
          temp.setCriteriaId(resultSet.getBigDecimal("CRITERIA_ID"));
          temp.setCriteriaPriority(resultSet.getBigDecimal("CRITERIA_PRIORITY"));
          temp.setConfirmMode(resultSet.getBigDecimal("CONFIRM_MODE"));
          temp.setUnqualifyBoo(toCharcter(resultSet.getString("UNQUALIFY_BOO"), 'N'));

          ArrayList<ActionCriteriaInfo> obj = response.getResponse().get(actionId);
          if (obj == null) {
            countRecord();
            obj = new ArrayList<>();
            response.getResponse().put(actionId, obj);
          }
          obj.add(temp);

        }
      }
      Integer code = (Integer) statement.getObject(1);
      //logger.debug("code = " + code);
    }

    @Override
    protected ActionCriteriaResponse createResponse() {
      return new ActionCriteriaResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      return new StringBuilder("{call CL_TM_GET_CRITERIA (?)}");

    }

    protected ActionCriteriaResponse query() {
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public ActionCriteriaResponse queryActionCriteria() {
    return new GetListCriteriaAction(logger).query();
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="Update Action Criteria all 3 step">
  protected class UpdateActionCriteriaStep1 extends DBTemplatesExecuteUpdate<ExecuteResponse, UtilityLogger, DBConnectionPools> {

    private String sqlWhere;
    private BigDecimal actionId;

    public UpdateActionCriteriaStep1(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected ExecuteResponse createResponse() {
      return new ExecuteResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append(" SELECT t1.TRANS_ID  INTO #TEMP_CRITERIA");
      sql.append(" from(   ");
      sql.append("     SELECT TRANS_ID,  ACTION_ID, ACTION_PATH_ID, CRITERIA_ID, ACTION_CRITERIA_ID , BA_ACCOUNT_BALANCE_ID");
      sql.append("     FROM CL_TEMP_CAL_TREATMENT");
      sql.append("     WHERE ACTION_ID = ").append(actionId.toPlainString());
      sql.append(" ) t1 left join (");
      sql.append("   ").append(sqlWhere);
      sql.append(" ) t2 on t1.BA_ACCOUNT_BALANCE_ID = t2.BA_ACCOUNT_BALANCE_ID");
      sql.append(" where t2.BA_ACCOUNT_BALANCE_ID is not null");
      return sql;
    }

    protected ExecuteResponse execute(String sql, BigDecimal actionId) {
      this.sqlWhere = sql;
      this.actionId = actionId;
      return executeUpdate(connection, false);
    }
  }

  protected class UpdateActionCriteriaStep2 extends DBTemplatesExecuteUpdate<ExecuteResponse, UtilityLogger, DBConnectionPools> {

    CLTempHisBaAccountBalance.ActionCriteriaInfo criteria;

    public UpdateActionCriteriaStep2(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected ExecuteResponse createResponse() {
      return new ExecuteResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append(" UPDATE CL_TEMP_CAL_TREATMENT");
      sql.append(" SET ")
        .append(" CRITERIA_ID = CRITERIA_ID + '").append(criteria.getCriteriaId().toPlainString()).append("|' , ")
        .append(" ACTION_CRITERIA_ID = ACTION_CRITERIA_ID + '").append(criteria.getActionCriteriaId().toPlainString()).append("|' , ")
        .append(" COUNTER_CRITERIA_ID = COUNTER_CRITERIA_ID + 1 , ")
        .append(" AC_UNQUALIFY_BOO = AC_UNQUALIFY_BOO+ '").append(criteria.getUnqualifyBoo()).append("|' ");
      //sql.append(", AC_CONFIRM_MODE = AC_CONFIRM_MODE + '").append(criteria.getConfirmMode().toPlainString()).append("|' ")
      sql.append(" FROM CL_TEMP_CAL_TREATMENT a  , #TEMP_CRITERIA b");
      sql.append(" WHERE a.TRANS_ID = b.TRANS_ID");
      return sql;
    }

    protected ExecuteResponse execute(CLTempHisBaAccountBalance.ActionCriteriaInfo criteria) {
      this.criteria = criteria;
      return executeUpdate(connection, false);
    }
  }

  protected class UpdateActionCriteriaStep3 extends DBTemplatesExecuteUpdate<ExecuteResponse, UtilityLogger, DBConnectionPools> {

    public UpdateActionCriteriaStep3(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected ExecuteResponse createResponse() {
      return new ExecuteResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append("drop table #TEMP_CRITERIA");
      return sql;
    }

    protected ExecuteResponse execute() {
      return executeUpdate(connection, false);
    }
  }

  public ExecuteResponse prepareActionCriteria(String sql, BigDecimal actionId) {
    return new UpdateActionCriteriaStep1(logger).execute(sql, actionId);
  }

  public ExecuteResponse updateActionCriteria(CLTempHisBaAccountBalance.ActionCriteriaInfo criteria) {
    return new UpdateActionCriteriaStep2(logger).execute(criteria);
  }

  public ExecuteResponse clearActionCriteria() {
    return new UpdateActionCriteriaStep3(logger).execute();
  }

  //</editor-fold>  
  //
  //<editor-fold defaultstate="collapsed" desc="GET CounterExemptResponse">
  public class CounterExemptResponse extends DBTemplatesResponse< Integer> {

    @Override
    protected Integer createResponse() {
      return 0;
    }

  }

  protected class CounterExempt extends DBTemplateCallableStatement<CounterExemptResponse, UtilityLogger, DBConnectionPools> {

    private TempAccountBalance baInfo;
    private CLAction.ActionInfo action;

    public CounterExempt(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected void setParameter() throws SQLException {
      statement.setBigDecimal(1, action.getActionID());

      statement.setString(2, baInfo.getCaNumber());
      statement.setString(3, baInfo.getSaNumber());
      statement.setString(4, baInfo.getBaNumber());
      statement.setString(5, baInfo.getBillRegionId().toPlainString());
      statement.setString(6, baInfo.getBillProvinceId().toPlainString());
      statement.setString(7, baInfo.getBillZipcode());
      statement.registerOutParameter(8, java.sql.Types.INTEGER);
      statement.registerOutParameter(9, java.sql.Types.INTEGER);

    }

    @Override
    protected void setReturnValue() throws SQLException {
      Integer code = (Integer) statement.getObject(8);
      //logger.debug("code = " + code);
      code = (Integer) statement.getObject(9);
      //logger.debug("code = " + code);
      response.setResponse(code);
    }

    @Override
    protected CounterExemptResponse createResponse() {
      return new CounterExemptResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      return new StringBuilder("{call CL_TM_GET_EXEMPT (?,?,?,?,?,?,?,?,?)}");

    }

    protected CounterExemptResponse execute(TempAccountBalance baInfo, CLAction.ActionInfo action) {
      this.action = action;
      this.baInfo = baInfo;
      return execute(Constants.getDBConnectionPools(logger), true);
    }

  }

  public CounterExemptResponse countExempt(TempAccountBalance baInfo, CLAction.ActionInfo action) {
    return new CounterExempt(logger).execute(baInfo, action);
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="copy treatment">
  protected class CopyTreatment extends DBTemplateCallableStatement<ExecuteResponse, UtilityLogger, DBConnectionPools> {

    private int updateBaInfo = 0;
    private int updateCLTreatment = 0;
    private int insertCLTreatment = 0;

    private TempAccountBalance baInfo;

    private Constants.ActionStatus actionstatusChange = Constants.ActionStatus.NULL;
    private BigDecimal treatmentId = new BigDecimal(0);

    public CopyTreatment(UtilityLogger logger) {
      super(logger);
    }

    /*
    @updateBaInfo int ,
    @updateCLTreatment int ,
    @insertClTreatment int ,

    @treatment_id unsigned bigint ,
    @action_status_change int ,

    @ca_no varchar(50),
    @ba_no varchar(50),
    
    @collection_path_id unsigned bigint,
    @action_id  unsigned bigint ,
    @action_path_id unsigned bigint ,
    
    @action_path_dtm datetime,
    @action_schedule_dtm datetime,
    
    @criteria_id unsigned bigint ,
    @action_criteria_id unsigned bigint ,
    
    @action_status int ,
    
    @action_status_dtm datetime,
    @min_due_date date,

    @identityResult unsigned bigint output ,
    @result_code integer output
     */
    @Override
    protected void setParameter() throws SQLException {
      statement.setQueryTimeout(1800);
      statement.setInt(1, updateBaInfo);
      statement.setInt(2, updateCLTreatment);
      statement.setInt(3, insertCLTreatment);
      statement.setBigDecimal(4, treatmentId);
      statement.setInt(5, actionstatusChange.getCode());
      statement.setString(6, baInfo.getCaNumber());
      statement.setString(7, baInfo.getBaNumber());
      statement.setBigDecimal(8, baInfo.getCollectionPathId());
      statement.setBigDecimal(9, baInfo.getActionId());
      statement.setBigDecimal(10, baInfo.getActionPathId());
      statement.setTimestamp(11, toSqlTimestamp(baInfo.getActionPathDtm(), null));
      statement.setTimestamp(12, toSqlTimestamp(baInfo.getActionScheduleDtm(), null));
      statement.setBigDecimal(13, baInfo.getCriteriaIdRun());//@criteria_id
      statement.setBigDecimal(14, baInfo.getActionCriteriaIdRun());//@action_criteria_id      
      statement.setInt(15, baInfo.getActionStatus().getCode());
      statement.setTimestamp(16, toSqlTimestamp(baInfo.getActionStatusDtm(), null));
      statement.setDate(17, toSqlDate(baInfo.getMinDueDate(), null));
      statement.registerOutParameter(18, java.sql.Types.NUMERIC);
      statement.registerOutParameter(19, java.sql.Types.INTEGER);
    }

    @Override
    protected void setReturnValue() throws SQLException {
      BigDecimal identiry = (BigDecimal) statement.getObject(18);
      //logger.debug("identiry = " + identiry.toPlainString());

      Integer code = (Integer) statement.getObject(19);
      //logger.debug("code = " + code);

      response.setResponse(Boolean.TRUE);

      countRecord();
    }

    @Override
    protected ExecuteResponse createResponse() {
      return new ExecuteResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      return new StringBuilder("{call CL_TM_COPY_TREATMENT (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
    }

    protected ExecuteResponse update(int updateBaInfo, int updateCLTreatment, int insertCLTreatment, BigDecimal treatmentId, Constants.ActionStatus actionstatusChange, TempAccountBalance baInfo) {
      this.updateBaInfo = updateBaInfo;
      this.updateCLTreatment = updateCLTreatment;
      this.insertCLTreatment = insertCLTreatment;
      this.treatmentId = treatmentId;
      this.actionstatusChange = actionstatusChange;
      this.baInfo = baInfo;
      return execute(connection, false);
    }

  }

  public ExecuteResponse executeTreatment(int updateBaInfo, int updateCLTreatment, int insertCLTreatment, BigDecimal treatmentId, Constants.ActionStatus actionstatusChange, TempAccountBalance baInfo) {
    return new CopyTreatment(logger).update(updateBaInfo, updateCLTreatment, insertCLTreatment, treatmentId, actionstatusChange, baInfo);
  }

  public ExecuteResponse executeTreatment(TempAccountBalance baInfo) {
    return new CopyTreatment(logger).update(0, 0, 1, BigDecimal.ZERO, Constants.ActionStatus.NULL, baInfo);
  }

  //</editor-fold>
  //
  public static void main(String[] args) {
    long k = System.currentTimeMillis();
    CNFDatabase cnf = new CNFDatabase("D:\\Work\\Ais\\Apps\\Batch\\configuration\\database.properties");

    Context ctx = new Context();
    ctx.initailLogger("LoggerMasterBatchInfo", "XXXX|YYYYY");

    new DBConnectionPools<>(cnf, ctx.getLogger()).buildeDataSource();

    CLTempHisBaAccountBalance mmmm = new CLTempHisBaAccountBalance(ctx.getLogger());

    CLTempHisBaAccountBalance.TempAccountBalance baInfo = new CLTempHisBaAccountBalance(ctx.getLogger()).new TempAccountBalance();
    baInfo.setCaNumber("aa");
    baInfo.setSaNumber("nn");
    baInfo.setBaNumber("cc");
    baInfo.setBillRegionId(new BigDecimal("10"));
    baInfo.setBillProvinceId(new BigDecimal("19"));
    baInfo.setBillZipcode("xx");

    CLAction.ActionInfo action = new CLAction(ctx.getLogger()).new ActionInfo();
    action.setActionID(new BigDecimal(1));
    CounterExemptResponse re = mmmm.countExempt(baInfo, action);

    ctx.getLogger().debug("ress = " + re.info());
    //ExecuteResponse mm = mmmm.truncate();
    //TempAccountBalanceResponse mm = mmmm.queryTempAccount(BigDecimal.TEN, new BigDecimal(System.nanoTime()));
    //HistoryActionResponse mm = mmmm.queryHistoryAction("ssssss","sss", new Date());
    //HistoryActionTLNLResponse mm = mmmm.queryHistoryTLNL("ssssss", "sss", new Date());
    /*
    CLTempHisBaAccountBalance.TempAccountBalance baInfo = new CLTempHisBaAccountBalance(ctx.getLogger()).new TempAccountBalance();
    baInfo.setTransId(new BigDecimal(1));
    CLAction.ActionInfo action = new CLAction(ctx.getLogger()).new ActionInfo();
    action.setActionID(new BigDecimal(1));
    action.setActionPathID(new BigDecimal(1));
    ExecuteResponse mm = mmmm.insertAndupdateActionToTempTable(baInfo, action, new Date(), new Date());
     */
 /*
    mmmm.buildConnection();
    ctx.getLogger().debug("" + mmmm.getDBConnection().ready());
    String sql = ""
    + "SELECT BA_ACCOUNT_BALANCE_ID "
    + "FROM CL_HIS_BA_ACCOUNT_BALANCE, CL_BA_INFO, CL_CA_INFO "
    + "WHERE CL_BA_INFO.BA_NO=CL_HIS_BA_ACCOUNT_BALANCE.BA_NO AND  "
    + "CL_CA_INFO.CA_NO=CL_BA_INFO.CA_NO AND  "
    + "(CL_CA_INFO.ACCOUNT_SEGMENT_ID = 1 OR CL_CA_INFO.ACCOUNT_SEGMENT_ID = 3 OR CL_CA_INFO.ACCOUNT_SEGMENT_ID = 5) AND  "
    + "CL_BA_INFO.COMPANY_ID = 1 ";
    String listAction = "10,11,12";
    ExecuteResponse mm = mmmm.prepareActionCriteria(sql, new BigDecimal(10));
    ctx.getLogger().debug("result : " + mm.info());
    ExecuteResponse xx = mmmm.unpdateActionCriteria(new BigDecimal(10), new BigDecimal(10));
    ctx.getLogger().debug("result : " + xx.info());
    ExecuteResponse yy = mmmm.clearActionCriteria();
    ctx.getLogger().debug("result : " + yy.info());
    new DBConnectionPools<>(cnf, ctx.getLogger()).closeDataSource();
    ctx.getLogger().debug("time : " + (System.currentTimeMillis() - k));
    mmmm.getDBConnection().close();
     mmmm.getDBConnection().closeDataSource();
     */

  }

}
//
//          temp.setTransId(resultSet.getBigDecimal("TRANS_ID"));
//          temp.setRunningId(resultSet.getBigDecimal("RUNNING_ID"));
//          temp.setRunningStatus(resultSet.getBigDecimal("RUNNING_STTS"));
//          temp.setCollectionPathId(resultSet.getBigDecimal("COLLECTION_PATH_ID"));
//          temp.setActionId(resultSet.getBigDecimal("ACTION_ID"));
//          temp.setActionPathId(resultSet.getBigDecimal("ACTION_PATH_ID"));
//          temp.setActionPathDate(toDate(resultSet.getDate("ACTION_PATH_DTM"), null));
//          temp.setActionScheduleDate(toDate(resultSet.getDate("ACTION_SCHEDULE_DTM"), null));
//          temp.setCriteriaId(resultSet.getBigDecimal("CRITERIA_ID"));
//          temp.setActionCriteriaId(resultSet.getBigDecimal("ACTION_CRITERIA_ID"));
//          temp.setActionStatus(Constants.mapActionStatus(toInteger(resultSet.getBigDecimal("ACTION_STATUS"), -9999)));
//          temp.setActionPathDate(toDate(resultSet.getDate("ACTION_STATUS_DTM"), null));
//          temp.setActionRemark(resultSet.getString("ACTION_REMARK"));
//          temp.setAdhocBoo(toCharcter(resultSet.getString("ADHOC_BOO"), 'N'));
//          temp.setBypassExemptBoo(toCharcter(resultSet.getString("BYPASS_EXEMPT_BOO"), 'N'));
//          temp.setActivityLogBoo(toCharcter(resultSet.getString("ACTIVITY_LOG_BOO"), 'N'));
//          temp.setBaNumber(resultSet.getString("BA_NO"));
//          temp.setCaNumber(resultSet.getString("CA_NO"));
//          temp.setSaNumber(resultSet.getString("SA_NO"));
//          temp.setCollectionPathIdCurrent(resultSet.getBigDecimal("COLLECTION_PATH_ID_CURR"));
//          temp.setActionStatus(Constants.mapActionStatus(toInteger(resultSet.getBigDecimal("PRIORITY_STATUS_ID"), -9999)));
/*
  public class TempAccountBalancex {

    public TempAccountBalancex() {
    }
    private BigDecimal transId;
    private BigDecimal runningId;
    private BigDecimal runningStatus;

    private BigDecimal collectionPathId;
    private BigDecimal actionId;
    private BigDecimal actionPathId;
    private Date actionPathDate;
    private Date actionScheduleDate;
    private BigDecimal criteriaId;
    private BigDecimal actionCriteriaId;
    private Constants.ActionStatus actionStatus;
    private Date actionStatusDate;
    private String actionRemark;
    private Character adhocBoo;
    private Character bypassExemptBoo;
    private Character activityLogBoo;

    private String baNumber;
    private String caNumber;
    private String saNumber;
    private BigDecimal collectionPathIdCurrent;
    private Constants.PriorityStatus priorityStatus;
    private Date priorityStatusDate;
    private String billZipcode;
    private BigDecimal billProvinceId;
    private BigDecimal billRegionId;

    private BigDecimal baAccountBalanceId;
    private BigDecimal arMovementId;
    private BigDecimal baNonVatBalance;
    private BigDecimal baExcVatBalance;
    private BigDecimal baVatBalance;
    private BigDecimal baTotalBalance;
    private BigDecimal baDisputeBalance;
    private BigDecimal baExcessBalance;
    private BigDecimal dueInvoiceCount;
    private BigDecimal dueNonVatBalance;
    private BigDecimal dueExcVatBalance;
    private BigDecimal dueVatBalance;
    private BigDecimal dueTotalBalance;
    private BigDecimal dueDisputeBalance;
    private BigDecimal contractPenalty;
    private BigDecimal minInvoiceId;
    private Date minInvoiceDate;
    private Date minDueDate;
    private BigDecimal maxInvoiceId;
    private Date maxInvoiceDate;
    private Date balanceStartDate;
    private Date balanceEndDate;
    // dynamic column
    private BigDecimal minDueDateCount;
    private BigDecimal priorityStatusDateCount;
    private Constants.PriorityStatusGroup priorityStatusGroup;
    private Constants.PriorityStatusGroupStatus priorityStatusGroupStatus;

    public BigDecimal getTransId() {
      return transId;
    }

    public void setTransId(BigDecimal transId) {
      this.transId = transId;
    }

    public BigDecimal getRunningId() {
      return runningId;
    }

    public void setRunningId(BigDecimal runningId) {
      this.runningId = runningId;
    }

    public BigDecimal getRunningStatus() {
      return runningStatus;
    }

    public void setRunningStatus(BigDecimal runningStatus) {
      this.runningStatus = runningStatus;
    }

    public BigDecimal getCollectionPathId() {
      return collectionPathId;
    }

    public void setCollectionPathId(BigDecimal collectionPathId) {
      this.collectionPathId = collectionPathId;
    }

    public BigDecimal getActionId() {
      return actionId;
    }

    public void setActionId(BigDecimal actionId) {
      this.actionId = actionId;
    }

    public BigDecimal getActionPathId() {
      return actionPathId;
    }

    public void setActionPathId(BigDecimal actionPathId) {
      this.actionPathId = actionPathId;
    }

    public Date getActionPathDate() {
      return actionPathDate;
    }

    public void setActionPathDate(Date actionPathDate) {
      this.actionPathDate = actionPathDate;
    }

    public Date getActionScheduleDate() {
      return actionScheduleDate;
    }

    public void setActionScheduleDate(Date actionScheduleDate) {
      this.actionScheduleDate = actionScheduleDate;
    }

    public BigDecimal getCriteriaId() {
      return criteriaId;
    }

    public void setCriteriaId(BigDecimal criteriaId) {
      this.criteriaId = criteriaId;
    }

    public BigDecimal getActionCriteriaId() {
      return actionCriteriaId;
    }

    public void setActionCriteriaId(BigDecimal actionCriteriaId) {
      this.actionCriteriaId = actionCriteriaId;
    }

    public Constants.ActionStatus getActionStatus() {
      return actionStatus;
    }

    public void setActionStatus(Constants.ActionStatus actionStatus) {
      this.actionStatus = actionStatus;
    }

    public Date getActionStatusDate() {
      return actionStatusDate;
    }

    public void setActionStatusDate(Date actionStatusDate) {
      this.actionStatusDate = actionStatusDate;
    }

    public String getActionRemark() {
      return actionRemark;
    }

    public void setActionRemark(String actionRemark) {
      this.actionRemark = actionRemark;
    }

    public Character getAdhocBoo() {
      return adhocBoo;
    }

    public void setAdhocBoo(Character adhocBoo) {
      this.adhocBoo = adhocBoo;
    }

    public Character getBypassExemptBoo() {
      return bypassExemptBoo;
    }

    public void setBypassExemptBoo(Character bypassExemptBoo) {
      this.bypassExemptBoo = bypassExemptBoo;
    }

    public Character getActivityLogBoo() {
      return activityLogBoo;
    }

    public void setActivityLogBoo(Character activityLogBoo) {
      this.activityLogBoo = activityLogBoo;
    }

    public String getBaNumber() {
      return baNumber;
    }

    public void setBaNumber(String baNumber) {
      this.baNumber = baNumber;
    }

    public String getCaNumber() {
      return caNumber;
    }

    public void setCaNumber(String caNumber) {
      this.caNumber = caNumber;
    }

    public String getSaNumber() {
      return saNumber;
    }

    public void setSaNumber(String saNumber) {
      this.saNumber = saNumber;
    }

    public BigDecimal getCollectionPathIdCurrent() {
      return collectionPathIdCurrent;
    }

    public void setCollectionPathIdCurrent(BigDecimal collectionPathIdCurrent) {
      this.collectionPathIdCurrent = collectionPathIdCurrent;
    }

    public Constants.PriorityStatus getPriorityStatus() {
      return priorityStatus;
    }

    public void setPriorityStatus(Constants.PriorityStatus priorityStatus) {
      this.priorityStatus = priorityStatus;
    }

    public Date getPriorityStatusDate() {
      return priorityStatusDate;
    }

    public void setPriorityStatusDate(Date priorityStatusDate) {
      this.priorityStatusDate = priorityStatusDate;
    }

    public String getBillZipcode() {
      return billZipcode;
    }

    public void setBillZipcode(String billZipcode) {
      this.billZipcode = billZipcode;
    }

    public BigDecimal getBillProvinceId() {
      return billProvinceId;
    }

    public void setBillProvinceId(BigDecimal billProvinceId) {
      this.billProvinceId = billProvinceId;
    }

    public BigDecimal getBillRegionId() {
      return billRegionId;
    }

    public void setBillRegionId(BigDecimal billRegionId) {
      this.billRegionId = billRegionId;
    }

    public BigDecimal getBaAccountBalanceId() {
      return baAccountBalanceId;
    }

    public void setBaAccountBalanceId(BigDecimal baAccountBalanceId) {
      this.baAccountBalanceId = baAccountBalanceId;
    }

    public BigDecimal getArMovementId() {
      return arMovementId;
    }

    public void setArMovementId(BigDecimal arMovementId) {
      this.arMovementId = arMovementId;
    }

    public BigDecimal getBaNonVatBalance() {
      return baNonVatBalance;
    }

    public void setBaNonVatBalance(BigDecimal baNonVatBalance) {
      this.baNonVatBalance = baNonVatBalance;
    }

    public BigDecimal getBaExcVatBalance() {
      return baExcVatBalance;
    }

    public void setBaExcVatBalance(BigDecimal baExcVatBalance) {
      this.baExcVatBalance = baExcVatBalance;
    }

    public BigDecimal getBaVatBalance() {
      return baVatBalance;
    }

    public void setBaVatBalance(BigDecimal baVatBalance) {
      this.baVatBalance = baVatBalance;
    }

    public BigDecimal getBaTotalBalance() {
      return baTotalBalance;
    }

    public void setBaTotalBalance(BigDecimal baTotalBalance) {
      this.baTotalBalance = baTotalBalance;
    }

    public BigDecimal getBaDisputeBalance() {
      return baDisputeBalance;
    }

    public void setBaDisputeBalance(BigDecimal baDisputeBalance) {
      this.baDisputeBalance = baDisputeBalance;
    }

    public BigDecimal getBaExcessBalance() {
      return baExcessBalance;
    }

    public void setBaExcessBalance(BigDecimal baExcessBalance) {
      this.baExcessBalance = baExcessBalance;
    }

    public BigDecimal getDueInvoiceCount() {
      return dueInvoiceCount;
    }

    public void setDueInvoiceCount(BigDecimal dueInvoiceCount) {
      this.dueInvoiceCount = dueInvoiceCount;
    }

    public BigDecimal getDueNonVatBalance() {
      return dueNonVatBalance;
    }

    public void setDueNonVatBalance(BigDecimal dueNonVatBalance) {
      this.dueNonVatBalance = dueNonVatBalance;
    }

    public BigDecimal getDueExcVatBalance() {
      return dueExcVatBalance;
    }

    public void setDueExcVatBalance(BigDecimal dueExcVatBalance) {
      this.dueExcVatBalance = dueExcVatBalance;
    }

    public BigDecimal getDueVatBalance() {
      return dueVatBalance;
    }

    public void setDueVatBalance(BigDecimal dueVatBalance) {
      this.dueVatBalance = dueVatBalance;
    }

    public BigDecimal getDueTotalBalance() {
      return dueTotalBalance;
    }

    public void setDueTotalBalance(BigDecimal dueTotalBalance) {
      this.dueTotalBalance = dueTotalBalance;
    }

    public BigDecimal getDueDisputeBalance() {
      return dueDisputeBalance;
    }

    public void setDueDisputeBalance(BigDecimal dueDisputeBalance) {
      this.dueDisputeBalance = dueDisputeBalance;
    }

    public BigDecimal getContractPenalty() {
      return contractPenalty;
    }

    public void setContractPenalty(BigDecimal contractPenalty) {
      this.contractPenalty = contractPenalty;
    }

    public BigDecimal getMinInvoiceId() {
      return minInvoiceId;
    }

    public void setMinInvoiceId(BigDecimal minInvoiceId) {
      this.minInvoiceId = minInvoiceId;
    }

    public Date getMinInvoiceDate() {
      return minInvoiceDate;
    }

    public void setMinInvoiceDate(Date minInvoiceDate) {
      this.minInvoiceDate = minInvoiceDate;
    }

    public Date getMinDueDate() {
      return minDueDate;
    }

    public void setMinDueDate(Date minDueDate) {
      this.minDueDate = minDueDate;
    }

    public BigDecimal getMaxInvoiceId() {
      return maxInvoiceId;
    }

    public void setMaxInvoiceId(BigDecimal maxInvoiceId) {
      this.maxInvoiceId = maxInvoiceId;
    }

    public Date getMaxInvoiceDate() {
      return maxInvoiceDate;
    }

    public void setMaxInvoiceDate(Date maxInvoiceDate) {
      this.maxInvoiceDate = maxInvoiceDate;
    }

    public Date getBalanceStartDate() {
      return balanceStartDate;
    }

    public void setBalanceStartDate(Date balanceStartDate) {
      this.balanceStartDate = balanceStartDate;
    }

    public Date getBalanceEndDate() {
      return balanceEndDate;
    }

    public void setBalanceEndDate(Date balanceEndDate) {
      this.balanceEndDate = balanceEndDate;
    }

    public BigDecimal getMinDueDateCount() {
      return minDueDateCount;
    }

    public void setMinDueDateCount(BigDecimal minDueDateCount) {
      this.minDueDateCount = minDueDateCount;
    }

    public BigDecimal getPriorityStatusDateCount() {
      return priorityStatusDateCount;
    }

    public void setPriorityStatusDateCount(BigDecimal priorityStatusDateCount) {
      this.priorityStatusDateCount = priorityStatusDateCount;
    }

    public Constants.PriorityStatusGroup getPriorityStatusGroup() {
      return priorityStatusGroup;
    }

    public void setPriorityStatusGroup(Constants.PriorityStatusGroup priorityStatusGroup) {
      this.priorityStatusGroup = priorityStatusGroup;
    }

    public Constants.PriorityStatusGroupStatus getPriorityStatusGroupStatus() {
      return priorityStatusGroupStatus;
    }

    public void setPriorityStatusGroupStatus(Constants.PriorityStatusGroupStatus priorityStatusGroupStatus) {
      this.priorityStatusGroupStatus = priorityStatusGroupStatus;
    }

  }

 */
 /*
  protected class GetListCurrentTreatment extends DBTemplateCallableStatement<CurrentTreatmentResponse, UtilityLogger> {

    private String baNumber;
    private String caNumber;
    private Date minDueDate;

    public GetListCurrentTreatment(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected void setParameter() throws SQLException {
      statement.setQueryTimeout(1800);
      statement.setString(1, caNumber);
      statement.setString(2, baNumber);
      statement.setDate(3, toSqlDate(minDueDate, null));
      statement.registerOutParameter(4, java.sql.Types.INTEGER);
    }

    @Override
    protected void setReturnValue() throws SQLException {
      if (resultSet != null) {
        while (resultSet.next()) {
          countRecord();
          CurrentTreatment temp = new CurrentTreatment();

          temp.setTreatmentId(resultSet.getBigDecimal("TREATMENT_ID"));
          temp.setCaNumber(resultSet.getString("CA_NO"));
          temp.setBaNumber(resultSet.getString("BA_NO"));
          temp.setCollectionPathId(resultSet.getBigDecimal("COLLECTION_PATH_ID"));
          temp.setActionId(resultSet.getBigDecimal("ACTION_ID"));
          temp.setActionPathId(resultSet.getBigDecimal("ACTION_PATH_ID"));

          temp.setActionPathDtm(toDate(resultSet.getTimestamp("ACTION_PATH_DTM"), null));
          temp.setActionScheduleDtm(toDate(resultSet.getTimestamp("ACTION_SCHEDULE_DTM"), null));

          temp.setCriteriaId(resultSet.getBigDecimal("CRITERIA_ID"));
          temp.setActionCriteriaId(resultSet.getBigDecimal("ACTION_CRITERIA_ID"));

          temp.setActionStatus(Constants.mapActionStatus(resultSet.getBigDecimal("ACTION_STATUS").intValue()));
          temp.setActionScheduleDtm(toDate(resultSet.getTimestamp("ACTION_STATUS_DTM"), null));
          temp.setMinDueDate(toDate(resultSet.getTimestamp("MIN_DUE_DATE"), null));

          temp.setPrimaryBoo(toCharcter(resultSet.getString("PRIMARY_BOO"), 'N'));

          response.getResponse().add(temp);
        }
      }
      Integer code = (Integer) statement.getObject(4);
      //logger.debug("code = " + code);
    }

    @Override
    protected CurrentTreatmentResponse createResponse() {
      return new CurrentTreatmentResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      return new StringBuilder("{call CL_TM_GET_CURRENT_TREATMENT ( ?, ?, ?)}");

    }

    protected CurrentTreatmentResponse query(String caNumber, String baNumber, Date minDueDate) {
      this.caNumber = caNumber;
      this.baNumber = baNumber;
      this.minDueDate = minDueDate;
      return executeQuery(getConnection(), true);
    }

  }

  public CurrentTreatmentResponse queryCurrentTreatment(String caNumber, String baNumber, Date minDueDate) {
    return new GetListCurrentTreatment(logger).query(caNumber, baNumber, minDueDate);
  }

 */
 /*
  //Insert into CRITERIA LOG
  //<editor-fold defaultstate="collapsed" desc="InsertCriteriaLog">
  public class InsertCriteriaLogResponse extends DBTemplatesResponse< BigDecimal> {

    @Override
    protected BigDecimal createResponse() {
      return new BigDecimal(0);
    }

  }

  protected class InsertCriteriaLog extends DBTemplateCallableStatement<InsertCriteriaLogResponse, UtilityLogger> {

    private Constants.LogCriteriaType type;
    private Constants.LogCriteriaOption option;
    private Constants.LogCriteriaStatus status;

    public InsertCriteriaLog(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected StringBuilder createSqlProcess() {
      return new StringBuilder("{call CL_TM_INSERT_LOG_CRITERIA (?,?,?,?,?)}");
    }

    @Override
    protected InsertCriteriaLogResponse createResponse() {
      return new InsertCriteriaLogResponse();
    }

    @Override
    protected void setParameter() throws SQLException {
      statement.setInt(1, type.getCode());
      statement.setInt(2, option.getCode());
      statement.setInt(3, status.getCode());
      statement.registerOutParameter(4, java.sql.Types.DECIMAL);
      statement.registerOutParameter(5, java.sql.Types.INTEGER);
    }

    @Override
    protected void setReturnValue() throws SQLException {
      BigDecimal id = (BigDecimal) statement.getObject(8);
      //logger.debug("code = " + id.toPlainString());
      response.setResponse(id);

      Integer code = (Integer) statement.getObject(9);
      //logger.debug("code = " + code);
    }

    protected InsertCriteriaLogResponse execute(Constants.LogCriteriaType type, Constants.LogCriteriaOption option, Constants.LogCriteriaStatus status) {
      return execute(getConnection(), true);
    }

  }

  public InsertCriteriaLogResponse insertCriteriaLog(Constants.LogCriteriaType type, Constants.LogCriteriaOption option, Constants.LogCriteriaStatus status) {
    return new InsertCriteriaLog(logger).execute(type, option, status);
  }

  //</editor-fold>
  //
 */
