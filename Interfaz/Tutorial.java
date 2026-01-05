package Interfaz;

import DatosAuxiliaresLogica.UnionInterfaces;
import Interfaz.Escenarios.Entrada;
import Interfaz.InterfazJugador.CuadroTexto;
import Interfaz.InterfazJugador.OpcionesDialogos;
import Interfaz.Menu.MenuPrincipal;
import Interfaz.MiniJuego.MinijuegoInterfaz;
import DatosAuxiliaresLogica.EfectosEspeciales;
import Logica.*;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.general.GeneralTree;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tutorial extends JFrame {
    private static final Logger logger = Logger.getLogger(Tutorial.class.getName());
    private final Dimension tamPant;
    private Escenario tutorialParte1;
    private Escenario tutorialParte2;
    private Escenario tutorialParte3;
    private Escenario tutorialParte4;
    private Escenario tutorialParte5;
    private Escenario tutorialParte6;
    private Escenario tutorialParte7;
    private Escenario tutorialParte8;
    private Escenario tutorialParte9;
    private Escenario tutorialParte10;
    private Timer timer;
    private TimerTask tarea;
    private Timer timer2;
    private TimerTask tarea2;
    private JButton botonSaltar;
    private Timer salida;
    private  TimerTask salidaTarea;
    private static boolean entro = false;



    public Tutorial() {
        Reproductor reproductor = Reproductor.getInstancia();
        reproductor.cambiarMusicaNombre("Galeria Silenciosa");


        tamPant = Toolkit.getDefaultToolkit().getScreenSize();

        tutorialParte1 = new Escenario("Tutorial Parte 1", "Punto inicial de partida", true);
        tutorialParte2 = new Escenario("Tutorial Parte 2", "Punto inicial de partida", true);
        tutorialParte3 = new Escenario("Tutorial Parte 3", "Punto inicial de partida", true);
        tutorialParte4 = new Escenario("Tutorial Parte 4", "Punto inicial de partida", true);
        tutorialParte5 = new Escenario("Tutorial Parte 5", "Punto inicial de partida", true);
        tutorialParte6 = new Escenario("Tutorial Parte 6", "Punto inicial de partida", true);
        tutorialParte7 = new Escenario("Tutorial Parte 7", "Punto inicial de partida", true);
        tutorialParte8 = new Escenario("Tutorial Parte 8", "Punto inicial de partida", true);
        tutorialParte9 = new Escenario("Tutorial Parte 9", "Punto inicial de partida", true);
        tutorialParte10 = new Escenario("Tutorial Parte 10", "Punto inicial de partida", true);

        crearDialogosParte1();

        timer = new Timer();
        tarea = new TimerTask() {
            @Override
            public void run() {
                ponerDialogoParte2();
            }
        };
        timer2 = new Timer();
        tarea2 = new TimerTask() {
            @Override
            public void run() {
                dispose();
            }
        };
        salida = new Timer();
        salidaTarea = new TimerTask() {
            @Override
            public void run() {
                if(UnionInterfaces.getInstance().getCerrarVentana()){
                    MenuPrincipal menu = new MenuPrincipal();
                    menu.setVisible(true);
                    UnionInterfaces.getInstance().setCerrarVentana(false);
                    dispose();
                    tarea2.cancel();
                }
            }
        };
        initComponents();
    }



    private void initComponents() {


        // Efectos especiales
        EfectosEspeciales e = EfectosEspeciales.getInstancia();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fondo = new JLabel();
        cajaTexto = new JPanel();
        botonSaltar = new JButton("Saltar al Juego");

        BufferedImage imagenCursor =null;
        try {
            imagenCursor =  ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Cursor.png"));
        } catch (IOException te) {
            throw new RuntimeException(te);
        }
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(imagenCursor, new Point(20, 0), "Cursor detective");
        setCursor(cursor);

        setMinimumSize(tamPant);
        setUndecorated(true);
        setPreferredSize(tamPant);
        getContentPane().setLayout(null);
        setBackground(new Color(0, 0, 0, 0));
        cajaTexto.setOpaque(false);
        cajaTexto.setBounds(0, 0, tamPant.width, tamPant.height);
        cajaTexto.setLayout(null);

        // Configurar botón saltar
        botonSaltar.setBounds((int)(tamPant.width*0.85), (int)(tamPant.height*0.05), (int)(tamPant.width*0.12), (int)(tamPant.height*0.06));
        botonSaltar.setFont(new Font("Segoe UI", Font.BOLD, (int)(tamPant.width*0.012)));
        botonSaltar.setBackground(new Color(200, 50, 50));
        botonSaltar.setForeground(Color.WHITE);
        botonSaltar.setBorderPainted(false);
        botonSaltar.setFocusPainted(false);
        botonSaltar.addActionListener(event -> saltarAlJuego());

        fondo.setFocusable(false);
        fondo.setMaximumSize(tamPant);
        fondo.setMinimumSize(tamPant);
        fondo.setPreferredSize(tamPant);
        fondo.setBounds(0, 0, tamPant.width, tamPant.height);

        getContentPane().add(botonSaltar);
        getContentPane().add(cajaTexto);
        getContentPane().add(fondo);

        ponerFondoParte1(0);
        ponerDialogoParte1();
        crearDialogosParte2();
        pack();
        salida.scheduleAtFixedRate(salidaTarea, 0, 20);
    }

    public void ponerDialogoParte1() {
        if(tutorialParte1.getNodoDialActual() == null || !(tutorialParte1.getArbolDial().nodeIsLeaf(tutorialParte1.getNodoDialActual()))) {

            Dialogo aux = tutorialParte1.getDialogoSiguiente(1);
            int nivelActualDial = tutorialParte1.getArbolDial().nodeLevel(tutorialParte1.getNodoDialActual());
            if (aux.getTexto().contains("- Ring ring ring ring ring.-")) {
                EfectosEspeciales e = EfectosEspeciales.getInstancia();
                e.efectoDeTelefonoResiviendoLlamda();
            }

            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);
            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    cTParte1MouseClicked(evt);
                }
            });
            cT.setBackground(new Color(0, 0, 0, 0));
            cajaTexto.removeAll();
            cajaTexto.add(cT);

            if(nivelActualDial == 1 || nivelActualDial == 3 || nivelActualDial == 5 || nivelActualDial == 9 || nivelActualDial == 17 || nivelActualDial == 34
                    || nivelActualDial == 43 || nivelActualDial == 61 || nivelActualDial == 76 || nivelActualDial == 82|| nivelActualDial == 85) {
                ponerFondoParte1(nivelActualDial);
                getContentPane().revalidate();
                getContentPane().repaint();
            }
        }

        else {

            cajaTexto.removeAll();
            cajaTexto.setVisible(false);
            crearMinijuego();
            timer.schedule(tarea, 5000);

        }
        getContentPane().revalidate();
        getContentPane().repaint();



    }

    public void ponerDialogoParte2() {

        if(tutorialParte2.getNodoDialActual() == null || !(tutorialParte2.getArbolDial().nodeIsLeaf(tutorialParte2.getNodoDialActual()))) {
            if(!(tutorialParte2.getNodoDialActual()==null))
            {

                if(!entro)
                {
                    entro = true;
                    Reproductor reproductor = Reproductor.getInstancia();
                    reproductor.cambiarMusicaNombre("Galeria Silenciosa");
                }


                Dialogo actual = tutorialParte2.getDialogoActual();
                if(!actual.getOpciones().isEmpty()){
                    OpcionesDialogos oD = new OpcionesDialogos(new JFrame(), true, actual.getOpciones());
                    oD.setBounds((int) (tamPant.width*0.28),(int) (tamPant.getHeight()*0.37), (int) (tamPant.width*0.48),(int) (tamPant.getHeight()*0.5));
                    oD.setVisible(true);
                }
            }
            Dialogo aux = tutorialParte2.getDialogoSiguiente(UnionInterfaces.getInstance().getOpcionDialogo());
            int nivelActualDial = tutorialParte2.getArbolDial().nodeLevel(tutorialParte2.getNodoDialActual());
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);

            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    cTParte2MouseClicked(evt);
                }
            });
            if(UnionInterfaces.getInstance().getOpcionDialogo()!=1)
                UnionInterfaces.getInstance().setOpcionDialogo(1);

            cajaTexto.removeAll();
            cajaTexto.add(cT);

            if(nivelActualDial == 0) {
                ponerFondoParte2(nivelActualDial);
                getContentPane().revalidate();
                getContentPane().repaint();
            }

        }else{
            ponerDialogoParte3();
        }
    }

    public void ponerDialogoParte3() {

        if(tutorialParte3.getNodoDialActual() == null || !(tutorialParte3.getArbolDial().nodeIsLeaf(tutorialParte3.getNodoDialActual()))) {
            if(!(tutorialParte3.getNodoDialActual()==null)){
                Dialogo actual = tutorialParte3.getDialogoActual();
                if(!actual.getOpciones().isEmpty()){
                    OpcionesDialogos oD = new OpcionesDialogos(new JFrame(), true, actual.getOpciones());
                    oD.setBounds((int) (tamPant.width*0.28),(int) (tamPant.getHeight()*0.37), (int) (tamPant.width*0.48),(int) (tamPant.getHeight()*0.5));
                    oD.setVisible(true);
                }
            }
            Dialogo aux = tutorialParte3.getDialogoSiguiente(UnionInterfaces.getInstance().getOpcionDialogo());
            int nivelActualDial = tutorialParte3.getArbolDial().nodeLevel(tutorialParte3.getNodoDialActual());
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);

            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    cTParte3MouseClicked(evt);
                }
            });
            if(UnionInterfaces.getInstance().getOpcionDialogo()!=1)
                UnionInterfaces.getInstance().setOpcionDialogo(1);

            cajaTexto.removeAll();
            cajaTexto.add(cT);

            if(nivelActualDial ==5 || nivelActualDial ==7 || nivelActualDial ==9 ) {
                ponerFondoParte3(nivelActualDial);
                getContentPane().revalidate();
                getContentPane().repaint();
            }
        }else {
            ponerDialogoParte4();
        }
    }
    public void ponerDialogoParte4() {

        if(tutorialParte4.getNodoDialActual() == null || !(tutorialParte4.getArbolDial().nodeIsLeaf(tutorialParte4.getNodoDialActual()))) {
            if(!(tutorialParte4.getNodoDialActual()==null)){
                Dialogo actual = tutorialParte4.getDialogoActual();
                if(!actual.getOpciones().isEmpty()){
                    OpcionesDialogos oD = new OpcionesDialogos(new JFrame(), true, actual.getOpciones());
                    oD.setBounds((int) (tamPant.width*0.28),(int) (tamPant.getHeight()*0.37), (int) (tamPant.width*0.48),(int) (tamPant.getHeight()*0.5));
                    oD.setVisible(true);
                }
            }
            Dialogo aux = tutorialParte4.getDialogoSiguiente(UnionInterfaces.getInstance().getOpcionDialogo());
            int nivelActualDial = tutorialParte4.getArbolDial().nodeLevel(tutorialParte4.getNodoDialActual());
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);

            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    cTParte4MouseClicked(evt);
                }
            });
            if(UnionInterfaces.getInstance().getOpcionDialogo()!=1)
                UnionInterfaces.getInstance().setOpcionDialogo(1);

            cajaTexto.removeAll();
            cajaTexto.add(cT);

        }else {
          ponerDialogoParte5();
        }
    }


    public void ponerDialogoParte5() {

        if(tutorialParte5.getNodoDialActual() == null || !(tutorialParte5.getArbolDial().nodeIsLeaf(tutorialParte5.getNodoDialActual()))) {
            if(!(tutorialParte5.getNodoDialActual()==null)){
                Dialogo actual = tutorialParte5.getDialogoActual();
                if(!actual.getOpciones().isEmpty()){

                    // Dato clave
                    Juego.getInstance().getPartidaActual().getJugador().getDiario().agregarDialogoImportante("Guia 1", "El guía conoce el museo como la palma de su mano.");

                    OpcionesDialogos oD = new OpcionesDialogos(new JFrame(), true, actual.getOpciones());
                    oD.setBounds((int) (tamPant.width*0.28),(int) (tamPant.getHeight()*0.37), (int) (tamPant.width*0.48),(int) (tamPant.getHeight()*0.5));
                    oD.setVisible(true);
                }
            }
            Dialogo aux = tutorialParte5.getDialogoSiguiente(UnionInterfaces.getInstance().getOpcionDialogo());
            int nivelActualDial = tutorialParte5.getArbolDial().nodeLevel(tutorialParte5.getNodoDialActual());
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);

            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    cTParte5MouseClicked(evt);
                }
            });
            if(UnionInterfaces.getInstance().getOpcionDialogo()!=1)
                UnionInterfaces.getInstance().setOpcionDialogo(1);

            cajaTexto.removeAll();
            cajaTexto.add(cT);

        }else {
            ponerDialogoParte6();
        }
    }
    public void ponerDialogoParte6() {

        if(tutorialParte6.getNodoDialActual() == null || !(tutorialParte6.getArbolDial().nodeIsLeaf(tutorialParte6.getNodoDialActual()))) {
            if(!(tutorialParte6.getNodoDialActual()==null)){
                Dialogo actual = tutorialParte6.getDialogoActual();
                if(!actual.getOpciones().isEmpty()){
                    OpcionesDialogos oD = new OpcionesDialogos(new JFrame(), true, actual.getOpciones());
                    oD.setBounds((int) (tamPant.width*0.28),(int) (tamPant.getHeight()*0.37), (int) (tamPant.width*0.48),(int) (tamPant.getHeight()*0.5));
                    oD.setVisible(true);
                }
            }
            Dialogo aux = tutorialParte6.getDialogoSiguiente(UnionInterfaces.getInstance().getOpcionDialogo());
            int nivelActualDial = tutorialParte6.getArbolDial().nodeLevel(tutorialParte6.getNodoDialActual());
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);

            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    cTParte6MouseClicked(evt);
                }
            });
            if(UnionInterfaces.getInstance().getOpcionDialogo()!=1)
                UnionInterfaces.getInstance().setOpcionDialogo(1);

            cajaTexto.removeAll();
            cajaTexto.add(cT);

        }else {
            ponerDialogoParte7();
        }
    }
    public void ponerDialogoParte7() {

        if(tutorialParte7.getNodoDialActual() == null || !(tutorialParte7.getArbolDial().nodeIsLeaf(tutorialParte7.getNodoDialActual()))) {
            if(!(tutorialParte7.getNodoDialActual()==null)){
                Dialogo actual = tutorialParte7.getDialogoActual();
                if(!actual.getOpciones().isEmpty()){
                    OpcionesDialogos oD = new OpcionesDialogos(new JFrame(), true, actual.getOpciones());
                    oD.setBounds((int) (tamPant.width*0.28),(int) (tamPant.getHeight()*0.37), (int) (tamPant.width*0.48),(int) (tamPant.getHeight()*0.5));
                    oD.setVisible(true);
                }
            }
            Dialogo aux = tutorialParte7.getDialogoSiguiente(UnionInterfaces.getInstance().getOpcionDialogo());
            int nivelActualDial = tutorialParte7.getArbolDial().nodeLevel(tutorialParte7.getNodoDialActual());
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);

            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    cTParte7MouseClicked(evt);
                }
            });
            if(UnionInterfaces.getInstance().getOpcionDialogo()!=1)
                UnionInterfaces.getInstance().setOpcionDialogo(1);

            cajaTexto.removeAll();
            cajaTexto.add(cT);

        }else {
            ponerDialogoParte8();
        }
    }
    public void ponerDialogoParte8() {

        if(tutorialParte8.getNodoDialActual() == null || !(tutorialParte8.getArbolDial().nodeIsLeaf(tutorialParte8.getNodoDialActual()))) {
            if(!(tutorialParte8.getNodoDialActual()==null)){
                Dialogo actual = tutorialParte8.getDialogoActual();
                if(!actual.getOpciones().isEmpty()){
                    OpcionesDialogos oD = new OpcionesDialogos(new JFrame(), true, actual.getOpciones());
                    oD.setBounds((int) (tamPant.width*0.28),(int) (tamPant.getHeight()*0.37), (int) (tamPant.width*0.48),(int) (tamPant.getHeight()*0.5));
                    oD.setVisible(true);
                }
            }
            Dialogo aux = tutorialParte8.getDialogoSiguiente(UnionInterfaces.getInstance().getOpcionDialogo());
            int nivelActualDial = tutorialParte8.getArbolDial().nodeLevel(tutorialParte8.getNodoDialActual());
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);

            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    cTParte8MouseClicked(evt);
                }
            });
            if(UnionInterfaces.getInstance().getOpcionDialogo()!=1)
                UnionInterfaces.getInstance().setOpcionDialogo(1);

            cajaTexto.removeAll();
            cajaTexto.add(cT);

        }else {
            ponerDialogoParte9();
        }
    }
    public void ponerDialogoParte9() {

        if(tutorialParte9.getNodoDialActual() == null || !(tutorialParte9.getArbolDial().nodeIsLeaf(tutorialParte9.getNodoDialActual()))) {
            if(!(tutorialParte9.getNodoDialActual()==null)){
                Dialogo actual = tutorialParte9.getDialogoActual();
                if(!actual.getOpciones().isEmpty()){
                    OpcionesDialogos oD = new OpcionesDialogos(new JFrame(), true, actual.getOpciones());
                    oD.setBounds((int) (tamPant.width*0.28),(int) (tamPant.getHeight()*0.37), (int) (tamPant.width*0.48),(int) (tamPant.getHeight()*0.5));
                    oD.setVisible(true);
                }
            }
            Dialogo aux = tutorialParte9.getDialogoSiguiente(UnionInterfaces.getInstance().getOpcionDialogo());
            int nivelActualDial = tutorialParte9.getArbolDial().nodeLevel(tutorialParte9.getNodoDialActual());
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);

            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    cTParte9MouseClicked(evt);
                }
            });
            if(UnionInterfaces.getInstance().getOpcionDialogo()!=1)
                UnionInterfaces.getInstance().setOpcionDialogo(1);

            cajaTexto.removeAll();
            cajaTexto.add(cT);


        }else {
           ponerDialogoParte10();
        }
    }
    public void ponerDialogoParte10() {

        if(tutorialParte10.getNodoDialActual() == null || !(tutorialParte10.getArbolDial().nodeIsLeaf(tutorialParte10.getNodoDialActual()))) {
            if(!(tutorialParte10.getNodoDialActual()==null)){
                Dialogo actual = tutorialParte10.getDialogoActual();
                if(!actual.getOpciones().isEmpty()){
                    OpcionesDialogos oD = new OpcionesDialogos(new JFrame(), true, actual.getOpciones());
                    oD.setBounds((int) (tamPant.width*0.28),(int) (tamPant.getHeight()*0.37), (int) (tamPant.width*0.48),(int) (tamPant.getHeight()*0.5));
                    oD.setVisible(true);
                }
            }
            Dialogo aux = tutorialParte10.getDialogoSiguiente(UnionInterfaces.getInstance().getOpcionDialogo());
            int nivelActualDial = tutorialParte10.getArbolDial().nodeLevel(tutorialParte10.getNodoDialActual());
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);

            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    cTParte10MouseClicked(evt);
                }
            });
            if(UnionInterfaces.getInstance().getOpcionDialogo()!=1)
                UnionInterfaces.getInstance().setOpcionDialogo(1);

            cajaTexto.removeAll();
            cajaTexto.add(cT);

        }else {
            iniciarMundo();
        }
    }

    public void ponerFondoParte1(int nivelActualDial){
        try {
            BufferedImage imagen = null;

            switch (nivelActualDial) {
                case 0:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Cinematica/Escena 1.jpg"));
                    break;
                case 1:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Cinematica/1763958158881.jpg"));
                    break;
                case 3:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Cinematica/Escena 3.jpg"));
                    break;
                case 5:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Cinematica/Escena 4.jpg"));
                    break;
                case 9:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Cinematica/Escena 5.png"));
                    break;
                case 17:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Cinematica/Escena 6.jpg"));
                    break;
                case 34:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Cinematica/Escena Auto.jpg"));
                    EfectosEspeciales e = EfectosEspeciales.getInstancia();
                    e.efectoDeLluvia();
                    break;
                case 43:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/entrada por afuera.jpg"));
                    EfectosEspeciales e1 = EfectosEspeciales.getInstancia();
                    e1.detenerEfecto();
                    break;
                case 61:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/entrada por dentro.jpg"));
                    EfectosEspeciales e2 = EfectosEspeciales.getInstancia();
                    e2.detenerEfecto();
                    break;
                case 76:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/pasillo 1.jpg"));
                    break;
                case 82:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/pasillo 3.jpg"));
                    break;
                case 85:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/Oficina Victima.png"));
                    break;

                default:
                    break;
            }

            ImageIcon icono = new ImageIcon(imagen != null ? imagen.getScaledInstance(tamPant.width, tamPant.height, Image.SCALE_SMOOTH) : null);
            fondo.setIcon(icono); // NOI18N


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void ponerFondoParte2(int nivelActualDial) {
        try {
            BufferedImage imagen = null;

            switch (nivelActualDial) {
                case 0:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/Oficina Victima.png"));
                    break;
                default:
                    break;
            }

            ImageIcon icono = new ImageIcon(Objects.requireNonNull(imagen).getScaledInstance(tamPant.width, tamPant.height, Image.SCALE_SMOOTH));
            fondo.setIcon(icono);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void ponerFondoParte3(int nivelActualDial) {
        try {
            BufferedImage imagen = null;

            switch (nivelActualDial) {
                case 5:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/pasillo 3.jpg"));
                    break;
                case 7:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/entrada por dentro.jpg"));
                    break;
                case 9:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/sala.jpg"));
                    break;

            }

            ImageIcon icono = new ImageIcon(Objects.requireNonNull(imagen).getScaledInstance(tamPant.width, tamPant.height, Image.SCALE_SMOOTH));
            fondo.setIcon(icono);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void crearDialogosParte1() {
        ImageIcon policia = new ImageIcon("DatosAuxiliares/Personajes/Policia.png");
        ImageIcon dueno = new ImageIcon("DatosAuxiliares/Personajes/Dueño.png");
        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");
        ImageIcon nada = new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png");

        Dialogo d1= new Dialogo("Ha sido una semana larga. Casi siento que no lo logro.  ", "", nada, true);
        Dialogo d2= new Dialogo("Creo que me merezco un pequeño descanso. Es sabado en la noche despues de todo, no creo que ocurra ningun problema. ", "", nada, true);
        Dialogo d3= new Dialogo("Voy a prender el televisor un momento, en la oficina me comentaron que hoy iban a pasar una pelicula muy buena a esta hora. ", "", nada, true);
        Dialogo d4= new Dialogo("Pondre el canal 34 a revisar si es verdad. Vere los primeros 10 minutos, si no me gusta como empieza no me va a interesar como termine.  ", "", nada, true);
        Dialogo d5= new Dialogo("Todavia necesito ponerme al dia con One Piece. No puedo desperdiciar el tiempo", "", nada, true);
        Dialogo d6= new Dialogo("-50 minutos despues.-", "", nada, true);
        Dialogo d7= new Dialogo("Ok. He de decir que la pelicula en realidad esta bastante buena", "", nada, true);
        Dialogo d8= new Dialogo("La tematica de que sean superheroes esta gastada, pero no me esperaba que de repente hubiera una invasion alienigena y un viajero temporal robotico llegara a salvar el dia, mientras que el segundo grupo fuera por ahoi cazando mitos con un perro.", "", nada, true);
        Dialogo d9= new Dialogo("No me queda nada mas que tragarme mi orgullo, esto parece que promete para una saga entera co 5 spin-off", "", nada, true);
        Dialogo d10= new Dialogo("Interrumpimos la programacion planificada para reportar una noticia de ultima hora.", "Presentadora", nada, true);
        Dialogo d11= new Dialogo("Genial. Justo cuando estaba la mejor parte. Solo espero que me lo pongan justo donde se quedo. No tolelaria perderme el final", "", nada, true);
        Dialogo d12= new Dialogo("Se reporta que en el famoso museo Logrenveich, creo que se pronuncia asi, se ha encontrado el cuerpo sin vida de uno de sus empleados.", "Presentadorea", nada, true);
        Dialogo d13= new Dialogo("¿Eso no es el museo en donde se iba a llevar a cabo la exposicion del Magnamen rojo?.", "", nada, true);
        Dialogo d14= new Dialogo("Con su exposicion mas ambiciosa la semana que viene, en donde hace presencia de su mas reciente adquisicion, El Magnamen rojo, el tercer rubi mas grande del mundo. El museo pasa por un momento delicado cuanto menos", "Presentadora", nada, true);
        Dialogo d15= new Dialogo("No se por que, pero presiento que de alguna forma me va a afectar esto.", "", nada, true);
        Dialogo d16= new Dialogo("- Ring ring ring ring ring.-", "", nada, true);
        Dialogo d17= new Dialogo("El mensajero de las malas noticias.", "", nada, true);
        Dialogo d18= new Dialogo("Espero que aprovecharas tus vacaciones", "Capitan", nada, true);
        Dialogo d19= new Dialogo("Algo asi.", "", nada, true);
        Dialogo d20= new Dialogo("¿Estas ocupado?.", "Capitan", nada, true);
        Dialogo d21= new Dialogo("La verdad es que si, ando en pijama, viendo una pelicula con mi taza favorita", "", nada, true);
        Dialogo d22= new Dialogo("Perfecto, estas libre entonces.", "Capitan", nada, true);
        Dialogo d23= new Dialogo("No he dicho eso.", "", nada, true);
        Dialogo d24= new Dialogo("Te necesitamos en el museo Logrimbver.", "Capitan", nada, true);
        Dialogo d25= new Dialogo("¿Tiene que ver con el cuerpo hallado?.", "", nada, true);
        Dialogo d26= new Dialogo("¿Como te enteraste?", "Capitan", nada, true);
        Dialogo d27= new Dialogo("Lo acaban de decir por el televisor", "", nada, true);
        Dialogo d28= new Dialogo("Mejor, ya estas enterado de la situacion entonces. Tienes 20 minutos para llegar.", "Capitan", nada, true);
        Dialogo d29= new Dialogo("Pero nunca estuve de acuerdo para ir en primer lugar.", "", nada, true);
        Dialogo d30= new Dialogo("-Biiiippppp.-", "", nada, true);
        Dialogo d31= new Dialogo("Demonios.", "", nada, true);
        Dialogo d32= new Dialogo("Parece que se acabo el descanso.", "", nada, true);
        Dialogo d33= new Dialogo("Y yo que ya estaba a punto de terminar la pelicula. Supongo que sera en otra ocasion.", "", nada, true);
        Dialogo d34= new Dialogo("Pero primero necesito ponerme una ropa adecuada para el trabajo.", "", nada, true);
        Dialogo d35= new Dialogo("Se reportan que para el dia de hoy habra fuertes lluvias en algunas regiones de la ciudad.","Emisora", nada, true);
        Dialogo d36= new Dialogo("No me digas. Recien me entero", "", nada, true);
        Dialogo d37= new Dialogo("Mira que tener que levantarme de mi comodo sillon solo para tener que investigar un caso a estas horas de la noche. Ser el mejor en lo que hago no siempre es bueno.", "", nada, true);
        Dialogo d38= new Dialogo("Ahora por lo que todos estaban esperando, la cancion You are like like a rainbow.", "Emisora", nada, true);
        Dialogo d39= new Dialogo("¿Quien le pone esos titulos horrorosos a las canciones?.", "", nada, true);
        Dialogo d40= new Dialogo("Oh baby, you are like a rainbow. Is hurting in my vains like hurricain. Your body, your booty and your eyes are like a bomb in myn heart.", "Cantante", nada, true);
        Dialogo d41= new Dialogo("Parece que no solo no saben poner nombres, tampoco saben componer ni cantar.", "", nada, true);
        Dialogo d42= new Dialogo("Voy a apagarlo. No se como los jovenes soportan este tipo de canciones hoy en dia.", "", nada, true);
        Dialogo d43= new Dialogo("-15 minutos despues-", "", nada, true);
        Dialogo d44= new Dialogo("Hola detective. Espero que haya descansado bien.", "Policia", policia, true);
        Dialogo d45= new Dialogo("Lo justo como para no volverme loco", "Detective", detective, true);
        Dialogo d46= new Dialogo("Si. Este trabajo tiende a hacer eso con la gente. Lo que vemos aqui puede quebrar a mas de uno.", "Policia", policia, true);
        Dialogo d47= new Dialogo("Y eso que todavia no has visto la mitad de lo que este trabajo tiene para ofrecer.", "Detective", detective, true);
        Dialogo d48= new Dialogo("Ni siquiera quiero imaginarlo.", "Policia", policia, true);
        Dialogo d49= new Dialogo("La verdad que no. ", "Detective", detective, true);
        Dialogo d50= new Dialogo("Y bien. ¿Con que exactamente estamos lidiando hoy? Escuche algo antes de venir, pero solamente algunos detalles.", "Detective", detective, true);
        Dialogo d51= new Dialogo("Hace aproximadamente una hora se encontro el cuerpo sin vida de Elliot Majader. Un economico encargado de las finanzas de museo.", "Policia", policia, true);
        Dialogo d52= new Dialogo("¿Que sabemos de el?", "Detective", detective, true);
        Dialogo d53= new Dialogo("No mucho. Una persona solitaria, sin amigos, no hablaba con nadie en el museo, sin pareja, probablemente sin amigos. Vivia en un apartamento pequeño en un rincon de la ciudad.", "Policia", policia, true);
        Dialogo d54= new Dialogo("¿Algun familiar cercano?", "Detective", detective, true);
        Dialogo d55= new Dialogo("Ninguno hasta donde sabemos.", "Policia", policia, true);
        Dialogo d56= new Dialogo("Interesante. La victima es una sombra, del tipo del que nadie se daria cuenta que desaparecio.", "Detective", detective, true);
        Dialogo d57= new Dialogo("Excepto que en este caso encontraron el cuerpo ", "Policia", policia, true);
        Dialogo d58= new Dialogo("¿Quien lo encontro?", "Detective", detective, true);
        Dialogo d59= new Dialogo("El guardia de seguridad. Se percato de que en un sector fallaban las camaras y decidio investigar la razon. Y bueno, encontro el cadaver de Elliot.", "Policia", policia, true);
        Dialogo d60= new Dialogo("Guiame hacia alli", "Detective", detective, true);
        Dialogo d61= new Dialogo("Claro, seria por este camino.", "Policia", policia, true);
        Dialogo d62= new Dialogo("Dime lo que han encontrado en la escena del crimen", "Detective", detective, true);
        Dialogo d63= new Dialogo("No mucho, el unico forense que queda trabajando a esta hora esta ocupado en otro caso.", "Policia", policia, true);
        Dialogo d64= new Dialogo("¿Y el resto?", "Detective", detective, true);
        Dialogo d65= new Dialogo("En su casa probablemente. ", "Policia", policia, true);
        Dialogo d66= new Dialogo("Por supuesto, ellos se pueden quedar en su casa descansando, pero yo tengo que venir a revisar una escena del crimen a esta hora de la noche.", "Detective", detective, true);
        Dialogo d67= new Dialogo("El jefe comento que usted era mas que suficiente para este caso. Comento que es hora de poner en practica esas habilidades tan especiales que dice tener.", "Policia", policia, true);
        Dialogo d68= new Dialogo("Razon no le falta. Soy el mejor en lo que hago. Pero eso no niega de que cuando termine exigire un dia de descanso en compensacion.", "Detective", detective, true);
        Dialogo d69= new Dialogo("Tambien menciono que diria algo como eso.", "Policia", policia, true);
        Dialogo d70= new Dialogo("No se preocupe detective. El caso esta practicamente cerrado. ", "Dueño", dueno, true);
        Dialogo d71= new Dialogo("¿Disculpa?", "Detective", detective, true);
        Dialogo d72= new Dialogo("Disculpado. Simplemente no hay necesidad de investigaciones posteriores. El pobre no pudo soportar la presion y escogio el camino facil.", "Dueño", dueno, true);
        Dialogo d73= new Dialogo("¿Acaso insinua que se suicido?", "Detective", detective, true);
        Dialogo d74= new Dialogo("No lo digo yo, las evidencias son claras.", "Dueño", dueno, true);
        Dialogo d75= new Dialogo("Eso lo decido yo, y por favor deje de molestar, interrumpe mi trabajo. Vamonos oficial", "Detective", detective, true);
        Dialogo d76= new Dialogo("Vera lo que digo cuando lo vea. ", "Dueño", dueno, true);
        Dialogo d77= new Dialogo("Podra ser excentrico y pesado, pero probablemente tenga razon. La escena apunta a un suicidio causado por el estres.", "Policia", policia, true);
        Dialogo d78= new Dialogo("Poco probable, los suicidios generados por trabajo rara vez ocurren en el centro laboral. Ademas segun lo que me comentaste su personalidad era mas bien reservada, dudo mucho que quisiera irse de forma tan llamativa.", "Detective", detective, true);
        Dialogo d79= new Dialogo("¿Y si se equivoca?", "Policia", policia, true);
        Dialogo d80= new Dialogo("Pocas veces lo hago.", "Detective", detective, true);
        Dialogo d81= new Dialogo("Eso he oido. Su ratio de casos resueltos es el mas alto en el cuartel. ", "Policia", policia, true);
        Dialogo d82= new Dialogo("Sin contar en los que he ayudado a cerrar como asesor.", "Detective", detective, true);
        Dialogo d83= new Dialogo("¿Es aqui?.", "Detective", detective, true);
        Dialogo d84= new Dialogo("Si, detras de esta puerta.", "Policia", policia, true);
        Dialogo d85= new Dialogo("Bueno, entonces vamos a entrar. ", "Detective", detective, true);
        Dialogo d86= new Dialogo("¿Por cierto, traiste el equipo para realizar el trabajo forense?.", "Detective", detective, true);
        Dialogo d87= new Dialogo("Si, aqui lo tengo", "Policia", policia, true);
        Dialogo d88= new Dialogo("Perfecto.", "Detective", detective, true);
        Dialogo d89= new Dialogo("Es hora de hacer mi trabajo. Mientras tanto, organiza a todo el personal del centro en la recepcion.", "Detective", detective, true);
        Dialogo d90= new Dialogo("Como ordene.", "Policia", policia, true);
        Dialogo d91= new Dialogo("Entonces solo quedamos tu y yo amigos. Dime como fue que terminaste asi.", "Detective", detective, true);


        BinaryTreeNode<Dialogo> node1 = new BinaryTreeNode<>(d1);
        BinaryTreeNode<Dialogo> node2 = new BinaryTreeNode<>(d2);
        BinaryTreeNode<Dialogo> node3 = new BinaryTreeNode<>(d3);
        BinaryTreeNode<Dialogo> node4 = new BinaryTreeNode<>(d4);
        BinaryTreeNode<Dialogo> node5 = new BinaryTreeNode<>(d5);
        BinaryTreeNode<Dialogo> node6 = new BinaryTreeNode<>(d6);
        BinaryTreeNode<Dialogo> node7 = new BinaryTreeNode<>(d7);
        BinaryTreeNode<Dialogo> node8 = new BinaryTreeNode<>(d8);
        BinaryTreeNode<Dialogo> node9 = new BinaryTreeNode<>(d9);
        BinaryTreeNode<Dialogo> node10 = new BinaryTreeNode<>(d10);
        BinaryTreeNode<Dialogo> node11 = new BinaryTreeNode<>(d11);
        BinaryTreeNode<Dialogo> node12 = new BinaryTreeNode<>(d12);
        BinaryTreeNode<Dialogo> node13 = new BinaryTreeNode<>(d13);
        BinaryTreeNode<Dialogo> node14 = new BinaryTreeNode<>(d14);
        BinaryTreeNode<Dialogo> node15 = new BinaryTreeNode<>(d15);
        BinaryTreeNode<Dialogo> node16 = new BinaryTreeNode<>(d16);
        BinaryTreeNode<Dialogo> node17 = new BinaryTreeNode<>(d17);
        BinaryTreeNode<Dialogo> node18 = new BinaryTreeNode<>(d18);
        BinaryTreeNode<Dialogo> node19 = new BinaryTreeNode<>(d19);
        BinaryTreeNode<Dialogo> node20 = new BinaryTreeNode<>(d20);
        BinaryTreeNode<Dialogo> node21 = new BinaryTreeNode<>(d21);
        BinaryTreeNode<Dialogo> node22 = new BinaryTreeNode<>(d22);
        BinaryTreeNode<Dialogo> node23 = new BinaryTreeNode<>(d23);
        BinaryTreeNode<Dialogo> node24 = new BinaryTreeNode<>(d24);
        BinaryTreeNode<Dialogo> node25 = new BinaryTreeNode<>(d25);
        BinaryTreeNode<Dialogo> node26 = new BinaryTreeNode<>(d26);
        BinaryTreeNode<Dialogo> node27 = new BinaryTreeNode<>(d27);
        BinaryTreeNode<Dialogo> node28 = new BinaryTreeNode<>(d28);
        BinaryTreeNode<Dialogo> node29 = new BinaryTreeNode<>(d29);
        BinaryTreeNode<Dialogo> node30 = new BinaryTreeNode<>(d30);
        BinaryTreeNode<Dialogo> node31 = new BinaryTreeNode<>(d31);
        BinaryTreeNode<Dialogo> node32 = new BinaryTreeNode<>(d32);
        BinaryTreeNode<Dialogo> node33 = new BinaryTreeNode<>(d33);
        BinaryTreeNode<Dialogo> node34 = new BinaryTreeNode<>(d34);
        BinaryTreeNode<Dialogo> node35 = new BinaryTreeNode<>(d35);
        BinaryTreeNode<Dialogo> node36 = new BinaryTreeNode<>(d36);
        BinaryTreeNode<Dialogo> node37 = new BinaryTreeNode<>(d37);
        BinaryTreeNode<Dialogo> node38 = new BinaryTreeNode<>(d38);
        BinaryTreeNode<Dialogo> node39 = new BinaryTreeNode<>(d39);
        BinaryTreeNode<Dialogo> node40 = new BinaryTreeNode<>(d40);
        BinaryTreeNode<Dialogo> node41 = new BinaryTreeNode<>(d41);
        BinaryTreeNode<Dialogo> node42 = new BinaryTreeNode<>(d42);
        BinaryTreeNode<Dialogo> node43 = new BinaryTreeNode<>(d43);
        BinaryTreeNode<Dialogo> node44 = new BinaryTreeNode<>(d44);
        BinaryTreeNode<Dialogo> node45 = new BinaryTreeNode<>(d45);
        BinaryTreeNode<Dialogo> node46 = new BinaryTreeNode<>(d46);
        BinaryTreeNode<Dialogo> node47 = new BinaryTreeNode<>(d47);
        BinaryTreeNode<Dialogo> node48 = new BinaryTreeNode<>(d48);
        BinaryTreeNode<Dialogo> node49 = new BinaryTreeNode<>(d49);
        BinaryTreeNode<Dialogo> node50 = new BinaryTreeNode<>(d50);
        BinaryTreeNode<Dialogo> node51 = new BinaryTreeNode<>(d51);
        BinaryTreeNode<Dialogo> node52 = new BinaryTreeNode<>(d52);
        BinaryTreeNode<Dialogo> node53 = new BinaryTreeNode<>(d53);
        BinaryTreeNode<Dialogo> node54 = new BinaryTreeNode<>(d54);
        BinaryTreeNode<Dialogo> node55 = new BinaryTreeNode<>(d55);
        BinaryTreeNode<Dialogo> node56 = new BinaryTreeNode<>(d56);
        BinaryTreeNode<Dialogo> node57 = new BinaryTreeNode<>(d57);
        BinaryTreeNode<Dialogo> node58 = new BinaryTreeNode<>(d58);
        BinaryTreeNode<Dialogo> node59 = new BinaryTreeNode<>(d59);
        BinaryTreeNode<Dialogo> node60 = new BinaryTreeNode<>(d60);
        BinaryTreeNode<Dialogo> node61 = new BinaryTreeNode<>(d61);
        BinaryTreeNode<Dialogo> node62 = new BinaryTreeNode<>(d62);
        BinaryTreeNode<Dialogo> node63 = new BinaryTreeNode<>(d63);
        BinaryTreeNode<Dialogo> node64 = new BinaryTreeNode<>(d64);
        BinaryTreeNode<Dialogo> node65 = new BinaryTreeNode<>(d65);
        BinaryTreeNode<Dialogo> node66 = new BinaryTreeNode<>(d66);
        BinaryTreeNode<Dialogo> node67 = new BinaryTreeNode<>(d67);
        BinaryTreeNode<Dialogo> node68 = new BinaryTreeNode<>(d68);
        BinaryTreeNode<Dialogo> node69 = new BinaryTreeNode<>(d69);
        BinaryTreeNode<Dialogo> node70 = new BinaryTreeNode<>(d70);
        BinaryTreeNode<Dialogo> node71 = new BinaryTreeNode<>(d71);
        BinaryTreeNode<Dialogo> node72 = new BinaryTreeNode<>(d72);
        BinaryTreeNode<Dialogo> node73 = new BinaryTreeNode<>(d73);
        BinaryTreeNode<Dialogo> node74 = new BinaryTreeNode<>(d74);
        BinaryTreeNode<Dialogo> node75 = new BinaryTreeNode<>(d75);
        BinaryTreeNode<Dialogo> node76 = new BinaryTreeNode<>(d76);
        BinaryTreeNode<Dialogo> node77 = new BinaryTreeNode<>(d77);
        BinaryTreeNode<Dialogo> node78 = new BinaryTreeNode<>(d78);
        BinaryTreeNode<Dialogo> node79 = new BinaryTreeNode<>(d79);
        BinaryTreeNode<Dialogo> node80 = new BinaryTreeNode<>(d80);
        BinaryTreeNode<Dialogo> node81 = new BinaryTreeNode<>(d81);
        BinaryTreeNode<Dialogo> node82 = new BinaryTreeNode<>(d82);
        BinaryTreeNode<Dialogo> node83 = new BinaryTreeNode<>(d83);
        BinaryTreeNode<Dialogo> node84 = new BinaryTreeNode<>(d84);
        BinaryTreeNode<Dialogo> node85 = new BinaryTreeNode<>(d85);
        BinaryTreeNode<Dialogo> node86 = new BinaryTreeNode<>(d86);
        BinaryTreeNode<Dialogo> node87 = new BinaryTreeNode<>(d87);
        BinaryTreeNode<Dialogo> node88 = new BinaryTreeNode<>(d88);
        BinaryTreeNode<Dialogo> node89 = new BinaryTreeNode<>(d89);
        BinaryTreeNode<Dialogo> node90 = new BinaryTreeNode<>(d90);
        BinaryTreeNode<Dialogo> node91 = new BinaryTreeNode<>(d91);


        GeneralTree<Dialogo> auxTree = new GeneralTree<>();

        auxTree.insertNode(node1, null);
        auxTree.insertNode(node2, node1);
        auxTree.insertNode(node3, node2);
        auxTree.insertNode(node4, node3);
        auxTree.insertNode(node5, node4);
        auxTree.insertNode(node6, node5);
        auxTree.insertNode(node7, node6);
        auxTree.insertNode(node8, node7);
        auxTree.insertNode(node9, node8);
        auxTree.insertNode(node10, node9);
        auxTree.insertNode(node11, node10);
        auxTree.insertNode(node12, node11);
        auxTree.insertNode(node13, node12);
        auxTree.insertNode(node14, node13);
        auxTree.insertNode(node15, node14);
        auxTree.insertNode(node16, node15);
        auxTree.insertNode(node17, node16);
        auxTree.insertNode(node18, node17);
        auxTree.insertNode(node19, node18);
        auxTree.insertNode(node20, node19);
        auxTree.insertNode(node21, node20);
        auxTree.insertNode(node22, node21);
        auxTree.insertNode(node23, node22);
        auxTree.insertNode(node24, node23);
        auxTree.insertNode(node25, node24);
        auxTree.insertNode(node26, node25);
        auxTree.insertNode(node27, node26);
        auxTree.insertNode(node28, node27);
        auxTree.insertNode(node29, node28);
        auxTree.insertNode(node30, node29);
        auxTree.insertNode(node31, node30);
        auxTree.insertNode(node32, node31);
        auxTree.insertNode(node33, node32);
        auxTree.insertNode(node34, node33);
        auxTree.insertNode(node35, node34);
        auxTree.insertNode(node36, node35);
        auxTree.insertNode(node37, node36);
        auxTree.insertNode(node38, node37);
        auxTree.insertNode(node39, node38);
        auxTree.insertNode(node40, node39);
        auxTree.insertNode(node41, node40);
        auxTree.insertNode(node42, node41);
        auxTree.insertNode(node43, node42);
        auxTree.insertNode(node44, node43);
        auxTree.insertNode(node45, node44);
        auxTree.insertNode(node46, node45);
        auxTree.insertNode(node47, node46);
        auxTree.insertNode(node48, node47);
        auxTree.insertNode(node49, node48);
        auxTree.insertNode(node50, node49);
        auxTree.insertNode(node51, node50);
        auxTree.insertNode(node52, node51);
        auxTree.insertNode(node53, node52);
        auxTree.insertNode(node54, node53);
        auxTree.insertNode(node55, node54);
        auxTree.insertNode(node56, node55);
        auxTree.insertNode(node57, node56);
        auxTree.insertNode(node58, node57);
        auxTree.insertNode(node59, node58);
        auxTree.insertNode(node60, node59);
        auxTree.insertNode(node61, node60);
        auxTree.insertNode(node62, node61);
        auxTree.insertNode(node63, node62);
        auxTree.insertNode(node64, node63);
        auxTree.insertNode(node65, node64);
        auxTree.insertNode(node66, node65);
        auxTree.insertNode(node67, node66);
        auxTree.insertNode(node68, node67);
        auxTree.insertNode(node69, node68);
        auxTree.insertNode(node70, node69);
        auxTree.insertNode(node71, node70);
        auxTree.insertNode(node72, node71);
        auxTree.insertNode(node73, node72);
        auxTree.insertNode(node74, node73);
        auxTree.insertNode(node75, node74);
        auxTree.insertNode(node76, node75);
        auxTree.insertNode(node77, node76);
        auxTree.insertNode(node78, node77);
        auxTree.insertNode(node79, node78);
        auxTree.insertNode(node80, node79);
        auxTree.insertNode(node81, node80);
        auxTree.insertNode(node82, node81);
        auxTree.insertNode(node83, node82);
        auxTree.insertNode(node84, node83);
        auxTree.insertNode(node85, node84);
        auxTree.insertNode(node86, node85);
        auxTree.insertNode(node87, node86);
        auxTree.insertNode(node88, node87);
        auxTree.insertNode(node89, node88);
        auxTree.insertNode(node90, node89);
        auxTree.insertNode(node91, node90);

        tutorialParte1.setArbolDial(auxTree);
    }

    public void crearDialogosParte2() {
        ImageIcon policia = new ImageIcon("DatosAuxiliares/Personajes/Policia.png");
        ImageIcon dueno = new ImageIcon("DatosAuxiliares/Personajes/Dueño.png");
        ImageIcon secretaria  = new ImageIcon("DatosAuxiliares/Personajes/Secretaria.png");
        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");
        ImageIcon esposa = new ImageIcon("DatosAuxiliares/Personajes/Esposa.png");
        ImageIcon guia = new ImageIcon("DatosAuxiliares/Personajes/Guia 1.png");
        ImageIcon seguridad = new ImageIcon("DatosAuxiliares/Personajes/Seguridad.png");
        ImageIcon nada = new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png");
        ImageIcon limpieza = new ImageIcon("DatosAuxiliares/Personajes/Conserje.png");
        ImageIcon guia2 = new ImageIcon("DatosAuxiliares/Personajes/Guia 2.png");

        Dialogo d92 = new Dialogo("Umm. Eso fue interesante.", "Detective", detective, true);
        Dialogo d93 = new Dialogo("(Debido a la naturaleza de la victima uno pensaria que seria bastante mas organizado, la mayoria de los economistas tienden a ser perfeccionistas o al menos un poco pulcros, dejandome pensando en solo dos posibles respuestas a tanto desorden.)", "Detective", detective, true);
        Dialogo d94 = new Dialogo("(O bien es una excepcion a la regla o hubo algun tipo de forcejeo. Ambas opciones son igual de fascinantes, solo que la ultima parece mas interesante.) ", "Detective", detective, true);
        Dialogo d95 = new Dialogo("(De igual forma el cuerpo me parece inusual, postrado en el suelo en lugar de quitarse la vida en un lugar mas comodo y simple como una silla, simplemente no tiene sentido para mi.)", "Detective", detective, true);
        Dialogo d96 = new Dialogo("(Hay muchas cosas en este caso que no cuadran.)", "Detective", detective, true);
        Dialogo d97 = new Dialogo("- Knock kcnok. -", "", nada, true);
        Dialogo d98 = new Dialogo("¿Quien es?.", "Detective", detective, true);
        Dialogo d99 = new Dialogo("Soy yo, he venido a decirle que todos los trabajadores del centro se encuentran reunidos en la recepción.", "Policia", policia, true);

        // --- Primer árbol de decisión ---
        Dialogo decisionInicio = new Dialogo("La mayoria ni siquiera parecia haberse enterado de que se habia encontrado el cuerpo del economico. Tuve que ponerlos al tanto de lo que estaba pasando.", "Policia", policia, true);
        decisionInicio.setOpciones(new LinkedList<>(Arrays.asList("¿Como reaccionaron?", "No debiste de haber hecho eso.")));

        Dialogo respuestaA = new Dialogo("Sorprendidos diria, honestamente no sabria decir. Eso si, ninguno parecia especialmente adolorido", "Policia", policia, true);
        Dialogo contRA = new Dialogo("Es mas de lo que me esperaría dado el carácter solitario del económico. Apuesto a que mas de la mitad ni siquiera se saben su nombre.", "Detective", detective, true);
        Dialogo contRA1 = new Dialogo("No creo que sea buena idea hacer apuestas acerca de un caso que involucre un cadaver. ", "Policia", policia, true);
        Dialogo contRA2 = new Dialogo("Le quitas lo divertido a la vida. Pero puede que tengas razon, no es hora de juegos, llevame hacia los testigos.", "Detective", detective, true);

        Dialogo respuestaB = new Dialogo("Debiste de dejarme ese trabajo a mi, hay mucha información que se puede extraer de la reacción de una persona a diferentes sucesos. Informacion que estoy acostumbrado a leer.", "Detective", detective, true);
        Dialogo contRB = new Dialogo("Disculpe. Lo tendré en cuenta para la próxima vez.", "Policia", policia, true);
        Dialogo contRB1 = new Dialogo("Lo hecho hecho está. No te preocupes por ello, todavia tengo mis metodos para hacerlos cantar.", "Detective", detective, true);
        Dialogo contRB2 = new Dialogo("Lo dice como si estuviera seguro de que fue un asesinato.", "Policia", policia, true);
        Dialogo contRB3 = new Dialogo("No puedo afirmar nada todavia, pero si puedo decir que este caso tiene demasiadas cosas extrañas.", "Detective", detective, true);
        Dialogo contRB4 = new Dialogo("Pero como todavia no tengo ninguna evidencia no hay nada determinante por ahora. Necesitamos reunir pruebas solidas. Por ahora vayamos hacia los testigos para reunir unos cuantos testimonios.", "Detective", detective, true);


        // Conexiones

        Dialogo d101 = new Dialogo("Si, por supuesto, actualmente todos se encuentran en la sala norte. Se que me dijo que los pusiera en la recepcion, pero considere que existia un riesgo real de que en caso de ser un asesinato el culpable huyera.", "Policia", policia, true);
        Dialogo d102 = new Dialogo("Buen juicio, si resulta en una verdadera excena del crimen es mejor dificultarles la salida. Aunque para la proxima ocasion consultalo conmigo primero, despues de todo soy tu jefe.", "Detective", detective, true);
        Dialogo d103 = new Dialogo("Lo tendre en consideracion.", "Policia", policia, true);
        Dialogo d104 = new Dialogo("Perfecto, ahora guiame hacia donde se encuentran los trabajadores del museo.", "Detective", detective, true);
        Dialogo d105 = new Dialogo("Por aqui.", "Policia", policia, true);
        Dialogo d106 = new Dialogo("¿Cuantos se encontraban en el museo a esta hora?", "Detective", detective, true);
        Dialogo d107 = new Dialogo("Segun pude verificar el museo tiene en total 28 trabajadores, de los cuales se quedaron hasta tarde hoy 7.", "Policia", policia, true);
        Dialogo d108 = new Dialogo("No es un mal numero para trabajar. ¿Algo mas que deba de saber si hay alguien que frecuenta el edificio o algo asi?.", "Detective", detective, true);
        Dialogo d109 = new Dialogo("No sabria decir, aunque muchos mencionan la presencia de un vagabundo en un callejon cercano que a menudo se le podia ver interactuando con el economico.", "Policia", policia, true);
        Dialogo d110 = new Dialogo("Eso será útil. ", "Detective", detective, true);
        Dialogo d110q = new Dialogo("Ah, detective, justo el hombre que estaba esperando. Por favor puede poner orden la sala, todos andan preocupados de que un asesino pueda andar por ahi. Por favor expliqueles que lo que le paso al economico fue por decision propia. ", "Dueño", dueno, true);
        Dialogo d110w = new Dialogo("Pondre orden en la sala, pero la posibilidad de un asesinato es alta. Por favor agente, manda las fotografias de la escena del crimen al laboratorio y pide un analisis inmediato. ", "Detective", detective, true);
        Dialogo d110e = new Dialogo("A sus ordenes.", "Policia", policia, true);
        Dialogo d110r = new Dialogo("Ahora, volviendo a lo que estabamos. Usted es el dueño del museo, y por tanto el más afectado por lo sucedido en él. Empezaré el interrogatorio por usted. Digo, si no le es de ningun problema. ", "Detective", detective, true);
        Dialogo d111 = new Dialogo("No.....heem, no, no hay nigun problema. Todo bien, nada de lo que preocuparse... he, puede preguntar lo que quiera.", "Dueño", dueno, true);

        Dialogo decisionDueno = new Dialogo("¿Que desea saber?", "Dueño", dueno, true);
        decisionDueno.setOpciones(new LinkedList<>(Arrays.asList("¿En donde se encontraba en el momento de la tragedia?", "¿El economico tenia algun enemigo?", "¿Como era su relación con el económico?")));

        Dialogo dRama1Due = new Dialogo("Estaba en mi oficina, trabajando en algunos documentos que tengo atrasados. No he dejado la habitacion en todo el dia.", "Dueño", dueno, true);
        Dialogo dRama1DuePr1 = new Dialogo("¿Desea saber algo mas?", "Dueño", dueno, true);
        dRama1DuePr1.setOpciones(new LinkedList<>(Arrays.asList( "¿El economico tenia algun enemigo?", "¿Como era su relación con el económico?")));

        Dialogo dRama1Due1 = new Dialogo("Hasta donde sé, era una persona tranquila, no creo que nadie lo llegara odiar. Era del tipo de gente que no sabes que esta a tu lado, hasta que empieza a hablar. No creo que nadie de aqui supiera su nombre para empezar.", "Dueño", dueno, true);
        Dialogo dRama1Due2 = new Dialogo("Ya entiendo, una ultima pregunta. ¿Como era su relación con el económico?", "Detective", detective, true);
        Dialogo dRama1Due3 = new Dialogo("Ninguna, personas como el no merecen que gaste mi tiempo en ellas.", "Dueño", dueno, true);
        Dialogo dRama1Due4 = new Dialogo("Me imagine que dirias algo asi. Gracias por su cooperacion, puede retirarse.", "Detective", detective, true);
        Dialogo dRama1Due5 = new Dialogo("Apurese en cerrar el caso, cada minuto que pierdo con ustedes es mi billetera el que lo paga.", "Dueño", dueno, true);
        Dialogo dRama1Due6 = new Dialogo("(Que persona mas rara.)", "Detective", detective, true);

        Dialogo dRama1Due1$1 = new Dialogo("Ninguna, personas como el no merecen que gaste mi tiempo en ellas", "Dueño", dueno, true);
        Dialogo dRama1Due2$1 = new Dialogo("Me imagine que dirias algo asi. Solo me queda una pregunta. ¿El economico tenia algun enemigo?", "Detective", detective, true);
        Dialogo dRama1Due3$1 = new Dialogo("Hasta donde sé, era una persona tranquila, no creo que nadie lo llegara odiar. Era del tipo de gente que no sabes que esta a tu lado, hasta que empieza a hablar. No creo que nadie de aqui supiera su nombre para empezar.", "Dueño", dueno, true);
        Dialogo dRama1Due4$1 = new Dialogo("Ya entiendo. Gracias por su cooperacion", "Detective", detective, true);
        Dialogo dRama1Due5$1 = new Dialogo("Apurese en cerrar el caso, cada minuto que pierdo con ustedes es mi billetera la que lo paga.", "Dueño", dueno, true);
        Dialogo dRama1Due6$1 = new Dialogo("(Que persona mas rara.)", "Detective", detective, true);

        Dialogo dRama2Due = new Dialogo("Hasta donde sé, era una persona tranquila, no creo que nadie lo llegara odiar. Era del tipo de gente que no sabes que esta a tu lado, hasta que empieza a hablar. No creo que nadie de aqui supiera su nombre para empezar.", "Dueño", dueno, true);
        Dialogo dRama2Due1 = new Dialogo("Asi que nadie de por aqui tenia una relacion cercana a el.", "Detective", detective, true);
        Dialogo dRama2Due2 = new Dialogo("Se podria decir. ¿Desea saber algo mas?", "Dueño", dueno, true);
        dRama2Due2.setOpciones(new LinkedList<>(Arrays.asList( "¿En donde se encontraba en el momento de la tragedia?", "¿Como era su relación con el económico?")));

        Dialogo dRama2Due3 = new Dialogo("Estaba en mi oficina, trabajando en algunos documentos que tengo atrasados. No he dejado la habitacion en todo el dia.", "Dueño", dueno, true);
        Dialogo dRama2Due4 = new Dialogo("Ya entiendo, una ultima pregunta. ¿Como era su relación con el económico?", "Detective", detective, true);
        Dialogo dRama2Due5 = new Dialogo("Ninguna, personas como el no merecen que gaste mi tiempo en ellas.", "Dueño", dueno, true);
        Dialogo dRama2Due6 = new Dialogo("Me imagine que dirias algo asi. Gracias por su cooperacion, puede retirarse.", "Detective", detective, true);
        Dialogo dRama2Due7 = new Dialogo("Apurese en cerrar el caso, cada minuto que pierdo con ustedes es mi billetera la que lo paga.", "Dueño", dueno, true);
        Dialogo dRama2Due8 = new Dialogo("(Que persona mas rara.)", "Detective", detective, true);

        Dialogo dRama2Due3$1 = new Dialogo("Ninguna, personas como el no merecen que gaste mi tiempo en ellas.", "Dueño", dueno, true);
        Dialogo dRama2Due4$1 = new Dialogo("Me imagine que dirias algo asi. Una ultima pregunta para terminar. ¿En donde se encontraba en el momento de la tragedia?", "Detective", detective, true);
        Dialogo dRama2Due5$1 = new Dialogo("Estaba en mi oficina, trabajando en algunos documentos que tengo atrasados. No he dejado la habitacion en todo el dia.", "Dueño", dueno, true);
        Dialogo dRama2Due6$1 = new Dialogo("Ya entiendo, una ultima pregunta.Gracias por su cooperacion, puede retirarse.", "Detective", detective, true);
        Dialogo dRama2Due7$1 = new Dialogo("Apurese en cerrar el caso, cada minuto que pierdo con ustedes es mi billetera la que lo paga.", "Dueño", dueno, true);
        Dialogo dRama2Due8$1 = new Dialogo("(Que persona mas rara.)", "Detective", detective, true);

        Dialogo dRama3Due = new Dialogo("Ninguna, personas como el no merecen que gaste mi tiempo en ellas.", "Dueño", dueno, true);
        Dialogo dRama3Due1 = new Dialogo("Me imagine que diria algo como eso.", "Detective", detective, true);
        Dialogo dRama3Due2 = new Dialogo("¿Desea saber algo mas?", "Dueño", dueno, true);
        dRama3Due2.setOpciones(new LinkedList<>(Arrays.asList( "¿En donde se encontraba en el momento de la tragedia?", "¿El economico tenia algun enemigo?")));

        Dialogo dRama3Due3 = new Dialogo("Estaba en mi oficina, trabajando en algunos documentos que tengo atrasados. No he dejado la habitacion en todo el dia.", "Dueño", dueno, true);
        Dialogo dRama3Due4 = new Dialogo("Ya entiendo, una ultima pregunta. ¿El economico tenia algun  enemigo?", "Detective", detective, true);
        Dialogo dRama3Due5 = new Dialogo("Hasta donde sé, era una persona tranquila, no creo que nadie lo llegara odiar. Era del tipo de gente que no sabes que esta a tu lado, hasta que empieza a hablar. No creo que nadie de aqui supiera su nombre para empezar.", "Dueño", dueno, true);
        Dialogo dRama3Due6 = new Dialogo("Asi que nadie de por aqui tenia una relacion cercana a el.", "Detective", detective, true);
        Dialogo dRama3Due7 = new Dialogo("Se podria decir. ¿Desea saber algo mas?", "Dueño", dueno, true);
        Dialogo dRama3Due8 = new Dialogo("No, con eso ya tengo suficiente. Gracias por su cooperacion, puede retirarse", "Detective", detective, true);
        Dialogo dRama3Due9 = new Dialogo("Apurese en cerrar el caso, cada minuto que pierdo con ustedes es mi billetera la que lo paga.", "Dueño", dueno, true);
        Dialogo dRama3Due10 = new Dialogo("(Que persona mas rara.)", "Detective", detective, true);

        Dialogo dRama3Due3$1 = new Dialogo("Hasta donde sé, era una persona tranquila, no creo que nadie lo llegara odiar. Era del tipo de gente que no sabes que esta a tu lado, hasta que empieza a hablar. No creo que nadie de aqui supiera su nombre para empezar.", "Dueño", dueno, true);
        Dialogo dRama3Due4$1 = new Dialogo("Asi que nadie de por aqui tenia una relacion cercana a el.", "Detective", detective, true);
        Dialogo dRama3Due5$1 = new Dialogo("Se podria decir. ¿Desea saber algo mas?", "Dueño", dueno, true);
        Dialogo dRama3Due6$1 = new Dialogo("Si. ¿Donde se encontraba en el momento de la tragedia?", "Detective", detective, true);
        Dialogo dRama3Due7$1 = new Dialogo("Estaba en mi oficina, trabajando en algunos documentos que tengo atrasados. No he dejado la habitacion en todo el dia.", "Dueño", dueno, true);
        Dialogo dRama3Due8$1 = new Dialogo("Ya entiendo, una ultima pregunta.Gracias por su cooperacion, puede retirarse.", "Detective", detective, true);
        Dialogo dRama3Due9$1 = new Dialogo("Apurese en cerrar el caso, cada minuto que pierdo con ustedes es mi billetera la que lo paga.", "Dueño", dueno, true);
        Dialogo dRama3Due10$1 = new Dialogo("(Que persona mas rara.)", "Detective", detective, true);

        // Conexiones

        BinaryTreeNode<Dialogo> node92 = new BinaryTreeNode<>(d92);
        BinaryTreeNode<Dialogo> node93 = new BinaryTreeNode<>(d93);
        BinaryTreeNode<Dialogo> node94 = new BinaryTreeNode<>(d94);
        BinaryTreeNode<Dialogo> node95 = new BinaryTreeNode<>(d95);
        BinaryTreeNode<Dialogo> node96 = new BinaryTreeNode<>(d96);
        BinaryTreeNode<Dialogo> node97 = new BinaryTreeNode<>(d97);
        BinaryTreeNode<Dialogo> node98 = new BinaryTreeNode<>(d98);
        BinaryTreeNode<Dialogo> node99 = new BinaryTreeNode<>(d99);

        BinaryTreeNode<Dialogo> node300 = new BinaryTreeNode<>(decisionInicio);

        BinaryTreeNode<Dialogo> node301 = new BinaryTreeNode<>(respuestaA);
        BinaryTreeNode<Dialogo> nodeConRA = new BinaryTreeNode<>(contRA);
        BinaryTreeNode<Dialogo> nodeConRA1 = new BinaryTreeNode<>(contRA1);
        BinaryTreeNode<Dialogo> nodeConRA2 = new BinaryTreeNode<>(contRA2);

        BinaryTreeNode<Dialogo> node302 = new BinaryTreeNode<>(respuestaB);
        BinaryTreeNode<Dialogo> nodeConRB = new BinaryTreeNode<>(contRB);
        BinaryTreeNode<Dialogo> nodeConRB1 = new BinaryTreeNode<>(contRB1);
        BinaryTreeNode<Dialogo> nodeConRB2 = new BinaryTreeNode<>(contRB2);
        BinaryTreeNode<Dialogo> nodeConRB3 = new BinaryTreeNode<>(contRB3);
        BinaryTreeNode<Dialogo> nodeConRB4 = new BinaryTreeNode<>(contRB4);


        BinaryTreeNode<Dialogo> node101 = new BinaryTreeNode<>(d101);
        BinaryTreeNode<Dialogo> node102 = new BinaryTreeNode<>(d102);
        BinaryTreeNode<Dialogo> node103 = new BinaryTreeNode<>(d103);
        BinaryTreeNode<Dialogo> node104 = new BinaryTreeNode<>(d104);
        BinaryTreeNode<Dialogo> node105 = new BinaryTreeNode<>(d105);
        BinaryTreeNode<Dialogo> node106 = new BinaryTreeNode<>(d106);
        BinaryTreeNode<Dialogo> node107 = new BinaryTreeNode<>(d107);
        BinaryTreeNode<Dialogo> node108 = new BinaryTreeNode<>(d108);
        BinaryTreeNode<Dialogo> node109 = new BinaryTreeNode<>(d109);
        BinaryTreeNode<Dialogo> node110 = new BinaryTreeNode<>(d110);
        BinaryTreeNode<Dialogo> node110q = new BinaryTreeNode<>(d110q);
        BinaryTreeNode<Dialogo> node110w = new BinaryTreeNode<>(d110w);
        BinaryTreeNode<Dialogo> node110e = new BinaryTreeNode<>(d110e);
        BinaryTreeNode<Dialogo> node110r = new BinaryTreeNode<>(d110r);
        BinaryTreeNode<Dialogo> node111 = new BinaryTreeNode<>(d111);

        BinaryTreeNode<Dialogo> node303 = new BinaryTreeNode<>(decisionDueno);

        BinaryTreeNode<Dialogo> nodeR1D = new BinaryTreeNode<>(dRama1Due);
        BinaryTreeNode<Dialogo> nodeR1P = new BinaryTreeNode<>(dRama1DuePr1);
        BinaryTreeNode<Dialogo> nodeR1D1 = new BinaryTreeNode<>(dRama1Due1);
        BinaryTreeNode<Dialogo> nodeR1D2 = new BinaryTreeNode<>(dRama1Due2);
        BinaryTreeNode<Dialogo> nodeR1D3 = new BinaryTreeNode<>(dRama1Due3);
        BinaryTreeNode<Dialogo> nodeR1D4 = new BinaryTreeNode<>(dRama1Due4);
        BinaryTreeNode<Dialogo> nodeR1D5 = new BinaryTreeNode<>(dRama1Due5);
        BinaryTreeNode<Dialogo> nodeR1D6 = new BinaryTreeNode<>(dRama1Due6);

        BinaryTreeNode<Dialogo> nodeR1D1$1 = new BinaryTreeNode<>(dRama1Due1$1);
        BinaryTreeNode<Dialogo> nodeR1D2$1 = new BinaryTreeNode<>(dRama1Due2$1);
        BinaryTreeNode<Dialogo> nodeR1D3$1 = new BinaryTreeNode<>(dRama1Due3$1);
        BinaryTreeNode<Dialogo> nodeR1D4$1 = new BinaryTreeNode<>(dRama1Due4$1);
        BinaryTreeNode<Dialogo> nodeR1D5$1 = new BinaryTreeNode<>(dRama1Due5$1);
        BinaryTreeNode<Dialogo> nodeR1D6$1 = new BinaryTreeNode<>(dRama1Due6$1);

        BinaryTreeNode<Dialogo> nodeR2D = new BinaryTreeNode<>(dRama2Due);

        BinaryTreeNode<Dialogo> nodeR2D1 = new BinaryTreeNode<>(dRama2Due1);
        BinaryTreeNode<Dialogo> nodeR2D2 = new BinaryTreeNode<>(dRama2Due2);

        BinaryTreeNode<Dialogo> nodeR2D3 = new BinaryTreeNode<>(dRama2Due3);
        BinaryTreeNode<Dialogo> nodeR2D4 = new BinaryTreeNode<>(dRama2Due4);
        BinaryTreeNode<Dialogo> nodeR2D5 = new BinaryTreeNode<>(dRama2Due5);
        BinaryTreeNode<Dialogo> nodeR2D6 = new BinaryTreeNode<>(dRama2Due6);
        BinaryTreeNode<Dialogo> nodeR2D7 = new BinaryTreeNode<>(dRama2Due7);
        BinaryTreeNode<Dialogo> nodeR2D8 = new BinaryTreeNode<>(dRama2Due8);


        BinaryTreeNode<Dialogo> nodeR2D3$1 = new BinaryTreeNode<>(dRama2Due3$1);
        BinaryTreeNode<Dialogo> nodeR2D4$1 = new BinaryTreeNode<>(dRama2Due4$1);
        BinaryTreeNode<Dialogo> nodeR2D5$1 = new BinaryTreeNode<>(dRama2Due5$1);
        BinaryTreeNode<Dialogo> nodeR2D6$1 = new BinaryTreeNode<>(dRama2Due6$1);
        BinaryTreeNode<Dialogo> nodeR2D7$1 = new BinaryTreeNode<>(dRama2Due7$1);
        BinaryTreeNode<Dialogo> nodeR2D8$1 = new BinaryTreeNode<>(dRama2Due8$1);


        BinaryTreeNode<Dialogo> nodeR3D = new BinaryTreeNode<>(dRama3Due);

        BinaryTreeNode<Dialogo> nodeR3D1 = new BinaryTreeNode<>(dRama3Due1);
        BinaryTreeNode<Dialogo> nodeR3D2 = new BinaryTreeNode<>(dRama3Due2);

        BinaryTreeNode<Dialogo> nodeR3D3 = new BinaryTreeNode<>(dRama3Due3);
        BinaryTreeNode<Dialogo> nodeR3D4 = new BinaryTreeNode<>(dRama3Due4);
        BinaryTreeNode<Dialogo> nodeR3D5 = new BinaryTreeNode<>(dRama3Due5);
        BinaryTreeNode<Dialogo> nodeR3D6 = new BinaryTreeNode<>(dRama3Due6);
        BinaryTreeNode<Dialogo> nodeR3D7 = new BinaryTreeNode<>(dRama3Due7);
        BinaryTreeNode<Dialogo> nodeR3D8 = new BinaryTreeNode<>(dRama3Due8);
        BinaryTreeNode<Dialogo> nodeR3D9 = new BinaryTreeNode<>(dRama3Due9);
        BinaryTreeNode<Dialogo> nodeR3D10 = new BinaryTreeNode<>(dRama3Due10);


        BinaryTreeNode<Dialogo> nodeR3D3$1 = new BinaryTreeNode<>(dRama3Due3$1);
        BinaryTreeNode<Dialogo> nodeR3D4$1 = new BinaryTreeNode<>(dRama3Due4$1);
        BinaryTreeNode<Dialogo> nodeR3D5$1 = new BinaryTreeNode<>(dRama3Due5$1);
        BinaryTreeNode<Dialogo> nodeR3D6$1 = new BinaryTreeNode<>(dRama3Due6$1);
        BinaryTreeNode<Dialogo> nodeR3D7$1 = new BinaryTreeNode<>(dRama3Due7$1);
        BinaryTreeNode<Dialogo> nodeR3D8$1 = new BinaryTreeNode<>(dRama3Due8$1);
        BinaryTreeNode<Dialogo> nodeR3D9$1 = new BinaryTreeNode<>(dRama3Due9$1);
        BinaryTreeNode<Dialogo> nodeR3D10$1 = new BinaryTreeNode<>(dRama3Due10$1);

        GeneralTree<Dialogo> auxTree = new GeneralTree<>();

        auxTree.insertNode(node92, null);
        auxTree.insertNode(node93, node92);
        auxTree.insertNode(node94, node93);
        auxTree.insertNode(node95, node94);
        auxTree.insertNode(node96, node95);
        auxTree.insertNode(node97, node96);
        auxTree.insertNode(node98, node97);
        auxTree.insertNode(node99, node98);
        auxTree.insertNode(node300, node99);

        auxTree.insertNode(node301, node300);
        auxTree.insertNode(nodeConRA, node301);
        auxTree.insertNode(nodeConRA1, nodeConRA);
        auxTree.insertNode(nodeConRA2, nodeConRA1);

        auxTree.insertNode(node302, node300);
        auxTree.insertNode(nodeConRB, node302);
        auxTree.insertNode(nodeConRB1, nodeConRB);
        auxTree.insertNode(nodeConRB2, nodeConRB1);
        auxTree.insertNode(nodeConRB3, nodeConRB2);
        auxTree.insertNode(nodeConRB4, nodeConRB3);

        GeneralTree<Dialogo> auxTree2 = new GeneralTree<>();
        auxTree2.insertNode(node101, null);
        auxTree2.insertNode(node102, node101);
        auxTree2.insertNode(node103, node102);
        auxTree2.insertNode(node104, node103);
        auxTree2.insertNode(node105, node104);
        auxTree2.insertNode(node106, node105);
        auxTree2.insertNode(node107, node106);
        auxTree2.insertNode(node108, node107);
        auxTree2.insertNode(node109, node108);
        auxTree2.insertNode(node110, node109);
        auxTree2.insertNode(node110q, node110);
        auxTree2.insertNode(node110w, node110q);
        auxTree2.insertNode(node110e, node110w);
        auxTree2.insertNode(node110r, node110e);
        auxTree2.insertNode(node111, node110r);
        auxTree2.insertNode(node303, node111);  //pregunta


        auxTree2.insertNode(nodeR1D, node303); //R1
        auxTree2.insertNode(nodeR1P, nodeR1D);

        auxTree2.insertNode(nodeR1D1, nodeR1P);
        auxTree2.insertNode(nodeR1D2, nodeR1D1);
        auxTree2.insertNode(nodeR1D3, nodeR1D2);
        auxTree2.insertNode(nodeR1D4, nodeR1D3);
        auxTree2.insertNode(nodeR1D5, nodeR1D4);
        auxTree2.insertNode(nodeR1D6, nodeR1D5);

        auxTree2.insertNode(nodeR1D1$1, nodeR1P);
        auxTree2.insertNode(nodeR1D2$1, nodeR1D1$1);
        auxTree2.insertNode(nodeR1D3$1, nodeR1D2$1);
        auxTree2.insertNode(nodeR1D4$1, nodeR1D3$1);
        auxTree2.insertNode(nodeR1D5$1, nodeR1D4$1);
        auxTree2.insertNode(nodeR1D6$1, nodeR1D5$1);


        auxTree2.insertNode(nodeR2D, node303); //R2
        auxTree2.insertNode(nodeR2D1, nodeR2D);
        auxTree2.insertNode(nodeR2D2, nodeR2D1);

        auxTree2.insertNode(nodeR2D3, nodeR2D2);
        auxTree2.insertNode(nodeR2D4, nodeR2D3);
        auxTree2.insertNode(nodeR2D5, nodeR2D4);
        auxTree2.insertNode(nodeR2D6, nodeR2D5);
        auxTree2.insertNode(nodeR2D7, nodeR2D6);
        auxTree2.insertNode(nodeR2D8, nodeR2D7);

        auxTree2.insertNode(nodeR2D3$1, nodeR2D2);
        auxTree2.insertNode(nodeR2D4$1, nodeR2D3$1);
        auxTree2.insertNode(nodeR2D5$1, nodeR2D4$1);
        auxTree2.insertNode(nodeR2D6$1, nodeR2D5$1);
        auxTree2.insertNode(nodeR2D7$1, nodeR2D6$1);
        auxTree2.insertNode(nodeR2D8$1, nodeR2D7$1);


        auxTree2.insertNode(nodeR3D, node303); //R3
        auxTree2.insertNode(nodeR3D1, nodeR3D);
        auxTree2.insertNode(nodeR3D2, nodeR3D1);

        auxTree2.insertNode(nodeR3D3, nodeR3D2);
        auxTree2.insertNode(nodeR3D4, nodeR3D3);
        auxTree2.insertNode(nodeR3D5, nodeR3D4);
        auxTree2.insertNode(nodeR3D6, nodeR3D5);
        auxTree2.insertNode(nodeR3D7, nodeR3D6);
        auxTree2.insertNode(nodeR3D8, nodeR3D7);
        auxTree2.insertNode(nodeR3D9, nodeR3D8);
        auxTree2.insertNode(nodeR3D10, nodeR3D9);

        auxTree2.insertNode(nodeR3D3$1, nodeR3D2);
        auxTree2.insertNode(nodeR3D4$1, nodeR3D3$1);
        auxTree2.insertNode(nodeR3D5$1, nodeR3D4$1);
        auxTree2.insertNode(nodeR3D6$1, nodeR3D5$1);
        auxTree2.insertNode(nodeR3D7$1, nodeR3D6$1);
        auxTree2.insertNode(nodeR3D8$1, nodeR3D7$1);
        auxTree2.insertNode(nodeR3D9$1, nodeR3D8$1);
        auxTree2.insertNode(nodeR3D10$1, nodeR3D9$1);



        tutorialParte2.setArbolDial(auxTree);
        tutorialParte3.setArbolDial(auxTree2);

        //Interaccion del detective con todos los personajes
        //Guardia de Seguridad

        Dialogo g1 = new Dialogo("Buenas noches, soy el detective asignado al caso. Necesito hablar con usted.", "Detective", detective, true);
        Dialogo g2 = new Dialogo("Buenas noches, detective. Soy el guardia de seguridad del museo.", "Guardia", seguridad, true);

        Dialogo decisionGuardia = new Dialogo("¿Qué desea preguntarme?", "Guardia", seguridad, true);
        decisionGuardia.setOpciones(new LinkedList<>(Arrays.asList( "¿Qué relación tenía con el economista?",
                "¿Dónde estaba usted cuando ocurrieron los hechos?", "Eso es suficiente por ahora. Terminemos la conversación.")));


        //Pregunta 2: Relación con la víctima
        Dialogo rB = new Dialogo("Casi ninguna. Era reservado, apenas cruzábamos palabras.", "Guardia", seguridad, true);
        //Pregunta 3: Dónde estaba en el momento de los hechos (dato clave)
        Dialogo rC = new Dialogo("Estaba revisando las cámaras. Justo a la hora del asesinato, una de ellas falló en la sección" +
                " medieval. Fui a investigar y encontré el cuerpo.", "Guardia", seguridad, true);

        Dialogo decisionGuardia2 = new Dialogo("¿Alguna pregunta mas?", "Guardia", seguridad, true);
        decisionGuardia2.setOpciones(new LinkedList<>(Arrays.asList("¿Dónde estaba usted cuando ocurrieron los hechos?", "Eso es suficiente por ahora. Terminemos la conversación.")));

        Dialogo decisionGuardia3 = new Dialogo("¿Alguna pregunta más?", "Guardia", seguridad, true);
        decisionGuardia3.setOpciones(new LinkedList<>(Arrays.asList( "¿Qué relación tenía con el economista?",
                "Eso es suficiente por ahora. Terminemos la conversación.")));

        Dialogo rD = new Dialogo("Entendido, detective. Si necesita algo más, estaré en mi puesto.", "Guardia", seguridad, true);
        Dialogo despediDet = new Dialogo("Gracias por su cooperacion, eso seria todo por ahora.", "Detective", detective, true);

        BinaryTreeNode<Dialogo> node112 = new BinaryTreeNode<>(g1);
        BinaryTreeNode<Dialogo> node113 = new BinaryTreeNode<>(g2);

        BinaryTreeNode<Dialogo> node306 = new BinaryTreeNode<>(decisionGuardia);
        BinaryTreeNode<Dialogo> node308 = new BinaryTreeNode<>(rB);
        BinaryTreeNode<Dialogo> node309 = new BinaryTreeNode<>(rC);
        BinaryTreeNode<Dialogo> node308copia = new BinaryTreeNode<>(rB);
        BinaryTreeNode<Dialogo> node309copia = new BinaryTreeNode<>(rC);
        BinaryTreeNode<Dialogo> node310 = new BinaryTreeNode<>(rD);
        BinaryTreeNode<Dialogo> node309A = new BinaryTreeNode<>(decisionGuardia2);
        BinaryTreeNode<Dialogo> node309B = new BinaryTreeNode<>(decisionGuardia3);
        BinaryTreeNode<Dialogo> nodedespD = new BinaryTreeNode<>(despediDet);

        GeneralTree<Dialogo> auxTree3 = new GeneralTree<>();

        auxTree3.insertNode(node112, null);
        auxTree3.insertNode(node113, node112);
        auxTree3.insertNode(node306, node113);
        auxTree3.insertNode(node308, node306);
        auxTree3.insertNode(node309A, node308);
        auxTree3.insertNode(node309copia, node309A);
        auxTree3.insertNode(node310, node309A);
        auxTree3.insertNode(node309, node306);
        auxTree3.insertNode(node309B, node309);
        auxTree3.insertNode(node308copia, node309B);
        auxTree3.insertNode(node310, node309B);
        auxTree3.insertNode(node310, node306);
        auxTree3.insertNode(nodedespD, node309copia);
        auxTree3.insertNode(nodedespD, node308copia);
        auxTree3.insertNode(node310,nodedespD);

        tutorialParte4.setArbolDial(auxTree3);

        //Cambio de personaje, Aqui empieza el guia

        Dialogo dect = new Dialogo("Buenas noches. Estoy interrogando al personal del museo. ¿Podría decirme quién es usted y " +
                "qué función cumple aquí?", "Detective", detective, true);
        Dialogo guia1 = new Dialogo("Claro, detective. Soy el guía principal del museo. Me encargo de recibir a los visitantes," +
                " explicarles las exposiciones y resolver cualquier duda que tengan.", "Guía", guia, true);

        // Primera decisión: preguntar o terminar
        Dialogo decision1 = new Dialogo("¿Desea saber algo más?", "Guía", guia, true);
        decision1.setOpciones(new LinkedList<>(Arrays.asList("¿Cómo era su trato con el economista?", "Eso es todo por ahora." +
                " Gracias por su tiempo.")));

        Dialogo respuesta1 = new Dialogo("La verdad, muy escaso. Era reservado, siempre metido en sus asuntos. " +
                "Apenas cruzábamos palabras.", "Guía", guia, true);
        Dialogo despedida1 = new Dialogo("A usted, detective. Si necesita algo más, estaré por aquí.", "Guía", guia, true);

        // Segunda decisión: preguntar ubicación o terminar
        Dialogo decision2 = new Dialogo("¿Desea saber algo más?", "Guía", guia, true);
        decision2.setOpciones(new LinkedList<>(Arrays.asList("¿Dónde se encontraba usted durante el incidente?", "Gracias. " +
                "Eso es todo por ahora.")));

        Dialogo respuesta2 = new Dialogo("Estaba en la sala de arte moderno, preparando la presentación de mañana. " +
                "Conozco el museo como la palma de mi mano, y no noté nada fuera de lo común.", "Guía", guia, true);

        // Dato clave

        Dialogo despedida2 = new Dialogo("A usted, detective. Que tenga buena noche.", "Guía", guia, true);
        Dialogo despeDetectiveGuia = new Dialogo("Gracias, eso seria todo por ahora", "Detective", detective, true);

        BinaryTreeNode<Dialogo> node114 = new BinaryTreeNode<>(dect);
        BinaryTreeNode<Dialogo> node115 = new BinaryTreeNode<>(guia1);

        BinaryTreeNode<Dialogo> node311 = new BinaryTreeNode<>(decision1);
        BinaryTreeNode<Dialogo> node312 = new BinaryTreeNode<>(respuesta1);
        BinaryTreeNode<Dialogo> node313 = new BinaryTreeNode<>(despedida1);
        BinaryTreeNode<Dialogo> node344 = new BinaryTreeNode<>(decision2);
        BinaryTreeNode<Dialogo> node314 = new BinaryTreeNode<>(respuesta2);
        BinaryTreeNode<Dialogo> node315 = new BinaryTreeNode<>(despedida2);
        BinaryTreeNode<Dialogo> nodeDesGuia = new BinaryTreeNode<>(despeDetectiveGuia);

        GeneralTree<Dialogo> auxTree4 = new GeneralTree<>();

        auxTree4.insertNode(node114, null);
        auxTree4.insertNode(node115, node114);
        auxTree4.insertNode(node311, node115);
        auxTree4.insertNode(node312, node311);
        auxTree4.insertNode(node313, node311);
        auxTree4.insertNode(node344, node312);
        auxTree4.insertNode(node314, node344);
        auxTree4.insertNode(nodeDesGuia, node314);
        auxTree4.insertNode(node315, nodeDesGuia);
        auxTree4.insertNode(node315, node344);

        tutorialParte5.setArbolDial(auxTree4);

        //Cambio de personaje, Aqui empieza la secretaria

        Dialogo s1 = new Dialogo("Buenas noches. Estoy reuniendo declaraciones del personal. ¿Podemos hablar un momento?", "Detective", detective, true);
        Dialogo s2 = new Dialogo("Por supuesto, detective. Soy la secretaria del director. Manejo su agenda, sus llamadas... y" +
                " a veces sus caprichos.", "Secretaria", secretaria, true);

        // Primera decisión
        Dialogo decs1= new Dialogo("¿Quiere saber algo más o solo vino a saludar?", "Secretaria", secretaria, true);
        decs1.setOpciones(new LinkedList<>(Arrays.asList("¿Tenía algún tipo de relación con el economista?", "No, eso sería todo por ahora.")));

        Dialogo resp1 = new Dialogo("Nada fuera de lo laboral. Era serio, distante... aunque a veces me lanzaba miradas que decían más " +
                "que sus palabras.", "Secretaria", secretaria, true);

        Dialogo desp1 = new Dialogo("Qué pena... justo cuando empezaba a disfrutar la charla. Estoy a su disposición, detective.", "Secretaria", secretaria, true);

        // Segunda decisión
        Dialogo decs2 = new Dialogo("¿Le interesa conocer algo mas acerca de mi.?", "Secretaria", secretaria, true);
        decs2.setOpciones(new LinkedList<>(Arrays.asList("¿Dónde se encontraba cuando ocurrió el incidente?", "Gracias por su tiempo. " +
                "Eso sería todo.")));
        ;
        Dialogo resp2 = new Dialogo("En mi oficina, como siempre. Archivando papeles, atendiendo llamadas... y esperando que algo" +
                " interesante pasara. Supongo que lo logró.", "Secretaria", secretaria, true);

        Dialogo desp2 = new Dialogo("A usted, detective. Que tenga una noche... intrigante.", "Secretaria", secretaria, true);
        Dialogo despDetSecret= new Dialogo("Eso seria todo, puede regresar. Gracias por su cooperación", "Detective", detective, true);

        BinaryTreeNode<Dialogo> node116 = new BinaryTreeNode<>(s1);
        BinaryTreeNode<Dialogo> node117 = new BinaryTreeNode<>(s2);

        BinaryTreeNode<Dialogo> node316 = new BinaryTreeNode<>(decs1);
        BinaryTreeNode<Dialogo> node317 = new BinaryTreeNode<>(resp1);
        BinaryTreeNode<Dialogo> node318 = new BinaryTreeNode<>(desp1);
        BinaryTreeNode<Dialogo> node345 = new BinaryTreeNode<>(decs2);
        BinaryTreeNode<Dialogo> node319 = new BinaryTreeNode<>(resp2);
        BinaryTreeNode<Dialogo> node320 = new BinaryTreeNode<>(desp2);
        BinaryTreeNode<Dialogo> nodeDesSecr = new BinaryTreeNode<>(despDetSecret);

        GeneralTree<Dialogo> auxTree5 = new GeneralTree<>();

        auxTree5.insertNode(node116, null);
        auxTree5.insertNode(node117, node116);
        auxTree5.insertNode(node316, node117);
        auxTree5.insertNode(node317, node316);
        auxTree5.insertNode(node318, node316);
        auxTree5.insertNode(node345, node317);
        auxTree5.insertNode(node319, node345);
        auxTree5.insertNode(nodeDesSecr, node319);
        auxTree5.insertNode(node320, nodeDesSecr);
        auxTree5.insertNode(node320, node345);

        tutorialParte6.setArbolDial(auxTree5);

        //Cambio de personaje, Aqui empieza el guia2
        Dialogo guiaM1 = new Dialogo("Buenas noches. Estoy hablando con el personal del museo. ¿Podría decirme su nombre y su función " +
                "aquí?", "Detective", detective, true);
        Dialogo guiaM2 = new Dialogo("Soy el segundo guía del museo. Me encargo de apoyar en las visitas... y de resolver los problemas " +
                "que otros prefieren ignorar.", "Guía 2", guia2, true);

        // Primera decisión
        Dialogo decis1 = new Dialogo("¿Algo más que quiera saber, o ya tiene suficiente para su informe?", "Guía 2", guia2, true);
        decis1.setOpciones(new LinkedList<>(Arrays.asList("¿Conocía bien al economista?", "Por ahora es suficiente. Gracias.")));

        Dialogo respt1 = new Dialogo("Lo justo. No era alguien con quien uno quisiera compartir un café. Siempre tan correcto," +
                " tan... aburrido.", "Guía 2", guia2, true);
        Dialogo relleno1 = new Dialogo("Aunque, claro, en este lugar todos llevamos máscaras. Algunas más pesadas que otras.", "Guía 2", guia2, true);

        Dialogo despd1 = new Dialogo("Como desee. Aunque si cambia de opinión, estaré por aquí... observando cómo se desenvuelven " +
                "las piezas.", "Guía 2", guia2, true);

        // Segunda decisión
        Dialogo decis2 = new Dialogo("¿Le interesa saber dónde estaba o ya tiene lo que vino a buscar?", "Guía 2", guia2, true);
        decis2.setOpciones(new LinkedList<>(Arrays.asList("¿Dónde se encontraba cuando ocurrió el incidente?", "Gracias por su tiempo. " +
                "Eso sería todo." )));

        Dialogo respt2 = new Dialogo("En la sala de esculturas. Estaba revisando unos textos para la próxima muestra. Me gusta trabajar" +
                " solo... menos distracciones, menos tonterías.", "Guía 2", guia2, true);
        Dialogo relleno2 = new Dialogo("Pero bueno, en este museo el silencio nunca es garantía de tranquilidad.", "Guía 2", guia2, true);

        Dialogo despdDete = new Dialogo("Gracias eso seria todo por ahora", "Detective", detective, true);

        Dialogo despd2 = new Dialogo("A usted, detective. Que tenga una noche... tranquila. O lo más cerca que pueda estar de eso.", "Guía 2", guia2, true);

        BinaryTreeNode<Dialogo> node118 = new BinaryTreeNode<>(guiaM1);
        BinaryTreeNode<Dialogo> node119 = new BinaryTreeNode<>(guiaM2);

        BinaryTreeNode<Dialogo> node321 = new BinaryTreeNode<>(decis1);
        BinaryTreeNode<Dialogo> node322 = new BinaryTreeNode<>(respt1);
        BinaryTreeNode<Dialogo> node323 = new BinaryTreeNode<>(relleno1);
        BinaryTreeNode<Dialogo> node324 = new BinaryTreeNode<>(despd1);
        BinaryTreeNode<Dialogo> node346 = new BinaryTreeNode<>(decis2);
        BinaryTreeNode<Dialogo> node325 = new BinaryTreeNode<>(respt2);
        BinaryTreeNode<Dialogo> node326 = new BinaryTreeNode<>(relleno2);
        BinaryTreeNode<Dialogo> node327 = new BinaryTreeNode<>(despd2);
        BinaryTreeNode<Dialogo> nodeDespedidaDet = new BinaryTreeNode<>(despdDete);

        GeneralTree<Dialogo> auxTree6 = new GeneralTree<>();
        auxTree6.insertNode(node118, null);
        auxTree6.insertNode(node119, node118);
        auxTree6.insertNode(node321, node119);
        auxTree6.insertNode(node322, node321);
        auxTree6.insertNode(node323, node322);
        auxTree6.insertNode(node324, node321);
        auxTree6.insertNode(node346, node323);
        auxTree6.insertNode(node325, node346);
        auxTree6.insertNode(node326, node325);
        auxTree6.insertNode(node327, node346);
        auxTree6.insertNode(nodeDespedidaDet, node326);
        auxTree6.insertNode(node327, nodeDespedidaDet);
        tutorialParte7.setArbolDial(auxTree6);

        //Cambio de personaje, Aqui empieza el de limpieza
        Dialogo l1 = new Dialogo("Hola. Estoy hablando con todos los empleados del museo. ¿Podría decirme su nombre y qué hace aquí?", "Detective", detective, true);
        Dialogo l2 = new Dialogo("Sí, claro. Soy el encargado de la limpieza del museo. Me encargo de dejar todo en orden cuando ya no" +
                " queda nadie.", "Limpieza", limpieza, true);
        Dialogo l3 = new Dialogo("Trabajo de noche, así que casi siempre estoy solo. Es tranquilo... aunque uno se encuentra cosas que" +
                " otros no notan.", "Limpieza", limpieza, true);

        // Primera decisión
        Dialogo de1 = new Dialogo("¿Quiere preguntarme algo más?", "Limpieza", limpieza, true);
        de1.setOpciones(new LinkedList<>(Arrays.asList("¿Conocía al economista?", "Está bien así, gracias por su tiempo.")) );

        Dialogo res1 = new Dialogo("Lo veía pasar. Siempre con prisa, como si el tiempo le debiera algo. Nunca me saludó, pero tampoco" +
                " molestaba.", "Limpieza", limpieza, true);
        Dialogo rell1 = new Dialogo("Una vez dejó caer unos papeles y ni se dio cuenta. Los recogí y se los puse en su escritorio." +
                " Nunca dijo nada.", "Limpieza", limpieza, true);

        Dialogo des1 = new Dialogo("De nada. Si necesita algo más, con gusto puedo ayudar.", "Limpieza", limpieza, true);

        // Segunda decisión
        Dialogo de2 = new Dialogo("¿Algo más en lo que lo pueda ayudar Detective?", "Limpieza", limpieza, true);
        de2.setOpciones(new LinkedList<>(Arrays.asList("¿Dónde se encontraba cuando ocurrió todo?", "Gracias. Eso es todo por ahora.")));

        Dialogo res2 = new Dialogo("En los baños, siempre empiezo por ahí. No escuché nada raro, solo el eco de mis pasos.", "Limpieza", limpieza, true);
        Dialogo rell2 = new Dialogo("A veces, mientras limpio, me encuentro con cosas que otros dejan olvidadas. Papeles, llaves," +
                " hasta notas raras. Supongo que es parte del trabajo.", "Limpieza", limpieza, true);


        // Dato clave
        Juego.getInstance().getPartidaActual().getJugador().getDiario().agregarDialogoImportante ("Limpieza", "Suele encontrarse con cosas útiles al estar solo limpiando.");

        Dialogo despedidaDetec = new Dialogo("Gracias, eso seria todo por ahora, mantengase cerca por" +
                " si necesitamos hacerle más preguntas", "Detective", detective, true);
        Dialogo des2 = new Dialogo("Que tenga buena noche, jefe.", "Limpieza", limpieza, true);

        BinaryTreeNode<Dialogo> node120 = new BinaryTreeNode<>(l1);
        BinaryTreeNode<Dialogo> node121 = new BinaryTreeNode<>(l2);
        BinaryTreeNode<Dialogo> node122 = new BinaryTreeNode<>(l3);

        BinaryTreeNode<Dialogo> node328 = new BinaryTreeNode<>(de1);
        BinaryTreeNode<Dialogo> node329 = new BinaryTreeNode<>(res1);
        BinaryTreeNode<Dialogo> node330 = new BinaryTreeNode<>(rell1); // 330
        BinaryTreeNode<Dialogo> node331 = new BinaryTreeNode<>(des1);
        BinaryTreeNode<Dialogo> node332 = new BinaryTreeNode<>(de2); //332
        BinaryTreeNode<Dialogo> node333 = new BinaryTreeNode<>(res2);
        BinaryTreeNode<Dialogo> node334 = new BinaryTreeNode<>(rell2);
        BinaryTreeNode<Dialogo> node335 = new BinaryTreeNode<>(des2);
        BinaryTreeNode<Dialogo> nodeDespedida = new BinaryTreeNode<>(despedidaDetec);

        GeneralTree<Dialogo> auxTree7 = new GeneralTree<>();
        auxTree7.insertNode(node120, null);
        auxTree7.insertNode(node121, node120);
        auxTree7.insertNode(node122, node121);
        auxTree7.insertNode(node328, node122);
        auxTree7.insertNode(node329, node328);


        auxTree7.insertNode(node330, node329);
        auxTree7.insertNode(node332, node330);
        auxTree7.insertNode(node331, node328);
        auxTree7.insertNode(node332, node122);
        auxTree7.insertNode(node333, node332);
        auxTree7.insertNode(node334, node333);
        auxTree7.insertNode(node335, node332);

        // Nodo de despedida
        auxTree7.insertNode(node328,nodeDespedida);
        auxTree7.insertNode(node332,nodeDespedida);

        tutorialParte8.setArbolDial(auxTree7);

        //Cambio de personaje, Aqui empieza la Esposa del jefe

        Dialogo e1 = new Dialogo("Buenas noches. Estoy hablando con todos los presentes en el museo. ¿Podría decirme quién es usted?", "Detective", detective, true);
        Dialogo e2 = new Dialogo("Soy la esposa del director del museo. Me llamo Cate Sinclair, esta noche lo acompañé al evento, como es habitual en ocasiones" +
                " importantes.", "Esposa", esposa, true);
        Dialogo e3 = new Dialogo("No suelo involucrarme en los asuntos del museo, pero conozco bien a quienes lo rodean.", "Esposa", esposa, true);

        // Primera decisión
        Dialogo d1 = new Dialogo("¿Desea preguntarme algo más, detective?", "Esposa", esposa, true);
        d1.setOpciones(new LinkedList<>(Arrays.asList("¿Qué opinión tenía del economista?", "Gracias. No tengo más preguntas por ahora.")));
        ;
        Dialogo rp1 = new Dialogo("Era un hombre reservado, meticuloso. Mi esposo confiaba en él, aunque yo siempre lo encontré..." +
                " difícil de leer.", "Esposa", esposa, true);
        Dialogo relle1 = new Dialogo("A veces me preguntaba si ocultaba algo. Pero supongo que todos lo hacemos, ¿no cree?", "Esposa", esposa, true);

        Dialogo despdid1 = new Dialogo("Muy bien. Si recuerda algo más, estaré en el vestíbulo. Prefiero no quedarme sola por ahora.", "Esposa", esposa, true);

        // Segunda decisión
        Dialogo d2 = new Dialogo("¿Desea saber dónde estaba durante el incidente o prefiere dejarlo aquí?", "Esposa", esposa, true);
        d2.setOpciones(new LinkedList<>(Arrays.asList("¿Dónde se encontraba cuando ocurrió el incidente?", "Gracias por su tiempo. " +
                "Eso sería todo.")));

        Dialogo rp2 = new Dialogo("Estaba en el salón principal, conversando con algunos invitados. No vi ni escuché nada fuera de " +
                "lo común.", "Esposa", esposa, true);
        Dialogo relle2 = new Dialogo("Aunque, para ser sincera, últimamente he notado cierta tensión en el ambiente. Nada concreto..." +
                " solo una sensación.", "Esposa", esposa, true);

        Dialogo despedidaDetective = new Dialogo("Eso seria todo por ahora, muchas gracias por su cooperación ", "Detective", detective, true);
        Dialogo despdid2 = new Dialogo("A usted, detective. Espero que encuentre respuestas pronto.", "Esposa", esposa, true);

        BinaryTreeNode<Dialogo> node123 = new BinaryTreeNode<>(e1);
        BinaryTreeNode<Dialogo> node124 = new BinaryTreeNode<>(e2);
        BinaryTreeNode<Dialogo> node125 = new BinaryTreeNode<>(e3);

        BinaryTreeNode<Dialogo> node336 = new BinaryTreeNode<>(d1);
        BinaryTreeNode<Dialogo> node337 = new BinaryTreeNode<>(rp1);
        BinaryTreeNode<Dialogo> node338 = new BinaryTreeNode<>(relle1);
        BinaryTreeNode<Dialogo> node339 = new BinaryTreeNode<>(despdid1);
        BinaryTreeNode<Dialogo> node340 = new BinaryTreeNode<>(d2);
        BinaryTreeNode<Dialogo> node341 = new BinaryTreeNode<>(rp2);
        BinaryTreeNode<Dialogo> node342 = new BinaryTreeNode<>(relle2);
        BinaryTreeNode<Dialogo> node343 = new BinaryTreeNode<>(despdid2);
        BinaryTreeNode<Dialogo> desp = new BinaryTreeNode<>(despedidaDetective);

        GeneralTree<Dialogo> auxTree8 = new GeneralTree<>();

        auxTree8.insertNode(node123, null);
        auxTree8.insertNode(node124, node123);
        auxTree8.insertNode(node125, node124);
        auxTree8.insertNode(node336, node125);
        // nodo de desicion 337
        auxTree8.insertNode(node337, node336);
        auxTree8.insertNode(node338, node337);

        // nodo de pregunta
        auxTree8.insertNode(node340, node338);

        auxTree8.insertNode(node339, node336);
        auxTree8.insertNode(node340, node125);
        auxTree8.insertNode(node341, node340);
        auxTree8.insertNode(node342, node341);
        auxTree8.insertNode(node343, node340);

        // nodo de salida con despedida
        auxTree8.insertNode(desp,node342);
        auxTree8.insertNode(node343, desp);



        tutorialParte9.setArbolDial(auxTree8);

        //Conversación Lineal entre personajes
        Dialogo p1 = new Dialogo("Gracias a todos por su colaboración. Tras revisar las pruebas encontradas en la escena y los testimonios recabados...", "Policía", policia, true);
        Dialogo p2 = new Dialogo("…la investigación ha concluido que el fallecimiento del economista fue un suicidio.", "Policía", policia, true);
        Dialogo p3 = new Dialogo("No se hallaron signos de violencia externa, ni evidencia que sugiera la intervención de un tercero.", "Policía", policia, true);
        Dialogo p4 = new Dialogo("Tampoco contamos con declaraciones que contradigan esta hipótesis. Por lo tanto, el caso queda oficialmente cerrado.", "Policía", policia, true);
        Dialogo p5 = new Dialogo("Si alguno de ustedes recuerda algo más tarde, puede acercarse a la comisaría. Pero por ahora, pueden retirarse.", "Policía", policia, true);

        // Reacciones de los personajes
        Dialogo guia1Otro = new Dialogo("¿Un suicidio? Qué conveniente...", "Guía 1", guia, true);
        Dialogo guia1Ot = new Dialogo("Con todo respeto, oficial, el economista no era precisamente impulsivo. Y usted lo sabe.", "Guía 1", guia, true);

        Dialogo secretaria1 = new Dialogo("¿Y qué hay del ruido que escucharon en el ala este? ¿Eso también fue un accidente?", "Secretaria", secretaria, true);

        Dialogo limpieza1 = new Dialogo("Yo no sé mucho, pero he visto cosas raras en este lugar. Cosas que no cuadran.", "Limpieza", limpieza, true);

        Dialogo esposa1 = new Dialogo("No quiero sonar dramática, pero esto se siente... precipitado. ¿De verdad están seguros?", "Esposa", esposa, true);

        // Policía reacciona con firmeza
        Dialogo p6 = new Dialogo("¡Silencio!", "Policía", policia, true);
        Dialogo p7 = new Dialogo("Esto no es un debate. El informe es claro, las pruebas son concluyentes y no hay evidencia que indique lo contrario.", "Policía", policia, true);
        Dialogo p8 = new Dialogo("Agradezco su cooperación, pero les pido que respeten el trabajo que se ha hecho. El caso está cerrado. Punto.", "Policía", policia, true);

        // Opinion del detective (conversación interna)
        Dialogo m1 = new Dialogo("(¿Un suicidio...? No lo sé...)", "Detective", detective, true);
        Dialogo m2 = new Dialogo("(Todo fue demasiado limpio. Demasiado rápido. Como si alguien quisiera cerrar esto antes de que " +
                "alguien hiciera demasiadas preguntas.)", "Detective", detective, true);
        Dialogo m3 = new Dialogo("(La carta... estaba perfectamente doblada, sin una sola mancha. ¿Quién se toma el tiempo de" +
                " escribir una nota de despedida y la deja tan impecable?)", "Detective", detective, true);
        Dialogo m4 = new Dialogo("(Y el cuchillo... estaba colocado con una precisión quirúrgica. No había sangre en el mango. " +
                "¿Cómo se apuñala alguien sin dejar rastro en sus propias manos?)", "Detective", detective, true);
        Dialogo m5 = new Dialogo("(No. Algo no encaja. Y si nadie más va a seguir con esto... lo haré yo.)", "Detective", detective, true);

        // Suena el teléfono
        Dialogo llamada1 = new Dialogo("*Riiing... Riiing...*", "", nada, true);
        Dialogo llamada2 = new Dialogo("¿Sí? Habla el detective.", "Detective", detective, true);
        Dialogo laboratorio1 = new Dialogo("Detective, nos comunicamos con usted desde el laboratorio forense. Acabamos de terminar" +
                " el análisis de las pruebas que envió.", "Laboratorio", nada, true);
        Dialogo laboratorio2 = new Dialogo("El cuchillo no coincide con la herida. El ángulo, la profundidad, la forma de la " +
                "herida... no concuerdan con un acto autoinfligidoni con un cuchillo asi.", "Laboratorio", nada, true);
        Dialogo laboratorio3 = new Dialogo("Y hay algo más: la carta de despedida. La caligrafía no coincide con la de la víctima." +
                " No es su letra.", "Laboratorio", nada, true);

        // Reacción del protagonista
        Dialogo m6 = new Dialogo("(Lo sabía...)", "Detective", detective, true);
        Dialogo m7 = new Dialogo("(Esto no fue un suicidio. Fue una puesta en escena.)", "Detective", detective, true);
        Dialogo m8 = new Dialogo("(Y ahora tengo una razón para seguir adelante.)", "Detective", detective, true);

        Dialogo detective2 = new Dialogo("¡Un momento!", "Detective", detective, true);
        Dialogo d3 = new Dialogo("Acabo de recibir una llamada del laboratorio forense. Las pruebas han sido analizadas.",
                "Detective", detective, true);
        Dialogo d4 = new Dialogo("El cuchillo encontrado junto al cuerpo... no coincide con la herida. La forma de la herida y " +
                "la profundidad no corresponden a un acto autoinfligido y menos por un objeto asi.", "Detective",
                detective, true);
        Dialogo d5 = new Dialogo("Y la carta de despedida... no fue escrita por la víctima. La caligrafía no coincide.",
                "Detective", detective, true);

        Dialogo d6 = new Dialogo("*Un murmullo recorre la sala. Algunos se miran entre sí, otros bajan la mirada.*", "", nada, true);

        Dialogo d7 = new Dialogo("Así que no, señores. Esto no fue un suicidio. Fue un montaje. Y alguien aquí sabe exactamente lo que pasó.", "Detective", detective, true);
        Dialogo d8 = new Dialogo("El caso no ha terminado. El caso apenas comienza.", "Detective",
                detective, true);

        Dialogo d9 = new Dialogo("¡NADIE SE MUEVE DEL MUSEO!", "Detective", detective, true);

        BinaryTreeNode<Dialogo> node126= new BinaryTreeNode<>(p1);
        BinaryTreeNode<Dialogo> node127= new BinaryTreeNode<>(p2);
        BinaryTreeNode<Dialogo> node128= new BinaryTreeNode<>(p3);
        BinaryTreeNode<Dialogo> node129= new BinaryTreeNode<>(p4);
        BinaryTreeNode<Dialogo> node130= new BinaryTreeNode<>(p5);
        BinaryTreeNode<Dialogo> node131= new BinaryTreeNode<>(guia1Otro);
        BinaryTreeNode<Dialogo> node132= new BinaryTreeNode<>(guia1Ot);
        BinaryTreeNode<Dialogo> node133= new BinaryTreeNode<>(secretaria1);
        BinaryTreeNode<Dialogo> node134= new BinaryTreeNode<>(limpieza1);
        BinaryTreeNode<Dialogo> node135= new BinaryTreeNode<>(esposa1);
        BinaryTreeNode<Dialogo> node136= new BinaryTreeNode<>(p6);
        BinaryTreeNode<Dialogo> node137= new BinaryTreeNode<>(p7);
        BinaryTreeNode<Dialogo> node138= new BinaryTreeNode<>(p8);
        BinaryTreeNode<Dialogo> node139= new BinaryTreeNode<>(m1);
        BinaryTreeNode<Dialogo> node140= new BinaryTreeNode<>(m2);
        BinaryTreeNode<Dialogo> node141= new BinaryTreeNode<>(m3);
        BinaryTreeNode<Dialogo> node142= new BinaryTreeNode<>(m4);
        BinaryTreeNode<Dialogo> node143= new BinaryTreeNode<>(m5);
        BinaryTreeNode<Dialogo> node144= new BinaryTreeNode<>(llamada1);
        BinaryTreeNode<Dialogo> node145= new BinaryTreeNode<>(llamada2);
        BinaryTreeNode<Dialogo> node146= new BinaryTreeNode<>(laboratorio1);
        BinaryTreeNode<Dialogo> node147= new BinaryTreeNode<>(laboratorio2);
        BinaryTreeNode<Dialogo> node148= new BinaryTreeNode<>(laboratorio3);
        BinaryTreeNode<Dialogo> node149= new BinaryTreeNode<>(m6);
        BinaryTreeNode<Dialogo> node150= new BinaryTreeNode<>(m7);
        BinaryTreeNode<Dialogo> node151= new BinaryTreeNode<>(m8);
        BinaryTreeNode<Dialogo> node152= new BinaryTreeNode<>(detective2);
        BinaryTreeNode<Dialogo> node153= new BinaryTreeNode<>(d3);
        BinaryTreeNode<Dialogo> node154= new BinaryTreeNode<>(d4);
        BinaryTreeNode<Dialogo> node155= new BinaryTreeNode<>(d5);
        BinaryTreeNode<Dialogo> node156= new BinaryTreeNode<>(d6);
        BinaryTreeNode<Dialogo> node157= new BinaryTreeNode<>(d7);
        BinaryTreeNode<Dialogo> node158= new BinaryTreeNode<>(d8);
        BinaryTreeNode<Dialogo> node159= new BinaryTreeNode<>(d9);


        GeneralTree<Dialogo> auxTree9 = new GeneralTree<>();
        auxTree9.insertNode(node126, null);
        auxTree9.insertNode(node127, node126);
        auxTree9.insertNode(node128, node127);
        auxTree9.insertNode(node129, node128);
        auxTree9.insertNode(node130, node129);
        auxTree9.insertNode(node131, node130);
        auxTree9.insertNode(node132, node131);
        auxTree9.insertNode(node133, node132);
        auxTree9.insertNode(node134, node133);
        auxTree9.insertNode(node135, node134);
        auxTree9.insertNode(node136, node135);
        auxTree9.insertNode(node137, node136);
        auxTree9.insertNode(node138, node137);
        auxTree9.insertNode(node139, node138);
        auxTree9.insertNode(node140, node139);
        auxTree9.insertNode(node141, node140);
        auxTree9.insertNode(node142, node141);
        auxTree9.insertNode(node143, node142);
        auxTree9.insertNode(node144, node143);
        auxTree9.insertNode(node145, node144);
        auxTree9.insertNode(node146, node145);
        auxTree9.insertNode(node147, node146);
        auxTree9.insertNode(node148, node147);
        auxTree9.insertNode(node149, node148);
        auxTree9.insertNode(node150, node149);
        auxTree9.insertNode(node151, node150);
        auxTree9.insertNode(node152, node151);
        auxTree9.insertNode(node153, node152);
        auxTree9.insertNode(node154, node153);
        auxTree9.insertNode(node155, node154);
        auxTree9.insertNode(node156, node155);
        auxTree9.insertNode(node157, node156);
        auxTree9.insertNode(node158, node157);
        auxTree9.insertNode(node159, node158);

        tutorialParte10.setArbolDial(auxTree9);


    }

    public void crearMinijuego(){
        // Ocultar botón saltar cuando comience el minijuego
        botonSaltar.setVisible(false);

        MiniJuego miniJuego = Juego.getInstance().getMinijuego(0);

        MinijuegoInterfaz minijuegoInterfaz = new MinijuegoInterfaz(miniJuego);
        minijuegoInterfaz.setBounds(0, 0, tamPant.width, tamPant.height);
        getContentPane().add(minijuegoInterfaz, 0);
        getContentPane().revalidate();
        getContentPane().repaint();

    }
    private void iniciarMundo(){
        Entrada entrada =  new Entrada();
        entrada.setVisible(true);
        timer2.schedule(tarea2, 2000);

    }
    private void cTParte1MouseClicked(MouseEvent evt) {
        ponerDialogoParte1();
        getContentPane().revalidate();
        getContentPane().repaint();

    }
    private void cTParte2MouseClicked(MouseEvent evt) {
        ponerDialogoParte2();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    private void cTParte3MouseClicked(MouseEvent evt) {
        ponerDialogoParte3();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    private void cTParte4MouseClicked(MouseEvent evt) {
        ponerDialogoParte4();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    private void cTParte5MouseClicked(MouseEvent evt) {
        ponerDialogoParte5();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    private void cTParte6MouseClicked(MouseEvent evt) {
        ponerDialogoParte6();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    private void cTParte7MouseClicked(MouseEvent evt) {
        ponerDialogoParte7();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    private void cTParte8MouseClicked(MouseEvent evt) {
        ponerDialogoParte8();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    private void cTParte9MouseClicked(MouseEvent evt) {
        ponerDialogoParte9();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    private void cTParte10MouseClicked(MouseEvent evt) {
        ponerDialogoParte10();
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    private void saltarAlJuego() {
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDeBoton();
        iniciarMundo();
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | UnsupportedLookAndFeelException ex) {
            logger.log(Level.SEVERE, null, ex);
        }


        /* Create and display the form */
        EventQueue.invokeLater(() -> new Tutorial().setVisible(true));

    }

    private JLabel fondo;
    private JPanel cajaTexto;
    // Variables declaration - do not modify//GEN-BEGIN:variables

    // End of variables declaration//GEN-END:variables
}


