package Interfaz.Escenarios;

import DatosAuxiliaresLogica.EfectosEspeciales;
import DatosAuxiliaresLogica.UnionInterfaces;
import Interfaz.InterfazJugador.InterfazUsuario;
import Interfaz.Menu.MenuPrincipal;
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

public class Sala extends ModeloEscenario {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Sala.class.getName());
    private Dimension tamPant;
    private int dialogoActual;
    private Timer timer;
    private TimerTask tarea;
    private InterfazUsuario interfazUsuario;
    private Timer timer2;
    private TimerTask tarea2;
    /**
     * Creates new form Entrada
     */
    public Sala() {
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();

        timer2 = new Timer();
        tarea2 = new TimerTask() {
            @Override
            public void run() {

                if(UnionInterfaces.getInstance().getCerrarVentana()){
                    MenuPrincipal menu = new MenuPrincipal();
                    menu.setVisible(true);
                    UnionInterfaces.getInstance().setCerrarVentana(false);
                    cerrarEscenario();
                    tarea2.cancel();
                }
            }
        };

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
        interfazUsuario= new InterfazUsuario();
        try {
            BufferedImage imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/sala.jpg"));


            // Actualizacion de donde esta el Jugador
            Partida p = Partida.getInstance();
            p.buscarEscenarioNombre("Sala Planta Alta");


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
        flechaEntradaDentro.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaEntradaMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaEntradaMouseExited(evt);
            }
        });
        flechaEntradaDentro.setToolTipText("Recepcion");
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
        flechaPasillo2.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaPasillo2MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaPasillo2MouseExited(evt);
            }
        });
        flechaPasillo2.setToolTipText("Ala norte");
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
        flechaSala2.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaSala2MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaSala2MouseExited(evt);
            }
        });
        flechaSala2.setToolTipText("Sala de Exibicion");
        flechaSala2.setOpaque(true);
        flechaSala2.setContentAreaFilled(false);
        flechaSala2.setBorderPainted(false);
        flechaSala2.setFocusPainted(false);

        getContentPane().add(flechaSala2);


        getContentPane().add(cajaTexto);

        lugar.setText("Sala Planta Alta");
        lugar.setOpaque(false);
        lugar.setForeground(Color.white);
        lugar.setFont(new Font("Segoe UI", 0, (int) (tamPant.width*0.035)));
        lugar.setBounds((int) (tamPant.width*0.03), (int) (tamPant.height*0.06), (int) (tamPant.width*0.35), (int) (tamPant.height*0.1));
        getContentPane().add(lugar);

        interfazUsuario.setBounds((int) (tamPant.width*0.55), (int) (tamPant.height*0.05), (int) (tamPant.width*0.45), (int) (tamPant.height*0.15));
        getContentPane().add(interfazUsuario);


        getContentPane().add(jLabel1);
        pack();
        timer2.scheduleAtFixedRate(tarea2, 0, 20);
    }
    public void ponerDialogo() {
    }

    private void cTMouseClicked(MouseEvent evt) {
        ponerDialogo();
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    private void flechaEntradaActionPerformed(ActionEvent evt) {
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDePasos();

        Recepcion entradaD = new Recepcion();
        entradaD.setVisible(true);
        tarea2.cancel();
        timer.schedule(tarea, 1000);
    }
    private void flechaPasillo2ActionPerformed(ActionEvent evt) {
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDePasos();

        Pasillo2 p2 = new Pasillo2();
        p2.setVisible(true);
        tarea2.cancel();
        timer.schedule(tarea, 1000);
    }
    private void flechaSala2ActionPerformed(ActionEvent evt) {
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDePasos();

        Sala2 sala2 = new Sala2();
        sala2.setVisible(true);
        tarea2.cancel();
        timer.schedule(tarea, 1000);
    }
    private void flechaEntradaMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha abajo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaEntradaDentro.setIcon(icono);
    }

    private void flechaEntradaMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha abajo BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaEntradaDentro.setIcon(icono);
    }
    private void flechaSala2MouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha derecha.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaSala2.setIcon(icono);
    }

    private void flechaSala2MouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha derecha BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaSala2.setIcon(icono);
    }
    private void flechaPasillo2MouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha izquierda BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaPasillo2.setIcon(icono);
    }

    private void flechaPasillo2MouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha izquierda.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaPasillo2.setIcon(icono);
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