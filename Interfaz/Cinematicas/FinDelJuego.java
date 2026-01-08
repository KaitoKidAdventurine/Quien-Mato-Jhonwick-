package Interfaz.Cinematicas;

import DatosAuxiliaresLogica.EfectosEspeciales;
import DatosAuxiliaresLogica.UnionInterfaces;
import Interfaz.InterfazJugador.CuadroTexto;
import Interfaz.InterfazJugador.OpcionesDialogos;
import Logica.Dialogo;
import Logica.Juego;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FinDelJuego extends JFrame {
    private JLabel fondo;
    private JPanel cajaTexto;
    private Dimension tamPant;

    public FinDelJuego(){
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        fondo = new JLabel();
        cajaTexto = new JPanel();
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

        ponerFondo(0);
        ponerDialogo();

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
            int nivelActualDial = Juego.getInstance().getPartidaActual().getEscenariosMundo().get(10).getArbolDial().nodeLevel(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(0).getNodoDialActual());
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);
            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    finalMouseClicked(evt);
                }
            });

            cajaTexto.removeAll();
            cajaTexto.add(cT);
           if(nivelActualDial==100)
               ponerFondo(nivelActualDial);
            if(UnionInterfaces.getInstance().getOpcionDialogo()!=1)
                UnionInterfaces.getInstance().setOpcionDialogo(1);
        }else {
            cajaTexto.removeAll();
        }
        revalidate();
        repaint();
    }

    private void finalMouseClicked(MouseEvent evt) {
        ponerDialogo();
        revalidate();
        repaint();
    }

    private void ponerFondo(int nivelActualDial) {
        try {
            BufferedImage imagen = null;
            switch (nivelActualDial) {
                case 0:
                    imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/Oficina Victima.png"));
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
