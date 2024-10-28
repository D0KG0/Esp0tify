/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author dokgo
 */
public class UsuarioNoExisteException extends Exception {
    
    public UsuarioNoExisteException(String string){
        super(string);
    }
    
}