package com.edmar.apiconsultemed.funcionario.infraestructure;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edmar.apiconsultemed.funcionario.Funcionario;
import com.edmar.apiconsultemed.infraestructure.GenericRepository;

@Repository
public interface FuncionarioRepository extends GenericRepository<Funcionario, Long> {
	
	public List<Funcionario> findByPessoaNomeContaining(String nome);
}
