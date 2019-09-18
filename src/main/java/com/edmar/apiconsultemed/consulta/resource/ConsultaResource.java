package com.edmar.apiconsultemed.consulta.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edmar.apiconsultemed.agendamento.service.AgendamentoService;
import com.edmar.apiconsultemed.consulta.Consulta;
import com.edmar.apiconsultemed.consulta.dto.FiltroDataDto;
import com.edmar.apiconsultemed.consulta.service.ConsultaService;
import com.edmar.apiconsultemed.medico.Medico;
import com.edmar.apiconsultemed.medico.dto.MedicoCreateDTO;
import com.edmar.apiconsultemed.medico.service.MedicoService;
import com.edmar.apiconsultemed.paciente.Paciente;
import com.edmar.apiconsultemed.paciente.service.PacienteService;
@RestController
@RequestMapping("api/consultas")
public class ConsultaResource {
	
	@Autowired
	private ConsultaService consultaService;
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Consulta consulta){
		this.consultaService.salvar(consulta);	
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping
	public ResponseEntity<?> atualizar(@RequestBody Consulta consulta){
		this.consultaService.salvar(consulta);		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping
	public ResponseEntity<List<Consulta>> listar() { 
		List<Consulta> consultas = this.consultaService.listar();
		return ResponseEntity.ok(consultas);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Consulta> buscarPorId(@PathVariable final long id){
		Optional<Consulta> consulta = this.consultaService.buscarPorId(id);
		return ResponseEntity.ok(consulta.get());
	}
	
	@DeleteMapping("/{id}")
	public void remover(@PathVariable final long id) {
		this.consultaService.remover(id);
	}
}
