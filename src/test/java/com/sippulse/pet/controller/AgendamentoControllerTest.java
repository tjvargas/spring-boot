package com.sippulse.pet.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sippulse.pet.controller.form.AgendamentoForm;
import com.sippulse.pet.entity.RecursoFisico;
import com.sippulse.pet.enums.TipoServico;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AgendamentoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	@WithMockUser
	void shouldList() throws Exception {
		this.mockMvc.perform(get("/agendamentos")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser
	void shouldListByVeterinarioOuData() throws Exception {
		this.mockMvc.perform(get("/agendamentos?veterinario=Jumberlâncio&data=2021-05-05")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	void shouldForbiddenList() throws Exception {
		this.mockMvc.perform(get("/agendamentos")).andDo(print()).andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser
	void shouldGetById() throws Exception {
		this.mockMvc.perform(get("/agendamentos/1")).andDo(print()).andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	void clienteShouldViewOwnAgendamentosPublic() throws Exception {
		this.mockMvc.perform(get("/clientes/59682641012/agendamentos")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser
	void shouldNotCreate() throws Exception {
		AgendamentoForm form = new AgendamentoForm();
		form.setDataAgendamento(LocalDateTime.now());
		form.setTempoAtendimentoPrevisto((short) 50);
		form.setTipoServico(TipoServico.BANHO);
		form.setIdPet(1l);
		this.mockMvc.perform(post("/agendamentos/")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(form)))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	@WithMockUser
	void shouldCreate() throws Exception {
		RecursoFisico recurso = new RecursoFisico();
		recurso.setId(1l);
		AgendamentoForm form = new AgendamentoForm();
		form.setDataAgendamento(LocalDateTime.now());
		form.setTempoAtendimentoPrevisto((short) 50);
		form.setTipoServico(TipoServico.BANHO);
		form.setRecursos(Arrays.asList(recurso));
		form.setIdPet(1l);
		this.mockMvc.perform(post("/agendamentos/")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(form)))
			.andExpect(status().isCreated());
	}
	
	@Test
	@WithMockUser
	void shouldNotCreateRecursoAlocado() throws Exception {
		RecursoFisico recurso = new RecursoFisico();
		recurso.setId(1l);
		AgendamentoForm form = new AgendamentoForm();
		LocalDateTime horaInicio = LocalDateTime.of(2021, 05, 05, 05, 00);
		form.setDataAgendamento(horaInicio);
		form.setTempoAtendimentoPrevisto((short) 50);
		form.setTipoServico(TipoServico.BANHO);
		form.setRecursos(Arrays.asList(recurso));
		form.setIdPet(1l);
		this.mockMvc.perform(post("/agendamentos/")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(form)))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	@WithMockUser
	void shouldDelete() throws Exception {
		this.mockMvc.perform(delete("/agendamentos/1"))
				.andDo(print())
				.andExpect(status().isOk());
	}
}

