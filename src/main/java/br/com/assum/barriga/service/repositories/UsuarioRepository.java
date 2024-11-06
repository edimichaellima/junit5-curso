package br.com.assum.barriga.service.repositories;

import java.util.Optional;

import br.com.assum.barriga.domain.Usuario;

public interface UsuarioRepository {

	Usuario salvar(Usuario usuario);
	
	Optional<Usuario> getUserByEmail(String email);
}
