package br.ufg.inf.vendaingresso.dao;

import br.ufg.inf.vendaingresso.Cliente;

/**
 *
 * @author aluno
 */
public interface ClienteDAO {
    public void salvar(Cliente cliente);
    public Cliente getByCpf(String cpf);
}
