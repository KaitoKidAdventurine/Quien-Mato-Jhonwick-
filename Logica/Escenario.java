package Logica;

import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.general.GeneralTree;

import java.util.ArrayList;
import java.util.LinkedList;

public class Escenario {
    private String nombre;
    private String descripcion;
    private boolean investigado;
    private ArrayList<Interactuable> interactuables;
    private GeneralTree<Dialogo> arbolDial;
    private BinaryTreeNode<Dialogo> nodoDialActual;
    private int posList;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isInvestigado() {
        return investigado;
    }

    public void setInvestigado(boolean investigado) {
        this.investigado = investigado;
    }

    public ArrayList<Interactuable> getInteractuables() {
        return interactuables;
    }

    public void setInteractuables(ArrayList<Interactuable> interactuables) {
        this.interactuables = interactuables;
    }

    public void investigar() {
    }

    public void agregarInteractuable(Interactuable i) {
        interactuables.add(i);
    }

    public GeneralTree<Dialogo> getArbolDial() {
        return arbolDial;
    }

    public void setArbolDial(GeneralTree<Dialogo> arbolDial) {
        this.arbolDial = arbolDial;
    }

    public Dialogo getDialogoActual() {
        return nodoDialActual.getInfo();
    }

    public BinaryTreeNode<Dialogo> getNodoDialActual() {
        return nodoDialActual;
    }

    public Dialogo getDialogoSiguiente(int opcionElegida) {
        int cantOpciones;

        if (nodoDialActual != null && !(arbolDial.nodeIsLeaf(nodoDialActual))) {
            cantOpciones = arbolDial.nodeDegree(nodoDialActual);
            nodoDialActual = nodoDialActual.getLeft();

            if (opcionElegida >= 2 && opcionElegida > cantOpciones) {
                throw new IllegalArgumentException("La opción (pregunta elegida) sobrepsasa la cantidad de opciones disponibles (posibles dialogos a mostrar).");
            }

            //opcionElegida también es representado como la cantidad de llamadas getRight() para hallar el diálogoo que debe mostrarse.
            for (; opcionElegida >= 2; opcionElegida--) {
                nodoDialActual = nodoDialActual.getRight();
            }

            /*
             * La opcion elegida intenta escoger un diálogo que, por ahora, no puede mostrarse. Rebobinar al último diálogo.
             * Es posible mostrar un diálogo o mensaje de error antes de volver a mostrar el último diálogo para permitir otra oportunidad.
             */
            if (!(nodoDialActual.getInfo().isRevelable())) {
                nodoDialActual = arbolDial.getFather(nodoDialActual);
            }
        } else if (nodoDialActual == null) {
            nodoDialActual = (BinaryTreeNode<Dialogo>) arbolDial.getRoot();
        }

        return nodoDialActual.getInfo();
    }

    public Dialogo getAnteriorDialogo() {
        if (arbolDial.getFather(nodoDialActual) == null) {
            throw new IllegalArgumentException("No existen diálogos preiviamente mostrados. El diálogo actual coincide con el diálogo inicial.");
        }

        nodoDialActual = arbolDial.getFather(nodoDialActual);
        return nodoDialActual.getInfo();
    }

    public LinkedList<String> getOpcionesDialActual() {
        return nodoDialActual.getInfo().getOpciones();
    }
}
