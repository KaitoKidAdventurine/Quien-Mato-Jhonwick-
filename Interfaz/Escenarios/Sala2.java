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

public class Sala2  extends ModeloEscenario {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Sala2.class.getName());
    private Dimension tamPant;
    private java.util.Timer timer;
    private TimerTask tarea;
    private InterfazUsuario interfazUsuario;
    private Timer timer2;
    private TimerTask tarea2;
    private JButton guia1;
    private JButton guia2;
    /**
     * Creates new form Entrada
     */
    public Sala2() {
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
                dispose();  UnionInterfaces.getInstance().setUsandoFlecha(false);
            }
        };

    }


    @SuppressWarnings("unchecked")
    private void initComponents() {
        cajaTexto  = new JPanel();
        flechaSala = new JButton();
        lugar = new JLabel();
        interfazUsuario= new InterfazUsuario();
        guia1 = new JButton();
        guia2 = new JButton();
        try {
            BufferedImage imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/sala 2.jpg"));


            // Actualizacion de donde esta el Jugador
            Juego.getInstance().getPartidaActual().buscarEscenarioNombre("Exposicion de Antiguedades");


            jLabel1 = new JLabel();
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setMinimumSize(tamPant);
            setUndecorated(true);
            setPreferredSize(tamPant);
            getContentPane().setLayout(null);
            ImageIcon icono = new ImageIcon(imagen.getScaledInstance(tamPant.width, tamPant.height, Image.SCALE_SMOOTH));
            jLabel1.setIcon(icono); // NOI18N
            jLabel1.setFocusable(false);

            jLabel1.setBounds(0, 0, tamPant.width, tamPant.height);

            cajaTexto.setBackground(new Color(0, 0, 0, 0));
            cajaTexto.setBounds(0, 0,  tamPant.width, tamPant.height);
            cajaTexto.setLayout(null);

            BufferedImage imagen2 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha abajo.png"));
            ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
            flechaSala.setIcon(icono2);

            BufferedImage imagen3 = ImageIO.read(new File("DatosAuxiliares/Personajes/Guia 1.png"));
            ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.12), (int) (tamPant.height*0.55), Image.SCALE_SMOOTH));
            guia1.setIcon(icono3);

            BufferedImage imagen4 = ImageIO.read(new File("DatosAuxiliares/Personajes/Guia 2.png"));
            ImageIcon icono4 = new ImageIcon(imagen4.getScaledInstance((int) (tamPant.width*0.163), (int) (tamPant.height*0.58), Image.SCALE_SMOOTH));
            guia2.setIcon(icono4);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        flechaSala.setBounds((int) (tamPant.width*0.48), (int) (tamPant.height*0.84), (int) (tamPant.width*0.048), (int) (tamPant.height*0.124));
        flechaSala.setBackground(Color.red);
        flechaSala.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaSalidaActionPerformed(evt);
            }
        });
        flechaSala.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaSalaMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaSalaMouseExited(evt);
            }
        });
        flechaSala.setOpaque(true);
        flechaSala.setContentAreaFilled(false);
        flechaSala.setBorderPainted(false);
        flechaSala.setFocusPainted(false);
        flechaSala.setToolTipText("Sala Planta Alta");

        getContentPane().add(cajaTexto);
        getContentPane().add(flechaSala);


        guia1.setBounds((int) (tamPant.width*0.29), (int) (tamPant.height*0.39), (int) (tamPant.width*0.12), (int) (tamPant.height*0.55));
        guia1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                guia1ActionPerformed(evt);
            }

        });
        guia1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                guia1MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                guia1MouseExited(evt);
            }
        });
        guia1.setOpaque(true);
        guia1.setContentAreaFilled(false);
        guia1.setBorderPainted(false);
        guia1.setFocusPainted(false);
        getContentPane().add(guia1);


        guia2.setBounds((int) (tamPant.width*0.6), (int) (tamPant.height*0.38), (int) (tamPant.width*0.163), (int) (tamPant.height*0.58));
        guia2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                guia2ActionPerformed(evt);
            }

        });
        guia2.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                guia2MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                guia2MouseExited(evt);
            }
        });
        guia2.setOpaque(true);
        guia2.setContentAreaFilled(false);
        guia2.setBorderPainted(false);
        guia2.setFocusPainted(false);
        getContentPane().add(guia2);


        lugar.setText("Exposicion de Antiguedades");
        lugar.setOpaque(false);
        lugar.setForeground(Color.white);
        lugar.setFont(new Font("Segoe UI", 0, (int) (tamPant.width*0.035)));
        lugar.setBounds((int) (tamPant.width*0.03), (int) (tamPant.height*0.06), (int) (tamPant.width*0.45), (int) (tamPant.height*0.1));
        getContentPane().add(lugar);


        interfazUsuario.setBounds((int) (tamPant.width*0.55), (int) (tamPant.height*0.05), (int) (tamPant.width*0.45), (int) (tamPant.height*0.15));
        getContentPane().add(interfazUsuario);

        getContentPane().add(jLabel1);

        pack();
        timer2.scheduleAtFixedRate(tarea2, 0, 10);
    }

    private void guia2MouseExited(MouseEvent evt) {
        BufferedImage imagen4 = null;
        try {
            imagen4 = ImageIO.read(new File("DatosAuxiliares/Personajes/Guia 2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono4 = new ImageIcon(imagen4.getScaledInstance((int) (tamPant.width*0.163), (int) (tamPant.height*0.58), Image.SCALE_SMOOTH));
        guia2.setIcon(icono4);
    }

    private void guia2MouseEntered(MouseEvent evt) {
        BufferedImage imagen4 = null;
        try {
            imagen4 = ImageIO.read(new File("DatosAuxiliares/Personajes/Guia 2 BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono4 = new ImageIcon(imagen4.getScaledInstance((int) (tamPant.width*0.163), (int) (tamPant.height*0.58), Image.SCALE_SMOOTH));
        guia2.setIcon(icono4);
    }

    private void guia2ActionPerformed(ActionEvent evt) {
        if(Juego.getInstance().getPartidaActual().getEventos().getRonda()==0){
            ponerDialogosEstatico(crearDialogoNoDisponible(), 0);
        }else if (Juego.getInstance().getPartidaActual().getEventos().getRonda()==1 && !Juego.getInstance().getPartidaActual().getEventos().isGuia2Ya()){
            ponerDialogoGuia2();
            guia2.setVisible(false);
        }else
            ponerDialogosEstatico(crearDialogoYa(), 0);
    }


    private void guia2MouseClicked(){
        ponerDialogoGuia2();
    }
    private ArrayList<Dialogo> crearDialogoNoDisponible(){
        ArrayList<Dialogo> dialogosEstatic= new ArrayList<>();

        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");
        Dialogo d1= new Dialogo("(No creo que sea lo mas adecuado preguntar al guia secundario acerca del lugar, por ahora.)", "Detective", detective, true);
        Dialogo d2= new Dialogo("(Pospondre su interrogatorio hasta que haya terminado con los trabajadores mas importantes.)", "Detective", detective, true);

        dialogosEstatic.add(d1);
        dialogosEstatic.add(d2);
        return dialogosEstatic;
    }
    private ArrayList<Dialogo> crearDialogoYa(){
        ArrayList<Dialogo> dialogosEstatic = new ArrayList<>();

        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");
        Dialogo d1= new Dialogo("(Ya hable con el)", "Detective", detective, true);
        Dialogo d2= new Dialogo("(Deberia de enfocarme en recorrer el museo y buscar otras pistas.)", "Detective", detective, true);

        dialogosEstatic.add(d1);
        dialogosEstatic.add(d2);
        return dialogosEstatic;
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
                    EstatiMouseClicked2(dialogos, finalActual);
                }
            });

            EfectosEspeciales e = EfectosEspeciales.getInstancia();
            e.detenerSiEsNecesario();

            if (aux.getTextoImport().isEmpty() == false)
            {
                aux.guardarEnDiario();
            }

            cajaTexto.removeAll();
            cajaTexto.add(cT);
        }else {
            cajaTexto.removeAll();
        }

    }
    private void EstatiMouseClicked2(ArrayList<Dialogo> dialogos, int actual){
        ponerDialogosEstatico(dialogos, actual);
    }

    private void guia1MouseExited(MouseEvent evt) {
        BufferedImage imagen3 = null;
        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/Personajes/Guia 1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.12), (int) (tamPant.height*0.55), Image.SCALE_SMOOTH));
        guia1.setIcon(icono3);
    }

    private void guia1MouseEntered(MouseEvent evt) {
        BufferedImage imagen3 = null;
        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/Personajes/Guia 1 BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.12), (int) (tamPant.height*0.55), Image.SCALE_SMOOTH));
        guia1.setIcon(icono3);
    }

    private void guia1ActionPerformed(ActionEvent evt) {
        if(Juego.getInstance().getPartidaActual().getEventos().getRonda()==0 && !Juego.getInstance().getPartidaActual().getEventos().isGuia1Ya()){
            ponerDialogoGuia1();
            guia1.setVisible(false);
        }else ponerDialogosEstatico(crearDialogoYa(), 0);

    }

    public void ponerDialogoGuia1() {
        if(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(4).getNodoDialActual() == null || !(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(4).getArbolDial().nodeIsLeaf(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(4).getNodoDialActual()))) {
            if(!(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(4).getNodoDialActual()==null)){
                Dialogo actual = Juego.getInstance().getPartidaActual().getEscenariosMundo().get(4).getDialogoActual();
                if(!actual.getOpciones().isEmpty()){
                    OpcionesDialogos oD = new OpcionesDialogos(new JFrame(), true, actual.getOpciones());
                    oD.setBounds((int) (tamPant.width*0.28),(int) (tamPant.getHeight()*0.37), (int) (tamPant.width*0.48),(int) (tamPant.getHeight()*0.5));
                    oD.setVisible(true);
                }
            }
            Dialogo aux = Juego.getInstance().getPartidaActual().getEscenariosMundo().get(4).getDialogoSiguiente(UnionInterfaces.getInstance().getOpcionDialogo());
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono(),!aux.getTextoImport().isEmpty());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);
            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                  guia1MouseClicked(evt);
                }
            });
            if(UnionInterfaces.getInstance().getOpcionDialogo()!=1)
                UnionInterfaces.getInstance().setOpcionDialogo(1);

            EfectosEspeciales e = EfectosEspeciales.getInstancia();
            e.detenerSiEsNecesario();

            if (aux.getTextoImport().isEmpty() == false)
            {
                aux.guardarEnDiario();
            }
            cajaTexto.removeAll();
            cajaTexto.add(cT);
        }else {
            cajaTexto.removeAll();
            Juego.getInstance().getPartidaActual().cambiarEvento("guia 1", Juego.getInstance().getPartidaActual().getEscenariosMundo().get(4).getArbolDial());
            Juego.getInstance().getPartidaActual().getEventos().cambiarARonda1();
            guia1.setVisible(true);
        }
    }
    public void ponerDialogoGuia2() {
        if(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(8).getNodoDialActual() == null || !(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(8).getArbolDial().nodeIsLeaf(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(8).getNodoDialActual()))) {
            if(!(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(8).getNodoDialActual()==null)){
                Dialogo actual = Juego.getInstance().getPartidaActual().getEscenariosMundo().get(8).getDialogoActual();
                if(!actual.getOpciones().isEmpty()){
                    OpcionesDialogos oD = new OpcionesDialogos(new JFrame(), true, actual.getOpciones());
                    oD.setBounds((int) (tamPant.width*0.28),(int) (tamPant.getHeight()*0.37), (int) (tamPant.width*0.48),(int) (tamPant.getHeight()*0.5));
                    oD.setVisible(true);
                }
            }
            Dialogo aux = Juego.getInstance().getPartidaActual().getEscenariosMundo().get(8).getDialogoSiguiente(UnionInterfaces.getInstance().getOpcionDialogo());
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono(),!aux.getTextoImport().isEmpty());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);
            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    guia2MouseClicked();
                }
            });
            if(UnionInterfaces.getInstance().getOpcionDialogo()!=1)
                UnionInterfaces.getInstance().setOpcionDialogo(1);

            EfectosEspeciales e = EfectosEspeciales.getInstancia();
            e.detenerSiEsNecesario();

            if (aux.getTextoImport().isEmpty() == false)
            {
                aux.guardarEnDiario();
            }

            cajaTexto.removeAll();
            cajaTexto.add(cT);
        }else {
            cajaTexto.removeAll();
            Juego.getInstance().getPartidaActual().cambiarEvento("aman", Juego.getInstance().getPartidaActual().getEscenarios().get(8).getArbolDial());

            Juego.getInstance().getPartidaActual().getEventos().cambiarRonda2();
            guia2.setVisible(true);
        }
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    private void guia1MouseClicked(MouseEvent evt) {
        ponerDialogoGuia1();
    }
    private void flechaSalidaActionPerformed(ActionEvent evt) {
        if(!UnionInterfaces.getInstance().getUsandoFlecha()) {
            UnionInterfaces.getInstance().setUsandoFlecha(true);
            EfectosEspeciales e = EfectosEspeciales.getInstancia();
            e.efectoDePasos();

            Sala sala = new Sala();
            sala.setVisible(true);
            tarea2.cancel();
            timer.schedule(tarea, 500);
        }
    }
    private void flechaSalaMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha abajo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaSala.setIcon(icono);
    }

    private void flechaSalaMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha abajo BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaSala.setIcon(icono);
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
        java.awt.EventQueue.invokeLater(() -> new Sala2().setVisible(true));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel cajaTexto;
    private javax.swing.JButton flechaSala;
    private javax.swing.JLabel lugar;
    // End of variables declaration//GEN-END:variables
}
