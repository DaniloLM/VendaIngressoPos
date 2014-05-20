package br.ufg.inf.vendaingresso.service;

import br.ufg.inf.vendaingresso.Acesso; 
import br.ufg.inf.vendaingresso.Funcionario;

/**
 *
 * @author Ana Clara
 */
public interface FuncionarioService {
    
    public void cadastrarFuncionario(Funcionario funcionario, Acesso acesso);    
}
