package com.edmar.apiconsultemed.pessoa.infraestructure;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edmar.apiconsultemed.infraestructure.GenericRepository;
import com.edmar.apiconsultemed.paciente.Paciente;
import com.edmar.apiconsultemed.pessoa.Pessoa;

@Repository
public interface PessoaRepository extends GenericRepository<Pessoa, Long> {
	
	public List<Pessoa> findByNomeContaining(String nome);
	                                                                                                                                                                        
	@Query("select case when (count(p.id) > 0) then true else false end from Pessoa p where p.cpf= :cpf "
			+ "  and p.cpf != :cpfAntigo")
	boolean verificarExistenciaCpf( @Param("cpf") final String cpf, @Param("cpfAntigo") final String cpfAntigo);
}
