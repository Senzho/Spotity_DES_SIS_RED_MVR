package controlador;

import InterfazGrafica.MessageFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Renato
 */
public class VentanaCalidadAudioController implements Initializable {

    @FXML
    private RadioButton btnBajo;
    @FXML
    private RadioButton btnMedio;
    @FXML
    private RadioButton btnAlto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ToggleGroup group = new ToggleGroup();
        btnMedio.setToggleGroup(group);
        btnBajo.setToggleGroup(group);
        btnAlto.setToggleGroup(group);
    }

    public void guardarCalidad() {
        FileWriter fw = null;
        try {
            String calidad = "";
            if (btnBajo.isSelected()) {
                calidad = "bajo";
            } else if (this.btnMedio.isSelected()) {
                calidad = "medio";
            } else if (this.btnAlto.isSelected()) {
                calidad = "alto";
            }   String ruta = "C:\\calidadAudio";
            File directorio = new File(ruta);
            if (!directorio.exists()) {
                directorio.mkdir();
            }   File archivo = new File(directorio + "\\calidad.txt");
            fw = new FileWriter(archivo);
            PrintWriter pw = new PrintWriter(fw);
            System.out.println("Guardando calidad");
            MessageFactory.showMessage("Calidad seleccionada", "Calidad cambiada","La calidad seleccionada es "+calidad, Alert.AlertType.INFORMATION);
            pw.println(calidad);
        } catch (IOException ex) {
            Logger.getLogger(VentanaCalidadAudioController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(VentanaCalidadAudioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
