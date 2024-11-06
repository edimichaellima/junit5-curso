package br.com.assum.barriga.service.repositories;

import java.util.List;

import br.com.assum.barriga.domain.Conta;

public interface ContaRepository {
	
	Conta salvar(Conta conta);

	List<Conta> obterContasPorUsuario(Long usuarioId);
	
	void delete(Conta conta);
}
