package controlador;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import negocio.Album;
import negocio.Artista;
import negocio.Cancion;
import serviciosCliente.ClienteCancionPrivada;

public class PanelBibliotecaPersonalController implements Initializable{
    @FXML
    private ComboBox combo;
    @FXML
    private ScrollPane scroll;
    
    private VBox panelCanciones;
    private FlowPane panelGeneral;
    private List<Cancion> canciones;
    private List<Artista> artistas;
    private List<Album> albumes;
    private int idUsuario;
    
    private void cargarCombo(){
        this.combo.getItems().add("Canciones");
        this.combo.getItems().add("Albumes");
        this.combo.getItems().add("Artistas");
        this.combo.setValue("Canciones");
    }
    private void cargarCanciones(){
        if (!this.canciones.isEmpty()){
            this.scroll.setContent(this.panelCanciones);
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
    private void cargarTodo(){
        Platform.runLater(() -> {
            new ClienteCancionPrivada().obtenerDeUsuario(1).forEach((canPriv) -> {
                this.canciones.add(canPriv.getIdCancion());
            });
            this.cargarCanciones();
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.cargarCombo();
        this.canciones = new ArrayList();
        this.artistas = new ArrayList();
        this.albumes = new ArrayList();
    }
    
    public void inicar(int idUsuario){
        this.idUsuario = idUsuario;
        this.panelCanciones = new VBox();
        this.panelCanciones.setSpacing(5);
        this.cargarTodo();
    }
}
