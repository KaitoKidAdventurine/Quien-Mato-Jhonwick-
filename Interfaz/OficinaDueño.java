package Interfaz;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OficinaDueño extends JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(OficinaDueño.class.getName());
    private Dimension tamPant;
    private int dialogoActual;

    /**
     * Creates new form Entrada
     */
    public OficinaDueño() {
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        initComponents();

    }


    @SuppressWarnings("unchecked")
    private void initComponents() {
        cajaTexto  = new JPanel();
        try {
            BufferedImage imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/oficina del dueño.jpg"));

            jLabel1 = new JLabel();
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            setMinimumSize(tamPant);
            setUndecorated(true);
            setPreferredSize(tamPant);
            getContentPane().setLayout(null);
            ImageIcon icono = new ImageIcon(imagen.getScaledInstance(tamPant.width, tamPant.height, Image.SCALE_SMOOTH));
            jLabel1.setIcon(icono); // NOI18N
            jLabel1.setFocusable(false);
            jLabel1.setMaximumSize(tamPant);
            jLabel1.setMinimumSize(tamPant);
            jLabel1.setPreferredSize(tamPant);
            jLabel1.setBounds(0, 0, tamPant.width, tamPant.height);

            cajaTexto.setOpaque(false);
            cajaTexto.setBounds(220, 280, 1200, 800);
            cajaTexto.setLayout(null);
            getContentPane().add(cajaTexto);
            getContentPane().add(jLabel1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        pack();
    }
    public void ponerDialogo() {
    }

    private void cTMouseClicked(MouseEvent evt) {
        ponerDialogo();
        getContentPane().revalidate();
        getContentPane().repaint();
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new OficinaDueño().setVisible(true));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel cajaTexto;
    // End of variables declaration//GEN-END:variables
}
