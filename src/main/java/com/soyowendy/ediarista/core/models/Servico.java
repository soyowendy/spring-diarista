package com.soyowendy.ediarista.core.models;

import com.soyowendy.ediarista.core.enums.Icone;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Servico {
	@EqualsAndHashCode.Include
	@ToString.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 50, nullable = false)
	private String nome;
	@Column(name = "valor_minimo", nullable = false)
	private BigDecimal valorMinimo;
	@Column(name = "qtd_horas", nullable = false)
	private Integer qtdHoras;
	@Column(name = "porcentagem_comissao", nullable = false)
	private BigDecimal porcentagemComissao;
	@Column(name = "horas_quarto", nullable = false)
	private Integer horasQuarto;
	@Column(name = "valor_quarto", nullable = false)
	private BigDecimal valorQuarto;
	@Column(name = "horas_sala", nullable = false)
	private Integer horasSala;
	@Column(name = "valor_sala", nullable = false)
	private BigDecimal valorSala;
	@Column(name = "horas_banheiro", nullable = false)
	private Integer horasBanheiro;
	@Column(name = "valor_banheiro", nullable = false)
	private BigDecimal valorBanheiro;
	@Column(name = "horas_cozinha", nullable = false)
	private Integer horasCozinha;
	@Column(name = "valor_cozinha", nullable = false)
	private BigDecimal valorCozinha;
	@Column(name = "horas_quintal", nullable = false)
	private Integer horasQuintal;
	@Column(name = "valor_quintal", nullable = false)
	private BigDecimal valorQuintal;
	@Column(name = "horas_outros", nullable = false)
	private Integer horasOutros;
	@Column(name = "valor_outros", nullable = false)
	private BigDecimal valorOutros;
	@Column(nullable = false, length = 14)
	@Enumerated(EnumType.STRING)
	private Icone icone;
	@Column(nullable = false)
	private Integer posicao;
}