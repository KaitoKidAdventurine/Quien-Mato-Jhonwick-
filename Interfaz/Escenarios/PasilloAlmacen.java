package Interfaz.Escenarios;

import DatosAuxiliaresLogica.EfectosEspeciales;
import DatosAuxiliaresLogica.UnionInterfaces;
import Interfaz.InterfazJugador.InterfazUsuario;
import Interfaz.Menu.MenuPrincipal;
import Logica.Juego;
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

public class PasilloAlmacen extends ModeloEscenario {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PasilloAlmacen.class.getName());
    private Dimension tamPant;
    private int dialogoActual;
    private java.util.Timer timer;
    private TimerTask tarea;
    private InterfazUsuario interfazUsuario;
    private Timer timer2;
    private TimerTask tarea2;
    /**
     * Creates new form Entrada
     */
    public PasilloAlmacen() {
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
                }else{
                    revalidate();
                    repaint();
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
        flechaPasillo3 = new JButton();
        flechaAlmacen = new JButton();
        flechaCallejon = new JButton();
        lugar = new JLabel();
        interfazUsuario= new InterfazUsuario();
        try {
            BufferedImage imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/pasillo almacen - callejon.jpg"));

            // Actualizacion de donde esta el Jugador
            Juego.getInstance().getPartidaActual().buscarEscenarioNombre("Ala Sur");


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

            cajaTexto.setBackground(new Color(0, 0, 0, 0));
            cajaTexto.setBounds(0, 0,  tamPant.width, tamPant.height);
            cajaTexto.setLayout(null);
            BufferedImage imagen2 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha arriba.png"));
            ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
            flechaCallejon.setIcon(icono2);


            BufferedImage imagen3 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha izquierda.png"));
            ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
            flechaAlmacen.setIcon(icono3);


            BufferedImage imagen4 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha derecha.png"));
            ImageIcon icono4 = new ImageIcon(imagen4.getScaledInstance((int) (int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
            flechaPasillo3.setIcon(icono4);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        flechaPasillo3.setBounds((int) (tamPant.width*0.75), (int) (tamPant.height*0.85), (int) (tamPant.width*0.08), (int) (tamPant.height*0.07));
        flechaPasillo3.setBackground(Color.red);
        flechaPasillo3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaPasillo3ActionPerformed(evt);
            }
        });
        flechaPasillo3.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaPasillo3MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaPasillo3MouseExited(evt);
            }
        });
        flechaPasillo3.setToolTipText("Oficinas Planta Baja");
        flechaPasillo3.setOpaque(true);
        flechaPasillo3.setContentAreaFilled(false);
        flechaPasillo3.setBorderPainted(false);
        flechaPasillo3.setFocusPainted(false);


        getContentPane().add(flechaPasillo3);

        flechaAlmacen.setBounds((int) (tamPant.width*0.19), (int) (tamPant.height*0.8), (int) (tamPant.width*0.08), (int) (tamPant.height*0.07));
        flechaAlmacen.setBackground(Color.red);
        flechaAlmacen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaAlmacenActionPerformed(evt);
            }
        });
        flechaAlmacen.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaAlmacenMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaAlmacenMouseExited(evt);
            }
        });
        flechaAlmacen.setToolTipText("Almacen");
        flechaAlmacen.setOpaque(true);
        flechaAlmacen.setContentAreaFilled(false);
        flechaAlmacen.setBorderPainted(false);
        flechaAlmacen.setFocusPainted(false);

        getContentPane().add(flechaAlmacen);

        flechaCallejon.setBounds((int) (tamPant.width*0.6), (int) (tamPant.height*0.6), (int) (tamPant.width*0.048), (int) (tamPant.height*0.124));
        flechaCallejon.setBackground(Color.red);
        flechaCallejon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaCallejonActionPerformed(evt);
            }
        });
        flechaCallejon.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaCallejonMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaCallejonMouseExited(evt);
            }
        });
        flechaCallejon.setToolTipText("Callejon");
        flechaCallejon.setOpaque(true);
        flechaCallejon.setContentAreaFilled(false);
        flechaCallejon.setBorderPainted(false);
        flechaCallejon.setFocusPainted(false);



        getContentPane().add(flechaCallejon);


        getContentPane().add(cajaTexto);

        lugar.setText("Ala Sur");
        lugar.setOpaque(false);
        lugar.setForeground(Color.white);
        lugar.setFont(new Font("Segoe UI", 0, (int) (tamPant.width*0.035)));
        lugar.setBounds((int) (tamPant.width*0.03), (int) (tamPant.height*0.06), (int) (tamPant.width*0.35), (int) (tamPant.height*0.1));
        getContentPane().add(lugar);


        interfazUsuario.setBounds((int) (tamPant.width*0.55), (int) (tamPant.height*0.05), (int) (tamPant.width*0.45), (int) (tamPant.height*0.15));
        getContentPane().add(interfazUsuario);

        getContentPane().add(jLabel1);
        pack();
        timer2.scheduleAtFixedRate(tarea2, 0, 10);
    }
    public void ponerDialogo() {
    }

    private void cTMouseClicked(MouseEvent evt) {
        ponerDialogo();
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    private void flechaPasillo3ActionPerformed(ActionEvent evt) {
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDePasos();

        Pasillo3 pasillo3 = new Pasillo3();
        pasillo3.setVisible(true);
        tarea2.cancel();
        timer.schedule(tarea, 1000);
    }
    private void flechaAlmacenActionPerformed(ActionEvent evt) {
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDePasos();

        Almacen almacen= new Almacen();
        almacen.setVisible(true);
        tarea2.cancel();
        timer.schedule(tarea, 1000);
    }
    private void flechaCallejonActionPerformed(ActionEvent evt) {
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDePasos();

        Callejon callejon = new Callejon();
        callejon.setVisible(true);
        tarea2.cancel();
        timer.schedule(tarea, 1000);
    }
    private void flechaPasillo3MouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha derecha.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaPasillo3.setIcon(icono);
    }

    private void flechaPasillo3MouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha derecha BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaPasillo3.setIcon(icono);
    }
    private void flechaCallejonMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha arriba.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaCallejon.setIcon(icono);
    }

    private void flechaCallejonMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha arriba BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaCallejon.setIcon(icono);
    }


    private void flechaAlmacenMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha izquierda.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaAlmacen.setIcon(icono);
    }
    private void flechaAlmacenMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha izquierda BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaAlmacen.setIcon(icono);
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
        java.awt.EventQueue.invokeLater(() -> new PasilloAlmacen().setVisible(true));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel cajaTexto;
    private javax.swing.JButton flechaPasillo3;
    private javax.swing.JButton flechaAlmacen;
    private javax.swing.JButton flechaCallejon;
    private javax.swing.JLabel lugar;
    // End of variables declaration//GEN-END:variables
}