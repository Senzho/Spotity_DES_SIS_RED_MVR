
package controlador;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import negocio.Listareproduccion;
import serviciosCliente.ClienteListaReproduccion;

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
            //listas=(ObservableList<Listareproduccion>) listaListas;
            //listas.addAll(listaListas);
            for(int i=0; i<listaListas.size(); i++){
                Listareproduccion nuevaLista = listaListas.get(i);
                this.listas.add(nuevaLista);
                
                System.out.println(i+" elemento "+listaListas.get(i).getNombre());
            }
        }
        /*for(int i=0; i<listaListas.size(); i++){
            listas.add(listaListas.get(i));
        }*/
    }
    
}
