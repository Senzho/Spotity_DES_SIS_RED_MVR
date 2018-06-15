package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import negocio.Album;
import negocio.Cancion;
import negocio.Usuario;

public class PanelCancionesController implements Initializable {
    @FXML
    private Label nombreAlbum;
    @FXML
    private ImageView imagen;
    @FXML
    private VBox panelCanciones;
    private Usuario usuarioActual;
    
    private Album album;
    private EscuchadorCancion escuchador;
    
    private void cargarCanciones(){
        new Cancion().obtenerCanciones(this.album.getIdAlbum()).forEach((cancion) -> {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/vista/PanelCancion.fxml"));
            try {
                AnchorPane pane = loader.load();
                PanelCancionController controller = loader.getController();
                controller.iniciar(cancion, usuarioActual, this.escuchador);
                this.panelCanciones.getChildren().add(pane);
            } catch (IOException ex) {
                Logger.getLogger(PanelArtistasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    private void cargarAlbum(){
        this.nombreAlbum.setText(this.album.getNombre());
        Platform.runLater(() -> {
            this.imagen.setImage(new Image("http://localhost:8080/AccesoSpotify2018/Albumes/" + this.album.getIdAlbum() + ".jpg"));
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
    public void iniciar(Album album, Usuario usuario, EscuchadorCancion escuchador){
        usuarioActual=usuario;
        this.escuchador = escuchador;
        this.album = album;
        this.cargarAlbum();
        Platform.runLater(() -> {
            this.cargarCanciones();
        });
    }
}
