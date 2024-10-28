/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datatypes;

import java.time.LocalDate;
import logica.Subscripcion;

/**
 *
 * @author frank
 */
public class DataSub {
    private String tipo;
    private String estado;
    private LocalDate inicio;
    private LocalDate fin;
    private String cli;
    
    public void DataSub(){
        
    }

    public DataSub(String tipo, String estado, LocalDate inicio, LocalDate fin, String cli) {
        this.tipo = tipo;
        this.estado = estado;
        this.inicio = inicio;
        this.fin = fin;
        this.cli = cli;
    }

    public DataSub(Subscripcion sub) {
        this.tipo = sub.getTipo();
        this.estado = sub.getEstado();
        this.inicio = sub.getInicio();
        this.fin = sub.getFin();
        this.cli = sub.getCli().getNickname();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getCli() {
        return cli;
    }

    public void setCli(String cli) {
        this.cli = cli;
    }
    
    
}
