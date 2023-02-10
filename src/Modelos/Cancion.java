/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import AccesoDatos.Conexion;
import Negocio.EArtista;
import Negocio.ECancion;
import Negocio.EComboBox;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author laboratorio_computo
 */
public class Cancion {
    Conexion objConex = new Conexion();
    CallableStatement stmt;
    ResultSet rs;
    Connection connect = null;
    
    public Cancion() {
        connect = objConex.getConexion();
    }
    
    public ArrayList getAll(){
        ArrayList arrayList = new ArrayList();
            
        try{
            
            stmt = connect.prepareCall("SELECT * FROM func_listar_cancion()");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                ECancion objCancion = new ECancion();
                
                EArtista objArtista = new EArtista();
                
                objArtista.setNombre(rs.getString("artista"));
                
                objCancion.setId(rs.getInt("id_cancion"));
                objCancion.setNombre(rs.getString("nombre"));
                objCancion.setTipo(rs.getString("tipo"));
                objCancion.setEstado(rs.getString("estado"));
                objCancion.setArtista(objArtista);
                arrayList.add(objCancion);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayList;
    }
    
    public ArrayList searchById(int id){
        ArrayList arrayList = new ArrayList();
        
        try{
            
            stmt = connect.prepareCall("SELECT * FROM func_buscar_cancion(?)");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                ECancion objCancion = new ECancion();
                EArtista objArtista = new EArtista();
                
                objArtista.setNombre(rs.getString("artista"));
                
                objCancion.setId(rs.getInt("id_cancion"));
                objCancion.setNombre(rs.getString("nombre"));
                objCancion.setTipo(rs.getString("tipo"));
                objCancion.setEstado(rs.getString("estado"));
                objCancion.setArtista(objArtista);
                arrayList.add(objCancion);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayList;
    }
    
    public ArrayList searchByName(String name){
        ArrayList arrayList = new ArrayList();
        
        try{
            
            stmt = connect.prepareCall("SELECT * FROM func_buscar_cancion_nombre(?)");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                ECancion objCancion = new ECancion();
                EArtista objArtista = new EArtista();
                
                objArtista.setNombre(rs.getString("artista"));
                
                objCancion.setId(rs.getInt("id_cancion"));
                objCancion.setNombre(rs.getString("nombre"));
                objCancion.setTipo(rs.getString("tipo"));
                objCancion.setEstado(rs.getString("estado"));
                objCancion.setArtista(objArtista);
                arrayList.add(objCancion);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayList;
    }
    
    public String create(ECancion objCancion){
        String message = "";
        
        try{
            
            stmt = connect.prepareCall("call sp_registrar_cancion(?, ?, ?, ?, ?)");
            stmt.setString(1, objCancion.getNombre());
            stmt.setString(2, objCancion.getTipo());
            stmt.setString(3, objCancion.getEstado());
            stmt.setInt(4, objCancion.getArtista().getId());
            stmt.registerOutParameter(5, Types.VARCHAR);
            stmt.executeUpdate();
            message = stmt.getString(5);
                    
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return message;
    }
    
    public String update(ECancion objCancion){
        String message = "";
        
        try{
            
            stmt = connect.prepareCall("call sp_actualizar_cancion(?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, objCancion.getId());
            stmt.setString(2, objCancion.getNombre());
            stmt.setString(3, objCancion.getTipo());
            stmt.setString(4, objCancion.getEstado());
            stmt.setInt(5, objCancion.getArtista().getId());
            stmt.registerOutParameter(6, Types.VARCHAR);
            stmt.executeUpdate();
            message = stmt.getString(6);
                    
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return message;
    }
    
    public ComboBoxModel fillCombobox(){
        DefaultComboBoxModel comboBox = new DefaultComboBoxModel();
        
        ArrayList arrayList = this.getAll();
        for (int i = 0; i < arrayList.size(); i++) {
            ECancion cancion = (ECancion)arrayList.get(i);
            comboBox.addElement(new EComboBox(cancion.getId(), cancion.getNombre()));
        }
        
        return comboBox;
    }
    
}
