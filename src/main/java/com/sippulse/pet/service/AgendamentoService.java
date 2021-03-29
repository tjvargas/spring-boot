package com.sippulse.pet.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sippulse.pet.controller.dto.AgendamentoDTO;
import com.sippulse.pet.controller.exception.MyBadRequestException;
import com.sippulse.pet.controller.exception.MyNotFoundException;
import com.sippulse.pet.controller.form.AgendamentoForm;
import com.sippulse.pet.entity.Agendamento;
import com.sippulse.pet.entity.Pet;
import com.sippulse.pet.entity.Recurso;
import com.sippulse.pet.repository.AgendamentoRepository;
import com.sippulse.pet.repository.PetRepository;
import com.sippulse.pet.repository.RecursoRepository;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	@Autowired
	private RecursoRepository recursoRepository;
	@Autowired
	private PetRepository petRepository;

	/**
	 * Agenda um serviço como consulta, banho ou tosa. Valida antes se os recursos como funcionários, veterinários, máquinas, consultórios estão disponíveis para aquele dia e horário.
	 * @param form
	 * @return um agendamento concluído
	 */
	public Agendamento montarAgenda(@Valid AgendamentoForm form) {
		Set<Long> idsRecursosSolicitados = form.getRecursos().stream().map(Recurso::getId).collect(Collectors.toSet());
		List<Recurso> recursosSolicitados = this.recursoRepository.findByIdIn(idsRecursosSolicitados); 
		LocalDateTime horaInicio = form.getDataAgendamento();
		LocalDateTime horaFim = horaInicio.plusMinutes(form.getTempoAtendimentoPrevisto());
		if (!this.isRecursosLivres(idsRecursosSolicitados, horaInicio, horaFim)) {
			throw new MyBadRequestException("Todos os recursos não estão disponíveis!");
		}

		Optional<Pet> pet = petRepository.findById(form.getIdPet());
		if (!pet.isPresent()) {
			throw new MyNotFoundException("Pet não encontrado!");
		}
		Agendamento agendamento = new Agendamento();
		agendamento.setPet(pet.get());
		agendamento.setTipoServico(form.getTipoServico());
		agendamento.setRecursos(recursosSolicitados);
		agendamento.setDataAgendamento(horaInicio);
		agendamento.setDataTermino(horaFim);

		return agendamento;
	}

	public AgendamentoDTO agendar(@Valid AgendamentoForm form) {
		Agendamento agendamento = this.montarAgenda(form);
		agendamento = this.agendamentoRepository.save(agendamento);
		short tempoAtendimentoPrevisto = (short) ChronoUnit.MINUTES.between(agendamento.getDataAgendamento(), agendamento.getDataTermino());

		return new AgendamentoDTO(agendamento, tempoAtendimentoPrevisto);
	}

	public boolean isRecursosLivres(Set<Long> idRecursosSolicitados, LocalDateTime de, LocalDateTime ate) {
		List<Recurso> recursosOcupados = this.agendamentoRepository.findRecursosOcupados(de, ate, idRecursosSolicitados);
		if (recursosOcupados.isEmpty()) {
			return true;
		}
		return false;
	}

}
