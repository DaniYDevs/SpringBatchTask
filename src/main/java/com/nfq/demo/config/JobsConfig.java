package com.nfq.demo.config;

@Configuration
@EnableBatchProcessing
public class JobsConfig {
    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job importUserJob(Step importStep, Step cleanupStep, Step ejemploTaskletStep) {
        return jobBuilderFactory.get("importUserJob")
                .start(importStep)
                .next(cleanupStep)
                .next(ejemploTaskletStep)
                .listener(importUserJobListener()) // Agrega el listener para eventos del job
                .build();
    }

    @Bean
    public JobExecutionListener importUserJobListener() {
        return new JobExecutionListener() {
            @Override
            public void beforeJob(JobExecution jobExecution) {
                System.out.println("Antes de ejecutar el job...");
            }

            @Override
            public void afterJob(JobExecution jobExecution) {
                System.out.println("Después de ejecutar el job...");
            }
        };
    }
}
