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
@Table(name = "ciclo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ciclo.findAll", query = "SELECT c FROM Ciclo c"),
    @NamedQuery(name = "Ciclo.findByIDCiclo", query = "SELECT c FROM Ciclo c WHERE c.iDCiclo = :iDCiclo"),
    @NamedQuery(name = "Ciclo.findByAnio", query = "SELECT c FROM Ciclo c WHERE c.anio = :anio"),
    @NamedQuery(name = "Ciclo.findByCiclo", query = "SELECT c FROM Ciclo c WHERE c.ciclo = :ciclo")})
public class Ciclo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCiclo")
    private Integer iDCiclo;
    @Column(name = "Anio")
    private String anio;
    @Column(name = "Ciclo")
    private String ciclo;
    @OneToMany(mappedBy = "iDCiclo")
    private List<Proyecto> proyectoList;

    public Ciclo() {
    }

    public Ciclo(Integer iDCiclo) {
        this.iDCiclo = iDCiclo;
    }

    public Integer getIDCiclo() {
        return iDCiclo;
    }

    public void setIDCiclo(Integer iDCiclo) {
        this.iDCiclo = iDCiclo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
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
        hash += (iDCiclo != null ? iDCiclo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ciclo)) {
            return false;
        }
        Ciclo other = (Ciclo) object;
        if ((this.iDCiclo == null && other.iDCiclo != null) || (this.iDCiclo != null && !this.iDCiclo.equals(other.iDCiclo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ciclo +" "+ anio;
    }

}
