/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.cmd.treatment;

import th.co.ais.cpac.cl.common.Context;

/**
 *
 * @author Optimus
 */
public class SampleContext {
  public static void main(String[] args) {
    
    
    Context context = new Context();
    context.initailLogger("LoggerMasterBatchInfo", "UserID|SessionID");
    //context.initailDataGrid("datagrid1");
    
    context.getLogger().debug("aaaaaaaaaaa");
    //context.getDataGrid().get("","");
    
  }
}
