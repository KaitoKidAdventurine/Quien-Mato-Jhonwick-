package Logica;

import DatosAuxiliaresLogica.EfectosEspeciales;
import DatosAuxiliaresLogica.Informacion;
import Logica.Sexo;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

public class Diario implements Serializable
{
    private static final long serialVersionUID = 1L;
    private LinkedList<Informacion> dialogosImportantes;

    public Diario()
    {
        this.dialogosImportantes = new LinkedList<Informacion>();
        inicializarDatos();
    }

    public void setDialogosImportantes() {
        this.dialogosImportantes = new LinkedList<Informacion>();
    }

    public LinkedList<Informacion> getDialogosImportantes()
    {
        return dialogosImportantes;
    }

    // Metodo para inicializar a todos los NPC
    public void inicializarDatos() {

        // 1. CASO
        Informacion victima = new Informacion("Henry Bennett", "32", Sexo.MASCULINO, "Economico");
        victima.agregarCualidad("Timido");
        victima.agregarCualidad("Reservado");
        victima.agregarCualidad("Poco amigable");
        victima.agregarCualidad("Tranquilo");
        dialogosImportantes.add(victima);

        // 2. JEFE
        Informacion jefe = new Informacion("Capitan", "45", Sexo.MASCULINO, "Jefe");
        jefe.agregarCualidad("Autoritario");
        jefe.agregarCualidad("estricto");
        jefe.agregarCualidad("responsable");
        jefe.agregarCualidad("serio");
        jefe.agregarCualidad("lider");
        dialogosImportantes.add(jefe);

        // 3. POLICÍA
        Informacion policia = new Informacion("Max Turner", "35", Sexo.MASCULINO, "Policia");
        policia.agregarCualidad("Justo");
        policia.agregarCualidad("valiente");
        policia.agregarCualidad("protector");
        policia.agregarCualidad("honesto");
        policia.agregarCualidad("disciplinado");
        dialogosImportantes.add(policia);

        // 4. DUEÑO
        Informacion dueno = new Informacion("Theodore Winslow", "50", Sexo.MASCULINO, "Dueño");
        dueno.agregarCualidad("Ambicioso");
        dueno.agregarCualidad("calculador");
        dueno.agregarCualidad("exitoso");
        dueno.agregarCualidad("dominante");
        dueno.agregarCualidad("materialista");
        dialogosImportantes.add(dueno);

        // 5. ESPOSA DEL DUEÑO
        Informacion esposa = new Informacion("Cate Sinclair", "42", Sexo.FEMENINO, "Esposa");
        esposa.agregarCualidad("Elegante");
        esposa.agregarCualidad("sofisticada");
        esposa.agregarCualidad("celosa");
        esposa.agregarCualidad("manipuladora");
        esposa.agregarCualidad("orgullosa");
        dialogosImportantes.add(esposa);

        // 6. SECRETARIA
        Informacion secretaria = new Informacion("Elizabeth Reed", "30", Sexo.FEMENINO, "Secretaria");
        secretaria.agregarCualidad("Eficiente");
        secretaria.agregarCualidad("discreta");
        secretaria.agregarCualidad("observadora");
        secretaria.agregarCualidad("leal");
        secretaria.agregarCualidad("organizada");
        dialogosImportantes.add(secretaria);

        // 7. GUÍA 1
        Informacion guia1 = new Informacion("William Harrington", "25", Sexo.MASCULINO, "Guia");
        guia1.agregarCualidad("Amigable");
        guia1.agregarCualidad("conocedor");
        guia1.agregarCualidad("paciente");
        guia1.agregarCualidad("comunicativo");
        guia1.agregarCualidad("servicial");
        dialogosImportantes.add(guia1);

        // 8. GUÍA 2
        Informacion guia2 = new Informacion("Alex Valle", "27", Sexo.MASCULINO, "Guia");
        guia2.agregarCualidad("Antipático");
        guia2.agregarCualidad("Prepotente");
        guia2.agregarCualidad("Avaricioso");
        guia2.agregarCualidad("Hipócrita");
        guia2.agregarCualidad("Deshonesto");
        dialogosImportantes.add(guia2);

        // 9. SEGURIDAD
        Informacion seguridad = new Informacion("Kai Collins", "40", Sexo.MASCULINO, "Seguridad");
        seguridad.agregarCualidad("Vigilante");
        seguridad.agregarCualidad("fuerte");
        seguridad.agregarCualidad("protector");
        seguridad.agregarCualidad("serio");
        seguridad.agregarCualidad("confiable");
        dialogosImportantes.add(seguridad);

        // 10. VAGABUNDO
        Informacion vagabundo = new Informacion("Leo Kuibbert", "55", Sexo.MASCULINO, "Vagabundo");
        vagabundo.agregarCualidad("Misterioso");
        vagabundo.agregarCualidad("observador");
        vagabundo.agregarCualidad("solitario");
        vagabundo.agregarCualidad("sabio");
        vagabundo.agregarCualidad("marginal");
        dialogosImportantes.add(vagabundo);

        // 11 Limpieza
        Informacion infoLimpieza = new Informacion("Victor Langley");
        infoLimpieza.setEdadNPC("69");
        infoLimpieza.setSexo(Sexo.MASCULINO);
        infoLimpieza.setOcupacion("Encargado de limpieza");
        infoLimpieza.getPersonalidad().add("Curioso");
        infoLimpieza.getPersonalidad().add("Observador");
        infoLimpieza.getPersonalidad().add("Buen corazón");
        infoLimpieza.getPersonalidad().add("Trabajador");
        infoLimpieza.getPersonalidad().add("Atento a los detalles");
        dialogosImportantes.add(infoLimpieza);

    }

    public String buscarNombre(String profesion) {
        String nombre = "";

        switch(profesion.toLowerCase()) {

            case "Caso":
            case "vic":
            case "Vic":
            case "Victima":
            case "victima":
            case "caso":
            case "investigacion":
            case "Investigacion":
            case "economico":
                nombre = "Henry Bennett";
                break;

            case "jefe":
            case "capitan":
            case "Capitan":
            case "Jefe":
            case "cap":
            case "Cap":
                nombre = "Capitan";
                break;

            case "policia":
            case "Policia":
            case "Oficial":
            case "ofi":
            case "oficial":
            case "policía":
            case "Policía":
                nombre = "Max Turner";
                break;

            case "dueño":
            case "dueno":
            case "duenno":
            case "Dueno":
            case "Dueño":
            case "propietario":
                nombre = "Theodore Winslow";
                break;

            case "esposa":
            case "esp":
            case "esposa del dueño":
            case "Esposa":
                nombre = "Cate Sinclair";
                break;

            case "Relacion de la Victima":
            case "Sec":
            case "sec" :
            case "Secretaria":
            case "secretaria":
                nombre = "Elizabeth Reed";
                break;

            case "guia":
            case "Guia 1":
            case "Guía 1":
            case "guia 1":
            case "Guía":
            case "guía":
            case "guía 1":
            case "Guia Principal":
                nombre = "Alex Valle";
                break;

            case "guia 2":
            case "guía 2":
            case "amante":
            case "Amante":
            case "aman":
            case "Aman":
                nombre = "William Harrington";

                break;

            case "Guardia":
            case "Seguridad":
            case "seguridad":
            case "guardia":
                nombre = "Kai Collins";
                break;

            case "vagabundo":
            case "mendigo":
                nombre = "Leo Kuibbert";
                break;

            case "Limpiador":
            case "limpiador":
            case "limp":
            case "Limp":
            case "Don Limpio":
            case "Victor":
            case "victor":
            case "Limpieza":
                    nombre = "Victor Langley";
                break;


            default:
                nombre = "Personaje no encontrado";
                break;
        }
        return nombre;
    }


    // Para guardar los dialogos mas importantes para cada NPC
    public void agregarDialogoImportante(String profesion, String informacion)
    {
        try
        {
            String nombreNPC = buscarNombre(profesion);
            boolean salida = false;
            Iterator<Informacion> II = dialogosImportantes.iterator();
            while(!salida && II.hasNext())
            {
                Informacion i = II.next();
                if (i.getNombreNPC().equals(nombreNPC))
                {
                    i.getListaDeDialogos().add(informacion);
                    salida = true;
                    EfectosEspeciales efe = EfectosEspeciales.getInstancia();
                    efe.efectoDeEscribirDiario();
                }
            }
        }

        catch (Exception e)
        {
            System.err.println("Error No se encontro el NPC: " + e.getMessage());
        }

    }


    public void agregarInformacion(Informacion informacion){
        dialogosImportantes.add(informacion);
    }
}