/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.cnf;

import java.util.HashMap;
import th.co.ais.cpac.cl.template.configuration.CNFFileTemplate;

/**
 *
 * @author Optimus
 */
public class CNFDatabase extends CNFFileTemplate {

  public CNFDatabase() {
  }

  public CNFDatabase(String nfc) {
    super(nfc);
  }

  private static final Object concurrent = new Object();

  private static HashMap<String, String> data = null;
  private static HashMap<String, Object> arrayData = null;

  private static String absolutepath = null;
  private static int timeInterval = 0;
  private static long lastupdatefile = 0;
  private static long lastupdatetime = 0;

  @Override
  protected Object getObjectSynchronized() {
    return concurrent;
  }

  @Override
  protected void setAbsolutePath(String p) {
    absolutepath = null;
    absolutepath = p;
  }

  @Override
  protected String getAbsolutePath() {
    return absolutepath;
  }

  @Override
  protected int getTimeInterval() {
    return timeInterval;
  }

  @Override
  protected void setTimeInterval(int p) {
    timeInterval = p;
  }

  @Override
  protected long getLastUpdateFile() {
    return lastupdatefile;
  }

  @Override
  protected void setLastUpdateFile(long p) {
    lastupdatefile = p;
  }

  @Override
  protected long getTimeUpdateHashMap() {
    return lastupdatetime;
  }

  @Override
  protected void setTimeUpdateHashMap(long p) {
    lastupdatetime = p;
  }

  @Override
  protected HashMap<String, String> createHashMap() {
    if (data == null) {
      data = new HashMap<>();
    }
    return data;
  }

  @Override
  protected HashMap<String, Object> createHashMapArray() {
    if (arrayData == null) {
      arrayData = new HashMap<>();
    }
    return arrayData;
  }

  @Override
  protected HashMap<String, String> getHashMap() {
    return data;
  }

  @Override
  protected HashMap<String, Object> getHashMapArray() {
    return arrayData;
  }

  public static void main(String[] args) {

    CNFDatabase cc = new CNFDatabase("D:\\AppsServer\\apache-tomcat-8.0.9\\Apps\\gbe\\conf\\common.properties");
    System.out.println(cc.dump("\r\n"));

    System.out.println(new CNFDatabase().dump("\r\n"));

  }
}
