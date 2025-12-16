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

public class SalaDeCamaras extends ModeloEscenario {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SalaDeCamaras.class.getName());
    private Dimension tamPant;
    private int dialogoActual;
    private Timer timer;
    private TimerTask tarea;
    private InterfazUsuario interfazUsuario;
    private Timer timer2;
    private TimerTask tarea2;
    private Escenario escenario;
    private GeneralTree<Dialogo> arbolOriginal;
    private GeneralTree<Dialogo> arbolSecretaria;
    private boolean guardiaActivo;
    private boolean secretariaActiva;
    /**
     * Creates new form Entrada
     */
    public SalaDeCamaras() {
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
        arbolSecretaria = construirArbolSecretaria();

        guardiaActivo = true;
        secretariaActiva = false;

        escenario = new Escenario("Sala de Seguridad", "Centro de vigilancia del museo", true);
        escenario.setArbolDial(arbolOriginal);
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {
        cajaTexto  = new JPanel();
        flechaPasillo1 = new JButton();
        lugar = new JLabel();
        interfazUsuario= new InterfazUsuario();
        try {
            BufferedImage imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/sala de camaras.jpg"));




            // Actualizacion de donde esta el Jugador
            Partida p = Partida.getInstance();
            p.buscarEscenarioNombre("Sala de Vigilancia");



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
            getContentPane().add(cajaTexto);

            BufferedImage imagen2 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha abajo.png"));
            ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
            flechaPasillo1.setIcon(icono2);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        flechaPasillo1.setBounds((int) (tamPant.width*0.47), (int) (tamPant.height*0.84), (int) (tamPant.width*0.048), (int) (tamPant.height*0.124));
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
        flechaPasillo1.setToolTipText("Ala este");
        flechaPasillo1.setOpaque(true);
        flechaPasillo1.setContentAreaFilled(false);
        flechaPasillo1.setBorderPainted(false);
        flechaPasillo1.setFocusPainted(false);

        getContentPane().add(flechaPasillo1);

        lugar.setText("Sala de Vigilancia");
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
    public void ponerDialogo() {
    }

    private void cTMouseClicked(MouseEvent evt) {
        ponerDialogo();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    private void flechaPasillo1ActionPerformed(ActionEvent evt) {
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDePasos();

        Pasillo1 pasillo1 = new Pasillo1();
        pasillo1.setVisible(true);
        tarea2.cancel();
        timer.schedule(tarea, 1000);

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

    public void activarSecretaria() {
        guardiaActivo = false;
        secretariaActiva = true;
        escenario.setArbolDial(arbolSecretaria);
    }

    public void restaurarGuardia() {
        guardiaActivo = true;
        secretariaActiva = false;
        escenario.setArbolDial(arbolOriginal);
    }

    public GeneralTree<Dialogo> construirArbolGuardia() {
        ImageIcon guardia = new ImageIcon("DatosAuxiliares/Personajes/Seguridad.jpg");
        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");

        Dialogo d1 = new Dialogo("Oh, pero si es el detective...", "Guardia de seguridad", guardia, true);
        Dialogo d2 = new Dialogo("Asegúrese de tocar la puerta la próxima vez. Pero no importa, usted es bienvenido.", "Guardia de seguridad", guardia, true);

        Dialogo d3 = new Dialogo("Dígame, ¿qué necesita saber?", "Guardia de seguridad", guardia, true);
        d1.setOpciones(new LinkedList<String>(Arrays.asList("¿Qué cámara falló?", "¿Se ve al economista en otras cámaras?", "¿Y el jefe?")));

        Dialogo d4 = new Dialogo("La 2-B, pasillo del almacén: de 01:07 a 03:02.", "Guardia de seguridad", guardia, true);
        Dialogo d5 = new Dialogo("Aquí tiene el ticket impreso; el técnico aún no firma.", "Guardia de seguridad", guardia, true);
        Dialogo d6 = new Dialogo("El DVR sigue grabando, pero ese segmento está en negro.", "Guardia de seguridad", guardia, true);
        Dialogo d7 = new Dialogo("Puedo extraer el disco si tiene orden.", "Guardia de seguridad", guardia, true);
        Dialogo d8 = new Dialogo("(Unos momentos después...)", "", null, true);
        Dialogo d9 = new Dialogo("No toque los cables, hay corriente.", "Guardia de seguridad", guardia, true);
        Dialogo d10 = new Dialogo("El manual de usuario está debajo del monitor.", "Guardia de seguridad", guardia, true);
        Dialogo d11 = new Dialogo("Grabación se sobrescribe cada 48 horas; hoy se borrará de nuevo.", "Guardia de seguridad", guardia, true);
        Dialogo d12 = new Dialogo("Si necesita copia, tráiga un pendrive.", "Guardia de seguridad", guardia, true);
        Dialogo d13 = new Dialogo("Formato de vídeo: .avi, compresión H264.", "Guardia de seguridad", guardia, true);
        Dialogo d14 = new Dialogo("Recuerde: sin orden del jefe no puedo entregar nada.", "Guardia de seguridad", guardia, true);
        Dialogo d15 = new Dialogo("Aunque… si es para aclarar la muerte, yo firmo un parte.", "Guardia de seguridad", guardia, true);
        Dialogo d16 = new Dialogo("Aquí tiene el ticket original.", "Guardia de seguridad", guardia, true);
        Dialogo d17 = new Dialogo("Y esta es mi firma como custodia.", "Guardia de seguridad", guardia, true);
        Dialogo d18 = new Dialogo("Aprecio su cooperación. Le dejo en paz.", "Detective", detective, true);
        Dialogo d19 = new Dialogo("Sí: a las 23:45 entra por la entrada principal.", "Guardia de seguridad", guardia, true);
        Dialogo d20 = new Dialogo("23:48 pasa por recepción y 23:52 desaparece de campo.", "Guardia de seguridad", guardia, true);
        Dialogo d21 = new Dialogo("No sale más; la siguiente grabación útil es el cadáver a las 06:10.", "Guardia de seguridad", guardia, true);
        Dialogo d22 = new Dialogo("Lo veo a las 00:03 bajando la escalera de incendios.", "Guardia de seguridad", guardia, true);
        Dialogo d23 = new Dialogo("00:07 entra en su oficina y 00:11 sale con un paquete.", "Guardia de seguridad", guardia, true);
        Dialogo d24 = new Dialogo("Después vuelve a subir… ya no aparece hasta la mañana.", "Guardia de seguridad", guardia, true);

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
        BinaryTreeNode<Dialogo> n24 = new BinaryTreeNode<>(d24);

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
                 aux.insertNode(n19, n3);
                    aux.insertNode(n20, n19);
                       aux.insertNode(n21, n20);
                 aux.insertNode(n22, n3);
                    aux.insertNode(n23, n22);
                       aux.insertNode(n24, n23);

        return aux;
    }

    public GeneralTree<Dialogo> construirArbolSecretaria() {
        ImageIcon secretaria  = new ImageIcon("DatosAuxiliares/Personajes/Secretaria.png");
        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");

        Dialogo d1 = new Dialogo("¿Usted me llamó, detective?", "Secretaria", secretaria, true);
        d1.setOpciones(new LinkedList<String>(Arrays.asList("¿Sabes manejar el DVR?", "Ves algo raro en el monitor?", "¿Por qué ayudas ahora?")));

        Dialogo d2 = new Dialogo("Sí, la contraseña es la fecha de inauguración: 15102010.", "Secretaria", secretaria, true);
        Dialogo d3 = new Dialogo("Con eso accede al backup de 48 h; descargue lo que necesite.", "Secretaria", secretaria, true);
        Dialogo d4 = new Dialogo("Yo me quedo aquí por si necesita ayuda.", "Secretaria", secretaria, true);
        Dialogo d5 = new Dialogo("No toque los botones rojos, resetean el sistema.", "Secretaria", secretaria, true);
        Dialogo d6 = new Dialogo("Si aparece ‘overwrite’, pulse ‘Sí’.", "Secretaria", secretaria, true);
        Dialogo d7 = new Dialogo("Los archivos se guardan en la carpeta ‘Grabaciones’.", "Secretaria", secretaria, true);
        Dialogo d8 = new Dialogo("Cuando termine, apague el monitor; así no despierta sospechas.", "Secretaria", secretaria, true);
        Dialogo d9 = new Dialogo("Y bájele el volumen, por favor.", "Secretaria", secretaria, true);
        Dialogo d10 = new Dialogo("Cuídese, detective.", "Secretaria", secretaria, true);
        Dialogo d11 = new Dialogo("(Vuelve a recepción)", "", null, true);
        Dialogo d12 = new Dialogo("Fíjese en la esquina inferior: sombra de alguien fuera de campo a las 02:58.", "Secretaria", secretaria, true);
        Dialogo d13 = new Dialogo("Porque ya no quiero encubrir al jefe… él me obligó.", "Secretaria", secretaria, true);

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
           aux.insertNode(n12, n1);
           aux.insertNode(n13, n1);

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
        java.awt.EventQueue.invokeLater(() -> new SalaDeCamaras().setVisible(true));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel cajaTexto;
    private javax.swing.JButton flechaPasillo1;
    private javax.swing.JLabel lugar;
    // End of variables declaration//GEN-END:variables
}