package negocio;

public class CancionLista{
    private int id;
    private Cancion idCancion;
    private Listareproduccion idLista;

    public CancionLista() {

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

    /**
     * @return the idListareproduccion
     */
    public Listareproduccion getIdLista() {
        return idLista;
    }

    /**
     * @param idListareproduccion the idListareproduccion to set
     */
    public void setIdLista(Listareproduccion idListareproduccion) {
        this.idLista = idListareproduccion;
    }


}
