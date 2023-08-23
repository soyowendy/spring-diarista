package com.soyowendy.ediarista.web.dtos;

import com.soyowendy.ediarista.core.enums.Icone;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServicoFormDTO {
	@NotNull
	@Size(min = 3, max = 50)
	private String nome;
	@NotNull
	@PositiveOrZero
	private BigDecimal valorMinimo;
	@NotNull
	@PositiveOrZero
	private Integer qtdHoras;
	@NotNull
	@PositiveOrZero
	@Max(100)
	private BigDecimal porcentagemComissao;
	@NotNull
	@PositiveOrZero
	private Integer horasQuarto;
	@NotNull
	@PositiveOrZero
	private BigDecimal valorQuarto;
	@NotNull
	@PositiveOrZero
	private Integer horasSala;
	@NotNull
	@PositiveOrZero
	private BigDecimal valorSala;
	@NotNull
	@PositiveOrZero
	private Integer horasBanheiro;
	@NotNull
	@PositiveOrZero
	private BigDecimal valorBanheiro;
	@NotNull
	@PositiveOrZero
	private Integer horasCozinha;
	@NotNull
	@PositiveOrZero
	private BigDecimal valorCozinha;
	@NotNull
	@PositiveOrZero
	private Integer horasQuintal;
	@NotNull
	@PositiveOrZero
	private BigDecimal valorQuintal;
	@NotNull
	@PositiveOrZero
	private Integer horasOutros;
	@NotNull
	@PositiveOrZero
	private BigDecimal valorOutros;
	@NotNull
	private Icone icone;
	@NotNull
	@Positive
	private Integer posicao;
}
