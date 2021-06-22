/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientedda;

import Entidades.Mensaje;
import Entidades.Usuario;

/**
 *
 * @author Nan
 */
public interface IFrmAppCliente {
     public void Procesar (Mensaje msg);
    public void setController(Controlador controlador);
    public String obtenerNombreIngresado();
    public String obtenerContrasenaIngresada();
//    public Usuario obtenerUsuarioForm();
    
}
