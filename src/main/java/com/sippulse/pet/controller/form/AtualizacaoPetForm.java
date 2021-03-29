package com.sippulse.pet.controller.form;

import com.sippulse.pet.entity.Pet;
import com.sippulse.pet.repository.PetRepository;


public class AtualizacaoPetForm extends PetForm{


	public Pet atualizar(Long id, PetRepository petRepository) {
		Pet pet = petRepository.getOne(id);
		
		pet.setDataNascimento(this.getDataNascimento());
		pet.setNome(this.getNome());
		pet.setRaca(this.getRaca());
		
		return pet;
	}
}
