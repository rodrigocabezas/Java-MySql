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
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Nan
 */
public class GestoraPartidas {
    private ArrayList<Partida> colPartidas;
    private static GestoraPartidas gestoraPartida = null;
    
     private GestoraPartidas(){
        colPartidas = new ArrayList();
    }
    
    public static GestoraPartidas getInstance(){
        if(gestoraPartida == null){
            gestoraPartida = new GestoraPartidas();
        }
        return gestoraPartida;
    }
    
    public Partida agregarPartida (int filas,int columnas,Usuario jugador,boolean pendiente,int pozo){
        pendiente = true;
        Partida partida = new Partida(filas, columnas,jugador,pendiente,pozo);//Agregar jugador
       
        colPartidas.add(partida);//agregar atributo booleano de pendiente
        return partida;
    }
    public Partida buscarPartidaPendiente(){
        for(Partida p:colPartidas){
            if(p.isPendiente()==true)
            {
                return p;
            }
            return null;
        }
        return null;
    }
    public Mensaje ingresarJugadorParaJugar(Usuario j){
        Partida p = buscarPartidaPendiente();
        int apuesta =0;
        Mensaje mensaje = null;
        if(p==null){
            mensaje = new Mensaje(Evento.CREAR_NUEVA_PARTIDA,j);
            return mensaje;
        }else if(p.getJugadorUno().getNombreUsu().equalsIgnoreCase(j.getNombreUsu())){
            mensaje = new Mensaje(Evento.USUARIO_YA_LOGUEADO, p);
                return mensaje;
        } else{
            apuesta = p.getPozo();
            if(j.getSaldo()>= apuesta){
                
                p.setJugadorDos(j);
                p.getJugadorDos().setSaldo(p.getJugadorDos().getSaldo() - apuesta);
                p.setPozo(p.getPozo() + apuesta);
//                j.setSaldo(j.getSaldo()-p.getPozo());
                mensaje = new Mensaje(Evento.INGRESO_A_PARTIDA, p);
                return mensaje;
            }else{
                mensaje = new Mensaje(Evento.SALDO_INSUFICIENTE, p);
                return mensaje;
            }
            
        }
    }
    
    public ArrayList<Partida> obtenerPartidas(){
        return (ArrayList<Partida>) colPartidas.clone();
        //
    }
    
    public void procesarPartida(int id,Mensaje msj) throws RemoteException{
        Partida p = buscarPartidaPorId(id);
        p.Procesar(msj);
        
    }
    
    public Partida buscarPartidaPorId(int id){
        for(Partida p:colPartidas){
            if(p.getIdPartida()==id)
            {
                return p;
            }
            return null;
        }
        return null;
    }
}
