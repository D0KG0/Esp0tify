/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.Collection;
import datatypes.DataUsuario;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;



/**
 *
 * @author dokgo
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements Serializable {
    
    @Id
    protected String nickname;
    
    protected String nombre;
    protected String apellido;
    
    @Column(unique = true)
    protected String email;
    
    protected LocalDate fechaNac;
    protected String imagenPerfil;
    protected String tipo;
    //album

    
    
    public Usuario(){};

    public Usuario(String nickname, String nombre, String apellido, String email, LocalDate fechaNac, String imagenPerfil, String tipo) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNac = fechaNac;
        this.imagenPerfil = imagenPerfil;
        this.tipo = tipo;
        
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public DataUsuario devolverData(){
        return new DataUsuario(getNickname(), getNombre(), getApellido(), getEmail(), getFechaNac(), getImagenPerfil(), null, null);
    }

    
}

