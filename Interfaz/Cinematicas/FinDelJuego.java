package Interfaz.Cinematicas;

import DatosAuxiliaresLogica.EfectosEspeciales;
import DatosAuxiliaresLogica.UnionInterfaces;
import Interfaz.InterfazJugador.CuadroTexto;
import Interfaz.InterfazJugador.OpcionesDialogos;
import Interfaz.Menu.MenuPrincipal;
import Logica.Dialogo;
import Logica.Juego;
import Logica.Reproductor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class FinDelJuego extends JFrame {
    private Timer timer;
    private TimerTask tarea;
    private JLabel fondo;
    private JPanel cajaTexto;
    private Dimension tamPant;

    public FinDelJuego(){
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        fondo = new JLabel();
        cajaTexto = new JPanel();
        timer = new Timer();
        tarea = new TimerTask() {
            @Override
            public void run() {
                if(UnionInterfaces.getInstance().getCerrarVentana()){
                    MenuPrincipal menu = new MenuPrincipal();
                    menu.setVisible(true);
                    UnionInterfaces.getInstance().setCerrarVentana(false);
                    dispose();
                    tarea.cancel();
                }else{
                    revalidate();
                    repaint();
                }
            }
        };

        initComponents();
    }

    public void initComponents(){

        BufferedImage imagenCursor =null;
        try {
            imagenCursor =  ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Cursor.png"));
        } catch (IOException te) {
            throw new RuntimeException(te);
        }
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(imagenCursor, new Point(20, 0), "Cursor detective");
        setCursor(cursor);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(null);
        setMinimumSize(tamPant);
        setUndecorated(true);
        setPreferredSize(tamPant);
        getContentPane().setLayout(null);
        setBackground(new Color(0, 0, 0, 0));

        cajaTexto.setOpaque(false);
        cajaTexto.setBounds(0, 0, tamPant.width, tamPant.height);
        cajaTexto.setLayout(null);


        fondo.setFocusable(false);
        fondo.setMaximumSize(tamPant);
        fondo.setMinimumSize(tamPant);
        fondo.setPreferredSize(tamPant);
        fondo.setBounds(0, 0, tamPant.width, tamPant.height);

        getContentPane().add(cajaTexto);
        getContentPane().add(fondo);

        ponerFondo(0, UnionInterfaces.getInstance().getOpcionDialogo());
        ponerDialogo();
timer.scheduleAtFixedRate(tarea, 0, 10);
        pack();
    }

    private void ponerDialogo() {
        if(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(10).getNodoDialActual() == null || !(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(10).getArbolDial().nodeIsLeaf(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(10).getNodoDialActual()))) {
            if(!(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(10).getNodoDialActual()==null)){
                Dialogo actual = Juego.getInstance().getPartidaActual().getEscenariosMundo().get(10).getDialogoActual();
                if(!actual.getOpciones().isEmpty()){
                    OpcionesDialogos oD = new OpcionesDialogos(new JFrame(), true, actual.getOpciones());
                    oD.setBounds((int) (tamPant.width*0.28),(int) (tamPant.getHeight()*0.37), (int) (tamPant.width*0.48),(int) (tamPant.getHeight()*0.5));
                    oD.setVisible(true);
                }
            }
            Dialogo aux = Juego.getInstance().getPartidaActual().getEscenariosMundo().get(10).getDialogoSiguiente(UnionInterfaces.getInstance().getOpcionDialogo());
            int nivelActualDial = Juego.getInstance().getPartidaActual().getEscenariosMundo().get(10).getArbolDial().nodeLevel(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(10).getNodoDialActual());
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono(),!aux.getTextoImport().isEmpty());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);
            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    finalMouseClicked(evt);
                }
            });

            EfectosEspeciales e = EfectosEspeciales.getInstancia();
            e.detenerSiEsNecesario();

            cajaTexto.removeAll();
            cajaTexto.add(cT);
           if(nivelActualDial==22 ||nivelActualDial==16 || nivelActualDial==30 || nivelActualDial==34 || nivelActualDial==52 ||
                   nivelActualDial==53 || nivelActualDial==54 || nivelActualDial==56 )
               ponerFondo(nivelActualDial, UnionInterfaces.getInstance().getOpcionDialogo());
            if(UnionInterfaces.getInstance().getOpcionDialogo()!=1)
                UnionInterfaces.getInstance().setOpcionDialogo(1);

            Reproductor reproductor = Reproductor.getInstancia();

            if (aux.getTexto().equals("-Slapp.-"))
               e.efectoRomperPuerta();

           if (aux.getTexto().equals("(Bip.)"))
               e.efectoLlamadaTelefono();

           if (aux.getTexto().equals("¿Qué haces aquí? No tengo tiempo para tus teorías."))
                reproductor.musicaTension();
           if (aux.getTexto().equals("*El jefe saca un arma y dispara. El proyectil impacta en el hombro de tu compañero, que cae al suelo.*"))
               e.efectoRevolver();

           if (aux.getTexto().equals("*Disparas. El jefe cae al suelo, sin vida. El museo queda en silencio.*"))
                e.efectoPistola();

           if (aux.getTexto().equals("*El jefe, temblando, deja caer el arma. Se sienta, derrotado, con la mirada perdida.*"))
               e.efectoArmaCallendo();

           if (aux.getTexto().equals("*La policía llega. El jefe es arrestado. Tu compañero es trasladado al hospital. Afuera, las sirenas se mezclan con el murmullo de los curiosos.*"))
                e.efectoAmbulancia();

        }else {
            cajaTexto.removeAll();
            ponerCreditos();
        }

    }

    private void ponerCreditos() {
        CreditosFinales creditos = new CreditosFinales();
        cajaTexto.add(creditos);
        revalidate();
        repaint();
    }

    private void finalMouseClicked(MouseEvent evt) {
        ponerDialogo();
        revalidate();
        repaint();
    }

    private void ponerFondo(int nivelActualDial, int opcionDialogo) {
        try {
            BufferedImage imagen = null;
            switch (nivelActualDial) {
                case 0:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Cinematica/Final 1.png"));
                    break;
                case 16:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/Oficina Victima.png"));
                    break;
                case 22:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/Entrada.jpg"));
                    break;
                case 30:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Cinematica/Final 2.png"));
                    break;
                case 34:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Cinematica/Final 3.png"));
                    break;
                case 52:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Cinematica/Final 4.png"));
                    break;
                case 53:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Cinematica/Final 5.png"));
                    break;
                case 54:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Cinematica/Final 6 .png"));
                    break;
                case 56:
                    if(opcionDialogo==1) {
                        imagen = ImageIO.read(new File("DatosAuxiliares/Cinematica/Final asesinado.png"));
                    }else
                        imagen = ImageIO.read(new File("DatosAuxiliares/Cinematica/Final arrestado.png"));
                    break;
                default:
                    break;
            }

            ImageIcon icono = new ImageIcon(imagen.getScaledInstance(tamPant.width, tamPant.height, Image.SCALE_SMOOTH) );
            fondo.setIcon(icono);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
}
