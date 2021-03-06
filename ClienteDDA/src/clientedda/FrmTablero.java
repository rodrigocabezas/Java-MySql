/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientedda;

import Entidades.Evento;
import Entidades.Mensaje;
import Entidades.Partida;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Jorge Baldassini
 */
public class FrmTablero extends javax.swing.JFrame implements ITableroUI {

    PartidaTableModel tableModel;
    private String Figura;

    public FrmTablero(Partida p) {
        initComponents();
        tableModel = new PartidaTableModel(p);
        tblTablero.setModel(tableModel);
        tblTablero.setDefaultRenderer(Object.class, new CasilleroCellRenderer());
        //para asignarle una alura a las filas
        tblTablero.setRowHeight(150);
        //para maximizar el formulario
        this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTablero = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        tblTablero.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblTablero);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void actualizar() {
        tableModel.fireTableDataChanged();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTablero;
    // End of variables declaration//GEN-END:variables

    @Override
    public void procesar(Mensaje msg) {
        if (msg.getEvento() == Evento.JUGADA_NO_PERMITIDA) {
          
//            JOptionPane.showMessageDialog(null, msg.getAux().toString());
            System.out.println(msg.getEvento());
        } else if (msg.getEvento() == Evento.JUGADA_REALIZADA) {
            
            Partida p = (Partida) msg.getAux();
            System.out.println(msg.getEvento());
            System.out.println("Ahora es el turno de " + p.getTurnoActual().getNombreUsu());
            
            tableModel = new PartidaTableModel(p);
            tblTablero.setModel(tableModel);
            tblTablero.setDefaultRenderer(Object.class, new CasilleroCellRenderer());
            actualizar();
        } else if (msg.getEvento() == Evento.PERDISTE){
            Partida p = (Partida) msg.getAux();
            p.setPendiente(false);
            System.out.println("Jugador perdedor: " + p.getTurnoActual().getNombreUsu());
            System.out.println(p.getJugadorUno().getNombreUsu() + "Saldo final:" + p.getJugadorUno().getSaldo());
            System.out.println(p.getJugadorDos().getNombreUsu() + "Saldo final:" + p.getJugadorDos().getSaldo());
        }
    }

    @Override
    public void setControlador(ControladorPartida cp) {
        //Asigna al controlador como un mouseListener del
        //jtable (ya que al heredar de MouseAdapter
        //es un MouseListener) de ese modo escuha el evento mouseClicked
//         tblTablero.addMouseListener( cp);
        tblTablero.addMouseListener((MouseListener) cp);
    }

    @Override
    public String getFigura() {
        return this.Figura;
    }

    @Override
    public int[] obtenerCeldaSeleccionada() {
        int row = tblTablero.getSelectedRow();
        int column = tblTablero.getSelectedColumn();
        return new int[]{row, column};

    }

}
