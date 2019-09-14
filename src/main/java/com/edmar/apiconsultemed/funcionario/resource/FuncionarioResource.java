package com.edmar.apiconsultemed.funcionario.resource;

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

import com.edmar.apiconsultemed.funcionario.Funcionario;
import com.edmar.apiconsultemed.funcionario.dto.FuncionarioFiltroBuscaDto;
import com.edmar.apiconsultemed.funcionario.service.FuncionarioService;


@Controller
@RequestMapping("/funcionario")
public class FuncionarioResource {

	private static final String PAGES_NOVO_FUNCIONARIO = "pages/funcionario/novo_funcionario";

	private static final String PAGES_CONTATO_LISTAGEM = "pages/funcionario/funcionarios";

	@Autowired
	private FuncionarioService funcionarioService;

	@GetMapping
	public ModelAndView listar(@ModelAttribute("filtro") FuncionarioFiltroBuscaDto filtro) {
		ModelAndView mv = new ModelAndView(PAGES_CONTATO_LISTAGEM);
		Optional<List<Funcionario>> funcionarios = this.funcionarioService.filtroListagem(filtro.getNome());
		mv.addObject("funcionarios",funcionarios.get());

		return mv; 
	}

	@GetMapping("/delete/{id}")
	public ModelAndView excluir(@PathVariable Long id, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/funcionario");
		this.funcionarioService.remover(id);
		attributes.addFlashAttribute("removido", "Funcionario removido com sucesso!");
		return mv;
	}

//	@GetMapping("/edit/{id}")
//	public ModelAndView edit(@PathVariable("id") Long id) {
//		final Funcionario funcionario = this.funcionarioService.buscarPorId(id);
//		return novo(funcionario);
//	}

	@GetMapping("/novo")
	public ModelAndView novo(Funcionario funcionario) {
		ModelAndView mv = new ModelAndView(PAGES_NOVO_FUNCIONARIO);
		mv.addObject("funcionario", funcionario);
		return mv;
	}

	@PostMapping("/save")
	public ModelAndView salvar(@Valid Funcionario funcionario, BindingResult result,Model model,
			RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/funcionario");

		if (result.hasErrors()) {
			return novo(funcionario);
		}
		//Caso exista um usuário com mesmo login, uma mensagem é enviada para a view
    	final String mensagemusuario=  this.funcionarioService.salvarFuncionario(funcionario);
    	
    	if (mensagemusuario.equals("")) {
    		attributes.addFlashAttribute("mensagem", "Funcionario salvo com sucesso");
    		return mv;
		}
		attributes.addFlashAttribute("mensagemErro", mensagemusuario);

		return new ModelAndView("redirect:/funcionario/novo");
	}
}
