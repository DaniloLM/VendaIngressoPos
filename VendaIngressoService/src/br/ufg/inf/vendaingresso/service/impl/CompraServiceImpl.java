package br.ufg.inf.vendaingresso.service.impl;

import br.ufg.inf.vendaingresso.Cliente;
import br.ufg.inf.vendaingresso.Compra;
import br.ufg.inf.vendaingresso.Evento;
import br.ufg.inf.vendaingresso.Funcionario;
import br.ufg.inf.vendaingresso.Ingresso;
import br.ufg.inf.vendaingresso.Secao;
import br.ufg.inf.vendaingresso.dao.CompraDAO;
import br.ufg.inf.vendaingresso.dao.IngressoDAO;
import br.ufg.inf.vendaingresso.dao.ClienteDAO;
import br.ufg.inf.vendaingresso.dao.impl.CompraDAOImpl;
import br.ufg.inf.vendaingresso.dao.impl.IngressoDAOImpl;
import br.ufg.inf.vendaingresso.exception.SaveException;
import br.ufg.inf.vendaingresso.service.CompraService;
import java.util.Map;

/**
 *
 * @author Ana Clara
 */
public class CompraServiceImpl implements CompraService {

    CompraDAO compraDAO; 
    IngressoDAO ingressoDAO;
﻿    ClienteDAO clienteDAO;

    
    public CompraServiceImpl(){
        compraDAO = new CompraDAOImpl(); 
        ingressoDAO = new IngressoDAOImpl();
        clienteDAO = new ClienteDAOImpl();
    }
    
    /**
     * Metodo para cadastrar a compra
     * 
     * @param cliente
     * @param funcionario
     * @param secao 
     * @param evento 
     */
    @Override
    public void cadastrarCompra(Cliente cliente, Funcionario funcionario, Secao secao) {
        validate(cliente, funcionario, secao);
        compraDAO.salvar(cliente, funcionario, secao);
        ingressoDAO.atualizar(cliente);
        clienteDAO.atualizar(cliente);
    }
    
    /**
     * Metodo para cancelar a compra
     * 
     * @param cliente
     * @param funcionario
     */
    @Override
    public void cancelarCompra(Cliente cliente, Funcionario funcionario){
        validate(cliente, funcionario); 
        compraDAO.remover(cliente, funcionario);
    }
    
    /**
     * Metodo para recuperar a compra
     * 
     * @param cliente
     * @param funcionario
     * @return
     */
    @Override
    public Compra recuperarCompra(Cliente cliente, Funcionario funcionario){
        Compra compra; 
        
        validate(cliente, funcionario);
        compra = compraDAO.getCompra(cliente, funcionario);
        return compra;
        
    }
    
    /**
     * Metodo para recuperar os assentos disponveis do evento
     * @param evento
     * @return assentos
    */
    @Override
    public Map<Ingresso, Secao> recuperarAssentosDisponiveis(Evento evento) {
        validate(evento);
        Map<Ingresso, Secao> assentos = ingressoDAO.getIngressoDisponiveis(evento);
        return assentos;
    }
    
    public void validate(Cliente cliente, Funcionario funcionario, Secao secao){
        if (cliente.getCpf() == null || cliente.getCpf().equals("")) {
            throw new SaveException("CPF do cliente não pode ser vazio");
        } else if (funcionario.getCpf() == null || funcionario.getCpf().equals("")){
                throw new SaveException("CPF do funcionário não pode ser vazio.");
        } else {
            if (secao.getNome() == null || secao.getNome().equals("")){
                throw new SaveException("Seção não pode ser vazia.");
            }
        }
    }
    
    public void validate(Cliente cliente, Funcionario funcionario){
        if (cliente.getCpf() == null || cliente.getCpf().equals("")) {
            throw new SaveException("CPF do cliente não pode ser vazio");
        } else {
            if (funcionario.getCpf() == null || funcionario.getCpf().equals("")){
                throw new SaveException("CPF do funcionário não pode ser vazio.");
            }
        }
    }
    
    public void validate(Evento evento){
        if (evento.getNome() == null || evento.getNome().equals("")){
                throw new SaveException("Evento não pode ser vazio.");
        }
    }
}
 
