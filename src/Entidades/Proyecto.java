/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jesus
 */
@Entity
@Table(name = "proyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p"),
    @NamedQuery(name = "Proyecto.findByIDProyecto", query = "SELECT p FROM Proyecto p WHERE p.iDProyecto = :iDProyecto"),
    @NamedQuery(name = "Proyecto.findByNombredelProyecto", query = "SELECT p FROM Proyecto p WHERE p.nombredelProyecto = :nombredelProyecto"),
    @NamedQuery(name = "Proyecto.findByDescripcion", query = "SELECT p FROM Proyecto p WHERE p.descripcion = :descripcion")})
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDProyecto")
    private Integer iDProyecto;
    @Column(name = "NombredelProyecto")
    private String nombredelProyecto;
    @Column(name = "Descripcion")
    private String descripcion;
    @JoinColumn(name = "IDCiclo", referencedColumnName = "IDCiclo")
    @ManyToOne
    private Ciclo iDCiclo;
    @JoinColumn(name = "IDEncargado", referencedColumnName = "IDEncargado")
    @ManyToOne
    private Encargado iDEncargado;
    @JoinColumn(name = "IDEquipo", referencedColumnName = "IDEquipo")
    @ManyToOne
    private Equipo iDEquipo;

    public Proyecto() {
    }

    public Proyecto(Integer iDProyecto) {
        this.iDProyecto = iDProyecto;
    }

    public Integer getIDProyecto() {
        return iDProyecto;
    }

    public void setIDProyecto(Integer iDProyecto) {
        this.iDProyecto = iDProyecto;
    }

    public String getNombredelProyecto() {
        return nombredelProyecto;
    }

    public void setNombredelProyecto(String nombredelProyecto) {
        this.nombredelProyecto = nombredelProyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Ciclo getIDCiclo() {
        return iDCiclo;
    }

    public void setIDCiclo(Ciclo iDCiclo) {
        this.iDCiclo = iDCiclo;
    }

    public Encargado getIDEncargado() {
        return iDEncargado;
    }

    public void setIDEncargado(Encargado iDEncargado) {
        this.iDEncargado = iDEncargado;
    }

    public Equipo getIDEquipo() {
        return iDEquipo;
    }

    public void setIDEquipo(Equipo iDEquipo) {
        this.iDEquipo = iDEquipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDProyecto != null ? iDProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.iDProyecto == null && other.iDProyecto != null) || (this.iDProyecto != null && !this.iDProyecto.equals(other.iDProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Proyecto[ iDProyecto=" + iDProyecto + " ]";
    }
    
}
