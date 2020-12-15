package com.ribeiro.ImportCpf.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Person {
	
	private @NonNull Integer id;
	
	private @NonNull String name;
	
	private @NonNull String cpf;

	
//	public Person() {
//		super();
//	}
//
//	public Person(int id, String name, String cpf) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.cpf = cpf;
//	}
//
//	@Override
//	public String toString() {
//		return "Person [id=" + id + ", name=" + name + ", cpf=" + cpf + "]";
//	}
	
}
