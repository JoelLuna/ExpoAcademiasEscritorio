package Entidades;

import Entidades.Ciclo;
import Entidades.Encargado;
import Entidades.Equipo;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-11T22:28:14")
@StaticMetamodel(Proyecto.class)
public class Proyecto_ { 

    public static volatile SingularAttribute<Proyecto, String> descripcion;
    public static volatile SingularAttribute<Proyecto, Integer> iDProyecto;
    public static volatile SingularAttribute<Proyecto, String> nombredelProyecto;
    public static volatile SingularAttribute<Proyecto, Equipo> iDEquipo;
    public static volatile SingularAttribute<Proyecto, Ciclo> iDCiclo;
    public static volatile SingularAttribute<Proyecto, Encargado> iDEncargado;

}