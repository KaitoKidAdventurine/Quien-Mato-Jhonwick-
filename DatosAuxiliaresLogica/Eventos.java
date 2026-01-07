package DatosAuxiliaresLogica;

import java.io.Serializable;

public class Eventos implements Serializable {
    private static final long serialVersionUID = 1L;

    private int dialogoCapitanActual;
    private int ronda;
    private boolean duenoYA;
    private boolean secretariaYa;
    private boolean seguridadYa;
    private boolean policiaYa;
    private boolean conserjeYa;
    private boolean guia1Ya;
    private boolean guia2Ya;
    private boolean esposaYa;
    private boolean vagabundoYa;
    private boolean puertaCerrada;
    private boolean camarasRevisadas;
    private boolean policiaSiguiendo;
    private boolean banoRevisado;
    private boolean almacenRevisado;
    public Eventos() {
        this.duenoYA = false;
        this.secretariaYa = false;
        this.seguridadYa = false;
        this.policiaYa = false;
        this.conserjeYa = false;
        this.guia1Ya = false;
        this.guia2Ya = false;
        this.vagabundoYa = false;
        this.esposaYa = false;
        this.puertaCerrada = false;
        this.camarasRevisadas = false;
        this.policiaSiguiendo = false;
        this.banoRevisado=false;
        this.almacenRevisado = false;
        dialogoCapitanActual=0;
        ronda = 0;
    }
    

    public int getRonda() {
        return ronda;
    }

    public void setRonda(int ronda) {
        this.ronda = ronda;
    }

    public int getDialogoCapitanActual() {
        return dialogoCapitanActual;
    }

    public void setDialogoCapitanActual(int dialogoCapitanActual) {
        this.dialogoCapitanActual = dialogoCapitanActual;
    }

    public boolean isDuenoYA() {
        return duenoYA;
    }

    public void setDuenoYA(boolean duenoYA) {
        this.duenoYA = duenoYA;
    }

    public boolean isSecretariaYa() {
        return secretariaYa;
    }

    public void setSecretariaYa(boolean secretariaYa) {
        this.secretariaYa = secretariaYa;
    }

    public boolean isSeguridadYa() {
        return seguridadYa;
    }

    public void setSeguridadYa(boolean seguridadYa) {
        this.seguridadYa = seguridadYa;
    }

    public boolean isPoliciaYa() {
        return policiaYa;
    }

    public void setPoliciaYa(boolean policiaYa) {
        this.policiaYa = policiaYa;
    }

    public boolean isConserjeYa() {
        return conserjeYa;
    }

    public void setConserjeYa(boolean conserjeYa) {
        this.conserjeYa = conserjeYa;
    }

    public boolean isGuia1Ya() {
        return guia1Ya;
    }

    public void setGuia1Ya(boolean guia1Ya) {
        this.guia1Ya = guia1Ya;
    }

    public boolean isGuia2Ya() {
        return guia2Ya;
    }

    public void setGuia2Ya(boolean guia2Ya) {
        this.guia2Ya = guia2Ya;
    }

    public boolean isEsposaYa() {
        return esposaYa;
    }

    public void setEsposaYa(boolean esposaYa) {
        this.esposaYa = esposaYa;
    }
    public void cambiarARonda1(){
        if(duenoYA==true && seguridadYa==true && guia1Ya==true){
            setRonda(1);
        }
    }
    public void cambiarRonda2(){
        if(secretariaYa ==true && guia2Ya==true && conserjeYa==true)
            setRonda(2);
    }

    public void cambiarRonda3(){
        if(vagabundoYa==true)
            setRonda(3);
    }
    public void cambiarRonda4(){
        if(esposaYa==true)
            setRonda(4);
    }
    public void cambiarRonda5(){
        setRonda(5);
    }
    public boolean isVagabundoYa() {
        return vagabundoYa;
    }

    public void setVagabundoYa(boolean vagabundoYa) {
        this.vagabundoYa = vagabundoYa;
    }

    public boolean isPuertaCerrada() {
        return puertaCerrada;
    }

    public void setPuertaCerrada(boolean puertaCerrada) {
        this.puertaCerrada = puertaCerrada;
    }

    public boolean isCamarasRevisadas() {
        return camarasRevisadas;
    }

    public void setCamarasRevisadas(boolean camarasRevisadas) {
        this.camarasRevisadas = camarasRevisadas;
    }

    public boolean isPoliciaSiguiendo() {
        return policiaSiguiendo;
    }

    public void setPoliciaSiguiendo(boolean policiaSiguiendo) {
        this.policiaSiguiendo = policiaSiguiendo;
    }

    public boolean isBanoRevisado() {
        return banoRevisado;
    }

    public void setBanoRevisado(boolean banoRevisado) {
        this.banoRevisado = banoRevisado;
    }

    public boolean isAlmacenRevisado() {
        return almacenRevisado;
    }

    public void setAlmacenRevisado(boolean almacenRevisado) {
        this.almacenRevisado = almacenRevisado;
    }
}
