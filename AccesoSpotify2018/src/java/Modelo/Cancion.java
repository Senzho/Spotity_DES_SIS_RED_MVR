/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Victor Javier
 */
@Entity
@Table(name = "cancion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cancion.findAll", query = "SELECT c FROM Cancion c")
    , @NamedQuery(name = "Cancion.findByIdCancion", query = "SELECT c FROM Cancion c WHERE c.idCancion = :idCancion")
    , @NamedQuery(name = "Cancion.findByNombre", query = "SELECT c FROM Cancion c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Cancion.findByGenero", query = "SELECT c FROM Cancion c WHERE c.genero = :genero")
    , @NamedQuery(name = "Cancion.findByDuracion", query = "SELECT c FROM Cancion c WHERE c.duracion = :duracion")
    , @NamedQuery(name = "Cancion.findByAlbum", query = "SELECT c FROM Cancion c WHERE c.idAlbum.idAlbum = :idAlbum")
})
public class Cancion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCancion")
    private Integer idCancion;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 100)
    @Column(name = "genero")
    private String genero;
    @Size(max = 10)
    @Column(name = "duracion")
    private String duracion;
    @OneToMany(mappedBy = "idCancion")
    private Collection<Historialreproduccion> historialreproduccionCollection;
    @OneToMany(mappedBy = "idCancion")
    private Collection<Cancionprivada> cancionprivadaCollection;
    @JoinColumn(name = "idAlbum", referencedColumnName = "idAlbum")
    @ManyToOne
    private Album idAlbum;
    @JoinColumn(name = "idArtista", referencedColumnName = "idArtista")
    @ManyToOne
    private Artista idArtista;
    @OneToMany(mappedBy = "idCancion")
    private Collection<CancionLista> cancionListaCollection;

    public Cancion() {
    }

    public Cancion(Integer idCancion) {
        this.idCancion = idCancion;
    }

    public Integer getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(Integer idCancion) {
        this.idCancion = idCancion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    @XmlTransient
    public Collection<Historialreproduccion> getHistorialreproduccionCollection() {
        return historialreproduccionCollection;
    }

    public void setHistorialreproduccionCollection(Collection<Historialreproduccion> historialreproduccionCollection) {
        this.historialreproduccionCollection = historialreproduccionCollection;
    }

    @XmlTransient
    public Collection<Cancionprivada> getCancionprivadaCollection() {
        return cancionprivadaCollection;
    }

    public void setCancionprivadaCollection(Collection<Cancionprivada> cancionprivadaCollection) {
        this.cancionprivadaCollection = cancionprivadaCollection;
    }

    public Album getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Album idAlbum) {
        this.idAlbum = idAlbum;
    }

    public Artista getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(Artista idArtista) {
        this.idArtista = idArtista;
    }

    @XmlTransient
    public Collection<CancionLista> getCancionListaCollection() {
        return cancionListaCollection;
    }

    public void setCancionListaCollection(Collection<CancionLista> cancionListaCollection) {
        this.cancionListaCollection = cancionListaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCancion != null ? idCancion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cancion)) {
            return false;
        }
        Cancion other = (Cancion) object;
        if ((this.idCancion == null && other.idCancion != null) || (this.idCancion != null && !this.idCancion.equals(other.idCancion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Cancion[ idCancion=" + idCancion + " ]";
    }
    
}
