package Logica;

import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

public class Inventario implements Serializable
{
    private static final long serialVersionUID = 1L;

    public LinkedList<ObjetoMochila> objetosInventario;

    public Inventario()
    {
        this.objetosInventario = new LinkedList<ObjetoMochila>();
    }

    public LinkedList<ObjetoMochila> getObjetosInventario() {
        return objetosInventario;
    }

    public void setObjetosInventario(LinkedList<ObjetoMochila> objetosInventario) {
        this.objetosInventario = objetosInventario;
    }


    public void agregarObjetoInventario(ObjetoMochila o)
    {
        objetosInventario.add(o);
    }

    public void removerObjetoMochilaNombre(String nombre)
    {
        boolean salida =false;

        Iterator<ObjetoMochila> II = objetosInventario.iterator();
        while (II.hasNext() && !salida)
        {
            ObjetoMochila obj = II.next();
            if(obj.getNombre().equals(nombre)) {
                II.remove();
                salida = true;
            }
        }
    }

    public void removerObjetoMochilaPorObjeto(ObjetoMochila o)
    {
        boolean salida =false;

        Iterator<ObjetoMochila> II = objetosInventario.iterator();
        while (II.hasNext() && !salida)
        {
            ObjetoMochila obj = II.next();
            if(obj.getNombre().equals(o.getNombre())) {
                II.remove();
                salida = true;
            }
        }
    }

}
