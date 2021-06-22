/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.FileInputStream;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author home
 */
public class DataBase {

    static String ipBD;
    static String portBD;
    static String serviceBD;
    static String userBD;
    static String passwd;
    static String url;
    static boolean logConect = true;

    Connection conn = null;

    public DataBase() {
        try {

            ipBD = "localhost";
            serviceBD = "obligatorio";
            userBD = "root";
            passwd = "";
            portBD = "3306";

            String hostname = java.net.InetAddress.getLocalHost().getHostName().trim();
            this.printSystem("hostname:" + hostname);

            url = "jdbc:mysql://" + ipBD + ":" + portBD + "/" + serviceBD + "?useServerPrepStmts=true";
            this.printSystem("url = " + url);
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userBD, passwd);

            if (conn != null) {
                conn.setAutoCommit(false);
                this.printSystem("---> CONECTADO AL SERVIDOR: " + ipBD + " BASE:" + serviceBD);
            } else {
                this.printSystem("---> NO! CONECTADO AL SERVIDOR: " + ipBD);
            }
            logConect = false;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public Connection getConnection() throws Exception {
        try {
            if (this.conn == null) {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection(url, userBD, passwd);

                if (conn != null) {
                    conn.setAutoCommit(false);
                } else {
                    throw (new Exception("null"));
                }
            }
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
                conn.close();
            }
            throw (new Exception("Sin Conexion Base de Datos. [" + e.getMessage() + "]"));
        }
        return conn;
    }

    public void printSystem(String msj) {
        if (logConect) {
            System.out.println(msj);
        }
    }

    public ArrayList<Usuario> obtenerPersonas() {
        Connection conn = null;
        ArrayList<Usuario> lst = new ArrayList<>();
        try {
            conn = getConnection();
            String query = "select * from usuario";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setNombreUsu(rs.getString("nombre"));
                u.setContrasenia(rs.getString("contrasena"));
                u.setNombreCompleto(rs.getString("nombreCompleto"));
                u.setSaldo(rs.getInt("saldo"));
                u.setAdm(rs.getBoolean("adm"));
                lst.add(u);
            }
            return lst;
        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException sq) {
            }

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException sq) {
            }
        }
        return lst;
    }

    public Usuario validate_login(String nombre, String contrasena) {
        try {
            Connection conn = getConnection();
            PreparedStatement pst = conn.prepareStatement("Select * from usuario where nombre=? and contrasena=?");
            pst.setString(1, nombre);
            pst.setString(2, contrasena);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                 Usuario u = new Usuario();
                u.setNombreUsu(rs.getString("nombre"));
                u.setContrasenia(rs.getString("contrasena"));
                u.setNombreCompleto(rs.getString("nombreCompleto"));
                u.setSaldo(rs.getInt("saldo"));
                return u;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
