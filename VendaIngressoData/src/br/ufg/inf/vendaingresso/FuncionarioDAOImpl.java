package br.ufg.inf.vendaingresso;

import br.ufg.inf.vendaingresso.utils.*;

import java.sql.Connection;
import java.sql.Date;
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
            String sql = "INSERT INTO funcionario (id, nome, login, senha, idacesso, cpf)"
                    + " VALUES (SELECT NVL(MAX(id),0)+1, \'?\', \'?\', \'?\', (SELECT id FROM acesso WHERE tipo LIKE '?'), \'?\');";
            ps = conn.prepareStatement(sql);           
            ps.setString(2, funcionario.getNome());
            ps.setString(3, funcionario.getLogin());
            ps.setString(4, funcionario.getSenha());
            ps.setString(5, acesso.getTipo());
            ps.setString(6, funcionario.getCpf());
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
