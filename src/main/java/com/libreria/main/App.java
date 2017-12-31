package com.libreria.main;

import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = null;
        
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/AppView.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        if (root != null) { 
            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/app.css");
            
            stage.setScene(scene);
            stage.setTitle("Librería la única");
            stage.setResizable(false);
            stage.show();
        }
    }
}
