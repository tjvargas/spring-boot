package com.sippulse.pet.controller.form;

import com.sippulse.pet.controller.enums.TipoRecurso;
import com.sippulse.pet.entity.Clinica;
import com.sippulse.pet.entity.Recurso;
import com.sippulse.pet.entity.RecursoFisico;
import com.sippulse.pet.entity.RecursoHumano;

public class RecursoFactory {

	protected static Recurso criarPeloForm(RecursoForm recursoForm, Clinica clinica) {

		if (TipoRecurso.FISICO.equals(recursoForm.getTipoRecurso())) {
			
			RecursoFisico rf = new RecursoFisico();

			rf.setCustoHora(recursoForm.getCustoHora());
			rf.setDescricao(recursoForm.getDescricao());
			rf.setNome(recursoForm.getNome());
			rf.setClinica(clinica);
			rf.setDataVencimentoGarantia(recursoForm.getDataVencimentoGarantia());

			return rf;

		} else {
			
			RecursoHumano rh = new RecursoHumano();

			rh.setCustoHora(recursoForm.getCustoHora());
			rh.setDescricao(recursoForm.getDescricao());
			rh.setNome(recursoForm.getNome());
			rh.setClinica(clinica);
			rh.setFuncao(recursoForm.getFuncao());
			rh.setCpf(recursoForm.getCpf());

			return rh;

		}

	}
}
