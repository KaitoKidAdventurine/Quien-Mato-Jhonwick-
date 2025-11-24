package Logica;

import DatosAuxiliaresLogica.Informacion;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.TreeSet;
import DatosAuxiliaresLogica.Informacion;

public class Diario 
{
    private LinkedList<Informacion> entradas;

    public Diario(LinkedList<Informacion> entradas) {
        this.entradas = entradas;
    }

    public LinkedList<Informacion> getEntradas() {
        return entradas;
    }

    public void setEntradas(LinkedList<Informacion> entradas) {
        this.entradas = entradas;
    }

    /*
    public String dialogoImportante()
    {

    }
    public void agregarPartida() {}
    public void mostrarProgreso() {}
    * */
}