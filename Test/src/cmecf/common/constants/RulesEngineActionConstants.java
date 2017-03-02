// Reviewer: tliu
package cmecf.common.constants;

public class RulesEngineActionConstants {

  // should be treated as case-insensitive
  // action part validation code list for JRP
  public static final String JRP_DOCUMENT_FILE_CONTROL_ID_FOR_FILES = "JRPacketDocumentFileControlIDForFiles";
  public static final String JRP_DOCUMENT_FILE_CONTROL_ID_FOR_NOTES = "JRPacketDocumentFileControlIDForNotes";
  public static final String JRP_DUE_DATE_OFFSET_DAYS = "JRPacketDueDateOffsetDays";
  public static final String JRP_DUE_DATE_OFFSET_WEEKS = "JRPacketDueDateOffsetWeeks";
  public static final String JRP_PACKET_ID = "JRPacketID";
  public static final String JRP_PERMISSION_DEFINITION = "JRPacketPermissionDefinition";
  public static final String JRP_PRIORITY = "JRPacketPriority";
  public static final String JRP_RECIPIENT_ID = "JRPacketRecipientReferenceRepresentation";
  public static final String JRP_STATUS = "JRPacketStatus";
  public static final String JRP_ADDITIONALFILING_PRIORITY = "JRPacketPriorityAdditionalFiling";
  public static final String JRP_ADDITIONALFILING_STATUS = "JRPacketStatusAdditionalFiling";
  public static final String JRP_ADDITIONALFILING_ROUTING = "JRPacketRecipientReferenceRespresentationAdditionalFiling";
  public static final String JRP_ADDITIONALFILING_DUE_DATE_OFFSET_DAYS = "JRPacketDueDateOffsetDaysAdditionalFiling";
  public static final String JRP_ADDITIONALFILING_DUE_DATE_OFFSET_WEEKS = "JRPacketDueDateOffsetWeeksAdditionalFiling";
  public static final String JRP_PRIMARY_INDICATOR = "JRPacketPrimaryIndicator";
  public static final String JRP_UNRELATED_EVENTS = "JRPacketUnrelatedEvents";

  // constant JRP component IDs needed by rules engine
  public static final Integer JRP_CONFIG_CHAMBERS_TERMINAL_DIGITS_COMPONENT_ID = 3203;

  // action part validation code list for Alerts
  public static final String ALERT_EMAIL_RECIPIENT_GROUP_ID = "AlertEmailRecipientGroupID";
  public static final String ALERT_EMAIL_RECIPIENT_PRID = "AlertEmailRecipientPrid";
  public static final String ALERT_FROM_USER_PRID = "AlertFromUserPrid";
  public static final String ALERT_PRIORITY = "AlertPriority";
  public static final String ALERT_SOURCE_TYPE = "AlertType";
  public static final String ALERT_TRIGGERING_COMPONENT_ID = "AlertTriggeringComponentID";

  // Alert type description
  public static final String ALERT_TYPE_DESCRIPTION_FOR_CASE_AND_ENTRY = "Case/Entry";
  public static final String ALERT_TYPE_DESCRIPTION_FOR_INACTIVE_CASES = "Inactive Cases";
  public static final String ALERT_TYPE_DESCRIPTION_FOR_JRP = "Judge Review Packet";
  public static final String ALERT_TYPE_DESCRIPTION_FOR_WORKLOAD_DISTRIBUTION = "Workload";

  // Alert action category reference - case sensitive
  public static final String ALERT_ACTION_CATEGORY_REFERENCE = "Alert";

  // JRP action category reference - case sensitive
  public static final String JRP_ACTION_CATEGORY_REFERENCE = "JRP";

  // Workload assignment action category reference - case sensitive
  public static final String WORKLOAD_ASSGN_ACTION_CATEGORY_REFERENCE = "WorkloadAssgn";

  // AP test: citation category reference - case sensitive
  public static final String CITATION_CATEGORY_REFERENCE = "Citation";

  // common action part validations for all tasks
  public static final String ACTION_PART_ACTION_PRIORITY_ORDER = "RuleActionPriorityOrder";
  public static final String ACTION_PART_ACTION_TYPE_ID = "RuleActionPartActionTypeID";
  public static final String ACTION_PART_ACTION_DEFINITION_CODE_PREFIX = "RuleActionPartActionDefinitionCodePrefix";
  public static final String ACTION_PART_DEFINITION_ID = "RuleActionPartDefinitionID";
  public static final String ACTION_PART_FACTOR_DEF_ID = "RuleFactorDefinitionID";
  public static final String ACTION_PART_VALIDATION_ID = "RuleActionPartValidationID";

  // action priority order constants
  public static final Integer ACTION_PRIORITY_ORDER_LOWEST = 0;
  public static final Integer ACTION_PRIORITY_ORDER_DEFAULT = 1;

  // action type list
  // case sensitive

  // for AP citation meta/preview test
  public static final String ACTION_TYPE_CITATION_META = "createCitationMeta";
  public static final String ACTION_TYPE_CITATION_PREVIEW = "previewCitationLink";

  // for Alert
  public static final String ACTION_TYPE_ALERT_SEND_ALERT = "sendAlert";
  public static final String ACTION_TYPE_ALERT_SEND_SCHEDULED_ALERT = "sendScheduledAlert";

  // for Workload assignment
  public static final String ACTION_TYPE_WORKLOAD_ASSGN = "assignWorkload";
  public static final String ACTION_TYPE_WORKLOAD_SCHEDULED_ASSGN = "assignScheduledWorkload";

  // for JRP
  public static final String ACTION_TYPE_JRP_CREATE_PACKET = "createJRPacket";
  public static final String ACTION_TYPE_JRP_ADD_TO_EXIST_PACKET = "addToJRPacket";
  public static final String ACTION_TYPE_JRP_CREATE_SUB_ACTION = "createJRPSubAction";

  // for WorkLoad Distribution
  public static final String WORKLOAD_ASSIGNED_TO_PRIMARY = "wkaAssignedToPrimary"; // workLoadGroupAssigned
  public static final String WORKLOAD_ASSIGNED_TO_SECONDARY = "wkaAssignedToSecondary"; // workLoadGroupBackup
  public static final String WORKLOAD_ASSIGNMENT_TYPE = "wktType"; // workLoadTaskCategoryName
  public static final String WORKLOAD_ASSIGNMENT_WEIGHT = "wktWeight";
  public static final String WORKLOAD_ASSIGNMENT_PRIORITY = "wktPriority";
  // need action creator ID to populate workLoadTaskCreatorID
  // need to store rule factor set ID for invoking the action -- TODO
  // should be removed, start date is a new rule fact "RuleCheckStartDate", end date is the existing rule set end date
  public static final String WORKLOAD_ASSIGNMENT_DATE_START = "wktDateStart"; // associationBeginDate
  public static final String WORKLOAD_ASSIGNMENT_DATE_END = "wktDateEnd"; // associationEndDate

  // Alert Email template
  public static final String PRIORITY_KEY = "\\{Priority\\}";
  public static final String CASE_KEY = "\\{Case\\}";
  public static final String SUBJECT_KEY = "\\{Subject\\}";
  public static final String TYPE_KEY = "\\{Type\\}";
  public static final String FROM_KEY = "\\{From\\}";

  public static final String ALERT_EMAIL_TEMPLATE_START = "<HTML><BODY>\n";
  public static final String ALERT_EMAIL_TEMPLATE_MAIN = "<TABLE cellpadding=\"2\" cellspacing=\"2\">\n"
      + "\t<TR><TD><STRONG>Priority:</STRONG></TD><TD>{Priority}</TD></TR>\n" + "\t<TR><TD><STRONG>Case:</STRONG></TD><TD>{Case}</TD></TR>\n"
      + "\t<TR><TD><STRONG>Subject:</STRONG></TD><TD>{Subject}</TD></TR>\n" + "\t<TR><TD><STRONG>Type:</STRONG></TD><TD>{Type}</TD></TR>\n";
  public static final String ALERT_EMAIL_FROM_ROW = "\t<TR><TD><STRONG>From:</STRONG></TD><TD>{From}</TD></TR>\n";

  public static final String ALERT_EMAIL_TEMPLATE_END = "</TABLE></BODY></HTML>";
}
