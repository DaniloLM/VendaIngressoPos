/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufg.inf.vendaingresso.dao;

import br.ufg.inf.vendaingresso.Acesso;
import br.ufg.inf.vendaingresso.Funcionario;

/**
 *
 * @author Ana Clara
 */
public interface ControleAcessoDAO {  
    public boolean login(Funcionario funcionario);
    public boolean verificaAcesso(Funcionario funcionario); 
}
