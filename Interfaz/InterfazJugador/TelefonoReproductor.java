package Interfaz.InterfazJugador;

import DatosAuxiliaresLogica.EfectosEspeciales;
import Logica.Reproductor;
import Logica.Telefono;

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
    private JButton apagar;
    public TelefonoReproductor(Telefono telefonoLogica){
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
        apagar = new JButton();

        setBackground(new Color(0, 0, 0, 0));
        setLayout(null);

        cancionAnterior.setBorderPainted(false);
        cancionAnterior.setContentAreaFilled(false);
        cancionAnterior.setFocusPainted(false);
        cancionAnterior.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                botonAnteriorMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                botonAnteriorMouseExited(evt);
            }
        });
        cancionAnterior.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonAnteriorActionPerformed(evt);
            }
        });
        cancionAnterior.setBounds((int) (tamPant.width*0.045), (int) (tamPant.height*0.53),(int) (tamPant.width*0.045), (int) (tamPant.height*0.05));

        BufferedImage imagen1 = null;

        try {
            imagen1 = ImageIO.read(new File("DatosAuxiliares/Telefono/anterior.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono1 = new ImageIcon(imagen1.getScaledInstance((int) (tamPant.width*0.045), (int) (tamPant.height*0.05), Image.SCALE_SMOOTH));
        cancionAnterior.setIcon(icono1);

        add(cancionAnterior);


        cancionSiguiente.setBorderPainted(false);
        cancionSiguiente.setContentAreaFilled(false);
        cancionSiguiente.setFocusPainted(false);
        cancionSiguiente.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                botonSiguienteMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                botonSiguienteMouseExited(evt);
            }
        });
        cancionSiguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonSiguienteActionPerformed(evt);
            }
        });
        cancionSiguiente.setBounds((int) (tamPant.width*0.175), (int) (tamPant.height*0.53),(int) (tamPant.width*0.045), (int) (tamPant.height*0.05));

        BufferedImage imagen2 = null;

        try {
            imagen2 = ImageIO.read(new File("DatosAuxiliares/Telefono/siguiente.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.045), (int) (tamPant.height*0.05), Image.SCALE_SMOOTH));
        cancionSiguiente.setIcon(icono2);

        add(cancionSiguiente);

        pausa.setBorderPainted(false);
        pausa.setContentAreaFilled(false);
        pausa.setFocusPainted(false);
        pausa.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                botonPausaMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                botonPausaMouseExited(evt);
            }
        });
        pausa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonPausaActionPerformed(evt);
            }
        });
        pausa.setBounds((int) (tamPant.width*0.11), (int) (tamPant.height*0.53),(int) (tamPant.width*0.045), (int) (tamPant.height*0.05));

        BufferedImage imagen3 = null;

        try {
            if(Reproductor.getInstancia().isEnReproduccion()) {
                imagen3 = ImageIO.read(new File("DatosAuxiliares/Telefono/pausa.png"));
            }else{
                imagen3 = ImageIO.read(new File("DatosAuxiliares/Telefono/reproducir triangulo.png"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.045), (int) (tamPant.height*0.05), Image.SCALE_SMOOTH));
        pausa.setIcon(icono3);

        add(pausa);

        salir.setBorderPainted(false);
        salir.setContentAreaFilled(false);
        salir.setFocusPainted(false);
        salir.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                botonSalirMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                botonSalirMouseExited(evt);
            }
        });

        BufferedImage imagen6 = null;

        try {
            imagen6 = ImageIO.read(new File("DatosAuxiliares/Telefono/atras.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono6 = new ImageIcon(imagen6.getScaledInstance((int) (tamPant.width*0.05), (int) (tamPant.height*0.07), Image.SCALE_SMOOTH));
        salir.setIcon(icono6);
        salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });
        salir.setBounds((int) (tamPant.height*0.11), (int) (tamPant.height*0.66),(int) (tamPant.width*0.05), (int) (tamPant.height*0.07));

        add(salir);

        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/Telefono/reproductor.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.255), (int) (tamPant.height*0.72), Image.SCALE_SMOOTH));
        fondo.setIcon(icono);
        fondo.setBounds((int) (tamPant.width*0.005),0, (int) (tamPant.width*0.255), (int) (tamPant.height*0.72));

        nombreCancion.setText(Reproductor.getInstancia().nombreDeLaCancionActual());
        nombreCancion.setBounds((int) (tamPant.width*0.02),(int) (tamPant.height*0.42), (int) (tamPant.width*0.228), (int) (tamPant.height*0.1));
        nombreCancion.setFont(new java.awt.Font("Segoe UI", 0, (int)(tamPant.width*0.014)));
        nombreCancion.setForeground(Color.WHITE);
        nombreCancion.setHorizontalAlignment(SwingConstants.CENTER);
        add(nombreCancion);

        apagar.setBorderPainted(false);
        apagar.setContentAreaFilled(false);
        apagar.setFocusPainted(false);
        apagar.setBounds((int) (tamPant.width*0.16), (int) (tamPant.height*0.66),(int) (tamPant.width*0.05), (int) (tamPant.height*0.07));

        BufferedImage imagen7 = null;

        try {
            imagen7 = ImageIO.read(new File("DatosAuxiliares/Telefono/apagar.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono7 = new ImageIcon(imagen7.getScaledInstance((int) (tamPant.width*0.05), (int) (tamPant.height*0.07), Image.SCALE_SMOOTH));
        apagar.setIcon(icono7);
        add(apagar);

        add(fondo);

    }

    private void botonPausaActionPerformed(ActionEvent evt) {
        boolean activo = Reproductor.getInstancia().isEnReproduccion();

        BufferedImage imagen3 = null;
        try {
            if(activo==true) {
                imagen3 = ImageIO.read(new File("DatosAuxiliares/Telefono/reproducir  triangulo R.png"));
                Reproductor.getInstancia().desactivarMusica();
            }else{
                imagen3 = ImageIO.read(new File("DatosAuxiliares/Telefono/pausa r.png"));
                Reproductor.getInstancia().activarMusica();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.045), (int) (tamPant.height*0.05), Image.SCALE_SMOOTH));
        pausa.setIcon(icono3);
        revalidate();
        repaint();

    }

    private void botonPausaMouseEntered(MouseEvent evt) {
        BufferedImage imagen3 = null;

        try {
            if(Reproductor.getInstancia().isEnReproduccion()) {
                imagen3 = ImageIO.read(new File("DatosAuxiliares/Telefono/pausa r.png"));
            }else{
                imagen3 = ImageIO.read(new File("DatosAuxiliares/Telefono/reproducir  triangulo R.png"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.045), (int) (tamPant.height*0.05), Image.SCALE_SMOOTH));
        pausa.setIcon(icono3);

    }

    private void botonPausaMouseExited(MouseEvent evt) {
        BufferedImage imagen3 = null;

        try {
            if(Reproductor.getInstancia().isEnReproduccion()) {
                imagen3 = ImageIO.read(new File("DatosAuxiliares/Telefono/pausa.png"));
            }else{
                imagen3 = ImageIO.read(new File("DatosAuxiliares/Telefono/reproducir triangulo.png"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.045), (int) (tamPant.height*0.05), Image.SCALE_SMOOTH));
        pausa.setIcon(icono3);
    }



    private void botonSalirActionPerformed(ActionEvent evt) {
        EfectosEspeciales e = EfectosEspeciales.getInstancia();
        e.efectoDeBotonesTelefono();
        getParent().getComponent(1).setVisible(true);
        getParent().getComponent(4).setVisible(false);
    }

    private void botonSalirMouseEntered(MouseEvent evt) {
        BufferedImage imagen6 = null;

        try {
            imagen6 = ImageIO.read(new File("DatosAuxiliares/Telefono/atras r.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono6 = new ImageIcon(imagen6.getScaledInstance((int) (tamPant.width*0.05), (int) (tamPant.height*0.07), Image.SCALE_SMOOTH));
        salir.setIcon(icono6);
    }

    private void botonSalirMouseExited(MouseEvent evt) {
        BufferedImage imagen6 = null;

        try {
            imagen6 = ImageIO.read(new File("DatosAuxiliares/Telefono/atras.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono6 = new ImageIcon(imagen6.getScaledInstance((int) (tamPant.width*0.05), (int) (tamPant.height*0.07), Image.SCALE_SMOOTH));
        salir.setIcon(icono6);
    }

    private void botonSiguienteActionPerformed(ActionEvent evt) {
        Reproductor.getInstancia().cambiarMusicaSiguiente();
        nombreCancion.setText(Reproductor.getInstancia().nombreDeLaCancionActual());
        revalidate();
        repaint();
    }


    private void botonSiguienteMouseEntered(MouseEvent evt) {
        BufferedImage imagen2 = null;

        try {
            imagen2 = ImageIO.read(new File("DatosAuxiliares/Telefono/siguiente r.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.045), (int) (tamPant.height*0.05), Image.SCALE_SMOOTH));
        cancionSiguiente.setIcon(icono2);
    }

    private void botonSiguienteMouseExited(MouseEvent evt) {
        BufferedImage imagen2 = null;

        try {
            imagen2 = ImageIO.read(new File("DatosAuxiliares/Telefono/siguiente.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.045), (int) (tamPant.height*0.05), Image.SCALE_SMOOTH));
        cancionSiguiente.setIcon(icono2);

    }


    private void botonAnteriorActionPerformed(ActionEvent evt) {
        Reproductor.getInstancia().cambiarMusicaAnterioPorIndice();
        nombreCancion.setText(Reproductor.getInstancia().nombreDeLaCancionActual());
        revalidate();
        repaint();
    }

    private void botonAnteriorMouseEntered(MouseEvent evt) {
        BufferedImage imagen1 = null;

        try {
            imagen1 = ImageIO.read(new File("DatosAuxiliares/Telefono/anterior r.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono1 = new ImageIcon(imagen1.getScaledInstance((int) (tamPant.width*0.045), (int) (tamPant.height*0.05), Image.SCALE_SMOOTH));
        cancionAnterior.setIcon(icono1);
    }

    void botonAnteriorMouseExited(MouseEvent evt) {
        BufferedImage imagen1 = null;

        try {
            imagen1 = ImageIO.read(new File("DatosAuxiliares/Telefono/anterior.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono1 = new ImageIcon(imagen1.getScaledInstance((int) (tamPant.width*0.045), (int) (tamPant.height*0.05), Image.SCALE_SMOOTH));
        cancionAnterior.setIcon(icono1);
    }




}
