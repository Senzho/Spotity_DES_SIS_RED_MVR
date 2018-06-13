package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;

public class PanelBibliotecaPersonalController implements Initializable{
    @FXML
    private ComboBox combo;
    @FXML
    private ScrollPane scroll;
    
    private void cargarCombo(){
        this.combo.getItems().add("Canciones");
        this.combo.getItems().add("Albumes");
        this.combo.getItems().add("Artistas");
        this.combo.setValue("Canciones");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.cargarCombo();
    }
    
}
