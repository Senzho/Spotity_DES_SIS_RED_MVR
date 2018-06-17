package controlador;

public interface EscuchadorReproduccion {
    public void cancionTerminada(int idCancion);
    public void cancionNoReproducida(int idCancion);
}
