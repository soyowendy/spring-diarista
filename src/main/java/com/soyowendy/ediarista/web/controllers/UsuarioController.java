package com.soyowendy.ediarista.web.controllers;

import com.soyowendy.ediarista.web.dtos.FlashMessageDTO;
import com.soyowendy.ediarista.web.dtos.UsuarioCadastroFormDTO;
import com.soyowendy.ediarista.web.services.WebUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView modelAndView = new ModelAndView("admin/usuario/cadastro-form");
		modelAndView.addObject("cadastroForm", new UsuarioCadastroFormDTO());
		return modelAndView;
	}

	@PostMapping("/cadastrar")
	public String cadastrar(@Valid @ModelAttribute("cadastroForm") UsuarioCadastroFormDTO form,
	                        BindingResult result,
	                        RedirectAttributes attrs) {
		if (result.hasErrors()) {
			return "admin/usuario/cadastro-form";
		}

		usuarioService.cadastrar(form);
		attrs.addFlashAttribute("alert", new FlashMessageDTO("alert-success", "Usuário cadastrado com sucesso!"));

		return "redirect:/admin/usuarios";
	}

	@GetMapping("/{id}/excluir")
	public String excluir(@PathVariable Long id, RedirectAttributes attrs) {
		usuarioService.excluirPorId(id);
		attrs.addFlashAttribute("alert", new FlashMessageDTO("alert-success", "Usuário excluído com sucesso"));
		return "redirect:/admin/usuarios";
	}
}
