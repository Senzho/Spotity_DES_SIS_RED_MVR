package negocio;

import java.util.Collection;

public class Listareproduccion{
    private int idLista;
    private String nombre;
    private Collection<CancionLista> cancionListaCollection;
    private Usuario idUsuario;

    public Listareproduccion() {

    }

    public int getIdLista() {
        return idLista;
    }
    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Collection<CancionLista> getCancionListaCollection() {
        return cancionListaCollection;
    }
    public void setCancionListaCollection(Collection<CancionLista> cancionListaCollection) {
        this.cancionListaCollection = cancionListaCollection;
    }
    public Usuario getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
}
