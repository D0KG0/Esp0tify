package logica;
import datatypes.DataUsuario;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.persistence.Entity;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.OneToMany;


@Entity
public class Artista extends Usuario implements Serializable{
    @Column(length = 500)
    private String biografia;
    private String sitioWeb;

    @OneToMany(mappedBy = "artista")
    private List<Album> Albumes = new ArrayList<>();
    
    public Artista(){}

    public Artista(String nickname, String nombre, String apellido, String email, LocalDate fechaNac, String imagenPerfil,String biografia, String sitioWeb, String tipo) {
        super(nickname, nombre, apellido, email, fechaNac, imagenPerfil, tipo);
        this.biografia = biografia;
        this.sitioWeb = sitioWeb;
    }

    
    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }
    
    @Override
    public DataUsuario devolverData() {
    DataUsuario dataArtista = new DataUsuario(getNickname(), getNombre(), getApellido(), getEmail(), getFechaNac(), getImagenPerfil(), getBiografia(), getSitioWeb());
    return dataArtista;
    }
    
        //ALBUM
    
    
    public List<Album> getAlbumesMap(){
        return this.Albumes;
    }
    
    public void addAlbum(Album album){
        this.Albumes.add(album);
    }
    
    public Album getAlbum(String nombre){
        int cont = 0;
        for (Album album : this.Albumes){
            if (album.getNombre() == nombre){
                return ((Album) this.Albumes.get(cont));
            }
            cont++;
        }
        return null;
    }
    
    public void altaTema(String nombreAlbum, String nombre, String duracion, Integer posicion, String direccionWeb, String archivo){
        Tema nuevoTema = new Tema(nombre, duracion, posicion, direccionWeb, archivo);
        //Album album = Albumes.get(nombreAlbum);
        Album album = null;
        int cont = 0;
        for (Album alb : this.Albumes){
            if (alb.getNombre() == nombre){
                album = ((Album) this.Albumes.get(cont));
            }
            cont++;
        }
        
        if (album != null){
            album.addTema(nuevoTema);
        }
    }
    
    public Collection<Album> getAlbumes(){
        Collection<Album> albumes = Albumes;
        return albumes;
    }
    
}

