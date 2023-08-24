package com.soyowendy.ediarista.auth.services;

import com.soyowendy.ediarista.auth.models.UsuarioAutenticado;
import com.soyowendy.ediarista.core.models.Usuario;
import com.soyowendy.ediarista.core.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		String mensagem = String.format("Usuário com email %s não encontrado", email);

		return usuarioRepository.findByEmail(email)
				.map(UsuarioAutenticado::new)
				.orElseThrow(() -> new UsernameNotFoundException(mensagem));
	}
}
