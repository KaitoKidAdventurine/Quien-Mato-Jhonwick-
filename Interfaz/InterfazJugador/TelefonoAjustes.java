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

public class TelefonoAjustes extends JPanel{

    private Dimension tamPant;
    private JButton fondoAnterior;
    private JButton fondoSiguiente;
    private JButton ponerFondo;
    private JButton salir;
    private JLabel fondo;
    private JLabel muestra;

    public TelefonoAjustes() {
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        initComponents();

    }
private void initComponents() {
    fondoAnterior = new JButton();
    salir = new JButton();
    fondo = new JLabel();
    muestra = new JLabel();
    fondoSiguiente = new JButton();
    ponerFondo = new JButton();



    setBackground(new Color(0, 0, 0, 0));
    setLayout(null);

    fondoAnterior.setBorderPainted(false);
    fondoAnterior.setContentAreaFilled(true);
    fondoAnterior.setFocusPainted(false);
    fondoAnterior.addMouseListener(new MouseAdapter() {
        public void mouseEntered(MouseEvent evt) {
            jButton1MouseEntered(evt);
        }
        public void mouseExited(MouseEvent evt) {
            jButton1MouseExited(evt);
        }
    });
    fondoAnterior.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });
    fondoAnterior.setBounds((int) (tamPant.width*0.025), (int) (tamPant.height*0.235),(int) (tamPant.width*0.035), (int) (tamPant.height*0.04));
    fondoAnterior.setBackground(Color.blue);
    add(fondoAnterior);


    fondoSiguiente.setBorderPainted(false);
    fondoSiguiente.setContentAreaFilled(true);
    fondoSiguiente.setFocusPainted(false);
    fondoSiguiente.addMouseListener(new MouseAdapter() {
        public void mouseEntered(MouseEvent evt) {
            jButton3MouseEntered(evt);
        }
        public void mouseExited(MouseEvent evt) {
            jButton3MouseExited(evt);
        }
    });
    fondoSiguiente.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            jButton3ActionPerformed(evt);
        }
    });
    fondoSiguiente.setBounds((int) (tamPant.width*0.205), (int) (tamPant.height*0.235),(int) (tamPant.width*0.035), (int) (tamPant.height*0.04));
    fondoSiguiente.setBackground(Color.blue);
    add(fondoSiguiente);

    ponerFondo.setBorderPainted(false);
    ponerFondo.setContentAreaFilled(true);
    ponerFondo.setFocusPainted(false);
    ponerFondo.addMouseListener(new MouseAdapter() {
        public void mouseEntered(MouseEvent evt) {
            jButton3MouseEntered(evt);
        }
        public void mouseExited(MouseEvent evt) {
            jButton3MouseExited(evt);
        }
    });
    ponerFondo.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            jButton3ActionPerformed(evt);
        }
    });
    ponerFondo.setBounds((int) (tamPant.width*0.055), (int) (tamPant.height*0.5),(int) (tamPant.width*0.155), (int) (tamPant.height*0.04));
    ponerFondo.setBackground(Color.blue);
    ponerFondo.setFont(new Font("Segoe UI", 0, (int)(tamPant.width*0.014)));
    ponerFondo.setForeground(Color.WHITE);
    ponerFondo.setHorizontalAlignment(SwingConstants.CENTER);

    ponerFondo.setText("Establecer fondo");
    add(ponerFondo);

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



    BufferedImage imagen2 = null;

    try {
        imagen2 = ImageIO.read(new File("DatosAuxiliares/Telefono/Fondo Mistico.png"));
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.13), (int) (tamPant.height*0.40), Image.SCALE_SMOOTH));
    muestra.setIcon(icono2);
    muestra.setBounds((int) (tamPant.width*0.0675),(int) (tamPant.height*0.07),  (int) (tamPant.width*0.13), (int) (tamPant.height*0.4));
add(muestra);

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
