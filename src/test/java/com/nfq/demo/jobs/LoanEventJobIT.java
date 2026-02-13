package com.nfq.demo.jobs;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@SpringBatchTest
public class LoanEventJobIT {
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testFullJob() throws Exception {
        jdbcTemplate.execute("DELETE FROM DATOS_TEST");

        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
        Integer totalRows = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM DATOS_TEST", Integer.class);

        assertEquals(500, totalRows, "Debería haber 500 registros insertados en la tabla");
    }
}
