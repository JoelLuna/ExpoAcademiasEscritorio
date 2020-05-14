/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controladores.AlumnoJpaController;
import Entidades.Alumno;
import Entidades.Equipo;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jesus
 */
public class AlumnoDAO {

    private AlumnoJpaController alucntrl = new AlumnoJpaController();
    private Alumno alumno = new Alumno();
    private String mensaje = "";
    Equipo equipo = new Equipo();

    public String insertarAlumno(Integer id, Integer numEquipo, String nombre, String correo) {
        try {
            alumno.setIDAlumno(id); //la bdd la asigna sola ..pendiente, PK que pedo el id, es autoincremental o la asignamos
            alumno.setIDEquipo(new Equipo(numEquipo));//pendiente, FK como la asigno la que ya esta creada en la tabla equipo
            alumno.setNombre(nombre);
            alumno.setCorreo(correo);
            alucntrl.create(alumno);
            mensaje = "Guardado Correctamente";
        } catch (Exception e) {
            System.out.println("Mensaje en guardar: " + e.getMessage());
            mensaje = "No se pudó guardar la información";
        }

        return mensaje;
    }

    public String actualizarAlumno(int id, Integer numEquipo, String nombre, String correo) {
        try {
            alumno.setIDAlumno(id); //pendiente, PK que pedo el id, es autoincremental o la asignamos
            alumno.setIDEquipo(new Equipo(numEquipo));//pendiente, FK como la asigno la que ya esta creada en la tabla equipo
            alumno.setNombre(nombre);
            alumno.setCorreo(correo);
            alucntrl.edit(alumno);
            mensaje = "Actualizado Correctamente";
        } catch (Exception e) {
            System.out.println("Mensaje en actualizar: " + e.getMessage());
            mensaje = "No se pudó actualizar la información";
        }

        return mensaje;
    }

    public String insertarAlumno2(Alumno alumnox) {
        try {
            alumno.setIDAlumno(alumnox.getIDAlumno()); //la bdd la asigna sola ..pendiente, PK que pedo el id, es autoincremental o la asignamos
            alumno.setIDEquipo(new Equipo(alumnox.getIDEquipo().getIDEquipo()));//pendiente, FK como la asigno la que ya esta creada en la tabla equipo
            alumno.setNombre(alumnox.getNombre());
            alumno.setCorreo(alumnox.getCorreo());
            alucntrl.create(alumno);
            mensaje = "Guardado Correctamente";
        } catch (Exception e) {
            System.out.println("Mensaje en guardar: " + e.getMessage());
            mensaje = "No se pudó guardar la información";
        }

        return mensaje;
    }

    public String actualizarAlumno(Alumno alumnox) {
        try {
            alumno.setIDAlumno(alumnox.getIDAlumno()); //la bdd la asigna sola ..pendiente, PK que pedo el id, es autoincremental o la asignamos
            alumno.setIDEquipo(new Equipo(alumnox.getIDEquipo().getIDEquipo()));//pendiente, FK como la asigno la que ya esta creada en la tabla equipo
            alumno.setNombre(alumnox.getNombre());
            alumno.setCorreo(alumnox.getCorreo());
            alucntrl.edit(alumno);
            mensaje = "Actualizado Correctamente";
        } catch (Exception e) {
            System.out.println("Mensaje en actualizar: " + e.getMessage());
            mensaje = "No se pudó actualizar la información";
        }

        return mensaje;
    }

    public String eliminarAlumno(int id) {
        try {
            alucntrl.destroy(id);
            mensaje = "Eliminado correctamente";
        } catch (Exception e) {
            System.out.println("Mensaje en eliminar: " + e.getMessage());
            mensaje = "No se pudó eliminar la información";
        }

        return mensaje;
    }

    public DefaultTableModel tablaAlumnos() {
        DefaultTableModel talumnos = new DefaultTableModel();
        System.out.println(alucntrl.findAlumnoEntities().size());
        System.out.println(alucntrl.getAlumnoCount());

        talumnos.setColumnIdentifiers(new Object[]{"ID", "Nombre", "Correo", "Equipo"});
        Iterator it = alucntrl.findAlumnoEntities().iterator();
        while (it.hasNext()) {
            Alumno a = (Alumno) it.next();
            System.out.println(a.getIDAlumno());
            System.out.println(a.getNombre());
            System.out.println(a.getCorreo());
            System.out.println(a.getIDEquipo().getIDEquipo());

            Integer id = a.getIDAlumno();
            String nom = a.getNombre();
            String correo = a.getCorreo();
            Integer nequipo = a.getIDEquipo().getIDEquipo();

            talumnos.addRow(new Object[]{id, nom, correo, nequipo});

        }
        return talumnos;
    }

    public List<Alumno> listaAlumnos() {
        return alucntrl.findAlumnoEntities();
    }

    public Alumno alumnoPorId(Alumno alumnon) {
       
        Alumno al = alucntrl.findAlumno(alumnon.getIDAlumno());
        if (al == null) {
            return null;
        }
        return al;
    }

}
