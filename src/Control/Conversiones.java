/*
 * Conversiones.java
 */
package Control;

import Controladores.CicloJpaController;
import Entidades.Ciclo;
import Entidades.Proyecto;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Jesús González
 */
public class Conversiones {

    CicloJpaController cjpa = new CicloJpaController();

    public DefaultComboBoxModel ciclosComboBoxModel(List<Ciclo> listaCiclos) {
        DefaultComboBoxModel<Ciclo> defaultComboBoxModel = new DefaultComboBoxModel<>();
        if (listaCiclos != null) {
            Iterator it = cjpa.findCicloEntities().iterator();
        while (it.hasNext()) {
            Ciclo c = (Ciclo) it.next();
            System.out.println(c.getCiclo());
            System.out.println(c.getAnio());
            System.out.println(c.getIDCiclo());

            Integer id = c.getIDCiclo();
            String ciclo = c.getCiclo();
            String anio = c.getAnio();

            defaultComboBoxModel.addElement(c);

        }
            return defaultComboBoxModel;
        }
        return null;
    }
//
//    public DefaultComboBoxModel turistasComboBoxModel(List<Turista> ListaTurista) {
//        DefaultComboBoxModel<Turista> defaultComboBoxModel = new DefaultComboBoxModel<>();
//        if (ListaTurista != null) {
//            for (int i = 0; i < ListaTurista.size(); i++) {
//                defaultComboBoxModel.addElement(ListaTurista.get(i));
//            }
//            return defaultComboBoxModel;
//        }
//        return null;
//    }
}
