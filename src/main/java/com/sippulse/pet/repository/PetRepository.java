package com.sippulse.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sippulse.pet.entity.Pet;

public interface PetRepository extends JpaRepository<Pet, Long>{
}
