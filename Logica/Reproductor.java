package Logica;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import DatosAuxiliaresLogica.Cancion;
import javazoom.jl.player.Player;

public class Reproductor {
    //private Player reproductorMP3;
    private Thread hiloReproduccion;
    private ArrayList<Cancion> canciones;
    private int indiceActual;
    private boolean enReproduccion;
    private Player reproductorMP3;

    public Reproductor() {
        this.canciones = new ArrayList<Cancion>();
        this.indiceActual = 0;
        this.enReproduccion = false;
        this.reproductorMP3= new Player();

        // Cargar canción específica
        File archivoMusica = new File("Musica/Canciones/CancMenu.mp3");
        if (archivoMusica.exists()) {
            Cancion cancion = new Cancion("CancMenu", archivoMusica);
            canciones.add(cancion);
            iniciarMusica();
        } else {
            System.err.println("No se encontró el archivo de música: " + archivoMusica.getAbsolutePath());
        }
    }

    // Getters
    public Player getReproductorMP3() 
    {
        return reproductorMP3;
    }

    public Thread getHiloReproduccion() 
    {
        return hiloReproduccion;
    }

    public ArrayList<Cancion> getCanciones() 
    {
        return canciones;
    }

    public int getIndiceActual() 
    {
        return indiceActual;
    }

    public boolean isEnReproduccion() 
    {
        return enReproduccion;
    }

    // Setters
    public void setReproductorMP3(Player reproductorMP3)
    {
        this.reproductorMP3 = reproductorMP3;
    }

    public void setHiloReproduccion(Thread hiloReproduccion) {
        this.hiloReproduccion = hiloReproduccion;
    }

    public void setCanciones(ArrayList<Cancion> canciones) {
        this.canciones = canciones;
    }

    public void setIndiceActual(int indiceActual) {
        this.indiceActual = indiceActual;
    }

    public void setEnReproduccion(boolean enReproduccion) {
        this.enReproduccion = enReproduccion;
    }

    public void iniciarMusica()
    {
        try 
        {
            // Guardo el valor del primer archivo d musica de la lista.
            Cancion c = canciones.get(0);
            indiceActual = 0;
        
            // Convierto el File en un FileInput... para poder despues convertirlo en un Player
            FileInputStream stream = new FileInputStream(c.getFile());
            reproductorMP3 = new Player(stream);
        
            // Se usa el Thread para dejar reproduciendo la musica en segundo plano 
            // y asi seguir con el programa. 
            hiloReproduccion = new Thread(() -> {
                try 
                {
                    reproductorMP3.play(); 
                } catch (Exception e) 
                {
                System.err.println("Error reproduciendo: " + e.getMessage());
                }
            });
        
            hiloReproduccion.start();
            enReproduccion = true;
        
        } catch (Exception e) 
        {
            System.err.println("Error iniciando música: " + e.getMessage());
        }
    }

    // Se cambia a la cancion que se quiera en especifico por indice
    public void cambiarMusicaIndice(int i)
    {
        try 
        {
            Cancion c = canciones.get(i);
            indiceActual = i;
        
            FileInputStream stream = new FileInputStream(c.getFile());
            reproductorMP3 = new Player(stream);
        
 
            hiloReproduccion = new Thread(() -> {
                try 
                {
                   reproductorMP3.play(); 
                } catch (Exception e) 
                {
                System.err.println("Error reproduciendo: " + e.getMessage());
                }
            });
        
            hiloReproduccion.start();
            enReproduccion = true;
        
        } catch (Exception e) 
        {
            System.err.println("Error iniciando música: " + e.getMessage());
        }
    }

    public void cambiarMusicaNombre(String nombre)
    {
        try
        {
            boolean encontrado = false;
            for (int i = 0; i < canciones.size() && !encontrado; i++)
            {
                if (canciones.get(i).getNombre().equals(nombre))
                {
                    encontrado = true;
                    indiceActual = i;
                }
            }

            if (encontrado)
            {
                cambiarMusicaIndice(indiceActual);
            }

            else
            {
                throw new IllegalArgumentException("La canción '" + nombre + "' no fue encontrada");
            }
        } catch(IllegalArgumentException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void cambiarMusicaSiguiente()
    {
        if(indiceActual == canciones.size() -1)
        {
            cambiarMusicaIndice(0);
        }
        cambiarMusicaIndice(indiceActual+1);
    }

    public void detenerCancion()
    {
        enReproduccion = false;

        if (reproductorMP3 != null)
        {
            reproductorMP3.close();
        }
        
        if (hiloReproduccion != null && hiloReproduccion.isAlive()) 
        {
            hiloReproduccion.interrupt();
        }

    }*/
    }
}