package br.com.assum.barriga.service;

import java.util.Optional;

import br.com.assum.barriga.domain.Usuario;
import br.com.assum.barriga.domain.exceptions.ValidationException;
import br.com.assum.barriga.service.repositories.UsuarioRepository;

public class UsuarioService {
	
	private UsuarioRepository repository;

	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}

	public Usuario salvar(Usuario usuario) {
		repository.getUserByEmail(usuario.getEmail()).ifPresent(user -> {
			throw new ValidationException(String.format("Usuário %s já cadastrado!", usuario.getEmail()));
		});
		return repository.salvar(usuario);
	}
	
	public Optional<Usuario> getUserByEmail(String email){
		return repository.getUserByEmail(email);
	}
}
