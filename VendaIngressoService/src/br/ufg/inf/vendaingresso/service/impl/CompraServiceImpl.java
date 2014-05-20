/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufg.inf.vendaingresso.service.impl;

import br.ufg.inf.vendaingresso.Cliente;
import br.ufg.inf.vendaingresso.Compra;
import br.ufg.inf.vendaingresso.Funcionario;
import br.ufg.inf.vendaingresso.Secao;
import br.ufg.inf.vendaingresso.dao.CompraDAO;
import br.ufg.inf.vendaingresso.dao.IngressoDAO;
import br.ufg.inf.vendaingresso.dao.impl.CompraDAOImpl;
import br.ufg.inf.vendaingresso.dao.impl.IngressoDAOImpl;
import br.ufg.inf.vendaingresso.exception.SaveException;
import br.ufg.inf.vendaingresso.service.CompraService;

/**
 *
 * @author Ana Clara
 */
public class CompraServiceImpl implements CompraService {

    CompraDAO compraDAO; 
    IngressoDAO ingressoDAO; 
    
    public CompraServiceImpl(){
        compraDAO = new CompraDAOImpl(); 
        ingressoDAO = new IngressoDAOImpl();
    }
    
    /**
     * Metodo para cadastrar a compra
     * 
     * @param cliente
     * @param funcionario
     * @param secao 
     */
    @Override
    public void cadastrarCompra(Cliente cliente, Funcionario funcionario, Secao secao) {
        validate(cliente, funcionario, secao);
        compraDAO.salvar(cliente, funcionario, secao);
        ingressoDAO.atualizar(cliente);
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
}
 
