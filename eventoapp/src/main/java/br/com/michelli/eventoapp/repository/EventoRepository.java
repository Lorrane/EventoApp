package br.com.michelli.eventoapp.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.michelli.eventoapp.model.Evento;

public interface EventoRepository extends CrudRepository <Evento, String>{
	
	

}
