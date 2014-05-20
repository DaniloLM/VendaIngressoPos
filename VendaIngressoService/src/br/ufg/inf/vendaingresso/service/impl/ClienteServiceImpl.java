/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufg.inf.vendaingresso.service.impl;

import br.ufg.inf.vendaingresso.Cliente;
import br.ufg.inf.vendaingresso.dao.ClienteDAO;
import br.ufg.inf.vendaingresso.dao.impl.ClienteDAOImpl;
import br.ufg.inf.vendaingresso.exception.SaveException;
import br.ufg.inf.vendaingresso.service.ClienteService;


/**
 *
 * @author Elton Ricelli
 */
public class ClienteServiceImpl implements ClienteService {

    ClienteDAO clienteDAO;

    public ClienteServiceImpl() {
        clienteDAO = new ClienteDAOImpl();
    }

    @Override
    public void cadastrarCliente(Cliente cliente) throws SaveException{
        validate(cliente);
        clienteDAO.salvar(cliente);
        
    }

    private void validate(Cliente cliente) {
        if (cliente == null) {
            throw new SaveException("Cliente não pode ser vazio.");
        } else {
            if (cliente.getNome() == null || cliente.getNome().equals("")) {
                throw new SaveException("Nome não pode ser vazio.");
            } else {
                if (cliente.getCpf() == null || cliente.getCpf().equals("")) {
                   throw new SaveException("CPF não pode ser vazio.");
                }
            }
        }
    }
}
