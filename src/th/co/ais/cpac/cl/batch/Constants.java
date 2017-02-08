/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import th.co.ais.cpac.cl.batch.cnf.CNFDatabase;
import th.co.ais.cpac.cl.common.UtilityLogger;
import th.co.ais.cpac.cl.template.database.DBConnectionPools;

/**
 *
 * @author Optimus
 */
public class Constants extends th.co.ais.cpac.cl.template.configuration.Constants {

  public static int letterRunning = 1;

  public static DBConnectionPools getDBConnectionPools(UtilityLogger logger) {
    CNFDatabase cnf = new CNFDatabase();
    return new DBConnectionPools<>(cnf, logger);
  }

  public static final synchronized String buildSequence() {
    if (letterRunning > 999999) {
      letterRunning = 1;
    }
    return String.format("%06d", letterRunning++);
  }

  public enum RecordTypeInterface {
    Header(1),
    Body(2),
    Footer(9);
    private final int code;

    private RecordTypeInterface(int code) {
      this.code = code;
    }

    public String getCode() {
      switch (code) {
        case 1:
          return "01";
        case 2:
          return "02";
        case 9:
          return "09";
      }
      return "00";
    }

  }

  public enum ActionTrigerType {
    DebtAge(1),
    StatusAge(2),
    ActionAge(3),
    NULL(-9999);

    private final int code;

    private ActionTrigerType(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

    public String getKeyActionTrigerType() {
      return this.name();
    }

  }

  public static final ActionTrigerType mapActionTrigerType(int code) {
    if (ActionTrigerType.DebtAge.getCode() == code) {
      return ActionTrigerType.DebtAge;
    } else if (ActionTrigerType.StatusAge.getCode() == code) {
      return ActionTrigerType.StatusAge;
    } else if (ActionTrigerType.ActionAge.getCode() == code) {
      return ActionTrigerType.ActionAge;
    }
    return ActionTrigerType.NULL;
  }

  public enum ActionTriger {
    DueDate(1),
    SD1Date(2),
    SD2Date(3),
    SD3Date(4),
    DTDate(5),
    TLDate(6),
    NLDate(7),
    NULL(-9999);
    private final int code;

    private ActionTriger(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

    public String getKeyActionTriger() {
      switch (code) {
        case 1: {
          return "DueDate";
        }
        case 2: {
          return "SD1Date";
        }
        case 3: {
          return "SD2Date";
        }
        case 4: {
          return "SD3Date";
        }
        case 5: {
          return "DTDate";
        }
        case 6: {
          return "TLDate";
        }
        case 7: {
          return "NLDate";
        }
      }
      return "NULL";
    }

  }

  public static final ActionTriger mapActionTriger(BigDecimal code) {
    if (code == null) {
      return ActionTriger.NULL;
    }
    if (ActionTriger.DueDate.getCode() == code.intValue()) {
      return ActionTriger.DueDate;
    } else if (ActionTriger.SD1Date.getCode() == code.intValue()) {
      return ActionTriger.SD1Date;
    } else if (ActionTriger.SD2Date.getCode() == code.intValue()) {
      return ActionTriger.SD2Date;
    } else if (ActionTriger.SD3Date.getCode() == code.intValue()) {
      return ActionTriger.SD3Date;
    } else if (ActionTriger.DTDate.getCode() == code.intValue()) {
      return ActionTriger.DTDate;
    } else if (ActionTriger.TLDate.getCode() == code.intValue()) {
      return ActionTriger.TLDate;
    } else if (ActionTriger.NLDate.getCode() == code.intValue()) {
      return ActionTriger.NLDate;
    }
    return ActionTriger.NULL;
  }

  public enum ActionMode {
    SMS(1),
    TELE(2),
    SuspendDebt(3),
    Terminate(4),
    Letter(5),
    ThirdParty(6),
    Legal(7),
    Waive(8),
    WriteOff(9),
    Blacklist(10),
    DeBlacklist(11),
    Reconnect(12),
    NULL(-9999);

    private final int code;

    private ActionMode(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

    public String getCodeString() {
      switch (code) {
        case 1:
          return "TS";
        case 2:
          return "TH";
        case 3:
          return "SD";
        case 4:
          return "DT";
        case 5:
          return "LT";
        case 6:
          return "TP";
        case 7:
          return "LG";
        case 8:
          return "WA";
        case 9:
          return "WO";
        case 10:
          return "BL";
        case 11:
          return "DL";
        case 12:
          return "RC";
      }
      return "NULL";
    }
  }

  public static final ActionMode mapActionMode(int code) {
    if (ActionMode.SMS.getCode() == code) {
      return ActionMode.SMS;
    } else if (ActionMode.TELE.getCode() == code) {
      return ActionMode.TELE;
    } else if (ActionMode.SuspendDebt.getCode() == code) {
      return ActionMode.SuspendDebt;
    } else if (ActionMode.Terminate.getCode() == code) {
      return ActionMode.Terminate;
    } else if (ActionMode.Letter.getCode() == code) {
      return ActionMode.Letter;
    } else if (ActionMode.ThirdParty.getCode() == code) {
      return ActionMode.ThirdParty;
    } else if (ActionMode.Legal.getCode() == code) {
      return ActionMode.Legal;
    } else if (ActionMode.Waive.getCode() == code) {
      return ActionMode.Waive;
    } else if (ActionMode.WriteOff.getCode() == code) {
      return ActionMode.WriteOff;
    } else if (ActionMode.Blacklist.getCode() == code) {
      return ActionMode.Blacklist;
    } else if (ActionMode.DeBlacklist.getCode() == code) {
      return ActionMode.DeBlacklist;
    } else if (ActionMode.Reconnect.getCode() == code) {
      return ActionMode.Reconnect;
    }
    return ActionMode.NULL;
  }

  public enum ActionOperator {
    MINUS(1), PLUS(2), NULL(-9999);
    private final int code;

    private ActionOperator(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

    public int getValue() {
      switch (code) {
        case 1:
          return -1;
        case 2:
          return 1;
      }
      return 0;
    }

  }

  public static final ActionOperator mapActionOperator(int code) {
    if (ActionOperator.MINUS.getCode() == code) {
      return ActionOperator.MINUS;
    } else if (ActionOperator.PLUS.getCode() == code) {
      return ActionOperator.PLUS;
    }
    return ActionOperator.NULL;
  }

  public static final ActionOperator mapActionOperator(String code) {
    if ("-".equals(code)) {
      return ActionOperator.MINUS;
    } else if ("+".equals(code)) {
      return ActionOperator.PLUS;
    }
    return ActionOperator.NULL;
  }

  public static final ActionStatus[] ACTION_STATUS_DONE = {ActionStatus.InProgress, ActionStatus.Complete, ActionStatus.Incomplete};

  public static final String getStringActionStatusDone() {
    String rs = "";
    for (int i = 0; i < ACTION_STATUS_DONE.length; i++) {
      if (i == 0) {
        rs = "" + ACTION_STATUS_DONE[i].getCode();
      } else {
        rs += ",";
        rs += ACTION_STATUS_DONE[i].getCode();
      }
    }
    return rs;
  }

  public enum ActionStatus {
    UnqualifiedChangeActionCriteria(-9),
    UnqualifiedChangePath(-8),
    UnqualifiedChangeAction(-7),
    UnqualifiedChangeSchedule(-6),
    UnqualifiedExemption(-5),
    UnqualifiedAdjustment(-4),
    UnqualifiedPaid(-3),
    UnqualifiedRejected(-2),
    UnqualifiedNoTreatment(-1),
    VerifyingData(0),
    WaitingtoConfirm(1),
    WaitingtoProcess(2),
    InProgress(3),
    Complete(4),
    Incomplete(5),
    Failed(6),
    NULL(-9999);

    private final int code;

    private ActionStatus(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }
    public BigDecimal getBigDecimalCode(){
      return new BigDecimal(code);
    }
  }

  public static final ActionStatus mapActionStatus(BigDecimal code) {
    if (code == null) {
      return ActionStatus.NULL;
    }
    return mapActionStatus(code.intValue());
  }

  public static final ActionStatus mapActionStatus(int code) {
    if (ActionStatus.UnqualifiedChangePath.getCode() == code) {
      return ActionStatus.UnqualifiedChangePath;
    } else if (ActionStatus.UnqualifiedChangeAction.getCode() == code) {
      return ActionStatus.UnqualifiedChangeAction;
    } else if (ActionStatus.UnqualifiedChangeSchedule.getCode() == code) {
      return ActionStatus.UnqualifiedChangeSchedule;
    } else if (ActionStatus.UnqualifiedExemption.getCode() == code) {
      return ActionStatus.UnqualifiedExemption;
    } else if (ActionStatus.UnqualifiedAdjustment.getCode() == code) {
      return ActionStatus.UnqualifiedAdjustment;
    } else if (ActionStatus.UnqualifiedPaid.getCode() == code) {
      return ActionStatus.UnqualifiedPaid;
    } else if (ActionStatus.UnqualifiedRejected.getCode() == code) {
      return ActionStatus.UnqualifiedRejected;
    } else if (ActionStatus.UnqualifiedNoTreatment.getCode() == code) {
      return ActionStatus.UnqualifiedNoTreatment;
    } else if (ActionStatus.VerifyingData.getCode() == code) {
      return ActionStatus.VerifyingData;
    } else if (ActionStatus.WaitingtoConfirm.getCode() == code) {
      return ActionStatus.WaitingtoConfirm;
    } else if (ActionStatus.WaitingtoProcess.getCode() == code) {
      return ActionStatus.WaitingtoProcess;
    } else if (ActionStatus.InProgress.getCode() == code) {
      return ActionStatus.InProgress;
    } else if (ActionStatus.Complete.getCode() == code) {
      return ActionStatus.Complete;
    } else if (ActionStatus.Incomplete.getCode() == code) {
      return ActionStatus.Incomplete;
    } else if (ActionStatus.Failed.getCode() == code) {
      return ActionStatus.Failed;
    } else if (ActionStatus.UnqualifiedChangeActionCriteria.getCode() == code) {
      return ActionStatus.UnqualifiedChangeActionCriteria;
    }

    return ActionStatus.NULL;
  }

  public enum ActionType {
    PreDue(1),
    PreSuspend(2),
    Suspend1(3),
    PostSuspend1(4),
    Suspend2(5),
    PostSuspend2(6),
    Suspend3(7),
    PostSuspend3(8),
    Terminate(9),
    PostTerminate(10),
    TerminateLetter(11),
    PostTerminateLetter(12),
    NoticeLeller(13),
    PostNotice(14),
    NULL(-9999);

    private final int code;

    private ActionType(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

    public String getCodeString() {
      switch (code) {
        case 1:
          return "Pre-Due";
        case 2:
          return "Pre-SD";
        case 3:
          return "SD1";
        case 4:
          return "Post-SD1";
        case 5:
          return "SD2";
        case 6:
          return "Post-SD2";
        case 7:
          return "SD3";
        case 8:
          return "Post-SD3";
        case 9:
          return "DT";
        case 10:
          return "Post-DT";
        case 11:
          return "TL";
        case 12:
          return "Post-TL";
        case 13:
          return "NL";
        case 14:
          return "Post-NL";
      }
      return "NULL";

    }

  }

  public static final ActionType mapActionType(int code) {
    if (ActionType.PreDue.getCode() == code) {
      return ActionType.PreDue;
    } else if (ActionType.PreSuspend.getCode() == code) {
      return ActionType.PreSuspend;
    } else if (ActionType.Suspend1.getCode() == code) {
      return ActionType.Suspend1;
    } else if (ActionType.PostSuspend1.getCode() == code) {
      return ActionType.PostSuspend1;
    } else if (ActionType.Suspend2.getCode() == code) {
      return ActionType.Suspend2;
    } else if (ActionType.PostSuspend2.getCode() == code) {
      return ActionType.PostSuspend2;
    } else if (ActionType.Terminate.getCode() == code) {
      return ActionType.Terminate;
    } else if (ActionType.PostTerminate.getCode() == code) {
      return ActionType.PostTerminate;
    }
    return ActionType.NULL;
  }

  public enum PriorityStatus {
    Active(1),
    Suspend1(2),
    Suspend2(3),
    SuspendDebt1(4),
    SuspendDebt2(5),
    SuspendDebt3(6),
    SuspendCreditLimit1(7),
    SuspendCreditLimit2(8),
    SuspendCreditLimit3(9),
    SuspendFraud1(10),
    SuspendFraud2(11),
    SuspendFraud3(12),
    Disconnect(13),
    Terminate(14),
    Inactive(15),
    Other(16),
    NULL(-9999);

    private final int code;

    private PriorityStatus(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

  }

  public static final PriorityStatus mapPriorityStatus(int code) {
    if (PriorityStatus.Active.getCode() == code) {
      return PriorityStatus.Active;
    } else if (PriorityStatus.Suspend1.getCode() == code) {
      return PriorityStatus.Suspend1;
    } else if (PriorityStatus.Suspend2.getCode() == code) {
      return PriorityStatus.Suspend2;
    } else if (PriorityStatus.SuspendDebt1.getCode() == code) {
      return PriorityStatus.SuspendDebt1;
    } else if (PriorityStatus.SuspendDebt2.getCode() == code) {
      return PriorityStatus.SuspendDebt2;
    } else if (PriorityStatus.SuspendDebt3.getCode() == code) {
      return PriorityStatus.SuspendDebt3;
    } else if (PriorityStatus.SuspendCreditLimit1.getCode() == code) {
      return PriorityStatus.SuspendCreditLimit1;
    } else if (PriorityStatus.SuspendCreditLimit2.getCode() == code) {
      return PriorityStatus.SuspendCreditLimit2;
    } else if (PriorityStatus.SuspendCreditLimit3.getCode() == code) {
      return PriorityStatus.SuspendCreditLimit3;
    } else if (PriorityStatus.SuspendFraud1.getCode() == code) {
      return PriorityStatus.SuspendFraud1;
    } else if (PriorityStatus.SuspendFraud2.getCode() == code) {
      return PriorityStatus.SuspendFraud2;
    } else if (PriorityStatus.SuspendFraud3.getCode() == code) {
      return PriorityStatus.SuspendFraud3;
    } else if (PriorityStatus.Disconnect.getCode() == code) {
      return PriorityStatus.Disconnect;
    } else if (PriorityStatus.Terminate.getCode() == code) {
      return PriorityStatus.Terminate;
    } else if (PriorityStatus.Inactive.getCode() == code) {
      return PriorityStatus.Inactive;
    } else if (PriorityStatus.Other.getCode() == code) {
      return PriorityStatus.Other;
    }
    return PriorityStatus.NULL;
  }

  public enum PriorityStatusGroup {
    Active(1),
    Suspend1(2),
    Suspend2(3),
    Suspend3(4),
    TerminateAndInactive(5),
    Other(6),
    NULL(-9999);

    private final int code;

    private PriorityStatusGroup(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

  }

  public static final PriorityStatusGroup mapPriorityStatusGroup(int code) {
    if (PriorityStatusGroup.Active.getCode() == code) {
      return PriorityStatusGroup.Active;
    } else if (PriorityStatusGroup.Suspend1.getCode() == code) {
      return PriorityStatusGroup.Suspend1;
    } else if (PriorityStatusGroup.Suspend2.getCode() == code) {
      return PriorityStatusGroup.Suspend2;
    } else if (PriorityStatusGroup.Suspend3.getCode() == code) {
      return PriorityStatusGroup.Suspend3;
    } else if (PriorityStatusGroup.TerminateAndInactive.getCode() == code) {
      return PriorityStatusGroup.TerminateAndInactive;
    } else if (PriorityStatusGroup.Other.getCode() == code) {
      return PriorityStatusGroup.Other;
    }
    return PriorityStatusGroup.NULL;
  }

  public enum PriorityStatusGroupStatus {
    Active(1),
    Suspend(2),
    Disconnect(3),
    Terminate(4),
    Inactive(5),
    Other(9),
    NULL(-9999);

    private final int code;

    private PriorityStatusGroupStatus(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

  }

  public static final PriorityStatusGroupStatus mapPriorityStatusGroupStatus(int code) {
    if (PriorityStatusGroupStatus.Active.getCode() == code) {
      return PriorityStatusGroupStatus.Active;
    } else if (PriorityStatusGroupStatus.Suspend.getCode() == code) {
      return PriorityStatusGroupStatus.Suspend;
    } else if (PriorityStatusGroupStatus.Disconnect.getCode() == code) {
      return PriorityStatusGroupStatus.Disconnect;
    } else if (PriorityStatusGroupStatus.Terminate.getCode() == code) {
      return PriorityStatusGroupStatus.Terminate;
    } else if (PriorityStatusGroupStatus.Inactive.getCode() == code) {
      return PriorityStatusGroupStatus.Inactive;
    } else if (PriorityStatusGroupStatus.Other.getCode() == code) {
      return PriorityStatusGroupStatus.Other;
    }
    return PriorityStatusGroupStatus.NULL;
  }

  public enum ActionPeriodType {
    Day(1), Month(2), NULL(-9999);
    private final int code;

    private ActionPeriodType(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

    public Date calculate(Date d, BigDecimal val) {
      try {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        switch (code) {
          case 1: {
            c.add(Calendar.DATE, val.intValue());
            return c.getTime();
          }
          case 2: {
            c.add(Calendar.MONTH, val.intValue());
            if (c.get(Calendar.DATE) == now.get(Calendar.DATE)) {
              c.add(Calendar.DATE, -1);
            }
            return c.getTime();
          }
        }
      } catch (Exception ex) {

      }
      return null;
    }

  }

  public static final ActionPeriodType mapActionPeriodType(BigDecimal code) {
    if (code == null) {
      return ActionPeriodType.NULL;
    }

    if (ActionPeriodType.Day.getCode() == code.intValue()) {
      return ActionPeriodType.Day;
    } else if (ActionPeriodType.Month.getCode() == code.intValue()) {
      return ActionPeriodType.Month;
    }
    return ActionPeriodType.NULL;
  }

  public enum CalendarType {
    /*
      System Maintenance
      วันหยุดนักขัตฤกษ์
      วันหยุดเสาร์อาทิตย์
      วันศุกร์
      ช่วงเทศกาล
      ช่วงเหตุภัยพิบัติ
      ช่วงเหตุการณ์สำคัญอื่นๆ
     */
    SystemMaintenance(1),
    Holiday(2),
    Weekend(3),
    Friday(4),
    Festival(5),
    Disaster(6),
    OtherEvents(7),
    NULL(-9999);
    private final int code;

    private CalendarType(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }
  }

  public static final CalendarType mapCalendarType(int code) {
    if (CalendarType.SystemMaintenance.getCode() == code) {
      return CalendarType.SystemMaintenance;
    } else if (CalendarType.Holiday.getCode() == code) {
      return CalendarType.Holiday;
    } else if (CalendarType.Weekend.getCode() == code) {
      return CalendarType.Weekend;
    } else if (CalendarType.Friday.getCode() == code) {
      return CalendarType.Friday;
    } else if (CalendarType.Festival.getCode() == code) {
      return CalendarType.Festival;
    } else if (CalendarType.Disaster.getCode() == code) {
      return CalendarType.Disaster;
    } else if (CalendarType.OtherEvents.getCode() == code) {
      return CalendarType.OtherEvents;
    }
    return CalendarType.NULL;
  }

  public enum LogCriteriaOption {
    VerifyOnly(1),
    VerifyAndRun(2),
    NULL(-9999);
    private final int code;

    private LogCriteriaOption(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

  }

  public static final LogCriteriaOption mapLogCriteriaOption(int code) {
    if (LogCriteriaOption.VerifyOnly.getCode() == code) {
      return LogCriteriaOption.VerifyOnly;
    } else if (LogCriteriaOption.VerifyAndRun.getCode() == code) {
      return LogCriteriaOption.VerifyAndRun;
    }
    return LogCriteriaOption.NULL;
  }

  public enum LogCriteriaStatus {
    /*      
    Processing
    รอตรวจสอบ
    ตรวจสอบแล้ว เก็บข้อมูลไว้ก่อน
    ตรวจสอบแล้ว รอ Purge ข้อมูล
     */

    Processing(1),
    WaitVerify(2),
    VerifyAndKeep(3),
    VerifyAndPurge(4),
    NULL(-9999);
    private final int code;

    private LogCriteriaStatus(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

  }

  public static final LogCriteriaStatus mapLogCriteriaStatus(int code) {
    if (LogCriteriaStatus.Processing.getCode() == code) {
      return LogCriteriaStatus.Processing;
    } else if (LogCriteriaStatus.WaitVerify.getCode() == code) {
      return LogCriteriaStatus.WaitVerify;
    } else if (LogCriteriaStatus.VerifyAndKeep.getCode() == code) {
      return LogCriteriaStatus.VerifyAndKeep;
    } else if (LogCriteriaStatus.VerifyAndPurge.getCode() == code) {
      return LogCriteriaStatus.VerifyAndPurge;
    }
    return LogCriteriaStatus.NULL;
  }

  public enum LogCriteriaType {
    Path(1),
    Action(2),
    NULL(-9999);
    private final int code;

    private LogCriteriaType(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

  }

  public static final LogCriteriaType mapLogCriteriaType(int code) {
    if (LogCriteriaType.Path.getCode() == code) {
      return LogCriteriaType.Path;
    } else if (LogCriteriaType.Action.getCode() == code) {
      return LogCriteriaType.Action;
    }
    return LogCriteriaType.NULL;
  }

  public enum CriteriaLevel {
    CA(1),
    SA(2),
    BA(3),
    NULL(-9999);
    private final int code;

    private CriteriaLevel(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

    public boolean isCALevel() {
      return code == CA.getCode();
    }

    public boolean isSALevel() {
      return code == SA.getCode();
    }

    public boolean isBALevel() {
      return code == BA.getCode();
    }

  }

  public static final CriteriaLevel mapCriteriaLevel(int code) {
    if (CriteriaLevel.CA.getCode() == code) {
      return CriteriaLevel.CA;
    } else if (CriteriaLevel.BA.getCode() == code) {
      return CriteriaLevel.BA;
    }
    return CriteriaLevel.NULL;
  }

  public enum ContactLevel {
    CA(1),
    SA(3),
    BA(2),
    NULL(-9999);
    private final int code;

    private ContactLevel(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

    public boolean isCALevel() {
      return code == CA.getCode();
    }

    public boolean isBALevel() {
      return code == BA.getCode();
    }

  }

  public static final ContactLevel mapContactLevel(BigDecimal code) {
    if (code == null) {
      return ContactLevel.NULL;
    }
    return mapContactLevel(code.intValue());
  }

  public static final ContactLevel mapContactLevel(int code) {
    if (ContactLevel.CA.getCode() == code) {
      return ContactLevel.CA;
    } else if (ContactLevel.BA.getCode() == code) {
      return ContactLevel.BA;
    } else if (ContactLevel.SA.getCode() == code) {
      return ContactLevel.SA;
    }
    return ContactLevel.NULL;
  }

  public enum TemplateLevel {
    NA(0),
    CA(1),
    SA(2),
    BA(3);
    private final int code;

    private TemplateLevel(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

    public boolean isCALevel() {
      return code == CA.getCode();
    }

    public boolean isBALevel() {
      return code == BA.getCode();
    }

  }

  public static final TemplateLevel mapTemplateLevel(BigDecimal code) {
    if (code == null) {
      return TemplateLevel.NA;
    }
    return mapTemplateLevel(code.intValue());
  }

  public static final TemplateLevel mapTemplateLevel(int code) {
    if (TemplateLevel.CA.getCode() == code) {
      return TemplateLevel.CA;
    } else if (TemplateLevel.BA.getCode() == code) {
      return TemplateLevel.BA;
    } else if (TemplateLevel.SA.getCode() == code) {
      return TemplateLevel.SA;
    }
    return TemplateLevel.NA;
  }

  public enum TemplateType {
    SMS(1),
    EMAIL(2),
    LETTER(3),
    NULL(-9999);
    private final int code;

    private TemplateType(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

    public boolean isTemplateTypeSMS() {
      return code == 1;
    }

    public boolean isTemplateTypeEMAIL() {
      return code == 2;
    }

    public boolean isTemplateTypeLETTER() {
      return code == 3;
    }

    public String getKeys() {
      switch (code) {
        case 1:
          return "SMS";
        case 2:
          return "EMAIL";
        case 3:
          return "LETTER";
        default:
          break;
      }
      return "xxxxxxxxxxxxxx";
    }
  }

  public static final TemplateType mapTemplateType(BigDecimal code) {
    if (code == null) {
      return TemplateType.NULL;
    }
    return mapTemplateType(code.intValue());
  }

  public static final TemplateType mapTemplateType(int code) {
    if (TemplateType.SMS.getCode() == code) {
      return TemplateType.SMS;
    } else if (TemplateType.EMAIL.getCode() == code) {
      return TemplateType.EMAIL;
    } else if (TemplateType.LETTER.getCode() == code) {
      return TemplateType.LETTER;
    }
    return TemplateType.NULL;
  }

  public enum DataOwner {
    NA(0),
    Corporate(1),
    Residential(2);

    private final int code;

    private DataOwner(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

    public boolean isCorporate() {
      return code == Corporate.getCode();
    }

    public boolean isResidential() {
      return code == Residential.getCode();
    }
  }

  public static final DataOwner mapDataOwner(BigDecimal code) {
    if (code == null) {
      return DataOwner.NA;
    }
    return mapDataOwner(code.intValue());
  }

  public static final DataOwner mapDataOwner(int code) {
    if (DataOwner.Corporate.getCode() == code) {
      return DataOwner.Corporate;
    } else if (DataOwner.Residential.getCode() == code) {
      return DataOwner.Residential;
    }
    return DataOwner.NA;
  }

  public enum LetterType {

    RemindLetter(1),
    WarningLetter(2),
    TerminateLetter(3),
    NoticeLetter(4),
    InHouseLetter(5),
    NULL(-9999);

    private final int code;

    private LetterType(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }
    private static final String string_code[] = {"NULL", "RL", "WL", "TL", "NL", "IHL"};

    public String getStringCode() {
      if (code > 0 && code < 6) {
        return string_code[code];
      }
      return string_code[0];
    }

    public String getStringCodeCorporate() {
      String temp = getStringCode();
      if ("NULL".equals(temp)) {
        return temp;
      }
      return temp + "C";
    }

  }

  public static final LetterType mapLetterType(BigDecimal code) {
    if (code == null) {
      return LetterType.NULL;
    }
    return mapLetterType(code.intValue());
  }

  public static final LetterType mapLetterType(int code) {
    if (LetterType.RemindLetter.getCode() == code) {
      return LetterType.RemindLetter;
    } else if (LetterType.WarningLetter.getCode() == code) {
      return LetterType.WarningLetter;
    } else if (LetterType.TerminateLetter.getCode() == code) {
      return LetterType.TerminateLetter;
    } else if (LetterType.NoticeLetter.getCode() == code) {
      return LetterType.NoticeLetter;
    } else if (LetterType.InHouseLetter.getCode() == code) {
      return LetterType.InHouseLetter;
    }
    return LetterType.NULL;
  }

  public enum LetterPrintType {
    ManualPrinting(1),
    PrintingHouseIncludingEnvelopes(2),
    PrintingHouseExcludingEnvelopes(3),
    NULL(-9999);
    private final int code;

    private LetterPrintType(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

  }

  public static final LetterPrintType mapLetterPrintType(BigDecimal code) {
    if (code == null) {
      return LetterPrintType.NULL;
    }
    return mapLetterPrintType(code.intValue());
  }

  public static final LetterPrintType mapLetterPrintType(int code) {
    if (LetterPrintType.ManualPrinting.getCode() == code) {
      return LetterPrintType.ManualPrinting;
    } else if (LetterPrintType.PrintingHouseIncludingEnvelopes.getCode() == code) {
      return LetterPrintType.PrintingHouseIncludingEnvelopes;
    } else if (LetterPrintType.PrintingHouseExcludingEnvelopes.getCode() == code) {
      return LetterPrintType.PrintingHouseExcludingEnvelopes;
    }
    return LetterPrintType.NULL;
  }

  public enum SenderType {
    SMS_EMAIL_DUE_CUSTOMER(1),
    SMS_EMAIL_NOTIFY_ADMIN(2),
    NULL(-9999);
    private final int code;

    private SenderType(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }
  }

  public static final SenderType mapSenderType(int code) {
    if (SenderType.SMS_EMAIL_DUE_CUSTOMER.getCode() == code) {
      return SenderType.SMS_EMAIL_DUE_CUSTOMER;
    } else if (SenderType.SMS_EMAIL_NOTIFY_ADMIN.getCode() == code) {
      return SenderType.SMS_EMAIL_NOTIFY_ADMIN;
    }
    return SenderType.NULL;
  }

  public enum BaStatusGroup {
    Active(1),
    Inactive(0),
    Other(9);
    private final int code;

    private BaStatusGroup(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }
  }

  public static final BaStatusGroup mapBaStatusGroup(int code) {
    if (BaStatusGroup.Active.getCode() == code) {
      return BaStatusGroup.Active;
    } else if (BaStatusGroup.Inactive.getCode() == code) {
      return BaStatusGroup.Inactive;
    }
    return BaStatusGroup.Other;
  }

  public enum ContactOptionSMS {
    Contact_Number(5),
    Mobile_Number(4),
    NULL(-9999);
    private final int code;

    private ContactOptionSMS(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

    public boolean isContact() {
      switch (code) {
        case 1001: {
          return true;
        }
      }
      return false;
    }
  }

  public static final ContactOptionSMS mapContactOptionSMS(int code) {
    if (ContactOptionSMS.Contact_Number.getCode() == code) {
      return ContactOptionSMS.Contact_Number;
    } else if (ContactOptionSMS.Mobile_Number.getCode() == code) {
      return ContactOptionSMS.Mobile_Number;
    }
    return ContactOptionSMS.NULL;
  }

  public enum ParameterTemplateSMS {

    MOBILE_NUM(10101001),
    BALANCE_MNY(10101002),
    CRM_DUE_DAT(10101003),
    BILL_DATE(10101004),
    SUSPEND_DATE(10101005),
    BILL_NUM(10101006),
    COUNT_INVOICE(10101007),
    BILLING_CYCLE(10101008),
    INVOICE_MONTH(10101009),
    INVOICE_AMOUNT(10101010),
    NULL(-9999);
    private final int code;

    private ParameterTemplateSMS(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

  }

  public static final ParameterTemplateSMS mapParameterTemplateSMS(BigDecimal code) {
    if (code == null) {
      return ParameterTemplateSMS.NULL;
    }
    return mapParameterTemplateSMS(code.intValue());
  }

  public static final ParameterTemplateSMS mapParameterTemplateSMS(int code) {
    if (ParameterTemplateSMS.MOBILE_NUM.getCode() == code) {
      return ParameterTemplateSMS.MOBILE_NUM;
    } else if (ParameterTemplateSMS.BALANCE_MNY.getCode() == code) {
      return ParameterTemplateSMS.BALANCE_MNY;
    } else if (ParameterTemplateSMS.CRM_DUE_DAT.getCode() == code) {
      return ParameterTemplateSMS.CRM_DUE_DAT;
    } else if (ParameterTemplateSMS.BILL_DATE.getCode() == code) {
      return ParameterTemplateSMS.BILL_DATE;
    } else if (ParameterTemplateSMS.SUSPEND_DATE.getCode() == code) {
      return ParameterTemplateSMS.SUSPEND_DATE;
    } else if (ParameterTemplateSMS.BILL_NUM.getCode() == code) {
      return ParameterTemplateSMS.BILL_NUM;
    } else if (ParameterTemplateSMS.COUNT_INVOICE.getCode() == code) {
      return ParameterTemplateSMS.COUNT_INVOICE;
    } else if (ParameterTemplateSMS.BILLING_CYCLE.getCode() == code) {
      return ParameterTemplateSMS.BILLING_CYCLE;
    } else if (ParameterTemplateSMS.INVOICE_MONTH.getCode() == code) {
      return ParameterTemplateSMS.INVOICE_MONTH;
    } else if (ParameterTemplateSMS.INVOICE_AMOUNT.getCode() == code) {
      return ParameterTemplateSMS.INVOICE_AMOUNT;
    }
    return ParameterTemplateSMS.NULL;
  }

  public enum ContactOptionLetter {
    //Home_Address(1001),
    VAT_Address(1),
    Billing_Address(2),
    Contact_Address(3),
    NULL(-9999);
    private final int code;

    private ContactOptionLetter(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

  }

  public static final ContactOptionLetter mapContactOptionLetter(int code) {
    //if (ContactOptionLetter.Home_Address.getCode() == code) {
    //return ContactOptionLetter.Home_Address;
    //} else 

    if (ContactOptionLetter.VAT_Address.getCode() == code) {
      return ContactOptionLetter.VAT_Address;
    } else if (ContactOptionLetter.Billing_Address.getCode() == code) {
      return ContactOptionLetter.Billing_Address;
    } else if (ContactOptionLetter.Billing_Address.getCode() == code) {
      return ContactOptionLetter.Billing_Address;
    }
    return ContactOptionLetter.NULL;
  }

  public enum ParameterTemplateLetter {
    ADDRESS1(10501001),
    ADDRESS2(10501002),
    BARCODE(10501003),
    BARCODE_AR(10501004),
    BILLING_ACC_NAME(10501005),
    BILLING_ACC_NUM(10501006),
    COMPANY_NAME(10501007),
    CONTACT_NAME(10501008),
    CONTACT_NUMBER_DESC(10501009),
    COST_MNY(10501010),
    GEN_SEQ(10501011),
    GRAND_TOTAL_BALANCE(10501012),
    LAST_AR_DAT(10501013),
    LETTER_DAT(10501014),
    LETTER_DAT_REF(10501015),
    MOBILE_NUM(10501016),
    MOBILE_NUM_LIST(10501017),
    MODE_ASSIGN(10501018),
    MODE_ASSIGN_REF(10501019),
    NEW_BILLING_ACC_NUM(10501020),
    OWNER_NAME(10501021),
    PARTY_DESC(10501022),
    PARTY_TELEPHONE(10501023),
    PAYMENT_DUE_DAT(10501024),
    PROVINCE_CODE(10501025),
    REGISTER_DAT(10501026),
    SERVICE_NAME(10501027),
    STATUS_DAT(10501028),
    TERMINATE_MONTH(10501029),
    TOTAL_BALANCE(10501030),
    ZIP_CODE(10501031),
    NULL(-9999);
    private final int code;

    private ParameterTemplateLetter(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

  }

  public static final ParameterTemplateLetter mapParameterTemplateLetter(BigDecimal code) {
    if (code == null) {
      return ParameterTemplateLetter.NULL;
    }
    return mapParameterTemplateLetter(code.intValue());
  }

  public static final ParameterTemplateLetter mapParameterTemplateLetter(int code) {
    if (ParameterTemplateLetter.ADDRESS1.getCode() == code) {
      return ParameterTemplateLetter.ADDRESS1;
    } else if (ParameterTemplateLetter.ADDRESS2.getCode() == code) {
      return ParameterTemplateLetter.ADDRESS2;
    } else if (ParameterTemplateLetter.BARCODE.getCode() == code) {
      return ParameterTemplateLetter.BARCODE;
    } else if (ParameterTemplateLetter.BARCODE_AR.getCode() == code) {
      return ParameterTemplateLetter.BARCODE_AR;
    } else if (ParameterTemplateLetter.BILLING_ACC_NAME.getCode() == code) {
      return ParameterTemplateLetter.BILLING_ACC_NAME;
    } else if (ParameterTemplateLetter.BILLING_ACC_NUM.getCode() == code) {
      return ParameterTemplateLetter.BILLING_ACC_NUM;
    } else if (ParameterTemplateLetter.COMPANY_NAME.getCode() == code) {
      return ParameterTemplateLetter.COMPANY_NAME;
    } else if (ParameterTemplateLetter.CONTACT_NAME.getCode() == code) {
      return ParameterTemplateLetter.CONTACT_NAME;
    } else if (ParameterTemplateLetter.CONTACT_NUMBER_DESC.getCode() == code) {
      return ParameterTemplateLetter.CONTACT_NUMBER_DESC;
    } else if (ParameterTemplateLetter.COST_MNY.getCode() == code) {
      return ParameterTemplateLetter.COST_MNY;
    } else if (ParameterTemplateLetter.GEN_SEQ.getCode() == code) {
      return ParameterTemplateLetter.GEN_SEQ;
    } else if (ParameterTemplateLetter.GRAND_TOTAL_BALANCE.getCode() == code) {
      return ParameterTemplateLetter.GRAND_TOTAL_BALANCE;
    } else if (ParameterTemplateLetter.LAST_AR_DAT.getCode() == code) {
      return ParameterTemplateLetter.LAST_AR_DAT;
    } else if (ParameterTemplateLetter.LETTER_DAT.getCode() == code) {
      return ParameterTemplateLetter.LETTER_DAT;
    } else if (ParameterTemplateLetter.LETTER_DAT_REF.getCode() == code) {
      return ParameterTemplateLetter.LETTER_DAT_REF;
    } else if (ParameterTemplateLetter.MOBILE_NUM.getCode() == code) {
      return ParameterTemplateLetter.MOBILE_NUM;
    } else if (ParameterTemplateLetter.MOBILE_NUM_LIST.getCode() == code) {
      return ParameterTemplateLetter.MOBILE_NUM_LIST;
    } else if (ParameterTemplateLetter.MODE_ASSIGN.getCode() == code) {
      return ParameterTemplateLetter.MODE_ASSIGN;
    } else if (ParameterTemplateLetter.MODE_ASSIGN_REF.getCode() == code) {
      return ParameterTemplateLetter.MODE_ASSIGN_REF;
    } else if (ParameterTemplateLetter.NEW_BILLING_ACC_NUM.getCode() == code) {
      return ParameterTemplateLetter.NEW_BILLING_ACC_NUM;
    } else if (ParameterTemplateLetter.OWNER_NAME.getCode() == code) {
      return ParameterTemplateLetter.OWNER_NAME;
    } else if (ParameterTemplateLetter.PARTY_DESC.getCode() == code) {
      return ParameterTemplateLetter.PARTY_DESC;
    } else if (ParameterTemplateLetter.PARTY_TELEPHONE.getCode() == code) {
      return ParameterTemplateLetter.PARTY_TELEPHONE;
    } else if (ParameterTemplateLetter.PAYMENT_DUE_DAT.getCode() == code) {
      return ParameterTemplateLetter.PAYMENT_DUE_DAT;
    } else if (ParameterTemplateLetter.PROVINCE_CODE.getCode() == code) {
      return ParameterTemplateLetter.PROVINCE_CODE;
    } else if (ParameterTemplateLetter.REGISTER_DAT.getCode() == code) {
      return ParameterTemplateLetter.REGISTER_DAT;
    } else if (ParameterTemplateLetter.SERVICE_NAME.getCode() == code) {
      return ParameterTemplateLetter.SERVICE_NAME;
    } else if (ParameterTemplateLetter.STATUS_DAT.getCode() == code) {
      return ParameterTemplateLetter.STATUS_DAT;
    } else if (ParameterTemplateLetter.TERMINATE_MONTH.getCode() == code) {
      return ParameterTemplateLetter.TERMINATE_MONTH;
    } else if (ParameterTemplateLetter.TOTAL_BALANCE.getCode() == code) {
      return ParameterTemplateLetter.TOTAL_BALANCE;
    } else if (ParameterTemplateLetter.ZIP_CODE.getCode() == code) {
      return ParameterTemplateLetter.ZIP_CODE;
    }
    return ParameterTemplateLetter.NULL;
  }

  public enum ContactOptionEMail {
    Contact_EMail(1001),
    NULL(-9999);
    private final int code;

    private ContactOptionEMail(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

    public boolean isContact() {
      switch (code) {
        case 1001: {
          return true;
        }
      }
      return false;
    }
  }

  public static final ContactOptionEMail mapContactOptionEMail(int code) {
    if (ContactOptionEMail.Contact_EMail.getCode() == code) {
      return ContactOptionEMail.Contact_EMail;
    }
    return ContactOptionEMail.NULL;
  }

  public enum ParameterTemplateEMail {

    MOBILE_NUM(10010001),
    BALANCE_MNY(10010002),
    CRM_DUE_DAT(10010003),
    BILL_DATE(10010004),
    SUSPEND_DATE(10010005),
    BILL_NUM(10010006),
    COUNT_INVOICE(10010007),
    BILLING_CYCLE(10010008),
    INVOICE_MONTH(10010009),
    INVOICE_AMOUNT(10010010),
    NULL(-9999);
    private final int code;

    private ParameterTemplateEMail(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

  }

  public static final ParameterTemplateEMail mapParameterTemplateEMail(int code) {
    if (ParameterTemplateEMail.MOBILE_NUM.getCode() == code) {
      return ParameterTemplateEMail.MOBILE_NUM;
    } else if (ParameterTemplateEMail.BALANCE_MNY.getCode() == code) {
      return ParameterTemplateEMail.BALANCE_MNY;
    } else if (ParameterTemplateEMail.CRM_DUE_DAT.getCode() == code) {
      return ParameterTemplateEMail.CRM_DUE_DAT;
    } else if (ParameterTemplateEMail.BILL_DATE.getCode() == code) {
      return ParameterTemplateEMail.BILL_DATE;
    } else if (ParameterTemplateEMail.SUSPEND_DATE.getCode() == code) {
      return ParameterTemplateEMail.SUSPEND_DATE;
    } else if (ParameterTemplateEMail.BILL_NUM.getCode() == code) {
      return ParameterTemplateEMail.BILL_NUM;
    } else if (ParameterTemplateEMail.COUNT_INVOICE.getCode() == code) {
      return ParameterTemplateEMail.COUNT_INVOICE;
    } else if (ParameterTemplateEMail.BILLING_CYCLE.getCode() == code) {
      return ParameterTemplateEMail.BILLING_CYCLE;
    } else if (ParameterTemplateEMail.INVOICE_MONTH.getCode() == code) {
      return ParameterTemplateEMail.INVOICE_MONTH;
    } else if (ParameterTemplateEMail.INVOICE_AMOUNT.getCode() == code) {
      return ParameterTemplateEMail.INVOICE_AMOUNT;
    }
    return ParameterTemplateEMail.NULL;
  }

  public enum RecordStatus {
    AVALIABLE(1),
    DISABLE(0);
    private final int code;

    private RecordStatus(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }
  }

  public static final RecordStatus mapRecordStatus(int code) {
    if (RecordStatus.AVALIABLE.getCode() == code) {
      return RecordStatus.AVALIABLE;
    }
    return RecordStatus.DISABLE;
  }

  public enum BatchGroup {
    ActivityLog(1),
    SMS(2),
    Email(3),
    Letter(4),
    PreSDFile(5),
    CampaignDebtFile(6),
    Suspend(7),
    Terminate(8),
    Reconnect(9),
    Blacklist(10),
    DeBlacklist(11),
    WaiveBatch(12),
    WriteOff(13),
    Recovery(14),
    Commission(15),
    Exempt(16),
    Unknow(-9999);
    private final int code;

    private BatchGroup(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }
  }

  public static final BatchGroup mapBatchGroup(int code) {
    if (BatchGroup.ActivityLog.getCode() == code) {
      return BatchGroup.ActivityLog;
    } else if (BatchGroup.SMS.getCode() == code) {
      return BatchGroup.SMS;
    } else if (BatchGroup.Email.getCode() == code) {
      return BatchGroup.Email;
    } else if (BatchGroup.Letter.getCode() == code) {
      return BatchGroup.Letter;
    } else if (BatchGroup.PreSDFile.getCode() == code) {
      return BatchGroup.PreSDFile;
    } else if (BatchGroup.CampaignDebtFile.getCode() == code) {
      return BatchGroup.CampaignDebtFile;
    } else if (BatchGroup.Suspend.getCode() == code) {
      return BatchGroup.Suspend;
    } else if (BatchGroup.Terminate.getCode() == code) {
      return BatchGroup.Terminate;
    } else if (BatchGroup.Reconnect.getCode() == code) {
      return BatchGroup.Reconnect;
    } else if (BatchGroup.Blacklist.getCode() == code) {
      return BatchGroup.Blacklist;
    } else if (BatchGroup.DeBlacklist.getCode() == code) {
      return BatchGroup.DeBlacklist;
    } else if (BatchGroup.WaiveBatch.getCode() == code) {
      return BatchGroup.WaiveBatch;
    } else if (BatchGroup.WriteOff.getCode() == code) {
      return BatchGroup.WriteOff;
    } else if (BatchGroup.Recovery.getCode() == code) {
      return BatchGroup.Recovery;
    } else if (BatchGroup.Commission.getCode() == code) {
      return BatchGroup.Commission;
    } else if (BatchGroup.Exempt.getCode() == code) {
      return BatchGroup.Exempt;
    }
    return BatchGroup.Unknow;
  }

  public enum Environment {
    PROD(1),
    DEV(2),
    SIT(3),
    UnKnow(-9999);
    private final int code;

    private Environment(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }
  }

  public static final Environment mapEnvironment(int code) {
    if (Environment.PROD.getCode() == code) {
      return Environment.PROD;
    } else if (Environment.DEV.getCode() == code) {
      return Environment.DEV;
    } else if (Environment.SIT.getCode() == code) {
      return Environment.SIT;
    }
    return Environment.UnKnow;
  }

  public enum OutboundStatus {
    NoOutboundResponse(0),
    Generating(1),
    Complete(2),
    Failed(3),
    Cancelled(4),
    UnKnow(-9999);
    private final int code;

    private OutboundStatus(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }
  }

  public static final OutboundStatus mapOutboundStatus(int code) {
    if (OutboundStatus.NoOutboundResponse.getCode() == code) {
      return OutboundStatus.NoOutboundResponse;
    } else if (OutboundStatus.Generating.getCode() == code) {
      return OutboundStatus.Generating;
    } else if (OutboundStatus.Complete.getCode() == code) {
      return OutboundStatus.Complete;
    } else if (OutboundStatus.Failed.getCode() == code) {
      return OutboundStatus.Failed;
    } else if (OutboundStatus.Cancelled.getCode() == code) {
      return OutboundStatus.Cancelled;
    }
    return OutboundStatus.UnKnow;
  }

  public enum InboundStatus {
    NoInboundResponse(0),
    Pending(1),
    Received(2),
    Complete(3),
    UnKnow(-9999);
    private final int code;

    private InboundStatus(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }
  }

  public static final InboundStatus mapInboundStatus(int code) {
    if (InboundStatus.NoInboundResponse.getCode() == code) {
      return InboundStatus.NoInboundResponse;
    } else if (InboundStatus.Pending.getCode() == code) {
      return InboundStatus.Pending;
    } else if (InboundStatus.Received.getCode() == code) {
      return InboundStatus.Received;
    } else if (InboundStatus.Complete.getCode() == code) {
      return InboundStatus.Complete;
    }
    return InboundStatus.UnKnow;
  }

  public enum ParameterTemplateSuspendAndTerminate {
    RecordType(19101001),
    MobileNumber(19101002),
    OrderType(19101003),
    SuspendType(19101004),
    ActionReason(19101005),
    ReconnectionDate(19101006),
    NetworkType(19101007),
    NULL(-9999);
    private final int code;

    private ParameterTemplateSuspendAndTerminate(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

  }

  public static final ParameterTemplateSuspendAndTerminate mapParameterTemplateSuspendAndTerminate(BigDecimal codeBigDecimal) {
    int code = codeBigDecimal.intValue();
    if (ParameterTemplateSuspendAndTerminate.RecordType.getCode() == code) {
      return ParameterTemplateSuspendAndTerminate.RecordType;
    } else if (ParameterTemplateSuspendAndTerminate.MobileNumber.getCode() == code) {
      return ParameterTemplateSuspendAndTerminate.MobileNumber;
    } else if (ParameterTemplateSuspendAndTerminate.OrderType.getCode() == code) {
      return ParameterTemplateSuspendAndTerminate.OrderType;
    } else if (ParameterTemplateSuspendAndTerminate.SuspendType.getCode() == code) {
      return ParameterTemplateSuspendAndTerminate.SuspendType;
    } else if (ParameterTemplateSuspendAndTerminate.ActionReason.getCode() == code) {
      return ParameterTemplateSuspendAndTerminate.ActionReason;
    } else if (ParameterTemplateSuspendAndTerminate.ReconnectionDate.getCode() == code) {
      return ParameterTemplateSuspendAndTerminate.ReconnectionDate;
    } else if (ParameterTemplateSuspendAndTerminate.NetworkType.getCode() == code) {
      return ParameterTemplateSuspendAndTerminate.NetworkType;
    }
    return ParameterTemplateSuspendAndTerminate.NULL;
  }

  public enum BlackListOption {
    NoAction(0),
    Blacklist(1),
    DeBlacklist(2);
    private final int code;

    private BlackListOption(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

  }

  public static final BlackListOption mapBlackListOption(BigDecimal codeBigDecimal) {
    int code = codeBigDecimal.intValue();
    if (BlackListOption.Blacklist.getCode() == code) {
      return BlackListOption.Blacklist;
    } else if (BlackListOption.DeBlacklist.getCode() == code) {
      return BlackListOption.DeBlacklist;
    }
    return BlackListOption.NoAction;
  }

  public enum ParameterTemplateBlacklist {
    RecordType(19102001),
    CANumber(19102002),
    BANumber(19102003),
    FRMobileNumber(19102004),
    BlacklistDateTime(19102005),
    BlacklistType(19102006),
    BlacklistSubType(19102007),
    Source(19102008),
    DLFlag(19102009),
    DLReason(19102010),
    BlacklistEndDate(19102011),
    Remark(19102012),
    DMSCount(19102013),
    Reason(19102014),
    NULL(-9999);
    private final int code;

    private ParameterTemplateBlacklist(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

  }

  public static final ParameterTemplateBlacklist mapParameterTemplateBlacklist(BigDecimal codeBigDecimal) {
    int code = codeBigDecimal.intValue();
    if (ParameterTemplateBlacklist.RecordType.getCode() == code) {
      return ParameterTemplateBlacklist.RecordType;
    } else if (ParameterTemplateBlacklist.CANumber.getCode() == code) {
      return ParameterTemplateBlacklist.CANumber;
    } else if (ParameterTemplateBlacklist.BANumber.getCode() == code) {
      return ParameterTemplateBlacklist.BANumber;
    } else if (ParameterTemplateBlacklist.FRMobileNumber.getCode() == code) {
      return ParameterTemplateBlacklist.FRMobileNumber;
    } else if (ParameterTemplateBlacklist.BlacklistDateTime.getCode() == code) {
      return ParameterTemplateBlacklist.BlacklistDateTime;
    } else if (ParameterTemplateBlacklist.BlacklistType.getCode() == code) {
      return ParameterTemplateBlacklist.BlacklistType;
    } else if (ParameterTemplateBlacklist.BlacklistSubType.getCode() == code) {
      return ParameterTemplateBlacklist.BlacklistSubType;
    } else if (ParameterTemplateBlacklist.Source.getCode() == code) {
      return ParameterTemplateBlacklist.Source;
    } else if (ParameterTemplateBlacklist.DLFlag.getCode() == code) {
      return ParameterTemplateBlacklist.DLFlag;
    } else if (ParameterTemplateBlacklist.DLReason.getCode() == code) {
      return ParameterTemplateBlacklist.DLReason;
    } else if (ParameterTemplateBlacklist.BlacklistEndDate.getCode() == code) {
      return ParameterTemplateBlacklist.BlacklistEndDate;
    } else if (ParameterTemplateBlacklist.Remark.getCode() == code) {
      return ParameterTemplateBlacklist.Remark;
    } else if (ParameterTemplateBlacklist.DMSCount.getCode() == code) {
      return ParameterTemplateBlacklist.DMSCount;
    } else if (ParameterTemplateBlacklist.Reason.getCode() == code) {
      return ParameterTemplateBlacklist.Reason;

    }
    return ParameterTemplateBlacklist.NULL;
  }

  public static void main(String[] args) throws ParseException {

    for (int i = 0; i < 100001; i++) {
      System.out.println(buildSequence());
    }

//    ActionStatus mode = mapActionStatus(10);
//    switch (mode) {
//      case Complete: {
//        break;
//      }
//      case InProgress: {
//        break;
//      }
//    }
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    Date dd = sdf.parse("2016-02-01");
//
//    System.out.println(ActionPeriodType.Month.calculate(dd, BigDecimal.ONE));
  }

}
//<editor-fold defaultstate="collapsed" desc="comment">
/*
  public enum ContactOptionSMS {
  CA_Contact_Number(1001),
  CA_X_Number(1002),
  CA_All_Number(1003),
  SA_Contact_Number(1004),
  SA_X_Number(1005),
  SA_All_Number(1006),
  BA_Contact_Number(1007),
  BA_X_Number(1008),
  BA_All_Number(1009),
  CA_Contact_Email(1010),
  CA_X_Email(1011),
  CA_All_Email(1012),
  SA_Contact_Email(1013),
  SA_X_Email(1014),
  SA_All_Email(1015),
  BA_Contact_Email(1016),
  BA_X_Email(1017),
  BA_All_Email(1018),
  NULL(-9999);
  private final int code;
  
  private ContactOptionSMS(int code) {
  this.code = code;
  }
  
  public int getCode() {
  return code;
  }
  
  public boolean isContact() {
  switch (code) {
  case 1001:
  case 1004:
  case 1007:
  case 1010:
  case 1013:
  case 1016: {
  return true;
  }
  }
  return false;
  }
  
  public boolean isCALevel() {
  switch (code) {
  case 1001:
  case 1002:
  case 1003:
  case 1010:
  case 1011:
  case 1012: {
  return true;
  }
  }
  return false;
  }
  
  public boolean isSALevel() {
  switch (code) {
  case 1004:
  case 1005:
  case 1006:
  case 1013:
  case 1014:
  case 1015: {
  return true;
  }
  }
  return false;
  }
  
  public boolean isBALevel() {
  switch (code) {
  case 1007:
  case 1008:
  case 1009:
  case 1016:
  case 1017:
  case 1018: {
  return true;
  }
  }
  return false;
  }
  }
 */
//</editor-fold>

