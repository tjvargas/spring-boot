package com.sippulse.pet.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import com.sippulse.pet.enums.Funcao;

/**
 * Representa os recursos da empresa, como funcionários, equipamentos, salas, etc.
 * <p> Recursos estes, utilizados para serviços e {@link Agendamento}
 * @author tjvar
 *
 */
@Entity
@Inheritance (strategy = InheritanceType.JOINED)
public class Recurso extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Clinica clinica;
	private String nome;
	private String descricao;
	private Double custoHora;
	
	public Clinica getClinica() {
		return clinica;
	}
	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getCustoHora() {
		return custoHora;
	}
	public void setCustoHora(Double custoHora) {
		this.custoHora = custoHora;
	}

	
}
