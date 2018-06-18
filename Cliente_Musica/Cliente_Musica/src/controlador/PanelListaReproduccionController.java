/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import InterfazGrafica.MessageFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;
import negocio.CancionLista;
import negocio.Listareproduccion;
import negocio.Usuario;
import serviciosCliente.ClienteCancionLista;
import serviciosCliente.ClienteListaReproduccion;

public class PanelListaReproduccionController implements Initializable {

    @FXML
    private Label etiquetaNombreLista;
    private Listareproduccion listaSeleccionada;
    @FXML
    private VBox vboxCanciones;
    @FXML
    private Button botonEditar;
    @FXML
    private Button eliminarLista;
    @FXML
    private AnchorPane panelPrincipal;
    private Usuario usuarioActual;
    private EscuchadorCancion escuchador;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void iniciarVentana(int idLista, Usuario usuarioLogueado, EscuchadorCancion escuchador){
        usuarioActual=usuarioLogueado;
        this.escuchador = escuchador;
        listaSeleccionada=new ClienteListaReproduccion().find_JSON(idLista);
        List<CancionLista> listaCanciones = new ArrayList();
        List<CancionLista> cancionesListaReproduccion = new ArrayList();
        listaCanciones=new ClienteCancionLista().findAll_JSON();
        for(int i=0; i<listaCanciones.size(); i++){
            Listareproduccion listaActual;
            listaActual=listaCanciones.get(i).getIdLista();
            if(listaActual.getIdLista()==idLista){
                cancionesListaReproduccion.add(listaCanciones.get(i));
            }
        }
        etiquetaNombreLista.setText(listaSeleccionada.getNombre());
        listaSeleccionada.setCancionListaCollection(cancionesListaReproduccion);
        for(int i=0; i<listaSeleccionada.getCancionListaCollection().size(); i++){
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/vista/PanelCancion.fxml"));
            try {
                AnchorPane pane = loader.load();
                PanelCancionController controller = loader.getController();
                controller.iniciar(cancionesListaReproduccion.get(i).getIdCancion(), usuarioActual, this.escuchador);
                this.vboxCanciones.getChildren().add(pane);
            } catch (IOException ex) {
                Logger.getLogger(PanelArtistasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void editarNombreLista(ActionEvent event) throws IOException {
        String nuevoNombreLista= JOptionPane.showInputDialog("Por favor ingrese el nuevo nombre para la lista: ");
        Listareproduccion listaEditar=listaSeleccionada;
        listaEditar.setNombre(nuevoNombreLista);
        String mensaje;
        if((!nuevoNombreLista.equals(""))||(!nuevoNombreLista.equals(" "))||(!nuevoNombreLista.equals(null))){
            new ClienteListaReproduccion().edit_JSON(listaEditar, listaEditar.getIdLista());
            mensaje="La lista fué editada exitosamente!";
        }else{
            mensaje="Lo sentimos, parace haber ocurrido un error...";
        }
        Alert aviso = new Alert(Alert.AlertType.WARNING);
        aviso.setTitle("información");
        aviso.setHeaderText("Aviso");
        aviso.setContentText(mensaje);
        ButtonType botonOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        aviso.getButtonTypes().setAll(botonOK);
        aviso.showAndWait();
        FXMLLoader loader = new FXMLLoader(VentanaMenuPrincipalController.class.getResource("/vista/PanelListasReproduccion.fxml"));
        Parent root = (Parent) loader.load();
        PanelListasReproduccionController panelListas = loader.getController();
        panelListas.iniciarPantalla(usuarioActual, this.escuchador);
        panelPrincipal.getChildren().clear();
        panelPrincipal.getChildren().add(root);
    }

    @FXML
    private void eliminarListaReproduccion(ActionEvent event) throws IOException {
        int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro qué desea eliminar esta lista?");
        if(resp==0){
            if(eliminarCanciones()){
                JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
                FXMLLoader loader = new FXMLLoader(VentanaMenuPrincipalController.class.getResource("/vista/PanelListasReproduccion.fxml"));
                Parent root = (Parent) loader.load();
                PanelListasReproduccionController panelListas = loader.getController();
                panelListas.iniciarPantalla(usuarioActual, this.escuchador);
                panelPrincipal.getChildren().clear();
                panelPrincipal.getChildren().add(root);
            }           
        }
    }
    
    private boolean eliminarCanciones(){
        boolean cancionesEliminadas=false;
        List <CancionLista>listaCancionesEliminar=new ArrayList();
        listaCancionesEliminar.addAll(listaSeleccionada.getCancionListaCollection());
        try{
            for(int i=0; i<listaSeleccionada.getCancionListaCollection().size(); i++){
                new ClienteCancionLista().remove(listaCancionesEliminar.get(i).getId());
            }
            new ClienteListaReproduccion().remove(listaSeleccionada.getIdLista());
            cancionesEliminadas=true;
        }catch(Exception ex){
            Logger.getLogger(PanelArtistasController.class.getName()).log(Level.SEVERE, null, ex);
            MessageFactory.showMessage("Error", "Datos", "No se pudieron obtener los datos", Alert.AlertType.INFORMATION);
        }  
        return cancionesEliminadas;
    }
}