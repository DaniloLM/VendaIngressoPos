/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    
    @Override
    public void salvar(Compra compra, Cliente cliente, Funcionario funcionario, Secao secao) {
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
            ps.setString(2, cliente.getCpf());
            ps.setString(3, funcionario.getCpf());
            ps.setString(4, secao.getNome());
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
    public Compra getById(Funcionario funcionario){
        Compra compra = null;
        try{
            conn = ConnectionFactory.getConnection(); 
            String sql = "SELECT * "
                       +   "FROM compra c"
                       +   "JOIN funcionario f ON c.idfuncionario = f.idfuncionario "
                       +  "WHERE f.nome LIKE \'?\';";
            ps = conn.prepareStatement(sql);
            ps.setString(1, funcionario.getNome());
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