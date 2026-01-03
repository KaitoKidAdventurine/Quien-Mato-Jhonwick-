package Interfaz.MiniJuego;

import Interfaz.InterfazJugador.InterfazMenuMinijuego;
import Logica.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

public class MinijuegoInterfaz extends JPanel {
    private Dimension tamPant;
    private JLabel fondo;
    private JLabel labelLista;
    private ArrayList<ObjetoMinijuego> objetosMinijuego;
    private JPanel panelEncontrables;
    private ArrayList<JLabel> objetosEncontrables;
    private ArrayList<ObjetoEscenario> objetosEnc;
    private ArrayList<ObjetoEscenario> objEncontrados;
    private int cantiDePistas;
    private JLabel canPistasMostrable;
    private JButton pista;
    private MiniJuego mini;
    private Timer timerMini1;
    private Timer timerMini2;
    private Timer timerMini3;
    private TimerTask tarea1;
    private TimerTask tarea2;
    private TimerTask tarea3;
    private JPanel contenedorPistas;
    private boolean pistaLista;
    private InterfazMenuMinijuego menu;


    public MinijuegoInterfaz(MiniJuego miniJuego) {

        timerMini1 = new java.util.Timer();
        timerMini2 = new java.util.Timer();
        timerMini3 = new java.util.Timer();
        tarea1 = new TimerTask() {
            @Override
            public void run() {
                contenedorPistas.removeAll();
                pistaLista = true;
                revalidate();
                repaint();
            }
        };
        tarea2 = new TimerTask() {
            @Override
            public void run() {
                contenedorPistas.removeAll();
                pistaLista = true;
                revalidate();
                repaint();
            }
        };
        tarea3 = new TimerTask() {
            @Override
            public void run() {
                contenedorPistas.removeAll();
                pistaLista = true;
                revalidate();
                repaint();
            }
        };
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        objetosMinijuego = new ArrayList<>();
        objetosEncontrables = new ArrayList<>();
        cantiDePistas = 3;
        clonarMinijuego(miniJuego);
        objetosEnc = mini.getListaObjetos();
        objEncontrados = new ArrayList<>(mini.getCola());
        labelLista = new JLabel();
        pista = new JButton();
        canPistasMostrable = new JLabel(Integer.toString(cantiDePistas));
        pistaLista = true;
        contenedorPistas = new JPanel();
        menu = new InterfazMenuMinijuego();
        initComponents();
    }



    private void initComponents() {

        Reproductor reproductor = Reproductor.getInstancia();
        reproductor.cambiarMusicaNombre("Busqueda");
        fondo = new JLabel();
        setLayout(null);
        panelEncontrables = new JPanel();
        setBackground(Color.blue);
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
        panelEncontrables.setBounds(0, 0, (int) (tamPant.width * 0.25), tamPant.height);
        for (int i = 0; i < 5; i++) {
            mini.pedirSiguienteObjeCola();
        }
        panelEncontrables.setBackground(Color.black);
        panelEncontrables.setLayout(null);
        add(panelEncontrables);

        add(menu);
        hacerListaObjetosEncontrables();
        ponerObjetos();

        pista.setBounds((int) (tamPant.width * 0.08),  (int) (tamPant.height* 0.76), (int) (tamPant.width * 0.09), (int) (tamPant.height* 0.11));
        BufferedImage imagenPista = null;
        try {
            imagenPista = ImageIO.read(new File("DatosAuxiliares/Minijuego/Lupa.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon iconoPista = new ImageIcon(imagenPista.getScaledInstance((int) (tamPant.width * 0.09), (int) (tamPant.height* 0.11), Image.SCALE_SMOOTH));
        pista.setIcon(iconoPista);
        pista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usarPista();
            }
        });
        pista.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(cantiDePistas>0) {
                    BufferedImage imagenPista = null;
                    try {
                        imagenPista = ImageIO.read(new File("DatosAuxiliares/Minijuego/Lupa BR.png"));
                    } catch (IOException te) {
                        throw new RuntimeException(te);
                    }
                    ImageIcon iconoPista = new ImageIcon(imagenPista.getScaledInstance((int) (tamPant.width * 0.09), (int) (tamPant.height * 0.11), Image.SCALE_SMOOTH));
                    pista.setIcon(iconoPista);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (cantiDePistas > 0) {
                    BufferedImage imagenPista = null;
                    try {
                        imagenPista = ImageIO.read(new File("DatosAuxiliares/Minijuego/Lupa.png"));
                    } catch (IOException te) {
                        throw new RuntimeException(te);
                    }
                    ImageIcon iconoPista = new ImageIcon(imagenPista.getScaledInstance((int) (tamPant.width * 0.09), (int) (tamPant.height * 0.11), Image.SCALE_SMOOTH));
                    pista.setIcon(iconoPista);
                }
            }
        });
        pista.setBorderPainted(false);
        pista.setContentAreaFilled(false);
        pista.setFocusPainted(false);
        pista.setToolTipText("Usar pista.");
        panelEncontrables.add(pista);

        canPistasMostrable.setBounds((int) (tamPant.width * 0.1),  (int) (tamPant.height* 0.86), (int) (tamPant.width * 0.05), (int) (tamPant.height* 0.08));
        canPistasMostrable.setHorizontalAlignment(SwingConstants.CENTER);
        canPistasMostrable.setFont(new Font("Segoe UI", 0, (int) (tamPant.width*0.018)));
        canPistasMostrable.setForeground(Color.white);
        panelEncontrables.add(canPistasMostrable);


        fondo.setBounds((int) (tamPant.width * 0.23), 0, (int) (tamPant.width * 0.75), tamPant.height);

        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(new File(String.valueOf(mini.getFoto())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width * 0.75), tamPant.height, Image.SCALE_SMOOTH));
        fondo.setIcon(icono);

        BufferedImage imagen3 = null;
        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Borde Minijuego.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width * 0.25), tamPant.height, Image.SCALE_SMOOTH));
        labelLista.setIcon(icono3);
        labelLista.setBounds(0, 0,(int) (tamPant.width * 0.25), tamPant.height );
        panelEncontrables.add(labelLista);

        contenedorPistas.setBounds(0,  0, (int) (tamPant.width * 0.75), tamPant.height);
        contenedorPistas.setLayout(null);
        contenedorPistas.setBackground(new Color(0, 0, 0, 0));
        add(contenedorPistas);
        add(fondo);
    }

    private void buscarObjeto(ActionEvent evt) {
        ObjetoMinijuego accionador = (ObjetoMinijuego) evt.getSource();

        for (int i = 0; i < objetosEnc.size(); i++) {
            if (accionador.getNombreObjeto().equals(objetosEnc.get(i).getNombre())) {
                objetosEnc.remove(i);
                remove(accionador);
                EfectosEspeciales e =EfectosEspeciales.getInstancia();
                e.efectoObjetoEncontrado();
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

    private void ponerObjetos() {
        for (int i = 0; i < objetosMinijuego.size(); i++) {
            add(objetosMinijuego.get(i), i);
        }
    }

    private void usarPista(){
       if(cantiDePistas>0 && pistaLista==true){
           ObjetoMinijuego objetoABuscar = obtenerObjetoRandom();

           ImageIcon iconoOG = new ImageIcon("DatosAuxiliares/Minijuego/Pista.gif");
           Image imgScal = iconoOG.getImage().getScaledInstance(objetoABuscar.getWidth(), objetoABuscar.getHeight(), Image.SCALE_DEFAULT);
           ImageIcon iconoAmpl = new ImageIcon(imgScal, iconoOG.getDescription());

           JLabel pistaEnEscenario = new JLabel(iconoAmpl);
           pistaEnEscenario.setBounds(objetoABuscar.getBounds());

           contenedorPistas.add(pistaEnEscenario);
           switch ( cantiDePistas){
               case 1:
                   timerMini1.schedule(tarea1, 3000);
                   break;
               case 2:
                   timerMini2.schedule(tarea2, 3000);
                   break;
               case 3:
                   timerMini3.schedule(tarea3, 3000);
                   break;
           }

           cantiDePistas --;
           canPistasMostrable.setText(Integer.toString(cantiDePistas));
           revalidate();
           repaint();
           pistaLista=false;
           if(cantiDePistas==0){
                   BufferedImage imagenPista = null;
                   try {
                       imagenPista = ImageIO.read(new File("DatosAuxiliares/Minijuego/Pista Vacia.png"));
                   } catch (IOException te) {
                       throw new RuntimeException(te);
                   }
                   ImageIcon iconoPista = new ImageIcon(imagenPista.getScaledInstance((int) (tamPant.width * 0.09), (int) (tamPant.height * 0.11), Image.SCALE_SMOOTH));
                   pista.setIcon(iconoPista);
           }
       }else
           Toolkit.getDefaultToolkit().beep();
    }

    private ObjetoMinijuego obtenerObjetoRandom() {
        ObjetoMinijuego aux = null;
        int tope = buscarTopeBuscables();
        int numeroABuscar = 0;

        if(tope>1)
            numeroABuscar= new Random().nextInt(tope-1);

        String objetoABuscar = objetosEncontrables.get(numeroABuscar).getText();
        int i =0;
        boolean encontrado = false;
        while(i<objetosMinijuego.size() && !encontrado){
            if(objetosMinijuego.get(i).getNombreObjeto().equals(objetoABuscar)){
                encontrado = true;
                aux = objetosMinijuego.get(i);
            }
            i++;
        }
        return aux;
    }

    private int buscarTopeBuscables() {
        int tope =0;
        for(int i =0; i < 5; i++){
            if(!objetosEncontrables.get(i).getText().isEmpty()){
                tope++;
            }
        }
        return tope;
    }


    private void hacerListaObjetosEncontrables() {

        for (int i = 0; i < 5; i++) {
            JLabel encontrable = new JLabel();
            encontrable.setOpaque(false);
            encontrable.setHorizontalAlignment(SwingConstants.CENTER);
            encontrable.setForeground(Color.white);
            encontrable.setText(objetosEnc.get(i).getNombre());
            encontrable.setBounds((int) (tamPant.width * 0.02), ((int) (tamPant.height * (0.18) + (int) (tamPant.width * ((0.06) * i)))), (int) (tamPant.width * 0.21), (int) (tamPant.height * 0.1));
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