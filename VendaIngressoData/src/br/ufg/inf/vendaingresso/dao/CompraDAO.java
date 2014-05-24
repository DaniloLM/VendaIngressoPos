package br.ufg.inf.vendaingresso.dao;

import br.ufg.inf.vendaingresso.Cliente;
import br.ufg.inf.vendaingresso.Compra;
import br.ufg.inf.vendaingresso.Evento;
import br.ufg.inf.vendaingresso.Funcionario;
import br.ufg.inf.vendaingresso.Secao;

/**
 *
 * @author Ana Clara
 */
public interface CompraDAO {
    public void salvar(Cliente cliente, Funcionario funcionario, Secao secao, Evento evento);
    public void remover(Cliente cliente, Funcionario funcionario);
    public Compra getCompra(Cliente cliente, Funcionario funcionario);
}
