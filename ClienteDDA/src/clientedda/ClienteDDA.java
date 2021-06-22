/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientedda;

import Remotas.IFachadaServidor;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nan
 */
public class ClienteDDA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         try {
            IFachadaServidor fachada = (IFachadaServidor) Naming.lookup(("rmi://localhost/fachadaServidor"));
            FrmLogin frm = new FrmLogin(fachada);
            Controlador controlador = new Controlador(fachada, frm);
            frm.setVisible(true);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(ClienteDDA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
