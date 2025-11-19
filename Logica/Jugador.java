package Logica;

import java.util.LinkedHashSet;
import cu.edu.cujae.ceis.tree.binary.BinaryTree;

public class Jugador 
{
    private String nombre;
    private BinaryTree<String> elecciones;
    private Mapa mapa;
    private Telefono telefono;
    private Diario diario;

    public Jugador(String nombre) 
    {
        this.nombre = nombre;
        this.elecciones = new BinaryTree<>();
        this.mapa = new Mapa();
        this.telefono = new Telefono();
        this.diario = new Diario();
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

    public Mapa getMapa() 
    { 
        return mapa; 
    }
    public void setMapa(Mapa mapa) 
    { 
        this.mapa = mapa; 
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

    public void mover() {}
    public void interactuar() {}
    public void tomarDecision() {}
}