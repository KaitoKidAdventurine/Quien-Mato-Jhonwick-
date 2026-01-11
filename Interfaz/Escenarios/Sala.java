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

public class Sala extends ModeloEscenario {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Sala.class.getName());
    private Dimension tamPant;
    private Timer timer;
    private TimerTask tarea;
    private InterfazUsuario interfazUsuario;
    private Timer timer2;
    private TimerTask tarea2;
    private JButton esposa;
    private JButton estatua;
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
                    timer.schedule(tarea, 700);
                    UnionInterfaces.getInstance().setCerrarVentana(false);
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
                dispose(); UnionInterfaces.getInstance().setUsandoFlecha(false);
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
        esposa = new JButton();
        estatua = new JButton();

        try {
            BufferedImage imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/Sala.png"));


            // Actualizacion de donde esta el Jugador
            Juego.getInstance().getPartidaActual().buscarEscenarioNombre("Sala Planta Alta");


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

            BufferedImage imagen3 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha abajo.png"));
            ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
            flechaEntradaDentro.setIcon(icono3);


            BufferedImage imagen4 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha izquierda.png"));
            ImageIcon icono4 = new ImageIcon(imagen4.getScaledInstance((int) (int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
            flechaPasillo2.setIcon(icono4);


            BufferedImage imagen5 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha derecha.png"));
            ImageIcon icono5 = new ImageIcon(imagen5.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
            flechaSala2.setIcon(icono5);

            BufferedImage imagen6 = ImageIO.read(new File("DatosAuxiliares/Personajes/Esposa.png"));
            ImageIcon icono6 = new ImageIcon(imagen6.getScaledInstance((int) (tamPant.width*0.125), (int) (tamPant.height*0.5), Image.SCALE_SMOOTH));
            esposa.setIcon(icono6);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        getContentPane().add(cajaTexto);
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




        esposa.setBounds((int) (tamPant.width*0.33), (int) (tamPant.height*0.4), (int) (tamPant.width*0.125), (int) (tamPant.height*0.5));
        esposa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                esposaActionPerformed(evt);
            }

        });
        esposa.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                esposaMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                esposaMouseExited(evt);
            }
        });
        esposa.setOpaque(true);
        esposa.setContentAreaFilled(false);
        esposa.setBorderPainted(false);
        esposa.setFocusPainted(false);
        getContentPane().add(esposa);


        estatua.setBounds((int) (tamPant.width*0.87), (int) (tamPant.height*0.34), (int) (tamPant.width*0.075), (int) (tamPant.height*0.475));
        estatua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                estatuaActionPerformed(evt);
            }

        });
        estatua.setContentAreaFilled(false);
        estatua.setBorderPainted(false);
        estatua.setFocusPainted(false);
        getContentPane().add(estatua);


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
        timer2.scheduleAtFixedRate(tarea2, 0, 5);
    }

    private void estatuaActionPerformed(ActionEvent evt) {
        if(Juego.getInstance().getPartidaActual().getEventos().isPoliciaSiguiendo()){
            esposa.setVisible(false);
            ponerDialogoEstatua();
        }


    }

    private void esposaMouseExited(MouseEvent evt) {
        BufferedImage imagen6 = null;
        try {
            imagen6 = ImageIO.read(new File("DatosAuxiliares/Personajes/Esposa.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono6 = new ImageIcon(imagen6.getScaledInstance((int) (tamPant.width*0.125), (int) (tamPant.height*0.5), Image.SCALE_SMOOTH));
        esposa.setIcon(icono6);
    }

    private void esposaMouseEntered(MouseEvent evt) {
        BufferedImage imagen6 = null;
        try {
            imagen6 = ImageIO.read(new File("DatosAuxiliares/Personajes/Esposa BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono6 = new ImageIcon(imagen6.getScaledInstance((int) (tamPant.width*0.125), (int) (tamPant.height*0.5), Image.SCALE_SMOOTH));
        esposa.setIcon(icono6);
    }

    private void esposaActionPerformed(ActionEvent evt) {
        int variable =Juego.getInstance().getPartidaActual().getEventos().getRonda();
        switch (variable){
            case 0, 1, 2:
                ponerDialogosEstatico(crearDialogoNoDisponible(), 0);
                break;
            case 3:
                if(!Juego.getInstance().getPartidaActual().getEventos().isEsposaYa()) {
                    Juego.getInstance().getPartidaActual().getEscenariosMundo().get(6).restaurarDialogo();
                    ponerDialogoEsposa();
                    esposa.setVisible(false);
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
        Dialogo d1= new Dialogo("(No creo que sea lo mas adecuado preguntar a la esposa del dueño acerca del lugar, por ahora.)", "Detective", detective, true);
        Dialogo d2= new Dialogo("(Pospondre su interrogatorio hasta que haya terminado con los trabajadores mas importantes.)", "Detective", detective, true);

        dialogosEsta.add(d1);
        dialogosEsta.add(d2);
        return dialogosEsta;
    }
    private ArrayList<Dialogo> crearDialogoYa(){
        ArrayList<Dialogo> dialogoEstatic = new ArrayList<>();

        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");
        Dialogo d1= new Dialogo("(Ya hable con ella)", "Detective", detective, true);
        Dialogo d2= new Dialogo("(Deberia de enfocarme en recorrer el museo y buscar otras pistas.)", "Detective", detective, true);

        dialogoEstatic.add(d1);
        dialogoEstatic.add(d2);
        return dialogoEstatic;
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
    private void EstatiMouseClicked(ArrayList<Dialogo> dialogos, int actual){
        ponerDialogosEstatico(dialogos, actual);

    }
    public void ponerDialogoEsposa() {
        if(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(6).getNodoDialActual() == null || !(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(6).getArbolDial().nodeIsLeaf(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(6).getNodoDialActual()))) {
            if(!(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(6).getNodoDialActual()==null)){
                Dialogo actual = Juego.getInstance().getPartidaActual().getEscenariosMundo().get(6).getDialogoActual();
                if(!actual.getOpciones().isEmpty()){
                    OpcionesDialogos oD = new OpcionesDialogos(new JFrame(), true, actual.getOpciones());
                    oD.setBounds((int) (tamPant.width*0.28),(int) (tamPant.getHeight()*0.37), (int) (tamPant.width*0.48),(int) (tamPant.getHeight()*0.5));
                    oD.setVisible(true);
                }
            }
            Dialogo aux = Juego.getInstance().getPartidaActual().getEscenariosMundo().get(6).getDialogoSiguiente(UnionInterfaces.getInstance().getOpcionDialogo());
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono(),!aux.getTextoImport().isEmpty());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);
            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    esposaMouseClicked(evt);
                }
            });

            if(UnionInterfaces.getInstance().getOpcionDialogo()!=1)
                UnionInterfaces.getInstance().setOpcionDialogo(1);

            EfectosEspeciales e = EfectosEspeciales.getInstancia();
            e.detenerSiEsNecesario();

            if (!aux.getTextoImport().isEmpty())
            {
                aux.guardarEnDiario();
            }

            cajaTexto.removeAll();
            cajaTexto.add(cT);
        }else {
            cajaTexto.removeAll();
            Juego.getInstance().getPartidaActual().cambiarEvento("esposa", Juego.getInstance().getPartidaActual().getEscenariosMundo().get(6).getArbolDial());
            Juego.getInstance().getPartidaActual().getEventos().cambiarRonda4();
            if(!Juego.getInstance().getPartidaActual().getEventos().isEsposaYa())
                ponerDialogoInconcluso();
            esposa.setVisible(true);
        }
    }

    private void ponerDialogoInconcluso() {
        ponerDialogosEstatico(crearDialogoEsposaTodavia(), 0);
    }

    private ArrayList<Dialogo> crearDialogoEsposaTodavia(){
        ArrayList<Dialogo> dialogosConserje = new ArrayList<>();

        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");
        Dialogo d1= new Dialogo("(No esta contanto todo lo que sabe, o por lo menos no lo que queria excuchar)", "Detective", detective, true);
        Dialogo d2= new Dialogo("(Deberia de volver a hablar con ella y preguntarle otras cosas)", "Detective", detective, true);

        dialogosConserje.add(d1);
        dialogosConserje.add(d2);
        return dialogosConserje;
    }
    public void ponerDialogoEstatua() {
        if(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(9).getNodoDialActual() == null || !(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(9).getArbolDial().nodeIsLeaf(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(9).getNodoDialActual()))) {
            if(!(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(9).getNodoDialActual()==null)){
                Dialogo actual = Juego.getInstance().getPartidaActual().getEscenariosMundo().get(9).getDialogoActual();
                if(!actual.getOpciones().isEmpty()){
                    OpcionesDialogos oD = new OpcionesDialogos(new JFrame(), true, actual.getOpciones());
                    oD.setBounds((int) (tamPant.width*0.28),(int) (tamPant.getHeight()*0.37), (int) (tamPant.width*0.48),(int) (tamPant.getHeight()*0.5));
                    oD.setVisible(true);
                }
            }
            Dialogo aux = Juego.getInstance().getPartidaActual().getEscenariosMundo().get(9).getDialogoSiguiente(UnionInterfaces.getInstance().getOpcionDialogo());
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono(),!aux.getTextoImport().isEmpty());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);
            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    estatuaMouseClicked(evt);
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
            Juego.getInstance().getPartidaActual().getEventos().cambiarRonda5();
            esposa.setVisible(true);
        }

    }

    private void estatuaMouseClicked(MouseEvent evt) {
        ponerDialogoEstatua();
    }

    private void esposaMouseClicked(MouseEvent evt) {
        ponerDialogoEsposa();
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    private void flechaEntradaActionPerformed(ActionEvent evt) {
        if(!UnionInterfaces.getInstance().getUsandoFlecha()) {
            UnionInterfaces.getInstance().setUsandoFlecha(true);
            EfectosEspeciales e = EfectosEspeciales.getInstancia();
            e.efectoDePasos();

            Recepcion entradaD = new Recepcion();
            entradaD.setVisible(true);
            tarea2.cancel();
            timer.schedule(tarea, 500);
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
    private void flechaSala2ActionPerformed(ActionEvent evt) {
        if(!UnionInterfaces.getInstance().getUsandoFlecha()) {
            UnionInterfaces.getInstance().setUsandoFlecha(true);
            EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDePasos();

        Sala2 sala2 = new Sala2();
        sala2.setVisible(true);
        tarea2.cancel();
        timer.schedule(tarea, 500);
        }
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