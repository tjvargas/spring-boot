package com.sippulse.pet.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Faz o beutify dos erros de validação, imprimindo como {@link ErroValidacaoFormat}
 * @author tjvar
 *
 */
@RestControllerAdvice
public class ValidacaoHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroValidacaoFormat> handle(MethodArgumentNotValidException exception) {
		List<ErroValidacaoFormat> dto = new ArrayList<>();
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroValidacaoFormat erro = new ErroValidacaoFormat(e.getField(), mensagem);
			dto.add(erro);
		});
		
		return dto;
	}

}
