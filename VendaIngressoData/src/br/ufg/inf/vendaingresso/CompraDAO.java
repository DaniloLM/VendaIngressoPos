package br.ufg.inf.vendaingresso;

import java.sql.ResultSet;

/**
 *
 * @author aluno
 */
public interface CompraDAO {
    public void salvar(Compra compra, Cliente cliente, Funcionario funcionario, Secao secao);
    public void remover(Compra compra, Funcionario funcionario);
    public ResultSet getById(Funcionario funcionario);
}
