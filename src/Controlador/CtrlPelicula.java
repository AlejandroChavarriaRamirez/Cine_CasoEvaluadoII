/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author aleja
 */
import Modelo.Pelicula;
import Modelo.SentenciasPelicula;
import Vista.frmPelicula;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class CtrlPelicula implements ActionListener {

    private final Pelicula modelo;
    private final SentenciasPelicula consultas;
    private final frmPelicula vista;

    public CtrlPelicula(Pelicula modelo, SentenciasPelicula consultas, frmPelicula vista) {
        this.modelo = modelo;
        this.consultas = consultas;
        this.vista = vista;
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnConsultar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
    }

    public void inicio() {
        vista.setTitle("Control de Pelicula");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    public void Limpiar() {
        vista.txtId.setText("");
        vista.txtTitulo.setText("");
        vista.txtAnio.setText("");
        vista.txtGenero.setText("");
        vista.txtDuracion.setText("");
    }

    public void actionPerformed(ActionEvent e) {
        //agregar
        if (e.getSource() == vista.btnAgregar) {
            try {
                modelo.setTitulo(vista.txtTitulo.getText());
                modelo.setAnio(Integer.parseInt(vista.txtAnio.getText()));
                modelo.setGenero(vista.txtGenero.getText());
                modelo.setDuracion(Integer.parseInt(vista.txtDuracion.getText()));
                if (consultas.registrar(modelo)) {
                    JOptionPane.showMessageDialog(null, "Registro guardado correctamente");
                    Limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Anio o duracion debe ser numeros");
            }
        }
        //consultar
        if (e.getSource() == vista.btnConsultar) {
            try {
                modelo.setId(Integer.parseInt(vista.txtId.getText()));
                if (consultas.buscar(modelo)) {
                    vista.txtId.setText(String.valueOf(modelo.getId()));
                    vista.txtTitulo.setText(modelo.getTitulo());
                    vista.txtAnio.setText(String.valueOf(modelo.getAnio()));
                    vista.txtGenero.setText(modelo.getGenero());
                    vista.txtDuracion.setText(String.valueOf(modelo.getDuracion()));
                } else {
                    JOptionPane.showMessageDialog(null, "Registro no encontrado");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El id debe ser numero");
            }
        }
        //eliminar
        if (e.getSource() == vista.btnEliminar) {
            try {
                modelo.setId(Integer.parseInt(vista.txtId.getText()));
                if (consultas.eliminar(modelo)) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado correctamente");
                    Limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El id debe ser numero");
            }
        }
    }

}
