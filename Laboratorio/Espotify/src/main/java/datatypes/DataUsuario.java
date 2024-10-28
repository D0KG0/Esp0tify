/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datatypes;

import java.time.LocalDate;

/**
 *
 * @author dokgo
 */
public class DataUsuario {
    private String nick;
    private String nombre;
    private String apellido;
    private String correo;
    private LocalDate fechaNac;
    private String biografia;
    private String sitioWeb;
    private String imagen;
    private String tipo;

    public DataUsuario(String nick, String nombre, String apellido, String correo, LocalDate fechaNac, String imagenP, String biografia, String sitioWeb) {
        this.nick = nick;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fechaNac = fechaNac;
        this.biografia = biografia;
        this.sitioWeb = sitioWeb;
        this.imagen = imagenP;
        
    }
    
    public DataUsuario(String nick, String nombre, String apellido, String correo, LocalDate fechaNac, String imagenP) {
        this.nick = nick;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fechaNac = fechaNac;
        this.imagen = imagenP;
        
    }
    
    public DataUsuario(String nick, String nombre, String apellido, String correo, LocalDate fechaNac, String imagenP, String biografia, String sitioWeb, String tipo) {
        this.nick = nick;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fechaNac = fechaNac;
        this.biografia = biografia;
        this.sitioWeb = sitioWeb;
        this.imagen = imagenP;
        this.tipo = tipo;
        
    }
    
    public DataUsuario(String nick, String nombre, String apellido, String correo, LocalDate fechaNac, String imagenP, String tipo) {
        this.nick = nick;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.fechaNac = fechaNac;
        this.imagen = imagenP;
        this.tipo = tipo;
        
    }
    
    public DataUsuario(String nick, String nombre){
        this.nick = nick;
        this.nombre = nombre;
    }

    public String getNick() {
        return nick;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public String getBiografia() {
        return biografia;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public String getImagen() {
        return imagen;
    }

//    public void setImagen(String imagen) {
//        this.imagen = imagen;
//    }

    public String getTipo() {
        return tipo;
    }

//    public void setTipo(String tipo) {
//        this.tipo = tipo;
//    }
    
    
    
}

