package com.johnworks.fastmeal.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntidadeEmUsoException extends ResponseStatusException {

	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoException(String mensagem) {
		super(HttpStatus.CONFLICT, mensagem);
	}
	
}
