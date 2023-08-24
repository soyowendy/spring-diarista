package com.soyowendy.ediarista.core.models;

import com.soyowendy.ediarista.core.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Usuario {
	@EqualsAndHashCode.Include
	@ToString.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nome_completo", nullable = false)
	private String nomeCompleto;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String senha;
	@Column(name = "tipo_usuario", length = 8, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;
}
