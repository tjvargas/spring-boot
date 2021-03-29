package com.sippulse.pet.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sippulse.pet.controller.form.PetForm;
import com.sippulse.pet.entity.Pet;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PetControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	void test() throws Exception {
		this.mockMvc.perform(get("/pets/")).andDo(print()).andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	@WithMockUser
	void shouldGetById() throws Exception {
		this.mockMvc.perform(get("/pets/1")).andDo(print()).andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("nome", is("kioko"))); 
	}
	
	@Test
	@WithMockUser
	void shouldCreate() throws Exception {
		PetForm pet = new PetForm();
		pet.setDataNascimento(LocalDate.now());
		pet.setRaca("poodle");
		pet.setIdCliente(1l);
		pet.setNome("Xaiane");
		this.mockMvc.perform(post("/pets/")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(pet)))
			.andExpect(status().isCreated())
	    	.andExpect(jsonPath("nome", is("Xaiane"))); 
	}
	
	@Test
	@WithMockUser
	void shouldNotCreate() throws Exception {
		PetForm pet = new PetForm();
		pet.setDataNascimento(LocalDate.now());
		pet.setIdCliente(1l);
		this.mockMvc.perform(post("/pets/")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(pet)))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	@WithMockUser
	void shouldDelete() throws Exception {
		this.mockMvc.perform(delete("/pets/1"))
				.andDo(print())
				.andExpect(status().isOk());
	}
}

