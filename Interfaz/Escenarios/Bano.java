package Interfaz.Escenarios;

import DatosAuxiliaresLogica.EfectosEspeciales;
import DatosAuxiliaresLogica.UnionInterfaces;
import Interfaz.InterfazJugador.CuadroTexto;
import Interfaz.InterfazJugador.InterfazUsuario;
import Interfaz.InterfazJugador.OpcionesDialogos;
import Interfaz.Menu.MenuPrincipal;
import Logica.Dialogo;
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

public class Bano extends ModeloEscenario {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Bano.class.getName());
    private Dimension tamPant;
    private int dialogoActual;
    private Timer timer;
    private TimerTask tarea;
    private Timer timer2;
    private TimerTask tarea2;
    private InterfazUsuario interfazUsuario;
    private JButton conserje;

    /**
     * Creates new form Entrada
     */
    public Bano() {
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
        flechaEntradaDentro = new JButton();
        lugar = new JLabel();
        interfazUsuario= new InterfazUsuario();
        conserje = new JButton();



        try {
            BufferedImage imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/baño 1.jpg"));

            Juego.getInstance().getPartidaActual().buscarEscenarioNombre("Banno Planta Baja");

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

            BufferedImage imagen3 = ImageIO.read(new File("DatosAuxiliares/Personajes/Conserje.png"));
            ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.2), (int) (tamPant.height*0.6), Image.SCALE_SMOOTH));
            conserje.setIcon(icono3);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        flechaEntradaDentro.setBounds((int) (tamPant.width*0.35), (int) (tamPant.height*0.83), (int) (tamPant.width*0.048), (int) (tamPant.height*0.124));
        flechaEntradaDentro.setBackground(Color.red);
        flechaEntradaDentro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaSalidaActionPerformed(evt);
            }
        });
        flechaEntradaDentro.setToolTipText("Recepcion");
        flechaEntradaDentro.setOpaque(true);
        flechaEntradaDentro.setContentAreaFilled(false);
        flechaEntradaDentro.setBorderPainted(false);
        flechaEntradaDentro.setFocusPainted(false);

        flechaEntradaDentro.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaEntradaDentroMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaEntradaDentroMouseExited(evt);
            }
        });


        getContentPane().add(cajaTexto);
        getContentPane().add(flechaEntradaDentro);

        conserje.setBounds((int) (tamPant.width*0.6), (int) (tamPant.height*0.36), (int) (tamPant.width*0.2), (int) (tamPant.height*0.6));
        conserje.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                conserjeActionPerformed(evt);
            }

        });
        conserje.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                conserjeMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                conserjeMouseExited(evt);
            }
        });
        conserje.setOpaque(true);
        conserje.setContentAreaFilled(false);
        conserje.setBorderPainted(false);
        conserje.setFocusPainted(false);
        getContentPane().add(conserje);

        lugar.setText("Baño Planta Baja");
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

    private void conserjeMouseExited(MouseEvent evt) {
        BufferedImage imagen3 = null;
        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/Personajes/Conserje.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.2), (int) (tamPant.height*0.6), Image.SCALE_SMOOTH));
        conserje.setIcon(icono3);
    }

    private void conserjeMouseEntered(MouseEvent evt) {
        BufferedImage imagen3 = null;
        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/Personajes/Conserje BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.2), (int) (tamPant.height*0.6), Image.SCALE_SMOOTH));
        conserje.setIcon(icono3);
    }

    private void conserjeActionPerformed(ActionEvent evt) {
        ponerDialogo();
        conserje.setVisible(false);
    }

    private void flechaEntradaDentroMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha abajo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaEntradaDentro.setIcon(icono);
    }

    private void flechaEntradaDentroMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha abajo BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaEntradaDentro.setIcon(icono);
    }

    public void ponerDialogo() {
        if(Juego.getInstance().getEscenarios().get(2).getNodoDialActual() == null || !(Juego.getInstance().getEscenarios().get(2).getArbolDial().nodeIsLeaf(Juego.getInstance().getEscenarios().get(2).getNodoDialActual()))) {
            if(!(Juego.getInstance().getEscenarios().get(2).getNodoDialActual()==null)){
                Dialogo actual = Juego.getInstance().getEscenarios().get(2).getDialogoActual();
                if(!actual.getOpciones().isEmpty()){
                    OpcionesDialogos oD = new OpcionesDialogos(new JFrame(), true, actual.getOpciones());
                    oD.setBounds((int) (tamPant.width*0.28),(int) (tamPant.getHeight()*0.37), (int) (tamPant.width*0.48),(int) (tamPant.getHeight()*0.5));
                    oD.setVisible(true);
                }
            }
            Dialogo aux = Juego.getInstance().getEscenarios().get(2).getDialogoSiguiente(UnionInterfaces.getInstance().getOpcionDialogo());
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);
            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    conserjeMouseClicked(evt);
                }
            });
            if(UnionInterfaces.getInstance().getOpcionDialogo()!=1)
                UnionInterfaces.getInstance().setOpcionDialogo(1);
            cajaTexto.removeAll();
            cajaTexto.add(cT);
        }else {
            cajaTexto.removeAll();
            revalidate();
            repaint();
            conserje.setVisible(true);
        }
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    private void conserjeMouseClicked(MouseEvent evt) {
        ponerDialogo();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    private void flechaSalidaActionPerformed(ActionEvent evt) {
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDePasos();

        Recepcion entradaD = new Recepcion();
        entradaD.setVisible(true);
        tarea2.cancel();
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
        java.awt.EventQueue.invokeLater(() -> new Bano().setVisible(true));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel cajaTexto;
    private javax.swing.JButton flechaEntradaDentro;
    private javax.swing.JLabel lugar;
    // End of variables declaration//GEN-END:variables
}