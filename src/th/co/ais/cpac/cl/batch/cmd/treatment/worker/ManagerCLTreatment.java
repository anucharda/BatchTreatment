/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.cmd.treatment.worker;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import th.co.ais.cpac.cl.batch.db.CLTempHisBaAccountBalance;
import th.co.ais.cpac.cl.common.Context;

/**
 *
 * @author Optimus
 */
public class ManagerCLTreatment {

  private final Context ctx;

  public ManagerCLTreatment(Context ctx) {
    this.ctx = ctx;
  }

  public void copy2Treatment() {

    while (true) {
      CLTempHisBaAccountBalance dbTemp = new CLTempHisBaAccountBalance(ctx.getLogger());
      CLTempHisBaAccountBalance.TreatmentResponse list = dbTemp.queryTempAccount(new BigDecimal(System.nanoTime()));
      switch (list.getStatusCode()) {
        case CLTempHisBaAccountBalance.TempAccountBalanceResponse.STATUS_COMPLETE: {

          ExecutorService executor1 = Executors.newFixedThreadPool(1000);
          
          Iterator<String> keys = list.getResponse().keySet().iterator();
          while(keys.hasNext()){
            String key = keys.next();
            ArrayList<CLTempHisBaAccountBalance.TempAccountBalance> temp = list.getResponse().get(key);
            executor1.execute(new WorkerCopyTreatment(ctx, temp));
          }
          
          executor1.shutdown();

          while (!executor1.isTerminated()) {
            try {
              Thread.sleep(1000L);
            } catch (InterruptedException ex) {
            }
          }
          break;
        }
        case CLTempHisBaAccountBalance.TempAccountBalanceResponse.STATUS_DATA_NOT_FOUND: {
          //end while true
          return;
        }
        default: {
          //sleep and retry
        }
      }
    }    
  }
}
