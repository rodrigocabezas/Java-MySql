/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Remotas;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Nan
 */
public interface IRemoteObserver extends Remote{
    public void update(IFachadaServidor i,Object c)throws RemoteException;
}
