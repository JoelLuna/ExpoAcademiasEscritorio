package Entidades;

import Entidades.Equipo;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-11T22:28:14")
@StaticMetamodel(Alumno.class)
public class Alumno_ { 

    public static volatile SingularAttribute<Alumno, String> correo;
    public static volatile SingularAttribute<Alumno, Integer> iDAlumno;
    public static volatile SingularAttribute<Alumno, Equipo> iDEquipo;
    public static volatile SingularAttribute<Alumno, String> nombre;

}