package com.sippulse.pet.controller.form;

import java.util.Arrays;
import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.sippulse.pet.controller.exception.MyNotFoundException;
import com.sippulse.pet.entity.Cliente;
import com.sippulse.pet.entity.ClienteDetalhe;
import com.sippulse.pet.entity.Clinica;
import com.sippulse.pet.entity.Endereco;
import com.sippulse.pet.repository.ClienteRepository;
import com.sippulse.pet.repository.ClinicaRepository;

public class ClienteForm {

	@NotNull
	private String nome;
	private String telefone;
	@NotNull
	@CPF
	private String cpf;
	@Email
	private String email;
	private String rua;
	private String numero;
	private String complemento;
	private String chamarPorQuem;
	private Long idClinica;

	public Long getIdClinica() {
		return idClinica;
	}

	public void setIdClinica(Long idClinica) {
		this.idClinica = idClinica;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEmail() {
		return email;
	}

	public String getRua() {
		return rua;
	}

	public String getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getChamarPorQuem() {
		return chamarPorQuem;
	}

	public void setChamarPorQuem(String chamarPorQuem) {
		this.chamarPorQuem = chamarPorQuem;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Cliente converter(ClinicaRepository clinicaRepository) {
		Optional<Clinica> clinica = clinicaRepository.findById(idClinica);
		if (!clinica.isPresent()) {
			throw new MyNotFoundException("Clinica não encontrada");
		}
		Cliente cliente = new Cliente(clinica.get(), new ClienteDetalhe());

		ClienteDetalhe cd = cliente.getClienteDetalhe();
		cd.setCpf(this.cpf);
		cd.setEmail(this.email);
		cd.setNome(this.nome);
		cd.setTelefone(this.telefone);

		Endereco endereco = new Endereco(cd, this.rua, this.numero, this.complemento);
		endereco.setChamarPorQuem(chamarPorQuem);
		cd.setEnderecos(Arrays.asList(endereco));

		return cliente;

	}

}
