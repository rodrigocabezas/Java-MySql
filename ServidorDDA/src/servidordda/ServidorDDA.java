/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidordda;

import Entidades.Partida;
import Entidades.Usuario;
import Remotas.IFachadaServidor;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nan
 */
public class ServidorDDA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            IFachadaServidor f = (IFachadaServidor) UnicastRemoteObject.exportObject(FachadaServidor.getInstance(), 0);
            LocateRegistry.createRegistry(1099);
            Naming.rebind("fachadaServidor", f);
            System.out.println("Servidor iniciado");
           ArrayList<Partida> colPartida = GestoraPartidas.getInstance().obtenerPartidas();
     for(Partida p:colPartida){
//            if(u.getNombreUsu().equalsIgnoreCase("Facundo")){
               System.out.println(p.obtenerCantidadFilas());
           //    System.out.println(u.getContrasenia());
//               
//           }else{
//                  System.out.println("NOOOO"); 
//                  System.out.println(u.getNombreUsu());
             }
          // }
//           if(GestoraUsuario.getInstance().Login("Facundo", "abcdef") == true){
//           System.out.println("SIII");
//           }else{
//           System.out.println("Nada che");
//           }  
//           if(GestoraUsuario.getInstance().Login("Jugador", "abcd") != false){
//               System.out.println("SII");
//           }else{
//               System.out.println("NOO");
//           }
//           FachadaServidor.getInstance().Login("Jugador","abcd");
           
        } catch (RemoteException ex) {
            Logger.getLogger(ServidorDDA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MalformedURLException.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
