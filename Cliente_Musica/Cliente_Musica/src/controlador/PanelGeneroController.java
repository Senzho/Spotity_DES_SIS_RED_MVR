package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import negocio.GeneroArtista;

public class PanelGeneroController implements Initializable{
    @FXML
    private Label nombre;
    
    private GeneroArtista genero;
    
    private void cargarGenero(){
        this.nombre.setText(this.genero.getGenero());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void iniciar(GeneroArtista genero){
        this.genero = genero;
        this.cargarGenero();
    }
}