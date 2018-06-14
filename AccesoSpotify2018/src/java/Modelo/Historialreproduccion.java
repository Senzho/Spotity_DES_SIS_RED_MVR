/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Victor Javier
 */
@Entity
@Table(name = "historialreproduccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historialreproduccion.findAll", query = "SELECT h FROM Historialreproduccion h")
    , @NamedQuery(name = "Historialreproduccion.findById", query = "SELECT h FROM Historialreproduccion h WHERE h.id = :id")
    , @NamedQuery(name = "Historialreproduccion.findByUsuario", query = "SELECT c FROM Cancion c, Historialreproduccion h WHERE c.idCancion = h.idCancion.idCancion AND h.idUsuario.idUsuario = :idUsuario")
})
public class Historialreproduccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "idCancion", referencedColumnName = "idCancion")
    @ManyToOne
    private Cancion idCancion;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario idUsuario;

    public Historialreproduccion() {
    }

    public Historialreproduccion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historialreproduccion)) {
            return false;
        }
        Historialreproduccion other = (Historialreproduccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Historialreproduccion[ id=" + id + " ]";
    }
    
}
