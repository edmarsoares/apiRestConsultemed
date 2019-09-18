package com.edmar.apiconsultemed.medico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class MedicoException extends RuntimeException{
	
	public MedicoException(String erro) {
		super(erro);
	}
}
