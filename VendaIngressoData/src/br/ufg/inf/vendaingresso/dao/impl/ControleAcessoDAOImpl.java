package br.ufg.inf.vendaingresso.dao.impl;

import br.ufg.inf.vendaingresso.Funcionario;
import br.ufg.inf.vendaingresso.dao.ControleAcessoDAO;
import br.ufg.inf.vendaingresso.utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author aluno
 */
public class ControleAcessoDAOImpl implements ControleAcessoDAO {

    private Connection conn = null; 
    private Statement statement = null; 
    private PreparedStatement ps = null; 
    private ResultSet rs = null;
    
    /**
     * Metodo que verifica login
     * 
     * @param funcionario
     * @return funcionariolido
     */
    @Override
    public boolean login(Funcionario funcionario) {
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "SELECT nome "
                       + "  FROM funcionario "
                       + " WHERE login = \'?\' "
                       + "   AND senha = \'?\';";
            ps = conn.prepareStatement(sql);
            ps.setString(1, funcionario.getLogin());
            ps.setString(2, funcionario.getSenha());
            rs = ps.executeQuery();
            return rs.next();
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

    /**
     * Metodo que verifica acessoa pra geracao de relatorio
     * 
     * @param funcionario
     * @return
     */
    @Override
    public boolean verificaAcesso(Funcionario funcionario) {
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "SELECT * "
                       + "  FROM funcionario "
                       + "  JOIN acesso ON funcionario.idacesso = acesso.id "
                       + " WHERE acesso.tipo = 'Gerente' "
                       + "   AND funcionario.cpf = \'?\';";
            ps = conn.prepareStatement(sql);
            ps.setString(1, funcionario.getCpf());
            rs = ps.executeQuery();
            return rs.next();
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
            if (rs != null) {
                rs.close();
            }
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
