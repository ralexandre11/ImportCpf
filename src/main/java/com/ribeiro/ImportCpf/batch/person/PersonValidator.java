package com.ribeiro.ImportCpf.batch.person;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.ribeiro.ImportCpf.domain.Person;

@Component
public class PersonValidator implements Function<Person, Boolean>
{
  @Override
  public Boolean apply(final Person person)
  {
    if (Long.valueOf(person.getCpf()) % 2 > 0)
    {
      return false;
    }
    return true;
  }
}