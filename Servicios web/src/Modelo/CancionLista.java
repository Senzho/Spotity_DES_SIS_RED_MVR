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
@Table(name = "cancion_lista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CancionLista.findAll", query = "SELECT c FROM CancionLista c")
    , @NamedQuery(name = "CancionLista.findById", query = "SELECT c FROM CancionLista c WHERE c.id = :id")})
public class CancionLista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "idLista", referencedColumnName = "idLista")
    @ManyToOne
    private Listareproduccion idLista;
    @JoinColumn(name = "idCancion", referencedColumnName = "idCancion")
    @ManyToOne
    private Cancion idCancion;

    public CancionLista() {
    }

    public CancionLista(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Listareproduccion getIdLista() {
        return idLista;
    }

    public void setIdLista(Listareproduccion idLista) {
        this.idLista = idLista;
    }

    public Cancion getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(Cancion idCancion) {
        this.idCancion = idCancion;
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
        if (!(object instanceof CancionLista)) {
            return false;
        }
        CancionLista other = (CancionLista) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.CancionLista[ id=" + id + " ]";
    }
    
}
