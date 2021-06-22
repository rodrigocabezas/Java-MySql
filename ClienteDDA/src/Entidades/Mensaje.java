/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;

/**
 *
 * @author rodrii
 */
public class Mensaje implements Serializable {
    
    private Evento evento;
    private Object aux;

    public Mensaje(Evento evento, Object aux) {
        this.evento = evento;
        this.aux = aux;
    }

    
    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Object getAux() {
        return aux;
    }

    public void setAux(Object aux) {
        this.aux = aux;
    }
}
