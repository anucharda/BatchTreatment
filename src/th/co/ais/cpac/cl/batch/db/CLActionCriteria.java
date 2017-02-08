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
import java.util.Date;
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
public class CLActionCriteria {

  protected final UtilityLogger logger;

  public CLActionCriteria(UtilityLogger logger) {
    this.logger = logger;
  }

  //<editor-fold defaultstate="collapsed" desc="get cl_action_criteria">
  public class ActionCriteriaInfo {

    public ActionCriteriaInfo() {
    }
    private BigDecimal actionCriteriaId;
    private BigDecimal actionId;
    private BigDecimal criteriaId;
    private BigDecimal criteriaPriority;
    private BigDecimal criteriaLevel;
    private BigDecimal confirmMode;
    private BigDecimal assignType;
    private BigDecimal assignSubtype;
    private BigDecimal assignMode;
    private String campaignCode;
    private BigDecimal smsGpbMode;
    private BigDecimal smsOvcMode;
    private Character ivrPresdBoo;
    private BigDecimal ivrPresdDay;
    private BigDecimal debtTypeId;
    private Character userNumbertifyBoo;
    private Character unqualifyBoo;
    private Date effectStartDate;
    private Date effectEndDate;
    private Date recordStartDtm;
    private Date recordEndDtm;
    private BigDecimal recordOrder;
    private Date created;
    private String createdBy;
    private Date lastUpd;
    private String lastUpdBy;

    public BigDecimal getActionCriteriaId() {
      return actionCriteriaId;
    }

    public void setActionCriteriaId(BigDecimal actionCriteriaId) {
      this.actionCriteriaId = actionCriteriaId;
    }

    public BigDecimal getActionId() {
      return actionId;
    }

    public void setActionId(BigDecimal actionId) {
      this.actionId = actionId;
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

    public BigDecimal getCriteriaLevel() {
      return criteriaLevel;
    }

    public void setCriteriaLevel(BigDecimal criteriaLevel) {
      this.criteriaLevel = criteriaLevel;
    }

    public BigDecimal getConfirmMode() {
      return confirmMode;
    }

    public void setConfirmMode(BigDecimal confirmMode) {
      this.confirmMode = confirmMode;
    }

    public BigDecimal getAssignType() {
      return assignType;
    }

    public void setAssignType(BigDecimal assignType) {
      this.assignType = assignType;
    }

    public BigDecimal getAssignSubtype() {
      return assignSubtype;
    }

    public void setAssignSubtype(BigDecimal assignSubtype) {
      this.assignSubtype = assignSubtype;
    }

    public BigDecimal getAssignMode() {
      return assignMode;
    }

    public void setAssignMode(BigDecimal assignMode) {
      this.assignMode = assignMode;
    }

    public String getCampaignCode() {
      return campaignCode;
    }

    public void setCampaignCode(String campaignCode) {
      this.campaignCode = campaignCode;
    }

    public BigDecimal getSmsGpbMode() {
      return smsGpbMode;
    }

    public void setSmsGpbMode(BigDecimal smsGpbMode) {
      this.smsGpbMode = smsGpbMode;
    }

    public BigDecimal getSmsOvcMode() {
      return smsOvcMode;
    }

    public void setSmsOvcMode(BigDecimal smsOvcMode) {
      this.smsOvcMode = smsOvcMode;
    }

    public Character getIvrPresdBoo() {
      return ivrPresdBoo;
    }

    public void setIvrPresdBoo(Character ivrPresdBoo) {
      this.ivrPresdBoo = ivrPresdBoo;
    }

    public BigDecimal getIvrPresdDay() {
      return ivrPresdDay;
    }

    public void setIvrPresdDay(BigDecimal ivrPresdDay) {
      this.ivrPresdDay = ivrPresdDay;
    }

    public BigDecimal getDebtTypeId() {
      return debtTypeId;
    }

    public void setDebtTypeId(BigDecimal debtTypeId) {
      this.debtTypeId = debtTypeId;
    }

    public Character getUserNumbertifyBoo() {
      return userNumbertifyBoo;
    }

    public void setUserNumbertifyBoo(Character userNumbertifyBoo) {
      this.userNumbertifyBoo = userNumbertifyBoo;
    }

    public Character getUnqualifyBoo() {
      return unqualifyBoo;
    }

    public void setUnqualifyBoo(Character unqualifyBoo) {
      this.unqualifyBoo = unqualifyBoo;
    }

    public Date getEffectStartDate() {
      return effectStartDate;
    }

    public void setEffectStartDate(Date effectStartDate) {
      this.effectStartDate = effectStartDate;
    }

    public Date getEffectEndDate() {
      return effectEndDate;
    }

    public void setEffectEndDate(Date effectEndDate) {
      this.effectEndDate = effectEndDate;
    }

    public Date getRecordStartDtm() {
      return recordStartDtm;
    }

    public void setRecordStartDtm(Date recordStartDtm) {
      this.recordStartDtm = recordStartDtm;
    }

    public Date getRecordEndDtm() {
      return recordEndDtm;
    }

    public void setRecordEndDtm(Date recordEndDtm) {
      this.recordEndDtm = recordEndDtm;
    }

    public BigDecimal getRecordOrder() {
      return recordOrder;
    }

    public void setRecordOrder(BigDecimal recordOrder) {
      this.recordOrder = recordOrder;
    }

    public Date getCreated() {
      return created;
    }

    public void setCreated(Date created) {
      this.created = created;
    }

    public String getCreatedBy() {
      return createdBy;
    }

    public void setCreatedBy(String createdBy) {
      this.createdBy = createdBy;
    }

    public Date getLastUpd() {
      return lastUpd;
    }

    public void setLastUpd(Date lastUpd) {
      this.lastUpd = lastUpd;
    }

    public String getLastUpdBy() {
      return lastUpdBy;
    }

    public void setLastUpdBy(String lastUpdBy) {
      this.lastUpdBy = lastUpdBy;
    }

  }

  public class ActionCriteriaResponse extends DBTemplatesResponse<HashMap<String, ActionCriteriaInfo>> {

    @Override
    protected HashMap<String, ActionCriteriaInfo> createResponse() {
      return new HashMap<>();
    }

  }

  protected class GetActionCriteriaByActionId extends DBTemplatesExecuteQuery<ActionCriteriaResponse, UtilityLogger, DBConnectionPools> {

    public GetActionCriteriaByActionId(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected ActionCriteriaResponse createResponse() {
      return new ActionCriteriaResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      String actionList = "";
      StringBuilder sql = new StringBuilder();
      for (int i = 0; i < actionId.size(); i++) {
        if (i > 0) {
          actionList += ",";
        }
        actionList += actionId.get(i).toPlainString();
      }

      sql.append(" SELECT ");
      sql.append("   ACTION_CRITERIA_ID, ACTION_ID, CRITERIA_ID, CRITERIA_PRIORITY, CRITERIA_LEVEL, CONFIRM_MODE, ASSIGN_TYPE, ASSIGN_SUBTYPE, ASSIGN_MODE, CAMPAIGN_CODE,  ");
      sql.append("   SMS_GPB_MODE, SMS_OVC_MODE, IVR_PRESD_BOO, IVR_PRESD_DAY, DEBT_TYPE_ID, USER_NOTIFY_BOO, UNQUALIFY_BOO,  ");
      sql.append("   EFFECT_START_DATE, EFFECT_END_DATE, RECORD_START_DTM, RECORD_END_DTM, RECORD_ORDER, CREATED, CREATED_BY, LAST_UPD, LAST_UPD_BY  ");
      sql.append(" FROM dbo.CL_ACTION_CRITERIA ");
      sql.append(" WHERE ACTION_ID in (").append(actionList).append(") ");

      return sql;
    }

    private ArrayList<BigDecimal> actionId;

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      ActionCriteriaInfo temp = new ActionCriteriaInfo();
      temp.setActionCriteriaId(resultSet.getBigDecimal("ACTION_CRITERIA_ID"));
      temp.setActionId(resultSet.getBigDecimal("ACTION_ID"));
      temp.setCriteriaId(resultSet.getBigDecimal("CRITERIA_ID"));
      temp.setCriteriaPriority(resultSet.getBigDecimal("CRITERIA_PRIORITY"));
      temp.setCriteriaLevel(resultSet.getBigDecimal("CRITERIA_LEVEL"));
      temp.setConfirmMode(resultSet.getBigDecimal("CONFIRM_MODE"));
      temp.setAssignType(resultSet.getBigDecimal("ASSIGN_TYPE"));
      temp.setAssignSubtype(resultSet.getBigDecimal("ASSIGN_SUBTYPE"));
      temp.setAssignMode(resultSet.getBigDecimal("ASSIGN_MODE"));
      temp.setCampaignCode(resultSet.getString("CAMPAIGN_CODE"));
      temp.setSmsGpbMode(resultSet.getBigDecimal("SMS_GPB_MODE"));
      temp.setSmsOvcMode(resultSet.getBigDecimal("SMS_OVC_MODE"));
      temp.setIvrPresdBoo(toCharcter(resultSet.getString("IVR_PRESD_BOO"), 'N'));
      temp.setIvrPresdDay(resultSet.getBigDecimal("IVR_PRESD_DAY"));
      temp.setDebtTypeId(resultSet.getBigDecimal("DEBT_TYPE_ID"));
      temp.setUserNumbertifyBoo(toCharcter(resultSet.getString("USER_NOTIFY_BOO"), 'N'));
      temp.setUnqualifyBoo(toCharcter(resultSet.getString("UNQUALIFY_BOO"), 'N'));
      temp.setEffectStartDate(toDate(resultSet.getTimestamp("EFFECT_START_DATE"), null));
      temp.setEffectEndDate(toDate(resultSet.getTimestamp("EFFECT_END_DATE"), null));
      temp.setRecordStartDtm(toDate(resultSet.getTimestamp("RECORD_START_DTM"), null));
      temp.setRecordEndDtm(toDate(resultSet.getTimestamp("RECORD_END_DTM"), null));
      temp.setRecordOrder(resultSet.getBigDecimal("RECORD_ORDER"));
      temp.setCreated(toDate(resultSet.getTimestamp("CREATED"), null));
      temp.setCreatedBy(resultSet.getString("CREATED_BY"));
      temp.setLastUpd(toDate(resultSet.getTimestamp("LAST_UPD"), null));
      temp.setLastUpdBy(resultSet.getString("LAST_UPD_BY"));
      String key = temp.getActionId().toPlainString() + "_" + temp.getActionCriteriaId();
      response.getResponse().put(key, temp);

    }

    protected ActionCriteriaResponse execute(ArrayList<BigDecimal> actionId) {

      this.actionId = actionId;
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public ActionCriteriaResponse getActionCriteriaByActionId(ArrayList<BigDecimal> actionId) {
    return new GetActionCriteriaByActionId(logger).execute(actionId);
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="get cl_action_criteria and CL_ORDER_CRITERIA  ">
  public class ActionOrderCriteria {

    public ActionOrderCriteria() {
    }
    private BigDecimal orderCriteriaId;
    private BigDecimal actionCriteriaId;
    private String orderType;
    private String orderReason;
    private String suspendType;
    private BigDecimal blacklistOption;
    private String blacklistType;
    private String blacklistSubtype;
    private String blacklistReason;
    private Character exemptBoo;
    private BigDecimal exemptReasonId;
    private BigDecimal exemptPackId;
    private BigDecimal actionId;
    private BigDecimal criteriaId;

    public BigDecimal getOrderCriteriaId() {
      return orderCriteriaId;
    }

    public void setOrderCriteriaId(BigDecimal orderCriteriaId) {
      this.orderCriteriaId = orderCriteriaId;
    }

    public BigDecimal getActionCriteriaId() {
      return actionCriteriaId;
    }

    public void setActionCriteriaId(BigDecimal actionCriteriaId) {
      this.actionCriteriaId = actionCriteriaId;
    }

    public BigDecimal getBlacklistOption() {
      return blacklistOption;
    }

    public void setBlacklistOption(BigDecimal blacklistOption) {
      this.blacklistOption = blacklistOption;
    }

    public String getBlacklistType() {
      return blacklistType;
    }

    public void setBlacklistType(String blacklistType) {
      this.blacklistType = blacklistType;
    }

    public String getBlacklistSubtype() {
      return blacklistSubtype;
    }

    public void setBlacklistSubtype(String blacklistSubtype) {
      this.blacklistSubtype = blacklistSubtype;
    }

    public String getBlacklistReason() {
      return blacklistReason;
    }

    public void setBlacklistReason(String blacklistReason) {
      this.blacklistReason = blacklistReason;
    }

    public Character getExemptBoo() {
      return exemptBoo;
    }

    public void setExemptBoo(Character exemptBoo) {
      this.exemptBoo = exemptBoo;
    }

    public BigDecimal getExemptReasonId() {
      return exemptReasonId;
    }

    public void setExemptReasonId(BigDecimal exemptReasonId) {
      this.exemptReasonId = exemptReasonId;
    }

    public BigDecimal getExemptPackId() {
      return exemptPackId;
    }

    public void setExemptPackId(BigDecimal exemptPackId) {
      this.exemptPackId = exemptPackId;
    }

    public BigDecimal getActionId() {
      return actionId;
    }

    public void setActionId(BigDecimal actionId) {
      this.actionId = actionId;
    }

    public BigDecimal getCriteriaId() {
      return criteriaId;
    }

    public void setCriteriaId(BigDecimal criteriaId) {
      this.criteriaId = criteriaId;
    }

    public String getOrderType() {
      return orderType;
    }

    public void setOrderType(String orderType) {
      this.orderType = orderType;
    }

    public String getOrderReason() {
      return orderReason;
    }

    public void setOrderReason(String orderReason) {
      this.orderReason = orderReason;
    }

    public String getSuspendType() {
      return suspendType;
    }

    public void setSuspendType(String suspendType) {
      this.suspendType = suspendType;
    }

  }

  public class ActionOrderCriteriaResponse extends DBTemplatesResponse<HashMap<String, ActionOrderCriteria>> {

    @Override
    protected HashMap<String, ActionOrderCriteria> createResponse() {
      return new HashMap<>();
    }

  }

  protected class GetActionOrderCriteriaByActionId extends DBTemplatesExecuteQuery<ActionOrderCriteriaResponse, UtilityLogger, DBConnectionPools> {

    public GetActionOrderCriteriaByActionId(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected ActionOrderCriteriaResponse createResponse() {
      return new ActionOrderCriteriaResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      String actionList = "";
      StringBuilder sql = new StringBuilder();
      for (int i = 0; i < actionId.size(); i++) {
        if (i > 0) {
          actionList += ",";
        }
        actionList += actionId.get(i).toPlainString();
      }

      sql.append(" SELECT t2.* , t1.ACTION_ID, t1.CRITERIA_ID").append(Constants.END_LINE);
      sql.append(" FROM (").append(Constants.END_LINE);
      sql.append("   SELECT ").append(Constants.END_LINE);
      sql.append("     ACTION_CRITERIA_ID, ACTION_ID, CRITERIA_ID").append(Constants.END_LINE);
      sql.append("   FROM dbo.CL_ACTION_CRITERIA").append(Constants.END_LINE);
      sql.append("   WHERE ACTION_ID in ( ").append(actionList).append(")").append(Constants.END_LINE);
      sql.append(" )t1 left join (").append(Constants.END_LINE);
      sql.append("   SELECT ").append(Constants.END_LINE);
      sql.append("     ORDER_CRITERIA_ID, ACTION_CRITERIA_ID, ORDER_TYPE, ORDER_REASON, SUSPEND_TYPE, ").append(Constants.END_LINE);
      sql.append("     BLACKLIST_OPTION, BLACKLIST_TYPE, BLACKLIST_SUBTYPE, BLACKLIST_REASON, ").append(Constants.END_LINE);
      sql.append("     EXEMPT_BOO, EXEMPT_REASON_ID, EXEMPT_PACK_ID").append(Constants.END_LINE);
      sql.append("   FROM dbo.CL_ORDER_CRITERIA").append(Constants.END_LINE);
      sql.append("   WHERE RECORD_END_DTM IS NULL").append(Constants.END_LINE);
      sql.append(" )t2 on t1.ACTION_CRITERIA_ID = t2.ACTION_CRITERIA_ID ").append(Constants.END_LINE);
      sql.append(" WHERE t2.ACTION_CRITERIA_ID IS NOT NULL").append(Constants.END_LINE);
      return sql;
    }

    private ArrayList<BigDecimal> actionId;

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      ActionOrderCriteria temp = new ActionOrderCriteria();

      temp.setOrderCriteriaId(resultSet.getBigDecimal("ORDER_CRITERIA_ID"));
      temp.setActionCriteriaId(resultSet.getBigDecimal("ACTION_CRITERIA_ID"));

      temp.setOrderType(resultSet.getString("ORDER_TYPE"));
      temp.setOrderReason(resultSet.getString("ORDER_REASON"));
      temp.setSuspendType(resultSet.getString("SUSPEND_TYPE"));

      temp.setBlacklistOption(resultSet.getBigDecimal("BLACKLIST_OPTION"));
      temp.setBlacklistType(resultSet.getString("BLACKLIST_TYPE"));
      temp.setBlacklistSubtype(resultSet.getString("BLACKLIST_SUBTYPE"));

      temp.setBlacklistReason(resultSet.getString("BLACKLIST_REASON"));

      temp.setExemptBoo(toCharcter(resultSet.getString("EXEMPT_BOO"), 'N'));
      temp.setExemptReasonId(resultSet.getBigDecimal("EXEMPT_REASON_ID"));
      temp.setExemptPackId(resultSet.getBigDecimal("EXEMPT_PACK_ID"));
      temp.setActionId(resultSet.getBigDecimal("ACTION_ID"));
      temp.setCriteriaId(resultSet.getBigDecimal("CRITERIA_ID"));

      String key = temp.getActionCriteriaId().toPlainString() + "_" + temp.getActionId().toPlainString() + "_" + temp.getCriteriaId().toPlainString();
      response.getResponse().put(key, temp);

    }

    protected ActionOrderCriteriaResponse execute(ArrayList<BigDecimal> actionId) {
      this.actionId = actionId;
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public ActionOrderCriteriaResponse getActionOrderCriteriaByActionId(ArrayList<BigDecimal> actionId) {
    return new GetActionOrderCriteriaByActionId(logger).execute(actionId);
  }

  //</editor-fold>
  //
}
