package controlador;

import negocio.Cancion;

public interface EscuchadorCancion {
    public void cancionAReproduccion(Cancion cancion);
    public void cancionGenerarEstacion(Cancion cancion);
    public void cancionSiguienteCola(Cancion cancion);
    public void cancionFinalCola(Cancion cancion);
}
