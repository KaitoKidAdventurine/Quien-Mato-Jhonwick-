package DatosAuxiliaresLogica;

public class UnionInterfaces {

    private int opcionDialogo;
    private boolean cerrarVentana;
    private boolean salirJuego;
    private boolean confirmarBorrado;
    private static UnionInterfaces instancia;

    public UnionInterfaces(){
        this.opcionDialogo = 1;
        this.cerrarVentana= false;
        this.salirJuego = false;
        this.confirmarBorrado = false;
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

    public boolean getSalirJuego() {
        return salirJuego;
    }

    public void setSalirJuego(boolean salirJuego) {
        this.salirJuego = salirJuego;
    }

    public boolean getConfirmarBorrado() {
        return confirmarBorrado;
    }

    public void setConfirmarBorrado(boolean confirmarBorrado) {
        this.confirmarBorrado = confirmarBorrado;
    }
}
