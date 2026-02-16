package com.nfq.demo.tasklets;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.lang.Nullable;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class FileCleanupTasklet implements Tasklet {

    private final String directoryPath;

    public FileCleanupTasklet(String directoryPath){
        this.directoryPath = directoryPath;
    }

    @Nullable
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files != null) {
            Instant cutOffDate = Instant.now().minus(30, ChronoUnit.DAYS);

            for (File file : files) {
                if (file.isFile()) {
                    BasicFileAttributes attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                    Instant creationTime = attrs.creationTime().toInstant();

                    if (creationTime.isBefore(cutOffDate)) {
                        boolean deleted = file.delete();
                        if (deleted) {
                            System.out.println("Archivo eliminado por antigüedad: " + file.getName());
                        }
                    }
                }
            }
        }
        return RepeatStatus.FINISHED;
    }
}
