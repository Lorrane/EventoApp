package br.com.michelli.eventoapp.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.michelli.eventoapp.model.Convidado;
import br.com.michelli.eventoapp.model.Evento;

public interface ConvidadoRepository extends CrudRepository<Convidado, String>{

	public Iterable<Convidado> findByEvento(Evento evento);
	
}
