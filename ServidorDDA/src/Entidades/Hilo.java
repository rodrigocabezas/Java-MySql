/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;

/**
 *
 * @author Nan
 */
public class Hilo implements Runnable, Serializable{
    
    
    public static final int EVENTO_ADD_SECONDS = 1;
    public static final int EVENTO_TIME_OUT = 2;
    
    private transient Thread hilo;
    private boolean ejecutar = false;
    private int segundos;
    private final transient MiObservable observable = new MiObservable();
    
    
    public Hilo(){
        
    }

    public int getSegundos() {
        return segundos;
    }  
    
    
    private void avisar(Object evento){
        observable.avisar(evento);
    }

    public void ejecutar(){
        if(!ejecutar ){
            ejecutar = true;
            hilo = new Thread(this);
            hilo.start();
        }
    }

    public void parar(){
        ejecutar=false;
        hilo.interrupt();
    }
    
    public void reset(){
        segundos = 0;
        avisar(Hilo.EVENTO_ADD_SECONDS);
    }

    @Override
    public void run() {
        for (;segundos< Partida.getTIEMPO_LIMITE()*60&&ejecutar;segundos++){
            avisar(Hilo.EVENTO_ADD_SECONDS);
            try {
                hilo.sleep(1000);     
                
            } catch (InterruptedException ex) {
                
                System.err.println("modelo.Proceso.run() " + ex.getMessage());
                
            }
        }
        ejecutar = false;    
        reset();
        avisar(Hilo.EVENTO_TIME_OUT);
//        JOptionPane.showMessageDialog(null, "Pasaron 60 segundos,ronda sin apostar");
        
    }
    
}
