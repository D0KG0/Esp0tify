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
import persistencia.ControladorPersistencia;
import persistencia.iControladorPersistencia;


public class ServidorCentral {
    ControladorPersistencia cpu;
    iSistema sys = new Fabrica().getSistema(cpu);
    public static void main(String[] args) throws UsuarioRepetidoException {
        Fabrica f = new Fabrica();
        
        //iControladorPersistencia cpu = f.getControladorPersistencia();
        
        
        Principal programa = new Principal();
        programa.setVisible(true);
        programa.setLocationRelativeTo(null);
        
    }
   
}
