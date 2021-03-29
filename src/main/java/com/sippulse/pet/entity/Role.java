package com.sippulse.pet.entity;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role extends BaseEntity implements GrantedAuthority {

	private static final long serialVersionUID = -9114639546816593340L;
	
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String getAuthority() {
		return this.nome;
	}
	
}
