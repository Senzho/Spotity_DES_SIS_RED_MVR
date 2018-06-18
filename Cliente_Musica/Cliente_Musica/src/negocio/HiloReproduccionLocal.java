package negocio;

import controlador.EscuchadorReproduccion;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class HiloReproduccionLocal implements Runnable{
    private final int idCancion;
    private Player apl;
    private final EscuchadorReproduccion escuchador;
    
    private String obtenerRuta(){
        String ruta = "";
        File general = new File("C:/SpotifyCli/Biblioteca");
        if (general.exists()){
            fors:
            for (File artista : general.listFiles()){
                for (File album : artista.listFiles()){
                    for (File cancion : album.listFiles()){
                        if (cancion.getName().startsWith("" + this.idCancion)){
                            ruta = "C:/SpotifyCli/Biblioteca/" + artista.getName() + "/" + album.getName() + "/" + this.idCancion + ".mp3";
                            break fors;
                        }
                    }
                }
            }
        }   
        return ruta;
    }
    
    public HiloReproduccionLocal(int idCancion, EscuchadorReproduccion escuchador){
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
        String ruta = this.obtenerRuta();
        if (ruta.equals("")){
            this.escuchador.cancionNoReproducida(this.idCancion);
        }else{
            try {
                File file = new File(this.obtenerRuta());
                FileInputStream imputStream = new FileInputStream(file);
                byte[] buffer = new byte[(int) file.length()];
                imputStream.read(buffer);
                ByteArrayInputStream cancion = new ByteArrayInputStream(buffer);
                this.apl = new Player(cancion);
                apl.play();
                if (this.apl.isComplete()){
                    this.escuchador.cancionTerminada(this.idCancion);
                }
            } catch (IOException | JavaLayerException ex) {
                this.escuchador.cancionNoReproducida(this.idCancion);
                Logger.getLogger(HiloReproduccionLocal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
}
