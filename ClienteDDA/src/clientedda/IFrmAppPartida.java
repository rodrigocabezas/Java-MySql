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
public interface IFrmAppPartida {
    void procesar(Mensaje msg);
    void setControlador(ControladorPrePartida cp);
    public String obtenerFilasIngresadas();
    public String obtenerColumnasIngresadas();
    public String obtenerApuestaIngresada();
    public Usuario obtenerUsuarioForm();
    
}
