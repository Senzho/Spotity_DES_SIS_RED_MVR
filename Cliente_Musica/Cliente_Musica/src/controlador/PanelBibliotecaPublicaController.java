package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import negocio.Artista;
import negocio.GeneroArtista;
import serviciosCliente.ClienteArtista;
import serviciosCliente.ClienteGeneroArtista;

public class PanelBibliotecaPublicaController implements Initializable {
    @FXML
    private ComboBox comboNavegacion;
    @FXML
    private FlowPane panelNavegacion;
    
    private List<GeneroArtista> generos;
    private List<Artista> artistas;
    
    private void cargarCombo(){
        this.comboNavegacion.getItems().clear();
        this.comboNavegacion.getItems().add("Géneros");
        this.comboNavegacion.getItems().add("Artistas");
    }
    private List<GeneroArtista> filtrarGeneros(){
        List<GeneroArtista> lista = new ArrayList();
        this.generos.forEach((genero) -> {
            boolean yaEsta = false;
            for (GeneroArtista genero2 : lista) {
                if (genero2.getGenero().equals(genero.getGenero())){
                    yaEsta = true;
                    break;
                }
            }
            if (!yaEsta){
                lista.add(genero);
            }
        });
        return lista;
    }
    private void cargarGeneros(List<GeneroArtista> generos){
        if (!generos.isEmpty()){
            this.panelNavegacion.getChildren().clear();
            generos.forEach((genero) -> {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/vista/PanelGenero.fxml"));
                try {
                    AnchorPane pane = loader.load();
                    PanelGeneroController controller = loader.getController();
                    controller.iniciar(genero);
                    this.panelNavegacion.getChildren().add(pane);
                } catch (IOException ex) {
                    Logger.getLogger(PanelBibliotecaPublicaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } 
    }
    private void obtenerArtistas(){
        this.artistas = new ClienteArtista().findAll_JSON();
    }
    private void cargarArtistas(){
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.cargarCombo();
        Platform.runLater(()-> {
            this.generos = new ClienteGeneroArtista().findAll_JSON();
            this.cargarGeneros(this.filtrarGeneros());
        });
    }
    
    //Eventos:
    
    public void comboNavegacion_onClick(){
        if (this.comboNavegacion.getSelectionModel().getSelectedIndex() == 0){
            this.cargarGeneros(generos);
        }else{
            
        }
    }
}
