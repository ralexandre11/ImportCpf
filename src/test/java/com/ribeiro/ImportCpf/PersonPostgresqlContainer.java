package com.ribeiro.ImportCpf;

import org.testcontainers.containers.PostgreSQLContainer;

public class PersonPostgresqlContainer extends PostgreSQLContainer<PersonPostgresqlContainer>
{
  private static final String IMAGE_VERSION = "postgres:11.1";
  private static PersonPostgresqlContainer container;

  private PersonPostgresqlContainer()
  {
    super(IMAGE_VERSION);
  }

  public static PersonPostgresqlContainer getInstance()
  {
    if (container == null)
    {
      container = new PersonPostgresqlContainer().withDatabaseName("testdb")
          .withUsername("postgres")
          .withPassword("postgres");
      container.start();
    }
    return container;
  }

  @Override
  public void start()
  {
    super.start();
    System.setProperty("DB_URL", container.getJdbcUrl());
  }

  @Override
  public void stop()
  {
    // do nothing, JVM handles shut down
  }
}