package DatosAuxiliaresLogica;

import java.util.LinkedList;

public class Informacion
{
    private String nombreNPC;
    private LinkedList<String> listaDeInformacion;

    public Informacion(String nombreNPC, LinkedList<String> listaDeInformacion) {
        this.nombreNPC = nombreNPC;
        this.listaDeInformacion = listaDeInformacion;
    }

    public String getNombreNPC() {
        return nombreNPC;
    }

    public void setNombreNPC(String nombreNPC) {
        this.nombreNPC = nombreNPC;
    }

    public LinkedList<String> getListaDeInformacion() {
        return listaDeInformacion;
    }

    public void setListaDeInformacion(LinkedList<String> listaDeInformacion) {
        this.listaDeInformacion = listaDeInformacion;
    }
}
