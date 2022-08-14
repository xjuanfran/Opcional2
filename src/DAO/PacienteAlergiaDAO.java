/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.ModelAlergias;
import Modelo.ModelPacientes;
import Modelo.ModelPacienteAlergias;
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
public class PacienteAlergiaDAO {
      
    Conexion Conectar = new Conexion();

    public PacienteAlergiaDAO() {
    }
    
     public ArrayList<ModelAlergias> listadoPacientesAlergia(String id) {
        Connection con = null;
        
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        PreparedStatement pstm2 = null;
        ResultSet rs2 = null;
        
        ArrayList<ModelPacienteAlergias> listadoPacientesAlergia = new ArrayList<>();
        ArrayList<ModelAlergias> listadoModelAlergias = new ArrayList<>();
        
        try {
            con = Conectar.getConnection();
            String sql = "";
            sql = "SELECT * FROM pacientealergias where id_paciente = '"+ id +"'";
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();

            ModelPacienteAlergias pacienteAlergia = null;
            while (rs.next()) {
                pacienteAlergia = new ModelPacienteAlergias();
                pacienteAlergia.setIdPaciente(rs.getString("id_paciente"));
                pacienteAlergia.setIdAlergia(rs.getInt("id_alergia"));
                listadoPacientesAlergia.add(pacienteAlergia);
            }
            
            String sql2 = "";
            sql2 = "SELECT * FROM alergias";
            pstm2 = con.prepareStatement(sql2);

            rs2 = pstm2.executeQuery();
            
            ModelAlergias alergias = null;
            while (rs2.next()) {
                alergias = new ModelAlergias();
                int comparador = rs2.getInt("id");
                for (int i = 0; i < listadoPacientesAlergia.size(); i++) {
                    if(comparador == listadoPacientesAlergia.get(i).getIdAlergia()){
                        alergias.setId(rs2.getInt("id"));
                        alergias.setNombre(rs2.getString("nombre"));
                    }
                }    
                listadoModelAlergias.add(alergias);
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
        return listadoModelAlergias;
    }
     
    public void agregarAlergia(String idPaciente, int idAlergia) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = Conectar.getConnection();
            String sql = "";
            sql = "INSERT INTO pacientealergias values('" +idPaciente+"',"
                    +idAlergia + ")";
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

        public void modificarAlergia(String idPaciente, int idAlergia){
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = Conectar.getConnection();
            String sql = "";
            sql ="UPDATE pacientealergias SET "
                    + "id_alergia=" + idAlergia 
                    + " WHERE id_paciente='"+idPaciente + "'";
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
        
        public ArrayList<ModelAlergias> listadoComboAlergias() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<ModelAlergias> listadoAlergias = new ArrayList<>();
        try {
            con = Conectar.getConnection();
            String sql = "";
            sql = "SELECT * FROM alergias ";
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();

            ModelAlergias alergia = null;
            while (rs.next()) {
                alergia = new ModelAlergias();
                alergia.setId(rs.getInt("id"));
                alergia.setNombre(rs.getString("nombre"));
                listadoAlergias.add(alergia);
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
        return listadoAlergias;
    }
        
    public ArrayList<ModelAlergias> listadoAlergiaAll() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<ModelAlergias> listadoAlergias = new ArrayList<>();
        try {
            con = Conectar.getConnection();
            String sql = "";
            sql = "SELECT * FROM alergias ";
            pstm = con.prepareStatement(sql);

            rs = pstm.executeQuery();

            ModelAlergias alergia = null;
            while (rs.next()) {
                alergia = new ModelAlergias();
                alergia.setId(rs.getInt("id"));
                alergia.setNombre(rs.getString("nombre"));
                listadoAlergias.add(alergia);
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
        return listadoAlergias;
    }

}
