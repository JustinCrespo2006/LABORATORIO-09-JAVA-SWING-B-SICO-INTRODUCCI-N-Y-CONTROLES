package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class TareaVista extends JFrame {
    private final JTextField txtNuevaTarea;
    private final JButton btnAgregar;
    private final JButton btnCompletar;
    private final DefaultListModel<String> modeloListaVisual;
    private final JList<String> listaVisual;

    public TareaVista() {
        setTitle("Gestor de Tareas - MVC");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelSuperior = new JPanel(new BorderLayout(5, 5));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        txtNuevaTarea = new JTextField();
        btnAgregar = new JButton("Anotar Tarea");
        panelSuperior.add(new JLabel("Nueva tarea:"), BorderLayout.NORTH);
        panelSuperior.add(txtNuevaTarea, BorderLayout.CENTER);
        panelSuperior.add(btnAgregar, BorderLayout.EAST);

        modeloListaVisual = new DefaultListModel<>();
        listaVisual = new JList<>(modeloListaVisual);
        listaVisual.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollLista = new JScrollPane(listaVisual);
        scrollLista.setBorder(BorderFactory.createTitledBorder("Tareas Pendientes"));

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnCompletar = new JButton("Completar y Eliminar");
        panelInferior.add(btnCompletar);

        add(panelSuperior, BorderLayout.NORTH);
        add(scrollLista, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    public String getTextoTarea() {
        return txtNuevaTarea.getText();
    }

    public void limpiarCampoTexto() {
        txtNuevaTarea.setText("");
        txtNuevaTarea.requestFocus();
    }

    public int getIndiceSeleccionado() {
        return listaVisual.getSelectedIndex();
    }

    public void actualizarLista(List<String> tareas) {
        modeloListaVisual.clear();
        for (String tarea : tareas) {
            modeloListaVisual.addElement(tarea);
        }
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Atención", JOptionPane.WARNING_MESSAGE);
    }

    public void addAgregarListener(ActionListener listener) {
        btnAgregar.addActionListener(listener);
        txtNuevaTarea.addActionListener(listener);
    }

    public void addCompletarListener(ActionListener listener) {
        btnCompletar.addActionListener(listener);
    }
}