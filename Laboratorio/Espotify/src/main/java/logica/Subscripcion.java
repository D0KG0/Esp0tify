/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import datatypes.DataSub;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author frank
 */
@Entity
public class Subscripcion implements Serializable {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String tipo;    // Solo "Semanal", "Mensual" o "Anual"
    private String estado;  //  Solo “Pendiente”, "Vigente", "Vencida" o ¨Cancelada¨
    private LocalDate inicio;
    private LocalDate fin; //calculada cuando se cambia el tipo
    @OneToOne
    private Cliente cli;
    
    public Subscripcion(){
        this.estado = "Pendiente";
        this.inicio = LocalDate.now();
    }
    
    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
        switch (tipo){
            case "Semanal": fin = inicio.plusWeeks(1);
                            break;
            case "Mensual": fin = inicio.plusMonths(1);
                            break;
            case "Anual": fin = inicio.plusYears(1);
                            break;
        }
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }
    
}
