package com.sippulse.pet.controller.form;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.sippulse.pet.entity.Recurso;
import com.sippulse.pet.enums.TipoServico;

public class AgendamentoForm {

	@NotNull
	private Long idPet;
	@NotNull
	private LocalDateTime dataAgendamento;
	@NotNull
	private TipoServico tipoServico;
	@NotNull
	@Min(1) @Max(30000)
	private short duracaoAtendimento;
	@NotNull
	@NotEmpty
	private List<Recurso> recursos;
	
	public Long getIdPet() {
		return idPet;
	}
	public void setIdPet(Long idPet) {
		this.idPet = idPet;
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
	public short getTempoAtendimentoPrevisto() {
		return duracaoAtendimento;
	}
	public void setTempoAtendimentoPrevisto(short tempoAtendimentoPrevisto) {
		this.duracaoAtendimento = tempoAtendimentoPrevisto;
	}
	public List<Recurso> getRecursos() {
		return recursos;
	}
	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}
	
	
	
	
}
