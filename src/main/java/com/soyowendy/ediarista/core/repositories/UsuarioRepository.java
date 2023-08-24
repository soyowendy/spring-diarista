package com.soyowendy.ediarista.core.repositories;

import com.soyowendy.ediarista.core.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String email);
}
