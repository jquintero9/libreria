package com.libreria.controllers;

import com.libreria.dao.AutorDAO;
import com.libreria.dao.PaisDAO;
import com.libreria.db.DBException;
import com.libreria.models.Autor;
import com.libreria.models.Pais;
import com.libreria.utils.InputField;
import com.libreria.utils.Message;
import com.libreria.utils.Regex;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class AutorController implements Initializable {
    
    // Componentes de la tabla
    @FXML
    private TableView<Autor> table;
    
    @FXML
    private TableColumn nombresColumn;
    
    @FXML
    private TableColumn apellidosColumn;
    
    @FXML
    private TableColumn nacionalidadColumn;
    
    // Componentes del formulario
    @FXML
    private InputField txfNombres;
    
    @FXML
    private InputField txfApellidos;
    
    @FXML
    private ComboBox comboBoxPais;
    
    @FXML
    private Label messageNombres;
    
    @FXML 
    private Label messageApellidos;
    
    // Botones
    @FXML
    private Button btnGuardar;
    
    @FXML
    private Button btnEliminar;
    
    private ObservableList<Autor> data;
    private ObservableList<Pais> paises;
    
    private HashMap<String, Long> listaPaises;
    
    private AutorDAO autorDAO;
    private PaisDAO paisDAO;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        initInputNombres();
        initInputApellidos();
    }
    
    private void initInputNombres() {
        txfNombres.setLabelMessage(messageNombres);
        txfNombres.setMaxLength(64);
        txfNombres.setRegex(Regex.TEXT);
        txfNombres.addMessage(InputField.KEY_MAX_LENGTH, Message.getLengthMessage(64));
        txfNombres.addMessage(InputField.KEY_REGEX, Message.TEXT_MESSAGE);
    }
    
    private void initInputApellidos() {
        txfApellidos.setLabelMessage(messageApellidos);
        txfApellidos.setMaxLength(64);
        txfApellidos.setRegex(Regex.TEXT);
        txfApellidos.addMessage(InputField.KEY_MAX_LENGTH, Message.getLengthMessage(64));
        txfApellidos.addMessage(InputField.KEY_REGEX, Message.TEXT_MESSAGE);
    }
    
    private void onSelectRow() {
        table.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Autor> observable, Autor oldValue, Autor newValue) -> {
            btnEliminar.setDisable(false);
        });
    }
    
    @FXML
    private void validate() {
         if (txfNombres.isValid() && txfApellidos.isValid() && comboBoxPais.getValue() != null) {
            btnGuardar.setDisable(false);
        } else btnGuardar.setDisable(true);
    }
    
    @FXML
    private void reset() {
        txfNombres.setText("");
        messageNombres.setText("");
        messageApellidos.setText("");
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
            //txfNombres.setText(ev.getNewValue().toString());
        } else if (ev.getSource() == apellidosColumn) {
            autor.setApellidos(ev.getNewValue().toString());
            //txfApellidos.setText(ev.getNewValue().toString());
        } else if (ev.getSource() == nacionalidadColumn) {
            Long idPais = listaPaises.get(ev.getNewValue().toString());
            autor.setNombrePais(ev.getNewValue().toString());
            autor.setPais(idPais);
            //comboBoxPais.setValue(ev.getNewValue().toString());
        }
        
        // Se actualiza el registro en la base de datos.
        try {
            autorDAO.update(autor);
        } catch (DBException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    private void save(ActionEvent ev) {
        Autor autor = new Autor(
                txfNombres.getText(),
                txfApellidos.getText(),
                listaPaises.get(comboBoxPais.getValue().toString())
        );
        
        autor.setNombrePais(comboBoxPais.getValue().toString());
        
        try {
            autorDAO.create(autor);
            data.add(autor);
        } catch (DBException ex) {
            System.out.println(ex.getMessage());
        }
        
        reset();
    }
    
    @FXML
    private void delete(ActionEvent ev) {
        
        Autor autor = table.getSelectionModel().getSelectedItem();
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Eliminar Autor");
        alert.setHeaderText("Esta acción no podrá ser revertida.");
        alert.setContentText("¿Está seguro que desea eliminar el autor " + autor.getFullName() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                autorDAO.delete(autor.getId());
                data.remove(autor);
            } catch (DBException ex) {
                System.out.println(ex.getMessage());
            }
            
            Alert alert2 = new Alert(AlertType.INFORMATION);
            alert2.setTitle("Operación exitosa");
            alert2.setHeaderText(null);
            alert2.setContentText("El autor " + autor.getFullName() + " ha sido eliminado exitosamente.");

            alert2.showAndWait();
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
