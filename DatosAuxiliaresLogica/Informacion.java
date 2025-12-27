package DatosAuxiliaresLogica;

import Logica.Sexo;

import java.io.Serializable;
import java.util.LinkedList;

public class Informacion implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String nombreNPC;
    private LinkedList<String> listaDeDialogos;
    private String edadNPC;
    private Sexo sexo;
    private String ocupacion;
    private LinkedList<String> personalidad;

    public Informacion(String nombreNPC) {
        this.nombreNPC = nombreNPC;
        listaDeDialogos = new LinkedList<String>();
        edadNPC = "Desconocido";
        sexo = Sexo.DESCONOCIDO;
        ocupacion = "Desconocido";
        personalidad = new LinkedList<String>();
    }

    public Informacion(String nombreNPC, String edadNPC, Sexo sexo, String ocupacion) {
        this.nombreNPC = nombreNPC;
        listaDeDialogos = new LinkedList<String>();
        this.edadNPC = edadNPC;
        this.sexo = sexo;
        this.ocupacion = ocupacion;
        personalidad = new LinkedList<String>();
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

    public String getEdadNPC() { return edadNPC; }
    public void setEdadNPC(String edadNPC) { this.edadNPC = edadNPC; }

    public Sexo getSexo() { return sexo; }
    public void setSexo(Sexo sexo) { this.sexo = sexo; }

    public String getOcupacion() { return ocupacion; }
    public void setOcupacion(String ocupacion) { this.ocupacion = ocupacion; }

    public LinkedList<String> getPersonalidad() { return personalidad; }
    public void setPersonalidad(LinkedList<String> personalidad) { this.personalidad = personalidad; }
    public void agregarCualidad(String cualidad) { personalidad.addLast(cualidad); }
}
