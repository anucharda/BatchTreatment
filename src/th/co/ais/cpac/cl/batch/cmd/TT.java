/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.cmd;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import th.co.ais.cpac.cl.batch.util.UtilityDate;

/**
 *
 * @author Optimus
 */
public class TT {

  private static final String ASSING_ID = "<!--ASSING_ID-->";
  private static final String ASSING_IDR = "\\<!--ASSING_ID--\\>";

  public static void main(String[] args) throws ParseException {
    
    ArrayList<String> ll = new ArrayList<>();
    for(int i = 0 ; i < 100 ; i++){
      ll.add(""+i);
    }
    int i =0; 
    while(!ll.isEmpty()){
      String ooo = ll.remove(0);
      System.out.println((++i) + " -> "+ooo);
    }
    
    
    
    
    String aaaa = "bbbbb" + ASSING_ID + "bbbbbbb";
    System.out.println(aaaa.replaceAll(ASSING_IDR, "ddddddddddddddddddddd"));

    /*
    String sss = "|2134|";
    System.out.println(Integer.MAX_VALUE);
    System.out.println(new BigInteger("12345678901234567890123456789012345678901234567890123456789012345678901234567890").add(BigInteger.ONE));
    System.out.println();
    System.out.println(Arrays.toString(sss.split("\\|")));
     */
 /*
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    Date date1 = dateFormat.parse("2016/01/23");

    Calendar startCalendar = new GregorianCalendar();
    startCalendar.setTime(date1);
    for (int i = 0; i < 200; i++) {

      Date date2 = UtilityDate.addDate(null, date1, i, Calendar.DATE);
      // System.out.println(date2.toString());
      Calendar endCalendar = new GregorianCalendar();
      endCalendar.setTime(date2);

      if (endCalendar.get(Calendar.DATE) == startCalendar.get(Calendar.DATE)
        && endCalendar.get(Calendar.MONTH) == startCalendar.get(Calendar.MONTH)
        && endCalendar.get(Calendar.YEAR) == startCalendar.get(Calendar.YEAR)) {
        System.out.println("round[" + i + "]" + dateFormat.format(date1) + " -> " + dateFormat.format(date2) + " --> " + 0);
      } else {
        int diff = (endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR)) * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH) + 1;
        System.out.println("round[" + i + "]" + dateFormat.format(date1) + " -> " + dateFormat.format(date2) + " --> " + diff);
      }
    }
     */
 /*
     
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    Date convertedDate = new Date();
    Calendar c = Calendar.getInstance();
    c.setTime(convertedDate);
    c.set(Calendar.DAY_OF_MONTH, 31);
    System.out.println(c.getTime());
    c.add(Calendar.MONTH, 1);
    System.out.println(c.getTime());
     */
//    ArrayList<Date> l = new ArrayList<>();
//    l.add(null);
//    l.add(new Date());
//    
//    for(int i = 0 ; i < l.size();i++){
//      System.out.println(l.get(i));
//    }
  }
}
