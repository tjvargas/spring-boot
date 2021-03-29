package com.sippulse.pet.controller.form;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.sippulse.pet.controller.exception.MyNotFoundException;
import com.sippulse.pet.entity.Cliente;
import com.sippulse.pet.entity.Pet;
import com.sippulse.pet.repository.ClienteRepository;

public class PetForm {

	@NotNull
	private String nome;
	@NotNull
	private String raca;
	private LocalDate dataNascimento;
	@NotNull
	private Long idCliente;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRaca() {
		return raca;
	}
	public void setRaca(String raca) {
		this.raca = raca;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Pet converter(ClienteRepository cr) {
		Optional<Cliente> cliente = cr.findById(this.getIdCliente());
		if(!cliente.isPresent()) {
			throw new MyNotFoundException("Cliente não encontrado!");
		}
		Pet pet = new Pet();
		pet.setCliente(cliente.get());
		pet.setDataNascimento(this.dataNascimento);
		pet.setNome(this.nome);
		pet.setRaca(this.raca);
		
		return pet;
	}
}
