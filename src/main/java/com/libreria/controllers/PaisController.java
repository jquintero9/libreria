package com.libreria.controllers;

import com.libreria.models.Pais;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.input.KeyEvent;

public class PaisController extends Controller<Pais> implements Initializable {
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        onSelectRow();
    }
    
    @Override
    public void resetForm(ActionEvent ev) {
        btnDelete.setDisable(false);
    }

    @Override
    public void edit(TableColumn.CellEditEvent ev) {
        
    }
    
    @Override
    public void delete(ActionEvent ev) {
        btnSave.setDisable(false);
    }

    @Override
    public void save(ActionEvent ev) {
    
    }

    @Override
    public void validate(KeyEvent ev) {
        
    }
    
}
