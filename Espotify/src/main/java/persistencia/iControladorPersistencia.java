/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import java.util.List;
import logica.Cliente;
import logica.Usuario;

/**
 *
 * @author dokgo
 */
public interface iControladorPersistencia {
    
    public abstract void crearUsuario(Usuario usuario);
    public abstract Usuario obtenerUsuario(String nickname);
    public abstract void actualizarUsuario(Usuario usuario);
    public abstract void eliminarUsuario(String email);
    //public abstract void cerrar();
    public abstract List<Cliente> obtenerTodosLosClientes();
}
