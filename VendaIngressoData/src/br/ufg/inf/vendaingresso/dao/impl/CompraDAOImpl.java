package br.ufg.inf.vendaingresso.dao.impl;

import br.ufg.inf.vendaingresso.Cliente;
import br.ufg.inf.vendaingresso.Compra;
import br.ufg.inf.vendaingresso.Evento;
import br.ufg.inf.vendaingresso.Funcionario;
import br.ufg.inf.vendaingresso.Secao;
import br.ufg.inf.vendaingresso.dao.CompraDAO;
import br.ufg.inf.vendaingresso.utils.ConnectionFactory;
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
public class CompraDAOImpl implements CompraDAO{
    
    private Connection conn = null; 
    private Statement statement = null; 
    private PreparedStatement ps = null; 
    private ResultSet rs = null;
    
   /**
    * Metodo para inserir a compra no banco
    * 
    * @param cliente
    * @param funcionario
    * @param secao
     * @param evento
    */
    @Override
    public void salvar(Cliente cliente, Funcionario funcionario, Secao secao, Evento evento) {
        try{
            conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO compra (id, datacompra, idcliente, idfuncionario, idingresso) "
                         + "VALUES ((SELECT NVL(MAX(id),0)+1 FROM compra)"
                         +         ",SYSDATE"
                         +         ",(SELECT id FROM cliente WHERE cpf LIKE ?) " 
                         +         ",(SELECT id FROM funcionario WHERE cpf LIKE ?) " 
                         +         ",(SELECT ingresso.id "
                         +             "FROM ingresso "
                         +             "JOIN secao ON ingresso.idsecao = secao.id "
                         +             "JOIN evento ON secao.idevento = evento.id " 
                         +             " WHERE secao.nome LIKE ? AND evento.nome LIKE ? AND ROWNUM = 1))";
            ps = conn.prepareStatement(sql);            
            ps.setString(1, cliente.getCpf());
            ps.setString(2, funcionario.getCpf());
            ps.setString(3, secao.getNome());
            ps.setString(4, evento.getNome());
            ps.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Erro " 
                                     + e.getSQLState()
                                     + " ao atualizar o objeto: "
                                     + e.getLocalizedMessage());
        } catch (ClassNotFoundException e){
            throw new RuntimeException("Erro ao conectar no banco: "
                                     + e.getMessage());     
        } finally {
            close();
        }
    }
    
    /**
    * Metodo para remover uma compra cpf do funcionario e do cliente
    * 
    * @param cliente
    * @param funcionario
    */
    @Override
    public void remover(Cliente cliente, Funcionario funcionario) {
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "DELETE FROM compra where id IN (SELECT compra.id FROM compra "
                    + "     JOIN funcionario ON compra.idfuncionario = funcionario.id  "
                    + "     JOIN cliente ON compra.idcliente = cliente.id "
                    + "     WHERE cliente.cpf LIKE \'?\' AND funcionario.cpf LIKE \'?\')";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.getCpf());
            ps.setString(2, funcionario.getCpf());
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
    
   /**
    * Metodo para retornar idcompra pelo funcionario
    * 
     * @param cliente
    * @param funcionario
    * @return compra
    */
    @Override
    public Map<String, String> getCompra(Cliente cliente, Funcionario funcionario){
        Map<String, String> compra = new HashMap<>();
        try{
            conn = ConnectionFactory.getConnection(); 
            String sql = "SELECT to_char(datacompra, 'dd/mm/yyyy') as datacompra, secao.nome as nome FROM compra, ingresso, secao " +
                          "WHERE compra.idingresso = ingresso.id " +
                            "AND ingresso.idsecao = secao.id " +
                            "AND idfuncionario IN (SELECT id FROM funcionario WHERE cpf LIKE ?) " +
                            "AND idcliente IN (SELECT id FROM cliente WHERE cpf LIKE ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, funcionario.getCpf());
            ps.setString(2, cliente.getCpf());
            rs = ps.executeQuery();
            while(rs.next()){
                String datacompra = rs.getString("datacompra");
                String secao = rs.getString("nome"); 
                compra.put(datacompra, secao); 
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
        return compra;
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
