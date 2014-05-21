package br.ufg.inf.vendaingresso.service.impl;

import br.ufg.inf.vendaingresso.Funcionario;
import br.ufg.inf.vendaingresso.dao.FuncionarioDAO;
import br.ufg.inf.vendaingresso.dao.impl.ControleAcesso;
import br.ufg.inf.vendaingresso.dao.impl.FuncionarioDAOImpl;
import br.ufg.inf.vendaingresso.exception.SaveException;
import br.ufg.inf.vendaingresso.service.ControleAcessoService;


/**
 *
 * @author Danilolm
 */
public class ControleAcessoServiceImpl implements ControleAcessoService {

    FuncionarioDAO funcionarioDAO;
    
    @Override
    public boolean login(String login, String senha) {
        funcionarioDAO = new FuncionarioDAOImpl();
    }

    @Override
    public boolean verificaAcesso(Funcionario funcionario) {
        validate(funcionario);
        funcionarioDAO.salvar(funcionario); 
    }
    
     private void validate(Funcionario funcionario) {
        if (funcionario == null) {
            throw new SaveException("Funcionario não pode ser vazio.");
        } else {
            if (funcionario.getLogin() == null || funcionario.getLogin().equals("")) {
                throw new SaveException("Login não pode ser vazio.");
            } else {
                if (funcionario.getSenha() == null || funcionario.getSenha().equals("")) {
                   throw new SaveException("Senha não pode ser vazio.");
                }
            }
        }
    }

}
