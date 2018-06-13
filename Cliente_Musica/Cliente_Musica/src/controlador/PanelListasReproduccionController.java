
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;





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
    public void inicializarTablaListas() {
        columnaNombreLista.setCellValueFactory(new PropertyValueFactory<Listareproduccion, String>("nombre"));
        listas = FXCollections.observableArrayList();
        tablaListas.setItems(listas);
        tablaListas.getSelectionModel().setCellSelectionEnabled(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void iniciarPantalla(){
        inicializarTablaListas();
        this.listaListas=new ClienteListaReproduccion().findAll_JSON();
        if(listaListas.isEmpty()){
            System.out.println("Lista Vacia");
        }else{
            System.out.println("Lista con elementos");
            for(int i=0; i<listaListas.size(); i++){
                Listareproduccion nuevaLista = listaListas.get(i);
                this.listas.add(nuevaLista);
                
                //System.out.println(i+" elemento "+listaListas.get(i).getNombre());
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
                        listaController.iniciarVentana(listaSeleccionada.getIdLista());
                        //Panel panelSubir = loader.getController();****************************
                        panelPrincipal.getChildren().clear();
                        panelPrincipal.getChildren().add(root);
                    } catch (IOException ex) {
                        Logger.getLogger(VentanaMenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        
        
    }
    
}
