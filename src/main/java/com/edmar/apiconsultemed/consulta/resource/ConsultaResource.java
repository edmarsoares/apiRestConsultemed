package com.edmar.apiconsultemed.consulta.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edmar.apiconsultemed.agendamento.service.AgendamentoService;
import com.edmar.apiconsultemed.consulta.Consulta;
import com.edmar.apiconsultemed.consulta.dto.FiltroDataDto;
import com.edmar.apiconsultemed.consulta.service.ConsultaService;
import com.edmar.apiconsultemed.medico.Medico;
import com.edmar.apiconsultemed.medico.service.MedicoService;
import com.edmar.apiconsultemed.paciente.Paciente;
import com.edmar.apiconsultemed.paciente.service.PacienteService;
@Controller
@RequestMapping("/consulta")
public class ConsultaResource {
	
	private static final String PAGES_NOVO_CONSULTA = "pages/consulta/nova_consulta";

	private static final String PAGES_CONSULTA_LISTAGEM = "pages/consulta/consultas";

	@Autowired
	private ConsultaService consultaService;
	
	@Autowired
	private MedicoService medicoService;
	
	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private AgendamentoService agendamentoService;

	@GetMapping("/cancelarAgendamento/{id}")
	public ModelAndView cancelarConsulta(@PathVariable Long id, RedirectAttributes attributes ) {
		ModelAndView mv = new ModelAndView("redirect:/consulta");
		final String mensagemCancelamento = this.agendamentoService.cancelarAgendamento(id);
		mv.addObject("mensagem",mensagemCancelamento);

		return mv;
	}
	
	@GetMapping()
	public ModelAndView listar(@ModelAttribute("filtro") FiltroDataDto filtro) {
		ModelAndView mv = new ModelAndView(PAGES_CONSULTA_LISTAGEM);
		Optional<List<Consulta>> consultas = this.consultaService.filtroListagem(filtro.getDataAgendamento());
		mv.addObject("consultas",consultas.get());

		return mv; 
	}

	@GetMapping("/delete/{id}")
	public ModelAndView excluir(@PathVariable Long id, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/consulta");
		this.consultaService.remover(id);
		attributes.addFlashAttribute("removido", "Consulta agendada com sucesso!");
		return mv;
	}

//	@GetMapping("/edit/{id}")
//	public ModelAndView edit(@PathVariable("id") Long id) {
//		final Consulta consulta = this.consultaService.buscarPorId(id);
//		return novo(consulta);
//	}

	@GetMapping("/novo")
	public ModelAndView novo(Consulta consulta) {
		ModelAndView mv = new ModelAndView(PAGES_NOVO_CONSULTA);
		List<Medico> medicos = this.medicoService.listar();
		List<Paciente> pacientes = this.pacienteService.listar();
		
		mv.addObject("pacientes", pacientes);
		mv.addObject("medicos", medicos);
		mv.addObject("consulta", consulta);
		return mv;
	}

	@PostMapping("/save")
	public ModelAndView salvar(@Valid Consulta consulta, BindingResult result,Model model, 
			RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/consulta");

		if (result.hasErrors()) {
			return novo(consulta);
		}
		//Caso exista uma consulta com data,hora e médico informado, uma mensagem é enviada para a view
    	final String mensagemConsulta =  this.consultaService.salvarConsulta(consulta);
    	
    	if (mensagemConsulta.equals("")) {
    		attributes.addFlashAttribute("mensagem", "Consulta Agendada!");
    		return mv;
		}
		attributes.addFlashAttribute("mensagemErro", mensagemConsulta);

		return new ModelAndView("redirect:/consulta/novo");
	}
}
