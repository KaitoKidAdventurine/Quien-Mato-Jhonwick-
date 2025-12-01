package Logica;

import DatosAuxiliaresLogica.Fondos;
import javax.swing.*;
import java.util.ArrayList;


public class Telefono {

    private ImageIcon fondoDePantalla;
    private ArrayList<Fondos> fondos;
    private int fondoActual;
    private int indiceDeCambio;


    // Constructor
    public Telefono() {
        this.fondos = new ArrayList<Fondos>();
        inicializarFondos();
        fondoActual = 0;
        fondoDePantalla = fondos.get(fondoActual).getImagen();
    }


    // Get y Set
    public ImageIcon getFondoDePantalla() {
        return fondoDePantalla;
    }

    public void setFondoDePantalla(ImageIcon fondoDePantalla) {
        this.fondoDePantalla = fondoDePantalla;
    }

    public ArrayList<Fondos> getFondos() {
        return fondos;
    }

    public void setFondos(ArrayList<Fondos> fondos) {
        this.fondos = fondos;
    }



    // Metodos
    public void agregarFondos(Fondos f)
    {
        fondos.add(f);
    }

    public void bajarVolumen()
    {
        Reproductor reproductor = Reproductor.getInstancia();
        reproductor.bajarVolumen();
    }

    public void subirVolumen()
    {
        Reproductor reproductor = Reproductor.getInstancia();
        reproductor.subirVolumen();
    }

    public void cambiarMusicaNombre(String nombre)
    {
        Reproductor reproductor = Reproductor.getInstancia();
        reproductor.cambiarMusicaNombre(nombre);
    }

    public void desactivarMusica()
    {
        Reproductor reproductor = Reproductor.getInstancia();
        reproductor.desactivarMusica();
    }

    public void activarMusica()
    {
        Reproductor reproductor = Reproductor.getInstancia();
        reproductor.activarMusica();
    }

    public void inicializarFondos()
    {
        agregarFondos(new Fondos("Auto",new ImageIcon("DatosAuxiliares/Fondos De Pantalla Telefono/Auto.jpg")));
        agregarFondos(new Fondos("Carretera",new ImageIcon("DatosAuxiliares/Fondos De Pantalla Telefono/Carretera.jpg")));
        agregarFondos(new Fondos("Cielo Estrellado",new ImageIcon("DatosAuxiliares/Fondos De Pantalla Telefono/Cielo Estrellado.jpeg")));
        agregarFondos(new Fondos("Gatos",new ImageIcon("DatosAuxiliares/Fondos De Pantalla Telefono/Gatos.jpg")));
        agregarFondos(new Fondos("Luna",new ImageIcon("DatosAuxiliares/Fondos De Pantalla Telefono/Luna")));
        agregarFondos(new Fondos("Luna LLena",new ImageIcon("DatosAuxiliares/Fondos De Pantalla Telefono/Luna LLena.jpeg")));
        agregarFondos(new Fondos("Luna Rompiendo El Cielo",new ImageIcon("DatosAuxiliares/Fondos De Pantalla Telefono/Luna Rompiendo El Cielo.jpeg")));
        agregarFondos(new Fondos("Montannas",new ImageIcon("DatosAuxiliares/Fondos De Pantalla Telefono/Montannas.jpg")));
        agregarFondos(new Fondos("Naruto",new ImageIcon("DatosAuxiliares/Fondos De Pantalla Telefono/Naruto.jpg")));
        agregarFondos(new Fondos("Noche Montannas",new ImageIcon("DatosAuxiliares/Fondos De Pantalla Telefono/Noche Montannas")));
        agregarFondos(new Fondos("Nubes y Montannas",new ImageIcon("DatosAuxiliares/Fondos De Pantalla Telefono/Nubes y Montannas.jpg")));
        agregarFondos(new Fondos("Torre Eiffel",new ImageIcon("DatosAuxiliares/Fondos De Pantalla Telefono/Torre Eiffel.jpg")));
    }

    public void buscarPorNombre(String nom)
    {
        try {
            boolean encontrado = false;
            for (int i = 0; i < fondos.size() && !encontrado; i++) {
                if (fondos.get(i).getNombre().equals(nom)) {
                    encontrado = true;
                    fondoDePantalla = fondos.get(i).getImagen();
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void cambiarFondoIndice(int i) {
        try {
            fondoActual = i;
            indiceDeCambio = fondoActual;
            fondoDePantalla = fondos.get(fondoActual).getImagen();
        } catch (Exception e) {
            System.err.println("Error cambiando la imagen del telefono: " + e.getMessage());
        }
    }

    public void cambiarSiguienteFondo()
    {
        if (fondoActual == fondos.size() - 1) {
            cambiarFondoIndice(0);
        }
        cambiarFondoIndice(fondoActual + 1);
    }

    public void cambiarFondoAnterioPorIndice() {
        if (fondoActual == 0 ) {
            cambiarFondoIndice(fondos.size()-1);
        }
        cambiarFondoIndice(fondoActual - 1);
    }

    public ImageIcon enviarSiguienteFondo()
    {
        ImageIcon imagen = new ImageIcon();

        if(indiceDeCambio == fondos.size()-1)
        {
            indiceDeCambio = 0;
            imagen = fondos.get(indiceDeCambio).getImagen();
        }
        else
        {
            indiceDeCambio++;
            imagen = fondos.get(indiceDeCambio).getImagen();
        }
        return imagen;
    }

    public ImageIcon enviarAnteriorFondo()
    {
        ImageIcon imagen = new ImageIcon();
        if(indiceDeCambio == 0)
        {
            indiceDeCambio = fondos.size()-1;
            imagen = fondos.get(indiceDeCambio).getImagen();
        }

        else
        {
            indiceDeCambio--;
            imagen = fondos.get(indiceDeCambio).getImagen();
        }
        return imagen;
    }

    public void colocarFondoActual()
    {
        cambiarFondoIndice(indiceDeCambio);
    }
}