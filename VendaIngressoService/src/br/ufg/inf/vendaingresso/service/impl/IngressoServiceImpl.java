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
    public void cadastrarIngresso(Secao secao) {
        validate(secao);
        ingressoDAO.salvar(secao);
    }
    
    public void validate(Secao secao){
        if (secao == null || secao.equals("")){
            throw new SaveException("Seção não pode ser vazia.");
        } else {
            if(secao.getNome() == null || secao.getNome().equals("")){
                throw new SaveException("Nome da seção não pode ser vazio.");
            }
        } 
    }
}
