package Logica;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;

public class Partida
{
    private String idPartida;
    private LocalDate fechaInicio;
    private String estado;
    private int finalizadoMarcador;
    private LinkedList<Escenario> escenarios;

    public Partida(String idPartida, LocalDate fechaInicio, String estado, int finalizadoMarcador) 
    {
        this.idPartida = idPartida;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
        this.finalizadoMarcador = finalizadoMarcador;
        escenarios = new LinkedList<>();
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

    public int getFinalizadoMarcador() 
    { 
        return finalizadoMarcador; 
    }
    public void setFinalizadoMarcador(int marcador) 
    { 
        this.finalizadoMarcador = marcador; 
    }

    public void iniciarPartida() 
    {}
    public void cargarProgreso() 
    {}
    public void cargarPartida() 
    {}

    public LinkedList<Escenario> getEscenarios() { return escenarios; }
    public void setEscenarios(LinkedList<Escenario> escenarios) { this.escenarios = escenarios; }
}

