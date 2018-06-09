/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class VentanaInicioSesionController implements Initializable {

    @FXML
    private Button botonIngresar;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void desplegarMenuPrincipal(ActionEvent event) {
        FXMLLoader loger = new FXMLLoader(getClass().getResource("/vista/MenuPrincipal.fxml"));
        Parent root = null;
        try {
            root = (Parent) loger.load();
        } catch (IOException ex) {
            Logger.getLogger(VentanaInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        VentanaMenuPrincipalController menuController = loger.getController();
        Stage menu = new Stage();
        menu.setScene(new Scene(root));
        menu.show();
        Stage ventanaAnterior = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ventanaAnterior.close();
    }
    
}
