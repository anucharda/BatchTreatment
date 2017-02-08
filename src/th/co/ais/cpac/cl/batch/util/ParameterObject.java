/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import th.co.ais.cpac.cl.batch.db.CLOrder;

/**
 *
 * @author Optimus
 */
public class ParameterObject {

  public static void main(String[] args) {
    CLOrder db = new CLOrder(null);
    CLOrder.CLBatcInfo ooo = db.buildCLBatcInfo();
    String objectname = "clBatchInfo";
    Method[] method = CLOrder.CLBatcInfo.class.getMethods();
    ArrayList<String> listOutput = new ArrayList<String>();
    
    for (int i = 0; i < method.length; i++) {      
      //System.out.println(method[i].getName());
      String name = method[i].getName();
      if(name.startsWith("set")){
        String gen = objectname+"."+name+"(xxxxxx);";
        listOutput.add(gen);        
      }
    }
    Collections.sort(listOutput);
    for(int i = 0 ; i < listOutput.size();i++){
      System.out.println(listOutput.get(i));
    }
  }
}
