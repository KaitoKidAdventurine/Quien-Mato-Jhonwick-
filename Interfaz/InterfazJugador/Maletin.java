package Interfaz.InterfazJugador;

import Logica.Juego;
import Logica.Jugador;
import Logica.ObjetoEscenario;
import Logica.Partida;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Maletin extends javax.swing.JDialog {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Maletin.class.getName());
    private Dimension tamPant;
    private JLabel fondo;
   private  ArrayList <JPanel> slotsInventario;
    private JButton botonSalir;


    public Maletin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        slotsInventario = new ArrayList<>();
        initComponents();
    }


    private void initComponents() {
        ponerSlots();
       // ponerObjetos();

        fondo = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setLayout(null);
        setBackground(new Color(0, 0, 0, 75));

        ponerObjetos();

        for(int i = 0; i<8; i++){
            add(slotsInventario.get(i));

        }
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Maleta interior.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.9), tamPant.height, Image.SCALE_SMOOTH));
        fondo.setIcon(icono);
        fondo.setBounds((int) (tamPant.width*0.05), 0,(int) (tamPant.width*0.9),tamPant.height);

        add(fondo);

        botonSalir = new JButton("Salir");
        botonSalir.setBounds((int) (tamPant.width*0.88), (int) (tamPant.height*0.06), (int) (tamPant.width*0.1), (int) (tamPant.height*0.06));
        botonSalir.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.012)));
        botonSalir.setForeground(Color.white);
        botonSalir.setBackground(new Color(100, 13, 13));
        botonSalir.setBorder(new LineBorder(Color.black, 2));
        botonSalir.setFocusPainted(false);
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        getContentPane().add(botonSalir);
        pack();
    }// </editor-fold>

    private void ponerSlots() {
        for(int i =0; i<8; i++){
            JPanel casilla = new JPanel();
            casilla.setBackground(new Color( 0, 0, 0, 80));
            casilla.setLayout(null);
            casilla.setBorder(new LineBorder(new Color(0, 0, 0,150), 2, true) );
            slotsInventario.add(casilla);
        }
        slotsInventario.getFirst().setBounds((int) (tamPant.width*0.183), (int) (tamPant.height*0.173),(int) (tamPant.width*0.305), (int) (tamPant.height*0.171));
        slotsInventario.get(1).setBounds((int) (tamPant.width*0.487), (int) (tamPant.height*0.173),(int) (tamPant.width*0.305), (int) (tamPant.height*0.171));
        slotsInventario.get(2).setBounds((int) (tamPant.width*0.183), (int) (tamPant.height*0.343),(int) (tamPant.width*0.305), (int) (tamPant.height*0.171));
        slotsInventario.get(3).setBounds((int) (tamPant.width*0.487), (int) (tamPant.height*0.343),(int) (tamPant.width*0.305), (int) (tamPant.height*0.171));
        slotsInventario.get(4).setBounds((int) (tamPant.width*0.183), (int) (tamPant.height*0.513),(int) (tamPant.width*0.305), (int) (tamPant.height*0.171));
        slotsInventario.get(5).setBounds((int) (tamPant.width*0.487), (int) (tamPant.height*0.513),(int) (tamPant.width*0.305), (int) (tamPant.height*0.171));
        slotsInventario.get(6).setBounds((int) (tamPant.width*0.183), (int) (tamPant.height*0.683),(int) (tamPant.width*0.305), (int) (tamPant.height*0.171));
        slotsInventario.get(7).setBounds((int) (tamPant.width*0.487), (int) (tamPant.height*0.683),(int) (tamPant.width*0.305), (int) (tamPant.height*0.171));

    }

    private void ponerObjetos() {
        LinkedList<ObjetoEscenario> listaDeObjetos = Jugador.getInstancia().getMaletin();
        int i =0;
        Iterator<ObjetoEscenario> iterator= listaDeObjetos.iterator();
        while(i<listaDeObjetos.size() && i<8 ){
            ObjetoEscenario objetoEscenario = iterator.next();
            SlotInventario slot = new SlotInventario(objetoEscenario.getImagen(), objetoEscenario.getNombre(), objetoEscenario.getDescripcion());
            slotsInventario.get(i).add(slot);
            i++;

        }
        if(i<8){
            for(int j=i; j<8; j++){
                slotsInventario.get(j).setBackground(new Color(0, 0, 0, 140));
            }
        }
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

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Maletin dialog = new Maletin(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify


    // End of variables declaration
}

