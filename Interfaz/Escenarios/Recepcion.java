package Interfaz.Escenarios;

import Interfaz.InterfazJugador.InterfazUsuario;
import Logica.Partida;

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

public class Recepcion extends JFrame{
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Recepcion.class.getName());
    private Dimension tamPant;
    private Timer timer;
    private TimerTask tarea;
    private InterfazUsuario interfazUsuario;
    /**
    /**
     * Creates new form Entrada
     */
    public Recepcion() {
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
        fondo = new JLabel();
        flechaSalida = new JButton();
        flechaBano = new JButton();
        flechaSala1 = new JButton();
        flechaPasillo1 = new JButton();
        lugar = new JLabel();
        interfazUsuario= new InterfazUsuario();
        try {
            BufferedImage imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/entrada por dentro.jpg"));


            // Actualizacion de donde esta el Jugador
            Partida p = Partida.getInstance();
            p.buscarEscenarioNombre("Recepcion");


            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setMinimumSize(tamPant);
            setUndecorated(true);
            setPreferredSize(tamPant);
            getContentPane().setLayout(null);
            ImageIcon icono = new ImageIcon(imagen.getScaledInstance(tamPant.width, tamPant.height, Image.SCALE_SMOOTH));
            fondo.setIcon(icono); // NOI18N
            fondo.setFocusable(false);
            fondo.setMaximumSize(tamPant);
            fondo.setMinimumSize(tamPant);
            fondo.setPreferredSize(tamPant);
            fondo.setBounds(0, 0, tamPant.width, tamPant.height);

            cajaTexto.setOpaque(false);
            cajaTexto.setBounds(220, 280, 1200, 800);
            cajaTexto.setLayout(null);

            BufferedImage imagen2 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha arriba.png"));
            ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
            flechaSalida.setIcon(icono2);


            BufferedImage imagen3 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha derecha.png"));
            ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
            flechaBano.setIcon(icono3);


            BufferedImage imagen4 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha izquierda.png"));
            ImageIcon icono4 = new ImageIcon(imagen4.getScaledInstance((int) (int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
            flechaSala1.setIcon(icono4);


            BufferedImage imagen5 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha abajo.png"));
            ImageIcon icono5 = new ImageIcon(imagen5.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
            flechaPasillo1.setIcon(icono5);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        flechaSalida.setBounds((int) (tamPant.width*0.48), (int) (tamPant.height*0.58), (int) (tamPant.width*0.048), (int) (tamPant.height*0.124));
        flechaSalida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaSalidaActionPerformed(evt);
            }
        });
        flechaSalida.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaSalidaMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaSalidaMouseExited(evt);
            }
        });
        flechaSalida.setOpaque(true);
        flechaSalida.setContentAreaFilled(false);
        flechaSalida.setBorderPainted(false);
        flechaSalida.setFocusPainted(false);

        flechaBano.setBounds((int) (tamPant.width*0.88), (int) (tamPant.height*0.8), (int) (tamPant.width*0.08), (int) (tamPant.height*0.07));
        flechaBano.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaBanoActionPerformed(evt);
            }
        });
        flechaBano.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaBanoMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaBanoMouseExited(evt);
            }
        });

        flechaBano.setOpaque(true);
        flechaBano.setContentAreaFilled(false);
        flechaBano.setBorderPainted(false);
        flechaBano.setFocusPainted(false);


        flechaSala1.setBounds((int) (tamPant.width*0.06), (int) (tamPant.height*0.64), (int) (tamPant.width*0.1), (int) (tamPant.height*0.08));
        flechaSala1.setBackground(Color.red);
        flechaSala1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaSala1ActionPerformed(evt);
            }
        });
        flechaSala1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaSala1MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaSala1MouseExited(evt);
            }
        });
        flechaSala1.setOpaque(true);
        flechaSala1.setContentAreaFilled(false);
        flechaSala1.setBorderPainted(false);
        flechaSala1.setFocusPainted(false);


        flechaPasillo1.setBounds((int) (tamPant.width*0.34), (int) (tamPant.height*0.84), (int) (tamPant.width*0.05), (int) (tamPant.height*0.13));
        flechaPasillo1.setBackground(Color.red);
        flechaPasillo1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaPasillo1ActionPerformed(evt);
            }

        });
        flechaPasillo1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaPasillo1MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaPasillo1MouseExited(evt);
            }
        });
        flechaPasillo1.setOpaque(true);
        flechaPasillo1.setContentAreaFilled(false);
        flechaPasillo1.setBorderPainted(false);
        flechaPasillo1.setFocusPainted(false);


        getContentPane().add(cajaTexto);
        getContentPane().add(flechaSalida);
        getContentPane().add(flechaBano);
        getContentPane().add(flechaSala1);
        getContentPane().add(flechaPasillo1);

        lugar.setText("Recepcion");
        lugar.setOpaque(false);
        lugar.setForeground(Color.white);
        lugar.setFont(new Font("Segoe UI", 0, (int) (tamPant.width*0.035)));
        lugar.setBounds((int) (tamPant.width*0.03), (int) (tamPant.height*0.06), (int) (tamPant.width*0.3), (int) (tamPant.height*0.1));
        getContentPane().add(lugar);

        interfazUsuario.setBounds((int) (tamPant.width*0.55), (int) (tamPant.height*0.05), (int) (tamPant.width*0.45), (int) (tamPant.height*0.15));
        getContentPane().add(interfazUsuario);

        getContentPane().add(fondo);
        pack();
    }
    public void ponerDialogo() {
    }

    private void cTMouseClicked(MouseEvent evt) {
        ponerDialogo();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    private void flechaSalidaActionPerformed(ActionEvent evt) {
        Entrada entrada = new Entrada();
        entrada.setVisible(true);
        timer.schedule(tarea, 1000);
    }
    private void flechaBanoActionPerformed(ActionEvent evt) {
        Bano bano = new Bano();
        bano.setVisible(true);
        timer.schedule(tarea, 1000);
    }
    private void flechaSala1ActionPerformed(ActionEvent evt) {
        Sala sala1 = new Sala();
        sala1.setVisible(true);
        timer.schedule(tarea, 1000);
    }
    private void flechaPasillo1ActionPerformed(ActionEvent evt) {

        Pasillo1 pasillo1 = new Pasillo1();
        pasillo1.setVisible(true);
        timer.schedule(tarea, 1000);


    }
    private void flechaSalidaMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha arriba.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaSalida.setIcon(icono);
    }

    private void flechaSalidaMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha arriba BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaSalida.setIcon(icono);
    }
    private void flechaSala1MouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha izquierda.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int)  (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaSala1.setIcon(icono);
    }

    private void flechaSala1MouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha izquierda BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int)  (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaSala1.setIcon(icono);
    }


    private void flechaBanoMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha derecha.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaBano.setIcon(icono);
    }
    private void flechaBanoMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha derecha BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaBano.setIcon(icono);
    }
    private void flechaPasillo1MouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha abajo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaPasillo1.setIcon(icono);
    }
    private void flechaPasillo1MouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha abajo BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaPasillo1.setIcon(icono);
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
        java.awt.EventQueue.invokeLater(() -> new Recepcion().setVisible(true));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fondo;
    private javax.swing.JPanel cajaTexto;
    private javax.swing.JButton flechaSalida;
    private javax.swing.JButton flechaBano;
    private javax.swing.JButton flechaSala1;
    private javax.swing.JButton flechaPasillo1;
    private javax.swing.JLabel lugar;// End of variables declaration//GEN-END:variables
}
