package DatosAuxiliaresLogica;

public class Datos
{
    private String nomNPC;
    private String textoImportante;

    public Datos(String nomNPC, String textoImportante)
    {
        this.nomNPC = nomNPC;
        this.textoImportante = textoImportante;
    }

    public String getNomNPC() {
        return nomNPC;
    }

    public void setNomNPC(String nomNPC) {
        this.nomNPC = nomNPC;
    }

    public String getTextoImportante() {
        return textoImportante;
    }

    public void setTextoImportante(String textoImportante) {
        this.textoImportante = textoImportante;
    }
}
