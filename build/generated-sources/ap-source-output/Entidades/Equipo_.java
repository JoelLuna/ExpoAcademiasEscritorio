package Entidades;

import Entidades.Alumno;
import Entidades.Proyecto;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-11T22:28:14")
@StaticMetamodel(Equipo.class)
public class Equipo_ { 

    public static volatile ListAttribute<Equipo, Proyecto> proyectoList;
    public static volatile SingularAttribute<Equipo, Integer> iDEquipo;
    public static volatile ListAttribute<Equipo, Alumno> alumnoList;

}