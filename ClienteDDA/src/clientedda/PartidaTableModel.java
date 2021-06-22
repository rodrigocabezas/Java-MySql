/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientedda;

import Entidades.Partida;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jorge Baldassini
 */
public class PartidaTableModel extends AbstractTableModel{
    private Partida partida;
    public PartidaTableModel (Partida partida){
        this.partida = partida;
    }

    
    @Override
    public int getRowCount() {
        return this.partida.obtenerCantidadFilas();
    }

    @Override
    public int getColumnCount() {
        return this.partida.obtenerCantidadColumnas();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       return this.partida.obtenerCasillero(rowIndex,columnIndex);
    }
    
}
