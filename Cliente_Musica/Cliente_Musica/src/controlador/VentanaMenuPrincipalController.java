
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import negocio.Album;
import negocio.Artista;
import negocio.Usuario;
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author Renato
 */
public class VentanaMenuPrincipalController implements Initializable, EscuchadorArtista, EscuchadorAlbum {

    @FXML
    private BorderPane panelPrincipal;
    @FXML
    private Label etiquetaNombreUsuario;
    @FXML
    private Button botonBibliotecas;
    @FXML
    private Button botonListas;
    @FXML
    private Button botonSubirCancion;
    @FXML
    private Button botonEstablecerCalidad;
    @FXML
    private Button botonCerrarSesion;
    @FXML
    private Button botonHistorial;
    private Usuario usuarioActual;

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void desplegarPanelBibliotecas(ActionEvent event) {
        PopOver pop = new PopOver();

        VBox box = new VBox();
        box.setStyle("-fx-background-color: white;");
        Button botonBibliotecaPublica= new Button();
        botonBibliotecaPublica.setText("Biblioteca Publica");
        Button botonBibliotecaPrivada= new Button();
        botonBibliotecaPrivada.setText("Biblioteca Privada");
        botonBibliotecaPrivada.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(VentanaMenuPrincipalController.class.getResource("/vista/PanelBibliotecaPersonal.fxml"));
                    Parent root = (Parent) loader.load();
                    PanelBibliotecaPersonalController controller = loader.getController();
                    controller.inicar(VentanaMenuPrincipalController.this.usuarioActual.getIdUsuario());
                    //Panel panelSubir = loader.getController();****************************
                    pop.hide();
                    panelPrincipal.getChildren().clear();
                    panelPrincipal.setCenter(root);
                } catch (IOException ex) {
                    Logger.getLogger(VentanaMenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        botonBibliotecaPublica.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(VentanaMenuPrincipalController.class.getResource("/vista/PanelBibliotecaPublica.fxml"));
                    Parent root = (Parent) loader.load();
                    PanelBibliotecaPublicaController controller = loader.getController();
                    controller.iniciar(VentanaMenuPrincipalController.this);
                    //Panel panelSubir = loader.getController();****************************
                    pop.hide();
                    panelPrincipal.getChildren().clear();
                    panelPrincipal.setCenter(root);
                    
                } catch (IOException ex) {
                    Logger.getLogger(VentanaMenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        box.getChildren().addAll(botonBibliotecaPublica, botonBibliotecaPrivada);
        pop.setContentNode(box);
        pop.setArrowLocation(PopOver.ArrowLocation.LEFT_CENTER);
        pop.show(botonBibliotecas);
    }

    @FXML
    private void desplegarPanelSubirCancion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(VentanaMenuPrincipalController.class.getResource("/vista/PanelSubirCancion.fxml"));
        Parent root = (Parent) loader.load();
        PanelSubirCancionController panelSubir = loader.getController();
        panelSubir.iniciarVentana(usuarioActual);
        panelPrincipal.getChildren().clear();
        //panelPrincipal.getChildren().add(root);
        panelPrincipal.setCenter(root);
    }

    @FXML
    private void desplegarPanelEstablcerCalidad(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(VentanaMenuPrincipalController.class.getResource("/vista/VentanaCalidadAudio.fxml"));
        Parent root = (Parent) loader.load();
        VentanaCalidadAudioController ventanaCalidad = loader.getController();
        panelPrincipal.getChildren().clear();
        panelPrincipal.setCenter(root);
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        Stage stage = (Stage) botonCerrarSesion.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void desplegarPanelListasDeReproduccion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(VentanaMenuPrincipalController.class.getResource("/vista/PanelListasReproduccion.fxml"));
        Parent root = (Parent) loader.load();
        PanelListasReproduccionController panelListas = loader.getController();
        panelListas.iniciarPantalla(usuarioActual);
        panelPrincipal.getChildren().clear();
        panelPrincipal.setCenter(root);
    }

    @FXML
    private void desplegarPanelHistorial(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(VentanaMenuPrincipalController.class.getResource("/vista/PanelHistorialReproduccion.fxml"));
        Parent root = (Parent) loader.load();
        //PanelSubirCancionController panelSubir = loader.getController();
        panelPrincipal.getChildren().clear();
        //panelPrincipal.getChildren().add(root);
        panelPrincipal.setCenter(root);
    }

    @Override
    public void artistaSeleccionado(Artista artista) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/vista/PanelArtistas.fxml"));
        try {
            Parent root = (Parent) loader.load();
            PanelArtistasController controller = loader.getController();
            controller.iniciar(artista, this, usuarioActual);
            this.panelPrincipal.getChildren().clear();
            this.panelPrincipal.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(VentanaMenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void albumSeleccionado(Album album) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/vista/PanelCanciones.fxml"));
        try {
            Parent root = (Parent) loader.load();
            PanelCancionesController controller = loader.getController();
            controller.iniciar(album, usuarioActual);
            this.panelPrincipal.getChildren().clear();
            this.panelPrincipal.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(VentanaMenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setUsuario(Usuario usuario){
        this.usuarioActual=usuario;
        etiquetaNombreUsuario.setText(usuarioActual.getNombre());
    }
}
