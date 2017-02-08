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
import th.co.ais.cpac.cl.batch.db.CLBatch;
import th.co.ais.cpac.cl.batch.db.CLCriteriaAttribute;
import th.co.ais.cpac.cl.batch.db.PMCompany;
import th.co.ais.cpac.cl.common.Context;
import th.co.ais.cpac.cl.common.UtilityLogger;
import th.co.ais.cpac.cl.template.database.DBConnectionPools;

/**
 *
 * @author Optimus
 */
public abstract class z_TreatmentTemplate_1 {

  public z_TreatmentTemplate_1(String[] args) {

  }

  protected String[] argument;
  protected CNFDatabase database;
  protected Context context;
  protected ArrayList<BigDecimal> listActionId;

   
  
  protected abstract String getPathDatabase();

  protected abstract String getTreatmentName();

  protected abstract boolean business();

  protected DBConnectionPools getConnection(Context ctx) {
    DBConnectionPools<CNFDatabase, UtilityLogger> dd = new DBConnectionPools<>(database, ctx.getLogger());
    return dd;
  }

  protected boolean initial() {
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
    context.initailLogger("LoggerMasterBatchInfo", System.currentTimeMillis() + "|system");

    DBConnectionPools<CNFDatabase, UtilityLogger> pool = new DBConnectionPools<>(database, context.getLogger());
    pool.buildeDataSource();

    if (!pool.poolActive()) {
      System.out.println("Database connection pool error.");
      return false;
    }

    if (argument == null) {
      return true;
    }

    for (int i = 0; i < argument.length; i++) {
      if (listActionId == null) {
        listActionId = new ArrayList();
      }
      listActionId.add(new BigDecimal(argument[i]));
    }

    return true;
  }

  protected boolean close() {
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

  // 
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
        context.getLogger().debug("xxxxxxx");
        return true;
      }

    };

    mm.execute();
  }

}
