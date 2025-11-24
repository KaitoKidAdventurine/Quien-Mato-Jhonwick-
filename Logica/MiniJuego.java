package Logica;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class MiniJuego
{
    private Deque<ObjetoEscenario> cola;
    private LinkedList<ObjetoEscenario> listaObjetos;

    public MiniJuego(Deque<ObjetoEscenario> cola, LinkedList<ObjetoEscenario> listaObjetos)
    {
        this.cola = cola;
        this.listaObjetos = listaObjetos;
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

    public void agregarObjetoLista(ObjetoEscenario o)
    {
        listaObjetos.add(o);
    }
    public ObjetoEscenario pedirSiguienteObjeCola()
    {
        return cola.pop();
    }

    // No lo he creado porque no se que datos tendra los objetos de la mochila
    // Pero como tal solo seria conseguir la informacion y transformarlo que eso
    // es sencillo
    public void agregarObjetoMochila(ObjetoEscenario o)
    {

    }
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
                if(objeto.getImportante())
                {
                    agregarObjetoMochila(objeto);
                }
                salida = true;
            }
        }
    }

}


