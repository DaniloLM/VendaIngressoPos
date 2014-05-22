package br.ufg.inf.vendaingresso.dao.impl;

import br.ufg.inf.vendaingresso.dao.SecaoDAO;
import br.ufg.inf.vendaingresso.Secao;
import br.ufg.inf.vendaingresso.utils.*;
        
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ana Clara
 */
public class SecaoDAOImpl implements SecaoDAO{
    
    private Connection conn = null; 
    private Statement statement = null; 
    private PreparedStatement ps = null; 
   
    

    /**
     * Metodo para inserir a secao no banco
     * @param secao
     */
    @Override
    public void salvar(Secao secao) {
        try {
            conn = ConnectionFactory.getConnection(); 
            String sql = "INSERT INTO secao (id, nome, valor) "
                       +            "VALUES (SELECT NVL(MAX(id),0)+1, \'?\', ? FROM secao);";
            ps = conn.prepareStatement(sql);
            ps.setString(1, secao.getNome());
            ps.setDouble(2, secao.getValor());
            ps.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Erro " + e.getSQLState()
                                      + " ao atualizar o banco: " 
                                      + e.getLocalizedMessage());
        } catch (ClassNotFoundException e){
            throw new RuntimeException("Erro ao conectar no banco: "
                                      + e.getMessage());
        } finally {
            close();
        }
        
        
    }
    
    public void close(){
        try {            
            if (statement != null) {
                statement.close();
            }
            
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e){
            
        }
    }
}
