package Interfaz.InterfazJugador;

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

public class TelefonoLlamar extends JPanel {
    private Dimension tamPant;
    private JButton llamarJefe;
    private JButton llamarOtro;
    private JButton salir;
    private JLabel contactos;
    public TelefonoLlamar(){
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        initComponents();
    }

    private void initComponents() {
        llamarJefe = new JButton();
        salir = new JButton();
        contactos = new JLabel();
        llamarOtro = new JButton();
        setBackground(new Color(0, 0, 0, 0));
        setLayout(null);

        llamarJefe.setBorderPainted(false);
        llamarJefe.setContentAreaFilled(false);
        llamarJefe.setFocusPainted(false);
        llamarJefe.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        llamarJefe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        llamarJefe.setBounds((int) (tamPant.width*0.017), (int) (tamPant.height*0.13),(int) (tamPant.width*0.23), (int) (tamPant.height*0.07));
        llamarJefe.setBackground(Color.blue);
        add(llamarJefe);


        llamarOtro.setBorderPainted(false);
        llamarOtro.setContentAreaFilled(false);
        llamarOtro.setFocusPainted(false);
        llamarOtro.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                jButton3MouseExited(evt);
            }
        });
        llamarOtro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        llamarOtro.setBounds((int) (tamPant.width*0.017), (int) (tamPant.height*0.21),(int) (tamPant.width*0.23), (int) (tamPant.height*0.4));
        llamarOtro.setBackground(Color.blue);
        add(llamarOtro);


        salir.setBorderPainted(false);
        salir.setContentAreaFilled(false);
        salir.setFocusPainted(false);
        salir.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });

        BufferedImage imagen3 = null;

        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/Telefono/atras.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.05), (int) (tamPant.height*0.07), Image.SCALE_SMOOTH));
        salir.setIcon(icono3);

        salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        salir.setBounds((int) (tamPant.height*0.11), (int) (tamPant.height*0.66),(int) (tamPant.width*0.05), (int) (tamPant.height*0.07));

        add(salir);

        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/Telefono/Contactos.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.255), (int) (tamPant.height*0.72), Image.SCALE_SMOOTH));
        contactos.setIcon(icono);
        contactos.setBounds((int) (tamPant.width*0.005),0, (int) (tamPant.width*0.255), (int) (tamPant.height*0.72));

        add(contactos);

    }

    private void jButton3ActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "No creo que sea buena idea llamarlo a esta hora");
    }

    private void jButton3MouseExited(MouseEvent evt) {

    }

    private void jButton3MouseEntered(MouseEvent evt) {
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        getParent().getComponent(0).setVisible(true);
        getParent().getComponent(1).setVisible(false);
        getParent().revalidate();
        getParent().repaint();
        getParent().remove(1);

    }

    private void jButton2MouseExited(MouseEvent evt) {
        BufferedImage imagen3 = null;

        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/Telefono/atras.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.05), (int) (tamPant.height*0.07), Image.SCALE_SMOOTH));
        salir.setIcon(icono3);

    }

    private void jButton2MouseEntered(MouseEvent evt) {
        BufferedImage imagen3 = null;

        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/Telefono/atras r.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.05), (int) (tamPant.height*0.07), Image.SCALE_SMOOTH));
        salir.setIcon(icono3);

    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "Llamar al jefe");
    }

    void jButton1MouseExited(MouseEvent evt) {
    }


    private void jButton1MouseEntered(MouseEvent evt) {
    }

}


