package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import negocio.GeneroArtista;

public class PanelGeneroController implements Initializable{
    @FXML
    private Label nombre;
    
    private GeneroArtista genero;
    private EscuchadorGenero escuchador;
    @FXML
    private ImageView imagenGenero;
    
    private void cargarGenero(){
        this.nombre.setText(this.genero.getGenero());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void iniciar(GeneroArtista genero, EscuchadorGenero escuchador){
        this.genero = genero;
        this.escuchador = escuchador;
        this.cargarGenero();
        Image imagen = new Image(this.getClass().getResourceAsStream("/vista/ImagenGenero"));
        this.imagenGenero.setImage(imagen);
    }
    
    //Eventos:
    
    @FXML
    public void onClick(){
        this.escuchador.generoSeleccionado(this.genero);
    }
}
