package logica;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Tema;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-09-22T22:36:12", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(ListaReproduccion.class)
public class ListaReproduccion_ { 

    public static volatile SetAttribute<ListaReproduccion, Tema> listaTemas;
    public static volatile SingularAttribute<ListaReproduccion, String> imagen;
    public static volatile SingularAttribute<ListaReproduccion, Integer> Id;
    public static volatile SingularAttribute<ListaReproduccion, String> nombre;

}