package Interfaz.Escenarios;

import Interfaz.InterfazJugador.InterfazUsuario;

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

public class Pasillo2 extends JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Pasillo2.class.getName());
    private Dimension tamPant;
    private int dialogoActual;
    private java.util.Timer timer;
    private TimerTask tarea;
    private InterfazUsuario interfazUsuario;
    /**
     * Creates new form Entrada
     */
    public Pasillo2() {
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
        flechaSala = new JButton();
        flechaOficiaJefe = new JButton();
        flechaBano2 = new JButton();
        lugar = new JLabel();
        interfazUsuario= new InterfazUsuario();
        try {
            BufferedImage imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/pasillo 2.jpg"));

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
            flechaSala.setIcon(icono3);


            BufferedImage imagen4 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha izquierda.png"));
            ImageIcon icono4 = new ImageIcon(imagen4.getScaledInstance((int) (int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
            flechaOficiaJefe.setIcon(icono4);


            BufferedImage imagen5 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha derecha.png"));
            ImageIcon icono5 = new ImageIcon(imagen5.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
            flechaBano2.setIcon(icono5);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        flechaSala.setBounds((int) (tamPant.width*0.48), (int) (tamPant.height*0.84), (int) (tamPant.width*0.048), (int) (tamPant.height*0.124));
        flechaSala.setBackground(Color.red);
        flechaSala.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaSalaActionPerformed(evt);
            }
        });
        flechaSala.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaSalaMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaSalaMouseExited(evt);
            }
        });
        flechaSala.setOpaque(true);
        flechaSala.setContentAreaFilled(false);
        flechaSala.setBorderPainted(false);
        flechaSala.setFocusPainted(false);

        getContentPane().add(flechaSala);

        flechaOficiaJefe.setBounds((int) (tamPant.width*0.3), (int) (tamPant.height*0.66), (int) (tamPant.width*0.08), (int) (tamPant.height*0.07));
        flechaOficiaJefe.setBackground(Color.red);
        flechaOficiaJefe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaOficinaJefeActionPerformed(evt);
            }
        });
        flechaOficiaJefe.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaOficinaJefeMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaOfJefeMouseExited(evt);
            }
        });
        flechaOficiaJefe.setOpaque(true);
        flechaOficiaJefe.setContentAreaFilled(false);
        flechaOficiaJefe.setBorderPainted(false);
        flechaOficiaJefe.setFocusPainted(false);

        getContentPane().add(flechaOficiaJefe);

        flechaBano2.setBounds((int) (tamPant.width*0.65), (int) (tamPant.height*0.66), (int) (tamPant.width*0.08), (int) (tamPant.height*0.07));
        flechaBano2.setBackground(Color.red);
        flechaBano2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaBano2ActionPerformed(evt);
            }
        });
        flechaBano2.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaBano2MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaBano2MouseExited(evt);
            }
        });
        flechaBano2.setOpaque(true);
        flechaBano2.setContentAreaFilled(false);
        flechaBano2.setBorderPainted(false);
        flechaBano2.setFocusPainted(false);

        getContentPane().add(flechaBano2);


        getContentPane().add(cajaTexto);

        lugar.setText("Ala norte");
        lugar.setOpaque(false);
        lugar.setForeground(Color.white);
        lugar.setFont(new Font("Segoe UI", 0, (int) (tamPant.width*0.035)));
        lugar.setBounds((int) (tamPant.width*0.03), (int) (tamPant.height*0.06), (int) (tamPant.width*0.3), (int) (tamPant.height*0.1));
        getContentPane().add(lugar);


        interfazUsuario.setBounds((int) (tamPant.width*0.55), (int) (tamPant.height*0.05), (int) (tamPant.width*0.45), (int) (tamPant.height*0.15));
        getContentPane().add(interfazUsuario);

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

    private void flechaSalaActionPerformed(ActionEvent evt) {
        Sala sala = new Sala();
        sala.setVisible(true);
        timer.schedule(tarea, 1000);
    }
    private void flechaOficinaJefeActionPerformed(ActionEvent evt) {
        OficinaJefe oficinaJefe = new OficinaJefe();
        oficinaJefe.setVisible(true);
        timer.schedule(tarea, 1000);
    }
    private void flechaBano2ActionPerformed(ActionEvent evt) {
        Bano2 bano2 = new Bano2();
        bano2.setVisible(true);
        timer.schedule(tarea, 1000);
    }
    private void flechaSalaMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha abajo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaSala.setIcon(icono);
    }

    private void flechaSalaMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha abajo BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaSala.setIcon(icono);
    }
    private void flechaBano2MouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha derecha.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaBano2.setIcon(icono);
    }

    private void flechaBano2MouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha derecha BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaBano2.setIcon(icono);
    }
    private void flechaOficinaJefeMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha izquierda BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaOficiaJefe.setIcon(icono);
    }

    private void flechaOfJefeMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha izquierda.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaOficiaJefe.setIcon(icono);
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
        java.awt.EventQueue.invokeLater(() -> new Pasillo2().setVisible(true));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel cajaTexto;
    private javax.swing.JButton flechaSala;
    private javax.swing.JButton flechaOficiaJefe;
    private javax.swing.JButton flechaBano2;
    private javax.swing.JLabel lugar;
    // End of variables declaration//GEN-END:variables
}
