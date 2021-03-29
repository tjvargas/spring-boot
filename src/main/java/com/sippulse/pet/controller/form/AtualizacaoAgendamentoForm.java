package com.sippulse.pet.controller.form;

import java.util.Optional;

import com.sippulse.pet.controller.exception.MyNotFoundException;
import com.sippulse.pet.entity.Agendamento;
import com.sippulse.pet.repository.AgendamentoRepository;

public class AtualizacaoAgendamentoForm extends AgendamentoForm {

	/**
	 * @param id
	 * @param ar
	 * @return
	 */
	public Agendamento atualizar(Long id, AgendamentoRepository ar) {
		Optional<Agendamento> agendamentoOp = ar.findById(id);
		if (!agendamentoOp.isPresent()) {
			throw new MyNotFoundException();
		}
		Agendamento agendamento = agendamentoOp.get();
		agendamento.setDataAgendamento(super.getDataAgendamento());
		agendamento.setDataTermino(super.getDataAgendamento().plusMinutes(super.getTempoAtendimentoPrevisto()));
		agendamento.setRecursos(super.getRecursos());
		agendamento.setTipoServico(super.getTipoServico());
		
		return agendamento;
	}
}
