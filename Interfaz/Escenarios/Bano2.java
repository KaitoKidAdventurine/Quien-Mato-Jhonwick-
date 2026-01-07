package Interfaz.Escenarios;

import DatosAuxiliaresLogica.EfectosEspeciales;
import DatosAuxiliaresLogica.UnionInterfaces;
import Interfaz.InterfazJugador.CuadroTexto;
import Interfaz.InterfazJugador.InterfazUsuario;
import Interfaz.InterfazJugador.OpcionesDialogos;
import Interfaz.Menu.MenuPrincipal;
import Interfaz.MiniJuego.MinijuegoInterfaz;
import Logica.Dialogo;
import Logica.Juego;
import Logica.MiniJuego;

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

public class Bano2  extends ModeloEscenario {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Bano2.class.getName());
    private Dimension tamPant;
    private int dialogoActual;
    private java.util.Timer timer;
    private TimerTask tarea;
    private Timer timer2;
    private TimerTask tarea2;
    private InterfazUsuario interfazUsuario;
    private JButton revisarBano;
    private Timer timer3;
    private TimerTask tarea3;
    /**
     * Creates new form Entrada
     */
    public Bano2() {
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
        timer3 = new Timer();
        tarea3 = new TimerTask() {
            @Override
            public void run() {
                if(Juego.getInstance().getPartidaActual().getEventos().isBanoRevisado()){
                    cajaTexto.removeAll();
                    revisarBano.setVisible(false);
                    ponerDialogo();
                    tarea3.cancel();
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
        flechaPasillo2 = new JButton();
        lugar = new JLabel();
        interfazUsuario= new InterfazUsuario();
        revisarBano = new JButton();


        try {
            BufferedImage imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/baño 2.jpg"));

            // Actualizacion de donde esta el Jugador
            Juego.getInstance().getPartidaActual().buscarEscenarioNombre("Banno Planta Alta");


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
            flechaPasillo2.setIcon(icono2);

            BufferedImage imagen3 = ImageIO.read(new File("DatosAuxiliares/Minijuego/Buscar.png"));
            ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.08), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
            revisarBano.setIcon(icono3);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        flechaPasillo2.setBounds((int) (tamPant.width*0.48), (int) (tamPant.height*0.84), (int) (tamPant.width*0.048), (int) (tamPant.height*0.124));
        flechaPasillo2.setBackground(Color.red);
        flechaPasillo2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaPasillo2ActionPerformed(evt);
            }
        });
        flechaPasillo2.setToolTipText("Ala norte");
        flechaPasillo2.setOpaque(true);
        flechaPasillo2.setContentAreaFilled(false);
        flechaPasillo2.setBorderPainted(false);
        flechaPasillo2.setFocusPainted(false);

        flechaPasillo2.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaPasillo2MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaPasillo2MouseExited(evt);
            }
        });

        getContentPane().add(cajaTexto);

        getContentPane().add(flechaPasillo2);

        revisarBano.setBounds((int) (tamPant.width*0.47), (int) (tamPant.height*0.53), (int) (tamPant.width*0.08), (int) (tamPant.height*0.11));
        revisarBano.setContentAreaFilled(false);
        revisarBano.setBorderPainted(false);
        revisarBano.setFocusPainted(false);
        revisarBano.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                revisarBanoActionPerformed(evt);
            }

        });
        revisarBano.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                revisarBanoMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                revisarBanoMouseExited(evt);
            }
        });
        if(!(Juego.getInstance().getPartidaActual().getEventos().getRonda()==5)) {
            revisarBano.setVisible(false);
        }
        if(Juego.getInstance().getPartidaActual().getEventos().isBanoRevisado()){
                revisarBano.setVisible(false);
        }
        getContentPane().add(revisarBano);

        lugar.setText("Baño Planta Alta");
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
            if(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(7).getNodoDialActual() == null || !(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(7).getArbolDial().nodeIsLeaf(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(7).getNodoDialActual()))) {
                if(!(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(7).getNodoDialActual()==null)){
                    Dialogo actual = Juego.getInstance().getPartidaActual().getEscenariosMundo().get(7).getDialogoActual();
                    if(!actual.getOpciones().isEmpty()){
                        OpcionesDialogos oD = new OpcionesDialogos(new JFrame(), true, actual.getOpciones());
                        oD.setBounds((int) (tamPant.width*0.28),(int) (tamPant.getHeight()*0.37), (int) (tamPant.width*0.48),(int) (tamPant.getHeight()*0.5));
                        oD.setVisible(true);
                    }
                }
                Dialogo aux = Juego.getInstance().getPartidaActual().getEscenariosMundo().get(7).getDialogoSiguiente(UnionInterfaces.getInstance().getOpcionDialogo());
                CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono());
                cT.setBounds(0, 0, tamPant.width, tamPant.height);
                cT.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        banoMouseClicked(evt);
                    }
                });

                if(UnionInterfaces.getInstance().getOpcionDialogo()!=1)
                    UnionInterfaces.getInstance().setOpcionDialogo(1);

                cajaTexto.removeAll();
                cajaTexto.add(cT);
            }else {
                cajaTexto.removeAll();
            }

        }

        private void banoMouseClicked(MouseEvent evt) {
            ponerDialogo();

        }

    private void revisarBanoMouseExited(MouseEvent evt) {
        BufferedImage imagen =null;
        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/Minijuego/Buscar.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.08), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        revisarBano.setIcon(icono);

    }

    private void revisarBanoMouseEntered(MouseEvent evt) {
        BufferedImage imagen =null;
        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/Minijuego/Buscar BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.08), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        revisarBano.setIcon(icono);
    }

    private void revisarBanoActionPerformed(ActionEvent evt) {
        MiniJuego miniJuego = Juego.getInstance().getMinijuego(1);

        MinijuegoInterfaz minijuegoInterfaz = new MinijuegoInterfaz(miniJuego, 2);
        minijuegoInterfaz.setBounds(0, 0, tamPant.width, tamPant.height);
        cajaTexto.add(minijuegoInterfaz);
        timer3.scheduleAtFixedRate(tarea3, 0 ,20);
    }

    private void flechaPasillo2MouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha abajo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaPasillo2.setIcon(icono);
    }

    private void flechaPasillo2MouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha abajo BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaPasillo2.setIcon(icono);
    }
    private void flechaPasillo2ActionPerformed(ActionEvent evt) {
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDePasos();

        Pasillo2 p2 = new Pasillo2();
        p2.setVisible(true);
        tarea2.cancel();
        timer.schedule(tarea, 1000);
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
        java.awt.EventQueue.invokeLater(() -> new Bano2().setVisible(true));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel cajaTexto;
    private javax.swing.JButton flechaPasillo2;
    private javax.swing.JLabel lugar;
    // End of variables declaration//GEN-END:variables
}
