package com.edmar.apiconsultemed.pessoa.exception;

public class PessoaNotFoundException extends RuntimeException{
	
	public PessoaNotFoundException( String erro) {
		super(erro);
	}
}
