package Interfaz.Escenarios;

import DatosAuxiliaresLogica.EfectosEspeciales;
import DatosAuxiliaresLogica.UnionInterfaces;
import Interfaz.InterfazJugador.CuadroTexto;
import Interfaz.InterfazJugador.InterfazUsuario;
import Interfaz.InterfazJugador.OpcionesDialogos;
import Interfaz.Menu.MenuPrincipal;
import Logica.Dialogo;
import Logica.Juego;

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

public class Callejon extends ModeloEscenario {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Callejon.class.getName());
    private Dimension tamPant;
    private Timer timer;
    private TimerTask tarea;
    private Timer timer2;
    private TimerTask tarea2;
    private InterfazUsuario interfazUsuario;
    private JButton vagabundo;

    /**
     * Creates new form Entrada
     */
    public Callejon() {
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
                dispose();
                UnionInterfaces.getInstance().setUsandoFlecha(false);
            }
        };

    }


    @SuppressWarnings("unchecked")
    private void initComponents() {
        cajaTexto  = new JPanel();
        flechaPasilloAlmacen = new JButton();
        lugar = new JLabel();
        interfazUsuario= new InterfazUsuario();
        vagabundo = new JButton();



        try {
            BufferedImage imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/callejon 1.jpg"));

            // Actualizacion de donde esta el Jugador
            Juego.getInstance().getPartidaActual().buscarEscenarioNombre("Callejon");

            jLabel1 = new JLabel();
            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
            flechaPasilloAlmacen.setIcon(icono2);

            BufferedImage imagen3 = ImageIO.read(new File("DatosAuxiliares/Personajes/Vagabundo.png"));
            ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.139), (int) (tamPant.height*0.29), Image.SCALE_SMOOTH));
            vagabundo.setIcon(icono3);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        flechaPasilloAlmacen.setBounds((int) (tamPant.width*0.4), (int) (tamPant.height*0.84), (int) (tamPant.width*0.048), (int) (tamPant.height*0.124));
        flechaPasilloAlmacen.setBackground(Color.red);
        flechaPasilloAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flechaPasilloAlmacenActionPerformed(evt);
            }
        });
        flechaPasilloAlmacen.setOpaque(true);
        flechaPasilloAlmacen.setContentAreaFilled(false);
        flechaPasilloAlmacen.setBorderPainted(false);
        flechaPasilloAlmacen.setFocusPainted(false);
        flechaPasilloAlmacen.setToolTipText("Ala sur");
        flechaPasilloAlmacen.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaMouseExited(evt);
            }
        });

        getContentPane().add(cajaTexto);
        getContentPane().add(flechaPasilloAlmacen);




        lugar.setText("Callejón");
        lugar.setOpaque(false);
        lugar.setForeground(Color.white);
        lugar.setFont(new java.awt.Font("Segoe UI", 0, (int) (tamPant.width*0.035)));
        lugar.setBounds((int) (tamPant.width*0.03), (int) (tamPant.height*0.06), (int) (tamPant.width*0.3), (int) (tamPant.height*0.1));
        getContentPane().add(lugar);

        vagabundo.setBounds((int) (tamPant.width*0.65), (int) (tamPant.height*0.603), (int) (tamPant.width*0.163), (int) (tamPant.height*0.34));
        vagabundo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                vagabundoActionPerformed(evt);
            }

        });
        vagabundo.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                vagabundoMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                vagabundoMouseExited(evt);
            }
        });
        vagabundo.setOpaque(true);
        vagabundo.setContentAreaFilled(false);
        vagabundo.setBorderPainted(false);
        vagabundo.setFocusPainted(false);
        getContentPane().add(vagabundo);

        interfazUsuario.setBounds((int) (tamPant.width*0.55), (int) (tamPant.height*0.05), (int) (tamPant.width*0.45), (int) (tamPant.height*0.15));
        getContentPane().add(interfazUsuario);

        getContentPane().add(jLabel1);
        pack();
        timer2.scheduleAtFixedRate(tarea2, 0, 10);
    }

    private void vagabundoMouseExited(MouseEvent evt) {
        BufferedImage imagen3 = null;
        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/Personajes/Vagabundo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.139), (int) (tamPant.height*0.29), Image.SCALE_SMOOTH));
        vagabundo.setIcon(icono3);
    }

    private void vagabundoMouseEntered(MouseEvent evt) {
        BufferedImage imagen3 = null;
        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/Personajes/Vagabundo BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.139), (int) (tamPant.height*0.29), Image.SCALE_SMOOTH));
        vagabundo.setIcon(icono3);
    }

    private void vagabundoActionPerformed(ActionEvent evt) {
        int variable =Juego.getInstance().getPartidaActual().getEventos().getRonda();
        switch (variable){
            case 0, 1:
                ponerDialogosEstatico(crearDialogoNoDisponible(), 0);
                break;
            case 2:
                if(!Juego.getInstance().getPartidaActual().getEventos().isVagabundoYa()) {
                    ponerDialogo();
                    vagabundo.setVisible(false);
                }else ponerDialogosEstatico(crearDialogoYa(), 0);
                break;
            default:
                ponerDialogosEstatico(crearDialogoYa(), 0);
                break;
        }
    }
    private ArrayList<Dialogo> crearDialogoNoDisponible(){
        ArrayList<Dialogo> dialogosEsta= new ArrayList<>();

        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");
        Dialogo d1= new Dialogo("(No creo que sea lo mas adecuado preguntar al vagabundo acerca del lugar, por ahora.)", "Detective", detective, true);
        Dialogo d2= new Dialogo("(Pospondre su interrogatorio hasta que haya terminado con los trabajadores mas importantes.)", "Detective", detective, true);

        dialogosEsta.add(d1);
        dialogosEsta.add(d2);
        return dialogosEsta;
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
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono(),!aux.getTextoImport().isEmpty());
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
        if(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(5).getNodoDialActual() == null || !(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(5).getArbolDial().nodeIsLeaf(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(5).getNodoDialActual()))) {
            if(!(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(5).getNodoDialActual()==null)){
                Dialogo actual = Juego.getInstance().getPartidaActual().getEscenariosMundo().get(5).getDialogoActual();
                if(!actual.getOpciones().isEmpty()){
                    OpcionesDialogos oD = new OpcionesDialogos(new JFrame(), true, actual.getOpciones());
                    oD.setBounds((int) (tamPant.width*0.28),(int) (tamPant.getHeight()*0.37), (int) (tamPant.width*0.48),(int) (tamPant.getHeight()*0.5));
                    oD.setVisible(true);
                }
            }
            Dialogo aux = Juego.getInstance().getPartidaActual().getEscenariosMundo().get(5).getDialogoSiguiente(UnionInterfaces.getInstance().getOpcionDialogo());
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono(),!aux.getTextoImport().isEmpty());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);
            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    vagabundoMouseClicked(evt);
                }
            });
            if(UnionInterfaces.getInstance().getOpcionDialogo()!=1)
                UnionInterfaces.getInstance().setOpcionDialogo(1);
            cajaTexto.removeAll();
            cajaTexto.add(cT);
        }else {
            cajaTexto.removeAll();
            Juego.getInstance().getPartidaActual().getEventos().setVagabundoYa(true);
            Juego.getInstance().getPartidaActual().getEventos().cambiarRonda3();
            Juego.getInstance().getPartidaActual().getJugador().usarObjeto("Pan con jamon");
            vagabundo.setVisible(true);
        }

    }
    private void flechaMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha abajo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaPasilloAlmacen.setIcon(icono);
    }

    private void flechaMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha abajo BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaPasilloAlmacen.setIcon(icono);
    }

    private void flechaPasilloAlmacenActionPerformed(ActionEvent evt) {
        if(!UnionInterfaces.getInstance().getUsandoFlecha()) {
            UnionInterfaces.getInstance().setUsandoFlecha(true);
            EfectosEspeciales e = EfectosEspeciales.getInstancia();
            e.efectoDePasos();

            PasilloAlmacen pasilloAlmacen = new PasilloAlmacen();
            pasilloAlmacen.setVisible(true);
            tarea2.cancel();
            timer.schedule(tarea, 500);
        }
    }
    private void vagabundoMouseClicked(MouseEvent evt) {
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
        java.awt.EventQueue.invokeLater(() -> new Callejon().setVisible(true));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel cajaTexto;
    private javax.swing.JButton flechaPasilloAlmacen;
    private javax.swing.JLabel lugar;
    // End of variables declaration//GEN-END:variables
}