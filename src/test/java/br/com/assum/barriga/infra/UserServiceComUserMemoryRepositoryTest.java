package br.com.assum.barriga.infra;

import static br.com.assum.barriga.domain.builders.UsuarioBuilder.umUsuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.com.assum.barriga.domain.Usuario;
import br.com.assum.barriga.domain.exceptions.ValidationException;
import br.com.assum.barriga.service.UsuarioService;

@Tag("infra")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceComUserMemoryRepositoryTest {
	private static UsuarioService service = new UsuarioService(new UsuarioMemoryRepository());
	
	@Test
	@Order(1)
	public void deveSalvarUsuarioValido() {
		Usuario user = service.salvar(umUsuario().comId(null).agora());
		Assertions.assertNotNull(user.getId());
	}
	
	@Test
	@Order(2)
	public void deveRejeitarUsuarioExistente() {
		ValidationException ex = Assertions.assertThrows(ValidationException.class, () -> 
			service.salvar(umUsuario().comId(null).agora()));
		Assertions.assertEquals("Usuário user@mail.com já cadastrado!", ex.getMessage());
	}
}
