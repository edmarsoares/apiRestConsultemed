/**
 * 
 */
package com.edmar.apiconsultemed.medico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.edmar.apiconsultemed.consulta.service.ConsultaService;
import com.edmar.apiconsultemed.infraestructure.GenericRepository;
import com.edmar.apiconsultemed.medico.Medico;
import com.edmar.apiconsultemed.medico.infraestructure.MedicoRepository;
import com.edmar.apiconsultemed.service.ServicoGenerico;
import com.edmar.apiconsultemed.usuario.service.UsuarioService;

@Service
public class MedicoService extends ServicoGenerico<Medico, Long> {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ConsultaService consultaService;
	
	@Override
	public GenericRepository<Medico, Long> getRepository() {
		// TODO Auto-generated method stub
		return this.medicoRepository;
	}
	
	@Transactional(readOnly = true)
	public Optional<List<Medico>> filtroListagem(final String filtro) {

		if (!StringUtils.isEmpty(filtro)) {
			List<Medico> medicosFiltrados = this.medicoRepository.findByPessoaNomeContaining(filtro);
			return Optional.ofNullable(medicosFiltrados);
		}
		List<Medico>  medicos = this.medicoRepository.findAll();
		
		return Optional.ofNullable(medicos);

	}

	@Transactional
	@Override
	public void salvar(final Medico medico) {
		this.usuarioService.prepararParaPersisti(medico.getPessoa().getUsuario());
		this.medicoRepository.save(medico);		
	}
	
}
