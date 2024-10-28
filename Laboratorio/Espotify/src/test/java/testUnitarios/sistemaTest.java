/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package testUnitarios;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import controladores.Fabrica;
import controladores.Sistema;
import static controladores.Sistema.ARTISTA;
import controladores.iSistema;
import datatypes.DataAlbum;
import datatypes.DataGenero;
import datatypes.DataLista;
import datatypes.DataSub;
import datatypes.DataTema;
import datatypes.DataUsuario;
import excepciones.AlbumNoExisteException;
import excepciones.GeneroNoExisteException;
import excepciones.GeneroRepetidoException;
import excepciones.ListaRepetidaException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import logica.Album;
import logica.Artista;
import logica.Cliente;
import logica.Genero;
import logica.ListaParticular;
import logica.ListaPorDefecto;
import logica.ListaReproduccion;
import logica.Subscripcion;
import logica.Tema;
import logica.Usuario;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import persistencia.ControladorPersistencia;

/**
 *
 * @author dokgo
 */
public class sistemaTest {
    
    iSistema sys;
    ControladorPersistencia cpu;
    
    public sistemaTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        
        cpu = mock(ControladorPersistencia.class);
        sys = new Fabrica().getSistema(cpu);
        
        
    }
    
    @AfterEach
    public void tearDown() {
    }

    
    //////////////////////////////////////////////////////////////////////////TEST////////////////////////////////////////////////////////////////////////////
    
    //@i1
    //////////////////////////////////////AltaPerfil//////////////////////////////////////
    @Test
    public void testAltaPerfil_ClienteNuevo() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        assertDoesNotThrow(() -> {
            sistema.altaPerfil("clienteNuevo1", "Nombre", "Apellido", "password", "email@example.com", LocalDate.now(), "imagen.jpg", null, null, "cliente");
        });
    }

    @Test
    public void testAltaPerfil_ArtistaNuevo() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        assertDoesNotThrow(() -> {
            sistema.altaPerfil("artistaNuevo1", "Nombre", "Apellido", "password", "email@example.com", LocalDate.now(), "imagen.jpg", "biografía", "sitioWeb", "artista");
        });
    }

    @Test
    public void testAltaPerfil_UsuarioYaRegistrado() throws UsuarioRepetidoException {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        String nicknameExistente = "usuarioExistente1";
        Usuario usuarioExistente = new Usuario(nicknameExistente, "Nombre", "Apellido", "password", "email@example.com", LocalDate.now(), "imagen.jpg", "cliente");

        
        when(cpuMock.obtenerUsuario(nicknameExistente)).thenReturn(usuarioExistente);

        
        assertThrows(UsuarioRepetidoException.class, () -> {
            sistema.altaPerfil(nicknameExistente, "Nombre", "Apellido", "password", 
                              "email@example.com", LocalDate.now(), "imagen.jpg", 
                              null, null, "cliente");
        });

        
        verify(cpuMock).obtenerUsuario(nicknameExistente);
    }
    
    @Test
    public void testAltaPerfil_TipoUsuarioNoReconocido() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        String tipoInvalido = "tipoInvalido"; 

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            sistema.altaPerfil("usuarioNuevo", "Nombre", "Apellido", "password", "email@example.com", LocalDate.now(), "imagen.jpg", null, null, tipoInvalido);
        });

        
        assertEquals("Tipo de usuario no reconocido: " + tipoInvalido, exception.getMessage());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// DataUsuario[] getClientes()//////////////////////////////////////
    @Test
    public void testGetClientes() throws UsuarioNoExisteException {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);  

        
        Cliente cliente1 = new Cliente("cliente1", "Nombre1", "Apellido1", "pass1", "email1@test.com", LocalDate.now(), "imagen1.jpg", "cliente");
        Cliente cliente2 = new Cliente("cliente2", "Nombre2", "Apellido2", "pass2", "email2@test.com", LocalDate.now(), "imagen2.jpg", "cliente");

        List<Cliente> clientesMock = Arrays.asList(cliente1, cliente2);

       
        when(cpuMock.obtenerTodosLosClientes()).thenReturn(clientesMock);

        
        for (Cliente c : clientesMock) {
            
        }

        try {
            
            DataUsuario[] result = sistema.getClientes();
            

            
            if (result != null) {
                for (DataUsuario du : result) {
                    
                }
            }

            assertNotNull(result, "El resultado no debería ser null");
            assertEquals(2, result.length, "Deberían haber 2 clientes");
            assertEquals("cliente1", result[0].getNick(), "El primer cliente debería ser cliente1");
            assertEquals("cliente2", result[1].getNick(), "El segundo cliente debería ser cliente2");
        } catch (UsuarioNoExisteException e) {
            
            fail("No debería lanzar excepción cuando hay clientes: " + e.getMessage());
        }

        
        verify(cpuMock, times(1)).obtenerTodosLosClientes();
    }

    @Test
    public void testGetClientes_NoClientes() {
    
    ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

    
    Sistema sistema = new Sistema(cpuMock);

    
    List<Cliente> clientesMock = new ArrayList<>();
    when(cpuMock.obtenerTodosLosClientes()).thenReturn(clientesMock);

    
    
    
    try {
        
        sistema.getClientes();
        fail("Debería lanzar UsuarioNoExisteException cuando no hay clientes.");
    } catch (UsuarioNoExisteException e) {
        
        
        assertEquals("No existen clientes registrados", e.getMessage());
    }

    
    verify(cpuMock, times(1)).obtenerTodosLosClientes();
}


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// DataUsuario[] getArtistas()//////////////////////////////////////
    @Test
    public void testGetArtistas() throws UsuarioNoExisteException {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        Artista artista1 = new Artista("artista1", "Nombre1", "Apellido1", "pass1", "email1@test.com", LocalDate.now(), "imagen1.jpg","mibio","misitio", "artista");
        Artista artista2 = new Artista("artista2", "Nombre2", "Apellido2", "pass2", "email2@test.com", LocalDate.now(), "imagen2.jpg","mibio2","misitio2", "artista");

        List<Artista> artistasMock = Arrays.asList(artista1, artista2);

        
        when(cpuMock.obtenerTodosLosArtistas()).thenReturn(artistasMock);

        try {
            
            DataUsuario[] result = sistema.getArtistas();

            
            assertNotNull(result, "El resultado no debería ser null");
            assertEquals(2, result.length, "Deberían haber 2 artistas");
            assertEquals("artista1", result[0].getNick(), "El primer artista debería ser artista1");
            assertEquals("artista2", result[1].getNick(), "El segundo artista debería ser artista2");
        } catch (UsuarioNoExisteException e) {
            fail("No debería lanzar excepción cuando hay artistas: " + e.getMessage());
        }

        
        verify(cpuMock, times(1)).obtenerTodosLosArtistas();
    }
    
    @Test
    public void testGetArtistas_NoArtistas() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        List<Artista> artistasMock = new ArrayList<>();
        when(cpuMock.obtenerTodosLosArtistas()).thenReturn(artistasMock);

        
        try {
            sistema.getArtistas();
            fail("Debería lanzar UsuarioNoExisteException cuando no hay artistas.");
        } catch (UsuarioNoExisteException e) {
            
            assertEquals("No existen artistas registrados", e.getMessage());
        }

        
        verify(cpuMock, times(1)).obtenerTodosLosArtistas();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// boolean existeUsuario //////////////////////////////////////
    
    @Test
    public void testExisteUsuario_UsuarioExiste() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);
        
        
        String nicknameExistente = "usuarioExistente";
        Usuario usuarioMock = new Usuario(nicknameExistente, "Nombre", "Apellido", "password", "email@example.com", LocalDate.now(), "imagen.jpg", "cliente");

        when(cpuMock.obtenerUsuario(nicknameExistente)).thenReturn(usuarioMock);

        
        boolean resultado = sistema.existeUsuario(nicknameExistente);
        assertTrue(resultado, "Debería devolver true si el usuario existe");

        
        verify(cpuMock, times(1)).obtenerUsuario(nicknameExistente);
    }

    @Test
    public void testExisteUsuario_UsuarioNoExiste() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);
        
        String nicknameNoExistente = "usuarioNoExistente";

        when(cpuMock.obtenerUsuario(nicknameNoExistente)).thenReturn(null);

        
        boolean resultado = sistema.existeUsuario(nicknameNoExistente);
        assertFalse(resultado, "Debería devolver false si el usuario no existe");

        
        verify(cpuMock, times(1)).obtenerUsuario(nicknameNoExistente);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// boolean existeMail //////////////////////////////////////
    @Test
    public void testExisteMail_MailExiste() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);
        
        String emailExistente = "emailExistente@example.com";

        when(cpuMock.ExisteMail(emailExistente)).thenReturn(true);

        
        boolean resultado = sistema.existeMail(emailExistente);
        assertTrue(resultado, "Debería devolver true si el correo existe");

        
        verify(cpuMock, times(1)).ExisteMail(emailExistente);
    }

    @Test
    public void testExisteMail_MailNoExiste() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);
        
        String emailNoExistente = "emailNoExistente@example.com";

        when(cpuMock.ExisteMail(emailNoExistente)).thenReturn(false);

        
        boolean resultado = sistema.existeMail(emailNoExistente);
        assertFalse(resultado, "Debería devolver false si el correo no existe");

        
        verify(cpuMock, times(1)).ExisteMail(emailNoExistente);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// Usuario obtenerUsuarioMail //////////////////////////////////////
    @Test
    public void testObtenerUsuarioMail_UsuarioExiste() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String emailExistente = "usuario@example.com";
        Usuario usuarioSimulado = new Usuario();
        usuarioSimulado.setEmail(emailExistente);
        usuarioSimulado.setNombre("Usuario Ejemplo");

        when(cpuMock.obtenerUsuarioMail(emailExistente)).thenReturn(usuarioSimulado);

        
        Usuario resultado = sistema.obtenerUsuarioMail(emailExistente);
        assertNotNull(resultado, "Debería devolver un usuario si el correo existe");
        assertEquals(emailExistente, resultado.getEmail(), "El email del usuario debería coincidir");
        assertEquals("Usuario Ejemplo", resultado.getNombre(), "El nombre del usuario debería coincidir");

        
        verify(cpuMock, times(1)).obtenerUsuarioMail(emailExistente);
    }

    @Test
    public void testObtenerUsuarioMail_UsuarioNoExiste() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String emailNoExistente = "noexiste@example.com";
        when(cpuMock.obtenerUsuarioMail(emailNoExistente)).thenReturn(null);

        
        Usuario resultado = sistema.obtenerUsuarioMail(emailNoExistente);
        assertNull(resultado, "Debería devolver null si el correo no existe");

        
        verify(cpuMock, times(1)).obtenerUsuarioMail(emailNoExistente);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// Cliente obtenerCliente //////////////////////////////////////
    
    @Test
    public void testObtenerCliente_ClienteExiste() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String emailExistente = "usuario@example.com";
        Cliente usuarioSimulado = new Cliente();
        usuarioSimulado.setEmail(emailExistente);
        usuarioSimulado.setNombre("Cliente Ejemplo");

        when(cpuMock.obtenerCliente(emailExistente)).thenReturn(usuarioSimulado);

        
        Cliente resultado = sistema.ObtenerCliente(emailExistente);
        assertNotNull(resultado, "Debería devolver un usuario si el correo existe");
        assertEquals(emailExistente, resultado.getEmail(), "El email del usuario debería coincidir");
        assertEquals("Cliente Ejemplo", resultado.getNombre(), "El nombre del usuario debería coincidir");

        
        verify(cpuMock, times(1)).obtenerCliente(emailExistente);
    }

    @Test
    public void testObtenerCliente_ClienteNoExiste() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String emailNoExistente = "noexiste@example.com";
        when(cpuMock.obtenerCliente(emailNoExistente)).thenReturn(null);

        
        Cliente resultado = sistema.ObtenerCliente(emailNoExistente);
        assertNull(resultado, "Debería devolver null si el correo no existe");

        
        verify(cpuMock, times(1)).obtenerCliente(emailNoExistente);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// Artista obtenerArtista //////////////////////////////////////
    @Test
    public void testObtenerArtista_ArtistaExiste() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String emailExistente = "usuario@example.com";
        Artista usuarioSimulado = new Artista();
        usuarioSimulado.setEmail(emailExistente);
        usuarioSimulado.setNombre("Artista Ejemplo");

        when(cpuMock.obtenerArtista(emailExistente)).thenReturn(usuarioSimulado);

        
        Artista resultado = sistema.ObtenerArtista(emailExistente);
        assertNotNull(resultado, "Debería devolver un usuario si el correo existe");
        assertEquals(emailExistente, resultado.getEmail(), "El email del usuario debería coincidir");
        assertEquals("Artista Ejemplo", resultado.getNombre(), "El nombre del usuario debería coincidir");

        
        verify(cpuMock, times(1)).obtenerArtista(emailExistente);
    }

    @Test
    public void testObtenerArtista_ArtistaNoExiste() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String emailNoExistente = "noexiste@example.com";
        when(cpuMock.obtenerArtista(emailNoExistente)).thenReturn(null);

        
        Artista resultado = sistema.ObtenerArtista(emailNoExistente);
        assertNull(resultado, "Debería devolver null si el correo no existe");

        
        verify(cpuMock, times(1)).obtenerArtista(emailNoExistente);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// List<DataUsuario> getSeguidores (DataUsuario ALLT)//////////////////////////////////////
    @Test
    public void testGetSeguidores_ArtistaTieneSeguidores() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String nicknameArtista = "artista1";
        Artista artistaSeguido = new Artista();
        artistaSeguido.setNickname(nicknameArtista);

        Cliente clienteSeguidor = new Cliente();
        clienteSeguidor.setNickname("cliente1");
        clienteSeguidor.setNombre("Juan");
        clienteSeguidor.setApellido("Pérez");
        clienteSeguidor.setEmail("juan.perez@example.com");
        clienteSeguidor.setFechaNac(LocalDate.of(1990, 1, 1));
        clienteSeguidor.setImagenPerfil("imagen1.jpg");

        
        ArrayList<Usuario> usuariosSiguiendo = new ArrayList<>();
        usuariosSiguiendo.add(artistaSeguido);
        clienteSeguidor.setUsuariosSiguiendo(usuariosSiguiendo);

        
        when(cpuMock.obtenerTodosLosUsuarios()).thenReturn(Arrays.asList(clienteSeguidor));

        
        List<DataUsuario> seguidores = sistema.getSeguidores(nicknameArtista);
        assertEquals(1, seguidores.size(), "Debería haber un seguidor");
        DataUsuario seguidor = seguidores.get(0);
        assertEquals("cliente1", seguidor.getNick(), "El nickname del seguidor debería ser 'cliente1'");
        assertEquals("Juan", seguidor.getNombre(), "El nombre del seguidor debería ser 'Juan'");
        assertEquals("Pérez", seguidor.getApellido(), "El apellido del seguidor debería ser 'Pérez'");
        assertEquals("juan.perez@example.com", seguidor.getCorreo(), "El email del seguidor debería ser 'juan.perez@example.com'");
        assertEquals(LocalDate.of(1990, 1, 1), seguidor.getFechaNac(), "La fecha de nacimiento del seguidor debería ser '1990-01-01'");
        assertEquals("imagen1.jpg", seguidor.getImagen(), "La imagen de perfil debería ser 'imagen1.jpg'");
        seguidor.getTipo();
        seguidor.getBiografia();
        seguidor.getSitioWeb();
        
        
        
        DataUsuario dataUsuario1 = new DataUsuario(
            "cliente1", "Juan", "Pérez", "juan.perez@example.com", 
            LocalDate.of(1990, 1, 1), "imagen1.jpg", 
            "Biografía de Juan", "www.juanperez.com", "cliente"
        );
        assertNotNull(dataUsuario1, "dataUsuario1 no debería ser nulo");

        
        DataUsuario dataUsuario2 = new DataUsuario(
            "cliente1", "Juan", "Pérez", "juan.perez@example.com", 
            LocalDate.of(1990, 1, 1), "imagen1.jpg", "cliente"
        );
        assertNotNull(dataUsuario2, "dataUsuario2 no debería ser nulo");

        
        DataUsuario dataUsuario3 = new DataUsuario("cliente1", "Juan");
        assertNotNull(dataUsuario3, "dataUsuario3 no debería ser nulo");
        
        clienteSeguidor.getTipo();
        clienteSeguidor.getPassword();
        clienteSeguidor.setTipo("cliente");
        clienteSeguidor.setPassword("Password");
        
        Album album = new Album();
        clienteSeguidor.agregarAlbumFavorito(album);
        Tema tema = new Tema();
        clienteSeguidor.agregarTemaFavorito(tema);
        clienteSeguidor.getAlbumsFavoritos();
        
        
        
        artistaSeguido.getAlbumes();
        artistaSeguido.getAlbumesMap();
        
        
        
        Genero gen = new Genero();
        Genero gen2 = new Genero(gen);
        assertNotNull(gen2);
        assertNotNull(gen);
        Album albumGenerico = sistema.getAlbumGenerico();

        
        Album nuevoAlbum = new Album(albumGenerico);
        nuevoAlbum.setArtista(artistaSeguido);
        
        gen.getAlbumes();
        gen.getAlbumes2();
        gen.addAlbum(nuevoAlbum);
        gen.setNombre("asd");
        gen.setPadre("asdasd");
        
        
        
        String nombreT = "Tema TestC";
        String duracion = "03:45";
        Integer posicion = 1;
        String direccionWeb = "httpss";
        String archivo = "tema_test.mp3";

        
        Tema tema3 = new Tema(nombreT, duracion, posicion, direccionWeb, archivo);
        Tema t = new Tema(tema3);
        t.getDataTema();
        t.setAlbum(nuevoAlbum);
        
        
        
        
        nuevoAlbum.getDataAlbum();
        nuevoAlbum.addTema(tema3);
        nuevoAlbum.devolverData();
        nuevoAlbum.getTema("asd");
        nuevoAlbum.copia(nuevoAlbum);
        
        nuevoAlbum.altaTema("asd1", "asd2", 2, "asd3", "asd4");
        
        artistaSeguido.getAlbum("asd");
        artistaSeguido.setBiografia("sd");
        artistaSeguido.setSitioWeb("asd");
        artistaSeguido.addAlbum(nuevoAlbum);
        artistaSeguido.altaTema("asd","asd", "asd", 2, "asd", "asd");
        
        
    }
    
    @Test
    public void testGetSeguidores_UsuarioNoEsCliente() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String nicknameArtista = "artista1";
        Artista artistaSeguido = new Artista();
        artistaSeguido.setNickname(nicknameArtista);

        
        Artista artistaNoSeguidor = new Artista();
        artistaNoSeguidor.setNickname("artista2");
        artistaNoSeguidor.setNombre("Maria");
        artistaNoSeguidor.setApellido("Gomez");
        artistaNoSeguidor.setEmail("maria.gomez@example.com");
        artistaNoSeguidor.setFechaNac(LocalDate.of(1985, 5, 10));
        artistaNoSeguidor.setImagenPerfil("imagen2.jpg");

        
        when(cpuMock.obtenerTodosLosUsuarios()).thenReturn(Arrays.asList(artistaNoSeguidor));

        
        List<DataUsuario> seguidores = sistema.getSeguidores(nicknameArtista);
        assertEquals(0, seguidores.size(), "No debería haber ningún seguidor");
    }

    @Test
    public void testGetSeguidores_ArtistaNoTieneSeguidores() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String nicknameArtista = "artista1";
        Artista artistaSeguido = new Artista();
        artistaSeguido.setNickname(nicknameArtista);

        Cliente clienteSeguidor = new Cliente();
        clienteSeguidor.setNickname("cliente1");

        
        clienteSeguidor.setUsuariosSiguiendo(new ArrayList<>());

        
        when(cpuMock.obtenerTodosLosUsuarios()).thenReturn(Arrays.asList(clienteSeguidor));

        
        List<DataUsuario> seguidores = sistema.getSeguidores(nicknameArtista);
        assertTrue(seguidores.isEmpty(), "La lista de seguidores debería estar vacía");
    }

    @Test
    public void testGetSeguidores_ArtistaNoExiste() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String nicknameArtista = "artistaNoExistente";

        Cliente clienteSeguidor = new Cliente();
        clienteSeguidor.setNickname("cliente1");

        
        Artista artistaDiferente = new Artista();
        artistaDiferente.setNickname("artistaDiferente");
        ArrayList<Usuario> usuariosSiguiendo = new ArrayList<>();
        usuariosSiguiendo.add(artistaDiferente);
        clienteSeguidor.setUsuariosSiguiendo(usuariosSiguiendo);

        
        when(cpuMock.obtenerTodosLosUsuarios()).thenReturn(Arrays.asList(clienteSeguidor));

        
        List<DataUsuario> seguidores = sistema.getSeguidores(nicknameArtista);
        assertTrue(seguidores.isEmpty(), "La lista de seguidores debería estar vacía si el artista no existe");
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// Map<String, ArrayList<DataUsuario>> UsuarioSeguidos //////////////////////////////////////
    @Test
    public void testUsuarioSeguidos_ClienteSigueClientesYArtistas() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String cliente_seleccionado = "cliente1";

        
        Cliente clienteElegido = new Cliente();
        clienteElegido.setNickname(cliente_seleccionado);

        
        Cliente clienteSeguido = new Cliente();
        clienteSeguido.setNickname("clienteSeguido");
        clienteSeguido.setNombre("Juan");
        clienteSeguido.setApellido("Pérez");
        clienteSeguido.setEmail("juan.perez@example.com");
        clienteSeguido.setFechaNac(LocalDate.of(1990, 1, 1));
        clienteSeguido.setImagenPerfil("imagen1.jpg");

        Artista artistaSeguido = new Artista();
        artistaSeguido.setNickname("artistaSeguido");
        artistaSeguido.setNombre("Maria");
        artistaSeguido.setApellido("Gomez");
        artistaSeguido.setEmail("maria.gomez@example.com");
        artistaSeguido.setFechaNac(LocalDate.of(1985, 5, 10));
        artistaSeguido.setImagenPerfil("imagen2.jpg");

        
        ArrayList<Usuario> usuariosSiguiendo = new ArrayList<>();
        usuariosSiguiendo.add(clienteSeguido);
        usuariosSiguiendo.add(artistaSeguido);
        clienteElegido.setUsuariosSiguiendo(usuariosSiguiendo);

        
        when(cpuMock.obtenerCliente(cliente_seleccionado)).thenReturn(clienteElegido);

        
        Map<String, ArrayList<DataUsuario>> resultado = sistema.UsuarioSeguidos(cliente_seleccionado);

        
        assertNotNull(resultado, "El resultado no debe ser nulo");
        
        
        ArrayList<DataUsuario> clientesSeguidos = resultado.get("clientes");
        assertEquals(1, clientesSeguidos.size(), "Debe haber 1 cliente seguido");
        DataUsuario clienteData = clientesSeguidos.get(0);
        assertEquals("clienteSeguido", clienteData.getNick(), "El nickname del cliente seguido debe ser 'clienteSeguido'");
        assertEquals("Juan", clienteData.getNombre(), "El nombre del cliente seguido debe ser 'Juan'");

        
        ArrayList<DataUsuario> artistasSeguidos = resultado.get("artistas");
        assertEquals(1, artistasSeguidos.size(), "Debe haber 1 artista seguido");
        DataUsuario artistaData = artistasSeguidos.get(0);
        assertEquals("artistaSeguido", artistaData.getNick(), "El nickname del artista seguido debe ser 'artistaSeguido'");
        assertEquals("Maria", artistaData.getNombre(), "El nombre del artista seguido debe ser 'Maria'");
    }
    
    @Test
    public void testUsuarioSeguidos_ClienteSigueUsuarioDesconocido() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String cliente_seleccionado = "cliente3";

        
        Cliente clienteElegido = new Cliente();
        clienteElegido.setNickname(cliente_seleccionado);

        
        Usuario usuarioDesconocido = mock(Usuario.class);
        when(usuarioDesconocido.getNickname()).thenReturn("usuarioDesconocido");
        when(usuarioDesconocido.getNombre()).thenReturn("Desconocido");
        when(usuarioDesconocido.getApellido()).thenReturn("Apellido");
        when(usuarioDesconocido.getEmail()).thenReturn("desconocido@example.com");
        when(usuarioDesconocido.getFechaNac()).thenReturn(LocalDate.of(2000, 1, 1));
        when(usuarioDesconocido.getImagenPerfil()).thenReturn("imagen4.jpg");

        
        ArrayList<Usuario> usuariosSiguiendo = new ArrayList<>();
        usuariosSiguiendo.add(usuarioDesconocido);
        clienteElegido.setUsuariosSiguiendo(usuariosSiguiendo);

        
        when(cpuMock.obtenerCliente(cliente_seleccionado)).thenReturn(clienteElegido);

        
        Map<String, ArrayList<DataUsuario>> resultado = sistema.UsuarioSeguidos(cliente_seleccionado);

        
        assertNotNull(resultado, "El resultado no debe ser nulo");

        
        ArrayList<DataUsuario> clientesSeguidos = resultado.get("clientes");
        assertEquals(0, clientesSeguidos.size(), "No debe haber clientes seguidos");

        
        ArrayList<DataUsuario> artistasSeguidos = resultado.get("artistas");
        assertEquals(0, artistasSeguidos.size(), "No debe haber artistas seguidos");
    }
    
    @Test
    public void testUsuarioSeguidos_ClienteNoSigueNadie() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String cliente_seleccionado = "cliente1";

        
        Cliente clienteElegido = new Cliente();
        clienteElegido.setNickname(cliente_seleccionado);

        
        clienteElegido.setUsuariosSiguiendo(new ArrayList<>());

        
        when(cpuMock.obtenerCliente(cliente_seleccionado)).thenReturn(clienteElegido);

        
        Map<String, ArrayList<DataUsuario>> resultado = sistema.UsuarioSeguidos(cliente_seleccionado);

        
        assertNotNull(resultado, "El resultado no debe ser nulo");

        
        ArrayList<DataUsuario> clientesSeguidos = resultado.get("clientes");
        ArrayList<DataUsuario> artistasSeguidos = resultado.get("artistas");

        assertTrue(clientesSeguidos.isEmpty(), "La lista de clientes seguidos debe estar vacía");
        assertTrue(artistasSeguidos.isEmpty(), "La lista de artistas seguidos debe estar vacía");
    }

    @Test
    public void testUsuarioSeguidos_ClienteSoloSigueArtistas() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String cliente_seleccionado = "cliente1";

        
        Cliente clienteElegido = new Cliente();
        clienteElegido.setNickname(cliente_seleccionado);

        
        Artista artistaSeguido = new Artista();
        artistaSeguido.setNickname("artistaSeguido");
        artistaSeguido.setNombre("Maria");
        artistaSeguido.setApellido("Gomez");
        artistaSeguido.setEmail("maria.gomez@example.com");
        artistaSeguido.setFechaNac(LocalDate.of(1985, 5, 10));
        artistaSeguido.setImagenPerfil("imagen2.jpg");

        
        ArrayList<Usuario> usuariosSiguiendo = new ArrayList<>();
        usuariosSiguiendo.add(artistaSeguido);
        clienteElegido.setUsuariosSiguiendo(usuariosSiguiendo);

        
        when(cpuMock.obtenerCliente(cliente_seleccionado)).thenReturn(clienteElegido);

        
        Map<String, ArrayList<DataUsuario>> resultado = sistema.UsuarioSeguidos(cliente_seleccionado);

        
        assertNotNull(resultado, "El resultado no debe ser nulo");

        
        ArrayList<DataUsuario> clientesSeguidos = resultado.get("clientes");
        assertTrue(clientesSeguidos.isEmpty(), "La lista de clientes seguidos debe estar vacía");

        
        ArrayList<DataUsuario> artistasSeguidos = resultado.get("artistas");
        assertEquals(1, artistasSeguidos.size(), "Debe haber 1 artista seguido");
        DataUsuario artistaData = artistasSeguidos.get(0);
        assertEquals("artistaSeguido", artistaData.getNick(), "El nickname del artista seguido debe ser 'artistaSeguido'");
        assertEquals("Maria", artistaData.getNombre(), "El nombre del artista seguido debe ser 'Maria'");
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// DataAlbum[] getAlbumsByArtista //////////////////////////////////////
    @Test
    public void testGetAlbumsByArtista_AlbumsExist() throws AlbumNoExisteException {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String artistaNombre = "artista1";

        
        Album album1 = mock(Album.class);
        Album album2 = mock(Album.class);
        
        
        Collection<DataTema> temasVacios = new ArrayList<>();
        Collection<String> generosVacios = new ArrayList<>();

        
        DataAlbum dataAlbum1 = new DataAlbum("Album 1", 2022, "imagen1.jpg",temasVacios,generosVacios);
        DataAlbum dataAlbum2 = new DataAlbum("Album 2", 2021, "imagen2.jpg",temasVacios,generosVacios);
        
        when(album1.devolverData()).thenReturn(dataAlbum1);
        when(album2.devolverData()).thenReturn(dataAlbum2);

        
        when(cpuMock.obtenerAlbumPorArtista(artistaNombre)).thenReturn(Arrays.asList(album1, album2));

        
        DataAlbum[] resultado = sistema.getAlbumsByArtista(artistaNombre);

        
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertEquals(2, resultado.length, "Debe haber 2 álbumes");

        
        assertEquals("Album 1", resultado[0].getNombre(), "El primer álbum debe ser 'Album 1'");
        assertEquals(2022, resultado[0].getFechaCreacion(), "La fecha de creación del primer álbum debe ser 2022");

        
        assertEquals("Album 2", resultado[1].getNombre(), "El segundo álbum debe ser 'Album 2'");
        assertEquals(2021, resultado[1].getFechaCreacion(), "La fecha de creación del segundo álbum debe ser 2021");

        
        verify(album1, times(1)).devolverData();
        verify(album2, times(1)).devolverData();
    }

    @Test
    public void testGetAlbumsByArtista_NoAlbums() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String artistaNombre = "artista1";

        
        when(cpuMock.obtenerAlbumPorArtista(artistaNombre)).thenReturn(Collections.emptyList());

        
        assertThrows(AlbumNoExisteException.class, () -> {
            sistema.getAlbumsByArtista(artistaNombre);
        }, "Se debe lanzar la excepción AlbumNoExisteException cuando no hay álbumes");
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// Album getAlbum //////////////////////////////////////
    @Test
    public void testGetAlbum_AlbumExists() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String nombreAlbum = "Album Test";

        
        Album mockAlbum = mock(Album.class);
        
        
        when(cpuMock.obtenerAlbumPorNombre(nombreAlbum)).thenReturn(mockAlbum);

        
        Album resultado = sistema.getAlbum(nombreAlbum);

        
        assertNotNull(resultado, "El álbum no debe ser nulo");

        
        verify(cpuMock, times(1)).obtenerAlbumPorNombre(nombreAlbum);

        
        assertEquals(mockAlbum, resultado, "El álbum retornado debe ser el esperado");
    }

    @Test
    public void testGetAlbum_AlbumDoesNotExist() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String nombreAlbum = "Album Inexistente";

        
        when(cpuMock.obtenerAlbumPorNombre(nombreAlbum)).thenReturn(null);

        
        Album resultado = sistema.getAlbum(nombreAlbum);

        
        assertNull(resultado, "El resultado debe ser nulo si no existe el álbum");

        
        verify(cpuMock, times(1)).obtenerAlbumPorNombre(nombreAlbum);
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// Album getTema //////////////////////////////////////
    @Test
    public void testGetTema_TemaExists() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String nombreTema = "Tema Test";

        
        Tema mockTema = mock(Tema.class);
        
        
        when(cpuMock.obtenerTemaPorNombre(nombreTema)).thenReturn(mockTema);

        
        Tema resultado = sistema.getTema(nombreTema);

        
        assertNotNull(resultado, "El tema no debe ser nulo");

        
        verify(cpuMock, times(1)).obtenerTemaPorNombre(nombreTema);

        
        assertEquals(mockTema, resultado, "El tema retornado debe ser el esperado");
    }

    @Test
    public void testGetTema_TemaDoesNotExist() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String nombreTema = "Tema Inexistente";

        
        when(cpuMock.obtenerTemaPorNombre(nombreTema)).thenReturn(null);

        
        Tema resultado = sistema.getTema(nombreTema);

        
        assertNull(resultado, "El resultado debe ser nulo si no existe el tema");

        
        verify(cpuMock, times(1)).obtenerTemaPorNombre(nombreTema);
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// void AgregarListaFavoritaCliente //////////////////////////////////////
//    @Test
//    public void testAgregarListaFavoritaCliente() {
//        // Crear el mock de ControladorPersistencia (cpu)
//        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
//
//        // Crear una instancia de Sistema con el mock
//        Sistema sistema = new Sistema(cpuMock);
//
//        // Datos de prueba
//        String nicknameCliente = "clienteTest";
//        ListaReproduccion lista = mock(ListaReproduccion.class); // Crear un mock de ListaReproduccion
//
//        // Crear un mock del cliente
//        Cliente clienteMock = mock(Cliente.class);
//
//        // Simular que se devuelve un cliente con el nickname dado
//        when(cpuMock.obtenerCliente(nicknameCliente)).thenReturn(clienteMock);
//
//        // Ejecutar el método
//        sistema.AgregarListaFavoritaCliente(nicknameCliente, lista);
//
//        // Verificar que se llamó a obtenerCliente con el nickname correcto
//        verify(cpuMock, times(1)).obtenerCliente(nicknameCliente);
//
//        // Verificar que se llamó a agregarListaFavorita en el cliente
//        verify(clienteMock, times(1)).agregarListaFavorita(lista);
//
//        // Verificar que se llamó a actualizarUsuario en cpu
//        verify(cpuMock, times(1)).actualizarUsuario(clienteMock);
//    }
    
    @Test
    public void testAgregarListaFavoritaCliente() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String nicknameCliente = "clienteTest";
        ListaReproduccion lista = mock(ListaReproduccion.class); 
        ListaReproduccion listaDuplicada = mock(ListaReproduccion.class); 

        
        Cliente cliente = new Cliente();

        
        when(cpuMock.obtenerCliente(nicknameCliente)).thenReturn(cliente);

        
        sistema.AgregarListaFavoritaCliente(nicknameCliente, lista);

        
        assertTrue(cliente.getListasFavoritas().contains(lista), "La lista debería ser agregada cuando no está presente.");

        
        verify(cpuMock, times(1)).obtenerCliente(nicknameCliente);
        verify(cpuMock, times(1)).actualizarUsuario(cliente);

        
        int listasFavoritasSizeBefore = cliente.getListasFavoritas().size();
        sistema.AgregarListaFavoritaCliente(nicknameCliente, lista); 

        
        assertEquals(listasFavoritasSizeBefore, cliente.getListasFavoritas().size(), "La lista no debería agregarse si ya está en la colección.");

        
        sistema.AgregarListaFavoritaCliente(nicknameCliente, listaDuplicada);

        
        assertTrue(cliente.getListasFavoritas().contains(listaDuplicada), "La nueva lista debería ser agregada.");
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// void AgregarAlbumFavoritaCliente //////////////////////////////////////
    @Test
    public void testAgregarAlbumFavoritoCliente() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String nicknameCliente = "clienteTest";
        Album albumMock = mock(Album.class); 

        
        Cliente clienteMock = mock(Cliente.class);

        
        when(cpuMock.obtenerCliente(nicknameCliente)).thenReturn(clienteMock);

        
        sistema.AgregarAlbumFavoritoCliente(nicknameCliente, albumMock);

        
        verify(cpuMock, times(1)).obtenerCliente(nicknameCliente);

        
        verify(clienteMock, times(1)).agregarAlbumFavorito(albumMock);

        
        verify(cpuMock, times(1)).actualizarUsuario(clienteMock);
    }

    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// void AgregarTemaFavoritaCliente //////////////////////////////////////
    @Test
    public void testAgregarTemaFavoritoCliente() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String nicknameCliente = "clienteTest";
        Tema temaMock = mock(Tema.class); 

        
        Cliente clienteMock = mock(Cliente.class);

        
        when(cpuMock.obtenerCliente(nicknameCliente)).thenReturn(clienteMock);

        
        sistema.AgregarTemaFavoritoCliente(nicknameCliente, temaMock);

        
        verify(cpuMock, times(1)).obtenerCliente(nicknameCliente);

        
        verify(clienteMock, times(1)).agregarTemaFavorito(temaMock);

        
        verify(cpuMock, times(1)).actualizarUsuario(clienteMock);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// DataAlbum getAlbumEspecificoGen2 ALLT //////////////////////////////////////
    @Test
    public void testGetAlbumEspecificoGen2() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String nombreAlbum = "albumTest";
        String nombreGenero = "generoTest";

        
        Album albumMock = mock(Album.class);

        
        when(albumMock.getNombre()).thenReturn(nombreAlbum);
        when(albumMock.getFechaCreacion()).thenReturn(2023);
        when(albumMock.getImagenAlbum()).thenReturn("imagenTest.jpg");

        
        Genero generoMock = mock(Genero.class);
        when(generoMock.getNombre()).thenReturn(nombreGenero);

        
        List<Genero> generos = new ArrayList<>();
        generos.add(generoMock);
        when(albumMock.getGeneros()).thenReturn(generos);

        
        Tema temaMock = mock(Tema.class);
        when(temaMock.getNombre()).thenReturn("temaTest");
        when(temaMock.getDuracion()).thenReturn("03:00");
        when(temaMock.getPosicion()).thenReturn(1);
        when(temaMock.getArchivo()).thenReturn("archivoTest.mp3");
        when(temaMock.getDireccionWeb()).thenReturn("http://temaTest.com");

        
        List<Tema> temas = new ArrayList<>();
        temas.add(temaMock);
        when(albumMock.getTemas()).thenReturn(temas);

        
        when(cpuMock.getAlbumGen(nombreGenero, nombreAlbum)).thenReturn(albumMock);

        
        DataAlbum result = sistema.getAlbumEspecificoGen2(nombreAlbum, nombreGenero);

        
        assertEquals(nombreAlbum, result.getNombre());
        assertEquals(2023, result.getFechaCreacion());
        assertEquals("imagenTest.jpg", result.getImagenAlbum());

        List<String> generosResult = new ArrayList<>(result.getGeneros());
        assertEquals(1, generosResult.size());
        assertEquals(nombreGenero, generosResult.get(0));

        Collection<DataTema> temasResult = result.getTemas();
        assertEquals(1, temasResult.size());

        
        DataTema temaResult = temasResult.iterator().next();
        assertEquals("temaTest", temaResult.getNombre());
        assertEquals("03:00", temaResult.getDuracion());
        assertEquals(1, temaResult.getPosicion());
        assertEquals("archivoTest.mp3", temaResult.getArchivo());
        assertEquals("http://temaTest.com", temaResult.getDireccionWeb());
        
        //
        result.hashCode();
        result.getId();
        result.setId(12345L);
        //
        temaResult.getId();
        DataTema testcla = new DataTema();
        assertNotNull(testcla);
        

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// DataAlbum getAlbumEspecificoArt2 //////////////////////////////////////
    @Test
    public void testGetAlbumEspecificoArt2() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String nombreAlbum = "albumTest";
        String nombreArtista = "artistaTest";

        
        Album albumMock = mock(Album.class);

        
        when(albumMock.getNombre()).thenReturn(nombreAlbum);
        when(albumMock.getFechaCreacion()).thenReturn(2023);
        when(albumMock.getImagenAlbum()).thenReturn("imagenTest.jpg");

        
        Genero generoMock = mock(Genero.class);
        when(generoMock.getNombre()).thenReturn("generoTest");

        
        List<Genero> generos = new ArrayList<>();
        generos.add(generoMock);
        when(albumMock.getGeneros()).thenReturn(generos);

        
        Tema temaMock = mock(Tema.class);
        when(temaMock.getNombre()).thenReturn("temaTest");
        when(temaMock.getDuracion()).thenReturn("03:00");
        when(temaMock.getPosicion()).thenReturn(1);
        when(temaMock.getArchivo()).thenReturn("archivoTest.mp3");
        when(temaMock.getDireccionWeb()).thenReturn("http://temaTest.com");

        
        List<Tema> temas = new ArrayList<>();
        temas.add(temaMock);
        when(albumMock.getTemas()).thenReturn(temas);

        
        when(cpuMock.getAlbumArt(nombreArtista, nombreAlbum)).thenReturn(albumMock);

        
        DataAlbum result = sistema.getAlbumEspecificoArt2(nombreAlbum, nombreArtista);

        
        assertEquals(nombreAlbum, result.getNombre());
        assertEquals(2023, result.getFechaCreacion());
        assertEquals("imagenTest.jpg", result.getImagenAlbum());

        List<String> generosResult = new ArrayList<>(result.getGeneros());
        assertEquals(1, generosResult.size());
        assertEquals("generoTest", generosResult.get(0));

        
        Collection<DataTema> temasResult = result.getTemas();
        assertEquals(1, temasResult.size());

        
        DataTema temaResult = temasResult.iterator().next();
        assertEquals("temaTest", temaResult.getNombre());
        assertEquals("03:00", temaResult.getDuracion());
        assertEquals(1, temaResult.getPosicion());
        assertEquals("http://temaTest.com", temaResult.getDireccionWeb());
        assertEquals("archivoTest.mp3", temaResult.getArchivo());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //@i2
    ////////////////////////////////////// void addGeneroAlbum //////////////////////////////////////
    @Test
    public void testAddGeneroAlbum() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String nombreGenero = "generoTest";

        
        Genero generoMock = mock(Genero.class);
        when(generoMock.getNombre()).thenReturn(nombreGenero);

        
        List<Genero> generosMockList = new ArrayList<>();
        generosMockList.add(generoMock);

        
        when(cpuMock.getGeneros()).thenReturn(generosMockList);
        when(cpuMock.obtenerGenero(nombreGenero)).thenReturn(generoMock);

        
        sistema.addGeneroAlbum(nombreGenero);

        
        assertTrue(sistema.getAlbumGenerico().getGeneros().contains(generoMock));
    }
    
    @Test
    public void testAddGeneroAlbum_GeneroNoCoincide() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String nombreGenero = "generoTest";
        String nombreGeneroDiferente = "generoNoCoincide";

        
        Genero generoMock = mock(Genero.class);
        when(generoMock.getNombre()).thenReturn(nombreGeneroDiferente); 

        
        List<Genero> generosMockList = new ArrayList<>();
        generosMockList.add(generoMock);

        
        when(cpuMock.getGeneros()).thenReturn(generosMockList);
        when(cpuMock.obtenerGenero(nombreGenero)).thenReturn(generoMock); 

        
        sistema.addGeneroAlbum(nombreGenero);

        
        assertFalse(sistema.getAlbumGenerico().getGeneros().contains(generoMock), "El género no debería haberse agregado ya que el nombre no coincide.");
    }

    @Test
    public void testAddGeneroAlbumGeneroNoExistente() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String nombreGenero = "generoTest";

        
        when(cpuMock.obtenerGenero(nombreGenero)).thenReturn(null);

        
        sistema.addGeneroAlbum(nombreGenero);

        
        assertTrue(sistema.getAlbumGenerico().getGeneros().isEmpty());
    }

    @Test
    public void testAddGeneroAlbumYaExistente() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String nombreGenero = "generoTest";

        
        Genero generoMock = mock(Genero.class);
        when(generoMock.getNombre()).thenReturn(nombreGenero);

        
        List<Genero> generosMockList = new ArrayList<>();
        generosMockList.add(generoMock);

        
        when(cpuMock.getGeneros()).thenReturn(generosMockList);
        when(cpuMock.obtenerGenero(nombreGenero)).thenReturn(generoMock);

        
        sistema.getAlbumGenerico().addGenero(generoMock);

        
        sistema.addGeneroAlbum(nombreGenero);

        
        assertEquals(1, sistema.getAlbumGenerico().getGeneros().size());
        verify(cpuMock, never()).actualizarAlbum(any(Album.class)); 
    }

    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// void altaGenero //////////////////////////////////////
    @Test
    public void testAltaGenero() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        
        Sistema sistemaSpy = spy(new Sistema(cpuMock));

        
        String nombreGenero = "Rock";

        
        sistemaSpy.altaGenero(nombreGenero);

        
        verify(sistemaSpy).addGenero(argThat(genero -> genero.getNombre().equals(nombreGenero)));
    }
    
   @Test
    public void testAddGenero() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Genero generoMock = mock(Genero.class);

        when(generoMock.getNombre()).thenReturn("Rock");

        Sistema sistema = new Sistema(cpuMock);

        sistema.addGenero(generoMock);

        
        Map<String, Genero> generosMap = sistema.getGenerosMap();
        assertTrue(generosMap.containsKey("Rock"));
        assertEquals(generoMock, generosMap.get("Rock"));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// HashSet<String> getGeneros //////////////////////////////////////
    @Test
    public void testGetGeneros() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        List<Genero> generosMock = Arrays.asList(
            new Genero("Rock"), 
            new Genero("Pop")
        );

        
        when(cpuMock.getGeneros()).thenReturn(generosMock);

        
        HashSet<String> resultGeneros = sistema.getGeneros();

        
        assertTrue(resultGeneros.contains("Rock"));
        assertTrue(resultGeneros.contains("Pop"));
        assertEquals(2, resultGeneros.size()); 
    }

    @Test
    public void testGetGenerosEmpty() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        when(cpuMock.getGeneros()).thenReturn(Collections.emptyList());

        
        HashSet<String> resultGeneros = sistema.getGeneros();

        
        assertTrue(resultGeneros.isEmpty());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// void altaAlbum() //////////////////////////////////////
    @Test
    public void testAltaAlbum() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        
        
        Sistema sistema = new Sistema(cpuMock);
        
        
        Album albumGenerico = sistema.getAlbumGenerico(); 

        
        Album nuevoAlbum = new Album(albumGenerico);
        
        
        sistema.altaAlbum();
        
        
        ArgumentCaptor<Album> albumCaptor = ArgumentCaptor.forClass(Album.class);
        verify(cpuMock).crearAlbum(albumCaptor.capture());
        
        
        Album albumCapturado = albumCaptor.getValue();
        
        
        assertNotNull(albumCapturado, "El álbum capturado no debe ser nulo.");
        
        
        assertEquals(nuevoAlbum.getNombre(), albumCapturado.getNombre(), "El nombre del álbum no coincide.");
        assertEquals(nuevoAlbum.getFechaCreacion(), albumCapturado.getFechaCreacion(), "La fecha de creación no coincide.");
        assertEquals(nuevoAlbum.getImagenAlbum(), albumCapturado.getImagenAlbum(), "La imagen del álbum no coincide.");
        
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// Album getAlbumGenerico(); //////////////////////////////////////
    @Test
    public void testGetAlbumGenerico() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        Album albumGenerico = sistema.getAlbumGenerico();

        
        assertNotNull(albumGenerico, "El álbum genérico no debe ser nulo.");

        
        assertTrue(albumGenerico instanceof Album, "El objeto retornado debe ser una instancia de Album.");
        
        
        
        assertEquals(0, albumGenerico.getTemas().size(), "El álbum genérico debe estar vacío al inicio.");
        
        
    }
     
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// boolean verificaAlbum //////////////////////////////////////
    @Test
    public void testVerificaAlbum_Existe() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        
        
        Artista artistaMock = new Artista();
        artistaMock.setNombre("Artista Test");
        
        
        Album albumMock = new Album();
        albumMock.setNombre("Album Test");
        albumMock.setArtista(artistaMock); 
        
        
        when(cpuMock.getAlbumArt("Artista Test", "Album Test")).thenReturn(albumMock);
        
        
        Sistema sistema = new Sistema(cpuMock);
        
        
        boolean resultado = sistema.verificaAlbum("Album Test", "Artista Test");
        
        
        assertTrue(resultado, "El álbum debería existir.");
    }


    @Test
    public void testVerificaAlbum_NoExiste() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        
        
        when(cpuMock.getAlbumArt("Artista Test", "Album Test")).thenReturn(null);
        
        
        Sistema sistema = new Sistema(cpuMock);
        
        
        boolean resultado = sistema.verificaAlbum("Album Test", "Artista Test");
        
        
        assertFalse(resultado, "El álbum no debería existir.");
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// void cargarDatosAlbum //////////////////////////////////////
    @Test
    public void testCargarDatosAlbum_ArtistaExiste() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        
        Artista artistaMock = new Artista();
        artistaMock.setNombre("Artista Test");

        
        when(cpuMock.obtenerUsuario("Artista Test")).thenReturn(artistaMock);

        
        String nombreAlbum = "Album Test";
        Integer fechaCreacion = 2024;
        String imagen = "imagen_test.jpg";

        
        sistema.cargarDatosAlbum(nombreAlbum, "Artista Test", fechaCreacion, imagen);

        
        Album albumGenerico = sistema.getAlbumGenerico();

        
        assertEquals(nombreAlbum, albumGenerico.getNombre(), "El nombre del álbum debería coincidir.");
        assertEquals(fechaCreacion, albumGenerico.getFechaCreacion(), "La fecha de creación debería coincidir.");
        assertEquals(imagen, albumGenerico.getImagenAlbum(), "La imagen del álbum debería coincidir.");
        assertEquals(artistaMock, albumGenerico.getArtista(), "El artista del álbum debería coincidir.");
    }

    @Test
    public void testCargarDatosAlbum_ArtistaNoExiste() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        when(cpuMock.obtenerUsuario("Artista No Existe")).thenReturn(null);

        
        String nombreAlbum = "Album Test";
        Integer fechaCreacion = 2024;
        String imagen = "imagen_test.jpg";

        
        Album albumGenericoAntes = sistema.getAlbumGenerico();
        albumGenericoAntes.setNombre(null);
        albumGenericoAntes.setFechaCreacion(null);
        albumGenericoAntes.setImagenAlbum(null);
        albumGenericoAntes.setArtista(null);

        
        sistema.cargarDatosAlbum(nombreAlbum, "Artista No Existe", fechaCreacion, imagen);

        
        Album albumGenerico = sistema.getAlbumGenerico();

        
        assertNull(albumGenerico.getNombre(), "El nombre del álbum debería seguir siendo null.");
        assertNull(albumGenerico.getFechaCreacion(), "La fecha de creación debería seguir siendo null.");
        assertNull(albumGenerico.getImagenAlbum(), "La imagen del álbum debería seguir siendo null.");
        assertNull(albumGenerico.getArtista(), "El artista del álbum debería seguir siendo null.");

        
        verify(cpuMock, never()).crearAlbum(any(Album.class));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// void cargarGenerosSys(); //////////////////////////////////////
    @Test
    public void testCargarGenerosSys() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        List<Genero> listaGenerosMock = new ArrayList<>();
        listaGenerosMock.add(new Genero("Rock"));
        listaGenerosMock.add(new Genero("Pop"));
        listaGenerosMock.add(new Genero("Jazz"));

        
        when(cpuMock.getGeneros()).thenReturn(listaGenerosMock);

        
        sistema.cargarGenerosSys();

        
        Map<String, Genero> generosMap = sistema.getGenerosMap();

        
        assertNotNull(generosMap, "El mapa de géneros no debe ser nulo.");

        
        assertEquals(listaGenerosMock.size(), generosMap.size(), "El tamaño del mapa de géneros no coincide con la lista de géneros.");

        
        for (Genero genero : listaGenerosMock) {
            assertTrue(generosMap.containsKey(genero.getNombre()), "El mapa no contiene el género: " + genero.getNombre());
            assertEquals(genero, generosMap.get(genero.getNombre()), "El género no coincide en el mapa para: " + genero.getNombre());
        }

        
        verify(cpuMock, times(1)).getGeneros();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// void altaTema //////////////////////////////////////
    @Test
    public void testAltaTema() {
        
        Album albumMock = mock(Album.class); 
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        sistema.setAlbumGenerico(albumMock);

        
        String nombreT = "Tema Test";
        String duracion = "03:45";
        Integer posicion = 1;
        String direccionWeb = "https";
        String archivo = "tema_test.mp3";

        
        Tema temaMock = new Tema(nombreT, duracion, posicion, direccionWeb, archivo);
        when(albumMock.altaTema(nombreT, duracion, posicion, direccionWeb, archivo)).thenReturn(temaMock);

        
        sistema.altaTema(nombreT, duracion, posicion, direccionWeb, archivo);

        
        ArgumentCaptor<String> nombreCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> duracionCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> posicionCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> direccionWebCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> archivoCaptor = ArgumentCaptor.forClass(String.class);

        
        verify(albumMock).altaTema(
            nombreCaptor.capture(),
            duracionCaptor.capture(),
            posicionCaptor.capture(),
            direccionWebCaptor.capture(),
            archivoCaptor.capture()
        );

        
        assertEquals(nombreT, nombreCaptor.getValue(), "El nombre del tema no coincide.");
        assertEquals(duracion, duracionCaptor.getValue(), "La duración del tema no coincide.");
        assertEquals(posicion, posicionCaptor.getValue(), "La posición del tema no coincide.");
        assertEquals(direccionWeb, direccionWebCaptor.getValue(), "La dirección web no coincide.");
        assertEquals(archivo, archivoCaptor.getValue(), "El archivo del tema no coincide.");
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// Album getAlbumEspecificoArt //////////////////////////////////////
    @Test
    public void testGetAlbumEspecificoArt() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String nombreAlbum = "Album Test";
        String nombreArtista = "ArtistaTest";
        
        
        String nickname = "ArtistaTest";
        String nombre = "Juan";
        String apellido = "Perez";
        String password = "password123";
        String email = "juan@example.com";
        LocalDate fechaNac = LocalDate.of(1990, 5, 20);
        String imagenPerfil = "imagen_perfil.jpg";
        String biografia = "Biografía del artista";
        String sitioWeb = "https";
        String tipo = "Artista";

        
        Artista artistaMock = new Artista(nickname, nombre, apellido, password, email, fechaNac, imagenPerfil, biografia, sitioWeb, tipo);

        
        Album albumMock = new Album();
        albumMock.setNombre(nombreAlbum);
        albumMock.setArtista(artistaMock);  

        
        when(cpuMock.getAlbumArt(nombreArtista, nombreAlbum)).thenReturn(albumMock);

        
        Album resultado = sistema.getAlbumEspecificoArt(nombreAlbum, nombreArtista);

        
        verify(cpuMock).getAlbumArt(nombreArtista, nombreAlbum);

        
        assertNotNull(resultado, "El álbum no debe ser nulo.");
        assertEquals(nombreAlbum, resultado.getNombre(), "El nombre del álbum no coincide.");
        assertEquals(nombreArtista, resultado.getArtista().getNickname(), "El nombre del artista no coincide.");
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// Album getAlbumEspecificoGen //////////////////////////////////////
    @Test
    public void testGetAlbumEspecificoGen() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String nombreAlbum = "Album Test";
        String nombreGenero = "Rock";

        
        Album albumMock = new Album();
        albumMock.setNombre(nombreAlbum);
        
        
        Genero generoMock = new Genero(nombreGenero);
        albumMock.addGenero(generoMock);  

        
        when(cpuMock.getAlbumGen(nombreGenero, nombreAlbum)).thenReturn(albumMock);

        
        Album resultado = sistema.getAlbumEspecificoGen(nombreAlbum, nombreGenero);

        
        verify(cpuMock).getAlbumGen(nombreGenero, nombreAlbum);

        
        assertNotNull(resultado, "El álbum no debe ser nulo.");
        assertEquals(nombreAlbum, resultado.getNombre(), "El nombre del álbum no coincide.");

        
        assertTrue(
            resultado.getGeneros().stream().anyMatch(g -> g.getNombre().equals(nombreGenero)),
            "El género no fue encontrado en la lista de géneros."
        );
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //@i5
    ////////////////////////////////////// String getPadre //////////////////////////////////////
    @Test
    public void testGetPadre() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        
        
        Sistema sistema = new Sistema(cpuMock);

        
        String nombreGenero = "Rock";
        String nombrePadre = "Música Popular";

        
        Genero generoMock = mock(Genero.class);
        when(generoMock.getPadre()).thenReturn(nombrePadre);

        
        
        when(sistema.getGenero(nombreGenero)).thenReturn(generoMock);

        
        String resultado = sistema.getPadre(nombreGenero);

        
        verify(generoMock).getPadre(); 

        
        assertEquals(nombrePadre, resultado, "El padre del género no coincide.");
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// boolean encontrarGenero //////////////////////////////////////
    @Test
    public void testEncontrarGenero_Existe() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String nombreGenero = "Rock";

        
        when(cpuMock.obtenerGenero(nombreGenero)).thenReturn(new Genero(nombreGenero, null));

        
        boolean resultado = sistema.encontrarGenero(nombreGenero);

        
        assertTrue(resultado, "El género debería ser encontrado.");
    }

    @Test
    public void testEncontrarGenero_NoExiste() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String nombreGenero = "Jazz";

        
        when(cpuMock.obtenerGenero(nombreGenero)).thenReturn(null);

        
        boolean resultado = sistema.encontrarGenero(nombreGenero);

        
        assertFalse(resultado, "El género no debería ser encontrado.");
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// void altaGenero //////////////////////////////////////
    @Test
    public void testAltaGenero_GeneroRepetido() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String nombreGenero = "Pop";
        String padre = "Música";

        
        when(cpuMock.obtenerGenero(nombreGenero)).thenReturn(new Genero(nombreGenero, padre));

        
        GeneroRepetidoException exception = assertThrows(GeneroRepetidoException.class, () -> {
            sistema.altaGenero(nombreGenero, padre);
        });

        
        assertEquals("Ya existe un Genero con ese nombre, por favor corrijalo para continuar.", exception.getMessage());
    }

    @Test
    public void testAltaGenero_NuevoGenero() throws GeneroRepetidoException {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        String nombreGenero = "Hip Hop";
        String padre = "Música";

        
        when(cpuMock.obtenerGenero(nombreGenero)).thenReturn(null);

        
        sistema.altaGenero(nombreGenero, padre);

        
        verify(cpuMock).crearGenero(argThat(genero -> 
            genero.getNombre().equals(nombreGenero) && 
            genero.getPadre().equals(padre)
        ));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// Set<String> listarGeneros(); //////////////////////////////////////
    @Test
    public void testListarGeneros() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        
        
        Sistema sistema = new Sistema(cpuMock);
        
        
        Genero genero1 = new Genero("Rock", null);
        Genero genero2 = new Genero("Pop", null);
        Genero genero3 = new Genero("Jazz", null);
        
        List<Genero> listaGeneros = Arrays.asList(genero1, genero2, genero3);
        
        
        when(cpuMock.getGeneros()).thenReturn(listaGeneros);
        
        
        Set<String> generos = sistema.listarGeneros();
        
        
        assertEquals(3, generos.size(), "El tamaño del conjunto de géneros debe ser 3.");
        assertTrue(generos.contains("Rock"), "El conjunto debe contener 'Rock'.");
        assertTrue(generos.contains("Pop"), "El conjunto debe contener 'Pop'.");
        assertTrue(generos.contains("Jazz"), "El conjunto debe contener 'Jazz'.");
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// Set<String> listarGenerosSinPadre //////////////////////////////////////
    @Test
    public void testListarGenerosSinPadre() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        
        
        Sistema sistema = new Sistema(cpuMock);
        
        
        Genero genero1 = new Genero("Rock", "Genero");
        Genero genero2 = new Genero("Pop", "Subgenero");
        Genero genero3 = new Genero("Jazz", "Genero");
        Genero genero4 = new Genero("Blues", "Genero");
        
        List<Genero> listaGeneros = Arrays.asList(genero1, genero2, genero3, genero4);
        
        
        when(cpuMock.getGeneros()).thenReturn(listaGeneros);
        
        
        Set<String> generosSinPadre = sistema.listarGenerosSinPadre();
        
        
        assertEquals(3, generosSinPadre.size(), "El tamaño del conjunto de géneros sin padre debe ser 3.");
        assertTrue(generosSinPadre.contains("Rock"), "El conjunto debe contener 'Rock'.");
        assertTrue(generosSinPadre.contains("Jazz"), "El conjunto debe contener 'Jazz'.");
        assertTrue(generosSinPadre.contains("Blues"), "El conjunto debe contener 'Blues'.");
        assertFalse(generosSinPadre.contains("Pop"), "El conjunto no debe contener 'Pop'.");
    }
    
    
    
    @Test
    public void testEncontrarLista_CuandoLaListaExiste() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        
        
        Sistema sistema = new Sistema(cpuMock);
        
        
        ListaReproduccion lista1 = new ListaReproduccion("Mis Canciones Favoritas", "imagen1.png");
        ListaReproduccion lista2 = new ListaReproduccion("Rock Clásico", "imagen2.png");
        ListaReproduccion lista3 = new ListaReproduccion("Jazz para Relajarse", "imagen3.png");
        
        List<ListaReproduccion> listaReproducciones = Arrays.asList(lista1, lista2, lista3);
        
        
        when(cpuMock.listaListas()).thenReturn(listaReproducciones);
        
        
        boolean resultado = sistema.encontrarLista("Rock Clásico");
        
        
        assertTrue(resultado, "Se esperaba que la lista 'Rock Clásico' fuera encontrada.");
    }

    @Test
    public void testEncontrarLista_CuandoLaListaNoExiste() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        
        
        Sistema sistema = new Sistema(cpuMock);
        
        
        ListaReproduccion lista1 = new ListaReproduccion("Mis Canciones Favoritas", "imagen1.png");
        ListaReproduccion lista2 = new ListaReproduccion("Rock Clásico", "imagen2.png");
        ListaReproduccion lista3 = new ListaReproduccion("Jazz para Relajarse", "imagen3.png");
        
        List<ListaReproduccion> listaReproducciones = Arrays.asList(lista1, lista2, lista3);
        
        
        when(cpuMock.listaListas()).thenReturn(listaReproducciones);
        
        
        boolean resultado = sistema.encontrarLista("Hip Hop");
        
        
        assertFalse(resultado, "Se esperaba que la lista 'Hip Hop' no fuera encontrada.");
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// void altaListaPart //////////////////////////////////////
    @Test
    public void testAltaListaPart_CuandoLaListaNoExiste() throws ListaRepetidaException {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        
        
        Sistema sistema = new Sistema(cpuMock);
        
        
        DataLista dtl = new DataLista("Lista Favorita", "imagen.png", "cliente123");
        
        
        when(cpuMock.listaListas()).thenReturn(new ArrayList<>());
        
        
        Cliente cliente = new Cliente("cliente123", "Nombre", "Apellido", "password", "email@example.com", 
                                       LocalDate.of(2000, 1, 1), "imagenPerfil.png", "tipo");
        when(cpuMock.obtenerCliente("cliente123")).thenReturn(cliente);

        
        sistema.altaListaPart(dtl);
        
        
        verify(cpuMock).crearListarPar(any(ListaParticular.class));
        verify(cpuMock).obtenerCliente("cliente123");
    }

    @Test
    public void testAltaListaPart_CuandoLaListaYaExiste() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        
        
        Sistema sistema = new Sistema(cpuMock);
        
        
        DataLista dtl = new DataLista("Lista Favorita", "imagen.png", "cliente123");
        
        
        ListaParticular listaExistente = new ListaParticular("Lista Favorita", "imagen.png", "cliente123");
        when(cpuMock.listaListas()).thenReturn(Collections.singletonList(listaExistente));
        
        
        assertThrows(ListaRepetidaException.class, () -> {
            sistema.altaListaPart(dtl);
        });
        
        
        verify(cpuMock, never()).crearListarPar(any(ListaParticular.class));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// void altaListaDef //////////////////////////////////////
    @Test
    public void testAltaListaDef_CuandoLaListaNoExiste() throws ListaRepetidaException {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        
        
        Sistema sistema = new Sistema(cpuMock);
        
        
        DataLista dtl = new DataLista("Lista Defecto", "imagen.png", "Genero");
        
        
        when(cpuMock.listaListas()).thenReturn(new ArrayList<>());
        
        
        sistema.altaListaDef(dtl);
        
        
        verify(cpuMock).crearListaDef(any(ListaPorDefecto.class));
    }

    @Test
    public void testAltaListaDef_CuandoLaListaYaExiste() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        
        
        Sistema sistema = new Sistema(cpuMock);
        
        
        DataLista dtl = new DataLista("Lista Defecto", "imagen.png", "Genero");
        
        
        ListaPorDefecto listaExistente = new ListaPorDefecto("Lista Defecto", "imagen.png", "Genero");
        when(cpuMock.listaListas()).thenReturn(Collections.singletonList(listaExistente));
        
        
        assertThrows(ListaRepetidaException.class, () -> {
            sistema.altaListaDef(dtl);
        });
        
        
        verify(cpuMock, never()).crearListaDef(any(ListaPorDefecto.class));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// Set<String> listarListas //////////////////////////////////////
    @Test
    public void testListarListas() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        
        
        Sistema sistema = new Sistema(cpuMock);
        
        
        ListaReproduccion lista1 = new ListaReproduccion("Lista Favorita", "imagen1.png");
        ListaReproduccion lista2 = new ListaReproduccion("Lista de Éxitos", "imagen2.png");
        
        
        when(cpuMock.listaListas()).thenReturn(Arrays.asList(lista1, lista2));
        
        
        Set<String> resultado = sistema.listarListas();
        
        
        assertEquals(2, resultado.size()); 
        assertTrue(resultado.contains("Lista Favorita"));
        assertTrue(resultado.contains("Lista de Éxitos"));
    }

    @Test
    public void testListarListas_SinListas() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        
        
        Sistema sistema = new Sistema(cpuMock);
        
        
        when(cpuMock.listaListas()).thenReturn(Collections.emptyList());
        
        
        Set<String> resultado = sistema.listarListas();
        
        
        assertTrue(resultado.isEmpty());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// Set<String> listarListasPar //////////////////////////////////////
    @Test
    public void testListarListasPar() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        
        
        Sistema sistema = new Sistema(cpuMock);
        
        
        ListaReproduccion lista1 = new ListaParticular("Lista Favorita", "imagen1.png", "cliente1");
        ListaReproduccion lista2 = new ListaPorDefecto("Lista de Éxitos", "imagen2.png", "Genero1");
        ListaReproduccion lista3 = new ListaParticular("Lista Relax", "imagen3.png", "cliente2");
        
        
        when(cpuMock.listaListas()).thenReturn(Arrays.asList(lista1, lista2, lista3));
        
        
        Set<String> resultado = sistema.listarListasPar();
        
        
        assertEquals(2, resultado.size()); 
        assertTrue(resultado.contains("Lista Favorita"));
        assertTrue(resultado.contains("Lista Relax"));
        assertFalse(resultado.contains("Lista de Éxitos")); 
    }

    @Test
    public void testListarListasPar_SinListasParticulares() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        
        
        Sistema sistema = new Sistema(cpuMock);
        
        
        ListaReproduccion lista = new ListaPorDefecto("Lista de Éxitos", "imagen2.png", "Genero1");
        when(cpuMock.listaListas()).thenReturn(Collections.singletonList(lista));
        
        
        Set<String> resultado = sistema.listarListasPar();
        
        
        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testListarListasPar_SinListas() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        
        
        Sistema sistema = new Sistema(cpuMock);
        
        
        when(cpuMock.listaListas()).thenReturn(Collections.emptyList());
        
        
        Set<String> resultado = sistema.listarListasPar();
        
        
        assertTrue(resultado.isEmpty());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// Set<String> listarListasDef //////////////////////////////////////
    @Test
    public void testListarListasDef() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        
        
        Sistema sistema = new Sistema(cpuMock);
        
        
        ListaReproduccion lista1 = new ListaParticular("Lista Favorita", "imagen1.png", "cliente1");
        ListaReproduccion lista2 = new ListaPorDefecto("Lista de Éxitos", "imagen2.png", "Genero1");
        ListaReproduccion lista3 = new ListaPorDefecto("Lista Clásicos", "imagen3.png", "Genero2");
        
        
        when(cpuMock.listaListas()).thenReturn(Arrays.asList(lista1, lista2, lista3));
        
        
        Set<String> resultado = sistema.listarListasDef();
        
        
        assertEquals(2, resultado.size()); 
        assertTrue(resultado.contains("Lista de Éxitos"));
        assertTrue(resultado.contains("Lista Clásicos"));
        assertFalse(resultado.contains("Lista Favorita")); 
    }

    @Test
    public void testListarListasDef_SinListasPorDefecto() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        
        
        Sistema sistema = new Sistema(cpuMock);
        
        
        ListaReproduccion lista = new ListaParticular("Lista Favorita", "imagen1.png", "cliente1");
        when(cpuMock.listaListas()).thenReturn(Collections.singletonList(lista));
        
        
        Set<String> resultado = sistema.listarListasDef();
        
        
        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testListarListasDef_SinListas() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        
        
        Sistema sistema = new Sistema(cpuMock);
        
        
        when(cpuMock.listaListas()).thenReturn(Collections.emptyList());
        
        
        Set<String> resultado = sistema.listarListasDef();
        
        
        assertTrue(resultado.isEmpty());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// DataLista darLista //////////////////////////////////////
//    @Test
//    public void testDarLista_ListaPorDefecto() {
//        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
//        Sistema sistema = new Sistema(cpuMock);
//        
//        ListaPorDefecto listaPorDefecto = new ListaPorDefecto("Lista de Éxitos", "imagen.png", "Genero1");
//        when(cpuMock.findLista("Lista de Éxitos")).thenReturn(listaPorDefecto);
//        
//        DataLista resultado = sistema.darLista("Lista de Éxitos");
//        
//        assertNotNull(resultado);
//        assertEquals("Lista de Éxitos", resultado.getNombre());
//        assertEquals("imagen.png", resultado.getImagen());
//    }
//
//    @Test
//    public void testDarLista_ListaParticular() {
//        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
//        Sistema sistema = new Sistema(cpuMock);
//        
//        ListaParticular listaParticular = new ListaParticular("Lista Favorita", "imagen.png", "cliente1");
//        when(cpuMock.findLista("Lista Favorita")).thenReturn(listaParticular);
//        
//        DataLista resultado = sistema.darLista("Lista Favorita");
//        
//        assertNotNull(resultado);
//        assertEquals("Lista Favorita", resultado.getNombre());
//        assertEquals("imagen.png", resultado.getImagen());
//    }
//
//    @Test
//    public void testDarLista_NoExiste() {
//        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
//        Sistema sistema = new Sistema(cpuMock);
//        
//        
//        when(cpuMock.findLista("Lista Inexistente")).thenReturn(null);
//        
//        
//        DataLista resultado = sistema.darLista("Lista Inexistente");
//        
//        
//        assertNull(resultado);
//    }
    @Test
    public void testDarLista_ListaPorDefecto() {
        // Mock de ControladorPersistencia (cpu)
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        // Crear una instancia de Sistema con el mock
        Sistema sistema = new Sistema(cpuMock);

        // Preparar datos de prueba
        int idListaPorDefecto = 1;
        ListaPorDefecto listaPorDefecto = new ListaPorDefecto("Lista de Éxitos", "imagen.png", "Genero1");

        // Configurar el comportamiento del mock para devolver una ListaPorDefecto
        when(cpuMock.findLista(idListaPorDefecto)).thenReturn(listaPorDefecto);

        // Ejecutar el método
        DataLista resultado = sistema.darLista(idListaPorDefecto);

        // Verificar el resultado
        assertNotNull(resultado);
        assertEquals("Lista de Éxitos", resultado.getNombre());
        assertEquals("imagen.png", resultado.getImagen());
    }

    @Test
    public void testDarLista_ListaParticular() {
        // Mock de ControladorPersistencia (cpu)
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        // Crear una instancia de Sistema con el mock
        Sistema sistema = new Sistema(cpuMock);

        // Preparar datos de prueba
        int idListaParticular = 2;
        ListaParticular listaParticular = new ListaParticular("Lista Favorita", "imagen.png", "cliente1");

        // Configurar el comportamiento del mock para devolver una ListaParticular
        when(cpuMock.findLista(idListaParticular)).thenReturn(listaParticular);

        // Ejecutar el método
        DataLista resultado = sistema.darLista(idListaParticular);

        // Verificar el resultado
        assertNotNull(resultado);
        assertEquals("Lista Favorita", resultado.getNombre());
        assertEquals("imagen.png", resultado.getImagen());
    }

//    @Test
//    public void testDarLista_NoExiste() {
//        // Mock de ControladorPersistencia (cpu)
//        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
//
//        // Crear una instancia de Sistema con el mock
//        Sistema sistema = new Sistema(cpuMock);
//
//        // Preparar datos de prueba
//        int idListaInexistente = 3;
//
//        // Configurar el comportamiento del mock para devolver null cuando la lista no existe
//        when(cpuMock.findLista(idListaInexistente)).thenReturn(null);
//
//        // Ejecutar el método
//        DataLista resultado = sistema.darLista(idListaInexistente);
//
//        // Verificar el resultado
//        assertNull(resultado);
//   
//    }

    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// List<DataLista> traerListasDefectoPorGenero //////////////////////////////////////
    @Test
    public void testTraerListasDefectoPorGenero() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        
        String genero = "Pop";
        ListaPorDefecto lista1 = new ListaPorDefecto("Lista Pop 1", "imagen1.png", "Pop");
        ListaPorDefecto lista2 = new ListaPorDefecto("Lista Pop 2", "imagen2.png", "Pop");
        List<ListaPorDefecto> listas = new ArrayList<>();
        listas.add(lista1);
        listas.add(lista2);
        
        
        when(cpuMock.traerListasPorDefectoPorGenero(genero)).thenReturn(listas);
        
        
        List<DataLista> resultado = sistema.traerListasDefectoPorGenero(genero);
        
        
        assertNotNull(resultado);
        
        assertEquals(2, resultado.size());
        
        
        assertEquals("Lista Pop 1", resultado.get(0).getNombre());
        assertEquals("imagen1.png", resultado.get(0).getImagen());
        assertEquals("Lista Pop 2", resultado.get(1).getNombre());
        assertEquals("imagen2.png", resultado.get(1).getImagen());
    }
    
    @Test
    public void testTraerListasDefectoPorGenero_NoListas() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        
        String genero = "Jazz";
        List<ListaPorDefecto> listas = new ArrayList<>();
        
        
        when(cpuMock.traerListasPorDefectoPorGenero(genero)).thenReturn(listas);
        
        
        List<DataLista> resultado = sistema.traerListasDefectoPorGenero(genero);
        
        
        assertNotNull(resultado);
        
        assertEquals(0, resultado.size());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// ListaParticular traerListaParticularPorId //////////////////////////////////////
    @Test
    public void testTraerListaParticularPorId_Existente() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        
        int id = 1;
        ListaParticular listaEsperada = new ListaParticular("Lista Favorita", "imagen.png", "cliente1");
        
        
        when(cpuMock.traerListaParticularPorId(id)).thenReturn(listaEsperada);
        
        
        ListaParticular resultado = sistema.traerListaParticularPorId(id);
        
        
        assertNotNull(resultado);
        assertEquals(listaEsperada.getNombre(), resultado.getNombre());
        assertEquals(listaEsperada.getImagen(), resultado.getImagen());
    }
    
    @Test
    public void testTraerListaParticularPorId_NoExistente() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        
        int id = 999;  
        
        
        when(cpuMock.traerListaParticularPorId(id)).thenReturn(null);
        
        
        ListaParticular resultado = sistema.traerListaParticularPorId(id);
        
        
        assertNull(resultado);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// List<DataLista> traerListasParticularesPorClientes //////////////////////////////////////
    @Test
    public void testTraerListasParticularesPorClientes_Existentes() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        
        String nickname = "cliente1";
        ListaParticular lista1 = new ListaParticular("Lista Favorita", "imagen1.png", nickname);
        ListaParticular lista2 = new ListaParticular("Lista de Éxitos", "imagen2.png", nickname);
        
        List<ListaParticular> listasEsperadas = new ArrayList<>();
        listasEsperadas.add(lista1);
        listasEsperadas.add(lista2);
        
        
        when(cpuMock.traerListasParticularesPorCliente(nickname)).thenReturn(listasEsperadas);
        
        
        List<DataLista> resultado = sistema.traerListasParticularesPorClientes(nickname);
        
        
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Lista Favorita", resultado.get(0).getNombre());
        assertEquals("Lista de Éxitos", resultado.get(1).getNombre());
    }
    
    @Test
    public void testTraerListasParticularesPorClientes_NoExistentes() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        
        String nickname = "cliente2";
        
        
        when(cpuMock.traerListasParticularesPorCliente(nickname)).thenReturn(new ArrayList<>());
        
        
        List<DataLista> resultado = sistema.traerListasParticularesPorClientes(nickname);
        
        
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// DataLista traerListaDefGeneroNombre //////////////////////////////////////
    @Test
    public void testTraerListaDefGeneroNombre_Existente() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        
        String genero = "Rock";
        String nombre = "Lista de Rock Clásico";
        
        
        ListaPorDefecto listaEsperada = new ListaPorDefecto(nombre, "imagen.png", genero);
        
        
        List<ListaPorDefecto> listasPorDefecto = new ArrayList<>();
        listasPorDefecto.add(listaEsperada);
        
        
        when(cpuMock.traerListasPorDefectoPorGenero(genero)).thenReturn(listasPorDefecto);
        
        
        DataLista resultado = sistema.traerListaDefGeneroNombre(genero, nombre);
        
        
        assertNotNull(resultado);
        assertEquals(nombre, resultado.getNombre());
    }
    
    @Test
public void testTraerListaDefGeneroNombre_NoCoincideNombre() {
    
    ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
    Sistema sistema = new Sistema(cpuMock);
    
    
    String genero = "Rock";
    String nombreBuscado = "Lista de Rock Clásico";
    String nombreNoCoincidente = "Lista de Pop Clásico";
    
    
    ListaPorDefecto listaNoCoincidente = new ListaPorDefecto(nombreNoCoincidente, "imagen.png", genero);
    
    
    List<ListaPorDefecto> listasPorDefecto = new ArrayList<>();
    listasPorDefecto.add(listaNoCoincidente);
    
    
    when(cpuMock.traerListasPorDefectoPorGenero(genero)).thenReturn(listasPorDefecto);
    
    
    DataLista resultado = sistema.traerListaDefGeneroNombre(genero, nombreBuscado);
    
    
    assertNull(resultado, "El resultado debería ser nulo porque no hay lista con el nombre especificado");
}

    @Test
    public void testTraerListaDefGeneroNombre_NoExistente() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        
        String genero = "Pop";
        String nombre = "Lista de Éxitos";
        
        
        List<ListaPorDefecto> listasPorDefecto = new ArrayList<>();
        
        
        when(cpuMock.traerListasPorDefectoPorGenero(genero)).thenReturn(listasPorDefecto);
        
        
        DataLista resultado = sistema.traerListaDefGeneroNombre(genero, nombre);
        
        
        assertNull(resultado);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// DataLista traerListaPartClienteNombre //////////////////////////////////////
    @Test
    public void testTraerListaPartClienteNombre_Existente() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        
        String cliente = "cliente1";
        String nombre = "Lista Favorita";
        
        
        ListaParticular listaEsperada = new ListaParticular(nombre, "imagen.png", cliente);
        
        
        List<ListaParticular> listasParticulares = new ArrayList<>();
        listasParticulares.add(listaEsperada);
        
        
        when(cpuMock.traerListasParticularesPorCliente(cliente)).thenReturn(listasParticulares);
        
        
        DataLista resultado = sistema.traerListaPartClienteNombre(cliente, nombre);
        
        
        assertNotNull(resultado);
        assertEquals(nombre, resultado.getNombre());
    }
    
    @Test
    public void testTraerListaPartClienteNombre_NoCoincideNombre() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        String cliente = "cliente1";
        String nombreBuscado = "Lista Favorita";
        String nombreNoCoincidente = "Lista de Pop";

        
        ListaParticular listaNoCoincidente = new ListaParticular(nombreNoCoincidente, "imagen.png", cliente);

        
        List<ListaParticular> listasParticulares = new ArrayList<>();
        listasParticulares.add(listaNoCoincidente);

        
        when(cpuMock.traerListasParticularesPorCliente(cliente)).thenReturn(listasParticulares);

        
        DataLista resultado = sistema.traerListaPartClienteNombre(cliente, nombreBuscado);

        
        assertNull(resultado, "El resultado debería ser nulo porque no hay lista con el nombre especificado");
    }

    @Test
    public void testTraerListaPartClienteNombre_NoExistente() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        String cliente = "cliente2";
        String nombre = "Lista No Existente";

        
        List<ListaParticular> listasParticulares = new ArrayList<>();

        
        when(cpuMock.traerListasParticularesPorCliente(cliente)).thenReturn(listasParticulares);

        
        DataLista resultado = sistema.traerListaPartClienteNombre(cliente, nombre);

        
        assertNull(resultado, "Se esperaba que el resultado fuera nulo");
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// List<String> traerNickNamesClientes //////////////////////////////////////
    @Test
    public void testTraerNickNamesClientes() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        Usuario usuario1 = new Usuario("nick1", "Nombre1", "Apellido1", "password1", "email1", LocalDate.now(), "imagen1", "cliente");
        Usuario usuario2 = new Usuario("nick2", "Nombre2", "Apellido2", "password2", "email2", LocalDate.now(), "imagen2", "cliente");
        
        
        when(cpuMock.traerUsuariosClientes()).thenReturn(Arrays.asList(usuario1, usuario2));

        
        List<String> nicknames = sistema.traerNickNamesClientes();

        
        assertEquals(2, nicknames.size(), "La lista de apodos debería tener dos elementos");
        assertTrue(nicknames.contains("nick1"), "La lista de apodos debería contener 'nick1'");
        assertTrue(nicknames.contains("nick2"), "La lista de apodos debería contener 'nick2'");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// void modificarListaParticularPorId //////////////////////////////////////
    @Test
    public void testModificarListaParticularPorId() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        
        int listaId = 1;
        ListaParticular listaParticular = new ListaParticular("Lista Favorita", "imagen.png", "clienteId");
        
        when(cpuMock.traerListaParticularPorId(listaId)).thenReturn(listaParticular);
        
        
        sistema.modificarListaParticularPorId(listaId);
        
        
        assertTrue(listaParticular.isPublica(), "La lista debería ser pública después de la modificación");

        
        verify(cpuMock).modificarListaParticular(listaParticular);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// ArrayList Nicknames_De_Clientes //////////////////////////////////////
    @Test
    public void testNicknames_De_Clientes() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        Cliente cliente1 = new Cliente("nick1", "Nombre1", "Apellido1", "password1", "email1", LocalDate.now(), "imagen1", "tipo1");
        Cliente cliente2 = new Cliente("nick2", "Nombre2", "Apellido2", "password2", "email2", LocalDate.now(), "imagen2", "tipo2");
        
        
        when(cpuMock.obtenerTodosLosClientes()).thenReturn(Arrays.asList(cliente1, cliente2));

        
        ArrayList<String> nicknames = sistema.Nicknames_De_Clientes();

        
        assertEquals(2, nicknames.size(), "La lista de apodos debería tener dos elementos");
        assertTrue(nicknames.contains("nick1"), "La lista de apodos debería contener 'nick1'");
        assertTrue(nicknames.contains("nick2"), "La lista de apodos debería contener 'nick2'");
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// ArrayList Listar_Usuarios_A_Seguir //////////////////////////////////////
    @Test
    public void testListar_Usuarios_A_Seguir() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        Usuario usuario1 = new Usuario("nick1", "Nombre1", "Apellido1", "password1", "email1", LocalDate.now(), "imagen1", "tipo1");
        Usuario usuario2 = new Usuario("nick2", "Nombre2", "Apellido2", "password2", "email2", LocalDate.now(), "imagen2", "tipo2");
        Usuario usuario3 = new Usuario("nick3", "Nombre3", "Apellido3", "password3", "email3", LocalDate.now(), "imagen3", "tipo3");

        
        Cliente clienteSeguidor = new Cliente("clienteNick", "NombreCliente", "ApellidoCliente", "passwordCliente", "emailCliente", LocalDate.now(), "imagenCliente", "tipoCliente");
        clienteSeguidor.setUsuariosSiguiendo(new ArrayList<>(Arrays.asList(usuario1, usuario2)));

        
        List<Usuario> todosLosUsuarios = new ArrayList<>(Arrays.asList(usuario1, usuario2, usuario3));
        
        
        when(cpuMock.obtenerCliente("clienteNick")).thenReturn(clienteSeguidor);
        when(cpuMock.getUsuarios()).thenReturn(todosLosUsuarios);
        
        
        ArrayList<String> nicknames = sistema.Listar_Usuarios_A_Seguir("clienteNick");

        
        assertEquals(1, nicknames.size(), "La lista de apodos debería tener un elemento"); 
        assertTrue(nicknames.contains("nick3"), "La lista de apodos debería contener 'nick3'");
        assertFalse(nicknames.contains("nick1"), "La lista de apodos no debería contener 'nick1'");
        assertFalse(nicknames.contains("nick2"), "La lista de apodos no debería contener 'nick2'");
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// void Seguir_Usuario //////////////////////////////////////
    @Test
    public void testSeguirUsuario() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        Cliente clienteSeguidor = new Cliente("clienteNick", "NombreCliente", "ApellidoCliente", "passwordCliente", "emailCliente", LocalDate.now(), "imagenCliente", "tipoCliente");
        clienteSeguidor.setUsuariosSiguiendo(new ArrayList<>()); 

        
        Usuario usuarioASeguir = new Usuario("nickUsuario", "NombreUsuario", "ApellidoUsuario", "passwordUsuario", "emailUsuario", LocalDate.now(), "imagenUsuario", "tipoUsuario");

        
        when(cpuMock.obtenerCliente("clienteNick")).thenReturn(clienteSeguidor);
        when(cpuMock.obtenerUsuario("nickUsuario")).thenReturn(usuarioASeguir);

        
        sistema.Seguir_Usuario("clienteNick", "nickUsuario");

        
        assertTrue(clienteSeguidor.getUsuariosSiguiendo().contains(usuarioASeguir), "El cliente debería seguir al usuario");

        
        verify(cpuMock, times(1)).actualizarUsuario(clienteSeguidor);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// ArrayList<String> Listar_Usuarios_A_DEJAR_DE_Seguir //////////////////////////////////////
    @Test
    public void testListarUsuariosADejarDeSeguir() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        Cliente clienteElegido = new Cliente("clienteNick", "NombreCliente", "ApellidoCliente", "passwordCliente", "emailCliente", LocalDate.now(), "imagenCliente", "tipoCliente");
        
        
        Usuario usuario1 = new Usuario("nickUsuario1", "NombreUsuario1", "ApellidoUsuario1", "passwordUsuario1", "emailUsuario1", LocalDate.now(), "imagenUsuario1", "tipoUsuario1");
        Usuario usuario2 = new Usuario("nickUsuario2", "NombreUsuario2", "ApellidoUsuario2", "passwordUsuario2", "emailUsuario2", LocalDate.now(), "imagenUsuario2", "tipoUsuario2");

        
        ArrayList<Usuario> usuariosSeguidos = new ArrayList<>();
        usuariosSeguidos.add(usuario1);
        usuariosSeguidos.add(usuario2);
        clienteElegido.setUsuariosSiguiendo(usuariosSeguidos);

        
        when(cpuMock.obtenerCliente("clienteNick")).thenReturn(clienteElegido);

        
        ArrayList<String> result = sistema.Listar_Usuarios_A_DEJAR_DE_Seguir("clienteNick");

        
        assertEquals(2, result.size(), "La lista de usuarios a dejar de seguir debería tener dos elementos");

        
        assertTrue(result.contains("nickUsuario1"), "La lista debería contener nickUsuario1");
        assertTrue(result.contains("nickUsuario2"), "La lista debería contener nickUsuario2");
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// void Dejar_De_Seguir_Usuario //////////////////////////////////////
    @Test
    public void testDejarDeSeguirUsuario() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        Cliente clienteElegido = new Cliente("clienteNick", "NombreCliente", "ApellidoCliente", "passwordCliente", "emailCliente", LocalDate.now(), "imagenCliente", "tipoCliente");
        
        
        Usuario usuario1 = new Usuario("nickUsuario1", "NombreUsuario1", "ApellidoUsuario1", "passwordUsuario1", "emailUsuario1", LocalDate.now(), "imagenUsuario1", "tipoUsuario1");
        Usuario usuario2 = new Usuario("nickUsuario2", "NombreUsuario2", "ApellidoUsuario2", "passwordUsuario2", "emailUsuario2", LocalDate.now(), "imagenUsuario2", "tipoUsuario2");

        
        ArrayList<Usuario> usuariosSeguidos = new ArrayList<>();
        usuariosSeguidos.add(usuario1);
        usuariosSeguidos.add(usuario2);
        clienteElegido.setUsuariosSiguiendo(usuariosSeguidos);

        
        when(cpuMock.obtenerCliente("clienteNick")).thenReturn(clienteElegido);
        when(cpuMock.obtenerUsuario("nickUsuario1")).thenReturn(usuario1);

        
        sistema.Dejar_De_Seguir_Usuario("clienteNick", "nickUsuario1");

        
        assertFalse(clienteElegido.getUsuariosSiguiendo().contains(usuario1), "El usuario nickUsuario1 debería haber sido eliminado de la lista de seguidos");
        assertTrue(clienteElegido.getUsuariosSiguiendo().contains(usuario2), "El usuario nickUsuario2 debería seguir en la lista de seguidos");

        
        verify(cpuMock).actualizarUsuario(clienteElegido);
    }
    
    @Test
    public void testDejarDeSeguirUsuario_NoSeguido() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        Cliente clienteElegido = new Cliente("clienteNick", "NombreCliente", "ApellidoCliente", "passwordCliente", "emailCliente", LocalDate.now(), "imagenCliente", "tipoCliente");

        
        Usuario usuarioSeguido = new Usuario("nickUsuarioSeguido", "NombreUsuarioSeguido", "ApellidoUsuarioSeguido", "passwordUsuarioSeguido", "emailUsuarioSeguido", LocalDate.now(), "imagenUsuarioSeguido", "tipoUsuarioSeguido");
        Usuario usuarioNoSeguido = new Usuario("nickUsuarioNoSeguido", "NombreUsuarioNoSeguido", "ApellidoUsuarioNoSeguido", "passwordUsuarioNoSeguido", "emailUsuarioNoSeguido", LocalDate.now(), "imagenUsuarioNoSeguido", "tipoUsuarioNoSeguido");

        
        ArrayList<Usuario> usuariosSeguidos = new ArrayList<>();
        usuariosSeguidos.add(usuarioSeguido);
        clienteElegido.setUsuariosSiguiendo(usuariosSeguidos);

        
        when(cpuMock.obtenerCliente("clienteNick")).thenReturn(clienteElegido);
        when(cpuMock.obtenerUsuario("nickUsuarioNoSeguido")).thenReturn(usuarioNoSeguido);

        
        sistema.Dejar_De_Seguir_Usuario("clienteNick", "nickUsuarioNoSeguido");

        
        assertTrue(clienteElegido.getUsuariosSiguiendo().contains(usuarioSeguido), "El usuario seguido debería seguir en la lista de seguidos");
        assertFalse(clienteElegido.getUsuariosSiguiendo().contains(usuarioNoSeguido), "El usuario no seguido nunca debería estar en la lista de seguidos");

        
        verify(cpuMock).actualizarUsuario(clienteElegido);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// ArrayList<String> Listar_Temas_A_Seguir //////////////////////////////////////
    @Test
    void testListar_Temas_A_Seguir_Cubre_Else() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        String clienteSeleccionado = "TestCliente";
        Cliente clienteMock = mock(Cliente.class);

        
        Tema tema1 = new Tema("Tema1", "3:00", 1);
        tema1.setId(1L);
        Tema tema2 = new Tema("Tema2", "4:00", 2);
        tema2.setId(2L);
        Tema tema3 = new Tema("Tema3", "5:00", 3);
        tema3.setId(3L);

        
        Tema temaFavoritoNoDisponible = new Tema("TemaNoDisponible", "6:00", 4);
        temaFavoritoNoDisponible.setId(4L);

        
        List<Tema> todosTemas = new ArrayList<>(List.of(tema1, tema2, tema3));
        ArrayList<Tema> temasFavoritos = new ArrayList<>(List.of(temaFavoritoNoDisponible));

        
        when(cpuMock.obtenerCliente(clienteSeleccionado)).thenReturn(clienteMock);
        when(clienteMock.getTemasFavoritos()).thenReturn(temasFavoritos);
        when(cpuMock.Listar_Temas()).thenReturn(todosTemas);

        
        ArrayList<String> resultado = sistema.Listar_Temas_A_Seguir(clienteSeleccionado);

        
        assertEquals(3, resultado.size(), "El tamaño de la lista debería ser 3 (todos los temas disponibles).");
        assertTrue(resultado.contains("Tema1 1"), "La lista debería contener 'Tema1 1'.");
        assertTrue(resultado.contains("Tema2 2"), "La lista debería contener 'Tema2 2'.");
        assertTrue(resultado.contains("Tema3 3"), "La lista debería contener 'Tema3 3'.");
        assertFalse(resultado.contains("TemaNoDisponible 4"), "La lista no debería contener el tema favorito no disponible.");

        
        verify(cpuMock).obtenerCliente(clienteSeleccionado);
        verify(clienteMock).getTemasFavoritos();
        verify(cpuMock).Listar_Temas();
    }
    
    @Test
    void testListar_Temas_A_Seguir() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        String clienteSeleccionado = "TestCliente";
        Cliente clienteMock = mock(Cliente.class);
        
        
        Tema tema1 = new Tema("Tema1", "3:00", 1);
        tema1.setId(1L);
        Tema tema2 = new Tema("Tema2", "4:00", 2);
        tema2.setId(2L);
        Tema tema3 = new Tema("Tema3", "5:00", 3);
        tema3.setId(3L);

        List<Tema> todosTemas = new ArrayList<>(List.of(tema1, tema2, tema3));
        ArrayList<Tema> temasFavoritos = new ArrayList<>(List.of(tema2));

        
        when(cpuMock.obtenerCliente(clienteSeleccionado)).thenReturn(clienteMock);
        when(clienteMock.getTemasFavoritos()).thenReturn(temasFavoritos);
        when(cpuMock.Listar_Temas()).thenReturn(todosTemas);

        
        ArrayList<String> resultado = sistema.Listar_Temas_A_Seguir(clienteSeleccionado);

        
        assertEquals(2, resultado.size());
        assertTrue(resultado.contains("Tema1 1"));
        assertTrue(resultado.contains("Tema3 3"));
        assertFalse(resultado.contains("Tema2 2"));

        
        verify(cpuMock).obtenerCliente(clienteSeleccionado);
        verify(clienteMock).getTemasFavoritos();
        verify(cpuMock).Listar_Temas();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// void Seguir_TEMA //////////////////////////////////////
    @Test
    void testSeguir_TEMA() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        String clienteSeleccionado = "TestCliente";
        String temaSeleccionado = "Tema2 2";
        
        Cliente clienteMock = mock(Cliente.class);
        
        
        Tema tema1 = new Tema("Tema1", "3:00", 1);
        tema1.setId(1L);
        Tema tema2 = new Tema("Tema2", "4:00", 2);
        tema2.setId(2L);
        Tema tema3 = new Tema("Tema3", "5:00", 3);
        tema3.setId(3L);

        List<Tema> todosTemas = new ArrayList<>(List.of(tema1, tema2, tema3));
        ArrayList<Tema> temasFavoritos = new ArrayList<>();

        
        when(cpuMock.obtenerCliente(clienteSeleccionado)).thenReturn(clienteMock);
        when(cpuMock.Listar_Temas()).thenReturn(todosTemas);
        when(clienteMock.getTemasFavoritos()).thenReturn(temasFavoritos);

        
        sistema.Seguir_TEMA(clienteSeleccionado, temaSeleccionado);

        
        verify(cpuMock).obtenerCliente(clienteSeleccionado);
        verify(cpuMock).Listar_Temas();
        verify(clienteMock).getTemasFavoritos();
        verify(cpuMock).actualizarUsuario(clienteMock);

        assertEquals(1, temasFavoritos.size());
        assertEquals(tema2, temasFavoritos.get(0));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// ArrayList<String> Listar_Albumes_A_Seguir //////////////////////////////////////
    @Test
    void testListar_Albumes_A_Seguir() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        String clienteSeleccionado = "TestCliente";
        Cliente clienteMock = mock(Cliente.class);

        
        Album album1 = new Album("Album1", 2022, "imagen1.jpg");
        album1.setId(1L);
        Album album2 = new Album("Album2", 2021, "imagen2.jpg");
        album2.setId(2L);
        Album album3 = new Album("Album3", 2020, "imagen3.jpg");
        album3.setId(3L);

        
        Album albumFavoritoNoDisponible = new Album("AlbumNoDisponible", 2019, "imagen4.jpg");
        albumFavoritoNoDisponible.setId(4L);

        
        List<Album> todosAlbumes = new ArrayList<>(List.of(album1, album2, album3));
        ArrayList<Album> albumesFavoritos = new ArrayList<>(List.of(album2, albumFavoritoNoDisponible));

        
        when(cpuMock.obtenerCliente(clienteSeleccionado)).thenReturn(clienteMock);
        when(clienteMock.getAlbumesFavoritos()).thenReturn(albumesFavoritos);
        when(cpuMock.Listar_Albumes()).thenReturn(todosAlbumes);

        
        ArrayList<String> resultado = sistema.Listar_Albumes_A_Seguir(clienteSeleccionado);

        
        assertEquals(2, resultado.size(), "La lista debería contener 2 álbumes disponibles para seguir.");
        assertTrue(resultado.contains("Album1 1"), "La lista debería contener 'Album1 1'.");
        assertTrue(resultado.contains("Album3 3"), "La lista debería contener 'Album3 3'.");
        assertFalse(resultado.contains("Album2 2"), "La lista no debería contener 'Album2 2' porque ya es un favorito.");
        assertFalse(resultado.contains("AlbumNoDisponible 4"), "La lista no debería contener el álbum favorito no disponible.");

        
        verify(cpuMock).obtenerCliente(clienteSeleccionado);
        verify(clienteMock).getAlbumesFavoritos();
        verify(cpuMock).Listar_Albumes();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// void Seguir_ALBUM //////////////////////////////////////
    @Test
    void testSeguir_ALBUM() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        String clienteSeleccionado = "TestCliente";
        String albumSeleccionado = "Album1 1";

        
        Cliente clienteMock = mock(Cliente.class);
        Album album1 = new Album("Album1", 2022, "imagen1.jpg");
        album1.setId(1L);
        Album album2 = new Album("Album2", 2021, "imagen2.jpg");
        album2.setId(2L);

        
        List<Album> todosAlbumes = new ArrayList<>(List.of(album1, album2));
        List<Album> albumesFavoritos = new ArrayList<>();

        
        when(cpuMock.obtenerCliente(clienteSeleccionado)).thenReturn(clienteMock);
        when(clienteMock.getAlbumesFavoritos()).thenReturn((ArrayList<Album>) albumesFavoritos);
        when(cpuMock.Listar_Albumes()).thenReturn(todosAlbumes);

        
        sistema.Seguir_ALBUM(clienteSeleccionado, albumSeleccionado);

        
        assertEquals(1, albumesFavoritos.size(), "Debería haber 1 álbum favorito.");
        assertTrue(albumesFavoritos.contains(album1), "El álbum seleccionado debería estar en los favoritos.");

        
        verify(cpuMock).actualizarUsuario(clienteMock);

        
        verify(cpuMock).obtenerCliente(clienteSeleccionado);
        verify(cpuMock).Listar_Albumes();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// ArrayList<String> Listar_Listas_A_Seguir //////////////////////////////////////
    @Test
    void testListar_Listas_A_Seguir() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        String clienteSeleccionado = "TestCliente";
        Cliente clienteMock = mock(Cliente.class);

        
        ListaParticular lista1 = mock(ListaParticular.class);
        when(lista1.getNombre()).thenReturn("Lista1");
        when(lista1.getId()).thenReturn(1); 
        when(lista1.isPublica()).thenReturn(true);

        ListaReproduccion listaFavorita = mock(ListaReproduccion.class);
        when(listaFavorita.getNombre()).thenReturn("ListaFavorita");
        when(listaFavorita.getId()).thenReturn(2); 
        when(listaFavorita.getNombre()).thenReturn("ListaFavorita");

        
        List<ListaReproduccion> todasLasListas = new ArrayList<>(List.of(lista1));
        ArrayList<ListaReproduccion> listasFavoritas = new ArrayList<>(List.of(listaFavorita));

        
        when(cpuMock.obtenerCliente(clienteSeleccionado)).thenReturn(clienteMock);
        when(clienteMock.getListasFavoritas()).thenReturn(listasFavoritas);
        when(cpuMock.listaListas()).thenReturn(todasLasListas);

        
        ArrayList<String> resultado = sistema.Listar_Listas_A_Seguir(clienteSeleccionado);

        
        assertEquals(1, resultado.size(), "Debería haber 1 lista disponible para seguir.");
        assertTrue(resultado.contains("Lista1 1"), "La lista debería contener 'Lista1 1'.");
        assertFalse(resultado.contains("ListaFavorita 2"), "La lista no debería contener 'ListaFavorita 2' porque ya es una favorita.");

        
        verify(cpuMock).obtenerCliente(clienteSeleccionado);
        verify(cpuMock).listaListas();
        verify(clienteMock).getListasFavoritas();
    }
    
    
    @Test
    void testListar_Listas_A_Seguir_ListaParticularPublica_NoFavorita() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        String clienteSeleccionado = "TestCliente";
        Cliente clienteMock = mock(Cliente.class);

        
        ListaParticular listaPublica = mock(ListaParticular.class);
        when(listaPublica.getNombre()).thenReturn("ListaPublica");
        when(listaPublica.getId()).thenReturn(1); 
        when(listaPublica.isPublica()).thenReturn(true);

        
        ArrayList<ListaReproduccion> listasFavoritas = new ArrayList<>();

        
        List<ListaReproduccion> todasLasListas = new ArrayList<>(List.of(listaPublica));

        
        when(cpuMock.obtenerCliente(clienteSeleccionado)).thenReturn(clienteMock);
        when(clienteMock.getListasFavoritas()).thenReturn(listasFavoritas);
        when(cpuMock.listaListas()).thenReturn(todasLasListas);

        
        ArrayList<String> resultado = sistema.Listar_Listas_A_Seguir(clienteSeleccionado);

        
        assertEquals(1, resultado.size(), "Debería haber 1 lista pública disponible para seguir.");
        assertTrue(resultado.contains("ListaPublica 1"), "La lista pública debería contener 'ListaPublica 1'.");
    }

    
    @Test
    void testListar_Listas_A_Seguir_NoListaParticular() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        String clienteSeleccionado = "TestCliente";
        Cliente clienteMock = mock(Cliente.class);

        
        ListaReproduccion listaGenerica = mock(ListaReproduccion.class);  
        when(listaGenerica.getNombre()).thenReturn("ListaGenerica");
        when(listaGenerica.getId()).thenReturn(1);

        
        ArrayList<ListaReproduccion> listasFavoritas = new ArrayList<>();

        
        List<ListaReproduccion> todasLasListas = new ArrayList<>(List.of(listaGenerica));

        
        when(cpuMock.obtenerCliente(clienteSeleccionado)).thenReturn(clienteMock);
        when(clienteMock.getListasFavoritas()).thenReturn(listasFavoritas);
        when(cpuMock.listaListas()).thenReturn(todasLasListas);

        
        ArrayList<String> resultado = sistema.Listar_Listas_A_Seguir(clienteSeleccionado);

        
        assertEquals(1, resultado.size(), "Debería haber 1 lista genérica disponible para seguir.");
        assertTrue(resultado.contains("ListaGenerica 1"), "La lista genérica debería contener 'ListaGenerica 1'.");
    }

    
    @Test
    void testListar_Listas_A_Seguir_ListaParticularPublica_Favorita() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        String clienteSeleccionado = "TestCliente";
        Cliente clienteMock = mock(Cliente.class);

        
        ListaParticular listaPublicaFavorita = mock(ListaParticular.class);
        when(listaPublicaFavorita.getNombre()).thenReturn("ListaFavorita");
        when(listaPublicaFavorita.getId()).thenReturn(1); 
        when(listaPublicaFavorita.isPublica()).thenReturn(true);

        
        List<ListaReproduccion> todasLasListas = new ArrayList<>(List.of(listaPublicaFavorita));

        
        when(cpuMock.obtenerCliente(clienteSeleccionado)).thenReturn(clienteMock);
        when(clienteMock.getListasFavoritas()).thenReturn(new ArrayList<>(List.of(listaPublicaFavorita)));
        when(cpuMock.listaListas()).thenReturn(todasLasListas);

        
        ArrayList<String> resultado = sistema.Listar_Listas_A_Seguir(clienteSeleccionado);

        
        assertEquals(0, resultado.size(), "No debería haber listas disponibles para seguir porque es una lista favorita.");
    }

    @Test
    void testListar_Listas_A_Seguir_ListaParticularPrivada() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        String clienteSeleccionado = "TestCliente";
        Cliente clienteMock = mock(Cliente.class);

        
        ListaParticular listaPrivada = mock(ListaParticular.class);
        when(listaPrivada.getNombre()).thenReturn("ListaPrivada");
        when(listaPrivada.getId()).thenReturn(2); 
        when(listaPrivada.isPublica()).thenReturn(false);

        
        List<ListaReproduccion> todasLasListas = new ArrayList<>(List.of(listaPrivada));

        
        when(cpuMock.obtenerCliente(clienteSeleccionado)).thenReturn(clienteMock);
        when(clienteMock.getListasFavoritas()).thenReturn(new ArrayList<>());
        when(cpuMock.listaListas()).thenReturn(todasLasListas);

        
        ArrayList<String> resultado = sistema.Listar_Listas_A_Seguir(clienteSeleccionado);

        
        assertEquals(0, resultado.size(), "No debería haber listas disponibles para seguir porque es privada.");
    }

    @Test
    void testListar_Listas_A_Seguir_SinListas() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        String clienteSeleccionado = "TestCliente";
        Cliente clienteMock = mock(Cliente.class);

        
        List<ListaReproduccion> todasLasListas = new ArrayList<>();

        
        when(cpuMock.obtenerCliente(clienteSeleccionado)).thenReturn(clienteMock);
        when(clienteMock.getListasFavoritas()).thenReturn(new ArrayList<>());
        when(cpuMock.listaListas()).thenReturn(todasLasListas);

        
        ArrayList<String> resultado = sistema.Listar_Listas_A_Seguir(clienteSeleccionado);

        
        assertEquals(0, resultado.size(), "No debería haber listas disponibles para seguir porque no hay listas.");
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// void Seguir_LISTA //////////////////////////////////////
    @Test
    void testSeguir_LISTA() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        String clienteSeleccionado = "TestCliente";
        String listaSeleccionada = "Lista1 1"; 

        
        Cliente clienteMock = mock(Cliente.class);

        
        ListaReproduccion lista1 = mock(ListaReproduccion.class);
        ListaReproduccion lista2 = mock(ListaReproduccion.class);

        
        when(lista1.getNombre()).thenReturn("Lista1");
        when(lista1.getId()).thenReturn(1);
        when(lista2.getNombre()).thenReturn("Lista2");
        when(lista2.getId()).thenReturn(2);

        
        List<ListaReproduccion> listasFavoritas = new ArrayList<>();

        
        List<ListaReproduccion> todasLasListas = new ArrayList<>(List.of(lista1, lista2));

        
        when(cpuMock.obtenerCliente(clienteSeleccionado)).thenReturn(clienteMock);
        when(cpuMock.listaListas()).thenReturn(todasLasListas);
        when(clienteMock.getListasFavoritas()).thenReturn((ArrayList<ListaReproduccion>) listasFavoritas);

        
        sistema.Seguir_LISTA(clienteSeleccionado, listaSeleccionada);

        
        verify(clienteMock).getListasFavoritas();
        assertTrue(clienteMock.getListasFavoritas().contains(lista1), "La lista seleccionada debería haber sido agregada a las listas favoritas del cliente.");

        
        verify(cpuMock).actualizarUsuario(clienteMock);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// List<String> traerAlbumesArtista //////////////////////////////////////
    @Test
    public void testTraerAlbumesArtista_AlbumesExistentes() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        String nicknameArtista = "artista1";

        
        Artista artistaMock = new Artista();
        artistaMock.setNickname(nicknameArtista);

        
        Album album1 = new Album();
        album1.setNombre("Album 1");

        Album album2 = new Album();
        album2.setNombre("Album 2");

        List<Album> albumesMock = Arrays.asList(album1, album2);

        
        when(cpuMock.obtenerUsuario(nicknameArtista)).thenReturn(artistaMock);
        when(cpuMock.getAlbumesArt(artistaMock)).thenReturn(albumesMock);

        
        List<String> resultado = sistema.traerAlbumesArtista(nicknameArtista);

        
        assertNotNull(resultado, "La lista de álbumes no debería ser nula");
        assertEquals(2, resultado.size(), "Debería haber 2 álbumes");
        assertEquals("Album 1", resultado.get(0), "El primer álbum debería ser 'Album 1'");
        assertEquals("Album 2", resultado.get(1), "El segundo álbum debería ser 'Album 2'");
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// boolean existeTemaLista //////////////////////////////////////
    @Test
    public void testExisteTemaLista_TemaExiste() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        Long idTema = 1L;
        int idLista = 100;

        
        Tema temaMock = new Tema();
        temaMock.setId(idTema);

        
        Set<Tema> listaTemasMock = new HashSet<>();
        listaTemasMock.add(temaMock);

        
        ListaReproduccion listaMock = new ListaReproduccion();
        listaMock.setListaTemas(listaTemasMock);

        
        when(cpuMock.traerListaReproduccionPorId(idLista)).thenReturn(listaMock);

        
        boolean resultado = sistema.existeTemaLista(idTema, idLista);

        
        assertTrue(resultado, "El tema debería existir en la lista");
    }
    
    @Test
    public void testExisteTemaLista_TemaNoExiste() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        Long idTema = 1L;
        int idLista = 100;

        
        Tema otroTema = new Tema();
        otroTema.setId(2L);

        
        Set<Tema> listaTemasMock = new HashSet<>();
        listaTemasMock.add(otroTema);

        
        ListaReproduccion listaMock = new ListaReproduccion();
        listaMock.setListaTemas(listaTemasMock);

        
        when(cpuMock.traerListaReproduccionPorId(idLista)).thenReturn(listaMock);

        
        boolean resultado = sistema.existeTemaLista(idTema, idLista);

        
        assertFalse(resultado, "El tema no debería existir en la lista");
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// boolean isPublicaLista //////////////////////////////////////
    @Test
    public void testEsPublicaLista_ListaPublica() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        int idLista = 1;
        ListaParticular listaParticularMock = mock(ListaParticular.class);

        
        when(cpuMock.traerListaParticularPorId(idLista)).thenReturn(listaParticularMock);
        when(listaParticularMock.isPublica()).thenReturn(true);

        
        boolean resultado = sistema.esPublicaLista(idLista);
        assertTrue(resultado, "La lista debería ser pública");
    }

    @Test
    public void testEsPublicaLista_ListaNoPublica() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        int idLista = 2;
        ListaParticular listaParticularMock = mock(ListaParticular.class);

        
        when(cpuMock.traerListaParticularPorId(idLista)).thenReturn(listaParticularMock);
        when(listaParticularMock.isPublica()).thenReturn(false);

        
        boolean resultado = sistema.esPublicaLista(idLista);
        assertFalse(resultado, "La lista no debería ser pública");
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// Vector<DataAlbum> traerAlbumes //////////////////////////////////////
    @Test
    public void testTraerAlbumes_ListaNoVacia() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        Album album1 = mock(Album.class);
        Album album2 = mock(Album.class);

        
        DataAlbum dataAlbum1 = new DataAlbum("Album1", 2021, "imagen1.png", new ArrayList<>(), new ArrayList<>());
        DataAlbum dataAlbum2 = new DataAlbum("Album2", 2022, "imagen2.png", new ArrayList<>(), new ArrayList<>());

        
        when(album1.getDataAlbum()).thenReturn(dataAlbum1);
        when(album2.getDataAlbum()).thenReturn(dataAlbum2);

        
        List<Album> albumesSimulados = Arrays.asList(album1, album2);

        
        when(cpuMock.Listar_Albumes()).thenReturn(albumesSimulados);

        
        Vector<DataAlbum> resultado = sistema.traerAlbumes();

        
        assertNotNull(resultado);
        assertEquals(2, resultado.size(), "Debería haber 2 álbumes en la lista");

        
        assertEquals("Album1", resultado.get(0).getNombre());
        assertEquals("Album2", resultado.get(1).getNombre());
    }

    @Test
    public void testTraerAlbumes_ListaVacia() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        when(cpuMock.Listar_Albumes()).thenReturn(Collections.emptyList());

        
        Vector<DataAlbum> resultado = sistema.traerAlbumes();

        
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty(), "La lista debería estar vacía");
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// List<DataTema> traerTemasPorIdAlbum //////////////////////////////////////
    @Test
    public void testTraerTemasPorIdAlbum_AlbumConTemas() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        Long idAlbum = 1L;

        
        Tema tema1 = mock(Tema.class);
        Tema tema2 = mock(Tema.class);

        
        DataTema dataTema1 = new DataTema("Tema 1", "180",1, "url1", "album1");
        DataTema dataTema2 = new DataTema("Tema 2", "200",2, "url2", "album2");

        
        when(tema1.getDataTemaI()).thenReturn(dataTema1);
        when(tema2.getDataTemaI()).thenReturn(dataTema2);

        
        List<Tema> temasSimulados = Arrays.asList(tema1, tema2);

        
        when(cpuMock.traerTemasPorIdAlbum(idAlbum)).thenReturn(temasSimulados);

        
        List<DataTema> resultado = sistema.traerTemasPorIdAlbum(idAlbum);

        
        assertNotNull(resultado);
        assertEquals(2, resultado.size(), "Debería haber 2 temas en la lista");

        
        assertEquals("Tema 1", resultado.get(0).getNombre());
        assertEquals("Tema 2", resultado.get(1).getNombre());
    }

    @Test
    public void testTraerTemasPorIdAlbum_AlbumSinTemas() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        Long idAlbum = 1L;

        
        when(cpuMock.traerTemasPorIdAlbum(idAlbum)).thenReturn(Collections.emptyList());

        
        List<DataTema> resultado = sistema.traerTemasPorIdAlbum(idAlbum);

        
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty(), "La lista debería estar vacía");
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// void agregarTemaLista //////////////////////////////////////
    @Test
    public void testAgregarTemaLista() throws Exception {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        int idLista = 1;
        Long idTema = 2L;
        ListaParticular listaParticular = spy(new ListaParticular());
        listaParticular.setListaTemas(new HashSet<>()); 
        Tema nuevoTema = new Tema();

        when(cpuMock.traerListaParticularPorId(idLista)).thenReturn(listaParticular);
        when(cpuMock.traerTemaPorId(idTema)).thenReturn(nuevoTema);

        
        sistema.agregarTemaLista(idLista, idTema);

        
        verify(cpuMock).traerListaParticularPorId(idLista);
        verify(cpuMock).traerTemaPorId(idTema);
        verify(cpuMock).editarListaReproduccion(listaParticular);
        
        
        verify(listaParticular).agregarTema(nuevoTema);
    }

    @Test
    public void testAgregarTemaListaConListaPorDefecto() throws Exception {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        int idLista = 1;
        Long idTema = 2L;
        ListaPorDefecto listaPorDefecto = spy(new ListaPorDefecto());
        listaPorDefecto.setListaTemas(new HashSet<>()); 
        Tema nuevoTema = new Tema();

        when(cpuMock.traerListaParticularPorId(idLista)).thenReturn(null);
        when(cpuMock.traerListaPorDefectoPorId(idLista)).thenReturn(listaPorDefecto);
        when(cpuMock.traerTemaPorId(idTema)).thenReturn(nuevoTema);

        
        sistema.agregarTemaLista(idLista, idTema);

        
        verify(cpuMock).traerListaParticularPorId(idLista);
        verify(cpuMock).traerListaPorDefectoPorId(idLista);
        verify(cpuMock).traerTemaPorId(idTema);
        verify(cpuMock).editarListaReproduccion(listaPorDefecto);
        
        
        verify(listaPorDefecto).agregarTema(nuevoTema);
    } 
    
    @Test
    public void testAgregarTemaLista_CatchException() throws Exception {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        int idLista = 1;
        Long idTema = 2L;
        ListaParticular listaParticular = new ListaParticular();
        listaParticular.setListaTemas(new HashSet<>()); 
        Tema nuevoTema = new Tema();

        
        when(cpuMock.traerListaParticularPorId(idLista)).thenReturn(listaParticular);
        when(cpuMock.traerTemaPorId(idTema)).thenReturn(nuevoTema);

        
        doThrow(new RuntimeException("Error al editar la lista")).when(cpuMock).editarListaReproduccion(any(ListaReproduccion.class));

        
        try {
            sistema.agregarTemaLista(idLista, idTema);
        } catch (Exception e) {
            
            assertEquals("Error al editar la lista", e.getMessage());
        }

        
        verify(cpuMock).traerListaParticularPorId(idLista);
        verify(cpuMock).traerTemaPorId(idTema);
        verify(cpuMock).editarListaReproduccion(any(ListaReproduccion.class));

        
        assertTrue(listaParticular.getListaTemas().contains(nuevoTema));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// Vector<DataLista> traerListasParticularesPublicas //////////////////////////////////////
    @Test
    public void testTraerListasParticularesPublicas() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        ListaParticular lista1 = new ListaParticular("Lista 1", "imagen1.png", "cliente1");
        ListaParticular lista2 = new ListaParticular("Lista 2", "imagen2.png", "cliente2");
        lista1.setPublica(true);  
        lista2.setPublica(true);  
        
        
        List<ListaParticular> listasParticulares = List.of(lista1, lista2);
        
        
        when(cpuMock.traerListasParticularesPublicas()).thenReturn(listasParticulares);
        
        
        Vector<DataLista> resultado = sistema.traerListasParticularesPublicas();
        
        
        assertNotNull(resultado);
        
        assertEquals(listasParticulares.size(), resultado.size());
        
        
        for (int i = 0; i < listasParticulares.size(); i++) {
            DataLista dataListaEsperada = new DataLista(listasParticulares.get(i));
            assertEquals(dataListaEsperada.getNombre(), resultado.get(i).getNombre());
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// List<DataTema> traerTemasPorIdListaReproduccion //////////////////////////////////////
    @Test
    public void testTraerTemasPorIdListaReproduccion() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        int idListaReproduccion = 1;

        
        Set<Tema> listaTemas = new HashSet<>();

        Tema tema1 = new Tema();
        tema1.setId(1L);
        tema1.setNombre("Tema 1");
        tema1.setDuracion("300"); 
        tema1.setPosicion(1); 
        tema1.setDireccionWeb("http://example.com/t1");
        tema1.setArchivo("archivo1.mp3");
        listaTemas.add(tema1);

        Tema tema2 = new Tema();
        tema2.setId(2L);
        tema2.setNombre("Tema 2");
        tema2.setDuracion("250"); 
        tema2.setPosicion(2); 
        tema2.setDireccionWeb("http://example.com/t2");
        tema2.setArchivo("archivo2.mp3");
        listaTemas.add(tema2);

        
        when(cpuMock.traerTemasPorIdListaReproduccion(idListaReproduccion)).thenReturn(listaTemas);

        
        List<DataTema> resultado = sistema.traerTemasPorIdListaReproduccion(idListaReproduccion);

        
        assertNotNull(resultado);

        
        assertEquals(listaTemas.size(), resultado.size());

        
        boolean foundTema1 = resultado.stream().anyMatch(dataTema -> "Tema 1".equals(dataTema.getNombre()));
        boolean foundTema2 = resultado.stream().anyMatch(dataTema -> "Tema 2".equals(dataTema.getNombre()));

        assertTrue(foundTema1);
        assertTrue(foundTema2);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// List<DataLista> traerListasPorDefecto //////////////////////////////////////
    @Test
    public void testTraerListasPorDefecto() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        List<ListaPorDefecto> listasPorDefecto = new ArrayList<>();
        
        
        ListaPorDefecto lista1 = new ListaPorDefecto(); 
        lista1.setId(1); 
        lista1.setNombre("Lista de Prueba 1");

        
        Set<Tema> temas1 = new HashSet<>();
        Tema tema1 = new Tema("Tema 1", "3:45", 1); 
        tema1.setId(1L); 
        temas1.add(tema1);
        lista1.setListaTemas(temas1); 

        listasPorDefecto.add(lista1);
        
        
        ListaPorDefecto lista2 = new ListaPorDefecto();
        lista2.setId(2);
        lista2.setNombre("Lista de Prueba 2");

        
        Set<Tema> temas2 = new HashSet<>();
        Tema tema2 = new Tema("Tema 2", "4:20", 2); 
        tema2.setId(2L); 
        temas2.add(tema2);
        lista2.setListaTemas(temas2);

        listasPorDefecto.add(lista2);

        
        when(cpuMock.Listar_Listas_Por_Defecto()).thenReturn(listasPorDefecto);

        
        List<DataLista> resultado = sistema.traerListasPorDefecto();

        
        assertNotNull(resultado);

        
        assertEquals(listasPorDefecto.size(), resultado.size());

        
        boolean foundLista1 = resultado.stream().anyMatch(dataLista -> "Lista de Prueba 1".equals(dataLista.getNombre()));
        boolean foundLista2 = resultado.stream().anyMatch(dataLista -> "Lista de Prueba 2".equals(dataLista.getNombre()));

        assertTrue(foundLista1);
        assertTrue(foundLista2);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// List<DataTema> traerTemasPorListasPorDefectoId //////////////////////////////////////
    @Test
    public void testTraerTemasPorListasPorDefectoId() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        int listaId = 1;

        
        ListaPorDefecto listaDefecto = new ListaPorDefecto();
        listaDefecto.setId(listaId); 
        listaDefecto.setNombre("Lista de Prueba");

        
        Set<Tema> temas = new HashSet<>();
        Tema tema1 = new Tema("Tema 1", "3:30", 1);
        tema1.setId(1L);
        temas.add(tema1);

        Tema tema2 = new Tema("Tema 2", "4:15", 2);
        tema2.setId(2L);
        temas.add(tema2);

        
        listaDefecto.setListaTemas(temas);

        
        when(cpuMock.traerListaPorDefectoPorId(listaId)).thenReturn(listaDefecto);

        
        List<DataTema> resultado = sistema.traerTemasPorListasPorDefectoId(listaId);

        
        assertNotNull(resultado);

        
        assertEquals(temas.size(), resultado.size());

        
        boolean foundTema1 = resultado.stream().anyMatch(dataTema -> "Tema 1".equals(dataTema.getNombre()));
        boolean foundTema2 = resultado.stream().anyMatch(dataTema -> "Tema 2".equals(dataTema.getNombre()));

        assertTrue(foundTema1);
        assertTrue(foundTema2);
    }
    
    @Test
    public void testTraerListaPorDefectoNombreNoIgual() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        ListaPorDefecto lista1 = new ListaPorDefecto("Lista 1", "imagen1", "genero1");
        ListaPorDefecto lista2 = new ListaPorDefecto("Lista 2", "imagen2", "genero2");

        
        when(cpuMock.Listar_Listas_Por_Defecto()).thenReturn(Arrays.asList(lista1, lista2));

        
        String nombreBuscado = "Lista Inexistente";

        
        ListaPorDefecto resultado = sistema.traerListaPorDefecto(nombreBuscado);

        
        assertNull(resultado, "Se esperaba que el resultado fuera null ya que no hay coincidencias.");
        
        
        verify(cpuMock, times(1)).Listar_Listas_Por_Defecto();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// List<DataLista> traerListasFavoritasPorIdCliente //////////////////////////////////////
    @Test
    public void testTraerListasFavoritasPorIdCliente() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        String nickname = "clientePrueba";

        
        Cliente cliente = new Cliente(nickname, "Nombre", "Apellido", "password", "email@example.com", LocalDate.now(), "imagenPerfil", "tipo");

        
        List<ListaReproduccion> listasFavoritas = new ArrayList<>();
        
        ListaReproduccion lista1 = new ListaReproduccion();
        lista1.setId(1);
        lista1.setNombre("Lista Favorita 1");
        listasFavoritas.add(lista1);

        ListaReproduccion lista2 = new ListaReproduccion();
        lista2.setId(2);
        lista2.setNombre("Lista Favorita 2");
        listasFavoritas.add(lista2);

        
        cliente.setListasFavoritas((ArrayList<ListaReproduccion>) listasFavoritas);

        
        when(cpuMock.obtenerCliente(nickname)).thenReturn(cliente);

        
        List<DataLista> resultado = sistema.traerListasFavoritasPorIdCliente(nickname);

        
        assertNotNull(resultado);

        
        assertEquals(listasFavoritas.size(), resultado.size());

        
        boolean foundLista1 = resultado.stream().anyMatch(dataLista -> "Lista Favorita 1".equals(dataLista.getNombre()));
        boolean foundLista2 = resultado.stream().anyMatch(dataLista -> "Lista Favorita 2".equals(dataLista.getNombre()));

        assertTrue(foundLista1);
        assertTrue(foundLista2);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// void eliminarListaFavoritaCliente //////////////////////////////////////
    @Test
    public void testEliminarListaFavoritaCliente() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        String nickname = "clientePrueba";
        int idLista = 1;

        
        Cliente cliente = new Cliente(nickname, "Nombre", "Apellido", "password", "email@example.com", LocalDate.now(), "imagenPerfil", "tipo");

        
        ListaReproduccion listaFavorita = new ListaReproduccion();
        listaFavorita.setId(idLista);
        listaFavorita.setNombre("Lista Favorita 1");

        
        List<ListaReproduccion> listasFavoritas = new ArrayList<>();
        listasFavoritas.add(listaFavorita);

        
        cliente.setListasFavoritas((ArrayList<ListaReproduccion>) listasFavoritas);

        
        when(cpuMock.obtenerCliente(nickname)).thenReturn(cliente);

        
        sistema.eliminarListaFavoritaCliente(nickname, idLista);

        
        assertFalse(cliente.getListasFavoritas().contains(listaFavorita));

        
        verify(cpuMock).editarCliente(cliente);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// List<DataTema> traerTemasFavoritosPorIdCliente //////////////////////////////////////
    @Test
    public void testTraerTemasFavoritosPorIdCliente() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        String nickname = "clientePrueba";

        
        Cliente cliente = new Cliente(nickname, "Nombre", "Apellido", "password", "email@example.com", LocalDate.now(), "imagenPerfil", "tipo");

        
        List<Tema> listaTemasFavoritos = new ArrayList<>();

        
        Tema tema1 = new Tema("Tema 1", "3:30", 1);
        tema1.setId(1L); 
        listaTemasFavoritos.add(tema1);

        Tema tema2 = new Tema("Tema 2", "4:15", 2);
        tema2.setId(2L); 
        listaTemasFavoritos.add(tema2);

        
        cliente.setTemasFavoritos((ArrayList<Tema>) listaTemasFavoritos);

        
        when(cpuMock.obtenerCliente(nickname)).thenReturn(cliente);

        
        List<DataTema> result = sistema.traerTemasFavoritosPorIdCliente(nickname);

        
        assertEquals(2, result.size());
        assertEquals("Tema 1", result.get(0).getNombre());
        assertEquals("Tema 2", result.get(1).getNombre());

        
        verify(cpuMock).obtenerCliente(nickname);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// void eliminarTemasFavoritosCliente //////////////////////////////////////
    @Test
    public void testEliminarTemasFavoritosCliente() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        String nickname = "clientePrueba";
        Long idTema = 1L;

        
        Cliente cliente = new Cliente(nickname, "Nombre", "Apellido", "password", "email@example.com", LocalDate.now(), "imagenPerfil", "tipo");

        
        List<Tema> listaTemasFavoritos = new ArrayList<>();
        Tema temaFavorito = new Tema("Tema Favorito", "3:30", 1);
        temaFavorito.setId(idTema);
        listaTemasFavoritos.add(temaFavorito);
        cliente.setTemasFavoritos((ArrayList<Tema>) listaTemasFavoritos); 

        
        when(cpuMock.obtenerCliente(nickname)).thenReturn(cliente);

        
        sistema.eliminarTemasFavoritosCliente(nickname, idTema);

        
        assertFalse(cliente.getTemasFavoritos().contains(temaFavorito), "El tema favorito debería haber sido eliminado.");

        
        verify(cpuMock).editarCliente(cliente);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// List<DataAlbum> traerAlbumesFavoritosPorIdCliente //////////////////////////////////////
    @Test
    public void testTraerAlbumesFavoritosPorIdCliente() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        String nickname = "clientePrueba";

        
        Cliente cliente = new Cliente(nickname, "Nombre", "Apellido", "password", "email@example.com", LocalDate.now(), "imagenPerfil", "tipo");
        
        Artista artista = new Artista(
        "artistaPrueba",                
        "NombreArtista",               
        "ApellidoArtista",             
        "passwordArtista",             
        "artista@example.com",         
        LocalDate.now(),               
        "imagenArtista.jpg",           
        "Biografía del artista",       
        "www.artista.com",             
        "artista"                      
    );
        
        List<Album> listaAlbumesFavoritos = new ArrayList<>();
        Album albumFavorito = new Album("Album Favorito", 2021, "imagenAlbum");
        albumFavorito.setArtista(artista);
        listaAlbumesFavoritos.add(albumFavorito);
        cliente.setAlbumesFavoritos((ArrayList<Album>) listaAlbumesFavoritos); 

        
        when(cpuMock.obtenerCliente(nickname)).thenReturn(cliente);

        
        List<DataAlbum> result = sistema.traerAlbumesFavoritosPorIdCliente(nickname);

        
        assertEquals(1, result.size(), "Debería haber un álbum favorito en la lista.");
        assertEquals(albumFavorito.getDataAlbum(), result.get(0), "El álbum favorito debería coincidir con el esperado.");

        
        verify(cpuMock).obtenerCliente(nickname);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// void eliminarAlbumesFavoritosCliente //////////////////////////////////////
    @Test
    public void testEliminarAlbumesFavoritosCliente() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        String nickname = "clientePrueba";

        
        Cliente cliente = new Cliente(nickname, "Nombre", "Apellido", "password", "email@example.com", LocalDate.now(), "imagenPerfil", "tipo");

        
        List<Album> listaAlbumesFavoritos = new ArrayList<>();

        
        Album album1 = new Album("Album 1", 2000,"imagen1");
        album1.setId(1L); 
        listaAlbumesFavoritos.add(album1);

        Album album2 = new Album("Album 2", 2000,"imagen2");
        album2.setId(2L); 
        listaAlbumesFavoritos.add(album2);

        
        cliente.setAlbumesFavoritos((ArrayList<Album>) listaAlbumesFavoritos); 

        
        when(cpuMock.obtenerCliente(nickname)).thenReturn(cliente);

        
        Long idAlbumAEliminar = 1L;

        
        sistema.eliminarAlbumesFavoritosCliente(nickname, idAlbumAEliminar);

        
        assertFalse(cliente.getAlbumesFavoritos().stream().anyMatch(a -> a.getId().equals(idAlbumAEliminar)));

        
        verify(cpuMock).editarCliente(cliente);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// List<DataLista> getListasParticulares //////////////////////////////////////
    @Test
    public void testGetListasParticulares() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        String nickname = "clientePrueba";

        
        List<ListaParticular> listasParticulares = new ArrayList<>();

        
        ListaParticular lp1 = new ListaParticular("Lista 1", "imagen1.jpg", nickname);
        lp1.setId(1); 
        listasParticulares.add(lp1);

        ListaParticular lp2 = new ListaParticular("Lista 2", "imagen2.jpg", nickname);
        lp2.setId(2); 
        listasParticulares.add(lp2);

        
        when(cpuMock.traerListasParticularesPorCliente(nickname)).thenReturn(listasParticulares);

        
        List<DataLista> result = sistema.getListasParticulares(nickname);

        
        assertEquals(2, result.size());
        assertEquals("Lista 1", result.get(0).getNombre());
        assertEquals("Lista 2", result.get(1).getNombre());

        
        verify(cpuMock).traerListasParticularesPorCliente(nickname);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// DataLista getListaDefecto //////////////////////////////////////
    @Test
    public void testGetListaDefecto() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        String nombreLista = "Lista Defecto";
        String genero = "Pop"; 

        
        List<ListaPorDefecto> listasPorDefecto = new ArrayList<>();

        
        ListaPorDefecto listaPorDefecto = new ListaPorDefecto(nombreLista, "imagenDefecto.jpg", genero);
        listaPorDefecto.setId(1); 
        listasPorDefecto.add(listaPorDefecto);

        
        when(cpuMock.Listar_Listas_Por_Defecto()).thenReturn(listasPorDefecto);

        
        DataLista result = sistema.getListaDefecto(nombreLista);

        
        assertNotNull(result);
        assertEquals(nombreLista, result.getNombre());
        assertEquals(1, result.getId()); 

        
        verify(cpuMock).Listar_Listas_Por_Defecto();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// DataGenero[] getGeneros2 ALLT//////////////////////////////////////
    @Test
    public void testGetGeneros2ConGeneros() throws GeneroNoExisteException {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        List<Genero> listaGeneros = new ArrayList<>();
        Genero genero1 = new Genero("Pop");
        Genero genero2 = new Genero("Rock");

        
        when(cpuMock.obtenerTodosLosGeneros()).thenReturn(listaGeneros);
        listaGeneros.add(genero1);
        listaGeneros.add(genero2);

        
        DataGenero[] result = sistema.getGeneros2();

        
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals(genero1.devolverData(), result[0]);
        assertEquals(genero2.devolverData(), result[1]);
        
        
        DataGenero gentest = new DataGenero("asdasd","asdasd2");
        DataGenero gent = new DataGenero();
        assertNotNull(gent);
        gentest.setNombre("sd");
        gentest.setPadre("qwe");
        gentest.hashCode();
        gentest.getNombre();
        gentest.getPadre();

        
        verify(cpuMock).obtenerTodosLosGeneros();
    }

    @Test
    public void testGetGeneros2SinGeneros() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        when(cpuMock.obtenerTodosLosGeneros()).thenReturn(new ArrayList<>());

        
        assertThrows(GeneroNoExisteException.class, () -> sistema.getGeneros2());

        
        verify(cpuMock).obtenerTodosLosGeneros();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// DataAlbum[] getAlbumsByGenero //////////////////////////////////////
    @Test
    public void testGetAlbumsByGeneroConAlbumes() throws AlbumNoExisteException {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        Artista artista = new Artista(
            "artistaPrueba",              
            "NombreArtista",             
            "ApellidoArtista",            
            "passwordArtista",             
            "artista@example.com",         
            LocalDate.now(),              
            "imagenArtista.jpg",           
            "Biografía del artista",      
            "www.artista.com",             
            "artista"                      
        );
        String genero = "Pop";

        
        List<Album> listaAlbumes = new ArrayList<>();

        
        Genero genero1 = new Genero("Pop");
        Genero genero2 = new Genero("Rock");

        
        Album album1 = new Album("Álbum 1", 2020, "imagen1.jpg");
        album1.setId(1L); 
        album1.addGenero(genero1);
        album1.setArtista(artista);

        Album album2 = new Album("Álbum 2", 2021, "imagen2.jpg");
        album2.setId(2L); 
        album2.addGenero(genero2);
        album2.setArtista(artista);

        
        listaAlbumes.add(album1);
        listaAlbumes.add(album2);

        
        when(cpuMock.obtenerAlbumPorGenero(genero)).thenReturn(listaAlbumes);

        
        DataAlbum[] result = sistema.getAlbumsByGenero(genero);

        
        assertEquals(2, result.length);
        assertEquals(album1.devolverData(), result[0]);
        assertEquals(album2.devolverData(), result[1]);

        
        verify(cpuMock).obtenerAlbumPorGenero(genero);
    }

    @Test
    public void testGetAlbumsByGeneroSinAlbumes() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        String genero = "Jazz";

        
        when(cpuMock.obtenerAlbumPorGenero(genero)).thenReturn(new ArrayList<>());

        
        assertThrows(AlbumNoExisteException.class, () -> sistema.getAlbumsByGenero(genero));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// void eliminarTemasListaPart ALLT //////////////////////////////////////
    @Test
    void testEliminarTemasListaPart() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        String nombreCliente = "TestCliente";
        String nombreLista = "TestLista";
        Long idTema = 1L;

        
        ListaParticular listaPartMock = mock(ListaParticular.class);
        DataLista dataListaMock = mock(DataLista.class);

        
        Sistema sistemaSpy = spy(sistema);
        doReturn(dataListaMock).when(sistemaSpy).traerListaPartClienteNombre(nombreCliente, nombreLista);

        
        when(dataListaMock.getId()).thenReturn(1);
        when(cpuMock.traerListaReproduccionPorId(1)).thenReturn(listaPartMock);

        
        sistemaSpy.eliminarTemasListaPart(nombreCliente, nombreLista, idTema);

        
        verify(sistemaSpy).traerListaPartClienteNombre(nombreCliente, nombreLista);
        verify(cpuMock).traerListaReproduccionPorId(1);
        verify(listaPartMock).eliminarTema(idTema);
        verify(cpuMock).editarListaReproduccion(listaPartMock);
        verify(dataListaMock).eliminarTema(idTema);     
       
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// void eliminarTemaListaDef //////////////////////////////////////
    @Test
    void testEliminarTemaListaDef() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        String nombre = "MiLista";
        Long idTema = 1L;
        int idLista = 10;

        DataLista dtL = mock(DataLista.class);
        ListaPorDefecto listaD = mock(ListaPorDefecto.class);

        
        Sistema sistemaSpy = spy(sistema);
        doReturn(dtL).when(sistemaSpy).getListaDefecto(nombre);

        
        when(dtL.getId()).thenReturn(idLista);

        
        when(cpuMock.traerListaPorDefectoPorId(idLista)).thenReturn(listaD);

        
        sistemaSpy.eliminarTemaListaDef(nombre, idTema);

        
        verify(sistemaSpy).getListaDefecto(nombre);
        verify(dtL).getId();
        verify(cpuMock).traerListaPorDefectoPorId(idLista);
        verify(listaD).eliminarTema(idTema);
        verify(cpuMock).editarListaReproduccion(listaD);
        verify(dtL).eliminarTema(idTema);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// boolean Encontrar_Lista_Por_Defecto //////////////////////////////////////
    @Test
    void testEncontrarListaPorDefecto_Existente() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        String nombreBuscado = "Lista1";
        ListaPorDefecto lista1 = mock(ListaPorDefecto.class);
        ListaPorDefecto lista2 = mock(ListaPorDefecto.class);
        
        when(lista1.getNombre()).thenReturn("Lista1");
        when(lista2.getNombre()).thenReturn("Lista2");
        
        List<ListaPorDefecto> listas = Arrays.asList(lista1, lista2);
        when(cpuMock.Listar_Listas_Por_Defecto()).thenReturn(listas);

        
        boolean resultado = sistema.Encontrar_Lista_Por_Defecto(nombreBuscado);

        
        assertTrue(resultado);
        verify(cpuMock).Listar_Listas_Por_Defecto();
    }

    @Test
    void testEncontrarListaPorDefecto_NoExistente() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        String nombreBuscado = "ListaInexistente";
        ListaPorDefecto lista1 = mock(ListaPorDefecto.class);
        
        when(lista1.getNombre()).thenReturn("Lista1");
        
        List<ListaPorDefecto> listas = Collections.singletonList(lista1);
        when(cpuMock.Listar_Listas_Por_Defecto()).thenReturn(listas);

        
        boolean resultado = sistema.Encontrar_Lista_Por_Defecto(nombreBuscado);

        
        assertFalse(resultado);
        verify(cpuMock).Listar_Listas_Por_Defecto();
    }

    @Test
    void testEncontrarListaPorDefecto_ListaVacia() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        String nombreBuscado = "CualquierNombre";
        
        when(cpuMock.Listar_Listas_Por_Defecto()).thenReturn(Collections.emptyList());

        
        boolean resultado = sistema.Encontrar_Lista_Por_Defecto(nombreBuscado);

        
        assertFalse(resultado);
        verify(cpuMock).Listar_Listas_Por_Defecto();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// boolean Encontrar_Lista_Particular //////////////////////////////////////
    @Test
    void testEncontrarListaParticular_Existente() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        String nombreClienteBuscado = "Cliente1";
        String nombreListaBuscada = "Lista1";
        ListaParticular lista1 = mock(ListaParticular.class);
        ListaParticular lista2 = mock(ListaParticular.class);
        
        when(lista1.getNombre()).thenReturn("Lista1");
        when(lista1.getCliente()).thenReturn("Cliente1");
        when(lista2.getNombre()).thenReturn("Lista2");
        when(lista2.getCliente()).thenReturn("Cliente2");
        
        List<ListaParticular> listas = Arrays.asList(lista1, lista2);
        when(cpuMock.Listar_Listas_Particulares()).thenReturn(listas);

        
        boolean resultado = sistema.Encontrar_Lista_Particular(nombreClienteBuscado, nombreListaBuscada);

        
        assertTrue(resultado);
        verify(cpuMock).Listar_Listas_Particulares();
    }

    @Test
    void testEncontrarListaParticular_NombreListaNoCoincide() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        String nombreClienteBuscado = "Cliente1";
        String nombreListaBuscada = "ListaInexistente";
        ListaParticular lista1 = mock(ListaParticular.class);
        
        when(lista1.getNombre()).thenReturn("Lista1");
        when(lista1.getCliente()).thenReturn("Cliente1");
        
        List<ListaParticular> listas = Collections.singletonList(lista1);
        when(cpuMock.Listar_Listas_Particulares()).thenReturn(listas);

        
        boolean resultado = sistema.Encontrar_Lista_Particular(nombreClienteBuscado, nombreListaBuscada);

        
        assertFalse(resultado);
        verify(cpuMock).Listar_Listas_Particulares();
    }

    @Test
    void testEncontrarListaParticular_NombreClienteNoCoincide() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        String nombreClienteBuscado = "ClienteInexistente";
        String nombreListaBuscada = "Lista1";
        ListaParticular lista1 = mock(ListaParticular.class);
        
        when(lista1.getNombre()).thenReturn("Lista1");
        when(lista1.getCliente()).thenReturn("Cliente1");
        
        List<ListaParticular> listas = Collections.singletonList(lista1);
        when(cpuMock.Listar_Listas_Particulares()).thenReturn(listas);

        
        boolean resultado = sistema.Encontrar_Lista_Particular(nombreClienteBuscado, nombreListaBuscada);

        
        assertFalse(resultado);
        verify(cpuMock).Listar_Listas_Particulares();
    }

    @Test
    void testEncontrarListaParticular_ListaVacia() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        String nombreClienteBuscado = "CualquierCliente";
        String nombreListaBuscada = "CualquierLista";
        
        when(cpuMock.Listar_Listas_Particulares()).thenReturn(Collections.emptyList());

        
        boolean resultado = sistema.Encontrar_Lista_Particular(nombreClienteBuscado, nombreListaBuscada);

        
        assertFalse(resultado);
        verify(cpuMock).Listar_Listas_Particulares();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// ListaParticular traerListaParticularPorCliente //////////////////////////////////////
    @Test
    public void testTraerListaParticularPorClienteNombreIgual() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        ListaParticular lista1 = new ListaParticular("Lista 1", "imagen1", "cliente1");
        ListaParticular lista2 = new ListaParticular("Lista 2", "imagen2", "cliente1");
        ListaParticular lista3 = new ListaParticular("Lista 3", "imagen3", "cliente2");

        
        when(cpuMock.traerListasParticularesPorCliente("cliente1"))
                .thenReturn(Arrays.asList(lista1, lista2, lista3));

        
        String nombreBuscado = "Lista 1";
        String nicknameBuscado = "cliente1";

        
        ListaParticular resultado = sistema.traerListaParticularPorCliente(nombreBuscado, nicknameBuscado);

        
        assertNotNull(resultado, "Se esperaba que se encontrara una lista particular.");
        assertEquals("Lista 1", resultado.getNombre(), "El nombre de la lista encontrada debería ser 'Lista 1'.");
        
        
        verify(cpuMock, times(1)).traerListasParticularesPorCliente(nicknameBuscado);
    }

    @Test
    public void testTraerListaParticularPorClienteNombreNoIgual() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        ListaParticular lista1 = new ListaParticular("Lista 1", "imagen1", "cliente1");
        ListaParticular lista2 = new ListaParticular("Lista 2", "imagen2", "cliente1");

        
        when(cpuMock.traerListasParticularesPorCliente("cliente1"))
                .thenReturn(Arrays.asList(lista1, lista2));

        
        String nombreBuscado = "Lista Inexistente";
        String nicknameBuscado = "cliente1";

        
        ListaParticular resultado = sistema.traerListaParticularPorCliente(nombreBuscado, nicknameBuscado);

        
        assertNull(resultado, "Se esperaba que no se encontrara ninguna lista particular.");
        
        
        verify(cpuMock, times(1)).traerListasParticularesPorCliente(nicknameBuscado);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// ListaPorDefecto traerListaPorDefecto //////////////////////////////////////
    @Test
    public void testTraerListaPorDefectoConNombreIgual() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        ListaPorDefecto lista1 = new ListaPorDefecto("Lista Clásica", "imagen1", "clásico");
        ListaPorDefecto lista2 = new ListaPorDefecto("Lista Moderna", "imagen2", "moderno");

        
        when(cpuMock.Listar_Listas_Por_Defecto())
                .thenReturn(Arrays.asList(lista1, lista2));

        
        String nombreBuscado = "Lista Clásica";

        
        ListaPorDefecto resultado = sistema.traerListaPorDefecto(nombreBuscado);

        
        assertNotNull(resultado, "Se esperaba que se encontrara una lista por defecto.");
        assertEquals("Lista Clásica", resultado.getNombre(), "El nombre de la lista encontrada debería ser 'Lista Clásica'.");
        
        
        verify(cpuMock, times(1)).Listar_Listas_Por_Defecto();
    }

    @Test
    public void testTraerListaPorDefectoConNombreNoIgual() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        ListaPorDefecto lista1 = new ListaPorDefecto("Lista Clásica", "imagen1", "clásico");
        ListaPorDefecto lista2 = new ListaPorDefecto("Lista Moderna", "imagen2", "moderno");

        
        when(cpuMock.Listar_Listas_Por_Defecto())
                .thenReturn(Arrays.asList(lista1, lista2));

        
        String nombreBuscado = "Lista Inexistente";

        
        ListaPorDefecto resultado = sistema.traerListaPorDefecto(nombreBuscado);

        
        assertNull(resultado, "Se esperaba que no se encontrara ninguna lista por defecto.");
        
        
        verify(cpuMock, times(1)).Listar_Listas_Por_Defecto();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// List<Tema> getTemas //////////////////////////////////////
    @Test
    public void testGetTemas() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        Album album = new Album("Álbum Test", 2022, "imagenTest");
        Tema tema1 = new Tema("Tema 1", "Artista 1",1);
        Tema tema2 = new Tema("Tema 2", "Artista 2",2);

        
        when(cpuMock.traerTemasPorIdAlbum(album.getId()))
                .thenReturn(Arrays.asList(tema1, tema2));

        
        List<Tema> resultados = sistema.getTemas(album);

        
        assertNotNull(resultados, "Se esperaba que la lista de temas no fuera nula.");
        assertEquals(2, resultados.size(), "Se esperaba que hubiera 2 temas en la lista.");
        assertTrue(resultados.contains(tema1), "Se esperaba que la lista contuviera 'Tema 1'.");
        assertTrue(resultados.contains(tema2), "Se esperaba que la lista contuviera 'Tema 2'.");

        
        verify(cpuMock, times(1)).traerTemasPorIdAlbum(album.getId());
    }

    @Test
    public void testGetTemasConAlbumSinTemas() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        Album album = new Album("Álbum Vacío", 2022, "imagenVacia");

        
        when(cpuMock.traerTemasPorIdAlbum(album.getId()))
                .thenReturn(Arrays.asList());

        
        List<Tema> resultados = sistema.getTemas(album);

        
        assertNotNull(resultados, "Se esperaba que la lista de temas no fuera nula.");
        assertTrue(resultados.isEmpty(), "Se esperaba que la lista de temas estuviera vacía.");

        
        verify(cpuMock, times(1)).traerTemasPorIdAlbum(album.getId());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// ListaReproduccion getLista //////////////////////////////////////
    @Test
    public void testGetLista_Found() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        String nombreLista = "Mi Lista";
        ListaReproduccion listaMock = mock(ListaReproduccion.class);

        
        when(cpuMock.obtenerListaPorNombre(nombreLista)).thenReturn(listaMock);

        
        ListaReproduccion result = sistema.getLista(nombreLista);

        
        assertNotNull(result);  
        assertEquals(listaMock, result);  
        verify(cpuMock).obtenerListaPorNombre(nombreLista);  
    }

    @Test
    public void testGetLista_NotFound() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        String nombreLista = "Lista Inexistente";

        
        when(cpuMock.obtenerListaPorNombre(nombreLista)).thenReturn(null);

        
        ListaReproduccion result = sistema.getLista(nombreLista);

        
        assertNull(result);  
        verify(cpuMock).obtenerListaPorNombre(nombreLista);  
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// AGREGADAS SERVIDOR WEB //////////////////////////////////////
    
    ////////////////////////////////////// boolean tipoLista //////////////////////////////////////
    @Test
    public void testTipoLista_ListaParticular() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        int idListaParticular = 1;

        
        when(cpuMock.findLista(idListaParticular)).thenReturn(new ListaParticular());

        
        boolean resultado = sistema.tipoLista(idListaParticular);

        
        assertTrue(resultado, "Se esperaba true para una lista de tipo ListaParticular");
    }

    @Test
    public void testTipoLista_ListaPorDefecto() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        int idListaPorDefecto = 2;

        
        when(cpuMock.findLista(idListaPorDefecto)).thenReturn(new ListaPorDefecto());

        
        boolean resultado = sistema.tipoLista(idListaPorDefecto);

        
        assertFalse(resultado, "Se esperaba false para una lista de tipo ListaPorDefecto");
    }

    @Test
    public void testTipoLista_ListaNoExiste() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        
        int idListaInexistente = 3;

        
        when(cpuMock.findLista(idListaInexistente)).thenReturn(null);

        
        boolean resultado = sistema.tipoLista(idListaInexistente);

        
        assertFalse(resultado, "Se esperaba false cuando la lista no existe");
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// List<DataAlbum> getAllAlbums() //////////////////////////////////////
    @Test
    public void testGetAllAlbums() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

        Collection<DataTema> temasVacios = new ArrayList<>();
        Collection<String> generosVacios = new ArrayList<>();
        
        DataAlbum album1 = new DataAlbum("Album 1", 2022, "imagen1.jpg",temasVacios,generosVacios);
        DataAlbum album2 = new DataAlbum("Album 1", 2022, "imagen1.jpg",temasVacios,generosVacios);
        List<DataAlbum> albumesEsperados = Arrays.asList(album1, album2);

        
        when(cpuMock.getAllAlbumes()).thenReturn(albumesEsperados);

        
        List<DataAlbum> resultado = sistema.getAllAlbums();

        
        assertNotNull(resultado, "La lista de álbumes no debería ser null");
        assertEquals(albumesEsperados.size(), resultado.size(), "La cantidad de álbumes debería coincidir");
        assertEquals(albumesEsperados, resultado, "La lista de álbumes devuelta no coincide con la esperada");

        
        verify(cpuMock, times(1)).getAllAlbumes();
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// DataSub darSubUsuario //////////////////////////////////////
    @Test
    public void testDarSubUsuario() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);

        
        Sistema sistema = new Sistema(cpuMock);

       
        String nicknameCliente = "clienteTest";

        
        Cliente clienteMock = mock(Cliente.class);
        when(clienteMock.getNickname()).thenReturn(nicknameCliente);

       
        Subscripcion subscripcionMock = mock(Subscripcion.class);
        when(subscripcionMock.getTipo()).thenReturn("Premium");
        when(subscripcionMock.getEstado()).thenReturn("Activa"); // Cambiado a "Activa"
        when(subscripcionMock.getInicio()).thenReturn(LocalDate.of(2023, 1, 1));
        when(subscripcionMock.getFin()).thenReturn(LocalDate.of(2024, 1, 1));
        when(subscripcionMock.getCli()).thenReturn(clienteMock);

        
        when(clienteMock.getSub()).thenReturn(subscripcionMock);

        
        when(cpuMock.obtenerCliente(nicknameCliente)).thenReturn(clienteMock);

        
        DataSub resultado = sistema.darSubUsuario(nicknameCliente);

        
        assertNotNull(resultado, "El resultado no debería ser null");
        assertEquals("Premium", resultado.getTipo(), "El tipo de suscripción no coincide");
        assertEquals("Activa", resultado.getEstado(), "El estado de suscripción no coincide"); 
        assertEquals(LocalDate.of(2023, 1, 1), resultado.getInicio(), "La fecha de inicio no coincide");
        assertEquals(LocalDate.of(2024, 1, 1), resultado.getFin(), "La fecha de fin no coincide");
        assertEquals(nicknameCliente, resultado.getCli(), "El nickname del cliente no coincide");

        
        verify(cpuMock, times(1)).obtenerCliente(nicknameCliente);
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// List<DataLista> traerListasPublicasTodas() //////////////////////////////////////
    @Test
    public void testTraerListasPublicasTodas() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        ListaParticular listaParticular1 = new ListaParticular("Lista 1", "imagen1.jpg", "cliente1");
        ListaParticular listaParticular2 = new ListaParticular("Lista 2", "imagen2.jpg", "cliente2");

        
        listaParticular1.setPublica(true);
        listaParticular2.setPublica(true);

        
        Tema tema1 = new Tema("Tema 1", "Artista 1", 1);
        Tema tema2 = new Tema("Tema 2", "Artista 2", 2);

        listaParticular1.agregarTema(tema1);
        listaParticular2.agregarTema(tema2);

        
        List<ListaParticular> listasParticularesPublicasMock = new ArrayList<>();
        listasParticularesPublicasMock.add(listaParticular1);
        listasParticularesPublicasMock.add(listaParticular2);

        
        when(cpuMock.traerListasParticularesPublicas()).thenReturn(listasParticularesPublicasMock);

        
        List<DataLista> resultado = sistema.traerListasPublicasTodas();

        
        assertNotNull(resultado, "El resultado no debería ser null");
        assertEquals(2, resultado.size(), "Debería haber 2 listas públicas");

        
        assertEquals(listaParticular1.getNombre(), resultado.get(0).getNombre(), "El nombre de la primera lista no coincide");
        assertEquals(listaParticular2.getNombre(), resultado.get(1).getNombre(), "El nombre de la segunda lista no coincide");

        
        assertEquals(1, resultado.get(0).getTemas().size(), "La primera lista debería tener 1 tema");
        assertEquals(1, resultado.get(1).getTemas().size(), "La segunda lista debería tener 1 tema");

        
        verify(cpuMock, times(1)).traerListasParticularesPublicas();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// List<DataSub> listarSubsPendientes() //////////////////////////////////////
//    @Test
//    public void testListarSubsPendientes() {
//        
//        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
//        Sistema sistema = new Sistema(cpuMock);
//
//        
//        Cliente cliente1 = new Cliente("user1", "Juan", "Pérez", "pass123", "juan@mail.com", 
//                                     LocalDate.of(1990, 1, 1), "imagen1.jpg", "Cliente");
//        Cliente cliente2 = new Cliente("user2", "María", "López", "pass456", "maria@mail.com", 
//                                     LocalDate.of(1992, 2, 2), "imagen2.jpg", "Cliente");
//
//        
//        Subscripcion subPendiente1 = new Subscripcion();
//        subPendiente1.setId(1);
//        subPendiente1.setEstado("Pendiente");
//        subPendiente1.setTipo("Mensual");
//        subPendiente1.setInicio(LocalDate.now());
//        subPendiente1.setCli(cliente1);
//        cliente1.setSub(subPendiente1);
//
//        Subscripcion subPendiente2 = new Subscripcion();
//        subPendiente2.setId(2);
//        subPendiente2.setEstado("Pendiente");
//        subPendiente2.setTipo("Anual");
//        subPendiente2.setInicio(LocalDate.now());
//        subPendiente2.setCli(cliente2);
//        cliente2.setSub(subPendiente2);
//
//        Subscripcion subAprobada = new Subscripcion();
//        subAprobada.setId(3);
//        subAprobada.setEstado("Vigente");
//        subAprobada.setTipo("Semanal");
//        subAprobada.setInicio(LocalDate.now());
//        
//
//        
//        List<Subscripcion> todasLasSubs = new ArrayList<>();
//        todasLasSubs.add(subPendiente1);
//        todasLasSubs.add(subAprobada);
//        todasLasSubs.add(subPendiente2);
//
//        
//        when(cpuMock.listarSubs()).thenReturn(todasLasSubs);
//
//        
//        List<DataSub> resultado = sistema.listarSubsPendientes();
//
//        
//        assertNotNull(resultado, "La lista de resultado no debería ser null");
//        assertEquals(2, resultado.size(), "Deberían haber 2 subscripciones pendientes");
//
//        
//        for (DataSub dataSub : resultado) {
//            assertEquals("Pendiente", dataSub.getEstado(), 
//                "Todas las subscripciones en el resultado deberían estar pendientes");
//        }
//
//        
//        assertTrue(resultado.stream().anyMatch(ds -> ds.getCli().equals("user1")), 
//            "Debería existir una subscripción pendiente del usuario1");
//        assertTrue(resultado.stream().anyMatch(ds -> ds.getCli().equals("user2")), 
//            "Debería existir una subscripción pendiente del usuario2");
//
//        
//        verify(cpuMock, times(1)).listarSubs();
//    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// List<String> listarUsuarioSubsPendientes() //////////////////////////////////////
//    @Test
//    public void testListarUsuarioSubsPendientes() {
//       
//        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
//        Sistema sistema = new Sistema(cpuMock);
//
//        
//        Cliente cliente1 = new Cliente("user1", "Juan", "Pérez", "pass123", "juan@mail.com",
//                LocalDate.of(1990, 1, 1), "imagen1.jpg", "Cliente");
//        Cliente cliente2 = new Cliente("user2", "María", "López", "pass456", "maria@mail.com",
//                LocalDate.of(1992, 2, 2), "imagen2.jpg", "Cliente");
//
//        
//        Subscripcion subPendiente1 = new Subscripcion();
//        subPendiente1.setId(1);
//        subPendiente1.setEstado("Pendiente");
//        subPendiente1.setTipo("Mensual");
//        subPendiente1.setInicio(LocalDate.now());
//        subPendiente1.setCli(cliente1);
//        cliente1.setSub(subPendiente1);
//
//        Subscripcion subPendiente2 = new Subscripcion();
//        subPendiente2.setId(2);
//        subPendiente2.setEstado("Pendiente");
//        subPendiente2.setTipo("Anual");
//        subPendiente2.setInicio(LocalDate.now());
//        subPendiente2.setCli(cliente2);
//        cliente2.setSub(subPendiente2);
//
//        
//        List<Subscripcion> todasLasSubs = new ArrayList<>();
//        todasLasSubs.add(subPendiente1);
//        todasLasSubs.add(subPendiente2);
//
//        
//        when(cpuMock.listarSubs()).thenReturn(todasLasSubs);
//
//        
//        List<String> resultado = sistema.listarUsuarioSubsPendientes();
//
//        
//        assertNotNull(resultado, "La lista de resultado no debería ser null");
//        assertEquals(0, resultado.size(), "Deberían haber 2 nicks de usuarios en la lista");
//
//        
//        //assertTrue(resultado.contains("user1"), "La lista debería contener el nick 'user1'");
//        assertTrue(resultado.contains("user2"), "La lista debería contener el nick 'user2'");
//
//        
//        verify(cpuMock, times(1)).listarSubs();
//    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// void altaSubscripcion //////////////////////////////////////
     @Test
    public void testAltaSubscripcion_CrearNuevaSubscripcion() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        Cliente cliente = new Cliente("user31", "Juan", "Pérez", "pass123", "juan@mail.com",
                LocalDate.of(1990, 1, 1), "imagen1.jpg", "Cliente");
        
        
        
        when(cpuMock.obtenerCliente("user31")).thenReturn(cliente);
        
       
        sistema.altaSubscripcion("user31", "Mensual");
        
        
        assertNotNull(cliente.getSub(), "El cliente debería tener una subscripción asociada");
        assertEquals("Mensual", cliente.getSub().getTipo(), "El tipo de la subscripción debería ser 'Mensual'");
        
        
        verify(cpuMock, times(1)).obtenerCliente("user31");
        
        verify(cpuMock, times(1)).actualizarUsuario(cliente);
    }

    @Test
    public void testAltaSubscripcion_ActualizarSubscripcionExistente() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        Cliente cliente = new Cliente("user23", "Juan", "Pérez", "pass123", "juan@mail.com",
                LocalDate.of(1990, 1, 1), "imagen1.jpg", "Cliente");
        
        Subscripcion subscripcionExistente = new Subscripcion();
        subscripcionExistente.setTipo("Anual");
        cliente.setSub(subscripcionExistente);
        
        
        when(cpuMock.obtenerCliente("user23")).thenReturn(cliente);
        
        
        sistema.altaSubscripcion("user23", "Mensual");
        
        
        assertNotNull(cliente.getSub(), "El cliente debería tener una subscripción asociada");
        assertEquals("Mensual", cliente.getSub().getTipo(), "El tipo de la subscripción debería ser 'Mensual'");
        
        
        verify(cpuMock, times(1)).obtenerCliente("user23");
        
        verify(cpuMock, times(1)).actualizarUsuario(cliente);
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// void cambioEstadoSubscripcion //////////////////////////////////////
    @Test
    public void testCambioEstadoSubscripcion() throws Exception {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        Cliente cliente = new Cliente("user24", "Juan", "Pérez", "pass123", "juan@mail.com",
                LocalDate.of(1990, 1, 1), "imagen1.jpg", "Cliente");
        Subscripcion subscripcion = new Subscripcion();
        subscripcion.setEstado("Vigente");
        cliente.setSub(subscripcion);

        
        when(cpuMock.obtenerCliente("user24")).thenReturn(cliente);

       
        sistema.cambioEstadoSubscripcion("user24", "Cancelada");

        
        assertEquals("Cancelada", cliente.getSub().getEstado(), "El estado de la subscripción debería ser 'Cancelada'");

        
        verify(cpuMock, times(1)).obtenerCliente("user24");
        
        verify(cpuMock, times(1)).actualizarUsuario(cliente); 
    }

    @Test
    public void testCambioEstadoSubscripcion_SinSubscripcion()  {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        
        Cliente cliente = new Cliente("user25", "Juan", "Pérez", "pass123", "juan@mail.com",
                LocalDate.of(1990, 1, 1), "imagen1.jpg", "Cliente");

        
        when(cpuMock.obtenerCliente("user25")).thenReturn(cliente);

        
        sistema.cambioEstadoSubscripcion("user25", "Vigente");

        
        assertNull(cliente.getSub(), "El cliente no debería tener una subscripción asociada");

        
        verify(cpuMock, times(1)).obtenerCliente("user25");
        
        verify(cpuMock, times(0)).actualizarUsuario(cliente); 
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// List<DataTema> getDataTemasAjax //////////////////////////////////////
    @Test
    public void testGetDataTemasAjax() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        List<Tema> temasMock;
        
        Tema tema1 = new Tema("Tema 1", "3:00", 1, "url1", "archivo1");
        Tema tema2 = new Tema("Tema 2", "4:00", 2, "url2", "archivo2");
        
        tema1.setId(1L);
        tema2.setId(2L);
        
        temasMock = new ArrayList<>();
        
        temasMock.add(tema1);
        temasMock.add(tema2);
        
        when(cpuMock.getTemasAjax("test")).thenReturn(temasMock);

        
        List<DataTema> result = sistema.getDataTemasAjax("test");

        
        assertEquals(2, result.size());
        assertEquals("Tema 1", result.get(0).getNombre());
        assertEquals("Tema 2", result.get(1).getNombre());
        
        
        verify(cpuMock).getTemasAjax("test");
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// List<DataLista> getDataListasReproduccionAjax //////////////////////////////////////
    @Test
    public void testGetDataListasReproduccionAjax_ListaParticular() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        ListaParticular listaParticular = new ListaParticular("Particular 1", "imagen1.jpg", "cliente1");
        listaParticular.setId(1);
        
        ListaPorDefecto listaPorDefecto = new ListaPorDefecto("Defecto 1", "imagen2.jpg", "rock");
        listaPorDefecto.setId(2);

        
        List<ListaReproduccion> listasMock = new ArrayList<>();
        listasMock.add(listaParticular);
        listasMock.add(listaPorDefecto);
        
        when(cpuMock.getListasAjax("test")).thenReturn(listasMock);

        
        List<DataLista> result = sistema.getDataListasReproduccionAjax("test", "part");

        
        assertEquals(1, result.size());
        assertEquals("Particular 1", result.get(0).getNombre());
        assertEquals("imagen1.jpg", result.get(0).getImagen());
        assertEquals("cliente1", result.get(0).getExtra());

        
        verify(cpuMock).getListasAjax("test");
    }

    @Test
    public void testGetDataListasReproduccionAjax_ListaPorDefecto() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        ListaParticular listaParticular = new ListaParticular("Particular 1", "imagen1.jpg", "cliente1");
        listaParticular.setId(1);
        
        ListaPorDefecto listaPorDefecto = new ListaPorDefecto("Defecto 1", "imagen2.jpg", "rock");
        listaPorDefecto.setId(2);

        
        List<ListaReproduccion> listasMock = new ArrayList<>();
        listasMock.add(listaParticular);
        listasMock.add(listaPorDefecto);
        
        when(cpuMock.getListasAjax("test")).thenReturn(listasMock);

        
        List<DataLista> result = sistema.getDataListasReproduccionAjax("test", "default");

        
        assertEquals(1, result.size());
        assertEquals("Defecto 1", result.get(0).getNombre());
        assertEquals("imagen2.jpg", result.get(0).getImagen());
        assertEquals("rock", result.get(0).getExtra());

        
        verify(cpuMock).getListasAjax("test");
    }

    @Test
    public void testGetDataListasReproduccionAjax_Vacio() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        when(cpuMock.getListasAjax("test")).thenReturn(new ArrayList<>());

        
        List<DataLista> result = sistema.getDataListasReproduccionAjax("test", "part");

        
        assertEquals(0, result.size());

        
        verify(cpuMock).getListasAjax("test");
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// List<DataAlbum> getDataAlbumsAjax //////////////////////////////////////
    @Test
    public void testGetDataAlbumsAjax() {
        
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);

        Artista artista1 = new Artista("artista23", "Nombre1", "Apellido1", "pass1", "email1@test.com", LocalDate.now(), "imagen1.jpg","mibio","misitio", "artista");
        
        Album album1 = new Album("Álbum 1", 2022, "imagen1.jpg");
        album1.setId(1L);
        album1.setArtista(artista1);
        
        
        List<Album> albumsMock = new ArrayList<>();
        albumsMock.add(album1);
        
        when(cpuMock.getAlbumsAjax("test")).thenReturn(albumsMock);

        
        List<DataAlbum> result = sistema.getDataAlbumsAjax("test");

        
        assertEquals(1, result.size());
        assertEquals("Álbum 1", result.get(0).getNombre());
        assertEquals(2022, result.get(0).getFechaCreacion());
        assertEquals("imagen1.jpg", result.get(0).getImagenAlbum());

        
        verify(cpuMock).getAlbumsAjax("test");
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// List<DataUsuario> getDataUsuariosAjax //////////////////////////////////////
    @Test
    void testGetDataUsuariosAjax_ReturnsArtistas() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        
        List<Usuario> usuarios = new ArrayList<>();
        Artista artista = new Artista("artista1", "Nombre", "Apellido", "password", "email@example.com", LocalDate.of(2000, 1, 1), "imagenPerfil", "biografía", "sitioWeb", "art");
        usuarios.add(artista);
        
        when(cpuMock.getUsuariosAjax("artista1")).thenReturn(usuarios);
        
        
        List<DataUsuario> result = sistema.getDataUsuariosAjax("artista1", "art");
        
        
        assertEquals(1, result.size());
        assertEquals("artista1", result.get(0).getNick());
        assertEquals("Nombre", result.get(0).getNombre());
        assertEquals("Apellido", result.get(0).getApellido());
        assertEquals("email@example.com", result.get(0).getCorreo());
        assertEquals(LocalDate.of(2000, 1, 1), result.get(0).getFechaNac());
        assertEquals("imagenPerfil", result.get(0).getImagen());
    }

    @Test
    void testGetDataUsuariosAjax_ReturnsClientes() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        List<Usuario> usuarios = new ArrayList<>();
        Cliente cliente = new Cliente("cliente1", "Nombre", "Apellido", "password", "email@example.com", LocalDate.of(2000, 1, 1), "imagenPerfil", "cli");
        usuarios.add(cliente);
        
        when(cpuMock.getUsuariosAjax("cliente1")).thenReturn(usuarios);
        
        
        List<DataUsuario> result = sistema.getDataUsuariosAjax("cliente1", "cli");
        
        
        assertEquals(1, result.size());
        assertEquals("cliente1", result.get(0).getNick());
        assertEquals("Nombre", result.get(0).getNombre());
        assertEquals("Apellido", result.get(0).getApellido());
        assertEquals("email@example.com", result.get(0).getCorreo());
        assertEquals(LocalDate.of(2000, 1, 1), result.get(0).getFechaNac());
        assertEquals("imagenPerfil", result.get(0).getImagen());
    }

    @Test
    void testGetDataUsuariosAjax_ReturnsEmptyListForInvalidType() {
        ControladorPersistencia cpuMock = mock(ControladorPersistencia.class);
        Sistema sistema = new Sistema(cpuMock);
        
        List<Usuario> usuarios = new ArrayList<>();
        when(cpuMock.getUsuariosAjax("noExistentUser")).thenReturn(usuarios);
        
        
        List<DataUsuario> result = sistema.getDataUsuariosAjax("noExistentUser", "nonExistentType");
        
        
        assertTrue(result.isEmpty());
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////// /////////////////////// //////////////////////////////////////
    
    
}

















