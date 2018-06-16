package FuncionesServidor;

import Util.Ruta;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import negocio.Peticion;

public class DescargarAudio implements Runnable {

    private Peticion peticion;
    private Socket socket;

    public DescargarAudio(Socket socket, Peticion peticion) {//, Peticion peticion) {
        this.peticion = peticion;
        this.socket = socket;
    }

    @Override
    public void run() {
        FileInputStream imputStream = null;
        try {
            File file = new File(Ruta.getRutaCancion(this.peticion.getIdCancion()));
            imputStream = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            imputStream.read(buffer);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeInt(buffer.length);
            dos.write(buffer);
        } catch (IOException ex) {
            Logger.getLogger(DescargarAudio.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                imputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(DescargarAudio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
