package br.ufg.inf.vendaingresso.dao.impl;

import br.ufg.inf.vendaingresso.dao.CompraDAO;
import br.ufg.inf.vendaingresso.Cliente;
import br.ufg.inf.vendaingresso.Compra;
import br.ufg.inf.vendaingresso.Funcionario;
import br.ufg.inf.vendaingresso.Secao;
import br.ufg.inf.vendaingresso.utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    */
    @Override
    public void salvar(Cliente cliente, Funcionario funcionario, Secao secao) {
        try{
            conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO compra " 
                       + "VALUES (SELECT NVL(MAX(id),0)+1,"
                       +         ",SYSDATE"
                       +         ",(SELECT id "
                       +             "FROM cliente "
                       +            "WHERE cpf LIKE \'?\')"
                       +         ",(SELECT id "
                       +             "FROM funcionario "
                       +            "WHERE cpf LIKE \'?\')"
                       +         ",(SELECT ingresso.id "
                       +             "FROM ingresso "
                       +             "JOIN secao ON ingresso.idsecao = secao.id "
                       +            "WHERE secao.nome LIKE \'?\'));";
            ps = conn.prepareStatement(sql);            
            ps.setString(1, cliente.getCpf());
            ps.setString(2, funcionario.getCpf());
            ps.setString(3, secao.getNome());
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
    * Metodo para remover uma compra pelo idcompra e nome do funcionario
    * 
    * @param cliente
    * @param funcionario
    */
    @Override
    public void remover(Cliente cliente, Funcionario funcionario) {
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "DELETE " 
                       +   "FROM (SELECT id " 
                       +           "FROM compra c " 
                       +           "JOIN funcionario f ON c.idfuncionario = f.id " 
                       +           "JOIN cliente ON c.idcliente = cliente.id"
                       +          "WHERE cliente.cpf LIKE \'?\' " 
                       +            "AND f.cpf LIKE \'?\');";
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
    public Compra getCompra(Cliente cliente, Funcionario funcionario){
        Compra compra = null;
        try{
            conn = ConnectionFactory.getConnection(); 
            String sql = "SELECT * "
                       +   "FROM compra c"
                       +   "JOIN funcionario f ON c.idfuncionario = f.id "
                       +   "JOIN cliente ON c.idcliente = cliente.id"
                       +  "WHERE cliente.cpf LIKE \'?\' "
                       +    "AND f.cpf LIKE \'?\';";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cliente.getCpf());
            ps.setString(2, funcionario.getCpf());
            rs = ps.executeQuery();
            if(rs.next()){
                compra = new Compra(); 
                compra.setId(rs.getLong("id"));
                compra.setDataCompra(rs.getDate("datacompra"));
            } else {
                close();
                throw new RuntimeException("Compra não encontrada!");
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
            return compra; 
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
