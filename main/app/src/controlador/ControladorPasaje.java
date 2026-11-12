package controlador;

import modelo.DatosPasajeInvalidosException;
import modelo.Pasaje;
import modelo.PersistenciaArchivoException;
import modelo.RepositorioArchivo;
import Vista.VistaCompraPasaje;

import javax.swing.JOptionPane;

public class ControladorPasaje {
    private final VistaCompraPasaje vista;
    private final RepositorioArchivo<Pasaje> repositorio;

    public ControladorPasaje(VistaCompraPasaje vista, RepositorioArchivo<Pasaje> repositorio) {
        this.vista = vista;
        this.repositorio = repositorio;
        inicializarEventos();
    }

    private void inicializarEventos() {
        this.vista.addReiniciarListener(e -> vista.blanquearComponentes());
        this.vista.addMostrarResumenListener(e -> procesarCompraPasaje());
    }

    private void procesarCompraPasaje() {
        try {
            // 1. Intenta instanciar el modelo (si algo falla, lanza DatosPasajeInvalidosException)
            Pasaje nuevoPasaje = new Pasaje(
                    vista.getNombres(),
                    vista.getDocumento(),
                    vista.getFechaViaje(),
                    vista.getOrigenSeleccionado(),
                    vista.getDestinoSeleccionado(),
                    vista.getPisoSeleccionado(),
                    vista.getServiciosSeleccionados(),
                    vista.getCalidadSeleccionada()
            );

            // 2. Intenta persistir en el archivo mediante el repositorio genérico
            repositorio.guardar(nuevoPasaje);

            // 3. Muestra el resumen en el cuadro de diálogo si todo salió bien
            String mensajeDialogo = nuevoPasaje.toResumen()
                    + "\n[Guardado exitosamente en '" + repositorio.getRutaArchivo() + "']\n"
                    + "Total de pasajes registrados en sesión: " 
                    + repositorio.getRegistrosEnMemoria().size();

            vista.mostrarMensajeDialogo(
                    mensajeDialogo,
                    "Resumen de Compra de Pasaje",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (DatosPasajeInvalidosException ex) {
            // Captura errores de validación de datos del formulario
            vista.mostrarMensajeDialogo(
                    ex.getMessage(),
                    "Datos de Pasaje Inválidos",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (Exception ex) {
            // Captura preventiva ante cualquier error inesperado en tiempo de ejecución
            vista.mostrarMensajeDialogo(
                    "Ocurrió un error inesperado: " + ex.getMessage(),
                    "Error del Sistema",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}