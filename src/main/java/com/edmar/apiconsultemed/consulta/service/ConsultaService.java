package com.edmar.apiconsultemed.consulta.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edmar.apiconsultemed.agendamento.StatusAgendamento;
import com.edmar.apiconsultemed.consulta.Consulta;
import com.edmar.apiconsultemed.consulta.exception.ConsultaException;
import com.edmar.apiconsultemed.consulta.infraestructure.ConsultaRepository;
import com.edmar.apiconsultemed.infraestructure.GenericRepository;
import com.edmar.apiconsultemed.service.EmailService;
import com.edmar.apiconsultemed.service.ServicoGenerico;
import com.edmar.apiconsultemed.usuario.service.UsuarioService;

@Service
public class ConsultaService extends ServicoGenerico<Consulta, Long> {

	@Autowired
	private ConsultaRepository consultaRepository;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EmailService emailService;

	@Override
	public GenericRepository<Consulta, Long> getRepository() {
		// TODO Auto-generated method stub
		return this.consultaRepository;
	}

	@Transactional(readOnly = true)
	public boolean existeConsultaComHoraEData(final LocalDate dataAgendamento, final LocalTime hora,
			final Long idMedico) {
		boolean existeConsulta = this.consultaRepository.existeConsultaComHoraEData(dataAgendamento, hora, idMedico);

		return existeConsulta;
	}

	@Transactional(readOnly = true)
	public Optional<List<Consulta>> filtroListagem(final LocalDate filtro) {

		if (filtro != null) {
			List<Consulta> ConsultasFiltradas = this.consultaRepository.buscarPorDataAgendamento(filtro);
			return Optional.ofNullable(ConsultasFiltradas);
		}
		List<Consulta> consultas = this.consultaRepository.findAll();

		return Optional.ofNullable(consultas);

	}

	@Transactional
	@Override
	public void salvar(final Consulta consulta) {

		final LocalDate dataAgendamento = consulta.getAgendamento().getDataAgendamento();
		final LocalTime hora = consulta.getAgendamento().getHoraAgendamento();
		final Long idMedico = consulta.getMedico().getId();

		consulta.getAgendamento().setStatus(StatusAgendamento.AGENDADO);

		final boolean existeConsulta = this.existeConsultaComHoraEData(dataAgendamento, hora, idMedico);

		if (existeConsulta) {
			throw new ConsultaException("Já existe uma consulta nesta data e hora para este médico");
		}

		final String emailFromPaciente = consulta.getAgendamento().getPaciente().getPessoa().getUsuario().getLogin();

		this.emailService.sendMail(emailFromPaciente, consulta.getAgendamento());
		this.consultaRepository.save(consulta);

	}

	@Transactional
	public void editar(final Consulta consulta) {

		this.verificarSePodeAtualizar(consulta);
		
		consulta.getAgendamento().setStatus(StatusAgendamento.REAGENDADO);

		final String emailFromPaciente = consulta.getAgendamento().getPaciente().getPessoa().getUsuario().getLogin();

		this.emailService.sendMail(emailFromPaciente, consulta.getAgendamento());
		this.consultaRepository.save(consulta);

	}
	
	/**
	 * Método responsável por verificar se uma consulta em fase de edição pode ser atualizada.
	 * Caso os dados passados sejam na edição sejam os mesmos, a edição será realizada
	 * @param consulta
	 * @throws ConsultaException lança uma exceção caso a consulta ja esteja marcada
	 */
	@Transactional
	public void verificarSePodeAtualizar(Consulta consulta) {
		
		final LocalDate dataAgendamento = consulta.getAgendamento().getDataAgendamento();
		final LocalTime hora = consulta.getAgendamento().getHoraAgendamento();
		final Long idMedico = consulta.getMedico().getId();

		Optional<Consulta> consultaFromDB = this.buscarPorId(consulta.getId());

		final LocalDate dataAgendamentoDB = consultaFromDB.get().getAgendamento().getDataAgendamento();
		final LocalTime horaDB = consultaFromDB.get().getAgendamento().getHoraAgendamento();
		final Long idMedicoDB = consultaFromDB.get().getMedico().getId();

		if (!dataAgendamento.equals(dataAgendamentoDB) || !hora.equals(horaDB) || idMedico != idMedicoDB) {
			final boolean existeConsulta = this.existeConsultaComHoraEData(dataAgendamento, hora, idMedico);

			if (existeConsulta) {
				throw new ConsultaException("Já existe uma consulta nesta data e hora para este médico");
			}
		}
	}

	@Transactional(readOnly = true)
	public List<Consulta> filtrarConsultaPorMedicoComData(final Long idMedico) {
		return this.consultaRepository.filtrarConsultaPorMedicoComData(idMedico);
	}

	@Transactional
	public boolean existePacienteVinculadoAconsulta(final long id) {
		return this.existePacienteVinculadoAconsulta(id);
	}

}
