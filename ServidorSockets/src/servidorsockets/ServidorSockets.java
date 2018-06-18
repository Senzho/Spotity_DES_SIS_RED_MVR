/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorsockets;

import negocio.Peticion;
import FuncionesServidor.DescargarAudio;
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
                Peticion peticion = (Peticion) entrada.readObject();
                if(peticion.getTipoPeticion().equals("reproducir")){
                    
                }else if(peticion.getTipoPeticion().equals("descargar")){
                    System.out.println("peticion");
                    new Thread(new DescargarAudio(cliente, peticion)).start();
                }else if(peticion.getTipoPeticion().equals("subir")){
                	//new Thread(new SubirAudio(cliente, peticion)).start();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorSockets.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServidorSockets.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
