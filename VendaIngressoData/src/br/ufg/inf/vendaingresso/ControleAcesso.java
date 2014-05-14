package br.ufg.inf.vendaingresso;

/**
 *
 * @author aluno
 */
public interface ControleAcesso {
    public boolean login(String login, String senha);
    public boolean verificaAcesso(Funcionario funcionario);
}
