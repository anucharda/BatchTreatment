/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.template;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import th.co.ais.cpac.cl.batch.cnf.CNFDatabase;
import th.co.ais.cpac.cl.batch.db.CLAction;
import th.co.ais.cpac.cl.batch.db.CLBatch;
import th.co.ais.cpac.cl.batch.db.CLCriteriaAttribute;
import th.co.ais.cpac.cl.batch.db.CLLanguage;
import th.co.ais.cpac.cl.batch.db.CLTempTreatment;
import th.co.ais.cpac.cl.batch.db.PMCompany;
import th.co.ais.cpac.cl.common.Context;
import th.co.ais.cpac.cl.common.UtilityLogger;
import th.co.ais.cpac.cl.template.database.DBConnectionPools;

/**
 *
 * @author Optimus
 */
public abstract class TreatmentTemplate {

  public TreatmentTemplate(String[] args) {
    argument = args;
  }

  protected enum TempTreatmentStatus {
    START(0),
    READY(1),
    EMPTY(2),
    ERROR(3);
    private final int code;

    private TempTreatmentStatus(int code) {
      this.code = code;
    }
  }

  protected String[] argument;
  protected CNFDatabase database;
  protected Context context;
  protected ArrayList<BigDecimal> listActionId;

  protected CLTempTreatment tempTable;

  protected abstract String getPathDatabase();

  protected abstract String getTreatmentName();

  protected abstract boolean business();

  protected DBConnectionPools getConnection(Context ctx) {
    DBConnectionPools<CNFDatabase, UtilityLogger> dd = new DBConnectionPools<>(database, ctx.getLogger());
    return dd;
  }

  protected boolean initial() {

    if (argument == null) {
      return true;
    }

    for (int i = 0; i < argument.length; i++) {
      if (listActionId == null) {
        listActionId = new ArrayList();
      }
      listActionId.add(new BigDecimal(argument[i]));
    }

    String fileConfig;
    if (getPathDatabase() == null) {
      fileConfig = System.getProperty("configuration.path");
    } else {
      fileConfig = getPathDatabase();
    }

    if (fileConfig == null) {
      System.out.println("File Configuration not found.");
      return false;
    }

    File f = new File(fileConfig);
    if (!f.isFile() || !f.canRead()) {
      System.out.println("File configuration can read.");
      return false;
    }
    database = new CNFDatabase(fileConfig);

    context = new Context();
    context.initailLogger("LoggerMasterBatchInfo", System.currentTimeMillis() + "");

    DBConnectionPools<CNFDatabase, UtilityLogger> pool = new DBConnectionPools<>(database, context.getLogger());
    pool.buildeDataSource();

    if (!pool.poolActive()) {
      System.out.println("Database connection pool error.");
      return false;
    }

    tempTable = new CLTempTreatment(context.getLogger());
    tempTable.buildConnection();

    return true;
  }

  protected boolean close() {

    if (tempTable != null && tempTable.getDBConnection() != null) {
      tempTable.getDBConnection().close();
    }
    new DBConnectionPools<>(database, context.getLogger()).closeDataSource();
    return true;
  }

  public boolean execute() {
    try {
      if (!initial()) {
        return false;
      }
      if (!business()) {
        return false;
      }
    } catch (Exception ex) {
    } finally {
      close();
    }
    return true;
  }

  protected CLBatch.CLBatchVersionInfo getBatchInfo(BigDecimal batchId) {
    CLBatch tblCLBatch = new CLBatch(context.getLogger());
    CLBatch.GetCLBatchVersionResponse response = tblCLBatch.getCLBatchVersion(batchId);
    context.getLogger().info(response.info().toString());
    switch (response.getStatusCode()) {
      case CLBatch.GetCLBatchVersionResponse.STATUS_COMPLETE: {
        return response.getResponse();
      }
      default: {
        return null;
      }
    }
  }

  protected CLBatch.CLBatchPathInfo getBatchPathInfo(BigDecimal batchId) {
    CLBatch tblCLBatch = new CLBatch(context.getLogger());
    CLBatch.CLBatchPathResponse response = tblCLBatch.getCLBatchPath(batchId);
    context.getLogger().info(response.info().toString());
    switch (response.getStatusCode()) {
      case CLBatch.CLBatchPathResponse.STATUS_COMPLETE: {
        CLBatch.CLBatchPathInfo t = response.getResponse();
        return t;
      }
      default: {
        return null;
      }
    }
  }

  protected ArrayList<CLCriteriaAttribute.CriteriaAttributeInfo> getCriteriaAttribute(BigDecimal criteriaId) {
    CLCriteriaAttribute tblCriteriaAttribute = new CLCriteriaAttribute(context.getLogger());
    CLCriteriaAttribute.CriteriaAttributeInfoResponse response = tblCriteriaAttribute.getCriteriaAttribute(criteriaId);
    context.getLogger().info(response.info().toString());
    switch (response.getStatusCode()) {
      case CLBatch.CLBatchPathResponse.STATUS_COMPLETE: {
        ArrayList<CLCriteriaAttribute.CriteriaAttributeInfo> t = response.getResponse();
        return t;
      }
      default: {
        return null;
      }
    }
  }

  protected HashMap<String, PMCompany.PMCompanyInfo> getCompanyActive() {
    PMCompany tblPMCompany = new PMCompany(context.getLogger());
    PMCompany.PMCompanyResponse response = tblPMCompany.getCompanyActive();
    context.getLogger().info(response.info().toString());
    switch (response.getStatusCode()) {
      case CLBatch.CLBatchPathResponse.STATUS_COMPLETE: {
        HashMap<String, PMCompany.PMCompanyInfo> t = response.getResponse();
        return t;
      }
      default: {
        return null;
      }
    }
  }

  protected HashMap<BigDecimal, CLLanguage.CLLanguagsInfo> getLanguageInfo() {
    CLLanguage tbl = new CLLanguage(context.getLogger());
    CLLanguage.LanguagsInfoResponse response = tbl.getLanguagsInfo();
    context.getLogger().info(response.info().toString());
    switch (response.getStatusCode()) {
      case CLLanguage.LanguagsInfoResponse.STATUS_COMPLETE: {
        HashMap<BigDecimal, CLLanguage.CLLanguagsInfo> tt = response.getResponse();
        return tt;
      }
      default: {
        return null;
      }
    }
  }

  protected HashMap<BigDecimal, CLAction.ActionDetail> getActionInfo() {
    CLAction pathAction = new CLAction(context.getLogger());
    CLAction.ActionDetailResponse pathActionInfo = pathAction.queryActionDetail();
    context.getLogger().debug(pathActionInfo.info().toString());
    switch (pathActionInfo.getStatusCode()) {
      case CLAction.ActionDetailResponse.STATUS_COMPLETE: {
        return pathActionInfo.getResponse();
      }
      case CLAction.ActionDetailResponse.STATUS_DATA_NOT_FOUND: {
        return new HashMap<>();
      }
      default: {
        return null;
      }
    }
  }

  protected TempTreatmentStatus tempTreatmentStatus = TempTreatmentStatus.START;
  protected ArrayList<ArrayList<CLTempTreatment.TempTreatment>> listTempTreatment;
  protected boolean endLoopTempTreatmentError = false;
  protected int errorRound = 0;

  protected boolean canPrepareTempTreatment() {
    CLTempTreatment.ExecuteResponse response = tempTable.prepareDataTreatment(listActionId);
    context.getLogger().info(response.info().toString());
    switch (response.getStatusCode()) {
      case CLTempTreatment.ExecuteResponse.STATUS_COMPLETE: {
        tempTreatmentStatus = TempTreatmentStatus.READY;
        return true;
      }
      case CLTempTreatment.ExecuteResponse.STATUS_DATA_NOT_FOUND: {
        tempTreatmentStatus = TempTreatmentStatus.EMPTY;
        return true;
      }
      default: {
        tempTreatmentStatus = TempTreatmentStatus.ERROR;
        return false;
      }
    }
  }

  protected boolean canPrepareCriteriaLevel() {
    CLTempTreatment.ExecuteResponse response = tempTable.prepareCriteraiLevel();
    context.getLogger().info(response.info().toString());
    switch (response.getStatusCode()) {
      case CLTempTreatment.ExecuteResponse.STATUS_COMPLETE: {
        tempTreatmentStatus = TempTreatmentStatus.READY;
        return true;
      }
      case CLTempTreatment.ExecuteResponse.STATUS_DATA_NOT_FOUND: {
        tempTreatmentStatus = TempTreatmentStatus.EMPTY;
        return true;
      }
      default: {
        tempTreatmentStatus = TempTreatmentStatus.ERROR;
        return false;
      }
    }
  }

  protected boolean canPrepareCriteriaLevelAndCompanyCode() {
    CLTempTreatment.ExecuteResponse response = tempTable.prepareCriteraiLevel();
    context.getLogger().info(response.info().toString());
    switch (response.getStatusCode()) {
      case CLTempTreatment.ExecuteResponse.STATUS_COMPLETE: {
        tempTreatmentStatus = TempTreatmentStatus.READY;
        return true;
      }
      case CLTempTreatment.ExecuteResponse.STATUS_DATA_NOT_FOUND: {
        tempTreatmentStatus = TempTreatmentStatus.EMPTY;
        return true;
      }
      default: {
        tempTreatmentStatus = TempTreatmentStatus.ERROR;
        return false;
      }
    }
  }

  protected void getListTempTreatment() {
    CLTempTreatment.TempTreatmentResponse response = tempTable.getListTempTreatment(System.nanoTime());
    context.getLogger().info(response.info().toString());
    switch (response.getStatusCode()) {
      case CLTempTreatment.ExecuteResponse.STATUS_COMPLETE: {
        listTempTreatment = response.getResponse();
        tempTreatmentStatus = TempTreatmentStatus.READY;
        errorRound = 0;
        break;
      }
      case CLTempTreatment.ExecuteResponse.STATUS_DATA_NOT_FOUND: {
        listTempTreatment = new ArrayList<>();
        tempTreatmentStatus = TempTreatmentStatus.EMPTY;
        errorRound = 0;
        break;
      }
      default: {
        errorRound++;
        tempTreatmentStatus = TempTreatmentStatus.ERROR;
        break;
      }
    }
  }

  protected void getListGroupCriteriaTempTreatment() {
    CLTempTreatment.TempTreatmentResponse response = tempTable.getGroupListTempTreatment(System.nanoTime());
    context.getLogger().info(response.info().toString());
    switch (response.getStatusCode()) {
      case CLTempTreatment.ExecuteResponse.STATUS_COMPLETE: {
        listTempTreatment = response.getResponse();
        tempTreatmentStatus = TempTreatmentStatus.READY;
        errorRound = 0;
        break;
      }
      case CLTempTreatment.ExecuteResponse.STATUS_DATA_NOT_FOUND: {
        listTempTreatment = new ArrayList<>();
        tempTreatmentStatus = TempTreatmentStatus.EMPTY;
        errorRound = 0;
        break;
      }
      default: {
        errorRound++;
        tempTreatmentStatus = TempTreatmentStatus.ERROR;
        break;
      }
    }
  }

  protected boolean endLoopTempTreatment() {
    return tempTreatmentStatus == TempTreatmentStatus.EMPTY;
  }

  protected boolean endLoopRetry() {
    return errorRound >= 10;
  }

  protected String getFileName() {
    return "";
  }

  // 
  protected boolean overLimitPerDay(CLBatch.CLBatchVersionInfo batchVersionInfo, int recordNumb) {
    if (batchVersionInfo.getLimitPerFile() == null || batchVersionInfo.getLimitPerFile().intValue() < 1) {
      return false;
    }
    return batchVersionInfo.getLimitPerFile().intValue() > recordNumb;
  }

  protected boolean overLimitPerFile(CLBatch.CLBatchVersionInfo batchVersionInfo, int recordNumb) {
    if (batchVersionInfo.getLimitPerFile() == null || batchVersionInfo.getLimitPerFile().intValue() < 1) {
      return false;
    }
    return batchVersionInfo.getLimitPerFile().intValue() > recordNumb;
  }

  public static void main(String[] args) {

    TreatmentTemplate mm = new TreatmentTemplate(args) {
      @Override
      protected String getPathDatabase() {
        return "D:\\Work\\Ais\\Apps\\Batch\\configuration\\database.properties";
      }

      @Override
      protected String getTreatmentName() {
        return "SMS";
      }

      @Override
      protected boolean business() {
        //context.getLogger().debug("xxxxxxx");
        return true;
      }

    };

    mm.execute();
  }

}
