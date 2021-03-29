package com.sippulse.pet.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.sippulse.pet.entity.Endereco;

public class EnderecoDTO {
	private String rua;
	private String numero;
	private String complemento;
	private String chamarPorQuem;
	
	public EnderecoDTO(Endereco endereco) {
		super();
		this.rua = endereco.getRua();
		this.numero = endereco.getNumero();
		this.complemento = endereco.getComplemento();
		this.chamarPorQuem = endereco.getChamarPorQuem();
	}
	public String getRua() {
		return rua;
	}
	public String getNumero() {
		return numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public String getChamarPorQuem() {
		return chamarPorQuem;
	}
	
	public static List<EnderecoDTO> converter(List<Endereco> enderecos) {
		return enderecos.stream().map(EnderecoDTO::new).collect(Collectors.toList());
	}
}
