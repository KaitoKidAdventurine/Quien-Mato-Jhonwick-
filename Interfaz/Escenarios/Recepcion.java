package Interfaz.Escenarios;

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
    private boolean secretariaActivada;    //Ya se mostró?
    private boolean hablandoConPolicia;    //Estado inicial
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
        Entrada entrada = new Entrada();
        entrada.setVisible(true);
        timer.schedule(tarea, 1000);
    }
    private void flechaBanoActionPerformed(ActionEvent evt) {
        Bano bano = new Bano();
        bano.setVisible(true);
        timer.schedule(tarea, 1000);
    }
    private void flechaSala1ActionPerformed(ActionEvent evt) {
        Sala sala1 = new Sala();
        sala1.setVisible(true);
        timer.schedule(tarea, 1000);
    }
    private void flechaPasillo1ActionPerformed(ActionEvent evt) {

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
        if (!secretariaActivada) {
            secretariaActivada = true;
            hablandoConPolicia = false;
            escenario.setArbolDial(arbolSecretaria);   // cambio de árbol
        }
    }

    public void restaurarPolicia() {    //Si más adelante quieres volver a hablar con el policía (No se vuelve hablar con la secretaria)
        hablandoConPolicia = true;
        escenario.setArbolDial(arbolOriginal);
    }

    private GeneralTree<Dialogo> construirArbolPolicia() {
        ImageIcon policia = new ImageIcon("DatosAuxiliares/Personajes/Policia.png");
        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");

        Dialogo d1 = new Dialogo("Buenos días. Soy el oficial encargado. Anoche murió uno de sus empleados. Por ahora todo parece un suicidio… pero hay detalles que no encajan. ¿Va a colaborar?", "Policía", policia, true);
        d1.setOpciones(new LinkedList<>(Arrays.asList("Qué ocurrió anoche?", "Necesito hablar con los empleados", "¿Puedo inspeccionar la entrada?", "¿Has revisado el libro de visitas?", "Gracias, seguiré solo")));

        Dialogo d2 = new Dialogo("El cuerpo está en la oficina del economista, puerta entreabierta, sangre seca. Hay un cuchillo y una nota.", "Policía", policia, true);
        d2.setOpciones(new LinkedList<>(Arrays.asList("¿Quién lo encontró?", "¿Qué pruebas hay?")));

        Dialogo d3 = new Dialogo("Fue el guardia nocturno, Jaime. Hace su ronda a las 6:00 y la encontró.", "Policía", policia, true);
        d3.setOpciones(new LinkedList<>(Arrays.asList("¿A qué hora?", "¿Dónde estaba el guardia?")));

        Dialogo d4 = new Dialogo("Entre 6:05 y 6:10. Llama al 112 a las 6:12.", "Policía", policia, true);

        Dialogo d5 = new Dialogo("En el pasillo del ala este; su recorrido pasa frente a esa oficina justo al final del turno.", "Policía", policia, true);

        Dialogo d6 = new Dialogo("Cuchillo de cocina pequeño, hoja de 10 cm. La carta es un ‘adiós’… pero la letra no es suya.", "Policía", policia, true);
        d6.setOpciones(new LinkedList<>(Arrays.asList("¿El cuchillo coincide?", "¿La carta es auténtica?")));

        Dialogo d7 = new Dialogo("No coincide: la herida es más ancha y limpia. Algo como una hoja de esgrima encajaría mejor.", "Policía", policia, true);

        Dialogo d8 = new Dialogo("Autenticidad cero. Comparé la firma: la nota está forzada.", "Policía", policia, true);

        Dialogo d9 = new Dialogo("Todos están dentro. Pregunte por ellos cuando quiera.", "Policía", policia, true);
        d9.setOpciones(new LinkedList<>(Arrays.asList("¿Dónde está la secretaria?", "¿Y el guardia de seguridad?")));

        Dialogo d10 = new Dialogo("En su mesa de recepción, al fondo a la izquierda.", "Policía", policia, true);

        Dialogo d11 = new Dialogo("Revisando las cámaras en la sala de vigilancia; suba las escaleras, primera puerta.", "Policía", policia, true);

        Dialogo d12 = new Dialogo("Claro. La entrada principal tiene el detector apagado por las obras; cualquiera podría haberse colado.", "Policía", policia, true);

        Dialogo d13 = new Dialogo("Encarguen la lista de visitantes si cree que alguien externo entró.", "Policía", policia, true);

        Dialogo d14 = new Dialogo("Sí, pero anoche no consta nadie después de las 20:00… sospechoso, ¿no?", "Policía", policia, true);
        d14.setOpciones(new LinkedList<>(Arrays.asList("¿Algún nombre raro?", "¿Horarios anómalos?")));

        Dialogo d15 = new Dialogo("Un tal ‘Mario L.’ firmó a las 19:50 y no registró salida. Estoy investigando quién es.", "Policía", policia, true);

        Dialogo d16 = new Dialogo("La última firma de salida es 19:30. A partir de ahí, nada… huele a intruso.", "Policía", policia, true);

        Dialogo d17 = new Dialogo("Gracias, oficial. Revisaré todo por mi cuenta.", "Jugador", detective, true);

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

        GeneralTree<Dialogo> aux = new GeneralTree<>();

        aux.insertNode(n1, null);    //Cada nivel de bloque de código representa el nivel del arbol y, antes de volver al nivel actual de un nodo, se tienen todos sus hijos.
           aux.insertNode(n2, n1);
              aux.insertNode(n3, n2);
                 aux.insertNode(n4, n3);
                 aux.insertNode(n5, n3);
              aux.insertNode(n6, n2);
                 aux.insertNode(n7, n6);
                 aux.insertNode(n8, n6);
           aux.insertNode(n9, n1);
              aux.insertNode(n10, n9);
              aux.insertNode(n11, n9);
           aux.insertNode(n12, n1);
              aux.insertNode(n13, n12);
           aux.insertNode(n14, n1);
              aux.insertNode(n15, n14);
              aux.insertNode(n16, n14);
           aux.insertNode(n17, n1);

        return aux;
    }

    private GeneralTree<Dialogo> construirArbolSecretaria() {
        ImageIcon secretaria  = new ImageIcon("DatosAuxiliares/Personajes/Secretaria.png");

        Dialogo d18 = new Dialogo("¿Busca algo? Yo ya dije lo que sabía… aunque…", "Secretaria", secretaria, true);
        d18.setOpciones(new LinkedList<>(Arrays.asList("¿Cómo conocías al Económico?", "¿Viste algo anoche?")));

        Dialogo d19 = new Dialogo("Éramos pareja en secreto. Estaba petrificado: decía que alguien robaba del fondo de exposiciones.", "Secretaria", secretaria, true);

        Dialogo d20 = new Dialogo("Por eso guardaba los papeles en su PC… y me dio parte de la contraseña por si le pasaba algo.", "Secretaria", secretaria, true);

        Dialogo d21 = new Dialogo("Anoche volví por un informe olvidado. Pasaban las 23:30 cuando oí voces en la escalera de incendios.", "Secretaria", secretaria, true);
        d21.setOpciones(new LinkedList<>(Arrays.asList("¿A quién viste?", "¿Qué hora era?")));

        Dialogo d22 = new Dialogo("Solo vi una silueta masculina, traje oscuro… podría ser el jefe o el guardia, no me atreví a mirar.", "Secretaria", secretaria, true);

        Dialogo d23 = new Dialogo("23:35 más o menos. Salí por la puerta lateral y me fui. Ahora lo lamento…", "Secretaria", secretaria, true);

        BinaryTreeNode<Dialogo> n18 = new BinaryTreeNode<>(d18);
        BinaryTreeNode<Dialogo> n19 = new BinaryTreeNode<>(d19);
        BinaryTreeNode<Dialogo> n20 = new BinaryTreeNode<>(d20);
        BinaryTreeNode<Dialogo> n21 = new BinaryTreeNode<>(d21);
        BinaryTreeNode<Dialogo> n22 = new BinaryTreeNode<>(d22);
        BinaryTreeNode<Dialogo> n23 = new BinaryTreeNode<>(d23);

        GeneralTree<Dialogo> aux = new GeneralTree<>();

        aux.insertNode(n18, null);    //Cada nivel de bloque de código representa el nivel del arbol y, antes de volver al nivel actual de un nodo, se tienen todos sus hijos.
           aux.insertNode(n19, n18);
              aux.insertNode(n20, n19);
           aux.insertNode(n21, n18);
              aux.insertNode(n22, n21);
              aux.insertNode(n23, n21);

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
