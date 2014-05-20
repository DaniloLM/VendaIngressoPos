/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufg.inf.vendaingresso.service;

import br.ufg.inf.vendaingresso.Cliente;
import br.ufg.inf.vendaingresso.Compra;
import br.ufg.inf.vendaingresso.Funcionario;
import br.ufg.inf.vendaingresso.Secao;

/**
 *
 * @author Ana Clara
 */
public interface CompraService {
    
    public void cadastrarCompra(Cliente cliente, Funcionario funcionario, Secao secao);
    public void cancelarCompra(Cliente cliente, Funcionario funcionario);
    public Compra recuperarCompra(Cliente cliente, Funcionario funcionario);
}
