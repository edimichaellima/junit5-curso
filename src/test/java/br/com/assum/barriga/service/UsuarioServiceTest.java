package br.com.assum.barriga.service;

import static br.com.assum.barriga.domain.builders.UsuarioBuilder.umUsuario;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.assum.barriga.domain.Usuario;
import br.com.assum.barriga.domain.exceptions.ValidationException;
import br.com.assum.barriga.service.repositories.UsuarioRepository;

@Tag("service")
@Tag("usuario")
@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
	@Mock private UsuarioRepository repository;
	@InjectMocks private UsuarioService service;
	
//	@AfterEach
//	public void tearDown() {
//		Mockito.verifyNoMoreInteractions(repository);
//	}
	
	@Test
	public void deveRetornarEmptyQuandoUsuarioInexistente() {			
//		Mockito.when(repository.getUserByEmail("mail@mail.com")).thenReturn(Optional.empty());
		
		Optional<Usuario> user = service.getUserByEmail("mail@mail.com");
		Assertions.assertTrue(user.isEmpty());
	}
	
	@Test
	public void deveRetornarUmUsuarioPorEmail() {		
		when(repository.getUserByEmail("mail@mail.com"))
			.thenReturn(Optional.of(umUsuario().agora()));
		
		Optional<Usuario> user = service.getUserByEmail("mail@mail.com");
		System.out.println(user);
		Assertions.assertTrue(user.isPresent());
		
		verify(repository, Mockito.atLeastOnce()).getUserByEmail("mail@mail.com");
		verify(repository, Mockito.never()).getUserByEmail("outroEmail@mail.com");
		Mockito.verifyNoMoreInteractions(repository);
	}
	
	@Test
	public void deveSalvarUsuarioComSucesso() {
		Usuario userToSave = umUsuario().comId(null).agora();
		
		
//		when(repository.getUserByEmail(userToSave.getEmail()))
//			.thenReturn(Optional.empty());
		 
		when(repository.salvar(userToSave)).thenReturn(umUsuario().agora());		
		
		Usuario savedUser = service.salvar(userToSave);
		Assertions.assertNotNull(savedUser.getId());
		
		verify(repository).getUserByEmail(userToSave.getEmail());
//		verify(repository).salvar(userToSave);
	}
	
	@Test
	public void deveRejeitarUsuarioExistente() {
		Usuario userToSave = umUsuario().comId(null).agora();
		
		when(repository.getUserByEmail(userToSave.getEmail()))
			.thenReturn(Optional.of(umUsuario().agora()));
		
		ValidationException ex = Assertions.assertThrows(ValidationException.class, () -> 
				service.salvar(userToSave));
		
		Assertions.assertTrue(ex.getMessage().endsWith("já cadastrado!"));
		
		verify(repository, never()).salvar(userToSave);
	}
	
	

}
