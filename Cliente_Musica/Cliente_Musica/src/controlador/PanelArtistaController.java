package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import negocio.Artista;

public class PanelArtistaController implements Initializable{
    @FXML
    private Label nombre;
    @FXML
    private Label generos;
    @FXML
    private ImageView imagen;
    
    private Artista artista;
    private EscuchadorArtista escuchador;
    
    private void cargarArtista(){
        this.nombre.setText(this.artista.getNombre());
        this.generos.setText("");
        this.artista.obtenerGeneros().forEach((genero) -> {
            this.generos.setText(this.generos.getText() + " | " + genero.getGenero());
        });
        Platform.runLater(() -> {
            this.imagen.setImage(new Image("http://" + ResourceBundle.getBundle("Recursos/Conectividad").getString("ip_datos") + ":8080/AccesoSpotify2018/Artistas/" + this.artista.getIdArtista() + ".jpg"));
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void iniciar(Artista artista, EscuchadorArtista escuchador){
        this.escuchador = escuchador;
        this.artista = artista;
        this.cargarArtista();
    }
    
    //Eventos:
    
    public void onClick(){
        this.escuchador.artistaSeleccionado(this.artista);
    }
}
