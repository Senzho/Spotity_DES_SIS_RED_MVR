
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import negocio.Usuario;

/**
 * FXML Controller class
 *
 * @author Renato
 */
public class PanelSubirCancionController implements Initializable {

    @FXML
    private Button botonElegirArchivo;
    @FXML
    private Label etiquetaRutaArchivo;
    @FXML
    private Button botonSubirArchivo;
    @FXML
    private RadioButton radioCancion;
    @FXML
    private RadioButton radioAlbum;
    private String tipoArchivo;
    private Usuario usuarioActual;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void iniciarVentana(Usuario usuario){
        this.usuarioActual=usuario;
        tipoArchivo="cancion";
        radioCancion.setSelected(true);
        radioAlbum.setSelected(false);
    }

    @FXML
    private void elegirArchivo(ActionEvent event) {
        
        
    }

    @FXML
    private void subirArchivo(ActionEvent event) {
        
        
    }

    @FXML
    private void setArchivoCancion(ActionEvent event) {
        tipoArchivo="cancion";
        radioCancion.setSelected(true);
        radioAlbum.setSelected(false);
        
    }

    @FXML
    private void setArchivoAlbum(ActionEvent event) {
        tipoArchivo="album";
        radioCancion.setSelected(false);
        radioAlbum.setSelected(true);
    }
    
}
