/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import negocio.Cancion;
import negocio.CancionLista;
import negocio.Listareproduccion;
import serviciosCliente.ClienteCancionLista;
import serviciosCliente.ClienteListaReproduccion;

public class PanelListaReproduccionController implements Initializable {

    @FXML
    private Label etiquetaNombreLista;
    private Listareproduccion listaSeleccionada;
    @FXML
    private VBox vboxCanciones;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void iniciarVentana(int idLista){
        listaSeleccionada=new ClienteListaReproduccion().find_JSON(idLista);
        List<CancionLista> listaCanciones = new ArrayList();
        listaCanciones=new ClienteCancionLista().findAll_JSON();
        for(int i=0; i<listaCanciones.size(); i++){
            Listareproduccion listaActual;
            listaActual=listaCanciones.get(i).getIdLista();
            System.out.println("nombre: "+listaActual.getNombre());
            System.out.println("id: "+listaActual.getIdLista());
            
            if(listaActual.getIdLista()!=idLista){
                listaCanciones.remove(i);
            }
        }
        etiquetaNombreLista.setText(listaSeleccionada.getNombre());
        listaSeleccionada.setCancionListaCollection(listaCanciones);
        for(int i=0; i<listaSeleccionada.getCancionListaCollection().size(); i++){
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/vista/PanelCancion.fxml"));
            try {
                AnchorPane pane = loader.load();
                PanelCancionController controller = loader.getController();
                controller.iniciar(listaCanciones.get(i).getIdCancion());
                this.vboxCanciones.getChildren().add(pane);
            } catch (IOException ex) {
                Logger.getLogger(PanelArtistasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    
}