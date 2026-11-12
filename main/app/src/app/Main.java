package app;

import controlador.ControladorPasaje;
import modelo.Pasaje;
import modelo.RepositorioArchivo;
import Vista.VistaCompraPasaje;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        // Ejecutar la GUI en el Event Dispatch Thread (buena práctica en Swing)
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            // 1. Instanciar el modelo genérico apuntando al archivo de texto
            RepositorioArchivo<Pasaje> repositorio = new RepositorioArchivo<>("registro_pasajes.txt");

            // 2. Instanciar la vista
            VistaCompraPasaje vista = new VistaCompraPasaje();

            // 3. Instanciar el controlador conectando vista y modelo
            new ControladorPasaje(vista, repositorio);

            // 4. Mostrar la ventana
            vista.setVisible(true);
        });
    }
}