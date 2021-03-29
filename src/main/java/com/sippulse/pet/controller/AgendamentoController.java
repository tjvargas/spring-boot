package com.sippulse.pet.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sippulse.pet.controller.dto.AgendamentoDTO;
import com.sippulse.pet.controller.form.AgendamentoForm;
import com.sippulse.pet.controller.form.AtualizacaoAgendamentoForm;
import com.sippulse.pet.entity.Agendamento;
import com.sippulse.pet.repository.AgendamentoRepository;
import com.sippulse.pet.service.AgendamentoService;

@RestController
@RequestMapping("agendamentos")
public class AgendamentoController {

	@Autowired
	private AgendamentoRepository agendamentoRepository;
	@Autowired
	private AgendamentoService agendamentoService;

	@GetMapping
	public Page<AgendamentoDTO> listaAgendamentos(@RequestParam(required = false) String nomeRecurso, @RequestParam(required = false) String dia, Pageable pageable) {
		return AgendamentoDTO.converter(this.agendamentoRepository.findByFilter(nomeRecurso, dia, pageable));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<AgendamentoDTO> cadastrar(@RequestBody @Valid AgendamentoForm form, UriComponentsBuilder uriBuilder) {
		AgendamentoDTO dto = this.agendamentoService.agendar(form);

		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AgendamentoDTO> find(@PathVariable Long id) {
		Optional<Agendamento> agendamento = this.agendamentoRepository.findById(id);
		if (agendamento.isPresent()) {
			return ResponseEntity.ok().body(new AgendamentoDTO(agendamento.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<AgendamentoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoAgendamentoForm form, UriComponentsBuilder uriBuilder) {
		Agendamento agendamento = form.atualizar(id, this.agendamentoRepository);
		return ResponseEntity.ok().body(new AgendamentoDTO(agendamento));
	}

	/**
	 * Deleta os dados do cliente, o cliente permanece
	 * 
	 * @param id do cliente a limpar os dados
	 * @return
	 */
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> delete(@PathVariable Long id) {
		this.agendamentoRepository.deleteById(id);

		return ResponseEntity.ok().build();
	}

}