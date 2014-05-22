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
     * @param ingresso
     * @param secao
     * @param evento
     */
    @Override
    public void salvar(Ingresso ingresso, Secao secao, Evento evento){
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO ingresso VALUES (SELECT NVL(MAX(id),0)+1, " 
                       +                            ",(SELECT id " 
                       +                                "FROM secao " 
                       +                               "WHERE nome LIKE \'?\') " 
                       +                            ",(SELECT id " 
                       +                                "FROM evento "
                       +                               "WHERE nome LIKE \'?\'));";
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
     */
    @Override
    public void atualizar(Cliente cliente){
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "UPDATE ingresso "
                   +        "SET idcompra = (SELECT id"
                   +                          "FROM compra "
                   +                          "JOIN cliente ON compra.idcliente = cliente.id "
                   +                         "WHERE cliente.cpf LIKE \'?\');";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.getCpf());
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
        int count = 0;
        try{
            conn = ConnectionFactory.getConnection(); 
            String sql = "SELECT COUNT(*) AS vendidos"
                       +   "FROM ingressos "
                       +  "WHERE idcompra IS NOT NULL;";
            ps = conn.prepareStatement(sql); 
            rs = ps.executeQuery();
            if (rs.next()) {
               count = rs.getInt("vendidos");
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
            return count;
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
        int count = 0;
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
                count = rs.getInt("vendidos");
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
            return count;
        }
    }
    
    /**
     * Metodo para contar o total de ingressos vendidos por evento 
     * @param evento
     * @return rs
     */
    @Override
    public int getVendidosEvento(Evento evento) {
        int count = 0;
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
                count = rs.getInt("vendidos");
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
            return count;
        }
    }
    
    /**
     * Metodo para listar os ingressos disponiveis por evento 
     * @param evento
     * @return ingressos
     */
    @Override
    public Map<Ingresso, Secao> getIngressoDisponiveis(Evento evento){
        Map<Ingresso,Secao> ingresso = new HashMap<>();
        try{
            conn = ConnectionFactory.getConnection();
            String sql = "SELECT ingresso.id AS id " 
                       + "      ,secao.nome AS nome "
                       + "  FROM ingresso "
                       + "  JOIN secao ON ingresso.idsecao = secao.id "
                       + "  JOIN evento ON ingress.idevento = evento.id "
                       + " WHERE ingresso.idcompra IS NULL"
                       + "   AND evento.nome LIKE \'?\';";
            ps = conn.prepareStatement(sql);
            ps.setString(1, evento.getNome());
            rs = ps.executeQuery();
            while (rs.next()) {
                Ingresso ingressolido = new Ingresso();
                Secao secaolida = new Secao();
                ingressolido.setId(rs.getLong("id"));
                secaolida.setNome(rs.getString("nome"));
                ingresso.put(ingressolido, secaolida);
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
