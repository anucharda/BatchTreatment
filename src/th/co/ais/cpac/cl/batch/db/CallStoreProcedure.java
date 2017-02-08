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
public class CallStoreProcedure {

  protected final UtilityLogger logger;

  public CallStoreProcedure(UtilityLogger logger) {
    this.logger = logger;
  }

  public class TempAccountBalance {

    public TempAccountBalance() {
    }

    private BigDecimal transId;
    private BigDecimal runningId;
    private BigDecimal runningStatus;
    private BigDecimal collectionPathId;
    private BigDecimal actionId;
    private BigDecimal actionPathId;
    private Date actionPathDttm;
    private Date actionScheduleDttm;
    private BigDecimal criteriaId;
    private BigDecimal actionCriteriaId;
    private String baNumber;
    private String caNumber;
    private String saNumber;
    private BigDecimal baStatusId;
    private Date baStatusDttm;
    private BigDecimal priorityStatusId;
    private Date priorityStatusDate;
    private BigDecimal countPriorityStatusDate;
    private String billZipcode;
    private BigDecimal billProvinceId;
    private BigDecimal billRegionId;
    private BigDecimal baAccountBalanceId;
    private BigDecimal baNonVatBalance;
    private BigDecimal baExcVatBalance;
    private BigDecimal baVatBalance;
    private BigDecimal baTotalBalance;
    private BigDecimal baDisputeBalance;
    private BigDecimal baExcessBalance;
    private BigDecimal dueNonVatBalance;
    private BigDecimal dueExcVatBalance;
    private BigDecimal dueVatBalance;
    private BigDecimal dueTotalBalance;
    private BigDecimal dueDisputeBalance;
    private BigDecimal contractPenalty;
    private Date minDueDate;
    private BigDecimal countMinDueDate;

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

    public Date getActionPathDttm() {
      return actionPathDttm;
    }

    public void setActionPathDttm(Date actionPathDttm) {
      this.actionPathDttm = actionPathDttm;
    }

    public Date getActionScheduleDttm() {
      return actionScheduleDttm;
    }

    public void setActionScheduleDttm(Date actionScheduleDttm) {
      this.actionScheduleDttm = actionScheduleDttm;
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

    public BigDecimal getBaStatusId() {
      return baStatusId;
    }

    public void setBaStatusId(BigDecimal baStatusId) {
      this.baStatusId = baStatusId;
    }

    public Date getBaStatusDttm() {
      return baStatusDttm;
    }

    public void setBaStatusDttm(Date baStatusDttm) {
      this.baStatusDttm = baStatusDttm;
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

    public BigDecimal getCountPriorityStatusDate() {
      return countPriorityStatusDate;
    }

    public void setCountPriorityStatusDate(BigDecimal countPriorityStatusDate) {
      this.countPriorityStatusDate = countPriorityStatusDate;
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

    public Date getMinDueDate() {
      return minDueDate;
    }

    public void setMinDueDate(Date minDueDate) {
      this.minDueDate = minDueDate;
    }

    public BigDecimal getCountMinDueDate() {
      return countMinDueDate;
    }

    public void setCountMinDueDate(BigDecimal countMinDueDate) {
      this.countMinDueDate = countMinDueDate;
    }

  }

  public class TempAccountBalanceResponse extends DBTemplatesResponse<ArrayList<TempAccountBalance>> {

    @Override
    protected ArrayList<TempAccountBalance> createResponse() {
      return new ArrayList<>();
    }
  }

  protected class GetListDriveAction extends DBTemplateCallableStatement<TempAccountBalanceResponse, UtilityLogger, DBConnectionPools> {

    public GetListDriveAction(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected void setParameter() throws SQLException {
      statement.setQueryTimeout(1800);
      statement.setBigDecimal(1, new BigDecimal(0));
      statement.setBigDecimal(2, new BigDecimal(System.nanoTime()));
      statement.registerOutParameter(3, java.sql.Types.INTEGER);
    }

    @Override
    protected void setReturnValue() throws SQLException {
      if (resultSet != null) {
        while (resultSet.next()) {
          countRecord();
          TempAccountBalance temp = new TempAccountBalance();
          temp.setRunningId(resultSet.getBigDecimal("TRANS_ID"));
          logger.debug(temp.getRunningId().toString());
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
      return new StringBuilder("{call CL_GET_DATA_DRIVE_ACTION (?, ?, ?)}");

    }

    protected TempAccountBalanceResponse query() {
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public TempAccountBalanceResponse query() {
    return new GetListDriveAction(logger).query();
  }

  public static void main(String[] args) {
    long k = System.currentTimeMillis();
    CNFDatabase cnf = new CNFDatabase("D:\\Work\\Ais\\Apps\\Batch\\configuration\\database.properties");

    Context ctx = new Context();
    ctx.initailLogger("LoggerMasterBatchInfo", "XXXX|YYYYY");

    new DBConnectionPools<>(cnf, ctx.getLogger()).buildeDataSource();

    CallStoreProcedure mmmm = new CallStoreProcedure(ctx.getLogger());
    TempAccountBalanceResponse datax = mmmm.query();
    ctx.getLogger().debug("record = " + datax.getRecordCount());

    new DBConnectionPools<>(cnf, ctx.getLogger()).closeDataSource();
    ctx.getLogger().debug("time : " + (System.currentTimeMillis() - k));
  }

}
