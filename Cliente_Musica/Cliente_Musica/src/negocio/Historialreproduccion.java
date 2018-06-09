package negocio;

public class Historialreproduccion{
    private int id;
    private Cancion idCancion;
    private Usuario idUsuario;

    public Historialreproduccion() {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Cancion getIdCancion() {
        return idCancion;
    }
    public void setIdCancion(Cancion idCancion) {
        this.idCancion = idCancion;
    }
    public Usuario getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
}
