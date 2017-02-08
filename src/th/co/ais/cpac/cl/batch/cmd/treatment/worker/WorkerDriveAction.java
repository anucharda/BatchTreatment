/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.cmd.treatment.worker;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import th.co.ais.cpac.cl.batch.Constants;
import th.co.ais.cpac.cl.batch.cnf.CNFDatabase;
import th.co.ais.cpac.cl.batch.db.CLAction;
import th.co.ais.cpac.cl.batch.db.CLTempHisBaAccountBalance;
import th.co.ais.cpac.cl.batch.db.CLTempHisBaAccountBalance.TempAccountBalance;
import th.co.ais.cpac.cl.batch.util.UtilityDate;
import th.co.ais.cpac.cl.common.Context;
import th.co.ais.cpac.cl.common.UtilityLogger;
import th.co.ais.cpac.cl.template.database.DBConnectionPools;

/**
 *
 * @author Optimus
 */
public class WorkerDriveAction extends Thread {

  public class CommonBean {

    public CommonBean(CLAction.ActionDetail actionDetail, HashMap<String, ArrayList<BigDecimal>> day, HashMap<String, ArrayList<CLAction.ActionInfo>> action, HashMap<BigDecimal, CLAction.ActionSkipDetail> actionSkip) {
      this.actionDetail = actionDetail;
      this.day = day;
      this.action = action;
      this.actionSkip = actionSkip;
    }
    //action detail 
    private final CLAction.ActionDetail actionDetail;
    //ActionTriger : actionId
    private final HashMap<String, ArrayList<BigDecimal>> day;
    //ActionTriger : action info
    private final HashMap<String, ArrayList<CLAction.ActionInfo>> action;
    //actionId  : action skill info
    private final HashMap<BigDecimal, CLAction.ActionSkipDetail> actionSkip;

    protected CLAction.ActionDetail getActionDetail() {
      return actionDetail;
    }

    protected HashMap<String, ArrayList<BigDecimal>> getDay() {
      return day;
    }

    protected HashMap<String, ArrayList<CLAction.ActionInfo>> getAction() {
      return action;
    }

    protected HashMap<BigDecimal, CLAction.ActionSkipDetail> getActionSkip() {
      return actionSkip;
    }

    public void debugActionDetail(Context ctx) {
      if (actionDetail == null) {
        ctx.getLogger().debug("ActionDetail is null");
        return;
      }
      ctx.getLogger().debug("ActionDetail CollectionPathId " + actionDetail.getCollectionPathID().toPlainString());
      for (int i = 0; i < actionDetail.getListActionInfo().size(); i++) {
        CLAction.ActionInfo obj = actionDetail.getListActionInfo().get(i);
        ctx.getLogger().debug("ActionDetail " + obj.toString());
      }
    }

    public void debugDay(Context ctx) {
      if (day == null || day.isEmpty()) {
        ctx.getLogger().debug("Day is null {keep action} ");
        return;
      }
      Iterator<String> keys = day.keySet().iterator();

      while (keys.hasNext()) {
        String key = keys.next();
        ArrayList<BigDecimal> list = day.get(key);
        for (int i = 0; i < list.size(); i++) {
          ctx.getLogger().debug(key + ":" + list.get(i));
        }
      }
    }

    public void debugAction(Context ctx) {
      if (action == null || action.isEmpty()) {
        ctx.getLogger().debug("Action is null {keep action} ");
        return;
      }
      Iterator<String> keys = action.keySet().iterator();

      while (keys.hasNext()) {
        String key = keys.next();
        ArrayList<CLAction.ActionInfo> list = action.get(key);
        for (int i = 0; i < list.size(); i++) {
          ctx.getLogger().debug(key + ":" + list.get(i).toString());
        }
      }
    }

    public void debugActionSkip(Context ctx) {
      if (actionSkip == null || actionSkip.isEmpty()) {
        ctx.getLogger().debug("ActionSkip is null");
        return;
      }
      Iterator<BigDecimal> keys = actionSkip.keySet().iterator();

      while (keys.hasNext()) {
        BigDecimal key = keys.next();
        CLAction.ActionSkipDetail list = actionSkip.get(key);
        for (CLAction.ActionSkipInfo listActionSkipInfo : list.getListActionSkipInfo()) {
          ctx.getLogger().debug(key.toPlainString() + ":" + listActionSkipInfo.toString());
        }
      }
    }
  }

  private final Context ctx;
  private final CommonBean action;
  private final TempAccountBalance baInfo;

  private ArrayList<CLAction.ActionInfo> listActionRun = new ArrayList<>();
  private ArrayList<Date> listActionDate = new ArrayList<>();
  private ArrayList<Date> listActionDateRun = new ArrayList<>();
  private ArrayList<Constants.ActionStatus> listStatusExempt = new ArrayList<>();
  private Date dateRunning = null;
  private BigDecimal dateDiff = null;

  public WorkerDriveAction(Context ctx, CommonBean action, TempAccountBalance baInfo) {
    String xx = "{driveAction:" + baInfo.getBaNumber() + "}";
    this.ctx = ctx.clone(xx);
    this.action = action;
    this.baInfo = baInfo;

  }

  public WorkerDriveAction() {
    this.ctx = null;
    this.action = null;
    this.baInfo = null;
  }

  public CommonBean buildWorkBean(CLAction.ActionDetail actionDetail, HashMap<String, ArrayList<BigDecimal>> day, HashMap<String, ArrayList<CLAction.ActionInfo>> action, HashMap<BigDecimal, CLAction.ActionSkipDetail> actionSkip) {
    return new CommonBean(actionDetail, day, action, actionSkip);
  }

  protected static DBConnectionPools getConnection(Context ctx) {
    CNFDatabase cnf = new CNFDatabase();
    DBConnectionPools<CNFDatabase, UtilityLogger> dd = new DBConnectionPools<>(cnf, ctx.getLogger());
    return dd;
  }

  @Override
  public void run() {
    ctx.getLogger().debug("Start WorkerDriveAction ..........");
    ctx.getLogger().debug(baInfo.toString());

    Constants.ActionTrigerType actiontrigerType = mapActionTrigerType();
    ctx.getLogger().debug("actiontrigertype is " + actiontrigerType.name());

    ArrayList<CLAction.ActionInfo> listOfAction = action.getAction().get(actiontrigerType.getKeyActionTrigerType());
    ctx.getLogger().debug("listOfAction " + listOfAction);

    ArrayList<BigDecimal> listOfDay = action.getDay().get(actiontrigerType.getKeyActionTrigerType());
    ctx.getLogger().debug("listOfDay = ");
    ctx.getLogger().debug(listOfDay);

    if (listOfDay == null || listOfAction == null) {
      ctx.getLogger().debug("priority_status_id not match " + baInfo.toString());
      // ???? update status_code check error
      updateRunnigStatusTempTable();
      return;
    }

    CLTempHisBaAccountBalance.HistoryActionTLNLResponse tlnlResponse = null;
    if (baInfo.getPriorityStatusGroup() == Constants.PriorityStatusGroup.TerminateAndInactive) {
      tlnlResponse = new CLTempHisBaAccountBalance(ctx.getLogger()).queryHistoryTLNL(baInfo.getCaNumber(), baInfo.getBaNumber(), baInfo.getMinDueDate());
      ctx.getLogger().debug(tlnlResponse.info().toString());
    }
    Constants.ActionTriger actiontriger = mapActionTriger(tlnlResponse);

    setActionRun(listOfDay, listOfAction, tlnlResponse, actiontriger);

    setPrerequisiteAction();

    setScheduler();

    setActionSkip();

    setExempt();

    updateActionTempTable();

  }

  private Constants.ActionTrigerType mapActionTrigerType() {
    if (baInfo.getPriorityStatusGroup() == null) {
      return Constants.ActionTrigerType.NULL;
    }
    switch (baInfo.getPriorityStatusGroup()) {
      case Active: {
        return Constants.ActionTrigerType.DebtAge;
      }
      case Suspend1:
      case Suspend2:
      case Suspend3: {
        return Constants.ActionTrigerType.StatusAge;
      }
      case TerminateAndInactive: {
        return Constants.ActionTrigerType.ActionAge;
      }

    }
    return Constants.ActionTrigerType.NULL;
  }

  private Constants.ActionTriger mapActionTriger(CLTempHisBaAccountBalance.HistoryActionTLNLResponse tlnlResponse) {
    if (baInfo.getPriorityStatusGroup() == null) {
      return Constants.ActionTriger.NULL;
    }
    switch (baInfo.getPriorityStatusGroup()) {
      case Active: {
        return Constants.ActionTriger.DueDate;
      }
      case Suspend1: {
        return Constants.ActionTriger.SD1Date;
      }
      case Suspend2: {
        return Constants.ActionTriger.SD2Date;
      }
      case Suspend3: {
        return Constants.ActionTriger.SD3Date;
      }

      case TerminateAndInactive: {
        if (tlnlResponse == null) {
          return Constants.ActionTriger.NULL;
        }
        switch (tlnlResponse.getStatusCode()) {
          case CLTempHisBaAccountBalance.HistoryActionTLNLResponse.STATUS_COMPLETE: {
            ArrayList<CLTempHisBaAccountBalance.HistoryActionTLNL> tlnl = tlnlResponse.getResponse();
            boolean terminateletter = false, noticeLeller = false;
            for (int i = 0; i < tlnl.size(); i++) {
              if (tlnl.get(i).getActionType() == Constants.ActionType.TerminateLetter) {
                terminateletter = true;
                continue;
              }
              if (tlnl.get(i).getActionType() == Constants.ActionType.NoticeLeller) {
                noticeLeller = true;
              }
            }
            if (noticeLeller) {
              return Constants.ActionTriger.NLDate;
            } else if (terminateletter) {
              return Constants.ActionTriger.TLDate;
            } else {
              return Constants.ActionTriger.DTDate;
            }
          }
          case CLTempHisBaAccountBalance.HistoryActionTLNLResponse.STATUS_DATA_NOT_FOUND: {
            return Constants.ActionTriger.DTDate;
          }
          default: {
            break;
          }
        }
        break;
      }
      default: {
        break;
      }
    }
    return Constants.ActionTriger.NULL;
  }

  private void setActionRun(ArrayList<BigDecimal> listOfDay, ArrayList<CLAction.ActionInfo> listOfAction, CLTempHisBaAccountBalance.HistoryActionTLNLResponse tlnlResponse, Constants.ActionTriger actiontriger) {

    //<editor-fold defaultstate="collapsed" desc="Find Date">
    switch (actiontriger) {
      case DueDate: {
        dateDiff = baInfo.getMinDueDateCount();
        dateRunning = baInfo.getMinDueDate();
        break;
      }
      case SD1Date:
      case SD2Date:
      case SD3Date:
      case DTDate: {
        dateDiff = baInfo.getPriorityStatusDateCount();
        dateRunning = baInfo.getPriorityStatusDate();
        break;
      }
      case TLDate:
      case NLDate: {
        Constants.ActionType actionCheck = Constants.ActionType.TerminateLetter;
        if (actiontriger == Constants.ActionTriger.NLDate) {
          actionCheck = Constants.ActionType.NoticeLeller;
        }
        ArrayList<CLTempHisBaAccountBalance.HistoryActionTLNL> temp = tlnlResponse.getResponse();
        for (int i = 0; i < temp.size(); i++) {
          if (temp.get(i).getActionType() == actionCheck) {
            dateDiff = temp.get(i).getMessageDateCount();
            dateRunning = temp.get(i).getMessageDate();
            break;
          }
        }
        break;
      }
      default: {
        return;
      }
    }
    if (dateDiff == null) {
      ctx.getLogger().error("dateDiff is null");
      return;
    }
    ctx.getLogger().debug("dateDiff    := " + dateDiff.toPlainString());
    ctx.getLogger().debug("dateRunning := " + UtilityDate.toString(ctx, dateRunning, "NULL"));
    //</editor-fold>

    //find action ok
    //<editor-fold defaultstate="collapsed" desc="Find First Action">
    int indexAction = -1;

    for (int i = 0; i < listOfDay.size(); i++) {
      ctx.getLogger().debug("listOfDay(" + i + ") = " + listOfDay.get(i).intValue() + "-" + listOfAction.get(i).toString());
      if (listOfDay.get(i).intValue() >= dateDiff.intValue()) {
        indexAction = i;
        listActionRun.add(listOfAction.get(i));
        listActionDate.add(UtilityDate.addDate(ctx, dateRunning, listOfDay.get(i).intValue(), Calendar.DATE));
        ctx.getLogger().debug("first action " + listOfAction.get(i).toString());
        ctx.getLogger().debug("listActionDate " + listActionDate);
        break;
      }
    }
    // กรณีที่ date run เกินไปหมดแล้ว แต่ priority_status ยังไม่เปลี่ยนแปลง
    if (indexAction == -1) {
      ctx.getLogger().debug("set last action group");
      indexAction = listOfDay.size() - 1;
      listActionRun.add(listOfAction.get(indexAction));
      listActionDate.add(UtilityDate.addDate(ctx, dateRunning, listOfDay.get(indexAction).intValue(), Calendar.DATE));
      ctx.getLogger().debug("first action " + listActionRun.get(0).toString());
      ctx.getLogger().debug("listActionDate " + listActionDate);
    }

    //ctx.getLogger().debug("dateDiff    := " + dateDiff.toPlainString());
    //ctx.getLogger().debug("dateRunning := " + UtilityDate.toString(ctx, dateRunning, null));
    //
    //</editor-fold>
    ctx.getLogger().debug("indexAction : " + indexAction);
    //find start date same 
    //<editor-fold defaultstate="collapsed" desc="Find Action Start Same">
    for (int i = (indexAction + 1); i < listOfDay.size(); i++) {
      ctx.getLogger().debug("Same DateDiff =" + dateDiff.intValue());
      ctx.getLogger().debug("Same listOfDay(" + i + ") = " + listOfDay.get(i).intValue());

      if (listOfDay.get(indexAction).intValue() == listOfDay.get(i).intValue()) {
        listActionRun.add(listOfAction.get(i));
        listActionDate.add(UtilityDate.addDate(ctx, dateRunning, listOfDay.get(i).intValue(), Calendar.DATE));
        continue;
      }
      break;
    }
    //</editor-fold>

    //find action period 
    Date now = new Date();
    //<editor-fold defaultstate="collapsed" desc="Find Action Period">
    for (int i = 0; i < indexAction; i++) {
      if (listOfAction.get(i).getActionPlanBoo() == 'Y') {

        int dateCount = listOfDay.get(i).intValue();
        Constants.ActionPeriodType periodType = listOfAction.get(i).getActionPeriodType();
        BigDecimal periodVal = listOfAction.get(i).getActionPeriodValue();
        Date dateStart = UtilityDate.addDate(ctx, dateRunning, dateCount, Calendar.DATE);
        Date dateEnd = periodType.calculate(dateStart, periodVal);

        ctx.getLogger().debug("Find Action Period:" + listOfAction.get(i).getActionID().toPlainString());
        if (now.compareTo(dateEnd) > 0) {
          ctx.getLogger().debug("Action add for Period:" + listOfAction.get(i).getActionID().toPlainString());
          ctx.getLogger().debug(listOfDay);
          listActionRun.add(listOfAction.get(i));
          listActionDate.add(now);
        } else {
          ctx.getLogger().debug("Action skip for Period:" + listOfAction.get(i).getActionID().toPlainString());
        }

      }
    }
    //</editor-fold>
    //

  }

  private void setPrerequisiteAction() {
    ctx.getLogger().debug("start find prerequisite action");
    ArrayList<CLAction.ActionInfo> listActionRunTemp = new ArrayList<>();
    CLTempHisBaAccountBalance.HistoryActionResponse listActionHistory = null;

    for (int i = 0; i < listActionRun.size(); i++) {
      CLAction.ActionInfo actionRun = listActionRun.get(i);
      if (actionRun.getPreActionID() == null) {
        ctx.getLogger().debug("action(" + actionRun.getActionID().toPlainString() + "):no have prerequisite");
        listActionRunTemp.add(actionRun);
      } else {
        if (listActionHistory == null) {
          listActionHistory = new CLTempHisBaAccountBalance(ctx.getLogger()).queryHistoryAction(baInfo.getCaNumber(), baInfo.getBaNumber(), baInfo.getMinDueDate());
          ctx.getLogger().debug(listActionHistory.info().toString());
          switch (listActionHistory.getStatusCode()) {
            case CLTempHisBaAccountBalance.HistoryActionResponse.STATUS_COMPLETE: {
              break;
            }
            case CLTempHisBaAccountBalance.HistoryActionResponse.STATUS_DATA_NOT_FOUND: {
              break;
            }
            default: {
              return;
            }
          }
        }
        listActionRunTemp.add(getPrerequisiteAction(actionRun, listActionHistory.getResponse()));
      }
    }
    listActionRun = listActionRunTemp;
  }

  private CLAction.ActionInfo getPrerequisiteAction(CLAction.ActionInfo actPre, ArrayList<CLTempHisBaAccountBalance.HistoryAction> listHistory) {
    ctx.getLogger().debug("getPrerequisiteAction");
    ArrayList<CLAction.ActionInfo> listAction = action.getActionDetail().getListActionInfo();
    ArrayList<CLAction.ActionInfo> temp = new ArrayList<>();
    temp.add(actPre);
    CLAction.ActionInfo tttt = actPre;
    // หา list ของ action prerequisite ::
    // ???? กลัวว่าจะเกิด loop ไม่รู้จบ 
    while (true) {
      ctx.getLogger().debug("get prerequisite action id : " + tttt.getActionID());
      for (int i = 0; i < tttt.getSequence(); i++) {
        if (listAction.get(i).getActionID().equals(tttt.getPreActionID())) {
          tttt = listAction.get(i);
          temp.add(tttt);
          ctx.getLogger().debug("get prerequisite action id : " + tttt.getActionID() + " to " + tttt.getActionID());
          break;
        }
      }
      if (tttt.getPreActionID() == null) {
        break;
      }
    }
    // check ว่า history run action อะไรไปแล้วบ้าง    
    for (int i = (temp.size() - 1); i > -1; i--) {
      CLAction.ActionInfo tt = temp.get(i);
      boolean ready = true;
      ctx.getLogger().debug("Compare Action " + tt.getActionID());
      for (int j = 0; j < listHistory.size(); j++) {
        if (tt.getPreActionID() == listHistory.get(j).getActionId()) {
          ctx.getLogger().debug("Action " + listHistory.get(j).getActionId() + " Success");
          ready = false;
          break;
        } else {
          ctx.getLogger().debug("Action " + listHistory.get(j).getActionId() + " Matched ");
        }
      }
      if (ready) {
        return tt;
      }
    }

    return null;
  }

  private void setScheduler() {
    ctx.getLogger().debug("setScheduler ..");
    ArrayList<Date> listActionDateTemp = new ArrayList<>();
    for (int i = 0; i < listActionRun.size(); i++) {
      CLAction.ActionInfo aa = listActionRun.get(i);
      if (listActionRun.get(i).getActionPlanBoo() == 'Y') {
        ctx.getLogger().debug("Action " + listActionRun.get(i).getActionID() + "(" + listActionRun.get(i).getActionPlanBoo() + ")");
        listActionDateTemp.add(getActionPlanDay(listActionDate.get(i), aa));
      } else {
        ctx.getLogger().debug("Action " + listActionRun.get(i).getActionID() + "(" + listActionRun.get(i).getActionPlanBoo() + ")");
        listActionDateTemp.add(UtilityDate.meargeTime(ctx, listActionDate.get(i), listActionRun.get(i).getActionTime(), null));
      }
    }
    listActionDate = listActionDateTemp;
  }

  private Date getActionPlanDay(Date dateRun, CLAction.ActionInfo act) {
    if (act == null) {
      ctx.getLogger().error("getActionPlanDay act is null");
      return null;
    }
    Date meargeDate = UtilityDate.meargeTime(ctx, dateRun, act.getActionTime(), null);
    if (meargeDate == null) {
      ctx.getLogger().error("getActionPlanDay meargeDate is null");
      return null;
    }

    Calendar cal = Calendar.getInstance();
    cal.setTime(meargeDate);

    int dateNo = cal.get(Calendar.DAY_OF_MONTH);
    String[] days = act.getActionPlanDay().split("\\|");

    for (int i = 0; i < days.length; i++) {
      if ("EOM".equals(days[i])) {
        ctx.getLogger().error("getActionPlanDay EOM");
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
      } else {
        ctx.getLogger().error("getActionPlanDay OTHER");
        int dNo = Integer.parseInt(days[i]);
        if (dateNo <= dNo) {
          cal.set(Calendar.DAY_OF_MONTH, dNo);
          return cal.getTime();
        }
      }
    }
    int dNo = Integer.parseInt(days[0]);
    cal.set(Calendar.DAY_OF_MONTH, dNo);
    cal.add(Calendar.MONTH, 1);
    return cal.getTime();
  }

  private void setActionSkip() {
    ctx.getLogger().debug("setActionSkip");
    ArrayList<Date> listActionDateTemp = new ArrayList<>();
    for (int i = 0; i < listActionRun.size(); i++) {
      CLAction.ActionInfo aa = listActionRun.get(i);
      if (aa.getNumberSkipAction().intValue() > 0) {
        ctx.getLogger().debug("setActionSkip find skip date");
        listActionDateTemp.add(getDateActionSkip(listActionDate.get(i), aa));
      } else {
        ctx.getLogger().debug("setActionSkip no skip date");
        listActionDateTemp.add(listActionDate.get(i));
      }
    }
    listActionDateRun = listActionDateTemp;
  }

  private Date getDateActionSkip(Date skipDate, CLAction.ActionInfo act) {
    Long skip = UtilityDate.toLong(ctx, skipDate, "yyyyMMddhhmm", null);
    if (skip == null) {
      ctx.getLogger().debug("getDateActionSkip skip is null");
      return null;
    }
    CLAction.ActionSkipDetail aaaa = action.getActionSkip().get(act.getActionID());
    ArrayList<CLAction.ActionSkipInfo> list = aaaa.getListActionSkipInfo();
    Date dateRun = new Date(skipDate.getTime());
    while (true) {
      ctx.getLogger().debug("loop find Action skip ");
      boolean newLoop = false;
      for (int i = 0; i < list.size(); i++) {
        CLAction.ActionSkipInfo actskip = list.get(i);
        ctx.getLogger().debug("getDateActionSkip : " + actskip.toString());
        if (actskip.getDateTimeFrom() >= skip && skip <= actskip.getDateTimeTo()) {
          switch (actskip.getCalendarType()) {
            case Disaster:
            case Festival:
            case Holiday:
            case OtherEvents: {
              ctx.getLogger().debug("getDateActionSkip : group 1");
              dateRun = UtilityDate.addDate(ctx, actskip.getDateTimeTo(), "yyyyMMddhhmm", 1, Calendar.DATE, null);
              dateRun = UtilityDate.meargeTime(ctx, dateRun, skipDate, null);
              newLoop = true;
              break;
            }
            case Friday: {
              ctx.getLogger().debug("getDateActionSkip : group 2");
              Calendar cal = Calendar.getInstance();
              cal.setTime(skipDate);
              boolean monday = cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
              if (monday) {
                dateRun = UtilityDate.addDate(ctx, skipDate, 1, Calendar.DATE);
                newLoop = true;
              }
              break;
            }
            case Weekend: {
              ctx.getLogger().debug("getDateActionSkip : group 3");
              Calendar cal = Calendar.getInstance();
              cal.setTime(skipDate);
              if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                dateRun = UtilityDate.addDate(ctx, skipDate, 2, Calendar.DATE);
                newLoop = true;
              } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                dateRun = UtilityDate.addDate(ctx, skipDate, 1, Calendar.DATE);
                newLoop = true;
              }
              break;
            }
            case SystemMaintenance: {
              ctx.getLogger().debug("getDateActionSkip : group 4");
              //dateRun = UtilityDate.addDate(ctx, actskip.getDateTimeTo(), "yyyyMMddhhmm", 1, Calendar.DATE, null);
              dateRun = UtilityDate.addDate(ctx, actskip.getDateTimeTo(), "yyyyMMddhhmm", 5, Calendar.MINUTE, null);
              newLoop = true;
              break;
            }
            default:
              ctx.getLogger().debug("getDateActionSkip : default");
              return null;
          }
        }
        if (newLoop) {
          break;
        }
      }
      if (newLoop) {
        continue;
      }
      break;
    }

    return dateRun;
  }

  private void setExempt() {
    ctx.getLogger().debug("setExempt");
    for (int i = 0; i < listActionRun.size(); i++) {
      ctx.getLogger().debug("setExempt " + listActionRun.get(i).toString());
      CLTempHisBaAccountBalance cc = new CLTempHisBaAccountBalance(ctx.getLogger());
      CLTempHisBaAccountBalance.CounterExemptResponse res = cc.countExempt(baInfo, listActionRun.get(i));
      ctx.getLogger().debug(res.info().toString());
      switch (res.getStatusCode()) {
        case CLTempHisBaAccountBalance.CounterExemptResponse.STATUS_COMPLETE: {
          if (res.getResponse() > 0) {
            ctx.getLogger().debug("setExempt UnqualifiedExemption ");
            listStatusExempt.add(Constants.ActionStatus.UnqualifiedExemption);
          } else {
            ctx.getLogger().debug("setExempt VerifyingData");
            listStatusExempt.add(Constants.ActionStatus.VerifyingData);
          }
          break;
        }
        default: {
          ctx.getLogger().debug("setExempt VerifyingData");
          listStatusExempt.add(Constants.ActionStatus.NULL);
        }
      }
    }
  }

  private void updateActionTempTable() {
    if (listActionRun != null && !listActionRun.isEmpty()) {
      CLTempHisBaAccountBalance update = new CLTempHisBaAccountBalance(ctx.getLogger());
      update.buildConnection();
      update.getDBConnection().setManualCommit();

      for (int i = 0; i < listActionRun.size(); i++) {
        boolean complete = true;
        CLTempHisBaAccountBalance.ExecuteResponse resp;
        if (i == 0) {
          resp = update.updateActionToTempTable(baInfo, listActionRun.get(i), listActionDate.get(i), listActionDateRun.get(i), listStatusExempt.get(i));
        } else {
          resp = update.insertAndupdateActionToTempTable(baInfo, listActionRun.get(i), listActionDate.get(i), listActionDateRun.get(i), listStatusExempt.get(i));
        }
        ctx.getLogger().debug(resp.info().toString());
        switch (resp.getStatusCode()) {
          case CLTempHisBaAccountBalance.ExecuteResponse.STATUS_COMPLETE: {
            break;
          }
          default: {
            complete = false;
            update.getDBConnection().rollback();
            break;
          }
        }
        if (!complete) {
          update.getDBConnection().rollback();
          break;
        }
      }

      update.getDBConnection().commit();
      update.getDBConnection().setAutoCommit();
      update.getDBConnection().close();
    }
  }

  private void updateRunnigStatusTempTable() {

    CLTempHisBaAccountBalance update = new CLTempHisBaAccountBalance(ctx.getLogger());
    update.buildConnection();
    update.getDBConnection().setManualCommit();

    CLTempHisBaAccountBalance.ExecuteResponse resp;

    resp = update.updateRunnigStatusTempTable(baInfo);
    ctx.getLogger().debug(resp.info().toString());
    switch (resp.getStatusCode()) {
      case CLTempHisBaAccountBalance.ExecuteResponse.STATUS_COMPLETE:
      case CLTempHisBaAccountBalance.ExecuteResponse.STATUS_DATA_NOT_FOUND: {
        break;
      }
      default: {
        update.getDBConnection().rollback();
        break;
      }
    }
    update.getDBConnection().commit();
    update.getDBConnection().setAutoCommit();
    update.getDBConnection().close();
  }

}
