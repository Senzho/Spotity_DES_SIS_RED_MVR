/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionesServidor;

import Util.Ruta;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import negocio.Peticion;

/**
 *
 * @author Desktop
 */
public class ReproducirAudio implements Runnable {
    private Peticion peticion;
    private Socket cliente;
    
    public ReproducirAudio(Socket cliente, Peticion peticion){
        this.peticion = peticion;
        this.cliente = cliente;
    }
    @Override
    public void run() {
        final String filename = Ruta.getRutaCancion(peticion.getIdCancion(), peticion.getCalidad());
        File cancion = new File(filename);
        DataOutputStream salida;
        try {
            FileInputStream entradaArchivo = new FileInputStream(cancion);
            byte[] archivo = new byte[(int) cancion.length()];
            entradaArchivo.read(archivo);
            salida = new DataOutputStream(cliente.getOutputStream());
            salida.writeInt(archivo.length);
            salida.write(archivo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
