package br.ufg.inf.vendaingresso;

import java.sql.ResultSet;

/**
 *
 * @author aluno
 */
public interface CompraDAO {
    public void salvar(Compra compra);
    public void remover(Compra compra, Funcionario funcionario);
    public ResultSet getById(Funcionario funcionario);
}
