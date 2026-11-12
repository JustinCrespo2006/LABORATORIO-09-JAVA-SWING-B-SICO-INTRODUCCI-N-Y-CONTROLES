package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VistaCompraPasaje extends JFrame {

    // 1. Etiquetas (rotulan qué información se ingresa en cada componente)
    private JLabel lblNombres;
    private JLabel lblDocumento;
    private JLabel lblFechaViaje;
    private JLabel lblOrigen;
    private JLabel lblDestino;
    private JLabel lblPiso;
    private JLabel lblServiciosOpcionales;
    private JLabel lblCalidadServicio;

    // 2. Campos de Texto (nombres, documento de identidad y fecha de viaje)
    private JTextField txtNombres;
    private JTextField txtDocumento;
    private JTextField txtFechaViaje;

    // 3. Cuadros combinados (lugar de origen y de destino)
    private JComboBox<String> cbOrigen;
    private JComboBox<String> cbDestino;

    // 4. Botones de Opción (1er o 2do piso)
    private JRadioButton rbPrimerPiso;
    private JRadioButton rbSegundoPiso;
    private ButtonGroup bgPiso;

    // 5. Casillas de Verificación (servicios opcionales: audífonos, manta, revistas)
    private JCheckBox chkAudifonos;
    private JCheckBox chkManta;
    private JCheckBox chkRevistas;

    // 6. Lista (3 calidades de servicio: económico, standard, VIP)
    private JList<String> lstCalidadServicio;

    // 7. Botones de Comando (reiniciar/blanquear y mostrar diálogo)
    private JButton btnReiniciar;
    private JButton btnMostrarResumen;

    public VistaCompraPasaje() {
        setTitle("Empresa de Transporte Terrestre - Compra de Pasajes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 480);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Pasajero y Viaje"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Inicialización de Etiquetas
        lblNombres = new JLabel("Nombres Completos:");
        lblDocumento = new JLabel("Documento de Identidad:");
        lblFechaViaje = new JLabel("Fecha de Viaje (dd/mm/aaaa):");
        lblOrigen = new JLabel("Lugar de Origen:");
        lblDestino = new JLabel("Lugar de Destino:");
        lblPiso = new JLabel("Piso de Preferencia:");
        lblServiciosOpcionales = new JLabel("Servicios Opcionales:");
        lblCalidadServicio = new JLabel("Calidad de Servicio:");

        // Inicialización de Campos de Texto
        txtNombres = new JTextField(20);
        txtDocumento = new JTextField(15);
        txtFechaViaje = new JTextField(10);

        // Inicialización de Cuadros Combinados (usando Generics en Swing)
        String[] ciudades = {"-- Seleccione --", "Arequipa", "Lima", "Cusco", "Tacna", "Puno", "Trujillo"};
        cbOrigen = new JComboBox<>(ciudades);
        cbDestino = new JComboBox<>(ciudades);

        // Inicialización de Botones de Opción
        rbPrimerPiso = new JRadioButton("1er Piso");
        rbSegundoPiso = new JRadioButton("2do Piso");
        bgPiso = new ButtonGroup();
        bgPiso.add(rbPrimerPiso);
        bgPiso.add(rbSegundoPiso);
        JPanel panelPiso = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelPiso.add(rbPrimerPiso);
        panelPiso.add(rbSegundoPiso);

        // Inicialización de Casillas de Verificación
        chkAudifonos = new JCheckBox("Audífonos");
        chkManta = new JCheckBox("Manta");
        chkRevistas = new JCheckBox("Revistas");
        JPanel panelServicios = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelServicios.add(chkAudifonos);
        panelServicios.add(chkManta);
        panelServicios.add(chkRevistas);

        // Inicialización de Lista (3 calidades: económico, standard, VIP)
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        modeloLista.addElement("Económico");
        modeloLista.addElement("Standard");
        modeloLista.addElement("VIP");
        lstCalidadServicio = new JList<>(modeloLista);
        lstCalidadServicio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstCalidadServicio.setVisibleRowCount(3);
        JScrollPane scrollLista = new JScrollPane(lstCalidadServicio);

        // Ubicación de componentes en la grilla
        agregarFila(panelFormulario, gbc, 0, lblNombres, txtNombres);
        agregarFila(panelFormulario, gbc, 1, lblDocumento, txtDocumento);
        agregarFila(panelFormulario, gbc, 2, lblFechaViaje, txtFechaViaje);
        agregarFila(panelFormulario, gbc, 3, lblOrigen, cbOrigen);
        agregarFila(panelFormulario, gbc, 4, lblDestino, cbDestino);
        agregarFila(panelFormulario, gbc, 5, lblPiso, panelPiso);
        agregarFila(panelFormulario, gbc, 6, lblServiciosOpcionales, panelServicios);
        agregarFila(panelFormulario, gbc, 7, lblCalidadServicio, scrollLista);

        // Inicialización de Botones de Comando
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        btnReiniciar = new JButton("Reiniciar / Blanquear");
        btnMostrarResumen = new JButton("Registrar y Mostrar Resumen");
        panelBotones.add(btnReiniciar);
        panelBotones.add(btnMostrarResumen);

        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        setContentPane(panelPrincipal);
    }

    private void agregarFila(JPanel panel, GridBagConstraints gbc, int fila, JLabel etiqueta, Component comp) {
        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.weightx = 0.35;
        panel.add(etiqueta, gbc);

        gbc.gridx = 1;
        gbc.gridy = fila;
        gbc.weightx = 0.65;
        panel.add(comp, gbc);
    }

    // --- Métodos para que el Controlador obtenga los datos ---

    public String getNombres() {
        return txtNombres.getText().trim();
    }

    public String getDocumento() {
        return txtDocumento.getText().trim();
    }

    public String getFechaViaje() {
        return txtFechaViaje.getText().trim();
    }

    public String getOrigenSeleccionado() {
        return (String) cbOrigen.getSelectedItem();
    }

    public int getIndiceOrigen() {
        return cbOrigen.getSelectedIndex();
    }

    public String getDestinoSeleccionado() {
        return (String) cbDestino.getSelectedItem();
    }

    public int getIndiceDestino() {
        return cbDestino.getSelectedIndex();
    }

    public String getPisoSeleccionado() {
        if (rbPrimerPiso.isSelected()) return "1er Piso";
        if (rbSegundoPiso.isSelected()) return "2do Piso";
        return null;
    }

    public List<String> getServiciosSeleccionados() {
        List<String> servicios = new ArrayList<>();
        if (chkAudifonos.isSelected()) servicios.add("Audífonos");
        if (chkManta.isSelected()) servicios.add("Manta");
        if (chkRevistas.isSelected()) servicios.add("Revistas");
        return servicios;
    }

    public String getCalidadSeleccionada() {
        return lstCalidadServicio.getSelectedValue();
    }

    // --- Métodos de manipulación de la Vista desde el Controlador ---

    public void blanquearComponentes() {
        txtNombres.setText("");
        txtDocumento.setText("");
        txtFechaViaje.setText("");
        cbOrigen.setSelectedIndex(0);
        cbDestino.setSelectedIndex(0);
        bgPiso.clearSelection();
        chkAudifonos.setSelected(false);
        chkManta.setSelected(false);
        chkRevistas.setSelected(false);
        lstCalidadServicio.clearSelection();
        txtNombres.requestFocus();
    }

    public void addReiniciarListener(ActionListener listener) {
        btnReiniciar.addActionListener(listener);
    }

    public void addMostrarResumenListener(ActionListener listener) {
        btnMostrarResumen.addActionListener(listener);
    }

    public void mostrarMensajeDialogo(String mensaje, String titulo, int tipoMensaje) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipoMensaje);
    }
}