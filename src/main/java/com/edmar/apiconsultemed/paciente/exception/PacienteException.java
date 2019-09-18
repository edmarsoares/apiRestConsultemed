package com.edmar.apiconsultemed.paciente.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class PacienteException extends RuntimeException{
	
	public PacienteException(final String erro) {
		super(erro);
	}
}
