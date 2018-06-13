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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import negocio.Album;
import serviciosCliente.ClienteCancion;

public class PanelCancionesController implements Initializable {
    @FXML
    private Label nombreAlbum;
    @FXML
    private ImageView imagen;
    @FXML
    private VBox panelCanciones;
    
    private Album album;
    
    private void cargarCanciones(){
        new ClienteCancion().obtenerCancionesAlbum(this.album.getIdAlbum()).forEach((cancion) -> {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/vista/PanelCancion.fxml"));
            try {
                AnchorPane pane = loader.load();
                PanelCancionController controller = loader.getController();
                controller.iniciar(cancion);
                this.panelCanciones.getChildren().add(pane);
            } catch (IOException ex) {
                Logger.getLogger(PanelArtistasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    private void cargarAlbum(){
        this.nombreAlbum.setText(this.album.getNombre());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
    public void iniciar(Album album){
        this.album = album;
        this.cargarAlbum();
        Platform.runLater(() -> {
            this.cargarCanciones();
        });
    }
}
