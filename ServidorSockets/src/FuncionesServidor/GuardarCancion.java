/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FuncionesServidor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Desktop
 */
public class GuardarCancion implements Runnable {

    private final String RUTA_REGISTRO = "C:\\ffmpeg-20180617-bd47cca-win32-static\\bin\\ffmpeg.exe";
    String comando = "";
    private String rutaCancion;
    private String calidad;

    public GuardarCancion(String rutaCancion, String calidad) {
        this.rutaCancion = rutaCancion;
        this.calidad = calidad;
    }

    @Override
    public void run() {
        comando = "cmd /C start /wait " + RUTA_REGISTRO + " -i " + (char) 34 + rutaCancion + (char) 34 + " -ab " + calidad + "k " + calidad + ".mp3";
    }

}
