package com.soyowendy.ediarista.web.mappers;

import com.soyowendy.ediarista.core.models.Usuario;
import com.soyowendy.ediarista.web.dtos.UsuarioCadastroFormDTO;
import com.soyowendy.ediarista.web.dtos.UsuarioEdicaoFormDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WebUsuarioMapper {
	WebUsuarioMapper INSTANCE = Mappers.getMapper(WebUsuarioMapper.class);

	Usuario toModel(UsuarioCadastroFormDTO form);

	Usuario toModel(UsuarioEdicaoFormDTO form);

	UsuarioEdicaoFormDTO toForm(Usuario model);
}
