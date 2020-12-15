package com.ribeiro.ImportCpf.batch.person;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ribeiro.ImportCpf.domain.Person;

class PersonValidatorTest
{

  private PersonValidator validator;

  @BeforeEach
  void setup()
  {
    validator = new PersonValidator();
  }

  @Test
  void givenOddCpf_whenValidate_returnFalse()
  {
    final Person person = new Person(1, "person", "22221");

    final Boolean actual = validator.apply(person);

    assertFalse(actual);
  }

  @Test
  void givenEvenCpf_whenValidate_returnTrue()
  {
    final Person person = new Person(1, "person", "22222");

    final Boolean actual = validator.apply(person);

    assertTrue(actual);
  }
}
