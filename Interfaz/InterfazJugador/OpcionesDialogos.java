package Interfaz.InterfazJugador;

import DatosAuxiliaresLogica.UnionInterfaces;
import Logica.Dialogo;

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
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class OpcionesDialogos extends JDialog {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(OpcionesDialogos.class.getName());
    private Dimension tamPant;
    private JButton boton1;
    private JButton boton2;
    private JButton boton3;
    private  LinkedList<String> opciones;

    public OpcionesDialogos(java.awt.Frame parent, boolean modal, LinkedList<String> opc) {
        super(parent, modal);
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        opciones = new LinkedList<>(opc);
        initComponents();
    }

    private void initComponents() {

        getContentPane().setLayout(null);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 1));

        boton1 = new JButton();
        boton1.setFont(new Font("Segoe UI", 0, (int) (tamPant.width*0.015)));
        boton1.setForeground(new Color(255, 255, 255));
        boton1.setHorizontalAlignment(SwingConstants.CENTER);
        boton1.setText(opciones.getFirst());
        boton1.setBorderPainted(false);
        boton1.setContentAreaFilled(false);
        boton1.setFocusPainted(false);
        boton1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton1MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                boton1MouseExited(evt);
            }
        });
        boton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boton1ActionPerformed(evt);
            }
        });

        boton1.setBounds((int) (tamPant.width*0.01),(int) (tamPant.getHeight()*0.03), (int) (tamPant.width*0.43),(int) (tamPant.getHeight()*0.05));
        add(boton1);

        boton2 = new JButton();
        boton2.setFont(new Font("Segoe UI", 0, (int) (tamPant.width*0.015)));
        boton2.setForeground(new Color(255, 255, 255));
        boton2.setHorizontalAlignment(SwingConstants.CENTER);
        boton2.setText(opciones.get(1));
        boton2.setBorderPainted(false);
        boton2.setContentAreaFilled(false);
        boton2.setFocusPainted(false);
        boton2.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton2MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                boton2MouseExited(evt);
            }
        });
        boton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boton2ActionPerformed(evt);
            }
        });

        boton2.setBounds((int) (tamPant.width*0.01),(int) (tamPant.getHeight()*0.1), (int) (tamPant.width*0.43),(int) (tamPant.getHeight()*0.05));
        add(boton2);

        if(opciones.size()>2) {
            boton3 = new JButton();
            boton3.setFont(new Font("Segoe UI", 0, (int) (tamPant.width * 0.015)));
            boton3.setForeground(new Color(255, 255, 255));
            boton3.setHorizontalAlignment(SwingConstants.CENTER);
            boton3.setText(opciones.get(2));
            boton3.setBorderPainted(false);
            boton3.setContentAreaFilled(false);
            boton3.setFocusPainted(false);
            boton3.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    boton3MouseEntered(evt);
                }

                public void mouseExited(MouseEvent evt) {
                    boton3MouseExited(evt);
                }
            });
            boton3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    boton3ActionPerformed(evt);
                }
            });

            boton3.setBounds((int) (tamPant.width * 0.01), (int) (tamPant.getHeight() * 0.17), (int) (tamPant.width * 0.43), (int) (tamPant.getHeight() * 0.05));
            add(boton3);
        }


    }

    private void boton3ActionPerformed(ActionEvent evt) {
        UnionInterfaces.getInstance().setOpcionDialogo(3);
        this.dispose();
    }

    private void boton3MouseExited(MouseEvent evt) {
        boton3.setForeground(Color.WHITE);
    }

    private void boton3MouseEntered(MouseEvent evt) {
        boton3.setForeground(Color.red);
    }

    private void boton2ActionPerformed(ActionEvent evt) {
        UnionInterfaces.getInstance().setOpcionDialogo(2);
        this.dispose();
    }

    private void boton2MouseExited(MouseEvent evt) {
        boton2.setForeground(Color.WHITE);
    }

    private void boton2MouseEntered(MouseEvent evt) {
        boton2.setForeground(Color.red);
    }

    private void boton1ActionPerformed(ActionEvent evt) {
        UnionInterfaces.getInstance().setOpcionDialogo(1);
        this.dispose();
    }

    private void boton1MouseExited(MouseEvent evt) {
        boton1.setForeground(Color.WHITE);
    }

    private void boton1MouseEntered(MouseEvent evt) {
        boton1.setForeground(Color.red);
    }
}
