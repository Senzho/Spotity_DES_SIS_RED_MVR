package negocio;

import controlador.EscuchadorDescarga;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import serviciosCliente.ClienteCancion;
import serviciosCliente.ClienteCancionPrivada;
import controlador.EscuchadorReproduccion;
import javax.ws.rs.ClientErrorException;

public class Cancion {

    private int idCancion;
    private String nombre;
    private String genero;
    private String duracion;
    private Album idAlbum;
    private Artista idArtista;
    private HiloReproduccionRemota hiloReproduccion;
    private HiloReproduccionLocal hiloReproduccionLocal;

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

    public List<Cancion> obtenerCanciones(int idAlbum) throws ClientErrorException{
        return new ClienteCancion().obtenerCancionesAlbum(idAlbum);
    }

    public List<Cancion> obtenerCancionesUsuario(int idUsuario) throws ClientErrorException{
        List<Cancion> canciones = new ArrayList();
        new ClienteCancionPrivada().obtenerDeUsuario(idUsuario).forEach((canPriv) -> {
            canciones.add(canPriv.getIdCancion());
        });
        return canciones;
    }

    public List<Cancion> obtenerCanciones(String genero) throws ClientErrorException{
        return new ClienteCancion().buscarCanciones(genero);
    }

    public boolean disponibleSinConexion(int idUsuario) throws ClientErrorException{
        Cancionprivada canPriv = new ClienteCancionPrivada().obtenerDeCancion(idUsuario, this.idCancion);
        return canPriv.getId() > 0;
    }

    public void agregarAColeccion(Usuario usuario) throws ClientErrorException{
        Cancionprivada cancionPriv = new Cancionprivada();
        cancionPriv.setId(0);
        cancionPriv.setIdCancion(this);
        cancionPriv.setIdUsuario(usuario);
        cancionPriv.setDisponibleSnConexion(false);
        new ClienteCancionPrivada().create_JSON(cancionPriv);
    }

    public void establecerComoDisponible(int idUsuario) throws ClientErrorException{
        Cancionprivada cancionPriv = new ClienteCancionPrivada().establecerComoDisponible(idUsuario, this.idCancion);
    }

    public void descargarCancion(EscuchadorDescarga escuchador) {
        new Thread(new HiloDescarga(this.idCancion, escuchador, this.getRuta())).start();
    }
    public void detenerCancion(){
        if(hiloReproduccion!= null){
            hiloReproduccion.detenerCancion();
        }else if (this.hiloReproduccionLocal != null){
            this.hiloReproduccionLocal.detenerCancion();
        }
    }

    public void reproducirCancion(EscuchadorReproduccion escuchador) {
        this.hiloReproduccion = new HiloReproduccionRemota(this.idCancion, escuchador);
        new Thread(hiloReproduccion).start();
    }
    public void reproducirLocal(EscuchadorReproduccion escuchador){
        this.hiloReproduccionLocal = new HiloReproduccionLocal(this.idCancion, escuchador);
        new Thread(hiloReproduccionLocal).start();
    }
}
