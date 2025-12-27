package Interfaz.InterfazJugador;

import DatosAuxiliaresLogica.UnionInterfaces;

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

public class ConfirmarSalida extends JDialog {
    private Dimension tamPant;
    private JButton yes;
    private JButton no;
    private JLabel texto;
    private boolean paraSalir;
    private boolean paraPartida;
    private JLabel fondo;
    public ConfirmarSalida(java.awt.Frame parent, boolean modal, String notificacion, boolean salir, boolean partida) {
        super(parent, modal);
        paraSalir = salir;
        paraPartida = partida;
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        initComponents(notificacion);
    }

    public void initComponents(String notificacion){
        yes = new JButton("Si");
        no = new JButton("No");
        texto = new JLabel("<html>" + notificacion + "</html>");
        fondo = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        if(!(notificacion.equals("Ya existe una partida en este lugar. ¿Estas seguro de que quieres eliminar esta partida?"))) {
            setBounds((int) (tamPant.width * 0.325), (int) (tamPant.height * 0.33), (int) (tamPant.width * 0.35), (int) (tamPant.height * 0.27));
            texto.setBounds((int) (tamPant.width*0.03), (int) (tamPant.height*0.04), (int) (tamPant.width*0.29), (int) (tamPant.height*0.1));
            yes.setBounds((int)(tamPant.width*0.07),(int) (tamPant.getHeight()*0.17), (int)(tamPant.width*0.05), (int) (tamPant.height*0.05));
            no.setBounds((int)(tamPant.width*0.23),(int) (tamPant.getHeight()*0.17), (int)(tamPant.width*0.05), (int) (tamPant.height*0.05));
            fondo.setBounds(0,0, (int)(tamPant.width*0.35), (int)(tamPant.height*0.27));
        }else {
            setBounds((int) (tamPant.width * 0.3), (int) (tamPant.height * 0.3), (int) (tamPant.width * 0.4), (int) (tamPant.height * 0.33));
            texto.setBounds((int) (tamPant.width*0.03), (int) (tamPant.height*0.04), (int) (tamPant.width*0.34), (int) (tamPant.height*0.18));
            yes.setBounds((int)(tamPant.width*0.07),(int) (tamPant.getHeight()*0.23), (int)(tamPant.width*0.05), (int) (tamPant.height*0.05));
            no.setBounds((int)(tamPant.width*0.27),(int) (tamPant.getHeight()*0.23), (int)(tamPant.width*0.05), (int) (tamPant.height*0.05));
            fondo.setBounds(0,0, (int)(tamPant.width*0.4), (int)(tamPant.height*0.33));
        }
        setBackground(new Color(45, 45, 45, 250));



        texto.setForeground(Color.white);
        texto.setFont(new Font("Segoe UI", 0, (int) (tamPant.width*0.017)));
        texto.setHorizontalAlignment(SwingConstants.CENTER);

        getContentPane().add(texto);

        yes.setFont(new Font("Segoe UI", 0, (int)(tamPant.width*0.017)));
        yes.setForeground(new Color(255, 255, 255));
        yes.setOpaque(false);
        yes.setBorderPainted(false);
        yes.setContentAreaFilled(false);
        yes.setFocusPainted(false);
        yes.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                yesMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                yesMouseExited(evt);
            }
        });
        yes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                yesActionPerformed(evt);
            }
        });
        getContentPane().add(yes);

        no.setFont(new Font("Segoe UI", 0, (int)(tamPant.width*0.017)));
        no.setForeground(new Color(255, 255, 255));
        no.setOpaque(false);
        no.setBorderPainted(false);
        no.setContentAreaFilled(false);
        no.setFocusPainted(false);

        no.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                noMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                noMouseExited(evt);
            }
        });
        no.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                noActionPerformed(evt);
            }
        });
        getContentPane().add(no);

        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Fondo confirmar.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon icono = null;
        if(!(notificacion.equals("Ya existe una partida en este lugar. ¿Estas seguro de que quieres eliminar esta partida?"))) {
            icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width * 0.35), (int) (tamPant.height * 0.27), Image.SCALE_SMOOTH));
        }else
            icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width * 0.4), (int) (tamPant.height * 0.33), Image.SCALE_SMOOTH));
        fondo.setIcon(icono);

        getContentPane().add(fondo);

    }

    private void noActionPerformed(ActionEvent evt) {
        dispose();
    }

    private void noMouseExited(MouseEvent evt) {
        no.setForeground(new Color(255, 255, 255));
    }

    private void noMouseEntered(MouseEvent evt) {
        no.setForeground(Color.red);
    }

    private void yesActionPerformed(ActionEvent evt) {
        if(paraSalir) {
            UnionInterfaces.getInstance().setSalirJuego(true);
        } else if (paraPartida){
            UnionInterfaces.getInstance().setConfirmarBorrado(true);
        }else
            UnionInterfaces.getInstance().setCerrarVentana(true);
        dispose();

    }

    private void yesMouseExited(MouseEvent evt) {
        yes.setForeground(new Color(255, 255, 255));
    }

    private void yesMouseEntered(MouseEvent evt) {
        yes.setForeground(Color.red);
    }
}
