package com.sippulse.pet.controller.form;

import java.util.Optional;

import com.sippulse.pet.controller.exception.MyNotFoundException;
import com.sippulse.pet.entity.Cliente;
import com.sippulse.pet.entity.ClienteDetalhe;
import com.sippulse.pet.entity.Endereco;
import com.sippulse.pet.repository.ClienteRepository;
import com.sippulse.pet.repository.EnderecoRepository;

public class AtualizacaoClienteForm extends ClienteForm {

	public ClienteDetalhe atualizar(Long id, ClienteRepository clienteRepository, EnderecoRepository enderecoRepository) {
		Optional<Cliente> clienteOP = clienteRepository.findById(id);
		if (!clienteOP.isPresent()) {
			throw new MyNotFoundException("Cliente não encontrado!");
		}
		ClienteDetalhe clienteDetalhe = clienteOP.get().getClienteDetalhe();
		if (clienteDetalhe == null) {
			throw new MyNotFoundException("Dados Cliente não encontrado.");
		}
		Endereco endereco = enderecoRepository.findByRuaAndNumeroAndComplemento(super.getRua(), super.getNumero(), super.getComplemento());
		if (endereco == null) {
			endereco = new Endereco(clienteDetalhe, super.getRua(), super.getNumero(), super.getComplemento());
		}

		endereco.setChamarPorQuem(super.getChamarPorQuem());
		clienteDetalhe.setCpf(super.getCpf());
		clienteDetalhe.setEmail(super.getEmail());
		clienteDetalhe.setNome(super.getNome());
		clienteDetalhe.setTelefone(super.getTelefone());
		
		return clienteDetalhe;
	}

}
