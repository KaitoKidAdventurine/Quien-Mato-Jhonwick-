package Logica;

import java.io.*;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import cu.edu.cujae.ceis.tree.binary.BinaryTree;

import javax.swing.*;

public class Jugador implements Serializable, Cloneable
{
    private static final long serialVersionUID = 1L;

    public String nombre;
    public BinaryTree<String> elecciones;
    public Telefono telefono;
    public Diario diario;
    public LinkedList<ObjetoEscenario> maletin;
    public Escenario escenarioActual;
    /* public int cont;
    public LinkedList<String> infoImportante;
    */


    public Jugador()
    {
        // Aqui se le pone el nombre del jugador
        this.nombre = "";
        this.elecciones = new BinaryTree<>();
        this.telefono = new Telefono();
        this.diario = new Diario();
        this.maletin = new LinkedList<ObjetoEscenario>();
        /*this.cont = 0;
        this.infoImportante = new LinkedList<>();
        */
    }


    // Razon por la que se usa el patron Clone: Se necesita poder hacer que el usaurio mientras juegue
    // pueda decidir si guardar o no. Por lo que CLone le permitira esto a la perfeccion porque como son
    // clones no se modificaran entre ellos cuando se desee mantener la inforamcion antes de guardar.

    @Override
    public Jugador clone() {
        Jugador copia = null;

        try {
            // Ver el codigo como una maquina de creacion de objetos

            // Primero se guarda el objeto en bytes ( O sea que se consigue el plano del objeto)
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Despues se crea la maquina de creacion de objeto
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            // Se le manda la informacion a la maquina o sea para que la guarde en un plano aparte
            oos.writeObject(this);
            oos.close();

            // Despues leemos el objeto ( El plano )
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            // Clonamos la informacion ( se le manda a la maquina )
            ObjectInputStream ois = new ObjectInputStream(bais);
            // La maquina saca el clon
            copia = (Jugador) ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return copia;
    }



    public String getNombre()
    {
        return nombre;
    }
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public BinaryTree<String> getElecciones()
    {
        return elecciones;
    }
    public void setElecciones(BinaryTree<String> elecciones)
    {
        this.elecciones = elecciones;
    }

    public Telefono getTelefono()
    {
        return telefono;
    }
    public void setTelefono(Telefono telefono)
    {
        this.telefono = telefono;
    }

    public Diario getDiario()
    {
        return diario;
    }
    public void setDiario(Diario diario)
    {
        this.diario = diario;
    }

    public Escenario getEscenarioActual() {
        return escenarioActual;
    }

    public void setEscenarioActual(Escenario escenarioActual) {
        this.escenarioActual = escenarioActual;
    }

    /*
    public int getCont() {return cont;}

    public void setCont(int cont) {this.cont = cont;}

    public LinkedList<String> getInfoImportante() {return infoImportante;}

    public void setInfoImportante(LinkedList<String> infoImportante) {
        this.infoImportante = infoImportante;
    }
     */


    public void usarObjeto(String nombre){
        Iterator<ObjetoEscenario> ii = maletin.iterator();
        boolean encontrado = false;
        while(ii.hasNext() && !encontrado){
            ObjetoEscenario aux = ii.next();
            if(aux.getNombre().equals(nombre)){
                encontrado =true;
                maletin.remove(aux);
            }
        }
    }
    public boolean revisarSiExisteObjetoEnMochila(String nombre) {

        Iterator<ObjetoEscenario> ii = maletin.iterator();
        boolean encontrado = false;

        while (ii.hasNext() && !encontrado) {
            ObjetoEscenario aux = ii.next();
            if (aux.getNombre().equals(nombre)) {
                encontrado = true;
            }
        }
        return encontrado;
    }
    // Metodos
    public void agregarAlMaletin(ObjetoEscenario o)
    {
        maletin.add(o);
    }

    public LinkedList<ObjetoEscenario> getMaletin() {
        return maletin;
    }

    public void setMaletin(LinkedList<ObjetoEscenario> maletin) {
        this.maletin = maletin;
    }
}