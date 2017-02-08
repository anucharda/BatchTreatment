/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.db;

import java.math.BigDecimal;
import th.co.ais.cpac.cl.batch.Constants;
import th.co.ais.cpac.cl.common.UtilityLogger;
import th.co.ais.cpac.cl.template.database.DBConnectionPools;
import th.co.ais.cpac.cl.template.database.DBTemplatesResponse;
import th.co.ais.cpac.cl.template.database.DBTemplatesUpdate;

public class TempAccountBalance {

  protected final UtilityLogger logger;

 

  public TempAccountBalance(UtilityLogger logger) {
    this.logger = logger;
  }

  public class ExecuteResponse extends DBTemplatesResponse<Boolean> {

    @Override
    protected Boolean createResponse() {
      return false;
    }
  }

  public class BAAccountTemp {

    public BAAccountTemp() {
    }

  }

  //<editor-fold defaultstate="collapsed" desc="UpdateDataDriveAction  CL_TERATMENT_ACCOUNT_BALANCE_TEMP">
  protected class UpdateDataDriveAction extends DBTemplatesUpdate<ExecuteResponse, UtilityLogger, DBConnectionPools> {

    private int rowNumb;
    private BigDecimal pathId;
    private BigDecimal runningId;

    public UpdateDataDriveAction(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append(" UPDATE TOP ").append(rowNumb).append(" CL_TEMP_HIS_BA_ACCOUNT_BALANCE");
      sql.append(" SET RUNNING_ID = ").append(runningId).append(" , RUNNING_STTS = 0 ");
      sql.append(" WHERE COLLECTION_PATH_ID = @pathId and RUNNING_ID is null ");
      return sql;
    }

    @Override
    protected ExecuteResponse createResponse() {
      return new ExecuteResponse();
    }

    protected ExecuteResponse copy(int rownumb, BigDecimal pathId, BigDecimal runningId) {
      return executeUpdate(Constants.getDBConnectionPools(logger), true);
    }
  }
  //</editor-fold>
  //

}
