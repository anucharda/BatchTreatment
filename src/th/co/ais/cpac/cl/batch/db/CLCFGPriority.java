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
public class CLCFGPriority {

  protected final UtilityLogger logger;

  public CLCFGPriority(UtilityLogger logger) {
    this.logger = logger;
  }

  //
  //<editor-fold defaultstate="collapsed" desc="Get CFGPriorityInfo ">
  public class CFGPriorityInfo {

    protected CFGPriorityInfo() {
    }
    private String mobileStatus;
    private String suspendType;
    private BigDecimal priorityStatusId;
    private BigDecimal priorityOrder;
    private Character smsBoo;
    private Character suspend1Boo;
    private Character suspend2Boo;
    private Character suspend3Boo;
    private Character terminateBoo;
    private Character reconnectBoo;

    public String getMobileStatus() {
      return mobileStatus;
    }

    public void setMobileStatus(String mobileStatus) {
      this.mobileStatus = mobileStatus;
    }

    public String getSuspendType() {
      return suspendType;
    }

    public void setSuspendType(String suspendType) {
      this.suspendType = suspendType;
    }

    public BigDecimal getPriorityStatusId() {
      return priorityStatusId;
    }

    public void setPriorityStatusId(BigDecimal priorityStatusId) {
      this.priorityStatusId = priorityStatusId;
    }

    public BigDecimal getPriorityOrder() {
      return priorityOrder;
    }

    public void setPriorityOrder(BigDecimal priorityOrder) {
      this.priorityOrder = priorityOrder;
    }

    public Character getSmsBoo() {
      return smsBoo;
    }

    public void setSmsBoo(Character smsBoo) {
      this.smsBoo = smsBoo;
    }

    public Character getSuspend1Boo() {
      return suspend1Boo;
    }

    public void setSuspend1Boo(Character suspend1Boo) {
      this.suspend1Boo = suspend1Boo;
    }

    public Character getSuspend2Boo() {
      return suspend2Boo;
    }

    public void setSuspend2Boo(Character suspend2Boo) {
      this.suspend2Boo = suspend2Boo;
    }

    public Character getSuspend3Boo() {
      return suspend3Boo;
    }

    public void setSuspend3Boo(Character suspend3Boo) {
      this.suspend3Boo = suspend3Boo;
    }

    public Character getTerminateBoo() {
      return terminateBoo;
    }

    public void setTerminateBoo(Character terminateBoo) {
      this.terminateBoo = terminateBoo;
    }

    public Character getReconnectBoo() {
      return reconnectBoo;
    }

    public void setReconnectBoo(Character reconnectBoo) {
      this.reconnectBoo = reconnectBoo;
    }

  }

  public class CFGPriorityInfoResponse extends DBTemplatesResponse<ArrayList<CFGPriorityInfo>> {

    @Override
    protected ArrayList<CFGPriorityInfo> createResponse() {
      return new ArrayList<>();
    }

  }

  protected class GetCFGPriorityInfo extends DBTemplatesExecuteQuery<CFGPriorityInfoResponse, UtilityLogger, DBConnectionPools> {

    public GetCFGPriorityInfo(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected CFGPriorityInfoResponse createResponse() {
      return new CFGPriorityInfoResponse();
    }
    private String status = "";

    //
    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();

      sql.append(" SELECT ").append(Constants.END_LINE);
      sql.append("   MOBILE_STATUS, SUSPEND_TYPE, PRIORITY_STATUS_ID, PRIORITY_ORDER,").append(Constants.END_LINE);
      sql.append("   SMS_BOO, SD1_BOO, SD2_BOO, SD3_BOO, DT_BOO, RC_BOO ").append(Constants.END_LINE);
      sql.append(" FROM dbo.CL_CFG_PRIORITY ").append(Constants.END_LINE);
      sql.append(" WHERE RECORD_STATUS = 1 and ").append(status).append(Constants.END_LINE);

      return sql;
    }

    private String caNo;

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      CFGPriorityInfo temp = new CFGPriorityInfo();
      temp.setMobileStatus(resultSet.getString("MOBILE_STATUS"));
      temp.setSuspendType(resultSet.getString("SUSPEND_TYPE"));
      temp.setPriorityStatusId(resultSet.getBigDecimal("PRIORITY_STATUS_ID"));
      temp.setPriorityOrder(resultSet.getBigDecimal("PRIORITY_ORDER"));
      temp.setSmsBoo(toCharcter(resultSet.getString("SMS_BOO"), 'N'));
      temp.setSuspend1Boo(toCharcter(resultSet.getString("SD1_BOO"), 'N'));
      temp.setSuspend2Boo(toCharcter(resultSet.getString("SD2_BOO"), 'N'));
      temp.setSuspend3Boo(toCharcter(resultSet.getString("SD3_BOO"), 'N'));
      temp.setTerminateBoo(toCharcter(resultSet.getString("DT_BOO"), 'N'));
      temp.setReconnectBoo(toCharcter(resultSet.getString("RC_BOO"), 'N'));
      response.getResponse().add(temp);
    }

    protected CFGPriorityInfoResponse execute(String status) {
      this.status = status;
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public CFGPriorityInfoResponse getCFGPrioritySuspend1() {
    return new GetCFGPriorityInfo(logger).execute("SD1_BOO = 'Y'");
  }

  public CFGPriorityInfoResponse getCFGPrioritySuspend2() {
    return new GetCFGPriorityInfo(logger).execute("SD2_BOO = 'Y'");
  }

  public CFGPriorityInfoResponse getCFGPrioritySuspend3() {
    return new GetCFGPriorityInfo(logger).execute("SD3_BOO = 'Y'");
  }

  public CFGPriorityInfoResponse getCFGPriorityTerminate() {
    return new GetCFGPriorityInfo(logger).execute("DT_BOO = 'Y'");
  }

  public CFGPriorityInfoResponse getCFGPriorityReconnect() {
    return new GetCFGPriorityInfo(logger).execute("RC_BOO = 'Y'");
  }
  //</editor-fold>
  //

}
