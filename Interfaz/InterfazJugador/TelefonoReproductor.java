package Interfaz.InterfazJugador;

import Logica.Reproductor;

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

public class TelefonoReproductor extends JPanel {
    private Dimension tamPant;
    private JButton cancionAnterior;
    private JButton cancionSiguiente;
    private JButton pausa;
    private JButton salir;
    private JLabel fondo;
    private JLabel nombreCancion;
    public TelefonoReproductor(){
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        initComponents();
    }

    private void initComponents() {
        cancionAnterior = new JButton();
        salir = new JButton();
        fondo = new JLabel();
        nombreCancion = new JLabel();
        cancionSiguiente = new JButton();
        pausa = new JButton();


        setBackground(new Color(0, 0, 0, 0));
        setLayout(null);

        cancionAnterior.setBorderPainted(false);
        cancionAnterior.setContentAreaFilled(true);
        cancionAnterior.setFocusPainted(false);
        cancionAnterior.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        cancionAnterior.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        cancionAnterior.setBounds((int) (tamPant.width*0.05), (int) (tamPant.height*0.53),(int) (tamPant.width*0.035), (int) (tamPant.height*0.04));
        cancionAnterior.setBackground(Color.blue);
        add(cancionAnterior);


        cancionSiguiente.setBorderPainted(false);
        cancionSiguiente.setContentAreaFilled(true);
        cancionSiguiente.setFocusPainted(false);
        cancionSiguiente.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                jButton3MouseExited(evt);
            }
        });
        cancionSiguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        cancionSiguiente.setBounds((int) (tamPant.width*0.18), (int) (tamPant.height*0.53),(int) (tamPant.width*0.035), (int) (tamPant.height*0.04));
        cancionSiguiente.setBackground(Color.blue);
        add(cancionSiguiente);

        pausa.setBorderPainted(false);
        pausa.setContentAreaFilled(true);
        pausa.setFocusPainted(false);
        pausa.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                jButton3MouseExited(evt);
            }
        });
        pausa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        pausa.setBounds((int) (tamPant.width*0.115), (int) (tamPant.height*0.53),(int) (tamPant.width*0.035), (int) (tamPant.height*0.04));
        pausa.setBackground(Color.blue);
        add(pausa);

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

        salir.setText("X");
        salir.setForeground(Color.black);
        salir.setFont(new Font("Segoe UI", 0, 24));
        salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        salir.setBounds((int) (tamPant.height*0.13), (int) (tamPant.height*0.65),(int) (tamPant.width*0.05), (int) (tamPant.height*0.1));

        add(salir);

        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/Telefono/Fondo Simple.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.255), (int) (tamPant.height*0.72), Image.SCALE_SMOOTH));
        fondo.setIcon(icono);
        fondo.setBounds((int) (tamPant.width*0.005),0, (int) (tamPant.width*0.255), (int) (tamPant.height*0.72));

        nombreCancion.setText("Nombre de la Cancion verdadera");
        nombreCancion.setBounds((int) (tamPant.width*0.02),(int) (tamPant.height*0.27), (int) (tamPant.width*0.228), (int) (tamPant.height*0.1));
        nombreCancion.setFont(new java.awt.Font("Segoe UI", 0, (int)(tamPant.width*0.014)));
        nombreCancion.setForeground(Color.WHITE);
        nombreCancion.setHorizontalAlignment(SwingConstants.CENTER);
        add(nombreCancion);
        add(fondo);

    }

    private void jButton3ActionPerformed(ActionEvent evt) {
        Reproductor.getInstancia().cambiarMusicaSiguiente();
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
    }

    private void jButton2MouseEntered(MouseEvent evt) {
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "Cancion anterior");
    }

    void jButton1MouseExited(MouseEvent evt) {
    }


    private void jButton1MouseEntered(MouseEvent evt) {
    }

}
