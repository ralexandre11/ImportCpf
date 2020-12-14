package com.ribeiro.ImportCpf;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ImportRunner implements ApplicationRunner
{
  private final JobLauncher jobLauncher;

  private final Job job;

  public ImportRunner(final JobLauncher jobLauncher, final Job job)
  {
    this.jobLauncher = jobLauncher;
    this.job = job;
  }

  @Override
  public void run(final ApplicationArguments args) throws Exception
  {
    final JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
    jobParametersBuilder.addString("input.file.name", args.getSourceArgs()[0]);
    final JobParameters jobParameters = jobParametersBuilder.toJobParameters();

    final JobExecution execution = jobLauncher.run(job, jobParameters);

    System.out.println("Completion Status : " + execution.getStatus());
  }

}
