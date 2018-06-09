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
 * @author Desktop
 */
@Entity
@Table(name = "cancionprivada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cancionprivada.findAll", query = "SELECT c FROM Cancionprivada c")
    , @NamedQuery(name = "Cancionprivada.findById", query = "SELECT c FROM Cancionprivada c WHERE c.id = :id")
    , @NamedQuery(name = "Cancionprivada.findByCalificacion", query = "SELECT c FROM Cancionprivada c WHERE c.calificacion = :calificacion")
    , @NamedQuery(name = "Cancionprivada.findByDisponibleSnConexion", query = "SELECT c FROM Cancionprivada c WHERE c.disponibleSnConexion = :disponibleSnConexion")})
public class Cancionprivada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "calificacion")
    private Integer calificacion;
    @Column(name = "disponibleSnConexion")
    private Boolean disponibleSnConexion;
    @JoinColumn(name = "idCancion", referencedColumnName = "idCancion")
    @ManyToOne
    private Cancion idCancion;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario idUsuario;

    public Cancionprivada() {
    }

    public Cancionprivada(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public Boolean getDisponibleSnConexion() {
        return disponibleSnConexion;
    }

    public void setDisponibleSnConexion(Boolean disponibleSnConexion) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cancionprivada)) {
            return false;
        }
        Cancionprivada other = (Cancionprivada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Cancionprivada[ id=" + id + " ]";
    }
    
}
