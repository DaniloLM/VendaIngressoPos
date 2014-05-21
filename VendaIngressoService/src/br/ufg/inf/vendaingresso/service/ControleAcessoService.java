package br.ufg.inf.vendaingresso.service;

import br.ufg.inf.vendaingresso.Funcionario;

/**
 *
 * @author Danilolm
 */
public interface ControleAcessoService {
    public boolean login(String login, String senha);
    public boolean verificaAcesso(Funcionario funcionario);
}