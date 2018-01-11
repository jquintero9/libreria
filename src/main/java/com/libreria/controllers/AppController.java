package com.libreria.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.Parent;
import javafx.scene.Scene;

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
    
    /* Escenas que serán visualizados según la opción del menú */
    private Scene autoresScene;
    
    /* Estas dos variables representan el estado del menú */
    private byte currentOption;
    private byte prevOption;
    
    /* Este contenedr almacena los botones del menú. */
    private ArrayList<Label> buttonsMenu;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            /* Se inicializa el contenedor y se agregan los botones. */
            buttonsMenu = new ArrayList();
            buttonsMenu.add(lbAutores);
            buttonsMenu.add(lbPaises);
            buttonsMenu.add(lbGeneros);
            buttonsMenu.add(lbLibros);
            
            initPanes();
        } catch (IOException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initPanes() throws IOException {
        /* Se inicializa el contenedor de la vista Autores. */
        Parent rootAutores = FXMLLoader.load((getClass().getResource("/fxml/AutorView.fxml")));
        
        if (rootAutores != null) {
            autoresScene = new Scene(rootAutores);
            mainContainer.setRight(autoresScene.getRoot());
        } else System.out.println("Not Loaded");
        
    }
    
    private void selectOption() {
        /* Se actuliaza el botón que ha sido presionado. */
        buttonsMenu.get(prevOption).getStyleClass().remove("active");
        buttonsMenu.get(currentOption).getStyleClass().add("active");
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
        
        if (prevOption != currentOption)
            selectOption();
    }
}
