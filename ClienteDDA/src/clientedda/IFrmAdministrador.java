/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientedda;

import Entidades.Mensaje;

/**
 *
 * @author Nan
 */
public interface IFrmAdministrador {
    public void Procesar (Mensaje msg);
    public void setController(ControladorAdministrador controlador);
}
