package com.soyowendy.ediarista.web.services;

import com.soyowendy.ediarista.core.exceptions.ServicoNaoEncontradoException;
import com.soyowendy.ediarista.core.models.Servico;
import com.soyowendy.ediarista.core.repositories.ServicoRepository;
import com.soyowendy.ediarista.web.dtos.ServicoFormDTO;
import com.soyowendy.ediarista.web.mappers.WebServicoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WebServicoService {
	@Autowired
	private ServicoRepository servicoRepository;

	@Autowired
	private WebServicoMapper mapper;

	public List<Servico> buscarTodos() {
		return servicoRepository.findAll();
	}

	public Servico cadastrar(ServicoFormDTO form) {
		Servico model = mapper.toModel(form);

		return servicoRepository.save(model);
	}

	public Servico buscarPorId(Long id) {
		Optional<Servico> servicoEncontrado = servicoRepository.findById(id);

		if (servicoEncontrado.isPresent()) {
			return servicoEncontrado.get();
		}

		String mensagemDeErro = String.format("Servico com ID %d não encontrado", id);
		throw new ServicoNaoEncontradoException(mensagemDeErro);
	}

	public Servico editar(ServicoFormDTO form, Long id) {
		Servico servicoEncontrado = buscarPorId(id);

		Servico model = mapper.toModel(form);
		model.setId(servicoEncontrado.getId());

		return servicoRepository.save(model);
	}

	public void excluirPorId(Long id) {
		Servico servicoEncontrado = buscarPorId(id);

		servicoRepository.delete(servicoEncontrado);
	}
}
