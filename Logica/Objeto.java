package Logica;

import java.util.Deque;
import java.util.ArrayDeque;

public class Objeto extends Interactuable 
{
    private String tipo;
    private boolean recogible;
    private Deque<String> historia;

    public Objeto(String nombre, String descripcion, boolean intercambiable, String tipo,
                  boolean recogible) {
        super(nombre, descripcion, intercambiable);
        this.tipo = tipo;
        this.recogible = recogible;
        this.historia = new ArrayDeque<>();
    }

    public String getTipo() 
    { 
        return tipo; 
    }
    public void setTipo(String tipo) {
         
        this.tipo = tipo; 
    }

    public boolean isRecogible() {
         return recogible; 
        }
    public void setRecogible(boolean recogible) 
    { 
        this.recogible = recogible; 
    }

    public Deque<String> getHistoria() 
    { 
        return historia; 
    }

    public void recoger() {}

    @Override
    public void interactuar() {}
    @Override
    public String obtenerInformacion() { return descripcion; }
}
