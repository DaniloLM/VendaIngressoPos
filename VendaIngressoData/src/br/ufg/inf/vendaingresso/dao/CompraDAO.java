package br.ufg.inf.vendaingresso.dao;

import br.ufg.inf.vendaingresso.Cliente;
import br.ufg.inf.vendaingresso.Compra;
import br.ufg.inf.vendaingresso.Funcionario;
import br.ufg.inf.vendaingresso.Secao;

/**
 *
 * @author Ana Clara
 */
public interface CompraDAO {
    public void salvar(Compra compra, Cliente cliente, Funcionario funcionario, Secao secao);
    public void remover(Compra compra, Funcionario funcionario);
    public Compra getById(Funcionario funcionario);
}
