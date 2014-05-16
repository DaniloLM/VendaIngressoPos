/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufg.inf.vendaingresso;

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
    
    @Override
    public void salvar(Compra compra) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
    * Metodo para remover uma compra pelo idcompra e nome do funcionario
    * @param compra
    * @param funcionario
    */
    @Override
    public void remover(Compra compra, Funcionario funcionario) {
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "DELETE " 
                       +   "FROM (SELECT idcompra " 
                       +           "FROM compra c " 
                       +           "JOIN funcionario f ON c.idfuncionario = f.idfuncionario " 
                       +           "WHERE idcompra = ? " 
                       +             "AND f.nome LIKE \'?\');";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, compra.getId());
            ps.setString(2, funcionario.getNome());
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
    * @param funcionario
    * @return rs
    */
    @Override
    public ResultSet getById(Funcionario funcionario){
        try{
            conn = ConnectionFactory.getConnection(); 
            String sql = "SELECT idcompra "
                       +   "FROM compra c"
                       +   "JOIN funcionario f ON c.idfuncionario = f.idfuncionario "
                       +  "WHERE f.nome LIKE \'?\';";
            ps = conn.prepareStatement(sql);
            ps.setString(1, funcionario.getNome());
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
