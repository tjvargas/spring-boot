package com.sippulse.pet.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.sippulse.pet.enums.TipoServico;

/**
 * Para organizar atendimentos ou consultas marcadas, bem como os recursos alocados por elas.
 * @author tjvar
 *
 */
@Entity
public class Agendamento extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Pet pet;

	private LocalDateTime dataAgendamento;
	private LocalDateTime dataTermino;
	
	@Enumerated(EnumType.STRING)
	private TipoServico tipoServico;
	
	@ManyToMany
	private List<Recurso> recursos;

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public LocalDateTime getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDateTime dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public List<Recurso> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}

	public LocalDateTime getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(LocalDateTime dataTermino) {
		this.dataTermino = dataTermino;
	}
	
	
}
