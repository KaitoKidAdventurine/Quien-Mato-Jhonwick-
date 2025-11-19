package Logica;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Dialogo 
{
    private String texto;
    private String tipo;
    private LinkedHashSet<String> opciones;
    private Queue<String> respuestas;

    public Dialogo(String texto, String tipo) 
    {
        this.texto = texto;
        this.tipo = tipo;
        this.opciones = new LinkedHashSet<>();
        this.respuestas = new LinkedList<>();
    }

    public String getTexto() 
    { 
        return texto; 
    }
    public void setTexto(String texto) 
    { 
        this.texto = texto; 
    }

    public String getTipo() 
    { 
        return tipo; 
    }
    public void setTipo(String tipo) 
    {
        this.tipo = tipo; 
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
}