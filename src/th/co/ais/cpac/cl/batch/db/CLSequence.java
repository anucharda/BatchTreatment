/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.db;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import th.co.ais.cpac.cl.batch.Constants;
import th.co.ais.cpac.cl.batch.cnf.CNFDatabase;
import th.co.ais.cpac.cl.common.Context;
import th.co.ais.cpac.cl.common.UtilityLogger;
import th.co.ais.cpac.cl.template.database.DBConnectionPools;
import th.co.ais.cpac.cl.template.database.DBTemplatesExecuteQuery;
import th.co.ais.cpac.cl.template.database.DBTemplatesResponse;

/**
 *
 * @author Optimus
 */
public class CLSequence {

  protected final UtilityLogger logger;

  public CLSequence(UtilityLogger logger) {
    this.logger = logger;
  }

  //<editor-fold defaultstate="collapsed" desc="Get Sequence By Key">
  public class CLSequenceInfo {

    private BigDecimal sequence;

    public BigDecimal getSequence() {
      return sequence;
    }

    public void setSequence(BigDecimal sequence) {
      this.sequence = sequence;
    }

  }

  public class CLSequenceInfoResponse extends DBTemplatesResponse< CLSequenceInfo> {

    @Override
    protected CLSequenceInfo createResponse() {
      return new CLSequenceInfo();
    }

  }

  protected class GetSequence extends DBTemplatesExecuteQuery<CLSequenceInfoResponse, UtilityLogger, DBConnectionPools> {

    public GetSequence(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected CLSequenceInfoResponse createResponse() {
      return new CLSequenceInfoResponse();
    }
    private String key = null;

    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append(" SELECT CURRENT_RUNNO FROM CL_RUNNO WHERE _TABLE_NAME = '").append(key).append("'");
      return sql;
    }

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      response.getResponse().setSequence(resultSet.getBigDecimal("CURRENT_RUNNO"));
    }

    protected CLSequenceInfoResponse execute(String keySeq) {
      this.key = keySeq;
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public CLSequenceInfoResponse getSequence(String key) {
    return new GetSequence(logger).execute(key);
  }
  //</editor-fold>

  static class Run extends Thread {

    int i = 0;

    public Run(int i) {
      this.i = i;
    }

    @Override
    public void run() {
      //System.out.println("round : " + i + " -> " + System.currentTimeMillis());
      Context ctx = new Context();
      ctx.initailLogger("LoggerMasterBatchInfo", "" + i);
      CLSequence cl = new CLSequence(ctx.getLogger());
      CLSequenceInfoResponse resp = cl.getSequence("boy");
      //System.out.println(resp.info());
      switch (resp.getStatusCode()) {
        case CLSequenceInfoResponse.STATUS_COMPLETE: {
          //System.out.println(i + " : " + resp.getResponse().getSequence().toPlainString());
          System.out.println(resp.info() + " -> " + resp.getResponse().getSequence().toPlainString());
          break;
        }
        default: {
          System.out.println(i + " : " + "ERROR " + resp.info().toString());
        }
      }

    }

  }

  public static void main(String[] args) throws InterruptedException, SQLException {

    long k = System.currentTimeMillis();
    CNFDatabase cnf = new CNFDatabase("D:\\Work\\Ais\\Apps\\Batch\\configuration\\database.properties");

    Context ctx = new Context();
    ctx.initailLogger("LoggerMasterBatchInfo", "XXXX|YYYYY");

    DBConnectionPools<CNFDatabase, UtilityLogger> dbPool = new DBConnectionPools<>(cnf, ctx.getLogger());

    dbPool.buildeDataSource();
    dbPool.getConnection();

    dbPool.close();

    if (!dbPool.poolActive()) {
      System.out.println("pool error");
      return;
    }
    Thread.sleep(2000);

    ExecutorService executor14 = Executors.newFixedThreadPool(60);

    for (int j = 0; j < 200; j++) {
      executor14.execute(new Run(j));
    }

    executor14.shutdown();

    while (!executor14.isTerminated()) {
      try {
        Thread.sleep(1000L);
      } catch (InterruptedException ex) {
      }
    }

    /* */
 /*
    ArrayList<DBConnectionPools> list = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      //Thread.sleep(1000);
      DBConnectionPools<CNFDatabase, UtilityLogger> dbPools = new DBConnectionPools<>(cnf, ctx.getLogger());
      dbPools.getConnection();
      list.add(dbPools);
      
      System.out.println(i );
    }
    
    for(int i =0 ; i < 100 ; i ++){
      list.get(i).close();
    }
     */
    //Thread.sleep(30000);
    dbPool.closeDataSource();
  }

}
