/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.cmd.terminate;

import java.util.ArrayList;
import java.util.HashMap;
import th.co.ais.cpac.cl.batch.Constants;
import th.co.ais.cpac.cl.batch.db.CLActionCriteria;
import th.co.ais.cpac.cl.batch.db.CLCFGPriority;
import th.co.ais.cpac.cl.batch.db.CLCriteriaAttribute;
import th.co.ais.cpac.cl.batch.db.CLMobileInformation;
import th.co.ais.cpac.cl.batch.db.CLNetworkType;
import th.co.ais.cpac.cl.batch.db.CLProductType;
import th.co.ais.cpac.cl.batch.db.CLTempTreatment;
import th.co.ais.cpac.cl.batch.db.PMCompany;
import th.co.ais.cpac.cl.batch.template.SDDTWorkerTreatmentTemplate;
import th.co.ais.cpac.cl.common.Context;

/**
 *
 * @author Optimus
 */
public class WorkerTerminate extends SDDTWorkerTreatmentTemplate {

  public WorkerTerminate(
    ArrayList<CLCriteriaAttribute.CriteriaAttributeInfo> listParamter,
    ArrayList<CLCriteriaAttribute.CriteriaAttributeInfo> listParamterBlacklist,
    HashMap<String, CLActionCriteria.ActionOrderCriteria> listActionOrderCriteria,
    HashMap<String, CLProductType.ProductTypeInfo> listProductType,
    HashMap<String, CLNetworkType.NetworkTypeInfo> listCLNetworkType,
    ArrayList<CLCFGPriority.CFGPriorityInfo> listCLCFGPriority,
    HashMap<String, PMCompany.PMCompanyInfo> listCompany,
    Context context,
    ArrayList<CLTempTreatment.TempTreatment> listTempTreatment) {

    super(listParamter, listParamterBlacklist, listActionOrderCriteria, listProductType, listCLNetworkType, listCLCFGPriority, listCompany, context, listTempTreatment);

  }

  @Override
  public void run() {
    context.getLogger().debug("Worker Terminate ...........");

    setListBaNumberAndMapBaNumber();

    CLActionCriteria.ActionOrderCriteria actionOrderCriteria = getActionOrderCriteria();
    ArrayList<CLMobileInformation.CLMobileInfo> listMobile = getListMobileProcess();
    boolean baInactive = true;
    if (listMobile == null) {
      context.getLogger().debug("");
      return;
    }
    for (int i = 0; i < listMobile.size(); i++) {
      CLMobileInformation.CLMobileInfo mobileInfo = listMobile.get(i);

      // กำหนด set 'Y' ที่ CFGPriorityInfo ให้ "PREPAID FLAG = Y"
      // กรณีที่ต้องการให้ file Inactive Ba Status = "";
      if (mobileInfo.getPrepaidBoo() == 'Y') {
        //baInactive = false;
        continue;
      }

      // ??? check exampt mobile status
      if (isExampt()) {
        baInactive = false;
        continue;
      }

      String messageOrder = getMessageOrderSuspendOrTerminate(mobileInfo);
      if (messageOrder == null) {
        context.getLogger().error("create message fail : " + mobileInfo.toString());
        return;
      }

      CLNetworkType.NetworkTypeInfo networkType = getNetworkType(mobileInfo);
      CLTempTreatment.TempTreatment tempTreatment = getMapBaNumber().get(mobileInfo.getBaNumber());
      dataProcess.addListObjectCLOrder(buildObjectCLOrder(messageOrder, tempTreatment, mobileInfo, networkType));

    }

    if (dataProcess.getListObjectCLOrder() == null || dataProcess.getListObjectCLOrder().isEmpty()) {
      return;
    }
    dataProcess.setAddBaNumberInactive(baInactive);

    if (baInactive) {
      int last = dataProcess.getListObjectCLOrder().size() - 1;
      dataProcess.getListObjectCLOrder().get(last).getClOrder().setEndRequestBoo('Y');
    }
    
    Constants.BlackListOption blacklistOption = Constants.mapBlackListOption(actionOrderCriteria.getBlacklistOption());
    if (blacklistOption != Constants.BlackListOption.Blacklist) {
      return;
    }

    CLTempTreatment.TempTreatment tempTreatment = listTempTreatment.get(0);
    if (isExampt()) {
      return;
    }

    if (!canBlacklist()) {
      return;
    }

    String messageBlacklist = getMessageBlacklist(tempTreatment);
    ObjectBlacklist objectBlacklist = buildObjectBlacklist(messageBlacklist, tempTreatment);
    dataProcess.addListObjectBlacklist(objectBlacklist);

  }

  private final ObjectTerminate dataProcess = new ObjectTerminate();

  public class ObjectTerminate {

    public ObjectTerminate() {
    }

    private final ArrayList<ObjectCLOrder> listObjectCLOrder = new ArrayList<>();

    private final ArrayList<ObjectBlacklist> listObjectBlacklist = new ArrayList<>();

    private boolean addBaNumberInactive = false;

    public ArrayList<ObjectCLOrder> getListObjectCLOrder() {
      return listObjectCLOrder;
    }

    public ArrayList<ObjectBlacklist> getListObjectBlacklist() {
      return listObjectBlacklist;
    }

    public void addListObjectCLOrder(ObjectCLOrder temp) {
      listObjectCLOrder.add(temp);
    }

    public void addListObjectBlacklist(ObjectBlacklist temp) {
      listObjectBlacklist.add(temp);
    }

    public boolean isAddBaNumberInactive() {
      return addBaNumberInactive;
    }

    public void setAddBaNumberInactive(boolean addBaNumberInactive) {
      this.addBaNumberInactive = addBaNumberInactive;
    }

  }

  public ObjectTerminate getDataProcess() {
    return dataProcess;
  }

}
