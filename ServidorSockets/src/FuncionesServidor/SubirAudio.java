package funcionesServidor;

import FuncionesServidor.DescargarAudio;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import negocio.Peticion;

public class SubirAudio implements Runnable {

    private final String ALTO = "320.mp3";
    private final String MEDIO = "256.mp3";
    private final String BAJO = "48.mp3";
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
            entradaDatos.readFully(bytesCancion);
            
            FileOutputStream salida = new FileOutputStream("archivo.mp3");
            salida.write(bytesCancion);
            salida.close();
            File archivo = new File("archivo.mp3");
            Thread h1 = new Thread(new GuardarCancion(archivo.getAbsolutePath(),this.ALTO));
            Thread h2 = new Thread(new GuardarCancion(archivo.getAbsolutePath(),this.MEDIO));
            Thread h3 = new Thread(new GuardarCancion(archivo.getAbsolutePath(),this.BAJO));
            h1.start();
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
