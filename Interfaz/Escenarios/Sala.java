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

public class Sala extends JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Sala.class.getName());
    private Dimension tamPant;
    private int dialogoActual;
    private Timer timer;
    private TimerTask tarea;

    /**
     * Creates new form Entrada
     */
    public Sala() {
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
        flechaEntradaDentro = new JButton();
        flechaPasillo2 = new JButton();
        flechaSala2 = new JButton();
        lugar = new JLabel();
        try {
            BufferedImage imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/sala.jpg"));

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


            BufferedImage imagen3 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha abajo.png"));
            ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
            flechaEntradaDentro.setIcon(icono3);


            BufferedImage imagen4 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha izquierda.png"));
            ImageIcon icono4 = new ImageIcon(imagen4.getScaledInstance((int) (int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
            flechaPasillo2.setIcon(icono4);


            BufferedImage imagen5 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha derecha.png"));
            ImageIcon icono5 = new ImageIcon(imagen5.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
            flechaSala2.setIcon(icono5);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        flechaEntradaDentro.setBounds((int) (tamPant.width*0.48), (int) (tamPant.height*0.84), (int) (tamPant.width*0.048), (int) (tamPant.height*0.124));
        flechaEntradaDentro.setBackground(Color.red);
        flechaEntradaDentro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaEntradaActionPerformed(evt);
            }
        });
        flechaEntradaDentro.setOpaque(true);
        flechaEntradaDentro.setContentAreaFilled(false);
        flechaEntradaDentro.setBorderPainted(false);
        flechaEntradaDentro.setFocusPainted(false);



        getContentPane().add(flechaEntradaDentro);

        flechaPasillo2.setBounds((int) (tamPant.width*0.19), (int) (tamPant.height*0.67), (int) (tamPant.width*0.08), (int) (tamPant.height*0.07));
        flechaPasillo2.setBackground(Color.red);
        flechaPasillo2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaPasillo2ActionPerformed(evt);
            }
        });
        flechaPasillo2.setOpaque(true);
        flechaPasillo2.setContentAreaFilled(false);
        flechaPasillo2.setBorderPainted(false);
        flechaPasillo2.setFocusPainted(false);



        getContentPane().add(flechaPasillo2);

        flechaSala2.setBounds((int) (tamPant.width*0.7), (int) (tamPant.height*0.67), (int) (tamPant.width*0.08), (int) (tamPant.height*0.07));
        flechaSala2.setBackground(Color.red);
        flechaSala2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaSala2ActionPerformed(evt);
            }
        });
        flechaSala2.setOpaque(true);
        flechaSala2.setContentAreaFilled(false);
        flechaSala2.setBorderPainted(false);
        flechaSala2.setFocusPainted(false);

        getContentPane().add(flechaSala2);


        getContentPane().add(cajaTexto);

        lugar.setText("Sala Planta Alta");
        lugar.setOpaque(false);
        lugar.setForeground(Color.white);
        lugar.setFont(new java.awt.Font("Segoe UI", 0, (int) (tamPant.width*0.035)));
        lugar.setBounds((int) (tamPant.width*0.03), (int) (tamPant.height*0.06), (int) (tamPant.width*0.35), (int) (tamPant.height*0.1));
        getContentPane().add(lugar);


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

    private void flechaEntradaActionPerformed(ActionEvent evt) {
        Recepcion entradaD = new Recepcion();
        entradaD.setVisible(true);
        timer.schedule(tarea, 1000);
    }
    private void flechaPasillo2ActionPerformed(ActionEvent evt) {
        Pasillo2 p2 = new Pasillo2();
        p2.setVisible(true);
        timer.schedule(tarea, 1000);
    }
    private void flechaSala2ActionPerformed(ActionEvent evt) {
        Sala2 sala2 = new Sala2();
        sala2.setVisible(true);
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
        java.awt.EventQueue.invokeLater(() -> new Sala().setVisible(true));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel cajaTexto;
    private javax.swing.JButton flechaEntradaDentro;
    private javax.swing.JButton flechaPasillo2;
    private javax.swing.JButton flechaSala2;
    private javax.swing.JLabel lugar;
    // End of variables declaration//GEN-END:variables
}