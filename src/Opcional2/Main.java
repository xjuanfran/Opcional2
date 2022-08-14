/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Opcional2;

import Controlador.ControladorPacientes;
import DAO.PacienteAlergiaDAO;
import DAO.PacientesDAO;
import Modelo.ModelPacientes;
import Vista.Paciente;

/**
 *
 * @author Leon
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Paciente vista = new Paciente();
        PacientesDAO model= new PacientesDAO();
        PacienteAlergiaDAO modeloAlergia = new PacienteAlergiaDAO();
        ControladorPacientes control = new ControladorPacientes(model, vista, modeloAlergia);  
        
    }
    
}
