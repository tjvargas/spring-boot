package com.sippulse.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sippulse.pet.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	Cliente findByClienteDetalheCpf(String cpf);
}
