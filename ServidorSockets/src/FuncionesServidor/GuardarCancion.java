package FuncionesServidor;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        comando = "cmd /C start /wait " + RUTA_REGISTRO + " -i " + (char) 34 + rutaCancion + "320.mp3" + (char) 34 + " -ab " + calidad + "k " + rutaCancion + calidad + ".mp3";
        System.out.println(comando);
        try {
            Process pr = Runtime.getRuntime().exec(comando);
        } catch (IOException ex) {
            Logger.getLogger(GuardarCancion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
