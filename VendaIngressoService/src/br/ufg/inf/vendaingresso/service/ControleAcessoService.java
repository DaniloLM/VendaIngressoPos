package br.ufg.inf.vendaingresso.service;

import br.ufg.inf.vendaingresso.Funcionario;

/**
 *
 * @author Danilolm
 */
public interface ControleAcessoService {
    public boolean login(Funcionario funcionario);
    public boolean verificaAcesso(Funcionario funcionario);
}