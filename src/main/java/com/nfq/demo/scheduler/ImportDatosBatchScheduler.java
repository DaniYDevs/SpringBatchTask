package com.nfq.demo.scheduler;

@Service
public class ImportDatosBatchScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Scheduled(cron = "0 0 2 * * ?") // Ejecuta a medianoche todos los días
    public void ejecutarImportDatosBatch(Job importUserJob) throws Exception {
        // Aquí puedes implementar la lógica para ejecutar tu job de Spring Batch
        // Por ejemplo, puedes usar JobLauncher para lanzar el job programáticamente
        jobLauncher.run(importUserJob, new JobParameters());
        System.out.println("Ejecutando el job de importación de datos...");
    }
}
