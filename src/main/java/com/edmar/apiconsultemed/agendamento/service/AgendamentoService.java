package com.edmar.apiconsultemed.agendamento.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edmar.apiconsultemed.agendamento.Agendamento;
import com.edmar.apiconsultemed.agendamento.StatusAgendamento;
import com.edmar.apiconsultemed.agendamento.exception.AgendamentoException;
import com.edmar.apiconsultemed.agendamento.infraestructure.AgendamentoRepository;
import com.edmar.apiconsultemed.consulta.Consulta;
import com.edmar.apiconsultemed.infraestructure.GenericRepository;
import com.edmar.apiconsultemed.service.ServicoGenerico;

@Service
public class AgendamentoService extends ServicoGenerico<Agendamento, Long>{
	
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@Override
	public GenericRepository<Agendamento, Long> getRepository() {
		// TODO Auto-generated method stub
		return agendamentoRepository;
	}

	@Transactional
	public void cancelarAgendamento(final Long id) {
		// TODO Auto-generated method stub
		final Optional<Agendamento> agendamentoFromDB = this.buscarPorId(id);

		if (agendamentoFromDB == null) {
			throw new AgendamentoException("Não existe um agendamento com este identificador!");
		}

		agendamentoFromDB.get().setStatus(StatusAgendamento.CANCELADO);

		this.salvar(agendamentoFromDB.get());

	}

}
