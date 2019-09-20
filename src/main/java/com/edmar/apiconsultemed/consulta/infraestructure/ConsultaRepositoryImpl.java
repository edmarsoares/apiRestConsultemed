package com.edmar.apiconsultemed.consulta.infraestructure;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class ConsultaRepositoryImpl implements ConsultaRepositoryCustom {
	
	@PersistenceContext
	private EntityManager manager;
	
//	private NamedParameterJdbcOperations jdbcTemplate;

	@Override
	public boolean existeConsultaComHoraEData(LocalDate dataAgendamento, LocalTime hora, Long idMedico) {
		
		final StringBuilder builder = new StringBuilder();
		
		builder.append("select case when (count(con) > 0) then true else false end ")
		.append(" from Consulta con INNER JOIN con.agendamento as agen")
		.append(" where agen.dataAgendamento = :dataAgendamento ")
		.append(" and agen.horaAgendamento = :hora ")
		.append(" and con.medico.id = :id ");
		
		String queryBuilder = builder.toString();
		
		TypedQuery<Boolean> query = manager
				.createQuery(queryBuilder, Boolean.class)
				.setParameter("dataAgendamento", dataAgendamento)
				.setParameter("hora", hora)
				.setParameter("id", idMedico);
		
		// TODO Auto-generated method stub
		return query.getSingleResult();
	}

}
