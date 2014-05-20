package br.ufg.inf.vendaingresso.service.impl;

import br.ufg.inf.vendaingresso.Evento;
import br.ufg.inf.vendaingresso.dao.EventoDAO;
import br.ufg.inf.vendaingresso.dao.impl.EventoDAOImpl;
import br.ufg.inf.vendaingresso.exception.SaveException;
import br.ufg.inf.vendaingresso.service.EventoService;

/**
 *
 * @author Ana Clara
 */
public class EventoServiceImpl implements EventoService{
    
    EventoDAO eventoDAO; 
    
    public EventoServiceImpl(){
        eventoDAO = new EventoDAOImpl();
    }

    @Override
    public void cadastrarEvento(Evento evento) {
        validate(evento);
        eventoDAO.salvar(evento);
    }
    
    public void validate(Evento evento){
        if(evento == null || evento.equals("")){
            throw new SaveException("Evento não pode ser vazio.");
        } else if (evento.getNome() == null || evento.getNome().equals("")){
            throw new SaveException("Nome do evento não pode ser vazio.");
        } else {
            if(evento.getDataEvento() == null || evento.getDataEvento().equals("")){
                throw new SaveException("Data do evento não pode ser vazia.");
            }
        } 
    }
}
