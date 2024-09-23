/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author dokgo
 */

import presentacionGUI.Principal;
import controladores.Fabrica;
import controladores.iSistema;
import excepciones.UsuarioRepetidoException;
import persistencia.iControladorPersistencia;


public class ServidorCentral {
    
    public static void main(String[] args) throws UsuarioRepetidoException {
        Fabrica f = new Fabrica();
        iSistema sys = f.getSistema();
        //iControladorPersistencia cpu = f.getControladorPersistencia();
        
        
        Principal programa = new Principal();
        programa.setVisible(true);
        programa.setLocationRelativeTo(null);
        
    }
   
}
