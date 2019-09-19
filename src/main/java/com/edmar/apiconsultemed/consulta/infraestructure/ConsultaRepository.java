package com.edmar.apiconsultemed.consulta.infraestructure;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edmar.apiconsultemed.consulta.Consulta;
import com.edmar.apiconsultemed.infraestructure.GenericRepository;

@Repository
public interface ConsultaRepository extends GenericRepository<Consulta, Long>{
	
	@Query("select case when (count(con) > 0) then true else false end " + 
			" from Consulta con INNER JOIN con.agendamento as agen " + 
			" where agen.dataAgendamento = :dataAgendamento " + 
			" and agen.horaAgendamento = :hora " + 
			" and con.medico.id = :id ")
	boolean existeConsultaComHoraEData(@Param("dataAgendamento") final LocalDate agendamento, @Param("hora") final LocalTime horaAgendamento, 
									   @Param("id") final Long idMedico);
	
	@Query("SELECT c FROM Consulta c INNER JOIN c.agendamento as agen WHERE " + 
			" agen.dataAgendamento = :agendamento")
	List<Consulta> buscarPorDataAgendamento( @Param("agendamento") final LocalDate dataAgendamento);
	
	@Query("SELECT c FROM Consulta c JOIN c.medico as m WHERE m.id = :id")
	List<Consulta> filtrarConsultaPorMedicoComData(@Param("id") final Long id);
	
	@Query("select case when (count(con) > 0) then true else false end " + 
			" from Consulta con INNER JOIN con.agendamento.paciente as p where p.id = :id")
	boolean existePacienteVinculadoAconsulta(@Param("id") final long id);
}
