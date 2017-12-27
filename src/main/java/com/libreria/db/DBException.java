package com.libreria.db;

public class DBException extends Exception {
    
    public static String FAIL_TRANSACTION = "Error ha ocurrido un error al procesar la transacción.";
    public static String FAIL_CONNECTION = "Error ocurrio un error al conectarse a la base de datos.";
    public static String FAIL_CLOSE_CONNECTION = "Ocurrio un error al cerrar la conexión con la base de datos.";
    public static String FAIL_CLOSE_STM = "Ocurrio un error al liberar los recursos.";
    public static String FAIL_STM = "Ocurrio un error al asignar los parametros del statement.";
    
    public DBException(String message) {
        super(message);
    }
}
