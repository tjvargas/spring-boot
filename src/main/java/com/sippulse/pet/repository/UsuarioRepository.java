package com.sippulse.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sippulse.pet.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Usuario findByLogin(String username);

}
