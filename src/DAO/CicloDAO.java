/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controladores.CicloJpaController;
import Entidades.Ciclo;
 
/**
 *
 * @author Jesus
 */
public class CicloDAO {

    CicloJpaController cicntrl = new CicloJpaController();
    Ciclo ciclo = new Ciclo();
    private String mensaje = "";

    public String insertarCiclo(String anio, String ciclon) {
        try {
            ciclo.setIDCiclo((cicntrl.getCicloCount() + 1));
            ciclo.setAnio(anio);
            ciclo.setCiclo(ciclon);
            cicntrl.create(ciclo);
            mensaje = "Guardado correctamente";
        } catch (Exception e) {
            System.out.println("Mensaje en guardar: " + e.getMessage());
            mensaje = "No se pudó guardar la información";
        }

        return mensaje;
    }
    
    public String actualizarCiclo(int id, String anio, String ciclon) {
        try {
            ciclo.setIDCiclo(id);
            ciclo.setAnio(anio);
            ciclo.setCiclo(ciclon);
            cicntrl.edit(ciclo);
            mensaje = "Actualizado correctamente";
        } catch (Exception e) {
            System.out.println("Mensaje en guardar: " + e.getMessage());
            mensaje = "No se pudó guardar la información";
        }

        return mensaje;
    }
    
    public String eliminarCiclo(int id) {
        try {
            cicntrl.destroy(id);
            mensaje = "Eliminado correctamente";
        } catch (Exception e) {
            System.out.println("Mensaje en eliminar: " + e.getMessage());
            mensaje = "No se pudó eliminar la información";
        }

        return mensaje;
    }
    
}
