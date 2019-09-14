package com.edmar.apiconsultemed.agendamento.infraestructure;

import org.springframework.stereotype.Repository;

import com.edmar.apiconsultemed.agendamento.Agendamento;
import com.edmar.apiconsultemed.infraestructure.GenericRepository;

@Repository
public interface AgendamentoRepository extends GenericRepository<Agendamento, Long> {
	
	
}
