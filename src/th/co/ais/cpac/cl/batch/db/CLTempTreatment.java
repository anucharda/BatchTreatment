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
import th.co.ais.cpac.cl.batch.cnf.CNFDatabase;
import th.co.ais.cpac.cl.common.UtilityLogger;

import th.co.ais.cpac.cl.template.database.DBConnectionPools;
import th.co.ais.cpac.cl.template.database.DBTemplatesExecuteQuery;
import th.co.ais.cpac.cl.template.database.DBTemplatesExecuteUpdate;
import th.co.ais.cpac.cl.template.database.DBTemplatesResponse;

/**
 *
 * @author Optimus
 */
public class CLTempTreatment {

  protected final UtilityLogger logger;

  public CLTempTreatment(UtilityLogger logger) {
    this.logger = logger;
  }

  public boolean buildConnection() {
    CNFDatabase cnf = new CNFDatabase();
    connection = new DBConnectionPools<>(cnf, logger);
    connection.getConnection();
    return connection.ready();
  }
  private DBConnectionPools<CNFDatabase, UtilityLogger> connection;

  public DBConnectionPools getDBConnection() {
    return connection;
  }

  public class ExecuteResponse extends DBTemplatesResponse<Boolean> {

    @Override
    protected Boolean createResponse() {
      return false;
    }

    @Override
    public void setResponse(Boolean boo) {
      response = boo;
    }
  }
  //
  //<editor-fold defaultstate="collapsed" desc="PrepareDataSMSTreatment">

  protected class PrepareDataTreatment extends DBTemplatesExecuteUpdate<ExecuteResponse, UtilityLogger, DBConnectionPools> {

    private ArrayList<BigDecimal> listActionId;

    public PrepareDataTreatment(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected ExecuteResponse createResponse() {
      return new ExecuteResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      String actionList = "";
      for (int i = 0; i < listActionId.size(); i++) {
        if (i == 0) {
          actionList += listActionId.get(i).toPlainString();
        } else {
          actionList += ",";
          actionList += listActionId.get(i).toPlainString();
        }
      }
     
      StringBuilder prepareDataTreatment = new StringBuilder();
      prepareDataTreatment.append("SELECT ").append(Constants.END_LINE);
      prepareDataTreatment.append("  ROWID = identity(20) ,").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.TREATMENT_ID, d11.CA_NO, d11.BA_NO, ").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.COLLECTION_PATH_ID, d11.ACTION_ID, d11.ACTION_PATH_ID, d11.ACTION_PATH_DTM, d11.ACTION_SCHEDULE_DTM, d11.CRITERIA_ID, ").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.ACTION_CRITERIA_ID, d11.ACTION_STATUS, d11.ACTION_STATUS_DTM, ").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.ACTION_MODE,d11.ACTION_TYPE ,d11.CRITERIA_LEVEL, ").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.SA_NO, d11.BA_TITLE, d11.BA_NAME, d11.BA_STATUS, d11.BA_STATUS_DTM, ").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.CATEGORY,d11.SUBCATEGORY, d11.COMPANY_CODE, d11.REGISTER_DATE, d11.REGISTER_LOCATION, d11.BILLING_SYSTEM, d11.BILLING_CYCLE, d11.PAYMENT_TERM, ").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.PAYMENT_BEHAVIOR, d11.PAYMENT_METHOD, d11.BANK_NAME, d11.BANK_ACCNT_NO, d11.CREDIT_CARD_TYPE, ").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.CREDIT_CARD_BANK, d11.CREDIT_CARD_NAME, d11.CREDIT_CARD_REF, d11.PRODUCT_GROUP_ID, d11.PRIORITY_STATUS_ID, d11.PRIORITY_STATUS_DATE, ").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.BILL_ADDRESS_NAME, d11.BILL_ADDRESS_LINE_1, d11.BILL_ADDRESS_LINE_2, d11.BILL_ADDRESS_LINE_3, d11.BILL_ADDRESS_LINE_4, d11.BILL_ADDRESS_LINE_5, ").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.BILL_ZIPCODE, d11.BILL_PROVINCE_ID, d11.BILL_REGION_ID, d11.BILL_LANGUAGE, d11.REF_BA_NO, ").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.SFF_ACCOUNT_ID, d11.SB_ACCOUNT_ID, ").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.BA_STATUS_GROUP, ").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.CATEGORY_GROUP, ").append(Constants.END_LINE);
      prepareDataTreatment.append("  d12.BA_TOTAL_BAL, d12.DUE_TOTAL_BAL, d12.MIN_INVOICE_DATE, d12.MIN_DUE_DATE, d12.MAX_INVOICE_DATE , d12.BA_INVOICE_CNT ,d12.MIN_INVOICE_ID, ").append(Constants.END_LINE);
      prepareDataTreatment.append("  CAST( 0 AS NUMERIC(19) ) RUNNING_ID , CAST( 0 AS NUMERIC(19) ) RUNNING_STTS INTO #TEMP_TREATEMENT_TS  ").append(Constants.END_LINE);
      prepareDataTreatment.append("FROM( ").append(Constants.END_LINE);
      prepareDataTreatment.append("  SELECT  ").append(Constants.END_LINE);
      prepareDataTreatment.append("    d9.TREATMENT_ID, d9.CA_NO, d9.BA_NO,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("    d9.COLLECTION_PATH_ID, d9.ACTION_ID, d9.ACTION_PATH_ID, d9.ACTION_PATH_DTM, d9.ACTION_SCHEDULE_DTM, d9.CRITERIA_ID,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("    d9.ACTION_CRITERIA_ID, d9.ACTION_STATUS, d9.ACTION_STATUS_DTM, d9.MIN_DUE_DATE, ").append(Constants.END_LINE);
      prepareDataTreatment.append("    d9.ACTION_MODE,d9.ACTION_TYPE ,d9.CRITERIA_LEVEL, ").append(Constants.END_LINE);
      prepareDataTreatment.append("    d9.SA_NO, d9.BA_TITLE, d9.BA_NAME, d9.BA_STATUS, d9.BA_STATUS_DTM,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("    d9.CATEGORY,d9.SUBCATEGORY, d9.COMPANY_CODE, d9.REGISTER_DATE, d9.REGISTER_LOCATION, d9.BILLING_SYSTEM, d9.BILLING_CYCLE, d9.PAYMENT_TERM,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("    d9.PAYMENT_BEHAVIOR, d9.PAYMENT_METHOD, d9.BANK_NAME, d9.BANK_ACCNT_NO, d9.CREDIT_CARD_TYPE,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("    d9.CREDIT_CARD_BANK, d9.CREDIT_CARD_NAME, d9.CREDIT_CARD_REF, d9.PRODUCT_GROUP_ID, d9.PRIORITY_STATUS_ID, d9.PRIORITY_STATUS_DATE,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("    d9.BILL_ADDRESS_NAME, d9.BILL_ADDRESS_LINE_1, d9.BILL_ADDRESS_LINE_2, d9.BILL_ADDRESS_LINE_3, d9.BILL_ADDRESS_LINE_4, d9.BILL_ADDRESS_LINE_5,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("    d9.BILL_ZIPCODE, d9.BILL_PROVINCE_ID, d9.BILL_REGION_ID, d9.BILL_LANGUAGE, d9.REF_BA_NO,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("    d9.SFF_ACCOUNT_ID, d9.SB_ACCOUNT_ID, ").append(Constants.END_LINE);
      prepareDataTreatment.append("    d9.BA_STATUS_GROUP, ").append(Constants.END_LINE);
      prepareDataTreatment.append("    d10.CATEGORY_GROUP ").append(Constants.END_LINE);
      prepareDataTreatment.append("  FROM ( ").append(Constants.END_LINE);
      prepareDataTreatment.append("    SELECT  ").append(Constants.END_LINE);
      prepareDataTreatment.append("      d7.TREATMENT_ID, d7.CA_NO, d7.BA_NO,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("      d7.COLLECTION_PATH_ID, d7.ACTION_ID, d7.ACTION_PATH_ID, d7.ACTION_PATH_DTM, d7.ACTION_SCHEDULE_DTM, d7.CRITERIA_ID,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("      d7.ACTION_CRITERIA_ID, d7.ACTION_STATUS, d7.ACTION_STATUS_DTM, d7.MIN_DUE_DATE, ").append(Constants.END_LINE);
      prepareDataTreatment.append("      d7.ACTION_MODE,d7.ACTION_TYPE ,d7.CRITERIA_LEVEL, ").append(Constants.END_LINE);
      prepareDataTreatment.append("      d7.SA_NO, d7.BA_TITLE, d7.BA_NAME, d7.BA_STATUS, d7.BA_STATUS_DTM,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("      d7.CATEGORY,d7.SUBCATEGORY, d7.COMPANY_CODE, d7.REGISTER_DATE, d7.REGISTER_LOCATION, d7.BILLING_SYSTEM, d7.BILLING_CYCLE, d7.PAYMENT_TERM,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("      d7.PAYMENT_BEHAVIOR, d7.PAYMENT_METHOD, d7.BANK_NAME, d7.BANK_ACCNT_NO, d7.CREDIT_CARD_TYPE,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("      d7.CREDIT_CARD_BANK, d7.CREDIT_CARD_NAME, d7.CREDIT_CARD_REF, d7.PRODUCT_GROUP_ID, d7.PRIORITY_STATUS_ID, d7.PRIORITY_STATUS_DATE,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("      d7.BILL_ADDRESS_NAME, d7.BILL_ADDRESS_LINE_1, d7.BILL_ADDRESS_LINE_2, d7.BILL_ADDRESS_LINE_3, d7.BILL_ADDRESS_LINE_4, d7.BILL_ADDRESS_LINE_5,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("      d7.BILL_ZIPCODE, d7.BILL_PROVINCE_ID, d7.BILL_REGION_ID, d7.BILL_LANGUAGE, d7.REF_BA_NO,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("      d7.SFF_ACCOUNT_ID, d7.SB_ACCOUNT_ID, ").append(Constants.END_LINE);
      prepareDataTreatment.append("      d8.BA_STATUS_GROUP ").append(Constants.END_LINE);
      prepareDataTreatment.append("    FROM ( ").append(Constants.END_LINE);
      prepareDataTreatment.append("      SELECT ").append(Constants.END_LINE);
      prepareDataTreatment.append("        d5.TREATMENT_ID, d5.CA_NO, d5.BA_NO,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("        d5.COLLECTION_PATH_ID, d5.ACTION_ID, d5.ACTION_PATH_ID, d5.ACTION_PATH_DTM, d5.ACTION_SCHEDULE_DTM, d5.CRITERIA_ID,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("        d5.ACTION_CRITERIA_ID, d5.ACTION_STATUS, d5.ACTION_STATUS_DTM, d5.MIN_DUE_DATE, ").append(Constants.END_LINE);
      prepareDataTreatment.append("        d5.ACTION_MODE,d5.ACTION_TYPE ,d5.CRITERIA_LEVEL, ").append(Constants.END_LINE);
      prepareDataTreatment.append("        d6.SA_NO, d6.BA_TITLE, d6.BA_NAME, d6.BA_STATUS, d6.BA_STATUS_DTM,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("        d6.CATEGORY,d6.SUBCATEGORY, d6.COMPANY_CODE, d6.REGISTER_DATE, d6.REGISTER_LOCATION, d6.BILLING_SYSTEM, d6.BILLING_CYCLE, d6.PAYMENT_TERM,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("        d6.PAYMENT_BEHAVIOR, d6.PAYMENT_METHOD, d6.BANK_NAME, d6.BANK_ACCNT_NO, d6.CREDIT_CARD_TYPE,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("        d6.CREDIT_CARD_BANK, d6.CREDIT_CARD_NAME, d6.CREDIT_CARD_REF, d6.PRODUCT_GROUP_ID, d6.PRIORITY_STATUS_ID, d6.PRIORITY_STATUS_DATE,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("        d6.BILL_ADDRESS_NAME, d6.BILL_ADDRESS_LINE_1, d6.BILL_ADDRESS_LINE_2, d6.BILL_ADDRESS_LINE_3, d6.BILL_ADDRESS_LINE_4, d6.BILL_ADDRESS_LINE_5,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("        d6.BILL_ZIPCODE, d6.BILL_PROVINCE_ID, d6.BILL_REGION_ID, d6.BILL_LANGUAGE, d6.REF_BA_NO,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("        d6.SFF_ACCOUNT_ID, d6.SB_ACCOUNT_ID ").append(Constants.END_LINE);
      prepareDataTreatment.append("      FROM ( ").append(Constants.END_LINE);
      prepareDataTreatment.append("        SELECT  ").append(Constants.END_LINE);
      prepareDataTreatment.append("          d3.TREATMENT_ID, d3.CA_NO, d3.BA_NO,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("          d3.COLLECTION_PATH_ID, d3.ACTION_ID, d3.ACTION_PATH_ID, d3.ACTION_PATH_DTM, d3.ACTION_SCHEDULE_DTM, d3.CRITERIA_ID,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("          d3.ACTION_CRITERIA_ID, d3.ACTION_STATUS, d3.ACTION_STATUS_DTM, d3.MIN_DUE_DATE, ").append(Constants.END_LINE);
      prepareDataTreatment.append("          d3.ACTION_MODE,d3.ACTION_TYPE , ").append(Constants.END_LINE);
      prepareDataTreatment.append("          d4.CRITERIA_LEVEL ").append(Constants.END_LINE);
      prepareDataTreatment.append("        FROM ( ").append(Constants.END_LINE);
      prepareDataTreatment.append("          SELECT  ").append(Constants.END_LINE);
      prepareDataTreatment.append("            d1.TREATMENT_ID, d1.CA_NO, d1.BA_NO,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("            d1.COLLECTION_PATH_ID, d1.ACTION_ID, d1.ACTION_PATH_ID, d1.ACTION_PATH_DTM, d1.ACTION_SCHEDULE_DTM, d1.CRITERIA_ID,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("            d1.ACTION_CRITERIA_ID, d1.ACTION_STATUS, d1.ACTION_STATUS_DTM, d1.MIN_DUE_DATE, ").append(Constants.END_LINE);
      prepareDataTreatment.append("            d2.ACTION_MODE,d2.ACTION_TYPE ").append(Constants.END_LINE);
      prepareDataTreatment.append("          FROM ( ").append(Constants.END_LINE);
      prepareDataTreatment.append("            SELECT  ").append(Constants.END_LINE);
      prepareDataTreatment.append("              TREATMENT_ID, CA_NO, BA_NO,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("              COLLECTION_PATH_ID, ACTION_ID, ACTION_PATH_ID, ACTION_PATH_DTM, ACTION_SCHEDULE_DTM, CRITERIA_ID,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("              ACTION_CRITERIA_ID, ACTION_STATUS, ACTION_STATUS_DTM, MIN_DUE_DATE ").append(Constants.END_LINE);
      prepareDataTreatment.append("            FROM dbo.CL_TREATMENT  ").append(Constants.END_LINE);
      prepareDataTreatment.append("            WHERE ACTION_ID in (").append(actionList).append(") and ACTION_SCHEDULE_DTM < getdate()  and ACTION_STATUS =  2 ").append(Constants.END_LINE);
      prepareDataTreatment.append("          )d1 left join ( ").append(Constants.END_LINE);
      prepareDataTreatment.append("            SELECT  ").append(Constants.END_LINE);
      prepareDataTreatment.append("              ACTION_ID, ACTION_MODE, ACTION_TYPE ").append(Constants.END_LINE);
      prepareDataTreatment.append("            FROM dbo.CL_ACTION ").append(Constants.END_LINE);
      prepareDataTreatment.append("            WHERE RECORD_STATUS = 1 ").append(Constants.END_LINE);
      prepareDataTreatment.append("          )d2 on d1.ACTION_ID = d2.ACTION_ID ").append(Constants.END_LINE);
      prepareDataTreatment.append("        )d3 left join ( ").append(Constants.END_LINE);
      prepareDataTreatment.append("          SELECT  ").append(Constants.END_LINE);
      prepareDataTreatment.append("            ACTION_CRITERIA_ID, CRITERIA_LEVEL ").append(Constants.END_LINE);
      prepareDataTreatment.append("          FROM dbo.CL_ACTION_CRITERIA ").append(Constants.END_LINE);
      prepareDataTreatment.append("        )d4 on d3.ACTION_CRITERIA_ID = d4.ACTION_CRITERIA_ID ").append(Constants.END_LINE);
      prepareDataTreatment.append("      )d5 left join ( ").append(Constants.END_LINE);
      prepareDataTreatment.append("        SELECT  ").append(Constants.END_LINE);
      prepareDataTreatment.append("          BA_NO, CA_NO, SA_NO, BA_TITLE, BA_NAME, BA_STATUS, BA_STATUS_DTM,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("          CATEGORY ,SUBCATEGORY, COMPANY_CODE, REGISTER_DATE, REGISTER_LOCATION, BILLING_SYSTEM, BILLING_CYCLE, PAYMENT_TERM,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("          PAYMENT_BEHAVIOR, PAYMENT_METHOD, BANK_NAME, BANK_ACCNT_NO, CREDIT_CARD_TYPE,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("          CREDIT_CARD_BANK, CREDIT_CARD_NAME, CREDIT_CARD_REF, PRODUCT_GROUP_ID, PRIORITY_STATUS_ID, PRIORITY_STATUS_DATE,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("          BILL_ADDRESS_NAME, BILL_ADDRESS_LINE_1, BILL_ADDRESS_LINE_2, BILL_ADDRESS_LINE_3, BILL_ADDRESS_LINE_4, BILL_ADDRESS_LINE_5,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("          BILL_ZIPCODE, BILL_PROVINCE_ID, BILL_REGION_ID, BILL_LANGUAGE, REF_BA_NO,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("          SFF_ACCOUNT_ID, SB_ACCOUNT_ID ").append(Constants.END_LINE);
      prepareDataTreatment.append("        FROM dbo.CL_BA_INFO ").append(Constants.END_LINE);
      prepareDataTreatment.append("      )d6 on d5.BA_NO = d6.BA_NO and d5.CA_NO = d6.CA_NO ").append(Constants.END_LINE);
      prepareDataTreatment.append("    )d7 left join ( ").append(Constants.END_LINE);
      prepareDataTreatment.append("      SELECT  ").append(Constants.END_LINE);
      prepareDataTreatment.append("        BA_STATUS, BA_STATUS_GROUP ").append(Constants.END_LINE);
      prepareDataTreatment.append("      FROM dbo.CL_SFF_BA_STATUS ").append(Constants.END_LINE);
      prepareDataTreatment.append("      WHERE RECORD_STATUS = 1 ").append(Constants.END_LINE);
      prepareDataTreatment.append("    )d8 on d7.BA_STATUS = d8.BA_STATUS  ").append(Constants.END_LINE);
      prepareDataTreatment.append("  ) d9 left join ( ").append(Constants.END_LINE);
      prepareDataTreatment.append("    SELECT  ").append(Constants.END_LINE);
      prepareDataTreatment.append("      CATEGORY, CATEGORY_GROUP ").append(Constants.END_LINE);
      prepareDataTreatment.append("    FROM dbo.CL_SFF_CATEGORY ").append(Constants.END_LINE);
      prepareDataTreatment.append("    WHERE RECORD_STATUS = 1 ").append(Constants.END_LINE);
      prepareDataTreatment.append("  )d10 on d9.CATEGORY = d10.CATEGORY ").append(Constants.END_LINE);
      prepareDataTreatment.append(")d11 left join ( ").append(Constants.END_LINE);
      prepareDataTreatment.append("  SELECT  ").append(Constants.END_LINE);
      prepareDataTreatment.append("    BA_NO, BA_TOTAL_BAL, DUE_TOTAL_BAL, MIN_INVOICE_DATE, MIN_DUE_DATE, MAX_INVOICE_DATE , BA_INVOICE_CNT ,MIN_INVOICE_ID  ").append(Constants.END_LINE);
      prepareDataTreatment.append("  FROM dbo.CL_HIS_BA_ACCOUNT_BALANCE ").append(Constants.END_LINE);
      prepareDataTreatment.append("  WHERE CURRENT_BOO = 'Y' ").append(Constants.END_LINE);
      prepareDataTreatment.append(")d12 on d11.BA_NO = d12.BA_NO ").append(Constants.END_LINE);
      prepareDataTreatment.append("ORDER BY MIN_DUE_DATE , DUE_TOTAL_BAL  ").append(Constants.END_LINE);

      return prepareDataTreatment;
    }

    protected ExecuteResponse execute(ArrayList<BigDecimal> listActionId) {
      this.listActionId = listActionId;
      return executeUpdate(connection, false);
    }
  }

  public ExecuteResponse prepareDataTreatment(ArrayList<BigDecimal> listActionId) {
    return new PrepareDataTreatment(logger).execute(listActionId);
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="Group CRITERIA LEVEL">
  protected class PrepareGroupDataTreatment extends DBTemplatesExecuteUpdate<ExecuteResponse, UtilityLogger, DBConnectionPools> {

    static final int GROUP_CA_SA_BA = 10;
    static final int GROUP_CA_SA_BA_COMPANY_CODE = 20;

    public PrepareGroupDataTreatment(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected ExecuteResponse createResponse() {
      return new ExecuteResponse();
    }
    private int case_prepare_group;

    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder prepareDataTreatment = new StringBuilder();
      if (case_prepare_group == GROUP_CA_SA_BA) {
        prepareDataTreatment.append(" SELECT ROWID , RUNNING_STTS INTO #TEMPUPDATE ").append(Constants.END_LINE);
        prepareDataTreatment.append(" FROM ( ").append(Constants.END_LINE);
        prepareDataTreatment.append("   SELECT MIN(ROWID) ROWID  , CAST( 10 AS NUMERIC(19) ) RUNNING_STTS ").append(Constants.END_LINE);
        prepareDataTreatment.append("   FROM #TEMP_TREATEMENT_TS ").append(Constants.END_LINE);
        prepareDataTreatment.append("   WHERE CRITERIA_LEVEL = ").append(Constants.CriteriaLevel.CA.getCode()).append(Constants.END_LINE);
        prepareDataTreatment.append("   GROUP BY COLLECTION_PATH_ID ,ACTION_ID , CRITERIA_ID ,CA_NO ").append(Constants.END_LINE);
        prepareDataTreatment.append("   UNION ALL ").append(Constants.END_LINE);
        prepareDataTreatment.append("   SELECT MIN(ROWID) ROWID ,CAST( 20 AS NUMERIC(19) ) RUNNING_STTS ").append(Constants.END_LINE);
        prepareDataTreatment.append("   FROM #TEMP_TREATEMENT_TS ").append(Constants.END_LINE);
        prepareDataTreatment.append("   WHERE CRITERIA_LEVEL = ").append(Constants.CriteriaLevel.SA.getCode()).append(Constants.END_LINE);
        prepareDataTreatment.append("   GROUP BY COLLECTION_PATH_ID ,ACTION_ID , CRITERIA_ID ,CA_NO ,SA_NO ").append(Constants.END_LINE);
        prepareDataTreatment.append("   UNION ALL ").append(Constants.END_LINE);
        prepareDataTreatment.append("   SELECT ROWID , CAST( 30 AS NUMERIC(19) ) RUNNING_STTS ").append(Constants.END_LINE);
        prepareDataTreatment.append("   FROM #TEMP_TREATEMENT_TS ").append(Constants.END_LINE);
        prepareDataTreatment.append("   WHERE CRITERIA_LEVEL = ").append(Constants.CriteriaLevel.BA.getCode()).append(Constants.END_LINE);
        prepareDataTreatment.append(" ) a1 ").append(Constants.END_LINE);
        prepareDataTreatment.append(Constants.END_LINE);
        prepareDataTreatment.append(" UPDATE #TEMP_TREATEMENT_TS ").append(Constants.END_LINE);
        prepareDataTreatment.append(" set  RUNNING_STTS = t2.RUNNING_STTS ").append(Constants.END_LINE);
        prepareDataTreatment.append(" from #TEMP_TREATEMENT_TS t1 , #TEMPUPDATE  t2 ").append(Constants.END_LINE);
        prepareDataTreatment.append(" where t1.ROWID = t2.ROWID  ").append(Constants.END_LINE);
        prepareDataTreatment.append(Constants.END_LINE);
        prepareDataTreatment.append(" drop table #TEMPUPDATE ").append(Constants.END_LINE);
      } else if (case_prepare_group == GROUP_CA_SA_BA_COMPANY_CODE) {
        prepareDataTreatment.append(" SELECT ROWID , RUNNING_STTS INTO #TEMPUPDATE ").append(Constants.END_LINE);
        prepareDataTreatment.append(" FROM ( ").append(Constants.END_LINE);
        prepareDataTreatment.append("   SELECT MIN(ROWID) ROWID  , CAST( 10 AS NUMERIC(19) ) RUNNING_STTS ").append(Constants.END_LINE);
        prepareDataTreatment.append("   FROM #TEMP_TREATEMENT_TS ").append(Constants.END_LINE);
        prepareDataTreatment.append("   WHERE CRITERIA_LEVEL = ").append(Constants.CriteriaLevel.CA.getCode()).append(Constants.END_LINE);
        prepareDataTreatment.append("   GROUP BY COLLECTION_PATH_ID ,ACTION_ID , CRITERIA_ID ,CA_NO ,COMPANY_CODE").append(Constants.END_LINE);
        prepareDataTreatment.append("   UNION ALL ").append(Constants.END_LINE);
        prepareDataTreatment.append("   SELECT MIN(ROWID) ROWID ,CAST( 20 AS NUMERIC(19) ) RUNNING_STTS ").append(Constants.END_LINE);
        prepareDataTreatment.append("   FROM #TEMP_TREATEMENT_TS ").append(Constants.END_LINE);
        prepareDataTreatment.append("   WHERE CRITERIA_LEVEL = ").append(Constants.CriteriaLevel.SA.getCode()).append(Constants.END_LINE);
        prepareDataTreatment.append("   GROUP BY COLLECTION_PATH_ID ,ACTION_ID , CRITERIA_ID ,CA_NO ,SA_NO  ,COMPANY_CODE").append(Constants.END_LINE);
        prepareDataTreatment.append("   UNION ALL ").append(Constants.END_LINE);
        prepareDataTreatment.append("   SELECT ROWID , CAST( 30 AS NUMERIC(19) ) RUNNING_STTS ").append(Constants.END_LINE);
        prepareDataTreatment.append("   FROM #TEMP_TREATEMENT_TS ").append(Constants.END_LINE);
        prepareDataTreatment.append("   WHERE CRITERIA_LEVEL = ").append(Constants.CriteriaLevel.BA.getCode()).append(Constants.END_LINE);
        prepareDataTreatment.append(" ) a1 ").append(Constants.END_LINE);
        prepareDataTreatment.append(Constants.END_LINE);
        prepareDataTreatment.append(" UPDATE #TEMP_TREATEMENT_TS ").append(Constants.END_LINE);
        prepareDataTreatment.append(" set  RUNNING_STTS = t2.RUNNING_STTS ").append(Constants.END_LINE);
        prepareDataTreatment.append(" from #TEMP_TREATEMENT_TS t1 , #TEMPUPDATE  t2 ").append(Constants.END_LINE);
        prepareDataTreatment.append(" where t1.ROWID = t2.ROWID  ").append(Constants.END_LINE);
        prepareDataTreatment.append(Constants.END_LINE);
        prepareDataTreatment.append(" drop table #TEMPUPDATE ").append(Constants.END_LINE);
      }
      return prepareDataTreatment;
    }

    protected ExecuteResponse execute(int case_prepare_group) {
      this.case_prepare_group = case_prepare_group;
      return executeUpdate(connection, false);
    }
  }

  public ExecuteResponse prepareCriteraiLevel() {
    return new PrepareGroupDataTreatment(logger).execute(PrepareGroupDataTreatment.GROUP_CA_SA_BA_COMPANY_CODE);
  }

  //</editor-fold>
  //
  //
  //<editor-fold defaultstate="collapsed" desc="GetListSMSTreatment">
  public class TempTreatment {

    private BigDecimal rowId;
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
    private Constants.ActionMode actionMode;
    private Constants.ActionType actionType;
    private Constants.CriteriaLevel criteriaLevel;
    private String saNumber;
    private String baTitle;
    private String baName;
    private String baStatus;
    private Date baStatusDtm;
    private String category;
    private String companyCode;
    private Date registerDate;
    private BigDecimal registerLocation;
    private String billingSystem;
    private String billingCycle;
    private BigDecimal paymentTerm;
    private BigDecimal paymentBehavior;
    private String paymentMethod;
    private String bankName;
    private String bankAccntNumber;
    private String creditCardType;
    private String creditCardBank;
    private String creditCardName;
    private String creditCardRef;
    private BigDecimal productGroupId;
    private BigDecimal priorityStatusId;
    private Date priorityStatusDate;
    private String billAddressName;
    private String billAddressLine1;
    private String billAddressLine2;
    private String billAddressLine3;
    private String billAddressLine4;
    private String billAddressLine5;
    private String billZipcode;
    private BigDecimal billProvinceId;
    private BigDecimal billRegionId;
    private String billLanguage;
    private String refBaNumber;
    private BigDecimal sffAccountId;
    private BigDecimal sbAccountId;
    private Constants.BaStatusGroup baStatusGroup;
    private String subcategory;
    private BigDecimal baTotalBal;
    private BigDecimal dueTotalBal;
    private BigDecimal minInvoiceId;
    private Date minInvoiceDate;
    private Date minDueDate;
    private Date maxInvoiceDate;
    private BigDecimal runningId;
    private BigDecimal runningStts;
    // add 
    private BigDecimal arMovementId;
    private BigDecimal baInvoiceCount;

    public BigDecimal getRowId() {
      return rowId;
    }

    public void setRowId(BigDecimal rowId) {
      this.rowId = rowId;
    }

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

    public Constants.CriteriaLevel getCriteriaLevel() {
      return criteriaLevel;
    }

    public void setCriteriaLevel(Constants.CriteriaLevel criteriaLevel) {
      this.criteriaLevel = criteriaLevel;
    }

    public String getSaNumber() {
      return saNumber;
    }

    public void setSaNumber(String saNumber) {
      this.saNumber = saNumber;
    }

    public String getBaTitle() {
      return baTitle;
    }

    public void setBaTitle(String baTitle) {
      this.baTitle = baTitle;
    }

    public String getBaName() {
      return baName;
    }

    public void setBaName(String baName) {
      this.baName = baName;
    }

    public String getBaStatus() {
      return baStatus;
    }

    public void setBaStatus(String baStatus) {
      this.baStatus = baStatus;
    }

    public Date getBaStatusDtm() {
      return baStatusDtm;
    }

    public void setBaStatusDtm(Date baStatusDtm) {
      this.baStatusDtm = baStatusDtm;
    }

    public String getCategory() {
      return category;
    }

    public void setCategory(String category) {
      this.category = category;
    }

    public String getCompanyCode() {
      return companyCode;
    }

    public void setCompanyCode(String companyCode) {
      this.companyCode = companyCode;
    }

    public Date getRegisterDate() {
      return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
      this.registerDate = registerDate;
    }

    public BigDecimal getRegisterLocation() {
      return registerLocation;
    }

    public void setRegisterLocation(BigDecimal registerLocation) {
      this.registerLocation = registerLocation;
    }

    public String getBillingSystem() {
      return billingSystem;
    }

    public void setBillingSystem(String billingSystem) {
      this.billingSystem = billingSystem;
    }

    public String getBillingCycle() {
      return billingCycle;
    }

    public void setBillingCycle(String billingCycle) {
      this.billingCycle = billingCycle;
    }

    public BigDecimal getPaymentTerm() {
      return paymentTerm;
    }

    public void setPaymentTerm(BigDecimal paymentTerm) {
      this.paymentTerm = paymentTerm;
    }

    public BigDecimal getPaymentBehavior() {
      return paymentBehavior;
    }

    public void setPaymentBehavior(BigDecimal paymentBehavior) {
      this.paymentBehavior = paymentBehavior;
    }

    public String getPaymentMethod() {
      return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
      this.paymentMethod = paymentMethod;
    }

    public String getBankName() {
      return bankName;
    }

    public void setBankName(String bankName) {
      this.bankName = bankName;
    }

    public String getBankAccntNumber() {
      return bankAccntNumber;
    }

    public void setBankAccntNumber(String bankAccntNumber) {
      this.bankAccntNumber = bankAccntNumber;
    }

    public String getCreditCardType() {
      return creditCardType;
    }

    public void setCreditCardType(String creditCardType) {
      this.creditCardType = creditCardType;
    }

    public String getCreditCardBank() {
      return creditCardBank;
    }

    public void setCreditCardBank(String creditCardBank) {
      this.creditCardBank = creditCardBank;
    }

    public String getCreditCardName() {
      return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
      this.creditCardName = creditCardName;
    }

    public String getCreditCardRef() {
      return creditCardRef;
    }

    public void setCreditCardRef(String creditCardRef) {
      this.creditCardRef = creditCardRef;
    }

    public BigDecimal getProductGroupId() {
      return productGroupId;
    }

    public void setProductGroupId(BigDecimal productGroupId) {
      this.productGroupId = productGroupId;
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

    public String getBillAddressName() {
      return billAddressName;
    }

    public void setBillAddressName(String billAddressName) {
      this.billAddressName = billAddressName;
    }

    public String getBillAddressLine1() {
      return billAddressLine1;
    }

    public void setBillAddressLine1(String billAddressLine1) {
      this.billAddressLine1 = billAddressLine1;
    }

    public String getBillAddressLine2() {
      return billAddressLine2;
    }

    public void setBillAddressLine2(String billAddressLine2) {
      this.billAddressLine2 = billAddressLine2;
    }

    public String getBillAddressLine3() {
      return billAddressLine3;
    }

    public void setBillAddressLine3(String billAddressLine3) {
      this.billAddressLine3 = billAddressLine3;
    }

    public String getBillAddressLine4() {
      return billAddressLine4;
    }

    public void setBillAddressLine4(String billAddressLine4) {
      this.billAddressLine4 = billAddressLine4;
    }

    public String getBillAddressLine5() {
      return billAddressLine5;
    }

    public void setBillAddressLine5(String billAddressLine5) {
      this.billAddressLine5 = billAddressLine5;
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

    public String getBillLanguage() {
      return billLanguage;
    }

    public void setBillLanguage(String billLanguage) {
      this.billLanguage = billLanguage;
    }

    public String getRefBaNumber() {
      return refBaNumber;
    }

    public void setRefBaNumber(String refBaNumber) {
      this.refBaNumber = refBaNumber;
    }

    public BigDecimal getSffAccountId() {
      return sffAccountId;
    }

    public void setSffAccountId(BigDecimal sffAccountId) {
      this.sffAccountId = sffAccountId;
    }

    public BigDecimal getSbAccountId() {
      return sbAccountId;
    }

    public void setSbAccountId(BigDecimal sbAccountId) {
      this.sbAccountId = sbAccountId;
    }

    public Constants.BaStatusGroup getBaStatusGroup() {
      return baStatusGroup;
    }

    public void setBaStatusGroup(Constants.BaStatusGroup baStatusGroup) {
      this.baStatusGroup = baStatusGroup;
    }

    public String getSubcategory() {
      return subcategory;
    }

    public void setSubcategory(String subcategory) {
      this.subcategory = subcategory;
    }

    public BigDecimal getBaTotalBal() {
      return baTotalBal;
    }

    public void setBaTotalBal(BigDecimal baTotalBal) {
      this.baTotalBal = baTotalBal;
    }

    public BigDecimal getDueTotalBal() {
      return dueTotalBal;
    }

    public void setDueTotalBal(BigDecimal dueTotalBal) {
      this.dueTotalBal = dueTotalBal;
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

    public Date getMaxInvoiceDate() {
      return maxInvoiceDate;
    }

    public void setMaxInvoiceDate(Date maxInvoiceDate) {
      this.maxInvoiceDate = maxInvoiceDate;
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

    public BigDecimal getArMovementId() {
      return arMovementId;
    }

    public void setArMovementId(BigDecimal arMovementId) {
      this.arMovementId = arMovementId;
    }

    public BigDecimal getBaInvoiceCount() {
      return baInvoiceCount;
    }

    public void setBaInvoiceCount(BigDecimal baInvoiceCount) {
      this.baInvoiceCount = baInvoiceCount;
    }

  }

  public class TempTreatmentResponse extends DBTemplatesResponse<ArrayList< ArrayList<TempTreatment>>> {

    @Override
    protected ArrayList<ArrayList<TempTreatment>> createResponse() {
      return new ArrayList<>();
    }

  }

  protected class GetListTempTreatment extends DBTemplatesExecuteQuery<TempTreatmentResponse, UtilityLogger, DBConnectionPools> {

    final static int SELECT_CA_SA_BA_LEVEL = 10;
    final static int SELECT_CA_SA_BA_COMPANY_CODE_LEVEL = 20;
    final static int SELECT_BA_LEVEL = 30;

    public GetListTempTreatment(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected TempTreatmentResponse createResponse() {
      return new TempTreatmentResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      int rowNumb = 20;
      switch (case_select) {
        case SELECT_CA_SA_BA_LEVEL:
        case SELECT_CA_SA_BA_COMPANY_CODE_LEVEL:
          sql.append("UPDATE TOP ").append(rowNumb).append(" #TEMP_TREATEMENT_TS ").append(Constants.END_LINE);
          sql.append("SET RUNNING_ID =  ").append(runningId).append(Constants.END_LINE);
          sql.append("WHERE RUNNING_ID = 0 AND (RUNNING_STTS= 10 OR RUNNING_STTS = 20 OR  RUNNING_STTS = 30 ) ").append(Constants.END_LINE);
          sql.append(Constants.END_LINE);
          sql.append("SELECT COLLECTION_PATH_ID ,ACTION_ID , CRITERIA_ID ,CA_NO ,SA_NO ,CRITERIA_LEVEL INTO #TEMP_UPDATE ").append(Constants.END_LINE);
          sql.append("FROM #TEMP_TREATEMENT_TS ").append(Constants.END_LINE);
          sql.append("WHERE RUNNING_ID = ").append(runningId).append(" AND CRITERIA_LEVEL in ( ").append(Constants.CriteriaLevel.CA.getCode()).append(" , ").append(Constants.CriteriaLevel.SA.getCode()).append(") ").append(Constants.END_LINE);
          sql.append(Constants.END_LINE);
          sql.append("UPDATE #TEMP_TREATEMENT_TS  ").append(Constants.END_LINE);
          sql.append("SET RUNNING_ID = ").append(runningId).append(" , RUNNING_STTS = 1000 ").append(Constants.END_LINE);
          sql.append("from #TEMP_TREATEMENT_TS t1 , #TEMP_UPDATE  t2 ").append(Constants.END_LINE);
          sql.append("where t1.RUNNING_ID = 0 and t1.RUNNING_STTS = 0 and t1.CRITERIA_LEVEL = ").append(Constants.CriteriaLevel.CA.getCode()).append(" and t2.CRITERIA_LEVEL = ").append(Constants.CriteriaLevel.CA.getCode()).append("  and t2.CA_NO = t1.CA_NO ").append(Constants.END_LINE);
          sql.append("and t2.COLLECTION_PATH_ID = t1.COLLECTION_PATH_ID and t2.ACTION_ID = t1.ACTION_ID and t2.CRITERIA_ID = t1.CRITERIA_ID  ").append(Constants.END_LINE);
          sql.append(Constants.END_LINE);
          sql.append("UPDATE #TEMP_TREATEMENT_TS  ").append(Constants.END_LINE);
          sql.append("SET RUNNING_ID = ").append(runningId).append(" , RUNNING_STTS = 1000 ").append(Constants.END_LINE);
          sql.append("from #TEMP_TREATEMENT_TS t1 , #TEMP_UPDATE  t2 ").append(Constants.END_LINE);
          sql.append("where t1.RUNNING_ID = 0 and t1.RUNNING_STTS = 0 and t1.CRITERIA_LEVEL = ").append(Constants.CriteriaLevel.SA.getCode()).append(" and t2.CRITERIA_LEVEL = ").append(Constants.CriteriaLevel.SA.getCode()).append(" and t2.CA_NO = t1.CA_NO and t2.SA_NO = t1.SA_NO   ").append(Constants.END_LINE);
          sql.append("and t2.COLLECTION_PATH_ID = t1.COLLECTION_PATH_ID and t2.ACTION_ID = t1.ACTION_ID and t2.CRITERIA_ID = t1.CRITERIA_ID  ").append(Constants.END_LINE);
          sql.append(Constants.END_LINE);
          sql.append("SELECT * ").append(Constants.END_LINE);
          sql.append("FROM #TEMP_TREATEMENT_TS  ").append(Constants.END_LINE);
          sql.append("WHERE RUNNING_ID = ").append(runningId).append(Constants.END_LINE);
          sql.append("ORDER BY CA_NO,COLLECTION_PATH_ID, ACTION_ID,CRITERIA_ID  ").append(Constants.END_LINE);
          sql.append(Constants.END_LINE);
          sql.append("drop table #TEMP_UPDATE ").append(Constants.END_LINE);
          break;

        case SELECT_BA_LEVEL:
          sql.append("UPDATE TOP ").append(rowNumb).append(" #TEMP_TREATEMENT_TS ").append(Constants.END_LINE);
          sql.append("SET RUNNING_ID =  ").append(runningId).append(Constants.END_LINE);
          sql.append("WHERE RUNNING_ID = 0 ");
          sql.append(Constants.END_LINE);
          sql.append("SELECT * ").append(Constants.END_LINE);
          sql.append("FROM #TEMP_TREATEMENT_TS  ").append(Constants.END_LINE);
          sql.append("WHERE RUNNING_ID = ").append(runningId).append(Constants.END_LINE);
          break;
        default:
          break;
      }
      return sql;
    }

    private final HashMap<String, ArrayList<TempTreatment>> index = new HashMap<>();
    private long runningId;
    private int case_select = 10;

    @Override
    protected void setReturnValue(ResultSet rs) throws SQLException {
      TempTreatment temp = new TempTreatment();
      temp.setRowId(rs.getBigDecimal("ROWID"));
      temp.setTreatmentId(rs.getBigDecimal("TREATMENT_ID"));
      temp.setCaNumber(rs.getString("CA_NO"));
      temp.setBaNumber(rs.getString("BA_NO"));
      temp.setCollectionPathId(rs.getBigDecimal("COLLECTION_PATH_ID"));
      temp.setActionId(rs.getBigDecimal("ACTION_ID"));
      temp.setActionPathId(rs.getBigDecimal("ACTION_PATH_ID"));
      temp.setActionPathDtm(toDate(rs.getTimestamp("ACTION_PATH_DTM"), null));
      temp.setActionScheduleDtm(toDate(rs.getTimestamp("ACTION_SCHEDULE_DTM"), null));
      temp.setCriteriaId(rs.getBigDecimal("CRITERIA_ID"));
      temp.setActionCriteriaId(rs.getBigDecimal("ACTION_CRITERIA_ID"));
      temp.setActionStatus(Constants.mapActionStatus(rs.getBigDecimal("ACTION_STATUS").intValue()));
      temp.setActionStatusDtm(toDate(rs.getTimestamp("ACTION_STATUS_DTM"), null));
      temp.setActionMode(Constants.mapActionMode(rs.getBigDecimal("ACTION_MODE").intValue()));
      temp.setActionType(Constants.mapActionType(rs.getBigDecimal("ACTION_TYPE").intValue()));
      temp.setCriteriaLevel(Constants.mapCriteriaLevel(rs.getBigDecimal("CRITERIA_LEVEL").intValue()));
      temp.setBaTitle(rs.getString("BA_TITLE"));
      temp.setBaName(rs.getString("BA_NAME"));
      temp.setBaStatus(rs.getString("BA_STATUS"));
      temp.setBaStatusDtm(toDate(rs.getTimestamp("BA_STATUS_DTM"), null));
      temp.setCategory(rs.getString("CATEGORY"));
      temp.setCompanyCode(rs.getString("COMPANY_CODE"));
      temp.setRegisterDate(toDate(rs.getTimestamp("REGISTER_DATE"), null));
      temp.setRegisterLocation(rs.getBigDecimal("REGISTER_LOCATION"));
      //temp.setBillingSystemId(rs.getBigDecimal("BILLING_SYSTEM"));
      temp.setBillingCycle(rs.getString("BILLING_CYCLE"));
      temp.setPaymentTerm(rs.getBigDecimal("PAYMENT_TERM"));
      temp.setBillAddressName(rs.getString("BILL_ADDRESS_NAME"));
      temp.setBillZipcode(rs.getString("BILL_ZIPCODE"));
      temp.setBillProvinceId(rs.getBigDecimal("BILL_PROVINCE_ID"));
      temp.setBillRegionId(rs.getBigDecimal("BILL_REGION_ID"));
      temp.setBaStatus(rs.getString("BA_STATUS"));
      temp.setBaStatusGroup(Constants.mapBaStatusGroup(rs.getBigDecimal("BA_STATUS_GROUP").intValue()));
      temp.setCategory(rs.getString("CATEGORY"));
      temp.setSubcategory(rs.getString("SUBCATEGORY"));
      temp.setBaTotalBal(rs.getBigDecimal("BA_TOTAL_BAL"));
      temp.setDueTotalBal(rs.getBigDecimal("DUE_TOTAL_BAL"));
      temp.setMinInvoiceDate(toDate(rs.getTimestamp("MIN_INVOICE_DATE"), null));
      temp.setMinDueDate(toDate(rs.getTimestamp("MIN_DUE_DATE"), null));
      temp.setMaxInvoiceDate(toDate(rs.getTimestamp("MAX_INVOICE_DATE"), null));
      temp.setBaInvoiceCount(rs.getBigDecimal("BA_INVOICE_CNT"));
      temp.setMinInvoiceId(rs.getBigDecimal("MIN_INVOICE_ID"));
      temp.setRunningId(rs.getBigDecimal("RUNNING_ID"));
      temp.setRunningStts(rs.getBigDecimal("RUNNING_STTS"));

      ArrayList<TempTreatment> ll;
      // check PATH , ACTION , CRITERIA , CA ,SA
      switch (case_select) {
        case SELECT_CA_SA_BA_LEVEL:
        case SELECT_CA_SA_BA_COMPANY_CODE_LEVEL:
          if (temp.getCriteriaLevel().isBALevel()) {
            ll = new ArrayList<>();
            ll.add(temp);
            response.getResponse().add(ll);
          } else if (temp.getCriteriaLevel().isCALevel()) {
            String key = temp.getCompanyCode() + "_" + temp.getCollectionPathId().toPlainString() + "_" + temp.getActionId().toPlainString() + "_" + temp.getCriteriaId() + "_" + temp.getCaNumber() + "_CALEVEL";
            ll = index.get(key);
            if (ll == null) {
              ll = new ArrayList<>();
              index.put(key, ll);
              response.getResponse().add(ll);
            }
            ll.add(temp);
          } else if (temp.getCriteriaLevel().isSALevel()) {
            String key = temp.getCompanyCode() + "_" + temp.getCollectionPathId().toPlainString() + "_" + temp.getActionId().toPlainString() + "_" + temp.getCriteriaId() + "_" + temp.getCaNumber() + "_" + temp.getSaNumber() + "_SALEVEL";
            ll = index.get(key);
            if (ll == null) {
              ll = new ArrayList<>();
              index.put(key, ll);
              response.getResponse().add(ll);
            }
            ll.add(temp);
          }
          break;
        case SELECT_BA_LEVEL:
          ll = new ArrayList<>();
          ll.add(temp);
          response.getResponse().add(ll);
          break;
        default:
          break;
      }

    }

    protected TempTreatmentResponse execute(long runningId, int case_select) {
      this.case_select = case_select;
      this.runningId = runningId;
      return executeQuery(connection, false);
    }

  }

  public TempTreatmentResponse getListTempTreatment(long runningId) {
    return new GetListTempTreatment(logger).execute(runningId, GetListTempTreatment.SELECT_BA_LEVEL);
  }

  public TempTreatmentResponse getGroupListTempTreatment(long runningId) {
    return new GetListTempTreatment(logger).execute(runningId, GetListTempTreatment.SELECT_CA_SA_BA_COMPANY_CODE_LEVEL);
  }
  //</editor-fold>
  //
}
/*

      sql.append(" SELECT ").append(Constants.END_LINE);
      sql.append("   VROWID = identity(20) ,").append(Constants.END_LINE);
      sql.append("   d11.TREATMENT_ID, d11.CA_NO, d11.BA_NO, ").append(Constants.END_LINE);
      sql.append("   d11.COLLECTION_PATH_ID, d11.ACTION_ID, d11.ACTION_PATH_ID, d11.ACTION_PATH_DTM, d11.ACTION_SCHEDULE_DTM, d11.CRITERIA_ID, ").append(Constants.END_LINE);
      sql.append("   d11.ACTION_CRITERIA_ID, d11.ACTION_STATUS, d11.ACTION_STATUS_DTM,").append(Constants.END_LINE);
      sql.append("   d11.ACTION_MODE,d11.ACTION_TYPE ,d11.CRITERIA_LEVEL,").append(Constants.END_LINE);
      sql.append("   d11.BA_TITLE, d11.BA_NAME, d11.BA_STATUS_ID, d11.BA_STATUS_DTM, d11.CATEGORY_ID, d11.COMPANY_ID, ").append(Constants.END_LINE);
      sql.append("   d11.REGISTER_DATE, d11.REGISTER_LOCATION,   d11.BILLING_CYCLE, d11.PAYMENT_TERM,").append(Constants.END_LINE);
      sql.append("   d11.BILL_ADDRESS_NAME, d11.BILL_ZIPCODE, d11.BILL_PROVINCE_ID, d11.BILL_REGION_ID,").append(Constants.END_LINE);
      sql.append("   d11.BA_STATUS, d11.BA_STATUS_GROUP , d11.CATEGORY, d11.SUBCATEGORY ,").append(Constants.END_LINE);
      sql.append("   d12.AR_MOVEMENT_ID , d12.BA_TOTAL_BAL, d12.DUE_TOTAL_BAL, d12.MIN_INVOICE_DATE, d12.MIN_DUE_DATE, d12.MAX_INVOICE_DATE,d12.BA_INVOICE_CNT ,").append(Constants.END_LINE);
      sql.append("   CAST( 0 AS NUMERIC(19) ) RUNNING_ID , CAST( 0 AS NUMERIC(19) ) RUNNING_STTS INTO #TEMP_TREATEMENT_TS ").append(Constants.END_LINE);
      sql.append(" FROM (").append(Constants.END_LINE);
      sql.append("   SELECT ").append(Constants.END_LINE);
      sql.append("     d9.TREATMENT_ID, d9.CA_NO, d9.BA_NO, ").append(Constants.END_LINE);
      sql.append("     d9.COLLECTION_PATH_ID, d9.ACTION_ID, d9.ACTION_PATH_ID, d9.ACTION_PATH_DTM, d9.ACTION_SCHEDULE_DTM, d9.CRITERIA_ID, ").append(Constants.END_LINE);
      sql.append("     d9.ACTION_CRITERIA_ID, d9.ACTION_STATUS, d9.ACTION_STATUS_DTM, d9.MIN_DUE_DATE,").append(Constants.END_LINE);
      sql.append("     d9.ACTION_MODE,d9.ACTION_TYPE ,d9.CRITERIA_LEVEL,").append(Constants.END_LINE);
      sql.append("     d9.BA_TITLE, d9.BA_NAME, d9.BA_STATUS_ID, d9.BA_STATUS_DTM, d9.CATEGORY_ID, d9.COMPANY_ID, ").append(Constants.END_LINE);
      sql.append("     d9.REGISTER_DATE, d9.REGISTER_LOCATION,   d9.BILLING_CYCLE, d9.PAYMENT_TERM,").append(Constants.END_LINE);
      sql.append("     d9.BILL_ADDRESS_NAME, d9.BILL_ZIPCODE, d9.BILL_PROVINCE_ID, d9.BILL_REGION_ID,").append(Constants.END_LINE);
      sql.append("     d9.BA_STATUS, d9.BA_STATUS_GROUP , ").append(Constants.END_LINE);
      sql.append("     d10.CATEGORY, d10.SUBCATEGORY").append(Constants.END_LINE);
      sql.append("   FROM (").append(Constants.END_LINE);
      sql.append("     SELECT ").append(Constants.END_LINE);
      sql.append("       d7.TREATMENT_ID, d7.CA_NO, d7.BA_NO, ").append(Constants.END_LINE);
      sql.append("       d7.COLLECTION_PATH_ID, d7.ACTION_ID, d7.ACTION_PATH_ID, d7.ACTION_PATH_DTM, d7.ACTION_SCHEDULE_DTM, d7.CRITERIA_ID, ").append(Constants.END_LINE);
      sql.append("       d7.ACTION_CRITERIA_ID, d7.ACTION_STATUS, d7.ACTION_STATUS_DTM, d7.MIN_DUE_DATE,").append(Constants.END_LINE);
      sql.append("       d7.ACTION_MODE,d7.ACTION_TYPE ,d7.CRITERIA_LEVEL,").append(Constants.END_LINE);
      sql.append("       d7.BA_TITLE, d7.BA_NAME, d7.BA_STATUS_ID, d7.BA_STATUS_DTM, d7.CATEGORY_ID, d7.COMPANY_ID, ").append(Constants.END_LINE);
      sql.append("       d7.REGISTER_DATE, d7.REGISTER_LOCATION,  d7.BILLING_CYCLE, d7.PAYMENT_TERM,").append(Constants.END_LINE);
      sql.append("       d7.BILL_ADDRESS_NAME, d7.BILL_ZIPCODE, d7.BILL_PROVINCE_ID, d7.BILL_REGION_ID,").append(Constants.END_LINE);
      sql.append("       d8.BA_STATUS, d8.BA_STATUS_GROUP").append(Constants.END_LINE);
      sql.append("     FROM (").append(Constants.END_LINE);
      sql.append("       SELECT").append(Constants.END_LINE);
      sql.append("         d5.TREATMENT_ID, d5.CA_NO, d5.BA_NO, ").append(Constants.END_LINE);
      sql.append("         d5.COLLECTION_PATH_ID, d5.ACTION_ID, d5.ACTION_PATH_ID, d5.ACTION_PATH_DTM, d5.ACTION_SCHEDULE_DTM, d5.CRITERIA_ID, ").append(Constants.END_LINE);
      sql.append("         d5.ACTION_CRITERIA_ID, d5.ACTION_STATUS, d5.ACTION_STATUS_DTM, d5.MIN_DUE_DATE,").append(Constants.END_LINE);
      sql.append("         d5.ACTION_MODE,d5.ACTION_TYPE ,d5.CRITERIA_LEVEL,").append(Constants.END_LINE);
      sql.append("         d6.BA_TITLE, d6.BA_NAME, d6.BA_STATUS_ID, d6.BA_STATUS_DTM, d6.CATEGORY_ID, d6.COMPANY_ID, ").append(Constants.END_LINE);
      sql.append("         d6.REGISTER_DATE, d6.REGISTER_LOCATION,  d6.BILLING_CYCLE, d6.PAYMENT_TERM,").append(Constants.END_LINE);
      sql.append("         d6.BILL_ADDRESS_NAME, d6.BILL_ZIPCODE, d6.BILL_PROVINCE_ID, d6.BILL_REGION_ID").append(Constants.END_LINE);
      sql.append("       FROM (").append(Constants.END_LINE);
      sql.append("         SELECT ").append(Constants.END_LINE);
      sql.append("           d3.TREATMENT_ID, d3.CA_NO, d3.BA_NO, ").append(Constants.END_LINE);
      sql.append("           d3.COLLECTION_PATH_ID, d3.ACTION_ID, d3.ACTION_PATH_ID, d3.ACTION_PATH_DTM, d3.ACTION_SCHEDULE_DTM, d3.CRITERIA_ID, ").append(Constants.END_LINE);
      sql.append("           d3.ACTION_CRITERIA_ID, d3.ACTION_STATUS, d3.ACTION_STATUS_DTM, d3.MIN_DUE_DATE,").append(Constants.END_LINE);
      sql.append("           d3.ACTION_MODE,d3.ACTION_TYPE ,").append(Constants.END_LINE);
      sql.append("           d4.CRITERIA_LEVEL").append(Constants.END_LINE);
      sql.append("         FROM (").append(Constants.END_LINE);
      sql.append("           SELECT ").append(Constants.END_LINE);
      sql.append("             d1.TREATMENT_ID, d1.CA_NO, d1.BA_NO, ").append(Constants.END_LINE);
      sql.append("             d1.COLLECTION_PATH_ID, d1.ACTION_ID, d1.ACTION_PATH_ID, d1.ACTION_PATH_DTM, d1.ACTION_SCHEDULE_DTM, d1.CRITERIA_ID,").append(Constants.END_LINE);
      sql.append("             d1.ACTION_CRITERIA_ID, d1.ACTION_STATUS, d1.ACTION_STATUS_DTM, d1.MIN_DUE_DATE,").append(Constants.END_LINE);
      sql.append("             d2.ACTION_MODE,d2.ACTION_TYPE").append(Constants.END_LINE);
      sql.append("           FROM (").append(Constants.END_LINE);
      sql.append("             SELECT ").append(Constants.END_LINE);
      sql.append("               TREATMENT_ID, CA_NO, BA_NO, ").append(Constants.END_LINE);
      sql.append("               COLLECTION_PATH_ID, ACTION_ID, ACTION_PATH_ID, ACTION_PATH_DTM, ACTION_SCHEDULE_DTM, CRITERIA_ID, ").append(Constants.END_LINE);
      sql.append("               ACTION_CRITERIA_ID, ACTION_STATUS, ACTION_STATUS_DTM, MIN_DUE_DATE").append(Constants.END_LINE);
      sql.append("             FROM dbo.CL_TREATMENT ").append(Constants.END_LINE);
      sql.append("             WHERE ACTION_ID in (").append(actionList).append(") and ACTION_SCHEDULE_DTM < getdate()  and ACTION_STATUS =  ").append(Constants.ActionStatus.WaitingtoProcess.getCode()).append(Constants.END_LINE);
      sql.append("           )d1 left join (").append(Constants.END_LINE);
      sql.append("             SELECT ").append(Constants.END_LINE);
      sql.append("               ACTION_ID, ACTION_MODE, ACTION_TYPE").append(Constants.END_LINE);
      sql.append("             FROM dbo.CL_ACTION").append(Constants.END_LINE);
      sql.append("             WHERE RECORD_STATUS = 1").append(Constants.END_LINE);
      sql.append("           )d2 on d1.ACTION_ID = d2.ACTION_ID").append(Constants.END_LINE);
      sql.append("         )d3 left join (").append(Constants.END_LINE);
      sql.append("           SELECT ").append(Constants.END_LINE);
      sql.append("             ACTION_CRITERIA_ID, CRITERIA_LEVEL").append(Constants.END_LINE);
      sql.append("           FROM dbo.CL_ACTION_CRITERIA").append(Constants.END_LINE);
      sql.append("         )d4 on d3.ACTION_CRITERIA_ID = d4.ACTION_CRITERIA_ID").append(Constants.END_LINE);
      sql.append("       )d5 left join (").append(Constants.END_LINE);
      sql.append("         SELECT ").append(Constants.END_LINE);
      sql.append("           BA_NO, CA_NO, SA_NO, BA_TITLE, BA_NAME, BA_STATUS_ID, BA_STATUS_DTM, CATEGORY_ID, COMPANY_ID, ").append(Constants.END_LINE);
      sql.append("           REGISTER_DATE, REGISTER_LOCATION,  BILLING_CYCLE, PAYMENT_TERM,").append(Constants.END_LINE);
      sql.append("           BILL_ADDRESS_NAME, BILL_ZIPCODE, BILL_PROVINCE_ID, BILL_REGION_ID").append(Constants.END_LINE);
      sql.append("         FROM dbo.CL_BA_INFO").append(Constants.END_LINE);
      sql.append("       )d6 on d5.BA_NO = d6.BA_NO and d5.CA_NO = d6.CA_NO").append(Constants.END_LINE);
      sql.append("     )d7 left join (").append(Constants.END_LINE);
      sql.append("       SELECT ").append(Constants.END_LINE);
      sql.append("         BA_STATUS_ID, BA_STATUS, BA_STATUS_GROUP, RECORD_STATUS").append(Constants.END_LINE);
      sql.append("       FROM dbo.CL_SFF_BA_STATUS").append(Constants.END_LINE);
      sql.append("       WHERE RECORD_STATUS = 1").append(Constants.END_LINE);
      sql.append("     )d8 on d7.BA_STATUS_ID = d8.BA_STATUS_ID ").append(Constants.END_LINE);
      sql.append("   )d9 left join (").append(Constants.END_LINE);
      sql.append("     SELECT ").append(Constants.END_LINE);
      sql.append("       CATEGORY_ID, CATEGORY, CATEGORY_GROUP, SUBCATEGORY").append(Constants.END_LINE);
      sql.append("     FROM dbo.CL_SFF_CATEGORY").append(Constants.END_LINE);
      sql.append("     WHERE RECORD_STATUS = 1").append(Constants.END_LINE);
      sql.append("   )d10 on d9.CATEGORY_ID = d10.CATEGORY_ID").append(Constants.END_LINE);
      sql.append(" )d11 left join (").append(Constants.END_LINE);
      sql.append("   SELECT ").append(Constants.END_LINE);
      sql.append("     AR_MOVEMENT_ID,BA_NO, BA_TOTAL_BAL, DUE_TOTAL_BAL, MIN_INVOICE_DATE, MIN_DUE_DATE, MAX_INVOICE_DATE,BA_INVOICE_CNT ").append(Constants.END_LINE);
      sql.append("   FROM dbo.CL_HIS_BA_ACCOUNT_BALANCE").append(Constants.END_LINE);
      sql.append("   WHERE CURRENT_BOO = 'Y'").append(Constants.END_LINE);
      sql.append(" )d12 on d11.BA_NO = d12.BA_NO").append(Constants.END_LINE);
      sql.append(" ORDER BY MIN_DUE_DATE , DUE_TOTAL_BAL ").append(Constants.END_LINE);
      sql.append(Constants.END_LINE);
      sql.append(" SELECT ").append(Constants.END_LINE);
      sql.append("   TREATMENT_ID INTO #TEMPUPDATE").append(Constants.END_LINE);
      sql.append(" FROM (").append(Constants.END_LINE);
      sql.append("   SELECT VROWID , TREATMENT_ID").append(Constants.END_LINE);
      sql.append("   FROM #TEMP_TREATEMENT_TS").append(Constants.END_LINE);
      sql.append("   WHERE CRITERIA_LEVEL = 0").append(Constants.END_LINE);
      sql.append(" ) t1 ,(    ").append(Constants.END_LINE);
      sql.append("   SELECT MIN( VROWID ) MIN_VROWID ").append(Constants.END_LINE);
      sql.append("   FROM #TEMP_TREATEMENT_TS").append(Constants.END_LINE);
      sql.append("   WHERE CRITERIA_LEVEL = 0").append(Constants.END_LINE);
      sql.append("   GROUP BY CA_NO ,COMPANY_ID, ACTION_ID , CRITERIA_ID ").append(Constants.END_LINE);
      sql.append(" ) t2").append(Constants.END_LINE);
      sql.append(" where t2.MIN_VROWID = t1.VROWID ").append(Constants.END_LINE);
      sql.append(Constants.END_LINE);
      sql.append(" UPDATE #TEMP_TREATEMENT_TS ").append(Constants.END_LINE);
      sql.append(" set  RUNNING_STTS = 100  ").append(Constants.END_LINE);
      sql.append(" from #TEMP_TREATEMENT_TS t1 , #TEMPUPDATE  t2 ").append(Constants.END_LINE);
      sql.append(" where t1.TREATMENT_ID = t2.TREATMENT_ID  ").append(Constants.END_LINE);
      sql.append(Constants.END_LINE);
      sql.append(" drop table #TEMPUPDATE").append(Constants.END_LINE);
 */
 /* version 2 

      prepareDataTreatment.append("SELECT ").append(Constants.END_LINE);
      prepareDataTreatment.append("  ROWID = identity(20) ,").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.TREATMENT_ID, d11.CA_NO, d11.BA_NO, ").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.COLLECTION_PATH_ID, d11.ACTION_ID, d11.ACTION_PATH_ID, d11.ACTION_PATH_DTM, d11.ACTION_SCHEDULE_DTM, d11.CRITERIA_ID, ").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.ACTION_CRITERIA_ID, d11.ACTION_STATUS, d11.ACTION_STATUS_DTM,  ").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.ACTION_MODE,d11.ACTION_TYPE ,d11.CRITERIA_LEVEL,").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.SA_NO, d11.BA_TITLE, d11.BA_NAME, d11.BA_STATUS_ID, d11.BA_STATUS_DTM, ").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.CATEGORY_ID, d11.COMPANY_ID, d11.REGISTER_DATE, d11.REGISTER_LOCATION, d11.BILLING_SYSTEM_ID, d11.BILLING_CYCLE, d11.PAYMENT_TERM, ").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.PAYMENT_BEHAVIOR, d11.PAYMENT_METHOD_ID, d11.BANK_NAME, d11.BANK_ACCNT_NO, d11.CREDIT_CARD_TYPE, ").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.CREDIT_CARD_BANK, d11.CREDIT_CARD_NAME, d11.CREDIT_CARD_REF, d11.PRODUCT_GROUP_ID, d11.PRIORITY_STATUS_ID, d11.PRIORITY_STATUS_DATE, ").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.BILL_ADDRESS_NAME, d11.BILL_ADDRESS_LINE_1, d11.BILL_ADDRESS_LINE_2, d11.BILL_ADDRESS_LINE_3, d11.BILL_ADDRESS_LINE_4, d11.BILL_ADDRESS_LINE_5, ").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.BILL_ZIPCODE, d11.BILL_PROVINCE_ID, d11.BILL_REGION_ID, d11.BILL_LANGUAGE_ID, d11.REF_BA_NO, ").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.SFF_ACCOUNT_ID, d11.SB_ACCOUNT_ID,").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.BA_STATUS, d11.BA_STATUS_GROUP,").append(Constants.END_LINE);
      prepareDataTreatment.append("  d11.CATEGORY, d11.SUBCATEGORY,").append(Constants.END_LINE);
      prepareDataTreatment.append("  d12.BA_TOTAL_BAL, d12.DUE_TOTAL_BAL, d12.MIN_INVOICE_DATE, d12.MIN_DUE_DATE, d12.MAX_INVOICE_DATE, d12.BA_INVOICE_CNT ,d12.MIN_INVOICE_ID ,").append(Constants.END_LINE);
      prepareDataTreatment.append("  CAST( 0 AS NUMERIC(19) ) RUNNING_ID , CAST( 0 AS NUMERIC(19) ) RUNNING_STTS INTO #TEMP_TREATEMENT_TS ").append(Constants.END_LINE);
      prepareDataTreatment.append("FROM (").append(Constants.END_LINE);
      prepareDataTreatment.append("  SELECT ").append(Constants.END_LINE);
      prepareDataTreatment.append("    d9.TREATMENT_ID, d9.CA_NO, d9.BA_NO, ").append(Constants.END_LINE);
      prepareDataTreatment.append("    d9.COLLECTION_PATH_ID, d9.ACTION_ID, d9.ACTION_PATH_ID, d9.ACTION_PATH_DTM, d9.ACTION_SCHEDULE_DTM, d9.CRITERIA_ID, ").append(Constants.END_LINE);
      prepareDataTreatment.append("    d9.ACTION_CRITERIA_ID, d9.ACTION_STATUS, d9.ACTION_STATUS_DTM, d9.MIN_DUE_DATE,").append(Constants.END_LINE);
      prepareDataTreatment.append("    d9.ACTION_MODE,d9.ACTION_TYPE ,d9.CRITERIA_LEVEL,").append(Constants.END_LINE);
      prepareDataTreatment.append("    d9.SA_NO, d9.BA_TITLE, d9.BA_NAME, d9.BA_STATUS_ID, d9.BA_STATUS_DTM, ").append(Constants.END_LINE);
      prepareDataTreatment.append("    d9.CATEGORY_ID, d9.COMPANY_ID, d9.REGISTER_DATE, d9.REGISTER_LOCATION, d9.BILLING_SYSTEM_ID, d9.BILLING_CYCLE, d9.PAYMENT_TERM, ").append(Constants.END_LINE);
      prepareDataTreatment.append("    d9.PAYMENT_BEHAVIOR, d9.PAYMENT_METHOD_ID, d9.BANK_NAME, d9.BANK_ACCNT_NO, d9.CREDIT_CARD_TYPE, ").append(Constants.END_LINE);
      prepareDataTreatment.append("    d9.CREDIT_CARD_BANK, d9.CREDIT_CARD_NAME, d9.CREDIT_CARD_REF, d9.PRODUCT_GROUP_ID, d9.PRIORITY_STATUS_ID, d9.PRIORITY_STATUS_DATE, ").append(Constants.END_LINE);
      prepareDataTreatment.append("    d9.BILL_ADDRESS_NAME, d9.BILL_ADDRESS_LINE_1, d9.BILL_ADDRESS_LINE_2, d9.BILL_ADDRESS_LINE_3, d9.BILL_ADDRESS_LINE_4, d9.BILL_ADDRESS_LINE_5, ").append(Constants.END_LINE);
      prepareDataTreatment.append("    d9.BILL_ZIPCODE, d9.BILL_PROVINCE_ID, d9.BILL_REGION_ID, d9.BILL_LANGUAGE_ID, d9.REF_BA_NO, ").append(Constants.END_LINE);
      prepareDataTreatment.append("    d9.SFF_ACCOUNT_ID, d9.SB_ACCOUNT_ID,").append(Constants.END_LINE);
      prepareDataTreatment.append("    d9.BA_STATUS, d9.BA_STATUS_GROUP,").append(Constants.END_LINE);
      prepareDataTreatment.append("    d10.CATEGORY, d10.SUBCATEGORY").append(Constants.END_LINE);
      prepareDataTreatment.append("  FROM (").append(Constants.END_LINE);
      prepareDataTreatment.append("    SELECT ").append(Constants.END_LINE);
      prepareDataTreatment.append("      d7.TREATMENT_ID, d7.CA_NO, d7.BA_NO, ").append(Constants.END_LINE);
      prepareDataTreatment.append("      d7.COLLECTION_PATH_ID, d7.ACTION_ID, d7.ACTION_PATH_ID, d7.ACTION_PATH_DTM, d7.ACTION_SCHEDULE_DTM, d7.CRITERIA_ID, ").append(Constants.END_LINE);
      prepareDataTreatment.append("      d7.ACTION_CRITERIA_ID, d7.ACTION_STATUS, d7.ACTION_STATUS_DTM, d7.MIN_DUE_DATE,").append(Constants.END_LINE);
      prepareDataTreatment.append("      d7.ACTION_MODE,d7.ACTION_TYPE ,d7.CRITERIA_LEVEL,").append(Constants.END_LINE);
      prepareDataTreatment.append("      d7.SA_NO, d7.BA_TITLE, d7.BA_NAME, d7.BA_STATUS_ID, d7.BA_STATUS_DTM, ").append(Constants.END_LINE);
      prepareDataTreatment.append("      d7.CATEGORY_ID, d7.COMPANY_ID, d7.REGISTER_DATE, d7.REGISTER_LOCATION, d7.BILLING_SYSTEM_ID, d7.BILLING_CYCLE, d7.PAYMENT_TERM, ").append(Constants.END_LINE);
      prepareDataTreatment.append("      d7.PAYMENT_BEHAVIOR, d7.PAYMENT_METHOD_ID, d7.BANK_NAME, d7.BANK_ACCNT_NO, d7.CREDIT_CARD_TYPE, ").append(Constants.END_LINE);
      prepareDataTreatment.append("      d7.CREDIT_CARD_BANK, d7.CREDIT_CARD_NAME, d7.CREDIT_CARD_REF, d7.PRODUCT_GROUP_ID, d7.PRIORITY_STATUS_ID, d7.PRIORITY_STATUS_DATE, ").append(Constants.END_LINE);
      prepareDataTreatment.append("      d7.BILL_ADDRESS_NAME, d7.BILL_ADDRESS_LINE_1, d7.BILL_ADDRESS_LINE_2, d7.BILL_ADDRESS_LINE_3, d7.BILL_ADDRESS_LINE_4, d7.BILL_ADDRESS_LINE_5, ").append(Constants.END_LINE);
      prepareDataTreatment.append("      d7.BILL_ZIPCODE, d7.BILL_PROVINCE_ID, d7.BILL_REGION_ID, d7.BILL_LANGUAGE_ID, d7.REF_BA_NO, ").append(Constants.END_LINE);
      prepareDataTreatment.append("      d7.SFF_ACCOUNT_ID, d7.SB_ACCOUNT_ID,").append(Constants.END_LINE);
      prepareDataTreatment.append("      d8.BA_STATUS, d8.BA_STATUS_GROUP").append(Constants.END_LINE);
      prepareDataTreatment.append("    FROM (").append(Constants.END_LINE);
      prepareDataTreatment.append("      SELECT").append(Constants.END_LINE);
      prepareDataTreatment.append("        d5.TREATMENT_ID, d5.CA_NO, d5.BA_NO, ").append(Constants.END_LINE);
      prepareDataTreatment.append("        d5.COLLECTION_PATH_ID, d5.ACTION_ID, d5.ACTION_PATH_ID, d5.ACTION_PATH_DTM, d5.ACTION_SCHEDULE_DTM, d5.CRITERIA_ID, ").append(Constants.END_LINE);
      prepareDataTreatment.append("        d5.ACTION_CRITERIA_ID, d5.ACTION_STATUS, d5.ACTION_STATUS_DTM, d5.MIN_DUE_DATE,").append(Constants.END_LINE);
      prepareDataTreatment.append("        d5.ACTION_MODE,d5.ACTION_TYPE ,d5.CRITERIA_LEVEL,").append(Constants.END_LINE);
      prepareDataTreatment.append("        d6.SA_NO, d6.BA_TITLE, d6.BA_NAME, d6.BA_STATUS_ID, d6.BA_STATUS_DTM, ").append(Constants.END_LINE);
      prepareDataTreatment.append("        d6.CATEGORY_ID, d6.COMPANY_ID, d6.REGISTER_DATE, d6.REGISTER_LOCATION, d6.BILLING_SYSTEM_ID, d6.BILLING_CYCLE, d6.PAYMENT_TERM, ").append(Constants.END_LINE);
      prepareDataTreatment.append("        d6.PAYMENT_BEHAVIOR, d6.PAYMENT_METHOD_ID, d6.BANK_NAME, d6.BANK_ACCNT_NO, d6.CREDIT_CARD_TYPE, ").append(Constants.END_LINE);
      prepareDataTreatment.append("        d6.CREDIT_CARD_BANK, d6.CREDIT_CARD_NAME, d6.CREDIT_CARD_REF, d6.PRODUCT_GROUP_ID, d6.PRIORITY_STATUS_ID, d6.PRIORITY_STATUS_DATE, ").append(Constants.END_LINE);
      prepareDataTreatment.append("        d6.BILL_ADDRESS_NAME, d6.BILL_ADDRESS_LINE_1, d6.BILL_ADDRESS_LINE_2, d6.BILL_ADDRESS_LINE_3, d6.BILL_ADDRESS_LINE_4, d6.BILL_ADDRESS_LINE_5, ").append(Constants.END_LINE);
      prepareDataTreatment.append("        d6.BILL_ZIPCODE, d6.BILL_PROVINCE_ID, d6.BILL_REGION_ID, d6.BILL_LANGUAGE_ID, d6.REF_BA_NO, ").append(Constants.END_LINE);
      prepareDataTreatment.append("        d6.SFF_ACCOUNT_ID, d6.SB_ACCOUNT_ID").append(Constants.END_LINE);
      prepareDataTreatment.append("      FROM (").append(Constants.END_LINE);
      prepareDataTreatment.append("        SELECT ").append(Constants.END_LINE);
      prepareDataTreatment.append("          d3.TREATMENT_ID, d3.CA_NO, d3.BA_NO, ").append(Constants.END_LINE);
      prepareDataTreatment.append("          d3.COLLECTION_PATH_ID, d3.ACTION_ID, d3.ACTION_PATH_ID, d3.ACTION_PATH_DTM, d3.ACTION_SCHEDULE_DTM, d3.CRITERIA_ID, ").append(Constants.END_LINE);
      prepareDataTreatment.append("          d3.ACTION_CRITERIA_ID, d3.ACTION_STATUS, d3.ACTION_STATUS_DTM, d3.MIN_DUE_DATE,").append(Constants.END_LINE);
      prepareDataTreatment.append("          d3.ACTION_MODE,d3.ACTION_TYPE ,").append(Constants.END_LINE);
      prepareDataTreatment.append("          d4.CRITERIA_LEVEL").append(Constants.END_LINE);
      prepareDataTreatment.append("        FROM (").append(Constants.END_LINE);
      prepareDataTreatment.append("          SELECT ").append(Constants.END_LINE);
      prepareDataTreatment.append("            d1.TREATMENT_ID, d1.CA_NO, d1.BA_NO, ").append(Constants.END_LINE);
      prepareDataTreatment.append("            d1.COLLECTION_PATH_ID, d1.ACTION_ID, d1.ACTION_PATH_ID, d1.ACTION_PATH_DTM, d1.ACTION_SCHEDULE_DTM, d1.CRITERIA_ID, ").append(Constants.END_LINE);
      prepareDataTreatment.append("            d1.ACTION_CRITERIA_ID, d1.ACTION_STATUS, d1.ACTION_STATUS_DTM, d1.MIN_DUE_DATE,").append(Constants.END_LINE);
      prepareDataTreatment.append("            d2.ACTION_MODE,d2.ACTION_TYPE").append(Constants.END_LINE);
      prepareDataTreatment.append("          FROM (").append(Constants.END_LINE);
      prepareDataTreatment.append("            SELECT ").append(Constants.END_LINE);
      prepareDataTreatment.append("              TREATMENT_ID, CA_NO, BA_NO, ").append(Constants.END_LINE);
      prepareDataTreatment.append("              COLLECTION_PATH_ID, ACTION_ID, ACTION_PATH_ID, ACTION_PATH_DTM, ACTION_SCHEDULE_DTM, CRITERIA_ID, ").append(Constants.END_LINE);
      prepareDataTreatment.append("              ACTION_CRITERIA_ID, ACTION_STATUS, ACTION_STATUS_DTM, MIN_DUE_DATE").append(Constants.END_LINE);
      prepareDataTreatment.append("            FROM dbo.CL_TREATMENT ").append(Constants.END_LINE);
      prepareDataTreatment.append("            WHERE ACTION_ID in (").append(actionList).append(") and ACTION_SCHEDULE_DTM < getdate()  and ACTION_STATUS =  ").append(Constants.ActionStatus.WaitingtoProcess.getCode()).append(Constants.END_LINE);
      prepareDataTreatment.append("          )d1 left join (").append(Constants.END_LINE);
      prepareDataTreatment.append("            SELECT ").append(Constants.END_LINE);
      prepareDataTreatment.append("              ACTION_ID, ACTION_MODE, ACTION_TYPE").append(Constants.END_LINE);
      prepareDataTreatment.append("            FROM dbo.CL_ACTION").append(Constants.END_LINE);
      prepareDataTreatment.append("            WHERE RECORD_STATUS = 1").append(Constants.END_LINE);
      prepareDataTreatment.append("          )d2 on d1.ACTION_ID = d2.ACTION_ID").append(Constants.END_LINE);
      prepareDataTreatment.append("        )d3 left join (").append(Constants.END_LINE);
      prepareDataTreatment.append("          SELECT ").append(Constants.END_LINE);
      prepareDataTreatment.append("            ACTION_CRITERIA_ID, CRITERIA_LEVEL").append(Constants.END_LINE);
      prepareDataTreatment.append("          FROM dbo.CL_ACTION_CRITERIA").append(Constants.END_LINE);
      prepareDataTreatment.append("        )d4 on d3.ACTION_CRITERIA_ID = d4.ACTION_CRITERIA_ID").append(Constants.END_LINE);
      prepareDataTreatment.append("      )d5 left join (").append(Constants.END_LINE);
      prepareDataTreatment.append("        SELECT ").append(Constants.END_LINE);
      prepareDataTreatment.append("          BA_NO, CA_NO, SA_NO, BA_TITLE, BA_NAME, BA_STATUS_ID, BA_STATUS_DTM, ").append(Constants.END_LINE);
      prepareDataTreatment.append("          CATEGORY_ID, COMPANY_ID, REGISTER_DATE, REGISTER_LOCATION, BILLING_SYSTEM_ID, BILLING_CYCLE, PAYMENT_TERM, ").append(Constants.END_LINE);
      prepareDataTreatment.append("          PAYMENT_BEHAVIOR, PAYMENT_METHOD_ID, BANK_NAME, BANK_ACCNT_NO, CREDIT_CARD_TYPE, ").append(Constants.END_LINE);
      prepareDataTreatment.append("          CREDIT_CARD_BANK, CREDIT_CARD_NAME, CREDIT_CARD_REF, PRODUCT_GROUP_ID, PRIORITY_STATUS_ID, PRIORITY_STATUS_DATE, ").append(Constants.END_LINE);
      prepareDataTreatment.append("          BILL_ADDRESS_NAME, BILL_ADDRESS_LINE_1, BILL_ADDRESS_LINE_2, BILL_ADDRESS_LINE_3, BILL_ADDRESS_LINE_4, BILL_ADDRESS_LINE_5, ").append(Constants.END_LINE);
      prepareDataTreatment.append("          BILL_ZIPCODE, BILL_PROVINCE_ID, BILL_REGION_ID, BILL_LANGUAGE_ID, REF_BA_NO, ").append(Constants.END_LINE);
      prepareDataTreatment.append("          SFF_ACCOUNT_ID, SB_ACCOUNT_ID").append(Constants.END_LINE);
      prepareDataTreatment.append("        FROM dbo.CL_BA_INFO").append(Constants.END_LINE);
      prepareDataTreatment.append("      )d6 on d5.BA_NO = d6.BA_NO and d5.CA_NO = d6.CA_NO").append(Constants.END_LINE);
      prepareDataTreatment.append("    )d7 left join (").append(Constants.END_LINE);
      prepareDataTreatment.append("      SELECT ").append(Constants.END_LINE);
      prepareDataTreatment.append("        BA_STATUS_ID, BA_STATUS, BA_STATUS_GROUP, RECORD_STATUS").append(Constants.END_LINE);
      prepareDataTreatment.append("      FROM dbo.CL_SFF_BA_STATUS").append(Constants.END_LINE);
      prepareDataTreatment.append("      WHERE RECORD_STATUS = 1").append(Constants.END_LINE);
      prepareDataTreatment.append("    )d8 on d7.BA_STATUS_ID = d8.BA_STATUS_ID ").append(Constants.END_LINE);
      prepareDataTreatment.append("  )d9 left join (").append(Constants.END_LINE);
      prepareDataTreatment.append("    SELECT ").append(Constants.END_LINE);
      prepareDataTreatment.append("      CATEGORY_ID, CATEGORY, CATEGORY_GROUP, SUBCATEGORY").append(Constants.END_LINE);
      prepareDataTreatment.append("    FROM dbo.CL_SFF_CATEGORY").append(Constants.END_LINE);
      prepareDataTreatment.append("    WHERE RECORD_STATUS = 1").append(Constants.END_LINE);
      prepareDataTreatment.append("  )d10 on d9.CATEGORY_ID = d10.CATEGORY_ID").append(Constants.END_LINE);
      prepareDataTreatment.append(")d11 left join (").append(Constants.END_LINE);
      prepareDataTreatment.append("  SELECT ").append(Constants.END_LINE);
      prepareDataTreatment.append("    BA_NO, BA_TOTAL_BAL, DUE_TOTAL_BAL, MIN_INVOICE_DATE, MIN_DUE_DATE, MAX_INVOICE_DATE , BA_INVOICE_CNT ,MIN_INVOICE_ID ").append(Constants.END_LINE);
      prepareDataTreatment.append("  FROM dbo.CL_HIS_BA_ACCOUNT_BALANCE").append(Constants.END_LINE);
      prepareDataTreatment.append("  WHERE CURRENT_BOO = 'Y'").append(Constants.END_LINE);
      prepareDataTreatment.append(")d12 on d11.BA_NO = d12.BA_NO").append(Constants.END_LINE);
      prepareDataTreatment.append("ORDER BY MIN_DUE_DATE , DUE_TOTAL_BAL ").append(Constants.END_LINE);
 */
