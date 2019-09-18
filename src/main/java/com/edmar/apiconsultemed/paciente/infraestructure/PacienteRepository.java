package com.edmar.apiconsultemed.paciente.infraestructure;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edmar.apiconsultemed.infraestructure.GenericRepository;
import com.edmar.apiconsultemed.paciente.Paciente;

/**
 * @author ricardo belo
 *
 */

@Repository
public interface PacienteRepository extends GenericRepository<Paciente, Long> {
	
	public List<Paciente> findByPessoaNomeContaining(String nome);
	                                                                                                                                                                        
}

