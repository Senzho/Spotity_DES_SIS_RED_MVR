package negocio;

import java.io.Serializable;

public class Peticion implements Serializable {
    private String tipoPeticion;
    private int idCancion;
    private String tipoArchivo;

    public Peticion(String tipoPeticion, int idCancion) {
        this.tipoPeticion = tipoPeticion;
        this.idCancion = idCancion;
    }

    public Peticion(String tipoPeticion, int idCancion, String tipoArchivo) {
        this.tipoPeticion = tipoPeticion;
        this.idCancion = idCancion;
        this.tipoArchivo = tipoArchivo;
    }
    
    public String getTipoPeticion() {
        return tipoPeticion;
    }
    public void setTipoPeticion(String tipoPeticion) {
        this.tipoPeticion = tipoPeticion;
    }
    public int getIdCancion() {
        return idCancion;
    }
    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }
    public String getTipoArchivo() {
        return tipoArchivo;
    }
    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }
}
