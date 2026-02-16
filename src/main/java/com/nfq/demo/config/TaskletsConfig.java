package com.nfq.demo.config;

@Configuracion
public class TaskletsConfig {

    @Bean
    public Tasklet taskletEjemplo() {
        return (contribution, chunkContext) -> {
            System.out.println("Ejecutando el tasklet de ejemplo...");
            return RepeatStatus.FINISHED;
        };
    }
}
