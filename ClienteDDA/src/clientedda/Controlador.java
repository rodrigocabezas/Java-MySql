/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientedda;

import Entidades.Evento;
import Entidades.Mensaje;
import Entidades.Usuario;
import Remotas.IFachadaServidor;
import Remotas.IRemoteObserver;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Nan
 */
public class Controlador extends UnicastRemoteObject implements IRemoteObserver, ActionListener{
    private IFachadaServidor servidor;
    private IFrmAppCliente formulario;
    
    public Controlador(IFachadaServidor servidor, IFrmAppCliente formulario) throws RemoteException{
        this.servidor = servidor;
        this.servidor.agregarObservador(this);
        this.formulario = formulario;
        this.formulario.setController(this);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        String nombre;
        String contrasena;
        try{
            nombre = this.formulario.obtenerNombreIngresado();
            contrasena = this.formulario.obtenerContrasenaIngresada();
        }catch(Exception ex){
            this.formulario.Procesar(new Mensaje(Evento.ERROR,"Número debe ser numérico"));
            return;
        }
//        Usuario usuario = new Usuario(numero,this.formulario.obtenerNombreIngresado());
        try {
            
            this.formulario.Procesar(this.servidor.IngresarJugador(nombre,contrasena));
        } catch (RemoteException ex) {
            this.formulario.Procesar(new Mensaje(Evento.ERROR,ex.getMessage()));
        }
    }

    @Override
    public void update(IFachadaServidor i, Object c) {
        Mensaje msg = (Mensaje)c;
        
       this.formulario.Procesar(msg);
    }
    
}
