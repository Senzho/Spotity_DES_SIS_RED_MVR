package negocio;

public class GeneroArtista{
    private int idGenero;
    private String genero;
    private Artista idArtista;

    public GeneroArtista() {

    }

    public int getIdGenero() {
        return idGenero;
    }
    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public Artista getIdArtista() {
        return idArtista;
    }
    public void setIdArtista(Artista idArtista) {
        this.idArtista = idArtista;
    }
}
