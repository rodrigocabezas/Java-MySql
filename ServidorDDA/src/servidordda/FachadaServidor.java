/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidordda;

import Entidades.Evento;
import Entidades.Mensaje;
import Entidades.Partida;
import Entidades.Usuario;
import Remotas.IFachadaServidor;
import Remotas.IRemoteObserver;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Nan
 */
public class FachadaServidor implements IFachadaServidor, Observer {

    private ArrayList<IRemoteObserver> colRemoteObservers;
    private static FachadaServidor fachadaServidor = null;

    private FachadaServidor() {
        colRemoteObservers = new ArrayList<>();
    }

    public static FachadaServidor getInstance() {
        if (fachadaServidor == null) {
            fachadaServidor = new FachadaServidor();
        }
        return fachadaServidor;
    }

    private void notificarObservadores(Object aux) throws RemoteException {
        Iterator<IRemoteObserver> it = colRemoteObservers.iterator();
        while (it.hasNext()) {
            try {
                it.next().update(this, aux);
            } catch (RemoteException ex) {
                System.out.println("Error al notificar: " + ex.getMessage());
                it.remove();
                System.out.println("Eliminado observer remoto no conectable");
            }
        }

    }

    @Override
    public void agregarObservador(IRemoteObserver observer) throws RemoteException {
        colRemoteObservers.add(observer);
    }

    @Override
    public void agregarPartida(int filas, int columna, Usuario jugador, boolean pendiente, int pozo) throws RemoteException {
        pendiente = true;
        Partida partida = GestoraPartidas.getInstance().agregarPartida(filas, columna, jugador, pendiente, pozo);//todos atributos
        partida.addObserver(this);

//       if(partida!=null){
        notificarObservadores(new Mensaje(Evento.PARTIDA_CREADA, partida));
//       }
//       else{
//            notificarObservadores(new Mensaje(Evento.ERROR, partida));
//       }

    }

    @Override
    public ArrayList<Partida> obtenerPartidas() throws RemoteException {
        return GestoraPartidas.getInstance().obtenerPartidas();
    }

    @Override
    public ArrayList<Usuario> obtenerUsuarios() throws RemoteException {
        return GestoraUsuario.getInstance().obtenerUsuario();
    }

    @Override
    public void Login(String nombre, String contrasena) throws RemoteException {

        Usuario u = GestoraUsuario.getInstance().Login(nombre, contrasena);
        if (u != null) {
            notificarObservadores(new Mensaje(Evento.USUARIO_LOGUEADO, u));
        } else {
            notificarObservadores(new Mensaje(Evento.ERROR, u));
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            notificarObservadores((Mensaje) arg);
        } catch (RemoteException ex) {
            Logger.getLogger(FachadaServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Mensaje IngresarJugador(String nombre, String contrasena) {
        Usuario u = GestoraUsuario.getInstance().Login(nombre, contrasena);
        if (u != null) {
                    if(u.isAdm() != true){
                         return GestoraPartidas.getInstance().ingresarJugadorParaJugar(u);
                    }else{
                        return new Mensaje(Evento.ADMINISTRADOR,u);
                    }
           

        } else {
            return new Mensaje(Evento.ERROR, "Usuario inválido");

        }
    }

    @Override
    public void procesarPartida(int id, Mensaje msj) throws RemoteException {
        GestoraPartidas.getInstance().procesarPartida(id, msj);
//        notificarObservadores(new Mensaje(Evento.PARTIDA_CREADA, partida));
    }

    @Override
    public Mensaje obtenerPartidasMensaje() throws RemoteException {
        ArrayList partidas = GestoraPartidas.getInstance().obtenerPartidas();

        return new Mensaje(Evento.PARTIDAS, partidas);
    }

}
