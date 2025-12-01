package Interfaz.InterfazJugador;

import Logica.Juego;
import Logica.Jugador;
import Logica.ObjetoEscenario;
import Logica.Partida;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Maletin extends javax.swing.JDialog {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Maletin.class.getName());
    private Dimension tamPant;
    private JScrollPane scrollObjetos;
    private JPanel panelInventario;


    public Maletin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        initComponents();
    }


    private void initComponents() {
        panelGeneral = new JPanel();

        panelInventario = new JPanel();
        panelInventario.setLayout(new BoxLayout(panelInventario, BoxLayout.Y_AXIS));
        panelInventario.setMaximumSize(new Dimension((int) (tamPant.width*0.35), Integer.MAX_VALUE));

        ponerObjetos();

        scrollObjetos= new JScrollPane();
        scrollObjetos.setViewportView(panelInventario);
        scrollObjetos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollObjetos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollObjetos.getVerticalScrollBar().setUnitIncrement(16);
        scrollObjetos.setBounds(0, 0,(int) (tamPant.width*0.34), (int) (tamPant.height*0.56));

        fondo = new JLabel();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
       setLayout(null);

        panelGeneral.setBounds((int) (tamPant.width*0.02), (int) (tamPant.height*0.02),(int) (tamPant.width*0.35), (int) (tamPant.height*0.57));

        panelGeneral.setBackground(Color.black);
        panelGeneral.setLayout(null);

        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/menu 1.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.4), (int) (tamPant.height*0.65), Image.SCALE_SMOOTH));
        fondo.setIcon(icono);
        fondo.setBounds(0, 0, (int) (tamPant.width*0.4), (int) (tamPant.height*0.65));

        panelGeneral.add(scrollObjetos);
        add(panelGeneral);
        add(fondo);

        pack();
    }// </editor-fold>

    private void ponerObjetos() {
        LinkedList<ObjetoEscenario> listaDeObjetos = Jugador.getInstancia().getMaletin();
int i =0;
        Iterator<ObjetoEscenario> iterator= listaDeObjetos.iterator();
        while(iterator.hasNext()){

            ObjetoEscenario objetoEscenario = iterator.next();
            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            panel.setBounds(0, ((int) (tamPant.height*0.1)*i), (int) (tamPant.width*0.3), (int) (tamPant.height*0.1));
            JPanel panelIzquierdo = new JPanel();
            panelIzquierdo.setBounds(0, 0, (int) (tamPant.width*0.1), (int) (tamPant.height*0.15));
            panelIzquierdo.setBorder(BorderFactory.createLineBorder(Color.black));
            panelIzquierdo.setLayout(null);

            JLabel nombreObj = new JLabel();
            nombreObj.setText(objetoEscenario.getNombre());
            nombreObj.setBounds((int) (tamPant.width*0.11), (int) (tamPant.height*0.01), (int) (tamPant.width*0.3), (int) (tamPant.height*0.05));
            nombreObj.setForeground(Color.black);
            JLabel fotoObje= new JLabel();

            BufferedImage imagen = null;

            try {
                imagen = ImageIO.read(new File(String.valueOf(objetoEscenario.getImagen())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.03), (int) (tamPant.height*0.06), Image.SCALE_SMOOTH));
            fotoObje.setIcon(icono);

            fotoObje.setBounds((int) (tamPant.width*0.005), (int) (tamPant.height*0.005), (int) (tamPant.width*0.09), (int) (tamPant.height*0.15));
            panelIzquierdo.add(fotoObje);

            JTextArea areaTexto = new JTextArea(objetoEscenario.getDescripcion());

            areaTexto.setEditable(false);
            areaTexto.setBackground(new Color(0, 0, 0, 0));
            areaTexto.setColumns(20);
            areaTexto.setFont(new Font("Segoe UI", 1, (int) (tamPant.width*0.01)));
            areaTexto.setRows(5);
            areaTexto.setLineWrap(true);
            areaTexto.setWrapStyleWord(true);
            areaTexto.setOpaque(false);
            areaTexto.setBounds((int) (tamPant.width*0.11), (int) (tamPant.height*0.05), (int) (tamPant.width*0.2), (int) (tamPant.height*0.1));
            areaTexto.setForeground(Color.black);

            panel.add(nombreObj);
            panel.add(areaTexto);
            panel.add(panelIzquierdo);
            i++;
            panelInventario.add(panel);

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

    private javax.swing.JPanel panelGeneral;
    private javax.swing.JLabel fondo;
    // End of variables declaration
}

