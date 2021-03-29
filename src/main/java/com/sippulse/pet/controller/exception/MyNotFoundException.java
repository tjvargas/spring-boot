package com.sippulse.pet.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MyNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 6415746203554243699L;

	public MyNotFoundException() {
		super();
	}

	public MyNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyNotFoundException(String message) {
		super(message);
	}

	public MyNotFoundException(Throwable cause) {
		super(cause);
	}
}