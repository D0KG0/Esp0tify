/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import datatypes.DataGenero;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Genero implements Serializable {
    @Id
    private String nombre;
    private String padre;
    
    @ManyToMany(mappedBy = "Generos")//, cascade = CascadeType.ALL)
    //private  HashMap<String, Album> Albumes = new HashMap<>();
    private List<Album> Albumes = new ArrayList<>();
    
    public static Map<String, Genero> listaGeneros = new HashMap<>();
    
    //Constructor
    
    public Genero() {}
    
    public Genero(String nombre, String padre) {
        this.nombre = nombre;
        this.padre = padre;
    }
    
    public Genero(Genero otroGenero) {
        this.nombre = otroGenero.nombre;
    }
    
    public Genero(String nombre){
        this.nombre = nombre;
    }

    //Getters y Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public String getPadre() {
        return padre;
    }
    public void setPadre(String padre) {
        this.padre = padre;
    }
    
    //Nuevo Genero
    public static void addGenero(Genero gen){
        listaGeneros.put(gen.getNombre(), gen);
    }

	//album
//    public void addAlbum(Album album){
//        String nombreAlbum = album.getNombre();
//        this.Albumes.put(nombreAlbum, album);
//    }
//    
//    public Collection<Album> getAlbumes(){
//        Collection<Album> albumes = Albumes.values();
//        return albumes;
//    }
    
    public void addAlbum(Album album){
        this.Albumes.add(album);
    }
    
    public Collection<Album> getAlbumes(){
        Collection<Album> albumes = Albumes;
        return albumes;
    }
    
    public DataGenero devolverData(){
        return new DataGenero(getNombre());
    }
    
    public List<Album> getAlbumes2(){
        List<Album> albumes = Albumes;
        return albumes;
    }
}