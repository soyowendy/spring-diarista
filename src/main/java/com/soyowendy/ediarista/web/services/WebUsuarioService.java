package com.soyowendy.ediarista.web.services;

import com.soyowendy.ediarista.core.enums.TipoUsuario;
import com.soyowendy.ediarista.core.models.Usuario;
import com.soyowendy.ediarista.core.repositories.UsuarioRepository;
import com.soyowendy.ediarista.web.dtos.UsuarioCadastroFormDTO;
import com.soyowendy.ediarista.web.mappers.WebUsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebUsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private WebUsuarioMapper mapper;

	public List<Usuario> buscarTodos() {
		return usuarioRepository.findAll();
	}

	public Usuario cadastrar(UsuarioCadastroFormDTO form) {
		Usuario model = mapper.toModel(form);

		model.setTipoUsuario(TipoUsuario.ADMIN);

		return usuarioRepository.save(model);
	}
}
