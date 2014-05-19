/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufg.inf.vendaingresso.service.impl;

import br.ufg.inf.vendaingresso.Cliente;
import br.ufg.inf.vendaingresso.service.RegraNegocioException;
import br.ufg.inf.vendaingresso.service.ClienteService;
import br.ufg.inf.vendaingresso.dao.ClienteDAO;
import br.ufg.inf.vendaingresso.dao.impl.ClienteDAOImpl;

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
    public void salvar(Cliente cliente) throws RegraNegocioException{
        verificaIntegridade(cliente);
        clienteDAO.salvar(cliente);
        
    }

    private void verificaIntegridade(Cliente cliente) {
        if (cliente == null) {
            throw new RegraNegocioException("Usuário não pode ser nulo");
        } else {
            if (cliente.getNome() == null || cliente.getNome().equals("")) {
                throw new RegraNegocioException("Nome de usuário inválido");
            } else {
                if (cliente.getId() == null || cliente.getId().equals("")) {
                    throw new RegraNegocioException("Id do Cliente inválido");
                }
            }
        }
    }
}
