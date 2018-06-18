package controlador;

import InterfazGrafica.MessageFactory;
import java.io.IOException;
import java.net.InetAddress;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import negocio.Cancion;
import negocio.Historialreproduccion;
import negocio.Usuario;
import serviciosCliente.ClienteCancion;

public class PanelReproducirCancionController implements Initializable, EscuchadorReproduccion {
    @FXML
    private VBox listaCanciones;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblArtista;
    @FXML
    private Label lblAlbum;
    @FXML
    private Label lblGenero;
    @FXML
    private Label lblDuracion;
    @FXML
    private ImageView imagenAlbum;
    @FXML
    private ImageView btnDetenerCancion;
    @FXML
    private ImageView imagenAnterior;
    @FXML
    private ImageView imagenSiguiente;
    
    private ClienteCancion clienteCancion;
    private List<Cancion> cola;
    private Stage stage;
    private Cancion cancionActual;
    private EscuchadorCancion escuchador;
    private Usuario usuario;
    private int idActual;

    private void agregarAVista(Cancion cancion, int posicion) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/vista/PanelCancion.fxml"));
        AnchorPane panel;
        try {
            panel = loader.load();
            PanelCancionController controller = loader.getController();
            controller.iniciar(cancion, this.usuario, this.escuchador);
            this.listaCanciones.getChildren().add(posicion, panel);
        } catch (IOException ex) {
            Logger.getLogger(PanelReproducirCancionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void setIdActual(int idCancion){
        boolean encontrada = false;
        for (int i = 0; i < this.cola.size(); i ++) {
            if (this.cola.get(i).getIdCancion() == idCancion){
                this.idActual = i;
                encontrada = true;
                break;
            }
        }
        if (!encontrada){
            this.idActual = -1;
        }
    }
    private Cancion getCancion(int idCancion){
        Cancion cancion = null;
        for (Cancion cancionLista : this.cola) {
            if (cancionLista.getIdCancion() == idCancion){
                cancion = cancionLista;
                break;
            }
        }
        return cancion;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.cola = new ArrayList();
        this.clienteCancion = new ClienteCancion();
        this.imagenAnterior.setImage(new Image(this.getClass().getResourceAsStream("/vista/darkLeftIcon.png")));
        this.imagenSiguiente.setImage(new Image(this.getClass().getResourceAsStream("/vista/darkRightIcon.png")));
        this.idActual = -1;
    }
    public void detener(){
        if(this.cancionActual != null){
            cancionActual.detenerCancion();
        }
    }
    public void iniciar(Stage stage, Usuario usuario, EscuchadorCancion escuchador) {
        this.stage = stage;
        this.usuario = usuario;
        this.escuchador = escuchador;
        this.stage.hide();
    }

    public void setCancion(Cancion cancion, boolean local) {
        this.detener();
        this.cancionActual = cancion;
        InetAddress ping;
        String ip = ResourceBundle.getBundle("Recursos/Conectividad").getString("ip_archivos");
        try {
            ping = InetAddress.getByName(ip);
            if (ping.isReachable(5000)) {
                this.stage.show ();
                if (local){
                    cancion.reproducirLocal(this);
                }else{
                    cancion.reproducirCancion(this);
                }
                this.setIdActual(cancion.getIdCancion());
                cargarCancion();
            } else {
                MessageFactory.showMessage("Aviso", "Conexión fallida", "No es posible acceder al servicio", Alert.AlertType.INFORMATION);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void cargarCancion(){
        this.lblAlbum.setText(this.cancionActual.getIdAlbum().getNombre());
        this.lblArtista.setText(this.cancionActual.getIdArtista().getNombre());
        this.lblGenero.setText(this.cancionActual.getGenero());
        this.lblNombre.setText(this.cancionActual.getNombre());
        this.lblDuracion.setText(this.cancionActual.getDuracion());
        this.imagenAlbum.setImage(new Image("http://" + ResourceBundle.getBundle("Recursos/Conectividad").getString("ip_datos") + ":8080/AccesoSpotify2018/Albumes/"+this.cancionActual.getIdAlbum().getIdAlbum()+".jpg"));
    }
    public void generarEstacion(String genero) {
        this.idActual = 0;
        listaCanciones.getChildren().clear();
        cola = new Cancion().obtenerCanciones(genero);
        cola.forEach((cancion) -> {
            int tam = this.listaCanciones.getChildren().size();
            this.agregarAVista(cancion, tam > 0?tam:0);
        });
        this.stage.show();
    }

    public void agregarSiguiente(Cancion cancion) {
        this.cola.add(0, cancion);
        this.agregarAVista(cancion, 0);
        if (!this.cola.isEmpty()){
            this.idActual ++;
        }
        this.stage.show();
    }

    public void agregarFinal(Cancion cancion) {
        this.cola.add(cancion);
        this.agregarAVista(cancion, this.cola.isEmpty()?0:this.cola.size() - 1);
        this.stage.show();
    }

    public void setCola(List<Cancion> cola) {
        this.cola = cola;
    }

    public void btnGenerarestacion_onClick() {
        if (this.cancionActual != null){
            this.generarEstacion(this.cancionActual.getGenero());
        }  
    }
    public void btnSiguiente_onClick(){
        if (this.idActual > -1){
            this.detener();
            if (this.idActual + 1 > this.cola.size() - 1){
                this.cancionActual = this.cola.get(0);
            }else{
                this.cancionActual = this.cola.get(idActual + 1);
            }
            this.cancionActual.reproducirCancion(this);
            this.setIdActual(this.cancionActual.getIdCancion());
            cargarCancion();
        }else{
            MessageFactory.showMessage("Aviso", "Cola", "No hay canciones en la cola", Alert.AlertType.INFORMATION);
        }
    }
    public void btnAnterior_onClick(){
        if (this.idActual > -1){
            this.detener();
            if (this.idActual - 1 < 0){
                this.cancionActual = this.cola.get(this.cola.size() - 1);
            }else{
                this.cancionActual = this.cola.get(idActual - 1);
            }
            this.cancionActual.reproducirCancion(this);
            this.setIdActual(this.cancionActual.getIdCancion());
            cargarCancion();
        }else{
            MessageFactory.showMessage("Aviso", "Cola", "No hay canciones en la cola", Alert.AlertType.INFORMATION);
        }
    }

    @Override
    public void cancionTerminada(int idCancion) {
        Platform.runLater(() -> {
            Historialreproduccion historial = new Historialreproduccion();
            historial.setId(0);
            historial.setIdUsuario(this.usuario);
            historial.setIdCancion(this.getCancion(idCancion));
            historial.agregar();
            this.btnSiguiente_onClick();
        });
    }
    @Override
    public void cancionNoReproducida(int idCancion) {
        Platform.runLater(() -> {
            MessageFactory.showMessage("Error", "Reproducción", "No se pudo reproducir la canción", Alert.AlertType.ERROR);
        });
    }
}
