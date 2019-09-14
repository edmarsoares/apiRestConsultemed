package com.edmar.apiconsultemed.funcionario.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edmar.apiconsultemed.funcionario.Funcionario;
import com.edmar.apiconsultemed.funcionario.service.FuncionarioService;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioResource {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Funcionario funcionario){
		this.funcionarioService.salvar(funcionario);	
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping
	public ResponseEntity<?> atualizar(@RequestBody Funcionario funcionario){
		this.funcionarioService.salvar(funcionario);		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping
	public ResponseEntity<List<Funcionario>> listar() { 
		List<Funcionario> funcionarios = this.funcionarioService.listar();
		return ResponseEntity.ok(funcionarios);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> buscarPorId(@PathVariable final long id){
		Optional<Funcionario> funcionario = this.funcionarioService.buscarPorId(id);
		return ResponseEntity.ok(funcionario.get());
	}
	
	@DeleteMapping("/{id}")
	public void remover(@PathVariable final long id) {
		this.funcionarioService.remover(id);
	}
}
