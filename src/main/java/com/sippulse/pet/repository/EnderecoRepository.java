package com.sippulse.pet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sippulse.pet.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	List<Endereco> findByClienteDetalheCpf(String cpf);

	Endereco findByRuaAndNumeroAndComplemento(String rua, String numero, String complemento);
}
