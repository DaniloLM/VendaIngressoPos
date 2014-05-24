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
        System.out.println("Cliente inserido com sucesso!");
    }

    private void validate(Cliente cliente) {
        if (cliente == null) {
            throw new SaveException("Cliente não pode ser vazio.");
        } else {
            if (cliente.getNome() == null || cliente.getNome().equals("")) {
                throw new SaveException("Nome não pode ser vazio.");
            } else {
                if (cliente.getCpf() == null || cliente.getCpf().equals("")) {
                   throw new SaveException("Senha não pode ser vazio.");
                }
            }
        }
    }
}
