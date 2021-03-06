package controlador;

import InterfazGrafica.MessageFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import negocio.Cancion;
import negocio.Usuario;
import serviciosCliente.ClienteUsuario;

public class PanelBibliotecaPersonalController implements Initializable{
    @FXML
    private ScrollPane scroll;
    
    private VBox panelCanciones;
    private List<Cancion> canciones;
    private int idUsuario;
    private EscuchadorCancion escuchador;
    
    private void mostrarCanciones(){
        if (!this.canciones.isEmpty()){
            this.scroll.setContent(this.panelCanciones);
            this.canciones.forEach((cancion) -> {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/vista/PanelCancion.fxml"));
                try {
                    AnchorPane pane = loader.load();
                    PanelCancionController controller = loader.getController();
                    controller.iniciar(cancion, adquirirUsuarioActual(), this.escuchador);
                    this.panelCanciones.getChildren().add(pane);
                } catch (IOException ex) {
                    Logger.getLogger(PanelArtistasController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }
    private void cargarCanciones(){
        Platform.runLater(() -> {
            try{
                new Cancion().obtenerCancionesUsuario(this.idUsuario).forEach((cancion) -> {
                    this.canciones.add(cancion);
                });
                this.mostrarCanciones();
            }catch(Exception ex){
                MessageFactory.showMessage("Error", "Datos", "No se pudieron obtener los datos", Alert.AlertType.INFORMATION);
                Logger.getLogger(PanelArtistasController.class.getName()).log(Level.SEVERE, null, ex);
            }   
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.canciones = new ArrayList();
    }
    
    public void inicar(int idUsuario, EscuchadorCancion escuchador){
        this.idUsuario = idUsuario;
        this.escuchador = escuchador;
        this.panelCanciones = new VBox();
        this.panelCanciones.setSpacing(5);
        this.cargarCanciones();
    }
    
    public Usuario adquirirUsuarioActual(){
        Usuario usuarioAdquirido;
        usuarioAdquirido=new ClienteUsuario().find_JSON(idUsuario);
        return usuarioAdquirido;
    }
}
