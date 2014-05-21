package br.ufg.inf.vendaingresso.service.impl;

import br.ufg.inf.vendaingresso.Cliente;
import br.ufg.inf.vendaingresso.Evento;
import br.ufg.inf.vendaingresso.Funcionario;
import br.ufg.inf.vendaingresso.Secao;
import br.ufg.inf.vendaingresso.dao.IngressoDAO;
import br.ufg.inf.vendaingresso.dao.impl.IngressoDAOImpl;
import br.ufg.inf.vendaingresso.exception.SaveException;
import br.ufg.inf.vendaingresso.service.RelatorioService;

/**
 *
 * @author Ana Clara
 */
public class RelatorioServiceImpl implements RelatorioService{

    IngressoDAO ingressoDAO; 
    
    public RelatorioServiceImpl(){
        ingressoDAO = new IngressoDAOImpl();
    }
    
    @Override
    public int contaIngressoTotal() {
        int count; 
        count = ingressoDAO.getVendidosTotal();
        return count;
    }

    @Override
    public int contaIngressoSecao(Evento evento, Secao secao) {
        int count;
        validate(evento, secao);
        count = ingressoDAO.getVendidosSecao(secao, evento);
        return count; 
    }

    @Override
    public int contaIngressoEvento(Evento evento) {
        int count; 
        validate(evento);
        count = ingressoDAO.getVendidosEvento(evento);
        return count; 
    }
    
    public void validate(Evento evento, Secao secao){
        if (evento.getNome() == null || evento.getNome().equals("")) {
            throw new SaveException("Evento não pode ser vazio.");
        } else {
            if (secao.getNome() == null || secao.getNome().equals("")){
                throw new SaveException("Seção não pode ser vazia.");
            }
        }
    }
    
    public void validate(Evento evento){
        if (evento.getNome() == null || evento.getNome().equals("")){
                throw new SaveException("Evento não pode ser vazio.");
        }
    }
}
