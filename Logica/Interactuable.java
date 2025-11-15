package Logica;

public abstract class Interactuable {
     
    protected String nombre;
    protected String descripcion;
    protected boolean Interactuable;

    public Interactuable(String nombre, String descripcion, boolean Interactuable) 
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.Interactuable = Interactuable;
    }

    public String getNombre() 
    { 
        return nombre; 
    }

    public void setNombre(String nombre) 
    { 
        this.nombre = nombre; 
    }

    public String getDescripcion() 
    { 
        return descripcion; 
    }
    public void setDescripcion(String descripcion) 
    { 
        this.descripcion = descripcion; 
    }

    public boolean getInteractuable() 
    { 
        return Interactuable; 
    }
    public void setInteractuable(boolean Interactuable) 
    { 
        this.Interactuable = Interactuable; 
    }

    public abstract void interactuar();
    public abstract String obtenerInformacion();
}
}
