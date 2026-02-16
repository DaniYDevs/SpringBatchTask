package com.nfq.demo.config;

import com.nfq.demo.tasklets.FileCleanupTasklet;

import java.io.File;

@Configuration
public class TaskletStepsConfig {

    @Bean
    public Step cleanupStep(StepBuilderFactory stepBuilderFactory){
        String directoryPath = System.getProperty("user.home") + File.separator + "ejercicio_batch";
        return stepBuilderFactory.get("cleanupStep")
                .tasklet(new FileCleanupTasklet(directoryPath))
                .build();
    }

    @Bean
    public Step ejemploTaskletStep(StepBuilderFactory stepBuilderFactory, Tasklet taskletEjemplo) {
        return stepBuilderFactory.get("ejemploTaskletStep")
                .tasklet(taskletEjemplo)
                .build();
    }
}
