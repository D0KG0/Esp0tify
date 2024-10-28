package logica;

import datatypes.DataTema;
import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Tema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Se usa un ID autogenerado
    private Long id;
    @Basic
    private String nombre;
    private String duracion;
    private Integer posicion;
    private String direccionWeb;
    private String archivo;
    
    @ManyToOne
    @JoinColumn(name = "album_id")  // Clave foránea en la tabla "Tema"
    private Album album;

    public Tema() {}

    public Tema(String nombre, String duracion, Integer posicion) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.posicion = posicion;
    }

    public Tema(String nombre, String duracion, Integer posicion, String direccionWeb, String archivo) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.posicion = posicion;
        this.direccionWeb = direccionWeb;
        this.archivo = archivo;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long idc){
        this.id = idc;
    }

    public Tema(Tema otroTema) {
        this.nombre = otroTema.nombre;
        this.duracion = otroTema.duracion;
        this.posicion = otroTema.posicion;
        this.direccionWeb = otroTema.direccionWeb;
        this.archivo = otroTema.archivo;
        this.album = otroTema.album;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
    
     public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    public String getDireccionWeb() {
        return direccionWeb;
    }

    public void setDireccionWeb(String direccionWeb) {
        this.direccionWeb = direccionWeb;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
    
    public void setAlbum(Album album) {
        this.album = album;
    }
    
    public DataTema getDataTema(){
        DataTema dt_tema = new DataTema(getNombre(), getDuracion(), getPosicion(), getDireccionWeb(),getArchivo());
    return dt_tema;
    }
    
    public DataTema getDataTemaI(){
        DataTema dt_tema = new DataTema(getId(),getNombre(), getDuracion(), getPosicion(), getDireccionWeb(),getArchivo());
    return dt_tema;
    }
}
