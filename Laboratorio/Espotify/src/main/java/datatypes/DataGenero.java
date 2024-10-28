/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datatypes;

import java.util.Objects;

/**
 *
 * @author miche
 */
public class DataGenero {
    private String nombre;
    private String padre;
    
    public DataGenero(){}
    
    public DataGenero(String nombre){
    this.nombre = nombre;
    }
    
    public DataGenero(String nombre, String padre) {
        this.nombre = nombre;
        this.padre = padre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DataGenero that = (DataGenero) obj;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
    
}
