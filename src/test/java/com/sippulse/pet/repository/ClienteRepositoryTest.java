package com.sippulse.pet.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.sippulse.pet.entity.Cliente;
import com.sippulse.pet.entity.ClienteDetalhe;
import com.sippulse.pet.entity.Clinica;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ClienteRepositoryTest {

	@Autowired
	private ClienteRepository clienteRepository;

	@Test
	void getById() {
		long id = 1;
		Optional<Cliente> cliente = clienteRepository.findById(id);

		assertTrue(cliente.isPresent());
	}

	@Test
	void save() {
		Clinica clinica = new Clinica();
		clinica.setId(1l);
		Cliente cliente = new Cliente(clinica, new ClienteDetalhe());
		ClienteDetalhe cd = cliente.getClienteDetalhe();
		cd.setNome("Thiago Vargas");
		cliente = clienteRepository.save(cliente);

		assertNotNull(cliente);
		assertEquals(cliente.getClienteDetalhe().getNome(), "Thiago Vargas");
	}

	@Test
	void update() {
		Long id = 1l;
		Cliente cliente = clienteRepository.getOne(id);

		cliente.getClienteDetalhe().setTelefone("444");

		Cliente clienteAtualizado = clienteRepository.getOne(id);
		assertTrue(clienteAtualizado.getClienteDetalhe().getTelefone().equals("444"));
	}
}
