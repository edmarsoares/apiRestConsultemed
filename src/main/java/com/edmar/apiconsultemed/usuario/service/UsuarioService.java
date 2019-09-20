/**
 * 
 */
package com.edmar.apiconsultemed.usuario.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edmar.apiconsultemed.agendamento.Agendamento;
import com.edmar.apiconsultemed.infraestructure.GenericRepository;
import com.edmar.apiconsultemed.service.ServicoGenerico;
import com.edmar.apiconsultemed.usuario.Usuario;
import com.edmar.apiconsultemed.usuario.exception.UsuarioException;
import com.edmar.apiconsultemed.usuario.infraestructure.UsuarioRepository;

@Service
public class UsuarioService extends ServicoGenerico<Usuario, Long> {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
    @Autowired 
    private JavaMailSender mailSender;
    
    @Value("${spring.mail.username}")
    private String emailSistema;

	@Transactional
	public boolean verificarExistenciaLogin(final String login, final String loginAntigo) {
		return this.usuarioRepository.verificarExistenciaLogin(login, loginAntigo); 
	}
	
	@Transactional
	public void prepararParaPersistir(final Usuario usuario) {
		String loginAntigo = "";
		
		if (usuario.getId() != null) {
			
			final Optional<Usuario> usuarioDB  = this.buscarPorId(usuario.getId());

			loginAntigo = usuarioDB.get().getLogin();
		}
		
		final boolean existeLogin = this.usuarioRepository.verificarExistenciaLogin(usuario.getLogin(), loginAntigo);
		
		if (existeLogin) {
			throw new UsuarioException("Já existe um usuário com este login");
		}
		
	}
	
	@Override
	public GenericRepository<Usuario, Long> getRepository() {
		// TODO Auto-generated method stub
		return this.usuarioRepository;
	}

}
