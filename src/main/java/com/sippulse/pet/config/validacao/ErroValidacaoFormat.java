package com.sippulse.pet.config.validacao;

/**
 * Resumo do erro de validação
 * @author tjvar
 *
 */
public class ErroValidacaoFormat {
	
	private String campo;
	private String erro;
	
	public ErroValidacaoFormat(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}
	
	

}
