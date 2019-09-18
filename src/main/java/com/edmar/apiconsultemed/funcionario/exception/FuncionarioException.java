package com.edmar.apiconsultemed.funcionario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class FuncionarioException extends RuntimeException{
	
	public FuncionarioException(final String erro) {
		super(erro);
	}
}
