/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clientedda;

import Entidades.casillero;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Baldassini, Jorge / CTC - ORT
 */
public class CasilleroCellRenderer extends JButton implements TableCellRenderer{
 
    
    public CasilleroCellRenderer(){

    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        casillero c = (casillero)value;
        if(c.isGolpeado()&& c.lugarMinado()){
            this.setIcon(new ImageIcon(getClass().getResource("labomba.png")));
        }
        else if(c.isGolpeado()){
            this.setBackground(Color.red);
        }
        else{
            this.setBackground(Color.gray);
        }
        return this;
        //JLabel label = new JLabel(new ImageIcon(buffer)));  
    }
    
}
