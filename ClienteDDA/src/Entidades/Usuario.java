package Entidades;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rodrii
 */
public class Usuario implements Serializable{
    private String nombreUsu;
    private String Contrasenia;
    private String NombreCompleto;
    private int saldo;

    public boolean isAdm() {
        return adm;
    }

    public void setAdm(boolean adm) {
        this.adm = adm;
    }
    private boolean adm;

    public Usuario(String nombreUsu, String Contrasenia, String NombreCompleto, int saldo) {
        this.nombreUsu = nombreUsu;
        this.Contrasenia = Contrasenia;
        this.NombreCompleto = NombreCompleto;
        this.saldo = saldo;
    }

    Usuario() {
        
    }

    public String getNombreUsu() {
        return nombreUsu;
    }

    public void setNombreUsu(String nombreUsu) {
        this.nombreUsu = nombreUsu;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String Contrasenia) {
        this.Contrasenia = Contrasenia;
    }

    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public void setNombreCompleto(String NombreCompleto) {
        this.NombreCompleto = NombreCompleto;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
    
    
}

