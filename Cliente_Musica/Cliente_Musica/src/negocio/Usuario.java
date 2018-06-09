package negocio;

import java.util.Collection;

public class Usuario implements Serializable {
    private int idUsuario;
    private String nombre;
    private String contrasena;
    private Collection<Historialreproduccion> historialreproduccionCollection;
    private Collection<Cancionprivada> cancionprivadaCollection;
    private Collection<Listareproduccion> listareproduccionCollection;

    public Usuario() {

    }

    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public Collection<Historialreproduccion> getHistorialreproduccionCollection() {
        return historialreproduccionCollection;
    }

    public void setHistorialreproduccionCollection(Collection<Historialreproduccion> historialreproduccionCollection) {
        this.historialreproduccionCollection = historialreproduccionCollection;
    }
    public Collection<Cancionprivada> getCancionprivadaCollection() {
        return cancionprivadaCollection;
    }
    public void setCancionprivadaCollection(Collection<Cancionprivada> cancionprivadaCollection) {
        this.cancionprivadaCollection = cancionprivadaCollection;
    }
    public Collection<Listareproduccion> getListareproduccionCollection() {
        return listareproduccionCollection;
    }
    public void setListareproduccionCollection(Collection<Listareproduccion> listareproduccionCollection) {
        this.listareproduccionCollection = listareproduccionCollection;
    }
}
