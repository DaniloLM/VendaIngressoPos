package br.ufg.inf.vendaingresso.dao.impl;

import br.ufg.inf.vendaingresso.Cliente;
import br.ufg.inf.vendaingresso.Evento;
import br.ufg.inf.vendaingresso.Ingresso;
import br.ufg.inf.vendaingresso.Secao;
import br.ufg.inf.vendaingresso.dao.IngressoDAO;
import br.ufg.inf.vendaingresso.utils.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ana Clara
 */
public class IngressoDAOImpl implements IngressoDAO{
    
    private Connection conn = null; 
    private Statement statement = null; 
    private PreparedStatement ps = null; 
    private ResultSet rs = null;
    
    /**
     * Metodo para inserir o ingresso no banco
     * @param secao
     * @param evento
     */
    @Override
    public void salvar(Secao secao, Evento evento){
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO ingresso (id, idsecao) " 
                       + "VALUES ((SELECT NVL(MAX(id),0)+1 FROM ingresso)"
                       + ",(SELECT secao.id "
                       +     "FROM secao "
                       +     "JOIN evento ON secao.idevento = evento.id "
                       +    "WHERE secao.nome LIKE ? AND evento.nome LIKE ?))";
            ps = conn.prepareStatement(sql);            
            ps.setString(1, secao.getNome());
            ps.setString(2, evento.getNome());
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
     * Metodo para atualizar o idcompra do ingresso quando uma compra for realizada
     * 
     * @param cliente
     * @param secao
     * @param evento
     */
    @Override
    public void atualizar(Cliente cliente, Secao secao, Evento evento){
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "UPDATE ingresso SET idcompra = (SELECT compra.id "
                                                        +  "FROM compra JOIN cliente ON compra.idcliente = cliente.id " 
                                                        + "WHERE cliente.cpf LIKE ?) WHERE idsecao = (SELECT secao.id "
                                                                                                     + "FROM secao " 
                                                                                                     + "JOIN evento ON secao.idevento = evento.id " 
                                                                                                     +"WHERE secao.nome LIKE ? AND evento.nome LIKE ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.getCpf());
            ps.setString(2, secao.getNome());
            ps.setString(3, evento.getNome());
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
     * 
     * @return count
     */
    @Override
    public int getVendidosTotal() {
        try{
            conn = ConnectionFactory.getConnection(); 
            String sql = "SELECT COUNT(id) AS vendidos FROM ingresso WHERE idcompra IS NOT NULL";
            ps = conn.prepareStatement(sql); 
            rs = ps.executeQuery();
            if (rs.next()) {
               int count = rs.getInt("vendidos");
               return count;
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
    public int getVendidosSecao(Secao secao, Evento evento) {
        try {
            conn = ConnectionFactory.getConnection();
            String sql =  "SELECT COUNT(ingresso.id) AS vendidos " +
                            "FROM ingresso " +
                            "JOIN secao  on ingresso.idsecao = secao.id " +
                            "JOIN evento on secao.idevento = evento.id " +
                           "WHERE idcompra IS NOT NULL " +
                             "AND evento.nome LIKE ?" +
                             "AND secao.nome LIKE ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, evento.getNome());
            ps.setString(2, secao.getNome());
            rs = ps.executeQuery();  
            if (rs.next()){
                int count = rs.getInt("vendidos");
                return count;
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
    public int getVendidosEvento(Evento evento) {
        try {
            conn = ConnectionFactory.getConnection();
            String sql =   "SELECT COUNT(id) AS vendidos " 
                        +    "FROM ingresso "
                        +    "JOIN secao ON ingresso.idsecao = secao.id " 
                        +    "JOIN evento ON secao.idevento = evento.id " 
                        +   "WHERE idcompra IS NOT NULL " 
                        +     "AND evento.nome LIKE ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, evento.getNome());
            rs = ps.executeQuery(); 
            if(rs.next()){
                int count = rs.getInt("vendidos");
                return count;
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
        }
    }
    
    /**
     * Metodo para listar os ingressos disponiveis por evento 
     * @param evento
     * @return ingressos
     */
    @Override
    public Map<String, Integer> getIngressoDisponiveis(Evento evento){
        Map<String, Integer> ingresso = new HashMap<>();
        try{
            conn = ConnectionFactory.getConnection();
            String sql = "    SELECT secao.nome " +
                         "          ,count(ingresso.id) AS ingressos " +
                         "      FROM ingresso " +
                         "      JOIN secao ON ingresso.idsecao = secao.id " +
                         "      JOIN evento ON secao.idevento = evento.id " +
                         "     WHERE ingresso.idcompra IS NULL AND evento.nome LIKE ? " +
                         "  GROUP BY secao.nome";
            ps = conn.prepareStatement(sql);
            ps.setString(1, evento.getNome());
            rs = ps.executeQuery();
            while (rs.next()) {
                String secaolida = rs.getString("nome");
                int ingressolido = rs.getInt("ingressos");
                ingresso.put(secaolida, ingressolido);
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
