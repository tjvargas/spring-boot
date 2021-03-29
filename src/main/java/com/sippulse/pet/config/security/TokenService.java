package com.sippulse.pet.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.sippulse.pet.entity.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${pet.jwt.expiration}")
	private String exp;

	@Value("${pet.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authenticate) {
		Usuario sub = (Usuario) authenticate.getPrincipal();
		Date dataExpiracao = new Date(new Date().getTime() + Long.parseLong(exp));
		return Jwts.builder().setIssuer("API").setSubject(sub.getId().toString()).setIssuedAt(new Date()).setExpiration(dataExpiracao).signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public Long getId(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
}
