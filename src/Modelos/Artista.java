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
public class Artista {
    Conexion objConex = new Conexion();
    CallableStatement stmt;
    ResultSet rs;
    Connection connect = null;
    
    public Artista() {
        connect = objConex.getConexion();
    }
    
    public ArrayList getAll(){
        ArrayList arrayList = new ArrayList();
            
        try{
            
            stmt = connect.prepareCall("SELECT * FROM func_listar_artista()");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                EArtista objArtista = new EArtista();
                objArtista.setId(rs.getInt("id_artista"));
                objArtista.setNombre(rs.getString("nombre"));
                objArtista.setSexo(rs.getString("sexo"));
                objArtista.setNacionalidad(rs.getString("nacionalidad"));
                arrayList.add(objArtista);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayList;
    }
    
    public ArrayList searchById(int id){
        ArrayList arrayList = new ArrayList();
            
        try{
            
            stmt = connect.prepareCall("SELECT * FROM func_buscar_artista(?)");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                EArtista objArtista = new EArtista();
                objArtista.setId(rs.getInt("id_artista"));
                objArtista.setNombre(rs.getString("nombre"));
                objArtista.setSexo(rs.getString("sexo"));
                objArtista.setNacionalidad(rs.getString("nacionalidad"));
                arrayList.add(objArtista);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayList;
    }
    
    public ArrayList searchByName(String name){
        ArrayList arrayList = new ArrayList();
        
        try{
            
            stmt = connect.prepareCall("SELECT * FROM func_buscar_artista_nombre(?)");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                EArtista objArtista = new EArtista();
                objArtista.setId(rs.getInt("id_artista"));
                objArtista.setNombre(rs.getString("nombre"));
                objArtista.setSexo(rs.getString("sexo"));
                objArtista.setNacionalidad(rs.getString("nacionalidad"));
                arrayList.add(objArtista);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayList;
    }
    
    public String create(EArtista objArtista){
        String message = "";
        
        try{
            
            stmt = connect.prepareCall("call sp_registrar_artista(?, ?, ?, ?)");
            stmt.setString(1, objArtista.getNombre());
            stmt.setString(2, objArtista.getSexo());
            stmt.setString(3, objArtista.getNacionalidad());
            stmt.registerOutParameter(4, Types.VARCHAR);
            stmt.executeUpdate();
            message = stmt.getString(4);
                    
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return message;
    }
    
    public String update(EArtista objArtista){
        String message = "";
        
        try{
            
            stmt = connect.prepareCall("call sp_actualizar_artista(?, ?, ?, ?, ?)");
            stmt.setInt(1, objArtista.getId());
            stmt.setString(2, objArtista.getNombre());
            stmt.setString(3, objArtista.getSexo());
            stmt.setString(4, objArtista.getNacionalidad());
            stmt.registerOutParameter(5, Types.VARCHAR, 100);
            stmt.executeUpdate();
            message = stmt.getString(5);
                    
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return message;
    }
    
    public ComboBoxModel fillCombobox(){
        DefaultComboBoxModel comboBox = new DefaultComboBoxModel();
        
        ArrayList arrayList = this.getAll();
        for (int i = 0; i < arrayList.size(); i++) {
            EArtista artista = (EArtista)arrayList.get(i);
            comboBox.addElement(new EComboBox(artista.getId(), artista.getNombre()));
        }
        
        return comboBox;
    }
    
}
