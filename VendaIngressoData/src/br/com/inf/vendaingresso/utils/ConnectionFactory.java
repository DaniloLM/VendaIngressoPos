package br.com.inf.vendaingresso.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author aluno
 */
public class ConnectionFactory {
    
    private static final String DRIVER = "";
    private static final String URL = "";
    private static final String PASSWORD = "";
    private static final String USER = "";
    
    public static Connection getConnection() 
            throws ClassNotFoundException, SQLException{
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
}
