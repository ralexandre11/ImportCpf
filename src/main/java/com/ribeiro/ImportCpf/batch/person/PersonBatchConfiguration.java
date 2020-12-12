package com.ribeiro.ImportCpf.batch.person;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
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
import org.springframework.core.io.ClassPathResource;

import com.ribeiro.importing.batch.common.JobNotificationListener;
import com.ribeiro.ImportCpf.domain.Person;

@Configuration
@EnableBatchProcessing
public class PersonBatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	    
    @Value("${file.input}")
    private String fileInput;

	/**
	 * Reader responsible to read the file and parse each line item into a Person object
	 * @return
	 */
    @Bean
    public FlatFileItemReader reader() {
        return new FlatFileItemReaderBuilder().name("personalItemReader")
          .resource(new ClassPathResource(fileInput))
          .delimited()
          .names(new String[] { "id", "name", "cpf" })
          .fieldSetMapper(new BeanWrapperFieldSetMapper() {{
              setTargetType(Person.class);
          }})
          .build();
    }

	/**
	 * Writer responsible to insert Person object into database
	 * @param dataSource
	 * @return
	 */
    @Bean
    public JdbcBatchItemWriter writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder()
          .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
          .sql("INSERT INTO person (id, name, cpf) VALUES (:id, :name, :cpf)")
          .dataSource(dataSource)
          .build();
    }
    
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importPersonJob")
          .incrementer(new RunIdIncrementer())
          .listener(listener)
          .flow(step1)
          .end()
          .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter writer) {
        return stepBuilderFactory.get("step1")
          .<Person, Person>  chunk(10)
          .reader(reader())
          .processor(processor())
          .writer(writer)
          .build();
    }

    @Bean
    public PsersonItemProcessor processor() {
        return new PersonItemProcessor();
    }
    
}
