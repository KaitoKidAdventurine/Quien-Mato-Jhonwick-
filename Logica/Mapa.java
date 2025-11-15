package Logica;

import java.util.LinkedHashSet;

public class Mapa {
    private Graph grafoEscenarios;
    private LinkedHashSet<String> ubicacionActual;

    public Mapa(Graph grafoEscenarios, LinkedHashSet<String> ubicacionActual) 
    {
        this.grafoEscenarios = grafoEscenarios;
        this.ubicacionActual = ubicacionActual;
    }

    public Graph getGrafoEscenarios() 
    { 
        return grafoEscenarios; 
    }
    public void setGrafoEscenarios(Graph grafoEscenarios) 
    { 
        this.grafoEscenarios = grafoEscenarios; 
    }

    public LinkedHashSet<String> getUbicacionActual() 
    { 
        return ubicacionActual; 
    }
    public void setUbicacionActual(LinkedHashSet<String> ubicacionActual) 
    {
        this.ubicacionActual = ubicacionActual; 
    }

    public void cambiarEscenario() {}
    public void obtenerRuta() {}
    public void mostrarMapa() {}
}
