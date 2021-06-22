/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.swing.JButton;

/**
 *
 * @author rodrii
 */
public class casillero extends JButton {

    private boolean golpeado;
    private boolean mina;

    public boolean isGolpeado() {
        return golpeado;
    }

    public void setGolpeado(boolean golpeado) {
        this.golpeado = golpeado;
    }

    public casillero() {
        super();
        golpeado = false;

        double random = Math.random();
        if (random > 0.7) {
            mina = true;
        } else {
            mina = false;
        }
    }

    public boolean lugarMinado() {
        return mina;
    }

}
