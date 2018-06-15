/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionesServidor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import servidorsockets.Peticion;

/**
 *
 * @author Desktop
 */
public class DescargarAudio implements Runnable {

    private Socket cliente;
    private Peticion peticion;
    private BufferedInputStream entrada;
    private BufferedOutputStream salida;

    public DescargarAudio(Socket cliente, Peticion peticion) {
        this.cliente = cliente;
        this.peticion = peticion;//aqui debe buscarse el archivo 
    }

    @Override
    public void run() {
        int in;
        byte[] byteArray;
        final String nombre = "C:\\Users\\Desktop\\Music\\Descargas de musica\\Hell.mp3";
        try {
            System.out.println("segunda entradas");
            File archivo = new File(nombre);
            FileInputStream imputStream = new FileInputStream(archivo);
            byte[] buffer = new byte[(int) archivo.length()];
            salida.write(buffer, 0, buffer.length);
            /*aqui se convirtio a arreglo de bytes.. .y solo importa el arreglo*/
            imputStream.read(buffer);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
