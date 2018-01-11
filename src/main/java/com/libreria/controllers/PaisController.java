package com.libreria.controllers;

import com.libreria.dao.PaisDAO;
import com.libreria.db.DBException;
import com.libreria.models.Pais;
import com.libreria.utils.InputField;
import com.libreria.utils.Message;
import com.libreria.utils.Regex;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;

public class PaisController extends Controller<Pais> implements Initializable {
    
    /* Campos de texto del formulario. */
    @FXML
    private InputField txtNombre;
    
    @FXML
    private InputField txtCodigo;
    
    /* Etiquetas para mostrar los mensajes de error en el formulario. */
    @FXML
    private Label messageNombre;
    
    @FXML
    private Label messageCodigo;
    
    /* Columnas de la tabla */
    @FXML
    private TableColumn colNombre;
    
    @FXML
    private TableColumn colCodigo;
    
    private PaisDAO paisDAO;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        paisDAO = new PaisDAO();
        
        initTable();
        onSelectRow();
        initInputNombre();
        initInputCodigo();
    }
    
    private void initTable() {
        /* Se referencian las columnas con los respectivos atributos del objeto Pais */
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colCodigo.setCellValueFactory(new PropertyValueFactory("cod"));
        
        // Se crea un campo de texto en la columna nombres y apellidos para que pueda ser editado.
        colNombre.setCellFactory(TextFieldTableCell.forTableColumn());
        colCodigo.setCellFactory(TextFieldTableCell.forTableColumn());
        
        /* Se cargan los datos desde la base de datos. */
        try {
            List<Pais> paises = paisDAO.all();
            data = FXCollections.observableList(paises);
            
            table.setItems(data);
        } catch (DBException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    private void initInputNombre() {
        txtNombre.setLabelMessage(messageNombre);
        txtNombre.setMaxLength(30);
        txtNombre.setRegex(Regex.TEXT);
        txtNombre.addMessage(InputField.KEY_MAX_LENGTH, Message.getLengthMessage(30));
        txtNombre.addMessage(InputField.KEY_REGEX, Message.TEXT_MESSAGE);
    }
    
    private void initInputCodigo() {
        txtCodigo.setLabelMessage(messageCodigo);
        txtCodigo.setMaxLength(3);
        txtCodigo.setRegex(Regex.TEXT);
        txtCodigo.addMessage(InputField.KEY_MAX_LENGTH, Message.getLengthMessage(3));
        txtCodigo.addMessage(InputField.KEY_REGEX, Message.TEXT_MESSAGE);
    }
    
    @Override
    public void resetForm(ActionEvent ev) {
        txtNombre.setText("");
        txtCodigo.setText("");
        messageCodigo.setText("");
        messageNombre.setText("");
        btnSave.setDisable(true);
    }

    @Override
    public void edit(TableColumn.CellEditEvent ev) {
        
    }
    
    @Override
    public void delete(ActionEvent ev) {
        Pais pais = table.getSelectionModel().getSelectedItem();
        
        try {
            paisDAO.delete(pais.getId());
            data.remove(pais);
        } catch (DBException ex) {
            Message.createAlert(
                    Alert.AlertType.ERROR, 
                    "Error",
                    null,
                    ex.getMessage()
            );
        }
        
        
    }

    @Override
    public void save(ActionEvent ev) {
        Pais pais = new Pais(
                txtCodigo.getText(),
                txtNombre.getText()
        );
        
        try {
            paisDAO.create(pais);
            data.add(pais);
            Message.createAlert(Alert.AlertType.CONFIRMATION, 
                    "País creado",
                    null,
                    "El país " + pais.getNombre() + " fue creado exitosamente."
            );
        } catch (DBException ex) {
            Message.createAlert(
                    Alert.AlertType.ERROR, 
                    "Error",
                    null,
                    ex.getMessage()
            );
        }
    }

    @Override
    public void validate(KeyEvent ev) {
        if (txtNombre.isValid() && txtCodigo.isValid()) {
            btnSave.setDisable(false);
        } else btnSave.setDisable(true);
    }
}
