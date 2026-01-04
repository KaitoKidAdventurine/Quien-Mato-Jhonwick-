package Interfaz.InterfazJugador;

import DatosAuxiliaresLogica.EfectosEspeciales;

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

public class InterfazMenuMinijuego extends JButton {
    private Dimension tamPant;

    public InterfazMenuMinijuego() {
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        initComponents();
    }

    private void initComponents() {

        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                MenuMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                MenuMouseExited(evt);
            }
        });
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                EfectosEspeciales e = EfectosEspeciales.getInstancia();
                e.efectoDeBoton();
                MenuActionPerformed(evt);
            }
        });
        setBounds((int) (tamPant.width*0.65), (int) (tamPant.height*0.05), (int) (tamPant.width*0.08), (int) (tamPant.height*0.1));

        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Menu.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.08), (int) (tamPant.height*0.1), Image.SCALE_SMOOTH));
        setIcon(icono);
    }
    private void MenuActionPerformed(ActionEvent evt) {

        MenuInterno menuInterno = new MenuInterno(new JFrame(), true);
        menuInterno.setBounds(0, 0,tamPant.width, tamPant.height);
        menuInterno.setVisible(true);


    }

    private void MenuMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Menu.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.08), (int) (tamPant.height*0.1), Image.SCALE_SMOOTH));
        setIcon(icono);
    }


    private void MenuMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Menu BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.08), (int) (tamPant.height*0.1), Image.SCALE_SMOOTH));
        setIcon(icono);
    }


}
