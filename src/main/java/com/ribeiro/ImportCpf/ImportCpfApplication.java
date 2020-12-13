package com.ribeiro.ImportCpf;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImportCpfApplication {

	public static void main(String[] args) {
		
		boolean fileOK=false;
		
		for (String arg : args) {
			if(arg.startsWith("--file.input=")) {
				try {
					fileOK = new File(arg.split("=")[1]).exists();
				} catch (Exception e) {
				}
			}
		}
		if(!fileOK) {
			System.err.println("Error! File not found");
			System.err.println("");
			System.err.println("Syntax: java -jar ImportCpf-0.0.1-SNAPSHOT.jar --file.input=<file name>");
			System.exit(1);
		} else {
			SpringApplication.run(ImportCpfApplication.class, args);
		}
	}
	
}
