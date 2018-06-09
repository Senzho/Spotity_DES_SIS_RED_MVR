/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_musica;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Renato
 */
public class Cliente_Musica extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/vista/VentanaInicioSesion.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);      
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
