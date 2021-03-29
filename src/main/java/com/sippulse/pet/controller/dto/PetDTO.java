package com.sippulse.pet.controller.dto;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import com.sippulse.pet.entity.Pet;

public class PetDTO {

	private Long id;
	private	String nome;
	private String raca;
	private LocalDate dataNascimento;
	
	public PetDTO(Pet pet) {
		super();
		this.id = pet.getId();
		this.nome = pet.getNome();
		this.raca = pet.getRaca();
		this.dataNascimento = pet.getDataNascimento();
	}
	public String getNome() {
		return nome;
	}
	public String getRaca() {
		return raca;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	public Long getId() {
		return id;
	}
	public static Page<PetDTO> converter(Page<Pet> pets) {
		return pets.map(PetDTO::new);
	}
}
