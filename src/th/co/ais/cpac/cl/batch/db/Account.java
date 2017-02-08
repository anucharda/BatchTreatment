/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.db;

import th.co.ais.cpac.cl.common.UtilityLogger;
import th.co.ais.cpac.cl.template.database.DBTemplatesResponse;

/**
 *
 * @author Optimus
 */
public class Account {

  protected final UtilityLogger logger;

 
  public Account(UtilityLogger logger) {
    this.logger = logger;
  }

  public class ExecuteResponse extends DBTemplatesResponse<Boolean> {

    @Override
    protected Boolean createResponse() {
      return false;
    }
  }
/*
  //
  //<editor-fold defaultstate="collapsed" desc="Truncate Table TEMP CL_TERATMENT_ACCOUNT_BALANCE_TEMP">
  protected class TruncateTempAccount extends DBTemplatesUpdate<ExecuteResponse, UtilityLogger> {

    public TruncateTempAccount(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected StringBuilder createSqlProcess() {
      return new StringBuilder("TRUNCATE TABLE CL_TEMP_HIS_BA_ACCOUNT_BALANCE");
    }

    @Override
    protected ExecuteResponse createResponse() {
      return new ExecuteResponse();
    }

    protected ExecuteResponse truncate() {
      return executeUpdate(getConnection(), true);
    }

  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="Copy Path to CL_TERATMENT_ACCOUNT_BALANCE_TEMP">
  protected class CopyPath extends DBTemplatesUpdate<ExecuteResponse, UtilityLogger> {

    private StringBuffer sqlPath;

    public CopyPath(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder(sqlPath);
      sql.append("INSERT INTO CL_TEMP_HIS_BA_ACCOUNT_BALANCE (MIN_DUE_DATE ,PRIORITY_STATUS_ID,CA_NO ,BA_ACCOUNT_BALANCE_ID , BA_NO , PRIORITY_STATUS_DATE , SA_NO,COLLECTION_PATH_ID )");
      sql.append(sqlPath);
      return sql;
    }

    @Override
    protected ExecuteResponse createResponse() {
      return new ExecuteResponse();
    }

    protected ExecuteResponse copy(StringBuffer sql) {
      sqlPath = sql;
      return executeUpdate(getConnection(), true);
    }

  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="Copy Account no in Path to CL_TERATMENT_ACCOUNT_BALANCE_TEMP">
  protected class CopyNoPath extends DBTemplatesUpdate<ExecuteResponse, UtilityLogger> {

    private Long startDate;
    private Long endDate;

    public CopyNoPath(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();

      return sql;
    }

    @Override
    protected ExecuteResponse createResponse() {
      return new ExecuteResponse();
    }

    protected ExecuteResponse copy() {
      return executeUpdate(getConnection(), true);
    }

  }

  //</editor-fold>
  //
  // command 
  public ExecuteResponse truncate() {
    return new TruncateTempAccount(logger).truncate();
  }

  public ExecuteResponse copyPath(StringBuffer sql) {
    return new CopyPath(logger).copy(sql);
  }

  public ExecuteResponse copyNoPath() {
    return new CopyNoPath(logger).copy();
  }
*/
}
