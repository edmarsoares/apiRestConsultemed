package com.edmar.apiconsultemed.medico.infraestructure;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edmar.apiconsultemed.infraestructure.GenericRepository;
import com.edmar.apiconsultemed.medico.Medico;

@Repository
public interface MedicoRepository extends GenericRepository<Medico, Long> {
	
	public List<Medico> findByPessoaNomeContaining(String nome);

}
