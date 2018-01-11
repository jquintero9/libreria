package com.libreria.utils;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.HashMap;

public class InputField extends TextField {
    
    public static final String KEY_MAX_LENGTH = "length";
    public static final String KEY_REGEX = "regex";
    
    private String regex;
    private Label labelMessage;
    private int maxLength;
    private HashMap<String, String> messages;
    
    public InputField() {
        messages = new HashMap();
    }

    @Override
    public void replaceText(int start, int end, String value) {
        if (validate(value)) {
            super.replaceText(start, end, value);
        }
    }

    @Override
    public void replaceSelection(String replacement) {
        if (validate(replacement)) {
            super.replaceSelection(replacement);
        }
    }
    
    private boolean validate(String value) {
        boolean isValid = false;
        
        if (validateLength(value)) {
            if (validateRegex(value)) {
                isValid = true;
                labelMessage.setText("");
            } else labelMessage.setText(messages.get(KEY_REGEX));
        } else labelMessage.setText(messages.get(KEY_MAX_LENGTH));
        
        return isValid;
    }
    
    private boolean validateRegex(String value) {
        return value.matches(regex) || value.equals("");
    }
    
    private boolean validateLength(String value) {
        return getText().length() < maxLength || value.equals("");
    }
    
    public void setRegex(String regex) {
        this.regex = regex;
    }
    
    public void setLabelMessage(Label label) {
        labelMessage = label;
    }
    
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }
    
    public void addMessage(String key, String message) {
        messages.put(key, message);
    }
    
    public boolean isValid() {
        System.out.println(getText().trim());
        return getText().trim().length() > 0;
    }
}
