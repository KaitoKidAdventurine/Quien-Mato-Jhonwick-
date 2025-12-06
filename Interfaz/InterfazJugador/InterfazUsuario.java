package Interfaz.InterfazJugador;

import Logica.*;

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

public class InterfazUsuario  extends javax.swing.JPanel {
    private Dimension tamPant;
    private javax.swing.JButton menu;
    private javax.swing.JButton telefono;
    private javax.swing.JButton diario;
    private javax.swing.JButton mochila;
    /**
     * Creates new form CuadroTexto
     */
    public InterfazUsuario() {
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        initComponents();
    }


    private void initComponents() {
        setOpaque(false);


        menu = new JButton();
        telefono= new JButton();
        diario = new JButton();
        mochila = new JButton();

        mochila.setBorderPainted(false);
        mochila.setContentAreaFilled(false);
        mochila.setFocusPainted(false);
        mochila.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                MochilaMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                MochilaMouseExited(evt);
            }
        });
        mochila.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                MochilaActionPerformed(evt);
            }
        });
        mochila.setBounds((int) (tamPant.width*0.23), (int) (tamPant.height*0.03), (int) (tamPant.width*0.1), (int) (tamPant.height*0.09));

        BufferedImage imagen4 = null;
        try {
            imagen4 = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Maleta.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono4 = new ImageIcon(imagen4.getScaledInstance((int) (tamPant.width*0.07), (int) (tamPant.height*0.15), Image.SCALE_SMOOTH));
        mochila.setIcon(icono4);


        menu.setBorderPainted(false);
        menu.setContentAreaFilled(false);
        menu.setFocusPainted(false);
        menu.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                MenuMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                MenuMouseExited(evt);
            }
        });
        menu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                MenuActionPerformed(evt);
            }
        });
        menu.setBounds((int) (tamPant.width*1.23), (int) (tamPant.height*0.03), (int) (tamPant.width*0.05), (int) (tamPant.height*0.05));

        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Menu.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.08), (int) (tamPant.height*0.1), Image.SCALE_SMOOTH));
        menu.setIcon(icono);



        telefono.setBorderPainted(false);
        telefono.setContentAreaFilled(false);
        telefono.setFocusPainted(false);
        telefono.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                TelefonoMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                TelefonoMouseExited(evt);
            }
        });
        telefono.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                TelefonoActionPerformed(evt);
            }
        });
        telefono.setBounds((int) (tamPant.width*0.43), (int) (tamPant.height*0.03), (int) (tamPant.width*0.05), (int) (tamPant.height*0.05));

        BufferedImage imagen2 = null;
        try {
            imagen2 = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Telefono.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.06), (int) (tamPant.height*0.1), Image.SCALE_SMOOTH));
        telefono.setIcon(icono2);


        diario.setBorderPainted(false);
        diario.setContentAreaFilled(false);
        diario.setFocusPainted(false);
        diario.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                DiarioMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                DiarioMouseExited(evt);
            }
        });
        diario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DiarioActionPerformed(evt);
            }
        });
        diario.setBounds((int) (tamPant.width*0.23), (int) (tamPant.height*0.03), (int) (tamPant.width*0.05), (int) (tamPant.height*0.05));

        BufferedImage imagen3 = null;
        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Diario.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.08), (int) (tamPant.height*0.1), Image.SCALE_SMOOTH));
        diario.setIcon(icono3);


crearObjetosMochila();
        add(diario);
        add(telefono);
        add(mochila);
        add(menu);

    }

    private void MochilaActionPerformed(ActionEvent evt) {

        Maletin maletin = new Maletin(new JFrame(), true);
        maletin.setBounds(0, 0,tamPant.width, tamPant.height);
        maletin.setVisible(true);

    }

    private void MochilaMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Maleta.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.07), (int) (tamPant.height*0.15), Image.SCALE_SMOOTH));
        mochila.setIcon(icono);
    }


    private void MochilaMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Maleta BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.07), (int) (tamPant.height*0.15), Image.SCALE_SMOOTH));
        mochila.setIcon(icono);
    }
    private void DiarioActionPerformed(ActionEvent evt) {
        DiarioInterfaz diarioInterfaz = new DiarioInterfaz(new JFrame(), true);
        diarioInterfaz.setBounds(0, 0,tamPant.width, tamPant.height);
        diarioInterfaz.setVisible(true);
    }

    private void DiarioMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Diario.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.08), (int) (tamPant.height*0.1), Image.SCALE_SMOOTH));
        diario.setIcon(icono);
    }


    private void DiarioMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Diario BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.08), (int) (tamPant.height*0.1), Image.SCALE_SMOOTH));
        diario.setIcon(icono);
    }
    private void TelefonoActionPerformed(ActionEvent evt) {
        TelefonoInterfaz telefono = new TelefonoInterfaz(new JFrame(), true);
        telefono.setBounds(0, 0,tamPant.width, tamPant.height);
        telefono.setVisible(true);
    }

    private void TelefonoMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Telefono.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.06), (int) (tamPant.height*0.1), Image.SCALE_SMOOTH));
        telefono.setIcon(icono);
    }


    private void TelefonoMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Telefono BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.06), (int) (tamPant.height*0.1), Image.SCALE_SMOOTH));
        telefono.setIcon(icono);
    }
    private void MenuActionPerformed(ActionEvent evt) {

        MenuInterno dialog = new MenuInterno(new javax.swing.JFrame(), true);
        dialog.setBounds(0, 0,tamPant.width, tamPant.height);
        dialog.setVisible(true);

    }

    private void MenuMouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Menu.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.08), (int) (tamPant.height*0.1), Image.SCALE_SMOOTH));
        menu.setIcon(icono);
    }


    private void MenuMouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Menu BR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.08), (int) (tamPant.height*0.1), Image.SCALE_SMOOTH));
        menu.setIcon(icono);
    }
    public void crearObjetosMochila(){
        ObjetoEscenario ob8 = new ObjetoEscenario("Telefono", true,new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/telefono de la victima.png"), 0.03F, 0.1F, 0.1F, 0.1F, false, "1");
        ObjetoEscenario ob9 = new ObjetoEscenario("Herida Grande", true, new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/Herida grande.png"), 0.55F, 0.9F, 0.1F, 0.1F, false, "2");
        ObjetoEscenario ob10 = new ObjetoEscenario("Laptop", true, new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/Laptop.png"), 0.27F, 0.33F, 0.18F, 0.1F, false, "nada");
        ObjetoEscenario ob11 = new ObjetoEscenario("Cigarros", true, new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/cigarros.png"), 0.62F, 0.2F, 0.19F, 0.1F, false, "nada");
        ObjetoEscenario ob12 = new ObjetoEscenario("Herida pequeña", true, new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/Herida pequeña.png"), 0.12F, 0.77F, 0.1F, 0.1F, true, "nada");
        ObjetoEscenario ob4 = new ObjetoEscenario("Mancha de sangre", true,new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/Mancha de sangre.png"), 0.7F, 0.7F, 0.1F, 0.1F, false, "nada");
        ObjetoEscenario ob1 = new ObjetoEscenario("Anillo", true,new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/anillo.png"), 0.3F, 0.5F, 0.1F, 0.1F, true, "nada");
        ObjetoEscenario ob2 = new ObjetoEscenario("Carnet", true, new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/carnet.png"), 0.4F, 0.1F, 0.1F, 0.1F, false, "nada");
        ObjetoEscenario ob3 = new ObjetoEscenario("Memoria USB", true,new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/memoria.png"), 0.1F, 0.2F, 0.1F, 0.1F, false, "nada");
        ObjetoEscenario ob5 = new ObjetoEscenario("Mancha de sangre", true,new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/Mancha de sangre.png"), 0.7F, 0.7F, 0.1F, 0.1F, false, "nada");
        ObjetoEscenario ob6 = new ObjetoEscenario("Anillo", true,new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/anillo.png"), 0.3F, 0.5F, 0.1F, 0.1F, true, "nada");
        ObjetoEscenario ob7 = new ObjetoEscenario("Carnet", true, new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/carnet.png"), 0.4F, 0.1F, 0.1F, 0.1F, false, "nada");
        ObjetoEscenario ob14 = new ObjetoEscenario("Memoria USB", true,new ImageIcon("DatosAuxiliares/Minijuego/EscenaCrimen/memoria.png"), 0.1F, 0.2F, 0.1F, 0.1F, false, "nada");

        Jugador.getInstancia().agregarAlMaletin(ob8);
        Jugador.getInstancia().agregarAlMaletin(ob9);
        Jugador.getInstancia().agregarAlMaletin(ob10);
        Jugador.getInstancia().agregarAlMaletin(ob11);
        Jugador.getInstancia().agregarAlMaletin(ob12);


    }




}
