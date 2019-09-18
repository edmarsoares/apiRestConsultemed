package com.edmar.apiconsultemed.consulta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ConsultaException extends RuntimeException{
	
	public ConsultaException(final String erro) {
		super(erro);
	}
}
