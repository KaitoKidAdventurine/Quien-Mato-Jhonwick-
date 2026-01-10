package Interfaz.InterfazJugador;

import DatosAuxiliaresLogica.EfectosEspeciales;
import DatosAuxiliaresLogica.UnionInterfaces;
import Logica.Dialogo;
import Logica.Juego;
import Logica.Jugador;
import Logica.Telefono;

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
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TelefonoInterfaz  extends javax.swing.JDialog {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelefonoInterfaz.class.getName());
    private Dimension tamPant;

    private Timer timer;
    private TimerTask tarea;
    private Timer timer1;
    private TimerTask tarea1;
    private Timer timer2;
    private TimerTask tarea2;
    private Telefono telefonoLogica;
    private JButton apagar;
    private JPanel cajaTexto;
    private TelefonoLlamar llamada;
    private TelefonoAjustes ajustesT;
    private TelefonoReproductor reproductorT;

    /**
     * Creates new form MenuInterno
     */
    public TelefonoInterfaz(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        telefonoLogica = Juego.getInstance().getPartidaActual().getJugador().getTelefono();
        cajaTexto = new JPanel();


        timer = new Timer();
        tarea = new TimerTask() {
            @Override
            public void run() {
                revalidate();
                repaint();
            }
        };
        timer1 = new Timer();
        tarea1 = new TimerTask() {
            @Override
            public void run() {
                if(UnionInterfaces.getInstance().getHablandoCapitan()){
                    UnionInterfaces.getInstance().setHablandoCapitan(false);
                    llamada.setVisible(false);
                    pantalla.setVisible(false);
                    telefono.setVisible(false);
                    llamadaCapitan(0);
                }
            }
        };
        timer2 = new Timer();
        tarea2 = new TimerTask() {
            @Override
            public void run() {
                if(UnionInterfaces.getInstance().getMolestandoTarde()){
                    UnionInterfaces.getInstance().setMolestandoTarde(false);
                    llamada.setVisible(false);
                    pantalla.setVisible(false);
                    telefono.setVisible(false);
                    molestarTarde();
                }
            }
        };
        initComponents();
    }


    private void initComponents() {

        pantalla = new JPanel();
        llamar = new JButton();
        ajustes = new JButton();
        canciones = new JButton();
        salir = new JButton();
        telefono = new JLabel();
        fondo = new JLabel();
        apagar = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);
        getContentPane().setPreferredSize(new Dimension((int) (tamPant.width*0.4), (int) (tamPant.height*0.6)));
        setBackground(new Color(0,0, 0, 75));

        cajaTexto.setBackground(new Color(0, 0, 0, 0));
        cajaTexto.setBounds(0, 0,  tamPant.width, tamPant.height);
        cajaTexto.setLayout(null);
        add(cajaTexto, 0);

        BufferedImage imagenCursor =null;
        try {
            imagenCursor =  ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Cursor.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(imagenCursor, new Point(20, 0), "Cursor detective");
        setCursor(cursor);

        pantalla.setBackground(Color.white );
        pantalla.setLayout(null);
        pantalla.setBounds((int) (tamPant.width*0.3394), (int) (tamPant.height*0.12), (int) (tamPant.width*0.27), (int) (tamPant.height*0.73));
        pantalla.setOpaque(false);

        llamar.setBorderPainted(false);
        llamar.setContentAreaFilled(false);
        llamar.setFocusPainted(false);
        llamar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        BufferedImage imagen1 = null;

        try {
            imagen1 = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Llamada.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono1 = new ImageIcon(imagen1.getScaledInstance((int) (tamPant.width*0.13), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
        llamar.setIcon(icono1);

        llamar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        llamar.setBounds((int) (tamPant.width*0.07), (int) (tamPant.height*0.1),(int) (tamPant.width*0.13), (int) (tamPant.height*0.13));
        pantalla.add(llamar);


        ajustes.setBorderPainted(false);
        ajustes.setContentAreaFilled(false);
        ajustes.setFocusPainted(false);
        ajustes.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });
        ajustes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt, telefonoLogica, fondo);
            }
        });
        BufferedImage imagen2 = null;

        try {
            imagen2 = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Ajustes.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.13), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
        ajustes.setIcon(icono2);
        ajustes.setBounds((int) (tamPant.width*0.07), (int) (tamPant.height*0.5),(int) (tamPant.width*0.13), (int) (tamPant.height*0.13));
        pantalla.add(ajustes);


        canciones.setBorderPainted(false);
        canciones.setContentAreaFilled(false);
        canciones.setFocusPainted(false);
        canciones.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                jButton3MouseExited(evt);
            }
        });
        canciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton3ActionPerformed(evt, telefonoLogica);
            }
        });

        BufferedImage imagen3 = null;

        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Reproductor.png"));} catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.13), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
        canciones.setIcon(icono3);
        canciones.setBounds((int) (tamPant.width*0.07), (int) (tamPant.height*0.3),(int) (tamPant.width*0.13), (int) (tamPant.height*0.13));
        pantalla.add(canciones);


        salir.setBorderPainted(false);
        salir.setContentAreaFilled(false);
        salir.setFocusPainted(false);
        pantalla.add(salir);

        BufferedImage imagen6 = null;

        try {
            imagen6 = ImageIO.read(new File("DatosAuxiliares/Telefono/atras.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono6 = new ImageIcon(imagen6.getScaledInstance((int) (tamPant.width*0.05), (int) (tamPant.height*0.07), Image.SCALE_SMOOTH));
        salir.setIcon(icono6);
        salir.setBounds((int) (tamPant.height*0.11), (int) (tamPant.height*0.66),(int) (tamPant.width*0.05), (int) (tamPant.height*0.07));

        pantalla.add(salir);



        apagar.setBorderPainted(false);
        apagar.setContentAreaFilled(false);
        apagar.setFocusPainted(false);
        apagar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                jButton4MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                jButton4MouseExited(evt);
            }
        });
        apagar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        apagar.setBounds((int) (tamPant.width*0.16), (int) (tamPant.height*0.66),(int) (tamPant.width*0.05), (int) (tamPant.height*0.07));

        BufferedImage imagen7 = null;

        try {
            imagen7 = ImageIO.read(new File("DatosAuxiliares/Telefono/apagar.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono7 = new ImageIcon(imagen7.getScaledInstance((int) (tamPant.width*0.05), (int) (tamPant.height*0.07), Image.SCALE_SMOOTH));
        apagar.setIcon(icono7);
        pantalla.add(apagar);

        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Mano Telefono.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.6), (int) (tamPant.height*0.9), Image.SCALE_SMOOTH));
        telefono.setIcon(icono);
        telefono.setBounds((int) (tamPant.width*0.26), (int) (tamPant.height*0.09), (int) (tamPant.width*0.6), (int) (tamPant.height*0.9));

        BufferedImage imagen5 = null;

        try {
            imagen5 = ImageIO.read(new File(String.valueOf(telefonoLogica.getFondoDePantalla())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono5 = new ImageIcon(imagen5.getScaledInstance((int) (tamPant.width*0.255), (int) (tamPant.height*0.72), Image.SCALE_SMOOTH));
        fondo.setIcon(icono5);
        fondo.setBounds((int) (tamPant.width*0.005),0, (int) (tamPant.width*0.255), (int) (tamPant.height*0.72));
        pantalla.add(fondo);
        add(pantalla, 1);
        add(telefono);

        llamada = new TelefonoLlamar();
        llamada.setBounds((int) (tamPant.width*0.3394), (int) (tamPant.height*0.12), (int) (tamPant.width*0.27), (int) (tamPant.height*0.73));
        llamada.setVisible(false);
        add(llamada,2);


        ajustesT = new TelefonoAjustes(telefonoLogica, fondo);
        ajustesT.setBounds((int) (tamPant.width*0.3394), (int) (tamPant.height*0.12), (int) (tamPant.width*0.27), (int) (tamPant.height*0.73));
        ajustesT.setVisible(false);
        add(ajustesT, 3);


        reproductorT = new TelefonoReproductor(telefonoLogica);
        reproductorT.setBounds((int) (tamPant.width*0.3394), (int) (tamPant.height*0.12), (int) (tamPant.width*0.27), (int) (tamPant.height*0.73));
        reproductorT.setVisible(false);
        add(reproductorT, 4);

        pack();
        timer.scheduleAtFixedRate(tarea, 0, 10);
        timer1.scheduleAtFixedRate(tarea1, 0, 20);
        timer2.scheduleAtFixedRate(tarea2, 0, 20);
    }// </editor-fold>

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDeBotonesTelefono();

        pantalla.setVisible(false);
        llamada.setVisible(true);
        reproductorT.setVisible(false);
        ajustesT.setVisible(false);
    }

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {
        BufferedImage imagen1 = null;

        try {
            imagen1 = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/LLamada BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono1 = new ImageIcon(imagen1.getScaledInstance((int) (tamPant.width*0.13), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
        llamar.setIcon(icono1);

    }

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {
        BufferedImage imagen1 = null;

        try {
            imagen1 = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Llamada.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono1 = new ImageIcon(imagen1.getScaledInstance((int) (tamPant.width*0.13), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
        llamar.setIcon(icono1);

    }


    private void jButton2ActionPerformed(ActionEvent evt, Telefono telefonoLogica, JLabel fondo) {
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDeBotonesTelefono();
        pantalla.setVisible(false);
        llamada.setVisible(false);
        reproductorT.setVisible(false);
        ajustesT.setVisible(true);
    }

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {
        BufferedImage imagen2 = null;

        try {
            imagen2 = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Ajustes BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.13), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
        ajustes.setIcon(icono2);
    }

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {
        BufferedImage imagen2 = null;

        try {
            imagen2 = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Ajustes.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.13), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
        ajustes.setIcon(icono2);
    }


    private void jButton3ActionPerformed(ActionEvent evt, Telefono telefonoLogica) {
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDeBotonesTelefono();
        pantalla.setVisible(false);
        reproductorT.setVisible(true);
        llamada.setVisible(false);
        ajustesT.setVisible(false);
    }

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {
        BufferedImage imagen3 = null;

        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Reproductor BR.png"));} catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.13), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
        canciones.setIcon(icono3);
    }

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {
        BufferedImage imagen3 = null;

        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Reproductor.png"));} catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.13), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
        canciones.setIcon(icono3);
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void jButton4MouseEntered(java.awt.event.MouseEvent evt) {
        BufferedImage imagen7 = null;

        try {
            imagen7 = ImageIO.read(new File("DatosAuxiliares/Telefono/apagrar r.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono7 = new ImageIcon(imagen7.getScaledInstance((int) (tamPant.width*0.05), (int) (tamPant.height*0.07), Image.SCALE_SMOOTH));
        apagar.setIcon(icono7);
    }

    private void jButton4MouseExited(java.awt.event.MouseEvent evt) {
        BufferedImage imagen7 = null;

        try {
            imagen7 = ImageIO.read(new File("DatosAuxiliares/Telefono/apagar.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono7 = new ImageIcon(imagen7.getScaledInstance((int) (tamPant.width*0.05), (int) (tamPant.height*0.07), Image.SCALE_SMOOTH));
        apagar.setIcon(icono7);
    }
    private void llamadaCapitan(int actual) {
        if (actual < Juego.getInstance().getPartidaActual().getDialogosCapitan().get(Juego.getInstance().getPartidaActual().getEventos().getRonda()+1).size()) {
            Dialogo aux = Juego.getInstance().getPartidaActual().getDialogosCapitan().get(Juego.getInstance().getPartidaActual().getEventos().getRonda()+1).get(actual);
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono(),!aux.getTextoImport().isEmpty());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);
            actual++;
            int finalActual = actual;

            EfectosEspeciales e = EfectosEspeciales.getInstancia();
            e.detenerSiEsNecesario();

            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    llamaCapMouseClicked(finalActual);
                }
            });

            if (aux.getTexto().equals("Beeeppp"))
            {
                e.efectoLlamadaTelefono();
            }

            else if(aux.getTexto().equals("-Biiiip-"))
            {
                e.efectoColgarTelefono();
            }



            cajaTexto.removeAll();
            cajaTexto.add(cT, 0);

        } else{
            cajaTexto.removeAll();
            llamada.setVisible(true);
            telefono.setVisible(true);
        }
    }

    private void llamaCapMouseClicked(int finalActual) {
        llamadaCapitan(finalActual);
    }
    private void molestarTarde() {
        ponerDialogosEstatico(crearDialogosMolestar(), 0);
    }
    private ArrayList<Dialogo> crearDialogosMolestar(){
        ArrayList<Dialogo> dialogosConserje = new ArrayList<>();

        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");
        Dialogo d1= new Dialogo("(Creo que estas no son horas de molestar)", "Detective", detective, true);
        Dialogo d2= new Dialogo("(Deberia de llamarle mañana.)", "Detective", detective, true);

        dialogosConserje.add(d1);
        dialogosConserje.add(d2);
        return dialogosConserje;
    }
    private void ponerDialogosEstatico(ArrayList<Dialogo> dialogos, int actual) {
        if(actual<dialogos.size()) {
            Dialogo aux = dialogos.get(actual);
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono(),!aux.getTextoImport().isEmpty());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);
            actual++;
            int finalActual = actual;

            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    EstatiMouseClicked(dialogos, finalActual);
                }
            });
            cajaTexto.removeAll();
            cajaTexto.add(cT);
        }else {
            cajaTexto.removeAll();
            llamada.setVisible(true);
            telefono.setVisible(true);
        }

    }
    private void EstatiMouseClicked(ArrayList<Dialogo> dialogos, int actual){
        ponerDialogosEstatico(dialogos, actual);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
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
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MenuInterno dialog = new MenuInterno(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton llamar;
    private javax.swing.JButton ajustes;
    private javax.swing.JButton canciones;
    private javax.swing.JButton salir;
    private javax.swing.JPanel pantalla;
    private javax.swing.JLabel telefono;
    private javax.swing.JLabel fondo;
    // End of variables declaration
}
