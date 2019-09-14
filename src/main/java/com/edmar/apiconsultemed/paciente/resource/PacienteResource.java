package com.edmar.apiconsultemed.paciente.resource;

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

import com.edmar.apiconsultemed.paciente.Paciente;
import com.edmar.apiconsultemed.paciente.dto.PacienteFiltroBuscaDto;
import com.edmar.apiconsultemed.paciente.service.PacienteService;

/**
 * @author ricardo belo
 *
 */

@Controller
@RequestMapping("/paciente")
public class PacienteResource {

	private static final String PAGES_NOVO_PACIENTE = "pages/paciente/novo_paciente";

	private static final String PAGES_CONTATO_LISTAGEM = "pages/paciente/pacientes";

	@Autowired
	private PacienteService pacienteService;

	@GetMapping
	public ModelAndView listar(@ModelAttribute("filtro") PacienteFiltroBuscaDto filtro) {
		ModelAndView mv = new ModelAndView(PAGES_CONTATO_LISTAGEM);
		Optional<List<Paciente>> pacientes = this.pacienteService.filtroListagem(filtro.getNome());
		mv.addObject("pacientes",pacientes.get());

		return mv; 
	}

	@GetMapping("/delete/{id}")
	public ModelAndView excluir(@PathVariable Long id, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/paciente");
		this.pacienteService.remover(id);
		attributes.addFlashAttribute("removido", "Paciente removido com sucesso!");
		return mv;
	}

//	@GetMapping("/edit/{id}")
//	public ModelAndView edit(@PathVariable("id") Long id) {
//		final Paciente paciente = this.pacienteService.buscarPorId(id);
//		return novo(paciente);
//	}

	@GetMapping("/novo")
	public ModelAndView novo(Paciente paciente) {
		ModelAndView mv = new ModelAndView(PAGES_NOVO_PACIENTE);
		mv.addObject("paciente", paciente);
		return mv;
	}

	@PostMapping("/save")
	public ModelAndView salvar(@Valid Paciente paciente, BindingResult result ,Model model,
			RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/paciente");

		if (result.hasErrors()) {
			return novo(paciente);
		}
		//Caso exista um usuário com mesmo login, uma mensagem é enviada para a view
    	final String mensagemusuario=  this.pacienteService.salvarPaciente(paciente);
    	
    	if (mensagemusuario.equals("")) {
    		attributes.addFlashAttribute("mensagem", "Paciente salvo com sucesso");
    		return mv;
		}
		attributes.addFlashAttribute("mensagemErro", mensagemusuario);

		return new ModelAndView("redirect:/paciente/novo");
	}
}
