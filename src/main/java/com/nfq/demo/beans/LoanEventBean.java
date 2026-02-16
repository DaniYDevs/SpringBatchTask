package com.nfq.demo.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@NoArgsConstructor
public class LoanEventBean {

    private String gfLoanEventId;
    private String gfFacilityId;
    private String gEntificId;
    private LocalDate gfCutoffDate;
    private String gfProjectName;
    private String gfProjectBorrowerDesc;
    private String gfEntityDesc;
    private String gfOriginationEntityDesc;
    private String gfCountryName;
    private String gfBbvaAgentRoleIndType;
    private String gfEntityName;
    private String gfMonitoringUnitDesc;
    private String gfFcltyTrcMntrManagerDesc;
    private String gfScMonitoringManagerDesc;
    private String gfEventDeletedIndType;
    private String gfEventTypeDesc;
    private String gfEventStatusDesc;
    private String gfEventCreditImpactLDesc;
    private String gfEventPflMonIndType;
    private String gfEventShortDesc;
    private String gfEventDesc;
    private BigDecimal gfFeeAmount;
    private String gCurrencyId;
    private String gfManualFinalVoteIndType;
    private BigDecimal gfFinalVoteResultPer;
    private BigDecimal gfApprovalVoteNeededPer;
    private String gfCurrentVoteResultDesc;
    private LocalDate gfEventApprovalDate;
    private LocalDate gfEventIssuingDate;
    private LocalDate gfEventReceptionDate;
    private LocalDate gfEventDeadlineDate;
    private LocalDate gfEventClosingDate;
    private String gfUserName;
    private LocalDate gfEventAddDate;
    private LocalDate gfEventUpdateDate;
    private String gfEventUpdateUserName;
    private String gfLastUpdateUserId;
    private String gfCancellationIndType;
    private LocalDate gfCancelDate;
    private String gfCancellationReasonDesc;
    private String gfEvNotAnsNeededIndType;
    private LocalDate gfNotAnswerDate;
    private String gfNotAnswerReasonDesc;
    private String gfIntRiskProcessIndType;
    private LocalDate gfRiskSubmissionDate;
    private LocalDate gfAnalysisRiskSanctionDate;
    private String gfIntRiskPSanctionDesc;
    private String gfBlackMountainEventId;
    private BigDecimal gfEventVersionNumber;
    private String gfActivityLogId;
    private String gfWorkflowStepName;
    private String gfEventGroupType;
    private String gfReqFieldsIntroIndType;
    private BigDecimal gfParticpantsQtyNumber;
    private String gfCommentaryDesc;
}
