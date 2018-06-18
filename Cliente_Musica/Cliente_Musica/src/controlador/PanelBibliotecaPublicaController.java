package controlador;

import InterfazGrafica.MessageFactory;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import negocio.Artista;
import negocio.GeneroArtista;
import serviciosCliente.ClienteArtista;
import serviciosCliente.ClienteGeneroArtista;

public class PanelBibliotecaPublicaController implements Initializable, EscuchadorGenero {
    @FXML
    private ComboBox comboNavegacion;
    @FXML
    private FlowPane panelNavegacion;
    
    private List<GeneroArtista> generos;
    private List<Artista> artistas;
    private EscuchadorArtista escuchador;
    
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
            this.generos = generos;
            this.panelNavegacion.getChildren().clear();
            generos.forEach((genero) -> {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/vista/PanelGenero.fxml"));
                try {
                    AnchorPane pane = loader.load();
                    PanelGeneroController controller = loader.getController();
                    controller.iniciar(genero, this);
                    this.panelNavegacion.getChildren().add(pane);
                } catch (IOException ex) {
                    Logger.getLogger(PanelBibliotecaPublicaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } 
    }
    private void obtenerArtistas(){
        this.artistas = new Artista().obtenerArtistas();
    }
    private void cargarArtistas(){
        if (!this.artistas.isEmpty()){
            this.panelNavegacion.getChildren().clear();
            this.artistas.forEach((artista) -> {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/vista/PanelArtista.fxml"));
                try {
                    AnchorPane pane = loader.load();
                    PanelArtistaController controller = loader.getController();
                    controller.iniciar(artista, this.escuchador);
                    this.panelNavegacion.getChildren().add(pane);
                } catch (IOException ex) {
                    Logger.getLogger(PanelBibliotecaPublicaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } 
    }
    private void cargarArtistasPorGenero(String genero){
        try{
            List<Artista> artistasGenero = new Artista().obtenerArtistas(genero);
            if (!artistasGenero.isEmpty()){
                this.panelNavegacion.getChildren().clear();
                artistasGenero.forEach((artista) -> {
                    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/vista/PanelArtista.fxml"));
                    try {
                        AnchorPane pane = loader.load();
                        PanelArtistaController controller = loader.getController();
                        controller.iniciar(artista, this.escuchador);
                        this.panelNavegacion.getChildren().add(pane);
                    } catch (IOException ex) {
                        Logger.getLogger(PanelBibliotecaPublicaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
        }catch(Exception ex){
            Logger.getLogger(PanelBibliotecaPublicaController.class.getName()).log(Level.SEVERE, null, ex);
            MessageFactory.showMessage("Error", "Datos", "No se pudieron obtener los datos", Alert.AlertType.INFORMATION);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void iniciar(EscuchadorArtista escuchador){
        this.escuchador = escuchador;
        this.cargarCombo();
        this.artistas = new ArrayList();
        Platform.runLater(()-> {
            this.generos = new ClienteGeneroArtista().findAll_JSON();
            this.cargarGeneros(this.filtrarGeneros());
        });
    }
    
    //Eventos:
    
    public void comboNavegacion_onClick(){
        if (this.comboNavegacion.getSelectionModel().getSelectedIndex() == 0){
            this.cargarGeneros(this.generos);
        }else{
            Platform.runLater(() -> {
                if (this.artistas.isEmpty()){
                    this.obtenerArtistas();
                }
                this.cargarArtistas();
            });
        }
    }

    @Override
    public void generoSeleccionado(GeneroArtista genero) {
        this.cargarArtistasPorGenero(genero.getGenero());
    }
}
