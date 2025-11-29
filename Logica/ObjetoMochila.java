package Logica;

import javax.swing.*;

public class ObjetoMochila
{
    private String nombre;
    private String descripcion;
    private ImageIcon foto;

    public ObjetoMochila(String nombre, String descripcion, ImageIcon foto)
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ImageIcon getFoto() {
        return foto;
    }

    public void setFoto(ImageIcon foto) {
        this.foto = foto;
    }
}
