/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import jakarta.persistence.Entity;

/**
 *
 * @author frank
 */
@Entity
public class ListaPorDefecto extends ListaReproduccion {
    private String genero;

    public ListaPorDefecto(String nombre, String imagen, String gen) {
        super(nombre, imagen);
        this.genero = gen;
    }

    public ListaPorDefecto() {
    }
    
    //Getters y Setters

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    
    
}
