package Util;

import java.io.File;

public class Ruta {
    public static String getRutaCancion(int idCancion){
        String ruta = "";
        File direc = new File("C:/Spotify/Biblioteca");
        fors:
        //artistas
        for (File artista : direc.listFiles()) {
            //albumes
            for (File album : artista.listFiles()){
                //canciones
                for (File cancion : album.listFiles()){
                    if (cancion.getName().startsWith(idCancion + "")){
                        ruta = cancion.getAbsolutePath() + "/320.mp3";
                        break fors;
                    }
                }
            }
        }
        return ruta;
    }
}
