package br.ufg.inf.vendaingresso;

import java.sql.ResultSet;

/**
 *
 * @author aluno
 */
public interface CompraDAO {
    public void salvar(Compra compra, Cliente cliente, Funcionario funcionario);
    public void remover(Compra compra, Funcionario funcionario);
    public Compra getById(Funcionario funcionario);
}
