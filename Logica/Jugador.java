package Logica;

import java.util.Iterator;
import java.util.LinkedList;

import cu.edu.cujae.ceis.tree.binary.BinaryTree;

import javax.swing.*;

public class Jugador 
{
    public String nombre;
    public BinaryTree<String> elecciones;
    public Telefono telefono;
    public Diario diario;
    public LinkedList<ObjetoEscenario> maletin;
    public Escenario escenarioActual;

    public Jugador()
    {
        // Aqui se le pone el nombre del jugador
        this.nombre = "";
        this.elecciones = new BinaryTree<>();
        this.telefono = new Telefono();
        this.diario = new Diario();
        this.maletin = new LinkedList<ObjetoEscenario>();
    }
    

    public String getNombre()
    { 
        return nombre; 
    }
    public void setNombre(String nombre) 
    { 
        this.nombre = nombre; 
    }

    public BinaryTree<String> getElecciones() 
    { 
        return elecciones; 
    }
    public void setElecciones(BinaryTree<String> elecciones) 
    { 
        this.elecciones = elecciones; 
    }

    public Telefono getTelefono() 
    { 
        return telefono; 
    }
    public void setTelefono(Telefono telefono) 
    { 
        this.telefono = telefono; 
    }

    public Diario getDiario()
    {
        return diario;
    }
    public void setDiario(Diario diario)
    {
        this.diario = diario;
    }

    public Escenario getEscenarioActual() {
        return escenarioActual;
    }

    public void setEscenarioActual(Escenario escenarioActual) {
        this.escenarioActual = escenarioActual;
    }


    // Metodos
    public void agregarAlMaletin(ObjetoEscenario o)
    {
        maletin.add(o);
    }

    public LinkedList<ObjetoEscenario> getMaletin() {
        return maletin;
    }

    public void setMaletin(LinkedList<ObjetoEscenario> maletin) {
        this.maletin = maletin;
    }
}