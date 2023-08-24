package com.soyowendy.ediarista.web.services;

import com.soyowendy.ediarista.core.models.Usuario;
import com.soyowendy.ediarista.core.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebUsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> buscarTodos() {
		return usuarioRepository.findAll();
	}
}
