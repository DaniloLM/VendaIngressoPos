package br.ufg.inf.vendaingresso.dao;

import br.ufg.inf.vendaingresso.Cliente;

/**
 *
 * @author danilolopesdemoraes
 */
public interface ClienteDAO {
    public void salvar(Cliente cliente);
    public void atualizar(Cliente cliente);
    public Cliente getByCpf(Cliente cliente);
}
