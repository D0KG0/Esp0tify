package logica;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Album;
import logica.ListaParticular;
import logica.ListaReproduccion;
import logica.Tema;
import logica.Usuario;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-09-22T22:36:12", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Cliente.class)
public class Cliente_ extends Usuario_ {

    public static volatile ListAttribute<Cliente, Album> albumesFavoritos;
    public static volatile ListAttribute<Cliente, Usuario> usuariosSiguiendo;
    public static volatile ListAttribute<Cliente, ListaParticular> listasDeCliente;
    public static volatile ListAttribute<Cliente, ListaReproduccion> listasFavoritas;
    public static volatile ListAttribute<Cliente, Tema> temasFavoritos;

}