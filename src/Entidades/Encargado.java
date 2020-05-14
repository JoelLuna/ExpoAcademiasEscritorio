/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jesus
 */
@Entity
@Table(name = "encargado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Encargado.findAll", query = "SELECT e FROM Encargado e"),
    @NamedQuery(name = "Encargado.findByIDEncargado", query = "SELECT e FROM Encargado e WHERE e.iDEncargado = :iDEncargado"),
    @NamedQuery(name = "Encargado.findByNombre", query = "SELECT e FROM Encargado e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Encargado.findByMateria", query = "SELECT e FROM Encargado e WHERE e.materia = :materia"),
    @NamedQuery(name = "Encargado.findByNivel", query = "SELECT e FROM Encargado e WHERE e.nivel = :nivel")})
public class Encargado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDEncargado")
    private Integer iDEncargado;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Materia")
    private String materia;
    @Column(name = "Nivel")
    private String nivel;
    @OneToMany(mappedBy = "iDEncargado")
    private List<Proyecto> proyectoList;

    public Encargado() {
    }

    public Encargado(Integer iDEncargado) {
        this.iDEncargado = iDEncargado;
    }

    public Integer getIDEncargado() {
        return iDEncargado;
    }

    public void setIDEncargado(Integer iDEncargado) {
        this.iDEncargado = iDEncargado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    @XmlTransient
    public List<Proyecto> getProyectoList() {
        return proyectoList;
    }

    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDEncargado != null ? iDEncargado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Encargado)) {
            return false;
        }
        Encargado other = (Encargado) object;
        if ((this.iDEncargado == null && other.iDEncargado != null) || (this.iDEncargado != null && !this.iDEncargado.equals(other.iDEncargado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Encargado[ iDEncargado=" + iDEncargado + " ]";
    }
    
}
