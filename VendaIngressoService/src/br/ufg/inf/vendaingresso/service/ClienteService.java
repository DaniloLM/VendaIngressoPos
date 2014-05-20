package br.ufg.inf.vendaingresso.service;

import br.ufg.inf.vendaingresso.Cliente;
import br.ufg.inf.vendaingresso.exception.SaveException;

/**
 *
 * @author Elton Ricelli
 */
public interface ClienteService {
    
    public void cadastrarCliente(Cliente cliente) throws SaveException;
}
