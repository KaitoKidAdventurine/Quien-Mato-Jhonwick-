package Interfaz.Escenarios;

import DatosAuxiliaresLogica.EfectosEspeciales;
import DatosAuxiliaresLogica.UnionInterfaces;
import Interfaz.InterfazJugador.InterfazUsuario;
import Interfaz.Menu.MenuPrincipal;
import Logica.Dialogo;
import Logica.Escenario;
import Logica.Juego;
import Logica.Partida;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.general.GeneralTree;

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
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class Pasillo1 extends ModeloEscenario {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Pasillo1.class.getName());
    private Dimension tamPant;
    private java.util.Timer timer;
    private TimerTask tarea;
    private InterfazUsuario interfazUsuario;
    private Timer timer2;
    private TimerTask tarea2;
    /**
     * Creates new form Entrada
     */
    public Pasillo1() {
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        timer2 = new Timer();
        tarea2 = new TimerTask() {
            @Override
            public void run() {

                if(UnionInterfaces.getInstance().getCerrarVentana()){
                    MenuPrincipal menu = new MenuPrincipal();
                    menu.setVisible(true);
                    UnionInterfaces.getInstance().setCerrarVentana(false);
                    dispose();

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
                dispose();  UnionInterfaces.getInstance().setUsandoFlecha(false);
            }
        };

    }


    @SuppressWarnings("unchecked")
    private void initComponents() {
        cajaTexto  = new JPanel();
        flechaEntradaDentro = new JButton();
        flechaCamara = new JButton();
        flechaPasillo3 = new JButton();
        lugar = new JLabel();
        interfazUsuario= new InterfazUsuario();
        try {
            BufferedImage imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/pasillo 1.jpg"));

            // Actualizacion de donde esta el Jugador
            Juego.getInstance().getPartidaActual().buscarEscenarioNombre("Ala este");


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

            BufferedImage imagen2 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha abajo.png"));
            ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
            flechaEntradaDentro.setIcon(icono2);

            BufferedImage imagen3 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha derecha.png"));
            ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
            flechaCamara.setIcon(icono3);

            BufferedImage imagen4 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha derecha.png"));
            ImageIcon icono4 = new ImageIcon(imagen4.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
            flechaPasillo3.setIcon(icono4);

            getContentPane().add(cajaTexto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        flechaEntradaDentro.setBounds((int) (tamPant.width*0.4), (int) (tamPant.height*0.84), (int) (tamPant.width*0.048), (int) (tamPant.height*0.124));
        flechaEntradaDentro.setBackground(Color.red);
        flechaEntradaDentro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                EfectosEspeciales e = EfectosEspeciales.getInstancia();
                e.efectoDePasos();
                flechaEntradaActionPerformed(evt);
            }
        });
        flechaEntradaDentro.setToolTipText("Recepcion");
        flechaEntradaDentro.setOpaque(true);
        flechaEntradaDentro.setContentAreaFilled(false);
        flechaEntradaDentro.setBorderPainted(false);
        flechaEntradaDentro.setFocusPainted(false);
        flechaEntradaDentro.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaEntradaDenMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaEtradaDentMouseExited(evt);
            }
        });

        getContentPane().add(flechaEntradaDentro);



        flechaCamara.setBounds((int) (tamPant.width*0.6), (int) (tamPant.height*0.72), (int) (tamPant.width*0.08), (int) (tamPant.height*0.07));
        flechaCamara.setBackground(Color.red);
        flechaCamara.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                EfectosEspeciales e = EfectosEspeciales.getInstancia();
                e.efectoDePasos();
                flechaCamaraActionPerformed(evt);
            }
        });
        flechaCamara.setToolTipText("Sala de Vigilancia");
        flechaCamara.setOpaque(true);
        flechaCamara.setContentAreaFilled(false);
        flechaCamara.setBorderPainted(false);
        flechaCamara.setFocusPainted(false);
        flechaCamara.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaCamaraMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaCamaraMouseExited(evt);
            }
        });


        getContentPane().add(flechaCamara);


        flechaPasillo3.setBounds((int) (tamPant.width*0.45), (int) (tamPant.height*0.52), (int) (tamPant.width*0.08), (int) (tamPant.height*0.07));
        flechaPasillo3.setBackground(Color.red);
        flechaPasillo3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                EfectosEspeciales e = EfectosEspeciales.getInstancia();
                e.efectoDePasos();
                flechaPasillo3ActionPerformed(evt);
            }
        });
        flechaPasillo3.setToolTipText("Oficinas Planta Baja");
        flechaPasillo3.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaMouseExited(evt);
            }
        });

        flechaPasillo3.setOpaque(true);
        flechaPasillo3.setContentAreaFilled(false);
        flechaPasillo3.setBorderPainted(false);
        flechaPasillo3.setFocusPainted(false);
        getContentPane().add(flechaPasillo3);

        lugar.setText("Ala este");
        lugar.setOpaque(false);
        lugar.setForeground(Color.white);
        lugar.setFont(new Font("Segoe UI", 0, (int) (tamPant.width*0.035)));
        lugar.setBounds((int) (tamPant.width*0.03), (int) (tamPant.height*0.06), (int) (tamPant.width*0.3), (int) (tamPant.height*0.1));
        getContentPane().add(lugar);

        interfazUsuario.setBounds((int) (tamPant.width*0.55), (int) (tamPant.height*0.05), (int) (tamPant.width*0.45), (int) (tamPant.height*0.15));
        getContentPane().add(interfazUsuario);

        getContentPane().add(jLabel1);
        pack();
        timer2.scheduleAtFixedRate(tarea2, 0, 10);
    }

    private void flechaCamaraActionPerformed(ActionEvent evt) {
        if(!UnionInterfaces.getInstance().getUsandoFlecha()) {
            UnionInterfaces.getInstance().setUsandoFlecha(true);
            EfectosEspeciales e = EfectosEspeciales.getInstancia();
            e.efectoDePasos();

            SalaDeCamaras sC = new SalaDeCamaras();
            sC.setVisible(true);
            tarea2.cancel();
            timer.schedule(tarea, 500);
        }
    }

    private void flechaCamaraMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha derecha.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaCamara.setIcon(icono);
    }

    private void flechaCamaraMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha derecha BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaCamara.setIcon(icono);
    }
    private void flechaEntradaActionPerformed(ActionEvent evt) {
        if(!UnionInterfaces.getInstance().getUsandoFlecha()) {
            UnionInterfaces.getInstance().setUsandoFlecha(true);
            Recepcion entradaD = new Recepcion();
        entradaD.setVisible(true);
        tarea2.cancel();
        timer.schedule(tarea, 500);
    }
    }
    private void flechaPasillo3ActionPerformed(ActionEvent evt) {
        if(!UnionInterfaces.getInstance().getUsandoFlecha()) {
            UnionInterfaces.getInstance().setUsandoFlecha(true);
            EfectosEspeciales e = EfectosEspeciales.getInstancia();
            e.efectoDePasos();
            Pasillo3 pasillo3 = new Pasillo3();
            pasillo3.setVisible(true);
            tarea2.cancel();
            timer.schedule(tarea, 500);
        }
    }
    private void flechaEtradaDentMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha abajo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaEntradaDentro.setIcon(icono);
    }

    private void flechaEntradaDenMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha abajo BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaEntradaDentro.setIcon(icono);
    }
    private void flechaMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha derecha.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaPasillo3.setIcon(icono);
    }

    private void flechaMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha derecha BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaPasillo3.setIcon(icono);
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
        java.awt.EventQueue.invokeLater(() -> new Pasillo1().setVisible(true));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel cajaTexto;
    private javax.swing.JButton flechaEntradaDentro;
    private javax.swing.JButton flechaCamara;
    private javax.swing.JButton flechaPasillo3;
    private javax.swing.JLabel lugar;
    // End of variables declaration//GEN-END:variables
}
