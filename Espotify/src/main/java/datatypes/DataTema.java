/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datatypes;

import logica.Tema;

/**
 *
 * @author miche
 */
public class DataTema {
    
    private Long id;        
    private String nombre;
    private String duracion;
    private Integer posicion;
    private String direccionWeb;
    private String archivo;

   

    public DataTema() {}

    public DataTema(String nombre, String duracion, Integer posicion, String direccionWeb, String archivo) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.posicion = posicion;
        this.direccionWeb = direccionWeb;
        this.archivo = archivo;
    }
    
    public DataTema(Long id , String nombre, String duracion, Integer posicion, String direccionWeb, String archivo) {
        this.id= id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.posicion = posicion;
        this.direccionWeb = direccionWeb;
        this.archivo = archivo;
    }
    
    public DataTema(Tema t){
        this.id = t.getId();
        this.nombre = t.getNombre();
        this.duracion = t.getDuracion();
        this.posicion = t.getPosicion();
        this.direccionWeb = t.getDireccionWeb();
        this.archivo = t.getArchivo();
    }
     
    public Long getId() {
        return id;
    }
        public String getNombre() {
        return nombre;
    }


    public String getDuracion() {
        return duracion;
    }

    
     public Integer getPosicion() {
        return posicion;
    }


    public String getDireccionWeb() {
        return direccionWeb;
    }


    public String getArchivo() {
        return archivo;
    }

}
