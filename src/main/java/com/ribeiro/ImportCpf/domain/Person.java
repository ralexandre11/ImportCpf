package com.ribeiro.ImportCpf.domain;

public class Person {
	
	private int id;
	
	private String name;
	
	private String cpf;

	
	public Person() {
		super();
	}

	public Person(int id, String name, String cpf) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", cpf=" + cpf + "]";
	}
	
}
