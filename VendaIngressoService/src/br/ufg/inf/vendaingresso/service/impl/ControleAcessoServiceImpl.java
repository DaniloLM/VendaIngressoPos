package br.ufg.inf.vendaingresso.service.impl;

import br.ufg.inf.vendaingresso.Funcionario;
import br.ufg.inf.vendaingresso.dao.ControleAcessoDAO;
import br.ufg.inf.vendaingresso.dao.impl.ControleAcessoDAOImpl;
import br.ufg.inf.vendaingresso.exception.SaveException;
import br.ufg.inf.vendaingresso.service.ControleAcessoService;


/**
 *
 * @author Danilolm
 */
public class ControleAcessoServiceImpl implements ControleAcessoService {

    ControleAcessoDAO controleacessoDAO;
    
    public ControleAcessoServiceImpl(){
        controleacessoDAO = new ControleAcessoDAOImpl();
    }
    
    @Override
    public boolean login(Funcionario funcionario) {
        validate(funcionario);
        return controleacessoDAO.login(funcionario);
    }

    @Override
    public boolean verificaAcesso(Funcionario funcionario) {
        return controleacessoDAO.verificaAcesso(funcionario);
    }
    
    private void validate(Funcionario funcionario) {
        if (funcionario == null) {
            throw new SaveException("Funcionário não pode ser vazio.");
        } else {
            if (funcionario.getLogin() == null || funcionario.getLogin().equals("")) {
                throw new SaveException("Login não pode ser vazio.");
            } else {
                if (funcionario.getSenha() == null || funcionario.getSenha().equals("")) {
                   throw new SaveException("Senha não pode ser vazia.");
                }
            }
        }
    }

}
