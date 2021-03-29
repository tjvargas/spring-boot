package com.sippulse.pet.controller;

import java.net.URI;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sippulse.pet.controller.dto.PetDTO;
import com.sippulse.pet.controller.form.AtualizacaoPetForm;
import com.sippulse.pet.controller.form.PetForm;
import com.sippulse.pet.entity.Pet;
import com.sippulse.pet.repository.ClienteRepository;
import com.sippulse.pet.repository.PetRepository;

@RestController
@RequestMapping("pets")
public class PetController {

	@Autowired
	private PetRepository petRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public Page<PetDTO> lista(Pageable pageable) {
		return PetDTO.converter(this.petRepository.findAll(pageable));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<PetDTO> cadastrar(@RequestBody @Valid PetForm form, UriComponentsBuilder uriBuilder) {
		Pet pet = form.converter(clienteRepository);
		this.petRepository.save(pet);

		URI uri = uriBuilder.path("/pets/{id}").buildAndExpand(pet.getId()).toUri();
		return ResponseEntity.created(uri).body(new PetDTO(pet));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PetDTO> find(@PathVariable Long id) {
		Optional<Pet> recurso = this.petRepository.findById(id);
		if (recurso.isPresent()) {
			return ResponseEntity.ok().body(new PetDTO(recurso.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<PetDTO> update(@PathVariable Long id, @RequestBody @Valid AtualizacaoPetForm form, UriComponentsBuilder uriBuilder) {
		Pet pet = form.atualizar(id, petRepository);
		return ResponseEntity.ok(new PetDTO(pet));
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> delete(@PathVariable Long id) {
		this.petRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}