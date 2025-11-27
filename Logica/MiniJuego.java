package Logica;

import javax.swing.*;
import java.util.*;

public class MiniJuego
{
    private String nomEscenario;
    private Deque<ObjetoEscenario> cola;
    private ArrayList<ObjetoEscenario> listaObjetos;
    private ImageIcon foto;
    private int indice;


    public MiniJuego(String nomEscenario, ImageIcon foto) {
        this.nomEscenario = nomEscenario;
        this.cola = new ArrayDeque<ObjetoEscenario>();
        this.listaObjetos = new ArrayList<ObjetoEscenario>();
        this.foto = foto;
        indice = 0;
    }

    //Get y Set
    public ImageIcon getFoto() {
        return foto;
    }

    public void setFoto(ImageIcon foto) {
        this.foto = foto;
    }

    public Deque<ObjetoEscenario> getCola() {
        return cola;
    }

    public void setCola(Deque<ObjetoEscenario> cola) {
        this.cola = cola;
    }

    public ArrayList<ObjetoEscenario> getListaObjetos() {
        return listaObjetos;
    }

    public void setListaObjetos(ArrayList<ObjetoEscenario> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }



    // Metodos
    public void agregarObjetoLista(ObjetoEscenario o)
    {

        o.setPosList(indice);
        indice++;
        listaObjetos.add(o);
    }
    public void pedirSiguienteObjeCola()
    {
        agregarObjetoLista(cola.pop());
    }

    public void agregarObjetoCola(ObjetoEscenario o){
        cola.offer(o);
    }

    public void agregarObjetoMaletin(ObjetoEscenario o)
    {
        Jugador jugador = Jugador.getInstancia();
        jugador.agregarAlMaletin(o);
    }

    public void objetoEncontrado(ObjetoEscenario o)
    {
        listaObjetos.remove(o.getPosList());
    }

}


