package DatosAuxiliaresLogica;

public class UnionInterfaces {

    private int opcionDialogo;
    private boolean cerrarVentana;
    private static UnionInterfaces instancia;

    public UnionInterfaces(){
        this.opcionDialogo = 1;
        this.cerrarVentana= false;
    }

    public int getOpcionDialogo() {
        return opcionDialogo;
    }

    public boolean getCerrarVentana() {
        return cerrarVentana;
    }

    public void setOpcionDialogo(int opcionDialogo) {
        this.opcionDialogo = opcionDialogo;
    }
    public void setCerrarVentana(boolean cerrarVentana){
        this.cerrarVentana=cerrarVentana;
    }
    public static UnionInterfaces getInstance(){
        if(instancia == null)
        {
            instancia = new UnionInterfaces();
        }
        return instancia;
    }
}
