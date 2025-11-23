/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz.Escenarios;

import Interfaz.InterfazJugador.CuadroTexto;
import Logica.Dialogo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author ROBERTO
 */
public class Entrada extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Entrada.class.getName());
private Dimension tamPant;
private  ArrayList<Dialogo> dialogosTuto;
private int dialogoActual;
    private Timer timer;
    private TimerTask tarea;
    /**
     * Creates new form Entrada
     */
    public Entrada() {
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        dialogosTuto= new ArrayList<Dialogo>();
        dialogoActual=0;
        crearDialogos();
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
        cajaTexto  = new JPanel();
        fondo = new JLabel();
        flecha = new JButton();
        lugar = new JLabel();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        try {
            BufferedImage imagen = ImageIO.read(new File("DatosAuxiliares/Escenarios/entrada por afuera.jpg"));

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
            cajaTexto.setBounds((int) (tamPant.width*0.17), (int) (tamPant.height* 0.34), (int) (tamPant.width*0.72), (int) (tamPant.height*0.6));
            cajaTexto.setLayout(null);

            BufferedImage imagen2 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha arriba.png"));
            ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.11), Image.SCALE_SMOOTH));
            flecha.setIcon(icono2);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        flecha.setBounds((int) (tamPant.width*0.484), (int) (tamPant.height*0.77), (int) (tamPant.width*0.048), (int) (tamPant.height*0.124));
        flecha.setOpaque(true);
        flecha.setContentAreaFilled(false);
        flecha.setBorderPainted(false);
        flecha.setFocusPainted(false);
        flecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flechaActionPerformed(evt);
            }
        });

        ponerDialogo();
        getContentPane().add(cajaTexto);
        getContentPane().add(flecha);

        lugar.setText("Entrada");
        lugar.setOpaque(false);
        lugar.setForeground(Color.white);
        lugar.setFont(new java.awt.Font("Segoe UI", 0, (int) (tamPant.width*0.05)));
        lugar.setBounds((int) (tamPant.width*0.03), (int) (tamPant.height*0.06), (int) (tamPant.width*0.2), (int) (tamPant.height*0.1));
       getContentPane().add(lugar);
        getContentPane().add(fondo );




        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void flechaActionPerformed(ActionEvent evt) {
        Recepcion recepcion = new Recepcion();
        recepcion.setVisible(true);
        timer.schedule(tarea, 1000);
    }

    public void ponerDialogo() {
        if(dialogoActual < dialogosTuto.size()) {
             Dialogo aux = dialogosTuto.get(dialogoActual);
             CuadroTexto cT = new CuadroTexto(aux.getTexto(), aux.getPersonaje(), aux.getIcono());
            cT.setBounds(0, 0, (int) (tamPant.width* 0.7), (int) (tamPant.height* 0.6));

            cT.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    cTMouseClicked(evt);
                }
            });
            dialogoActual++;
            cajaTexto.removeAll();
            cajaTexto.add(cT);
        }else {
            cajaTexto.removeAll();
        }

    }

    private void cTMouseClicked(MouseEvent evt) {
        ponerDialogo();
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    public void crearDialogos(){
/*
    ImageIcon policia = new ImageIcon("DatosAuxiliares/NPC/Policia.png");
    ImageIcon dueno = new ImageIcon("DatosAuxiliares/NPC/Dueño.png");
    ImageIcon secretaria  = new ImageIcon("DatosAuxiliares/NPC/Secretaria.png");
    Dialogo d1= new Dialogo("Hola. Como estas.", "Policia", policia );
    Dialogo d2= new Dialogo("Espero que hayas dormido bien.", "Policia", policia );
    Dialogo d3= new Dialogo("Tenemos trabajo que hacer.", "Policia", policia );
    Dialogo d4= new Dialogo("Este parece ser un caso bastante serio.", "Policia", policia );
    Dialogo d5= new Dialogo("No se alarme oficial. El caso esta practicamente resuelto.", "Dueño", dueno );
    Dialogo d6= new Dialogo("Simplemente no pudo aguantar el estres laboral.", "Dueño", dueno );
    Dialogo d7= new Dialogo("Por favor señor, hasta que el detective diga lo contrario el caso seguira abierto.", "Policia", policia );
    Dialogo d8= new Dialogo("Como diga.", "Dueño", dueno );
    Dialogo d9= new Dialogo("Buenas. Soy la secretaria del dueño del museo, sere la encargada de guiarte por el museo y de llevarte hasta la escena del crimen. Por favor, venga por este camino", "Secretaria", secretaria );
    dialogosTuto.add(d1);
    dialogosTuto.add(d2);
    dialogosTuto.add(d3);
    dialogosTuto.add(d4);
    dialogosTuto.add(d5);
    dialogosTuto.add(d6);
    dialogosTuto.add(d7);
    dialogosTuto.add(d8);
    dialogosTuto.add(d9);
    */

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
