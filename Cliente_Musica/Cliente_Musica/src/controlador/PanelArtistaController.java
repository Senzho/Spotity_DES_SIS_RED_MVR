package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import negocio.Artista;

public class PanelArtistaController implements Initializable{
    @FXML
    private Label nombre;
    @FXML
    private Label generos;
    
    private Artista artista;
    private EscuchadorArtista escuchador;
    
    private void cargarArtista(){
        this.nombre.setText(this.artista.getNombre());
        this.generos.setText("");
        this.artista.obtenerGeneros().forEach((genero) -> {
            this.generos.setText(this.generos.getText() + " | " + genero.getGenero());
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
