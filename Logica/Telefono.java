package Logica;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Telefono {
    private LinkedList<String> contactos;
    private Queue<String> mensajesRecibidos;
    private Stack<String> llamadasRealizadas;

    public Telefono() 
    {
        this.contactos = new LinkedList<String>();
        this.mensajesRecibidos = new LinkedList<>();
        this.llamadasRealizadas = new Stack<>();
    }

    public LinkedList<String> getContactos() 
    { 
        return contactos; 
    }
    public Queue<String> getMensajesRecibidos() 
    { 
        return mensajesRecibidos; 
    }
    public Stack<String> getLlamadasRealizadas() 
    {
        return llamadasRealizadas; 
    }

    public void llamar(String contacto) 
    {
        llamadasRealizadas.push(contacto);
    }

    public void enviarMensaje(String mensaje) 
    {
        mensajesRecibidos.offer(mensaje);
    }

    public void revisarMensaje() {}
}
