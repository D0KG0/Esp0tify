/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controladores;

import datatypes.DataUsuario;
import datatypes.DataLista;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import excepciones.ListaRepetidaException;
import excepciones.GeneroRepetidoException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.HashSet;
import java.util.ArrayList;
import logica.Usuario;
import logica.Album;
import logica.Genero;
import datatypes.DataAlbum;
import datatypes.DataGenero;
import datatypes.DataSub;
import datatypes.DataTema;
import excepciones.AlbumNoExisteException;
import excepciones.GeneroNoExisteException;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import logica.Artista;
import logica.Cliente;
import logica.ListaParticular;
import logica.ListaPorDefecto;
import logica.ListaReproduccion;
import logica.Tema;


public interface iSistema {
    public abstract void cargarDatos() throws GeneroRepetidoException, UsuarioRepetidoException;
//    public abstract void pruebaDatos(String s);
    public abstract List<DataTema> getDataTemasAjax(String s); //jUNit
    public abstract List<DataLista> getDataListasReproduccionAjax(String s, String tipo); //jUnit
    public abstract List<DataAlbum> getDataAlbumsAjax(String s); //jUnit
    public abstract List<DataUsuario> getDataUsuariosAjax(String s, String tipo); //Junit
    //Alta Usuarios - Consulta Usuarios  - Preferencias - agustin
    public abstract void altaPerfil(String nickname, String nombre, String apellido, String password, String email, LocalDate fechaNac, String imagen, String sitioWeb, String biografia, String tipo) throws UsuarioRepetidoException;//jUnit
    //public abstract DataUsuario[] getDataUsuarios() throws UsuarioNoExisteException; //No se usa
    public abstract DataUsuario[] getClientes() throws UsuarioNoExisteException;//jUnit
    public abstract DataUsuario[] getArtistas() throws UsuarioNoExisteException;//jUnit
    //public abstract DataUsuario[] filtrarUsuariosPorTipo(Usuario[] usuarios, String tipo); //No se usa
    public abstract boolean existeUsuario(String nickname);//jUnit
    //public abstract Usuario[] getUsuarios(); //No se usa pero NOSE PORQUE ACA SI.
    public abstract Usuario obtenerUsuarioMail(String email);//jUnit
    public abstract Usuario obtenerUsuario(String nick);//jUnit
    public abstract Cliente ObtenerCliente(String nick);//jUnit
    public abstract Artista ObtenerArtista(String nick);//jUnit
    //public abstract void addUsuario(Usuario user); //NO se usa
    //public abstract List<Cliente> getSeguidores(String nicknameArtista); //NO se usa
    public abstract List<DataUsuario> getSeguidores(String nicknameArtista);//jUnit
    //public abstract Map<String, ArrayList<String>> UsuarioSeguidos(String cliente_seleccionado); //NO se usa
    public abstract Map<String, ArrayList<DataUsuario>> UsuarioSeguidos(String cliente_seleccionado);//jUnit
    public abstract boolean existeMail(String email);//jUnit
    public abstract DataAlbum[] getAlbumsByArtista(String artista) throws AlbumNoExisteException;//jUnit
    public abstract Album getAlbum(String nombreA);//jUnit
    public abstract Tema getTema(String nombreT);//jUnit
    public abstract ListaReproduccion getLista(String nombreL);//jUnit
    public abstract void AgregarListaFavoritaCliente(String nickname, ListaReproduccion lista);//jUnit
    public abstract void AgregarAlbumFavoritoCliente(String nickname, Album album);//jUnit
    public abstract void AgregarTemaFavoritoCliente(String nickname, Tema tema);//jUnit
    public abstract DataAlbum getAlbumEspecificoGen2(String nombreA, String nombreGenero);//jUnit
    public abstract DataAlbum getAlbumEspecificoArt2(String nombreA, String nombreArtista);//jUnit
    
    
    //Album - michel
    //public abstract void actualizoAlbum();
    public abstract void altaGenero(String nombre);//jUnit
    public abstract void addGenero(Genero genero);//jUnit
    public abstract void addGeneroAlbum(String nombreGenero);//jUnit
    public abstract void altaAlbum();//jUnit
    public abstract Album getAlbumGenerico();//jUnit
    //public abstract Collection<DataAlbum> getAlbumesArt(String nombreArtista); //NO SE USA
    //public abstract Collection<DataAlbum> getAlbumesGen(String nombreGenero); //NO SE USA
    //public abstract void desvinculaAlbum(String nombreA, String nombreArtista);
    public abstract boolean verificaAlbum(String nombreA, String nombreArtista);//jUnit
    public abstract void cargarDatosAlbum(String nombre, String nombreArtista, Integer fechaCreacion, String imagen);//jUnit
    public abstract void cargarGenerosSys();//jUnit
    public abstract void altaTema(String nombreT, String duracion, Integer posicion, String direccionWeb, String archivo);//jUnit
    public abstract HashSet<String> getGeneros();  //jUnit
    public abstract Album getAlbumEspecificoArt(String nombreA, String nombreArtista);//jUnit
    public abstract Album getAlbumEspecificoGen(String nombreA, String nombreGenero);//jUnit
    public abstract List<DataAlbum> getAllAlbums(); //jUnit
    
    //genero - lista fran
    //Genero
    public abstract String getPadre(String nombreGenero); //jUnit
    public abstract Genero getGenero(String nombreGenero);//jUnit
    
    public abstract boolean encontrarGenero(String nom);//jUnit
    public abstract void altaGenero(String nom, String pad) throws GeneroRepetidoException;//jUnit
    public abstract Set<String> listarGeneros();//jUnit
    public abstract Set<String> listarGenerosSinPadre();//jUnit
    //Listas
    public abstract boolean encontrarLista(String nom);//jUnit
    public abstract void altaListaPart(DataLista dtl) throws ListaRepetidaException;//jUnit
    public abstract void altaListaDef(DataLista dtl) throws ListaRepetidaException;//jUnit
    public abstract Set<String> listarListas();//jUnit
    public abstract Set<String> listarListasPar();//jUnit
    public abstract Set<String> listarListasDef();//jUnit
    public abstract DataLista darLista(int nom);//Junit
    public abstract boolean tipoLista(int id); //Junit
    
    public abstract List<DataLista> traerListasDefectoPorGenero(String genero);//jUnit
    public abstract ListaParticular traerListaParticularPorId(int id);//jUnit
    public abstract void modificarListaParticularPorId(int listaId);//jUnit
    public abstract List<DataLista> traerListasParticularesPorClientes(String nickname);//jUnit

    public abstract List<String> traerNickNamesClientes();//jUnit
    public abstract DataLista traerListaDefGeneroNombre(String genero, String nombre);//jUnit
    public abstract DataLista traerListaPartClienteNombre(String cliente, String nombre);//jUnit
    public abstract List<DataLista> traerListasPublicasTodas(); //Junit
    public abstract List<DataSub> listarSubsPendientes();//jUnit
    public abstract List<String> listarUsuarioSubsPendientes(); //jUnit
    public abstract DataSub darSubUsuario(String nick); //jUnit
    public abstract void altaSubscripcion (String nick, String tipo);//jUnit
    public abstract void cambioEstadoSubscripcion(String nick, String estado);//jUNit

    
    
    //BRIAN
    public abstract ArrayList Nicknames_De_Clientes();//jUnit
    public abstract ArrayList Listar_Usuarios_A_Seguir(String cliente_seleccionado);//jUnit
    public abstract void Seguir_Usuario(String cliente_seleccionado, String nickname_usuario);//jUnit
    public abstract ArrayList<String> Listar_Usuarios_A_DEJAR_DE_Seguir(String cliente_seleccionado);//jUnit
    public abstract void Dejar_De_Seguir_Usuario(String cliente, String usuario);//jUnit
    public abstract ArrayList<String> Listar_Temas_A_Seguir(String cliente_seleccionado);//jUnit
    public abstract void Seguir_TEMA(String cliente_seleccionado, String tema_seleccionado);//jUnit
    public abstract ArrayList<String> Listar_Albumes_A_Seguir(String cliente_seleccionado);//jUnit
    public abstract void Seguir_ALBUM(String cliente_seleccionado,String album_seleccionado);//jUnit
    public abstract ArrayList<String> Listar_Listas_A_Seguir(String cliente_seleccionado);//jUnit
    public abstract void Seguir_LISTA(String cliente_seleccionado, String lista_seleccionada);//jUnit
    
    //PROBANDO
    //public abstract List<String> traerGeneros();
    //public abstract List<String> traerArtistas();
    //public abstract List<String> traerAlbumesGenero(String genero);
    public abstract List<String> traerAlbumesArtista(String artista);//jUnit
    public abstract boolean existeTemaLista(Long idTema, int idLista);//jUnit
    public abstract boolean esPublicaLista(int idLista);//jUnit
    
    public abstract Vector<DataAlbum> traerAlbumes();//jUnit
    public abstract List<DataTema> traerTemasPorIdAlbum(Long id);//jUnit
    public abstract void agregarTemaLista(int idLista, Long idTema);//jUnit
    public abstract Vector<DataLista> traerListasParticularesPublicas();//jUnit
    public abstract List<DataTema> traerTemasPorIdListaReproduccion(int id);//jUnit
    public abstract List<DataLista> traerListasPorDefecto();//jUnit
    public abstract List<DataTema> traerTemasPorListasPorDefectoId(int listaid);//jUnit
    public abstract List<DataLista> traerListasFavoritasPorIdCliente(String nickname);//jUnit
    public abstract void eliminarListaFavoritaCliente(String nickname, int idLista);//jUnit
    public abstract List<DataTema> traerTemasFavoritosPorIdCliente(String nickname);//jUnit
    public abstract void eliminarTemasFavoritosCliente(String nickname, Long idTema);//jUnit
    public abstract List<DataAlbum> traerAlbumesFavoritosPorIdCliente(String nickname);//jUnit
    public abstract void eliminarAlbumesFavoritosCliente(String nickname, Long idAlbum);//jUnit
    
    public abstract ListaParticular traerListaParticularPorCliente(String nombre, String nickname);//jUnit
    public abstract ListaPorDefecto traerListaPorDefecto(String nombre);//jUnit
    public abstract List<DataLista> getListasParticulares(String nickname);//jUnit
    public abstract DataLista getListaDefecto(String nombre);//jUnit
    public abstract List<Tema> getTemas(Album album);//jUnit
    public abstract DataGenero[] getGeneros2() throws GeneroNoExisteException;//jUnit
    public abstract DataAlbum[] getAlbumsByGenero(String genero) throws AlbumNoExisteException;//jUnit
    public abstract void eliminarTemasListaPart(String nombre, String nombreL, Long idTema);//jUnit
    public abstract void eliminarTemaListaDef(String nombre, Long idTema);//jUnit
    
    public abstract boolean Encontrar_Lista_Por_Defecto(String nombre);//jUnit
    public abstract boolean Encontrar_Lista_Particular(String nombre_cliente,String nombre_lista);//jUnit
    
}


