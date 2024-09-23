package logica;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Artista;
import logica.Genero;
import logica.Tema;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-09-22T22:36:12", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Album.class)
public class Album_ { 

    public static volatile ListAttribute<Album, Genero> Generos;
    public static volatile ListAttribute<Album, Tema> Temas;
    public static volatile SingularAttribute<Album, Artista> artista;
    public static volatile SingularAttribute<Album, String> ImagenAlbum;
    public static volatile SingularAttribute<Album, Integer> fechaCreacion;
    public static volatile SingularAttribute<Album, Long> id;
    public static volatile SingularAttribute<Album, String> nombre;

}