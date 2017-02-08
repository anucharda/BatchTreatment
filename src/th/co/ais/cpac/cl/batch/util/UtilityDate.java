/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import th.co.ais.cpac.cl.common.Context;

/**
 *
 * @author Optimus
 */
public class UtilityDate {

  public static final Date addDate(Context ctx, Date d, int no, int field) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(d);
    cal.add(field, no);
    return cal.getTime();
  }

  public static final Date meargeTime(Context ctx, Date date, Date time, Date defVal) {
    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat sdfTIme = new SimpleDateFormat("hhmm");
    SimpleDateFormat newDate = new SimpleDateFormat("yyyyMMdd hhmm");
    try {
      return newDate.parse(sdfDate.format(date) + " " + sdfTIme.format(time));
    } catch (ParseException ex) {
    }
    return defVal;
  }

  public static final Long toLong(Context ctx, Date date, String format, Long defVal) {
    SimpleDateFormat sdfDate = new SimpleDateFormat(format);
    try {
      return Long.parseLong(sdfDate.format(date));
    } catch (Exception ex) {
    }
    return defVal;
  }

  public static final Date addDate(Context ctx, Long date, String format, int no, int fieldType, Date defVal) {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    try {
      Date datex = sdf.parse(date.toString());
      return addDate(ctx, datex, no, fieldType);
    } catch (Exception ex) {
    }
    return defVal;
  }
  private static final String dateFormat = "yyyyMMddHHmmssSSS";

  public static final String toString(Context ctx, Date date, String defVals) {
    SimpleDateFormat dd = new SimpleDateFormat(dateFormat);
    try {
      dd.format(date);
    } catch (Exception ex) {

    }
    return defVals;
  }
}
