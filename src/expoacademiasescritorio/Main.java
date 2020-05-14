/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expoacademiasescritorio;
import DAO.AlumnoDAO;
import Entidades.Alumno;
/**
 *
 * @author Jesus
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       AlumnoDAO a = new AlumnoDAO();
       Alumno b = a.alumnoPorId(new Alumno(173151));
        System.out.println(b.getClass());
        System.out.println("      s     "+b.getIDAlumno());
        System.out.println("                     d" + b.getNombre());
        System.out.println(b.getCorreo());
        System.out.println(b.getIDEquipo().getIDEquipo());
        System.out.println(b.getIDEquipo());
    }
    
}
