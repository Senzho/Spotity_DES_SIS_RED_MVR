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
import javafx.stage.Stage;
import negocio.Cancion;
import negocio.Usuario;
import serviciosCliente.ClienteCancion;

/**
 *
 * @author Desktop
 */
public class PanelReproducirCancionController implements Initializable {
    @FXML
    private VBox listaCanciones;
    
    private ClienteCancion clienteCancion;
    private List<Cancion> cola;
    private Stage stage;
    private Cancion cancionActual;
    private EscuchadorCancion escuchador;
    private Usuario usuario;
    
    private void agregarAVista(Cancion cancion, int posicion){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/vista/PanelCancion.fxml"));
        AnchorPane panel;
        try {
            panel = loader.load();
            PanelCancionController controller = loader.getController();
            controller.iniciar(cancion, this.usuario, this.escuchador);
            this.listaCanciones.getChildren().add(posicion, panel);
        } catch (IOException ex) {
            Logger.getLogger(PanelReproducirCancionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.clienteCancion = new ClienteCancion();
    }
    
    public void iniciar(Stage stage, Usuario usuario, EscuchadorCancion escuchador){
        this.stage = stage;
        this.escuchador = escuchador;
        this.stage.hide();
    }
    public void setCancion(Cancion cancion){
        this.cancionActual = cancion;
        //verificar reproducción antes de mostrar esta ventana:
        this.stage.show();
    }
    //Este método se ocuparía cuando la estación de radio se crea fuera de esta ventana,
    public void generarEstacion(String genero){
        
    }
    public void agregarSiguiente(Cancion cancion){
        this.cola.add(0, cancion);
        this.agregarAVista(cancion, 0);
    }
    public void agregarFinal(Cancion cancion){
        this.cola.add(cancion);
        this.agregarAVista(cancion, this.cola.size());
    }
    public void setCola(List<Cancion> cola){
        this.cola = cola;
    }
    
    public void btnGenerarestacion_onClick(){
        cola = new ArrayList();
        cola = this.clienteCancion.buscarCanciones("rock");
        for (Cancion cancion:cola){
            this.agregarAVista(cancion, this.cola.size());
        }
    }
    
}
