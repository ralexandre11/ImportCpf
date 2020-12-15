package com.ribeiro.ImportCpf.batch.person;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ribeiro.ImportCpf.domain.Person;

@ExtendWith(MockitoExtension.class)
class PersonItemProcessorTest
{
  private PersonItemProcessor processor;

  @Mock
  private Function<Person, Boolean> validator;

  @BeforeEach
  void setup()
  {
    processor = new PersonItemProcessor(validator);
  }

  @Test
  void givenInvalidPerson_whenProcess_thenReturnNull() throws Exception
  {
    final Person p = new Person();
    Mockito.when(validator.apply(Mockito.any()))
        .thenReturn(false);

    final Person actual = processor.process(p);

    assertNull(actual);
  }
}
