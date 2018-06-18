/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.Serializable;

/**
 *
 * @author Desktop
 */
public class Peticion implements Serializable {
    private String tipoPeticion;
    private int idCancion;
    private String tipoArchivo;
    private byte[] cancion;
    private int idAlbum;
    private int idArtista;

    public Peticion(String tipoPeticion, byte[] cancion, int idAlbum, int idArtista, int idCancion) {
        this.tipoPeticion = tipoPeticion;
        this.cancion=cancion;
        this.idAlbum=idAlbum;
        this.idArtista=idArtista;
        this.idCancion=idCancion;
        
    }
    
    public Peticion(String tipoPeticion, int idCancion, String tipoArchivo) {
        this.tipoPeticion = tipoPeticion;
        this.idCancion = idCancion;
        this.tipoArchivo = tipoArchivo;
    }
    
    public Peticion(String tipoPeticion, int idCancion) {
        this.tipoPeticion = tipoPeticion;
        this.idCancion = idCancion;
    }
    
    public byte[] getCancion() {
        return cancion;
    }

    public void setCancion(byte[] cancion) {
        this.cancion = cancion;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
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
