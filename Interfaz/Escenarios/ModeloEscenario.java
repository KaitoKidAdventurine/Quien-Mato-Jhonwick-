package Interfaz.Escenarios;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class ModeloEscenario extends JFrame {
private Timer temporizador;
private TimerTask task;
    public ModeloEscenario(){
        temporizador = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                dispose();
            }
        };
    }

    public void cerrarEscenario(){
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
       temporizador.schedule(task, 1000);
    }
}
