package DatosAuxiliaresLogica;

import javax.swing.*;
import java.io.Serializable;

public class Fondos implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String nombre;
    private ImageIcon imagen;


    public Fondos(String nombre, ImageIcon imagen)
    {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }
}
