package br.ufg.inf.vendaingresso.dao.impl;

import br.ufg.inf.vendaingresso.dao.FuncionarioDAO;
import br.ufg.inf.vendaingresso.Acesso;
import br.ufg.inf.vendaingresso.Funcionario;
import br.ufg.inf.vendaingresso.utils.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author danilolopesdemoraes
 */
public class FuncionarioDAOImpl implements FuncionarioDAO{
    
    private Connection conn = null;
    private Statement statement = null;
    private PreparedStatement ps = null;

    @Override
    public void salvar(Funcionario funcionario, Acesso acesso) {
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO funcionario (id, nome, login, senha, idacesso, cpf) " +
                         "VALUES ((SELECT NVL(MAX(id),0)+1 FROM FUNCIONARIO), ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);           
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getLogin());
            ps.setString(3, funcionario.getSenha());
            ps.setLong(4, acesso.getId());
            ps.setString(5, funcionario.getCpf());
            ps.executeUpdate();
        } catch (SQLException e){
                throw new RuntimeException("Erro " + e.getSQLState()
                                           + " ao atualizar o objeto: " 
                                           + e.getLocalizedMessage());
        } catch (ClassNotFoundException e){
                throw new RuntimeException("Erro ao conectar ao banco: "
                                         + e.getMessage()); 
        } finally {
            close();
        }
    }
    
    private void close() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao fechar conexão!");
        }
    }
}
