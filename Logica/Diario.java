package Logica;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeSet;

public class Diario 
{
    private LinkedHashMap<String, String> entradas;
    private ArrayList<String> posiblesDecisiones;
    private TreeSet<String> limitacionesDecisiones;

    public Diario() 
    {
        this.entradas = new LinkedHashMap<>();
        this.posiblesDecisiones = new ArrayList<>();
        this.limitacionesDecisiones = new TreeSet<>();
    }

    public LinkedHashMap<String, String> getEntradas() 
    { 
        return entradas; 
    }
    public void setEntradas(LinkedHashMap<String, String> entradas) 
    { 
        this.entradas = entradas; 
    }

    public ArrayList<String> getPosiblesDecisiones() 
    { 
        return posiblesDecisiones; 
    }
    public void setPosiblesDecisiones(ArrayList<String> decisiones) 
    { 
        this.posiblesDecisiones = decisiones; 
    }

    public TreeSet<String> getLimitacionesDecisiones() 
    { 
        return limitacionesDecisiones; 
    }
    public void setLimitacionesDecisiones(TreeSet<String> limitaciones) 
    { 
        this.limitacionesDecisiones = limitaciones; 
    }

    public void agregarPartida() {}
    public void mostrarProgreso() {}
}