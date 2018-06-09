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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Desktop
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario")
    , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena")
    , @NamedQuery(name = "Usuario.findUserLogin", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre AND u.contrasena =:contrasena")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 100)
    @Column(name = "contrasena")
    private String contrasena;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<Historialreproduccion> historialreproduccionCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<Cancionprivada> cancionprivadaCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<Listareproduccion> listareproduccionCollection;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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

    @XmlTransient
    public Collection<Listareproduccion> getListareproduccionCollection() {
        return listareproduccionCollection;
    }

    public void setListareproduccionCollection(Collection<Listareproduccion> listareproduccionCollection) {
        this.listareproduccionCollection = listareproduccionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Usuario[ idUsuario=" + idUsuario + " ]";
    }
    
}
