package br.com.casadocodigo.loja.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.RoleDAO;
import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidator;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO dao;
	
	@Autowired
	private RoleDAO roleDAO;
	
	
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
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listar() {
		List<Usuario> usuarios = dao.listar();
		ModelAndView modelAndView = new ModelAndView("usuarios/lista");
		modelAndView.addObject("usuarios", usuarios);
		return modelAndView;
	}
	
	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") long id){
		ModelAndView modelAndView = new ModelAndView("usuarios/roles");
	    
//	    Recupera o usuario e suas roles
		Usuario usuario = dao.findUser(id);
	    List<Role> rolesDisponiveis = roleDAO.listAll();
	    
			
	    	List<Role> checkedRoles = usuario.getRoles();		
	    	usuario.setRoles(checkedRoles);
		
		
		List<Role> roles = new ArrayList<Role>();
		for (Role role:rolesDisponiveis){
			roles.add(role);
		}
		
		modelAndView.addObject("usuario", usuario);
		modelAndView.addObject("roles", roles);
	    return modelAndView;
	}
	
	@RequestMapping(value="lista")
	public ModelAndView atualizarRoles(Usuario usuario){
		ModelAndView modelAndView = new ModelAndView("usuarios/lista");
		
		List<Role> rolesSelecionadas = usuario.getRoles();
		Usuario usuarioSelecionado = dao.findUser(usuario.getId());
		usuarioSelecionado.setRoles(rolesSelecionadas);
		
		System.out.println("usuario com novas roles selecionadas: " + usuario.getRoles().toString());
		
		dao.update(usuarioSelecionado);
		
		List<Usuario> usuarios = dao.listar();
		modelAndView.addObject("usuarios", usuarios );
		return modelAndView;
	}
	

}
