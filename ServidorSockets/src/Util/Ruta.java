package Util;

import java.io.File;

public class Ruta {
    public static String getRutaCancion(int idCancion, String calidad){
        String ruta = "";
        File direc = new File("C:/Spotify/Biblioteca");
        fors:
        //artistas
        for (File artista : direc.listFiles()) {
            //albumes
            for (File album : artista.listFiles()){
                //canciones
                for (File cancion : album.listFiles()){
                    if (cancion.getName().equals(idCancion + "")){
                        ruta = cancion.getAbsolutePath() + "/" + calidad + ".mp3";
                        break fors;
                    }
                }
            }
        }
        return ruta;
    }
}
