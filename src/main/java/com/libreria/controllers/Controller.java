package com.libreria.controllers;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.input.KeyEvent;

public abstract class Controller<Model> {
    
    @FXML
    protected TableView<Model> table;
    
    @FXML
    protected Button btnSave;
    
    @FXML
    protected Button btnDelete;
    
    @FXML
    protected ObservableList<Model> data;
    
    protected void onSelectRow() {
        table.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Model> observable, Model oldValue, Model newValue) -> {
            btnSave.setDisable(false);
        });
    }
    
    @FXML
    public abstract void edit(CellEditEvent ev);
    
    @FXML
    public abstract void save(ActionEvent ev);
    
    @FXML
    public abstract void delete(ActionEvent ev);
    
    @FXML
    public abstract void validate(KeyEvent ev);
    
    @FXML
    public abstract void resetForm(ActionEvent ev);
    
}
