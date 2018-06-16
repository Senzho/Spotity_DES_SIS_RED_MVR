/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author Desktop
 */
public class HiloReproduccion implements Runnable {

    private int idCancion;
    private Player apl;
    public HiloReproduccion(int idCancion){
        this.idCancion = idCancion;
    }
    public void detenerCancion(){
        apl.close();
    }
    @Override
    public void run() {
        byte[] receivedData;
            int in;
            try {
                Socket connection = new Socket("192.168.43.126", 9000);
                receivedData = new byte[1024];
                BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
                DataInputStream dis = new DataInputStream(connection.getInputStream());
                ObjectOutputStream salidaObjeto = new ObjectOutputStream(connection.getOutputStream());
                Peticion peticion = new Peticion("reproducir", this.idCancion);
                salidaObjeto.writeObject(peticion);
                int tamano = dis.readInt();
                System.out.println(tamano);
                byte[] cancion2 = new byte[tamano];
                if (tamano > 0) {
                    dis.readFully(cancion2);
                }
                ByteArrayInputStream cancion = new ByteArrayInputStream(cancion2);
                try {
                    apl = new Player(cancion);
                    apl.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
                //bos.close();
                //dis.close();

            } catch (SocketException e) {
                Logger.getLogger(Cancion.class.getName()).log(Level.SEVERE, null, e);
            } catch (IOException ex) {
                Logger.getLogger(Cancion.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
