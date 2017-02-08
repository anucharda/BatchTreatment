/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.db;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import th.co.ais.cpac.cl.batch.Constants;
import th.co.ais.cpac.cl.common.UtilityLogger;
import th.co.ais.cpac.cl.template.database.DBConnectionPools;
import th.co.ais.cpac.cl.template.database.DBTemplatesExecuteQuery;
import th.co.ais.cpac.cl.template.database.DBTemplatesResponse;

/**
 *
 * @author Optimus
 */
public class CLCriteriaTemplate {

  protected final UtilityLogger logger;

  public CLCriteriaTemplate(UtilityLogger logger) {
    this.logger = logger;
  }

  //<editor-fold defaultstate="collapsed" desc="Get List Action Criteria TemplateInfo">
  public class CriteriaTemplateInfo {

    private BigDecimal templateCriteriaId;
    private BigDecimal actionId;
    private BigDecimal criteriaId;
    private BigDecimal templateId;
    private BigDecimal contactOptionId;
    private Constants.ContactLevel contactLevel;
    private BigDecimal contactQty;
    private Character appendBoo;
    private String appendMobile;
    private String appendEmail;
    private Constants.TemplateType templateType;
    private Constants.TemplateLevel templateLevel;
    private Constants.LetterType letterType;
    private Constants.LetterPrintType letterPrintType;
    private Character letterRegisBoo;
    private Character letterEmailBoo;
    private BigDecimal templateLanguageId;
    private BigDecimal languageId;
    private Character defaultBoo;
    private String templateAttn;
    private String templateSubject;
    private String templateMsg1;
    private String templateMsg2;
    private String templateMsg3;
    private String templateMsg4;
    private String templateMsg5;
    private String templatePostscript;
    private String templateFrom;
    private String templateDevision;
    private String templateContact;
    private String languageCode;
    private Constants.Language language;
    private Constants.TemplateType contactOptionType;
    private Character contactLevelBoo;
    private Character contactQtyBoo;
    private BigDecimal contactQtyDefault;
    private String letterTmpId;
    private String letterXeroxHeader;

    public BigDecimal getTemplateCriteriaId() {
      return templateCriteriaId;
    }

    public void setTemplateCriteriaId(BigDecimal templateCriteriaId) {
      this.templateCriteriaId = templateCriteriaId;
    }

    public BigDecimal getActionId() {
      return actionId;
    }

    public void setActionId(BigDecimal actionId) {
      this.actionId = actionId;
    }

    public BigDecimal getCriteriaId() {
      return criteriaId;
    }

    public void setCriteriaId(BigDecimal criteriaId) {
      this.criteriaId = criteriaId;
    }

    public BigDecimal getTemplateId() {
      return templateId;
    }

    public void setTemplateId(BigDecimal templateId) {
      this.templateId = templateId;
    }

    public BigDecimal getContactOptionId() {
      return contactOptionId;
    }

    public void setContactOptionId(BigDecimal contactOptionId) {
      this.contactOptionId = contactOptionId;
    }

    public Constants.ContactLevel getContactLevel() {
      return contactLevel;
    }

    public void setContactLevel(Constants.ContactLevel contactLevel) {
      this.contactLevel = contactLevel;
    }

    public BigDecimal getContactQty() {
      return contactQty;
    }

    public void setContactQty(BigDecimal contactQty) {
      this.contactQty = contactQty;
    }

    public Character getAppendBoo() {
      return appendBoo;
    }

    public void setAppendBoo(Character appendBoo) {
      this.appendBoo = appendBoo;
    }

    public String getAppendMobile() {
      return appendMobile;
    }

    public void setAppendMobile(String appendMobile) {
      this.appendMobile = appendMobile;
    }

    public String getAppendEmail() {
      return appendEmail;
    }

    public void setAppendEmail(String appendEmail) {
      this.appendEmail = appendEmail;
    }

    public Constants.TemplateType getTemplateType() {
      return templateType;
    }

    public void setTemplateType(Constants.TemplateType templateType) {
      this.templateType = templateType;
    }

    public Constants.TemplateLevel getTemplateLevel() {
      return templateLevel;
    }

    public void setTemplateLevel(Constants.TemplateLevel templateLevel) {
      this.templateLevel = templateLevel;
    }

    public Constants.LetterType getLetterType() {
      return letterType;
    }

    public void setLetterType(Constants.LetterType letterType) {
      this.letterType = letterType;
    }

    public Constants.LetterPrintType getLetterPrintType() {
      return letterPrintType;
    }

    public void setLetterPrintType(Constants.LetterPrintType letterPrintType) {
      this.letterPrintType = letterPrintType;
    }

    public Character getLetterRegisBoo() {
      return letterRegisBoo;
    }

    public void setLetterRegisBoo(Character letterRegisBoo) {
      this.letterRegisBoo = letterRegisBoo;
    }

    public Character getLetterEmailBoo() {
      return letterEmailBoo;
    }

    public void setLetterEmailBoo(Character letterEmailBoo) {
      this.letterEmailBoo = letterEmailBoo;
    }

    public BigDecimal getTemplateLanguageId() {
      return templateLanguageId;
    }

    public void setTemplateLanguageId(BigDecimal templateLanguageId) {
      this.templateLanguageId = templateLanguageId;
    }

    public BigDecimal getLanguageId() {
      return languageId;
    }

    public void setLanguageId(BigDecimal languageId) {
      this.languageId = languageId;
    }

    public Character getDefaultBoo() {
      return defaultBoo;
    }

    public void setDefaultBoo(Character defaultBoo) {
      this.defaultBoo = defaultBoo;
    }

    public String getTemplateAttn() {
      return templateAttn;
    }

    public void setTemplateAttn(String templateAttn) {
      this.templateAttn = templateAttn;
    }

    public String getTemplateSubject() {
      return templateSubject;
    }

    public void setTemplateSubject(String templateSubject) {
      this.templateSubject = templateSubject;
    }

    public String getTemplateMsg1() {
      return templateMsg1;
    }

    public void setTemplateMsg1(String templateMsg1) {
      this.templateMsg1 = templateMsg1;
    }

    public String getTemplateMsg2() {
      return templateMsg2;
    }

    public void setTemplateMsg2(String templateMsg2) {
      this.templateMsg2 = templateMsg2;
    }

    public String getTemplateMsg3() {
      return templateMsg3;
    }

    public void setTemplateMsg3(String templateMsg3) {
      this.templateMsg3 = templateMsg3;
    }

    public String getTemplateMsg4() {
      return templateMsg4;
    }

    public void setTemplateMsg4(String templateMsg4) {
      this.templateMsg4 = templateMsg4;
    }

    public String getTemplateMsg5() {
      return templateMsg5;
    }

    public void setTemplateMsg5(String templateMsg5) {
      this.templateMsg5 = templateMsg5;
    }

    public String getTemplatePostscript() {
      return templatePostscript;
    }

    public void setTemplatePostscript(String templatePostscript) {
      this.templatePostscript = templatePostscript;
    }

    public String getTemplateFrom() {
      return templateFrom;
    }

    public void setTemplateFrom(String templateFrom) {
      this.templateFrom = templateFrom;
    }

    public String getTemplateDevision() {
      return templateDevision;
    }

    public void setTemplateDevision(String templateDevision) {
      this.templateDevision = templateDevision;
    }

    public String getTemplateContact() {
      return templateContact;
    }

    public void setTemplateContact(String templateContact) {
      this.templateContact = templateContact;
    }

    public String getLanguageCode() {
      return languageCode;
    }

    public void setLanguageCode(String languageCode) {
      this.languageCode = languageCode;
    }

    public th.co.ais.cpac.cl.template.configuration.Constants.Language getLanguage() {
      return language;
    }

    public void setLanguage(th.co.ais.cpac.cl.template.configuration.Constants.Language language) {
      this.language = language;
    }

    public Constants.TemplateType getContactOptionType() {
      return contactOptionType;
    }

    public void setContactOptionType(Constants.TemplateType contactOptionType) {
      this.contactOptionType = contactOptionType;
    }

    public Character getContactLevelBoo() {
      return contactLevelBoo;
    }

    public void setContactLevelBoo(Character contactLevelBoo) {
      this.contactLevelBoo = contactLevelBoo;
    }

    public Character getContactQtyBoo() {
      return contactQtyBoo;
    }

    public void setContactQtyBoo(Character contactQtyBoo) {
      this.contactQtyBoo = contactQtyBoo;
    }

    public BigDecimal getContactQtyDefault() {
      return contactQtyDefault;
    }

    public void setContactQtyDefault(BigDecimal contactQtyDefault) {
      this.contactQtyDefault = contactQtyDefault;
    }

    public String getLetterTmpId() {
      return letterTmpId;
    }

    public void setLetterTmpId(String letterTmpId) {
      this.letterTmpId = letterTmpId;
    }

    public String getLetterXeroxHeader() {
      return letterXeroxHeader;
    }

    public void setLetterXeroxHeader(String letterXeroxHeader) {
      this.letterXeroxHeader = letterXeroxHeader;
    }

    @Override
    public String toString() {
      return "CriteriaTemplateInfo{" + "templateCriteriaId=" + templateCriteriaId + ", actionId=" + actionId + ", criteriaId=" + criteriaId + ", templateId=" + templateId + ", contactOptionId=" + contactOptionId + ", contactLevel=" + contactLevel + ", contactQty=" + contactQty + ", appendBoo=" + appendBoo + ", appendMobile=" + appendMobile + ", appendEmail=" + appendEmail + ", templateType=" + templateType + ", templateLevel=" + templateLevel + ", letterType=" + letterType + ", letterPrintType=" + letterPrintType + ", letterRegisBoo=" + letterRegisBoo + ", letterEmailBoo=" + letterEmailBoo + ", templateLanguageId=" + templateLanguageId + ", languageId=" + languageId + ", defaultBoo=" + defaultBoo + ", templateAttn=" + templateAttn + ", templateSubject=" + templateSubject + ", templateMsg1=" + templateMsg1 + ", templateMsg2=" + templateMsg2 + ", templateMsg3=" + templateMsg3 + ", templateMsg4=" + templateMsg4 + ", templateMsg5=" + templateMsg5 + ", templatePostscript=" + templatePostscript + ", templateFrom=" + templateFrom + ", templateDevision=" + templateDevision + ", templateContact=" + templateContact + ", languageCode=" + languageCode + ", language=" + language + ", contactOptionType=" + contactOptionType + ", contactLevelBoo=" + contactLevelBoo + ", contactQtyBoo=" + contactQtyBoo + ", contactQtyDefault=" + contactQtyDefault + ", letterTmpId=" + letterTmpId + ", letterXeroxHeader=" + letterXeroxHeader + '}';
    }

  }

  public class CriteriaTemplateInfoResponse extends DBTemplatesResponse<  HashMap<BigDecimal, HashMap<BigDecimal, HashMap<String, HashMap<BigDecimal, CriteriaTemplateInfo>>>>> {

    @Override
    protected HashMap<BigDecimal, HashMap<BigDecimal, HashMap<String, HashMap<BigDecimal, CriteriaTemplateInfo>>>> createResponse() {
      return new HashMap<>();
    }
  }

  protected class GetCriteriaTemplateInfo extends DBTemplatesExecuteQuery<CriteriaTemplateInfoResponse, UtilityLogger, DBConnectionPools> {

    private ArrayList<BigDecimal> listActionId;

    public GetCriteriaTemplateInfo(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected CriteriaTemplateInfoResponse createResponse() {
      return new CriteriaTemplateInfoResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      String actionList = "";
      for (int i = 0; i < listActionId.size(); i++) {
        if (i == 0) {
          actionList += listActionId.get(i).toPlainString();
        } else {
          actionList += ",";
        }
        actionList += listActionId.get(i).toPlainString();
      }

      StringBuilder sql = new StringBuilder();
      sql.append(" SELECT").append(Constants.END_LINE);
      sql.append("   d7.TEMPLATE_CRITERIA_ID, d7.ACTION_ID, d7.CRITERIA_ID, d7.TEMPLATE_ID, d7.CONTACT_OPTION_ID, ").append(Constants.END_LINE);
      sql.append("   d7.CONTACT_LEVEL, d7.CONTACT_QTY, d7.APPEND_BOO, d7.APPEND_MOBILE, d7.APPEND_EMAIL,").append(Constants.END_LINE);
      sql.append("   d7.TEMPLATE_TYPE, d7.TEMPLATE_LEVEL, d7.LETTER_TYPE, d7.LETTER_PRINT_TYPE, d7.LETTER_REGIS_BOO, d7.LETTER_EMAIL_BOO,").append(Constants.END_LINE);
      sql.append("   d7.TEMPLATE_LANGUAGE_ID, d7.LANGUAGE_ID, d7.DEFAULT_BOO, d7.TEMPLATE_ATTN, d7.TEMPLATE_SUBJECT, ").append(Constants.END_LINE);
      sql.append("   d7.TEMPLATE_MSG_1, d7.TEMPLATE_MSG_2, d7.TEMPLATE_MSG_3, d7.TEMPLATE_MSG_4, d7.TEMPLATE_MSG_5, ").append(Constants.END_LINE);
      sql.append("   d7.TEMPLATE_POSTSCRIPT, d7.TEMPLATE_FROM, d7.TEMPLATE_DEVISION, d7.TEMPLATE_CONTACT,d7.LETTER_TMP_ID, d7.LETTER_XEROX_HEADER,").append(Constants.END_LINE);
      sql.append("   d7.LANGUAGE_CODE,").append(Constants.END_LINE);
      sql.append("   d8.CONTACT_OPTION_TYPE, d8.CONTACT_LEVEL_BOO, d8.CONTACT_QTY_BOO, d8.CONTACT_QTY_DEFAULT").append(Constants.END_LINE);
      sql.append(" FROM (").append(Constants.END_LINE);
      sql.append("   SELECT ").append(Constants.END_LINE);
      sql.append(" 			d5.TEMPLATE_CRITERIA_ID, d5.ACTION_ID, d5.CRITERIA_ID, d5.TEMPLATE_ID, d5.CONTACT_OPTION_ID,").append(Constants.END_LINE);
      sql.append("       d5.CONTACT_LEVEL, d5.CONTACT_QTY, d5.APPEND_BOO, d5.APPEND_MOBILE, d5.APPEND_EMAIL,").append(Constants.END_LINE);
      sql.append("       d5.TEMPLATE_TYPE, d5.TEMPLATE_LEVEL, d5.LETTER_TYPE, d5.LETTER_PRINT_TYPE, d5.LETTER_REGIS_BOO, d5.LETTER_EMAIL_BOO,").append(Constants.END_LINE);
      sql.append("       d5.TEMPLATE_LANGUAGE_ID, d5.LANGUAGE_ID, d5.DEFAULT_BOO, d5.TEMPLATE_ATTN, d5.TEMPLATE_SUBJECT, ").append(Constants.END_LINE);
      sql.append("       d5.TEMPLATE_MSG_1, d5.TEMPLATE_MSG_2, d5.TEMPLATE_MSG_3, d5.TEMPLATE_MSG_4, d5.TEMPLATE_MSG_5, ").append(Constants.END_LINE);
      sql.append("       d5.TEMPLATE_POSTSCRIPT, d5.TEMPLATE_FROM, d5.TEMPLATE_DEVISION, d5.TEMPLATE_CONTACT,d5.LETTER_TMP_ID, d5.LETTER_XEROX_HEADER,").append(Constants.END_LINE);
      sql.append(" 			 d6.LANGUAGE_CODE").append(Constants.END_LINE);
      sql.append("   FROM (").append(Constants.END_LINE);
      sql.append("     SELECT").append(Constants.END_LINE);
      sql.append("       d3.TEMPLATE_CRITERIA_ID, d3.ACTION_ID, d3.CRITERIA_ID, d3.TEMPLATE_ID, d3.CONTACT_OPTION_ID, ").append(Constants.END_LINE);
      sql.append("       d3.CONTACT_LEVEL, d3.CONTACT_QTY, d3.APPEND_BOO, d3.APPEND_MOBILE, d3.APPEND_EMAIL,").append(Constants.END_LINE);
      sql.append("       d3.TEMPLATE_TYPE, d3.TEMPLATE_LEVEL, d3.LETTER_TYPE, d3.LETTER_PRINT_TYPE, d3.LETTER_REGIS_BOO, d3.LETTER_EMAIL_BOO,").append(Constants.END_LINE);
      sql.append("       d4.TEMPLATE_LANGUAGE_ID, d4.LANGUAGE_ID, d4.DEFAULT_BOO, d4.TEMPLATE_ATTN, d4.TEMPLATE_SUBJECT, ").append(Constants.END_LINE);
      sql.append("       d4.TEMPLATE_MSG_1, d4.TEMPLATE_MSG_2, d4.TEMPLATE_MSG_3, d4.TEMPLATE_MSG_4, d4.TEMPLATE_MSG_5, ").append(Constants.END_LINE);
      sql.append("       d4.TEMPLATE_POSTSCRIPT, d4.TEMPLATE_FROM, d4.TEMPLATE_DEVISION, d4.TEMPLATE_CONTACT ,d4.LETTER_TMP_ID, d4.LETTER_XEROX_HEADER").append(Constants.END_LINE);
      sql.append("     FROM (").append(Constants.END_LINE);
      sql.append("       SELECT").append(Constants.END_LINE);
      sql.append("         d1.TEMPLATE_CRITERIA_ID, d1.ACTION_ID, d1.CRITERIA_ID, d1.TEMPLATE_ID, d1.CONTACT_OPTION_ID, ").append(Constants.END_LINE);
      sql.append("         d1.CONTACT_LEVEL, d1.CONTACT_QTY, d1.APPEND_BOO, d1.APPEND_MOBILE, d1.APPEND_EMAIL,").append(Constants.END_LINE);
      sql.append("         d2.TEMPLATE_TYPE, d2.TEMPLATE_LEVEL, d2.LETTER_TYPE, d2.LETTER_PRINT_TYPE, d2.LETTER_REGIS_BOO, d2.LETTER_EMAIL_BOO").append(Constants.END_LINE);
      sql.append("       FROM (").append(Constants.END_LINE);

      sql.append("         SELECT ").append(Constants.END_LINE);
      sql.append("           x1.ACTION_CRITERIA_ID, x1.ACTION_ID, x1.CRITERIA_ID,").append(Constants.END_LINE);
      sql.append("           x2.TEMPLATE_CRITERIA_ID, x2.TEMPLATE_ID, x2.CONTACT_OPTION_ID, x2.CONTACT_LEVEL, x2.CONTACT_QTY, x2.APPEND_BOO, x2.APPEND_MOBILE, x2.APPEND_EMAIL").append(Constants.END_LINE);
      sql.append("         from (").append(Constants.END_LINE);
      sql.append("           SELECT ACTION_CRITERIA_ID, ACTION_ID, CRITERIA_ID").append(Constants.END_LINE);
      sql.append("           FROM dbo.CL_ACTION_CRITERIA").append(Constants.END_LINE);
      sql.append("           WHERE ACTION_ID in (").append(actionList).append(") and getdate() between EFFECT_START_DATE and isnull(EFFECT_END_DATE , getdate()) and RECORD_END_DTM is null").append(Constants.END_LINE);
      sql.append("         ) x1 left join (").append(Constants.END_LINE);
      sql.append("           SELECT TEMPLATE_CRITERIA_ID, ACTION_CRITERIA_ID, TEMPLATE_ID, CONTACT_OPTION_ID, CONTACT_LEVEL, CONTACT_QTY, APPEND_BOO, APPEND_MOBILE, APPEND_EMAIL").append(Constants.END_LINE);
      sql.append("           FROM dbo.CL_TEMPLATE_CRITERIA").append(Constants.END_LINE);
      sql.append("           WHERE RECORD_END_DTM is null").append(Constants.END_LINE);
      sql.append("         ) x2 on x1.ACTION_CRITERIA_ID = x2.ACTION_CRITERIA_ID ").append(Constants.END_LINE);
      sql.append("         WHERE x2.TEMPLATE_ID IS NOT NULL").append(Constants.END_LINE);
      sql.append("       )d1 left join (").append(Constants.END_LINE);
      sql.append("         SELECT ").append(Constants.END_LINE);
      sql.append("           TEMPLATE_ID, TEMPLATE_TYPE, TEMPLATE_LEVEL, LETTER_TYPE, LETTER_PRINT_TYPE, LETTER_REGIS_BOO, LETTER_EMAIL_BOO").append(Constants.END_LINE);
      sql.append("         FROM dbo.CL_TEMPLATE").append(Constants.END_LINE);
      sql.append("         WHERE getdate() between EFFECT_START_DATE and isnull(EFFECT_END_DATE , getdate())").append(Constants.END_LINE);
      sql.append("       )d2 on d1.TEMPLATE_ID = d2.TEMPLATE_ID").append(Constants.END_LINE);
      sql.append("     )d3 left join (").append(Constants.END_LINE);
      sql.append("       SELECT ").append(Constants.END_LINE);
      sql.append("         TEMPLATE_LANGUAGE_ID, TEMPLATE_ID, LANGUAGE_ID, DEFAULT_BOO, TEMPLATE_ATTN, TEMPLATE_SUBJECT, ").append(Constants.END_LINE);
      sql.append("         TEMPLATE_MSG_1, TEMPLATE_MSG_2, TEMPLATE_MSG_3, TEMPLATE_MSG_4, TEMPLATE_MSG_5, ").append(Constants.END_LINE);
      sql.append("         TEMPLATE_POSTSCRIPT, TEMPLATE_FROM, TEMPLATE_DEVISION, TEMPLATE_CONTACT,LETTER_TMP_ID, LETTER_XEROX_HEADER ").append(Constants.END_LINE);
      sql.append("       FROM dbo.CL_TEMPLATE_LANGUAGE").append(Constants.END_LINE);
      sql.append("       WHERE getdate() between  RECORD_START_DTM and isnull(RECORD_END_DTM,getdate())").append(Constants.END_LINE);
      sql.append("     )d4 on d3.TEMPLATE_ID = d4.TEMPLATE_ID").append(Constants.END_LINE);
      sql.append("   )d5 left join (").append(Constants.END_LINE);
      sql.append("     SELECT ").append(Constants.END_LINE);
      sql.append("       LANGUAGE_ID, LANGUAGE_CODE").append(Constants.END_LINE);
      sql.append("     FROM dbo.CL_LANGUAGE").append(Constants.END_LINE);
      sql.append("     WHERE RECORD_STATUS = 1").append(Constants.END_LINE);
      sql.append("   )d6 on d5.LANGUAGE_ID = d6.LANGUAGE_ID").append(Constants.END_LINE);
      sql.append(" ) d7 left join (").append(Constants.END_LINE);
      sql.append("   SELECT ").append(Constants.END_LINE);
      sql.append("     CONTACT_OPTION_ID, CONTACT_OPTION_TYPE, CONTACT_LEVEL_BOO, CONTACT_QTY_BOO, CONTACT_QTY_DEFAULT").append(Constants.END_LINE);
      sql.append("   FROM dbo.CL_CONTACT_OPTION").append(Constants.END_LINE);
      sql.append("   WHERE RECORD_STATUS = 1").append(Constants.END_LINE);
      sql.append(" ) d8 on d7.CONTACT_OPTION_ID = d8.CONTACT_OPTION_ID").append(Constants.END_LINE);
      sql.append(" order by ACTION_ID, CRITERIA_ID, TEMPLATE_TYPE ,TEMPLATE_ID").append(Constants.END_LINE);

      return sql;
    }

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      CriteriaTemplateInfo temp = new CriteriaTemplateInfo();
      temp.setTemplateCriteriaId(resultSet.getBigDecimal("TEMPLATE_CRITERIA_ID"));
      temp.setActionId(resultSet.getBigDecimal("ACTION_ID"));
      temp.setCriteriaId(resultSet.getBigDecimal("CRITERIA_ID"));
      temp.setTemplateId(resultSet.getBigDecimal("TEMPLATE_ID"));
      temp.setContactOptionId(resultSet.getBigDecimal("CONTACT_OPTION_ID"));
      temp.setContactLevel(Constants.mapContactLevel(resultSet.getBigDecimal("CONTACT_LEVEL")));
      temp.setContactQty(resultSet.getBigDecimal("CONTACT_QTY"));
      temp.setAppendBoo(toCharcter(resultSet.getString("APPEND_BOO"), 'N'));
      temp.setAppendMobile(resultSet.getString("APPEND_MOBILE"));
      temp.setAppendEmail(resultSet.getString("APPEND_EMAIL"));
      temp.setTemplateType(Constants.mapTemplateType(resultSet.getBigDecimal("TEMPLATE_TYPE")));
      temp.setTemplateLevel(Constants.mapTemplateLevel(resultSet.getBigDecimal("TEMPLATE_LEVEL")));
      temp.setLetterType(Constants.mapLetterType(resultSet.getBigDecimal("LETTER_TYPE")));
      temp.setLetterPrintType(Constants.mapLetterPrintType(resultSet.getBigDecimal("LETTER_PRINT_TYPE")));
      temp.setLetterRegisBoo(toCharcter(resultSet.getString("LETTER_REGIS_BOO"), 'N'));
      temp.setLetterEmailBoo(toCharcter(resultSet.getString("LETTER_EMAIL_BOO"), 'N'));
      temp.setTemplateLanguageId(resultSet.getBigDecimal("TEMPLATE_LANGUAGE_ID"));
      temp.setLanguageId(resultSet.getBigDecimal("LANGUAGE_ID"));
      temp.setDefaultBoo(toCharcter(resultSet.getString("DEFAULT_BOO"), 'N'));
      temp.setTemplateAttn(resultSet.getString("TEMPLATE_ATTN"));
      temp.setTemplateSubject(resultSet.getString("TEMPLATE_SUBJECT"));
      temp.setTemplateMsg1(resultSet.getString("TEMPLATE_MSG_1"));
      temp.setTemplateMsg2(resultSet.getString("TEMPLATE_MSG_2"));
      temp.setTemplateMsg3(resultSet.getString("TEMPLATE_MSG_3"));
      temp.setTemplateMsg4(resultSet.getString("TEMPLATE_MSG_4"));
      temp.setTemplateMsg5(resultSet.getString("TEMPLATE_MSG_5"));
      temp.setTemplatePostscript(resultSet.getString("TEMPLATE_POSTSCRIPT"));
      temp.setTemplateFrom(resultSet.getString("TEMPLATE_FROM"));
      temp.setTemplateDevision(resultSet.getString("TEMPLATE_DEVISION"));
      temp.setTemplateContact(resultSet.getString("TEMPLATE_CONTACT"));
      temp.setLetterTmpId(resultSet.getString("LETTER_TMP_ID"));
      temp.setLetterXeroxHeader(resultSet.getString("LETTER_XEROX_HEADER"));
      temp.setLanguageCode(resultSet.getString("LANGUAGE_CODE"));
      temp.setLanguage(Constants.mapLanguage(temp.getLanguageCode(), th.co.ais.cpac.cl.template.configuration.Constants.Language.THAI));
      temp.setContactOptionType(Constants.mapTemplateType(resultSet.getBigDecimal("CONTACT_OPTION_TYPE")));
      temp.setContactLevelBoo(toCharcter(resultSet.getString("CONTACT_LEVEL_BOO"), 'N'));
      temp.setContactQtyBoo(toCharcter(resultSet.getString("CONTACT_QTY_BOO"), 'N'));
      temp.setContactQtyDefault(resultSet.getBigDecimal("CONTACT_QTY_DEFAULT"));

      HashMap<BigDecimal, HashMap<BigDecimal, HashMap<String, HashMap<BigDecimal, CriteriaTemplateInfo>>>> actionId = response.getResponse();
      HashMap<BigDecimal, HashMap<String, HashMap<BigDecimal, CriteriaTemplateInfo>>> criteriaId;
      HashMap<String, HashMap<BigDecimal, CriteriaTemplateInfo>> templateType;
      HashMap<BigDecimal, CriteriaTemplateInfo> language;

      criteriaId = actionId.get(temp.getActionId());

      if (criteriaId == null) {
        criteriaId = new HashMap<>();
        templateType = new HashMap<>();
        language = new HashMap<>();
        language.put(temp.getLanguageId(), temp);
        templateType.put(temp.getTemplateType().getKeys(), language);
        criteriaId.put(temp.getCriteriaId(), templateType);
        response.getResponse().put(temp.getActionId(), criteriaId);
      } else {
        templateType = criteriaId.get(temp.getCriteriaId());
        if (templateType == null) {
          templateType = new HashMap<>();
          language = new HashMap<>();
          language.put(temp.getLanguageId(), temp);
          templateType.put(temp.getTemplateType().getKeys(), language);
          criteriaId.put(temp.getCriteriaId(), templateType);
        } else {
          language = templateType.get(temp.getTemplateType().getKeys());
          if (language == null) {
            language = new HashMap<>();
            language.put(temp.getLanguageId(), temp);
            templateType.put(temp.getTemplateType().getKeys(), language);
          } else {
            language.put(temp.getLanguageId(), temp);
          }
        }
      }
    }

    protected CriteriaTemplateInfoResponse execute(ArrayList<BigDecimal> listActionId) {
      this.listActionId = listActionId;
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public CriteriaTemplateInfoResponse getCriteriaTemplateInfo(ArrayList<BigDecimal> listActionId) {
    return new GetCriteriaTemplateInfo(logger).execute(listActionId);
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="Get ListParameter ">
  public class ParameterTemplate {

    protected ParameterTemplate() {
    }
    private BigDecimal templateId;
    private BigDecimal templateLanguageId;
    private BigDecimal languageId;
    private BigDecimal criteriaAttributeId;
    private BigDecimal attributeIndex;
    private BigDecimal attributeId;
    private String attributeAlias;
    private String attributeLabel;

    public BigDecimal getTemplateId() {
      return templateId;
    }

    public void setTemplateId(BigDecimal templateId) {
      this.templateId = templateId;
    }

    public BigDecimal getTemplateLanguageId() {
      return templateLanguageId;
    }

    public void setTemplateLanguageId(BigDecimal templateLanguageId) {
      this.templateLanguageId = templateLanguageId;
    }

    public BigDecimal getLanguageId() {
      return languageId;
    }

    public void setLanguageId(BigDecimal languageId) {
      this.languageId = languageId;
    }

    public BigDecimal getCriteriaAttributeId() {
      return criteriaAttributeId;
    }

    public void setCriteriaAttributeId(BigDecimal criteriaAttributeId) {
      this.criteriaAttributeId = criteriaAttributeId;
    }

    public BigDecimal getAttributeIndex() {
      return attributeIndex;
    }

    public void setAttributeIndex(BigDecimal attributeIndex) {
      this.attributeIndex = attributeIndex;
    }

    public BigDecimal getAttributeId() {
      return attributeId;
    }

    public void setAttributeId(BigDecimal attributeId) {
      this.attributeId = attributeId;
    }

    public String getAttributeAlias() {
      return attributeAlias;
    }

    public void setAttributeAlias(String attributeAlias) {
      this.attributeAlias = attributeAlias;
    }

    public String getAttributeLabel() {
      return attributeLabel;
    }

    public void setAttributeLabel(String attributeLabel) {
      this.attributeLabel = attributeLabel;
    }

  }

  public class ParameterSMSResponse extends DBTemplatesResponse<HashMap<BigDecimal, HashMap<BigDecimal, ArrayList<ParameterTemplate>>>> {

    @Override
    protected HashMap<BigDecimal, HashMap<BigDecimal, ArrayList<ParameterTemplate>>> createResponse() {
      return new HashMap<>();
    }
  }

  protected class GetParameterSMS extends DBTemplatesExecuteQuery<ParameterSMSResponse, UtilityLogger, DBConnectionPools> {

    public GetParameterSMS(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected ParameterSMSResponse createResponse() {
      return new ParameterSMSResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      String actionList = "";
      for (int i = 0; i < listActionId.size(); i++) {
        if (i == 0) {
          actionList += listActionId.get(i).toPlainString();
        } else {
          actionList += ",";
        }
        actionList += listActionId.get(i).toPlainString();
      }
      StringBuilder sql = new StringBuilder();

      sql.append(" SELECT ").append(Constants.END_LINE);
      sql.append("   t1.TEMPLATE_ID,t1.TEMPLATE_LANGUAGE_ID, t1.LANGUAGE_ID,").append(Constants.END_LINE);
      sql.append("   t1.CRITERIA_ATTRIBUTE_ID, t1.ATTRIBUTE_INDEX,").append(Constants.END_LINE);
      sql.append("   t2.ATTRIBUTE_ID, t2.ATTRIBUTE_ALIAS, t2.ATTRIBUTE_LABEL").append(Constants.END_LINE);
      sql.append(" FROM (").append(Constants.END_LINE);
      sql.append("   SELECT ").append(Constants.END_LINE);
      sql.append("     t1.TEMPLATE_ID,t1.TEMPLATE_LANGUAGE_ID, t1.LANGUAGE_ID,").append(Constants.END_LINE);
      sql.append("     t2.CRITERIA_ATTRIBUTE_ID, t2.ATTRIBUTE_INDEX").append(Constants.END_LINE);
      sql.append("   FROM (").append(Constants.END_LINE);
      sql.append("     SELECT ").append(Constants.END_LINE);
      sql.append("       t1.TEMPLATE_ID,t2.TEMPLATE_LANGUAGE_ID, t2.LANGUAGE_ID").append(Constants.END_LINE);
      sql.append("     FROM (").append(Constants.END_LINE);

      sql.append("        SELECT  distinct( TEMPLATE_ID)").append(Constants.END_LINE);
      sql.append("        FROM (").append(Constants.END_LINE);
      sql.append("          SELECT ACTION_CRITERIA_ID ").append(Constants.END_LINE);
      sql.append("          FROM dbo.CL_ACTION_CRITERIA").append(Constants.END_LINE);
      sql.append("          WHERE ACTION_ID in (").append(actionList).append(") and getdate() between EFFECT_START_DATE and isnull(EFFECT_END_DATE , getdate()) and RECORD_END_DTM is null").append(Constants.END_LINE);
      sql.append("        ) x1 left join (").append(Constants.END_LINE);
      sql.append("          SELECT  ACTION_CRITERIA_ID , TEMPLATE_ID").append(Constants.END_LINE);
      sql.append("          FROM dbo.CL_TEMPLATE_CRITERIA").append(Constants.END_LINE);
      sql.append("          WHERE  RECORD_END_DTM is null").append(Constants.END_LINE);
      sql.append("        ) x2 on x1.ACTION_CRITERIA_ID = x2.ACTION_CRITERIA_ID").append(Constants.END_LINE);
      sql.append("        WHERE TEMPLATE_ID is not null").append(Constants.END_LINE);

//      sql.append("       SELECT distinct(TEMPLATE_ID)").append(Constants.END_LINE);
//      sql.append("       FROM dbo.CL_TEMPLATE_CRITERIA").append(Constants.END_LINE);
//      sql.append("       WHERE ACTION_ID in (").append(actionList).append(") and getdate() between RECORD_START_DTM and isnull(RECORD_END_DTM,getdate())").append(Constants.END_LINE);
      sql.append("     )t1 left join (").append(Constants.END_LINE);
      sql.append("       SELECT TEMPLATE_LANGUAGE_ID, TEMPLATE_ID, LANGUAGE_ID").append(Constants.END_LINE);
      sql.append("       FROM dbo.CL_TEMPLATE_LANGUAGE").append(Constants.END_LINE);
      sql.append("       WHERE getdate() between RECORD_START_DTM and isnull(RECORD_END_DTM,getdate())").append(Constants.END_LINE);
      sql.append("     )t2 on t1.TEMPLATE_ID = t2.TEMPLATE_ID").append(Constants.END_LINE);
      sql.append("   )t1 left join (").append(Constants.END_LINE);
      sql.append("     SELECT TEMPLATE_LANGUAGE_ID, CRITERIA_ATTRIBUTE_ID, ATTRIBUTE_INDEX ").append(Constants.END_LINE);
      sql.append("     FROM dbo.CL_TEMPLATE_ATTRIBUTE").append(Constants.END_LINE);
      sql.append("     WHERE RECORD_STATUS = 1").append(Constants.END_LINE);
      sql.append("   )t2 on t1.TEMPLATE_LANGUAGE_ID = t2.TEMPLATE_LANGUAGE_ID").append(Constants.END_LINE);
      sql.append(" )t1 left join (").append(Constants.END_LINE);
      sql.append("   SELECT CRITERIA_ATTRIBUTE_ID, ATTRIBUTE_ID, ATTRIBUTE_ALIAS, ATTRIBUTE_LABEL").append(Constants.END_LINE);
      sql.append("   FROM dbo.CL_CRITERIA_ATTRIBUTE").append(Constants.END_LINE);
      sql.append(" )t2 on t1.CRITERIA_ATTRIBUTE_ID = t2.CRITERIA_ATTRIBUTE_ID").append(Constants.END_LINE);
      sql.append(" order by t1.TEMPLATE_ID , t1.TEMPLATE_LANGUAGE_ID,  t1.ATTRIBUTE_INDEX").append(Constants.END_LINE);

      return sql;
    }

    private ArrayList<BigDecimal> listActionId = null;

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {

      ParameterTemplate temp = new ParameterTemplate();
      temp.setTemplateId(resultSet.getBigDecimal("TEMPLATE_ID"));
      temp.setTemplateLanguageId(resultSet.getBigDecimal("TEMPLATE_LANGUAGE_ID"));
      temp.setLanguageId(resultSet.getBigDecimal("LANGUAGE_ID"));
      temp.setCriteriaAttributeId(resultSet.getBigDecimal("CRITERIA_ATTRIBUTE_ID"));
      temp.setAttributeIndex(resultSet.getBigDecimal("ATTRIBUTE_INDEX"));
      temp.setAttributeId(resultSet.getBigDecimal("ATTRIBUTE_ID"));
      temp.setAttributeAlias(resultSet.getString("ATTRIBUTE_ALIAS"));
      temp.setAttributeLabel(resultSet.getString("ATTRIBUTE_LABEL"));

      HashMap<BigDecimal, HashMap<BigDecimal, ArrayList<ParameterTemplate>>> templateId = response.getResponse();
      HashMap<BigDecimal, ArrayList<ParameterTemplate>> language = templateId.get(temp.getTemplateId());
      ArrayList<ParameterTemplate> listParam;

      if (language == null) {
        language = new HashMap<>();
        listParam = new ArrayList<>();
        listParam.add(temp);
        language.put(temp.getTemplateLanguageId(), listParam);
        templateId.put(temp.getTemplateId(), language);
      } else {
        listParam = language.get(temp.getTemplateLanguageId());
        if (listParam == null) {
          listParam = new ArrayList<>();
          listParam.add(temp);
          language.put(temp.getTemplateLanguageId(), listParam);
        } else {
          listParam.add(temp);
        }
      }
    }

    protected ParameterSMSResponse execute(ArrayList<BigDecimal> listActionId) {
      this.listActionId = listActionId;
      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public ParameterSMSResponse getListParameter(ArrayList<BigDecimal> listActionId) {
    return new GetParameterSMS(logger).execute(listActionId);
  }

  //</editor-fold>
  //
  //<editor-fold defaultstate="collapsed" desc="Get Sender Info">
  public class CLSenderInfo {

    private BigDecimal senderId;
    private Constants.SenderType senderType;
    private String companyCode;
    private BigDecimal productTypeId;
    private String smsSender;
    private String emailSender;

    public BigDecimal getSenderId() {
      return senderId;
    }

    public void setSenderId(BigDecimal senderId) {
      this.senderId = senderId;
    }

    public Constants.SenderType getSenderType() {
      return senderType;
    }

    public void setSenderType(Constants.SenderType senderType) {
      this.senderType = senderType;
    }

    public String getCompanyCode() {
      return companyCode;
    }

    public void setCompanyCode(String companyCode) {
      this.companyCode = companyCode;
    }

    public BigDecimal getProductTypeId() {
      return productTypeId;
    }

    public void setProductTypeId(BigDecimal productTypeId) {
      this.productTypeId = productTypeId;
    }

    public String getSmsSender() {
      return smsSender;
    }

    public void setSmsSender(String smsSender) {
      this.smsSender = smsSender;
    }

    public String getEmailSender() {
      return emailSender;
    }

    public void setEmailSender(String emailSender) {
      this.emailSender = emailSender;
    }

  }

  public class CLSenderResponse extends DBTemplatesResponse<HashMap<String, CLSenderInfo>> {

    @Override
    protected HashMap<String, CLSenderInfo> createResponse() {
      return new HashMap<>();
    }
  }

  protected class GetCLSenderInfo extends DBTemplatesExecuteQuery<CLSenderResponse, UtilityLogger, DBConnectionPools> {

    final static int CASE_SENDER_TYPE = 10;
    final static int CASE_SENDER_ALL = 30;

    public GetCLSenderInfo(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected CLSenderResponse createResponse() {
      return new CLSenderResponse();
    }
    private String companyCode;
    private ArrayList<String> listBaNumber;
    private String statusMobile;
    private String defaultId;
    private int case_select;

    //
    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      switch (case_select) {
        case CASE_SENDER_ALL: {
          sql.append(" SELECT   SENDER_TYPE, COMPANY_CODE, PRODUCT_TYPE_ID, SMS_SENDER, EMAIL_SENDER ").append(Constants.END_LINE);
          sql.append(" FROM CL_SENDER ").append(Constants.END_LINE);
          sql.append(" WHERE getdate() between EFFECT_START_DATE and isnull(EFFECT_END_DATE,getdate()) ").append(Constants.END_LINE);
          break;
        }
        case CASE_SENDER_TYPE: {
          String balist = arrayListStringToStringQuery(listBaNumber);
          sql.append(" SELECT TOP 1 T1.* ").append(Constants.END_LINE);
          sql.append(" FROM( ").append(Constants.END_LINE);
          sql.append("   SELECT SENDER_TYPE ,COMPANY_CODE , PRODUCT_TYPE_ID ,  SMS_SENDER , EMAIL_SENDER , SENDER_PRIORITY ").append(Constants.END_LINE);
          sql.append("   FROM CL_SENDER ").append(Constants.END_LINE);

          sql.append("   WHERE (COMPANY_CODE = '").append(companyCode).append("' AND  GETDATE() BETWEEN EFFECT_START_DATE AND ISNULL(EFFECT_END_DATE ,GETDATE() )) ").append(Constants.END_LINE);
          if (defaultId != null) {
            sql.append(" OR (SENDER_TYPE = ").append(defaultId).append(") ").append(Constants.END_LINE);
          }

          sql.append(" ) T1 LEFT JOIN ( ").append(Constants.END_LINE);
          sql.append("   SELECT PRODUCT_TYPE_ID ").append(Constants.END_LINE);
          sql.append("   FROM CL_MOBILE_INFO ").append(Constants.END_LINE);
          sql.append("   WHERE BA_NO IN (").append(balist).append(") AND MOBILE_STATUS IN (").append(statusMobile).append(") ").append(Constants.END_LINE);
          sql.append(" )T2 ON T1.PRODUCT_TYPE_ID = T2.PRODUCT_TYPE_ID ").append(Constants.END_LINE);
          sql.append(" ORDER BY T1.SENDER_PRIORITY ").append(Constants.END_LINE);
        }
      }
      return sql;
    }

    @Override
    protected void setReturnValue(ResultSet resultSet) throws SQLException {
      switch (case_select) {
        case CASE_SENDER_ALL: {
          CLSenderInfo temp = new CLSenderInfo();
          //temp.setSenderId(resultSet.getBigDecimal("SENDER_ID"));
          temp.setSenderType(Constants.mapSenderType(resultSet.getBigDecimal("SENDER_TYPE").intValue()));
          temp.setCompanyCode(resultSet.getString("COMPANY_CODE"));
          temp.setProductTypeId(resultSet.getBigDecimal("PRODUCT_TYPE_ID"));
          temp.setSmsSender(resultSet.getString("SMS_SENDER"));
          temp.setEmailSender(resultSet.getString("EMAIL_SENDER"));
          response.getResponse().put(temp.getCompanyCode() + "_" + temp.getProductTypeId(), temp);
          break;
        }
        case CASE_SENDER_TYPE: {
          CLSenderInfo temp = new CLSenderInfo();
          //temp.setSenderId(resultSet.getBigDecimal("SENDER_ID"));
          temp.setSenderType(Constants.mapSenderType(resultSet.getBigDecimal("SENDER_TYPE").intValue()));
          temp.setCompanyCode(resultSet.getString("COMPANY_CODE"));
          temp.setProductTypeId(resultSet.getBigDecimal("PRODUCT_TYPE_ID"));
          temp.setSmsSender(resultSet.getString("SMS_SENDER"));
          temp.setEmailSender(resultSet.getString("EMAIL_SENDER"));
          response.getResponse().put("SENDER", temp);
        }
      }
    }

    protected CLSenderResponse execute(String companyCode, ArrayList<String> listBaNumber, String statusMobile, String defaultId, int case_select) {
      this.companyCode = companyCode;
      this.listBaNumber = listBaNumber;
      this.statusMobile = statusMobile;
      this.defaultId = defaultId;
      this.case_select = case_select;

      return executeQuery(Constants.getDBConnectionPools(logger), true);
    }

  }

  public CLSenderResponse getCLSenderInfo() {
    return new GetCLSenderInfo(logger).execute(null, null, null, null, GetCLSenderInfo.CASE_SENDER_ALL);
  }

  public CLSenderResponse getSenderSMSInfo(String companyCode, ArrayList<String> listBaNumber, String listMobileStatus) {
    return new GetCLSenderInfo(logger).execute(companyCode, listBaNumber, listMobileStatus, "101", GetCLSenderInfo.CASE_SENDER_TYPE);
  }
  //</editor-fold>
  //
}
