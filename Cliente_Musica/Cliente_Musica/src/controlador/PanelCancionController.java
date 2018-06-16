package controlador;

import InterfazGrafica.MessageFactory;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import negocio.Cancion;
import negocio.CancionLista;
import negocio.Listareproduccion;
import negocio.Usuario;
import org.controlsfx.control.PopOver;
import serviciosCliente.ClienteCancionLista;
import serviciosCliente.ClienteListaReproduccion;

public class PanelCancionController implements Initializable, EscuchadorDescarga{
    @FXML
    private Label nombre;
    @FXML
    private Label duracion;
    @FXML
    private Label disponible;
    @FXML
    private ImageView opciones;
    @FXML
    private ImageView play;
    
    private Cancion cancion;
    @FXML
    private Button botonOpciones;
    private List<Listareproduccion> listaListas;
    private ObservableList <String> listaListasCombo= FXCollections.observableArrayList();
    private Listareproduccion listaSeleccionada;
    private String nombreListaSeleccionada;
    private Usuario usuarioActual;
    private EscuchadorCancion escuchador;
    
    private void cargarCancion(){
        this.nombre.setText(this.cancion.getNombre());
        this.duracion.setText(this.cancion.getDuracion());
        Platform.runLater(() -> {
            this.disponible.setText(this.cancion.disponibleSinConexion(this.usuarioActual.getIdUsuario())?"Disponible sin conexión":"Solo en línea");
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.opciones.setImage(new Image(this.getClass().getResourceAsStream("/vista/Opciones.png")));
        this.play.setImage(new Image(this.getClass().getResourceAsStream("/vista/play.png")));
        
    }
    
    public void iniciar(Cancion cancion, Usuario usuario, EscuchadorCancion escuchador){
        this.usuarioActual=usuario;
        this.escuchador = escuchador;
        this.cancion = cancion;
        this.cargarCancion();
    }
    
    //Eventos:
    
    public void opciones_onClick(){
        
    }

    @FXML
    private void desplegarOpciones(ActionEvent event) {
        PopOver pop = new PopOver();
        VBox box = new VBox();
        box.setStyle("-fx-background-color: white;");
        Button botonAgregar= new Button();
        botonAgregar.setMaxWidth(189.0);
        botonAgregar.setText("Agregar a Lista de Reproducción");
        Button botonDescargar= new Button();
        botonDescargar.setMaxWidth(189.0);
        botonDescargar.setText("Decargar");
        Button botonGenerarRadio= new Button();
        botonGenerarRadio.setMaxWidth(189.0);
        botonGenerarRadio.setText("Generar Estación de Radio");
        Button botonAgregarSiguiente= new Button();
        botonAgregarSiguiente.setMaxWidth(189.0);
        botonAgregarSiguiente.setText("Agregar siguiente");
        Button botonAgregarFinal= new Button();
        botonAgregarFinal.setMaxWidth(189.0);
        botonAgregarFinal.setText("Agregar al final");
        Button botonAgregarBiblioteca= new Button();
        botonAgregarBiblioteca.setMaxWidth(189.0);
        botonAgregarBiblioteca.setText("Agregar a mi colección");
        box.getChildren().addAll(botonAgregar, botonGenerarRadio, botonDescargar, botonAgregarSiguiente, botonAgregarFinal, botonAgregarBiblioteca);
        pop.setContentNode(box);
        pop.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);
        pop.show(opciones);
        pop.setAutoHide(true);
        botonAgregar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pop.hide();
                manejarBotonAgregarLista();
            }
        });
        botonGenerarRadio.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                pop.hide();
                PanelCancionController.this.escuchador.cancionGenerarEstacion(cancion);
            }
        });
        botonAgregarSiguiente.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                pop.hide();
                PanelCancionController.this.escuchador.cancionSiguienteCola(cancion);
            }
        });
        botonAgregarFinal.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                pop.hide();
                PanelCancionController.this.escuchador.cancionFinalCola(cancion);
            }
        });
        botonDescargar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                pop.hide();
                cancion.descargarCancion(PanelCancionController.this);
            }
        });
        botonAgregarBiblioteca.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                pop.hide();
                cancion.agregarAColeccion(usuarioActual);
            }
        });
    }

    public void establecerListas(){
        String[] listas= new String[20];
        boolean listaVacia=false;
        this.listaListas=new ClienteListaReproduccion().findAll_JSON();
        if(listaListas.isEmpty()){
            listaVacia=true;
        }else{
            listaListasCombo.add("Crear nueva lista");
            for(int i=0; i<listaListas.size(); i++){
                listaListasCombo.add(listaListas.get(i).getNombre());
            }
        }
    }
    
    public String[] adquirirListasReproduccion(){
        String[] listas= new String[20];
        boolean listaVacia=false;
        this.listaListas=new ClienteListaReproduccion().findAll_JSON();
        
        if(listaListas.isEmpty()){
            listaVacia=true;
        }else{
            for(int i=0; i<listaListas.size(); i++){
                listaListasCombo.add(listaListas.get(i).getNombre());
            }
            listaListasCombo.add("Crear nueva lista");
        }
        return listas;
    }
    
    public boolean mostrarCombo(){
        boolean nombreValido=true;
        ComboBox combo = new ComboBox();
        establecerListas();
        combo.setItems(listaListasCombo);
        //combo.setValue(listaListasCombo.get(0));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setGraphic(combo);
        ButtonType botonOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType botonCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.setTitle("Listas de Reproducción");
        alert.setHeaderText("Seleccione una lista de reproducción");
        alert.getButtonTypes().setAll(botonOK, botonCancelar);
        
        alert.showAndWait();
        if(combo.getValue()==null){
            nombreValido=false;
        }else{
            nombreListaSeleccionada=combo.getValue().toString();
            nombreValido=true;
        }
        
        return nombreValido;
        
    }
    
    public void manejarBotonAgregarLista(){
        boolean camposValidos=false;
        camposValidos= mostrarCombo(); 
        if(!camposValidos){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Error");
            alert.setHeaderText("Agregación Fallida");
            alert.setContentText("Por favor ingerse un nombre valido");
            alert.showAndWait();
        }else{
            if(nombreListaSeleccionada.equals("Crear nueva lista")){
            crearListaReproduccion();
            }else{
                for(int i=0; i<listaListas.size(); i++){
                    if(listaListas.get(i).getNombre().equals(nombreListaSeleccionada)){
                        listaSeleccionada=listaListas.get(i);
                        break;
                    }
                }
                CancionLista cancionAgregar= new CancionLista();
                cancionAgregar.setIdCancion(this.cancion);
                cancionAgregar.setIdLista(listaSeleccionada);
                if(!cancionRepetida()){
                    new ClienteCancionLista().create_JSON(cancionAgregar);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.setTitle("Exito");
                    alert.setHeaderText("Agregación exitosa");
                    alert.setContentText("La canción se agregó de manera correcata a la lista de reproducción seleccionada");
                    alert.showAndWait();
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.setTitle("Error");
                    alert.setHeaderText("Agregación Fallida");
                    alert.setContentText("La canción ya se encuentra en la lista de reproducción");
                    alert.showAndWait();
                }
            }
        }
        
         
        
        
    }
    
    
    private void crearListaReproduccion() {
        String nombreNuevaLista= JOptionPane.showInputDialog("Por favor ingrese el nombre de la nueva lista que desea crear: ");
        Listareproduccion nuevaLista = new Listareproduccion();
        nuevaLista.setNombre(nombreNuevaLista);
        nuevaLista.setIdUsuario(usuarioActual);
        String mensaje;
        int usuarioLista;
        boolean listaCreada;
        if((!nombreNuevaLista.equals(""))||(!nombreNuevaLista.equals(" "))){
            new ClienteListaReproduccion().create_JSON(nuevaLista);
            agregarCancion(nombreNuevaLista);
            mensaje="La lista fué creada exitosamente y se añadio la cancion!";
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
    }
    public boolean cancionRepetida(){
        boolean repetida=false;
        List<CancionLista> listaCanciones=new ClienteCancionLista().findAll_JSON();
        for(int i=0; i<listaCanciones.size(); i++){
            if(cancion.getIdCancion()==listaCanciones.get(i).getIdCancion().getIdCancion()){
                if(listaSeleccionada.getIdLista()==listaCanciones.get(i).getIdLista().getIdLista()){
                    repetida=true;
                    break;
                }
            }
        }
        return repetida;
    }
    
    public void agregarCancion(String nombreLista){
        CancionLista cancionAgregar= new CancionLista();
        this.listaListas=new ClienteListaReproduccion().findAll_JSON();
        for(int i=0; i<listaListas.size(); i++){
            if(listaListas.get(i).getNombre().equals(nombreLista)){
                listaSeleccionada=listaListas.get(i);
                break;
            }
        }
        cancionAgregar.setIdCancion(this.cancion);
        cancionAgregar.setIdLista(listaSeleccionada);
        new ClienteCancionLista().create_JSON(cancionAgregar);

    }

    //Eventos:
    
    public void play_onClick(){
        this.escuchador.cancionAReproduccion(this.cancion);
    }

    @Override
    public void cancionDescargada(boolean descargada) {
        if (descargada){
            Platform.runLater(() -> {
                this.cancion.establecerComoDisponible(this.usuarioActual.getIdUsuario());
            });
            this.disponible.setText("Disponible sin conexión");
            MessageFactory.showMessage("Éxito", "Descarga", this.cancion.getNombre() + " fué descargada exitosamente", Alert.AlertType.INFORMATION);
        }else{
            MessageFactory.showMessage("Error", "Descarga", this.cancion.getNombre() + " no fué descargada", Alert.AlertType.ERROR);
        }
    }
}
