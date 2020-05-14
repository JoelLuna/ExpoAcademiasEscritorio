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
 * @author Jesus
 */
@Entity
@Table(name = "alumno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alumno.findAll", query = "SELECT a FROM Alumno a"),
    @NamedQuery(name = "Alumno.findByIDAlumno", query = "SELECT a FROM Alumno a WHERE a.iDAlumno = :iDAlumno"),
    @NamedQuery(name = "Alumno.findByNombre", query = "SELECT a FROM Alumno a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Alumno.findByCorreo", query = "SELECT a FROM Alumno a WHERE a.correo = :correo")})
public class Alumno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDAlumno")
    private Integer iDAlumno;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "correo")
    private String correo;
    @JoinColumn(name = "IDEquipo", referencedColumnName = "IDEquipo")
    @ManyToOne(optional = false)
    private Equipo iDEquipo;

    public Alumno() {
    }

    public Alumno(Integer iDAlumno) {
        this.iDAlumno = iDAlumno;
    }

    public Integer getIDAlumno() {
        return iDAlumno;
    }

    public void setIDAlumno(Integer iDAlumno) {
        this.iDAlumno = iDAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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
        hash += (iDAlumno != null ? iDAlumno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.iDAlumno == null && other.iDAlumno != null) || (this.iDAlumno != null && !this.iDAlumno.equals(other.iDAlumno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Alumno[ iDAlumno=" + iDAlumno + " ]";
    }
    
}
