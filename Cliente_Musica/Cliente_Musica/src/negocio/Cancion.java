package negocio;

import com.sun.javaws.Main;
import controlador.EscuchadorDescarga;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import serviciosCliente.ClienteCancion;
import serviciosCliente.ClienteCancionPrivada;

public class Cancion {

    private int idCancion;
    private String nombre;
    private String genero;
    private String duracion;
    private Album idAlbum;
    private Artista idArtista;
    private HiloReproduccion hiloReproduccion;

    private String getRuta() {
        String ruta = "C:/SpotifyCli/Biblioteca/" + this.idArtista.getIdArtista() + "/" + this.idAlbum.getIdAlbum();
        ruta = new File(ruta).mkdirs() ? "C:/SpotifyCli/Biblioteca/" + this.idArtista.getIdArtista() + "/" + this.idAlbum.getIdAlbum() + "/" + this.idCancion + ".mp3" : "";
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

    public List<Cancion> obtenerCanciones(int idAlbum) {
        return new ClienteCancion().obtenerCancionesAlbum(idAlbum);
    }

    public List<Cancion> obtenerCancionesUsuario(int idUsuario) {
        List<Cancion> canciones = new ArrayList();
        new ClienteCancionPrivada().obtenerDeUsuario(idUsuario).forEach((canPriv) -> {
            canciones.add(canPriv.getIdCancion());
        });
        return canciones;
    }

    public List<Cancion> obtenerCanciones(String genero) {
        return new ClienteCancion().buscarCanciones(genero);
    }

    public boolean disponibleSinConexion(int idUsuario) {
        Cancionprivada canPriv = new ClienteCancionPrivada().obtenerDeCancion(idUsuario, this.idCancion);
        return canPriv.getId() > 0;
    }

    public void agregarAColeccion(Usuario usuario) {
        Cancionprivada cancionPriv = new Cancionprivada();
        cancionPriv.setId(0);
        cancionPriv.setIdCancion(this);
        cancionPriv.setIdUsuario(usuario);
        cancionPriv.setDisponibleSnConexion(false);
        new ClienteCancionPrivada().create_JSON(cancionPriv);
    }

    public void establecerComoDisponible(int idUsuario) {
        Cancionprivada cancionPriv = new ClienteCancionPrivada().establecerComoDisponible(idUsuario, this.idCancion);
    }

    public void descargarCancion(EscuchadorDescarga escuchador) {
        Platform.runLater(() -> {
            try {
                Socket socket = new Socket("192.168.43.126", 9000);
                Peticion peticion = new Peticion("descargar", Cancion.this.idCancion);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(peticion);
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                byte[] bytes = new byte[dis.readInt()];
                dis.readFully(bytes);
                FileOutputStream fos = new FileOutputStream(Cancion.this.getRuta());
                fos.write(bytes);
                fos.close();
                escuchador.cancionDescargada(true);
            } catch (IOException ex) {
                escuchador.cancionDescargada(false);
                Logger.getLogger(Cancion.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    public void detenerCancion(){
        if(hiloReproduccion!= null){
            hiloReproduccion.detenerCancion();
        }
    }

    public void reproducirCancion() {
        this.hiloReproduccion = new HiloReproduccion(this.idCancion);
        new Thread(hiloReproduccion).start();
    }
}
