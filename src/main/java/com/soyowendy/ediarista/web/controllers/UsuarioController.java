package com.soyowendy.ediarista.web.controllers;

import com.soyowendy.ediarista.web.services.WebUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/usuarios")
public class UsuarioController {
	@Autowired
	private WebUsuarioService usuarioService;

	@GetMapping
	public ModelAndView buscarTodos() {
		ModelAndView modelAndView = new ModelAndView("admin/usuario/lista");
		modelAndView.addObject("usuarios", usuarioService.buscarTodos());
		return modelAndView;
	}
}
