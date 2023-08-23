package com.soyowendy.ediarista.web.controllers;

import com.soyowendy.ediarista.core.enums.Icone;
import com.soyowendy.ediarista.core.models.Servico;
import com.soyowendy.ediarista.core.repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/servicos")
public class ServicoController {
	@Autowired
	private ServicoRepository servicoRepository;

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView modelAndView = new ModelAndView("admin/servico/form");
		modelAndView.addObject("servico", new Servico());
		return modelAndView;
	}

	@PostMapping("/cadastrar")
	public String cadastrar(Servico servico) {
		servicoRepository.save(servico);
		return "redirect:/admin/servicos/cadastrar";
	}

	@ModelAttribute("icones")
	public Icone[] getIcones() {
		return Icone.values();
	}
}
