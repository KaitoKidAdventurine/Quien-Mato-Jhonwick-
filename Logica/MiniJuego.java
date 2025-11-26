package Logica;

import javax.swing.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import Logica.ObjetoEscenario;

public class MiniJuego
{
    private Deque<ObjetoEscenario> cola;
    private LinkedList<ObjetoEscenario> listaObjetos;
    private ImageIcon foto;


    public MiniJuego( ImageIcon foto) {
        this.cola = new ArrayDeque<ObjetoEscenario>();
        this.listaObjetos = new LinkedList<ObjetoEscenario>();
        this.foto = foto;
    }

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

    public LinkedList<ObjetoEscenario> getListaObjetos() {
        return listaObjetos;
    }

    public void setListaObjetos(LinkedList<ObjetoEscenario> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public ObjetoEscenario pedirSiguienteObjeCola()
    {
        return cola.pop();
    }

    public void agregarObjetoCola(ObjetoEscenario o)
    {
        cola.offer(o);
    }
    public void agregarObjetoLista(ObjetoEscenario o)
    {
        listaObjetos.add(o);
    }


    /*
    public void objetoEncontrado(ObjetoEscenario o)
    {
        boolean salida = false;
        Iterator<ObjetoEscenario> II = listaObjetos.iterator();
        while (II.hasNext() && !salida)
        {
            ObjetoEscenario objeto = II.next();
            if (o == objeto)
            {
                objeto.setEncontrado(true);
                if(objeto.getImportante() == true)
                {
                    agregarAlMaletin(objeto);
                }
                salida = true;
            }
        }
    }
     */
}


