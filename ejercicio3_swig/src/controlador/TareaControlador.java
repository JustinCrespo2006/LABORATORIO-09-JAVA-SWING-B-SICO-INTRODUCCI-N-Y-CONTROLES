package controlador;

import modelo.TareaException;
import modelo.TareaModelo;
import vista.TareaVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TareaControlador {
    private final TareaModelo modelo;
    private final TareaVista vista;

    public TareaControlador(TareaModelo modelo, TareaVista vista) {
        this.modelo = modelo;
        this.vista = vista;

        this.vista.addAgregarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarTarea();
            }
        });

        this.vista.addCompletarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                completarTarea();
            }
        });
    }

    private void agregarTarea() {
        try {
            String texto = vista.getTextoTarea();
            modelo.agregarTarea(texto);
            vista.limpiarCampoTexto();
            vista.actualizarLista(modelo.obtenerTareas());
        } catch (TareaException ex) {
            vista.mostrarError(ex.getMessage());
        }
    }

    private void completarTarea() {
        try {
            int indice = vista.getIndiceSeleccionado();
            modelo.agregarTarea(vista.getTextoTarea()); // Ojo: solo usamos completarTarea abajo
        } catch (Exception ignored) {}
        
        try {
            int indice = vista.getIndiceSeleccionado();
            modelo.completarTarea(indice);
            vista.actualizarLista(modelo.obtenerTareas());
        } catch (TareaException ex) {
            vista.mostrarError(ex.getMessage());
        }
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}