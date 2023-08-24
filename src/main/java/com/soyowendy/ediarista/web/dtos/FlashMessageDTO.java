package com.soyowendy.ediarista.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlashMessageDTO {
	private String classeCss;
	private String mensagem;
}
