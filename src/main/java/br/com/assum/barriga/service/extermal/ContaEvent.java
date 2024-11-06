package br.com.assum.barriga.service.extermal;

import br.com.assum.barriga.domain.Conta;

public interface ContaEvent {
	public enum EventType {CREATED, UPDATED, DELETED}
	
	void dispatch(Conta conta, EventType type) throws Exception;
}
