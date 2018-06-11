package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import negocio.Album;
import util.Dates;

public class PanelAlbumController implements Initializable{
    @FXML
    private Label nombre;
    @FXML
    private Label fecha;
    @FXML
    private Label compania;
    
    private Album album;
    
    private void cargarAlbum(){
        this.nombre.setText(this.album.getNombre());
        this.fecha.setText(Dates.getSentence(this.album.getFechaLanzamiento()));
        this.compania.setText(this.album.getCompaniaDiscografica());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void inicar(Album album){
        this.album = album;
        this.cargarAlbum();
    }
}
