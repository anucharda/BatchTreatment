/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.db;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import th.co.ais.cpac.cl.batch.Constants;

import th.co.ais.cpac.cl.batch.cnf.CNFDatabase;
import th.co.ais.cpac.cl.common.Context;
import th.co.ais.cpac.cl.common.UtilityLogger;
import th.co.ais.cpac.cl.template.database.DBConnectionPools;
import th.co.ais.cpac.cl.template.database.DBTemplateCallableStatement;
import th.co.ais.cpac.cl.template.database.DBTemplatesExecuteQuery;
import th.co.ais.cpac.cl.template.database.DBTemplatesResponse;

/**
 *
 * @author Optimus
 */
public class CLAction {

  protected final UtilityLogger logger;

  public CLAction(UtilityLogger logger) {
    this.logger = logger;
  }

  //<editor-fold defaultstate="collapsed" desc="get path and action">
  public class ActionDetail {

    private BigDecimal collectionPathID;
    private ArrayList<ActionInfo> listActionInfo;

    public BigDecimal getCollectionPathID() {
      return collectionPathID;
    }

    public void setCollectionPathID(BigDecimal collectionPathID) {
      this.collectionPathID = collectionPathID;
    }

    public ArrayList<ActionInfo> getListActionInfo() {
      return listActionInfo;
    }

    public void setListActionInfo(ArrayList<ActionInfo> listActionInfo) {
      this.listActionInfo = listActionInfo;
    }

    @Override
    public String toString() {
      return "ActionDetail{" + "collectionPathID=" + collectionPathID + ", listActionInfo=" + listActionInfo + '}';
    }

  }

  public class ActionInfo {

    public ActionInfo() {
    }
    private int sequence;
    private BigDecimal collectionPath;
    private BigDecimal collectionOwer;
    private Character primaryBoo;

    private BigDecimal actionPathID;
    private BigDecimal actionID;
    private BigDecimal actionOrder;
    private Constants.ActionTriger actionTrigger;
    private Constants.ActionTrigerType actionTriggerType;
    private Constants.ActionOperator actionOperator;
    private BigDecimal actionDay;
    private BigDecimal actionNumberDay;
    private Constants.ActionPeriodType actionPeriodType;
    private BigDecimal actionPeriodValue;
    private BigDecimal preActionID;

    private BigDecimal actionOwner;
    private Constants.ActionMode actionMode;
    private Constants.ActionType actionType;
    private Character actionPlanBoo;
    private String actionPlanDay;
    private Date actionTime;
    private Character actionPeriodBoo;
    private Character activityLogBoo;
    private Character userNotifyBoo;
    private BigDecimal numberSkipAction;

    public BigDecimal getCollectionPath() {
      return collectionPath;
    }

    public void setCollectionPath(BigDecimal collectionPath) {
      this.collectionPath = collectionPath;
    }

    public BigDecimal getCollectionOwer() {
      return collectionOwer;
    }

    public void setCollectionOwer(BigDecimal collectionOwer) {
      this.collectionOwer = collectionOwer;
    }

    public Character getPrimaryBoo() {
      return primaryBoo;
    }

    public void setPrimaryBoo(Character primaryBoo) {
      this.primaryBoo = primaryBoo;
    }

    public BigDecimal getActionPathID() {
      return actionPathID;
    }

    public void setActionPathID(BigDecimal actionPathID) {
      this.actionPathID = actionPathID;
    }

    public BigDecimal getActionID() {
      return actionID;
    }

    public void setActionID(BigDecimal actionID) {
      this.actionID = actionID;
    }

    public BigDecimal getActionOrder() {
      return actionOrder;
    }

    public void setActionOrder(BigDecimal actionOrder) {
      this.actionOrder = actionOrder;
    }

    public Constants.ActionTriger getActionTrigger() {
      return actionTrigger;
    }

    public void setActionTrigger(Constants.ActionTriger actionTrigger) {
      this.actionTrigger = actionTrigger;
    }

    public Constants.ActionTrigerType getActionTriggerType() {
      return actionTriggerType;
    }

    public void setActionTriggerType(Constants.ActionTrigerType actionTriggerType) {
      this.actionTriggerType = actionTriggerType;
    }

    public Constants.ActionOperator getActionOperator() {
      return actionOperator;
    }

    public void setActionOperator(Constants.ActionOperator actionOperator) {
      this.actionOperator = actionOperator;
    }

    public BigDecimal getActionDay() {
      return actionDay;
    }

    public void setActionDay(BigDecimal actionDay) {
      this.actionDay = actionDay;
    }

    public BigDecimal getActionNumberDay() {
      return actionNumberDay;
    }

    public void setActionNumberDay(BigDecimal actionNumberDay) {
      this.actionNumberDay = actionNumberDay;
    }

    public Constants.ActionPeriodType getActionPeriodType() {
      return actionPeriodType;
    }

    public void setActionPeriodType(Constants.ActionPeriodType actionPeriodType) {
      this.actionPeriodType = actionPeriodType;
    }

    public BigDecimal getActionPeriodValue() {
      return actionPeriodValue;
    }

    public void setActionPeriodValue(BigDecimal actionPeriodValue) {
      this.actionPeriodValue = actionPeriodValue;
    }

    public BigDecimal getPreActionID() {
      return preActionID;
    }

    public void setPreActionID(BigDecimal preActionID) {
      this.preActionID = preActionID;
    }

    public BigDecimal getActionOwner() {
      return actionOwner;
    }

    public void setActionOwner(BigDecimal actionOwner) {
      this.actionOwner = actionOwner;
    }

    public Constants.ActionMode getActionMode() {
      return actionMode;
    }

    public void setActionMode(Constants.ActionMode actionMode) {
      this.actionMode = actionMode;
    }

    public Constants.ActionType getActionType() {
      return actionType;
    }

    public void setActionType(Constants.ActionType actionType) {
      this.actionType = actionType;
    }

    public Character getActionPlanBoo() {
      return actionPlanBoo;
    }

    public void setActionPlanBoo(Character actionPlanBoo) {
      this.actionPlanBoo = actionPlanBoo;
    }

    public String getActionPlanDay() {
      return actionPlanDay;
    }

    public void setActionPlanDay(String actionPlanDay) {
      this.actionPlanDay = actionPlanDay;
    }

    public Date getActionTime() {
      return actionTime;
    }

    public void setActionTime(Date actionTime) {
      this.actionTime = actionTime;
    }

    public Character getActionPeriodBoo() {
      return actionPeriodBoo;
    }

    public void setActionPeriodBoo(Character actionPeriodBoo) {
      this.actionPeriodBoo = actionPeriodBoo;
    }

    public Character getActivityLogBoo() {
      return activityLogBoo;
    }

    public void setActivityLogBoo(Character activityLogBoo) {
      this.activityLogBoo = activityLogBoo;
    }

    public Character getUserNotifyBoo() {
      return userNotifyBoo;
    }

    public void setUserNotifyBoo(Character userNotifyBoo) {
      this.userNotifyBoo = userNotifyBoo;
    }

    public BigDecimal getNumberSkipAction() {
      return numberSkipAction;
    }

    public void setNumberSkipAction(BigDecimal numberSkipAction) {
      this.numberSkipAction = numberSkipAction;
    }

    public int getSequence() {
      return sequence;
    }

    public void setSequence(int sequence) {
      this.sequence = sequence;
    }

    @Override
    public String toString() {
      return "ActionInfo{" + "sequence=" + sequence + ", collectionPath=" + collectionPath + ", collectionOwer=" + collectionOwer + ", primaryBoo=" + primaryBoo + ", actionPathID=" + actionPathID + ", actionID=" + actionID + ", actionOrder=" + actionOrder + ", actionTrigger=" + actionTrigger + ", actionTriggerType=" + actionTriggerType + ", actionOperator=" + actionOperator + ", actionDay=" + actionDay + ", actionNumberDay=" + actionNumberDay + ", actionPeriodType=" + actionPeriodType + ", actionPeriodValue=" + actionPeriodValue + ", preActionID=" + preActionID + ", actionOwner=" + actionOwner + ", actionMode=" + actionMode + ", actionType=" + actionType + ", actionPlanBoo=" + actionPlanBoo + ", actionPlanDay=" + actionPlanDay + ", actionTime=" + actionTime + ", actionPeriodBoo=" + actionPeriodBoo + ", activityLogBoo=" + activityLogBoo + ", userNotifyBoo=" + userNotifyBoo + ", numberSkipAction=" + numberSkipAction + '}';
    }

  }

  public class ActionDetailResponse extends DBTemplatesResponse<HashMap<BigDecimal, ActionDetail>> {

    @Override
    protected HashMap<BigDecimal, ActionDetail> createResponse() {
      return new HashMap<>();
    }
  }

  protected class QueryActionDetail extends DBTemplateCallableStatement<ActionDetailResponse, UtilityLogger, DBConnectionPools> {

    public QueryActionDetail(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected void setParameter() throws SQLException {
      statement.registerOutParameter(1, java.sql.Types.INTEGER);
    }

    @Override

    protected void setReturnValue() throws SQLException {
      if (resultSet != null) {
        while (resultSet.next()) {
          ActionInfo actInfo = new ActionInfo();
          BigDecimal pathId = resultSet.getBigDecimal("COLLECTION_PATH_ID");
          actInfo.setCollectionPath(pathId);
          actInfo.setCollectionOwer(resultSet.getBigDecimal("COLLECTION_PATH_OWNER"));
          actInfo.setPrimaryBoo(toCharcter(resultSet.getString("PRIMARY_BOO"), null));

          actInfo.setActionPathID(resultSet.getBigDecimal("ACTION_PATH_ID"));
          actInfo.setActionID(resultSet.getBigDecimal("ACTION_ID"));
          actInfo.setActionOrder(resultSet.getBigDecimal("ACTION_ORDER"));

          //logger.debug(resultSet.getBigDecimal("ACTION_TRIGGER").toPlainString());
          actInfo.setActionTrigger(Constants.mapActionTriger(resultSet.getBigDecimal("ACTION_TRIGGER")));
          actInfo.setActionTriggerType(Constants.mapActionTrigerType(toInteger(resultSet.getBigDecimal("ACTION_TRIGGER_TYPE"), -1)));

          actInfo.setActionOperator(Constants.mapActionOperator(resultSet.getInt("ACTION_OPERATOR")));
          actInfo.setActionDay(resultSet.getBigDecimal("ACTION_DAY"));
          actInfo.setActionNumberDay(new BigDecimal(actInfo.getActionDay().intValue() * actInfo.getActionOperator().getValue()));
          actInfo.setActionPeriodType(Constants.mapActionPeriodType(resultSet.getBigDecimal("ACTION_PERIOD_TYPE")));
          actInfo.setActionPeriodValue(resultSet.getBigDecimal("ACTION_PERIOD_VALUE"));
          actInfo.setPreActionID(resultSet.getBigDecimal("PRE_ACTION_ID"));

          actInfo.setActionOwner(resultSet.getBigDecimal("ACTION_OWNER"));
          actInfo.setActionMode(Constants.mapActionMode(resultSet.getBigDecimal("ACTION_MODE").intValue()));
          actInfo.setActionType(Constants.mapActionType(resultSet.getBigDecimal("ACTION_TYPE").intValue()));
          actInfo.setActionPlanBoo(toCharcter(resultSet.getString("ACTION_PLAN_BOO"), 'N'));
          actInfo.setActionPlanDay(resultSet.getString("ACTION_PLAN_DAY"));
          actInfo.setActionTime(resultSet.getTime("ACTION_TIME"));
          actInfo.setActionPeriodBoo(toCharcter(resultSet.getString("ACTION_PERIOD_BOO"), 'N'));
          actInfo.setActivityLogBoo(toCharcter(resultSet.getString("ACTIVITY_LOG_BOO"), 'N'));
          actInfo.setUserNotifyBoo(toCharcter(resultSet.getString("USER_NOTIFY_BOO"), 'N'));
          actInfo.setNumberSkipAction(resultSet.getBigDecimal("ACTION_SKIP_COUNT"));
          //logger.debug(actInfo.toString());

          ActionDetail obj = response.getResponse().get(pathId);
          if (obj == null) {
            actInfo.setSequence(0);
            countRecord();
            obj = new ActionDetail();
            obj.setCollectionPathID(pathId);
            obj.setListActionInfo(new ArrayList<>());
            obj.getListActionInfo().add(actInfo);
            response.getResponse().put(pathId, obj);
          } else {
            int size = obj.getListActionInfo().size();
            actInfo.setSequence(size);
            obj.getListActionInfo().add(actInfo);
          }
        }
      }
      Integer code = (Integer) statement.getObject(1);
      //logger.debug("code = " + code);
    }

    @Override
    protected ActionDetailResponse createResponse() {
      return new ActionDetailResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      return new StringBuilder("{call CL_TM_GET_ACTION (?)}");
    }

    protected ActionDetailResponse query() {
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public ActionDetailResponse queryActionDetail() {
    return new QueryActionDetail(logger).query();
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="get action skip">
  public class ActionSkipInfo {

    //t1.ACTION_ID ,t1.CALENDAR_TYPE,t1.SYSTEM_ID, t1.DATETIME_FROM, t1.DATETIME_TO ,  t2.ACTION_ID SYSTEM_MANTENANCE
    private BigDecimal actionId;
    private Constants.CalendarType calendarType;
    private BigDecimal systemId;
    private Long dateTimeFrom;
    private Long dateTimeTo;

    public BigDecimal getActionId() {
      return actionId;
    }

    public void setActionId(BigDecimal actionId) {
      this.actionId = actionId;
    }

    public Constants.CalendarType getCalendarType() {
      return calendarType;
    }

    public void setCalendarType(Constants.CalendarType calendarType) {
      this.calendarType = calendarType;
    }

    public BigDecimal getSystemId() {
      return systemId;
    }

    public void setSystemId(BigDecimal systemId) {
      this.systemId = systemId;
    }

    public Long getDateTimeFrom() {
      return dateTimeFrom;
    }

    public void setDateTimeFrom(Long dateTimeFrom) {
      this.dateTimeFrom = dateTimeFrom;
    }

    public Long getDateTimeTo() {
      return dateTimeTo;
    }

    public void setDateTimeTo(Long dateTimeTo) {
      this.dateTimeTo = dateTimeTo;
    }

    @Override
    public String toString() {
      return "ActionSkipInfo{" + "actionId=" + actionId + ", calendarType=" + calendarType + ", systemId=" + systemId + ", dateTimeFrom=" + dateTimeFrom + ", dateTimeTo=" + dateTimeTo + '}';
    }

  }

  public class ActionSkipDetail {

    private BigDecimal actionId;
    private ArrayList<ActionSkipInfo> listActionSkipInfo;

    public BigDecimal getActionId() {
      return actionId;
    }

    public void setActionId(BigDecimal actionId) {
      this.actionId = actionId;
    }

    public ArrayList<ActionSkipInfo> getListActionSkipInfo() {
      return listActionSkipInfo;
    }

    public void setListActionSkipInfo(ArrayList<ActionSkipInfo> listActionSkipInfo) {
      this.listActionSkipInfo = listActionSkipInfo;
    }

    @Override
    public String toString() {
      return "ActionSkipDetail{" + "actionId=" + actionId + ", listActionSkipInfo=" + listActionSkipInfo + '}';
    }

  }

  public class ActionSkipDetailResponse extends DBTemplatesResponse<HashMap<BigDecimal, ActionSkipDetail>> {

    @Override
    protected HashMap<BigDecimal, ActionSkipDetail> createResponse() {
      return new HashMap<>();
    }
  }

  protected class QueryActionSkipDetail extends DBTemplateCallableStatement<ActionSkipDetailResponse, UtilityLogger, DBConnectionPools> {

    public QueryActionSkipDetail(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected void setParameter() throws SQLException {
      statement.registerOutParameter(1, java.sql.Types.INTEGER);
    }

    @Override

    protected void setReturnValue() throws SQLException {
      if (resultSet != null) {
        while (resultSet.next()) {
          ActionSkipInfo aaa = new ActionSkipInfo();
          //t1.ACTION_ID ,t1.CALENDAR_TYPE,t1.SYSTEM_ID, t1.DATETIME_FROM, t1.DATETIME_TO ,  t2.ACTION_ID SYSTEM_MAINTENANCE

          BigDecimal actionId = resultSet.getBigDecimal("ACTION_ID");
          aaa.setActionId(actionId);
          aaa.setCalendarType(Constants.mapCalendarType(toInteger(resultSet.getBigDecimal("CALENDAR_TYPE"), -9999)));
          aaa.setSystemId(resultSet.getBigDecimal("SYSTEM_ID"));
          aaa.setDateTimeFrom(toLong(resultSet.getDate("DATETIME_FROM"), "yyyyMMddhhmm", null));
          aaa.setDateTimeTo(toLong(resultSet.getDate("DATETIME_TO"), "yyyyMMddhhmm", null));

          ActionSkipDetail obj = response.getResponse().get(actionId);
          if (obj == null) {
            countRecord();
            obj = new ActionSkipDetail();
            obj.setActionId(actionId);
            obj.setListActionSkipInfo(new ArrayList<>());
            obj.getListActionSkipInfo().add(aaa);
            response.getResponse().put(actionId, obj);
          } else {
            obj.getListActionSkipInfo().add(aaa);
          }
        }
      }
      Integer code = (Integer) statement.getObject(1);
      //logger.debug("code = " + code);
    }

    @Override
    protected ActionSkipDetailResponse createResponse() {
      return new ActionSkipDetailResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      return new StringBuilder("{call CL_TM_GET_ACTION_SKIP (?)}");
    }

    protected ActionSkipDetailResponse query() {
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public ActionSkipDetailResponse queryQueryActionSkipDetail() {
    return new QueryActionSkipDetail(logger).query();
  }
  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="Get ACTION LETTER HISTORY Info">

  public class ActionTLNLHistory {

    protected ActionTLNLHistory() {
    }
    private Constants.ActionType actionType;
    private Constants.ActionMode actionMode;
    private Date messageDate;
    private BigDecimal messageDateCount;
    private BigDecimal batchId;

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

    public BigDecimal getBatchId() {
      return batchId;
    }

    public void setBatchId(BigDecimal batchId) {
      this.batchId = batchId;
    }

  }

  public class ActionTLNLHistoryResponse extends DBTemplatesResponse<ArrayList<ActionTLNLHistory>> {

    @Override
    protected ArrayList<ActionTLNLHistory> createResponse() {
      return new ArrayList<>();
    }

  }

  protected class GetActionTLNLHistory extends DBTemplatesExecuteQuery<ActionTLNLHistoryResponse, UtilityLogger, DBConnectionPools> {

    public GetActionTLNLHistory(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected ActionTLNLHistoryResponse createResponse() {
      return new ActionTLNLHistoryResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append(" SELECT t1.ACTION_MODE,  t1.ACTION_TYPE , max(t1.MESSAGE_DATE) MESSAGE_DATE , datediff (dd ,  max(t1.MESSAGE_DATE) ,getdate())  MESSAGE_DATE_COUNT , max(t2.BATCH_ID) BATCH_ID").append(Constants.END_LINE);
      sql.append(" FROM ( ").append(Constants.END_LINE);
      sql.append("   SELECT a1.MESSAGE_ID , a1.ACTION_MODE,  a1.ACTION_TYPE ,  a2.MESSAGE_DATE  ").append(Constants.END_LINE);
      sql.append("   FROM (").append(Constants.END_LINE);
      sql.append("     SELECT z1.TREATMENT_ID, z1.CA_NO, z1.BA_NO, z1.ACTION_ID, z1.ACTION_MODE, z1.ACTION_TYPE, z2.TREATMENT_ID, z2.MESSAGE_ID  ").append(Constants.END_LINE);
      sql.append("     FROM (").append(Constants.END_LINE);
      sql.append("       SELECT t1.TREATMENT_ID,t1.CA_NO , t1.BA_NO, t2.ACTION_ID ,t2.ACTION_MODE,  t2.ACTION_TYPE").append(Constants.END_LINE);
      sql.append("       FROM (").append(Constants.END_LINE);
      sql.append("         SELECT TREATMENT_ID,CA_NO , BA_NO, ACTION_ID ").append(Constants.END_LINE);
      sql.append("         FROM CL_TREATMENT").append(Constants.END_LINE);
      sql.append("         WHERE  CA_NO = '").append(caNo).append("' and BA_NO = '").append(baNo).append("' and MIN_DUE_DATE = convert(date ,'").append(minDueDate).append("' ,112 ) and ACTION_STATUS in ( ").append(Constants.getStringActionStatusDone()).append(") ").append(Constants.END_LINE);
      sql.append("       ) t1 ,  (").append(Constants.END_LINE);
      sql.append("         SELECT ACTION_ID,  ACTION_MODE, ACTION_TYPE ").append(Constants.END_LINE);
      sql.append("         FROM CL_ACTION").append(Constants.END_LINE);
      sql.append("         WHERE ACTION_TYPE = 11 or ACTION_TYPE = 13").append(Constants.END_LINE);
      sql.append("       ) t2").append(Constants.END_LINE);
      sql.append("       WHERE  t1.ACTION_ID = t2.ACTION_ID").append(Constants.END_LINE);
      sql.append("     ) z1 left join (       ").append(Constants.END_LINE);
      sql.append("       SELECT TREATMENT_ID, MESSAGE_ID").append(Constants.END_LINE);
      sql.append("       FROM CL_MESSAGE_TREATMENT").append(Constants.END_LINE);
      sql.append("     ) z2 on z1.TREATMENT_ID = z2.TREATMENT_ID").append(Constants.END_LINE);
      sql.append("   ) a1 left join CL_MESSAGE a2 on a1.MESSAGE_ID = a2.MESSAGE_ID  ").append(Constants.END_LINE);
      sql.append(" ) t1 left join dbo.CL_LETTER t2 on t1.MESSAGE_ID = t2.MESSAGE_ID").append(Constants.END_LINE);
      sql.append(" GROUP BY  t1.ACTION_MODE,  t1.ACTION_TYPE ").append(Constants.END_LINE);
      return sql;
    }

    private String caNo;
    private String baNo;
    private String minDueDate;

    @Override
    protected void setReturnValue(ResultSet rs) throws SQLException {

    }

    protected ActionTLNLHistoryResponse execute(String caNo, String baNo, Date minDueDate) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      this.caNo = caNo;
      this.baNo = baNo;
      this.minDueDate = sdf.format(minDueDate);
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public ActionTLNLHistoryResponse getActionTLNLHistory(String caNo, String baNo, Date minDueDate) {
    return new GetActionTLNLHistory(logger).execute(caNo, baNo, minDueDate);
  }

  //</editor-fold>
  //
  //
  public static void main(String[] args) {
    CNFDatabase cnf = new CNFDatabase("D:\\Work\\Ais\\Apps\\Batch\\configuration\\database.properties");

    Context ctx = new Context();
    ctx.initailLogger("LoggerMasterBatchInfo", "XXXX|YYYYY");

    DBConnectionPools<CNFDatabase, UtilityLogger> ds = new DBConnectionPools<>(cnf, ctx.getLogger());
    ds.buildeDataSource();
    //ds.getConnection();
    //ds.close();

    CLAction mmmm = new CLAction(ctx.getLogger());
    ActionDetailResponse result = mmmm.queryActionDetail();

    ctx.getLogger().debug(result.info().toString());

    Iterator<BigDecimal> key = result.getResponse().keySet().iterator();

    while (key.hasNext()) {
      BigDecimal kkkkk = key.next();
      ActionDetail mm = result.getResponse().get(kkkkk);

      ctx.getLogger().debug("path : " + mm.getCollectionPathID());

    }

    new DBConnectionPools<>(cnf, ctx.getLogger()).closeDataSource();
  }

}


/*

  protected class QueryActionDetail extends DBTemplatesExecuteQuery<ActionDetailResponse, UtilityLogger> {

    public QueryActionDetail(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected void setReturnValue(ResultSet rs) throws SQLException {
      ActionInfo actInfo = new ActionInfo();
      actInfo.setActionPathID(rs.getBigDecimal("ACTION_PATH_ID"));
      actInfo.setActionID(rs.getBigDecimal("ACTION_ID"));
      actInfo.setActionOrder(rs.getBigDecimal("ACTION_ORDER"));
      actInfo.setActionTrigger(Constants.mapActionTriger(rs.getBigDecimal("ACTION_TRIGGER").intValue()));
      actInfo.setActionTriggerType(Constants.mapActionTrigerType(rs.getBigDecimal("ACTION_TRIGGER_TYPE").intValue()));
      actInfo.setActionOperator(Constants.mapActionOperator(rs.getString("ACTION_OPERATOR")));
      actInfo.setActionDay(rs.getBigDecimal("ACTION_DAY"));
      actInfo.setPreActionID(rs.getBigDecimal("PRE_ACTION_ID"));
      actInfo.setActionMode(Constants.mapActionMode(rs.getBigDecimal("ACTION_MODE").intValue()));
      actInfo.setActionType(Constants.mapActionType(rs.getBigDecimal("ACTION_TYPE").intValue()));
      actInfo.setActionPlanBoo(toCharcter(rs.getString("ACTION_PLAN_BOO"), 'N'));
      actInfo.setActionPlanDay(rs.getString("ACTION_PLAN_DAY"));
      actInfo.setActionTime(rs.getTime("ACTION_TIME"));
      //actInfo.setActionPeriodBoo(toCharcter(rs.getString("ACTION_PERIOD_BOO"), 'N'));
      actInfo.setActivityLogBoo(toCharcter(rs.getString("ACTIVITY_LOG_BOO"), 'N'));
      actInfo.setUserNotifyBoo(toCharcter(rs.getString("USER_NOTIFY_BOO"), 'N'));
      actInfo.setNumberSkipAction(rs.getBigDecimal("SKIP_COUNT"));
      //logger.debug(actInfo.toString());
      BigDecimal pathId = rs.getBigDecimal("COLLECTION_PATH_ID");
      ActionDetail obj = response.getResponse().get(pathId);
      if (obj == null) {
        obj = new ActionDetail();
        obj.setCollectionPathID(pathId);
        obj.setListActionInfo(new ArrayList<>());
        obj.getListActionInfo().add(actInfo);
        response.getResponse().put(pathId, obj);
      } else {
        obj.getListActionInfo().add(actInfo);
      }

    }

    @Override
    protected ActionDetailResponse createResponse() {
      return new ActionDetailResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();

      sql.append(" SELECT t1.COLLECTION_PATH_ID, ");
      sql.append("  t2.ACTION_PATH_ID,  t2.ACTION_ID, t2.ACTION_ORDER, t2.ACTION_TRIGGER ,t2.ACTION_TRIGGER_TYPE, t2.ACTION_OPERATOR, t2.ACTION_DAY,t2.PRE_ACTION_ID , ");
      sql.append("  t3.ACTION_MODE, t3.ACTION_TYPE, t3.ACTION_PLAN_BOO, t3.ACTION_PLAN_DAY, t3.ACTION_TIME, t3.ACTION_PERIOD_BOO, t3.ACTIVITY_LOG_BOO, t3.USER_NOTIFY_BOO , t3.SKIP_COUNT ");
      sql.append(" from ( ");
      sql.append("     SELECT COLLECTION_PATH_ID, COLLECTION_PATH_OWNER  ");
      sql.append("     FROM  CL_COLLECTION_PATH  ");
      sql.append("     WHERE  getdate() between EFFECT_START_DATE and  isnull(EFFECT_END_DATE,getdate()) ");
      sql.append(" ) t1 , ( ");
      sql.append("     select  x1.* , CONDITION_1 ACTION_TRIGGER_TYPE ");
      sql.append("     from ( ");
      sql.append("         SELECT ACTION_PATH_ID, COLLECTION_PATH_ID, ACTION_ID, ACTION_ORDER, ACTION_TRIGGER, ACTION_OPERATOR, ACTION_DAY, ACTION_PERIOD_TYPE, ACTION_PERIOD_VALUE, PRE_ACTION_ID ");
      sql.append("         FROM CL_ACTION_PATH  ");
      sql.append("         WHERE RECORD_END_DTM is null and getdate() between EFFECT_START_DATE and  isnull(EFFECT_END_DATE,getdate()) ");
      sql.append("     ) x1 left join ( ");
      sql.append("         SELECT  LOV_KEYVALUE,  CONDITION_1   ");
      sql.append("         FROM  CL_CFG_LOV ");
      sql.append("         WHERE LOV_KEYWORD = 'ACTION_TRIGGER' ");
      sql.append("     ) x2 on x1.ACTION_TRIGGER = x2.LOV_KEYVALUE ");
      sql.append(" ) t2 , ( ");
      sql.append("     SELECT y1.* , isnull(y2.SKIP_COUNT , 0 )  SKIP_COUNT ");
      sql.append("     FROM ( ");
      sql.append("         SELECT ACTION_ID, ACTION_OWNER, ACTION_MODE, ACTION_TYPE, ACTION_PLAN_BOO, ACTION_PLAN_DAY, ACTION_TIME, ACTION_PERIOD_BOO, ACTIVITY_LOG_BOO, USER_NOTIFY_BOO, RECORD_ORDER, RECORD_STATUS  ");
      sql.append("         FROM CL_ACTION  ");
      sql.append("         WHERE RECORD_STATUS = 1 ");
      sql.append("     ) y1  left join ( ");
      sql.append("         SELECT  ACTION_ID ,count(*) SKIP_COUNT ");
      sql.append("         FROM CL_ACTION_SKIP ");
      sql.append("         WHERE  getdate() between EFFECT_START_DATE and  isnull(EFFECT_END_DATE,getdate()) ");
      sql.append("     ) y2 on y1.ACTION_ID = y2.ACTION_ID  ");
      sql.append(" ) t3  ");
      sql.append(" where t1.COLLECTION_PATH_ID = t2.COLLECTION_PATH_ID and t2.ACTION_ID = t3.ACTION_ID ");
      sql.append(" order by COLLECTION_PATH_ID , ACTION_ORDER ");

      return sql;
    }

    protected ActionDetailResponse query() {
      return executeQuery(getConnection(), true);
    }
  }


 */
