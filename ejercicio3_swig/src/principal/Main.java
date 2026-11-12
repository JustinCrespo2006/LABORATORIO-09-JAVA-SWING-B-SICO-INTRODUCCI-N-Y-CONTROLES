package principal;

import controlador.TareaControlador;
import modelo.TareaModelo;
import vista.TareaVista;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TareaModelo modelo = new TareaModelo();
            TareaVista vista = new TareaVista();
            TareaControlador controlador = new TareaControlador(modelo, vista);
            
            controlador.iniciar();
        });
    }
}