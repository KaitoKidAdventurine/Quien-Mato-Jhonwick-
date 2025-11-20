package Logica;

import javax.swing.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Dialogo 
{
    private String texto;
    private String personaje;
    private LinkedHashSet<String> opciones;
    private Queue<String> respuestas;
    private ImageIcon icono;
    public Dialogo(String texto, String personaje, ImageIcon imagenPersonaje )
    {
        this.texto = texto;
        this.personaje = personaje;
        this.opciones = new LinkedHashSet<>();
        this.respuestas = new LinkedList<>();
        this.icono= imagenPersonaje;
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

    public LinkedHashSet<String> getOpciones()
    {
        return opciones;
    }
    public Queue<String> getRespuestas()
    {
        return respuestas;
    }

    public void elegirOpcion(String opcion)
    {
        respuestas.offer(opcion);
    }

    public ImageIcon getIcono() {
        return icono;
    }

    public void setIcono(ImageIcon icono) {
        this.icono = icono;
    }
}