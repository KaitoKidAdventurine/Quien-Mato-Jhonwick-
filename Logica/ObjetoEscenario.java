package Logica;

import javax.swing.*;

public class ObjetoEscenario extends Interactuable {
    private ImageIcon imagen;
    private float posX;
    private float posY;
    private float tamAncho;
    private float tamLargo;
    private boolean encontrado;
    private boolean importante;
    private String descripcion;

    public ObjetoEscenario(String nom, boolean clikleable, ImageIcon imagen, float posX,
                           float posY, float tamAncho, float tamLargo,
                           boolean importante, String descripcion) {
        super(nom, clikleable);
        this.imagen = imagen;
        this.posX = posX;
        this.posY = posY;
        this.tamAncho = tamAncho;
        this.tamLargo = tamLargo;
        this.encontrado = false;  // Siempre empieza como no encontrado
        this.importante = importante;
        this.descripcion = descripcion;
    }


    public ImageIcon getImagen() {
        return imagen;
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getTamAncho() {
        return tamAncho;
    }

    public void setTamAncho(float tamAncho) {
        this.tamAncho = tamAncho;
    }

    public float getTamLargo() {
        return tamLargo;
    }

    public void setTamLargo(float tamLargo) {
        this.tamLargo = tamLargo;
    }

    public boolean getEncontrado() {
        return encontrado;
    }

    public void setEncontrado(boolean encontrado) {
        this.encontrado = encontrado;
    }

    public boolean getImportante() {
        return importante;
    }

    public void setImportante(boolean importante) {
        this.importante = importante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Metodo para cambiar a encontrado
    public void encontradoObjec() {
        encontrado = true;
    }

}
