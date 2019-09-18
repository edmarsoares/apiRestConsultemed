package com.edmar.apiconsultemed.medico.resource;

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

import com.edmar.apiconsultemed.medico.Medico;
import com.edmar.apiconsultemed.medico.dto.MedicoCreateDTO;
import com.edmar.apiconsultemed.medico.service.MedicoService;

@RestController
@RequestMapping("api/medicos")
public class MedicoResource {
	
	@Autowired
	private MedicoService medicoService;
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody MedicoCreateDTO medicoDto){
		Medico medico = medicoDto.converteToMedico();
		this.medicoService.salvar(medico);	
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping
	public ResponseEntity<?> atualizar(@RequestBody Medico medico){
		this.medicoService.salvar(medico);		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping
	public ResponseEntity<List<Medico>> listar() { 
		List<Medico> medicos = this.medicoService.listar();
		return ResponseEntity.ok(medicos);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Medico> buscarPorId(@PathVariable final long id){
		Optional<Medico> medico = this.medicoService.buscarPorId(id);
		return ResponseEntity.ok(medico.get());
	}
	
	@DeleteMapping("/{id}")
	public void remover(@PathVariable final long id) {
		this.medicoService.remover(id);
	}
}
