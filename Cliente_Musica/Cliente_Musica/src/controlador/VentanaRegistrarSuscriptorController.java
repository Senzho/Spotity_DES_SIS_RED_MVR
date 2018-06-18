/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import InterfazGrafica.MessageFactory;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import negocio.Usuario;
import serviciosCliente.ClienteUsuario;

/**
 *
 * @author Desktop
 */
public class VentanaRegistrarSuscriptorController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private PasswordField txtContrasena;
    @FXML
    private PasswordField txtConfirmar;
    private ClienteUsuario cliente;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.cliente = new ClienteUsuario();
    }

    @FXML
    public void registrarse(ActionEvent event) {
        String nombreUsuario = this.txtNombre.getText();
        String contrasena = this.txtContrasena.getText();
        Usuario usuario = new Usuario();
        if (nombreUsuario.equals("") || contrasena.equals("") || txtConfirmar.getText().equals("")) {
             MessageFactory.showMessage("Datos incorrectos", "Usuario y/o contraseña vacíos", "Los campos se encuentran vacíos", Alert.AlertType.ERROR);
        } else {
            if (contrasena.equals(this.txtConfirmar.getText())) {
                usuario.setIdUsuario(0);
                usuario.setContrasena(contrasena);
                usuario.setNombre(nombreUsuario);
                cliente.create_JSON(usuario);
                MessageFactory.showMessage("Usuario registrado", "Usuario registrado exitosamente", "Bienvenido " + nombreUsuario, Alert.AlertType.CONFIRMATION);
                FXMLLoader loger = new FXMLLoader(getClass().getResource("/vista/VentanaInicioSesion.fxml"));
                Parent root = null;
                try {
                    root = (Parent) loger.load();
                } catch (IOException ex) {
                    Logger.getLogger(VentanaRegistrarSuscriptorController.class.getName()).log(Level.SEVERE, null, ex);
                }
                VentanaInicioSesionController controller = loger.getController();
                Stage menu = new Stage();
                menu.setScene(new Scene(root));
                menu.show();
                Stage ventanaAnterior = (Stage) ((Node) event.getSource()).getScene().getWindow();
                ventanaAnterior.close();
            } else {
                MessageFactory.showMessage("Datos incorrectos", "Las contraseñas no coinciden", "Revise su información", Alert.AlertType.ERROR);
            }
        }
    }
}
