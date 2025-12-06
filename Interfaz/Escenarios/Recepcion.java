package Interfaz.Escenarios;

import DatosAuxiliaresLogica.EfectosEspeciales;
import Interfaz.InterfazJugador.InterfazUsuario;
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

public class Recepcion extends JFrame{
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Recepcion.class.getName());
    private Dimension tamPant;
    private Timer timer;
    private TimerTask tarea;
    private InterfazUsuario interfazUsuario;
    private Escenario escenario;
    private GeneralTree<Dialogo> arbolOriginal;
    private GeneralTree<Dialogo> arbolSecretaria;
    private GeneralTree<Dialogo> arbolGuardia;
    private boolean secretariaActivada;
    private boolean hablandoConPolicia;
    private boolean apareceGuardia;
    /**
    /**
     * Creates new form Entrada
     */
    public Recepcion() {
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        initComponents();
        timer = new Timer();
        tarea = new TimerTask() {
            @Override
            public void run() {
                dispose();
            }
        };

        arbolOriginal = construirArbolPolicia();
        arbolSecretaria = construirArbolSecretaria();
        arbolGuardia = construirArbolGuardia();

        secretariaActivada = false;
        hablandoConPolicia = true;
        apareceGuardia =false;

        escenario = new Escenario("Recepción", "Primer sitio dentro del museo", true);
        escenario.setArbolDial(arbolOriginal);
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
        try {
            BufferedImage imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/entrada por dentro.jpg"));


            // Actualizacion de donde esta el Jugador
            Partida p = Partida.getInstance();
            p.buscarEscenarioNombre("Recepcion");


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

            cajaTexto.setOpaque(false);
            cajaTexto.setBounds(220, 280, 1200, 800);
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

        flechaBano.setOpaque(true);
        flechaBano.setContentAreaFilled(false);
        flechaBano.setBorderPainted(false);
        flechaBano.setFocusPainted(false);


        flechaSala1.setBounds((int) (tamPant.width*0.06), (int) (tamPant.height*0.64), (int) (tamPant.width*0.1), (int) (tamPant.height*0.08));
        flechaSala1.setBackground(Color.red);
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
        flechaSala1.setOpaque(true);
        flechaSala1.setContentAreaFilled(false);
        flechaSala1.setBorderPainted(false);
        flechaSala1.setFocusPainted(false);


        flechaPasillo1.setBounds((int) (tamPant.width*0.34), (int) (tamPant.height*0.84), (int) (tamPant.width*0.05), (int) (tamPant.height*0.13));
        flechaPasillo1.setBackground(Color.red);
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
        flechaPasillo1.setOpaque(true);
        flechaPasillo1.setContentAreaFilled(false);
        flechaPasillo1.setBorderPainted(false);
        flechaPasillo1.setFocusPainted(false);


        getContentPane().add(cajaTexto);
        getContentPane().add(flechaSalida);
        getContentPane().add(flechaBano);
        getContentPane().add(flechaSala1);
        getContentPane().add(flechaPasillo1);

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

        Entrada entrada = new Entrada();
        entrada.setVisible(true);
        timer.schedule(tarea, 1000);
    }
    private void flechaBanoActionPerformed(ActionEvent evt) {
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDePasos();

        Bano bano = new Bano();
        bano.setVisible(true);
        timer.schedule(tarea, 1000);
    }
    private void flechaSala1ActionPerformed(ActionEvent evt) {
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDePasos();

        Sala sala1 = new Sala();
        sala1.setVisible(true);
        timer.schedule(tarea, 1000);
    }
    private void flechaPasillo1ActionPerformed(ActionEvent evt) {
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDePasos();

        Pasillo1 pasillo1 = new Pasillo1();
        pasillo1.setVisible(true);
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

    public void activarSecretaria() {    //Llama esto cuando el jugador decide terminar con el policía o cuando intenta hablar con la secretaria.
        secretariaActivada = true;
        hablandoConPolicia = false;
        apareceGuardia = false;
        escenario.setArbolDial(arbolSecretaria);   // cambio de árbol
    }

    public void restaurarPolicia() {    //Si más adelante quieres volver a hablar con el policía
        secretariaActivada = false;
        hablandoConPolicia = true;
        apareceGuardia = false;
        escenario.setArbolDial(arbolOriginal);
    }

    public void consultarGuardia() {
        secretariaActivada = false;
        hablandoConPolicia = false;
        apareceGuardia = true;
        escenario.setArbolDial(arbolGuardia);
    }

    private GeneralTree<Dialogo> construirArbolPolicia() {
        ImageIcon policia = new ImageIcon("DatosAuxiliares/Personajes/Policia.png");
        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");

        Dialogo d1 = new Dialogo("Buenos días. Un empleado apareció muerto en su oficina. Por ahora todo parece suicidio… pero hay detalles que no encajan. ¿Qué quiere saber?", "Policía", policia, true);
        d1.setOpciones(new LinkedList<String>(Arrays.asList("¿Qué ocurrió?", "¿Pruebas?", "¿Puedo hablar con los empleados?")));

        Dialogo d2 = new Dialogo("El cuerpo está en la oficina del economista, puerta entreabierta, sangre seca.", "Policía", policia, true);
        Dialogo d3 = new Dialogo("Lo encontró el guardia al hacer su ronda de las 6.", "Policía", policia, true);
        Dialogo d4 = new Dialogo("Hay un cuchillo y una carta de despedida… pero la letra no coincide.", "Policía", policia, true);
        Dialogo d5 = new Dialogo("Por eso dudo del suicidio. Cuando quiera, hable con los empleados.", "Policía", policia, true);
        Dialogo d6 = new Dialogo("Cuchillo de cocina pequeño; la herida es más ancha y limpia – no encaja.", "Policía", policia, true);
        Dialogo d7 = new Dialogo("La carta está forzada; la firma no es suya.", "Policía", policia, true);
        Dialogo d8 = new Dialogo("Dos indicios de encubrimiento. Necesitamos hablar con todos.", "Policía", policia, true);
        Dialogo d9 = new Dialogo("Claro. La secretaria está en la mesa de recepción; el guardia, revisando cámaras. Pregúnteles.", "Policía", policia, true);
        Dialogo d10 = new Dialogo("Yo me quedaré por aquí si necesita algo más.", "Policía", policia, true);
        Dialogo d11 = new Dialogo("Está bien. Mantenme informado.", "Detective", detective, true);

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

        GeneralTree<Dialogo> aux = new GeneralTree<>();

        aux.insertNode(n1, null);
           aux.insertNode(n2, n1);
              aux.insertNode(n3, n2);
                 aux.insertNode(n4, n3);
                    aux.insertNode(n5, n4);
           aux.insertNode(n6, n1);
              aux.insertNode(n7, n6);
                 aux.insertNode(n8, n7);
           aux.insertNode(n9, n1);
              aux.insertNode(n10, n9);
                 aux.insertNode(n11, n10);

        return aux;
    }

    private GeneralTree<Dialogo> construirArbolSecretaria() {    //Aparece tras el diálogo 11 o evento
        ImageIcon secretaria  = new ImageIcon("DatosAuxiliares/Personajes/Secretaria.png");
        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");

        Dialogo d12 = new Dialogo("Así que usted es la secretaria. ¿Que tal si hago un par de preguntas si no le molesta?", "Detective", secretaria, true);
        Dialogo d13 = new Dialogo("Vale, no hay problema. Estoy dispuesta a colaborar.", "Secretaria", secretaria, true);
        Dialogo d14 = new Dialogo("Muy bien. Quisiera saber si usted y la víctima eran muy cercanos.", "Detective", detective, true);
        Dialogo d15 = new Dialogo("Sí, detective. Yo… yo era pareja de él en secreto.", "Secretaria", secretaria, true);
        Dialogo d16 = new Dialogo("Estaba aterrado: dijo que alguien desviaba fondos del museo.", "Secretaria", secretaria, true);
        Dialogo d17 = new Dialogo("Me dio parte de la contraseña de su PC – el resto está en su libro favorito.", "Secretaria", secretaria, true);
        Dialogo d18 = new Dialogo("Por favor, descubra la verdad.", "Secretaria", secretaria, true);
        Dialogo d19 = new Dialogo("No se preocupe. De eso me encargo yo.", "Detective", detective, true);
        Dialogo d25 = new Dialogo("Si necesita el libro, está en la estantería trasera, tercer estante.", "Secretaria", secretaria, true);
        Dialogo d26 = new Dialogo("Por favor, cuídelo… es el último recuerdo que tengo de él.", "Secretaria", secretaria, true);
        Dialogo d27 = new Dialogo("Lo haré. Gracias por la confianza.", "Detective", detective, true);

        BinaryTreeNode<Dialogo> n12 = new BinaryTreeNode<>(d12);
        BinaryTreeNode<Dialogo> n13 = new BinaryTreeNode<>(d13);
        BinaryTreeNode<Dialogo> n14 = new BinaryTreeNode<>(d14);
        BinaryTreeNode<Dialogo> n15 = new BinaryTreeNode<>(d15);
        BinaryTreeNode<Dialogo> n16 = new BinaryTreeNode<>(d16);
        BinaryTreeNode<Dialogo> n17 = new BinaryTreeNode<>(d17);
        BinaryTreeNode<Dialogo> n18 = new BinaryTreeNode<>(d18);
        BinaryTreeNode<Dialogo> n19 = new BinaryTreeNode<>(d19);
        BinaryTreeNode<Dialogo> n25 = new BinaryTreeNode<>(d25);
        BinaryTreeNode<Dialogo> n26 = new BinaryTreeNode<>(d26);
        BinaryTreeNode<Dialogo> n27 = new BinaryTreeNode<>(d27);

        GeneralTree<Dialogo> aux = new GeneralTree<>();

        aux.insertNode(n12, null);
           aux.insertNode(n13, n12);
              aux.insertNode(n14, n13);
                 aux.insertNode(n15, n14);
                    aux.insertNode(n16, n15);
                       aux.insertNode(n17, n16);
                          aux.insertNode(n18, n17);
                             aux.insertNode(n19, n18);
                                aux.insertNode(n25, n19);
                                   aux.insertNode(n26, n25);
                                      aux.insertNode(n27, n26);

        return aux;
    }

    private GeneralTree<Dialogo> construirArbolGuardia() {    //Aparece tras el diálogo 11 o evento
        ImageIcon guardia = new ImageIcon("DatosAuxiliares/Personajes/Seguridad.jpg");
        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");

        Dialogo d20 = new Dialogo("Veamos, ¿puede decirme que hacía anoche durante su guardia?", "Detective", detective, true);
        Dialogo d21 = new Dialogo("Sí señor. Mi ronda de anoche fue normal… salvo la cámara 2-B.", "Guardia de Seguridad", guardia, true);
        Dialogo d22 = new Dialogo("Falló de 1:00 a 3:00, justo el pasillo este.", "Guardia de Seguridad", guardia, true);
        Dialogo d23 = new Dialogo("Oí un silbido agudo, pero cuando llegué no había nadie.", "Guardia de Seguridad", guardia, true);
        Dialogo d24 = new Dialogo("Si recuerdo algo más, le aviso.", "Guardia de Seguridad", guardia, true);
        Dialogo d28 = new Dialogo("Ok, gracias por la información...", "Detective", detective, true);
        Dialogo d29 = new Dialogo("Ah, y anoté la matrícula de un coche blanco que se detuvo en la puerta trasera a la 1:20.", "Guardia de seguridad", guardia, true);
        Dialogo d30 = new Dialogo("No vi al conductor, pero las luces quedaron encendidas diez minutos.", "Guardia de seguridad", guardia, true);

        BinaryTreeNode<Dialogo> n20 = new BinaryTreeNode<>(d20);
        BinaryTreeNode<Dialogo> n21 = new BinaryTreeNode<>(d21);
        BinaryTreeNode<Dialogo> n22 = new BinaryTreeNode<>(d22);
        BinaryTreeNode<Dialogo> n23 = new BinaryTreeNode<>(d23);
        BinaryTreeNode<Dialogo> n24 = new BinaryTreeNode<>(d24);
        BinaryTreeNode<Dialogo> n28 = new BinaryTreeNode<>(d28);
        BinaryTreeNode<Dialogo> n29 = new BinaryTreeNode<>(d29);
        BinaryTreeNode<Dialogo> n30 = new BinaryTreeNode<>(d30);

        GeneralTree<Dialogo> aux = new GeneralTree<>();

        aux.insertNode(n20, null);
           aux.insertNode(n21, n20);
              aux.insertNode(n22, n21);
                 aux.insertNode(n23, n22);
                    aux.insertNode(n24, n23);
                       aux.insertNode(n28, n24);
                          aux.insertNode(n29, n28);
                             aux.insertNode(n30, n29);

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
