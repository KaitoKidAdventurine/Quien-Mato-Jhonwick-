package Interfaz.Cinematicas;

import DatosAuxiliaresLogica.UnionInterfaces;
import Logica.Reproductor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CreditosFinales extends JPanel {
     private  Dimension tamPant;
   private JLabel textoEnPantalla1;
    private JLabel textoEnPantalla2;
    private JLabel fondo;
    private Timer timer;
    private TimerTask tarea;
    private Timer timer2;
    private TimerTask tarea2;
    private ArrayList<String> cabecera;
    private ArrayList<String> cuerpos;
    private  int cabezaActual;
    private  boolean repetible;
private boolean fin;
    private int desfile;

    public CreditosFinales (){
        Reproductor reproductor = Reproductor.getInstancia();
        reproductor.agregarReproucirCancionDelFinal();
        tamPant = Toolkit.getDefaultToolkit().getScreenSize();
        cabezaActual=0;
        fin = false;
        fondo = new JLabel();
        repetible = true;
        timer = new Timer();
        tarea = new TimerTask() {
            @Override
            public void run() {
              ponerDesfile();
              revalidate();
              repaint();
            }
        };
        timer2 = new Timer();
        tarea2 = new TimerTask() {
            @Override
            public void run() {
               salirAlMenu();
            }
        };
        cabecera= crearCabeceras();
        cuerpos = crearCuerpos();
        setBounds(0, 0, tamPant.width,  tamPant.height);
        setBackground(Color.blue);
        setLayout(null);

        textoEnPantalla1 = new JLabel();
        textoEnPantalla1.setBounds((int) (tamPant.width*0.1),(int) (tamPant.getHeight()*0.2), (int) (tamPant.width*0.8),(int) (tamPant.getHeight()*0.2));
        textoEnPantalla1.setFont(new Font("Segoe UI", 0, (int) (tamPant.width*0.085)));
        textoEnPantalla1.setHorizontalAlignment(SwingConstants.CENTER);
        textoEnPantalla1.setVerticalAlignment(SwingConstants.CENTER);


        textoEnPantalla2 = new JLabel("Fin");
        textoEnPantalla2.setBounds(0,(int) (tamPant.getHeight()*0.41), tamPant.width,(int) (tamPant.getHeight()*0.2));
        textoEnPantalla2.setFont(new Font("Segoe UI", 0, (int) (tamPant.width*0.057)));
        textoEnPantalla2.setHorizontalAlignment(SwingConstants.CENTER);
        textoEnPantalla2.setVerticalAlignment(SwingConstants.CENTER);

        add(textoEnPantalla1);
        add(textoEnPantalla2);

        BufferedImage imagen = null;

        try {
            imagen = ImageIO.read(new File("DatosAuxiliares/Cinematica/Creditos.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon icono = new ImageIcon(imagen.getScaledInstance(tamPant.width, tamPant.height, Image.SCALE_SMOOTH));
        fondo.setIcon(icono);
        fondo.setBounds(0, 0, tamPant.width, tamPant.height);
        add(fondo);
        timer.scheduleAtFixedRate(tarea, 0, 21);
    }

    private void salirAlMenu() {
        UnionInterfaces.getInstance().setCerrarVentana(true);
    }

    private void ponerDesfile(){
        switch (desfile){
            case 0:
            case 292:
                if(repetible)
                textoEnPantalla1.setForeground(new Color(250, 250, 250, 0));
                textoEnPantalla2.setForeground(new Color(250, 250, 250, 0));
                break;
            case 4:
            case 288:
                if(repetible)
                textoEnPantalla1.setForeground(new Color(250, 250, 250, 20));
                textoEnPantalla2.setForeground(new Color(250, 250, 250, 20));
                break;
            case 8:
            case 284:
                if(repetible)
                textoEnPantalla1.setForeground(new Color(250, 250, 250, 40));
                textoEnPantalla2.setForeground(new Color(250, 250, 250, 40));
                break;
            case 12:
            case 280:
                if(repetible)
                textoEnPantalla1.setForeground(new Color(250, 250, 250, 60));
                textoEnPantalla2.setForeground(new Color(250, 250, 250, 60));
                break;
            case 16:
            case 276:
                if(repetible)
                textoEnPantalla1.setForeground(new Color(250, 250, 250, 80));
                textoEnPantalla2.setForeground(new Color(250, 250, 250, 80));
                break;
            case 20:
            case 272:
                if(repetible)
                textoEnPantalla1.setForeground(new Color(250, 250, 250, 100));
                textoEnPantalla2.setForeground(new Color(250, 250, 250, 100));
                break;
            case 24:
            case 268:
                if(repetible)
                textoEnPantalla1.setForeground(new Color(250, 250, 250, 120));
                textoEnPantalla2.setForeground(new Color(250, 250, 250, 120));
                break;
            case 28:
            case 264:
                if(repetible)
                textoEnPantalla1.setForeground(new Color(250, 250, 250, 140));
                textoEnPantalla2.setForeground(new Color(250, 250, 250, 140));
                break;
            case 32:
            case 260:
                if(repetible)
                textoEnPantalla1.setForeground(new Color(250, 250, 250, 160));
                textoEnPantalla2.setForeground(new Color(250, 250, 250, 160));
                break;
            case 36:
            case 256:
                if(repetible)
                textoEnPantalla1.setForeground(new Color(250, 250, 250, 180));
                textoEnPantalla2.setForeground(new Color(250, 250, 250, 180));
                break;
            case 40:
            case 252:
                if(repetible)
                textoEnPantalla1.setForeground(new Color(250, 250, 250, 200));
                textoEnPantalla2.setForeground(new Color(250, 250, 250, 200));
                break;
            case 44:
            case 248:
                if(repetible)
                textoEnPantalla1.setForeground(new Color(250, 250, 250, 220));
                textoEnPantalla2.setForeground(new Color(250, 250, 250, 220));
                break;
            case 48:
                if(repetible)
                textoEnPantalla1.setForeground(new Color(250, 250, 250, 240));
                textoEnPantalla2.setForeground(new Color(250, 250, 250, 240));
                revisarRepetible();
                if(fin) {
                    timer2.schedule(tarea2, 7000);
                    timer.cancel();
                }
                break;
            case 310:
                ponerSiguienteString();
                desfile=0;
                break;
            default:
                break;
        }
        desfile++;
    }

    private void revisarRepetible() {
        if(cabezaActual<cabecera.size()) {
            String cabeza = cabecera.get(cabezaActual);
            if (cabeza.equals(textoEnPantalla1.getText())) {
                repetible = false;
            } else
                repetible = true;
        }
    }

    private void ponerSiguienteString(){
        String cabeza = cabecera.get(cabezaActual);
        String cuerpo = cuerpos.get(cabezaActual);
       if(!cabeza.equals(textoEnPantalla1.getText())){
           textoEnPantalla1.setText(cabeza);
           repetible = true;
           if(textoEnPantalla1.getText().equals("Muchas gracias")){
               textoEnPantalla2.setBounds((int) (tamPant.width*0.1),(int) (tamPant.getHeight()*0.42), (int) (tamPant.width*0.8),(int) (tamPant.getHeight()*0.2));
               textoEnPantalla2.setFont(new Font("Segoe UI", 0, (int) (tamPant.width*0.085)));
           }
       }else {
           repetible = false;
       }
       if(!cuerpo.equals(" por jugar")) {
           textoEnPantalla2.setText(cuerpo);
       }else {
           textoEnPantalla2.setText(cuerpo);
           fin=true;
       }
       cabezaActual++;
    }
    private ArrayList<String> crearCabeceras(){
        ArrayList<String> cabeceras= new ArrayList<>();
        cabeceras.add("Director");
        cabeceras.add("Director");
        cabeceras.add("Backend");
        cabeceras.add("Backend");
        cabeceras.add("Backend");
        cabeceras.add("Visuales");
        cabeceras.add("Historia");
        cabeceras.add("Historia");
        cabeceras.add("Fronted");
        cabeceras.add("Musica");
        cabeceras.add("Muchas gracias");
        return cabeceras;
    }
    private ArrayList<String> crearCuerpos(){
        ArrayList<String> cuerpos= new ArrayList<>();
        cuerpos.add("Eriet Dario Armas Gonzales");
        cuerpos.add("Kevin Ronquillo Perez");
        cuerpos.add("Alberto Ramon Nogueira");
        cuerpos.add("Favio Hernandez Leal");
        cuerpos.add("Eriet Dario Armas Gonzales");
        cuerpos.add("Edel Lazaro Mejias Rodriguez");
        cuerpos.add("Yeilin Dignora de la Cruz Noriega");
        cuerpos.add("Kely Gonzales Baez");
        cuerpos.add("Kevin Ronquillo Perez");
        cuerpos.add("Eriet Dario Armas Gonzales");
        cuerpos.add(" por jugar");
        return cuerpos;
    }
}
