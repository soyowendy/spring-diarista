package com.soyowendy.ediarista.web.mappers;

import com.soyowendy.ediarista.core.models.Servico;
import com.soyowendy.ediarista.web.dtos.ServicoFormDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WebServicoMapper {
	WebServicoMapper INSTANCE = Mappers.getMapper(WebServicoMapper.class);
	Servico toModel(ServicoFormDTO form);
	ServicoFormDTO toForm(Servico model);
}
