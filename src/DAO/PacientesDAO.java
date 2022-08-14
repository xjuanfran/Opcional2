/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.ModelPacientes;
import Servicios.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Leon
 */
public class PacientesDAO {
    
    Conexion Conectar = new Conexion();

    public PacientesDAO() {
    }
    
    public ArrayList<ModelPacientes> listadoPacientes(String id) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<ModelPacientes> listadoPacientes = new ArrayList<>();
        try {
            con = Conectar.getConnection();
            String sql = "";
            sql = "SELECT * FROM pacientes where identificacion='"+id+"'" ;
            pstm = con.prepareStatement(sql);

            rs = pstm.executeQuery();

            ModelPacientes paciente = null;
            while (rs.next()) {
                paciente = new ModelPacientes();
                paciente.setIdentificacion(rs.getString("identificacion"));
                paciente.setApellidos(rs.getString("apellidos"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setDireccion(rs.getString("direccion"));
                listadoPacientes.add(paciente);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código : "
                    + ex.getErrorCode() + "\nError :" + ex.getMessage());
        } finally {
            try { 
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Código : "
                        + ex.getErrorCode() + "\nError :" + ex.getMessage());
            }
        }
        return listadoPacientes;
    }
    
        public void crearPacientes(String identificacion, String apellido, 
                String nombre, String telefono, String direccion) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = Conectar.getConnection();
            String sql = "";
            sql = "INSERT INTO pacientes values('" +identificacion+"','"
                    +apellido+"','"+nombre+"','"+telefono+"','"+direccion+"')";
            pstm = con.prepareStatement(sql);

            pstm.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código : "
                    + ex.getErrorCode() + "\nError :" + ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Código : "
                        + ex.getErrorCode() + "\nError :" + ex.getMessage());
            }
        }

        JOptionPane.showMessageDialog(null, "Se ha insertado con exito el registro");
    }
        
        public void modificarPacientes(String identificacion, String apellido, 
           String nombre, String telefono, String direccion) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = Conectar.getConnection();
            String sql = "";
            sql ="UPDATE pacientes SET "
                    + "identificacion='" + identificacion + "', apellidos='"+ apellido
                    + "' , nombre='" + nombre + "', telefono='"+ telefono + "',direccion='" + direccion
                    + "' WHERE identificacion='"+identificacion + "'";
            pstm = con.prepareStatement(sql);

            pstm.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Código : "
                    + ex.getErrorCode() + "\nError :" + ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Código : "
                        + ex.getErrorCode() + "\nError :" + ex.getMessage());
            }
        }

        JOptionPane.showMessageDialog(null, "Se ha modificado con exito el registro");
    }
}
