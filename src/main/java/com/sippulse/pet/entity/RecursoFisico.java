
package com.sippulse.pet.entity;

import java.time.LocalDate;

import javax.persistence.Entity;

import com.sippulse.pet.enums.TipoEquipamento;

@Entity
public class RecursoFisico extends Recurso{

	private static final long serialVersionUID = -1421029408927283841L;
	
	private LocalDate dataVencimentoGarantia;
	private TipoEquipamento tipoEquipamento;

	public LocalDate getDataVencimentoGarantia() {
		return dataVencimentoGarantia;
	}

	public void setDataVencimentoGarantia(LocalDate dataVencimentoGarantia) {
		this.dataVencimentoGarantia = dataVencimentoGarantia;
	}

	public TipoEquipamento getTipoEquipamento() {
		return tipoEquipamento;
	}

	public void setTipoEquipamento(TipoEquipamento tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}
	 

}
