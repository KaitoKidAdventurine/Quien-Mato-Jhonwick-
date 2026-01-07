/*
 * Clase para mostrar mensajes de éxito personalizables
 */
package DatosAuxiliaresLogica;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MensajeExito extends JDialog {

    private Dimension tamPant;

    // Constructor mejorado
    public MensajeExito(Window parent, boolean modal, String mensaje) {
        super(parent, modal ? DEFAULT_MODALITY_TYPE : ModalityType.MODELESS);
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        initComponents(mensaje);
        setLocationRelativeTo(parent);
        setVisible(true); // ¡IMPORTANTE! Esto hace que se muestre
    }

    // Constructor adicional con título
    public MensajeExito(Window parent, boolean modal, String mensaje, String titulo) {
        this(parent, modal, mensaje); // Reutiliza el constructor principal
    }

    private void initComponents(String mensajeTexto) {
        // Configuración básica del diálogo
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setModal(true);
        setLayout(new BorderLayout());

        // Panel principal con fondo
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        // Intentar cargar la imagen de fondo
        try {
            BufferedImage imagen = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/menu 1.jpg"));
            ImageIcon icono = new ImageIcon(imagen.getScaledInstance(
                    (int)(tamPant.width * 0.4),
                    (int)(tamPant.height * 0.6),
                    Image.SCALE_SMOOTH
            ));

            JLabel fondo = new JLabel(icono);
            fondo.setLayout(new BorderLayout());

            // Panel para el contenido
            JPanel contentPanel = new JPanel();
            contentPanel.setOpaque(false);
            contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
            contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

            // Mensaje
            JLabel mensajeLabel = new JLabel("<html><center>" + mensajeTexto.replace("\n", "<br>") + "</center></html>");
            mensajeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
            mensajeLabel.setForeground(Color.WHITE);
            mensajeLabel.setAlignmentX(CENTER_ALIGNMENT);

            // Espacio
            contentPanel.add(Box.createVerticalStrut(20));
            contentPanel.add(mensajeLabel);
            contentPanel.add(Box.createVerticalStrut(40));

            // Botón Entendido
            JButton btnEntendido = new JButton("Entendido");
            btnEntendido.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            btnEntendido.setForeground(Color.WHITE);
            btnEntendido.setContentAreaFilled(false);
            btnEntendido.setBorderPainted(false);
            btnEntendido.setFocusPainted(false);
            btnEntendido.setAlignmentX(CENTER_ALIGNMENT);

            // Estilo del botón
            btnEntendido.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    btnEntendido.setForeground(Color.RED);
                    btnEntendido.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btnEntendido.setForeground(Color.WHITE);
                    btnEntendido.setCursor(Cursor.getDefaultCursor());
                }
            });

            // Acción del botón
            btnEntendido.addActionListener(e -> {
                EfectosEspeciales.getInstancia().efectoDeBoton();
                dispose();
            });

            contentPanel.add(btnEntendido);

            fondo.add(contentPanel, BorderLayout.CENTER);
            mainPanel.add(fondo, BorderLayout.CENTER);

        } catch (IOException e) {

            mainPanel.setBackground(new Color(0, 0, 0, 220));

            JLabel mensajeLabel = new JLabel("<html><center>" + mensajeTexto.replace("\n", "<br>") + "</center></html>");
            mensajeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
            mensajeLabel.setForeground(Color.WHITE);
            mensajeLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JButton btnEntendido = new JButton("Entendido");
            btnEntendido.addActionListener(ef -> {
                EfectosEspeciales.getInstancia().efectoDeBoton();
                dispose();
            });

            mainPanel.setLayout(new BorderLayout());
            mainPanel.add(mensajeLabel, BorderLayout.CENTER);
            mainPanel.add(btnEntendido, BorderLayout.SOUTH);
        }

        // Configurar tamaño
        setSize((int)(tamPant.width * 0.4), (int)(tamPant.height * 0.6));

        // Añadir panel principal
        add(mainPanel);

        // Hacer que se pueda cerrar con ESC
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cerrar"
        );
        getRootPane().getActionMap().put("cerrar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EfectosEspeciales fe = EfectosEspeciales.getInstancia();
                fe.efectoDeBoton();
                dispose();
            }
        });
    }
}