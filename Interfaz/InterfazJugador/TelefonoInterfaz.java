package Interfaz.InterfazJugador;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class TelefonoInterfaz  extends javax.swing.JDialog {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelefonoInterfaz.class.getName());
    private Dimension tamPant;

    private Timer timer;
    private TimerTask tarea;

    /**
     * Creates new form MenuInterno
     */
    public TelefonoInterfaz(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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


    private void initComponents() {

        pantalla = new JPanel();
        llamar = new JButton();
        ajustes = new JButton();
        canciones = new JButton();
        salir = new JButton();
        telefono = new JLabel();
        fondo = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);
        getContentPane().setPreferredSize(new Dimension((int) (tamPant.width*0.4), (int) (tamPant.height*0.6)));
        setBackground(new Color(0,0, 0, 0));

        pantalla.setBackground(Color.white );
        pantalla.setLayout(null);
        pantalla.setBounds((int) (tamPant.width*0.16), (int) (tamPant.height*0.03), (int) (tamPant.width*0.27), (int) (tamPant.height*0.73));
        pantalla.setOpaque(false);
        llamar.setBorderPainted(false);
        llamar.setContentAreaFilled(false);
        llamar.setFocusPainted(false);
        llamar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        BufferedImage imagen1 = null;

        try {
            imagen1 = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Llamada.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono1 = new ImageIcon(imagen1.getScaledInstance((int) (tamPant.width*0.13), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
        llamar.setIcon(icono1);

        llamar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        llamar.setBounds((int) (tamPant.width*0.07), (int) (tamPant.height*0.1),(int) (tamPant.width*0.13), (int) (tamPant.height*0.13));
        pantalla.add(llamar);


        ajustes.setBorderPainted(false);
        ajustes.setContentAreaFilled(false);
        ajustes.setFocusPainted(false);
        ajustes.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });
        ajustes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        BufferedImage imagen2 = null;

        try {
            imagen2 = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Ajustes.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.13), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
        ajustes.setIcon(icono2);
        ajustes.setBounds((int) (tamPant.width*0.07), (int) (tamPant.height*0.5),(int) (tamPant.width*0.13), (int) (tamPant.height*0.13));
        pantalla.add(ajustes);


        canciones.setBorderPainted(false);
        canciones.setContentAreaFilled(false);
        canciones.setFocusPainted(false);
        canciones.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                jButton3MouseExited(evt);
            }
        });
        canciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        BufferedImage imagen3 = null;

        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Reproductor.png"));} catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.13), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
        canciones.setIcon(icono3);
        canciones.setBounds((int) (tamPant.width*0.07), (int) (tamPant.height*0.3),(int) (tamPant.width*0.13), (int) (tamPant.height*0.13));
        pantalla.add(canciones);


        salir.setBorderPainted(false);
        salir.setContentAreaFilled(false);
        salir.setFocusPainted(false);
       salir.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                jButton4MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                jButton4MouseExited(evt);
            }
        });
        salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        salir.setBounds((int) (tamPant.width*0.05), (int) (tamPant.height*0.44),(int) (tamPant.width*0.3), (int) (tamPant.height*0.065));
        pantalla.add(salir);




        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Mano Telefono.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.6), (int) (tamPant.height*0.9), Image.SCALE_SMOOTH));
        telefono.setIcon(icono);
        telefono.setBounds((int) (tamPant.width*0.08), 0, (int) (tamPant.width*0.6), (int) (tamPant.height*0.9));

        BufferedImage imagen5 = null;

        try {
            imagen5 = ImageIO.read(new File("DatosAuxiliares/Telefono/Fondo Simple.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono5 = new ImageIcon(imagen5.getScaledInstance((int) (tamPant.width*0.255), (int) (tamPant.height*0.72), Image.SCALE_SMOOTH));
        fondo.setIcon(icono5);
        fondo.setBounds((int) (tamPant.width*0.005),0, (int) (tamPant.width*0.255), (int) (tamPant.height*0.72));
        pantalla.add(fondo);
        add(pantalla, 0);
        add(telefono);

        pack();
    }// </editor-fold>

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

        TelefonoLlamar llamada = new TelefonoLlamar();
        llamada.setBounds((int) (tamPant.width*0.16), (int) (tamPant.height*0.03), (int) (tamPant.width*0.27), (int) (tamPant.height*0.73));add(llamada, 1);
        pantalla.setVisible(false);
        llamada.setVisible(true);
        revalidate();
        repaint();
    }

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {
        BufferedImage imagen1 = null;

        try {
            imagen1 = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/LLamada BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono1 = new ImageIcon(imagen1.getScaledInstance((int) (tamPant.width*0.13), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
        llamar.setIcon(icono1);

    }

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {
        BufferedImage imagen1 = null;

        try {
            imagen1 = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Llamada.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono1 = new ImageIcon(imagen1.getScaledInstance((int) (tamPant.width*0.13), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
        llamar.setIcon(icono1);

    }


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        TelefonoAjustes ajustesT = new TelefonoAjustes();
        ajustesT.setBounds((int) (tamPant.width*0.16), (int) (tamPant.height*0.03), (int) (tamPant.width*0.27), (int) (tamPant.height*0.73));
        add(ajustesT, 1);
        pantalla.setVisible(false);
        ajustesT.setVisible(true);
        revalidate();
        repaint();
    }

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {
        BufferedImage imagen2 = null;

        try {
            imagen2 = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Ajustes BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.13), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
        ajustes.setIcon(icono2);
    }

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {
        BufferedImage imagen2 = null;

        try {
            imagen2 = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Ajustes.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.13), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
        ajustes.setIcon(icono2);
    }


    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        TelefonoReproductor reproductorT = new TelefonoReproductor();
        reproductorT.setBounds((int) (tamPant.width*0.16), (int) (tamPant.height*0.03), (int) (tamPant.width*0.27), (int) (tamPant.height*0.73));
        add(reproductorT, 1);
        pantalla.setVisible(false);
        reproductorT.setVisible(true);
        revalidate();
        repaint();
    }

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {
        BufferedImage imagen3 = null;

        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Reproductor BR.png"));} catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.13), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
        canciones.setIcon(icono3);
    }

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {
        BufferedImage imagen3 = null;

        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Reproductor.png"));} catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.13), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
        canciones.setIcon(icono3);
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void jButton4MouseEntered(java.awt.event.MouseEvent evt) {

    }

    private void jButton4MouseExited(java.awt.event.MouseEvent evt) {

    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
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
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MenuInterno dialog = new MenuInterno(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton llamar;
    private javax.swing.JButton ajustes;
    private javax.swing.JButton canciones;
    private javax.swing.JButton salir;
    private javax.swing.JPanel pantalla;
    private javax.swing.JLabel telefono;
    private javax.swing.JLabel fondo;
    // End of variables declaration
}
