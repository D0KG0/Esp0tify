/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.persistence.Entity;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.ManyToMany;

/**
 *
 * @author dokgo
 */
@Entity
public class Cliente extends Usuario implements Serializable {

    public Cliente() {}

    public Cliente(String nickname, String nombre, String apellido, String email, LocalDate fechaNac, String imagenPerfil, String tipo) {
        super(nickname, nombre, apellido, email, fechaNac, imagenPerfil, tipo);
    }
    
    //brian
    //Seguir y dejar de seguir
    @ManyToMany
    private ArrayList< Usuario> usuariosSiguiendo = new ArrayList<>();
    @ManyToMany
     private ArrayList<ListaReproduccion> listasFavoritas = new ArrayList<>();
    @ManyToMany
    private ArrayList<ListaParticular> listasDeCliente = new ArrayList<>();
    @ManyToMany
    private ArrayList<Tema> temasFavoritos = new ArrayList<>(); 
    @ManyToMany
    private ArrayList<Album> albumesFavoritos = new ArrayList<>();

    public ArrayList<Usuario> getUsuariosSiguiendo() {
        return usuariosSiguiendo;
    }

    public void setUsuariosSiguiendo(ArrayList<Usuario> usuariosSiguiendo) {
        this.usuariosSiguiendo = usuariosSiguiendo;
    }

    public ArrayList<ListaReproduccion> getListasFavoritas() {
        return listasFavoritas;
    }

    public void setListasFavoritas(ArrayList<ListaReproduccion> listasFavoritas) {
        this.listasFavoritas = listasFavoritas;
    }

    public ArrayList<ListaParticular> getListasDeCliente() {
        return listasDeCliente;
    }

    public void setListasDeCliente(ArrayList<ListaParticular> listasDeCliente) {
        this.listasDeCliente = listasDeCliente;
    }

    public ArrayList<Tema> getTemasFavoritos() {
        return temasFavoritos;
    }

    public void setTemasFavoritos(ArrayList<Tema> temasFavoritos) {
        this.temasFavoritos = temasFavoritos;
    }

    public ArrayList<Album> getAlbumesFavoritos() {
        return albumesFavoritos;
    }

    public void setAlbumesFavoritos(ArrayList<Album> albumesFavoritos) {
        this.albumesFavoritos = albumesFavoritos;
    }

      public void agrgarListaAListasCreadas(ListaParticular lp){
        this.listasDeCliente.add(lp);
    }
      
      public void eliminarListaFavorita(int idLista) {
    listasFavoritas.removeIf(lista -> lista.getId() == idLista);
}
    
    public void eliminarTemaFavorito(Long idTema) {
    temasFavoritos.removeIf(temas -> temas.getId() == idTema);
}

    public void eliminarAlbumesFavoritos(Long idAlbum) {
        albumesFavoritos.removeIf(albumes -> albumes.getId() == idAlbum);
    }
    
    //Favoritos
    
    public void agregarAlbumFavorito(Album album) {
            albumesFavoritos.add(album);
    }

    public void agregarTemaFavorito(Tema tema) {
            temasFavoritos.add(tema);
        
    }

    public void agregarListaFavorita(ListaReproduccion lista) {
        if (!listasFavoritas.contains(lista)) {
            listasFavoritas.add(lista);
        }
    }
}
