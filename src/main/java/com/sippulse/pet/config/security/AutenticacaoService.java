package com.sippulse.pet.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sippulse.pet.entity.Usuario;
import com.sippulse.pet.repository.UsuarioRepository;


@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = this.usuarioRepository.findByLogin(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Dados inválidos!");
		}
		return usuario;
	}

}
