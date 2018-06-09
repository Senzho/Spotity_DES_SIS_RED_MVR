package negocio;

import java.util.Collection;

public class Artista{
    private int idArtista;
    private String nombre;
    private Collection<Album> albumCollection;
    private Collection<GeneroArtista> generoArtistaCollection;
    private Collection<Cancion> cancionCollection;

    public Artista() {

    }

    public int getIdArtista() {
        return idArtista;
    }
    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Collection<Album> getAlbumCollection() {
        return albumCollection;
    }
    public void setAlbumCollection(Collection<Album> albumCollection) {
        this.albumCollection = albumCollection;
    }
    public Collection<GeneroArtista> getGeneroArtistaCollection() {
        return generoArtistaCollection;
    }
    public void setGeneroArtistaCollection(Collection<GeneroArtista> generoArtistaCollection) {
        this.generoArtistaCollection = generoArtistaCollection;
    }
    public Collection<Cancion> getCancionCollection() {
        return cancionCollection;
    }
    public void setCancionCollection(Collection<Cancion> cancionCollection) {
        this.cancionCollection = cancionCollection;
    }
}
