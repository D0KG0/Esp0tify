/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;

/**
 *
 * @author dokgo
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ListaReproduccion {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int Id;
    private String nombre;
    private String imagen;
    @OneToMany
    private Set<Tema> listaTemas;
    
    //public static Map<String, ListaReproduccion> listaListas = new HashMap<>();
    public ListaReproduccion() {    
    }

    //Constructor
    public ListaReproduccion(String nombre, String imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.listaTemas = new HashSet<>();
    }
    
    //Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Set<Tema> getListaTemas() {
        return listaTemas;
    }
    
    public void setListaTemas(Set<Tema> listaTemas){
        this.listaTemas = listaTemas;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
    public void agregarTema(Tema tema) {
        listaTemas.add(tema);
        
    }
    
    public void eliminarTema(Long idTema) {
        listaTemas.removeIf(temas -> temas.getId() == idTema);
    }
}
