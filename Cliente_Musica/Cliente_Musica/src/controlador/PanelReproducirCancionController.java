/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import InterfazGrafica.MessageFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblArtista;
    @FXML
    private Label lblAlbum;
    @FXML
    private Label lblGenero;
    @FXML
    private ImageView imagenAlbum;
    @FXML
    private ImageView btnDetenerCancion;
    private ClienteCancion clienteCancion;
    private List<Cancion> cola;
    private Stage stage;
    private Cancion cancionActual;
    private EscuchadorCancion escuchador;
    private Usuario usuario;

    private void agregarAVista(Cancion cancion, int posicion) {
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
        this.cola = new ArrayList();
        this.clienteCancion = new ClienteCancion();
    }
    public void detener(){
        if(this.cancionActual != null){
            cancionActual.detenerCancion();
        }
    }
    public void iniciar(Stage stage, Usuario usuario, EscuchadorCancion escuchador) {
        this.stage = stage;
        this.usuario = usuario;
        this.escuchador = escuchador;
        this.stage.hide();
    }

    public void setCancion(Cancion cancion) {
        if(this.cancionActual!=null){
            cancionActual.detenerCancion();
        }
        this.cancionActual = cancion;
        InetAddress ping;
        String ip = "192.168.43.126";
        try {
            ping = InetAddress.getByName(ip);
            if (ping.isReachable(5000)) {
                this.stage.show ();
                cancion.reproducirCancion();
                cargarCancion();
            } else {
                MessageFactory.showMessage("Aviso", "Conexión fallida", "No es posible acceder al servicio", Alert.AlertType.INFORMATION);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void cargarCancion(){
        this.lblAlbum.setText(this.cancionActual.getIdAlbum().getNombre());
        this.lblArtista.setText(this.cancionActual.getIdArtista().getNombre());
        this.lblGenero.setText(this.cancionActual.getGenero());
        this.lblNombre.setText(this.cancionActual.getNombre());
        this.imagenAlbum.setImage(new Image("http://192.168.43.126:8080/Spotify/Albunes/"+this.cancionActual.getIdAlbum().getIdAlbum()+".jpg"));
    }
    //verificar reproducción antes de mostrar esta ventana:

    public void generarEstacion(String genero) {
        listaCanciones.getChildren().clear();
        cola = new Cancion().obtenerCanciones(genero);
        cola.forEach((cancion) -> {
            this.agregarAVista(cancion, 0);
        });
        this.stage.show();
    }

    public void agregarSiguiente(Cancion cancion) {
        this.cola.add(0, cancion);
        this.agregarAVista(cancion, 0);
        this.stage.show();
    }

    public void agregarFinal(Cancion cancion) {
        this.cola.add(cancion);
        this.agregarAVista(cancion, this.cola.isEmpty()?0:this.cola.size() - 1);
        this.stage.show();
    }

    public void setCola(List<Cancion> cola) {
        this.cola = cola;
    }

    public void btnGenerarestacion_onClick() {
        if (this.cancionActual != null){
            cola = new ArrayList();
            listaCanciones.getChildren().clear();
            cola = new Cancion().obtenerCanciones(this.cancionActual.getGenero());
            cola.forEach((cancion) -> {
                this.agregarAVista(cancion, 0);
            });
        }  
    }
}
