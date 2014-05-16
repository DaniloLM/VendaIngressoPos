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
public class ClienteDAOImpl implements ClienteDAO{
    private Connection conn = null; 
    private Statement statement = null; 
    private PreparedStatement ps = null; 
    private ResultSet rs = null;
    
    /**
     * Método que salva um cliente.
     * @param cliente 
     */
    @Override
    public void salvar(Cliente cliente) {
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO cliente (id, nome)"
                                     + "VALUES (?, \' ? \');";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, cliente.getId());
            ps.setString(2, cliente.getNome());
            ps.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Erro " + e.getSQLState()
                                       + " ao atualizar objeto: "
                                       + e.getLocalizedMessage()); 
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erro ao conectar no banco: "
                                       + e.getMessage());
        } finally {
            close();
        }
    }

    @Override
    public Cliente getByCpf(String cpf) {
        Cliente clienteLido = null;
        try{
            conn = ConnectionFactory.getConnection(); 
            String sql = "SELECT id "
                       +   "FROM cliente"
                       +  "WHERE cpf LIKE \'?\';";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cpf);
            rs = ps.executeQuery();
            if(rs.next()){
                clienteLido = new Cliente();
                clienteLido.setId(rs.getLong("id"));
                clienteLido.setNome(rs.getString("nome"));
                clienteLido.setCpf(rs.getString("cpf"));
            }else{
                close();
                throw new RuntimeException("Usuário não encontrado");
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
            return clienteLido; 
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
