package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import negocio.Album;
import util.Dates;

public class PanelAlbumController implements Initializable{
    @FXML
    private Label nombre;
    @FXML
    private Label fecha;
    @FXML
    private Label compania;
    @FXML
    private ImageView imagen;
    
    private Album album;
    private EscuchadorAlbum escuchador;
    
    private void cargarAlbum(){
        this.nombre.setText(this.album.getNombre());
        this.fecha.setText(Dates.getSentence(this.album.getFechaLanzamiento()));
        this.compania.setText(this.album.getCompaniaDiscografica());
        Platform.runLater(() -> {
            this.imagen.setImage(new Image("http://localhost:8080/AccesoSpotify2018/Albumes/" + this.album.getIdAlbum() + ".jpg"));
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void inicar(Album album, EscuchadorAlbum escuchador){
        this.album = album;
        this.escuchador = escuchador;
        this.cargarAlbum();
    }
    
    //Eventos:
    
    public void onClick(){
        this.escuchador.albumSeleccionado(this.album);
    }
}
