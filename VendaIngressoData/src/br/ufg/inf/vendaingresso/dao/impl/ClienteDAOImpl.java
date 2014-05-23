package br.ufg.inf.vendaingresso.dao.impl;

import br.ufg.inf.vendaingresso.Cliente;
import br.ufg.inf.vendaingresso.dao.ClienteDAO;
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
            String sql = "INSERT INTO cliente (id, nome, cpf) "
                       + "VALUES ((SELECT NVL(MAX(id),0)+1 FROM cliente), ?, ?)";
            ps = conn.prepareStatement(sql);           
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
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
    public Cliente getByCpf(Cliente cliente) {
        Cliente clienteLido = null;
        try{
            conn = ConnectionFactory.getConnection(); 
            String sql = "SELECT nome FROM cliente WHERE cpf LIKE ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.getCpf());
            rs = ps.executeQuery();
            if(rs.next()){
                clienteLido = new Cliente();
                clienteLido.setNome(rs.getString("nome"));
                clienteLido.setCpf(rs.getString("cpf"));
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
