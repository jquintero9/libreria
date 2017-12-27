package com.libreria.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *Permite crear una instancia de conexión con la base de datos.
 */
public class ConnectionDB {
    
    private static final String JDBC_DRIVER = "postgresql";
    private static final String HOST = "localhost";
    private static final String PORT = "5432";
    
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "";
    private static final String DB = "libreria";
    
    private static Connection connection = null;
    
    private ConnectionDB() {}
    
    public static void connect() throws DBException {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:" + JDBC_DRIVER + "://" + HOST + ":" + PORT + "/" + DB,
                    USERNAME,
                    PASSWORD
            );
        } catch(SQLException ex) {
            throw new DBException(DBException.FAIL_CONNECTION);
        }
    }
    
    public static void close() throws DBException {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            throw new DBException("Error al cerrar la conexión con la base de datos.");
        }
    }
    
    public static Connection getConnection() {
        return connection;
    }
}
