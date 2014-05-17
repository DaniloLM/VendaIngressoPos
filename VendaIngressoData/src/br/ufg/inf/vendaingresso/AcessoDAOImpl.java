package br.ufg.inf.vendaingresso;

import br.ufg.inf.vendaingresso.utils.ConnectionFactory;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author danilolopesdemoraes
 */
public class AcessoDAOImpl implements AcessoDAO{
    private Connection conn = null; 
    private Statement statement = null; 
    private PreparedStatement ps = null; 
    private ResultSet rs = null;
    
    @Override
    public Acesso getByTipo(String tipo) {
        Acesso acessoLido = null;
        try{
            conn = ConnectionFactory.getConnection(); 
            String sql = "SELECT * "
                       +   "FROM acesso"
                       +  "WHERE tipo LIKE \'?\';";
            ps = conn.prepareStatement(sql);
            ps.setString(1, tipo);
            rs = ps.executeQuery();
            if(rs.next()){
                acessoLido = new Acesso();
                acessoLido.setId(rs.getLong("id"));
                acessoLido.setTipo(rs.getString("tipo"));
            }else{
                close();
                throw new RuntimeException("Cliente não encontrado!");
            }
        } catch (SQLException e){
                throw new RuntimeException("Erro " + e.getSQLState()
                                           + " ao atualizar o objeto: " 
                                           + e.getLocalizedMessage());
        } catch (ClassNotFoundException e){
                throw new RuntimeException("Erro ao conectar ao banco: "
                                         + e.getMessage()); 
        } finally {
            close();
            return acessoLido; 
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
