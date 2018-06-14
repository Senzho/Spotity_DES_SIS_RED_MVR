package negocio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import serviciosCliente.ClienteCancion;
import serviciosCliente.ClienteCancionPrivada;

public class Cancion{
    private int idCancion;
    private String nombre;
    private String genero;
    private String duracion;
    private Album idAlbum;
    private Artista idArtista;
    
    private String getRuta(){
        String ruta = "C:/Spotify/Biblioteca/" + this.idArtista + "/" + this.idAlbum;
        ruta = new File(ruta).mkdirs()?"C:/Spotify/Biblioteca/" + this.idArtista + "/" + this.idAlbum + "/" + this.idCancion + ".mp3":"";
        return ruta;
    }

    public Cancion() {

    }

    public int getIdCancion() {
        return idCancion;
    }
    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getDuracion() {
        return duracion;
    }
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
    public Album getIdAlbum() {
        return idAlbum;
    }
    public void setIdAlbum(Album idAlbum) {
        this.idAlbum = idAlbum;
    }
    public Artista getIdArtista() {
        return idArtista;
    }
    public void setIdArtista(Artista idArtista) {
        this.idArtista = idArtista;
    }
    
    public List<Cancion> obtenerCanciones(int idAlbum){
        return new ClienteCancion().obtenerCancionesAlbum(idAlbum);
    }
    public List<Cancion> obtenerCancionesUsuario(int idUsuario){
        List<Cancion> canciones = new ArrayList();
        new ClienteCancionPrivada().obtenerDeUsuario(idUsuario).forEach((canPriv) -> {
            canciones.add(canPriv.getIdCancion());
        });
        return canciones;
    }
    public void descargarCancion(){
        byte[] bytes = new ClienteCancion().descargar(this.idCancion);
        try {
            FileOutputStream fos = new FileOutputStream(this.getRuta());
            fos.write(bytes);
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(Cancion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
