package com.sippulse.pet.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MyBadRequestException extends RuntimeException {
	private static final long serialVersionUID = 6415746203554243699L;

	public MyBadRequestException() {
		super();
	}

	public MyBadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyBadRequestException(String message) {
		super(message);
	}

	public MyBadRequestException(Throwable cause) {
		super(cause);
	}
}