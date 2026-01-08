package Logica;

import DatosAuxiliaresLogica.Datos;

import javax.swing.*;
import java.util.Iterator;
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
    private LinkedList<Datos> textoImport;


    public Dialogo(String texto, String personaje, ImageIcon imagenPersonaje, boolean revelable)
    {
        this.texto = texto;
        this.personaje = personaje;
        this.opciones = new LinkedList<>();
        this.icono = imagenPersonaje;
        this.revelable = revelable;
        textoImport = new LinkedList<Datos>();

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

    public LinkedList<Datos> getTextoImport() {
        return textoImport;
    }

    public void setTextoImport(LinkedList<Datos> textoImport) {
        this.textoImport = textoImport;
    }
    public void agregar(String nomNPC, String texto)
    {
        textoImport.add(new Datos(nomNPC, texto));
    }

    public void guardarEnDiario()
    {
        if (!textoImport.isEmpty())
        {
            Iterator<Datos> ID = textoImport.iterator();
            while (ID.hasNext())
            {
                Datos D = ID.next();
                Juego.getInstance().getPartidaActual().getJugador().getDiario().agregarDialogoImportante(D.getNomNPC(), D.getTextoImportante());
            }
        }
    }
}