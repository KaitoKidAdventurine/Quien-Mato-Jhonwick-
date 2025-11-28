package Logica;
import Interfaz.Escenarios.Almacen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

public class Partida
{
    private static Partida instancia;
    private String idPartida;
    private LocalDate fechaInicio;
    // El estado asumo que es en que Acto esta el jugador
    private String estado;
    private ArrayList<Escenario> escenarios;
    private Jugador jugador;

    public static Partida getInstance()
    {
        if(instancia == null)
        {
            instancia = new Partida();
        }
        return instancia;
    }


    private Partida() {
        // Para darle un valor al ID sera la partida que escoja el usuario.
        // O sea que cuando toque Nueva Partida 1, ese 1 sera el ID.
        // Modificar cuando se implemente.
        this.idPartida = "";
        this.fechaInicio = LocalDate.now();
        this.estado = "";
        this.escenarios = new ArrayList<Escenario>();

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




    // Metodos:
    public ArrayList<Escenario> getEscenarios() { return escenarios; }

    public void setEscenarios(ArrayList<Escenario> escenarios) { this.escenarios = escenarios; }

    // Metodos:

    public void buscarEscenarioNombre(String nom)
    {
        boolean salida = false;
        for(int i = 0; i < escenarios.size() && !salida; i++)
        {
            if(nom.equals(escenarios.get(i).getNombre()))
            {
                Jugador.getInstancia().setEscenarioActual(escenarios.get(i));
                salida = true;
            }
        }
    }

    public void agregarEscenariosAutomaticamente()
    {
        Escenario almacen = new Escenario("Almacen", "Lugar Donde se almacen obras de arte" , false);
        Escenario bannoPlantaBaja = new Escenario("Banno Planta Baja", "Banno de la Planta baja" , false);
        Escenario bannoPlantaAlta = new Escenario("Banno Planta Alta ", "Banno de la Planta alta" , false);
        Escenario callejon = new Escenario("Callejon", "Callejon de fuera del Museo" , false);
        Escenario entrada = new Escenario("Entrada", "Entrada Principal del Museo" , false);
        Escenario oficinaVictima = new Escenario("Oficina de la Victima", "Lugar donde fue encontrado el cuerpo" , false);
        Escenario oficinaDelJefe = new Escenario("Oficina del Jefe", "Lugar Donde trabaja el jefe de la victima" , false);
        Escenario alaEste = new Escenario("Ala este", "Ala este del museo" , false);
        Escenario alaNorte = new Escenario("Ala norte", "Ala norte del museo" , false);
        Escenario alaSur = new Escenario("Ala Sur", "Ala norte del museo" , false);
        Escenario oficinasPlantaBaja = new Escenario("Oficinas Planta Baja", "Lugar Donde se almacen obras de arte" , false);
        Escenario recepcion = new Escenario("Recepcion", "Recepcion del museo" , false);
        Escenario salaPlantaAlta = new Escenario("Sala Planta Alta", "Sala de la Planta Alta del museo" , false);
        Escenario exposicionDeAntiguedades= new Escenario("Exposicion de Antiguedades", "Lugar donde se exponen antiguedades del museo" , false);
        Escenario salaDeVigilancia = new Escenario("Sala de Vigilancia", "Lugar donde se encuentran las grabaciones del museo" , false);

        escenarios.add(almacen);
        escenarios.add(bannoPlantaBaja);
        escenarios.add(bannoPlantaAlta);
        escenarios.add(callejon);
        escenarios.add(entrada);
        escenarios.add(oficinaVictima);
        escenarios.add(oficinaDelJefe);
        escenarios.add(alaEste);
        escenarios.add(alaNorte);
        escenarios.add(alaSur);
        escenarios.add(oficinasPlantaBaja);
        escenarios.add(recepcion);
        escenarios.add(salaPlantaAlta);
        escenarios.add(exposicionDeAntiguedades);
        escenarios.add(salaDeVigilancia);
    }

    public void iniciarPartida()
    {}
    public void cargarPartida()
    {}
}

