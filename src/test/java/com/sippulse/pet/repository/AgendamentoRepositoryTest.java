package com.sippulse.pet.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.sippulse.pet.entity.Agendamento;
import com.sippulse.pet.entity.Pet;
import com.sippulse.pet.entity.RecursoHumano;
import com.sippulse.pet.enums.Funcao;
import com.sippulse.pet.enums.TipoServico; 

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class AgendamentoRepositoryTest { 

	@Autowired
	private AgendamentoRepository agendamentoRepository;
	@Autowired
	private TestEntityManager em;

	@Test
	void should_find_by_id() {
		long id = 1;
		Optional<Agendamento> ag = agendamentoRepository.findById(id);

		assertTrue(ag.isPresent());
	}

	@Test
	void should_save() {
		RecursoHumano veterinario = new RecursoHumano();
		veterinario.setCpf("9999999999");
		veterinario.setNome("Clorisvaldo");
		veterinario.setFuncao(Funcao.VETERINARIO);
		veterinario = em.persist(veterinario);
		Pet pet = em.find(Pet.class, 1l);
		
		Agendamento agendamento = new Agendamento();
		agendamento.setDataAgendamento(LocalDateTime.now().plusDays(1));
		agendamento.setDataTermino(LocalDateTime.now().plusDays(1).plusMinutes(50));
		agendamento.setPet(pet);
		agendamento.setRecursos(Arrays.asList(veterinario));
		agendamento.setTipoServico(TipoServico.CONSULTA);
		
		agendamento = agendamentoRepository.save(agendamento);

		Agendamento agendamentoCadastrado = em.find(Agendamento.class, agendamento.getId());
		assertNotNull(agendamentoCadastrado);
		assertEquals(agendamento.getPet().getNome(), "kioko");
		assertEquals(agendamento.getRecursos().size(), 1);
		assertEquals(agendamento.getRecursos().get(0).getNome(), "Clorisvaldo");
	}
	
	@Test
	void should_delete() {
		Agendamento agendamento = new Agendamento();
		agendamento.setDataAgendamento(LocalDateTime.now().plusDays(1));
		agendamento.setDataTermino(LocalDateTime.now().plusDays(1).plusMinutes(50));
		agendamentoRepository.save(agendamento);
		long count = agendamentoRepository.count();
		
		agendamentoRepository.delete(agendamento);
		assertEquals(count-1, agendamentoRepository.count());
	}

	@Test
	void should_update() {

		RecursoHumano veterinario = new RecursoHumano();
		veterinario.setCpf("9999999999");
		veterinario.setNome("Clorisvaldo");
		veterinario.setFuncao(Funcao.VETERINARIO);
		veterinario = em.persist(veterinario);
		
		Agendamento agendamento = em.find(Agendamento.class, 1l);
		assertNotNull(agendamento);
		assertEquals(agendamento.getRecursos().size(), 1);
		
		LocalDateTime dataInicio = LocalDateTime.now().plusDays(1);
		LocalDateTime dataFim = dataInicio.plusMinutes(50);
		agendamento.setDataAgendamento(dataInicio);
		agendamento.setDataTermino(dataFim);
		agendamento.setRecursos(Arrays.asList(veterinario));
		agendamento.setTipoServico(TipoServico.COMPLETO);
		
		assertEquals(agendamento.getRecursos().size(), 1);
		assertEquals(agendamento.getRecursos().get(0).getNome(), "Clorisvaldo");
		assertEquals(agendamento.getDataAgendamento(), dataInicio);
		assertEquals(agendamento.getDataTermino(), dataFim);
		
	}
	
	
}
