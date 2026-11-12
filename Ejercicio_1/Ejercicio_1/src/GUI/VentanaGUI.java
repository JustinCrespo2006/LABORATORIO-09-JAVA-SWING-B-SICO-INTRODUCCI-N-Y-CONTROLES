package GUI;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VentanaGUI extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaGUI frame = new VentanaGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                  e.printStackTrace();
                }
            }
        });
    }

    public VentanaGUI() {
        //Esta es la configuracion estandar de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 250);
        setTitle("Ejemplo Drag & Drop");
        
        //Esto ara que funcione el panel Base
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        // Importante para los ejees X y Y (Drag & Drop)
        contentPane.setLayout(null); 
    }
}