package com.sippulse.pet.controller.dto;

import org.springframework.data.domain.Page;

import com.sippulse.pet.entity.Cliente;
import com.sippulse.pet.entity.ClienteDetalhe;

public class ClienteDTO {

	private Long id;
	private String nome;
	private String telefone;
	private String cpf;
	private String email;

	public ClienteDTO(ClienteDetalhe clienteDetalhe) {
		this.id = clienteDetalhe.getCliente().getId();
		this.nome = clienteDetalhe.getNome();
		this.telefone = clienteDetalhe.getTelefone();
		this.cpf = clienteDetalhe.getCpf();
		this.email = clienteDetalhe.getEmail();
	}

	public ClienteDTO(Cliente cliente) {
		ClienteDetalhe clienteDetalhe = cliente.getClienteDetalhe();
		this.id = cliente.getId();
		this.nome = clienteDetalhe.getNome();
		this.telefone = clienteDetalhe.getTelefone();
		this.cpf = clienteDetalhe.getCpf();
		this.email = clienteDetalhe.getEmail();
	}

	public Long getId() {
		return id;
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

	public static Page<ClienteDTO> converter(Page<Cliente> clientes) {
		return clientes.map(ClienteDTO::new);
	}
}
