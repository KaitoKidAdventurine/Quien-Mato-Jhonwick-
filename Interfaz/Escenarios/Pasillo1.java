package Interfaz.Escenarios;

import DatosAuxiliaresLogica.EfectosEspeciales;
import DatosAuxiliaresLogica.UnionInterfaces;
import Interfaz.InterfazJugador.InterfazUsuario;
import Interfaz.Menu.MenuPrincipal;
import Logica.Dialogo;
import Logica.Escenario;
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

public class Pasillo1 extends ModeloEscenario {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Pasillo1.class.getName());
    private Dimension tamPant;
    private int dialogoActual;
    private java.util.Timer timer;
    private TimerTask tarea;
    private InterfazUsuario interfazUsuario;
    private Escenario escenario;
    private GeneralTree<Dialogo> arbolOriginal;
    private GeneralTree<Dialogo> arbolLimpiador;
    private int fase;
    private Timer timer2;
    private TimerTask tarea2;
    /**
     * Creates new form Entrada
     */
    public Pasillo1() {
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

        arbolOriginal = construirArbolGuardia();
        arbolLimpiador = construirArbolLimpiador();

        fase = 0;

        escenario = new Escenario("Ala Este", "Sala de exposiciones", true);
        //Es posible no asignar ningun dialogo, si primero se quiere investigar el escenario para luego consultar con el guardia.
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {
        cajaTexto  = new JPanel();
        flechaEntradaDentro = new JButton();
        flechaCamara = new JButton();
        flechaPasillo3 = new JButton();
        lugar = new JLabel();
        interfazUsuario= new InterfazUsuario();
        try {
            BufferedImage imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/pasillo 1.jpg"));

            // Actualizacion de donde esta el Jugador
            Partida p = Partida.getInstance();
            p.buscarEscenarioNombre("Ala este");


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

            BufferedImage imagen3 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha derecha.png"));
            ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
            flechaCamara.setIcon(icono3);

            BufferedImage imagen4 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha derecha.png"));
            ImageIcon icono4 = new ImageIcon(imagen4.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
            flechaPasillo3.setIcon(icono4);

            getContentPane().add(cajaTexto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        flechaEntradaDentro.setBounds((int) (tamPant.width*0.4), (int) (tamPant.height*0.84), (int) (tamPant.width*0.048), (int) (tamPant.height*0.124));
        flechaEntradaDentro.setBackground(Color.red);
        flechaEntradaDentro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                EfectosEspeciales e = EfectosEspeciales.getInstancia();
                e.efectoDePasos();
                flechaEntradaActionPerformed(evt);
            }
        });
        flechaEntradaDentro.setToolTipText("Recepcion");
        flechaEntradaDentro.setOpaque(true);
        flechaEntradaDentro.setContentAreaFilled(false);
        flechaEntradaDentro.setBorderPainted(false);
        flechaEntradaDentro.setFocusPainted(false);
        flechaEntradaDentro.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaEntradaDenMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaEtradaDentMouseExited(evt);
            }
        });

        getContentPane().add(flechaEntradaDentro);



        flechaCamara.setBounds((int) (tamPant.width*0.6), (int) (tamPant.height*0.72), (int) (tamPant.width*0.08), (int) (tamPant.height*0.07));
        flechaCamara.setBackground(Color.red);
        flechaCamara.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                EfectosEspeciales e = EfectosEspeciales.getInstancia();
                e.efectoDePasos();
                flechaCamaraActionPerformed(evt);
            }
        });
        flechaCamara.setToolTipText("Sala de Vigilancia");
        flechaCamara.setOpaque(true);
        flechaCamara.setContentAreaFilled(false);
        flechaCamara.setBorderPainted(false);
        flechaCamara.setFocusPainted(false);
        flechaCamara.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaCamaraMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaCamaraMouseExited(evt);
            }
        });


        getContentPane().add(flechaCamara);


        flechaPasillo3.setBounds((int) (tamPant.width*0.45), (int) (tamPant.height*0.52), (int) (tamPant.width*0.08), (int) (tamPant.height*0.07));
        flechaPasillo3.setBackground(Color.red);
        flechaPasillo3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                EfectosEspeciales e = EfectosEspeciales.getInstancia();
                e.efectoDePasos();
                flechaPasillo3ActionPerformed(evt);
            }
        });
        flechaPasillo3.setToolTipText("Oficinas Planta Baja");
        flechaPasillo3.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaMouseExited(evt);
            }
        });

        flechaPasillo3.setOpaque(true);
        flechaPasillo3.setContentAreaFilled(false);
        flechaPasillo3.setBorderPainted(false);
        flechaPasillo3.setFocusPainted(false);
        getContentPane().add(flechaPasillo3);

        lugar.setText("Ala este");
        lugar.setOpaque(false);
        lugar.setForeground(Color.white);
        lugar.setFont(new Font("Segoe UI", 0, (int) (tamPant.width*0.035)));
        lugar.setBounds((int) (tamPant.width*0.03), (int) (tamPant.height*0.06), (int) (tamPant.width*0.3), (int) (tamPant.height*0.1));
        getContentPane().add(lugar);

        interfazUsuario.setBounds((int) (tamPant.width*0.55), (int) (tamPant.height*0.05), (int) (tamPant.width*0.45), (int) (tamPant.height*0.15));
        getContentPane().add(interfazUsuario);

        getContentPane().add(jLabel1);
        pack();
        timer2.scheduleAtFixedRate(tarea2, 0, 20);
    }

    private void flechaCamaraActionPerformed(ActionEvent evt) {
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDePasos();

        SalaDeCamaras sC = new SalaDeCamaras();
        sC.setVisible(true);
        tarea2.cancel();
        timer.schedule(tarea, 1000);
    }

    public void ponerDialogo() {
    }

    private void cTMouseClicked(MouseEvent evt) {
        ponerDialogo();
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    private void flechaCamaraMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha derecha.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaCamara.setIcon(icono);
    }

    private void flechaCamaraMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha derecha BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaCamara.setIcon(icono);
    }
    private void flechaEntradaActionPerformed(ActionEvent evt) {
        Recepcion entradaD = new Recepcion();
        entradaD.setVisible(true);
        tarea2.cancel();
        timer.schedule(tarea, 1000);
    }
    private void flechaPasillo3ActionPerformed(ActionEvent evt) {
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDePasos();
        Pasillo3 pasillo3 = new Pasillo3();
        pasillo3.setVisible(true);
        tarea2.cancel();
        timer.schedule(tarea, 1000);
    }
    private void flechaEtradaDentMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha abajo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaEntradaDentro.setIcon(icono);
    }

    private void flechaEntradaDenMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha abajo BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaEntradaDentro.setIcon(icono);
    }
    private void flechaMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha derecha.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaPasillo3.setIcon(icono);
    }

    private void flechaMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha derecha BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.073), (int) (tamPant.height*0.063), Image.SCALE_SMOOTH));
        flechaPasillo3.setIcon(icono);
    }

    public void interrogarGuardia() {
        fase = 1;
        escenario.setArbolDial(arbolOriginal);
    }

    public void interrogarLimpiador() {
        fase = 0;
        escenario.setArbolDial(arbolLimpiador);
    }

    private GeneralTree<Dialogo> construirArbolGuardia() {
        ImageIcon guardia = new ImageIcon("DatosAuxiliares/Personajes/Seguridad.jpg");

        Dialogo d1 = new Dialogo("Saludos, detective. ¿En qué le puedo ayudar?", "Guardia de Seguridad", guardia, true);
        d1.setOpciones(new LinkedList<String>(Arrays.asList("¿Qué ocurrió aquí anoche?", "¿Cámaras?", "¿Viste al limpiador?")));

        Dialogo d2 = new Dialogo("Pasé a la 1:30 y todo estaba en calma… pero olió a solvente.", "Guardia de Seguridad", guardia, true);
        Dialogo d3 = new Dialogo("Revisé la cámara 2-B: falló justo esa hora.", "Guardia de Seguridad", guardia, true);
        Dialogo d4 = new Dialogo("Cuando volvió la imagen, el pasillo estaba vacío.", "Guardia de Seguridad", guardia, true);
        Dialogo d5 = new Dialogo("Anoche anoté una matrícula: coche blanco a la 1:20.", "Guardia de Seguridad", guardia, true);
        Dialogo d6 = new Dialogo("Luces encendidas diez minutos; no vi al conductor.", "Guardia de Seguridad", guardia, true);
        Dialogo d7 = new Dialogo("Si recuerdo algo más, le aviso.", "Guardia de Seguridad", guardia, true);
        Dialogo d8 = new Dialogo("Cuidado con el charco, acaban de fregar.", "Guardia de Seguridad", guardia, true);
        Dialogo d9 = new Dialogo("Y mantenga la puerta cerrada, el aire acondicionado se va.", "Guardia de Seguridad", guardia, true);
        Dialogo d10 = new Dialogo("Vuelvo en diez minutos a hacer otra ronda.", "Guardia de Seguridad", guardia, true);
        Dialogo d11 = new Dialogo("Cualquier cosa, habléme por el walkie.", "Guardia de Seguridad", guardia, true);
        Dialogo d12 = new Dialogo("Número de guardia: 331.", "Guardia de Seguridad", guardia, true);
        Dialogo d13 = new Dialogo("Me retiro, buena suerte.", "Guardia de Seguridad", guardia, true);
        Dialogo d14 = new Dialogo("Cámara 2-B apunta al almacén; falló 01:07-03:02.", "Guardia de Seguridad", guardia, true);
        Dialogo d15 = new Dialogo("Registra solo negro; nadie sabe por qué.", "Guardia de Seguridad", guardia, true);
        Dialogo d16 = new Dialogo("El técnico vendrá esta tarde; no toque nada.", "Guardia de Seguridad", guardia, true);
        Dialogo d17 = new Dialogo("Sí, anda desesperado: perdió las llaves del almacén.", "Guardia de Seguridad", guardia, true);
        Dialogo d18 = new Dialogo("Lo vi pasar dos veces con el trapo en la mano.", "Guardia de Seguridad", guardia, true);
        Dialogo d19 = new Dialogo("Dijo que las dejó en el baño de planta baja.", "Guardia de Seguridad", guardia, true);
        Dialogo d20 = new Dialogo("Si aparece, se las devolveré; avíseme.", "Guardia de Seguridad", guardia, true);

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
        aux.insertNode(n14, n1);
        aux.insertNode(n15, n14);
        aux.insertNode(n16, n15);
        aux.insertNode(n17, n1);
        aux.insertNode(n18, n17);
        aux.insertNode(n19, n18);
        aux.insertNode(n20, n19);

        return aux;
    }

    private GeneralTree<Dialogo> construirArbolLimpiador() {
        ImageIcon limpiador = new ImageIcon("DatosAuxiliares/Personajes/Conserge.png");

        Dialogo d1 = new Dialogo("Muy buenas. ¿Qué necesita usted, detective?", "Conserge", limpiador, true);
        d1.setOpciones(new LinkedList<String>(Arrays.asList("¿Qué llaves perdiste?", "¿Viste al jefe?", "¿Puerta secreta?")));

        Dialogo d2 = new Dialogo("Tres llaves grandes, una con cinta roja; abren el depósito.", "Conserge", limpiador, true);
        Dialogo d3 = new Dialogo("Sin ellas no puedo coger los químicos para desinfectar.", "Conserge", limpiador, true);
        Dialogo d4 = new Dialogo("Si las encuentro, seré su amigo para siempre.", "Conserge", limpiador, true);
        Dialogo d5 = new Dialogo("Oí pasos rápidos a la 1:25… zapatos de taco.", "Conserge", limpiador, true);
        Dialogo d6 = new Dialogo("No me atreví a mirar; seguí fregando.", "Conserge", limpiador, true);
        Dialogo d7 = new Dialogo("El jefe me pidió el kit de limpieza ayer.", "Conserge", limpiador, true);
        Dialogo d8 = new Dialogo("Dijo que había derramado vino en su oficina.”", "Conserge", limpiador, true);
        Dialogo d9 = new Dialogo("Raro, porque él solo bebe agua.", "Conserge", limpiador, true);
        Dialogo d10 = new Dialogo("Eso es todo lo que sé.", "Conserge", limpiador, true);
        Dialogo d11 = new Dialogo("Lo vi pasar corriendo hacia su oficina sobre las 23:30.", "Conserge", limpiador, true);
        Dialogo d12 = new Dialogo("No saludó; llevaba un sobre contra el pecho.", "Conserge", limpiador, true);
        Dialogo d13 = new Dialogo("Hay un viejo conducto de servicio… nunca lo he usado.", "Conserge", limpiador, true);
        Dialogo d14 = new Dialogo("Termina en el callejón trasero, detrás de la estatua.", "Conserge", limpiador, true);
        Dialogo d15 = new Dialogo("Cuidado, está lleno de telarañas.", "Conserge", limpiador, true);

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
        aux.insertNode(n4, n3);
        aux.insertNode(n5, n4);
        aux.insertNode(n6, n5);
        aux.insertNode(n7, n6);
        aux.insertNode(n8, n7);
        aux.insertNode(n9, n8);
        aux.insertNode(n10, n9);
        aux.insertNode(n11, n1);
        aux.insertNode(n12, n11);
        aux.insertNode(n13, n1);
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
        java.awt.EventQueue.invokeLater(() -> new Pasillo1().setVisible(true));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel cajaTexto;
    private javax.swing.JButton flechaEntradaDentro;
    private javax.swing.JButton flechaCamara;
    private javax.swing.JButton flechaPasillo3;
    private javax.swing.JLabel lugar;
    // End of variables declaration//GEN-END:variables
}
