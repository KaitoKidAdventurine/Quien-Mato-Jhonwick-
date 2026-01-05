package DatosAuxiliaresLogica;

public class Eventos {
    private int dialogoCapitanActual;
    private boolean acto1;



    public Eventos(){
        dialogoCapitanActual=0;
        acto1 = true;
    }

    public boolean getActo1() {
        return acto1;
    }

    public void setActo1(boolean acto1) {
        this.acto1 = acto1;
    }

    public int getDialogoCapitanActual() {
        return dialogoCapitanActual;
    }

    public void setDialogoCapitanActual(int dialogoCapitanActual) {
        this.dialogoCapitanActual = dialogoCapitanActual;
    }
}
