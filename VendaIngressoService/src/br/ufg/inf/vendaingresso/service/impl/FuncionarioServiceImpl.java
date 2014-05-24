package br.ufg.inf.vendaingresso.service.impl;


import br.ufg.inf.vendaingresso.Acesso;
import br.ufg.inf.vendaingresso.Funcionario;
import br.ufg.inf.vendaingresso.dao.FuncionarioDAO;
import br.ufg.inf.vendaingresso.dao.impl.FuncionarioDAOImpl;
import br.ufg.inf.vendaingresso.exception.SaveException;
import br.ufg.inf.vendaingresso.service.FuncionarioService;

/**
 *
 * @author Ana Clara
 */
public class FuncionarioServiceImpl implements FuncionarioService{
    
    FuncionarioDAO funcionarioDAO; 
    
    public FuncionarioServiceImpl(){
        funcionarioDAO = new FuncionarioDAOImpl();
    }

    @Override
    public void cadastrarFuncionario(Funcionario funcionario, Acesso acesso) throws SaveException{
        validate(funcionario, acesso);
        funcionarioDAO.salvar(funcionario, acesso);
        System.out.println("Funcionário inserido com sucesso!");
    }
    
    public void validate(Funcionario funcionario, Acesso acesso){
        if(funcionario == null || funcionario.equals("")){
            throw new SaveException("Funcionário não pode ser vazio.");
        } else if (funcionario.getNome() == null || funcionario.getNome().equals("")){
                throw new SaveException("Nome não pode ser vazio.");
            } else if(funcionario.getCpf() == null || funcionario.getCpf().equals("")){
                throw new SaveException("CPF não pode ser vazio.");
            } else if (funcionario.getLogin() == null || funcionario.getLogin().equals("")){
                throw new SaveException("Login não pode ser vazio.");
            } else if (funcionario.getSenha() == null || funcionario.getSenha().equals("")){
                throw new SaveException("Senha não pode ser vazia.");
            } else {
                if (acesso == null || acesso.equals("")){
                    throw new SaveException("Tipo de acesso não pode ser vazio.");
            }
        }
    }
}
