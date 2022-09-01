package com.ecommerce.savegames.service;

import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import com.ecommerce.savegames.model.Usuario;
import com.ecommerce.savegames.model.UsuarioLogin;
import com.ecommerce.savegames.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.encode(senha);

	}
      private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
		
	      	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		 
		    return encoder.matches(senhaDigitada, senhaBanco);

	}

	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

		if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			return Optional.empty();

		usuario.setSenha(criptografarSenha(usuario.getSenha()));

		return Optional.of(usuarioRepository.save(usuario));

	}
	
	public Optional<Usuario> atualizarUsuario(Usuario usuario){
		if(usuarioRepository.findById(usuario.getId()).isPresent()) {
			Optional<Usuario> buscaUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
			if ( (buscaUsuario.isPresent()) && ( buscaUsuario.get().getId() != usuario.getId()))
				throw new ResponseStatusException(
						HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
			
			usuario.setSenha(criptografarSenha(usuario.getSenha()));
			
			return Optional.ofNullable(usuarioRepository.save(usuario));
		}
		return Optional.empty();
	}
	
	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());
		
		if (usuario.isPresent()) {

			
			if (compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {

				/**
				 * Se as senhas forem iguais, atualiza o objeto usuarioLogin com os dados 
				 * recuperados do Banco de Dados e insere o Token Gerado através do Método
				 * gerarBasicToken.
				 * Desta forma, será possível exibir o nome e a foto do usuário no Frontend.
				 */
				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setFoto(usuario.get().getFoto());
				usuarioLogin.get().setToken(gerarBasicToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));
				usuarioLogin.get().setSenha(usuario.get().getSenha());

				/**
				 * Retorna o objeto usarioLogin atualizado para a classe UsuarioController.
				 * A Classe controladora checará se deu tudo certo nesta operação e retornará
				 * o status.
				 */
				return usuarioLogin;

			}
		}	
		
		/**
		 * empty -> Retorna uma instância de Optional vazia, caso o usuário não seja encontrado.
		 */
		return Optional.empty();
		
	}
	
	
	private String gerarBasicToken(String usuario, String senha) {

		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);

	}

	
}

	

