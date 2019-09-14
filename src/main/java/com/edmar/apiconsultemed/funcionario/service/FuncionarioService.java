/**
 * 
 */
package com.edmar.apiconsultemed.funcionario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.edmar.apiconsultemed.funcionario.Funcionario;
import com.edmar.apiconsultemed.funcionario.infraestructure.FuncionarioRepository;
import com.edmar.apiconsultemed.infraestructure.GenericRepository;
import com.edmar.apiconsultemed.service.ServicoGenerico;
import com.edmar.apiconsultemed.usuario.service.UsuarioService;

@Service
public class FuncionarioService extends ServicoGenerico<Funcionario, Long> {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public GenericRepository<Funcionario, Long> getRepository() {
		// TODO Auto-generated method stub
		return this.funcionarioRepository;
	}

	@Transactional(readOnly = true)
	public Optional<List<Funcionario>> filtroListagem(final String filtro) {

		if (!StringUtils.isEmpty(filtro)) {
			List<Funcionario> funcionariosFiltrados = this.funcionarioRepository.findByPessoaNomeContaining(filtro);
			return Optional.ofNullable(funcionariosFiltrados);
		}
		List<Funcionario>  funcionarios = this.funcionarioRepository.findAll();
		
		return Optional.ofNullable(funcionarios);

	}

	@Transactional
	public String salvarFuncionario(Funcionario funcionario) {
		String mensagemUsuario = this.usuarioService.prepararParaPersistir(funcionario.getPessoa().getUsuario());
		
		if (mensagemUsuario.equals("")) {
			this.funcionarioRepository.save(funcionario);
			return "";
		}
		
		return mensagemUsuario;
	}
}
