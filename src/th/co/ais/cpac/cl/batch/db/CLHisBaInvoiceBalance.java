/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.db;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import th.co.ais.cpac.cl.batch.Constants;
import th.co.ais.cpac.cl.common.UtilityLogger;
import th.co.ais.cpac.cl.template.database.DBConnectionPools;
import th.co.ais.cpac.cl.template.database.DBTemplatesExecuteQuery;
import th.co.ais.cpac.cl.template.database.DBTemplatesResponse;

/**
 *
 * @author Optimus
 */
public class CLHisBaInvoiceBalance {

  public CLHisBaInvoiceBalance(UtilityLogger logger) {
    this.logger = logger;
  }
  protected final UtilityLogger logger;

  //<editor-fold defaultstate="collapsed" desc="Get CLHisBaInvoiceBalance ">
  public class CLHisBaInvoiceBalanceInfo {

    protected CLHisBaInvoiceBalanceInfo() {
    }
    private BigDecimal baInvoiceBalanceId;
    private BigDecimal arMovementId;
    private String baNumber;
    private BigDecimal invoiceId;
    private Date processDtm;
    private Date movementDate;
    private BigDecimal movementSeq;
    private String movementType;
    private String movementFlg;
    private String refDocType;
    private BigDecimal refDocId;
    private Date refDocDate;
    private BigDecimal moveNumbernVatAmt;
    private BigDecimal moveExcVatAmt;
    private BigDecimal moveVatAmt;
    private BigDecimal moveTotalAmt;
    private BigDecimal invoiceNumbernVatBal;
    private BigDecimal invoiceExcVatBal;
    private BigDecimal invoiceVatBal;
    private BigDecimal invoiceTotalBal;
    private BigDecimal invoiceDisputeBal;
    private Date balanceStartDate;
    private Date balanceEndDate;
    private Date recordStartDtm;
    private Date recordEndDtm;
    private Character endDayBoo;
    private Character currentBoo;
    private Date created;
    private String createdBy;
    private Date lastUpd;
    private String lastUpdBy;

    public BigDecimal getBaInvoiceBalanceId() {
      return baInvoiceBalanceId;
    }

    public void setBaInvoiceBalanceId(BigDecimal baInvoiceBalanceId) {
      this.baInvoiceBalanceId = baInvoiceBalanceId;
    }

    public BigDecimal getArMovementId() {
      return arMovementId;
    }

    public void setArMovementId(BigDecimal arMovementId) {
      this.arMovementId = arMovementId;
    }

    public String getBaNumber() {
      return baNumber;
    }

    public void setBaNumber(String baNumber) {
      this.baNumber = baNumber;
    }

    public BigDecimal getInvoiceId() {
      return invoiceId;
    }

    public void setInvoiceId(BigDecimal invoiceId) {
      this.invoiceId = invoiceId;
    }

    public Date getProcessDtm() {
      return processDtm;
    }

    public void setProcessDtm(Date processDtm) {
      this.processDtm = processDtm;
    }

    public Date getMovementDate() {
      return movementDate;
    }

    public void setMovementDate(Date movementDate) {
      this.movementDate = movementDate;
    }

    public BigDecimal getMovementSeq() {
      return movementSeq;
    }

    public void setMovementSeq(BigDecimal movementSeq) {
      this.movementSeq = movementSeq;
    }

    public String getMovementType() {
      return movementType;
    }

    public void setMovementType(String movementType) {
      this.movementType = movementType;
    }

    public String getMovementFlg() {
      return movementFlg;
    }

    public void setMovementFlg(String movementFlg) {
      this.movementFlg = movementFlg;
    }

    public String getRefDocType() {
      return refDocType;
    }

    public void setRefDocType(String refDocType) {
      this.refDocType = refDocType;
    }

    public BigDecimal getRefDocId() {
      return refDocId;
    }

    public void setRefDocId(BigDecimal refDocId) {
      this.refDocId = refDocId;
    }

    public Date getRefDocDate() {
      return refDocDate;
    }

    public void setRefDocDate(Date refDocDate) {
      this.refDocDate = refDocDate;
    }

    public BigDecimal getMoveNumbernVatAmt() {
      return moveNumbernVatAmt;
    }

    public void setMoveNumbernVatAmt(BigDecimal moveNumbernVatAmt) {
      this.moveNumbernVatAmt = moveNumbernVatAmt;
    }

    public BigDecimal getMoveExcVatAmt() {
      return moveExcVatAmt;
    }

    public void setMoveExcVatAmt(BigDecimal moveExcVatAmt) {
      this.moveExcVatAmt = moveExcVatAmt;
    }

    public BigDecimal getMoveVatAmt() {
      return moveVatAmt;
    }

    public void setMoveVatAmt(BigDecimal moveVatAmt) {
      this.moveVatAmt = moveVatAmt;
    }

    public BigDecimal getMoveTotalAmt() {
      return moveTotalAmt;
    }

    public void setMoveTotalAmt(BigDecimal moveTotalAmt) {
      this.moveTotalAmt = moveTotalAmt;
    }

    public BigDecimal getInvoiceNumbernVatBal() {
      return invoiceNumbernVatBal;
    }

    public void setInvoiceNumbernVatBal(BigDecimal invoiceNumbernVatBal) {
      this.invoiceNumbernVatBal = invoiceNumbernVatBal;
    }

    public BigDecimal getInvoiceExcVatBal() {
      return invoiceExcVatBal;
    }

    public void setInvoiceExcVatBal(BigDecimal invoiceExcVatBal) {
      this.invoiceExcVatBal = invoiceExcVatBal;
    }

    public BigDecimal getInvoiceVatBal() {
      return invoiceVatBal;
    }

    public void setInvoiceVatBal(BigDecimal invoiceVatBal) {
      this.invoiceVatBal = invoiceVatBal;
    }

    public BigDecimal getInvoiceTotalBal() {
      return invoiceTotalBal;
    }

    public void setInvoiceTotalBal(BigDecimal invoiceTotalBal) {
      this.invoiceTotalBal = invoiceTotalBal;
    }

    public BigDecimal getInvoiceDisputeBal() {
      return invoiceDisputeBal;
    }

    public void setInvoiceDisputeBal(BigDecimal invoiceDisputeBal) {
      this.invoiceDisputeBal = invoiceDisputeBal;
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

    public Character getEndDayBoo() {
      return endDayBoo;
    }

    public void setEndDayBoo(Character endDayBoo) {
      this.endDayBoo = endDayBoo;
    }

    public Character getCurrentBoo() {
      return currentBoo;
    }

    public void setCurrentBoo(Character currentBoo) {
      this.currentBoo = currentBoo;
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

  public class CLHisBaInvoiceBalanceInfoResponse extends DBTemplatesResponse<CLHisBaInvoiceBalanceInfo> {

    @Override
    protected CLHisBaInvoiceBalanceInfo createResponse() {
      return new CLHisBaInvoiceBalanceInfo();
    }
  }

  protected class GetCurrentCLHisBaInvoiceBalanceInfo extends DBTemplatesExecuteQuery<CLHisBaInvoiceBalanceInfoResponse, UtilityLogger, DBConnectionPools> {

    public GetCurrentCLHisBaInvoiceBalanceInfo(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected CLHisBaInvoiceBalanceInfoResponse createResponse() {
      return new CLHisBaInvoiceBalanceInfoResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();

      sql.append(" SELECT BA_INVOICE_BALANCE_ID, AR_MOVEMENT_ID, BA_NO, INVOICE_ID, PROCESS_DTM, MOVEMENT_DATE, MOVEMENT_SEQ, MOVEMENT_TYPE, ").append(Constants.END_LINE);
      sql.append("   MOVEMENT_FLG, REF_DOC_TYPE, REF_DOC_ID, REF_DOC_DATE, MOVE_NON_VAT_AMT, MOVE_EXC_VAT_AMT, MOVE_VAT_AMT, MOVE_TOTAL_AMT, ").append(Constants.END_LINE);
      sql.append("   INVOICE_NON_VAT_BAL, INVOICE_EXC_VAT_BAL, INVOICE_VAT_BAL, INVOICE_TOTAL_BAL, INVOICE_DISPUTE_BAL, BALANCE_START_DATE,  ").append(Constants.END_LINE);
      sql.append("   BALANCE_END_DATE, RECORD_START_DTM, RECORD_END_DTM, END_DAY_BOO, CURRENT_BOO, CREATED, CREATED_BY, LAST_UPD, LAST_UPD_BY ").append(Constants.END_LINE);
      sql.append(" FROM  CL_HIS_BA_INVOICE_BALANCE ").append(Constants.END_LINE);
      sql.append(" WHERE AR_MOVEMENT_ID = ").append(arMovementId.toPlainString()).append(Constants.END_LINE);
      sql.append(" AND CURRENT_BOO = 'Y' ");
      return sql;
    }

    private BigDecimal arMovementId = null;

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      CLHisBaInvoiceBalanceInfo temp = response.getResponse();
      temp.setBaInvoiceBalanceId(resultSet.getBigDecimal("BA_INVOICE_BALANCE_ID"));
      temp.setArMovementId(resultSet.getBigDecimal("AR_MOVEMENT_ID"));
      temp.setBaNumber(resultSet.getString("BA_NO"));
      temp.setInvoiceId(resultSet.getBigDecimal("INVOICE_ID"));
      temp.setProcessDtm(toDate(resultSet.getTimestamp("PROCESS_DTM"), null));
      temp.setMovementDate(toDate(resultSet.getTimestamp("MOVEMENT_DATE"), null));
      temp.setMovementSeq(resultSet.getBigDecimal("MOVEMENT_SEQ"));
      temp.setMovementType(resultSet.getString("MOVEMENT_TYPE"));
      temp.setMovementFlg(resultSet.getString("MOVEMENT_FLG"));
      temp.setRefDocType(resultSet.getString("REF_DOC_TYPE"));
      temp.setRefDocId(resultSet.getBigDecimal("REF_DOC_ID"));
      temp.setRefDocDate(toDate(resultSet.getTimestamp("REF_DOC_DATE"), null));
      temp.setMoveNumbernVatAmt(resultSet.getBigDecimal("MOVE_NON_VAT_AMT"));
      temp.setMoveExcVatAmt(resultSet.getBigDecimal("MOVE_EXC_VAT_AMT"));
      temp.setMoveVatAmt(resultSet.getBigDecimal("MOVE_VAT_AMT"));
      temp.setMoveTotalAmt(resultSet.getBigDecimal("MOVE_TOTAL_AMT"));
      temp.setInvoiceNumbernVatBal(resultSet.getBigDecimal("INVOICE_NON_VAT_BAL"));
      temp.setInvoiceExcVatBal(resultSet.getBigDecimal("INVOICE_EXC_VAT_BAL"));
      temp.setInvoiceVatBal(resultSet.getBigDecimal("INVOICE_VAT_BAL"));
      temp.setInvoiceTotalBal(resultSet.getBigDecimal("INVOICE_TOTAL_BAL"));
      temp.setInvoiceDisputeBal(resultSet.getBigDecimal("INVOICE_DISPUTE_BAL"));
      temp.setBalanceStartDate(toDate(resultSet.getTimestamp("BALANCE_START_DATE"), null));
      temp.setBalanceEndDate(toDate(resultSet.getTimestamp("BALANCE_END_DATE"), null));
      temp.setRecordStartDtm(toDate(resultSet.getTimestamp("RECORD_START_DTM"), null));
      temp.setRecordEndDtm(toDate(resultSet.getTimestamp("RECORD_END_DTM"), null));
      temp.setEndDayBoo(toCharcter(resultSet.getString("END_DAY_BOO"), 'N'));
      temp.setCurrentBoo(toCharcter(resultSet.getString("CURRENT_BOO"), 'N'));
      temp.setCreated(toDate(resultSet.getTimestamp("CREATED"), null));
      temp.setCreatedBy(resultSet.getString("CREATED_BY"));
      temp.setLastUpd(toDate(resultSet.getTimestamp("LAST_UPD"), null));
      temp.setLastUpdBy(resultSet.getString("LAST_UPD_BY"));
    }

    protected CLHisBaInvoiceBalanceInfoResponse execute(BigDecimal arMovementId) {
      this.arMovementId = arMovementId;
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public CLHisBaInvoiceBalanceInfoResponse getCurrentCLHisBaInvoiceBalanceInfo(BigDecimal arMovementId) {
    return new GetCurrentCLHisBaInvoiceBalanceInfo(logger).execute(arMovementId);
  }

  //</editor-fold>
}
