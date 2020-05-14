/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controladores.EncargadoJpaController;
import Entidades.Encargado;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jesus
 */
public class EncargadoDAO {

    EncargadoJpaController encntrl = new EncargadoJpaController();
    Encargado encargado = new Encargado();
    private String mensaje = "";

    public String insertarEncargado(String nombre, String materia, String nivel) {
        try {
            encargado.setIDEncargado(Integer.MIN_VALUE);
            encargado.setNombre(nombre);
            encargado.setMateria(materia);
            encargado.setNivel(nivel);
            encntrl.create(encargado);
            mensaje = "Guardado correctamente";
        } catch (Exception e) {
            System.out.println("Mensaje en guardar: " + e.getMessage());
            mensaje = "No se pudó guardar la información";
        }

        return mensaje;
    }
    
    public String insertarEncargado2(Encargado encargadox) {
        try {
            encargado.setIDEncargado(encargadox.getIDEncargado());
            encargado.setNombre(encargadox.getNombre());
            encargado.setMateria(encargadox.getMateria());
            encargado.setNivel(encargadox.getNivel());
            encntrl.create(encargado);
            mensaje = "Guardado Correctamente";
        } catch (Exception e) {
            System.out.println("Mensaje en guardar: " + e.getMessage());
            mensaje = "No se pudó guardar la información";
        }

        return mensaje;
    }

    public String actualizarEncargado(Encargado encargadox) {
        try {
            encargado.setIDEncargado(encargadox.getIDEncargado());
            encargado.setNombre(encargadox.getNombre());
            encargado.setMateria(encargadox.getMateria());
            encargado.setNivel(encargadox.getNivel());
            encargado.setProyectoList(encntrl.findEncargado(encargadox.getIDEncargado()).getProyectoList());
            encntrl.edit(encargado);
            mensaje = "Actuzalizado correctamente";
        } catch (Exception e) {
            System.out.println("Mensaje en guardar: " + e.getMessage());
            mensaje = "No se pudó aztualizar la información";
        }

        return mensaje;
    }

    public String eliminarEncargado(int id) {
        try {
            encntrl.destroy(id);
            mensaje = "Eliminado correctamente";
        } catch (Exception e) {
            System.out.println("Mensaje en eliminar: " + e.getMessage());
            mensaje = "No se pudó eliminar la información";
        }

        return mensaje;
    }

    public DefaultTableModel tablaEncargados() {
        DefaultTableModel tencargados = new DefaultTableModel();
        System.out.println(encntrl.findEncargadoEntities().size());
        System.out.println(encntrl.getEncargadoCount());

        tencargados.setColumnIdentifiers(new Object[]{"ID", "Nombre", "Materia", "Nivel"});
        Iterator it = encntrl.findEncargadoEntities().iterator();
        while (it.hasNext()) {
            Encargado e = (Encargado) it.next();
            System.out.println(e.getIDEncargado());
            System.out.println(e.getNombre());
            System.out.println(e.getMateria());
            System.out.println(e.getNivel());

            Integer id = e.getIDEncargado();
            String nom = e.getNombre();
            String materia = e.getMateria();
            String nivel = e.getNivel();

            tencargados.addRow(new Object[]{id, nom, materia, nivel});

        }
        return tencargados;
    }

    public List<Encargado> listaEncargadoss() {
        return encntrl.findEncargadoEntities();
    }

    public Encargado encargadoPorId(Encargado encargadon) {

        Encargado en = encntrl.findEncargado(encargadon.getIDEncargado());
        if (en == null) {
            return null;
        }
        return en;
    }

}
