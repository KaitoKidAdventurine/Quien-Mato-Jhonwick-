package Interfaz.Menu;




import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.*;

import DatosAuxiliaresLogica.EfectosEspeciales;
import DatosAuxiliaresLogica.UnionInterfaces;
import Interfaz.InterfazJugador.ConfirmarSalida;
import Logica.Reproductor;

/**
 *
 * @author ROBERTO
 */
public class MenuPrincipal extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MenuPrincipal.class.getName());
    private Dimension tamPant;
    private Timer timer;
    private TimerTask tarea;
    int largoBot;
    int anchoBot;
    int xBot;
    private static boolean  evitarRepetir = false;
    /**
     * Creates new form Menu1
     */
    public MenuPrincipal()
    {
        if(!evitarRepetir)
        {
            Reproductor reproductor = Reproductor.getInstancia();
            reproductor.iniciarMusica();
            evitarRepetir = true;
        }
        timer = new Timer();
        tarea = new TimerTask() {
            @Override
            public void run() {
                dispose();
            }
        };

        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        largoBot = (int) (tamPant.getWidth()*0.2);
        anchoBot = (int) (tamPant.getHeight()*0.07);
        xBot = (int) (tamPant.getWidth()*0.07);
        initComponents();

    }

    private void initComponents() {

        BufferedImage imagenCursor =null;
        try {
             imagenCursor =  ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Cursor.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(imagenCursor, new Point(20, 0), "Cursor detective");
        setCursor(cursor);

        botonSalir = new javax.swing.JButton();
        botonNuevPart = new javax.swing.JButton();
        botonCargPar = new javax.swing.JButton();
        botonOpc = new javax.swing.JButton();
        botonCreditos = new javax.swing.JButton();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(tamPant));
        setUndecorated(true);
        getContentPane().setLayout(null);

        botonSalir.setFont(new java.awt.Font("Segoe UI", 0, (int)(largoBot*0.14)));
        botonSalir.setForeground(new java.awt.Color(255, 255, 255));
        botonSalir.setText("Salir");
        botonSalir.setBorderPainted(false);
        botonSalir.setContentAreaFilled(false);
        botonSalir.setFocusPainted(false);
        botonSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonSalirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonSalirMouseExited(evt);
            }
        });
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });
        botonSalir.setBounds(xBot,(int) (tamPant.getHeight()*0.86), largoBot, anchoBot);
        getContentPane().add(botonSalir);

        botonNuevPart.setFont(new java.awt.Font("Segoe UI", 0, (int)(largoBot*0.14)));
        botonNuevPart.setForeground(new java.awt.Color(255, 255, 255));
        botonNuevPart.setText("Nueva Partida");
        botonNuevPart.setBorderPainted(false);
        botonNuevPart.setContentAreaFilled(false);
        botonNuevPart.setFocusPainted(false);
        botonNuevPart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonNuevPartMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonNuevPartMouseExited(evt);
            }
        });
        botonNuevPart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevPartActionPerformed(evt);
            }
        });
        botonNuevPart.setBounds(xBot,(int) (tamPant.getHeight()*0.54), largoBot, anchoBot);
        getContentPane().add(botonNuevPart);

        botonCargPar.setFont(new java.awt.Font("Segoe UI", 0, (int)(largoBot*0.14)));
        botonCargPar.setForeground(new java.awt.Color(255, 255, 255));
        botonCargPar.setText("Cargar Partida");
        botonCargPar.setBorderPainted(false);
        botonCargPar.setContentAreaFilled(false);
        botonCargPar.setFocusPainted(false);
        botonCargPar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonCargParMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonCargParMouseExited(evt);
            }
        });
        botonCargPar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCargParActionPerformed(evt);
            }
        });
        botonCargPar.setBounds(xBot,(int) (tamPant.getHeight()*0.62), largoBot, anchoBot);
        getContentPane().add(botonCargPar);

        botonOpc.setFont(new java.awt.Font("Segoe UI", 0, (int)(largoBot*0.14)));
        botonOpc.setForeground(new java.awt.Color(255, 255, 255));
        botonOpc.setText("Opciones");
        botonOpc.setBorderPainted(false);
        botonOpc.setContentAreaFilled(false);
        botonOpc.setFocusPainted(false);
        botonOpc.setBounds(xBot,(int) (tamPant.getHeight()*0.70), largoBot, anchoBot);
        botonOpc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonOpcMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonOpcMouseExited(evt);
            }
        });
        botonOpc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonOpcActionPerformed(evt);
            }
        });
        getContentPane().add(botonOpc);

        botonCreditos.setFont(new java.awt.Font("Segoe UI", 0, (int)(largoBot*0.14)));
        botonCreditos.setForeground(new java.awt.Color(255, 255, 255));
        botonCreditos.setText("Creditos");
        botonCreditos.setBorderPainted(false);
        botonCreditos.setContentAreaFilled(false);
        botonCreditos.setFocusPainted(false);
        botonCreditos.setBounds(xBot,(int) (tamPant.getHeight()*0.78), largoBot, anchoBot);
        botonCreditos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonCreditosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonCreditosMouseExited(evt);
            }
        });
        botonCreditos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCreditosActionPerformed(evt);
            }
        });
        getContentPane().add(botonCreditos);



        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(new File(("DatosAuxiliares/Fondo presentacion.jpeg")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono = new ImageIcon(imagen.getScaledInstance(tamPant.width, tamPant.height , Image.SCALE_SMOOTH));


        fondo.setIcon(icono); // NOI18N
        fondo.setText("jLabel1");
        fondo.setMaximumSize(tamPant);
        fondo.setMinimumSize(tamPant);
        fondo.setPreferredSize(tamPant);
        fondo.setBounds(0,0, tamPant.width, tamPant.height );
        getContentPane().add(fondo);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDeBoton();

        ConfirmarSalida confirmarSalida = new ConfirmarSalida(new JFrame(), true, "Estas seguro de querer salir del juego", true, false);
        confirmarSalida.setVisible(true);
        if(UnionInterfaces.getInstance().getSalirJuego()){
            System.exit(0);
        }
    }

    private void botonNuevPartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevPartActionPerformed
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDeBoton();

        NuevaPartida nP = new NuevaPartida();
        nP.setVisible(true);
        timer.schedule(tarea, 1000);
    }

    private void botonCargParActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCargParActionPerformed
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDeBoton();

        CargarPartida cP = new CargarPartida();

        cP.setVisible(true);
        timer.schedule(tarea, 1000);

    }

    private void botonOpcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOpcActionPerformed
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDeBoton();

        Opciones opc = new Opciones();

        opc.setVisible(true);
        timer.schedule(tarea, 1000);
    }

    private void botonNuevPartMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonNuevPartMouseEntered
        botonNuevPart.setForeground(Color.red);
    }

    private void botonNuevPartMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonNuevPartMouseExited
        botonNuevPart.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_botonNuevPartMouseExited

    private void botonCargParMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCargParMouseEntered
        botonCargPar.setForeground(Color.red);
    }//GEN-LAST:event_botonCargParMouseEntered

    private void botonCargParMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCargParMouseExited
        botonCargPar.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_botonCargParMouseExited

    private void botonOpcMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonOpcMouseEntered
        botonOpc.setForeground(Color.red);
    }//GEN-LAST:event_botonOpcMouseEntered

    private void botonOpcMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonOpcMouseExited
        botonOpc.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_botonOpcMouseExited

    private void botonSalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonSalirMouseEntered
        botonSalir.setForeground(Color.red);
    }//GEN-LAST:event_botonSalirMouseEntered

    private void botonSalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonSalirMouseExited
        botonSalir.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_botonSalirMouseExited

    private void botonCreditosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCreditosMouseEntered
        botonCreditos.setForeground(Color.red);
    }//GEN-LAST:event_botonCreditosMouseEntered

    private void botonCreditosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCreditosMouseExited
        botonCreditos.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_botonCreditosMouseExited

    private void botonCreditosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCreditosActionPerformed
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDeBoton();

        Creditos cre = new Creditos();
        cre.setVisible(true);
        timer.schedule(tarea, 1000);
    }//GEN-LAST:event_botonCreditosActionPerformed



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
        java.awt.EventQueue.invokeLater(() -> new MenuPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCargPar;
    private javax.swing.JButton botonCreditos;
    private javax.swing.JButton botonNuevPart;
    private javax.swing.JButton botonOpc;
    private javax.swing.JButton botonSalir;
    private javax.swing.JLabel fondo;
    // End of variables declaration//GEN-END:variables
}

