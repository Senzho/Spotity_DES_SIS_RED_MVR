package negocio;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import serviciosCliente.ClienteAlbum;

public class Album{
    private int idAlbum;
    private String nombre;
    private Date fechaLanzamiento;
    private String companiaDiscografica;
    private Artista idArtista;
    private Collection<Cancion> cancionCollection;

    public Album() {

    }

    public int getIdAlbum() {
        return idAlbum;
    }
    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }
    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }
    public String getCompaniaDiscografica() {
        return companiaDiscografica;
    }
    public void setCompaniaDiscografica(String companiaDiscografica) {
        this.companiaDiscografica = companiaDiscografica;
    }
    public Artista getIdArtista() {
        return idArtista;
    }
    public void setIdArtista(Artista idArtista) {
        this.idArtista = idArtista;
    }
    public Collection<Cancion> getCancionCollection() {
        return cancionCollection;
    }
    public void setCancionCollection(Collection<Cancion> cancionCollection) {
        this.cancionCollection = cancionCollection;
    }
    
    public List<Album> adquirirAlbumes(int idArtista){
        return new ClienteAlbum().obtenerPorArtista(idArtista);
    }
}
