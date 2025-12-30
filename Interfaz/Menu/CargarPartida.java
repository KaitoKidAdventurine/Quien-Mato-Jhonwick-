/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz.Menu;

import DatosAuxiliaresLogica.EfectosEspeciales;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author ROBERTO
 */
public class CargarPartida extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CargarPartida.class.getName());
    private Dimension tamPant;
    private Timer timer;
    private TimerTask tarea;


    /**
     * Creates new form CargarPartida
     */
    public CargarPartida() {
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        initComponents();
        timer = new Timer();
        tarea = new TimerTask() {
            @Override
            public void run() {
                dispose();
            }
        };

    }


    private void initComponents() {
        botonAtras = new javax.swing.JButton();
        boton1 = new javax.swing.JButton();
        boton2 = new javax.swing.JButton();
        boton3 = new javax.swing.JButton();
        fondo = new javax.swing.JLabel();

        BufferedImage imagenCursor =null;
        try {
            imagenCursor =  ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Cursor.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(imagenCursor, new Point(20, 0), "Cursor detective");
        setCursor(cursor);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(tamPant);
        setUndecorated(true);
        setPreferredSize(tamPant);
        getContentPane().setLayout(null);

        botonAtras.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        botonAtras.setForeground(new java.awt.Color(255, 255, 255));
        botonAtras.setText("Atras");
        botonAtras.setBorder(null);
        botonAtras.setBorderPainted(false);
        botonAtras.setContentAreaFilled(false);
        botonAtras.setFocusPainted(false);
        botonAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonAtrasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonAtrasMouseExited(evt);
            }
        });
        botonAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAtrasActionPerformed(evt);
            }
        });
        botonAtras.setBounds(50, 80, 210, 100);
        getContentPane().add(botonAtras);

        boton1.setFont(new java.awt.Font("Segoe UI", 0, (int)(tamPant.width*0.025)));
        boton1.setForeground(new java.awt.Color(255, 255, 255));
        boton1.setText("Cargar Partida 1");
        boton1.setContentAreaFilled(false);
        boton1.setFocusPainted(false);
        boton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton1MouseExited(evt);
            }
        });
        boton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton1ActionPerformed(evt);
            }
        });
        boton1.setBounds((int) (tamPant.width*0.1), (int) (tamPant.height*0.26), (int) (tamPant.width*0.67), (int) (tamPant.height*0.19)); ;
        getContentPane().add(boton1);

        boton2.setFont(new java.awt.Font("Segoe UI", 0, (int) (tamPant.width*0.025)));
        boton2.setForeground(new java.awt.Color(255, 255, 255));
        boton2.setText("Cargar Partida 2");
        boton2.setContentAreaFilled(false);
        boton2.setFocusPainted(false);
        boton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton2boton1MouseExited(evt);
                boton2MouseExited(evt);
            }
        });
        boton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton2ActionPerformed(evt);
            }
        });
        boton2.setBounds((int) (tamPant.width*0.1), (int) (tamPant.height*0.48), (int) (tamPant.width*0.67), (int) (tamPant.height*0.19)); ;
        getContentPane().add(boton2);

        boton3.setFont(new java.awt.Font("Segoe UI", 0, (int) (tamPant.width*0.025)));
        boton3.setForeground(new java.awt.Color(255, 255, 255));
        boton3.setText("Cargar Partida 3");
        boton3.setContentAreaFilled(false);
        boton3.setFocusPainted(false);
        boton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton3MouseExited(evt);
            }
        });
        boton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton3ActionPerformed(evt);
            }
        });
        boton3.setBounds((int) (tamPant.width*0.1), (int) (tamPant.height*0.70), (int) (tamPant.width*0.67), (int) (tamPant.height*0.19)); ;
        getContentPane().add(boton3);

        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(new File(("DatosAuxiliares/Fondo presentacion.jpeg")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono = new ImageIcon(imagen.getScaledInstance(tamPant.width, tamPant.height , Image.SCALE_SMOOTH));


        fondo.setIcon(icono);
        fondo.setPreferredSize(tamPant);
        fondo.setBounds(0,0, tamPant.width, tamPant.height );
        getContentPane().add(fondo);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void otonAtrasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonAtrasMouseEntered
        botonAtras.setForeground(Color.red);
    }//GEN-LAST:event_botonAtrasMouseEntered

    private void botonAtrasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonAtrasMouseExited
        botonAtras.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_botonAtrasMouseExited
    private void botonAtrasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonAtrasMouseEntered
        botonAtras.setForeground(Color.red);
    }//GEN-LAST:event_botonAtrasMouseEntered

    private void botonAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAtrasActionPerformed
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDeBoton();

        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
        timer.schedule(tarea, 1000);
    }//GEN-LAST:event_botonAtrasActionPerformed

    private void boton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton1MouseEntered
        boton1.setForeground(Color.red);
    }//GEN-LAST:event_boton1MouseEntered

    private void boton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton1MouseExited
        boton1.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_boton1MouseExited

    private void boton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton1ActionPerformed

    private void boton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton2MouseEntered
        boton2.setForeground(Color.red);
    }//GEN-LAST:event_boton2MouseEntered

    private void boton2boton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton2boton1MouseExited
        boton1.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_boton2boton1MouseExited

    private void boton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton2MouseExited
        boton2.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_boton2MouseExited

    private void boton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton2ActionPerformed

    private void boton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton3MouseEntered
        boton3.setForeground(Color.red);
    }//GEN-LAST:event_boton3MouseEntered

    private void boton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton3MouseExited
        boton3.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_boton3MouseExited

    private void boton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton3ActionPerformed
    public void arreglarBot(){

    }

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
        java.awt.EventQueue.invokeLater(() -> new CargarPartida().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton1;
    private javax.swing.JButton boton2;
    private javax.swing.JButton boton3;
    private javax.swing.JButton botonAtras;
    private javax.swing.JLabel fondo;
    // End of variables declaration//GEN-END:variables
}
