package Interfaz.InterfazJugador;

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

public class TelefonoAjustes extends JPanel{

    private Dimension tamPant;
    private JButton fondoAnterior;
    private JButton fondoSiguiente;
    private JButton ponerFondo;
    private JButton salir;
    private JLabel fondo;
    private JLabel muestra;
    private JLabel nombreM;
    private Telefono telefonoL;
    private JLabel fondoTelefono;
    private JButton apagar;

    public TelefonoAjustes(Telefono telefonoLogica, JLabel fondoT) {
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        this.telefonoL = telefonoLogica;
        this.fondoTelefono = fondoT;

        initComponents();

    }
    private void initComponents()
    {
        fondoAnterior = new JButton();
        salir = new JButton();
        fondo = new JLabel();
        muestra = new JLabel();
        fondoSiguiente = new JButton();
        ponerFondo = new JButton();
        nombreM = new JLabel();
        apagar = new JButton();

        setBackground(new Color(0, 0, 0, 0));
        setLayout(null);

        fondoAnterior.setBorderPainted(false);
        fondoAnterior.setContentAreaFilled(false);
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
        fondoAnterior.setBounds((int) (tamPant.width*0.02), (int) (tamPant.height*0.355),(int) (tamPant.width*0.05), (int) (tamPant.height*0.06));

        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/Telefono/izquierda triangulo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.05), (int) (tamPant.height*0.06), Image.SCALE_SMOOTH));
        fondoAnterior.setIcon(icono);

        add(fondoAnterior);

        // Kevin: Esto lo coloque porque vi que no funcionaba el boton cuando lo tocabas
        // en una esta parte, como vez es el mismo codigo que tenias en la otra clase
        // Todavia no esta terminado... ando haciendolo lo que ando estudiandolo para hacerlo bien.
        /*
        apagar.setBorderPainted(false);
        apagar.setContentAreaFilled(false);
        apagar.setFocusPainted(false);
        apagar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                jButton4MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                jButton4MouseExited(evt);
            }
        });
        apagar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        apagar.setBounds((int) (tamPant.width*0.16), (int) (tamPant.height*0.66),(int) (tamPant.width*0.05), (int) (tamPant.height*0.07));


        add(apagar);

         */
        BufferedImage imagen7 = null;

        try {
            imagen7 = ImageIO.read(new File("DatosAuxiliares/Telefono/apagar.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono7 = new ImageIcon(imagen7.getScaledInstance((int) (tamPant.width*0.05), (int) (tamPant.height*0.07), Image.SCALE_SMOOTH));
        apagar.setIcon(icono7);

        fondoSiguiente.setBorderPainted(false);
        fondoSiguiente.setContentAreaFilled(false);
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
        fondoSiguiente.setBounds((int) (tamPant.width*0.19), (int) (tamPant.height*0.355),(int) (tamPant.width*0.05), (int) (tamPant.height*0.06));
        BufferedImage imagen1 = null;

        try {
            imagen1 = ImageIO.read(new File("DatosAuxiliares/Telefono/reproducir triangulo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono1 = new ImageIcon(imagen1.getScaledInstance((int) (tamPant.width*0.05), (int) (tamPant.height*0.06), Image.SCALE_SMOOTH));
        fondoSiguiente.setIcon(icono1);

        add(fondoSiguiente);

        ponerFondo.setBorderPainted(false);
        ponerFondo.setContentAreaFilled(false);
        ponerFondo.setFocusPainted(false);
        ponerFondo.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                jButton4MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                jButton4MouseExited(evt);
            }
        });
        ponerFondo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        ponerFondo.setBounds((int) (tamPant.width*0.055), (int) (tamPant.height*0.62),(int) (tamPant.width*0.155), (int) (tamPant.height*0.04));
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

        BufferedImage imagen3 = null;

        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/Telefono/atras.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.05), (int) (tamPant.height*0.07), Image.SCALE_SMOOTH));
        salir.setIcon(icono3);
        salir.setBounds((int) (tamPant.height*0.11), (int) (tamPant.height*0.66),(int) (tamPant.width*0.05), (int) (tamPant.height*0.07));

        salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });


        add(salir);

        BufferedImage imagen2 = null;

        try {
            imagen2 = ImageIO.read(new File("DatosAuxiliares/Telefono/Configuracion.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono2 = new ImageIcon(imagen2.getScaledInstance((int) (tamPant.width*0.255), (int) (tamPant.height*0.72), Image.SCALE_SMOOTH));
        fondo.setIcon(icono2);
        fondo.setBounds((int) (tamPant.width*0.005),0, (int) (tamPant.width*0.255), (int) (tamPant.height*0.72));



        BufferedImage imagen4 = null;

        try {
            imagen4 = ImageIO.read(new File(String.valueOf(telefonoL.getFondoDePantalla())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono4 = new ImageIcon(imagen4.getScaledInstance((int) (tamPant.width*0.13), (int) (tamPant.height*0.40), Image.SCALE_SMOOTH));
        muestra.setIcon(icono4);
        muestra.setBounds((int) (tamPant.width*0.0675),(int) (tamPant.height*0.19),  (int) (tamPant.width*0.13), (int) (tamPant.height*0.4));
        add(muestra);

        nombreM.setText(telefonoL.getNomFondoActual());
        nombreM.setBounds((int) (tamPant.width*0.02),(int) (tamPant.height*0.1), (int) (tamPant.width*0.228), (int) (tamPant.height*0.1));
        nombreM.setFont(new Font("Segoe UI", 0, (int)(tamPant.width*0.014)));
        nombreM.setForeground(Color.WHITE);
        nombreM.setHorizontalAlignment(SwingConstants.CENTER);
        add(nombreM);



        add(fondo);
    }

    private void jButton4ActionPerformed(ActionEvent evt) {
        telefonoL.colocarFondoActual();
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File(String.valueOf(telefonoL.getFondoDePantalla())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.255), (int) (tamPant.height*0.72), Image.SCALE_SMOOTH));
        fondoTelefono.setIcon(icono);
    }

    private void jButton4MouseExited(MouseEvent evt) {
        ponerFondo.setForeground(Color.WHITE);
    }

    private void jButton4MouseEntered(MouseEvent evt) {
        ponerFondo.setForeground(Color.red);

    }

    private void jButton3ActionPerformed(ActionEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File(String.valueOf(telefonoL.enviarSiguienteFondo())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.13), (int) (tamPant.height*0.40), Image.SCALE_SMOOTH));
        muestra.setIcon(icono);
        nombreM.setText(telefonoL.getNomFondoActual());
        revalidate();
        repaint();
    }

    private void jButton3MouseExited(MouseEvent evt) {
        BufferedImage imagen1 = null;

        try {
            imagen1 = ImageIO.read(new File("DatosAuxiliares/Telefono/reproducir triangulo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono1 = new ImageIcon(imagen1.getScaledInstance((int) (tamPant.width*0.05), (int) (tamPant.height*0.06), Image.SCALE_SMOOTH));
        fondoSiguiente.setIcon(icono1);
    }

    private void jButton3MouseEntered(MouseEvent evt) {
        BufferedImage imagen1 = null;

        try {
            imagen1 = ImageIO.read(new File("DatosAuxiliares/Telefono/reproducir  triangulo R.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono1 = new ImageIcon(imagen1.getScaledInstance((int) (tamPant.width*0.05), (int) (tamPant.height*0.06), Image.SCALE_SMOOTH));
        fondoSiguiente.setIcon(icono1);
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
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File(String.valueOf(telefonoL.enviarAnteriorFondo())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.13), (int) (tamPant.height*0.40), Image.SCALE_SMOOTH));
        muestra.setIcon(icono);
        revalidate();
        repaint();
    }

    void jButton1MouseExited(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/Telefono/izquierda triangulo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.05), (int) (tamPant.height*0.06), Image.SCALE_SMOOTH));
        fondoAnterior.setIcon(icono);
    }


    private void jButton1MouseEntered(MouseEvent evt) {
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/Telefono/izquierda trinagulo R.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.05), (int) (tamPant.height*0.06), Image.SCALE_SMOOTH));
        fondoAnterior.setIcon(icono);
    }

}
