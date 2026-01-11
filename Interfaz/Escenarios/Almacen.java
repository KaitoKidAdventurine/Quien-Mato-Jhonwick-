package Interfaz.Escenarios;

import DatosAuxiliaresLogica.EfectosEspeciales;
import DatosAuxiliaresLogica.UnionInterfaces;
import Interfaz.InterfazJugador.CuadroTexto;
import Interfaz.InterfazJugador.InterfazUsuario;
import Interfaz.InterfazJugador.OpcionesDialogos;
import Interfaz.Menu.MenuPrincipal;
import Interfaz.MiniJuego.MinijuegoInterfaz;
import Logica.Dialogo;
import Logica.Juego;
import Logica.MiniJuego;

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
import java.util.Timer;
import java.util.TimerTask;

public class Almacen extends ModeloEscenario {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Almacen.class.getName());
    private Dimension tamPant;
    private int dialogoActual;
    private Timer timer;
    private TimerTask tarea;
    private Timer timer2;
    private TimerTask tarea2;
    private InterfazUsuario interfazUsuario;
    private JButton revisarAlmacen;
    private Timer timer3;
    private TimerTask tarea3;
    /**
     * Creates new form Entrada
     */
    public Almacen() {
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        timer2 = new Timer();
        tarea2 = new TimerTask() {
            @Override
            public void run() {
                if(UnionInterfaces.getInstance().getCerrarVentana()){
                    MenuPrincipal menu = new MenuPrincipal();
                    menu.setVisible(true);
                    timer.schedule(tarea, 700);
                    UnionInterfaces.getInstance().setCerrarVentana(false);
                    tarea2.cancel();
                }else{
                    revalidate();
                    repaint();
                }
            }
        };
        timer3 = new Timer();
        tarea3 = new TimerTask() {
            @Override
            public void run() {
                if(Juego.getInstance().getPartidaActual().getEventos().isAlmacenRevisado()){
                    cajaTexto.removeAll();
                    revisarAlmacen.setVisible(false);
                    ponerDialogo();
                    tarea3.cancel();
                }
            }
        };
        initComponents();
        timer = new Timer();
        tarea = new TimerTask() {
            @Override
            public void run() {
                dispose();
                UnionInterfaces.getInstance().setUsandoFlecha(false);
            }
        };

    }


    @SuppressWarnings("unchecked")
    private void initComponents() {
        cajaTexto  = new JPanel();
        flechaPasilloAlmacen = new JButton();
        lugar = new JLabel();
        interfazUsuario= new InterfazUsuario();
        revisarAlmacen = new JButton();


        try {
            BufferedImage imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/puerta del lmacen.jpg"));

            // Actualizacion de donde esta el Jugador
            Juego.getInstance().getPartidaActual().buscarEscenarioNombre("Almacen");

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

            cajaTexto.setBackground(new Color(0, 0, 0, 0));
            cajaTexto.setBounds(0, 0,  tamPant.width, tamPant.height);
            cajaTexto.setLayout(null);

            BufferedImage imagen2 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha arriba.png"));
            ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
            flechaPasilloAlmacen.setIcon(icono2);

            BufferedImage imagen3 = ImageIO.read(new File("DatosAuxiliares/Minijuego/Buscar.png"));
            ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.08), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
            revisarAlmacen.setIcon(icono3);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        flechaPasilloAlmacen.setBounds((int) (tamPant.width*0.45), (int) (tamPant.height*0.5), (int) (tamPant.width*0.04), (int) (tamPant.height*0.11));
        flechaPasilloAlmacen.setBackground(Color.red);
        flechaPasilloAlmacen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaPasilloAlmacenActionPerformed(evt);
            }
        });

        flechaPasilloAlmacen.setOpaque(true);
        flechaPasilloAlmacen.setContentAreaFilled(false);
        flechaPasilloAlmacen.setBorderPainted(false);
        flechaPasilloAlmacen.setFocusPainted(false);
        flechaPasilloAlmacen.setToolTipText("Ala sur");
        flechaPasilloAlmacen.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                flechaMouseExited(evt);
            }
        });
        getContentPane().add(cajaTexto);

        getContentPane().add(flechaPasilloAlmacen);

        revisarAlmacen.setBounds((int) (tamPant.width*0.82), (int) (tamPant.height*0.53), (int) (tamPant.width*0.08), (int) (tamPant.height*0.11));
        revisarAlmacen.setContentAreaFilled(false);
        revisarAlmacen.setBorderPainted(false);
        revisarAlmacen.setFocusPainted(false);
        revisarAlmacen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                revisarAlmacenActionPerformed(evt);
            }

        });
        revisarAlmacen.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                revisarAlmaceMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                revisarAlmaceMouseExited(evt);
            }
        });
        if(!(Juego.getInstance().getPartidaActual().getEventos().getRonda()==5)) {
            revisarAlmacen.setVisible(false);
        }
        if(Juego.getInstance().getPartidaActual().getEventos().isAlmacenRevisado()){
            revisarAlmacen.setVisible(false);
        }
        getContentPane().add(revisarAlmacen);


        lugar.setText("Almacen");
        lugar.setOpaque(false);
        lugar.setForeground(Color.white);
        lugar.setFont(new Font("Segoe UI", 0, (int) (tamPant.width*0.035)));
        lugar.setBounds((int) (tamPant.width*0.03), (int) (tamPant.height*0.06), (int) (tamPant.width*0.3), (int) (tamPant.height*0.1));
        getContentPane().add(lugar);

        interfazUsuario.setBounds((int) (tamPant.width*0.55), (int) (tamPant.height*0.05), (int) (tamPant.width*0.45), (int) (tamPant.height*0.15));
        getContentPane().add(interfazUsuario);

        getContentPane().add(jLabel1);
        pack();
        timer2.scheduleAtFixedRate(tarea2, 0, 5);
    }
    public void ponerDialogo() {
        if(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(12).getNodoDialActual() == null || !(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(12).getArbolDial().nodeIsLeaf(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(12).getNodoDialActual()))) {
            if(!(Juego.getInstance().getPartidaActual().getEscenariosMundo().get(12).getNodoDialActual()==null)){
                Dialogo actual = Juego.getInstance().getPartidaActual().getEscenariosMundo().get(12).getDialogoActual();
                if(!actual.getOpciones().isEmpty()){
                    OpcionesDialogos oD = new OpcionesDialogos(UnionInterfaces.getInstance().getFrameActual(), true, actual.getOpciones());
                    oD.setBounds((int) (tamPant.width*0.28),(int) (tamPant.getHeight()*0.37), (int) (tamPant.width*0.48),(int) (tamPant.getHeight()*0.5));
                    oD.setVisible(true);
                }
            }
            Dialogo aux = Juego.getInstance().getPartidaActual().getEscenariosMundo().get(12).getDialogoSiguiente(UnionInterfaces.getInstance().getOpcionDialogo());
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono(),!aux.getTextoImport().isEmpty());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);
            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    almacenMouseClicked(evt);
                }
            });

            if(UnionInterfaces.getInstance().getOpcionDialogo()!=1)
                UnionInterfaces.getInstance().setOpcionDialogo(1);

            if (aux.getTextoImport().isEmpty() == false)
            {
                aux.guardarEnDiario();
            }

            cajaTexto.removeAll();
            cajaTexto.add(cT);
        }else {
            cajaTexto.removeAll();
        }

    }

    private void almacenMouseClicked(MouseEvent evt) {
        ponerDialogo();
    }


    private void flechaPasilloAlmacenActionPerformed(ActionEvent evt) {
       if(!UnionInterfaces.getInstance().getUsandoFlecha()) {
           UnionInterfaces.getInstance().setUsandoFlecha(true);
           EfectosEspeciales e = EfectosEspeciales.getInstancia();
           e.efectoAbrirYCerrarPuerta();

           PasilloAlmacen pasilloAlmacen = new PasilloAlmacen();
           pasilloAlmacen.setVisible(true);
           tarea2.cancel();
           timer.schedule(tarea, 700);
       }
    }

    private void flechaMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha arriba.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaPasilloAlmacen.setIcon(icono);
    }

    private void flechaMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha arriba BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        flechaPasilloAlmacen.setIcon(icono);
    }
    private void revisarAlmaceMouseExited(MouseEvent evt) {
        BufferedImage imagen =null;
        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/Minijuego/Buscar.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.08), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        revisarAlmacen.setIcon(icono);

    }

    private void revisarAlmaceMouseEntered(MouseEvent evt) {
        BufferedImage imagen =null;
        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/Minijuego/Buscar BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.08), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
        revisarAlmacen.setIcon(icono);
    }

    private void revisarAlmacenActionPerformed(ActionEvent evt) {
        MiniJuego miniJuego = Juego.getInstance().getMinijuego(3);

        MinijuegoInterfaz minijuegoInterfaz = new MinijuegoInterfaz(miniJuego, 3);
        minijuegoInterfaz.setBounds(0, 0, tamPant.width, tamPant.height);
        cajaTexto.add(minijuegoInterfaz);
        timer3.scheduleAtFixedRate(tarea3, 0 ,20);
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
        java.awt.EventQueue.invokeLater(() -> new Almacen().setVisible(true));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel cajaTexto;
    private javax.swing.JButton flechaPasilloAlmacen;
    private javax.swing.JLabel lugar;
    // End of variables declaration//GEN-END:variables
}