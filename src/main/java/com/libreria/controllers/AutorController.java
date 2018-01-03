package com.libreria.controllers;

import com.libreria.models.Autor;
import com.libreria.dao.AutorDAO;
import com.libreria.db.DBException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javax.crypto.Cipher;

public class AutorController implements Initializable {

    @FXML
    private TableView<Autor> table;
    
    @FXML
    private TableColumn nombresColumn;
    
    @FXML
    private TableColumn apellidosColumn;
    
    @FXML
    private TableColumn nacionalidadColumn;
    
    @FXML
    private TextField txfNombres;
    
    @FXML
    private TextField txfApellidos;
    
    private ObservableList<Autor> data;
    
    private AutorDAO autorDAO;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
    }
    
    private void onSelectRow() {
        table.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Autor> observable, Autor oldValue, Autor newValue) -> {
            txfNombres.setText(newValue.getNombres());
            txfApellidos.setText(newValue.getApellidos());
        });
    }
    
    @FXML
    private void editNombres(CellEditEvent ev) {
        Autor autor = table.getSelectionModel().getSelectedItem();
        autor.setNombres(ev.getNewValue().toString());
        txfNombres.setText(ev.getNewValue().toString());
        try {
            autorDAO.update(autor);
        } catch (DBException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    private void initTable() {
        autorDAO = new AutorDAO();
        
        try {
            data = FXCollections.observableList(autorDAO.all());
        } catch (DBException ex) {
            System.out.println("Ocurrio un error al obtener la lista de autores");
        }
        
        nombresColumn.setCellValueFactory(new PropertyValueFactory("nombres"));
        apellidosColumn.setCellValueFactory(new PropertyValueFactory("apellidos"));
        nacionalidadColumn.setCellValueFactory(new PropertyValueFactory("nombrePais"));
        
        nombresColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        
        onSelectRow();
        table.setItems(data);
    }
    
    
    
}
