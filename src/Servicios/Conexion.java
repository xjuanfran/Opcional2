/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Leon
 */
public class Conexion {
    
    
     Connection conexion;
     
         public Conexion() {
    }
      
    public Connection getConnection() {
       
        try {

            try { 
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            conexion = null;
            // Database connect
            // Conectamos con la base de datos
          conexion = DriverManager.getConnection(
                    "jdbc:postgresql://ec2-34-193-44-192.compute-1.amazonaws.com:5432/dd9t35ds6navl5?sslmode=require&user=pbbxqyydefnjkc&password=2a4f098de29dade2d79e86d0f3e78add99eff7719b095b02d8b0890cc230a85d",
                    "pbbxqyydefnjkc" , "2a4f098de29dade2d79e86d0f3e78add99eff7719b095b02d8b0890cc230a85d");
 
            boolean valid = conexion.isValid(50000);
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
             
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error: " + sqle);
             
        }
          return conexion;
    } 
    
    public void cerrarConexion() throws SQLException{
    conexion.close();
    }
    
}
