package br.ufg.inf.vendaingresso.service.impl;

import br.ufg.inf.vendaingresso.Evento;
import br.ufg.inf.vendaingresso.Ingresso;
import br.ufg.inf.vendaingresso.Secao;
import br.ufg.inf.vendaingresso.dao.SecaoDAO;
import br.ufg.inf.vendaingresso.dao.impl.SecaoDAOImpl;
import br.ufg.inf.vendaingresso.exception.SaveException;
import br.ufg.inf.vendaingresso.service.SecaoService;


/**
 *
 * @author Ana Clara
 */
public class SecaoServiceImpl implements SecaoService {

    SecaoDAO secaoDAO;

    public SecaoServiceImpl() {
        secaoDAO = new SecaoDAOImpl();
    }

    @Override
    public void cadastrarSecao(Secao secao, Evento evento) {
        validate(secao, evento);
        secaoDAO.salvar(secao, evento);
        System.out.println("Seção inserida com sucesso!");
    }
   
     public void validate(Secao secao, Evento evento){
        if (secao == null || secao.equals("")){
            throw new SaveException("Seção não pode ser vazia.");
        } else {
            if (evento == null || evento.equals("")){
                throw new SaveException("Evento não pode ser vazio.");
            } else{
                if (secao.getNome() == null || secao.getNome().equals("")){
                    throw new SaveException("Nome seção não pode ser vazio.");
                }
            }
        } 
    }
}
