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
import java.util.*;
import java.util.Timer;

public class Recepcion extends ModeloEscenario{
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Recepcion.class.getName());
    private Dimension tamPant;
    private Timer timer;
    private TimerTask tarea;
    private InterfazUsuario interfazUsuario;
    private Timer timer2;
    private TimerTask tarea2;
    private JButton policia;
    private JButton secretaria;

     /**
     * Creates new form Entrada
     */
    public Recepcion() {
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
        fondo = new JLabel();
        flechaSalida = new JButton();
        flechaBano = new JButton();
        flechaSala1 = new JButton();
        flechaPasillo1 = new JButton();
        lugar = new JLabel();
        interfazUsuario= new InterfazUsuario();
        policia = new JButton();
        secretaria = new JButton();
        try {
            BufferedImage imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/entrada por dentro.jpg"));


            // Actualizacion de donde esta el Jugador
            Juego.getInstance().getPartidaActual().buscarEscenarioNombre("Recepcion");

            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setMinimumSize(tamPant);
            setUndecorated(true);
            setPreferredSize(tamPant);
            getContentPane().setLayout(null);
            ImageIcon icono = new ImageIcon(imagen.getScaledInstance(tamPant.width, tamPant.height, Image.SCALE_SMOOTH));
            fondo.setIcon(icono); // NOI18N
            fondo.setFocusable(false);
            fondo.setMaximumSize(tamPant);
            fondo.setMinimumSize(tamPant);
            fondo.setPreferredSize(tamPant);
            fondo.setBounds(0, 0, tamPant.width, tamPant.height);

            cajaTexto.setBackground(new Color(0, 0, 0, 0));
            cajaTexto.setBounds(0, 0,  tamPant.width, tamPant.height);
            cajaTexto.setLayout(null);

            BufferedImage imagen2 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha arriba.png"));
            ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
            flechaSalida.setIcon(icono2);


            BufferedImage imagen3 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha derecha.png"));
            ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
            flechaBano.setIcon(icono3);


            BufferedImage imagen4 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha izquierda.png"));
            ImageIcon icono4 = new ImageIcon(imagen4.getScaledInstance((int) (int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
            flechaSala1.setIcon(icono4);


            BufferedImage imagen5 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha abajo.png"));
            ImageIcon icono5 = new ImageIcon(imagen5.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
            flechaPasillo1.setIcon(icono5);

            BufferedImage imagen6 = ImageIO.read(new File("DatosAuxiliares/Personajes/Policia.png"));
            ImageIcon icono6 = new ImageIcon(imagen6.getScaledInstance((int) (tamPant.width*0.083), (int) (tamPant.height*0.37), Image.SCALE_SMOOTH));
            policia.setIcon(icono6);

            BufferedImage imagen7 = ImageIO.read(new File("DatosAuxiliares/Personajes/Secretaria.png"));
            ImageIcon icono7 = new ImageIcon(imagen7.getScaledInstance((int) (tamPant.width*0.11), (int) (tamPant.height*0.49), Image.SCALE_SMOOTH));
            secretaria.setIcon(icono7);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        flechaSalida.setBounds((int) (tamPant.width*0.48), (int) (tamPant.height*0.58), (int) (tamPant.width*0.048), (int) (tamPant.height*0.124));
        flechaSalida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaSalidaActionPerformed(evt);
            }
        });
        flechaSalida.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaSalidaMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaSalidaMouseExited(evt);
            }
        });
        flechaSalida.setToolTipText("Entrada");
        flechaSalida.setOpaque(true);
        flechaSalida.setContentAreaFilled(false);
        flechaSalida.setBorderPainted(false);
        flechaSalida.setFocusPainted(false);

        flechaBano.setBounds((int) (tamPant.width*0.88), (int) (tamPant.height*0.8), (int) (tamPant.width*0.08), (int) (tamPant.height*0.07));
        flechaBano.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaBanoActionPerformed(evt);
            }
        });
        flechaBano.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaBanoMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaBanoMouseExited(evt);
            }
        });
        flechaBano.setToolTipText("Baño Planta Baja");
        flechaBano.setOpaque(true);
        flechaBano.setContentAreaFilled(false);
        flechaBano.setBorderPainted(false);
        flechaBano.setFocusPainted(false);


        flechaSala1.setBounds((int) (tamPant.width*0.06), (int) (tamPant.height*0.64), (int) (tamPant.width*0.1), (int) (tamPant.height*0.08));
        flechaSala1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaSala1ActionPerformed(evt);
            }
        });
        flechaSala1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaSala1MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaSala1MouseExited(evt);
            }
        });
        flechaSala1.setToolTipText("Sala Planta Alta");
        flechaSala1.setOpaque(true);
        flechaSala1.setContentAreaFilled(false);
        flechaSala1.setBorderPainted(false);
        flechaSala1.setFocusPainted(false);


        flechaPasillo1.setBounds((int) (tamPant.width*0.34), (int) (tamPant.height*0.84), (int) (tamPant.width*0.05), (int) (tamPant.height*0.13));
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

        policia.setBounds((int) (tamPant.width*0.31), (int) (tamPant.height*0.43), (int) (tamPant.width*0.083), (int) (tamPant.height*0.37));
        policia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                policiaActionPerformed(evt);
            }

        });
        policia.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                policiaMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                policiaMouseExited(evt);
            }
        });
        policia.setOpaque(true);
        policia.setContentAreaFilled(false);
        policia.setBorderPainted(false);
        policia.setFocusPainted(false);


        secretaria.setBounds((int) (tamPant.width*0.68), (int) (tamPant.height*0.45), (int) (tamPant.width*0.11), (int) (tamPant.height*0.49));
        secretaria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                secretariaActionPerformed(evt);
            }

        });
        secretaria.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                secretariaMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                secretariaMouseExited(evt);
            }
        });
        secretaria.setOpaque(true);
        secretaria.setContentAreaFilled(false);
        secretaria.setBorderPainted(false);
        secretaria.setFocusPainted(false);

        if(Juego.getInstance().getPartidaActual().getEventos().isPoliciaSiguiendo())
            policia.setVisible(false);

        add(cajaTexto);
        add(flechaSalida);
        add(flechaBano);
        add(flechaSala1);
        add(flechaPasillo1);
        add(policia);
        add(secretaria);
        lugar.setText("Recepcion");
        lugar.setOpaque(false);
        lugar.setForeground(Color.white);
        lugar.setFont(new Font("Segoe UI", 0, (int) (tamPant.width*0.035)));
        lugar.setBounds((int) (tamPant.width*0.03), (int) (tamPant.height*0.06), (int) (tamPant.width*0.3), (int) (tamPant.height*0.1));
        getContentPane().add(lugar);

        interfazUsuario.setBounds((int) (tamPant.width*0.55), (int) (tamPant.height*0.05), (int) (tamPant.width*0.45), (int) (tamPant.height*0.15));
        getContentPane().add(interfazUsuario);

        getContentPane().add(fondo);
        pack();
        timer2.scheduleAtFixedRate(tarea2, 0, 10);

    }

    private void secretariaMouseExited(MouseEvent evt) {
        BufferedImage imagen7 = null;
        try {
            imagen7 = ImageIO.read(new File("DatosAuxiliares/Personajes/Secretaria.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono7 = new ImageIcon(imagen7.getScaledInstance((int) (tamPant.width*0.11), (int) (tamPant.height*0.49), Image.SCALE_SMOOTH));
        secretaria.setIcon(icono7);
    }

    private void secretariaMouseEntered(MouseEvent evt) {
        BufferedImage imagen7 = null;
        try {
            imagen7 = ImageIO.read(new File("DatosAuxiliares/Personajes/Secretaria BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono7 = new ImageIcon(imagen7.getScaledInstance((int) (tamPant.width*0.11), (int) (tamPant.height*0.49), Image.SCALE_SMOOTH));
        secretaria.setIcon(icono7);
    }

    private void secretariaActionPerformed(ActionEvent evt) {
        if(Juego.getInstance().getPartidaActual().getEventos().getRonda()==0){
            ponerDialogosEstatico(crearDialogoNoDisponible(), 0, true);
        }else if (Juego.getInstance().getPartidaActual().getEventos().getRonda()==1 && !Juego.getInstance().getPartidaActual().getEventos().isSecretariaYa()){
            ponerDialogoSecretaria();
            secretaria.setVisible(false);
        }else
            ponerDialogosEstatico(crearDialogoYaSecre(), 0, true);
    }
    private ArrayList<Dialogo> crearDialogoNoDisponible(){
        ArrayList<Dialogo> dialogosEsta= new ArrayList<>();

        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");
        Dialogo d1= new Dialogo("(No creo que sea lo mas adecuado preguntar a la secretaria acerca del lugar, por ahora.)", "Detective", detective, true);
        Dialogo d2= new Dialogo("(Pospondre su interrogatorio hasta que haya terminado con los trabajadores mas importantes.)", "Detective", detective, true);

        dialogosEsta.add(d1);
        dialogosEsta.add(d2);
        return dialogosEsta;
    }
    private ArrayList<Dialogo> crearDialogoYaSecre(){
        ArrayList<Dialogo> dialogosSecre = new ArrayList<>();

        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");
        Dialogo d1= new Dialogo("(Ya hable con ella)", "Detective", detective, true);
        Dialogo d2= new Dialogo("(Deberia de enfocarme en recorrer el museo y buscar otras pistas.)", "Detective", detective, true);

        dialogosSecre.add(d1);
        dialogosSecre.add(d2);
        return dialogosSecre;
    }
    private void ponerDialogosEstatico(ArrayList<Dialogo> dialogos, int actual, boolean isSecretaria) {
        if(actual<dialogos.size()) {
            Dialogo aux = dialogos.get(actual);
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);
            actual++;
            int finalActual = actual;

            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    if(isSecretaria)
                        EstatiMouseClickedSecre(dialogos, finalActual);
                    else
                        EstatiMouseClickedPoli(dialogos, finalActual);
                }
            });
            cajaTexto.removeAll();
            cajaTexto.add(cT);
        }else {
            if(isSecretaria)
                secretaria.setVisible(true);
            else
                policia.setVisible(true);
            cajaTexto.removeAll();
        }
    }
    private void ponerDialogosEstaticoLlevarsePolicia(ArrayList<Dialogo> dialogos, int actual) {
        if(actual<dialogos.size()) {
            Dialogo aux = dialogos.get(actual);
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);
            actual++;
            int finalActual = actual;

            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                  ponerDialogosEstaticoLlevarsePolicia(dialogos, finalActual);
                }
            });
            cajaTexto.removeAll();
            cajaTexto.add(cT);
        }else {
                Juego.getInstance().getPartidaActual().getEventos().setPoliciaSiguiendo(true);
                cajaTexto.removeAll();
        }
    }
    private void EstatiMouseClickedLevarsePoli(ArrayList<Dialogo> dialogos, int actual){
        ponerDialogosEstaticoLlevarsePolicia(dialogos, actual);
    }
    private void EstatiMouseClickedSecre(ArrayList<Dialogo> dialogos, int actual){
        ponerDialogosEstatico(dialogos, actual, true);
    }
    private void EstatiMouseClickedPoli(ArrayList<Dialogo> dialogos, int actual){
        ponerDialogosEstatico(dialogos, actual, false);
    }
    private void policiaMouseExited(MouseEvent evt) {
        BufferedImage imagen6 = null;
        try {
            imagen6 = ImageIO.read(new File("DatosAuxiliares/Personajes/Policia.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono6 = new ImageIcon(imagen6.getScaledInstance((int) (tamPant.width*0.083), (int) (tamPant.height*0.37), Image.SCALE_SMOOTH));
        policia.setIcon(icono6);
    }

    private void policiaMouseEntered(MouseEvent evt) {
        BufferedImage imagen6 = null;
        try {
            imagen6 = ImageIO.read(new File("DatosAuxiliares/Personajes/Policia BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono6 = new ImageIcon(imagen6.getScaledInstance((int) (tamPant.width*0.083), (int) (tamPant.height*0.37), Image.SCALE_SMOOTH));
        policia.setIcon(icono6);
    }

    private void policiaActionPerformed(ActionEvent evt) {
        if(Juego.getInstance().getPartidaActual().getEventos().getRonda()==4){
            llevarseAlPolicia();
        } else if(!Juego.getInstance().getPartidaActual().getEventos().isPoliciaYa()){
            ponerDialogoPolicia();
        }else{
            ponerDialogosEstatico(crearDialogoPolicia(), 0, false);
        }
    }

    private void llevarseAlPolicia() {
        policia.setVisible(false);
        ponerDialogosEstaticoLlevarsePolicia(crearDialogoLlevarsePolciia(), 0);
    }
    private ArrayList<Dialogo> crearDialogoLlevarsePolciia(){
        ArrayList<Dialogo> dialogos = new ArrayList<>();

        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");
        ImageIcon poli = new ImageIcon("DatosAuxiliares/Personajes/Policia.png");
        Dialogo d1= new Dialogo("Hombre, menuda noche, ya estoy esperando a que se termine.", "Policia", poli, true);
        Dialogo d2= new Dialogo("No te preocupes, ya estamos bastante adelantados respecto al caso.", "Detective", detective, true);
        Dialogo d3= new Dialogo("Menos mal, ya se me estaban congelando algunas partes que es mejor no decir.", "Policia", poli, true);
        Dialogo d4= new Dialogo("Ahora necesito de tu ayuda, estoy buscando el arma homicida, tengo un presentimiento de que puede estar por cualquier parte. Necesito que me ayudes a encontrarla.", "Detective", detective, true);
        Dialogo d5= new Dialogo("No hay problema, despues de todo eres el jefe, debo de seguir tus ordenes.", "Policia", poli, true);
        Dialogo d6= new Dialogo("Perfecto, tiremos este lugar para abajo.", "Detective", detective, true);

        dialogos.add(d1);
        dialogos.add(d2);
        dialogos.add(d3);
        dialogos.add(d4);
        dialogos.add(d5);
        dialogos.add(d6);
        return  dialogos;
    }
    private ArrayList<Dialogo> crearDialogoPolicia(){
        ArrayList<Dialogo> dialogosPolicia = new ArrayList<>();

        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");
        ImageIcon poli = new ImageIcon("DatosAuxiliares/Personajes/Policia.png");
        Dialogo d1= new Dialogo("Hombre, menuda noche, ya estoy esperando a que se termine.", "Policia", poli, true);
        Dialogo d2= new Dialogo("Dimelo a mi. Tambien tengo deseo de cerrar el caso.", "Detective", detective, true);
        Dialogo d3= new Dialogo("Ni que lo digas. Tengo ganas de regresar a la casa para pasar tiempo de calidad con mi esposa", "Policia", poli, true);
        Dialogo d4= new Dialogo("Ejemm, bueno, yo tambien tengo cosas importantes que hacer en mi casa.", "Detective", detective, true);

        dialogosPolicia.add(d1);
        dialogosPolicia.add(d2);
        dialogosPolicia.add(d3);
        dialogosPolicia.add(d4);
        return dialogosPolicia;
    }
    public void ponerDialogoSecretaria() {
        if(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(0).getNodoDialActual() == null || !(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(0).getArbolDial().nodeIsLeaf(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(0).getNodoDialActual()))) {
            if(!(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(0).getNodoDialActual()==null)){
                Dialogo actual = Juego.getInstance().getPartidaActual().getEscenariosMundo().get(0).getDialogoActual();
                if(!actual.getOpciones().isEmpty()){
                    OpcionesDialogos oD = new OpcionesDialogos(new JFrame(), true, actual.getOpciones());
                    oD.setBounds((int) (tamPant.width*0.28),(int) (tamPant.getHeight()*0.37), (int) (tamPant.width*0.48),(int) (tamPant.getHeight()*0.5));
                    oD.setVisible(true);
                }
            }
            Dialogo aux = Juego.getInstance().getPartidaActual().getEscenariosMundo().get(0).getDialogoSiguiente(UnionInterfaces.getInstance().getOpcionDialogo());
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);
            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    secretariaMouseClicked(evt);
                }
            });

            if(UnionInterfaces.getInstance().getOpcionDialogo()!=1)
                UnionInterfaces.getInstance().setOpcionDialogo(1);

            cajaTexto.removeAll();
            cajaTexto.add(cT);
        }else {
            cajaTexto.removeAll();
            Juego.getInstance().getPartidaActual().getEventos().setSecretariaYa(true);
            Juego.getInstance().getPartidaActual().getEventos().cambiarRonda2();
            secretaria.setVisible(true);
        }

    }

    public void ponerDialogoPolicia() {
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
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);
            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    policiaMouseClicked();
                }
            });

            if(UnionInterfaces.getInstance().getOpcionDialogo()!=1)
                UnionInterfaces.getInstance().setOpcionDialogo(1);
            cajaTexto.removeAll();
            cajaTexto.add(cT);
        }else {
            cajaTexto.removeAll();
            Juego.getInstance().getPartidaActual().getEventos().setPoliciaYa(true);
            policia.setVisible(true);
        }

    }
    private void policiaMouseClicked(){
        ponerDialogoPolicia();
    }
    private void secretariaMouseClicked(MouseEvent evt) {
        ponerDialogoSecretaria();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    private void flechaSalidaActionPerformed(ActionEvent evt) {
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDePasos();

        Entrada entrada = new Entrada();
        entrada.setVisible(true);
        tarea2.cancel();
        timer.schedule(tarea, 1000);
    }
    private void flechaBanoActionPerformed(ActionEvent evt) {
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDePasos();

        Bano bano = new Bano();
        bano.setVisible(true);
        tarea2.cancel();
        timer.schedule(tarea, 1000);
    }
    private void flechaSala1ActionPerformed(ActionEvent evt) {
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDePasos();

        Sala sala1 = new Sala();
        sala1.setVisible(true);
        tarea2.cancel();
        timer.schedule(tarea, 1000);
    }
    private void flechaPasillo1ActionPerformed(ActionEvent evt) {
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDePasos();

        Pasillo1 pasillo1 = new Pasillo1();
        pasillo1.setVisible(true);
        tarea2.cancel();
        timer.schedule(tarea, 1000);


    }
    private void flechaSalidaMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha arriba.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaSalida.setIcon(icono);
    }

    private void flechaSalidaMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha arriba BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaSalida.setIcon(icono);
    }
    private void flechaSala1MouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha izquierda.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int)  (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaSala1.setIcon(icono);
    }

    private void flechaSala1MouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha izquierda BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int)  (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaSala1.setIcon(icono);
    }


    private void flechaBanoMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha derecha.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaBano.setIcon(icono);
    }
    private void flechaBanoMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha derecha BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaBano.setIcon(icono);
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
        java.awt.EventQueue.invokeLater(() -> new Recepcion().setVisible(true));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fondo;
    private javax.swing.JPanel cajaTexto;
    private javax.swing.JButton flechaSalida;
    private javax.swing.JButton flechaBano;
    private javax.swing.JButton flechaSala1;
    private javax.swing.JButton flechaPasillo1;
    private javax.swing.JLabel lugar;// End of variables declaration//GEN-END:variables
}
