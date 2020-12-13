package com.ribeiro.ImportCpf.batch.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobListener extends JobExecutionListenerSupport {

  private static final Logger logger = LoggerFactory.getLogger(JobListener.class);

  @Autowired
  private JdbcTemplate jdbcTemplate;
  
  @Override
  public void beforeJob(JobExecution jobExecution) {
		super.beforeJob(jobExecution);
		logger.info("Truncating table Person");
		jdbcTemplate.update("truncate table main.person");
		logger.info("Truncate successfully executed!");
  }
  
  @Override
  public void afterJob(JobExecution jobExecution) {
    super.afterJob(jobExecution);
    if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
	    jdbcTemplate.queryForObject("SELECT setval('main.person_seq', coalesce(max(id_person), 0)+1 , false) FROM main.person;",Integer.class);
	    logger.info("Sequence ajusted!");
    }
  }
  
}
