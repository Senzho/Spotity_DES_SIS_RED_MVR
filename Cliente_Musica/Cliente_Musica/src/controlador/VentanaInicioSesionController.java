package controlador;

import InterfazGrafica.MessageFactory;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import negocio.Usuario;
import serviciosCliente.ClienteUsuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class VentanaInicioSesionController implements Initializable {

    @FXML
    private Button botonIngresar;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContrasena;
    private ClienteUsuario clienteUsuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteUsuario = new ClienteUsuario();
    }

    @FXML
    private void desplegarMenuPrincipal(ActionEvent event) {
        String usuario = txtUsuario.getText();
        String contrasena = txtContrasena.getText();
        if (usuario.equals("") || contrasena.equals("")) {
            MessageFactory.showMessage("Datos incorrectos", "Usuario y/o contraseña vacíos", "Usuario o contraseña invalidos", Alert.AlertType.ERROR);
        } else {
            try{
                Usuario usuarioLogin = clienteUsuario.iniciarsesion(usuario, contrasena);
                if (usuarioLogin.getIdUsuario() == 0) {
                    MessageFactory.showMessage("Usuario incorrecto", "Datos incorrectos", "Usuario o contraseña invalidos", Alert.AlertType.ERROR);
                } else {
                    FXMLLoader loger = new FXMLLoader(getClass().getResource("/vista/MenuPrincipal.fxml"));
                    Parent root = null;
                    try {
                        root = (Parent) loger.load();
                    } catch (IOException ex) {
                        Logger.getLogger(VentanaInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    VentanaMenuPrincipalController menuController = loger.getController();
                    menuController.setUsuario(usuarioLogin);
                    Stage menu = new Stage();
                    menu.setScene(new Scene(root));
                    menu.show();
                    Stage ventanaAnterior = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    ventanaAnterior.close();
                }
            }catch(Exception ex){
                Logger.getLogger(PanelArtistasController.class.getName()).log(Level.SEVERE, null, ex);
                MessageFactory.showMessage("Error", "Datos", "No se pudieron obtener los datos", Alert.AlertType.INFORMATION);
            }  
        }
    }

    @FXML
    private void registrarUsuario(ActionEvent event) {
        FXMLLoader loger = new FXMLLoader(getClass().getResource("/vista/VentanaRegistrarSuscriptor.fxml"));
        Parent root = null;
        try {
            root = (Parent) loger.load();
        } catch (IOException ex) {
            Logger.getLogger(VentanaInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        VentanaRegistrarSuscriptorController controller = loger.getController();
        Stage menu = new Stage();
        menu.setScene(new Scene(root));
        menu.show();
        Stage ventanaAnterior = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ventanaAnterior.close();
    }
}
