package com.sippulse.pet.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.sippulse.pet.entity.Recurso;
import com.sippulse.pet.repository.RecursoRepository;

/**
 * Representa os dados de entrada de cadastro de funcionários como
 * {@link Recurso}
 * 
 * @author tjvar
 *
 */
public class AtualizacaoRecursoForm {

	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String nome;
	private String descricao;
	private Double custoHora;


	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setCustoHora(Double custoHora) {
		this.custoHora = custoHora;
	}

	/**
	 * Aplica os dados de entrada para a entidade existente
	 * 
	 * @param cr
	 * @return
	 */
	public Recurso converter(Long id, RecursoRepository recursoRepository) {
		Recurso recurso = recursoRepository.findById(id).get();

		recurso.setCustoHora(this.custoHora);
		recurso.setDescricao(this.descricao);
		recurso.setNome(this.nome);

		return recurso;

	}
}
