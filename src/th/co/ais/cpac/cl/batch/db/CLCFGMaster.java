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
public class CLCFGMaster {

  protected final UtilityLogger logger;

  public CLCFGMaster(UtilityLogger logger) {
    this.logger = logger;
  }

  //<editor-fold defaultstate="collapsed" desc="Get CL_CFG_PRIORITY">
  public class CLCFGPriorityInfo {

    protected CLCFGPriorityInfo() {
    }
    private String mobileStatus;
    private String suspendType;
    private BigDecimal priorityStatusId;
    private BigDecimal priorityOrder;

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

  }

  public class CLCFGPriorityResponse extends DBTemplatesResponse< ArrayList< CLCFGPriorityInfo>> {

    @Override
    protected ArrayList<CLCFGPriorityInfo> createResponse() {
      return new ArrayList<>();
    }
  }

  protected class GetCLCFGPriority extends DBTemplatesExecuteQuery<CLCFGPriorityResponse, UtilityLogger, DBConnectionPools> {

    final static int SMS = 10;
    final static int SD1 = 20;
    final static int SD2 = 30;
    final static int SD3 = 40;
    final static int DT = 50;
    final static int RC = 60;

    public GetCLCFGPriority(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected CLCFGPriorityResponse createResponse() {
      return new CLCFGPriorityResponse();
    }
    private int case_select = 0;

    //
    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append(" SELECT MOBILE_STATUS, SUSPEND_TYPE, PRIORITY_STATUS_ID, PRIORITY_ORDER").append(Constants.END_LINE);
      sql.append(" FROM dbo.CL_CFG_PRIORITY").append(Constants.END_LINE);
      sql.append(" WHERE RECORD_STATUS = 1 ");

      switch (case_select) {
        case SMS: {
          sql.append(" and SMS_BOO = 'Y' ").append(Constants.END_LINE);
          break;
        }
        case SD1: {
          sql.append(" and SMS_BOO = 'Y' ").append(Constants.END_LINE);
          break;
        }
        case SD2: {
          sql.append(" and SMS_BOO = 'Y' ").append(Constants.END_LINE);
          break;
        }
        case SD3: {
          sql.append(" and SMS_BOO = 'Y' ").append(Constants.END_LINE);
          break;
        }
        case DT: {
          sql.append(" and SMS_BOO = 'Y' ").append(Constants.END_LINE);
          break;
        }
        case RC: {
          sql.append(" and SMS_BOO = 'Y' ").append(Constants.END_LINE);
          break;
        }
      }
      return sql;
    }

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      CLCFGPriorityInfo temp = new CLCFGPriorityInfo();
      temp.setMobileStatus(resultSet.getString("MOBILE_STATUS"));
      temp.setSuspendType(resultSet.getString("SUSPEND_TYPE"));
      temp.setPriorityStatusId(resultSet.getBigDecimal("PRIORITY_STATUS_ID"));
      temp.setPriorityOrder(resultSet.getBigDecimal("PRIORITY_ORDER"));
      response.getResponse().add(temp);
    }

    protected CLCFGPriorityResponse execute(int case_select) {
      this.case_select = case_select;
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public CLCFGPriorityResponse getCLCFGPrioritySMS() {
    return new GetCLCFGPriority(logger).execute(GetCLCFGPriority.SMS);
  }

  public CLCFGPriorityResponse getCLCFGPrioritySD1() {
    return new GetCLCFGPriority(logger).execute(GetCLCFGPriority.SD1);
  }

  public CLCFGPriorityResponse getCLCFGPrioritySD2() {
    return new GetCLCFGPriority(logger).execute(GetCLCFGPriority.SD2);
  }

  public CLCFGPriorityResponse getCLCFGPrioritySD3() {
    return new GetCLCFGPriority(logger).execute(GetCLCFGPriority.SD3);
  }

  public CLCFGPriorityResponse getCLCFGPriorityDT() {
    return new GetCLCFGPriority(logger).execute(GetCLCFGPriority.DT);
  }

  public CLCFGPriorityResponse getCLCFGPriorityRC() {
    return new GetCLCFGPriority(logger).execute(GetCLCFGPriority.RC);
  }
  //</editor-fold>
  //
}
