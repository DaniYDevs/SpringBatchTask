package com.nfq.demo.steps;

import org.junit.jupiter.api.Test;
import org.springframework.batch.repeat.RepeatStatus;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

public class FileCleanupTaskletTests {
    @Test
    void testFileCleanup() throws Exception {
        File tempDir = Files.createTempDirectory("test_batch").toFile();

        File oldFile = new File(tempDir, "viejo.txt");
        oldFile.createNewFile();
        Instant hace40Dias = Instant.now().minus(40, ChronoUnit.DAYS);
        Files.setAttribute(oldFile.toPath(), "basic:lastModifiedTime", FileTime.from(hace40Dias));
        Files.setAttribute(oldFile.toPath(), "basic:creationTime", FileTime.from(hace40Dias));

        File newFile = new File(tempDir, "nuevo.txt");
        newFile.createNewFile();

        FileCleanupTasklet tasklet = new FileCleanupTasklet(tempDir.getAbsolutePath());
        RepeatStatus status = tasklet.execute(null, null);

        assertEquals(RepeatStatus.FINISHED, status);
        assertFalse(oldFile.exists(), "El archivo viejo debería haber sido borrado");
        assertTrue(newFile.exists(), "El archivo nuevo NO debería haber sido borrado");

        newFile.delete();
        tempDir.delete();
    }
}
