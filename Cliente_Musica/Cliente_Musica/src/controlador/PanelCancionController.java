package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import negocio.Cancion;

public class PanelCancionController implements Initializable{
    @FXML
    private Label nombre;
    @FXML
    private Label duracion;
    @FXML
    private Label disponible;
    @FXML
    private ImageView opciones;
    @FXML
    private ImageView play;
    
    private Cancion cancion;
    
    private void cargarCancion(){
        this.nombre.setText(this.cancion.getNombre());
        this.duracion.setText(this.cancion.getDuracion());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.opciones.setImage(new Image(this.getClass().getResourceAsStream("/vista/Opciones.png")));
        this.play.setImage(new Image(this.getClass().getResourceAsStream("/vista/play.png")));
    }
    
    public void iniciar(Cancion cancion){
        this.cancion = cancion;
        this.cargarCancion();
    }
    
    //Eventos:
    
    public void opciones_onClick(){
        
    }
}
