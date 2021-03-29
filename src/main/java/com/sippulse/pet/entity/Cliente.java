package com.sippulse.pet.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Mantém dados históricos como atendimentos e pedidos, não pode ser excluída.
 * <p>
 * Relacionamento intermediário com {@link ClienteDetalhe}
 * @author tjvar
 *
 */
@Entity
public class Cliente extends BaseEntity{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Clinica clinica;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private ClienteDetalhe clienteDetalhe;

	public Cliente(Clinica clinica, ClienteDetalhe clienteDetalhe) {
		super();
		this.clinica = clinica;
		clienteDetalhe.setCliente(this);
		this.clienteDetalhe = clienteDetalhe;
	}

	public Cliente() {
		super();
	}

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}

	public ClienteDetalhe getClienteDetalhe() {
		return clienteDetalhe;
	}

	public void setClienteDetalhe(ClienteDetalhe clienteDetalhe) {
		this.clienteDetalhe = clienteDetalhe;
	}
	
	
}
