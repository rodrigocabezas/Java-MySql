/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidordda;

import Entidades.DataBase;
import Entidades.Partida;
import Entidades.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Nan
 */
public class GestoraUsuario {
    private DataBase db = new DataBase();
     private ArrayList<Usuario> colUsuarios = new DataBase().obtenerPersonas();;
    private static GestoraUsuario gestoraUsuario = null;
    
     private GestoraUsuario(){
        colUsuarios = new ArrayList();
    }
    
    public static GestoraUsuario getInstance(){
        if(gestoraUsuario == null){
            gestoraUsuario = new GestoraUsuario();
        }
        return gestoraUsuario;
    }
    
    public void agregarUsuario (Usuario c){
        colUsuarios.add(c);
    }
    
    public ArrayList<Usuario> obtenerUsuario(){
        ArrayList<Usuario> colUsuarios =  new DataBase().obtenerPersonas();
////        colUsuarios = db.obtenerPersonas();
        return colUsuarios;
//        return (ArrayList<Usuario>) colUsuarios.clone();
    }
    
    public Usuario Login(String nombre,String contrasena){
//        DataBase db = new DataBase();
        ArrayList<Usuario> colU=  new DataBase().obtenerPersonas();
        Usuario usuario = null;
        for(Usuario u:colU)
        {
            
            if(u.getNombreUsu().equalsIgnoreCase(nombre) && u.getContrasenia().equalsIgnoreCase(contrasena)){
                usuario = u;
            
            
        }
        
    }
        return usuario;
        
}
    
    public Usuario buscarUsuarioPorNombre(String nombre){
        for(Usuario u:colUsuarios){
            if(u.getNombreUsu()==nombre)
            {
                return u;
            }
            return null;
        }
        return null;
    }
}
