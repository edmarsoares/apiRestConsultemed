package com.edmar.apiconsultemed.agendamento.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edmar.apiconsultemed.agendamento.service.AgendamentoService;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoResource {
	
	@Autowired
	private AgendamentoService agendamentoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> cancelarAgendamento(@PathVariable final long id){
		this.agendamentoService.cancelarAgendamento(id);
		return ResponseEntity.ok().build();
	}
	
}
