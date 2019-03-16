package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidator;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO dao;
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new UsuarioValidator());
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView gravar(@Validated Usuario usuario, BindingResult result, 
				RedirectAttributes redirectAttributes){
		
		
		if(!result.hasErrors()) {
			if (usuario.getPassword().equals(usuario.getRetypePassword())) {
				usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
				dao.gravar(usuario);
				redirectAttributes.addFlashAttribute("message", "Usuário cadastrado com sucesso!");
				return new ModelAndView("redirect:/usuarios");
			}
			
		}
		
		return form(usuario);
		
		
	}
	
	@RequestMapping("/form")
	public ModelAndView form(Usuario usuario) {
		ModelAndView modelAndView = new ModelAndView("usuarios/form");
		return modelAndView;
	}
	
	@RequestMapping( method=RequestMethod.GET)
	public ModelAndView listar() {
		List<Usuario> usuarios = dao.listar();
		ModelAndView modelAndView = new ModelAndView("usuarios/lista");
		modelAndView.addObject("usuarios", usuarios);
		return modelAndView;
	}

}
