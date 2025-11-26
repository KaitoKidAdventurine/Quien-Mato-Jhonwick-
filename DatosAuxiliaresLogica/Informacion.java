package DatosAuxiliaresLogica;

import java.util.LinkedList;

public class Informacion
{
    private String nombreNPC;
    private LinkedList<String> ListaDeDialogos;

    public Informacion(String nombreNPC) {
        this.nombreNPC = nombreNPC;
        ListaDeDialogos = new LinkedList<String>();
    }

    public String getNombreNPC() {
        return nombreNPC;
    }

    public void setNombreNPC(String nombreNPC) {
        this.nombreNPC = nombreNPC;
    }

    public LinkedList<String> getListaDeDialogos() {
        return ListaDeDialogos;
    }

    public void setListaDeDialogos(LinkedList<String> listaDeDialogos) {
        ListaDeDialogos = listaDeDialogos;
    }
}
