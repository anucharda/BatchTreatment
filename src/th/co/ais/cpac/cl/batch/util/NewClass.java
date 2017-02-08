/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.util;

import java.math.BigDecimal;

/**
 *
 * @author Optimus
 */
public class NewClass {
  public static void main(String[] args) {
    System.out.println(" :: " +BigDecimal.TEN.compareTo(BigDecimal.ZERO) );
    
    if(new BigDecimal(8).equals(new BigDecimal("8"))){
      System.out.println("same");
    }else{
      System.out.println("no");
    }
  }
}
