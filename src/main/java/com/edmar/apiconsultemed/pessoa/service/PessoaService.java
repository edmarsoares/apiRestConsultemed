package com.edmar.apiconsultemed.pessoa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edmar.apiconsultemed.infraestructure.GenericRepository;
import com.edmar.apiconsultemed.pessoa.Pessoa;
import com.edmar.apiconsultemed.pessoa.infraestructure.PessoaRepository;
import com.edmar.apiconsultemed.service.ServicoGenerico;
import com.edmar.apiconsultemed.usuario.exception.UsuarioException;

@Service
public class PessoaService extends ServicoGenerico<Pessoa, Long> {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	public GenericRepository<Pessoa, Long> getRepository() {
		// TODO Auto-generated method stub
		return this.pessoaRepository;
	}

	@Transactional()
	public void verificarExistenciaCpf(final Pessoa pessoa) {

		String cpfAntigo = "";

		if (pessoa.getId() != null) {
			final Optional<Pessoa> pessoaFromDB = this.buscarPorId(pessoa.getId());
			cpfAntigo = pessoaFromDB.get().getCpf();
		}
		boolean existeCpf = this.pessoaRepository.verificarExistenciaCpf(pessoa.getCpf(), cpfAntigo);

		if (existeCpf) {
			throw new UsuarioException("Já existe um usuário cadastrado com este mesmo cpf");
		}
	}
}
