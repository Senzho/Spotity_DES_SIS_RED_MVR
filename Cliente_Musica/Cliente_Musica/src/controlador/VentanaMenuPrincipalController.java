/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Renato
 */
public class VentanaMenuPrincipalController implements Initializable {

    @FXML
    private Pane panelPrincipal;
    @FXML
    private Label etiquetaNombreUsuario;
    @FXML
    private Button botonBibliotecas;
    @FXML
    private Button botonListas;
    @FXML
    private Button botonSubirCancion;
    @FXML
    private Button botonEstablecerCalidad;
    @FXML
    private Button botonCerrarSesion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void desplegarPanelBibliotecas(ActionEvent event) {
        
    }


    @FXML
    private void desplegarPanelSubirCancion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(VentanaMenuPrincipalController.class.getResource("/vista/PanelSubirCancion.fxml"));
        Parent root = (Parent) loader.load();
        PanelSubirCancionController panelSubir = loader.getController();
        panelPrincipal.getChildren().clear();
        panelPrincipal.getChildren().add(root);
    }

    @FXML
    private void desplegarPanelEstablcerCalidad(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(VentanaMenuPrincipalController.class.getResource("/vista/VentanaCalidadAudio.fxml"));
        Parent root = (Parent) loader.load();
        VentanaCalidadAudioController ventanaCalidad = loader.getController();
        panelPrincipal.getChildren().clear();
        panelPrincipal.getChildren().add(root);
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        Stage stage = (Stage) botonCerrarSesion.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void desplegarPanelListasDeReproduccion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(VentanaMenuPrincipalController.class.getResource("/vista/PanelListasReproduccion.fxml"));
        Parent root = (Parent) loader.load();
        PanelListasReproduccionController panelListas = loader.getController();
        panelPrincipal.getChildren().clear();
        panelPrincipal.getChildren().add(root);
    }
    
}
