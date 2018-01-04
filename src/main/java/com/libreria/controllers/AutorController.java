package com.libreria.controllers;

import com.libreria.models.Autor;
import com.libreria.dao.AutorDAO;
import com.libreria.dao.PaisDAO;
import com.libreria.db.DBException;
import com.libreria.models.Pais;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;

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
    
    @FXML
    private ComboBox comboBoxPais;
    
    private ObservableList<Autor> data;
    private ObservableList<Pais> paises;
    
    private HashMap<String, Long> listaPaises;
    
    private AutorDAO autorDAO;
    private PaisDAO paisDAO;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
    }
    
    private void onSelectRow() {
        table.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Autor> observable, Autor oldValue, Autor newValue) -> {
            txfNombres.setText(newValue.getNombres());
            txfApellidos.setText(newValue.getApellidos());
            comboBoxPais.setValue(newValue.getNombrePais());
        });
    }
    
    @FXML
    private void resetForm(ActionEvent ev) {
        txfNombres.setText("");
        txfApellidos.setText("");
        comboBoxPais.setValue(null);
    }
    
    @FXML
    private void editCells(CellEditEvent ev) {
        // Se obtiene la instancia del objeto que ha sido seleccionado.
        Autor autor = table.getSelectionModel().getSelectedItem();
        
        // Se verifica cual es la celda que va a ser editada.
        if (ev.getSource() == nombresColumn) {
            autor.setNombres(ev.getNewValue().toString());
            txfNombres.setText(ev.getNewValue().toString());
        } else if (ev.getSource() == apellidosColumn) {
            autor.setApellidos(ev.getNewValue().toString());
            txfApellidos.setText(ev.getNewValue().toString());
        } else if (ev.getSource() == nacionalidadColumn) {
            Long idPais = listaPaises.get(ev.getNewValue().toString());
            autor.setNombrePais(ev.getNewValue().toString());
            autor.setPais(idPais);
            comboBoxPais.setValue(ev.getNewValue().toString());
        }
        
        // Se actualiza el registro en la base de datos.
        try {
            autorDAO.update(autor);
        } catch (DBException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private void initTable() {
        autorDAO = new AutorDAO();
        
        // Se obtiene la lista de Autores.
        try {
            data = FXCollections.observableList(autorDAO.all());
        } catch (DBException ex) {
            System.out.println("Ocurrio un error al obtener la lista de autores");
        }
        
        // Se conectan las columnas de la tabla con el atributo correspondiente en la clase Autor.
        nombresColumn.setCellValueFactory(new PropertyValueFactory("nombres"));
        apellidosColumn.setCellValueFactory(new PropertyValueFactory("apellidos"));
        nacionalidadColumn.setCellValueFactory(new PropertyValueFactory("nombrePais"));
        
        // Se crea un campo de texto en la columna nombres y apellidos para que pueda ser editado.
        nombresColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        apellidosColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        
        // Se crea una instancia de PaisDAO para obtener la lista de paises desde la base de datos.
        paisDAO = new PaisDAO();
        
        // Se obtiene la lista de paises.
        try {
            List<Pais> paisesData = paisDAO.all();
            paises = FXCollections.observableList(paisesData);
            listaPaises = new HashMap();
            
            paisesData.forEach((p) -> {
                listaPaises.put(p.getNombre(), p.getId());
            });
            
        } catch (DBException ex) {
            System.out.println(ex.getMessage());
        }
        
        nacionalidadColumn.setCellFactory(ComboBoxTableCell.forTableColumn(paises));
        
        comboBoxPais.setItems(paises);
        
        onSelectRow();
        table.setItems(data);
    }
    
    
    
}
