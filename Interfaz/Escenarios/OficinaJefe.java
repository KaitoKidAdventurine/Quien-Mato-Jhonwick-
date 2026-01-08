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
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class OficinaJefe extends ModeloEscenario {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(OficinaJefe.class.getName());
    private Dimension tamPant;
    private Timer timer;
    private TimerTask tarea;
    private Timer timer2;
    private TimerTask tarea2;
    private InterfazUsuario interfazUsuario;
    private JButton duenno;

    /**
     * Creates new form Entrada
     */
    public OficinaJefe() {
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
                }else {
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
                dispose(); UnionInterfaces.getInstance().setUsandoFlecha(false);
            }
        };

    }


    @SuppressWarnings("unchecked")
    private void initComponents() {
        cajaTexto  = new JPanel();
        flechaPasillo2 = new JButton();
        lugar = new JLabel();
        interfazUsuario= new InterfazUsuario();
        duenno = new JButton();
        try {
            BufferedImage imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/oficina del dueño.jpg"));

            // Actualizacion de donde esta el Jugador
            Juego.getInstance().getPartidaActual().buscarEscenarioNombre("Oficina del Jefe");


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
            getContentPane().add(cajaTexto);
            BufferedImage imagen2 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha abajo.png"));
            ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
            flechaPasillo2.setIcon(icono2);

            BufferedImage imagen3 = ImageIO.read(new File("DatosAuxiliares/Personajes/Dueño.png"));
            ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.22), (int) (tamPant.height*0.63), Image.SCALE_SMOOTH));
            duenno.setIcon(icono3);

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
        flechaPasillo2.setOpaque(true);
        flechaPasillo2.setContentAreaFilled(false);
        flechaPasillo2.setBorderPainted(false);
        flechaPasillo2.setFocusPainted(false);
        flechaPasillo2.setToolTipText("Sala norte");
        flechaPasillo2.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaMouseExited(evt);
            }
        });

        getContentPane().add(flechaPasillo2);
        duenno.setBounds((int) (tamPant.width*0.68), (int) (tamPant.height*0.3), (int) (tamPant.width*0.22), (int) (tamPant.height*0.63));
        duenno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                duennoActionPerformed(evt);
            }

        });
        duenno.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                duennoMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                duennoMouseExited(evt);
            }
        });
        duenno.setOpaque(true);
        duenno.setContentAreaFilled(false);
        duenno.setBorderPainted(false);
        duenno.setFocusPainted(false);

        getContentPane().add(duenno);


        lugar.setText("Oficina del Jefe");
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

    private void duennoMouseExited(MouseEvent evt) {
        BufferedImage imagen3 = null;
        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/Personajes/Dueño.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.22), (int) (tamPant.height*0.63), Image.SCALE_SMOOTH));
        duenno.setIcon(icono3);
    }

    private void duennoMouseEntered(MouseEvent evt) {
        BufferedImage imagen3 = null;
        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/Personajes/Dueño BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.22), (int) (tamPant.height*0.63), Image.SCALE_SMOOTH));
        duenno.setIcon(icono3);
    }

    private void duennoActionPerformed(ActionEvent evt) {
        if(Juego.getInstance().getPartidaActual().getEventos().getRonda()==0 && !Juego.getInstance().getPartidaActual().getEventos().isDuenoYA()){
            ponerDialogo();
            duenno.setVisible(false);
        }else
            ponerDialogosEstatico(crearDialogoYa(), 0);
    }

    private ArrayList<Dialogo> crearDialogoYa(){
        ArrayList<Dialogo> dialogosConserje = new ArrayList<>();

        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");
        Dialogo d1= new Dialogo("(Ya hable con el)", "Detective", detective, true);
        Dialogo d2= new Dialogo("(Deberia de enfocarme en recorrer el museo y buscar otras pistas.)", "Detective", detective, true);

        dialogosConserje.add(d1);
        dialogosConserje.add(d2);
        return dialogosConserje;
    }
    private void ponerDialogosEstatico(ArrayList<Dialogo> dialogos, int actual) {
        if(actual<dialogos.size()) {
            Dialogo aux = dialogos.get(actual);
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);
            actual++;
            int finalActual = actual;

            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    EstatiMouseClicked(dialogos, finalActual);
                }
            });
            cajaTexto.removeAll();
            cajaTexto.add(cT);
        }else {
            cajaTexto.removeAll();
        }

    }
    private void EstatiMouseClicked(ArrayList<Dialogo> dialogos, int actual){
        ponerDialogosEstatico(dialogos, actual);

    }

    public void ponerDialogo() {
        if(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(3).getNodoDialActual() == null || !(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(3).getArbolDial().nodeIsLeaf(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(3).getNodoDialActual()))) {
            if(!(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(3).getNodoDialActual()==null)){
                Dialogo actual = Juego.getInstance().getPartidaActual().getEscenariosMundo().get(3).getDialogoActual();
                if(!actual.getOpciones().isEmpty()){
                    OpcionesDialogos oD = new OpcionesDialogos(new JFrame(), true, actual.getOpciones());
                    oD.setBounds((int) (tamPant.width*0.28),(int) (tamPant.getHeight()*0.37), (int) (tamPant.width*0.48),(int) (tamPant.getHeight()*0.5));
                    oD.setVisible(true);
                }
            }
            Dialogo aux = Juego.getInstance().getPartidaActual().getEscenariosMundo().get(3).getDialogoSiguiente(UnionInterfaces.getInstance().getOpcionDialogo());
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);
            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    duennoMouseClicked(evt);
                }
            });
            if(UnionInterfaces.getInstance().getOpcionDialogo()!=1)
                UnionInterfaces.getInstance().setOpcionDialogo(1);
            cajaTexto.removeAll();
            cajaTexto.add(cT);
        }else {
            cajaTexto.removeAll();
            Juego.getInstance().getPartidaActual().getEventos().setDuenoYA(true);
            Juego.getInstance().getPartidaActual().getEventos().cambiarARonda1();
            duenno.setVisible(true);
        }
    }

    private void flechaPasillo2ActionPerformed(ActionEvent evt) {
        if(!UnionInterfaces.getInstance().getUsandoFlecha()) {
            UnionInterfaces.getInstance().setUsandoFlecha(true);
            EfectosEspeciales e = EfectosEspeciales.getInstancia();
            e.efectoDePasos();

            Pasillo2 p2 = new Pasillo2();
            p2.setVisible(true);
            tarea2.cancel();
            timer.schedule(tarea, 500);
        }
    }
    private void duennoMouseClicked(MouseEvent evt) {
        ponerDialogo();
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    private void flechaMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha abajo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaPasillo2.setIcon(icono);
    }

    private void flechaMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha abajo BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
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
        java.awt.EventQueue.invokeLater(() -> new OficinaJefe().setVisible(true));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel cajaTexto;
    private javax.swing.JButton flechaPasillo2;
    private javax.swing.JLabel lugar;
    // End of variables declaration//GEN-END:variables
}
