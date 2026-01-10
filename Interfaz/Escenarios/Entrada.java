/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz.Escenarios;

import DatosAuxiliaresLogica.EfectosEspeciales;
import DatosAuxiliaresLogica.UnionInterfaces;
import Interfaz.InterfazJugador.CuadroTexto;
import Interfaz.InterfazJugador.InterfazUsuario;
import Interfaz.Menu.MenuPrincipal;
import Logica.Dialogo;
import Logica.Escenario;
import Logica.Juego;

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

/**
 *
 * @author ROBERTO
 */
public class Entrada extends ModeloEscenario {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Entrada.class.getName());
    private Dimension tamPant;
    private Timer timer;
    private Timer timer2;
    private TimerTask tarea;
    private TimerTask tarea2;
    private InterfazUsuario interfazUsuario;
    private Escenario escenario;

    /**
     * Creates new form Entrada
     */
    public Entrada() {
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        escenario = new Escenario("Entrada", "Punto inicial de partida", true);
        timer = new Timer();
        tarea = new TimerTask() {
            @Override
            public void run() {
                dispose();  UnionInterfaces.getInstance().setUsandoFlecha(false);
            }
        };
        timer2 = new Timer();
        tarea2 = new TimerTask() {
            @Override
            public void run() {

                if (UnionInterfaces.getInstance().getCerrarVentana()) {
                    MenuPrincipal menu = new MenuPrincipal();
                    menu.setVisible(true);
                    UnionInterfaces.getInstance().setCerrarVentana(false);
                     dispose();
                    tarea2.cancel();
                } else {
                    revalidate();
                    repaint();
                }
            }
        };
        initComponents();
    }


    private void initComponents() {
        cajaTexto = new JPanel();
        fondo = new JLabel();
        flecha = new JButton();
        lugar = new JLabel();
        interfazUsuario = new InterfazUsuario();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        try {
            BufferedImage imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/Entrada.jpg"));

            // Actualizacion de donde esta el Jugador
            Juego.getInstance().getPartidaActual().buscarEscenarioNombre("Entrada");

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

            cajaTexto.setBackground(new Color(0, 0, 0, 0));
            cajaTexto.setBounds(0, 0, tamPant.width, tamPant.height);
            cajaTexto.setLayout(null);

            BufferedImage imagen2 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha arriba.png"));
            ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width * 0.04), (int) (tamPant.height * 0.11), Image.SCALE_SMOOTH));
            flecha.setIcon(icono2);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        flecha.setBounds((int) (tamPant.width * 0.484), (int) (tamPant.height * 0.77), (int) (tamPant.width * 0.048), (int) (tamPant.height * 0.124));
        flecha.setOpaque(true);
        flecha.setToolTipText("Recepcion");
        flecha.setContentAreaFilled(false);
        flecha.setBorderPainted(false);
        flecha.setFocusPainted(false);
        flecha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                flechaActionPerformed(evt);
            }
        });

        flecha.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                flechaMouseEntered(evt);
            }

            public void mouseExited(MouseEvent evt) {
                flechaMouseExited(evt);
            }
        });
        getContentPane().add(cajaTexto);
        getContentPane().add(flecha);

        lugar.setText("Entrada");
        lugar.setOpaque(false);
        lugar.setForeground(Color.white);
        lugar.setFont(new Font("Segoe UI", 0, (int) (tamPant.width * 0.035)));
        lugar.setBounds((int) (tamPant.width * 0.03), (int) (tamPant.height * 0.06), (int) (tamPant.width * 0.3), (int) (tamPant.height * 0.1));
        getContentPane().add(lugar);

        interfazUsuario.setBounds((int) (tamPant.width * 0.55), (int) (tamPant.height * 0.05), (int) (tamPant.width * 0.45), (int) (tamPant.height * 0.15));
        getContentPane().add(interfazUsuario);

        getContentPane().add(fondo);

        pack();
        timer2.scheduleAtFixedRate(tarea2, 0, 10);
        if (Juego.getInstance().getPartidaActual().getEventos().getDialogoCapitanActual() == 0) {
            llamadaCapitan(0);
        }
    }// </editor-fold>//GEN-END:initComponents

    private void llamadaCapitan(int actual) {
        if (actual < Juego.getInstance().getPartidaActual().getDialogosCapitan().get(0).size()) {
            Dialogo aux = Juego.getInstance().getPartidaActual().getDialogosCapitan().get(0).get(actual);
            CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono(),!aux.getTextoImport().isEmpty());
            cT.setBounds(0, 0, tamPant.width, tamPant.height);
            actual++;
            int finalActual = actual;

            cT.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    llamaCapMouseClicked(finalActual);
                }
            });
            cajaTexto.removeAll();
            cajaTexto.add(cT, 0);
            switch (actual) {
                case 13:
                    resaltar(1);
                    break;
                case 15:
                    resaltar(2);
                    break;
                case 17:
                    resaltar(3);
                    break;
                case 19:
                    resaltar(4);
                    break;
                default:
                    break;

            }
        } else {
            cajaTexto.removeAll();
            Juego.getInstance().getPartidaActual().getEventos().setDialogoCapitanActual(1);
        }

    }

    private void resaltar(int i) {
        JPanel blanco = new JPanel();
        blanco.setBackground(new Color(255, 255, 255, 60));
        switch (i){
            case 1:
                blanco.setBounds((int) (tamPant.width * 0.595), (int) (tamPant.height * 0.085), (int) (tamPant.width * 0.08), (int) (tamPant.height * 0.11));
                break;
            case 2:
                blanco.setBounds((int) (tamPant.width * 0.685), (int) (tamPant.height * 0.085), (int) (tamPant.width * 0.08), (int) (tamPant.height * 0.11));
                break;
            case 3:
                blanco.setBounds((int) (tamPant.width * 0.78), (int) (tamPant.height * 0.085), (int) (tamPant.width * 0.08), (int) (tamPant.height * 0.11));
                break;
            case 4:
                blanco.setBounds((int) (tamPant.width * 0.878), (int) (tamPant.height * 0.085), (int) (tamPant.width * 0.08), (int) (tamPant.height * 0.11));
                break;
            default:
                break;
        }
        cajaTexto.add(blanco, 1);
    }

    private void llamaCapMouseClicked(int finalActual) {
        llamadaCapitan(finalActual);
    }

    private void flechaActionPerformed(ActionEvent evt) {
        if(!UnionInterfaces.getInstance().getUsandoFlecha()) {
            UnionInterfaces.getInstance().setUsandoFlecha(true);
            EfectosEspeciales e = EfectosEspeciales.getInstancia();
            e.efectoDePasos();

            Recepcion recepcion = new Recepcion();
            recepcion.setVisible(true);
            tarea2.cancel();
            timer.schedule(tarea, 500);
        }
    }

    private void flechaMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha arriba.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width * 0.04), (int) (tamPant.height * 0.11), Image.SCALE_SMOOTH));
        flecha.setIcon(icono);
    }

    private void flechaMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/Flecha arriba BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width * 0.04), (int) (tamPant.height * 0.11), Image.SCALE_SMOOTH));
        flecha.setIcon(icono);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Entrada().setVisible(true));

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fondo;
    private javax.swing.JPanel cajaTexto;
    private javax.swing.JButton flecha;
    private javax.swing.JLabel lugar;
    // End of variables declaration//GEN-END:variables


}
