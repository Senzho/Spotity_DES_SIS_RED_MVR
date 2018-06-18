/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorsockets;

import negocio.Peticion;
import FuncionesServidor.DescargarAudio;
import FuncionesServidor.ReproducirAudio;
import FuncionesServidor.SubirAudio;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Desktop
 */
public class ServidorSockets {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(9000);
            System.out.println("corriendo");
            while(true){
                Socket cliente = servidor.accept();
                ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
                negocio.Peticion peticion = (negocio.Peticion) entrada.readObject();
                if(peticion.getTipoPeticion().equals("reproducir")){
                    new Thread(new FuncionesServidor.ReproducirAudio(cliente, peticion)).start();
                }else if(peticion.getTipoPeticion().equals("descargar")){
                    new Thread(new FuncionesServidor.DescargarAudio(cliente, peticion)).start();
                }else if(peticion.getTipoPeticion().equals("subir")){
                	new Thread(new FuncionesServidor.SubirAudio(cliente, peticion)).start();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorSockets.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServidorSockets.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
