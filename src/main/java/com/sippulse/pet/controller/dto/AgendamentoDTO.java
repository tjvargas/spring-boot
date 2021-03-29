package com.sippulse.pet.controller.dto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.data.domain.Page;

import com.sippulse.pet.entity.Agendamento;
import com.sippulse.pet.entity.Recurso;
import com.sippulse.pet.enums.TipoServico;

public class AgendamentoDTO {

	private Long id;
	private Long idPet;
	private LocalDateTime dataAgendamento;
	private TipoServico tipoServico;
	private short tempoAtendimentoPrevisto;
	private List<Recurso> recursos;
	private String nomeCliente;

	public AgendamentoDTO(Agendamento agendamento, short tempoAtendimentoPrevisto) {
		this.id = agendamento.getId();
		this.idPet = agendamento.getPet().getId();
		this.dataAgendamento = agendamento.getDataAgendamento();
		this.tipoServico = agendamento.getTipoServico();
		this.tempoAtendimentoPrevisto = tempoAtendimentoPrevisto;
		this.recursos = agendamento.getRecursos();
		this.nomeCliente = agendamento.getPet().getCliente().getClienteDetalhe().getNome();
	}

	public AgendamentoDTO(Agendamento agendamento) {
		this.id = agendamento.getId();
		this.idPet = agendamento.getPet().getId();
		this.dataAgendamento = agendamento.getDataAgendamento();
		this.tipoServico = agendamento.getTipoServico();
		this.tempoAtendimentoPrevisto = (short) ChronoUnit.MINUTES.between(agendamento.getDataAgendamento(), agendamento.getDataTermino());;
		this.recursos = agendamento.getRecursos();
		this.nomeCliente = agendamento.getPet().getCliente().getClienteDetalhe().getNome();
	}
	
	public Long getId() {
		return id;
	}

	public Long getIdPet() {
		return idPet;
	}

	public LocalDateTime getDataAgendamento() {
		return dataAgendamento;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public short getTempoAtendimentoPrevisto() {
		return tempoAtendimentoPrevisto;
	}

	public List<Recurso> getRecursos() {
		return recursos;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}
	public static Page<AgendamentoDTO> converter(Page<Agendamento> pets) {
		return pets.map(AgendamentoDTO::new);
	}
}
