package com.sippulse.pet.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
/**
 * <B>LGPD</B>
 * <p>
 * Detalha um {@link Cliente}
 * <p>
 * Pode ser deletada quando um cliente pede as exclusões de todos os seus dados. Deixando o {@link Cliente} para fins de histórico
 * @author tjvar
 *
 */
@Entity
public class ClienteDetalhe extends BaseEntity{

	private static final long serialVersionUID = 1L;

	@OneToOne(mappedBy = "clienteDetalhe")
	private Cliente cliente;
	
	private String nome;
	private String telefone;
	@Column(unique = true)
	private String cpf;
	private String email;
	@OneToMany(mappedBy = "clienteDetalhe", cascade = CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
}
