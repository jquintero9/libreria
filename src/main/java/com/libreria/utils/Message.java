package com.libreria.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Message {
    
    public static final String TEXT_MESSAGE = "Este campo solo admite letras.";
    
    public static String getLengthMessage(int maxLength) {
        return "Este campo solo admite " + maxLength + " carácteres.";
    }
    
    public static void createAlert(AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
