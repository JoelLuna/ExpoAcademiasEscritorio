/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controladores.EquipoJpaController;
import Entidades.Equipo;

/**
 *
 * @author Jesus
 */
public class EquipoDAO {

    EquipoJpaController eqcntrl = new EquipoJpaController();
    Equipo equipo = new Equipo();
    private String mensaje = "";

    /**
     * Este metodo lo hice por el qlo porque definimos en la bdd que fuera
     * incremental
     *
     * @return Int numero del equipo siguiente
     */
    public String insertarEquipo() {
        try {
            equipo.setIDEquipo((eqcntrl.getEquipoCount() + 1));
            //en dado caso aqui le haria algo falta para agregar un proyecto
            eqcntrl.create(equipo);
            mensaje = "Nuevo equipo creado exitosamente";
        } catch (Exception e) {
            System.out.println("Mensaje en guardar: " + e.getMessage());
            mensaje = "No se pudó crear un nuevo equipo";
        }
        return mensaje;
    }

    public String eliminarEquipo(int id) {
        try {
            eqcntrl.destroy(id);
            mensaje = "Eliminado correctamente";
        } catch (Exception e) {
            System.out.println("Mensaje en eliminar: " + e.getMessage());
            mensaje = "No se pudó eliminar la información";
        }

        return mensaje;
    }

}
