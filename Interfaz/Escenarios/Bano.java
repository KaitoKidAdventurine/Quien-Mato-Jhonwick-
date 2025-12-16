package Interfaz.Escenarios;

import DatosAuxiliaresLogica.EfectosEspeciales;
import DatosAuxiliaresLogica.UnionInterfaces;
import Interfaz.InterfazJugador.InterfazUsuario;
import Interfaz.Menu.MenuPrincipal;
import Logica.Dialogo;
import Logica.Escenario;
import Logica.Jugador;
import Logica.Partida;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.general.GeneralTree;

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
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class Bano extends ModeloEscenario {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Bano.class.getName());
    private Dimension tamPant;
    private int dialogoActual;
    private Timer timer;
    private TimerTask tarea;
    private Timer timer2;
    private TimerTask tarea2;
    private InterfazUsuario interfazUsuario;
    private Escenario escenario;
    private GeneralTree<Dialogo> arbolConserje;
    private GeneralTree<Dialogo> arbolSecretaria;
    private boolean conserjeActivo = false;
    private boolean secretariaActiva = false;
    /**
     * Creates new form Entrada
     */
    public Bano() {
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

        arbolConserje = construirArbolConserje();
        arbolSecretaria = construirArbolSecretaria();

        conserjeActivo = false;
        secretariaActiva = false;

        escenario = new Escenario("Baño Planta Baja", "Primer área de aseo, contiguo a la entrada", true);
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {
        cajaTexto  = new JPanel();
        flechaEntradaDentro = new JButton();
        lugar = new JLabel();
        interfazUsuario= new InterfazUsuario();
        try {
            BufferedImage imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/baño 1.jpg"));

            // Actualizacion de donde esta el Jugador
            Partida p = Partida.getInstance();
            p.buscarEscenarioNombre("Banno Planta Baja");

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

            cajaTexto.setOpaque(false);
            cajaTexto.setBounds(220, 280, 1200, 800);
            cajaTexto.setLayout(null);


            BufferedImage imagen2 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha abajo.png"));
            ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
            flechaEntradaDentro.setIcon(icono2);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        flechaEntradaDentro.setBounds((int) (tamPant.width*0.35), (int) (tamPant.height*0.83), (int) (tamPant.width*0.048), (int) (tamPant.height*0.124));
        flechaEntradaDentro.setBackground(Color.red);
        flechaEntradaDentro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaSalidaActionPerformed(evt);
            }
        });
        flechaEntradaDentro.setToolTipText("Recepcion");
        flechaEntradaDentro.setOpaque(true);
        flechaEntradaDentro.setContentAreaFilled(false);
        flechaEntradaDentro.setBorderPainted(false);
        flechaEntradaDentro.setFocusPainted(false);

        flechaEntradaDentro.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaEntradaDentroMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaEntradaDentroMouseExited(evt);
            }
        });


        getContentPane().add(cajaTexto);
        getContentPane().add(flechaEntradaDentro);

        lugar.setText("Baño Planta Baja");
        lugar.setOpaque(false);
        lugar.setForeground(Color.white);
        lugar.setFont(new Font("Segoe UI", 0, (int) (tamPant.width*0.035)));
        lugar.setBounds((int) (tamPant.width*0.03), (int) (tamPant.height*0.06), (int) (tamPant.width*0.35), (int) (tamPant.height*0.1));
        getContentPane().add(lugar);

        interfazUsuario.setBounds((int) (tamPant.width*0.55), (int) (tamPant.height*0.05), (int) (tamPant.width*0.45), (int) (tamPant.height*0.15));
        getContentPane().add(interfazUsuario);

        getContentPane().add(jLabel1);
        pack();
        timer2.scheduleAtFixedRate(tarea2, 0, 20);
    }

    private void flechaEntradaDentroMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha abajo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaEntradaDentro.setIcon(icono);
    }

    private void flechaEntradaDentroMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha abajo BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaEntradaDentro.setIcon(icono);
    }

    public void ponerDialogo() {
    }

    private void cTMouseClicked(MouseEvent evt) {
        ponerDialogo();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    private void flechaSalidaActionPerformed(ActionEvent evt) {
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDePasos();

        Recepcion entradaD = new Recepcion();
        entradaD.setVisible(true);
        tarea2.cancel();
        timer.schedule(tarea, 1000);
    }

    public void activarLimpiador() {
        conserjeActivo = true;
        secretariaActiva = false;
        escenario.setArbolDial(arbolConserje);
    }

    public void activarSecretaria() {
        conserjeActivo = false;
        secretariaActiva = true;
        escenario.setArbolDial(arbolSecretaria);
    }

    public GeneralTree<Dialogo> construirArbolConserje() {
        ImageIcon conserje = new ImageIcon("DatosAuxiliares/Personajes/Conserge.png");
        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");

        Dialogo d1 = new Dialogo("Pero que...?", "Detective", detective, true);
        d1.setOpciones(new LinkedList<String>(Arrays.asList("¿Qué haces en el baño?", "¿Este olor a cloro es normal?", "¿Viste a alguien más?")));

        Dialogo d2 = new Dialogo("Rebusco entre los basureros; creí que dejé aquí las llaves.", "Conserje", conserje, true);
        Dialogo d3 = new Dialogo("Si no las encuentro, no puedo abrir el almacén.", "Conserje", conserje, true);
        Dialogo d4 = new Dialogo("El jefe me va a regañar otra vez.", "Conserje", conserje, true);
        Dialogo d5 = new Dialogo("¿Podría echarle un vistazo al suelo?", "Conserje", conserje, true);
        Dialogo d6 = new Dialogo("Claro. Adelante.", "Detective", detective, true);
        Dialogo d7 = new Dialogo("(Unos minutos más tarde...)", "", null, true);
        Dialogo d8 = new Dialogo("¡Aquí están! Gracias.", "Conserje", conserje, true);
        Dialogo d9 = new Dialogo("Noto olor a cloro muy fuerte… alguien limpió antes.", "Conserje", conserje, true);
        Dialogo d10 = new Dialogo("Esta cinta rota de embalar no es mía… uso cinta azul.", "Conserje", conserje, true);
        Dialogo d11 = new Dialogo("Guárdela; puede ser útil.", "Conserje", conserje, true);
        Dialogo d12 = new Dialogo("Vale, si usted lo dice...", "Detective", detective, true);
        Dialogo d13 = new Dialogo("Ya voy al almacén. Avíseme si necesita algo.", "Conserje", conserje, true);
        Dialogo d14 = new Dialogo("Y por favor, sea cuidadoso, que el suelo está resbaladizo; agárrese al pasamanos.", "Conserje", conserje, true);
        Dialogo d15 = new Dialogo("Cierro un momento, así no entra nadie más.", "Conserje", conserje, true);
        Dialogo d16 = new Dialogo("Si ve al jefe, dígale que ya tengo las llaves.", "Conserje", conserje, true);
        Dialogo d17 = new Dialogo("Y recuerde: cloro puro es peligroso.", "Conserje", conserje, true);
        Dialogo d18 = new Dialogo("Hasta luego, detective.", "Conserje", conserje, true);
        Dialogo d19 = new Dialogo("(Se va, puerta se cierra)", "", null, true);
        Dialogo d20 = new Dialogo("No; yo uso lejía diluida, este es cloro puro… industrial.", "Conserje", conserje, true);
        Dialogo d21 = new Dialogo("Significa que alguien ha querido borrar manchas difíciles… o sangre.", "Conserje", conserje, true);
        Dialogo d22 = new Dialogo("A la secretaria entró antes que yo; salió rápido.", "Conserje", conserje, true);
        Dialogo d23 = new Dialogo("Parecía nerviosa y se guardó algo en el bolso.", "Conserje", conserje, true);

        BinaryTreeNode<Dialogo> n1 = new BinaryTreeNode<>(d1);
        BinaryTreeNode<Dialogo> n2 = new BinaryTreeNode<>(d2);
        BinaryTreeNode<Dialogo> n3 = new BinaryTreeNode<>(d3);
        BinaryTreeNode<Dialogo> n4 = new BinaryTreeNode<>(d4);
        BinaryTreeNode<Dialogo> n5 = new BinaryTreeNode<>(d5);
        BinaryTreeNode<Dialogo> n6 = new BinaryTreeNode<>(d6);
        BinaryTreeNode<Dialogo> n7 = new BinaryTreeNode<>(d7);
        BinaryTreeNode<Dialogo> n8 = new BinaryTreeNode<>(d8);
        BinaryTreeNode<Dialogo> n9 = new BinaryTreeNode<>(d9);
        BinaryTreeNode<Dialogo> n10 = new BinaryTreeNode<>(d10);
        BinaryTreeNode<Dialogo> n11 = new BinaryTreeNode<>(d11);
        BinaryTreeNode<Dialogo> n12 = new BinaryTreeNode<>(d12);
        BinaryTreeNode<Dialogo> n13 = new BinaryTreeNode<>(d13);
        BinaryTreeNode<Dialogo> n14 = new BinaryTreeNode<>(d14);
        BinaryTreeNode<Dialogo> n15 = new BinaryTreeNode<>(d15);
        BinaryTreeNode<Dialogo> n16 = new BinaryTreeNode<>(d16);
        BinaryTreeNode<Dialogo> n17 = new BinaryTreeNode<>(d17);
        BinaryTreeNode<Dialogo> n18 = new BinaryTreeNode<>(d18);
        BinaryTreeNode<Dialogo> n19 = new BinaryTreeNode<>(d19);
        BinaryTreeNode<Dialogo> n20 = new BinaryTreeNode<>(d20);
        BinaryTreeNode<Dialogo> n21 = new BinaryTreeNode<>(d21);
        BinaryTreeNode<Dialogo> n22 = new BinaryTreeNode<>(d22);
        BinaryTreeNode<Dialogo> n23 = new BinaryTreeNode<>(d23);

        GeneralTree<Dialogo> aux = new GeneralTree<>();

        aux.insertNode(n1, null);
           aux.insertNode(n2, n1);
              aux.insertNode(n3, n2);
                 aux.insertNode(n4, n3);
                    aux.insertNode(n5, n4);
                       aux.insertNode(n6, n5);
                          aux.insertNode(n7, n6);
                             aux.insertNode(n8, n7);
                                aux.insertNode(n9, n8);
                                   aux.insertNode(n10, n9);
                                      aux.insertNode(n11, n10);
                                         aux.insertNode(n12, n11);
                                            aux.insertNode(n13, n12);
                                               aux.insertNode(n14, n13);
                                                  aux.insertNode(n15, n14);
                                                     aux.insertNode(n16, n15);
                                                        aux.insertNode(n17, n16);
                                                           aux.insertNode(n18, n17);
                                                              aux.insertNode(n19, n18);
           aux.insertNode(n20, n1);
              aux.insertNode(n21, n20);
           aux.insertNode(n22, n1);
              aux.insertNode(n23, n22);

        return aux;
    }

    public GeneralTree<Dialogo> construirArbolSecretaria() {
        ImageIcon secretaria  = new ImageIcon("DatosAuxiliares/Personajes/Secretaria.png");
        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");

        Dialogo d1 = new Dialogo("Espera un momento...", "Detective", detective, true);
        d1.setOpciones(new LinkedList<String>(Arrays.asList("¿Por qué estás en el baño?", "¿Viste al limpiador?", "¿Estás nerviosa?")));

        Dialogo d2 = new Dialogo("Vengo a retocarme… y a tirar algo.", "Secretaria", secretaria, true);
        Dialogo d3 = new Dialogo("(Te entrega una nota)", "", null, true);
        Dialogo d4 = new Dialogo("Es la contraseña completa; no la quiero ver más.", "Secretaria", secretaria, true);
        Dialogo d5 = new Dialogo("Sí, entró con sus bayetas… y con prisa.", "Secretaria", secretaria, true);
        Dialogo d6 = new Dialogo("Me pareció que escondía algo debajo del fregadero.", "Secretaria", secretaria, true);
        Dialogo d7 = new Dialogo("El jefe me pidió que borrara un archivo… y no sé si hacerlo.", "Secretaria", secretaria, true);
        Dialogo d8 = new Dialogo("Dile que no lo hice… todavía.", "Secretaria", secretaria, true);
        Dialogo d9 = new Dialogo("Confío en que descubra la verdad.", "Secretaria", secretaria, true);
        Dialogo d10 = new Dialogo("Debo volver a recepción antes de que me busque.", "Secretaria", secretaria, true);
        Dialogo d11 = new Dialogo("Cuídese, detective.", "Secretaria", secretaria, true);
        Dialogo d12 = new Dialogo("Gracias, es lo que hago siempre", "Detective", detective, true);
        Dialogo d13 = new Dialogo("(Se retoca el lápiz labial)", "", null, true);
        Dialogo d14 = new Dialogo("Perdón por el momento. Y gracias por escucharme.", "Secretaria", secretaria, true);
        Dialogo d15 = new Dialogo("(Sale rápido)", "", null, true);

        BinaryTreeNode<Dialogo> n1 = new BinaryTreeNode<>(d1);
        BinaryTreeNode<Dialogo> n2 = new BinaryTreeNode<>(d2);
        BinaryTreeNode<Dialogo> n3 = new BinaryTreeNode<>(d3);
        BinaryTreeNode<Dialogo> n4 = new BinaryTreeNode<>(d4);
        BinaryTreeNode<Dialogo> n5 = new BinaryTreeNode<>(d5);
        BinaryTreeNode<Dialogo> n6 = new BinaryTreeNode<>(d6);
        BinaryTreeNode<Dialogo> n7 = new BinaryTreeNode<>(d7);
        BinaryTreeNode<Dialogo> n8 = new BinaryTreeNode<>(d8);
        BinaryTreeNode<Dialogo> n9 = new BinaryTreeNode<>(d9);
        BinaryTreeNode<Dialogo> n10 = new BinaryTreeNode<>(d10);
        BinaryTreeNode<Dialogo> n11 = new BinaryTreeNode<>(d11);
        BinaryTreeNode<Dialogo> n12 = new BinaryTreeNode<>(d12);
        BinaryTreeNode<Dialogo> n13 = new BinaryTreeNode<>(d13);
        BinaryTreeNode<Dialogo> n14 = new BinaryTreeNode<>(d14);
        BinaryTreeNode<Dialogo> n15 = new BinaryTreeNode<>(d15);

        GeneralTree<Dialogo> aux = new GeneralTree<>();

        aux.insertNode(n1, null);
           aux.insertNode(n2, n1);
              aux.insertNode(n3, n2);
           aux.insertNode(n4, n1);
              aux.insertNode(n5, n4);
           aux.insertNode(n6, n1);
              aux.insertNode(n7, n6);
                 aux.insertNode(n8, n7);
                    aux.insertNode(n9, n8);
                       aux.insertNode(n10, n9);
                          aux.insertNode(n11, n10);
                             aux.insertNode(n12, n11);
                                aux.insertNode(n13, n12);
                                   aux.insertNode(n14, n13);
                                      aux.insertNode(n15, n14);

        return aux;
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
        java.awt.EventQueue.invokeLater(() -> new Bano().setVisible(true));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel cajaTexto;
    private javax.swing.JButton flechaEntradaDentro;
    private javax.swing.JLabel lugar;
    // End of variables declaration//GEN-END:variables
}