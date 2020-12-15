package com.ribeiro.ImportCpf.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@ToString
public class Person {
	
	private @NonNull Integer id;
	
	private @NonNull String name;
	
	private @NonNull String cpf;

}
