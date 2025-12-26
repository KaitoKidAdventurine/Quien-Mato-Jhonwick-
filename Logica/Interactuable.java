package Logica;

public abstract class Interactuable {

    protected String nombre;
    protected boolean clikleable;

    public Interactuable(String nombre, boolean clikleable)
    {
        this.nombre = nombre;
        this.clikleable = clikleable;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public boolean getClikleable()
    {
        return clikleable;
    }
    public void setClikleable(boolean clikleable)
    {
        this.clikleable = clikleable;
    }


}

