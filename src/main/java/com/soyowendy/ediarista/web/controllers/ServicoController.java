package com.soyowendy.ediarista.web.controllers;

import com.soyowendy.ediarista.core.enums.Icone;
import com.soyowendy.ediarista.core.models.Servico;
import com.soyowendy.ediarista.core.repositories.ServicoRepository;
import com.soyowendy.ediarista.web.dtos.ServicoFormDTO;
import com.soyowendy.ediarista.web.mappers.WebServicoMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/admin/servicos")
public class ServicoController {
	@Autowired
	private ServicoRepository servicoRepository;

	@Autowired
	private WebServicoMapper mapper;

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView modelAndView = new ModelAndView("admin/servico/form");
		modelAndView.addObject("form", new ServicoFormDTO());
		return modelAndView;
	}

	@PostMapping("/cadastrar")
	public String cadastrar(@Valid @ModelAttribute("form") ServicoFormDTO form, BindingResult result) {
		if (result.hasErrors()) {
			return "admin/servico/form";
		}

		Servico servico = mapper.toModel(form);
		servicoRepository.save(servico);

		return "redirect:/admin/servicos";
	}

	@GetMapping
	public ModelAndView buscarTodos() {
		ModelAndView modelAndView = new ModelAndView("admin/servico/lista");

		modelAndView.addObject("servicos", servicoRepository.findAll());

		return modelAndView;
	}

	@GetMapping("/{id}/editar")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("admin/servico/form");

		Optional<Servico> servico = servicoRepository.findById(id);
		ServicoFormDTO form = mapper.toForm(servico.get());

		modelAndView.addObject("form", form);

		return modelAndView;
	}

	@PostMapping("/{id}/editar")
	public String editar(@PathVariable Long id, @Valid @ModelAttribute("form") ServicoFormDTO form, BindingResult result) {
		if (result.hasErrors()) {
			return "admin/servico/form";
		}
		Servico servico = mapper.toModel(form);

		servico.setId(id);
		servicoRepository.save(servico);

		return "redirect:/admin/servicos";
	}

	@GetMapping("/{id}/excluir")
	public String excluir(@PathVariable Long id) {
		servicoRepository.deleteById(id);

		return "redirect:/admin/servicos";
	}

	@ModelAttribute("icones")
	public Icone[] getIcones() {
		return Icone.values();
	}
}
