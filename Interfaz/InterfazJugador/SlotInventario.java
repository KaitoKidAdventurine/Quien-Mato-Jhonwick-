package Interfaz.InterfazJugador;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SlotInventario extends JPanel {
    private Dimension tamPant;
    private JLabel imagenObj;
    private JLabel nombre;
    private JLabel descripcion;

    public SlotInventario(ImageIcon imagenObjeto, String nombreObj, String descripcionObj){
        tamPant = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());

        setBackground(new Color(0, 0, 0, 80));
        setLayout(null);
        setBorder(null);
        setBounds(0, 0,(int) (tamPant.width*0.304), (int) (tamPant.height*0.174));

        imagenObj = new JLabel();
        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(new File(String.valueOf(imagenObjeto)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.1),  (int) (tamPant.height*0.1), Image.SCALE_SMOOTH));
        imagenObj.setIcon(icono);
        imagenObj.setBounds((int) (tamPant.width*0.01), (int) (tamPant.height*0.032), (int) (tamPant.width*0.1), (int) (tamPant.height*0.1));
        add(imagenObj);

        nombre = new JLabel(nombreObj);
        nombre.setBounds((int) (tamPant.width*0.12), (int) (tamPant.height*0.01), (int) (tamPant.width*0.16), (int) (tamPant.height*0.04));
        nombre.setHorizontalAlignment(SwingConstants.CENTER);
        nombre.setFont(new Font("Segoe UI", 0, (int)(tamPant.height*0.02)));
        nombre.setForeground(new Color(255, 255, 255, 245));
        add(nombre);

        descripcion = new JLabel();

        descripcion.setOpaque(true);
        descripcion.setBackground(new Color(0, 0, 0, 0));
        descripcion.setFocusable(false);
        descripcion.setText("<html>" +descripcionObj + "</html>");
        descripcion.setBounds((int) (tamPant.width*0.12), (int) (tamPant.height*0.06), (int) (tamPant.width*0.16), (int) (tamPant.height*0.09));
        descripcion.setFont(new Font("Segoe UI", 0, (int)(tamPant.height*0.016)));
        descripcion.setHorizontalAlignment(SwingConstants.LEFT);
        descripcion.setVerticalAlignment(SwingConstants.TOP);
        descripcion.setForeground(new Color (240, 240, 240));
        add(descripcion);

    }





}

