package com.edmar.apiconsultemed.agendamento.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AgendamentoException extends RuntimeException{
	
	public AgendamentoException(final String erro) {
		super(erro);
	}
}
