/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Entidades.Evento;
import java.util.Observable;
import java.util.Observer;
import Entidades.Mensaje;
import Entidades.Usuario;
import Entidades.casillero;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author rodrii
 */
public class Partida extends Observable implements Serializable{
    
    public static int id = 1;

    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }
    public int idPartida;
 
        public Usuario getJugadorUno() {
        return jugadorUno;
    }
      casillero [][] casilleros;
      private String turnoActual;
      private Usuario jugadorUno;
      private Usuario jugadorDos;
      private boolean pendiente=true;

    public int getPozo() {
        return pozo;
    }

    public void setPozo(int pozo) {
        this.pozo = pozo;
    }
      public int pozo;

    public boolean isPendiente() {
        return pendiente;
    }

    public void setPendiente(boolean pendiente) {
        this.pendiente = pendiente;
    }

    public Usuario getJugadorDos() {
        return jugadorDos;
    }

    public void setJugadorDos(Usuario jugadorDos) {
        this.jugadorDos = jugadorDos;
    }

    @Override
    public String toString() {
        return "turno de " + turnoActual ;
    }
      
      
//    private static Partida instancia;
    
    public Partida(int filas,int columnas,Usuario jugador,boolean estado,int pozo){
        idPartida = id++;
        jugadorUno = jugador;
        turnoActual = jugador.getNombreUsu();
       inicializarCasilleros(filas,columnas);
    }
     
//      public static Partida GetInstancia(int x,int y,Usuario jugador,boolean estado)
//    {
////        if (instancia == null) 
//            instancia = new Partida(x,y,jugador,estado);       
//        
//        return instancia;
//    }
     
    private void inicializarCasilleros(int filas,int columnas){
        casilleros = new casillero[filas][columnas];
        for(int f = 0; f< filas; f++){
            for(int c =0; c<columnas;c++){
                casilleros [f][c]= new casillero();
            }
        }
    }
    
    public casillero obtenerCasillero(int x, int y){
        return casilleros[x][y];
    }
    
    public int obtenerCantidadFilas(){
        return casilleros.length;
    }
    public int obtenerCantidadColumnas(){
        return casilleros[0].length;
    }
    
    public void Procesar(Mensaje msg){
//        Mensaje mensaje = null;
        if(msg.getEvento()== Evento.CASILLERO_SELECCIONADO){
            int[]coord = (int[])msg.getAux();
            casillero c = this.obtenerCasillero(coord[0], coord[1]);
            if( c.lugarMinado()){
                setChanged();
                notifyObservers(new Mensaje(Evento.PERDISTE,this));
            }
            if(c.isGolpeado()){
                setChanged();
                notifyObservers(new Mensaje(Evento.JUGADA_NO_PERMITIDA,this));
            }else{
                c.setGolpeado(true);
                this.cambiarTurno();
                setChanged();
                notifyObservers(new Mensaje(Evento.JUGADA_REALIZADA,this));
            }
        }
    }
    private void NotificarAccion(String accion, String valor)
    {
       Mensaje aux= new Mensaje(Evento.JUGADA_REALIZADA, valor);
        
        this.setChanged();
        this.notifyObservers(aux);
    }
     public void AgregarJugador(Observer jugador)
    {
        this.addObserver(jugador);
        
        if (this.countObservers() == 2)
        {
            //asignar turno y notificar
            this.cambiarTurno();
            this.NotificarAccion("Turno",this.turnoActual);            
        }
        
    
}
       private void cambiarTurno()
    {
       if (this.turnoActual == jugadorUno.getNombreUsu()){
           this.turnoActual = jugadorDos.getNombreUsu();
       }else{
           this.turnoActual = jugadorUno.getNombreUsu();       
       }
        
        
            
    }
       
}