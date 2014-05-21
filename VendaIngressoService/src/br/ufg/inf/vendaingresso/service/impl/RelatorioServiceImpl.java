package br.ufg.inf.vendaingresso.service.impl;

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
    ControleAcessoServiceImpl controleacesso = new ControleAcessoServiceImpl();
    
    public RelatorioServiceImpl(){
        ingressoDAO = new IngressoDAOImpl();
    }
    
    /**
     * Metodo que retorna o total de ingressos vendidos
     * 
     * @param funcionario
     * @return
     */
    @Override
    public int contaIngressoTotal(Funcionario funcionario) {
        if(controleacesso.verificaAcesso(funcionario)){
            int count; 
            count = ingressoDAO.getVendidosTotal();
            return count;
        } else {
            throw new RuntimeException("Você não tem permissão para acessar o programa.");
        }
    }

    @Override
    public int contaIngressoSecao(Evento evento, Secao secao, Funcionario funcionario) {
        if(controleacesso.verificaAcesso(funcionario)){
            int count;
            validate(evento, secao);
            count = ingressoDAO.getVendidosSecao(secao, evento);
            return count; 
        } else {
            throw new RuntimeException("Você não tem permissão para acessar o programa.");
        }
    }

    @Override
    public int contaIngressoEvento(Evento evento, Funcionario funcionario) {
        if(controleacesso.verificaAcesso(funcionario)){
            int count; 
            validate(evento);
            count = ingressoDAO.getVendidosEvento(evento);
            return count;
        } else {
            throw new RuntimeException("Você não tem permissão para acessar o programa.");
        }
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
