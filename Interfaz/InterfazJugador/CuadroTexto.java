/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Interfaz.InterfazJugador;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author ROBERTO
 */
public class CuadroTexto extends javax.swing.JPanel {
    private Dimension tamPant;
    /**
     * Creates new form CuadroTexto
     */
    public CuadroTexto(String texto, String personaje, ImageIcon imagen) {
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        initComponents(texto, personaje, imagen);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents(String texto, String personaje, ImageIcon imagen) {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();

        setLayout(null);

        setOpaque(false);
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jPanel1.setLayout(null);


        jLabel3.setText(personaje);
        if(personaje.equals("Detective")) {
            jLabel3.setBounds((int) (tamPant.width*0.5), 0, (int) (tamPant.width*0.2), (int) (tamPant.height*0.05));

        }else{
            jLabel3.setBounds((int) (tamPant.width*0.1), 0, (int) (tamPant.width*0.2), (int) (tamPant.height*0.05));
        }

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, (int) (tamPant.width*0.025)));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabel3);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(255, 255, 255, 128));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 2, (int) (tamPant.width*0.020)));
        jTextArea1.setRows(5);
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setText(texto);
        jTextArea1.setOpaque(true);

        jTextArea1.setBorder(null);
        jTextArea1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextArea1.setEnabled(false);

        jScrollPane1.setViewportView(jTextArea1);
        jScrollPane1.setBounds(0,  (int) (tamPant.height*0.052), (int) (tamPant.width*0.7), (int) (tamPant.height*0.25));
        jScrollPane1.setBackground(Color.black);
        jScrollPane1.setOpaque(true);
        jPanel1.add(jScrollPane1);
        jPanel1.setOpaque(false);
        jPanel1.setBounds((int) (tamPant.width*0.15), (int) (tamPant.height*0.7), (int) (tamPant.width*0.7), (int) (tamPant.height*0.27));

        add(jPanel1);

        if(personaje.equals("Detective")){

            jLabel2.setBounds((int) (tamPant.width*0.04), (int) (tamPant.width*0.14),  (int) (tamPant.width*0.5), (int) (tamPant.height*1.1));
        }else{
            jLabel2.setBounds((int) (tamPant.width*0.74), (int) (tamPant.width*0.14),  (int) (tamPant.width*0.5), (int) (tamPant.height*1.1));
        }
            BufferedImage imagen2 = null;
            try {
                imagen2 = ImageIO.read(new File(String.valueOf(imagen)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.2), (int) (tamPant.height*0.9), Image.SCALE_SMOOTH));
            jLabel2.setIcon(icono2);

        add(jLabel2);
    }



    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;

}
