package Logica;

import java.util.Iterator;
import javax.swing.*;
import java.util.Arrays;
import java.util.LinkedList;

public class Juego {
    private static Juego instance;
    private String titulo;
    private String version;
    private LinkedList<Partida> partidas;
    private Partida partidaActual;

    private Juego() {
        this.titulo = "Juego";
        this.version = "0.01";
        this.partidas = new LinkedList<Partida>();
        //esto se quita mas adelante
        this.partidaActual = null;
    }

    public static Juego getInstance() {
        if (instance == null) {
            instance = new Juego();
        }
        return instance;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getVersion() {
        return version;
    }


    public void setVersion(String version) {
        this.version = version;
    }

    public LinkedList<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(LinkedList<Partida> partidas) {
        this.partidas = partidas;
    }

    public void agregarPartida(Partida p) {
        partidas.add(p);
    }

    public Partida getPartidaActual() {
        return partidaActual;
    }

    public void setPartidaActual(Partida partidaActual) {
        this.partidaActual = partidaActual;
    }

    public Partida obtenerPartida(String id) {
        Iterator<Partida> IP = partidas.iterator();
        boolean salida = false;
        Partida p = null;
        while (IP.hasNext() && !salida) {
            Partida par = IP.next();
            if (par.getIdPartida().equals(id)) {
                salida = true;
                p = par;
            }
        }
        return p;
    }

    public boolean crearNuevaPartida(String idPartida, String nombreJugador) {
        // Verificar si ya existe una partida con ese ID
        boolean salida = false;
        if (existePartida(idPartida)) {
            System.out.println("Ya existe una partida con ID: " + idPartida);
        }

        else
        {
            //  Creo la nueva partida
            Partida nuevaPartida = new Partida();
            nuevaPartida.setIdPartida(idPartida);

            //  Configuro a el jugador
            nuevaPartida.getJugador().setNombre(nombreJugador);

            // Agregar a la lista de partidas
            partidas.add(nuevaPartida);

            nuevaPartida.getJugador().setNombre(nombreJugador);

            // Cargar como partida actual (la que esta clonada)
            setPartidaActual(nuevaPartida.clone());


            System.out.println("Nueva partida creada y cargada: " + idPartida);
            salida = true;
        }
        return salida;
    }

    public void eliminarPartida(String id)
    {
        boolean salida = false;
        Iterator<Partida> IP = partidas.iterator();
        while (IP.hasNext() && !salida)
        {
            Partida p = IP.next();
            if (p.getIdPartida().equals(id))
            {
                IP.remove();
                salida = true;
            }
        }
    }
    public boolean existePartida(String id)
    {
        boolean salida = false;
        if (!partidas.isEmpty())
        {
            Iterator<Partida> IP = partidas.iterator();
            while (IP.hasNext() && !salida)
            {
                Partida p = IP.next();
                if (p.getIdPartida().equals(id))
                {
                    salida = true;
                }
            }
        }
        return salida;
    }

    public void iniciarJuego()
    {
        // Se necesita que se invoque el metodo obtenerPartida para poder obtener en que partida esta el usuario.
        // y pasarle el Id de la partida como parametro

    }
    public void cargarPartida()
    {

    }
    public void guardarPartida()
    {

    }

}
