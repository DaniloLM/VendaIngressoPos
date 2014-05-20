package br.ufg.inf.vendaingresso.service.impl;

import br.ufg.inf.vendaingresso.Evento;
import br.ufg.inf.vendaingresso.Ingresso;
import br.ufg.inf.vendaingresso.Secao;
import br.ufg.inf.vendaingresso.dao.IngressoDAO;
import br.ufg.inf.vendaingresso.dao.impl.IngressoDAOImpl;
import br.ufg.inf.vendaingresso.exception.SaveException;
import br.ufg.inf.vendaingresso.service.IngressoService;

/**
 *
 * @author Ana Clara
 */
public class IngressoServiceImpl implements IngressoService{
    
    IngressoDAO ingressoDAO; 
    
    public IngressoServiceImpl(){
        ingressoDAO = new IngressoDAOImpl();
    }

    @Override
    public void cadastrarIngresso(Ingresso ingresso, Secao secao, Evento evento) {
        validate(ingresso, secao, evento);
        ingressoDAO.salvar(ingresso, secao, evento);
    }
    
    public void validate(Ingresso ingresso, Secao secao, Evento evento){
        if(ingresso == null || evento.equals("")){
            throw new SaveException("Ingresso não pode ser vazio.");
        } else if (secao == null || secao.equals("")){
            throw new SaveException("Seção não pode ser vazia.");
        } else {
            if(evento == null || evento.equals("")){
                throw new SaveException("Evento não pode ser vazio.");
            }
        } 
    }
}
