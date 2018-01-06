package com.libreria.utils;

public class Message {
    
    public static final String TEXT_MESSAGE = "Este campo solo admite letras.";
    
    public static String getLengthMessage(int maxLength) {
        return "Este campo solo admite " + maxLength + " carácteres.";
    }
}
