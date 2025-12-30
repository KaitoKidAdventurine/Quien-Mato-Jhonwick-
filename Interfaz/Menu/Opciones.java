/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz.Menu;

import DatosAuxiliaresLogica.EfectosEspeciales;
import Logica.Reproductor;

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
public class Opciones extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Opciones.class.getName());
    private Dimension tamPant;
    int largoBot;
    int anchoBot;
    int xBot;
    int yBot;
    private Timer timer;
    private TimerTask tarea;
    private Reproductor reproductor;

    public Opciones() {
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        largoBot = (int) (tamPant.getWidth()*0.1);
        anchoBot = (int) (tamPant.getHeight()*0.05);
        xBot = (int) (tamPant.getWidth()*0.2);
        yBot = (int) tamPant.getWidth();

        reproductor = Reproductor.getInstancia();
        initComponents();
        configurarSlider();

        timer = new Timer();
        tarea = new TimerTask() {
            @Override
            public void run() {
                dispose();
            }
        };
    }

    private void configurarSlider() {
        jSlider.setMinimum(0);
        jSlider.setMaximum(100);
        jSlider.setValue((int)(reproductor.getVolumen() * 100));
        jSlider.setPaintTicks(true);
        jSlider.setPaintTrack(true);
        jSlider.setMajorTickSpacing(25);
        jSlider.setMinorTickSpacing(5);

        jSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderStateChanged(evt);
            }
        });
    }

    private void actualizarVolumen(int porcentaje) {
        float volumenNormalizado = porcentaje / 100.0f;
        reproductor.setVolumen(volumenNormalizado);
        volumen.setText("Volumen: " + porcentaje + "%");
        System.out.println(" Volumen actualizado a: " + porcentaje + "%");
    }

    private void controlarMusica(boolean activar) {
        if (activar) {
            reproductor.activarMusica();
            cajaMusica.setSelected(true);
            jSlider.setEnabled(true);
        } else {
            reproductor.desactivarMusica();
            cajaMusica.setSelected(false);
            jSlider.setEnabled(false);
        }
    }


    private void initComponents() {

        botonAtras = new javax.swing.JButton();
        volumen = new javax.swing.JLabel();
        musica = new javax.swing.JLabel();
        cajaMusica = new javax.swing.JCheckBox();
        jSlider = new javax.swing.JSlider();
        volumenPanel = new javax.swing.JPanel();
        musicaPanel = new javax.swing.JPanel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(tamPant);
        setUndecorated(true);
        setPreferredSize(tamPant);
        getContentPane().setLayout(null);

        BufferedImage imagenCursor =null;
        try {
            imagenCursor =  ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Cursor.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(imagenCursor, new Point(20, 0), "Cursor detective");
        setCursor(cursor);


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

        volumen.setFont(new java.awt.Font("Segoe UI", 0, (int)(largoBot*0.20)));
        volumen.setForeground(new java.awt.Color(255, 255, 255));
        volumen.setText("Volumen: " + (int)(reproductor.getVolumen() * 100) + "%");
        volumen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                volumenMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                volumenMouseExited(evt);
            }
        });
        volumen.setBounds(xBot, (int)(yBot *0.3), largoBot*2 , anchoBot);
        getContentPane().add(volumen);

        musica.setFont(new java.awt.Font("Segoe UI", 0, (int)(largoBot*0.20)));
        musica.setForeground(new java.awt.Color(255, 255, 255));
        musica.setText("Musica");
        musica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                musicaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                musicaMouseExited(evt);
            }
        });
        musica.setBounds(xBot, (int)(yBot *0.2), largoBot , anchoBot);
        getContentPane().add(musica);

        cajaMusica.setSelected(true);
        cajaMusica.setContentAreaFilled(false);
        cajaMusica.setFocusPainted(false);
        cajaMusica.setMargin(new java.awt.Insets(1, 1, 1, 1));
        cajaMusica.setMaximumSize(new java.awt.Dimension(40, 40));
        cajaMusica.setMinimumSize(new java.awt.Dimension(40, 19));
        cajaMusica.setPreferredSize(new java.awt.Dimension(40, 40));
        cajaMusica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cajaMusicaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cajaMusicaMouseExited(evt);
            }
        });
        cajaMusica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajaMusicaActionPerformed(evt);
            }
        });
        cajaMusica.setBounds((int)(xBot*2.6), (int)(yBot *0.2), (int)(largoBot*0.8), anchoBot);
        getContentPane().add(cajaMusica);

        jSlider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jSliderMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jSliderMouseExited(evt);
            }
        });
        jSlider.setBounds((int)(xBot*2.6), (int)(yBot *0.3), (int)(largoBot*1.9),  anchoBot);
        jSlider.setOpaque(false);
        getContentPane().add(jSlider);

        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(new File(("DatosAuxiliares/Fondo presentacion.jpeg")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono = new ImageIcon(imagen.getScaledInstance(tamPant.width, tamPant.height , Image.SCALE_SMOOTH));


        fondo.setIcon(icono);
        fondo.setMaximumSize(tamPant);
        fondo.setMinimumSize(tamPant);
        fondo.setPreferredSize(tamPant);
        fondo.setBounds(0,0, tamPant.width, tamPant.height );
        getContentPane().add(fondo);

        volumenPanel.setOpaque(false);
        volumenPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                volumenPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                volumenPanelMouseExited(evt);
            }
        });
        volumenPanel.setBounds(xBot, (int)(yBot *0.28), (int)(largoBot*4) , (int)(anchoBot*2));
        getContentPane().add(volumenPanel);

        musicaPanel.setOpaque(false);
        musicaPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                musicaPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                musicaPanelMouseExited(evt);
            }
        });
        musicaPanel.setBounds(xBot, (int)(yBot *0.18),  (int)(largoBot*4) ,(int)(anchoBot*2));
        getContentPane().add(musicaPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAtrasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonAtrasMouseEntered
        botonAtras.setForeground(Color.red);
    }//GEN-LAST:event_botonAtrasMouseEntered

    private void botonAtrasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonAtrasMouseExited
        botonAtras.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_botonAtrasMouseExited

    private void botonAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAtrasActionPerformed
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDeBoton();

        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
        timer.schedule(tarea, 1000);
    }//GEN-LAST:event_botonAtrasActionPerformed

    private void musicaPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_musicaPanelMouseEntered
        musica.setForeground(Color.red);
    }//GEN-LAST:event_musicaPanelMouseEntered

    private void musicaPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_musicaPanelMouseExited
        musica.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_musicaPanelMouseExited

    private void volumenPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volumenPanelMouseEntered
        volumen.setForeground(Color.red);
    }//GEN-LAST:event_volumenPanelMouseExited

    private void volumenPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volumenPanelMouseExited
        volumen.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_volumenPanelMouseExited

    private void musicaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_musicaMouseEntered
        musica.setForeground(Color.red);
    }//GEN-LAST:event_musicaMouseEntered

    private void musicaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_musicaMouseExited
        musica.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_musicaMouseExited

    private void volumenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volumenMouseEntered
        volumen.setForeground(Color.red);
    }//GEN-LAST:event_volumenMouseEntered

    private void volumenMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volumenMouseExited
        volumen.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_volumenMouseExited

    private void cajaMusicaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cajaMusicaMouseEntered
        musica.setForeground(Color.red);
    }//GEN-LAST:event_cajaMusicaMouseEntered

    private void cajaMusicaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cajaMusicaMouseExited
        musica.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_cajaMusicaMouseExited

    private void jSliderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSliderMouseEntered
        volumen.setForeground(Color.red);
    }//GEN-LAST:event_jSliderMouseEntered

    private void jSliderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSliderMouseExited
        volumen.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_jSliderMouseExited

    private void jSliderStateChanged(javax.swing.event.ChangeEvent evt) {
        if (!jSlider.getValueIsAdjusting()) {
            int porcentaje = jSlider.getValue();
            actualizarVolumen(porcentaje);
        }
    }

    private void cajaMusicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cajaMusicaActionPerformed
        controlarMusica(cajaMusica.isSelected());
    }//GEN-LAST:event_cajaMusicaActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new Opciones().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAtras;
    private javax.swing.JCheckBox cajaMusica;
    private javax.swing.JLabel fondo;
    private javax.swing.JSlider jSlider;
    private javax.swing.JLabel musica;
    private javax.swing.JPanel musicaPanel;
    private javax.swing.JLabel volumen;
    private javax.swing.JPanel volumenPanel;
    // End of variables declaration//GEN-END:variables
}