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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Desktop
 */
@Entity
@Table(name = "genero_artista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeneroArtista.findAll", query = "SELECT g FROM GeneroArtista g")
    , @NamedQuery(name = "GeneroArtista.findByIdGenero", query = "SELECT g FROM GeneroArtista g WHERE g.idGenero = :idGenero")
    , @NamedQuery(name = "GeneroArtista.findByGenero", query = "SELECT g FROM GeneroArtista g WHERE g.genero = :genero")})
public class GeneroArtista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGenero")
    private Integer idGenero;
    @Size(max = 100)
    @Column(name = "genero")
    private String genero;
    @JoinColumn(name = "idArtista", referencedColumnName = "idArtista")
    @ManyToOne
    private Artista idArtista;

    public GeneroArtista() {
    }

    public GeneroArtista(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGenero != null ? idGenero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeneroArtista)) {
            return false;
        }
        GeneroArtista other = (GeneroArtista) object;
        if ((this.idGenero == null && other.idGenero != null) || (this.idGenero != null && !this.idGenero.equals(other.idGenero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.GeneroArtista[ idGenero=" + idGenero + " ]";
    }
    
}
