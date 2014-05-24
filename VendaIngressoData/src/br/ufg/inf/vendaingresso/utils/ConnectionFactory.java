package br.ufg.inf.vendaingresso.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author danilolopesdemoraes
 */
public class ConnectionFactory {
    
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@//inforplace.no-ip.org:1521/pos";
    private static final String PASSWORD = "pos#123";
    private static final String USER = "pos";
    
    public static Connection getConnection() 
            throws ClassNotFoundException, SQLException{
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
}
