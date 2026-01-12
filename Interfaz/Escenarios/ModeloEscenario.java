package Interfaz.Escenarios;

import DatosAuxiliaresLogica.UnionInterfaces;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ModeloEscenario extends JFrame {
    public Timer temporizador;
    public TimerTask task;
    public ModeloEscenario(){
        temporizador = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                dispose();
            }
        };

        BufferedImage imagenCursor =null;
        try {
            imagenCursor =  ImageIO.read(new File("DatosAuxiliares/OjetosInterfaz/Cursor.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(imagenCursor, new Point(20, 0), "Cursor detective");
        setCursor(cursor);
      //  UnionInterfaces.getInstance().setFrameActual((JFrame) this);
    }



}
