/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufg.inf.vendaingresso.service;

import br.ufg.inf.vendaingresso.Cliente;

/**
 *
 * @author Elton Ricelli
 */
public interface ClienteService {
    
    public void salvar(Cliente cliente) throws RegraNegocioException;
}
