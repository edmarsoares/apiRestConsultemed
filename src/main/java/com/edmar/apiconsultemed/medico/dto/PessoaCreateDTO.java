package com.edmar.apiconsultemed.medico.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.edmar.apiconsultemed.usuario.Pessoa;

import lombok.Data;
@Data
public class PessoaCreateDTO {
	
	@Length(min=3, max=20, message="O nome não pode ser vazio")
	private String nome;
	
	private String telefone;
	
	@CPF(message = "Entre com um CPF valido!")
	private String cpf;
		
	private UsuarioCreateDTO usuario;

	public Pessoa convertToPessoa() {
		
		Pessoa p = new Pessoa();
		
		p.setNome(this.nome);
		p.setCpf(this.cpf);
		p.setTelefone(this.telefone);
		p.setUsuario(usuario.convertToUsuario());
		
		return p;
	};
}
