package Interfaz.MiniJuego;

public class ObjetoMinijuego extends javax.swing.JButton{
    private String nombreObjeto;

    public ObjetoMinijuego(String nombre){
        this.nombreObjeto = nombre;
    }

    public String getNombreObjeto() {
        return nombreObjeto;
    }

    public void setNombreObjeto(String nombreObjeto) {
        this.nombreObjeto = nombreObjeto;
    }
}