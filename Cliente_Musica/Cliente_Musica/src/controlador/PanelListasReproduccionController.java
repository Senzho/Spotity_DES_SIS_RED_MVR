
package controlador;

import java.io.IOException;
import negocio.Listareproduccion;
import serviciosCliente.ClienteListaReproduccion;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import negocio.Usuario;





/**
 * FXML Controller class
 *
 * @author Renato
 */
public class PanelListasReproduccionController implements Initializable {

    @FXML
    private TableView<Listareproduccion> tablaListas;
    @FXML
    private TableColumn columnaNombreLista;
    public ObservableList<Listareproduccion> listas;
    private List<Listareproduccion> listaListas;
    private static int fila;
    private static Listareproduccion listaSeleccionada;
    @FXML
    private AnchorPane panelPrincipal;
    @FXML
    private Button botonCrearLista;
    private Usuario usuario;
    public void inicializarTablaListas() {
        columnaNombreLista.setCellValueFactory(new PropertyValueFactory<Listareproduccion, String>("nombre"));
        listas = FXCollections.observableArrayList();
        tablaListas.setItems(listas);
        tablaListas.getSelectionModel().setCellSelectionEnabled(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void iniciarPantalla(Usuario usuarioActual){
        usuario=usuarioActual;
        int usuarioLista;
        boolean listaVacia;
        int idUsuarioActual=usuarioActual.getIdUsuario();
        inicializarTablaListas();
        this.listaListas=new ClienteListaReproduccion().findAll_JSON();
        if(listaListas.isEmpty()){
            listaVacia=true;
        }else{
            for(int i=0; i<listaListas.size(); i++){
                Listareproduccion nuevaLista = listaListas.get(i);
                usuarioLista=nuevaLista.getIdUsuario().getIdUsuario();
                if(usuarioLista==idUsuarioActual){
                    this.listas.add(nuevaLista);
                }
            }
        }
        
        tablaListas.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                listaSeleccionada=tablaListas.getSelectionModel().getSelectedItem();
                if (event.isPrimaryButtonDown()) {
                    try {
                        FXMLLoader loader = new FXMLLoader(PanelListasReproduccionController.class.getResource("/vista/PanelListaReproduccion.fxml"));
                        Parent root = (Parent) loader.load();
                        PanelListaReproduccionController listaController= loader.getController();
                        listaController.iniciarVentana(listaSeleccionada.getIdLista(), usuario);
                        panelPrincipal.getChildren().clear();
                        panelPrincipal.getChildren().add(root);
                    } catch (IOException ex) {
                        Logger.getLogger(VentanaMenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        
        
    }

    @FXML
    private void crearListaReproduccion(ActionEvent event) throws IOException {
        String nombreNuevaLista= JOptionPane.showInputDialog("Por favor ingrese el nombre de la nueva lista que desea crear: ");
        Listareproduccion nuevaLista = new Listareproduccion();
        nuevaLista.setNombre(nombreNuevaLista);
        nuevaLista.setIdUsuario(usuario);
        String mensaje;
        int usuarioLista;
        boolean listaCreada;
        if((!nombreNuevaLista.equals(""))||(!nombreNuevaLista.equals(" "))){
            new ClienteListaReproduccion().create_JSON(nuevaLista);
            mensaje="La lista fué creada exitosamente!";
            listaCreada=true;
        }else{
            mensaje="Lo sentimos, parace haber ocurrido un error...";
            listaCreada=false;
        }
        
        Alert aviso = new Alert(Alert.AlertType.WARNING);
        aviso.setTitle("información");
        aviso.setHeaderText("Aviso");
        aviso.setContentText(mensaje);
        ButtonType botonOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        aviso.getButtonTypes().setAll(botonOK);
        aviso.showAndWait();
        if(listaCreada){
            listas.clear();
            this.listaListas=new ClienteListaReproduccion().findAll_JSON();
            for(int i=0; i<listaListas.size(); i++){
                Listareproduccion listaAgregar = listaListas.get(i);
                usuarioLista=listaAgregar.getIdUsuario().getIdUsuario();
                if(usuarioLista==usuario.getIdUsuario()){
                    this.listas.add(listaAgregar);
                }
            }
        } 
        
    }
}
