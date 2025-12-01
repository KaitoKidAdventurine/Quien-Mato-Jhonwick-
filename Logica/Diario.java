package Logica;

import DatosAuxiliaresLogica.Informacion;

import java.util.Iterator;
import java.util.LinkedList;
import DatosAuxiliaresLogica.Informacion;

public class Diario 
{
    private LinkedList<Informacion> dialogosImportantes;

    public Diario()
    {
        this.dialogosImportantes = new LinkedList<Informacion>();
    }

    public void setDialogosImportantes() {
        this.dialogosImportantes = new LinkedList<Informacion>();
    }

    public LinkedList<Informacion> getDialogosImportantes()
    {
        return dialogosImportantes;
    }


    // Para guardar los dialogos mas importantes para cada NPC
    public void agregarDialogoImportante(String nombreNPC, String informacion)
    {
        try
        {
            boolean salida = false;
            Iterator<Informacion> II = dialogosImportantes.iterator();
            while(!salida && II.hasNext())
            {
               Informacion i = II.next();
                if (i.getNombreNPC().equals(nombreNPC))
                {
                    salida = true;
                    i.getListaDeDialogos().add(informacion);
                }
            }
        }

        catch (Exception e)
        {
            System.err.println("Error No se encontro: " + e.getMessage());
        }

    }
}