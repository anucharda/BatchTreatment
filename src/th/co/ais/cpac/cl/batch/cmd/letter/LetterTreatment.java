/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.cmd.letter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import th.co.ais.cpac.cl.batch.Constants;
import th.co.ais.cpac.cl.batch.db.CLAction;
import th.co.ais.cpac.cl.batch.db.CLBatch;
import th.co.ais.cpac.cl.batch.db.CLCriteriaTemplate;
import th.co.ais.cpac.cl.batch.db.CLLanguage;
import th.co.ais.cpac.cl.batch.db.CLLetterTreatment;
import th.co.ais.cpac.cl.batch.db.PMCompany;
import th.co.ais.cpac.cl.batch.template.SMSLetterTreatmentTemplate;
import th.co.ais.cpac.cl.common.Context;

/**
 *
 * @author Optimus
 */
public class LetterTreatment extends SMSLetterTreatmentTemplate {

  private static final BigDecimal BATCH_TYPE_LETTER_IHL = new BigDecimal("1005001000");
  private static final BigDecimal BATCH_TYPE_LETTER_NL = new BigDecimal("1005002000");
  private static final BigDecimal BATCH_TYPE_LETTER_NLC = new BigDecimal("1005003000");
  private static final BigDecimal BATCH_TYPE_LETTER_RLC = new BigDecimal("1005004000");
  private static final BigDecimal BATCH_TYPE_LETTER_TL = new BigDecimal("1005005000");
  private static final BigDecimal BATCH_TYPE_LETTER_TLC = new BigDecimal("1005006000");
  private static final BigDecimal BATCH_TYPE_LETTER_WL = new BigDecimal("1005007000");
  private static final BigDecimal BATCH_TYPE_LETTER_WLC = new BigDecimal("1005008000");

  private static final BigDecimal BATCH_TYPE_LETTER_EMAIL = new BigDecimal("1005009000");

  private static final String ASSING_ID = "<!--ASSING_ID-->";
  private static final String ASSING_ID_REPLACE = "\\<!--ASSING_ID--\\>";

  public LetterTreatment(String[] args) {
    super(args);
  }

  @Override
  protected String getPathDatabase() {
    return "D:\\Work\\Ais\\Apps\\Batch\\configuration\\database.properties";
  }

  @Override
  protected String getTreatmentName() {
    return "LETTER";
  }

  @Override
  protected boolean business() {
    //<editor-fold defaultstate="collapsed" desc="master">
    //<editor-fold defaultstate="collapsed" desc="get Batch Version Info">
    //CLBatch.CLBatchVersionInfo batchIdLetterInfo = null;
    /* ??? รอ update ว่าต้อง select อะไรบ้าง */
    HashMap<String, CLBatch.CLBatchVersionInfo> batchVersion = new HashMap<>();

    CLBatch.CLBatchVersionInfo batchIdIL = getBatchInfo(BATCH_TYPE_LETTER_IHL);
    if (batchIdIL == null) {
      context.getLogger().debug("BATCH_TYPE_LETTER_IL not found. (" + BATCH_TYPE_LETTER_IHL + ")");
      return false;
    }
    batchVersion.put(Constants.LetterType.InHouseLetter.getStringCode(), batchIdIL);

    CLBatch.CLBatchVersionInfo batchIdNL = getBatchInfo(BATCH_TYPE_LETTER_NL);
    if (batchIdNL == null) {
      context.getLogger().debug("BATCH_TYPE_LETTER_NL not found. (" + BATCH_TYPE_LETTER_NL + ")");
      return false;
    }
    batchVersion.put(Constants.LetterType.NoticeLetter.getStringCode(), batchIdNL);

    CLBatch.CLBatchVersionInfo batchIdNLC = getBatchInfo(BATCH_TYPE_LETTER_NLC);
    if (batchIdNLC == null) {
      context.getLogger().debug("BATCH_TYPE_LETTER_NLC not found. (" + BATCH_TYPE_LETTER_NLC + ")");
      return false;
    }
    batchVersion.put(Constants.LetterType.NoticeLetter.getStringCodeCorporate(), batchIdNLC);

    CLBatch.CLBatchVersionInfo batchIdRLC = getBatchInfo(BATCH_TYPE_LETTER_RLC);
    if (batchIdRLC == null) {
      context.getLogger().debug("BATCH_TYPE_LETTER_RLC not found. (" + BATCH_TYPE_LETTER_RLC + ")");
      return false;
    }
    batchVersion.put(Constants.LetterType.RemindLetter.getStringCodeCorporate(), batchIdRLC);

    CLBatch.CLBatchVersionInfo batchIdTL = getBatchInfo(BATCH_TYPE_LETTER_TL);
    if (batchIdTL == null) {
      context.getLogger().debug("BATCH_TYPE_LETTER_TL not found. (" + BATCH_TYPE_LETTER_TL + ")");
      return false;
    }
    batchVersion.put(Constants.LetterType.TerminateLetter.getStringCode(), batchIdTL);

    CLBatch.CLBatchVersionInfo batchIdTLC = getBatchInfo(BATCH_TYPE_LETTER_TLC);
    if (batchIdTLC == null) {
      context.getLogger().debug("BATCH_TYPE_LETTER_TLC not found. (" + BATCH_TYPE_LETTER_TLC + ")");
      return false;
    }
    batchVersion.put(Constants.LetterType.TerminateLetter.getStringCodeCorporate(), batchIdTLC);

    CLBatch.CLBatchVersionInfo batchIdWL = getBatchInfo(BATCH_TYPE_LETTER_WL);
    if (batchIdWL == null) {
      context.getLogger().debug("BATCH_TYPE_LETTER_WL not found. (" + BATCH_TYPE_LETTER_WL + ")");
      return false;
    }
    batchVersion.put(Constants.LetterType.WarningLetter.getStringCode(), batchIdWL);

    CLBatch.CLBatchVersionInfo batchIdWLC = getBatchInfo(BATCH_TYPE_LETTER_WLC);
    if (batchIdWLC == null) {
      context.getLogger().debug("BATCH_TYPE_LETTER_WLC not found. (" + BATCH_TYPE_LETTER_WLC + ")");
      return false;
    }
    batchVersion.put(Constants.LetterType.WarningLetter.getStringCodeCorporate(), batchIdWLC);

    CLBatch.CLBatchVersionInfo batchIdEMAIL = getBatchInfo(BATCH_TYPE_LETTER_EMAIL);
    if (batchIdEMAIL == null) {
      context.getLogger().debug("BATCH_TYPE_LETTER_EMAIL not found. (" + BATCH_TYPE_LETTER_EMAIL + ")");
      return false;
    }

    //batchSmsId = insertBatchIdLetter(batchIdLetterInfo);
    //batchIdLetterInfo.getLimitPerDay();
    //batchIdLetterInfo.getLimitPerFile();
    //</editor-fold>
    //
    //<editor-fold defaultstate="collapsed" desc="get Batch Path Info">
    //CLBatch.CLBatchVersionInfo batchIdLetterInfo = null;
    /* ??? รอ update ว่าต้อง select อะไรบ้าง */
    HashMap<String, CLBatch.CLBatchPathInfo> batchPath = new HashMap<>();
    CLBatch.CLBatchPathInfo tempBatchPathInfo = getBatchPathInfo(BATCH_TYPE_LETTER_IHL);
    if (tempBatchPathInfo == null) {
      context.getLogger().debug("PATH BATCH_TYPE_LETTER_IL not found. (" + BATCH_TYPE_LETTER_IHL + ")");
      return false;
    }
    batchPath.put(Constants.LetterType.InHouseLetter.getStringCode(), tempBatchPathInfo);

    tempBatchPathInfo = getBatchPathInfo(BATCH_TYPE_LETTER_NL);
    if (tempBatchPathInfo == null) {
      context.getLogger().debug("PATH BATCH_TYPE_LETTER_NL not found. (" + BATCH_TYPE_LETTER_NL + ")");
      return false;
    }
    batchPath.put(Constants.LetterType.NoticeLetter.getStringCode(), tempBatchPathInfo);

    tempBatchPathInfo = getBatchPathInfo(BATCH_TYPE_LETTER_NLC);
    if (tempBatchPathInfo == null) {
      context.getLogger().debug("PATH BATCH_TYPE_LETTER_NLC not found. (" + BATCH_TYPE_LETTER_NLC + ")");
      return false;
    }
    batchPath.put(Constants.LetterType.NoticeLetter.getStringCodeCorporate(), tempBatchPathInfo);

    tempBatchPathInfo = getBatchPathInfo(BATCH_TYPE_LETTER_RLC);
    if (tempBatchPathInfo == null) {
      context.getLogger().debug("PATH BATCH_TYPE_LETTER_RLC not found. (" + BATCH_TYPE_LETTER_RLC + ")");
      return false;
    }
    batchPath.put(Constants.LetterType.RemindLetter.getStringCodeCorporate(), tempBatchPathInfo);

    tempBatchPathInfo = getBatchPathInfo(BATCH_TYPE_LETTER_TL);
    if (tempBatchPathInfo == null) {
      context.getLogger().debug("PATH BATCH_TYPE_LETTER_TL not found. (" + BATCH_TYPE_LETTER_TL + ")");
      return false;
    }
    batchPath.put(Constants.LetterType.TerminateLetter.getStringCode(), tempBatchPathInfo);

    tempBatchPathInfo = getBatchPathInfo(BATCH_TYPE_LETTER_TLC);
    if (tempBatchPathInfo == null) {
      context.getLogger().debug("PATH BATCH_TYPE_LETTER_TLC not found. (" + BATCH_TYPE_LETTER_TLC + ")");
      return false;
    }
    batchPath.put(Constants.LetterType.TerminateLetter.getStringCodeCorporate(), tempBatchPathInfo);

    tempBatchPathInfo = getBatchPathInfo(BATCH_TYPE_LETTER_WL);
    if (tempBatchPathInfo == null) {
      context.getLogger().debug("PATH BATCH_TYPE_LETTER_WL not found. (" + BATCH_TYPE_LETTER_WL + ")");
      return false;
    }
    batchPath.put(Constants.LetterType.WarningLetter.getStringCode(), tempBatchPathInfo);

    tempBatchPathInfo = getBatchPathInfo(BATCH_TYPE_LETTER_WLC);
    if (tempBatchPathInfo == null) {
      context.getLogger().debug("BATCH_TYPE_LETTER_WLC not found. (" + BATCH_TYPE_LETTER_WLC + ")");
      return false;
    }
    batchPath.put(Constants.LetterType.WarningLetter.getStringCodeCorporate(), tempBatchPathInfo);

    //batchSmsId = insertBatchIdLetter(batchIdLetterInfo);
    //batchIdLetterInfo.getLimitPerDay();
    //batchIdLetterInfo.getLimitPerFile();
    //</editor-fold>
    //
    //<editor-fold defaultstate="collapsed" desc="getTemplateInfo">
    //action_id , criteria_id , template_type(sms ,letter) , language_id
    HashMap<BigDecimal, HashMap<BigDecimal, HashMap<String, HashMap<BigDecimal, CLCriteriaTemplate.CriteriaTemplateInfo>>>> templateInfo = getTemplateInfo();
    if (templateInfo == null) {
      return false;
    }
    //</editor-fold>

    //
    //<editor-fold defaultstate="collapsed" desc="getParameterInfo">
    //template_id , template_language_id
    HashMap<BigDecimal, HashMap<BigDecimal, ArrayList<CLCriteriaTemplate.ParameterTemplate>>> parameterInfo = getParameterInfo();
    if (parameterInfo == null) {
      return false;
    }
    //</editor-fold>
    //
    // ??? SENDER EMAIL ใช้ default 102
    //<editor-fold defaultstate="collapsed" desc="getSenderInfo">
    //COMPANY_ID_PRODUCT_TYPE_ID
    HashMap<String, CLCriteriaTemplate.CLSenderInfo> senderInfo = getSenderInfo();
    if (senderInfo == null) {
      return false;
    }
    //</editor-fold>
    //

    //<editor-fold defaultstate="collapsed" desc="getPathActionInfo">
    //COLLECTION_PATH_ID
    HashMap<BigDecimal, CLAction.ActionDetail> pathActionInfo = getActionInfo();
    if (pathActionInfo == null) {
      return false;
    }
    HashMap<BigDecimal, HashMap<BigDecimal, CLAction.ActionInfo>> pathInfo = convert(pathActionInfo);
    //</editor-fold>
    //
    //<editor-fold defaultstate="collapsed" desc="Get Company Info">
    HashMap<String, PMCompany.PMCompanyInfo> companyInfo = getCompanyActive();
    //</editor-fold>
    //
    //<editor-fold defaultstate="collapsed" desc="Get Language Info">
    HashMap<BigDecimal, CLLanguage.CLLanguagsInfo> languageInfo = getLanguageInfo();
    //</editor-fold>
    //
    //</editor-fold>
    //

    //<editor-fold defaultstate="collapsed" desc="Worker">
    if (!canPrepareTempTreatment()) {
      context.getLogger().debug("canPrepareTempTreatment end.");
      return false;
    }
    if (!canPrepareCriteriaLevel()) {
      context.getLogger().debug("canPrepareCriteriaLevel end.");
      return false;
    }

    HashMap<String, LettterGroup> data = new HashMap<>();
    while (true) {
      getListGroupCriteriaTempTreatment();
      switch (tempTreatmentStatus) {
        case READY: {
          ExecutorService executor1 = Executors.newFixedThreadPool(1);
          ArrayList<WorkerSendLetter> list = new ArrayList<>();
          for (int i = 0; i < listTempTreatment.size(); i++) {
            Context tempContext = context.clone(listTempTreatment.get(i).get(0).getCaNumber());
            list.add(new WorkerSendLetter(tempContext, listTempTreatment.get(i), ASSING_ID, templateInfo, senderInfo, pathInfo, parameterInfo, companyInfo, languageInfo));
          }
          for (int i = 0; i < list.size(); i++) {
            executor1.execute(list.get(i));
          }
          executor1.shutdown();
          while (!executor1.isTerminated()) {
            try {
              Thread.sleep(50L);
            } catch (InterruptedException ex) {
            }
          }
          /*
          if (true) {
            context.getLogger().debug("!!!!!!!!!!!!!! EXIT DEBUG !!!!!!!!!!!!!!!!!!!!");
            return false;
          }
           */
          for (int i = 0; i < list.size(); i++) {
            WorkerSendLetter.ObjectLetter temp = list.get(i).getObjectLetter();
            String key = temp.getFileName();
            LettterGroup letter = data.get(key);
            if (letter == null) {
              letter = new LettterGroup();
              data.put(key, letter);
            }
            letter.letterList.add(temp);
            letter.number += temp.getListClLetter().size();
          }
          insertLetterInformation(data, batchVersion, batchPath, false);
          break;
        }
      }
      if (endLoopTempTreatment()) {
        insertLetterInformation(data, batchVersion, batchPath, true);
        context.getLogger().info("Data Empty...");
        break;
      }
      if (endLoopRetry()) {
        context.getLogger().info("Error Max..");
        break;
      }
    }

    /*            
    // ?? แบ่งกลุ่มตาม ดูจาก ==> LETTER TYPE 
    IHL
    NL
    NLC    ---> ดูจาก corperate , resident 
    RLC    ---> ดูจาก corperate , resident
    TL
    TLC    ---> ดูจาก corperate , resident
    WL 
    WLC    ---> ดูจาก corperate , resident
    LETTER_PRINT_TYPE = รวมซอง ไม่รวมซอง มีผล กับ file name  +++ FIX _ LANGUAGE_HC.txt            
     */
    //</editor-fold>
    //
    return true;
  }

  //return HashMap<PATH_ID, HashMap<ACTION_ID , ActionInfo>> ;
  private HashMap<BigDecimal, HashMap<BigDecimal, CLAction.ActionInfo>> convert(HashMap<BigDecimal, CLAction.ActionDetail> pathActionInfo) {
    HashMap<BigDecimal, HashMap<BigDecimal, CLAction.ActionInfo>> result = new HashMap<>();

    Iterator<BigDecimal> path = pathActionInfo.keySet().iterator();
    while (path.hasNext()) {
      BigDecimal key = path.next();
      CLAction.ActionDetail a1 = pathActionInfo.get(key);
      HashMap<BigDecimal, CLAction.ActionInfo> actionInfo = new HashMap<>();
      result.put(key, actionInfo);
      for (int i = 0; i < a1.getListActionInfo().size(); i++) {
        CLAction.ActionInfo o = a1.getListActionInfo().get(i);
        actionInfo.put(o.getActionID(), o);
      }
    }

    return result;
  }

  private boolean insertLetterInformation(HashMap<String, LettterGroup> data, HashMap<String, CLBatch.CLBatchVersionInfo> batchVersion, HashMap<String, CLBatch.CLBatchPathInfo> batchPath, boolean endData) {
    if (data == null || data.isEmpty()) {
      context.getLogger().debug("insertLetterInformation : data not found. ");
      return true;
    }

    Iterator<String> keys = data.keySet().iterator();

    while (keys.hasNext()) {
      String key = keys.next();
      LettterGroup letter = data.get(key);
      ArrayList<WorkerSendLetter.ObjectLetter> listLetter = letter.letterList;
      if (listLetter == null || listLetter.isEmpty()) {
        data.remove(key);
        continue;
      }

      String letterType = listLetter.get(0).getLetterType();
      String fileName = listLetter.get(0).getFileName();
      CLBatch.CLBatchVersionInfo batchVersionInfo = batchVersion.get(letterType);
      CLBatch.CLBatchPathInfo batchPathInfo = batchPath.get(letterType);
      int maxRecordPerFile;

      if (batchVersionInfo.getLimitPerFile() == null || batchVersionInfo.getLimitPerFile().intValue() < 1) {
        if (!endData) {
          continue;
        }
        maxRecordPerFile = Integer.MAX_VALUE;
      } else {
        maxRecordPerFile = batchVersionInfo.getLimitPerFile().intValue();
      }
      while (true) {
        if (letter.letterList.isEmpty()) {
          break;
        }

        if (letter.number < maxRecordPerFile && !endData) {
          break;
        }

        String assingId = "" + System.currentTimeMillis();
        fileName = fileName.replaceAll(ASSING_ID_REPLACE, assingId);

        BigDecimal batchId = insertBatchIdLetter(batchVersionInfo, fileName);
        CLLetterTreatment tblLetter = new CLLetterTreatment(context.getLogger());

        BufferedWriter writer = null;
        try {
          String absoluteFile = "D:\\cPAC\\letter\\" + fileName + ".txt";
          writer = getWriter(absoluteFile);

          write(writer, "%! File Name: AWN_TL_59100001_51.txt");

          write(writer, "%! Total Record: 6   File Size:      1,633");
          write(writer, "(๚)SETDBSEP");
          write(writer, "(ptl_awn_new_n_b.dbm)STARTDBM ");

          write(writer, listLetter.get(0).getLabelValue().toString());

          tblLetter.buildConnection();
          int record = 0;
          while (true) {

            if (listLetter.isEmpty()) {
              break;
            }

            //WorkerSendLetter.ObjectLetter objectLetter = listLetter.get(0);
            if ((record + listLetter.get(0).getListClLetter().size()) > maxRecordPerFile) {
              break;
            }

            WorkerSendLetter.ObjectLetter letterObject = listLetter.remove(0);

            CLLetterTreatment.ExecuteResponse clmessage = tblLetter.insertCLMessage(letterObject.getClMessage());
            context.getLogger().debug(clmessage.info().toString());
            BigDecimal messageId;
            switch (clmessage.getStatusCode()) {
              case CLLetterTreatment.ExecuteResponse.STATUS_COMPLETE: {
                messageId = clmessage.getIdentity();
                break;
              }
              default: {
                continue;
              }
            }
            for (int k = 0; k < letterObject.getListTreatmentId().size(); k++) {
              CLLetterTreatment.CLMessageTreatment clMessageTreatment = letterObject.getListTreatmentId().get(k);
              clMessageTreatment.setMessageId(messageId);
              CLLetterTreatment.ExecuteResponse rs = tblLetter.insertCLMessageTreatment(clMessageTreatment);
              context.getLogger().debug(rs.info().toString());
            }

            for (int k = 0; k < letterObject.getListClLetter().size(); k++) {
              CLLetterTreatment.CLLetter clLetter = letterObject.getListClLetter().get(k);
              clLetter.setMessageId(messageId);
              clLetter.setLetterMessage(clLetter.getLetterMessage().replaceAll(ASSING_ID_REPLACE, assingId));
              clLetter.setLetterCode(clLetter.getLetterCode().replaceAll(ASSING_ID_REPLACE, assingId));
              clLetter.setTrackingNumber(clLetter.getTrackingNumber().replaceAll(ASSING_ID_REPLACE, assingId));
              clLetter.setLetterPath(batchPathInfo.getPathOutbound());
              clLetter.setBatchId(batchId);
              CLLetterTreatment.ExecuteResponse rs = tblLetter.insertCLLetter(clLetter);
              context.getLogger().debug(rs.info().toString());
              
              write(writer, clLetter.getLetterMessage());
            }

            letter.number -= letterObject.getListClLetter().size();
            record += letterObject.getListClLetter().size();
          }
        } finally {
          write(writer, "%%EOF");
          close(writer);
          tblLetter.getDBConnection().commit();
          tblLetter.getDBConnection().setAutoCommit();
          tblLetter.getDBConnection().close();
        }
      }
    }

    return true;
  }

  private void write(BufferedWriter writer, String content) {
    try {
      writer.write(content);
      writer.write(Constants.END_LINE);
    } catch (IOException ex) {
      context.getLogger().error("", ex);
    }
  }

  private BufferedWriter getWriter(String absoluteFile) {
    Path path = Paths.get(absoluteFile);
    try {
      BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("TIS-620"));
      return writer;
    } catch (IOException ex) {
      context.getLogger().error("", ex);
    }
    return null;
  }

  private void close(BufferedWriter writer) {
    if (writer != null) {
      try {
        writer.flush();
        writer.close();
      } catch (IOException ex) {
        context.getLogger().error("", ex);
      }
    }
  }

  private BigDecimal insertBatchIdLetter(CLBatch.CLBatchVersionInfo batchInfo, String fileName) {
    CLBatch clBatch = new CLBatch(context.getLogger());
    Date current = new Date();
    CLBatch.CLBatcInfo clBatchInfo = clBatch.buildCLBatcInfo();
    clBatchInfo.setBatchTypeId(batchInfo.getBatchTypeId());
    clBatchInfo.setBatchVersionNo(batchInfo.getBatchVersionNo());
    clBatchInfo.setBatchStartDtm(current);
    clBatchInfo.setBatchEndDtm(null);
    clBatchInfo.setBatchFileName(fileName);
    clBatchInfo.setResponseFileName(null);
    clBatchInfo.setOutboundStatus(Constants.OutboundStatus.Generating);
    clBatchInfo.setOutboundStatusDtm(current);
    clBatchInfo.setInboundStatus(Constants.InboundStatus.Pending);
    clBatchInfo.setOutboundStatusDtm(current);
    clBatchInfo.setCreated(current);
    clBatchInfo.setCreatedBy("system");
    clBatchInfo.setLastUpd(current);
    clBatchInfo.setLastUpdBy("system");
    CLBatch.ExecuteResponse clBatchResp = clBatch.insertCLBatch(clBatchInfo);
    context.getLogger().debug(clBatchResp.info().toString());
    switch (clBatchResp.getStatusCode()) {
      case CLBatch.ExecuteResponse.STATUS_COMPLETE: {
        return clBatchResp.getIdentity();
      }
      default: {
        return null;
      }
    }
  }

  private class LettterGroup {

    long number = 0;
    ArrayList<WorkerSendLetter.ObjectLetter> letterList = new ArrayList<>();
  }

  public static void main(String[] args) {
    System.out.println(args.length);
    System.out.println(Arrays.asList(args));
    String[] xx = {"1", "4"};

    new LetterTreatment(xx).execute();
  }
}
