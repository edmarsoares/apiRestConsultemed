package com.edmar.apiconsultemed.usuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UsuarioException extends RuntimeException {
	
	public UsuarioException(String erro) {
		super(erro);
	}
}
