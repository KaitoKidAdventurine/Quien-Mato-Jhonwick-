package Interfaz.Escenarios;

import DatosAuxiliaresLogica.EfectosEspeciales;
import DatosAuxiliaresLogica.UnionInterfaces;
import Interfaz.InterfazJugador.CuadroTexto;
import Interfaz.InterfazJugador.InterfazUsuario;
import Interfaz.InterfazJugador.OpcionesDialogos;
import Interfaz.Menu.MenuPrincipal;
import Interfaz.MiniJuego.MinijuegoInterfaz;
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

public class SalaDeCamaras extends ModeloEscenario {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SalaDeCamaras.class.getName());
    private Dimension tamPant;
    private Timer timer;
    private TimerTask tarea;
    private InterfazUsuario interfazUsuario;
    private Timer timer2;
    private TimerTask tarea2;
    private JButton seguridad;
    private JButton revisarCamaras;
    private Timer timer3;
    private TimerTask tarea3;

    /**
     * Creates new form Entrada
     */
    public SalaDeCamaras() {
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
                if(Juego.getInstance().getPartidaActual().getEventos().isCamarasRevisadas()){
                    cajaTexto.removeAll();
                    Juego.getInstance().getPartidaActual().getEventos().cambiarARonda1();
                    ponerDialogoRevision();
                    tarea3.cancel();
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
        flechaPasillo1 = new JButton();
        lugar = new JLabel();
        interfazUsuario= new InterfazUsuario();
        seguridad = new JButton();
        revisarCamaras = new JButton();

        try {
            BufferedImage imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/sala de camaras.jpg"));




            // Actualizacion de donde esta el Jugador
            Juego.getInstance().getPartidaActual().buscarEscenarioNombre("Sala de Vigilancia");



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
            flechaPasillo1.setIcon(icono2);

            BufferedImage imagen3 = ImageIO.read(new File("DatosAuxiliares/Personajes/Seguridad.png"));
            ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.125), (int) (tamPant.height*0.57), Image.SCALE_SMOOTH));
            seguridad.setIcon(icono3);

            BufferedImage imagen4 = ImageIO.read(new File("DatosAuxiliares/Minijuego/Buscar.png"));
            ImageIcon icono4 = new ImageIcon(imagen4.getScaledInstance((int) (tamPant.width*0.08), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
            revisarCamaras.setIcon(icono4);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        revisarCamaras.setBounds((int) (tamPant.width*0.47), (int) (tamPant.height*0.53), (int) (tamPant.width*0.08), (int) (tamPant.height*0.11));
        revisarCamaras.setContentAreaFilled(false);
        revisarCamaras.setBorderPainted(false);
        revisarCamaras.setFocusPainted(false);
        revisarCamaras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                revisarCamarasActionPerformed(evt);
            }

        });
        revisarCamaras.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                revisarCamarasMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                revisarCamarasMouseExited(evt);
            }
        });
        revisarCamaras.setVisible(false);
        getContentPane().add(revisarCamaras);

        flechaPasillo1.setBounds((int) (tamPant.width*0.47), (int) (tamPant.height*0.84), (int) (tamPant.width*0.048), (int) (tamPant.height*0.124));
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
        flechaPasillo1.setToolTipText("Ala este");
        flechaPasillo1.setOpaque(true);
        flechaPasillo1.setContentAreaFilled(false);
        flechaPasillo1.setBorderPainted(false);
        flechaPasillo1.setFocusPainted(false);

        getContentPane().add(flechaPasillo1);


        seguridad.setBounds((int) (tamPant.width*0.73), (int) (tamPant.height*0.37), (int) (tamPant.width*0.125), (int) (tamPant.height*0.57));
        seguridad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                seguridadActionPerformed(evt);
            }

        });
        seguridad.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                seguridadMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                seguridadMouseExited(evt);
            }
        });
        seguridad.setOpaque(true);
        seguridad.setContentAreaFilled(false);
        seguridad.setBorderPainted(false);
        seguridad.setFocusPainted(false);
        getContentPane().add(seguridad);

        lugar.setText("Sala de Vigilancia");
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

    private void revisarCamarasMouseExited(MouseEvent evt) {
        BufferedImage imagen =null;
        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/Minijuego/Buscar.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.08), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        revisarCamaras.setIcon(icono);

    }

    private void revisarCamarasMouseEntered(MouseEvent evt) {
       BufferedImage imagen =null;
        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/Minijuego/Buscar BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.08), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        revisarCamaras.setIcon(icono);
    }

    private void revisarCamarasActionPerformed(ActionEvent evt) {
        MiniJuego miniJuego = Juego.getInstance().getMinijuego(1);

        MinijuegoInterfaz minijuegoInterfaz = new MinijuegoInterfaz(miniJuego, 1);
        minijuegoInterfaz.setBounds(0, 0, tamPant.width, tamPant.height);
        cajaTexto.add(minijuegoInterfaz);
        timer3.scheduleAtFixedRate(tarea3, 0 ,20);
    }

    private void seguridadMouseExited(MouseEvent evt) {
        BufferedImage imagen3 = null;
        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/Personajes/Seguridad.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.125), (int) (tamPant.height*0.57), Image.SCALE_SMOOTH));
        seguridad.setIcon(icono3);
    }

    private void seguridadMouseEntered(MouseEvent evt) {
        BufferedImage imagen3 = null;
        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/Personajes/Seguridad BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.125), (int) (tamPant.height*0.57), Image.SCALE_SMOOTH));
        seguridad.setIcon(icono3);
    }

    private void seguridadActionPerformed(ActionEvent evt) {
        if((Juego.getInstance().getPartidaActual().getEventos().getRonda()==0 &&!Juego.getInstance().getPartidaActual().getEventos().isSeguridadYa())){
            ponerDialogo();
            seguridad.setVisible(false);
        }else
            ponerDialogosEstatico(crearDialogoYa(), 0, true);
    }

    private ArrayList<Dialogo> crearDialogoYa(){
        ArrayList<Dialogo> dialogoEstatic = new ArrayList<>();

        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");
        Dialogo d1= new Dialogo("(Ya hable con el)", "Detective", detective, true);
        Dialogo d2= new Dialogo("(Deberia de enfocarme en recorrer el museo y buscar otras pistas.)", "Detective", detective, true);

        dialogoEstatic.add(d1);
        dialogoEstatic.add(d2);
        return dialogoEstatic;
    }
    private void ponerDialogosEstatico(ArrayList<Dialogo> dialogos, int actual, boolean seguridad) {
        if(actual<dialogos.size()) {
            Dialogo aux = dialogos.get(actual);
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono(),!aux.getTextoImport().isEmpty());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);
            actual++;
            int finalActual = actual;

            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    if (seguridad)
                        estatiMouseClicked(dialogos, finalActual);
                    else
                        estatiMouseClickedPuerta(dialogos, finalActual);
                }
            });
            cajaTexto.removeAll();
            cajaTexto.add(cT);
        }else {
            cajaTexto.removeAll();
        }

    }
    private void ponerDialogosEstaticoSeguridad2(ArrayList<Dialogo> dialogos, int actual) {
        if(actual<dialogos.size()) {
            Dialogo aux = dialogos.get(actual);
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono(),!aux.getTextoImport().isEmpty());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);
            actual++;
            int finalActual = actual;

            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    estatiMouseClickedSegu2(dialogos, finalActual);
                }
            });
            cajaTexto.removeAll();
            cajaTexto.add(cT);
        }else {

            seguridad.setVisible(true);

            ObjetoEscenario pan = new ObjetoEscenario("Pan con jamon",true,  new ImageIcon("DatosAuxiliares/Objetos/Pan con jamon.png"), 0.1F, 0.1F, 0.1F, 0.1F, false, "Aperitivo entregado por el guardia de seguridad. Por alguna razón el jamón parece casi hechado a perder.");
            Juego.getInstance().getPartidaActual().getJugador().agregarAlMaletin(pan);
            cajaTexto.removeAll();
        }

    }
    private void estatiMouseClickedPuerta(ArrayList<Dialogo> dialogos, int actual) {
        ponerDialogosEstatico(dialogos, actual, false);
    }
    private void estatiMouseClickedSegu2(ArrayList<Dialogo> dialogos, int actual) {
        ponerDialogosEstaticoSeguridad2(dialogos, actual);
    }
    private void estatiMouseClicked(ArrayList<Dialogo> dialogos, int actual){
        ponerDialogosEstatico(dialogos, actual, true);

    }

    public void ponerDialogo() {
        if(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(1).getNodoDialActual() == null || !(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(1).getArbolDial().nodeIsLeaf(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(1).getNodoDialActual()))) {
            if(!(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(1).getNodoDialActual()==null)){
                Dialogo actual = Juego.getInstance().getPartidaActual().getEscenariosMundo().get(1).getDialogoActual();
                if(!actual.getOpciones().isEmpty()){
                    OpcionesDialogos oD = new OpcionesDialogos(new JFrame(), true, actual.getOpciones());
                    oD.setBounds((int) (tamPant.width*0.28),(int) (tamPant.getHeight()*0.37), (int) (tamPant.width*0.48),(int) (tamPant.getHeight()*0.5));
                    oD.setVisible(true);
                }
            }
            Dialogo aux = Juego.getInstance().getPartidaActual().getEscenariosMundo().get(1).getDialogoSiguiente(UnionInterfaces.getInstance().getOpcionDialogo());
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono(),!aux.getTextoImport().isEmpty());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);
            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    seguridadMouseClicked(evt);
                }
            });

            if(UnionInterfaces.getInstance().getOpcionDialogo()!=1)
                UnionInterfaces.getInstance().setOpcionDialogo(1);

            cajaTexto.removeAll();
            cajaTexto.add(cT);
        }else {
            cajaTexto.removeAll();
            revisarCamaras.setVisible(true);
            Juego.getInstance().getPartidaActual().getEventos().setSeguridadYa(true);
            Juego.getInstance().getPartidaActual().getEventos().setPuertaCerrada(true);
            seguridad.setVisible(true);
        }
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    private void seguridadMouseClicked(MouseEvent evt) {
        ponerDialogo();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    private void flechaPasillo1ActionPerformed(ActionEvent evt) {
        if(!Juego.getInstance().getPartidaActual().getEventos().isPuertaCerrada()){
            if(!UnionInterfaces.getInstance().getUsandoFlecha()) {
                UnionInterfaces.getInstance().setUsandoFlecha(true);
                EfectosEspeciales e = EfectosEspeciales.getInstancia();
                e.efectoDePasos();

                Pasillo1 pasillo1 = new Pasillo1();
                pasillo1.setVisible(true);
                tarea2.cancel();
                timer.schedule(tarea, 500);
            }
        }else{
            ponerDialogoPuertaCerrada();
        }


    }

    private void ponerDialogoPuertaCerrada() {
        ArrayList<Dialogo> dialogos = new ArrayList<>();
        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");
        Dialogo d1 = new Dialogo("No creo que sea buena idea salir de la habitación, primero debo de investigar las camaras", "Detective", detective, true);
        dialogos.add(d1);
        ponerDialogosEstatico(dialogos, 0, false);
    }

    private void ponerDialogoRevision() {
        ArrayList<Dialogo> dialogos = new ArrayList<>();
        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");
        ImageIcon seguri = new ImageIcon("DatosAuxiliares/Personajes/Seguridad.png");
        ImageIcon nada = new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png");

        Dialogo d1 = new Dialogo("Tenias razon, las camaras no revelan nada extraño. Todo parece extrañamente normal a excepcion de la camara que misteriosamente se desconecto a la hora del crimen.", "Detective", detective, true);
        Dialogo d2 = new Dialogo("Ves, se lo dije. A mi tambien me parecio un poco raro, asi que fui a investigar, ahi fue cuando encontre el cuerpo.", "Seguridad", seguri, true);
        Dialogo d3 = new Dialogo("Y como se que no te estas inventando eso para cubrirte la espalda.", "Detective", detective, true);
        Dialogo d4 = new Dialogo("Noo.. detective, tiene que creerme. Por favor. Vamos. Por que mataria yo a alguien como el economico. El chaval era un buen tipo, un poco rarito, pero bueno.", "Seguridad", seguri, true);
        Dialogo d5 = new Dialogo("Puede que tenngas razon, no tengo ninguna prueba que te señale como sospechoso, mas que fuiste el que encontro el cuerpo.", "Detective", detective, true);
        Dialogo d6 = new Dialogo("Gracias, muchas gracias, se lo agradezco desde el fondo de mi corazon, si me llegara a pasar algo mi hijo la pasaria muy mal." , "Seguridad", seguri, true);
        Dialogo d7 = new Dialogo("No cantes victoria, todavia no he decidido si eres totalmente libre de culpa.", "Detective", detective, true);
        Dialogo d8 = new Dialogo("Oh, claro. Bueno, no importa, tome esto, a esta hora se que da bastante hambre. Por si quiere un aperitivo nocturno.", "Seguridad", seguri, true);
        Dialogo d9 = new Dialogo("-Estira su mano para darte lo que parece un pan con jamon.-" , "", nada, true);
        Dialogo d10 = new Dialogo("Tomelo, es inofensivo, despues de todo era parte de mi comida para la guardia.", "Seguridad", seguri, true);
        Dialogo d11 = new Dialogo("No no, no tiene que darme nada.", "Detective", detective, true);
        Dialogo d12 = new Dialogo("No no, yo insisto." , "Seguridad", seguri, true);
        Dialogo d13 = new Dialogo("Aghh bueno, no pienso perder mas tiempo en esto, simplemente lo aceptare.", "Detective", detective, true);
        Dialogo d14 = new Dialogo("Perfecto, disfrutelo que esta bastante bueno.", "Seguridad", seguri, true);
        Dialogo d15 = new Dialogo("(Supongo que no tengo mas remedio que simplemente ponerlo en la mochila con las demas cosas. )", "Detective", detective, true);

        dialogos.add(d1);
        dialogos.add(d2);
        dialogos.add(d3);
        dialogos.add(d4);
        dialogos.add(d5);
        dialogos.add(d6);
        dialogos.add(d7);
        dialogos.add(d8);
        dialogos.add(d9);
        dialogos.add(d10);
        dialogos.add(d11);
        dialogos.add(d12);
        dialogos.add(d13);
        dialogos.add(d14);
        dialogos.add(d15);

        revisarCamaras.setVisible(false);
        seguridad.setVisible(false);
        ponerDialogosEstaticoSeguridad2(dialogos, 0);
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
        java.awt.EventQueue.invokeLater(() -> new SalaDeCamaras().setVisible(true));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel cajaTexto;
    private javax.swing.JButton flechaPasillo1;
    private javax.swing.JLabel lugar;
    // End of variables declaration//GEN-END:variables
}