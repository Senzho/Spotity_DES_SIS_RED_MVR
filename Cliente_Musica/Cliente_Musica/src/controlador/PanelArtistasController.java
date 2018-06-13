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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import negocio.Album;
import negocio.Artista;

public class PanelArtistasController implements Initializable{
    @FXML
    private Label nombre;
    @FXML
    private ScrollPane scroll;
    @FXML
    private ComboBox combo;
    
    private FlowPane panelAlbumes;
    private VBox panelCancionesAlbum;
    private Artista artista;
    private List<Album> albumes;
    private EscuchadorAlbum escuchador;
    
    private void cargarArtista(){
        this.nombre.setText(this.artista.getNombre());
    }
    private void cargarCombo(){
        this.combo.getItems().clear();
        this.combo.getItems().add("Vista de albumes");
        this.combo.getItems().add("Vista de canciones");
        this.combo.setValue("Vista de albumes");
    }
    private void cargarAlbumes(){
        if (!this.albumes.isEmpty()){
            this.panelAlbumes.getChildren().clear();
            this.scroll.setContent(this.panelAlbumes);
            this.albumes.forEach((album) -> {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/vista/PanelAlbum.fxml"));
                try {
                    AnchorPane pane = loader.load();
                    PanelAlbumController controller = loader.getController();
                    controller.inicar(album, this.escuchador);
                    this.panelAlbumes.getChildren().add(pane);
                } catch (IOException ex) {
                    Logger.getLogger(PanelArtistasController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }
    private void cargarCanciones(){
        if (!this.albumes.isEmpty()){
            this.panelCancionesAlbum.getChildren().clear();
            this.scroll.setContent(this.panelCancionesAlbum);
            this.albumes.forEach((album) -> {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/vista/PanelCancionesAlbum.fxml"));
                try {
                    AnchorPane pane = loader.load();
                    PanelCancionesAlbumController controller = loader.getController();
                    controller.iniciar(album);
                    this.panelCancionesAlbum.getChildren().add(pane);
                } catch (IOException ex) {
                    Logger.getLogger(PanelArtistasController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.panelAlbumes = new FlowPane();
        this.panelAlbumes.setVgap(5);
        this.panelAlbumes.setHgap(5);
        this.panelCancionesAlbum = new VBox();
        this.panelCancionesAlbum.setSpacing(5);
        this.albumes = new ArrayList();
    }
    
    public void iniciar(Artista artista, EscuchadorAlbum escuchador){
        this.artista = artista;
        this.escuchador = escuchador;
        this.cargarCombo();
        this.cargarArtista();
        Platform.runLater(() -> {
            this.albumes = new Album().adquirirAlbumes(artista.getIdArtista());
            this.cargarAlbumes();
        });
    }
    
    //Eventos:
    
    public void combo_onAction(){
        Platform.runLater(() -> {
            if (this.combo.getSelectionModel().getSelectedIndex() == 0){
                this.cargarAlbumes();
            }else{
                this.cargarCanciones();
            }
        });
    }
}
