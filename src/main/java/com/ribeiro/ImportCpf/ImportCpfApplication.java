package com.ribeiro.ImportCpf;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Here the magic begins
 *
 */
@SpringBootApplication
public class ImportCpfApplication
{

  public static void main(final String[] args)
  {
//    PersonPostgresqlContainer.getInstance();

    checkParameters(args);

    SpringApplication.run(ImportCpfApplication.class, args);
  }

  private static void checkParameters(final String[] args)
  {
    boolean hasFile = false;
    if (args.length > 0)
    {
      hasFile = new File(args[0]).exists();
    }

    if (!hasFile)
    {
      System.err.println("Error! File not found");
      System.err.println("");
      System.err.println("Syntax: java -jar target/ImportCpf-0.0.1-SNAPSHOT.jar <file name>");
      System.exit(1);
    }
  }

}
