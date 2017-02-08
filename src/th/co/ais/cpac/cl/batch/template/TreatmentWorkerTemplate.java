/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.template;

import java.util.ArrayList;
import java.util.HashMap;
import th.co.ais.cpac.cl.batch.db.CLTempTreatment;
import th.co.ais.cpac.cl.common.Context;

/**
 *
 * @author Optimus
 */
public class TreatmentWorkerTemplate extends Thread {

  protected final Context context;
  // detail ba 
  protected final ArrayList<CLTempTreatment.TempTreatment> listTempTreatment;
  //baNumber refference
  private CLTempTreatment.TempTreatment tempTreatmentReff;
  
  private ArrayList<String> listBaNumber = null;
  
  private HashMap<String, CLTempTreatment.TempTreatment> mapBaNumber = null;

  public TreatmentWorkerTemplate(Context context, ArrayList<CLTempTreatment.TempTreatment> listTempTreatment) {
    this.context = context;
    this.listTempTreatment = listTempTreatment;
  }

  protected void setListBaNumberAndMapBaNumber() {
    if (listTempTreatment == null || listTempTreatment.isEmpty()) {
      return;
    }
    listBaNumber = new ArrayList<>();
    mapBaNumber = new HashMap<>();

    for (int i = 0; i < listTempTreatment.size(); i++) {
      CLTempTreatment.TempTreatment tt = listTempTreatment.get(i);
      listBaNumber.add(tt.getBaNumber());
      mapBaNumber.put(tt.getBaNumber(), tt);
    }
  }

  protected ArrayList<String> getListBaNumber() {
    return listBaNumber;
  }

  protected HashMap<String, CLTempTreatment.TempTreatment> getMapBaNumber() {
    return mapBaNumber;
  }

  protected CLTempTreatment.TempTreatment getBaRefference() {
    if (listTempTreatment == null) {
      return null;
    }
    if (tempTreatmentReff != null) {
      return tempTreatmentReff;
    }

    for (int i = 0; i < listTempTreatment.size(); i++) {
      if (tempTreatmentReff == null) {
        tempTreatmentReff = listTempTreatment.get(i);
      } else if (tempTreatmentReff.getRegisterDate().after(listTempTreatment.get(i).getRegisterDate())) {
        tempTreatmentReff = listTempTreatment.get(i);
      }
    }
    return tempTreatmentReff;
  }

  protected boolean isExampt() {
    return false;
  }
}
