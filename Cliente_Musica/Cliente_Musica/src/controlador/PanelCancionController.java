package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import negocio.Cancion;

public class PanelCancionController implements Initializable{
    @FXML
    private Label nombre;
    @FXML
    private Label duracion;
    @FXML
    private Label disponible;
    
    private Cancion cancion;
    
    private void cargarCancion(){
        this.nombre.setText(this.cancion.getNombre());
        this.duracion.setText(this.cancion.getDuracion());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void iniciar(Cancion cancion){
        this.cancion = cancion;
        this.cargarCancion();
    }
}
