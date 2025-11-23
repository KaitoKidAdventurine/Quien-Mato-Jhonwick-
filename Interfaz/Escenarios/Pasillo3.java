package Interfaz.Escenarios;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Pasillo3 extends JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Pasillo3.class.getName());
    private Dimension tamPant;
    private int dialogoActual;
    private java.util.Timer timer;
    private TimerTask tarea;

    /**
     * Creates new form Entrada
     */
    public Pasillo3() {
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        initComponents();
        timer = new Timer();
        tarea = new TimerTask() {
            @Override
            public void run() {
                dispose();
            }
        };
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {
        cajaTexto  = new JPanel();
        flechaPasillo1 = new JButton();
        flechaOficinaEcono = new JButton();
        flechaPasilloAlmacen = new JButton();
        try {
            BufferedImage imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/pasillo 3.jpg"));

            jLabel1 = new JLabel();
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

            BufferedImage imagen2 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha abajo.png"));
            ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
            flechaPasillo1.setIcon(icono2);

            BufferedImage imagen3 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha izquierda.png"));
            ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
            flechaOficinaEcono.setIcon(icono3);

            BufferedImage imagen4 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha izquierda.png"));
            ImageIcon icono4 = new ImageIcon(imagen4.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
            flechaPasilloAlmacen.setIcon(icono4);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        flechaPasillo1.setBounds((int) (tamPant.width*0.65), (int) (tamPant.height*0.84), (int) (tamPant.width*0.048), (int) (tamPant.height*0.124));
        flechaPasillo1.setBackground(Color.red);
        flechaPasillo1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaPasillo1ActionPerformed(evt);
            }

        });
        flechaPasillo1.setOpaque(true);
        flechaPasillo1.setContentAreaFilled(false);
        flechaPasillo1.setBorderPainted(false);
        flechaPasillo1.setFocusPainted(false);
        getContentPane().add(flechaPasillo1);

        flechaOficinaEcono.setBounds((int) (tamPant.width*0.34), (int) (tamPant.height*0.7), (int) (tamPant.width*0.08), (int) (tamPant.height*0.07));
        flechaOficinaEcono.setBackground(Color.red);
        flechaOficinaEcono.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaOficinaEconoActionPerformed(evt);
            }

        });
        flechaOficinaEcono.setOpaque(true);
        flechaOficinaEcono.setContentAreaFilled(false);
        flechaOficinaEcono.setBorderPainted(false);
        flechaOficinaEcono.setFocusPainted(false);
        getContentPane().add(flechaOficinaEcono);


        flechaPasilloAlmacen.setBounds((int) (tamPant.width*0.6), (int) (tamPant.height*0.6), (int) (tamPant.width*0.08), (int) (tamPant.height*0.07));
        flechaPasilloAlmacen.setBackground(Color.red);
        flechaPasilloAlmacen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaPasilloAlmacenActionPerformed(evt);
            }

        });
        flechaPasilloAlmacen.setOpaque(true);
        flechaPasilloAlmacen.setContentAreaFilled(false);
        flechaPasilloAlmacen.setBorderPainted(false);
        flechaPasilloAlmacen.setFocusPainted(false);

        getContentPane().add(flechaPasilloAlmacen);
        getContentPane().add(jLabel1);
        pack();
    }
    public void ponerDialogo() {
    }

    private void cTMouseClicked(MouseEvent evt) {
        ponerDialogo();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    private void flechaPasillo1ActionPerformed(ActionEvent evt) {
        Pasillo1 pasillo1 = new Pasillo1();
        pasillo1.setVisible(true);
        timer.schedule(tarea, 1000);

    }
    private void flechaOficinaEconoActionPerformed(ActionEvent evt) {
        OficinaEconomico oficinaEconomico = new OficinaEconomico();
        oficinaEconomico.setVisible(true);
        timer.schedule(tarea, 1000);

    }

    private void flechaPasilloAlmacenActionPerformed(ActionEvent evt) {
        PasilloAlmacen pasilloAlmacen = new PasilloAlmacen();
        pasilloAlmacen.setVisible(true);
        timer.schedule(tarea, 1000);

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
        java.awt.EventQueue.invokeLater(() -> new Pasillo3().setVisible(true));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel cajaTexto;
    private javax.swing.JButton flechaPasillo1;
    private javax.swing.JButton flechaOficinaEcono;
    private javax.swing.JButton flechaPasilloAlmacen;
    // End of variables declaration//GEN-END:variables
}