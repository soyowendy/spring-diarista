package com.soyowendy.ediarista.web.services;

import com.soyowendy.ediarista.core.enums.TipoUsuario;
import com.soyowendy.ediarista.core.exceptions.SenhaIncorretaException;
import com.soyowendy.ediarista.core.exceptions.SenhasNaoConferemException;
import com.soyowendy.ediarista.core.exceptions.UsuarioJaCadastradoException;
import com.soyowendy.ediarista.core.exceptions.UsuarioNaoEncontradoException;
import com.soyowendy.ediarista.core.models.Usuario;
import com.soyowendy.ediarista.core.repositories.UsuarioRepository;
import com.soyowendy.ediarista.web.dtos.AlterarSenhaFormDTO;
import com.soyowendy.ediarista.web.dtos.UsuarioCadastroFormDTO;
import com.soyowendy.ediarista.web.dtos.UsuarioEdicaoFormDTO;
import com.soyowendy.ediarista.web.interfaces.IConfirmacaoSenha;
import com.soyowendy.ediarista.web.mappers.WebUsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.util.List;

@Service
public class WebUsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private WebUsuarioMapper mapper;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Usuario> buscarTodos() {
		return usuarioRepository.findAll();
	}

	public Usuario cadastrar(UsuarioCadastroFormDTO form) {
		validarConfirmacaoSenha(form);

		Usuario model = mapper.toModel(form);

		String senhaHash = passwordEncoder.encode(model.getSenha());

		model.setSenha(senhaHash);
		model.setTipoUsuario(TipoUsuario.ADMIN);

		validarCamposUnicos(model);

		return usuarioRepository.save(model);
	}

	public Usuario buscarPorId(Long id) {
		String mensagem = String.format("Usuário com ID %d não encontrado", id);

		return usuarioRepository.findById(id)
				.orElseThrow(() -> new UsuarioNaoEncontradoException(mensagem));
	}

	public Usuario buscarPorEmail(String email) {
		String mensagem = String.format("Usuário com email %s não encontrado", email);

		return usuarioRepository.findByEmail(email)
				.orElseThrow(() -> new UsuarioNaoEncontradoException(mensagem));
	}

	public UsuarioEdicaoFormDTO buscarFormPorId(Long id) {
		Usuario usuario = buscarPorId(id);

		return mapper.toForm(usuario);
	}

	public Usuario editar(Long id, UsuarioEdicaoFormDTO form) {
		Usuario usuarioEncontrado = buscarPorId(id);

		Usuario model = mapper.toModel(form);
		model.setId(usuarioEncontrado.getId());
		model.setSenha(usuarioEncontrado.getSenha());
		model.setTipoUsuario(usuarioEncontrado.getTipoUsuario());

		validarCamposUnicos(model);

		return usuarioRepository.save(model);
	}

	public void excluirPorId(Long id) {
		Usuario usuarioEncontrado = buscarPorId(id);

		usuarioRepository.delete(usuarioEncontrado);
	}

	public void alterarSenha(AlterarSenhaFormDTO form, String email) {
		Usuario usuario = buscarPorEmail(email);

		validarConfirmacaoSenha(form);

		String senhaAtual = usuario.getSenha();
		String senhaAntiga = form.getSenhaAntiga();
		String senha = form.getSenha();


		if (!passwordEncoder.matches(senhaAntiga, senhaAtual)) {
			String mensagem = "Senha antiga está incorreta";
			FieldError fieldError = new FieldError(
					form.getClass().getName(),
					"senhaAntiga",
					senhaAntiga,
					false,
					null,
					null,
					mensagem
			);

			throw new SenhaIncorretaException(mensagem, fieldError);
		}

		String novaSenhaHash = passwordEncoder.encode(senha);
		usuario.setSenha(novaSenhaHash);

		usuarioRepository.save(usuario);
	}

	private void validarConfirmacaoSenha(IConfirmacaoSenha confirmacao) {
		String senha = confirmacao.getSenha();
		String confirmacaoSenha = confirmacao.getConfirmacaoSenha();

		if (!senha.equals(confirmacaoSenha)) {
			String mensagem = "Os campos de senha não conferem";
			FieldError fieldError = new FieldError(
					confirmacao.getClass().getName(),
					"confirmacaoSenha",
					confirmacao.getConfirmacaoSenha(),
					false,
					null,
					null,
					mensagem
			);

			throw new SenhasNaoConferemException(mensagem, fieldError);
		}

	}

	private void validarCamposUnicos(Usuario usuario) {
		if (usuarioRepository.isEmailJaCadastrado(usuario.getEmail(), usuario.getId())) {
			String mensagem = "Já existe um usuário cadastrado com esse e-mail";
			FieldError fieldError = new FieldError(
					usuario.getClass().getName(),
					"email",
					usuario.getEmail(),
					false,
					null,
					null,
					mensagem
			);

			throw new UsuarioJaCadastradoException(mensagem, fieldError);
		}
	}
}
