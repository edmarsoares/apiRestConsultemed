package com.edmar.apiconsultemed.medico.dto;

import org.hibernate.validator.constraints.Length;

import com.edmar.apiconsultemed.usuario.Usuario;

import lombok.Data;

@Data
public class UsuarioCreateDTO {
	
	private Long id;

	@Length(min=8, max=50, message="O Email de acesso não pode ser vazio")
	private String login;
	
	private String senha;
	
	public Usuario convertToUsuario() {
		Usuario usuario = new Usuario();
		
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		return usuario;
	}
	
}
