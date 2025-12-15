package Interfaz.Menu;

import DatosAuxiliaresLogica.EfectosEspeciales;
import Logica.Reproductor;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OpcionesJugador extends JDialog {
    private Reproductor reproductor;
    private JLabel volumen;
    private JSlider jSlider;
    private JCheckBox cajaMusica;
    private BufferedImage imagenFondo;
    private Dimension tamPant;

    public OpcionesJugador(Dialog parent) {
        super(parent, false);
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        reproductor = Reproductor.getInstancia();
        cargarImagenFondo();
        initComponents();
        configurarSlider();

        int x = (int) (tamPant.width * 0.3);
        int y = (int) (tamPant.height * 0.2);
        setLocation(x, y);
    }

    public OpcionesJugador(Frame parent) {
        super(parent, false);
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        reproductor = Reproductor.getInstancia();
        cargarImagenFondo();
        initComponents();
        configurarSlider();
        setLocationRelativeTo(null);
    }

    private void cargarImagenFondo() {
        try {
            imagenFondo = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/menu 1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            imagenFondo = null;
        }
    }

    private void configurarSlider() {
        jSlider.setMinimum(0);
        jSlider.setMaximum(100);
        jSlider.setValue((int)(reproductor.getVolumen() * 100));
        jSlider.setPaintTicks(true);
        jSlider.setPaintTrack(true);
        jSlider.setMajorTickSpacing(25);
        jSlider.setMinorTickSpacing(5);

        jSlider.setUI(new BasicSliderUI(jSlider) {
            @Override
            public void paintThumb(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                g2d.fillOval(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height);
                g2d.setColor(Color.GRAY);
                g2d.drawOval(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height);
            }

            @Override
            public void paintTrack(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int trackHeight = 6;
                int trackWidth = trackRect.width;
                int trackY = trackRect.y + (trackRect.height - trackHeight) / 2;

                g2d.setColor(new Color(100, 100, 100));
                g2d.fillRect(trackRect.x, trackY, trackWidth, trackHeight);

                int fillWidth = (int) (trackWidth * ((float) slider.getValue() / slider.getMaximum()));
                g2d.setColor(new Color(220, 220, 220));
                g2d.fillRect(trackRect.x, trackY, fillWidth, trackHeight);

                g2d.setColor(Color.DARK_GRAY);
                g2d.drawRect(trackRect.x, trackY, trackWidth - 1, trackHeight - 1);
            }

            @Override
            public void paintTicks(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);

                if (slider.getPaintTicks() && slider.getMajorTickSpacing() > 0) {
                    int tickLength = 8;
                    int trackY = trackRect.y + (trackRect.height) / 2;

                    for (int value = slider.getMinimum(); value <= slider.getMaximum(); value += slider.getMajorTickSpacing()) {
                        int xPos = xPositionForValue(value);
                        g2d.drawLine(xPos, trackY + 4, xPos, trackY + 4 + tickLength);
                    }
                }
            }

            @Override
            protected Dimension getThumbSize() {
                return new Dimension(20, 20);
            }
        });

        jSlider.addChangeListener(e -> {
            if (!jSlider.getValueIsAdjusting()) {
                int porcentaje = jSlider.getValue();
                actualizarVolumen(porcentaje);
            }
        });
    }

    private void actualizarVolumen(int porcentaje) {
        float volumenNormalizado = porcentaje / 100.0f;
        reproductor.setVolumen(volumenNormalizado);
        volumen.setText("Volumen: " + porcentaje + "%");
    }

    private void controlarMusica(boolean activar) {
        if (activar) {
            reproductor.activarMusica();
            jSlider.setEnabled(true);
        } else {
            reproductor.desactivarMusica();
            jSlider.setEnabled(false);
        }
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 75));

        int ancho = (int) (tamPant.width * 0.4);
        int alto = (int) (tamPant.height * 0.65);
        setSize(ancho, alto);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(Color.BLACK);

        JLabel fondo = new JLabel();
        if (imagenFondo != null) {
            ImageIcon icono = new ImageIcon(imagenFondo.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH));
            fondo.setIcon(icono);
        }
        fondo.setLayout(new BorderLayout());

        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(null);
        panelContenido.setOpaque(false);

        fondo.add(panelContenido, BorderLayout.CENTER);
        panelPrincipal.add(fondo, BorderLayout.CENTER);
        setContentPane(panelPrincipal);
        panelContenido.setBounds(0, 0, ancho, alto);

        JLabel titulo = new JLabel("Opciones");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBounds(0, 60, ancho, 40);
        panelContenido.add(titulo);

        JLabel musica = new JLabel("Música:");
        musica.setFont(new Font("Segoe UI", Font.BOLD, 18));
        musica.setForeground(Color.WHITE);
        musica.setBounds(ancho/2 - 150, 170, 120, 30);
        panelContenido.add(musica);

        cajaMusica = new JCheckBox();
        cajaMusica.setSelected(true);
        cajaMusica.setContentAreaFilled(false);
        cajaMusica.setFocusPainted(false);
        cajaMusica.setBackground(new Color(0, 0, 0, 0));
        cajaMusica.addActionListener(e -> controlarMusica(cajaMusica.isSelected()));
        cajaMusica.setBounds(ancho/2 + 20, 170, 25, 25);
        panelContenido.add(cajaMusica);

        volumen = new JLabel("Volumen: " + (int)(reproductor.getVolumen() * 100) + "%");
        volumen.setFont(new Font("Segoe UI", Font.BOLD, 18));
        volumen.setForeground(Color.WHITE);
        volumen.setBounds(ancho/2 - 150, 230, 200, 30);
        panelContenido.add(volumen);

        jSlider = new JSlider();
        jSlider.setBounds(ancho/2 + 20, 225, 200, 40);
        jSlider.setBackground(new Color(0, 0, 0, 0));
        jSlider.setForeground(Color.WHITE);
        jSlider.setOpaque(false);
        panelContenido.add(jSlider);

        JButton botonAtras = new JButton("VOLVER");
        botonAtras.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botonAtras.setForeground(Color.WHITE);
        botonAtras.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(150, 150, 150), 2),
                BorderFactory.createEmptyBorder(8, 25, 8, 25)
        ));
        botonAtras.setContentAreaFilled(false);
        botonAtras.setFocusPainted(false);
        botonAtras.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                botonAtras.setForeground(Color.RED);
                botonAtras.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.RED, 2),
                        BorderFactory.createEmptyBorder(8, 25, 8, 25)
                ));
            }
            public void mouseExited(MouseEvent evt) {
                botonAtras.setForeground(Color.WHITE);
                botonAtras.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(150, 150, 150), 2),
                        BorderFactory.createEmptyBorder(8, 25, 8, 25)
                ));
            }
            public void mousePressed(MouseEvent evt) {
                botonAtras.setForeground(new Color(180, 0, 0));
            }
            public void mouseReleased(MouseEvent evt) {
                botonAtras.setForeground(Color.RED);
            }
        });
        botonAtras.addActionListener(e -> {
            EfectosEspeciales.getInstancia().efectoDeBoton();
            dispose();
        });
        botonAtras.setBounds(ancho/2 - 75, alto - 95, 150, 45);
        panelContenido.add(botonAtras);
    }
}