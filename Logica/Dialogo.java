package Logica;

import javax.swing.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Dialogo
{
    private String texto;
    private String personaje;
    private LinkedList<String> opciones;
    private ImageIcon icono;
    private boolean revelable;

    public Dialogo(String texto, String personaje, ImageIcon imagenPersonaje, boolean revelable)
    {
        this.texto = texto;
        this.personaje = personaje;
        this.opciones = new LinkedList<>();
        this.icono = imagenPersonaje;
        this.revelable = revelable;
    }

    public String getTexto()
    {
        return texto;
    }
    public void setTexto(String texto)
    {
        this.texto = texto;
    }

    public String getPersonaje()
    {
        return personaje;
    }
    public void setPersonaje(String tipo)
    {
        this.personaje = tipo;
    }

    public LinkedList<String> getOpciones()
    {
        return opciones;
    }
    public void setOpciones(LinkedList<String> opciones) { this.opciones = opciones; }

    public ImageIcon getIcono() {
        return icono;
    }

    public void setIcono(ImageIcon icono) {
        this.icono = icono;
    }

    public boolean isRevelable() { return revelable; }
    public void setRevelable(boolean revelable) { this.revelable = revelable; }
}