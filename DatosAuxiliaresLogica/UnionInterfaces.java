package DatosAuxiliaresLogica;

import javax.swing.*;

public class UnionInterfaces {

    private int opcionDialogo;
    private boolean cerrarVentana;
    private boolean salirJuego;
    private boolean confirmarBorrado;
    private static UnionInterfaces instancia;
    private boolean abriendoPartida;
    private boolean hablandoCapitan;
    private boolean molestandoTarde;
    private boolean usandoFlecha;
    private JFrame frameActual;
    public UnionInterfaces(){
        this.opcionDialogo = 1;
        this.cerrarVentana= false;
        this.salirJuego = false;
        this.confirmarBorrado = false;
        this.abriendoPartida=false;
        this.hablandoCapitan=false;
        this.molestandoTarde=false;
        this.usandoFlecha=false;
        frameActual = null;
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

    public boolean getAbriendoPartida() {
        return abriendoPartida;
    }

    public void setAbriendoPartida(boolean abriendoPartida) {
        this.abriendoPartida = abriendoPartida;
    }

    public boolean getHablandoCapitan() {
        return hablandoCapitan;
    }

    public void setHablandoCapitan(boolean hablandoCapitan) {
        this.hablandoCapitan = hablandoCapitan;
    }

    public boolean getMolestandoTarde() {
        return molestandoTarde;
    }

    public void setMolestandoTarde(boolean molestandoTarde) {
        this.molestandoTarde = molestandoTarde;
    }

    public boolean getUsandoFlecha() {
        return usandoFlecha;
    }

    public void setUsandoFlecha(boolean usandoFlecha) {
        this.usandoFlecha = usandoFlecha;
    }

    public JFrame getFrameActual() {
        return frameActual;
    }

    public void setFrameActual(JFrame frameActual) {
        this.frameActual = frameActual;
    }
}
