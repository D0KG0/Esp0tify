/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import persistencia.ControladorPersistencia;
import persistencia.iControladorPersistencia;

/**
 *
 * @author dokgo
 */
public class Fabrica {
    public Fabrica(){}
    //logica
    public iSistema getSistema(ControladorPersistencia cpu){
        return Sistema.getInstance(cpu);
    }
    
    public iControladorPersistencia getControlador(){
        return ControladorPersistencia.getInstance();
    }
    
}
