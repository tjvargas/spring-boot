package com.sippulse.pet.controller;

import java.net.URI;
import java.util.List;
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

import com.sippulse.pet.controller.dto.ClienteDTO;
import com.sippulse.pet.controller.dto.EnderecoDTO;
import com.sippulse.pet.controller.form.AtualizacaoClienteForm;
import com.sippulse.pet.controller.form.ClienteForm;
import com.sippulse.pet.entity.Cliente;
import com.sippulse.pet.entity.ClienteDetalhe;
import com.sippulse.pet.repository.ClienteDetalheRepository;
import com.sippulse.pet.repository.ClienteRepository;
import com.sippulse.pet.repository.ClinicaRepository;
import com.sippulse.pet.repository.EnderecoRepository;

@RestController
@RequestMapping("clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ClinicaRepository clinicaRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ClienteDetalheRepository clienteDetalheRepository;

	@GetMapping
	public Page<ClienteDTO> listaClientes(Pageable pageable) {
		return ClienteDTO.converter(this.clienteRepository.findAll(pageable));
	}

	@GetMapping("/{cpf}/agendamentos")
	@ResponseBody
	public List<EnderecoDTO> listaAgendamentos(@PathVariable String cpf) {
		return EnderecoDTO.converter(this.enderecoRepository.findByClienteDetalheCpf(cpf));
	}

	@GetMapping("/{cpf}/enderecos")
	@ResponseBody
	public List<EnderecoDTO> listaEnderecos(@PathVariable String cpf) {
		return EnderecoDTO.converter(this.enderecoRepository.findByClienteDetalheCpf(cpf));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ClienteDTO> cadastrar(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
		Cliente cliente = form.converter(clinicaRepository);
		this.clienteRepository.save(cliente);

		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDTO(cliente.getClienteDetalhe()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> find(@PathVariable Long id) {
		Optional<Cliente> cliente = this.clienteRepository.findById(id);
		if (cliente.isPresent()) {
			return ResponseEntity.ok().body(new ClienteDTO(cliente.get().getClienteDetalhe()));
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoClienteForm form, UriComponentsBuilder uriBuilder) {
		ClienteDetalhe cliente = form.atualizar(id, clienteRepository, enderecoRepository);
		return ResponseEntity.ok().body(new ClienteDTO(cliente));
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
		Optional<Cliente> clienteOp = this.clienteRepository.findById(id);
		if (!clienteOp.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cliente cliente = clienteOp.get();
		ClienteDetalhe clienteDetalhe = cliente.getClienteDetalhe();
		cliente.setClienteDetalhe(null);
		this.clienteDetalheRepository.delete(clienteDetalhe);

		return ResponseEntity.ok().build();
	}

}