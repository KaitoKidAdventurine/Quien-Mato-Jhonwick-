package Logica;

import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.general.GeneralTree;
import cu.edu.cujae.ceis.tree.iterators.general.InDepthIterator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import Logica.*;
public class Escenario implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String descripcion;
    private boolean investigado;
    private ArrayList<Interactuable> interactuables;
    private GeneralTree<Dialogo> arbolDial;
    private BinaryTreeNode<Dialogo> nodoDialActual;
    private int posList;
    private boolean dialogoCompletado = false;
    private String progresoDialogo = "";

    public Escenario(String nombre, String descripcion, boolean investigado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.investigado = investigado;
        this.interactuables = new ArrayList<Interactuable>();
        arbolDial = new GeneralTree<>();
        nodoDialActual = null;
    }

    public int getPosList() {
        return posList;
    }

    public void setPosList(int posList) {
        this.posList = posList;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public boolean isInvestigado() { return investigado; }
    public void setInvestigado(boolean investigado) { this.investigado = investigado; }

    public ArrayList<Interactuable> getInteractuables() { return interactuables; }
    public void setInteractuables(ArrayList<Interactuable> interactuables) { this.interactuables = interactuables; }

    public void investigar() {}
    public void agregarInteractuable(Interactuable i) {
        interactuables.add(i);
    }

    public GeneralTree<Dialogo> getArbolDial()
    {
        return arbolDial;
    }

    public void setArbolDial(GeneralTree<Dialogo> arbolDial) {
        this.arbolDial = arbolDial;
        this.nodoDialActual = null;
    }

    public Dialogo getDialogoActual() { return nodoDialActual.getInfo(); }
    public BinaryTreeNode<Dialogo> getNodoDialActual() { return nodoDialActual; }

    public void setNodoDialActual(BinaryTreeNode<Dialogo> nodoDialActual) {
        this.nodoDialActual = nodoDialActual;
    }


    public Dialogo getDialogoSiguiente(int opcionElegida) {
        // GUARDAR ANTES DE MOVERSE
        if (nodoDialActual != null) {
            progresoDialogo = nodoDialActual.getInfo().getTexto();
        }

        int cantOpciones;

        if (nodoDialActual != null && !(arbolDial.nodeIsLeaf(nodoDialActual))) {
            cantOpciones = arbolDial.nodeDegree(nodoDialActual);
            nodoDialActual = nodoDialActual.getLeft();


            // opcionElegida también es representado como la cantidad de llamadas getRight() para hallar el diálogo que debe mostrarse.
            if (opcionElegida >= 2 && opcionElegida > cantOpciones) {
                throw new IllegalArgumentException("La opción (pregunta elegida) sobrepsasa la cantidad de opciones disponibles (posibles dialogos a mostrar).");
            }

            /*
             * La opcion elegida intenta escoger un diálogo que, por ahora, no puede mostrarse. Rebobinar al último diálogo.
             * Es posible mostrar un diálogo o mensaje de error antes de volver a mostrar el último diálogo para permitir otra oportunidad.
             */
            for ( ; opcionElegida >= 2; opcionElegida--) {
                nodoDialActual = nodoDialActual.getRight();
            }

            if (!(nodoDialActual.getInfo().isRevelable())) {
                nodoDialActual = arbolDial.getFather(nodoDialActual);
            }
        }
        else if (nodoDialActual == null) {
            nodoDialActual = (BinaryTreeNode<Dialogo>)arbolDial.getRoot();
            progresoDialogo = nodoDialActual.getInfo().getTexto();
        }


        if (arbolDial.nodeIsLeaf(nodoDialActual)) {
            dialogoCompletado = true;
        }
        // Si nodoDialActual no es null y es hoja, no se hace nada - se queda en el mismo diálogo
        return nodoDialActual.getInfo();
    }

    public void restaurarDialogo() {
        if (dialogoCompletado) {
            nodoDialActual = null;
        } else if (progresoDialogo != null && !progresoDialogo.isEmpty()) {
            // Buscar el ultimo dialogo visto
            nodoDialActual = buscarNodo(progresoDialogo);
            if (nodoDialActual == null) {
                // Si no se encuentra, empezar desde el principio
                nodoDialActual = (BinaryTreeNode<Dialogo>)arbolDial.getRoot();
            }
        }
    }

    public String getProgresoDialogo() {
        return progresoDialogo;
    }

    public void setProgresoDialogo(String progresoDialogo) {
        this.progresoDialogo = progresoDialogo;
    }

    public boolean isDialogoCompletado() {
        return dialogoCompletado;
    }

    public void setDialogoCompletado(boolean completado) {
        this.dialogoCompletado = completado;
    }

    public void verificarSiCompletado() {
        if (nodoDialActual != null && arbolDial != null) {
            dialogoCompletado = arbolDial.nodeIsLeaf(nodoDialActual);
        }
    }


    public Dialogo getAnteriorDialogo() {
        if (arbolDial.getFather(nodoDialActual) == null) {
            throw new IllegalArgumentException("No existen diálogos preiviamente mostrados. El diálogo actual coincide con el diálogo inicial.");
        }

        nodoDialActual = arbolDial.getFather(nodoDialActual);
        return nodoDialActual.getInfo();
    }

    public BinaryTreeNode<Dialogo> buscarNodo(String texto) {
        BinaryTreeNode<Dialogo> node = null;
        InDepthIterator<Dialogo> it = arbolDial.inDepthIterator();
        boolean found = false;

        while(it.hasNext() && !found) {
            node = it.nextNode();
            if (node.getInfo().getTexto().equals(texto)) {
                found = true;
            }
        }
        return found ? node : null;
    }

    public LinkedList<String> getOpcionesDialActual() {
        return nodoDialActual.getInfo().getOpciones();
    }
}

