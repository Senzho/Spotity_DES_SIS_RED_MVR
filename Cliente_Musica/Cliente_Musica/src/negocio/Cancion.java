package negocio;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
        String ruta = "C:/SpotifyCli/Biblioteca/" + this.idArtista.getIdArtista() + "/" + this.idAlbum.getIdAlbum();
        ruta = new File(ruta).mkdirs()?"C:/SpotifyCli/Biblioteca/" + this.idArtista.getIdArtista() + "/" + this.idAlbum.getIdAlbum() + "/" + this.idCancion + ".mp3":"";
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
    public List<Cancion> obtenerCanciones(String genero){
        return new ClienteCancion().buscarCanciones(genero);
    }
    public boolean disponibleSinConexion(int idUsuario){
        Cancionprivada canPriv = new ClienteCancionPrivada().obtenerDeCancion(idUsuario, this.idCancion);
        return canPriv.getId()>0;
    }
    public void agregarAColeccion(Usuario usuario){
        Cancionprivada cancionPriv = new Cancionprivada();
        cancionPriv.setId(0);
        cancionPriv.setIdCancion(this);
        cancionPriv.setIdUsuario(usuario);
        cancionPriv.setDisponibleSnConexion(false);
        new ClienteCancionPrivada().create_JSON(cancionPriv);
    }
    public void descargarCancion(){
        Platform.runLater(() -> {
            byte[] bytes = null;
            try {
                Socket socket = new Socket("localhost", 9000);
                Peticion peticion = new Peticion("descargar", this.idCancion);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(peticion);
                BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
                int l = bis.read();
                bytes = new byte[l];
                bis.read(bytes, 0, l);
            } catch (IOException ex) {
                Logger.getLogger(Cancion.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                FileOutputStream fos = new FileOutputStream(this.getRuta());
                fos.write(bytes);
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(Cancion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); 
    }
}
