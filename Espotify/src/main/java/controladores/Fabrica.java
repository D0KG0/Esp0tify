/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;
import persistencia.iControladorPersistencia;
import persistencia.ControladorPersistencia;

/**
 *
 * @author dokgo
 */
public class Fabrica {
    public Fabrica(){}
    //logica
    public iSistema getSistema(){
        return Sistema.getInstance();
    }
    //persistencia
    public iControladorPersistencia getControladorPersistencia(){
        return ControladorPersistencia.getInstance();
    }
}
