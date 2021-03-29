package com.sippulse.pet.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sippulse.pet.controller.enums.TipoRecurso;
import com.sippulse.pet.controller.exception.MyNotFoundException;
import com.sippulse.pet.controller.form.AtualizacaoRecursoForm;
import com.sippulse.pet.controller.form.RecursoForm;
import com.sippulse.pet.entity.Recurso;
import com.sippulse.pet.repository.ClinicaRepository;
import com.sippulse.pet.repository.RecursoFisicoRepository;
import com.sippulse.pet.repository.RecursoHumanoRepository;
import com.sippulse.pet.repository.RecursoRepository;

@RestController
@RequestMapping("/recursos")
public class RecursoController {

	@Autowired
	private RecursoRepository recursoRepository;
	@Autowired
	private RecursoFisicoRepository recursoFisicoRepository;
	@Autowired
	private RecursoHumanoRepository recursoHumanoRepository;
	@Autowired
	private ClinicaRepository clinicaRepository;

	@GetMapping
	@Cacheable(value = "listaRecursos")
	public Page<?> lista(TipoRecurso tipoRecurso, @PageableDefault(sort = "id", direction = Direction.DESC) Pageable pageable) {
		if (tipoRecurso == null) {
			return this.recursoRepository.findAll(pageable);
		}
		switch (tipoRecurso) {
		case HUMANO:
			return this.recursoHumanoRepository.findAll(pageable);
		case FISICO:
			return this.recursoFisicoRepository.findAll(pageable);
		default:
			throw new MyNotFoundException("tipoRecurso não existe!");
		}
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "listaRecursos", allEntries = true)
	public ResponseEntity<Recurso> cadastrar(@RequestBody @Valid RecursoForm form, UriComponentsBuilder uriBuilder) {
		Recurso recurso = form.converter(clinicaRepository);
		this.recursoRepository.save(recurso);

		URI uri = uriBuilder.path("/recursos/{id}").buildAndExpand(recurso.getId()).toUri();
		return ResponseEntity.created(uri).body(recurso);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Recurso> find(@PathVariable Long id) {
		Optional<Recurso> recurso = this.recursoRepository.findById(id);
		if (recurso.isPresent()) {
			return ResponseEntity.ok().body(recurso.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("{id}")
	@Transactional
	@CacheEvict(value = "listaRecursos", allEntries = true)
	public ResponseEntity<Recurso> update(@PathVariable Long id, @RequestBody @Valid AtualizacaoRecursoForm form, UriComponentsBuilder uriBuilder) {
		Recurso recurso = form.converter(id, recursoRepository);
		return ResponseEntity.ok(recurso);
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	@CacheEvict(value = "listaRecursos", allEntries = true)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		this.recursoRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
