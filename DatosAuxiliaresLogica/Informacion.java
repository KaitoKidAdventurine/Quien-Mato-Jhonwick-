package DatosAuxiliaresLogica;

import java.util.LinkedList;

public class Informacion
{
    private String nombreNPC;
    private LinkedList<String> listaDeDialogos;

    public Informacion(String nombreNPC) {
        this.nombreNPC = nombreNPC;
        listaDeDialogos = new LinkedList<String>();
    }

    public String getNombreNPC() {
        return nombreNPC;
    }

    public void setNombreNPC(String nombreNPC) {
        this.nombreNPC = nombreNPC;
    }

    public LinkedList<String> getListaDeDialogos() {
        return listaDeDialogos;
    }

    public void setListaDeDialogos(LinkedList<String> listaDeDialogos) {
        this.listaDeDialogos = listaDeDialogos;
    }
    public void agregarDialogo(String dialogo){
        listaDeDialogos.add(dialogo);
    }
}
