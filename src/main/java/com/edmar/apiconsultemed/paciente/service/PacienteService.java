
package com.edmar.apiconsultemed.paciente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.edmar.apiconsultemed.consulta.service.ConsultaService;
import com.edmar.apiconsultemed.infraestructure.GenericRepository;
import com.edmar.apiconsultemed.paciente.Paciente;
import com.edmar.apiconsultemed.paciente.exception.PacienteException;
import com.edmar.apiconsultemed.paciente.infraestructure.PacienteRepository;
import com.edmar.apiconsultemed.pessoa.service.PessoaService;
import com.edmar.apiconsultemed.service.ServicoGenerico;
import com.edmar.apiconsultemed.usuario.service.UsuarioService;

/**
 * @author ricardo belo e edmar soares
 *
 */

@Service
public class PacienteService extends ServicoGenerico<Paciente, Long> {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ConsultaService consultaService;

	@Autowired
	private PessoaService pessoaService;

	@Override
	public GenericRepository<Paciente, Long> getRepository() {
		// TODO Auto-generated method stub
		return this.pacienteRepository;
	}

	@Transactional(readOnly = true)
	public Optional<List<Paciente>> filtroListagem(final String filtro) {

		if (!StringUtils.isEmpty(filtro)) {
			List<Paciente> pacientesFiltrados = this.pacienteRepository.findByPessoaNomeContaining(filtro);
			return Optional.ofNullable(pacientesFiltrados);
		}
		List<Paciente> pacientes = this.pacienteRepository.findAll();

		return Optional.ofNullable(pacientes);

	}

	@Transactional
	public void salvar(Paciente paciente) {

		this.usuarioService.prepararParaPersistir(paciente.getPessoa().getUsuario());

		this.pessoaService.verificarExistenciaCpf(paciente.getPessoa());

		this.pacienteRepository.save(paciente);
	}

	@Transactional()
	public String excluirPaciente(final Long id) {

		final boolean existePacienteVinculadoAconsulta = this.consultaService.existePacienteVinculadoAconsulta(id);

		if (existePacienteVinculadoAconsulta) {
			return "Este paciente não pode ser excluído porque está vinculado a uma consulta";
		}

		this.remover(id);
		return "Paciente excluído com sucesso!";
	}
}
