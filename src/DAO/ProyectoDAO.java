/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controladores.ProyectoJpaController;
import Entidades.Ciclo;
import Entidades.Encargado;
import Entidades.Equipo;
import Entidades.Proyecto;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jesus
 */
public class ProyectoDAO {

    private ProyectoJpaController pcntrl = new ProyectoJpaController();
    private Proyecto proyecto = new Proyecto();
    private String mensaje = "";

    /**
     * Metodo para insertar un proyecto en base a datos ya registrado excepto
     * nombre y descripcion
     *
     * @param nombre String Nombre del proyecto
     * @param ciclo
     * @param encargado
     * @param descripcion String descripcion proyecto, partes, tipo
     * @param equipo
     * @return
     */
    public String insertarProyecto(String nombre, Integer ciclo, Integer encargado, Integer equipo, String descripcion) {
        try {
            proyecto.setIDProyecto(pcntrl.getProyectoCount() + 1); // incremental desde aqui
            proyecto.setNombredelProyecto(nombre);
            proyecto.setIDCiclo(new Ciclo(ciclo));
            proyecto.setIDEncargado(new Encargado(encargado));
            proyecto.setIDEquipo(new Equipo(equipo));
            proyecto.setDescripcion(descripcion);
            pcntrl.create(proyecto);
            mensaje = "Guardado correctamente";
        } catch (Exception e) {
            System.out.println("Mensaje en guardar: " + e.getMessage());
            mensaje = "No se pudó guardar la información";
        }

        return mensaje;
    }

    public String insertarProyecto2(Proyecto proye) {
        try {
            proyecto.setIDProyecto(pcntrl.getProyectoCount() + 1); // incremental desde aqui
            proyecto.setNombredelProyecto(proye.getNombredelProyecto());
            proyecto.setIDCiclo(new Ciclo(proye.getIDCiclo().getIDCiclo()));
            proyecto.setIDEncargado(new Encargado(proye.getIDEncargado().getIDEncargado()));
            proyecto.setIDEquipo(new Equipo(proye.getIDEquipo().getIDEquipo()));
            proyecto.setDescripcion(proye.getDescripcion());
            pcntrl.create(proyecto);
            mensaje = "Guardado correctamente";
        } catch (Exception e) {
            System.out.println("Mensaje en guardar: " + e.getMessage());
            mensaje = "No se pudó guardar la información";
        }

        return mensaje;
    }

    public String actualizarProyecto(Proyecto proy) {
        try {
            proyecto.setIDProyecto(proy.getIDProyecto());
            proyecto.setNombredelProyecto(proy.getNombredelProyecto());
            proyecto.setIDCiclo(proy.getIDCiclo());
            proyecto.setIDEncargado(proy.getIDEncargado());
            proyecto.setIDEquipo(proy.getIDEquipo());
            proyecto.setDescripcion(proy.getDescripcion());
            pcntrl.edit(proyecto);
            mensaje = "Actualizado correctamente";
        } catch (Exception e) {
            System.out.println("Mensaje en guardar: " + e.getMessage());
            mensaje = "No se pudó actualizar la información";
        }

        return mensaje;
    }

    public String eliminarProyecto(int id) {
        try {
            pcntrl.destroy(id);
            mensaje = "Eliminado correctamente";
        } catch (Exception e) {
            System.out.println("Mensaje en eliminar: " + e.getMessage());
            mensaje = "No se pudó eliminar la información";
        }

        return mensaje;
    }

    public DefaultTableModel tablaProyectos() {
        DefaultTableModel tproyectos = new DefaultTableModel();
        System.out.println(pcntrl.findProyectoEntities().size());
        System.out.println(pcntrl.getProyectoCount());

        tproyectos.setColumnIdentifiers(new Object[]{"ID Proyecto", "Nombre del proyecto", "Descripción", "Equipo", "Encargado", "Ciclo"});
        Iterator it = pcntrl.findProyectoEntities().iterator();
        while (it.hasNext()) {
            Proyecto p = (Proyecto) it.next();
            System.out.println(p.getIDProyecto());
            System.out.println(p.getNombredelProyecto());
            System.out.println(p.getDescripcion());
            System.out.println(p.getIDEquipo().getIDEquipo());
            System.out.println(p.getIDEncargado().getNombre());
            System.out.println(p.getIDCiclo().getCiclo());

            Integer id = p.getIDProyecto();
            String nompro = p.getNombredelProyecto();
            String descripcion = p.getDescripcion();
            Integer nequipo = p.getIDEquipo().getIDEquipo();
            String encarga = p.getIDEncargado().getNombre();
            String ciclo = p.getIDCiclo().getCiclo();

            tproyectos.addRow(new Object[]{id, nompro, descripcion, nequipo, encarga, ciclo});

        }
        return tproyectos;
    }

    public List<Proyecto> listaProyectoss() {
        return pcntrl.findProyectoEntities();
    }

    public Proyecto proyectoPorId(Proyecto proyecton) {

        Proyecto pro = pcntrl.findProyecto(proyecton.getIDProyecto());
        if (pro == null) {
            return null;
        }
        return pro;
    }

}
