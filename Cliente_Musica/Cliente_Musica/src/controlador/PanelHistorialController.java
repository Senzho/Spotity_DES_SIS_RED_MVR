package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import negocio.Cancion;
import negocio.Historialreproduccion;

public class PanelHistorialController implements Initializable{
    @FXML
    private VBox panelCanciones;
    
    private List<Cancion> canciones;
    
    private void cargarHistorial(){
        if (!this.canciones.isEmpty()){
            this.canciones.forEach((cancion) -> {
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
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void iniciar(int idUsuario){
        Platform.runLater(() -> {
            this.canciones = new Historialreproduccion().consultarHistorial(idUsuario);
            this.cargarHistorial();
        });
    }
}
