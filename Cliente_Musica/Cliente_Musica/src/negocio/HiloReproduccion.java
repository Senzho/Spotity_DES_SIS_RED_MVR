package negocio;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import controlador.EscuchadorReproduccion;
import java.util.ResourceBundle;

public class HiloReproduccion implements Runnable {
    private final int idCancion;
    private Player apl;
    private final EscuchadorReproduccion escuchador;
    
    public HiloReproduccion(int idCancion, EscuchadorReproduccion escuchador){
        this.idCancion = idCancion;
        this.escuchador = escuchador;
    }
    
    public void detenerCancion(){
        if (this.apl != null){
            apl.close();
        }
    }
    @Override
    public void run() {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("Recursos/Conectividad");
            Socket connection = new Socket(rb.getString("ip_archivos"), Integer.parseInt(rb.getString("puerto")));
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
                if (this.apl.isComplete()){
                    this.escuchador.cancionTerminada(this.idCancion);
                }
            } catch (JavaLayerException e) {
                e.printStackTrace();
                this.escuchador.cancionNoReproducida(this.idCancion);
            }
        } catch (IOException e) {
            this.escuchador.cancionNoReproducida(this.idCancion);
            Logger.getLogger(Cancion.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
