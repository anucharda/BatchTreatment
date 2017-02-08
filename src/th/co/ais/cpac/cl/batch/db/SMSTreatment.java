/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.db;

import th.co.ais.cpac.cl.common.UtilityLogger;

/**
 *
 * @author Optimus
 */
public class SMSTreatment {

  protected final UtilityLogger logger;

  public SMSTreatment(UtilityLogger logger) {
    this.logger = logger;
  }
/*
  protected Connection getConnection() {
    CNFDatabase cnf = new CNFDatabase();
    DBConnectionPools<CNFDatabase, UtilityLogger> dd = new DBConnectionPools<>(cnf, logger);
    return dd.getConnection();
  }

  public boolean buildConnection() {
    CNFDatabase cnf = new CNFDatabase();
    connection = new DBConnectionPools<>(cnf, logger);
    connection.getConnection();
    return connection.ready();
  }
  DBConnectionPools<CNFDatabase, UtilityLogger> connection;

  public DBConnectionPools getDBConnection() {
    return connection;
  }

  public class ExecuteResponse extends DBTemplatesResponse<Boolean> {

    @Override
    protected Boolean createResponse() {
      return false;
    }

    @Override
    public void setResponse(Boolean boo) {
      response = boo;
    }
  }
*/
  //



  //
}
/*

  //<editor-fold defaultstate="collapsed" desc="Get Sender Info">
  public class CLSenderInfo {

    private BigDecimal senderId;
    private Constants.SenderType senderType;
    private BigDecimal companyId;
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

    public BigDecimal getCompanyId() {
      return companyId;
    }

    public void setCompanyId(BigDecimal companyId) {
      this.companyId = companyId;
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

  protected class GetCLSenderInfo extends DBTemplatesExecuteQuery<CLSenderResponse, UtilityLogger> {

    public GetCLSenderInfo(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected CLSenderResponse createResponse() {
      return new CLSenderResponse();
    }

    //
    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append(" SELECT SENDER_ID, SENDER_TYPE, COMPANY_ID, PRODUCT_TYPE_ID, SMS_SENDER, EMAIL_SENDER ").append(Constants.END_LINE);
      sql.append(" FROM CL_SENDER ").append(Constants.END_LINE);
      sql.append(" WHERE getdate() between EFFECT_START_DATE and isnull(EFFECT_END_DATE,getdate()) ").append(Constants.END_LINE);
      return sql;
    }

    @Override
    protected void setReturnValue(ResultSet rs) throws SQLException {
      CLSenderInfo temp = new CLSenderInfo();

      response.getResponse().put(temp.getCompanyId() + "_" + temp.getProductTypeId(), temp);
    }

    protected CLSenderResponse execute() {
      return executeQuery(getConnection(), true);
    }

  }

  public CLSenderResponse getCLSenderInfo() {
    return new GetCLSenderInfo(logger).execute();
  }

  //</editor-fold>
  //
* /
  //<editor-fold defaultstate="collapsed" desc="GetListSMSTreatment">
  public class SMSTreatment {

    private BigDecimal rowId;
    private BigDecimal treatmentId;
    private String caNumber;
    private String baNumber;
    private BigDecimal collectionPathId;
    private BigDecimal actionId;
    private BigDecimal actionPathId;
    private Date actionPathDtm;
    private Date actionScheduleDtm;
    private BigDecimal criteriaId;
    private BigDecimal actionCriteriaId;
    private Constants.ActionStatus actionStatus;
    private Date actionStatusDtm;
    private Constants.ActionMode actionMode;
    private Constants.ActionType actionType;
    private Constants.CriteriaLevel criteriaLevel;
    private String saNumber;
    private String baTitle;
    private String baName;
    private BigDecimal baStatusId;
    private Date baStatusDtm;
    private BigDecimal categoryId;
    private BigDecimal companyId;
    private Date registerDate;
    private BigDecimal registerLocation;
    private BigDecimal billingSystemId;
    private String billingCycle;
    private BigDecimal paymentTerm;
    private BigDecimal paymentBehavior;
    private BigDecimal paymentMethodId;
    private String bankName;
    private String bankAccntNumber;
    private String creditCardType;
    private String creditCardBank;
    private String creditCardName;
    private String creditCardRef;
    private BigDecimal productGroupId;
    private BigDecimal priorityStatusId;
    private Date priorityStatusDate;
    private String billAddressName;
    private String billAddressLine1;
    private String billAddressLine2;
    private String billAddressLine3;
    private String billAddressLine4;
    private String billAddressLine5;
    private String billZipcode;
    private BigDecimal billProvinceId;
    private BigDecimal billRegionId;
    private BigDecimal billLanguageId;
    private String refBaNumber;
    private BigDecimal sffAccountId;
    private BigDecimal sbAccountId;
    private String baStatus;
    private Constants.BaStatusGroup baStatusGroup;
    private String category;
    private String subcategory;
    private BigDecimal baTotalBal;
    private BigDecimal dueTotalBal;
    private BigDecimal minInvoiceId;
    private Date minInvoiceDate;
    private Date minDueDate;
    private Date maxInvoiceDate;
    private BigDecimal runningId;
    private BigDecimal runningStts;

    public BigDecimal getRowId() {
      return rowId;
    }

    public void setRowId(BigDecimal rowId) {
      this.rowId = rowId;
    }

    public BigDecimal getTreatmentId() {
      return treatmentId;
    }

    public void setTreatmentId(BigDecimal treatmentId) {
      this.treatmentId = treatmentId;
    }

    public String getCaNumber() {
      return caNumber;
    }

    public void setCaNumber(String caNumber) {
      this.caNumber = caNumber;
    }

    public String getBaNumber() {
      return baNumber;
    }

    public void setBaNumber(String baNumber) {
      this.baNumber = baNumber;
    }

    public BigDecimal getCollectionPathId() {
      return collectionPathId;
    }

    public void setCollectionPathId(BigDecimal collectionPathId) {
      this.collectionPathId = collectionPathId;
    }

    public BigDecimal getActionId() {
      return actionId;
    }

    public void setActionId(BigDecimal actionId) {
      this.actionId = actionId;
    }

    public BigDecimal getActionPathId() {
      return actionPathId;
    }

    public void setActionPathId(BigDecimal actionPathId) {
      this.actionPathId = actionPathId;
    }

    public Date getActionPathDtm() {
      return actionPathDtm;
    }

    public void setActionPathDtm(Date actionPathDtm) {
      this.actionPathDtm = actionPathDtm;
    }

    public Date getActionScheduleDtm() {
      return actionScheduleDtm;
    }

    public void setActionScheduleDtm(Date actionScheduleDtm) {
      this.actionScheduleDtm = actionScheduleDtm;
    }

    public BigDecimal getCriteriaId() {
      return criteriaId;
    }

    public void setCriteriaId(BigDecimal criteriaId) {
      this.criteriaId = criteriaId;
    }

    public BigDecimal getActionCriteriaId() {
      return actionCriteriaId;
    }

    public void setActionCriteriaId(BigDecimal actionCriteriaId) {
      this.actionCriteriaId = actionCriteriaId;
    }

    public Constants.ActionStatus getActionStatus() {
      return actionStatus;
    }

    public void setActionStatus(Constants.ActionStatus actionStatus) {
      this.actionStatus = actionStatus;
    }

    public Date getActionStatusDtm() {
      return actionStatusDtm;
    }

    public void setActionStatusDtm(Date actionStatusDtm) {
      this.actionStatusDtm = actionStatusDtm;
    }

    public Constants.ActionMode getActionMode() {
      return actionMode;
    }

    public void setActionMode(Constants.ActionMode actionMode) {
      this.actionMode = actionMode;
    }

    public Constants.ActionType getActionType() {
      return actionType;
    }

    public void setActionType(Constants.ActionType actionType) {
      this.actionType = actionType;
    }

    public Constants.CriteriaLevel getCriteriaLevel() {
      return criteriaLevel;
    }

    public void setCriteriaLevel(Constants.CriteriaLevel criteriaLevel) {
      this.criteriaLevel = criteriaLevel;
    }

    public String getSaNumber() {
      return saNumber;
    }

    public void setSaNumber(String saNumber) {
      this.saNumber = saNumber;
    }

    public String getBaTitle() {
      return baTitle;
    }

    public void setBaTitle(String baTitle) {
      this.baTitle = baTitle;
    }

    public String getBaName() {
      return baName;
    }

    public void setBaName(String baName) {
      this.baName = baName;
    }

    public BigDecimal getBaStatusId() {
      return baStatusId;
    }

    public void setBaStatusId(BigDecimal baStatusId) {
      this.baStatusId = baStatusId;
    }

    public Date getBaStatusDtm() {
      return baStatusDtm;
    }

    public void setBaStatusDtm(Date baStatusDtm) {
      this.baStatusDtm = baStatusDtm;
    }

    public BigDecimal getCategoryId() {
      return categoryId;
    }

    public void setCategoryId(BigDecimal categoryId) {
      this.categoryId = categoryId;
    }

    public BigDecimal getCompanyId() {
      return companyId;
    }

    public void setCompanyId(BigDecimal companyId) {
      this.companyId = companyId;
    }

    public Date getRegisterDate() {
      return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
      this.registerDate = registerDate;
    }

    public BigDecimal getRegisterLocation() {
      return registerLocation;
    }

    public void setRegisterLocation(BigDecimal registerLocation) {
      this.registerLocation = registerLocation;
    }

    public BigDecimal getBillingSystemId() {
      return billingSystemId;
    }

    public void setBillingSystemId(BigDecimal billingSystemId) {
      this.billingSystemId = billingSystemId;
    }

    public String getBillingCycle() {
      return billingCycle;
    }

    public void setBillingCycle(String billingCycle) {
      this.billingCycle = billingCycle;
    }

    public BigDecimal getPaymentTerm() {
      return paymentTerm;
    }

    public void setPaymentTerm(BigDecimal paymentTerm) {
      this.paymentTerm = paymentTerm;
    }

    public BigDecimal getPaymentBehavior() {
      return paymentBehavior;
    }

    public void setPaymentBehavior(BigDecimal paymentBehavior) {
      this.paymentBehavior = paymentBehavior;
    }

    public BigDecimal getPaymentMethodId() {
      return paymentMethodId;
    }

    public void setPaymentMethodId(BigDecimal paymentMethodId) {
      this.paymentMethodId = paymentMethodId;
    }

    public String getBankName() {
      return bankName;
    }

    public void setBankName(String bankName) {
      this.bankName = bankName;
    }

    public String getBankAccntNumber() {
      return bankAccntNumber;
    }

    public void setBankAccntNumber(String bankAccntNumber) {
      this.bankAccntNumber = bankAccntNumber;
    }

    public String getCreditCardType() {
      return creditCardType;
    }

    public void setCreditCardType(String creditCardType) {
      this.creditCardType = creditCardType;
    }

    public String getCreditCardBank() {
      return creditCardBank;
    }

    public void setCreditCardBank(String creditCardBank) {
      this.creditCardBank = creditCardBank;
    }

    public String getCreditCardName() {
      return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
      this.creditCardName = creditCardName;
    }

    public String getCreditCardRef() {
      return creditCardRef;
    }

    public void setCreditCardRef(String creditCardRef) {
      this.creditCardRef = creditCardRef;
    }

    public BigDecimal getProductGroupId() {
      return productGroupId;
    }

    public void setProductGroupId(BigDecimal productGroupId) {
      this.productGroupId = productGroupId;
    }

    public BigDecimal getPriorityStatusId() {
      return priorityStatusId;
    }

    public void setPriorityStatusId(BigDecimal priorityStatusId) {
      this.priorityStatusId = priorityStatusId;
    }

    public Date getPriorityStatusDate() {
      return priorityStatusDate;
    }

    public void setPriorityStatusDate(Date priorityStatusDate) {
      this.priorityStatusDate = priorityStatusDate;
    }

    public String getBillAddressName() {
      return billAddressName;
    }

    public void setBillAddressName(String billAddressName) {
      this.billAddressName = billAddressName;
    }

    public String getBillAddressLine1() {
      return billAddressLine1;
    }

    public void setBillAddressLine1(String billAddressLine1) {
      this.billAddressLine1 = billAddressLine1;
    }

    public String getBillAddressLine2() {
      return billAddressLine2;
    }

    public void setBillAddressLine2(String billAddressLine2) {
      this.billAddressLine2 = billAddressLine2;
    }

    public String getBillAddressLine3() {
      return billAddressLine3;
    }

    public void setBillAddressLine3(String billAddressLine3) {
      this.billAddressLine3 = billAddressLine3;
    }

    public String getBillAddressLine4() {
      return billAddressLine4;
    }

    public void setBillAddressLine4(String billAddressLine4) {
      this.billAddressLine4 = billAddressLine4;
    }

    public String getBillAddressLine5() {
      return billAddressLine5;
    }

    public void setBillAddressLine5(String billAddressLine5) {
      this.billAddressLine5 = billAddressLine5;
    }

    public String getBillZipcode() {
      return billZipcode;
    }

    public void setBillZipcode(String billZipcode) {
      this.billZipcode = billZipcode;
    }

    public BigDecimal getBillProvinceId() {
      return billProvinceId;
    }

    public void setBillProvinceId(BigDecimal billProvinceId) {
      this.billProvinceId = billProvinceId;
    }

    public BigDecimal getBillRegionId() {
      return billRegionId;
    }

    public void setBillRegionId(BigDecimal billRegionId) {
      this.billRegionId = billRegionId;
    }

    public BigDecimal getBillLanguageId() {
      return billLanguageId;
    }

    public void setBillLanguageId(BigDecimal billLanguageId) {
      this.billLanguageId = billLanguageId;
    }

    public String getRefBaNumber() {
      return refBaNumber;
    }

    public void setRefBaNumber(String refBaNumber) {
      this.refBaNumber = refBaNumber;
    }

    public BigDecimal getSffAccountId() {
      return sffAccountId;
    }

    public void setSffAccountId(BigDecimal sffAccountId) {
      this.sffAccountId = sffAccountId;
    }

    public BigDecimal getSbAccountId() {
      return sbAccountId;
    }

    public void setSbAccountId(BigDecimal sbAccountId) {
      this.sbAccountId = sbAccountId;
    }

    public String getBaStatus() {
      return baStatus;
    }

    public void setBaStatus(String baStatus) {
      this.baStatus = baStatus;
    }

    public Constants.BaStatusGroup getBaStatusGroup() {
      return baStatusGroup;
    }

    public void setBaStatusGroup(Constants.BaStatusGroup baStatusGroup) {
      this.baStatusGroup = baStatusGroup;
    }

    public String getCategory() {
      return category;
    }

    public void setCategory(String category) {
      this.category = category;
    }

    public String getSubcategory() {
      return subcategory;
    }

    public void setSubcategory(String subcategory) {
      this.subcategory = subcategory;
    }

    public BigDecimal getBaTotalBal() {
      return baTotalBal;
    }

    public void setBaTotalBal(BigDecimal baTotalBal) {
      this.baTotalBal = baTotalBal;
    }

    public BigDecimal getDueTotalBal() {
      return dueTotalBal;
    }

    public void setDueTotalBal(BigDecimal dueTotalBal) {
      this.dueTotalBal = dueTotalBal;
    }

    public Date getMinInvoiceDate() {
      return minInvoiceDate;
    }

    public void setMinInvoiceDate(Date minInvoiceDate) {
      this.minInvoiceDate = minInvoiceDate;
    }

    public Date getMinDueDate() {
      return minDueDate;
    }

    public void setMinDueDate(Date minDueDate) {
      this.minDueDate = minDueDate;
    }

    public Date getMaxInvoiceDate() {
      return maxInvoiceDate;
    }

    public void setMaxInvoiceDate(Date maxInvoiceDate) {
      this.maxInvoiceDate = maxInvoiceDate;
    }

    public BigDecimal getRunningId() {
      return runningId;
    }

    public void setRunningId(BigDecimal runningId) {
      this.runningId = runningId;
    }

    public BigDecimal getRunningStts() {
      return runningStts;
    }

    public void setRunningStts(BigDecimal runningStts) {
      this.runningStts = runningStts;
    }

    public BigDecimal getMinInvoiceId() {
      return minInvoiceId;
    }

    public void setMinInvoiceId(BigDecimal minInvoiceId) {
      this.minInvoiceId = minInvoiceId;
    }

  }

  public class SMSTreatmentResponse extends DBTemplatesResponse<ArrayList< ArrayList<SMSTreatment>>> {

    @Override
    protected ArrayList<ArrayList<SMSTreatment>> createResponse() {
      return new ArrayList<>();
    }

  }

  protected class GetListSMSTreatment extends DBTemplatesExecuteQuery<SMSTreatmentResponse, UtilityLogger> {

    public GetListSMSTreatment(UtilityLogger logger) {
      super(logger);
    }

    @Override
    protected SMSTreatmentResponse createResponse() {
      return new SMSTreatmentResponse();
    }

    @Override
    protected StringBuilder createSqlProcess() {
      StringBuilder sql = new StringBuilder();
      sql.append(" UPDATE TOP 2000 #TEMP_TREATEMENT_TS ").append(Constants.END_LINE);
      sql.append(" SET RUNNING_ID = @runningId , RUNNING_STTS = 200 ").append(Constants.END_LINE);
      sql.append(" WHERE RUNNING_ID = 0 AND ((CRITERIA_LEVEL = 1 AND RUNNING_STTS= 0 ) OR (CRITERIA_LEVEL = 0 AND RUNNING_STTS = 100) )  ").append(Constants.END_LINE);
      sql.append(Constants.END_LINE);
      sql.append(" SELECT CA_NO,ACTION_ID,CRITERIA_ID INTO #TEMP_UPDATE ").append(Constants.END_LINE);
      sql.append(" FROM #TEMP_TREATEMENT_TS ").append(Constants.END_LINE);
      sql.append(" WHERE RUNNING_ID = @runningId AND CRITERIA_LEVEL = 0  ").append(Constants.END_LINE);
      sql.append(Constants.END_LINE);
      sql.append(" UPDATE #TEMP_TREATEMENT_TS  ").append(Constants.END_LINE);
      sql.append(" SET RUNNING_ID = @runningId , RUNNING_STTS = 200 ").append(Constants.END_LINE);
      sql.append(" from #TEMP_TREATEMENT_TS t1 , #TEMP_UPDATE  t2 ").append(Constants.END_LINE);
      sql.append(" where RUNNING_ID = 0 and t1.RUNNING_STTS = 0 and t2.CA_NO = t1.CA_NO and t2.ACTION_ID = t1.ACTION_ID and t2.CRITERIA_ID = t1.CRITERIA_ID").append(Constants.END_LINE);
      sql.append(Constants.END_LINE);
      sql.append(" SELECT * ").append(Constants.END_LINE);
      sql.append(" FROM #TEMP_TREATEMENT_TS  ").append(Constants.END_LINE);
      sql.append(" WHERE RUNNING_ID = @runningId  ").append(Constants.END_LINE);
      sql.append(" ORDER BY CA_NO,ACTION_ID,CRITERIA_ID , CRITERIA_LEVEL ").append(Constants.END_LINE);
      sql.append(Constants.END_LINE);
      sql.append(" drop table #TEMP_UPDATE");
      return sql;
    }

    private String lastCaNo = "0";
    private String lastAction = "0";

    @Override
    protected void setReturnValue(ResultSet rs) throws SQLException {
      SMSTreatment temp = new SMSTreatment();
      temp.setRowId(rs.getBigDecimal("ROWID"));
      temp.setTreatmentId(rs.getBigDecimal("TREATMENT_ID"));
      temp.setCaNumber(rs.getString("CA_NO"));
      temp.setBaNumber(rs.getString("BA_NO"));
      temp.setCollectionPathId(rs.getBigDecimal("COLLECTION_PATH_ID"));
      temp.setActionId(rs.getBigDecimal("ACTION_ID"));
      temp.setActionPathId(rs.getBigDecimal("ACTION_PATH_ID"));
      temp.setActionPathDtm(toDate(rs.getTimestamp("ACTION_PATH_DTM"), null));
      temp.setActionScheduleDtm(toDate(rs.getTimestamp("ACTION_SCHEDULE_DTM"), null));
      temp.setCriteriaId(rs.getBigDecimal("CRITERIA_ID"));
      temp.setActionCriteriaId(rs.getBigDecimal("ACTION_CRITERIA_ID"));
      temp.setActionStatus(Constants.mapActionStatus(rs.getBigDecimal("ACTION_STATUS").intValue()));
      temp.setActionStatusDtm(toDate(rs.getTimestamp("ACTION_STATUS_DTM"), null));
      temp.setActionMode(Constants.mapActionMode(rs.getBigDecimal("ACTION_MODE").intValue()));
      temp.setActionType(Constants.mapActionType(rs.getBigDecimal("ACTION_TYPE").intValue()));
      temp.setCriteriaLevel(Constants.mapCriteriaLevel(rs.getBigDecimal("CRITERIA_LEVEL").intValue()));
      temp.setBaTitle(rs.getString("BA_TITLE"));
      temp.setBaName(rs.getString("BA_NAME"));
      temp.setBaStatusId(rs.getBigDecimal("BA_STATUS_ID"));
      temp.setBaStatusDtm(toDate(rs.getTimestamp("BA_STATUS_DTM"), null));
      temp.setCategoryId(rs.getBigDecimal("CATEGORY_ID"));
      temp.setCompanyId(rs.getBigDecimal("COMPANY_ID"));
      temp.setRegisterDate(toDate(rs.getTimestamp("REGISTER_DATE"), null));
      temp.setRegisterLocation(rs.getBigDecimal("REGISTER_LOCATION"));
      temp.setBillingSystemId(rs.getBigDecimal("BILLING_SYSTEM"));
      temp.setBillingCycle(rs.getString("BILLING_CYCLE"));
      temp.setPaymentTerm(rs.getBigDecimal("PAYMENT_TERM"));
      temp.setBillAddressName(rs.getString("BILL_ADDRESS_NAME"));
      temp.setBillZipcode(rs.getString("BILL_ZIPCODE"));
      temp.setBillProvinceId(rs.getBigDecimal("BILL_PROVINCE_ID"));
      temp.setBillRegionId(rs.getBigDecimal("BILL_REGION_ID"));
      temp.setBaStatus(rs.getString("BA_STATUS"));
      temp.setBaStatusGroup(Constants.mapBaStatusGroup(rs.getBigDecimal("BA_STATUS_GROUP").intValue()));
      temp.setCategory(rs.getString("CATEGORY"));
      temp.setSubcategory(rs.getString("SUBCATEGORY"));
      temp.setBaTotalBal(rs.getBigDecimal("BA_TOTAL_BAL"));
      temp.setDueTotalBal(rs.getBigDecimal("DUE_TOTAL_BAL"));
      temp.setMinInvoiceDate(toDate(rs.getTimestamp("MIN_INVOICE_DATE"), null));
      temp.setMinDueDate(toDate(rs.getTimestamp("MIN_DUE_DATE"), null));
      temp.setMaxInvoiceDate(toDate(rs.getTimestamp("MAX_INVOICE_DATE"), null));
      temp.setRunningId(rs.getBigDecimal("RUNNING_ID"));
      temp.setRunningStts(rs.getBigDecimal("RUNNING_STTS"));

      ArrayList<SMSTreatment> ll;

      // check CA , PATH , ACTION , CRITERIA , 
      if (temp.getCriteriaLevel().isCALevel()) {
        if (lastCaNo.equals(temp.getCaNumber())) {
          int index = response.getResponse().size() - 1;
          ll = response.getResponse().get(index);
          ll.add(temp);
        } else {
          lastCaNo = temp.getCaNumber();
          ll = new ArrayList<>();
          ll.add(temp);
          response.getResponse().add(ll);
        }
      } else {
        lastCaNo = "0";
        ll = new ArrayList<>();
        ll.add(temp);
        response.getResponse().add(ll);
      }

    }

    protected SMSTreatmentResponse execute() {
      return executeQuery(connection.getConnection(), false);
    }

  }

  public SMSTreatmentResponse getListSMSTreatment() {
    return new GetListSMSTreatment(logger).execute();
  }

  //</editor-fold>
  //


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

    private Constants.DataOwner templateOwner;
    private Constants.TemplateType templateType;
    private Constants.TemplateLevel templateLevel;
    private String templateCode;
    private String templateName;
    private String templateDesc;
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

    private BigDecimal contactOptionType;
    private String contactOptionName;
    private Character contactLevelBoo;
    private Character contactQtyBoo;
    private BigDecimal contactQtyDefault;
    private String description;

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

    public Constants.DataOwner getTemplateOwner() {
      return templateOwner;
    }

    public void setTemplateOwner(Constants.DataOwner templateOwner) {
      this.templateOwner = templateOwner;
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

    public String getTemplateCode() {
      return templateCode;
    }

    public void setTemplateCode(String templateCode) {
      this.templateCode = templateCode;
    }

    public String getTemplateName() {
      return templateName;
    }

    public void setTemplateName(String templateName) {
      this.templateName = templateName;
    }

    public String getTemplateDesc() {
      return templateDesc;
    }

    public void setTemplateDesc(String templateDesc) {
      this.templateDesc = templateDesc;
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

    public BigDecimal getContactOptionType() {
      return contactOptionType;
    }

    public void setContactOptionType(BigDecimal contactOptionType) {
      this.contactOptionType = contactOptionType;
    }

    public String getContactOptionName() {
      return contactOptionName;
    }

    public void setContactOptionName(String contactOptionName) {
      this.contactOptionName = contactOptionName;
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

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

  }

  public class CriteriaTemplateInfoResponse extends DBTemplatesResponse<  HashMap<BigDecimal, HashMap<BigDecimal, HashMap<String, HashMap<BigDecimal, CriteriaTemplateInfo>>>>> {

    @Override
    protected HashMap<BigDecimal, HashMap<BigDecimal, HashMap<String, HashMap<BigDecimal, CriteriaTemplateInfo>>>> createResponse() {
      return new HashMap<>();
    }
  }

  protected class GetCriteriaTemplateInfo extends DBTemplatesExecuteQuery<CriteriaTemplateInfoResponse, UtilityLogger> {

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
      String mmm = "";
      for (int i = 0; i < listActionId.size(); i++) {
        if (i == 0) {
          mmm += ",";
        }
        mmm += listActionId.get(i).toPlainString();
      }

      StringBuilder sql = new StringBuilder();

      sql.append(" SELECT ").append(Constants.END_LINE);
      sql.append("   d7.TEMPLATE_CRITERIA_ID, d7.ACTION_ID, d7.CRITERIA_ID, d7.TEMPLATE_ID, d7.CONTACT_OPTION_ID, ").append(Constants.END_LINE);
      sql.append("   d7.CONTACT_LEVEL, d7.CONTACT_QTY, d7.APPEND_BOO, d7.APPEND_MOBILE, d7.APPEND_EMAIL,").append(Constants.END_LINE);
      sql.append("   d7.TEMPLATE_OWNER, d7.TEMPLATE_TYPE, d7.TEMPLATE_LEVEL, d7.TEMPLATE_CODE, d7.TEMPLATE_NAME, d7.TEMPLATE_DESC, ").append(Constants.END_LINE);
      sql.append("   d7.LETTER_TYPE, d7.LETTER_PRINT_TYPE, d7.LETTER_REGIS_BOO, d7.LETTER_EMAIL_BOO,").append(Constants.END_LINE);
      sql.append("   d7.TEMPLATE_LANGUAGE_ID, d7.LANGUAGE_ID, d7.DEFAULT_BOO, ").append(Constants.END_LINE);
      sql.append("   d7.TEMPLATE_ATTN, d7.TEMPLATE_SUBJECT, ").append(Constants.END_LINE);
      sql.append("   d7.TEMPLATE_MSG_1, d7.TEMPLATE_MSG_2, d7.TEMPLATE_MSG_3, d7.TEMPLATE_MSG_4, d7.TEMPLATE_MSG_5, ").append(Constants.END_LINE);
      sql.append("   d7.TEMPLATE_POSTSCRIPT, d7.TEMPLATE_FROM, d7.TEMPLATE_DEVISION, d7.TEMPLATE_CONTACT ,").append(Constants.END_LINE);
      sql.append("   d7.LANGUAGE_CODE  ,").append(Constants.END_LINE);
      sql.append("   d8.CONTACT_OPTION_TYPE, d8.CONTACT_OPTION_NAME, d8.CONTACT_LEVEL_BOO, d8.CONTACT_QTY_BOO, d8.CONTACT_QTY_DEFAULT, d8.DESCRIPTION").append(Constants.END_LINE);
      sql.append(" FROM (").append(Constants.END_LINE);
      sql.append("   SELECT ").append(Constants.END_LINE);
      sql.append("     d5.TEMPLATE_CRITERIA_ID, d5.ACTION_ID, d5.CRITERIA_ID, d5.TEMPLATE_ID, d5.CONTACT_OPTION_ID, ").append(Constants.END_LINE);
      sql.append("     d5.CONTACT_LEVEL, d5.CONTACT_QTY, d5.APPEND_BOO, d5.APPEND_MOBILE, d5.APPEND_EMAIL,").append(Constants.END_LINE);
      sql.append("     d5.TEMPLATE_OWNER, d5.TEMPLATE_TYPE, d5.TEMPLATE_LEVEL, d5.TEMPLATE_CODE, d5.TEMPLATE_NAME, d5.TEMPLATE_DESC, ").append(Constants.END_LINE);
      sql.append("     d5.LETTER_TYPE, d5.LETTER_PRINT_TYPE, d5.LETTER_REGIS_BOO, d5.LETTER_EMAIL_BOO,").append(Constants.END_LINE);
      sql.append("     d5.TEMPLATE_LANGUAGE_ID, d5.LANGUAGE_ID, d5.DEFAULT_BOO, ").append(Constants.END_LINE);
      sql.append("     d5.TEMPLATE_ATTN, d5.TEMPLATE_SUBJECT, ").append(Constants.END_LINE);
      sql.append("     d5.TEMPLATE_MSG_1, d5.TEMPLATE_MSG_2, d5.TEMPLATE_MSG_3, d5.TEMPLATE_MSG_4, d5.TEMPLATE_MSG_5, ").append(Constants.END_LINE);
      sql.append("     d5.TEMPLATE_POSTSCRIPT, d5.TEMPLATE_FROM, d5.TEMPLATE_DEVISION, d5.TEMPLATE_CONTACT ,").append(Constants.END_LINE);
      sql.append("     d6.LANGUAGE_CODE  ").append(Constants.END_LINE);
      sql.append("   FROM (").append(Constants.END_LINE);
      sql.append("     SELECT").append(Constants.END_LINE);
      sql.append("       d3.TEMPLATE_CRITERIA_ID, d3.ACTION_ID, d3.CRITERIA_ID, d3.TEMPLATE_ID, d3.CONTACT_OPTION_ID, ").append(Constants.END_LINE);
      sql.append("       d3.CONTACT_LEVEL, d3.CONTACT_QTY, d3.APPEND_BOO, d3.APPEND_MOBILE, d3.APPEND_EMAIL,").append(Constants.END_LINE);
      sql.append("       d3.TEMPLATE_OWNER, d3.TEMPLATE_TYPE, d3.TEMPLATE_LEVEL, d3.TEMPLATE_CODE, d3.TEMPLATE_NAME, d3.TEMPLATE_DESC, ").append(Constants.END_LINE);
      sql.append("       d3.LETTER_TYPE, d3.LETTER_PRINT_TYPE, d3.LETTER_REGIS_BOO, d3.LETTER_EMAIL_BOO,").append(Constants.END_LINE);
      sql.append("       d4.TEMPLATE_LANGUAGE_ID, d4.LANGUAGE_ID, d4.DEFAULT_BOO, ").append(Constants.END_LINE);
      sql.append("       d4.TEMPLATE_ATTN, d4.TEMPLATE_SUBJECT, ").append(Constants.END_LINE);
      sql.append("       d4.TEMPLATE_MSG_1, d4.TEMPLATE_MSG_2, d4.TEMPLATE_MSG_3, d4.TEMPLATE_MSG_4, d4.TEMPLATE_MSG_5, ").append(Constants.END_LINE);
      sql.append("       d4.TEMPLATE_POSTSCRIPT, d4.TEMPLATE_FROM, d4.TEMPLATE_DEVISION, d4.TEMPLATE_CONTACT").append(Constants.END_LINE);
      sql.append("     FROM (").append(Constants.END_LINE);
      sql.append("       SELECT ").append(Constants.END_LINE);
      sql.append("         d1.TEMPLATE_CRITERIA_ID, d1.ACTION_ID, d1.CRITERIA_ID, d1.TEMPLATE_ID, d1.CONTACT_OPTION_ID, d1.CONTACT_LEVEL, d1.CONTACT_QTY, d1.APPEND_BOO, d1.APPEND_MOBILE, d1.APPEND_EMAIL,").append(Constants.END_LINE);
      sql.append("         d2.TEMPLATE_OWNER, d2.TEMPLATE_TYPE, d2.TEMPLATE_LEVEL, d2.TEMPLATE_CODE, d2.TEMPLATE_NAME, d2.TEMPLATE_DESC, ").append(Constants.END_LINE);
      sql.append("         d2.LETTER_TYPE, d2.LETTER_PRINT_TYPE, d2.LETTER_REGIS_BOO, d2.LETTER_EMAIL_BOO  ").append(Constants.END_LINE);
      sql.append("       FROM (").append(Constants.END_LINE);
      sql.append("         SELECT ").append(Constants.END_LINE);
      sql.append("           TEMPLATE_CRITERIA_ID, ACTION_ID, CRITERIA_ID, TEMPLATE_ID, CONTACT_OPTION_ID, CONTACT_LEVEL, CONTACT_QTY, APPEND_BOO, APPEND_MOBILE, APPEND_EMAIL").append(Constants.END_LINE);
      sql.append("         FROM dbo.CL_TEMPLATE_CRITERIA").append(Constants.END_LINE);
      sql.append("         WHERE  ACTION_ID in (").append(mmm).append(") and getdate() between RECORD_START_DTM and isnull(RECORD_END_DTM , getdate())").append(Constants.END_LINE);
      sql.append("       )d1 left join (").append(Constants.END_LINE);
      sql.append("         SELECT ").append(Constants.END_LINE);
      sql.append("           TEMPLATE_ID, TEMPLATE_OWNER, TEMPLATE_TYPE, TEMPLATE_LEVEL, TEMPLATE_CODE, TEMPLATE_NAME, TEMPLATE_DESC, ").append(Constants.END_LINE);
      sql.append("           LETTER_TYPE, LETTER_PRINT_TYPE, LETTER_REGIS_BOO, LETTER_EMAIL_BOO").append(Constants.END_LINE);
      sql.append("         FROM dbo.CL_TEMPLATE").append(Constants.END_LINE);
      sql.append("         WHERE getdate() between EFFECT_START_DATE and isnull(EFFECT_END_DATE , getdate())").append(Constants.END_LINE);
      sql.append("       )d2 on d1.TEMPLATE_ID = d2.TEMPLATE_ID").append(Constants.END_LINE);
      sql.append("     )d3 left join (").append(Constants.END_LINE);
      sql.append("       SELECT ").append(Constants.END_LINE);
      sql.append("         TEMPLATE_LANGUAGE_ID, TEMPLATE_ID, LANGUAGE_ID, DEFAULT_BOO, ").append(Constants.END_LINE);
      sql.append("         TEMPLATE_ATTN, TEMPLATE_SUBJECT, ").append(Constants.END_LINE);
      sql.append("         TEMPLATE_MSG_1, TEMPLATE_MSG_2, TEMPLATE_MSG_3, TEMPLATE_MSG_4, TEMPLATE_MSG_5, ").append(Constants.END_LINE);
      sql.append("         TEMPLATE_POSTSCRIPT, TEMPLATE_FROM, TEMPLATE_DEVISION, TEMPLATE_CONTACT").append(Constants.END_LINE);
      sql.append("       FROM dbo.CL_TEMPLATE_LANGUAGE").append(Constants.END_LINE);
      sql.append("       WHERE getdate() between  RECORD_START_DTM and isnull(RECORD_END_DTM,getdate())").append(Constants.END_LINE);
      sql.append("     )d4 on d3.TEMPLATE_ID = d4.TEMPLATE_ID").append(Constants.END_LINE);
      sql.append("   )d5 left join (").append(Constants.END_LINE);
      sql.append("     SELECT ").append(Constants.END_LINE);
      sql.append("       LANGUAGE_ID, LANGUAGE_CODE, LANGUAGE_NAME").append(Constants.END_LINE);
      sql.append("     FROM dbo.CL_LANGUAGE").append(Constants.END_LINE);
      sql.append("     WHERE RECORD_STATUS = 1").append(Constants.END_LINE);
      sql.append("   )d6 on d5.LANGUAGE_ID = d6.LANGUAGE_ID").append(Constants.END_LINE);
      sql.append(" ) d7 left join (").append(Constants.END_LINE);
      sql.append("   SELECT ").append(Constants.END_LINE);
      sql.append("     CONTACT_OPTION_ID, CONTACT_OPTION_TYPE, CONTACT_OPTION_NAME, CONTACT_LEVEL_BOO, CONTACT_QTY_BOO, CONTACT_QTY_DEFAULT, DESCRIPTION").append(Constants.END_LINE);
      sql.append("   FROM dbo.CL_CONTACT_OPTION").append(Constants.END_LINE);
      sql.append("   WHERE RECORD_STATUS = 1").append(Constants.END_LINE);
      sql.append(" ) d8 on d7.CONTACT_OPTION_ID = d8.CONTACT_OPTION_ID").append(Constants.END_LINE);
      sql.append(" order by ACTION_ID, CRITERIA_ID, TEMPLATE_ID      ").append(Constants.END_LINE);

      return sql;
    }

    @Override
    protected void setReturnValue(ResultSet rs) throws SQLException {
      CriteriaTemplateInfo temp = new CriteriaTemplateInfo();
      temp.setTemplateCriteriaId(rs.getBigDecimal("TEMPLATE_CRITERIA_ID"));
      temp.setActionId(rs.getBigDecimal("ACTION_ID"));
      temp.setCriteriaId(rs.getBigDecimal("CRITERIA_ID"));
      temp.setTemplateId(rs.getBigDecimal("TEMPLATE_ID"));
      temp.setContactOptionId(rs.getBigDecimal("CONTACT_OPTION_ID"));
      temp.setContactLevel(Constants.mapContactLevel(rs.getBigDecimal("CONTACT_LEVEL").intValue()));
      temp.setContactQty(rs.getBigDecimal("CONTACT_QTY"));
      temp.setAppendBoo(toCharcter(rs.getString("APPEND_BOO"), 'N'));
      temp.setAppendMobile(rs.getString("APPEND_MOBILE"));
      temp.setAppendEmail(rs.getString("APPEND_EMAIL"));
      temp.setTemplateOwner(Constants.mapDataOwner(rs.getBigDecimal("TEMPLATE_OWNER").intValue()));
      temp.setTemplateType(Constants.mapTemplateType(rs.getBigDecimal("TEMPLATE_TYPE").intValue()));
      temp.setTemplateLevel(Constants.mapTemplateLevel(rs.getBigDecimal("TEMPLATE_LEVEL").intValue()));
      temp.setTemplateCode(rs.getString("TEMPLATE_CODE"));
      temp.setTemplateName(rs.getString("TEMPLATE_NAME"));
      temp.setTemplateDesc(rs.getString("TEMPLATE_DESC"));
      temp.setLetterType(Constants.mapLetterType(rs.getBigDecimal("LETTER_TYPE").intValue()));
      temp.setLetterPrintType(Constants.mapLetterPrintType(rs.getBigDecimal("LETTER_PRINT_TYPE").intValue()));
      temp.setLetterRegisBoo(toCharcter(rs.getString("LETTER_REGIS_BOO"), 'N'));
      temp.setLetterEmailBoo(toCharcter(rs.getString("LETTER_EMAIL_BOO"), 'N'));
      temp.setTemplateLanguageId(rs.getBigDecimal("TEMPLATE_LANGUAGE_ID"));
      temp.setLanguageId(rs.getBigDecimal("LANGUAGE_ID"));
      temp.setDefaultBoo(toCharcter(rs.getString("DEFAULT_BOO"), 'N'));
      temp.setTemplateAttn(rs.getString("TEMPLATE_ATTN"));
      temp.setTemplateSubject(rs.getString("TEMPLATE_SUBJECT"));
      temp.setTemplateMsg1(rs.getString("TEMPLATE_MSG_1"));
      temp.setTemplateMsg2(rs.getString("TEMPLATE_MSG_2"));
      temp.setTemplateMsg3(rs.getString("TEMPLATE_MSG_3"));
      temp.setTemplateMsg4(rs.getString("TEMPLATE_MSG_4"));
      temp.setTemplateMsg5(rs.getString("TEMPLATE_MSG_5"));
      temp.setTemplatePostscript(rs.getString("TEMPLATE_POSTSCRIPT"));
      temp.setTemplateFrom(rs.getString("TEMPLATE_FROM"));
      temp.setTemplateDevision(rs.getString("TEMPLATE_DEVISION"));
      temp.setTemplateContact(rs.getString("TEMPLATE_CONTACT"));
      temp.setLanguageCode(rs.getString("LANGUAGE_CODE"));
      temp.setContactOptionType(rs.getBigDecimal("CONTACT_OPTION_TYPE"));
      temp.setContactOptionName(rs.getString("CONTACT_OPTION_NAME"));
      temp.setContactLevelBoo(toCharcter(rs.getString("CONTACT_LEVEL_BOO"), 'N'));
      temp.setContactQtyBoo(toCharcter(rs.getString("CONTACT_QTY_BOO"), 'N'));
      temp.setContactQtyDefault(rs.getBigDecimal("CONTACT_QTY_DEFAULT"));
      temp.setDescription(rs.getString("DESCRIPTION"));

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
      return executeQuery(getConnection(), false);
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

    private BigDecimal templateCriteriaId;
    private BigDecimal actionId;
    private BigDecimal criteriaId;
    private BigDecimal templateId;
    private BigDecimal templateLanguageId;
    private BigDecimal languageId;
    private BigDecimal criteriaAttributeId;
    private BigDecimal attributeIndex;
    private BigDecimal attributeId;

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

  }

  public class ParameterSMSResponse extends DBTemplatesResponse<HashMap<BigDecimal, HashMap<BigDecimal, ArrayList<ParameterTemplate>>>> {

    @Override
    protected HashMap<BigDecimal, HashMap<BigDecimal, ArrayList<ParameterTemplate>>> createResponse() {
      return new HashMap<>();
    }
  }

  protected class GetParameterSMS extends DBTemplatesExecuteQuery<ParameterSMSResponse, UtilityLogger> {

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
      StringBuilder sql = new StringBuilder();
      return sql;
    }

    private ArrayList<BigDecimal> lastActionId = null;

    @Override
    protected void setReturnValue(ResultSet rs) throws SQLException {

      /*
SELECT
  t1.TEMPLATE_CRITERIA_ID, t1.ACTION_ID, t1.CRITERIA_ID, t1.TEMPLATE_ID,
  t1.TEMPLATE_LANGUAGE_ID, t1.LANGUAGE_ID ,
  t1.CRITERIA_ATTRIBUTE_ID, t1.ATTRIBUTE_INDEX,
  t2.ATTRIBUTE_ID
FROM (
  SELECT 
    t1.TEMPLATE_CRITERIA_ID, t1.ACTION_ID, t1.CRITERIA_ID, t1.TEMPLATE_ID,
    t1.TEMPLATE_LANGUAGE_ID, t1.LANGUAGE_ID ,
    t2.CRITERIA_ATTRIBUTE_ID, t2.ATTRIBUTE_INDEX
  FROM (
    SELECT 
      t1.TEMPLATE_CRITERIA_ID, t1.ACTION_ID, t1.CRITERIA_ID, t1.TEMPLATE_ID,
      t2.TEMPLATE_LANGUAGE_ID, t2.LANGUAGE_ID
    FROM (
      SELECT TEMPLATE_CRITERIA_ID, ACTION_ID, CRITERIA_ID, TEMPLATE_ID
      FROM dbo.CL_TEMPLATE_CRITERIA
      WHERE ACTION_ID in (12,34) and getdate() between RECORD_START_DTM and isnull(RECORD_START_DTM,getdate())
    )t1 left join (
      SELECT TEMPLATE_LANGUAGE_ID, TEMPLATE_ID, LANGUAGE_ID
      FROM dbo.CL_TEMPLATE_LANGUAGE
      WHERE getdate() between RECORD_START_DTM and isnull(RECORD_START_DTM,getdate())
    )t2 on t1.TEMPLATE_ID = t2.TEMPLATE_ID
  )t1 left join (
    SELECT TEMPLATE_LANGUAGE_ID, CRITERIA_ATTRIBUTE_ID, ATTRIBUTE_INDEX
    FROM dbo.CL_TEMPLATE_ATTRIBUTE
    WHERE RECORD_STATUS = 1
  )t2 on t1.TEMPLATE_LANGUAGE_ID = t2.TEMPLATE_LANGUAGE_ID
)t1 left join (
  SELECT CRITERIA_ATTRIBUTE_ID,  ATTRIBUTE_ID
  FROM dbo.CL_CRITERIA_ATTRIBUTE
)t2 on t1.CRITERIA_ATTRIBUTE_ID = t2.CRITERIA_ATTRIBUTE_ID
      
       * /
      ParameterTemplate temp = new ParameterTemplate();
      HashMap<BigDecimal, HashMap<BigDecimal, ArrayList<ParameterTemplate>>> templateId = response.getResponse();

      HashMap<BigDecimal, ArrayList<ParameterTemplate>> language = templateId.get(temp.getTemplateId());
      ArrayList<ParameterTemplate> listParam;

      if (language == null) {
        language = new HashMap<>();
        listParam = new ArrayList<>();
        listParam.add(temp);
        language.put(temp.getLanguageId(), listParam);
        templateId.put(temp.getTemplateId(), language);
      } else {
        listParam = language.get(temp.getLanguageId());
        if (listParam == null) {
          listParam = new ArrayList<>();
          listParam.add(temp);
          language.put(temp.getLanguageId(), listParam);
        } else {
          listParam.add(temp);
        }
      }
    }

    protected ParameterSMSResponse execute(ArrayList<BigDecimal> listActionId) {
      this.lastActionId = listActionId;
      return executeQuery(getConnection(), true);
    }

  }

  public ParameterSMSResponse getListParameter(ArrayList<BigDecimal> listActionId) {
    return new GetParameterSMS(logger).execute(listActionId);
  }

  //</editor-fold>
*/