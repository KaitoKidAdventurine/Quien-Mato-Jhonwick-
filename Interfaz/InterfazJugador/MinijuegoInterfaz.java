package Interfaz.InterfazJugador;

import Logica.MiniJuego;
import Logica.ObjetoEscenario;

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
    private ArrayList<ObjetoMinijuego> objetosEscenarios;
    private JPanel panelEncontrables;
    private ArrayList<JLabel> objetosEncontrables;
    private ArrayList<ObjetoEscenario> objetosEnc;
    private MiniJuego mini;

    public MinijuegoInterfaz(MiniJuego miniJuego) {

        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        objetosEscenarios = new ArrayList<ObjetoMinijuego>();
        objetosEncontrables = new ArrayList<>();
        objetosEnc = miniJuego.getListaObjetos();
        mini = miniJuego;
        labelLista = new JLabel();
        initComponents(miniJuego);
    }

    private void initComponents(MiniJuego miniJuego) {
        fondo = new JLabel();
        setLayout(null);
        panelEncontrables = new JPanel();
        setBackground(Color.blue);
        Deque<ObjetoEscenario> objetos = miniJuego.getCola();
        Iterator<ObjetoEscenario> II = objetos.iterator();

        while (II.hasNext()) {
            ObjetoEscenario objeto = II.next();
            ObjetoMinijuego boton = new ObjetoMinijuego(objeto.getNombre());

            boton.setBounds((int) (tamPant.width * objeto.getPosX()), (int) (tamPant.height * objeto.getPosY()), (int) (tamPant.width * objeto.getTamLargo()), (int) (tamPant.height * objeto.getTamAncho()));
            BufferedImage imagen = null;
            try {
                imagen = ImageIO.read(new File(String.valueOf(objeto.getImagen())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width * 0.04), (int) (tamPant.height * 0.11), Image.SCALE_SMOOTH));
            boton.setIcon(icono);
            boton.setBorderPainted(false);
            boton.setContentAreaFilled(false);
            boton.setFocusPainted(false);
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    buscarObjeto(evt);
                }
            });
            objetosEscenarios.add(boton);
        }
        panelEncontrables.setBounds((int) (tamPant.width * 0.8), 0, (int) (tamPant.width * 0.2), tamPant.height);
        for (int i = 0; i < 5; i++) {
            miniJuego.pedirSiguienteObjeCola();
        }
        panelEncontrables.setBackground(Color.black);
        panelEncontrables.setLayout(null);
        hacerListaObjetosEncontrables();

        ponerObjetos(miniJuego);
        add(panelEncontrables);

        fondo.setBounds(0, 0, (int) (tamPant.width * 0.8), tamPant.height);

        BufferedImage imagen2 = null;
        try {
            imagen2 = ImageIO.read(new File(String.valueOf(miniJuego.getFoto())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width * 0.8), tamPant.height, Image.SCALE_SMOOTH));
        fondo.setIcon(icono2);

        BufferedImage imagen3 = null;
        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Borde Minijuego.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width * 0.2), tamPant.height, Image.SCALE_SMOOTH));
        labelLista.setIcon(icono3);
        add(fondo);

    }

    private void buscarObjeto(ActionEvent evt) {
        ObjetoMinijuego accionador = (ObjetoMinijuego) evt.getSource();

        for (int i = 0; i < objetosEnc.size(); i++) {

            if (accionador.getNombreObjeto().equals(objetosEnc.get(i).getNombre())) {
                objetosEnc.remove(i);
                accionador.setIcon(new javax.swing.ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"));

                if(!mini.getCola().isEmpty())
                    mini.pedirSiguienteObjeCola();

                actualizarObjetosEncontrables();

            }
        }

        revalidate();
        repaint();

    }

    private void ponerObjetos(MiniJuego miniJuego) {
        for (int i = 0; i < objetosEscenarios.size(); i++) {

            add(objetosEscenarios.get(i), i);
        }
    }

    private void hacerListaObjetosEncontrables() {

        for (int i = 0; i < 5; i++) {
            JLabel encontrable = new JLabel();
            encontrable.setOpaque(false);
            encontrable.setHorizontalAlignment(SwingConstants.CENTER);
            encontrable.setForeground(Color.white);
            encontrable.setText(objetosEnc.get(i).getNombre());
            encontrable.setBounds((int) (tamPant.width * 0.02), ((int) (tamPant.height * (0.4) + (int) (tamPant.width * ((0.05) * i)))), (int) (tamPant.width * 0.15), (int) (tamPant.height * 0.05));
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