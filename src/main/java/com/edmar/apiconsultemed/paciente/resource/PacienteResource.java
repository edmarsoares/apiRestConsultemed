package com.edmar.apiconsultemed.paciente.resource;

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

import com.edmar.apiconsultemed.paciente.Paciente;
import com.edmar.apiconsultemed.paciente.service.PacienteService;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteResource {
	
	@Autowired
	private PacienteService pacienteService;
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Paciente paciente){
		this.pacienteService.salvar(paciente);	
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping
	public ResponseEntity<?> atualizar(@RequestBody Paciente paciente){
		this.pacienteService.salvar(paciente);		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping
	public ResponseEntity<List<Paciente>> listar() { 
		List<Paciente> pacientes = this.pacienteService.listar();
		return ResponseEntity.ok(pacientes);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Paciente> buscarPorId(@PathVariable final long id){
		Optional<Paciente> paciente = this.pacienteService.buscarPorId(id);
		return ResponseEntity.ok(paciente.get());
	}
	
	@DeleteMapping("/{id}")
	public void remover(@PathVariable final long id) {
		this.pacienteService.remover(id);
	}
}
