/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import negocio.Cancion;
import serviciosCliente.ClienteCancion;

/**
 *
 * @author Desktop
 */
public class PanelReproducirCancionController implements Initializable {
    @FXML
    private VBox listaCanciones;
    private ClienteCancion clienteCancion;
    private List<Cancion> cancionesEstacion;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.clienteCancion = new ClienteCancion();
    }
    public void btnGenerarestacion_onClick(){
        cancionesEstacion = new ArrayList();
        cancionesEstacion = this.clienteCancion.buscarCanciones("rock");
        for (Cancion cancion:cancionesEstacion){
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/vista/PanelCancion.fxml"));
            AnchorPane panel;
            try {
                panel = loader.load();
                PanelCancionController controller = loader.getController();
                controller.iniciar(cancion);
                this.listaCanciones.getChildren().add(panel);
            } catch (IOException ex) {
                Logger.getLogger(PanelReproducirCancionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
