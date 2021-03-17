package br.com.michelli.eventoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.michelli.eventoapp.model.Convidado;
import br.com.michelli.eventoapp.model.Evento;
import br.com.michelli.eventoapp.repository.ConvidadoRepository;
import br.com.michelli.eventoapp.repository.EventoRepository;

@Controller
public class EventoController {

	@Autowired //Utilizado para "preencher" o Repository com as dependencias externas que usaremos
	private EventoRepository er;
	
	@Autowired
	private ConvidadoRepository cr;
	
	/* Ao ser chamado o /cadastrarEvento, vamos devolver a tela
	 * formEvento que está na pasta evento no templates
	 */
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.GET)
	public String form() {
		
		return "evento/formEvento";
		
	}
	
	/*
	 * Ao ser chamado o /cadastrarEvento pelo method post, ou seja,
	 * enviando o formulário preenchido, vamos pegar como parâmetro
	 * o evento e salvar com o repository e retornar para a tela.
	 */
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.POST)
	public String formPost(Evento evento) {
		
		er.save(evento);
		
		return "redirect:/cadastrarEvento";
		
	}
	
	/*
	 * Ao ser chamado o /eventos criamos a tela no mv e 
	 * buscamos no banco com o repository os eventos a serem
	 * listados, adicionamos no mv criado e devolvemos a tela
	 */
	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("index");//criamos a tela apontando para o index no templates
		Iterable <Evento> eventos = er.findAll();//busca no banco com repository
		mv.addObject("eventos",eventos);//adiciona os dados buscados no evento como objeto no mv
		return mv;//devolvemos a tela
	}
	
	/*
	 * 
	 */
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		Evento evento = er.findById(codigo);
		mv.addObject("evento", evento);
		return mv;
	}
	
	/*
	 * 
	 */
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesEventoPost(@PathVariable("codigo") long codigo, Convidado convidado) {
		Evento evento = er.findById(codigo);
		convidado.setEvento(evento);
		cr.save(convidado); 
		
		return "redirect:/{codigo}";
	}
}
