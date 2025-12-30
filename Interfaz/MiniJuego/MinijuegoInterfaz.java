package Interfaz.MiniJuego;

import Logica.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;

public class MinijuegoInterfaz extends javax.swing.JPanel {
    private Dimension tamPant;
    private JLabel fondo;
    private JLabel labelLista;
    private ArrayList<ObjetoMinijuego> objetosMinijuego;
    private JPanel panelEncontrables;
    private ArrayList<JLabel> objetosEncontrables;
    private ArrayList<ObjetoEscenario> objetosEnc;
    private ArrayList<ObjetoEscenario> objEncontrados;
    private MiniJuego mini;

    public MinijuegoInterfaz(MiniJuego miniJuego) {

        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        objetosMinijuego = new ArrayList<>();
        objetosEncontrables = new ArrayList<>();
        clonarMinijuego(miniJuego);
        objetosEnc = mini.getListaObjetos();
        objEncontrados = new ArrayList<>(mini.getCola());

        labelLista = new JLabel();
        initComponents();
    }



    private void initComponents() {
        Reproductor r = Reproductor.getInstancia();
        r.cambiarMusicaNombre("Busqueda");

        fondo = new JLabel();
        setLayout(null);
        panelEncontrables = new JPanel();
        setBackground(Color.blue);
        Reproductor reproductor = Reproductor.getInstancia();
        reproductor.musicaDeBusqueda();

        Deque<ObjetoEscenario> objetos = mini.getCola();
        Iterator<ObjetoEscenario> II = objetos.iterator();
        while (II.hasNext()) {
            ObjetoEscenario objeto = II.next();
            ObjetoMinijuego boton = new ObjetoMinijuego(objeto.getNombre());
            boton.setBounds((int) (tamPant.width * objeto.getPosX()), (int) (tamPant.height * objeto.getPosY()), (int) (tamPant.width * objeto.getTamAncho()), (int) (tamPant.height * objeto.getTamLargo()));
            boton.setBorderPainted(false);
            boton.setContentAreaFilled(false);
            boton.setFocusPainted(false);
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    buscarObjeto(evt);
                }
            });
            objetosMinijuego.add(boton);
        }
        panelEncontrables.setBounds((int) (tamPant.width * 0.75), 0, (int) (tamPant.width * 0.25), tamPant.height);
        for (int i = 0; i < 5; i++) {
            mini.pedirSiguienteObjeCola();
        }
        panelEncontrables.setBackground(Color.black);
        panelEncontrables.setLayout(null);
        hacerListaObjetosEncontrables();
        ponerObjetos(mini);
        add(panelEncontrables);

        fondo.setBounds(0, 0, (int) (tamPant.width * 0.75), tamPant.height);

        BufferedImage imagen2 = null;
        try {
            imagen2 = ImageIO.read(new File(String.valueOf(mini.getFoto())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width * 0.75), tamPant.height, Image.SCALE_SMOOTH));
        fondo.setIcon(icono2);

        BufferedImage imagen3 = null;
        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Borde Minijuego.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width * 0.245), tamPant.height, Image.SCALE_SMOOTH));
        labelLista.setIcon(icono3);
        labelLista.setBounds(0, 0,(int) (tamPant.width * 0.25), tamPant.height );
        panelEncontrables.add(labelLista);
        add(fondo);
    }

    private void buscarObjeto(ActionEvent evt) {
        ObjetoMinijuego accionador = (ObjetoMinijuego) evt.getSource();

        for (int i = 0; i < objetosEnc.size(); i++) {

            if (accionador.getNombreObjeto().equals(objetosEnc.get(i).getNombre())) {
                objetosEnc.remove(i);
                remove(accionador);
                objetosMinijuego.remove(accionador);

                if(!mini.getCola().isEmpty())
                    mini.pedirSiguienteObjeCola();

                actualizarObjetosEncontrables();

                if(!objetosMinijuego.isEmpty()){
                    revalidate();
                    repaint();
                }else{
                    getParent().getComponent(0).setVisible(false);
                    getParent().getComponent(2).setVisible(true);
                    getParent().revalidate();
                    getParent().repaint();
                    ponerObjetosEnMochila();
                    getParent().remove(0);
                }

            }
        }



    }
    private void clonarMinijuego(MiniJuego miniJuego) {
        mini = new MiniJuego("Tutorial", miniJuego.getFoto());
        Deque<ObjetoEscenario> objetos = miniJuego.getCola();
        Iterator<ObjetoEscenario> II = objetos.iterator();
        while (II.hasNext()) {
            ObjetoEscenario objeto = II.next();
            mini.agregarObjetoCola(objeto);
        }
    }
    private void ponerObjetosEnMochila() {
        for(int i =0; i< objEncontrados.size(); i++){
            ObjetoEscenario aux = objEncontrados.get(i);
            if(aux.getImportante()){
                Juego.getInstance().getPartidaActual().getJugador().agregarAlMaletin(aux);
            }
        }
    }

    private void ponerObjetos(MiniJuego miniJuego) {
        for (int i = 0; i < objetosMinijuego.size(); i++) {

            add(objetosMinijuego.get(i), i);
        }
    }

    private void hacerListaObjetosEncontrables() {

        for (int i = 0; i < 5; i++) {
            JLabel encontrable = new JLabel();
            encontrable.setOpaque(false);
            encontrable.setHorizontalAlignment(SwingConstants.CENTER);
            encontrable.setForeground(Color.white);
            encontrable.setText(objetosEnc.get(i).getNombre());
            encontrable.setBounds((int) (tamPant.width * 0.02), ((int) (tamPant.height * (0.3) + (int) (tamPant.width * ((0.06) * i)))), (int) (tamPant.width * 0.21), (int) (tamPant.height * 0.1));
            encontrable.setFont(new Font("Segoe UI", 0, (int) (tamPant.width*0.018)));
            objetosEncontrables.add(encontrable);
        }
        addLabels();
    }

    private void addLabels() {
        for (int i = 0; i < 5; i++) {
            JLabel encontrable = objetosEncontrables.get(i);
            panelEncontrables.add(encontrable);
        }
    }

    private void actualizarObjetosEncontrables() {
        for (int i = 0; i < 5; i++) {
            JLabel encontrable = objetosEncontrables.get(i);
            if(i<objetosEnc.size()) {

                encontrable.setText(objetosEnc.get(i).getNombre());
            }else {
                encontrable.setText("");

            }
        }
    }
}