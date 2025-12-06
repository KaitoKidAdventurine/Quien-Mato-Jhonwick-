/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Interfaz.InterfazJugador;

import DatosAuxiliaresLogica.Informacion;
import Logica.Diario;
import Logica.Jugador;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author ROBERTO
 */
public class DiarioInterfaz extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DiarioInterfaz.class.getName());
    private Dimension tamPant;
    private LinkedList<Informacion> informacion;
    private java.util.Timer timer;
    private TimerTask tarea;
    /**
     * Creates new form Diario
     */
    public DiarioInterfaz(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        crearInfoDiario();
        informacion= Jugador.getInstancia().getDiario().getDialogosImportantes();

        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        timer = new Timer();
        tarea = new TimerTask() {
            @Override
            public void run() {
               revalidate();
               repaint();
            }
        };
        timer.scheduleAtFixedRate(tarea, 0, 10);
        initComponents();
    }

    private void initComponents() {

        getContentPane().setBackground(new Color(0, 0, 0, 0));
        setUndecorated(true);
        Rectangle nombre = new Rectangle((int) (tamPant.width *0.16), (int) (tamPant.height*0.05), (int) (tamPant.width*0.25), (int) (tamPant.height*0.07));
        Rectangle foto= new Rectangle((int) (tamPant.width *0.01), (int) (tamPant.height*0.03), (int) (tamPant.width*0.12), (int) (tamPant.height*0.12));
        Rectangle datos = new Rectangle((int) (tamPant.width *0.36), (int) (tamPant.height*0.1), (int) (tamPant.width*0.43), (int) (tamPant.height*0.51));
        Rectangle registros = new Rectangle((int) (tamPant.width *0.03), (int) (tamPant.height*0.17), (int) (tamPant.width*0.3), (int) (tamPant.height*0.46));
        Rectangle panel = new Rectangle(0, 0, (int) (tamPant.width*0.75), (int) (tamPant.height*0.75));
        Rectangle labelDatos = new Rectangle((int) (tamPant.width*0.36), (int) (tamPant.height*0.03), (int) (tamPant.width*0.32), (int) (tamPant.height*0.08));

        jTabbedPane1 = new JTabbedPane();
        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabelR1 = new JLabel();
        registro1 = new JTextArea();
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        jPanel2 = new JPanel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jScrollPane2 = new JScrollPane();
        jTextArea2 = new JTextArea();
        jPanel3 = new JPanel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jScrollPane3 = new JScrollPane();
        jTextArea3 = new JTextArea();
        jPanel4 = new JPanel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        jScrollPane4 = new JScrollPane();
        jTextArea4 = new JTextArea();
        jPanel5 = new JPanel();
        jLabel9 = new JLabel();
        jLabel10 = new JLabel();
        jScrollPane5 = new JScrollPane();
        jTextArea5 = new JTextArea();
        jPanel6 = new JPanel();
        jLabel11 = new JLabel();
        jLabel12 = new JLabel();
        jScrollPane6 = new JScrollPane();
        jTextArea6 = new JTextArea();
        jPanel7 = new JPanel();
        jLabel13 = new JLabel();
        jLabel14 = new JLabel();
        jScrollPane7 = new JScrollPane();
        jTextArea7 = new JTextArea();
        jPanel8 = new JPanel();
        jLabel15 = new JLabel();
        jLabel16 = new JLabel();
        jScrollPane8 = new JScrollPane();
        jTextArea8 = new JTextArea();
        jPanel9 = new JPanel();
        jLabel17 = new JLabel();
        jLabel18 = new JLabel();
        jScrollPane9 = new JScrollPane();
        jTextArea9 = new JTextArea();
        jPanel10 = new JPanel();
        jLabel19 = new JLabel();
        jLabel20 = new JLabel();
        jScrollPane10 = new JScrollPane();
        jTextArea10 = new JTextArea();
        jPanel11 = new JPanel();
        jLabel21 = new JLabel();
        jLabel22 = new JLabel();
        jScrollPane11 = new JScrollPane();
        jTextArea11 = new JTextArea();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        setBackground(new Color(0, 0, 0, 75));

    jTabbedPane1.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setTabPlacement(JTabbedPane.TOP);
        jTabbedPane1.setOpaque(false);

     jPanel1.setBackground(new Color(18, 0, 90, 80));
        jPanel1.setLayout(null);

        jLabel1.setText("Detective");
        jLabel1.setBounds(nombre);
        jLabel1.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.016)));
        jLabel1.setForeground(Color.black);
        jPanel1.add(jLabel1);

        jLabel2.setBounds(foto);
        jLabel2.setOpaque(true);
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/Personajes/detective reducido.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.11), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
        jLabel2.setIcon(icono);
        jLabel2.setBorder(new LineBorder(Color.black, 2, true));
        jLabel2.setBackground(new Color(255, 255, 255, 30));
        jPanel1.add(jLabel2);


        jLabelR1.setText("Datos relacionados con la investigacion");
        jLabelR1.setBounds(labelDatos);
        jLabelR1.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.016)));
        jLabelR1.setForeground(Color.black);
        jPanel1.add(jLabelR1);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setBackground(new Color(0, 0, 0, 0));
        jTextArea1.setEnabled(false);
        jTextArea1.setFocusable(false);
        jTextArea1.setHighlighter(null);
        jTextArea1.setDisabledTextColor(Color.black);
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setEditable(false);

        Informacion info1 = informacion.getFirst();
        Iterator<String> iterator1= info1.getListaDeDialogos().iterator();
        while(iterator1.hasNext()){
            String contenido = iterator1.next();
            if(!jTextArea1.getText().equals("")) {

                jTextArea1.setText(jTextArea1.getText() + "\n" + "·" + contenido);
            }else
                jTextArea1.setText("·" + contenido);
        }
        jTextArea1.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.013)));

        jScrollPane1.setViewportView(jTextArea1);
        jScrollPane1.setBounds(datos);
        jScrollPane1.setOpaque(true);
        jScrollPane1.setBackground(new Color(0, 0, 0, 0));
        jScrollPane1.setBorder(null);

        jPanel1.add(jScrollPane1);



        registro1.setColumns(20);
        registro1.setRows(5);
        registro1.setHighlighter(null);
        registro1.setFocusable(false);
        registro1.setDisabledTextColor(Color.black);
        registro1.setLineWrap(true);
        registro1.setWrapStyleWord(true);
        registro1.setEditable(false);
        registro1.setBackground(new Color(0, 0, 0, 0));
        registro1.setText("   Datos  Generales: \nEdad:   28       Sexo: Masculino   \n" + "Ocupacion:   Detective \n" + "\n\n\n  Analisis de personalidad: \n"
        +"Inteligente, frio, calculador, pesimista, serio, poco empatico");
        registro1.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.015)));
        registro1.setBounds(registros);
        jPanel1.add(registro1);

        BufferedImage imagen2 = null;
        try {
            imagen2 = ImageIO.read(new File("DatosAuxiliares/InterfazUsuario/flecha derecha.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.04), (int) (tamPant.height*0.07), Image.SCALE_SMOOTH));

        jTabbedPane1.addTab("Investigacion", jPanel1);
        jPanel2.setLayout(null);

        jLabel3.setText("Image del NPC");
        jLabel3.setBounds(40, 0, 150, 90);
        jPanel2.add(jLabel3);

        jLabel4.setText("Nombre del NPC");
        jLabel4.setBounds(270, 30, 233, 28);
        jPanel2.add(jLabel4);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setText("2");
        jScrollPane2.setViewportView(jTextArea2);
        jScrollPane2.setBounds(30, 90, 590, 390);
        jPanel2.add(jScrollPane2);

        jTabbedPane1.addTab("Jefe", jPanel2);

        jPanel3.setLayout(null);

        jLabel5.setText("Image del NPC");
        jLabel5.setBounds(40, 0, 150, 90);
        jPanel3.add(jLabel5);

        jLabel6.setText("Nombre del NPC");
        jLabel6.setBounds(270, 30, 233, 28);
        jPanel3.add(jLabel6);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);
        jTextArea3.setBounds(30, 90, 590, 390);
        jPanel3.add(jScrollPane3);

        jTabbedPane1.addTab("Policia", jPanel3);

        jPanel4.setLayout(null);

        jLabel7.setText("Image del NPC");
        jLabel7.setBounds(40, 0, 150, 90);
        jPanel4.add(jLabel7);

        jLabel8.setText("Nombre del NPC");
        jLabel8.setBounds(270, 30, 233, 28);
        jPanel4.add(jLabel8);

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane4.setViewportView(jTextArea4);
        jScrollPane4.setBounds(30, 90, 590, 390);
        jPanel4.add(jScrollPane4);

        jTabbedPane1.addTab("Dueño", jPanel4);

        jPanel5.setLayout(null);

        jLabel9.setText("Image del NPC");
        jLabel9.setBounds(40, 0, 150, 90);
        jPanel5.add(jLabel9);

        jLabel10.setText("Nombre del NPC");
        jLabel10.setBounds(270, 30, 233, 28);
        jPanel5.add(jLabel10);

        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jScrollPane5.setViewportView(jTextArea5);
        jScrollPane5.setBounds(30, 90, 590, 390);
        jPanel5.add(jScrollPane5);

        jTabbedPane1.addTab("Esposa del dueño", jPanel5);

        jPanel6.setLayout(null);

        jLabel11.setText("Image del NPC");
        jLabel11.setBounds(40, 0, 150, 90);
        jPanel6.add(jLabel11);

        jLabel12.setText("Nombre del NPC");
        jLabel12.setBounds(270, 30, 233, 28);
        jPanel6.add(jLabel12);

        jTextArea6.setColumns(20);
        jTextArea6.setRows(5);
        jScrollPane6.setViewportView(jTextArea6);
        jScrollPane6.setBounds(30, 90, 590, 390);
        jPanel6.add(jScrollPane6);

        jTabbedPane1.addTab("Secretaria", jPanel6);

        jPanel7.setLayout(null);

        jLabel13.setText("Image del NPC");
        jLabel13.setBounds(40, 0, 150, 90);
        jPanel7.add(jLabel13);

        jLabel14.setText("Nombre del NPC");
        jLabel14.setBounds(270, 30, 233, 28);
        jPanel7.add(jLabel14);

        jTextArea7.setColumns(20);
        jTextArea7.setRows(5);
        jScrollPane7.setViewportView(jTextArea7);
        jScrollPane7.setBounds(30, 90, 590, 390);
        jPanel7.add(jScrollPane7);

        jTabbedPane1.addTab("Guia 1", jPanel7);
        jPanel8.setLayout(null);

        jLabel15.setText("Image del NPC");
        jLabel15.setBounds(40, 0, 150, 90);
        jPanel8.add(jLabel15);

        jLabel16.setText("Nombre del NPC");
        jLabel16.setBounds(270, 30, 233, 28);
        jPanel8.add(jLabel16);

        jTextArea8.setColumns(20);
        jTextArea8.setRows(5);
        jScrollPane8.setViewportView(jTextArea8);
        jScrollPane8.setBounds(30, 90, 590, 390);
        jPanel8.add(jScrollPane8);

        jTabbedPane1.addTab("Guia 2", jPanel8);

        jPanel9.setLayout(null);

        jLabel17.setText("Image del NPC");
        jLabel17.setBounds(40, 0, 150, 90);
        jPanel9.add(jLabel17);

        jLabel18.setText("Nombre del NPC");
        jLabel18.setBounds(270, 30, 233, 28);
        jPanel9.add(jLabel18);

        jTextArea9.setColumns(20);
        jTextArea9.setRows(5);
        jScrollPane9.setViewportView(jTextArea9);
        jScrollPane9.setBounds(30, 90, 590, 390);
        jPanel9.add(jScrollPane9);

        jTabbedPane1.addTab("Seguridad", jPanel9);

        jPanel10.setLayout(null);

        jLabel19.setText("Image del NPC");
        jLabel19.setBounds(40, 0, 150, 90);
        jPanel10.add(jLabel19);

        jLabel20.setText("Nombre del NPC");
        jLabel20.setBounds(270, 30, 233, 28);
        jPanel10.add(jLabel20);
        jTextArea10.setColumns(20);
        jTextArea10.setRows(5);
        jScrollPane10.setViewportView(jTextArea10);
        jScrollPane10.setBounds(30, 90, 590, 390);
        jPanel10.add(jScrollPane10);

        jTabbedPane1.addTab("Vagabundo", jPanel10);

        jPanel11.setLayout(null);

        jLabel21.setText("Image del NPC");
        jLabel21.setBounds(40, 0, 150, 90);
        jPanel11.add(jLabel21);

        jLabel22.setText("Nombre del NPC");
        jLabel22.setBounds(270, 30, 233, 28);
        jPanel11.add(jLabel22);

        jTextArea11.setColumns(20);
        jTextArea11.setRows(5);
        jScrollPane11.setViewportView(jTextArea11);
        jScrollPane11.setBounds(30, 90, 590, 390);
        jPanel11.add(jScrollPane11);

        jTabbedPane1.addTab("Victima", jPanel11);
        jTabbedPane1.setBounds((int) (tamPant.width*0.125), (int) (tamPant.height*0.15), (int) (tamPant.width*0.75), (int) (tamPant.height*0.75));
        getContentPane().add(jTabbedPane1);

        pack();
    }


    public void crearInfoDiario(){
        Diario diario = Jugador.getInstancia().getDiario();
        Informacion info = new Informacion("Detective");
        info.agregarDialogo("Estoy cansado");
        info.agregarDialogo("Necesito dormir");
        info.agregarDialogo("Quiero ver One Piece");
        info.agregarDialogo("Me gusta cantar");
        info.agregarDialogo("Deberia de comprar yuca mañana");
        info.agregarDialogo("Estoy cansado");
        info.agregarDialogo("Necesito dormir");
        info.agregarDialogo("Quiero ver One Piece");
        info.agregarDialogo("Me gusta cantar");
        info.agregarDialogo("Deberia de comprar yuca mañana");
        info.agregarDialogo("Estoy cansado");
        info.agregarDialogo("Necesito dormir");
        info.agregarDialogo("Quiero ver One Piece");
        info.agregarDialogo("Me gusta cantar");
        info.agregarDialogo("Deberia de comprar yuca mañana");
        info.agregarDialogo("Estoy cansado");
        info.agregarDialogo("Necesito dormir");
        info.agregarDialogo("Quiero ver One Piece");
        info.agregarDialogo("Me gusta cantar");
        info.agregarDialogo("Deberia de comprar yuca mañana");
        info.agregarDialogo("Estoy cansado");
        info.agregarDialogo("Necesito dormir");
        info.agregarDialogo("Quiero ver One Piece");
        info.agregarDialogo("Me gusta cantar");
        info.agregarDialogo("Deberia de comprar yuca mañana");
        info.agregarDialogo("Estoy cansado");
        info.agregarDialogo("Necesito dormir");
        info.agregarDialogo("Quiero ver One Piece");
        info.agregarDialogo("Me gusta cantar");
        info.agregarDialogo("Deberia de comprar yuca mañana");
        info.agregarDialogo("Estoy cansado");
        info.agregarDialogo("Necesito dormir");
        info.agregarDialogo("Quiero ver One Piece");
        info.agregarDialogo("Me gusta cantar");
        info.agregarDialogo("Deberia de comprar yuca mañana");
        info.agregarDialogo("Estoy cansado");
        info.agregarDialogo("Necesito dormir");
        info.agregarDialogo("Quiero ver One Piece");
        info.agregarDialogo("Me gusta cantar");
        info.agregarDialogo("Deberia de comprar yuca mañana");
        info.agregarDialogo("Estoy cansado");
        info.agregarDialogo("Necesito dormir");
        info.agregarDialogo("Quiero ver One Piece");
        info.agregarDialogo("Me gusta cantar");
        info.agregarDialogo("Deberia de comprar yuca mañana");
        info.agregarDialogo("Estoy cansado");
        info.agregarDialogo("Necesito dormir");
        info.agregarDialogo("Quiero ver One Piece");
        info.agregarDialogo("Me gusta cantar");
        info.agregarDialogo("Deberia de comprar yuca mañana");
        diario.agregarInformacion(info);
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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DiarioInterfaz dialog = new DiarioInterfaz(new javax.swing.JFrame(), true);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea10;
    private javax.swing.JTextArea jTextArea11;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextArea jTextArea7;
    private javax.swing.JTextArea jTextArea8;
    private javax.swing.JTextArea jTextArea9;
    private JTextArea registro1;
    private JLabel jLabelR1;
    // End of variables declaration//GEN-END:variables
}
