package Logica;

import java.util.Iterator;
import java.util.LinkedList;

import cu.edu.cujae.ceis.tree.binary.BinaryTree;

public class Jugador 
{
    private static Jugador instancia;
    private String nombre;
    private BinaryTree<String> elecciones;
    private Telefono telefono;
    private Diario diario;
    private LinkedList<ObjetoEscenario> maletin;
    private Escenario escenarioActual;

    private Jugador()
    {
        this.nombre = "";
        this.elecciones = new BinaryTree<>();
        this.telefono = new Telefono();
        this.diario = new Diario();
        this.maletin = new LinkedList<ObjetoEscenario>();
    }

    public static Jugador getInstancia()
    {
        if(instancia == null)
        {
            instancia = new Jugador();
        }
        return instancia;
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

}