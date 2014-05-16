
package br.ufg.inf.vendaingresso;

import br.ufg.inf.vendaingresso.utils.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aluno
 */
public class IngressoDAOImpl implements IngressoDAO{
    
    private Connection conn = null; 
    private Statement statement = null; 
    private PreparedStatement ps = null; 
    private ResultSet rs = null;
    
    /**
     * Metodo para inserir o ingresso no banco
     * @param ingresso
     */
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
    
    /**
     * Metodo para contar o total de ingressos vendidos
     * @return rs
     */
    @Override
    public ResultSet getVendidosTotal() { 
        try{
            conn = ConnectionFactory.getConnection(); 
            String sql = "SELECT COUNT(*) "
                       +   "FROM ingressos "
                       +  "WHERE idcompra IS NOT NULL;";
            ps = conn.prepareStatement(sql); 
            rs = ps.executeQuery();
            if (rs.next()) {
               return rs;  
            } else {
                close(); 
                throw new RuntimeException("Não existem ingressos vendidos!"); 
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
        } 
    }
    
    /**
     * Metodo para contar o total de vendidos por secao do evento
     * @param secao
     * @param evento
     * @return rs
     */
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
            if (rs.next()){
                return rs; 
            } else {
                close();
                throw new RuntimeException("Não existem ingressos vendidos para a secao do evento!");
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
        }
    }
    
    /**
     * Metodo para contar o total de ingressos vendidos por evento 
     * @param evento
     * @return rs
     */
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
            if(rs.next()){
                return rs; 
            } else {
                close();
                throw new RuntimeException("Não existem ingressos vendidos para o evento!");
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
            return rs;
        }
    }
    
    /**
     * Metodo para listar os ingressos disponiveis por evento 
     * @param evento
     * @return ingressos
     */
    @Override
    public List<Ingresso> getIngressoDisponiveis(Evento evento){
        List<Ingresso> ingresso = new ArrayList<>();
        try{
            conn = ConnectionFactory.getConnection();
            String sql = "SELECT * "
                       + "FROM ingresso "
                       + "WHERE idcompra IS NULL"
                       + "AND evento LIKE \'?\';";
            ps = conn.prepareStatement(sql);
            ps.setString(1, evento.getNome());
            rs = ps.executeQuery();
            while (rs.next()) {
                Ingresso ingressolido = new Ingresso();
                ingressolido.setId(rs.getLong("id"));
                ingresso.add(ingressolido);
            }
            return ingresso; 
        } catch (SQLException e){
            throw new RuntimeException("Erro " + e.getSQLState()
                                           + " ao atualizar o objeto: " 
                                           + e.getLocalizedMessage());
        } catch (ClassNotFoundException e){
            throw new RuntimeException("Erro ao conectar ao banco: "
                                         + e.getMessage());
        } finally{
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
