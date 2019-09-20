package com.edmar.apiconsultemed.consulta.infraestructure;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ConsultaRepositoryCustom {
	
	boolean existeConsultaComHoraEData(final LocalDate dataAgendamento, final LocalTime hora, final Long idMedico);

}
