/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.PacienteAlergiaDAO;
import DAO.PacientesDAO;
import Modelo.ModelAlergias;
import Modelo.ModelPacientes;
import Vista.Paciente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Leon
 */
public class ControladorPacientes {

    private PacientesDAO sql;
    private PacienteAlergiaDAO sql2;
    private Paciente vista;
    List<ModelPacientes> pacientes;
    List<ModelAlergias> alergias;
    List<ModelAlergias> alergiasAll;

    DefaultListModel<String> model = new DefaultListModel();

    public ControladorPacientes() {
    }

    public ControladorPacientes(PacientesDAO sql, Paciente vista, PacienteAlergiaDAO sql2) {
        this.sql = sql;
        this.sql2 = sql2;
        this.vista = vista;

        vista.setVisible(true);
        vista.setLocationRelativeTo(null);

        this.vista.addBtnAgregar(new CalculateListener());
        this.vista.addBtnActualizar(new CalculateListener());
        this.vista.addBtnCancelar(new CalculateListener());
        rellenarCombo();

    }

    public void listarPacientes() {
        try {

            String id = vista.getTxtIdentificacion().getText();
            pacientes = sql.listadoPacientes(id);

            if (pacientes.isEmpty()) {

                vista.btnGrabar.setText("Grabar");

            } else {

                vista.txtApellidos.setText(pacientes.get(0).getApellidos());
                vista.txtNombres.setText(pacientes.get(0).getNombre());
                vista.txtTelefono.setText(pacientes.get(0).getTelefono());
                vista.txtDirección.setText(pacientes.get(0).getDireccion());
                vista.btnGrabar.setText("Actualizar");

                vista.limpiarListadoLista();
                alergias = sql2.listadoPacientesAlergia(id);

                for (int i = 0; i < alergias.size(); i++) {
                    model.addElement(alergias.get(i).getNombre());
                }

                vista.jListAlergias.setModel(model);

            }

        } catch (Exception ex) {
            vista.displayErrorMessage("Error en la consulta");
        }
    }

    class CalculateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getActionCommand().equalsIgnoreCase("Actualizar")) {

                String identificacion;
                String apellidos;
                String nombre;
                String telefono;
                String direccion;
                
                model.clear();

                vista.txtApellidos.setText("");
                vista.txtNombres.setText("");
                vista.txtTelefono.setText("");
                vista.txtDirección.setText("");
                
                identificacion = (vista.getTxtIdentificacion().getText());
                sql.listadoPacientes(identificacion);
                listarPacientes();

            }

            if (e.getActionCommand().equalsIgnoreCase("Grabar")) {

                String identificacion;
                String apellidos;
                String nombre;
                String telefono;
                String direccion;            
    
                identificacion = vista.getTxtIdentificacion().getText();
                apellidos = vista.getTxtApellidos().getText();
                nombre = vista.getTxtNombres().getText();
                telefono = vista.getTxtTelefono().getText();
                direccion = vista.getTxtDirección().getText();
                sql.crearPacientes(identificacion, apellidos, nombre, telefono, direccion);
                listarPacientes();
            }
            if (e.getActionCommand().equalsIgnoreCase("Cancelar")) {

                System.exit(0);
            }
            if (e.getActionCommand().equalsIgnoreCase("Agregar")) {
                
                alergiasAll = sql2.listadoAlergiaAll();
                String idPaciente = (vista.getTxtIdentificacion().getText());
                int posicion = vista.cbxAlergia.getSelectedIndex();
                int idAlergia = alergiasAll.get(posicion).getId();
                System.out.println(idAlergia);

                sql2.agregarAlergia(idPaciente, idAlergia);
                vista.limpiarListadoLista();
                model.clear();
                DefaultListModel<String> model = new DefaultListModel();
                
                alergias = sql2.listadoPacientesAlergia(idPaciente);

                for (int i = 0; i < alergias.size(); i++) {
                    model.addElement(alergias.get(i).getNombre());
                }
                vista.jListAlergias.setModel(model);

            }
        }
    }

    public void rellenarCombo() {

        String nombre;

        try {
            vista.cbxAlergia.removeAllItems();
            alergias = sql2.listadoComboAlergias();
            for (int i = 0; i < alergias.size(); i++) {
                nombre = alergias.get(i).getNombre();
                vista.cbxAlergia.addItem(nombre);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar ComboBox" + e);
        }
    }

}
