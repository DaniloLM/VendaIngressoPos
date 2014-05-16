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
    public void salvar(Funcionario funcionario) {
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO funcionario (id, nome, dataevento) " 
                       +             "VALUES (?, \'?\',\'?\');";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, evento.getId());
            ps.setString(2, evento.getNome());
            ps.setDate(3, (Date) evento.getDataEvento()); 
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
