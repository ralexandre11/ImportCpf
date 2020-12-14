package com.ribeiro.ImportCpf.batch.person;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.ribeiro.ImportCpf.domain.Person;

@Component
public class PersonItemProcessor implements ItemProcessor<Person, Person>{
	
	private static final Logger logger = LoggerFactory.getLogger(PersonItemProcessor.class);
	
	 private final Function<Person, Boolean> validator;

	 public PersonItemProcessor(final Function<Person, Boolean> validator)
	 {
		 this.validator = validator;
	 }
	
	/**
	 * Responsible to make any necessary change in object data before be writed on database
	 */
	@Override
	public Person process(final Person person) throws Exception {
		if(!validator.apply(person)) {
			return null;
		}
		final int id = person.getId();
		final String name = person.getName();
		final String cpf = person.getCpf();
		
		final Person transformed = new Person(id,name,cpf);
		logger.info("Converting ( {} ) into ( {} )",person,transformed);
		
		return transformed;
	}
	
}