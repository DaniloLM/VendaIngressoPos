package br.ufg.inf.vendaingresso.dao.impl;

import br.ufg.inf.vendaingresso.Funcionario;

/**
 *
 * @author aluno
 */
public interface ControleAcesso {
    public boolean login(String login, String senha);
    public boolean verificaAcesso(Funcionario funcionario);
}
