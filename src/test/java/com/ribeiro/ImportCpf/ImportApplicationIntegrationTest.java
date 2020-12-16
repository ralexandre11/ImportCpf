package com.ribeiro.ImportCpf;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.JdbcDatabaseContainer.NoDriverFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest(args = "src/main/resources/list-cpf.txt")
@ActiveProfiles("test")
public class ImportApplicationIntegrationTest
{

  @ClassRule
  public static PersonPostgresqlContainer postgres = PersonPostgresqlContainer.getInstance();

  @Test
  void testImport() throws NoDriverFoundException, SQLException
  {
    assertEquals(3, countPerson());
  }

  private Long countPerson() throws NoDriverFoundException, SQLException
  {
    final Connection conn = postgres.createConnection("");
    final Statement statement = conn.createStatement();
    statement.execute("select count(0) from person");
    final ResultSet rs = statement.getResultSet();
    rs.next();
    return rs.getLong(1);
  }

}
