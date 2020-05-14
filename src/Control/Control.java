/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Controladores.*;
import DAO.*;
import Entidades.*;
import UI.ConstantesGUI;
import UI.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesus
 */
public class Control {

    private AlumnoDAO alumnoDAO = new AlumnoDAO();
    private AlumnoJpaController alumnocntrl = new AlumnoJpaController();
    private ProyectoDAO proyectoDAO = new ProyectoDAO();
    private ProyectoJpaController proycntrl = new ProyectoJpaController();
    private EncargadoDAO encargadoDAO = new EncargadoDAO();
    private EncargadoJpaController encargcntrl = new EncargadoJpaController();
    private CicloJpaController cjpacntrl = new CicloJpaController();

    public boolean agregaAlumno(JFrame frame) {
        boolean bolean = false;
        Alumno alumnob = null;
        StringBuffer respuesta = new StringBuffer("");
        List<Alumno> listaAlumnos = new ArrayList<>();

        DlgAlumnos dlgAlumnos;
        String id = JOptionPane.showInputDialog(frame, "Ingrese el id", "Agregar ID alumno", JOptionPane.QUESTION_MESSAGE);
        if (id == null) {
            return false;

        } else if (id.equalsIgnoreCase(" ") || id.length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese una id para el alumno");
            agregaAlumno(frame);
            return false;
        }
        Alumno alumno = new Alumno(Integer.parseInt(id));
        try {
            alumnob = alumnoDAO.alumnoPorId(alumno);
            listaAlumnos = alumnoDAO.listaAlumnos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!..", JOptionPane.ERROR_MESSAGE);
        }

        if (alumnob != null) {
            dlgAlumnos = new DlgAlumnos(frame, "El inmueble ya esta en catalogo.", true, alumnob, ConstantesGUI.DESPLEGAR, respuesta);
            return false;
        }
        System.out.println(alumno);
        System.out.println(alumno.getIDAlumno());
        System.out.println(alumno.getNombre());
        System.out.println(alumno.getCorreo());
        System.out.println(alumno.getIDEquipo());
        System.out.println("-------------------------nel");
        dlgAlumnos = new DlgAlumnos(frame, "Captura Datos Inmueble", true, alumno, ConstantesGUI.AGREGAR, respuesta);

        if (respuesta.substring(0).equals(ConstantesGUI.CANCELAR)) {
            return false;
        }
        try {
            System.out.println(alumno.getIDAlumno());
            System.out.println(alumno.getNombre());
            System.out.println(alumno.getCorreo());
            System.out.println(alumno.getIDEquipo().getIDEquipo());
            System.out.println(alumno);

            alumnoDAO.insertarAlumno2(alumno);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!..", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return bolean = true;

    }

    public boolean actualizaAlumno(JFrame frame) {
        Alumno alumno;
        StringBuffer respuesta = new StringBuffer("");
        DlgAlumnos dlgAlumnos;
        List<Alumno> listaAlumnos;

        String id = JOptionPane.showInputDialog(frame, "ID del alumno:",
                "Actualizar Datos alumno",
                JOptionPane.QUESTION_MESSAGE);
        if (id == null) {
            return false;
        } else if (id.length() == 0 || id.equalsIgnoreCase(" ")) {
            JOptionPane.showMessageDialog(null, "Ingrese una id para el alumno");
            actualizaAlumno(frame);
            return false;
        }
        alumno = new Alumno(Integer.parseInt(id));
        try {
            alumno = alumnoDAO.alumnoPorId(alumno);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (alumno == null) {
            JOptionPane.showMessageDialog(frame,
                    "El alumno no existe", "Error!!.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            listaAlumnos = alumnoDAO.listaAlumnos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        dlgAlumnos = new DlgAlumnos(frame, "Edita Datos Alumno", true, alumno, ConstantesGUI.ACTUALIZAR, respuesta);
        if (respuesta.substring(0).equals(ConstantesGUI.CANCELAR)) {
            return false;
        }
        try {
            System.out.println("actualizo");
            alumnoDAO.actualizarAlumno(alumno);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean elminarAlumno(JFrame frame) {
        Alumno alumno;
        StringBuffer respuesta = new StringBuffer("");
        DlgAlumnos dlgAlumnos;
        List<Alumno> listaAlumnos;

        String id = JOptionPane.showInputDialog(frame, "ID del Alumno:",
                "Eliminar Alumno",
                JOptionPane.QUESTION_MESSAGE);
        // Si el usuario presiono el boton Cancelar
        if (id == null) {
            return false;
        } else if (id.length() == 0 || id.equalsIgnoreCase(" ")) {
            JOptionPane.showMessageDialog(null, "Ingrese una id para el alumno");
            elminarAlumno(frame);
            return false;
        }

        alumno = new Alumno(Integer.parseInt(id));
        try {
            alumno = alumnoDAO.alumnoPorId(alumno);
        } catch (Exception e) {
            // Si ocurrio un error al leer de alumnos lista
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (alumno == null) {
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, "El alumno no existe",
                    "Error!!.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            // Obtiene la lista de generos de canciones
            listaAlumnos = alumnoDAO.listaAlumnos();
        } catch (Exception e) {
            // Si ocurrio un error al obtener la lista de la base de datos,
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Si existe la cancion, despliega los datos de la cancion
        dlgAlumnos = new DlgAlumnos(frame, "Alumno a eliminar", true, alumno, ConstantesGUI.ELIMINAR, respuesta);

        // Si el usuario presiono el boton Cancelar
        if (respuesta.substring(0).equals(ConstantesGUI.CANCELAR)) {
            return false;
        }
        try {
            // Elimina la cancion del catalogo de canciones
            alumnoDAO.eliminarAlumno(alumno.getIDAlumno());
        } catch (Exception e) {
            // Si ocurrio un error al borrar del catalogo de canciones,
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean agregarProyecto(JFrame frame) {
        boolean bolean = false;
        Proyecto proyectob = null;
        StringBuffer respuesta = new StringBuffer("");
        List<Proyecto> listaProyectos = new ArrayList<>();

        DlgProyectos dlgProyecto;
        String id = JOptionPane.showInputDialog(frame, "Ingrese el id", "Agregar ID proyecto", JOptionPane.QUESTION_MESSAGE);
        if (id == null) {
            return false;

        } else if (id.equalsIgnoreCase(" ") || id.length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese una id para el proyecto");
            agregarProyecto(frame);
            return false;
        }
        Proyecto proyecto = new Proyecto(Integer.parseInt(id));
        try {
            proyectob = proyectoDAO.proyectoPorId(proyecto);
            listaProyectos = proyectoDAO.listaProyectoss();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!..", JOptionPane.ERROR_MESSAGE);
        }

        if (proyectob != null) {
            dlgProyecto = new DlgProyectos(frame, "El proyecto ya está en catalogo.", true, proyectob, ConstantesGUI.DESPLEGAR, respuesta);
            return false;
        }
        System.out.println(proyecto);
//        System.out.println(proyecto.getIDProyecto());
//        System.out.println(proyecto.getNombredelProyecto());
//        System.out.println(proyecto.getIDEquipo().getIDEquipo());
//        System.out.println(proyecto.getIDCiclo().getIDCiclo());
        System.out.println("-------------------------nel");
        dlgProyecto = new DlgProyectos(frame, "Captura Datos Proyecto", true, proyecto, ConstantesGUI.AGREGAR, respuesta);

        if (respuesta.substring(0).equals(ConstantesGUI.CANCELAR)) {
            return false;
        }
        try {
            System.out.println(proyecto.getIDProyecto());
            System.out.println(proyecto.getNombredelProyecto());
            System.out.println(proyecto.getIDEquipo().getIDEquipo());
            System.out.println(proyecto.getIDCiclo().getIDCiclo());
            System.out.println(proyecto.getIDEncargado().getIDEncargado());
            System.out.println(proyecto.getDescripcion());
            System.out.println(proyecto);

            proyectoDAO.insertarProyecto2(proyecto);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!..", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return bolean = true;

    }

    public boolean agregaEncargado(JFrame frame) {
        boolean bolean = false;
        Encargado encargadob = null;
        StringBuffer respuesta = new StringBuffer("");
        List<Encargado> listaEncargados = new ArrayList<>();

        DlgEncargado dlgEncargado;
        String id = JOptionPane.showInputDialog(frame, "Ingrese el id", "Agregar ID encargado", JOptionPane.QUESTION_MESSAGE);
        if (id == null) {
            return false;

        } else if (id.equalsIgnoreCase(" ") || id.length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese una id para el alumno");
            agregaAlumno(frame);
            return false;
        }
        Encargado encargado = new Encargado(Integer.parseInt(id));
        try {
            encargadob = encargadoDAO.encargadoPorId(encargado);
            listaEncargados = encargadoDAO.listaEncargadoss();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!..", JOptionPane.ERROR_MESSAGE);
        }

        if (encargadob != null) {
            dlgEncargado = new DlgEncargado(frame, "El encargado ya está en catalogo.", true, encargadob, ConstantesGUI.DESPLEGAR, respuesta);
            return false;
        }
        System.out.println(encargado);
        System.out.println(encargado.getIDEncargado());
        System.out.println(encargado.getNombre());
        System.out.println(encargado.getMateria());
        System.out.println(encargado.getNivel());
        System.out.println("-------------------------nel");
        dlgEncargado = new DlgEncargado(frame, "Captura Datos Encargado", true, encargado, ConstantesGUI.AGREGAR, respuesta);

        if (respuesta.substring(0).equals(ConstantesGUI.CANCELAR)) {
            return false;
        }
        try {
            System.out.println(encargado.getIDEncargado());
            System.out.println(encargado.getNombre());
            System.out.println(encargado.getMateria());
            System.out.println(encargado.getNivel());
            System.out.println(encargado);

            encargadoDAO.insertarEncargado2(encargado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!..", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return bolean = true;

    }

    public boolean actualizaEncargado(JFrame frame) {
        Encargado encargado;
        StringBuffer respuesta = new StringBuffer("");
        DlgEncargado dlgEncargado;
        List<Encargado> listaEncargado;

        String id = JOptionPane.showInputDialog(frame, "ID del encargado:",
                "Actualizar Datos encargado",
                JOptionPane.QUESTION_MESSAGE);
        if (id == null) {
            return false;
        } else if (id.length() == 0 || id.equalsIgnoreCase(" ")) {
            JOptionPane.showMessageDialog(null, "Ingrese una id para el encargado");
            actualizaAlumno(frame);
            return false;
        }
        encargado = new Encargado(Integer.parseInt(id));
        try {
            encargado = encargadoDAO.encargadoPorId(encargado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (encargado == null) {
            JOptionPane.showMessageDialog(frame,
                    "El encargado no existe", "Error!!.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            listaEncargado = encargadoDAO.listaEncargadoss();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        dlgEncargado = new DlgEncargado(frame, "Edita Datos Encargado", true, encargado, ConstantesGUI.ACTUALIZAR, respuesta);
        if (respuesta.substring(0).equals(ConstantesGUI.CANCELAR)) {
            return false;
        }
        try {
            System.out.println("actualizo");
            encargadoDAO.actualizarEncargado(encargado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean eliminarEncargado(JFrame frame) {
        Encargado encargado;
        StringBuffer respuesta = new StringBuffer("");
        DlgEncargado dlgEncargado;
        List<Encargado> listaEncargados;

        String id = JOptionPane.showInputDialog(frame, "ID del Encargado:",
                "Eliminar Encargado",
                JOptionPane.QUESTION_MESSAGE);
        // Si el usuario presiono el boton Cancelar
        if (id == null) {
            return false;
        } else if (id.length() == 0 || id.equalsIgnoreCase(" ")) {
            JOptionPane.showMessageDialog(null, "Ingrese una id para el encargado");
            eliminarEncargado(frame);
            return false;
        }

        encargado = new Encargado(Integer.parseInt(id));
        try {
            encargado = encargadoDAO.encargadoPorId(encargado);
        } catch (Exception e) {
            // Si ocurrio un error al leer de alumnos lista
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (encargado == null) {
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, "El encargado no existe",
                    "Error!!.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            // Obtiene la lista de generos de canciones
            listaEncargados = encargadoDAO.listaEncargadoss();
        } catch (Exception e) {
            // Si ocurrio un error al obtener la lista de la base de datos,
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Si existe la cancion, despliega los datos de la cancion
        dlgEncargado = new DlgEncargado(frame, "Encargado a eliminar", true, encargado, ConstantesGUI.ELIMINAR, respuesta);

        // Si el usuario presiono el boton Cancelar
        if (respuesta.substring(0).equals(ConstantesGUI.CANCELAR)) {
            return false;
        }
        try {
            // Elimina la cancion del catalogo de canciones
            encargadoDAO.eliminarEncargado(encargado.getIDEncargado());
        } catch (Exception e) {
            // Si ocurrio un error al borrar del catalogo de canciones,
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

//    public DefaultComboBoxModel ciclosComboBoxModel() {
//        Conversiones conv = new Conversiones();
//        return conv.ciclosComboBoxModel(cjpacntrl.findCicloEntities());
//    }
    public boolean actualizarProyecto(JFrame frame) {
        Proyecto proyecto;
        StringBuffer respuesta = new StringBuffer("");
        DlgProyectos dlgProyecto;
        List<Proyecto> listaProyectos;

        String id = JOptionPane.showInputDialog(frame, "ID del proyecto:",
                "Actualizar Datos proyecto",
                JOptionPane.QUESTION_MESSAGE);
        if (id == null) {
            return false;
        } else if (id.length() == 0 || id.equalsIgnoreCase(" ")) {
            JOptionPane.showMessageDialog(null, "Ingrese una id para el proyecto");
            actualizarProyecto(frame);
            return false;
        }
        proyecto = new Proyecto(Integer.parseInt(id));
        try {
            proyecto = proyectoDAO.proyectoPorId(proyecto);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (proyecto == null) {
            JOptionPane.showMessageDialog(frame,
                    "El proyecto no existe", "Error!!.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            listaProyectos = proyectoDAO.listaProyectoss();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        dlgProyecto = new DlgProyectos(frame, "Edita Datos Proyecto", true, proyecto, ConstantesGUI.ACTUALIZAR, respuesta);
        if (respuesta.substring(0).equals(ConstantesGUI.CANCELAR)) {
            return false;
        }
        try {
            System.out.println("actualizo");
            proyectoDAO.actualizarProyecto(proyecto);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean eliminarProyecto(JFrame frame) {
        Proyecto proyecto;
        StringBuffer respuesta = new StringBuffer("");
        DlgProyectos dlgProyecto;
        List<Proyecto> listaProyectos;

        String id = JOptionPane.showInputDialog(frame, "ID del proyecto:",
                "Eliminar Proyecto",
                JOptionPane.QUESTION_MESSAGE);
        // Si el usuario presiono el boton Cancelar
        if (id == null) {
            return false;
        } else if (id.length() == 0 || id.equalsIgnoreCase(" ")) {
            JOptionPane.showMessageDialog(null, "Ingrese una id para el proyecto");
            eliminarEncargado(frame);
            return false;
        }

        proyecto = new Proyecto(Integer.parseInt(id));
        try {
            proyecto = proyectoDAO.proyectoPorId(proyecto);
        } catch (Exception e) {
            // Si ocurrio un error al leer de alumnos lista
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (proyecto == null) {
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, "El proyecto no existe",
                    "Error!!.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            // Obtiene la lista de generos de canciones
            listaProyectos = proyectoDAO.listaProyectoss();
        } catch (Exception e) {
            // Si ocurrio un error al obtener la lista de la base de datos,
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Si existe la cancion, despliega los datos de la cancion
        dlgProyecto = new DlgProyectos(frame, "Proyecto a eliminar", true, proyecto, ConstantesGUI.ELIMINAR, respuesta);

        // Si el usuario presiono el boton Cancelar
        if (respuesta.substring(0).equals(ConstantesGUI.CANCELAR)) {
            return false;
        }
        try {
            // Elimina la cancion del catalogo de canciones
            proyectoDAO.eliminarProyecto(proyecto.getIDProyecto());
        } catch (Exception e) {
            // Si ocurrio un error al borrar del catalogo de canciones,
            // despliega mensaje de error
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error!!.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
