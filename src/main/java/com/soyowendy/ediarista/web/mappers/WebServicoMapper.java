package com.soyowendy.ediarista.web.mappers;

import com.soyowendy.ediarista.core.models.Servico;
import com.soyowendy.ediarista.web.dtos.ServicoFormDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class WebServicoMapper {
	public Servico toModel(ServicoFormDTO form) {
		if (form == null) {
			throw new IllegalArgumentException();
		}

		Servico model = new Servico();

		model.setNome(form.getNome());
		model.setValorMinimo(form.getValorMinimo());
		model.setQtdHoras(form.getQtdHoras());
		model.setPorcentagemComissao(form.getPorcentagemComissao());
		model.setHorasQuarto(form.getHorasQuarto());
		model.setValorQuarto(form.getValorQuarto());
		model.setHorasSala(form.getHorasSala());
		model.setValorSala(form.getValorSala());
		model.setHorasBanheiro(form.getHorasBanheiro());
		model.setValorBanheiro(form.getValorBanheiro());
		model.setHorasCozinha(form.getHorasCozinha());
		model.setValorCozinha(form.getValorCozinha());
		model.setHorasQuintal(form.getHorasQuintal());
		model.setValorQuintal(form.getValorQuintal());
		model.setHorasOutros(form.getHorasOutros());
		model.setValorOutros(form.getValorOutros());
		model.setIcone(form.getIcone());
		model.setPosicao(form.getPosicao());
		return model;
	}

	public ServicoFormDTO toForm(Servico model) {
		if (model == null) {
			throw new IllegalArgumentException();
		}

		ServicoFormDTO form = new ServicoFormDTO();

		form.setNome(model.getNome());
		form.setValorMinimo(model.getValorMinimo());
		form.setQtdHoras(model.getQtdHoras());
		form.setPorcentagemComissao(model.getPorcentagemComissao());
		form.setHorasQuarto(model.getHorasQuarto());
		form.setValorQuarto(model.getValorQuarto());
		form.setHorasSala(model.getHorasSala());
		form.setValorSala(model.getValorSala());
		form.setHorasBanheiro(model.getHorasBanheiro());
		form.setValorBanheiro(model.getValorBanheiro());
		form.setHorasCozinha(model.getHorasCozinha());
		form.setValorCozinha(model.getValorCozinha());
		form.setHorasQuintal(model.getHorasQuintal());
		form.setValorQuintal(model.getValorQuintal());
		form.setHorasOutros(model.getHorasOutros());
		form.setValorOutros(model.getValorOutros());
		form.setIcone(model.getIcone());
		form.setPosicao(model.getPosicao());

		return form;
	}
}
