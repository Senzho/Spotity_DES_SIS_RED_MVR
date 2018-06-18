package funcionesServidor;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import negocio.Peticion;

public class SubirAudio implements Runnable {
    private final String ALTO = "320";
    private final String MEDIO = "256";
    private final String BAJO = "48";
    private Peticion peticion;
    private Socket socket;
    private final String ruta = "C:\\Spotify\\Biblioteca\\";
    private byte[] bytesCancion;
    
    public SubirAudio(Socket socket, Peticion peticion) {
        this.peticion = peticion;
        this.socket = socket;
    }

    @Override
    public void run() {
        DataInputStream entradaDatos = null;
        try {
            entradaDatos = new DataInputStream(socket.getInputStream());
            bytesCancion = peticion.getCancion();
            String r = this.ruta + this.peticion.getIdArtista() + "/" + this.peticion.getIdAlbum() + "/" + this.peticion.getIdCancion() + "/";
            new File(r).mkdirs();
            FileOutputStream salida = new FileOutputStream(r + "/" + this.ALTO + ".mp3");
            salida.write(bytesCancion);
            salida.close();
            Thread h2 = new Thread(new FuncionesServidor.GuardarCancion(r,this.MEDIO));
            Thread h3 = new Thread(new FuncionesServidor.GuardarCancion(r,this.BAJO));
            h2.start();
            h3.start();
            
        } catch (IOException ex) {
            Logger.getLogger(SubirAudio.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                entradaDatos.close();
            } catch (IOException ex) {
                Logger.getLogger(SubirAudio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
