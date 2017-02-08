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
public class PMInvoice {

  protected final UtilityLogger logger;

  public PMInvoice(UtilityLogger logger) {
    this.logger = logger;
  }

  //
  //<editor-fold defaultstate="collapsed" desc="Get Parameter SMS From PM_INVOICE">
  public class SMSParameterValue {

    protected SMSParameterValue() {
    }
    private Date billStartDate;
    private Date billEndDate;
    private BigDecimal invoiceTotalAmt;

    public Date getBillStartDate() {
      return billStartDate;
    }

    public void setBillStartDate(Date billStartDate) {
      this.billStartDate = billStartDate;
    }

    public Date getBillEndDate() {
      return billEndDate;
    }

    public void setBillEndDate(Date billEndDate) {
      this.billEndDate = billEndDate;
    }

    public BigDecimal getInvoiceTotalAmt() {
      return invoiceTotalAmt;
    }

    public void setInvoiceTotalAmt(BigDecimal invoiceTotalAmt) {
      this.invoiceTotalAmt = invoiceTotalAmt;
    }

  }

  public class SMSParameterValueResponse extends DBTemplatesResponse<SMSParameterValue> {

    @Override
    protected SMSParameterValue createResponse() {
      return new SMSParameterValue();
    }

  }

  protected class GetSMSParameterValue extends DBTemplatesExecuteQuery<SMSParameterValueResponse, UtilityLogger, DBConnectionPools> {

    public GetSMSParameterValue(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected SMSParameterValueResponse createResponse() {
      return new SMSParameterValueResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append(" SELECT BILL_START_DATE, BILL_END_DATE ,INVOICE_TOTAL_AMT ").append(Constants.END_LINE);
      sql.append(" FROM PMDB..PM_INVOICE ").append(Constants.END_LINE);
      sql.append(" WHERE INVOICE_ID =  ").append(invoiceId.toPlainString()).append(Constants.END_LINE);
      return sql;
    }

    private BigDecimal invoiceId;

    @Override
    protected void setReturnValue(ResultSet rs) throws SQLException {
      SMSParameterValue pm = response.getResponse();
      pm.setBillStartDate(toDate(rs.getDate("BILL_START_DATE"), null));
      pm.setBillEndDate(toDate(rs.getDate("BILL_END_DATE"), null));
      pm.setInvoiceTotalAmt(rs.getBigDecimal("INVOICE_TOTAL_AMT"));
    }

    protected SMSParameterValueResponse execute(BigDecimal invoiceId) {
      this.invoiceId = invoiceId;
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public SMSParameterValueResponse getSMSParameterValue(BigDecimal invoiceId) {
    return new GetSMSParameterValue(logger).execute(invoiceId);
  }

  //</editor-fold>
  //
}
