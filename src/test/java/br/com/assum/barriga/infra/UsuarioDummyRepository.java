package br.com.assum.barriga.infra;

import static br.com.assum.barriga.domain.builders.UsuarioBuilder.umUsuario;

import java.util.Optional;

import br.com.assum.barriga.domain.Usuario;
import br.com.assum.barriga.service.repositories.UsuarioRepository;

public class UsuarioDummyRepository implements UsuarioRepository {

	@Override
	public Usuario salvar(Usuario usuario) {
		return umUsuario()
				.comNome(usuario.getNome())
				.comEmail(usuario.getEmail())
				.comSenha(usuario.getSenha())
				.agora();
	}

	@Override
	public Optional<Usuario> getUserByEmail(String email) {
		if("user@mail.com".equals(email))
			return Optional.of(umUsuario().comEmail(email).agora());
		return Optional.empty();
	}

}
