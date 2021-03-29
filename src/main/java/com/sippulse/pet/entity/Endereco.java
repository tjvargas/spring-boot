package com.sippulse.pet.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Endereco extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private ClienteDetalhe clienteDetalhe;
	private String rua;
	private String numero;
	private String complemento;
	private String chamarPorQuem;
	
	public Endereco(ClienteDetalhe clienteDetalhe, String rua, String numero, String complemento) {
		super();
		clienteDetalhe.getEnderecos().add(this);
		this.clienteDetalhe = clienteDetalhe;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
	}
	public Endereco() {
		super();
	}
	public ClienteDetalhe getClienteDetalhe() {
		return clienteDetalhe;
	}
	public void setClienteDetalhe(ClienteDetalhe clienteDetalhe) {
		this.clienteDetalhe = clienteDetalhe;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getChamarPorQuem() {
		return chamarPorQuem;
	}
	public void setChamarPorQuem(String chamarPorQuem) {
		this.chamarPorQuem = chamarPorQuem;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(complemento, numero, rua);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Endereco))
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(complemento, other.complemento) && Objects.equals(numero, other.numero) && Objects.equals(rua, other.rua);
	}
	
}
