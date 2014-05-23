package br.ufg.inf.vendaingresso.dao.impl;

import br.ufg.inf.vendaingresso.dao.EventoDAO;
import br.ufg.inf.vendaingresso.Evento;
import br.ufg.inf.vendaingresso.utils.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author frederico
 */
public class EventoDAOImpl implements EventoDAO {

    private Connection conn = null;
    private Statement statement = null;
    private PreparedStatement ps = null;

    /**
     * Metodo para inserir um evento no banco
     * @param evento 
     */
    @Override
    public void salvar(Evento evento) {
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO EVENTO (id, nome, dataevento) " 
                       +             "VALUES ((SELECT NVL(MAX(id),0)+1 FROM evento), ?, to_date(?, 'yyyy/mm/dd'))";
            ps = conn.prepareStatement(sql);            
            ps.setString(1, evento.getNome());
            ps.setString(2, evento.getDataEvento()); 
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
    
    private void close() {
        try {
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
