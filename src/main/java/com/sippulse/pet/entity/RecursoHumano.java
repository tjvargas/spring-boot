package com.sippulse.pet.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.sippulse.pet.enums.Funcao;

@Entity
public class RecursoHumano extends Recurso{

	private static final long serialVersionUID = -5480042906679852378L;
	
	private String cpf;
	@Enumerated(EnumType.STRING)
	private Funcao funcao;
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	
	
}
