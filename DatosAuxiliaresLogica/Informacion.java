package DatosAuxiliaresLogica;

public class Informacion
{
    private String nombreNPC;
    private linkedList<String> listaDeInformacion;

    public Informacion(String nombreNPC, linkedList<String> listaDeInformacion) {
        this.nombreNPC = nombreNPC;
        this.listaDeInformacion = listaDeInformacion;
    }

    public String getNombreNPC() {
        return nombreNPC;
    }

    public void setNombreNPC(String nombreNPC) {
        this.nombreNPC = nombreNPC;
    }

    public linkedList<String> getListaDeInformacion() {
        return listaDeInformacion;
    }

    public void setListaDeInformacion(linkedList<String> listaDeInformacion) {
        this.listaDeInformacion = listaDeInformacion;
    }
}
