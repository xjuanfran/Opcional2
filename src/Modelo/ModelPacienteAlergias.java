/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Leon
 */
public class ModelPacienteAlergias {
    
    private String idPaciente;
    private int idAlergia;

    public ModelPacienteAlergias() {
    }

    public ModelPacienteAlergias(String idPaciente, int idAlergia) {
        this.idPaciente = idPaciente;
        this.idAlergia = idAlergia;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdAlergia() {
        return idAlergia;
    }

    public void setIdAlergia(int idAlergia) {
        this.idAlergia = idAlergia;
    }

        
    
}
