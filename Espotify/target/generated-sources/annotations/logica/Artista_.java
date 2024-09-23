package logica;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Album;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-09-22T22:36:12", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Artista.class)
public class Artista_ extends Usuario_ {

    public static volatile ListAttribute<Artista, Album> Albumes;
    public static volatile SingularAttribute<Artista, String> sitioWeb;
    public static volatile SingularAttribute<Artista, String> biografia;

}