/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Remotas;

import Entidades.Mensaje;
import Entidades.Partida;
import Entidades.Usuario;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Nan
 */
public interface IFachadaServidor extends Remote{
    public void agregarObservador(IRemoteObserver observer)throws RemoteException;
    public void agregarPartida (int filas, int columna,Usuario jugador,boolean pendiente,int pozo)throws RemoteException;
    public ArrayList<Partida> obtenerPartidas()throws RemoteException;
    public ArrayList<Usuario> obtenerUsuarios()throws RemoteException;
    public void Login(String nombre,String contrasena) throws RemoteException;
    public Mensaje IngresarJugador(String nombre,String contrasena) throws RemoteException;
    public void procesarPartida(int id,Mensaje msj) throws RemoteException;
    public Mensaje obtenerPartidasMensaje() throws RemoteException;
}
