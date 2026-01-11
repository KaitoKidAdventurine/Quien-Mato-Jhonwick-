/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Interfaz.InterfazJugador;

import DatosAuxiliaresLogica.UnionInterfaces;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author ROBERTO
 */
public class CuadroTexto extends javax.swing.JPanel {
    private Dimension tamPant;
    private Timer timer;
    private TimerTask tarea;
    private int valorPesta;
    /**
     * Creates new form CuadroTexto
     */
    public CuadroTexto(String texto, String personaje, ImageIcon imagen, boolean dialogoImportante) {
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        valorPesta=0;
        timer = new java.util.Timer();
        tarea = new TimerTask() {
            @Override
            public void run() {
                pestanear();

            }
        };
        initComponents(texto, personaje, imagen, dialogoImportante);
    }

    private void pestanear() {
        switch (valorPesta){
            case 0:
                jLabel4.setForeground(new Color(220, 220, 220, 0));
                break;
            case 3:
            case 33:
                jLabel4.setForeground(new Color(220, 220, 220, 40));
                break;

            case 6:
            case 30:
                jLabel4.setForeground(new Color(220, 220, 220, 80));
                break;

            case 9:
            case 27:
                jLabel4.setForeground(new Color(220, 220, 220, 120));
                break;

            case 12:
            case 24:
                jLabel4.setForeground(new Color(220, 220, 220, 160));
                break;
            case 15:
            case 21:
                jLabel4.setForeground(new Color(220, 220, 220, 180));
                break;
            case 18:
                jLabel4.setForeground(new Color(220, 220, 220, 220));
                break;

            case 36:
               valorPesta=0;

                break;
            default:
                break;
        }
            valorPesta++;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents(String texto, String personaje, ImageIcon imagen, boolean importante) {

        jPanel1 = new JPanel();
        jLabel3 = new JLabel();
        jLabel2 = new JLabel();
        jLabel4 = new JLabel();
        parrafo = new JLabel();
        capitanFondo = new JLabel();

        setLayout(null);


        setBackground(new Color(0, 0, 0, 0));
        jPanel1.setBackground(new Color(0, 0, 0, 100));
        jPanel1.setFocusable(true);
        jPanel1.setLayout(null);

        jLabel4.setText("Información añadida al diario");
        if(!importante) {
            jLabel4.setVisible(false);

        }else timer.scheduleAtFixedRate(tarea, 0, 20);
        jLabel4.setBounds((int) (tamPant.width*0.65), (int) (tamPant.height*0.18), (int) (tamPant.width*0.28), (int) (tamPant.height*0.05));
        jLabel4.setFont(new Font("Segoe UI", 0, (int) (tamPant.width*0.017)));

        add(jLabel4);


        jLabel3.setText(personaje);
        if(personaje.equals("Detective")) {
            jLabel3.setBounds((int) (tamPant.width*0.7), (int) (tamPant.height*0.7), (int) (tamPant.width*0.2), (int) (tamPant.height*0.05));

        }else{
            jLabel3.setBounds((int) (tamPant.width*0.252), (int) (tamPant.height*0.7), (int) (tamPant.width*0.2), (int) (tamPant.height*0.05));
        }

        jLabel3.setFont(new Font("Segoe UI", 0, (int) (tamPant.width*0.025)));
        jLabel3.setForeground(new Color(255, 255, 255));
        add(jLabel3);


        parrafo.setText("<html>" + texto );
        parrafo.setBounds((int) (tamPant.height*0.32),  (int) (tamPant.height*0.015), (int) (tamPant.width*0.68), (int) (tamPant.height*0.25));
        parrafo.setForeground(new Color(255, 255, 255, 200));
        parrafo.setOpaque(true);
        parrafo.setVerticalAlignment(SwingConstants.TOP);
        parrafo.setBackground(new Color(0, 0, 0, 0));

        parrafo.setFont(new Font("Segoe UI", 2, (int) (tamPant.width*0.020)));
        jPanel1.add(parrafo);


        jPanel1.setBounds( 0, (int) (tamPant.height*0.75), tamPant.width, (int) (tamPant.height*0.27));

        add(jPanel1);

        switch (personaje){
            case ("Detective"):
                jLabel2.setBounds((int) (tamPant.width*0.04), (int) (tamPant.width*0.13),  (int) (tamPant.width*0.5), (int) (tamPant.height*1.1));
                break;

            case ("Dueño"):
                jLabel2.setBounds((int) (tamPant.width*0.65), (int) (tamPant.width*0.14),  (int) (tamPant.width*0.5), (int) (tamPant.height*1.1));
                break;
            case("Guia"):
                jLabel2.setBounds((int) (tamPant.width*0.68), (int) (tamPant.width*0.14),  (int) (tamPant.width*0.5), (int) (tamPant.height*1.1));
                break;
            default:
                jLabel2.setBounds((int) (tamPant.width*0.74), (int) (tamPant.width*0.14),  (int) (tamPant.width*0.5), (int) (tamPant.height*1.1));
                break;
        }

        BufferedImage imagen2 = null;
        try {
            imagen2 = ImageIO.read(new File(String.valueOf(imagen)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        switch (personaje){
            case ("Dueño"):
                ImageIcon icono = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.3), (int) (tamPant.height*0.9), Image.SCALE_SMOOTH));
                jLabel2.setIcon(icono);
                break;

            case("Guia"):
                ImageIcon icono1 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.24), (int) (tamPant.height*0.9), Image.SCALE_SMOOTH));
                jLabel2.setIcon(icono1);
                break;
            case("Detective"):
                ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.225), (int) (tamPant.height*0.9), Image.SCALE_SMOOTH));
                jLabel2.setIcon(icono2);
                break;
            default:
                ImageIcon icono3 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.2), (int) (tamPant.height*0.9), Image.SCALE_SMOOTH));
                jLabel2.setIcon(icono3);
                break;
        }

        add(jLabel2);

        if(personaje.equals("Capitan")){
            BufferedImage imagenCapi = null;
            try {
                imagenCapi = ImageIO.read(new File(("DatosAuxiliares/Personajes/Habitacion capitan.png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ImageIcon iconoCapi = new ImageIcon(imagenCapi.getScaledInstance(tamPant.width, tamPant.height , Image.SCALE_SMOOTH));
            capitanFondo.setIcon(iconoCapi);
            capitanFondo.setBounds(0, 0, tamPant.width, tamPant.height);
            add(capitanFondo);
        }
        if(personaje.equals("Foto que poner")){
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File(("DatosAuxiliares/Cinematica/Foto Oficina.png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(tamPant.width, tamPant.height , Image.SCALE_SMOOTH));
            capitanFondo.setIcon(imageIcon);
            capitanFondo.setBounds(0, 0, tamPant.width, tamPant.height);
            add(capitanFondo);
            jLabel3.setText("");
        }

    }


    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private JLabel parrafo;
    private  JLabel capitanFondo;
}
