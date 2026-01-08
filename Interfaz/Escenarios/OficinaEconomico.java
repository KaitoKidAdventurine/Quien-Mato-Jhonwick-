package Interfaz.Escenarios;

//import Interfaz.InterfazJugador.InterfazUsuario;
import DatosAuxiliaresLogica.EfectosEspeciales;

import DatosAuxiliaresLogica.UnionInterfaces;
import Interfaz.Cinematicas.FinDelJuego;
import Interfaz.InterfazJugador.CuadroTexto;
import Interfaz.InterfazJugador.InterfazUsuario;
import Interfaz.InterfazJugador.OpcionesDialogos;
import Interfaz.Menu.MenuPrincipal;
import Logica.*;

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

public class OficinaEconomico extends ModeloEscenario {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(OficinaEconomico.class.getName());
    private Dimension tamPant;
    private java.util.Timer timer;
    private TimerTask tarea;
    private Timer timer2;
    private TimerTask tarea2;
    private InterfazUsuario interfazUsuario;
    private JButton victima;
    private JButton computadora;
    /**
     * Creates new form Entrada
     */
    public OficinaEconomico() {
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
                dispose();  UnionInterfaces.getInstance().setUsandoFlecha(false);
            }
        };

    }


    @SuppressWarnings("unchecked")
    private void initComponents() {
        cajaTexto  = new JPanel();
        flechaPasillo3 = new JButton();
        lugar = new JLabel();
        interfazUsuario= new InterfazUsuario();
        victima = new JButton();
        computadora= new JButton();
        try {
            BufferedImage imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/Oficina Victima.png"));

            // Actualizacion de donde esta el Jugador
            Juego.getInstance().getPartidaActual().buscarEscenarioNombre("Oficina de la Victima");

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

            BufferedImage imagen2 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha derecha.png"));
            ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
            flechaPasillo3.setIcon(icono2);



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        flechaPasillo3.setBounds((int) (tamPant.width*0.8), (int) (tamPant.height*0.68), (int) (tamPant.width*0.08), (int) (tamPant.height*0.07));
        flechaPasillo3.setBackground(Color.red);
        flechaPasillo3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaPasillo3ActionPerformed(evt);
            }
        });

        flechaPasillo3.setOpaque(true);
        flechaPasillo3.setContentAreaFilled(false);
        flechaPasillo3.setBorderPainted(false);
        flechaPasillo3.setFocusPainted(false);
        flechaPasillo3.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaMouseExited(evt);
            }
        });
        flechaPasillo3.setToolTipText("Oficina Planta Baja");
        getContentPane().add(flechaPasillo3);

        victima.setBounds((int) (tamPant.width*0.06), (int) (tamPant.height*0.82), (int) (tamPant.width*0.42), (int) (tamPant.height*0.2));
        victima.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                victimaActionPerformed(evt);
            }

        });

        victima.setOpaque(true);
        victima.setContentAreaFilled(false);
        victima.setBorderPainted(false);
        victima.setFocusPainted(false);

        getContentPane().add(victima);

        computadora.setBounds((int) (tamPant.width*0.32), (int) (tamPant.height*0.43), (int) (tamPant.width*0.438), (int) (tamPant.height*0.23));
        computadora.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                computadoraActionPerformed(evt);
            }

        });

        computadora.setContentAreaFilled(false);
        computadora.setBorderPainted(false);
        computadora.setFocusPainted(false);

        getContentPane().add(computadora);

        lugar.setText("Oficina de la Victima");
        lugar.setOpaque(false);
        lugar.setForeground(Color.white);
        lugar.setFont(new Font("Segoe UI", 0, (int) (tamPant.width*0.035)));
        lugar.setBounds((int) (tamPant.width*0.03), (int) (tamPant.height*0.06), (int) (tamPant.width*0.38), (int) (tamPant.height*0.1));
        getContentPane().add(lugar);


        interfazUsuario.setBounds((int) (tamPant.width*0.55), (int) (tamPant.height*0.05), (int) (tamPant.width*0.45), (int) (tamPant.height*0.15));
        getContentPane().add(interfazUsuario);

        getContentPane().add(jLabel1);

        pack();
        timer2.scheduleAtFixedRate(tarea2, 0, 10);
    }

    private void computadoraActionPerformed(ActionEvent evt) {
        if(Juego.getInstance().getPartidaActual().getEventos().getRonda()==5){
            if(Juego.getInstance().getPartidaActual().getJugador().revisarSiExisteObjetoEnMochila("Hoja de papel") /*&& Juego.getInstance().getPartidaActual().getJugador().revisarSiExisteObjetoEnMochila("Libro")*/){
                    ponerFinal();
            }else {
                ponerDialogosEstatico(crearDialogoComputadora(), 0);
            }
        }
    }



    private void victimaActionPerformed(ActionEvent evt) {
        ponerDialogosEstatico(crearDialogoMuerto(), 0);
    }


    private ArrayList<Dialogo> crearDialogoMuerto(){
        ArrayList<Dialogo> dialogosConserje = new ArrayList<>();

        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");
        Dialogo d1= new Dialogo("Es una lastima lo que te pasó", "Detective", detective, true);
        Dialogo d2= new Dialogo("Encontrare al culpable que te hizo esto y lo llevare ante la justicia", "Detective", detective, true);

        dialogosConserje.add(d1);
        dialogosConserje.add(d2);
        return dialogosConserje;
    }
    private ArrayList<Dialogo> crearDialogoComputadora(){
        ArrayList<Dialogo> dialogosConserje = new ArrayList<>();

        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");
        ImageIcon nada = new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png");
        Dialogo d1= new Dialogo("No tengo la mas remota idea de cual es la cotraseña. Probare 1234.", "Detective", detective, true);
        Dialogo d2= new Dialogo("-Error.- ", "", nada, true);
        Dialogo d3= new Dialogo("La secretaria menciono algo acerca de una frase en latin. Umbra mortis, pero estaba incompleta", "Detective", detective, true);
        Dialogo d4= new Dialogo("Debo de recorrer el museo en busca del resto de la frase.", "Detective", detective, true);

        dialogosConserje.add(d1);
        dialogosConserje.add(d2);
        dialogosConserje.add(d3);
        dialogosConserje.add(d4);
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
    private void ponerFinal() {
        FinDelJuego fin = new FinDelJuego();
        timer.schedule(tarea, 500);
        fin.setVisible(true );
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
        java.awt.EventQueue.invokeLater(() -> new OficinaEconomico().setVisible(true));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel cajaTexto;
    private javax.swing.JButton  flechaPasillo3;
    private javax.swing.JLabel lugar;
    // End of variables declaration//GEN-END:variables
}
