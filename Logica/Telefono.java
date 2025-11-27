package Logica;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// Falta lo de poder llamar al jefe. Que asumo que sera un arbol
public class Telefono {

    private ImageIcon fondoDePantalla;
    private ArrayList<ImageIcon> fondos;

    public Telefono() {
        this.fondos = new ArrayList<ImageIcon>();
    }

    public ImageIcon getFondoDePantalla() {
        return fondoDePantalla;
    }

    public void setFondoDePantalla(ImageIcon fondoDePantalla) {
        this.fondoDePantalla = fondoDePantalla;
    }

    public ArrayList<ImageIcon> getFondos() {
        return fondos;
    }

    public void setFondos(ArrayList<ImageIcon> fondos) {
        this.fondos = fondos;
    }




    public void agregarFondos(ImageIcon i)
    {
        fondos.add(i);
    }

    public void bajarVolumen()
    {
        Reproductor reproductor = Reproductor.getInstancia();
        reproductor.bajarVolumen();
    }

    public void subirVolumen()
    {
        Reproductor reproductor = Reproductor.getInstancia();
        reproductor.subirVolumen();
    }

    public void cambiarMusicaNombre(String nombre)
    {
        Reproductor reproductor = Reproductor.getInstancia();
        reproductor.cambiarMusicaNombre(nombre);
    }
    public void desactivarMusica()
    {
        Reproductor reproductor = Reproductor.getInstancia();
        reproductor.desactivarMusica();
    }
    public void activarMusica()
    {
        Reproductor reproductor = Reproductor.getInstancia();
        reproductor.activarMusica();
    }



}
