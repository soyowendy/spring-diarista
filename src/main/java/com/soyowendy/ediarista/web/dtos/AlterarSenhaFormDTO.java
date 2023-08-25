package com.soyowendy.ediarista.web.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlterarSenhaFormDTO {
	@NotNull
	@NotEmpty
	private String senhaAntiga;
	@NotNull
	@NotEmpty
	private String senha;
	@NotNull
	@NotEmpty
	private String confirmacaoSenha;
}
