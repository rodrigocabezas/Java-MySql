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
import javax.swing.JOptionPane;

/**
 *
 * @author Nan
 */
public class ControladorPrePartida extends UnicastRemoteObject implements IRemoteObserver, ActionListener{
     private ITableroUI tableroUI;
     private IFachadaServidor servidor;
    private IFrmAppPartida formulario;
    
    
    public ControladorPrePartida(IFachadaServidor servidor, IFrmAppPartida formulario) throws RemoteException{
        this.servidor = servidor;
        this.servidor.agregarObservador(this);
        this.formulario = formulario;
        this.formulario.setControlador(this);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        int filas = 0;
        int columnas = 0;
        int pozo = 0;
        boolean pendiente = true;
        Usuario u = formulario.obtenerUsuarioForm();
        try{
            
            filas = Integer.parseInt(this.formulario.obtenerFilasIngresadas());
            columnas = Integer.parseInt(this.formulario.obtenerColumnasIngresadas());
            pozo = Integer.parseInt(this.formulario.obtenerApuestaIngresada());
            
            
        }catch(Exception ex){
            this.formulario.procesar(new Mensaje(Evento.ERROR,"Número debe ser numérico"));
            return;
        }
//        Partida partida = new Partida(filas,columnas,apuesta);
//        Usuario usuario = new Usuario(numero,this.formulario.obtenerNombreIngresado());
        try {
            
            if(filas > 10 || filas < 3 || columnas > 10 || columnas < 3){
                JOptionPane.showMessageDialog(null, "Dimensiones incorectas");
            }else if(pozo > u.getSaldo()){
                JOptionPane.showMessageDialog(null, "Saldo insuficiente");
            }else{
            u.setSaldo(u.getSaldo()-pozo);
            this.servidor.agregarPartida(filas,columnas,u,pendiente,pozo);
            }
            
            
        } catch (RemoteException ex) {
            this.formulario.procesar(new Mensaje(Evento.ERROR,ex.getMessage()));
        }
    }

    @Override
    public void update(IFachadaServidor i, Object c) {
//        Mensaje msg = (Mensaje)c;
//       this.formulario.procesar((Mensaje) c);
        Mensaje msg = (Mensaje)c;
       this.formulario.procesar(msg);
    }


    
}
