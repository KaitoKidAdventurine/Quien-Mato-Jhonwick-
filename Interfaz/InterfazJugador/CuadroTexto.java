/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Interfaz.InterfazJugador;

import javax.swing.*;
import java.awt.*;

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
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, (int) (tamPant.width*0.025)));
        jLabel3.setBounds((int) (tamPant.width*0.1), 0, (int) (tamPant.width*0.13), (int) (tamPant.height*0.05));
        jPanel1.add(jLabel3);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(255, 255, 255));
        jTextArea1.setColumns(10);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 0, (int) (tamPant.width*0.020)));
        jTextArea1.setRows(5);
        jTextArea1.setText(texto);
        jTextArea1.setOpaque(false);
        jTextArea1.setBounds(0,  (int) (tamPant.height*0.053), (int) (tamPant.width*0.64), (int) (tamPant.height*0.25));
        jTextArea1.setBorder(null);
        jTextArea1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextArea1.setEnabled(false);
        jScrollPane1.setViewportView(jTextArea1);
        jScrollPane1.setBounds(0,  (int) (tamPant.height*0.053), (int) (tamPant.width*0.64), (int) (tamPant.height*0.25));

        jScrollPane1.setOpaque(true);
        jPanel1.add(jScrollPane1);
        jPanel1.setOpaque(false);
        jPanel1.setBounds((int) (tamPant.width*0.01), (int) (tamPant.height*0.3), (int) (tamPant.width*0.64), (int) (tamPant.height*0.27));

        add(jPanel1);

        jLabel2.setBounds((int) (tamPant.width*0.4), 0,  (int) (tamPant.width*0.42), (int) (tamPant.height*1.1));
        jLabel2.setIcon(imagen);
        add(jLabel2);
    }



    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;

}
