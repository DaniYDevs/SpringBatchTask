package com.nfq.demo.config;

import com.nfq.demo.beans.LoanEventBean;

import javax.sql.DataSource;
import java.io.File;
import java.time.LocalDate;

@Configuration
public class ChunkStepsConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    // Chunk step: importar

    @Bean
    public FlatFileItemReader<LoanEventBean> itemReader() {
        String filePath = System.getProperty("user.home") + File.separator + "ejercicio_batch" +
                File.separator + "datos_test.dat";
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("gfLoanEventId", "gfFacilityId", "gEntificId", "gfCutoffDate", "gfProjectName",
                "gfProjectBorrowerDesc", "gfEntityDesc", "gfOriginationEntityDesc", "gfCountryName",
                "gfBbvaAgentRoleIndType", "gfEntityName", "gfMonitoringUnitDesc", "gfFcltyTrc_mntrManagerDesc",
                "gfScMonitoringManagerDesc", "gfEventDeletedIndType", "gfEventTypeDesc", "gfEventStatusDesc",
                "gfEventCreditImpactLDesc", "gfEventPflMonIndType", "gfEventShortDesc", "gfEventDesc",
                "gfFeeAmount", "gCurrencyId", "gfManualFinalVoteIndType", "gfFinalVoteResultPer",
                "gfApprovalVoteNeededPer", "gfCurrentVoteResultDesc", "gfEventApprovalDate",
                "gfEventIssuingDate", "gfEventReceptionDate", "gfEventDeadlineDate", "gfEventClosingDate",
                "gfUserName", "gfEventAddDate", "gfEventUpdateDate", "gfEventUpdateUserName",
                "gfLastUpdateUserId", "gfCancellationIndType", "gfCancelDate", "gfCancellationReasonDesc",
                "gfEvNotAnsNeededIndType", "gfNotAnswerDate", "gfNotAnswerReasonDesc",
                "gfIntRiskProcessIndType", "gfRiskSubmissionDate", "gfAnalysisRiskSanctionDate",
                "gfIntRiskPSanctionDesc", "gfBlackMountainEventId", "gfEventVersionNumber",
                "gfActivityLogId", "gfWorkflowStepName", "gfEventGroupType", "gfReqFieldsIntroIndType",
                "gfParticpantsQtyNumber", "gfCommentaryDesc");

        tokenizer.setQuoteCharacter('"');
        tokenizer.setStrict(false);
        BeanWrapperFieldSetMapper<LoanEventBean> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(LoanEventBean.class);

        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(String.class, LocalDate.class, source -> {
            if (source == null || source.trim().isEmpty() || source.equals("\"\"")) return null;
            String cleanSource = source.replace("\"", "").trim();
            return LocalDate.parse(cleanSource);
        });
        fieldSetMapper.setConversionService(conversionService);
        DefaultLineMapper<LoanEventBean> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return new FlatFileItemReaderBuilder<LoanEventBean>()
                .name("loanEventReader")
                .resource(new FileSystemResource(filePath))
                .linesToSkip(1)
                .lineMapper(lineMapper)
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<LoanEventBean> itemWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<LoanEventBean>()
                .dataSource(dataSource)
                .sql("INSERT INTO DATOS_TEST (gf_loan_event_id, gf_facility_id, g_entific_id, gf_cutoff_date, " +
                        "gf_project_name, gf_project_borrower_desc, gf_entity_desc, gf_origination_entity_desc, " +
                        "gf_country_name, gf_bbva_agent_role_ind_type, gf_entity_name, gf_monitoring_unit_desc, " +
                        "gf_fclty_trc_mntr_manager_desc, gf_sc_monitoring_manager_desc, gf_event_deleted_ind_type, " +
                        "gf_event_type_desc, gf_event_status_desc, gf_event_credit_impact_l_desc, " +
                        "gf_event_pfl_mon_ind_type, gf_event_short_desc, gf_event_desc, gf_fee_amount, g_currency_id, "+
                        "gf_manual_final_vote_ind_type, gf_final_vote_result_per, gf_approval_vote_needed_per, " +
                        "gf_current_vote_result_desc, gf_event_approval_date, gf_event_issuing_date, " +
                        "gf_event_reception_date, gf_event_deadline_date, gf_event_closing_date, gf_user_name, " +
                        "gf_event_add_date, gf_event_update_date, gf_event_update_user_name, gf_last_update_user_id, " +
                        "gf_cancellation_ind_type, gf_cancel_date, gf_cancellation_reason_desc, " +
                        "gf_ev_not_ans_needed_ind_type, gf_not_answer_date, gf_not_answer_reason_desc, " +
                        "gf_int_risk_process_ind_type, gf_risk_submission_date, gf_analysis_risk_sanction_date, " +
                        "gf_int_risk_p_sanction_desc, gf_black_mountain_event_id, gf_event_version_number, " +
                        "gf_activity_log_id, gf_workflow_step_name, gf_event_group_type, " +
                        "gf_req_fields_intro_ind_type, gf_particpants_qty_number, gf_commentary_desc) " +
                        "VALUES (:gfLoanEventId, :gfFacilityId, :gEntificId, :gfCutoffDate, :gfProjectName, " +
                        ":gfProjectBorrowerDesc, :gfEntityDesc, :gfOriginationEntityDesc, :gfCountryName, " +
                        ":gfBbvaAgentRoleIndType, :gfEntityName, :gfMonitoringUnitDesc, :gfFcltyTrcMntrManagerDesc, " +
                        ":gfScMonitoringManagerDesc, :gfEventDeletedIndType, :gfEventTypeDesc, :gfEventStatusDesc, " +
                        ":gfEventCreditImpactLDesc, :gfEventPflMonIndType, :gfEventShortDesc, :gfEventDesc, " +
                        ":gfFeeAmount, :gCurrencyId, :gfManualFinalVoteIndType, :gfFinalVoteResultPer, " +
                        ":gfApprovalVoteNeededPer, :gfCurrentVoteResultDesc, :gfEventApprovalDate, " +
                        ":gfEventIssuingDate, :gfEventReceptionDate, :gfEventDeadlineDate, :gfEventClosingDate, " +
                        ":gfUserName, :gfEventAddDate, :gfEventUpdateDate, :gfEventUpdateUserName, " +
                        ":gfLastUpdateUserId, :gfCancellationIndType, :gfCancelDate, :gfCancellationReasonDesc, " +
                        ":gfEvNotAnsNeededIndType, :gfNotAnswerDate, :gfNotAnswerReasonDesc, " +
                        ":gfIntRiskProcessIndType, :gfRiskSubmissionDate, :gfAnalysisRiskSanctionDate, " +
                        ":gfIntRiskPSanctionDesc, :gfBlackMountainEventId, :gfEventVersionNumber, :gfActivityLogId, " +
                        ":gfWorkflowStepName, :gfEventGroupType, :gfReqFieldsIntroIndType, :gfParticpantsQtyNumber, " +
                        ":gfCommentaryDesc)")
                .beanMapped()
                .build();
    }

    @Bean
    public Step importStep(FlatFileItemReader<LoanEventBean> reader,
                           JdbcBatchItemWriter<LoanEventBean> writer){
        return stepBuilderFactory.get("importStep")
                .<LoanEventBean, LoanEventBean>chunk(100)
                .reader(reader)
                .writer(writer)
                .build();

    }

    // END Chunk step: importar
}
