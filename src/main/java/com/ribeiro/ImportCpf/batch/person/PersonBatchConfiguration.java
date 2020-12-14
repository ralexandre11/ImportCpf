package com.ribeiro.ImportCpf.batch.person;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;

import com.ribeiro.ImportCpf.domain.Person;

@Configuration
@EnableBatchProcessing
public class PersonBatchConfiguration {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonBatchConfiguration.class);
	
  @Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	/**
	 * Reader responsible to read the file and parse each line item into a Person object
	 * @return
	 */
	@Bean
  @StepScope
  public FlatFileItemReader<Person> reader(
      @Value("#{jobParameters['input.file.name']}") final String inputFileName)
  {
    logger.info("Importing from {}", inputFileName);
    return new FlatFileItemReaderBuilder<Person>().name("personItemReader")
        .resource(new PathResource(inputFileName))
        .delimited()
        .names(new String[] { "id", "name", "cpf" })
        .fieldSetMapper(new BeanWrapperFieldSetMapper<Person>()
        {
          {
            setTargetType(Person.class);
          }
        })
        .build();
	}
	
	/**
	 * Writer responsible to insert Person object into database
	 * @param dataSource
	 * @return
	 */
	@Bean
	public JdbcBatchItemWriter<Person> writer(final DataSource dataSource) {
	    return new JdbcBatchItemWriterBuilder<Person>()
	      .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
	      .sql("INSERT INTO person (id_person, name, cpf) VALUES (:id, :name, :cpf)")
	      .dataSource(dataSource)
	      .build();
	}
	
	@Bean
	public Job importPersonJob(final PersonJobListener listener, final Step step1) {
		final Job job = jobBuilderFactory.get("importPersonJob")
	      .incrementer(new RunIdIncrementer())
	      .listener(listener)
	      .flow(step1)
	      .end()
	      .build();
	    return job;
	}
	
	@Bean
  public Step step1(
      final FlatFileItemReader<Person> reader,
      final PersonItemProcessor processor,
      final JdbcBatchItemWriter<Person> writer)
  {
		final Step step = stepBuilderFactory.get("step1")
	      .<Person, Person> chunk(10)
        .reader(reader)
        .processor(processor)
	      .writer(writer)
	      .build();
	    return step;
	}

}