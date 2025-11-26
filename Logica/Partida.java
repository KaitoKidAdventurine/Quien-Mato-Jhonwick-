package Logica;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;

public class Partida
{
    private String idPartida;
    private LocalDate fechaInicio;
    // El estado asumo que es en que Acto esta el jugador
    private String estado;
    private LinkedList<Escenario> escenarios;
    private Jugador jugador;

    public Partida(String idPartida, LocalDate fechaInicio, String estado, LinkedList<Escenario> escenarios, Jugador jugador) {
        this.idPartida = idPartida;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
        this.escenarios = escenarios;
        this.jugador = jugador;
    }

    public String getIdPartida()
    { 
        return idPartida; 
    }
    public void setIdPartida(String idPartida) 
    { 
        this.idPartida = idPartida; 
    }

    public LocalDate getFechaInicio() 
    { 
        return fechaInicio; 
    }
    public void setFechaInicio(LocalDate fechaInicio) 
    { 
        this.fechaInicio = fechaInicio; 
    }

    public String getEstado() 
    { 
        return estado; 
    }
    public void setEstado(String estado) 
    { 
        this.estado = estado; 
    }



    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public LinkedList<Escenario> getEscenarios() { return escenarios; }
    public void setEscenarios(LinkedList<Escenario> escenarios) { this.escenarios = escenarios; }

    /*
    public void iniciarPartida()
    {}
    public void cargarPartida()
    {}
     */
}

