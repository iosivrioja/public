/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import AccesoDatos.Conexion;
import Negocio.ELogin;
import Negocio.EUsuarios;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laboratorio_computo
 */
public class Login {
    Conexion objConex = new Conexion();
    CallableStatement stmt;
    ResultSet rs;
    Connection connect = null;
    
    public Login() throws SQLException, ClassNotFoundException{
        connect = objConex.getConexion();
    }
    
    public ArrayList iniciarSesion(ELogin objLogin)
    {
        ArrayList arrayList = new ArrayList();
            
        try{
            
            stmt = connect.prepareCall("SELECT * FROM func_login(?, ?)");
            stmt.setString(1, objLogin.getCode());
            stmt.setString(2, objLogin.getPassword());
            rs = stmt.executeQuery();
            
            if(rs.next()){
                EUsuarios objUser = new EUsuarios();
                objUser.setCodigo(rs.getString("codigo"));
                objUser.setNombres(rs.getString("nombres"));
                objUser.setCorreo(rs.getString("correo"));
                objUser.setEstado(rs.getString("estado"));
                objUser.setTipoRol(rs.getString("role"));
                arrayList.add(objUser);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayList;
    }
}
