package Interfaz.InterfazJugador;

import DatosAuxiliaresLogica.Informacion;
import Logica.Diario;
import Logica.Juego;
import Logica.Jugador;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author ROBERTO
 */
public class DiarioInterfaz extends javax.swing.JDialog {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DiarioInterfaz.class.getName());
    private Dimension tamPant;
    private LinkedList<Informacion> informacion;
    private java.util.Timer timer;
    private TimerTask tarea;
    private JButton botonSalir;

    /**
     * Creates new form Diario
     */
    public DiarioInterfaz(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        crearInfoDiario();
        informacion= Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes();

        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        timer = new Timer();
        tarea = new TimerTask() {
            @Override
            public void run() {
                revalidate();
                repaint();
            }
        };
        timer.scheduleAtFixedRate(tarea, 0, 15);
        initComponents();
    }

    private void initComponents() {
        BufferedImage imagenCursor =null;
        try {
            imagenCursor =  ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Cursor.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(imagenCursor, new Point(20, 0), "Cursor detective");
        setCursor(cursor);

        getContentPane().setBackground(new Color(0, 0, 0, 0));
        setUndecorated(true);
        Rectangle nombre = new Rectangle((int) (tamPant.width * 0.16), (int) (tamPant.height * 0.05), (int) (tamPant.width * 0.25), (int) (tamPant.height * 0.07));
        Rectangle foto = new Rectangle((int) (tamPant.width * 0.01), (int) (tamPant.height * 0.03), (int) (tamPant.width * 0.12), (int) (tamPant.height * 0.12));
        Rectangle datos = new Rectangle((int) (tamPant.width * 0.42), (int) (tamPant.height * 0.15), (int) (tamPant.width * 0.43), (int) (tamPant.height * 0.53));
        Rectangle registros = new Rectangle((int) (tamPant.width * 0.03), (int) (tamPant.height * 0.17), (int) (tamPant.width * 0.3), (int) (tamPant.height * 0.4));
        Rectangle panel = new Rectangle(0, 0, (int) (tamPant.width * 0.8), (int) (tamPant.height * 0.78));
        Rectangle labelDatos = new Rectangle((int) (tamPant.width * 0.42), (int) (tamPant.height * 0.03), (int) (tamPant.width * 0.32), (int) (tamPant.height * 0.08));

        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabelR1 = new JLabel();
        registro1 = new JTextArea();
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        jPanel2 = new JPanel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jScrollPane2 = new JScrollPane();
        jTextArea2 = new JTextArea();
        jPanel3 = new JPanel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jScrollPane3 = new JScrollPane();
        jTextArea3 = new JTextArea();
        jPanel4 = new JPanel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        jScrollPane4 = new JScrollPane();
        jTextArea4 = new JTextArea();
        jPanel5 = new JPanel();
        jLabel9 = new JLabel();
        jLabel10 = new JLabel();
        jScrollPane5 = new JScrollPane();
        jTextArea5 = new JTextArea();
        jPanel6 = new JPanel();
        jLabel11 = new JLabel();
        jLabel12 = new JLabel();
        jScrollPane6 = new JScrollPane();
        jTextArea6 = new JTextArea();
        jPanel7 = new JPanel();
        jLabel13 = new JLabel();
        jLabel14 = new JLabel();
        jScrollPane7 = new JScrollPane();
        jTextArea7 = new JTextArea();
        jPanel8 = new JPanel();
        jLabel15 = new JLabel();
        jLabel16 = new JLabel();
        jScrollPane8 = new JScrollPane();
        jTextArea8 = new JTextArea();
        jPanel9 = new JPanel();
        jLabel17 = new JLabel();
        jLabel18 = new JLabel();
        jScrollPane9 = new JScrollPane();
        jTextArea9 = new JTextArea();
        jPanel10 = new JPanel();
        jLabel19 = new JLabel();
        jLabel20 = new JLabel();
        jScrollPane10 = new JScrollPane();
        jTextArea10 = new JTextArea();

        fondo = new JLabel();

        contenedor = new JPanel();
        contenedor.setBackground(new Color(0, 0, 0, 0));
        contenedor.setBounds((int) (tamPant.width * 0.14), (int) (tamPant.height * 0.17), (int) (tamPant.width * 0.8), (int) (tamPant.height * 0.78));
        contenedor.setLayout(null);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        setBackground(new Color(0, 0, 0, 75));

        jPanel1.setBackground(new Color(0, 0, 0, 0));
        jPanel1.setBounds(panel);
        jPanel1.setLayout(null);

        jLabel1.setText(Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(0).getNombreNPC());
        jLabel1.setBounds(nombre);
        jLabel1.setFont(new Font("Viner Hand ITC", Font.PLAIN, (int) (tamPant.width * 0.016)));
        jLabel1.setForeground(Color.black);
        jPanel1.add(jLabel1);

        jLabel2.setBounds(foto);
        jLabel2.setOpaque(true);
        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/Personajes/Victima Reducida.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width * 0.11), (int) (tamPant.height * 0.13), Image.SCALE_SMOOTH));
        jLabel2.setIcon(icono);
        jLabel2.setBorder(new LineBorder(Color.black, 2, true));
        jLabel2.setBackground(new Color(255, 255, 255, 30));
        jPanel1.add(jLabel2);

        jLabelR1.setText("Datos relacionados con la investigacion");
        jLabelR1.setBounds(labelDatos);
        jLabelR1.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.016)));
        jLabelR1.setForeground(Color.black);
        jPanel1.add(jLabelR1);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setBackground(new Color(0, 0, 0, 0));
        jTextArea1.setEnabled(false);
        jTextArea1.setFocusable(false);
        jTextArea1.setHighlighter(null);
        jTextArea1.setDisabledTextColor(Color.black);
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setEditable(false);

        Informacion info1 = informacion.getFirst();
        Iterator<String> iterator1 = info1.getListaDeDialogos().iterator();
        while (iterator1.hasNext()) {
            String contenido = iterator1.next();
            if (!jTextArea1.getText().isEmpty()) {
                jTextArea1.setText(jTextArea1.getText() + "\n" + "·" + contenido);
            } else
                jTextArea1.setText("·" + contenido);
        }
        jTextArea1.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.013)));

        jScrollPane1.setViewportView(jTextArea1);
        jScrollPane1.setBounds(datos);
        jScrollPane1.setOpaque(true);
        jScrollPane1.setBackground(new Color(0, 0, 0, 0));
        jScrollPane1.setBorder(null);

        jPanel1.add(jScrollPane1);

        registro1.setColumns(20);
        registro1.setRows(5);
        registro1.setHighlighter(null);
        registro1.setFocusable(false);
        registro1.setDisabledTextColor(Color.black);
        registro1.setLineWrap(true);
        registro1.setWrapStyleWord(true);
        registro1.setEditable(false);
        registro1.setBackground(new Color(0, 0, 0, 0));
        registro1.setText("   Datos  Generales: \nEdad: " + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(0).getEdadNPC() +       "    " +
                "Sexo: "+ Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(0).getSexo()  +
                "  \n" + "Ocupacion:  "+ Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(0).getOcupacion()  + " \n\n\n\n  Analisis de personalidad: \n");
        int r1= Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(0).getPersonalidad().size();
        for(int q = 0; q<r1; q++){
            if(q==(r1-1))
                registro1.setText(registro1.getText() + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(0).getPersonalidad().get(q));
            else
                registro1.setText(registro1.getText() + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(0).getPersonalidad().get(q)+ ", ");

        }
        registro1.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.015)));
        registro1.setBounds(registros);
        jPanel1.add(registro1);


        contenedor.add(jPanel1);

        //Pagina 2
        jPanel2.setBackground(new Color(0, 0, 0, 0));
        jPanel2.setLayout(null);
        jPanel2.setBounds(panel);

        jLabel4.setText(Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(1).getNombreNPC());
        jLabel4.setBounds(nombre);
        jLabel4.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.016)));
        jLabel4.setForeground(Color.black);
        jPanel2.add(jLabel4);

        jLabel3.setBounds(foto);
        jLabel3.setOpaque(true);
        BufferedImage imagen3 = null;

        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/Personajes/Capitan Cabeza.png"));
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen: " + e.getMessage());
        }

        if (imagen3 != null) {
            ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width * 0.11), (int) (tamPant.height * 0.13), Image.SCALE_SMOOTH));
            jLabel3.setIcon(icono3);
        }
        jLabel3.setBorder(new LineBorder(Color.black, 2, true));
        jLabel3.setBackground(new Color(255, 255, 255, 30));
        jPanel2.add(jLabel3);

        JLabel jLabelR2 = new JLabel();
        jLabelR2.setText("Datos relacionados con la investigacion");
        jLabelR2.setBounds(labelDatos);
        jLabelR2.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.016)));
        jLabelR2.setForeground(Color.black);
        jPanel2.add(jLabelR2);

        Informacion info2 = informacion.get(1);
        Iterator<String> iterator2 = info2.getListaDeDialogos().iterator();
        while(iterator2.hasNext()) {
            String contenido = iterator2.next();
            if (!jTextArea2.getText().isEmpty()) {
                jTextArea2.setText(jTextArea2.getText() + "\n" + "·" + contenido);
            } else
                jTextArea2.setText("·" + contenido);
        }
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setBackground(new Color(0, 0, 0, 0));
        jTextArea2.setEnabled(false);
        jTextArea2.setFocusable(false);
        jTextArea2.setHighlighter(null);
        jTextArea2.setDisabledTextColor(Color.black);
        jTextArea2.setLineWrap(true);
        jTextArea2.setWrapStyleWord(true);
        jTextArea2.setEditable(false);
        jTextArea2.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.013)));

        jScrollPane2.setViewportView(jTextArea2);
        jScrollPane2.setBounds(datos);
        jScrollPane2.setOpaque(true);
        jScrollPane2.setBackground(new Color(0, 0, 0, 0));
        jScrollPane2.setBorder(null);
        jPanel2.add(jScrollPane2);

        JTextArea registro2 = new JTextArea();
        registro2.setColumns(20);
        registro2.setRows(5);
        registro2.setHighlighter(null);
        registro2.setFocusable(false);
        registro2.setDisabledTextColor(Color.black);
        registro2.setLineWrap(true);
        registro2.setWrapStyleWord(true);
        registro2.setEditable(false);
        registro2.setBackground(new Color(0, 0, 0, 0));

        registro2.setText("   Datos  Generales: \nEdad: " + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(1).getEdadNPC() + "    "+
                "Sexo: "+ Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(1).getSexo()  +
                "  \n" + "Ocupacion:  "+ Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(1).getOcupacion()  + " \n\n\n\n  Analisis de personalidad: \n");
        int r2= Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(1).getPersonalidad().size();
        for(int q =0; q<r2; q++){
            if(q!=r2-1)
                registro2.setText(registro2.getText() + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(1).getPersonalidad().get(q) + ", ");
            else
                registro2.setText(registro2.getText() + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(1).getPersonalidad().get(q));

        }
        registro2.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.015)));
        registro2.setBounds(registros);
        jPanel2.add(registro2);


        //Pagina 3


        jPanel3.setBackground(new Color(0, 0, 0, 0));
        jPanel3.setLayout(null);
        jPanel3.setBounds(panel);

        jLabel6.setText(Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(2).getNombreNPC());
        jLabel6.setBounds(nombre);
        jLabel6.setFont(new Font("Viner Hand ITC", Font.PLAIN, (int) (tamPant.width * 0.016)));
        jLabel6.setForeground(Color.black);
        jPanel3.add(jLabel6);

        jLabel5.setBounds(foto);
        jLabel5.setOpaque(true);
        BufferedImage imagen5 = null;

        try {
            imagen5 = ImageIO.read(new File("DatosAuxiliares/Personajes/Policia Reducido.png"));
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen: " + e.getMessage());
        }

        if (imagen5 != null) {
            ImageIcon icono5 = new ImageIcon(imagen5.getScaledInstance((int) (tamPant.width * 0.11), (int) (tamPant.height * 0.13), Image.SCALE_SMOOTH));
            jLabel5.setIcon(icono5);
        }
        jLabel5.setBorder(new LineBorder(Color.black, 2, true));
        jLabel5.setBackground(new Color(255, 255, 255, 30));
        jPanel3.add(jLabel5);

        JLabel jLabelR3 = new JLabel();
        jLabelR3.setText("Datos relacionados con la investigacion");
        jLabelR3.setBounds(labelDatos);
        jLabelR3.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.016)));
        jLabelR3.setForeground(Color.black);
        jPanel3.add(jLabelR3);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jTextArea3.setBackground(new Color(0, 0, 0, 0));
        jTextArea3.setEnabled(false);
        jTextArea3.setFocusable(false);
        jTextArea3.setHighlighter(null);
        jTextArea3.setDisabledTextColor(Color.black);
        jTextArea3.setLineWrap(true);
        jTextArea3.setWrapStyleWord(true);
        jTextArea3.setEditable(false);

        Informacion info3 = informacion.get(2);
        Iterator<String> iterator3 = info3.getListaDeDialogos().iterator();
        while(iterator3.hasNext()) {
            String contenido = iterator3.next();
            if (!jTextArea3.getText().isEmpty()) {
                jTextArea3.setText(jTextArea3.getText() + "\n" + "·" + contenido);
            } else
                jTextArea3.setText("·" + contenido);
        }
        jTextArea3.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.013)));

        jScrollPane3.setViewportView(jTextArea3);
        jScrollPane3.setBounds(datos);
        jScrollPane3.setOpaque(true);
        jScrollPane3.setBackground(new Color(0, 0, 0, 0));
        jScrollPane3.setBorder(null);
        jPanel3.add(jScrollPane3);

        JTextArea registro3 = new JTextArea();
        registro3.setColumns(20);
        registro3.setRows(5);
        registro3.setHighlighter(null);
        registro3.setFocusable(false);
        registro3.setDisabledTextColor(Color.black);
        registro3.setLineWrap(true);
        registro3.setWrapStyleWord(true);
        registro3.setEditable(false);
        registro3.setBackground(new Color(0, 0, 0, 0));
        registro3.setText("   Datos  Generales: \nEdad: " + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(2).getEdadNPC() + "    "+
                "Sexo: "+ Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(2).getSexo()  +
                "  \n" + "Ocupacion:  "+ Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(2).getOcupacion()  + " \n\n\n\n  Analisis de personalidad: \n");
        int r3= Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(2).getPersonalidad().size();
        for(int q =0; q<r3; q++){
            if(q!=r3-1)
                registro3.setText(registro3.getText() + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(2).getPersonalidad().get(q) + ", ");
            else
                registro3.setText(registro3.getText() + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(2).getPersonalidad().get(q));

        }
        registro3.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.015)));
        registro3.setBounds(registros);
        jPanel3.add(registro3);


        //Pagina 4


        jPanel4.setBackground(new Color(0, 0, 0, 0));
        jPanel4.setLayout(null);
        jPanel4.setBounds(panel);

        jLabel8.setText(Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(3).getNombreNPC());
        jLabel8.setBounds(nombre);
        jLabel8.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.016)));
        jLabel8.setForeground(Color.black);
        jPanel4.add(jLabel8);

        jLabel7.setBounds(foto);
        jLabel7.setOpaque(true);
        BufferedImage imagen7 = null;

        try {
            imagen7 = ImageIO.read(new File("DatosAuxiliares/Personajes/Dueño Reducido.png"));
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen: " + e.getMessage());
        }

        if (imagen7 != null) {
            ImageIcon icono7 = new ImageIcon(imagen7.getScaledInstance((int) (tamPant.width * 0.11), (int) (tamPant.height * 0.13), Image.SCALE_SMOOTH));
            jLabel7.setIcon(icono7);
        }
        jLabel7.setBorder(new LineBorder(Color.black, 2, true));
        jLabel7.setBackground(new Color(255, 255, 255, 30));
        jPanel4.add(jLabel7);

        JLabel jLabelR4 = new JLabel();
        jLabelR4.setText("Datos relacionados con la investigacion");
        jLabelR4.setBounds(labelDatos);
        jLabelR4.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.016)));
        jLabelR4.setForeground(Color.black);
        jPanel4.add(jLabelR4);

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jTextArea4.setBackground(new Color(0, 0, 0, 0));
        jTextArea4.setEnabled(false);
        jTextArea4.setFocusable(false);
        jTextArea4.setHighlighter(null);
        jTextArea4.setDisabledTextColor(Color.black);
        jTextArea4.setLineWrap(true);
        jTextArea4.setWrapStyleWord(true);
        jTextArea4.setEditable(false);

        Informacion info4 = informacion.get(3);
        Iterator<String> iterator4 = info4.getListaDeDialogos().iterator();
        while(iterator4.hasNext()) {
            String contenido = iterator4.next();
            if (!jTextArea4.getText().isEmpty()) {
                jTextArea4.setText(jTextArea4.getText() + "\n" + "·" + contenido);
            } else
                jTextArea4.setText("·" + contenido);
        }

        jTextArea4.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.013)));

        jScrollPane4.setViewportView(jTextArea4);
        jScrollPane4.setBounds(datos);
        jScrollPane4.setOpaque(true);
        jScrollPane4.setBackground(new Color(0, 0, 0, 0));
        jScrollPane4.setBorder(null);
        jPanel4.add(jScrollPane4);

        JTextArea registro4 = new JTextArea();
        registro4.setColumns(20);
        registro4.setRows(5);
        registro4.setHighlighter(null);
        registro4.setFocusable(false);
        registro4.setDisabledTextColor(Color.black);
        registro4.setLineWrap(true);
        registro4.setWrapStyleWord(true);
        registro4.setEditable(false);
        registro4.setBackground(new Color(0, 0, 0, 0));
        registro4.setText("   Datos  Generales: \nEdad: " + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(3).getEdadNPC() + "    " +
                "Sexo: "+ Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(3).getSexo()  +
                "  \n" + "Ocupacion:  "+ Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(3).getOcupacion()  + " \n\n\n\n  Analisis de personalidad: \n");
        int r4= Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(3).getPersonalidad().size();
        for(int q =0; q<r4; q++){
            if(q!=r4-1)
                registro4.setText(registro4.getText() + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(3).getPersonalidad().get(q) + ", ");
            else
                registro4.setText(registro4.getText() + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(3).getPersonalidad().get(q));

        }
        registro4.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.015)));
        registro4.setBounds(registros);
        jPanel4.add(registro4);

        //Pagina 5

        jPanel5.setBackground(new Color(0, 0, 0, 0));
        jPanel5.setLayout(null);
        jPanel5.setBounds(panel);

        jLabel10.setText(Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(4).getNombreNPC());
        jLabel10.setBounds(nombre);
        jLabel10.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.016)));
        jLabel10.setForeground(Color.black);
        jPanel5.add(jLabel10);

        jLabel9.setBounds(foto);
        jLabel9.setOpaque(true);
        BufferedImage imagen9 = null;

        try {
            imagen9 = ImageIO.read(new File("DatosAuxiliares/Personajes/Esposa Reducida.png"));
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen: " + e.getMessage());
        }

        if (imagen9 != null) {
            ImageIcon icono9 = new ImageIcon(imagen9.getScaledInstance((int) (tamPant.width * 0.11), (int) (tamPant.height * 0.13), Image.SCALE_SMOOTH));
            jLabel9.setIcon(icono9);
        }
        jLabel9.setBorder(new LineBorder(Color.black, 2, true));
        jLabel9.setBackground(new Color(255, 255, 255, 30));
        jPanel5.add(jLabel9);

        JLabel jLabelR5 = new JLabel();
        jLabelR5.setText("Datos relacionados con la investigacion");
        jLabelR5.setBounds(labelDatos);
        jLabelR5.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.016)));
        jLabelR5.setForeground(Color.black);
        jPanel5.add(jLabelR5);

        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jTextArea5.setBackground(new Color(0, 0, 0, 0));
        jTextArea5.setEnabled(false);
        jTextArea5.setFocusable(false);
        jTextArea5.setHighlighter(null);
        jTextArea5.setDisabledTextColor(Color.black);
        jTextArea5.setLineWrap(true);
        jTextArea5.setWrapStyleWord(true);
        jTextArea5.setEditable(false);

        Informacion info5 = informacion.get(4);
        Iterator<String> iterator5 = info5.getListaDeDialogos().iterator();
        while(iterator5.hasNext()) {
            String contenido = iterator3.next();
            if (!jTextArea5.getText().isEmpty()) {
                jTextArea5.setText(jTextArea5.getText() + "\n" + "·" + contenido);
            } else
                jTextArea5.setText("·" + contenido);
        }

        jTextArea5.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.013)));

        jScrollPane5.setViewportView(jTextArea5);
        jScrollPane5.setBounds(datos);
        jScrollPane5.setOpaque(true);
        jScrollPane5.setBackground(new Color(0, 0, 0, 0));
        jScrollPane5.setBorder(null);
        jPanel5.add(jScrollPane5);

        JTextArea registro5 = new JTextArea();
        registro5.setColumns(20);
        registro5.setRows(5);
        registro5.setHighlighter(null);
        registro5.setFocusable(false);
        registro5.setDisabledTextColor(Color.black);
        registro5.setLineWrap(true);
        registro5.setWrapStyleWord(true);
        registro5.setEditable(false);
        registro5.setBackground(new Color(0, 0, 0, 0));
        registro5.setText("   Datos  Generales: \nEdad: " + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(4).getEdadNPC() + "    "+
                "Sexo: "+ Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(4).getSexo()  +
                "  \n" + "Ocupacion:  "+ Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(4).getOcupacion()  + " \n\n\n\n  Analisis de personalidad: \n");
        int r5= Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(4).getPersonalidad().size();
        for(int q =0; q<r5; q++) {
            if (q != r5-1)
                registro5.setText(registro5.getText() + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(4).getPersonalidad().get(q) + ", " );
            else
                registro5.setText(registro5.getText() + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(4).getPersonalidad().get(q));
        }
        registro5.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.015)));
        registro5.setBounds(registros);
        jPanel5.add(registro5);

        //Pagina 6
        jPanel6.setBackground(new Color(0, 0, 0, 0));
        jPanel6.setLayout(null);
        jPanel6.setBounds(panel);

        jLabel12.setText(Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(5).getNombreNPC());
        jLabel12.setBounds(nombre);
        jLabel12.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.016)));
        jLabel12.setForeground(Color.black);
        jPanel6.add(jLabel12);

        jLabel11.setBounds(foto);
        jLabel11.setOpaque(true);
        BufferedImage imagen11 = null;

        try {
            imagen11 = ImageIO.read(new File("DatosAuxiliares/Personajes/Secretaria Reducida.png"));
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen: " + e.getMessage());
        }

        if (imagen11 != null) {
            ImageIcon icono11 = new ImageIcon(imagen11.getScaledInstance((int) (tamPant.width * 0.11), (int) (tamPant.height * 0.13), Image.SCALE_SMOOTH));
            jLabel11.setIcon(icono11);
        }
        jLabel11.setBorder(new LineBorder(Color.black, 2, true));
        jLabel11.setBackground(new Color(255, 255, 255, 30));
        jPanel6.add(jLabel11);

        JLabel jLabelR6 = new JLabel();
        jLabelR6.setText("Datos relacionados con la investigacion");
        jLabelR6.setBounds(labelDatos);
        jLabelR6.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.016)));
        jLabelR6.setForeground(Color.black);
        jPanel6.add(jLabelR6);

        jTextArea6.setColumns(20);
        jTextArea6.setRows(5);
        jTextArea6.setBackground(new Color(0, 0, 0, 0));
        jTextArea6.setEnabled(false);
        jTextArea6.setFocusable(false);
        jTextArea6.setHighlighter(null);
        jTextArea6.setDisabledTextColor(Color.black);
        jTextArea6.setLineWrap(true);
        jTextArea6.setWrapStyleWord(true);
        jTextArea6.setEditable(false);

        Informacion info6 = informacion.get(5);
        Iterator<String> iterator6 = info6.getListaDeDialogos().iterator();
        while(iterator6.hasNext()) {
            String contenido = iterator6.next();
            if (!jTextArea6.getText().isEmpty()) {
                jTextArea6.setText(jTextArea6.getText() + "\n" + "·" + contenido);
            } else
                jTextArea6.setText("·" + contenido);
        }
        jTextArea6.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.013)));

        jScrollPane6.setViewportView(jTextArea6);
        jScrollPane6.setBounds(datos);
        jScrollPane6.setOpaque(true);
        jScrollPane6.setBackground(new Color(0, 0, 0, 0));
        jScrollPane6.setBorder(null);
        jPanel6.add(jScrollPane6);

        JTextArea registro6 = new JTextArea();
        registro6.setColumns(20);
        registro6.setRows(5);
        registro6.setHighlighter(null);
        registro6.setFocusable(false);
        registro6.setDisabledTextColor(Color.black);
        registro6.setLineWrap(true);
        registro6.setWrapStyleWord(true);
        registro6.setEditable(false);
        registro6.setBackground(new Color(0, 0, 0, 0));
        registro6.setText("   Datos  Generales: \nEdad: " + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(5).getEdadNPC() + "    "+
                "Sexo: "+ Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(5).getSexo()  +
                "  \n" + "Ocupacion:  "+ Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(5).getOcupacion()  + " \n\n\n\n  Analisis de personalidad: \n");
        int r6= Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(5).getPersonalidad().size();
        for(int q =0; q<r6; q++) {
            if (q != r6-1)
                registro6.setText(registro6.getText() + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(5).getPersonalidad().get(q) + ", ");
            else
                registro6.setText(registro6.getText() + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(5).getPersonalidad().get(q));
        }
        registro6.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.015)));
        registro6.setBounds(registros);
        jPanel6.add(registro6);

        //Pagina 7

        jPanel7.setBackground(new Color(0, 0, 0, 0));
        jPanel7.setLayout(null);
        jPanel7.setBounds(panel);

        jLabel14.setText(Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(6).getNombreNPC());
        jLabel14.setBounds(nombre);
        jLabel14.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.016)));
        jLabel14.setForeground(Color.black);
        jPanel7.add(jLabel14);

        jLabel13.setBounds(foto);
        jLabel13.setOpaque(true);
        BufferedImage imagen13 = null;

        try {
            imagen13 = ImageIO.read(new File("DatosAuxiliares/Personajes/Guia 1 Reducido.png"));
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen: " + e.getMessage());
        }

        if (imagen13 != null) {
            ImageIcon icono13 = new ImageIcon(imagen13.getScaledInstance((int) (tamPant.width * 0.11), (int) (tamPant.height * 0.13), Image.SCALE_SMOOTH));
            jLabel13.setIcon(icono13);
        }
        jLabel13.setBorder(new LineBorder(Color.black, 2, true));
        jLabel13.setBackground(new Color(255, 255, 255, 30));
        jPanel7.add(jLabel13);

        JLabel jLabelR7 = new JLabel();
        jLabelR7.setText("Datos relacionados con la investigacion");
        jLabelR7.setBounds(labelDatos);
        jLabelR7.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.016)));
        jLabelR7.setForeground(Color.black);
        jPanel7.add(jLabelR7);

        jTextArea7.setColumns(20);
        jTextArea7.setRows(5);
        jTextArea7.setBackground(new Color(0, 0, 0, 0));
        jTextArea7.setEnabled(false);
        jTextArea7.setFocusable(false);
        jTextArea7.setHighlighter(null);
        jTextArea7.setDisabledTextColor(Color.black);
        jTextArea7.setLineWrap(true);
        jTextArea7.setWrapStyleWord(true);
        jTextArea7.setEditable(false);

        Informacion info7 = informacion.get(6);
        Iterator<String> iterator7 = info7.getListaDeDialogos().iterator();
        while(iterator7.hasNext()) {
            String contenido = iterator7.next();
            if (!jTextArea7.getText().isEmpty()) {
                jTextArea7.setText(jTextArea7.getText() + "\n" + "·" + contenido);
            } else
                jTextArea7.setText("·" + contenido);
        }
        jTextArea7.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.013)));

        jScrollPane7.setViewportView(jTextArea7);
        jScrollPane7.setBounds(datos);
        jScrollPane7.setOpaque(true);
        jScrollPane7.setBackground(new Color(0, 0, 0, 0));
        jScrollPane7.setBorder(null);
        jPanel7.add(jScrollPane7);

        JTextArea registro7 = new JTextArea();
        registro7.setColumns(20);
        registro7.setRows(5);
        registro7.setHighlighter(null);
        registro7.setFocusable(false);
        registro7.setDisabledTextColor(Color.black);
        registro7.setLineWrap(true);
        registro7.setWrapStyleWord(true);
        registro7.setEditable(false);
        registro7.setBackground(new Color(0, 0, 0, 0));
        registro7.setText("   Datos  Generales: \nEdad: " + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(6).getEdadNPC() + "    "+
                "Sexo: "+ Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(6).getSexo()  +
                "  \n" + "Ocupacion:  "+ Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(6).getOcupacion()  + " \n\n\n\n  Analisis de personalidad: \n");
        int r7= Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(6).getPersonalidad().size();
        for(int q =0; q<r7; q++) {
            if (q != r7-1)
                registro7.setText(registro7.getText() + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(6).getPersonalidad().get(q) + ", ");
            else
                registro7.setText(registro7.getText() + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(6).getPersonalidad().get(q));
        }
        registro7.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.015)));
        registro7.setBounds(registros);
        jPanel7.add(registro7);

        //Pagina 8
        jPanel8.setBackground(new Color(0, 0, 0, 0));
        jPanel8.setLayout(null);
        jPanel8.setBounds(panel);

        jLabel16.setText(Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(7).getNombreNPC());
        jLabel16.setBounds(nombre);
        jLabel16.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.016)));
        jLabel16.setForeground(Color.black);
        jPanel8.add(jLabel16);

        jLabel15.setBounds(foto);
        jLabel15.setOpaque(true);
        BufferedImage imagen15 = null;

        try {
            imagen15 = ImageIO.read(new File("DatosAuxiliares/Personajes/Guia 2 Reducido.png"));
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen: " + e.getMessage());
        }

        if (imagen15 != null) {
            ImageIcon icono15 = new ImageIcon(imagen15.getScaledInstance((int) (tamPant.width * 0.11), (int) (tamPant.height * 0.13), Image.SCALE_SMOOTH));
            jLabel15.setIcon(icono15);
        }
        jLabel15.setBorder(new LineBorder(Color.black, 2, true));
        jLabel15.setBackground(new Color(255, 255, 255, 30));
        jPanel8.add(jLabel15);

        JLabel jLabelR8 = new JLabel();
        jLabelR8.setText("Datos relacionados con la investigacion");
        jLabelR8.setBounds(labelDatos);
        jLabelR8.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.016)));
        jLabelR8.setForeground(Color.black);
        jPanel8.add(jLabelR8);

        jTextArea8.setColumns(20);
        jTextArea8.setRows(5);
        jTextArea8.setBackground(new Color(0, 0, 0, 0));
        jTextArea8.setEnabled(false);
        jTextArea8.setFocusable(false);
        jTextArea8.setHighlighter(null);
        jTextArea8.setDisabledTextColor(Color.black);
        jTextArea8.setLineWrap(true);
        jTextArea8.setWrapStyleWord(true);
        jTextArea8.setEditable(false);

        Informacion info8 = informacion.get(7);
        Iterator<String> iterator8 = info8.getListaDeDialogos().iterator();
        while(iterator8.hasNext()) {
            String contenido = iterator8.next();
            if (!jTextArea8.getText().isEmpty()) {
                jTextArea8.setText(jTextArea8.getText() + "\n" + "·" + contenido);
            } else
                jTextArea8.setText("·" + contenido);
        }

        jTextArea8.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.013)));

        jScrollPane8.setViewportView(jTextArea8);
        jScrollPane8.setBounds(datos);
        jScrollPane8.setOpaque(true);
        jScrollPane8.setBackground(new Color(0, 0, 0, 0));
        jScrollPane8.setBorder(null);
        jPanel8.add(jScrollPane8);

        JTextArea registro8 = new JTextArea();
        registro8.setColumns(20);
        registro8.setRows(5);
        registro8.setHighlighter(null);
        registro8.setFocusable(false);
        registro8.setDisabledTextColor(Color.black);
        registro8.setLineWrap(true);
        registro8.setWrapStyleWord(true);
        registro8.setEditable(false);

        registro8.setBackground(new Color(0, 0, 0, 0));
        registro8.setText("   Datos  Generales: \nEdad: " + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(7).getEdadNPC() + "    "+
                "Sexo: "+ Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(7).getSexo()  +
                "  \n" + "Ocupacion:  "+ Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(7).getOcupacion()  + " \n\n\n\n  Analisis de personalidad: \n");
        int r8= Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(7).getPersonalidad().size();
        for(int q =0; q<r8; q++) {
            if (q != r8-1)
                registro8.setText(registro8.getText() + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(7).getPersonalidad().get(q) + ", ");
            else
                registro8.setText(registro8.getText() + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(7).getPersonalidad().get(q));
        }

        registro8.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.015)));
        registro8.setBounds(registros);
        jPanel8.add(registro8);

        //Pagina 9
        jPanel9.setBackground(new Color(0, 0, 0, 0));
        jPanel9.setLayout(null);
        jPanel9.setBounds(panel);

        jLabel18.setText(Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(8).getNombreNPC());
        jLabel18.setBounds(nombre);
        jLabel18.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.016)));
        jLabel18.setForeground(Color.black);
        jPanel9.add(jLabel18);

        jLabel17.setBounds(foto);
        jLabel17.setOpaque(true);
        BufferedImage imagen17 = null;

        try {
            imagen17 = ImageIO.read(new File("DatosAuxiliares/Personajes/Seguridad Reducido.png"));
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen: " + e.getMessage());
        }

        if (imagen17 != null) {
            ImageIcon icono17 = new ImageIcon(imagen17.getScaledInstance((int) (tamPant.width * 0.11), (int) (tamPant.height * 0.13), Image.SCALE_SMOOTH));
            jLabel17.setIcon(icono17);
        }
        jLabel17.setBorder(new LineBorder(Color.black, 2, true));
        jLabel17.setBackground(new Color(255, 255, 255, 30));
        jPanel9.add(jLabel17);

        JLabel jLabelR9 = new JLabel();
        jLabelR9.setText("Datos relacionados con la investigacion");
        jLabelR9.setBounds(labelDatos);
        jLabelR9.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.016)));
        jLabelR9.setForeground(Color.black);
        jPanel9.add(jLabelR9);

        jTextArea9.setColumns(20);
        jTextArea9.setRows(5);
        jTextArea9.setBackground(new Color(0, 0, 0, 0));
        jTextArea9.setEnabled(false);
        jTextArea9.setFocusable(false);
        jTextArea9.setHighlighter(null);
        jTextArea9.setDisabledTextColor(Color.black);
        jTextArea9.setLineWrap(true);
        jTextArea9.setWrapStyleWord(true);
        jTextArea9.setEditable(false);

        Informacion info9 = informacion.get(8);
        Iterator<String> iterator9 = info9.getListaDeDialogos().iterator();
        while(iterator9.hasNext()) {
            String contenido = iterator9.next();
            if (!jTextArea9.getText().isEmpty()) {
                jTextArea9.setText(jTextArea9.getText() + "\n" + "·" + contenido);
            } else
                jTextArea9.setText("·" + contenido);
        }

        jTextArea9.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.013)));

        jScrollPane9.setViewportView(jTextArea9);
        jScrollPane9.setBounds(datos);
        jScrollPane9.setOpaque(true);
        jScrollPane9.setBackground(new Color(0, 0, 0, 0));
        jScrollPane9.setBorder(null);
        jPanel9.add(jScrollPane9);

        JTextArea registro9 = new JTextArea();
        registro9.setColumns(20);
        registro9.setRows(5);
        registro9.setHighlighter(null);
        registro9.setFocusable(false);
        registro9.setDisabledTextColor(Color.black);
        registro9.setLineWrap(true);
        registro9.setWrapStyleWord(true);
        registro9.setEditable(false);
        registro9.setBackground(new Color(0, 0, 0, 0));

        registro9.setText("   Datos  Generales: \nEdad: " + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(8).getEdadNPC() + "    "+
                "Sexo: "+ Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(8).getSexo()  +
                "  \n" + "Ocupacion:  "+ Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(8).getOcupacion()  + " \n\n\n\n  Analisis de personalidad: \n");
        int r9= Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(8).getPersonalidad().size();
        for(int q =0; q<r9; q++) {
            if (q != r9-1)
                registro9.setText(registro9.getText() + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(8).getPersonalidad().get(q) + ", ");
            else
                registro9.setText(registro9.getText() + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(8).getPersonalidad().get(q));
        }
        registro9.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.015)));
        registro9.setBounds(registros);
        jPanel9.add(registro9);


        //Pagina 10


        jPanel10.setBackground(new Color(0, 0, 0, 0));
        jPanel10.setLayout(null);
        jPanel10.setBounds(panel);

        jLabel20.setText(Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(9).getNombreNPC());
        jLabel20.setBounds(nombre);
        jLabel20.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.016)));
        jLabel20.setForeground(Color.black);
        jPanel10.add(jLabel20);

        jLabel19.setBounds(foto);
        jLabel19.setOpaque(true);
        BufferedImage imagen19 = null;

        try {
            imagen19 = ImageIO.read(new File("DatosAuxiliares/Personajes/Vagabundo Reducido.png"));
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen: " + e.getMessage());
        }

        if (imagen19 != null) {
            ImageIcon icono19 = new ImageIcon(imagen19.getScaledInstance((int) (tamPant.width * 0.11), (int) (tamPant.height * 0.13), Image.SCALE_SMOOTH));
            jLabel19.setIcon(icono19);
        }
        jLabel19.setBorder(new LineBorder(Color.black, 2, true));
        jLabel19.setBackground(new Color(255, 255, 255, 30));
        jPanel10.add(jLabel19);

        JLabel jLabelR10 = new JLabel();
        jLabelR10.setText("Datos relacionados con la investigacion");
        jLabelR10.setBounds(labelDatos);
        jLabelR10.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.016)));
        jLabelR10.setForeground(Color.black);
        jPanel10.add(jLabelR10);

        jTextArea10.setColumns(20);
        jTextArea10.setRows(5);
        jTextArea10.setBackground(new Color(0, 0, 0, 0));
        jTextArea10.setEnabled(false);
        jTextArea10.setFocusable(false);
        jTextArea10.setHighlighter(null);
        jTextArea10.setDisabledTextColor(Color.black);
        jTextArea10.setLineWrap(true);
        jTextArea10.setWrapStyleWord(true);
        jTextArea10.setEditable(false);

        Informacion info10 = informacion.get(9);
        Iterator<String> iterator10 = info10.getListaDeDialogos().iterator();
        while(iterator10.hasNext()) {
            String contenido = iterator10.next();
            if (!jTextArea10.getText().isEmpty()) {
                jTextArea10.setText(jTextArea10.getText() + "\n" + "·" + contenido);
            } else
                jTextArea10.setText("·" + contenido);
        }
        jTextArea10.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.013)));

        jScrollPane10.setViewportView(jTextArea10);
        jScrollPane10.setBounds(datos);
        jScrollPane10.setOpaque(true);
        jScrollPane10.setBackground(new Color(0, 0, 0, 0));
        jScrollPane10.setBorder(null);
        jPanel10.add(jScrollPane10);

        JTextArea registro10 = new JTextArea();
        registro10.setColumns(20);
        registro10.setRows(5);
        registro10.setHighlighter(null);
        registro10.setFocusable(false);
        registro10.setDisabledTextColor(Color.black);
        registro10.setLineWrap(true);
        registro10.setWrapStyleWord(true);
        registro10.setEditable(false);
        registro10.setBackground(new Color(0, 0, 0, 0));
        registro10.setText("   Datos  Generales: \nEdad: " + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(9).getEdadNPC() + "    "+
                "Sexo: "+ Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(9).getSexo()  +
                "  \n" + "Ocupacion:  "+ Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(9).getOcupacion()  + " \n\n\n\n  Analisis de personalidad: \n");
        int r10= Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(9).getPersonalidad().size();
        for(int q =0; q<r10; q++) {
            if (q != r10-1)
                registro10.setText(registro10.getText() + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(9).getPersonalidad().get(q) + ", ");
            else
                registro10.setText(registro10.getText() + Juego.getInstance().getPartidaActual().getJugador().getDiario().getDialogosImportantes().get(9).getPersonalidad().get(q));
        }
        registro10.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.015)));
        registro10.setBounds(registros);
        jPanel10.add(registro10);


        //Fin de las paginas
        getContentPane().add(contenedor);
        botonSalir = new JButton("Salir");
        botonSalir.setBounds((int) (tamPant.width * 0.9), (int) (tamPant.height * 0.03), (int) (tamPant.width * 0.08), (int) (tamPant.height * 0.05));
        botonSalir.setFont(new Font("Viner Hand ITC", 0, (int) (tamPant.width * 0.012)));
        botonSalir.setForeground(Color.white);
        botonSalir.setBackground(new Color(100, 13, 13));
        botonSalir.setBorder(new LineBorder(Color.black, 2));
        botonSalir.setFocusPainted(false);
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        getContentPane().add(botonSalir);

        pack();
        BufferedImage imagen22 = null;

        try {
            imagen22 = ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Diario Fondo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono22 = new ImageIcon(imagen22.getScaledInstance((int) (tamPant.width * 0.9), (int) (tamPant.height * 0.83), Image.SCALE_SMOOTH));
        fondo.setIcon(icono22);
        fondo.setBounds((int) (tamPant.width * 0.06), (int) (tamPant.height * 0.1), (int) (tamPant.width * 0.9), (int) (tamPant.height * 0.9));
        getContentPane().add(fondo);

        // Definir dimensiones de los botones
        // Definir dimensiones de los botones
        int botonAnchoGrande = (int) (tamPant.width * 0.08);
        int botonAnchoNormal = (int) (tamPant.width * 0.07);
        int botonAlto = (int) (tamPant.height * 0.07);

// Espacio entre botones centrales (5 píxeles)
        int espacioEntreCentrales = 8;
// Espacio extra para separación de botones extremos (15 píxeles para que se note más)
        int espacioExtraExtremos = 8;

// Posiciones iniciales ajustadas
        int xInicial = (int) (tamPant.width * 0.117);
        int yPos = (int) (tamPant.height * 0.09);
        int xPos = xInicial;

// Botón 1: Investigacion (más grande, separado del grupo central)
        boton1 = new JButton();
        try {
            BufferedImage imagenBoton1 = ImageIO.read(new File("DatosAuxiliares/MarcadoresDiario/Victima.png"));
            if (imagenBoton1 != null) {
                ImageIcon iconoBoton1 = new ImageIcon(imagenBoton1.getScaledInstance(botonAnchoGrande, botonAlto, Image.SCALE_SMOOTH));
                boton1.setIcon(iconoBoton1);
            }
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen del botón 1: " + e.getMessage());
        }
        boton1.setToolTipText("Investigacion");
        boton1.setBounds(xPos, yPos, botonAnchoGrande, botonAlto);
        boton1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton1.setBorder(new LineBorder(Color.YELLOW, 2));
            }

            public void mouseExited(MouseEvent evt) {
                boton1.setBorder(null);
            }
        });
        boton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boton1ActionPerformed(evt);
            }
        });
        boton1.setFocusPainted(false);
        boton1.setContentAreaFilled(false);
        boton1.setBorderPainted(false);
        getContentPane().add(boton1);

// Actualizar posición X para el siguiente botón con espacio extra
        xPos += botonAnchoGrande + espacioExtraExtremos;

// Botón 2: Jefe
        boton2 = new JButton();
        try {
            BufferedImage imagenBoton2 = ImageIO.read(new File("DatosAuxiliares/MarcadoresDiario/capi.png"));
            if (imagenBoton2 != null) {
                ImageIcon iconoBoton2 = new ImageIcon(imagenBoton2.getScaledInstance(botonAnchoNormal, botonAlto, Image.SCALE_SMOOTH));
                boton2.setIcon(iconoBoton2);
            }
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen del botón 2: " + e.getMessage());
        }
        boton2.setToolTipText("Jefe");
        boton2.setBounds(xPos, yPos, botonAnchoNormal, botonAlto);
        boton2.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton2.setBorder(new LineBorder(Color.YELLOW, 2));
            }

            public void mouseExited(MouseEvent evt) {
                boton2.setBorder(null);
            }
        });
        boton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boton2ActionPerformed(evt);
            }
        });
        boton2.setFocusPainted(false);
        boton2.setContentAreaFilled(false);
        boton2.setBorderPainted(false);
        getContentPane().add(boton2);

// Actualizar posición X para siguiente botón
        xPos += botonAnchoNormal + espacioEntreCentrales;

// Botón 3: Policia
        boton3 = new JButton();
        try {
            BufferedImage imagenBoton3 = ImageIO.read(new File("DatosAuxiliares/MarcadoresDiario/Policia.png"));
            if (imagenBoton3 != null) {
                ImageIcon iconoBoton3 = new ImageIcon(imagenBoton3.getScaledInstance(botonAnchoNormal, botonAlto, Image.SCALE_SMOOTH));
                boton3.setIcon(iconoBoton3);
            }
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen del botón 3: " + e.getMessage());
        }
        boton3.setToolTipText("Policia");
        boton3.setBounds(xPos, yPos, botonAnchoNormal, botonAlto);
        boton3.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton3.setBorder(new LineBorder(Color.YELLOW, 2));
            }

            public void mouseExited(MouseEvent evt) {
                boton3.setBorder(null);
            }
        });
        boton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boton3ActionPerformed(evt);
            }
        });
        boton3.setFocusPainted(false);
        boton3.setContentAreaFilled(false);
        boton3.setBorderPainted(false);
        getContentPane().add(boton3);

// Actualizar posición X para siguiente botón
        xPos += botonAnchoNormal + espacioEntreCentrales;

// Botón 4: Dueño
        boton4 = new JButton();
        try {
            BufferedImage imagenBoton4 = ImageIO.read(new File("DatosAuxiliares/MarcadoresDiario/Dueno.png"));
            if (imagenBoton4 != null) {
                ImageIcon iconoBoton4 = new ImageIcon(imagenBoton4.getScaledInstance(botonAnchoNormal, botonAlto, Image.SCALE_SMOOTH));
                boton4.setIcon(iconoBoton4);
            }
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen del botón 4: " + e.getMessage());
        }
        boton4.setToolTipText("Dueño");
        boton4.setBounds(xPos, yPos, botonAnchoNormal, botonAlto);
        boton4.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton4.setBorder(new LineBorder(Color.YELLOW, 2));
            }

            public void mouseExited(MouseEvent evt) {
                boton4.setBorder(null);
            }
        });
        boton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boton4ActionPerformed(evt);
            }
        });
        boton4.setFocusPainted(false);
        boton4.setContentAreaFilled(false);
        boton4.setBorderPainted(false);
        getContentPane().add(boton4);

// Actualizar posición X para siguiente botón
        xPos += botonAnchoNormal + espacioEntreCentrales;

// Botón 5: Esposa
        boton5 = new JButton();
        try {
            BufferedImage imagenBoton5 = ImageIO.read(new File("DatosAuxiliares/MarcadoresDiario/Esposa.png"));
            if (imagenBoton5 != null) {
                ImageIcon iconoBoton5 = new ImageIcon(imagenBoton5.getScaledInstance(botonAnchoNormal, botonAlto, Image.SCALE_SMOOTH));
                boton5.setIcon(iconoBoton5);
            }
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen del botón 5: " + e.getMessage());
        }
        boton5.setToolTipText("Esposa");
        boton5.setBounds(xPos, yPos, botonAnchoNormal, botonAlto);
        boton5.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton5.setBorder(new LineBorder(Color.YELLOW, 2));
            }

            public void mouseExited(MouseEvent evt) {
                boton5.setBorder(null);
            }
        });
        boton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boton5ActionPerformed(evt);
            }
        });
        boton5.setFocusPainted(false);
        boton5.setContentAreaFilled(false);
        boton5.setBorderPainted(false);
        getContentPane().add(boton5);

// Actualizar posición X para siguiente botón
        xPos += botonAnchoNormal + espacioEntreCentrales;

// Botón 6: Secretaria
        boton6 = new JButton();
        try {
            BufferedImage imagenBoton6 = ImageIO.read(new File("DatosAuxiliares/MarcadoresDiario/Secretaria.png"));
            if (imagenBoton6 != null) {
                ImageIcon iconoBoton6 = new ImageIcon(imagenBoton6.getScaledInstance(botonAnchoNormal, botonAlto, Image.SCALE_SMOOTH));
                boton6.setIcon(iconoBoton6);
            }
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen del botón 6: " + e.getMessage());
        }
        boton6.setToolTipText("Secretaria");
        boton6.setBounds(xPos, yPos, botonAnchoNormal, botonAlto);
        boton6.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton6.setBorder(new LineBorder(Color.YELLOW, 2));
            }

            public void mouseExited(MouseEvent evt) {
                boton6.setBorder(null);
            }
        });
        boton6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boton6ActionPerformed(evt);
            }
        });
        boton6.setFocusPainted(false);
        boton6.setContentAreaFilled(false);
        boton6.setBorderPainted(false);
        getContentPane().add(boton6);

// Actualizar posición X para siguiente botón
        xPos += botonAnchoNormal + espacioEntreCentrales;

// Botón 7: Guia 1
        boton7 = new JButton();
        try {
            BufferedImage imagenBoton7 = ImageIO.read(new File("DatosAuxiliares/MarcadoresDiario/Guia.png"));
            if (imagenBoton7 != null) {
                ImageIcon iconoBoton7 = new ImageIcon(imagenBoton7.getScaledInstance(botonAnchoNormal, botonAlto, Image.SCALE_SMOOTH));
                boton7.setIcon(iconoBoton7);
            }
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen del botón 7: " + e.getMessage());
        }
        boton7.setToolTipText("Guia 1");
        boton7.setBounds(xPos, yPos, botonAnchoNormal, botonAlto);
        boton7.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton7.setBorder(new LineBorder(Color.YELLOW, 2));
            }

            public void mouseExited(MouseEvent evt) {
                boton7.setBorder(null);
            }
        });
        boton7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boton7ActionPerformed(evt);
            }
        });
        boton7.setFocusPainted(false);
        boton7.setContentAreaFilled(false);
        boton7.setBorderPainted(false);
        getContentPane().add(boton7);

// Actualizar posición X para siguiente botón
        xPos += botonAnchoNormal + espacioEntreCentrales;

// Botón 8: Guia 2
        boton8 = new JButton();
        try {
            BufferedImage imagenBoton8 = ImageIO.read(new File("DatosAuxiliares/MarcadoresDiario/Amante.png"));
            if (imagenBoton8 != null) {
                ImageIcon iconoBoton8 = new ImageIcon(imagenBoton8.getScaledInstance(botonAnchoNormal, botonAlto, Image.SCALE_SMOOTH));
                boton8.setIcon(iconoBoton8);
            }
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen del botón 8: " + e.getMessage());
        }
        boton8.setToolTipText("Guia 2");
        boton8.setBounds(xPos, yPos, botonAnchoNormal, botonAlto);
        boton8.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton8.setBorder(new LineBorder(Color.YELLOW, 2));
            }

            public void mouseExited(MouseEvent evt) {
                boton8.setBorder(null);
            }
        });
        boton8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boton8ActionPerformed(evt);
            }
        });
        boton8.setFocusPainted(false);
        boton8.setContentAreaFilled(false);
        boton8.setBorderPainted(false);
        getContentPane().add(boton8);

// Actualizar posición X para siguiente botón
        xPos += botonAnchoNormal + espacioEntreCentrales;

// Botón 9: Seguridad
        boton9 = new JButton();
        try {
            BufferedImage imagenBoton9 = ImageIO.read(new File("DatosAuxiliares/MarcadoresDiario/Seguridad.png"));
            if (imagenBoton9 != null) {
                ImageIcon iconoBoton9 = new ImageIcon(imagenBoton9.getScaledInstance(botonAnchoNormal, botonAlto, Image.SCALE_SMOOTH));
                boton9.setIcon(iconoBoton9);
            }
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen del botón 9: " + e.getMessage());
        }
        boton9.setToolTipText("Seguridad");
        boton9.setBounds(xPos, yPos, botonAnchoNormal, botonAlto);
        boton9.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton9.setBorder(new LineBorder(Color.YELLOW, 2));
            }

            public void mouseExited(MouseEvent evt) {
                boton9.setBorder(null);
            }
        });
        boton9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boton9ActionPerformed(evt);
            }
        });
        boton9.setFocusPainted(false);
        boton9.setContentAreaFilled(false);
        boton9.setBorderPainted(false);
        getContentPane().add(boton9);

// Actualizar posición X con espacio extra antes del último botón
        xPos += botonAnchoNormal + espacioExtraExtremos;

// Botón 10: Vagabundo - MÁS GRANDE (separado del grupo central)
        boton10 = new JButton();
        try {
            BufferedImage imagenBoton10 = ImageIO.read(new File("DatosAuxiliares/MarcadoresDiario/Vagabundo 2.png"));
            if (imagenBoton10 != null) {
                ImageIcon iconoBoton10 = new ImageIcon(imagenBoton10.getScaledInstance(botonAnchoGrande, botonAlto, Image.SCALE_SMOOTH));
                boton10.setIcon(iconoBoton10);
            }
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen del botón 10: " + e.getMessage());
        }
        boton10.setToolTipText("Vagabundo");
        boton10.setBounds(xPos, yPos, botonAnchoGrande, botonAlto);
        boton10.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton10.setBorder(new LineBorder(Color.YELLOW, 2));
            }

            public void mouseExited(MouseEvent evt) {
                boton10.setBorder(null);
            }
        });
        boton10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boton10ActionPerformed(evt);
            }
        });
        boton10.setFocusPainted(false);
        boton10.setContentAreaFilled(false);
        boton10.setBorderPainted(false);
        getContentPane().add(boton10);
    }
    private void boton1ActionPerformed(ActionEvent evt) {
        contenedor.removeAll();
        contenedor.add(jPanel1);

    }
    private void boton2ActionPerformed(ActionEvent evt) {
        contenedor.removeAll();
        contenedor.add(jPanel2);

    }

    private void boton3ActionPerformed(ActionEvent evt) {
        contenedor.removeAll();
        contenedor.add(jPanel3);

    }

    private void boton4ActionPerformed(ActionEvent evt) {
        contenedor.removeAll();
        contenedor.add(jPanel4);
    }

    private void boton5ActionPerformed(ActionEvent evt) {
        contenedor.removeAll();
        contenedor.add(jPanel5);
    }

    private void boton6ActionPerformed(ActionEvent evt) {
        contenedor.removeAll();
        contenedor.add(jPanel6);
    }

    private void boton7ActionPerformed(ActionEvent evt) {
        contenedor.removeAll();
        contenedor.add(jPanel7);
    }

    private void boton8ActionPerformed(ActionEvent evt) {
        contenedor.removeAll();
        contenedor.add(jPanel8);
    }

    private void boton9ActionPerformed(ActionEvent evt) {
        contenedor.removeAll();
        contenedor.add(jPanel9);
    }

    private void boton10ActionPerformed(ActionEvent evt) {
        contenedor.removeAll();
        contenedor.add(jPanel10);
    }

    public void crearInfoDiario(){
        Diario diario = Juego.getInstance().getPartidaActual().getJugador().getDiario();
        Informacion info = new Informacion("Detective");
        info.agregarDialogo("Esta muerto");

        diario.agregarInformacion(info);
    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DiarioInterfaz dialog = new DiarioInterfaz(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private JPanel contenedor;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea10;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextArea jTextArea7;
    private javax.swing.JTextArea jTextArea8;
    private javax.swing.JTextArea jTextArea9;
    private JTextArea registro1;
    private JLabel jLabelR1;
    private JLabel fondo;
    private JButton boton1;
    private JButton boton2;
    private JButton boton3;
    private JButton boton4;
    private JButton boton5;
    private JButton boton6;
    private JButton boton7;
    private JButton boton8;
    private JButton boton9;
    private JButton boton10;

}