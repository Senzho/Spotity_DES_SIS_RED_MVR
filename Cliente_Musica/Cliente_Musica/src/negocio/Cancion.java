package negocio;

import java.util.Collection;

public class Cancion{
    private int idCancion;
    private String nombre;
    private String genero;
    private String duracion;
    private Album idAlbum;
    private Artista idArtista;

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
}