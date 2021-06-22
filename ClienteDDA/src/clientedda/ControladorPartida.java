/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientedda;
 
import Entidades.Evento;
import Entidades.Mensaje;
import Entidades.Partida;
import Entidades.Usuario;
import Remotas.IFachadaServidor;
import Remotas.IRemoteObserver;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumno
 */
public class ControladorPartida extends UnicastRemoteObject implements IRemoteObserver, ActionListener,MouseListener{

    

    private ITableroUI tableroUI;
     private IFachadaServidor servidor;
    private Partida partida;

    public ControladorPartida(ITableroUI tableroUI, IFachadaServidor servidor, Partida partida) throws RemoteException{
        this.tableroUI = tableroUI;
        this.servidor = servidor;
        this.servidor.agregarObservador(this);
        this.partida = partida;
        tableroUI.setControlador(this);
    }
    
    
    public void setTableroUI(ITableroUI tableroUI) {
        this.tableroUI = tableroUI;
//        new Partida(x, z,jugador,pendiente).AgregarJugador((Observer) this);
    }
    
    @Override
    public void update(IFachadaServidor i, Object c) throws RemoteException {
        Mensaje msg = (Mensaje)c;
       this.tableroUI.procesar(msg);
//        System.out.println(msg.getEvento());
//               System.out.println(partida.getTurnoActual().getNombreUsu());
//        System.out.println(msg.getAux());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        partida.Procesar(new Mensaje(Evento.JUGADA_REALIZADA, e));
    }
    
    

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            servidor.procesarPartida(partida.getIdPartida(),new Mensaje(Evento.CASILLERO_SELECCIONADO,tableroUI.obtenerCeldaSeleccionada()));
        } catch (RemoteException ex) {
            Logger.getLogger(ControladorPartida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
