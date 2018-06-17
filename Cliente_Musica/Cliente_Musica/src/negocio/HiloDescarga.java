package negocio;

import controlador.EscuchadorDescarga;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloDescarga implements Runnable {
    private int idCancion;
    private EscuchadorDescarga escuchador;
    private String ruta;

    public HiloDescarga(int idCancion, EscuchadorDescarga escuchador, String ruta) {
        this.idCancion = idCancion;
        this.escuchador = escuchador;
        this.ruta = ruta;
    }

    @Override
    public void run() {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("Recursos/Conectividad");
            Socket socket = new Socket(rb.getString("ip_archivos"), Integer.parseInt(rb.getString("puerto")));
            Peticion peticion = new Peticion("descargar", this.idCancion);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(peticion);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            byte[] bytes = new byte[dis.readInt()];
            dis.readFully(bytes);
            FileOutputStream fos = new FileOutputStream(this.ruta);
            fos.write(bytes);
            fos.close();
            this.escuchador.cancionDescargada(true);
        } catch (IOException ex) {
            this.escuchador.cancionDescargada(false);
            Logger.getLogger(Cancion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
