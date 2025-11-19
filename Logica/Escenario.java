package Logica;

import java.util.ArrayList;

public class Escenario {
    private String nombre;
    private String descripcion;
    private boolean investigado;
    private ArrayList<Interactuable> interactuables;

    public Escenario(String nombre, String descripcion, boolean investigado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.investigado = investigado;
        this.interactuables = new ArrayList<>();
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public boolean isInvestigado() { return investigado; }
    public void setInvestigado(boolean investigado) { this.investigado = investigado; }

    public ArrayList<Interactuable> getInteractuables() { return interactuables; }
    public void setInteractuables(ArrayList<Interactuable> interactuables) { this.interactuables = interactuables; }

    public void investigar() {}
    public void agregarInteractuable(Interactuable i) {
        interactuables.add(i);
    }
}
