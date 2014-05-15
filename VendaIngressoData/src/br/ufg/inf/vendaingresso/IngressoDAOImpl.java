
package br.ufg.inf.vendaingresso;

import br.ufg.inf.vendaingresso.utils.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author aluno
 */
public class IngressoDAOImpl implements IngressoDAO{
    
    private Connection conn = null; 
    private Statement statement = null; 
    private PreparedStatement ps = null; 
    private ResultSet rs = null;
    
    @Override
    public void salvar(Ingresso ingresso){
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO ingresso (id, valor)"
                                     + "VALUES (\' ? \');";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, ingresso.getId());
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
    public ResultSet getVendidosTotal() {
       
        try{
            conn = ConnectionFactory.getConnection(); 
            String sql = "SELECT COUNT(*) "
                       +   "FROM ingressos "
                       +  "WHERE idcompra IS NOT NULL;";
            ps = conn.prepareStatement(sql); 
            rs = ps.executeQuery();
        } catch (SQLException e){
                throw new RuntimeException("Erro " + e.getSQLState()
                                           + " ao atualizar o objeto: " 
                                           + e.getLocalizedMessage());
        } catch (ClassNotFoundException e){
                throw new RuntimeException("Erro ao conectar ao banco: "
                                         + e.getMessage()); 
        } finally {
            close();
            return rs; 
        } 
    }

    @Override
    public ResultSet getVendidosSecao(Secao secao, Evento evento) {
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "SELECT COUNT(*) AS vendidos " 
                       +   "FROM ingresso i " 
                       +   "JOIN evento e on i.idevento = e.idevento "
                       +   "JOIN secao s on i.idsecao = s.idsecao "
                       +  "WHERE idcompra IS NOT NULL " 
                       +    "AND e.nome LIKE \' ? \' "
                       +    "AND s.nome LIKE \' ? \';";
            ps = conn.prepareStatement(sql);
            ps.setString(1, evento.getNome());
            ps.setString(2, secao.getNome());
            rs = ps.executeQuery();  
        } catch (SQLException e){
                throw new RuntimeException("Erro " + e.getSQLState()
                                           + " ao atualizar o objeto: " 
                                           + e.getLocalizedMessage());
        } catch (ClassNotFoundException e){
                throw new RuntimeException("Erro ao conectar ao banco: "
                                         + e.getMessage()); 
        } finally {
            close();
            return rs;
        }
    }

    @Override
    public ResultSet getVendidosEvento(Evento evento) {
        try {
           
            conn = ConnectionFactory.getConnection();
            String sql =   "SELECT COUNT(*) AS vendidos " 
                        +    "FROM ingresso i " 
                        +    "JOIN evento e on i.idevento = e.idevento " 
                        +   "WHERE idcompra IS NOT NULL " 
                        +     "AND e.nome LIKE \' ? \';";
            ps = conn.prepareStatement(sql);
            ps.setString(1, evento.getNome());
            rs = ps.executeQuery();  
        } catch (SQLException e){
                throw new RuntimeException("Erro " + e.getSQLState()
                                           + " ao atualizar o objeto: " 
                                           + e.getLocalizedMessage());
        } catch (ClassNotFoundException e){
                throw new RuntimeException("Erro ao conectar ao banco: "
                                         + e.getMessage()); 
        } finally {
            close();
            return rs;
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
