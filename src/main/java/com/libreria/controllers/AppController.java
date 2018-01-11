package com.libreria.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

public class AppController implements Initializable {
    
    /* Estas constantes representan las opciones del menú. */
    private final byte AUTORES = 0;
    private final byte PAISES = 1;
    private final byte GENEROS = 2;
    private final byte LIBROS = 3;
    
    /* Estos nodos representan los botones del menú. */
    @FXML
    private Label lbAutores;
    
    @FXML
    private Label lbPaises;
    
    @FXML
    private Label lbGeneros;
    
    @FXML
    private Label lbLibros;
    
    /* Este es el contenedor principal. */
    @FXML
    private BorderPane mainContainer;
    
    /* Estas dos variables representan el estado del menú */
    private byte currentOption;
    private byte prevOption;
    
    /* Este contenedor almacena los botones del menú. */
    private ArrayList<Label> buttonsMenu;
    
    /* Este diccionario almacena las rutas de los archivos que continen las
       distintas vistas.*/
    private HashMap<Byte, Scene> scenes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            /* Se inicializa el contenedor y se agregan los botones del menú. */
            buttonsMenu = new ArrayList();
            buttonsMenu.add(lbAutores);
            buttonsMenu.add(lbPaises);
            buttonsMenu.add(lbGeneros);
            buttonsMenu.add(lbLibros);
            
            /* Se inicializa el hashmap con las rutas de los arvhivos fxml que contienen las vistas. */
            initScenes();
            
            /* Se establece la escena por defecto */
            mainContainer.setCenter(scenes.get(currentOption).getRoot());
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se ha podido cargar la vista.");

            alert.showAndWait();
        }
    }
    
    private void initScenes() throws IOException {
        /* Se establece opción del menú Autores por defecto */
        currentOption = AUTORES;
        
        scenes = new HashMap();
        
        /* Se inicializa el contenedor de la vista Autores. */
        //Parent rootAutores = FXMLLoader.load((getClass().getResource("/fxml/AutorView.fxml")));
        //Parent rootPaises = FXMLLoader.load(getClass().getResource("/fxml/PaisView.fxml"));
        
        scenes.put(AUTORES, new Scene(FXMLLoader.load(getClass().getResource("/fxml/AutorView.fxml"))));
        scenes.put(PAISES, new Scene(FXMLLoader.load(getClass().getResource("/fxml/PaisView.fxml"))));
    }
    
    private void updateButtonState() {
        /* Se actuliaza el botón que ha sido presionado. */
        buttonsMenu.get(prevOption).getStyleClass().remove("active");
        buttonsMenu.get(currentOption).getStyleClass().add("active");
    }
    
    private void loadPane() {
        mainContainer.setCenter(scenes.get(currentOption).getRoot());
    }
    
    /**
     * Este función se ejecuta cada vez que el usuario hace click
     * en alguno de los botones del menu.
     * 
     * Se encarga de verificar cual botón fué presionado, para luego realizar
     * la acción correspondiente.
     * 
     * @param ev 
     */
    @FXML
    private void onClickMenu(MouseEvent ev) {
        prevOption = currentOption;
        
        if (ev.getSource() == lbAutores) {
            if (currentOption != AUTORES) {
                currentOption = AUTORES;
            }
        } else if (ev.getSource() == lbPaises) {
            if (currentOption != PAISES) {
                currentOption = PAISES;
            }
        } else if (ev.getSource() == lbGeneros) {
            if (currentOption != GENEROS) {
                currentOption = GENEROS;
            }
        } else if (ev.getSource() == lbLibros) {
            if (currentOption != LIBROS) {
                currentOption = LIBROS;
            }
        }
        
        if (prevOption != currentOption) {
            updateButtonState();
            loadPane();
        }
    }
}
