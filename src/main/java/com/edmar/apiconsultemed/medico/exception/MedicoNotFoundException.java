package com.edmar.apiconsultemed.medico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class MedicoNotFoundException extends RuntimeException{
	
	public MedicoNotFoundException(String erro) {
		super(erro);
	}
}
