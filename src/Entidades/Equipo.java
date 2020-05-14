/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jesus
 */
@Entity
@Table(name = "equipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Equipo e"),
    @NamedQuery(name = "Equipo.findByIDEquipo", query = "SELECT e FROM Equipo e WHERE e.iDEquipo = :iDEquipo")})
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDEquipo")
    private Integer iDEquipo;
    @OneToMany(mappedBy = "iDEquipo")
    private List<Proyecto> proyectoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDEquipo")
    private List<Alumno> alumnoList;

    public Equipo() {
    }

    public Equipo(Integer iDEquipo) {
        this.iDEquipo = iDEquipo;
    }

    public Integer getIDEquipo() {
        return iDEquipo;
    }

    public void setIDEquipo(Integer iDEquipo) {
        this.iDEquipo = iDEquipo;
    }

    @XmlTransient
    public List<Proyecto> getProyectoList() {
        return proyectoList;
    }

    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }

    @XmlTransient
    public List<Alumno> getAlumnoList() {
        return alumnoList;
    }

    public void setAlumnoList(List<Alumno> alumnoList) {
        this.alumnoList = alumnoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDEquipo != null ? iDEquipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipo)) {
            return false;
        }
        Equipo other = (Equipo) object;
        if ((this.iDEquipo == null && other.iDEquipo != null) || (this.iDEquipo != null && !this.iDEquipo.equals(other.iDEquipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Equipo[ iDEquipo=" + iDEquipo + " ]";
    }
    
}
