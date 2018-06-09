package negocio;

public class Cancionprivada{
    private int id;
    private int calificacion;
    private boolean disponibleSnConexion;
    private Cancion idCancion;
    private Usuario idUsuario;

    public Cancionprivada() {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCalificacion() {
        return calificacion;
    }
    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
    public boolean getDisponibleSnConexion() {
        return disponibleSnConexion;
    }
    public void setDisponibleSnConexion(boolean disponibleSnConexion) {
        this.disponibleSnConexion = disponibleSnConexion;
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
