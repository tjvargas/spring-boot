package com.sippulse.pet.controller.form;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.sippulse.pet.controller.enums.TipoRecurso;
import com.sippulse.pet.controller.exception.MyNotFoundException;
import com.sippulse.pet.entity.Clinica;
import com.sippulse.pet.entity.Recurso;
import com.sippulse.pet.enums.Funcao;
import com.sippulse.pet.repository.ClinicaRepository;

/**
 * Representa os dados de entrada de cadastro de funcionários como
 * {@link Recurso}
 * 
 * @author tjvar
 *
 */
public class RecursoForm {

	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String nome;
	private String descricao;
	private Double custoHora;
	private String cpf;
	private LocalDate dataVencimentoGarantia;
	@NotNull
	private Long idClinica;
	private Boolean ativo;
	private Funcao funcao;
	private TipoRecurso tipoRecurso;

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

	public Long getIdClinica() {
		return idClinica;
	}

	public void setIdClinica(Long idClinica) {
		this.idClinica = idClinica;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public TipoRecurso getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(TipoRecurso tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataVencimentoGarantia() {
		return dataVencimentoGarantia;
	}

	public void setDataVencimentoGarantia(LocalDate dataVencimentoGarantia) {
		this.dataVencimentoGarantia = dataVencimentoGarantia;
	}

	/**
	 * Converte os dados de entrada para entidade
	 * 
	 * @param cr
	 * @return
	 */
	public Recurso converter(ClinicaRepository cr) {
		Optional<Clinica> clinica = cr.findById(this.idClinica);
		if (!clinica.isPresent()) {
			throw new MyNotFoundException("Clinica inválida!");
		}
		Recurso recurso = RecursoFactory.criarPeloForm(this, clinica.get());
		
		return recurso;

	}
}
