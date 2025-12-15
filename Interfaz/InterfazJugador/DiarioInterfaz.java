/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Interfaz.InterfazJugador;

import DatosAuxiliaresLogica.Informacion;
import Logica.Diario;
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
        informacion= Jugador.getInstancia().getDiario().getDialogosImportantes();

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

        getContentPane().setBackground(new Color(0, 0, 0, 0));
        setUndecorated(true);
        Rectangle nombre = new Rectangle((int) (tamPant.width *0.16), (int) (tamPant.height*0.05), (int) (tamPant.width*0.25), (int) (tamPant.height*0.07));
        Rectangle foto= new Rectangle((int) (tamPant.width *0.01), (int) (tamPant.height*0.03), (int) (tamPant.width*0.12), (int) (tamPant.height*0.12));
        Rectangle datos = new Rectangle((int) (tamPant.width *0.42), (int) (tamPant.height*0.15), (int) (tamPant.width*0.43), (int) (tamPant.height*0.53));
        Rectangle registros = new Rectangle((int) (tamPant.width *0.03), (int) (tamPant.height*0.17), (int) (tamPant.width*0.3), (int) (tamPant.height*0.4));
        Rectangle panel = new Rectangle(0, 0, (int) (tamPant.width*0.8), (int) (tamPant.height*0.78));
        Rectangle labelDatos = new Rectangle((int) (tamPant.width*0.42), (int) (tamPant.height*0.03), (int) (tamPant.width*0.32), (int) (tamPant.height*0.08));

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
        jPanel11 = new JPanel();
        jLabel21 = new JLabel();
        jLabel22 = new JLabel();
        jScrollPane11 = new JScrollPane();
        jTextArea11 = new JTextArea();
        fondo = new JLabel();

        contenedor = new JPanel();
        contenedor.setBackground(new Color(0, 0, 0, 0));
        contenedor.setBounds((int) (tamPant.width *0.14), (int) (tamPant.height*0.17), (int) (tamPant.width*0.8), (int) (tamPant.height*0.78));
        contenedor.setLayout(null);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        setBackground(new Color(0, 0, 0, 75));



       jPanel1.setBackground(new Color(0, 0, 0, 0));
       jPanel1.setBounds(panel);
       jPanel1.setLayout(null);

        jLabel1.setText("Victima");
        jLabel1.setBounds(nombre);
        jLabel1.setFont(new Font("Viner Hand ITC", Font.PLAIN, (int)(tamPant.width*0.016)));
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

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance((int) (tamPant.width*0.11), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
        jLabel2.setIcon(icono);
        jLabel2.setBorder(new LineBorder(Color.black, 2, true));
        jLabel2.setBackground(new Color(255, 255, 255, 30));
        jPanel1.add(jLabel2);


        jLabelR1.setText("Datos relacionados con la investigacion");
        jLabelR1.setBounds(labelDatos);
        jLabelR1.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.016)));
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
        Iterator<String> iterator1= info1.getListaDeDialogos().iterator();
        while(iterator1.hasNext()){
            String contenido = iterator1.next();
            if(!jTextArea1.getText().isEmpty()) {

                jTextArea1.setText(jTextArea1.getText() + "\n" + "·" + contenido);
            }else
                jTextArea1.setText("·" + contenido);
        }
        jTextArea1.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.013)));

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
        registro1.setText("   Datos  Generales: \nEdad:   32       Sexo: Masculino   \n" + "Ocupacion:   Economico \n" + "\n\n\n  Analisis de personalidad: \n"
        +"Timido, Reservado, Poco amigable, tranquilo");
        registro1.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.015)));
        registro1.setBounds(registros);
        jPanel1.add(registro1);

        contenedor.add(jPanel1);

        jPanel2.setBackground(new Color(0, 0, 0, 0));
        jPanel2.setLayout(null);
        jPanel2.setBounds(panel);

        jLabel4.setText("Jefe");
        jLabel4.setBounds(nombre);
        jLabel4.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.016)));
        jLabel4.setForeground(Color.black);
        jPanel2.add(jLabel4);

        jLabel3.setBounds(foto);
        jLabel3.setOpaque(true);
        BufferedImage imagen3 = null;

        try {
            imagen3 = ImageIO.read(new File("DatosAuxiliares/Personajes/Detective Reducido.png"));
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen: " + e.getMessage());
        }

        if (imagen3 != null) {
            ImageIcon icono3 = new ImageIcon(imagen3.getScaledInstance((int) (tamPant.width*0.11), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
            jLabel3.setIcon(icono3);
        }
        jLabel3.setBorder(new LineBorder(Color.black, 2, true));
        jLabel3.setBackground(new Color(255, 255, 255, 30));
        jPanel2.add(jLabel3);

        JLabel jLabelR2 = new JLabel();
        jLabelR2.setText("Datos relacionados con la investigacion");
        jLabelR2.setBounds(labelDatos);
        jLabelR2.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.016)));
        jLabelR2.setForeground(Color.black);
        jPanel2.add(jLabelR2);

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
        jTextArea2.setText("Información del Jefe");
        jTextArea2.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.013)));

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
        registro2.setText("   Datos  Generales: \nEdad:   45       Sexo: Masculino   \n" + "Ocupacion:   Jefe \n" + "\n\n\n  Analisis de personalidad: \n"
        +"Autoritario, estricto, responsable, serio, lider");
        registro2.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.015)));
        registro2.setBounds(registros);
        jPanel2.add(registro2);



        jPanel3.setBackground(new Color(0, 0, 0, 0));
        jPanel3.setLayout(null);
        jPanel3.setBounds(panel);
        jLabel6.setText("Policia");
        jLabel6.setBounds(nombre);
        jLabel6.setFont(new Font("Viner Hand ITC", Font.PLAIN, (int)(tamPant.width*0.016)));
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
            ImageIcon icono5 = new ImageIcon(imagen5.getScaledInstance((int) (tamPant.width*0.11), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
            jLabel5.setIcon(icono5);
        }
        jLabel5.setBorder(new LineBorder(Color.black, 2, true));
        jLabel5.setBackground(new Color(255, 255, 255, 30));
        jPanel3.add(jLabel5);

        JLabel jLabelR3 = new JLabel();
        jLabelR3.setText("Datos relacionados con la investigacion");
        jLabelR3.setBounds(labelDatos);
        jLabelR3.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.016)));
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
        jTextArea3.setText("Información del Policia");
        jTextArea3.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.013)));

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
        registro3.setText("   Datos  Generales: \nEdad:   35       Sexo: Masculino   \n" + "Ocupacion:   Policia \n" + "\n\n\n  Analisis de personalidad: \n"
        +"Justo, valiente, protector, honesto, disciplinado");
        registro3.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.015)));
        registro3.setBounds(registros);
        jPanel3.add(registro3);


        jPanel4.setBackground(new Color(0, 0, 0, 0));
        jPanel4.setLayout(null);
        jPanel4.setBounds(panel);

        jLabel8.setText("Dueño");
        jLabel8.setBounds(nombre);
        jLabel8.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.016)));
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
            ImageIcon icono7 = new ImageIcon(imagen7.getScaledInstance((int) (tamPant.width*0.11), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
            jLabel7.setIcon(icono7);
        }
        jLabel7.setBorder(new LineBorder(Color.black, 2, true));
        jLabel7.setBackground(new Color(255, 255, 255, 30));
        jPanel4.add(jLabel7);

        JLabel jLabelR4 = new JLabel();
        jLabelR4.setText("Datos relacionados con la investigacion");
        jLabelR4.setBounds(labelDatos);
        jLabelR4.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.016)));
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
        jTextArea4.setText("Información del Dueño");
        jTextArea4.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.013)));

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
        registro4.setText("   Datos  Generales: \nEdad:   50       Sexo: Masculino   \n" + "Ocupacion:   Dueño \n" + "\n\n\n  Analisis de personalidad: \n"
        +"Ambicioso, calculador, exitoso, dominante, materialista");
        registro4.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.015)));
        registro4.setBounds(registros);
        jPanel4.add(registro4);


        jPanel5.setBackground(new Color(0, 0, 0, 0));
        jPanel5.setLayout(null);
        jPanel5.setBounds(panel);

        jLabel10.setText("Esposa del dueño");
        jLabel10.setBounds(nombre);
        jLabel10.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.016)));
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
            ImageIcon icono9 = new ImageIcon(imagen9.getScaledInstance((int) (tamPant.width*0.11), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
            jLabel9.setIcon(icono9);
        }
        jLabel9.setBorder(new LineBorder(Color.black, 2, true));
        jLabel9.setBackground(new Color(255, 255, 255, 30));
        jPanel5.add(jLabel9);

        JLabel jLabelR5 = new JLabel();
        jLabelR5.setText("Datos relacionados con la investigacion");
        jLabelR5.setBounds(labelDatos);
        jLabelR5.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.016)));
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
        jTextArea5.setText("Información de la Esposa del dueño");
        jTextArea5.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.013)));

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
        registro5.setText("   Datos  Generales: \nEdad:   42       Sexo: Femenino   \n" + "Ocupacion:   Esposa \n" + "\n\n\n  Analisis de personalidad: \n"
        +"Elegante, sofisticada, celosa, manipuladora, orgullosa");
        registro5.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.015)));
        registro5.setBounds(registros);
        jPanel5.add(registro5);


        jPanel6.setBackground(new Color(0, 0, 0, 0));
        jPanel6.setLayout(null);
        jPanel6.setBounds(panel);

        jLabel12.setText("Secretaria");
        jLabel12.setBounds(nombre);
        jLabel12.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.016)));
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
            ImageIcon icono11 = new ImageIcon(imagen11.getScaledInstance((int) (tamPant.width*0.11), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
            jLabel11.setIcon(icono11);
        }
        jLabel11.setBorder(new LineBorder(Color.black, 2, true));
        jLabel11.setBackground(new Color(255, 255, 255, 30));
        jPanel6.add(jLabel11);

        JLabel jLabelR6 = new JLabel();
        jLabelR6.setText("Datos relacionados con la investigacion");
        jLabelR6.setBounds(labelDatos);
        jLabelR6.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.016)));
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
        jTextArea6.setText("Información de la Secretaria");
        jTextArea6.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.013)));

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
        registro6.setText("   Datos  Generales: \nEdad:   30       Sexo: Femenino   \n" + "Ocupacion:   Secretaria \n" + "\n\n\n  Analisis de personalidad: \n"
        +"Eficiente, discreta, observadora, leal, organizada");
        registro6.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.015)));
        registro6.setBounds(registros);
        jPanel6.add(registro6);


        jPanel7.setBackground(new Color(0, 0, 0, 0));
        jPanel7.setLayout(null);
        jPanel7.setBounds(panel);

        jLabel14.setText("Guia 1");
        jLabel14.setBounds(nombre);
        jLabel14.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.016)));
        jLabel14.setForeground(Color.black);
        jPanel7.add(jLabel14);

        jLabel13.setBounds(foto);
        jLabel13.setOpaque(true);
        BufferedImage imagen13 = null;

        try {
            imagen13 = ImageIO.read(new File("DatosAuxiliares/Personajes/Guia Reducido.png"));
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen: " + e.getMessage());
        }

        if (imagen13 != null) {
            ImageIcon icono13 = new ImageIcon(imagen13.getScaledInstance((int) (tamPant.width*0.11), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
            jLabel13.setIcon(icono13);
        }
        jLabel13.setBorder(new LineBorder(Color.black, 2, true));
        jLabel13.setBackground(new Color(255, 255, 255, 30));
        jPanel7.add(jLabel13);

        JLabel jLabelR7 = new JLabel();
        jLabelR7.setText("Datos relacionados con la investigacion");
        jLabelR7.setBounds(labelDatos);
        jLabelR7.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.016)));
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
        jTextArea7.setText("Información del Guia 1");
        jTextArea7.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.013)));

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
        registro7.setText("   Datos  Generales: \nEdad:   25       Sexo: Masculino   \n" + "Ocupacion:   Guia \n" + "\n\n\n  Analisis de personalidad: \n"
        +"Amigable, conocedor, paciente, comunicativo, servicial");
        registro7.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.015)));
        registro7.setBounds(registros);
        jPanel7.add(registro7);


        jPanel8.setBackground(new Color(0, 0, 0, 0));
        jPanel8.setLayout(null);
        jPanel8.setBounds(panel);

        jLabel16.setText("Guia 2");
        jLabel16.setBounds(nombre);
        jLabel16.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.016)));
        jLabel16.setForeground(Color.black);
        jPanel8.add(jLabel16);

        jLabel15.setBounds(foto);
        jLabel15.setOpaque(true);
        BufferedImage imagen15 = null;

        try {
            imagen15 = ImageIO.read(new File("DatosAuxiliares/Personajes/Amante Reducida.png"));
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen: " + e.getMessage());
        }

        if (imagen15 != null) {
            ImageIcon icono15 = new ImageIcon(imagen15.getScaledInstance((int) (tamPant.width*0.11), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
            jLabel15.setIcon(icono15);
        }
        jLabel15.setBorder(new LineBorder(Color.black, 2, true));
        jLabel15.setBackground(new Color(255, 255, 255, 30));
        jPanel8.add(jLabel15);

        JLabel jLabelR8 = new JLabel();
        jLabelR8.setText("Datos relacionados con la investigacion");
        jLabelR8.setBounds(labelDatos);
        jLabelR8.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.016)));
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
        jTextArea8.setText("Información del Guia 2");
        jTextArea8.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.013)));

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
        registro8.setText("   Datos  Generales: \nEdad:   27       Sexo: Femenino   \n" + "Ocupacion:   Guia \n" + "\n\n\n  Analisis de personalidad: \n"
        +"Energica, entusiasta, extrovertida, carismática, alegre");
        registro8.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.015)));
        registro8.setBounds(registros);
        jPanel8.add(registro8);


        jPanel9.setBackground(new Color(0, 0, 0, 0));
        jPanel9.setLayout(null);
        jPanel9.setBounds(panel);

        jLabel18.setText("Seguridad");
        jLabel18.setBounds(nombre);
        jLabel18.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.016)));
        jLabel18.setForeground(Color.black);
        jPanel9.add(jLabel18);

        jLabel17.setBounds(foto);
        jLabel17.setOpaque(true);
        BufferedImage imagen17 = null;

        try {
            imagen17 = ImageIO.read(new File("DatosAuxiliares/Personajes/Detective Reducido.png"));
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen: " + e.getMessage());
        }

        if (imagen17 != null) {
            ImageIcon icono17 = new ImageIcon(imagen17.getScaledInstance((int) (tamPant.width*0.11), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
            jLabel17.setIcon(icono17);
        }
        jLabel17.setBorder(new LineBorder(Color.black, 2, true));
        jLabel17.setBackground(new Color(255, 255, 255, 30));
        jPanel9.add(jLabel17);

        JLabel jLabelR9 = new JLabel();
        jLabelR9.setText("Datos relacionados con la investigacion");
        jLabelR9.setBounds(labelDatos);
        jLabelR9.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.016)));
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
        jTextArea9.setText("Información del Seguridad");
        jTextArea9.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.013)));

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
        registro9.setText("   Datos  Generales: \nEdad:   40       Sexo: Masculino   \n" + "Ocupacion:   Seguridad \n" + "\n\n\n  Analisis de personalidad: \n"
        +"Vigilante, fuerte, protector, serio, confiable");
        registro9.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.015)));
        registro9.setBounds(registros);
        jPanel9.add(registro9);


        jPanel10.setBackground(new Color(0, 0, 0, 0));
        jPanel10.setLayout(null);
        jPanel10.setBounds(panel);

        jLabel20.setText("Vagabundo");
        jLabel20.setBounds(nombre);
        jLabel20.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.016)));
        jLabel20.setForeground(Color.black);
        jPanel10.add(jLabel20);

        jLabel19.setBounds(foto);
        jLabel19.setOpaque(true);
        BufferedImage imagen19 = null;

        try {
            imagen19 = ImageIO.read(new File("DatosAuxiliares/Personajes/Detective Reducido.png"));
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen: " + e.getMessage());
        }

        if (imagen19 != null) {
            ImageIcon icono19 = new ImageIcon(imagen19.getScaledInstance((int) (tamPant.width*0.11), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
            jLabel19.setIcon(icono19);
        }
        jLabel19.setBorder(new LineBorder(Color.black, 2, true));
        jLabel19.setBackground(new Color(255, 255, 255, 30));
        jPanel10.add(jLabel19);

        JLabel jLabelR10 = new JLabel();
        jLabelR10.setText("Datos relacionados con la investigacion");
        jLabelR10.setBounds(labelDatos);
        jLabelR10.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.016)));
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
        jTextArea10.setText("Información del Vagabundo");
        jTextArea10.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.013)));

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
        registro10.setText("   Datos  Generales: \nEdad:   55       Sexo: Masculino   \n" + "Ocupacion:   Vagabundo \n" + "\n\n\n  Analisis de personalidad: \n"
        +"Misterioso, observador, solitario, sabio, marginal");
        registro10.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.015)));
        registro10.setBounds(registros);
        jPanel10.add(registro10);

        jPanel11.setBackground(new Color(0, 0, 0, 0));
        jPanel11.setLayout(null);
        jPanel11.setBounds(panel);

        jLabel22.setText("Victima");
        jLabel22.setBounds(nombre);
        jLabel22.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.016)));
        jLabel22.setForeground(Color.black);
        jPanel11.add(jLabel22);

        jLabel21.setBounds(foto);
        jLabel21.setOpaque(true);
        BufferedImage imagen21 = null;

        try {
            imagen21 = ImageIO.read(new File("DatosAuxiliares/Personajes/Victima Reducida.png"));
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen: " + e.getMessage());
        }

        if (imagen21 != null) {
            ImageIcon icono21 = new ImageIcon(imagen21.getScaledInstance((int) (tamPant.width*0.11), (int) (tamPant.height*0.13), Image.SCALE_SMOOTH));
            jLabel21.setIcon(icono21);
        }
        jLabel21.setBorder(new LineBorder(Color.black, 2, true));
        jLabel21.setBackground(new Color(255, 255, 255, 30));
        jPanel11.add(jLabel21);

        JLabel jLabelR11 = new JLabel();
        jLabelR11.setText("Datos relacionados con la investigacion");
        jLabelR11.setBounds(labelDatos);
        jLabelR11.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.016)));
        jLabelR11.setForeground(Color.black);
        jPanel11.add(jLabelR11);

        jTextArea11.setColumns(20);
        jTextArea11.setRows(5);
        jTextArea11.setBackground(new Color(0, 0, 0, 0));
        jTextArea11.setEnabled(false);
        jTextArea11.setFocusable(false);
        jTextArea11.setHighlighter(null);
        jTextArea11.setDisabledTextColor(Color.black);
        jTextArea11.setLineWrap(true);
        jTextArea11.setWrapStyleWord(true);
        jTextArea11.setEditable(false);
        jTextArea11.setText("Información de la Victima");
        jTextArea11.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.013)));

        jScrollPane11.setViewportView(jTextArea11);
        jScrollPane11.setBounds(datos);
        jScrollPane11.setOpaque(true);
        jScrollPane11.setBackground(new Color(0, 0, 0, 0));
        jScrollPane11.setBorder(null);
        jPanel11.add(jScrollPane11);

        JTextArea registro11 = new JTextArea();
        registro11.setColumns(20);
        registro11.setRows(5);
        registro11.setHighlighter(null);
        registro11.setFocusable(false);
        registro11.setDisabledTextColor(Color.black);
        registro11.setLineWrap(true);
        registro11.setWrapStyleWord(true);
        registro11.setEditable(false);
        registro11.setBackground(new Color(0, 0, 0, 0));
        registro11.setText("   Datos  Generales: \nEdad:   32       Sexo: Masculino   \n" + "Ocupacion:   Empresario \n" + "\n\n\n  Analisis de personalidad: \n"
        +"Carismático, ambicioso, popular, influyente, exitoso");
        registro11.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.015)));
        registro11.setBounds(registros);
        jPanel11.add(registro11);


       getContentPane().add(contenedor);
        botonSalir = new JButton("Salir");
        botonSalir.setBounds((int) (tamPant.width*0.9), (int) (tamPant.height*0.03), (int) (tamPant.width*0.08), (int) (tamPant.height*0.05));
        botonSalir.setFont(new Font("Viner Hand ITC", 0, (int)(tamPant.width*0.012)));
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

        ImageIcon icono22 = new ImageIcon(imagen22.getScaledInstance((int) (tamPant.width*0.9), (int) (tamPant.height*0.83), Image.SCALE_SMOOTH));
        fondo.setIcon(icono22);
        fondo.setBounds((int) (tamPant.width*0.06),(int) (tamPant.height*0.1), (int) (tamPant.width*0.9), (int) (tamPant.height*0.9));
        getContentPane().add(fondo);

        boton1 = new JButton("Investigacion");
        boton1.setFont(new Font("Segoe UI", 0, (int)(tamPant.width*0.009)));
        boton1.setBounds((int) (tamPant.width*0.13),(int) (tamPant.height*0.09), (int) (tamPant.width*0.08), (int) (tamPant.height*0.07));
        boton1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton1mouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                boton1MouseExited(evt);
            }
        });
        boton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boton1ActionPerformed(evt);
            }
        });
        boton1.setFocusPainted(false);
        getContentPane().add(boton1);

        boton2 = new JButton("Jefe");
        boton2.setFont(new Font("Segoe UI", 0, (int)(tamPant.width*0.009)));
        boton2.setBounds((int) (tamPant.width*0.22),(int) (tamPant.height*0.09), (int) (tamPant.width*0.06), (int) (tamPant.height*0.07));
        boton2.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton2mouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                boton2MouseExited(evt);
            }
        });
        boton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boton2ActionPerformed(evt);
            }
        });
        boton2.setFocusPainted(false);
        getContentPane().add(boton2);

        boton3 = new JButton("Policia");
        boton3.setFont(new Font("Segoe UI", 0, (int)(tamPant.width*0.009)));
        boton3.setBounds((int) (tamPant.width*0.29),(int) (tamPant.height*0.09), (int) (tamPant.width*0.06), (int) (tamPant.height*0.07));
        boton3.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton3mouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                boton3MouseExited(evt);
            }
        });
        boton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boton3ActionPerformed(evt);
            }
        });
        boton3.setFocusPainted(false);
        getContentPane().add(boton3);

        boton4 = new JButton("Dueño");
        boton4.setFont(new Font("Segoe UI", 0, (int)(tamPant.width*0.009)));
        boton4.setBounds((int) (tamPant.width*0.36),(int) (tamPant.height*0.09), (int) (tamPant.width*0.06), (int) (tamPant.height*0.07));
        boton4.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton4mouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                boton4MouseExited(evt);
            }
        });
        boton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boton4ActionPerformed(evt);
            }
        });
        boton4.setFocusPainted(false);
        getContentPane().add(boton4);

        boton5 = new JButton("Esposa");
        boton5.setFont(new Font("Segoe UI", 0, (int)(tamPant.width*0.009)));
        boton5.setBounds((int) (tamPant.width*0.43),(int) (tamPant.height*0.09), (int) (tamPant.width*0.06), (int) (tamPant.height*0.07));
        boton5.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton5mouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                boton5MouseExited(evt);
            }
        });
        boton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boton5ActionPerformed(evt);
            }
        });
        boton5.setFocusPainted(false);
        getContentPane().add(boton5);

        boton6 = new JButton("Secretaria");
        boton6.setFont(new Font("Segoe UI", 0, (int)(tamPant.width*0.009)));
        boton6.setBounds((int) (tamPant.width*0.5),(int) (tamPant.height*0.09), (int) (tamPant.width*0.06), (int) (tamPant.height*0.07));
        boton6.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton6mouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                boton6MouseExited(evt);
            }
        });
        boton6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boton6ActionPerformed(evt);
            }
        });
        boton6.setFocusPainted(false);
        getContentPane().add(boton6);

        boton7 = new JButton("Guia 1");
        boton7.setFont(new Font("Segoe UI", 0, (int)(tamPant.width*0.009)));
        boton7.setBounds((int) (tamPant.width*0.57),(int) (tamPant.height*0.09), (int) (tamPant.width*0.06), (int) (tamPant.height*0.07));
        boton7.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton7mouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                boton7MouseExited(evt);
            }
        });
        boton7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boton7ActionPerformed(evt);
            }
        });
        boton7.setFocusPainted(false);
        getContentPane().add(boton7);

        boton8 = new JButton("Guia 2");
        boton8.setFont(new Font("Segoe UI", 0, (int)(tamPant.width*0.009)));
        boton8.setBounds((int) (tamPant.width*0.64),(int) (tamPant.height*0.09), (int) (tamPant.width*0.06), (int) (tamPant.height*0.07));
        boton8.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton8mouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                boton8MouseExited(evt);
            }
        });
        boton8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boton8ActionPerformed(evt);
            }
        });
        boton8.setFocusPainted(false);
        getContentPane().add(boton8);

        boton9 = new JButton("Seguridad");
        boton9.setFont(new Font("Segoe UI", 0, (int)(tamPant.width*0.009)));
        boton9.setBounds((int) (tamPant.width*0.71),(int) (tamPant.height*0.09), (int) (tamPant.width*0.06), (int) (tamPant.height*0.07));
        boton9.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton9mouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                boton9MouseExited(evt);
            }
        });
        boton9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boton9ActionPerformed(evt);
            }
        });
        boton9.setFocusPainted(false);
        getContentPane().add(boton9);

        boton10 = new JButton("Vagabundo");
        boton10.setFont(new Font("Segoe UI", 0, (int)(tamPant.width*0.009)));
        boton10.setBounds((int) (tamPant.width*0.78),(int) (tamPant.height*0.09), (int) (tamPant.width*0.08), (int) (tamPant.height*0.07));
        boton10.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton10mouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                boton10MouseExited(evt);
            }
        });
        boton10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boton10ActionPerformed(evt);
            }
        });
        boton10.setFocusPainted(false);
        getContentPane().add(boton10);


    }
    private void boton1ActionPerformed(ActionEvent evt) {
        contenedor.removeAll();
        contenedor.add(jPanel1);

    }
    private void boton1mouseEntered(MouseEvent evt) {

    }
    private void boton1MouseExited(MouseEvent evt) {

    }

    private void boton2ActionPerformed(ActionEvent evt) {
        contenedor.removeAll();
        contenedor.add(jPanel2);

    }

    private void boton2mouseEntered(MouseEvent evt) {

    }

    private void boton2MouseExited(MouseEvent evt) {

    }

    private void boton3ActionPerformed(ActionEvent evt) {
        contenedor.removeAll();
        contenedor.add(jPanel3);

    }

    private void boton3mouseEntered(MouseEvent evt) {

    }

    private void boton3MouseExited(MouseEvent evt) {

    }

    private void boton4ActionPerformed(ActionEvent evt) {
        contenedor.removeAll();
        contenedor.add(jPanel4);
    }

    private void boton4mouseEntered(MouseEvent evt) {

    }

    private void boton4MouseExited(MouseEvent evt) {

    }

    private void boton5ActionPerformed(ActionEvent evt) {
        contenedor.removeAll();
        contenedor.add(jPanel5);
    }

    private void boton5mouseEntered(MouseEvent evt) {

    }

    private void boton5MouseExited(MouseEvent evt) {

    }

    private void boton6ActionPerformed(ActionEvent evt) {
        contenedor.removeAll();
        contenedor.add(jPanel6);
    }

    private void boton6mouseEntered(MouseEvent evt) {

    }

    private void boton6MouseExited(MouseEvent evt) {

    }

    private void boton7ActionPerformed(ActionEvent evt) {
        contenedor.removeAll();
        contenedor.add(jPanel7);
    }

    private void boton7mouseEntered(MouseEvent evt) {

    }

    private void boton7MouseExited(MouseEvent evt) {

    }

    private void boton8ActionPerformed(ActionEvent evt) {
        contenedor.removeAll();
        contenedor.add(jPanel8);
    }

    private void boton8mouseEntered(MouseEvent evt) {

    }

    private void boton8MouseExited(MouseEvent evt) {

    }

    private void boton9ActionPerformed(ActionEvent evt) {
        contenedor.removeAll();
        contenedor.add(jPanel9);
    }

    private void boton9mouseEntered(MouseEvent evt) {

    }

    private void boton9MouseExited(MouseEvent evt) {

    }

    private void boton10ActionPerformed(ActionEvent evt) {
        contenedor.removeAll();
        contenedor.add(jPanel10);
    }

    private void boton10mouseEntered(MouseEvent evt) {

    }

    private void boton10MouseExited(MouseEvent evt) {

    }

    public void crearInfoDiario(){
        Diario diario = Jugador.getInstancia().getDiario();
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
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
    private javax.swing.JScrollPane jScrollPane11;
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
    private javax.swing.JTextArea jTextArea11;
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
    private JButton boton11;
}
