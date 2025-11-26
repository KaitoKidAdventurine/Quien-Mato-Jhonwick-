package Logica;

import java.util.LinkedList;

public class Juego {
    private String titulo;
    private String version;
    private LinkedList<Partida> partidas;

    private Juego() {
        this.titulo = "Juego";
        this.version = "0.01";
        this.partidas = new LinkedList<Partida>();
    }


    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    public LinkedList<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(LinkedList<Partida> partidas) {
        this.partidas = partidas;
    }

    public void agregarPartida(Partida p)
    {
        partidas.add(p);
    }


    public void iniciarJuego() {}
    public void cargarPartida() {}
    public void guardarPartida() {}

}
