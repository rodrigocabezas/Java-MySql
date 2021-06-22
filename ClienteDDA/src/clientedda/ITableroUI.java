/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientedda;

import Entidades.Mensaje;

/**
 *
 * @author Jorge Baldassini
 */
public interface ITableroUI {
     public String getFigura();
    int[] obtenerCeldaSeleccionada();
    void procesar(Mensaje msg);
    void setControlador(ControladorPartida cp);
}
