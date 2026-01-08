package Logica;

import DatosAuxiliaresLogica.Eventos;
import Interfaz.Escenarios.Almacen;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.general.GeneralTree;

import java.io.*;
import java.io.Serializable;
import javax.swing.*;
import java.time.LocalDate;
import java.util.*;

public class Partida implements Serializable, Cloneable
{
    private static final long serialVersionUID = 1L;

    private String idPartida;
    private LocalDate fechaInicio;
    // El estado asumo que es en que Acto esta el jugador
    private String estado;
    private ArrayList<Escenario> escenarios;
    private ArrayList<Escenario> escenariosMundo;
    private Jugador jugador;
    private Eventos eventos;
    private ArrayList<ArrayList<Dialogo>> dialogosCapitan;
    private boolean dialogosInicializados = false;

    public Partida() {
        // Para darle un valor al ID sera la partida que escoja el usuario.
        // O sea que cuando toque Nueva Partida 1, ese 1 sera el ID.
        // Modificar cuando se implemente.
        try {
            this.idPartida = "";
            this.fechaInicio = LocalDate.now();
            this.estado = "";
            this.escenarios = new ArrayList<Escenario>();
            this.escenariosMundo = new ArrayList<Escenario>();
            this.jugador = new Jugador();
            this.eventos = new Eventos();
            agregarEscenariosAutomaticamente();
            dialogosCapitan = new ArrayList<>();
            addEscenario();
            crearDialogosActo1();
            crearDialogosCapitan();


        } catch (Exception e) {
            System.out.println("ERROR en constructor de Partida: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }




    @Override
    public Partida clone() {
        Partida copia = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            oos.close();

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            copia = (Partida) ois.readObject();

            copia.restaurarTodosLosDialogos();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return copia;
    }

    // AÑADE ESTE MÉTODO NUEVO en Partida.java:
    public void restaurarTodosLosDialogos() {
        // Reconstruir estructura de árboles
        crearDialogosActo1();
        crearDialogosCapitan();

        // Restaurar estado de cada escenario
        for (Escenario escenario : escenariosMundo) {
            escenario.restaurarDialogo();
        }
    }


    public String getIdPartida()
    {
        return idPartida;
    }
    public void setIdPartida(String idPartida)
    {
        this.idPartida = idPartida;
    }

    public LocalDate getFechaInicio()
    {
        return fechaInicio;
    }
    public void setFechaInicio(LocalDate fechaInicio)
    {
        this.fechaInicio = fechaInicio;
    }

    public String getEstado()
    {
        return estado;
    }
    public void setEstado(String estado)
    {
        this.estado = estado;
    }


    public ArrayList<Escenario> getEscenarios() { return escenarios; }

    public void setEscenarios(ArrayList<Escenario> escenarios) { this.escenarios = escenarios; }

    public ArrayList<ArrayList<Dialogo>> getDialogosCapitan() {
        return dialogosCapitan;
    }

    public void setDialogosCapitan(ArrayList<ArrayList<Dialogo>> dialogosCapitan) {
        this.dialogosCapitan = dialogosCapitan;
    }

    // Metodos:

    public void buscarEscenarioNombre(String nom)
    {
        boolean salida = false;
        for(int i = 0; i < escenarios.size() && !salida; i++)
        {
            if(nom.equals(escenarios.get(i).getNombre()))
            {
                Juego.getInstance().getPartidaActual().getJugador().setEscenarioActual(escenarios.get(i));
                salida = true;
            }
        }
    }

    public Eventos getEventos() {
        return eventos;
    }

    public void setEventos(Eventos eventos) {
        this.eventos = eventos;
    }


    public void addEscenario() {

        Escenario acto1Parte1 = new Escenario("Acto 1-Secretaria", "Primera interaccion con la secretaria", true);
        Escenario acto1Parte2= new Escenario("Acto 1-Seguridad", "Primera interaccion con el de seguridad", true);
        Escenario acto1Parte3= new Escenario("Acto 1-Conserje", "Primera interaccion con el conserje", true);
        Escenario acto1Parte4= new Escenario("Acto 1-Dueño", "Primera interaccion con el dueño", true);
        Escenario acto1Parte5= new Escenario("Acto 1-Guias, ", "Primera interaccion con los guias", true);
        Escenario acto1Parte6= new Escenario("Acto 1-Vagabundo", "Primera interaccion con el vagabundo", true);
        Escenario acto1Parte7= new Escenario("Acto 1-Esposa", "Primera interaccion con la esposa", true);
        Escenario acto1Parte8= new Escenario("Acto 1-Policia", "Primera interaccion policia", true);
        Escenario acto1Parte9 = new Escenario("Acto 1-Guia 2", "Interaccion con segundo guia ", true);
        Escenario acto1Parte10 = new Escenario("Acto 1-Encontrar espada", "Encontrar espada", true);
        Escenario acto1Parte11 = new Escenario("Fin", "Fin", true);
        Escenario acto1Parte12 = new Escenario("Acto 1-Encontrar nota", "Encontrar nota", true);
        Escenario acto1Parte13 = new Escenario("Acto 1-Encontrar libro", "Encontrar libro", true);

        this.escenariosMundo.add(acto1Parte1);
        this.escenariosMundo.add(acto1Parte2);
        this.escenariosMundo.add(acto1Parte3);
        this.escenariosMundo.add(acto1Parte4);
        this.escenariosMundo.add(acto1Parte5);
        this.escenariosMundo.add(acto1Parte6);
        this.escenariosMundo.add(acto1Parte7);
        this.escenariosMundo.add(acto1Parte8);
        this.escenariosMundo.add(acto1Parte9);
        this.escenariosMundo.add(acto1Parte10);
        this.escenariosMundo.add(acto1Parte11);
        this.escenariosMundo.add(acto1Parte12);
        this.escenariosMundo.add(acto1Parte13);
    }


    public ArrayList<Escenario> getEscenariosMundo() {
        return escenariosMundo;
    }

    public void setEscenariosMundo(ArrayList<Escenario> escenarios) {
        this.escenariosMundo = escenarios;
    }

    public void agregarEscenariosAutomaticamente()
    {
        Escenario almacen = new Escenario("Almacen", "Lugar Donde se almacen obras de arte" , false);
        Escenario bannoPlantaBaja = new Escenario("Banno Planta Baja", "Banno de la Planta baja" , false);
        Escenario bannoPlantaAlta = new Escenario("Banno Planta Alta ", "Banno de la Planta alta" , false);
        Escenario callejon = new Escenario("Callejon", "Callejon de fuera del Museo" , false);
        Escenario entrada = new Escenario("Entrada", "Entrada Principal del Museo" , false);
        Escenario oficinaVictima = new Escenario("Oficina de la Victima", "Lugar donde fue encontrado el cuerpo" , false);
        Escenario oficinaDelJefe = new Escenario("Oficina del Jefe", "Lugar Donde trabaja el jefe de la victima" , false);
        Escenario alaEste = new Escenario("Ala este", "Ala este del museo" , false);
        Escenario alaNorte = new Escenario("Ala norte", "Ala norte del museo" , false);
        Escenario alaSur = new Escenario("Ala Sur", "Ala norte del museo" , false);
        Escenario oficinasPlantaBaja = new Escenario("Oficinas Planta Baja", "Lugar Donde se almacen obras de arte" , false);
        Escenario recepcion = new Escenario("Recepcion", "Recepcion del museo" , false);
        Escenario salaPlantaAlta = new Escenario("Sala Planta Alta", "Sala de la Planta Alta del museo" , false);
        Escenario exposicionDeAntiguedades= new Escenario("Exposicion de Antiguedades", "Lugar donde se exponen antiguedades del museo" , false);
        Escenario salaDeVigilancia = new Escenario("Sala de Vigilancia", "Lugar donde se encuentran las grabaciones del museo" , false);

        escenarios.add(almacen);
        escenarios.add(bannoPlantaBaja);
        escenarios.add(bannoPlantaAlta);
        escenarios.add(callejon);
        escenarios.add(entrada);
        escenarios.add(oficinaVictima);
        escenarios.add(oficinaDelJefe);
        escenarios.add(alaEste);
        escenarios.add(alaNorte);
        escenarios.add(alaSur);
        escenarios.add(oficinasPlantaBaja);
        escenarios.add(recepcion);
        escenarios.add(salaPlantaAlta);
        escenarios.add(exposicionDeAntiguedades);
        escenarios.add(salaDeVigilancia);
    }

    public Jugador getJugador() {

        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }


    private void crearDialogosCapitan() {
        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");
        ImageIcon nada = new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png");

        ArrayList<Dialogo> dialogo1= new ArrayList<>();
        Dialogo d1 = new Dialogo("- Bipp Bipp-.", "", nada, true);
        Dialogo d2 = new Dialogo("¿Quien sera a esta hora?", "Detective", detective, true);
        Dialogo d3 = new Dialogo("Oigo", "Detective", detective, true);
        Dialogo d4 = new Dialogo("Soy yo de nuevo. ¿Como te va con la investigacion?", "Capitan", nada, true);
        Dialogo d5 = new Dialogo("Normal, nada del otro mundo, un miercoles cualquiera. ", "Detective", detective, true);
        Dialogo d6 = new Dialogo("Eso no es lo que escuche, llame a tu oficial asignado y dice que no tienes ni idea de por donde empezar.", "Capitan", nada, true);
        Dialogo d7 = new Dialogo("Nada mas lejano de la realidad. Actualmente tengo a 4 personas sospechosas que podrian haber cometido el crimen.", "Detective", detective, true);
        Dialogo d8 = new Dialogo("Magnifico, solo espero que en tu apuro por ir a resolver el crimen rapido no te hayas olvidado de tus herramientas principales.", "Capitan", nada, true);
        Dialogo d9 = new Dialogo("No, los tengo a la mano.", "Detective", detective, true);
        Dialogo d10 = new Dialogo("¿Y estas seguro de como usarlos?", "Capitan", nada, true);
        Dialogo d11 = new Dialogo("Claro que si, llevo 11 años de experiencia.", "Detective", detective, true);
        Dialogo d12 = new Dialogo("Bueno, pero solo para estar seguros, dime como funcionan cada uno.", "Capitan", nada, true);
        Dialogo d13 = new Dialogo("Mira que eres pesado. Bien, en primer lugar tenemos al Diario. Encontradose primero de izquierda a derecha en la esquina superior derecha. Su funcion, llevar un registo de los sospechosos y del estado de la investigacion.", "Detective", detective, true);
        Dialogo d14 = new Dialogo("Bien, pero que me dices del telefono.", "Capitan", nada, true);
        Dialogo d15 = new Dialogo("El telefono es una herramienta que sirve para manipular la musica que escucho, y principalmente para llamarte a ti en caso de quedareme estancado, de igual forma tambien puedes manipular el aspecto del mismo, se encuentra como segundo de izquierda a derecha.", "Detective", detective, true);
        Dialogo d16 = new Dialogo("No esta mal. ¿Que harias si te encuentras un objeto que te pueda ayudar a resolver el crimen?", "Capitan", nada, true);
        Dialogo d17 = new Dialogo("Entonces lo pondria en mi maleta para usarlo en el futuro, con la cual puedo verificarlo cuando quiera y se encuentra como el tercer objeto de izquierda a derecha en la ezquina derecha de la pantalla.", "Detective", detective, true);
        Dialogo d18 = new Dialogo("Veo que no has perdido el toque. por ultimo. Explicame el ultimo objeto de la interfaz", "Capitan", nada, true);
        Dialogo d19 = new Dialogo("Sencillo. El menu interno, me permite guardar partida, salir al menu, salir al escritorio, y acceder a los ajustes, se encuentra al final de los objetos de la interfaz, en la ezquina superior derecha.", "Detective", detective, true);
        Dialogo d20 = new Dialogo("No esperaba menos de ti, como esperé estas listo para la acción." , "Capitan", nada, true);
        Dialogo d21 = new Dialogo("Siempre has sabido que soy tu mejor activo. ", "Detective", detective, true);
        Dialogo d22 = new Dialogo("No comentare nada al respecto a eso, pero respecto al caso solo te voy a decir que no la cagues, por alguna razon este caso parece preocuparle a los jefes. " , "Capitan", nada, true);
        Dialogo d23 = new Dialogo("Sabes que nunca lo haria, pero pos si acaso. ¿Cómo me recomendarias actuar? ", "Detective", detective, true);
        Dialogo d24 = new Dialogo("En primer lugar volver a entrevistar a todos, empieza por los que consideres mas gordos, luego ve por los pequeños a ver que encuentras. " , "Capitan", nada, true);
        Dialogo d25 = new Dialogo("Justo lo que pense que dirias. Entonces mi acercamiento inicial debe de ser, Dueño, Guia principal y Seguridad. Luego continuo con los demas. ", "Detective", detective, true);
        Dialogo d26 = new Dialogo("Parece que no tengo nada mas que enseñarte. Continua con la investigacion. " , "Capitan", nada, true);
        Dialogo d27 = new Dialogo("Si necesito consejo te vuelvo a llamar.", "Detective", detective, true);
        Dialogo d28 = new Dialogo("Esta bien. " , "Capitan", nada, true);
        Dialogo d29 = new Dialogo("-Biiiip.- " , "Capitan", nada, true);
        Dialogo d30 = new Dialogo("(Me trata como si fuera un niño pequeño, y tengo resueltos mas casos que el en toda su carrera de detective. En fin, todavia tengo un criminal que arrestar.", "Detective", detective, true);

        dialogo1.add(d1);
        dialogo1.add(d2);
        dialogo1.add(d3);
        dialogo1.add(d4);
        dialogo1.add(d5);
        dialogo1.add(d6);
        dialogo1.add(d7);
        dialogo1.add(d8);
        dialogo1.add(d9);
        dialogo1.add(d10);
        dialogo1.add(d11);
        dialogo1.add(d12);
        dialogo1.add(d13);
        dialogo1.add(d14);
        dialogo1.add(d15);
        dialogo1.add(d16);
        dialogo1.add(d17);
        dialogo1.add(d18);
        dialogo1.add(d19);
        dialogo1.add(d20);
        dialogo1.add(d21);
        dialogo1.add(d22);
        dialogo1.add(d23);
        dialogo1.add(d24);
        dialogo1.add(d25);
        dialogo1.add(d26);
        dialogo1.add(d27);
        dialogo1.add(d28);
        dialogo1.add(d29);
        dialogo1.add(d30);
        dialogosCapitan.add(dialogo1);

        ArrayList<Dialogo> dialogos2 = new ArrayList<>();
        Dialogo d31 = new Dialogo("Beeeppp", "", nada, true);
        Dialogo d32 = new Dialogo("Oigo", "Capitan", nada, true);
        Dialogo d33 = new Dialogo("Disculpe la molestia capitan.", "Detective", detective, true);
        Dialogo d34 = new Dialogo("No hay de que, sabia que volverias a llamar por algo que te acabo de decir.", "Capitan", nada, true);
        Dialogo d35 = new Dialogo("Recuerda, tienes que ir a hablar con el dueño del museo, el guardia de seguridad y el guia principal.", "Capitan", nada, true);
        Dialogo d36 = new Dialogo("¿Por alguna casualidad sabras donde se encuentran?", "Detective", detective, true);
        Dialogo d37 = new Dialogo("Yo no soy el que se encuentra en el terreno, ese eres tu. Aun asi puedo hacer una prediccion.", "Capitan", nada, true);
        Dialogo d38 = new Dialogo("Por lo general los de seguridad se quedan en los pisos bajos. Los dueños por alguna razon le gusta estar en lugares altos, y viendo el tamaño de la barriga en su expediente, este probablente le guste tener cerca un baño.", "Capitan", nada, true);
        Dialogo d39 = new Dialogo("En canto al guia principal no estoy seguro. ", "Capitan", nada, true);
        Dialogo d40 = new Dialogo("Gracias, has sido de mucha ayuda.", "Detective", detective, true);

        dialogos2.add(d31);
        dialogos2.add(d32);
        dialogos2.add(d33);
        dialogos2.add(d34);
        dialogos2.add(d35);
        dialogos2.add(d36);
        dialogos2.add(d37);
        dialogos2.add(d38);
        dialogos2.add(d39);
        dialogos2.add(d40);
        dialogosCapitan.add(dialogos2);

        ArrayList<Dialogo> dialogos3 = new ArrayList<>();
        Dialogo d41 = new Dialogo("Beeeppp", "", nada, true);
        Dialogo d42 = new Dialogo("Oigo", "Capitan", nada, true);
        Dialogo d43 = new Dialogo("Disculpe la molestia capitan.", "Detective", detective, true);
        Dialogo d44 = new Dialogo("No hay de que, sabia que volverias a llamar en cualquier momento. ¿Que necesitas ahora?", "Capitan", nada, true);
        Dialogo d45 = new Dialogo("Ya termine de tomar de nuevo la declaracion de dueño, el guia principal y el guardia de seguridad. ¿Como me recomiendas actuar ahora?", "Detective", detective, true);
        Dialogo d46 = new Dialogo("Mmmhh, si ya pasaste por los puestos mas importantes lo mejor seria entrevistar a los otros trabajadores del museo, a lo mejor puedes obtener algun tipo de informacion util acerca del caso, quien sabe.", "Capitan", nada, true);
        Dialogo d47 = new Dialogo("¿Eso tambien incluye a la esposa del dueño?", "Detective", detective, true);
        Dialogo d48 = new Dialogo("Por ahora mantenla al marge, despues de todo solo estaba de visita y no tendria ningun motivo real para llevar a cabo un asesinato. ", "Capitan", nada, true);
        Dialogo d49 = new Dialogo("Esta bien, ahora me pongo en funcion de ello. ", "Detective", detective, true);

        dialogos3.add(d41);
        dialogos3.add(d42);
        dialogos3.add(d43);
        dialogos3.add(d44);
        dialogos3.add(d45);
        dialogos3.add(d46);
        dialogos3.add(d47);
        dialogos3.add(d48);
        dialogos3.add(d49);

        dialogosCapitan.add(dialogos3);

        ArrayList<Dialogo> dialogos4 = new ArrayList<>();
        Dialogo d50 = new Dialogo("Beeeppp", "", nada, true);
        Dialogo d51 = new Dialogo("Oigo", "Capitan", nada, true);
        Dialogo d52 = new Dialogo("Disculpe la molestia capitan.", "Detective", detective, true);
        Dialogo d53 = new Dialogo("No hay de que, sabia que volverias a llamar en cualquier momento. ¿Que necesitas ahora?", "Capitan", nada, true);
        Dialogo d54 = new Dialogo("Ya termine de tomar de nuevo la declaracion del guia secundario, la secretaria y el conserje. ¿Como me recomiendas actuar ahora?", "Detective", detective, true);
        Dialogo d55 = new Dialogo("Si ya pasaste por todos los trabajadores se complica la tarea. Significa que deberas de buscar informacion mas escondida.", "Capitan", nada, true);
        Dialogo d56 = new Dialogo("Podria probar con el vagabundo.", "Detective", detective, true);
        Dialogo d57 = new Dialogo("¿Hay un vagabundo en el museo? ", "Capitan", nada, true);
        Dialogo d58 = new Dialogo("No exactamente en el museo, cerca de el, en un callejon. Algunos empleados han comentado verlo visto interactuando con la victima en mas de un acasion.", "Detective", detective, true);
        Dialogo d59 = new Dialogo("Parece que ya tienes una idea entonces.", "Capitan", nada, true);
        Dialogo d60 = new Dialogo("Si. Ahora me pongo en funcion de ello.", "Detective", detective, true);

        dialogos4.add(d50);
        dialogos4.add(d51);
        dialogos4.add(d52);
        dialogos4.add(d53);
        dialogos4.add(d54);
        dialogos4.add(d55);
        dialogos4.add(d56);
        dialogos4.add(d57);
        dialogos4.add(d58);
        dialogos4.add(d59);
        dialogos4.add(d60);
        dialogosCapitan.add(dialogos4);

        ArrayList<Dialogo> dialogos5 = new ArrayList<>();
        Dialogo d61 = new Dialogo("Beeeppp", "", nada, true);
        Dialogo d62 = new Dialogo("Oigo", "Capitan", nada, true);
        Dialogo d63 = new Dialogo("Disculpe la molestia capitan.", "Detective", detective, true);
        Dialogo d64 = new Dialogo("No hay de que, sabia que volverias a llamar en cualquier momento. ¿Que necesitas ahora?", "Capitan", nada, true);
        Dialogo d65 = new Dialogo("Ya tome la declaracion del vagaundo. En esencia, luego de ser sobornado con un pan me dijo que alguien entro a la 1:00 AM por la escalera de incendio y se metio en la oficina de la victima.", "Detective", detective, true);
        Dialogo d66 = new Dialogo("Eso quiere decir que el asesino conoce el lugar y sabia a quien iba a atacar, no fue coincidencia, fue un acto premeditado.", "Capitan", nada, true);
        Dialogo d67 = new Dialogo("Lo mismo opino. El problema, el vagabundo no pudo verle la cara al asesino.", "Detective", detective, true);
        Dialogo d68 = new Dialogo("Otro camino muerto entonces. Parece que tendras que probar suerte con la esposa del duaño, solo para estar seguros.", "Capitan", nada, true);
        Dialogo d69 = new Dialogo("Probare a ver que ta---.", "Detective", detective, true);
        Dialogo d70 = new Dialogo("Beeppp", "Capitan", nada, true);
        Dialogo d71 = new Dialogo("No me dejó terminar de hablar", "Detective", detective, true);

        dialogos5.add(d61);
        dialogos5.add(d62);
        dialogos5.add(d63);
        dialogos5.add(d64);
        dialogos5.add(d65);
        dialogos5.add(d66);
        dialogos5.add(d67);
        dialogos5.add(d68);
        dialogos5.add(d69);
        dialogos5.add(d70);
        dialogos5.add(d71);
        dialogosCapitan.add(dialogos5);

        ArrayList<Dialogo> dialogos6 = new ArrayList<>();
        Dialogo d72 = new Dialogo("Beeeppp", "", nada, true);
        Dialogo d73 = new Dialogo("Oigo", "Capitan", nada, true);
        Dialogo d74 = new Dialogo("Disculpe la molestia capitan.", "Detective", detective, true);
        Dialogo d75 = new Dialogo("No hay de que, sabia que volverias a llamar en cualquier momento. ¿Que necesitas ahora?", "Capitan", nada, true);
        Dialogo d76 = new Dialogo("Despues de hablar con la esposa del dueño vi en una foto de la semana pasada en su oficina una espada, que parecce tener las proporciones adecuadas para coincidir con el arma del crimen.", "Detective", detective, true);
        Dialogo d77 = new Dialogo("Bien hecho, solo te queda encontrar el arma.", "Capitan", nada, true);
        Dialogo d78 = new Dialogo("No te preocupes, buscare por todo el museo, si dicha arma sigue estando aqui creeme que la encontraré.", "Detective", detective, true);
        Dialogo d79 = new Dialogo("Ponte en ello.", "Capitan", nada, true);
        Dialogo d80 = new Dialogo("Por supuesto, por quien me tom---.", "Detective", detective, true);
        Dialogo d81 = new Dialogo("Beeppp", "Capitan", nada, true);
        Dialogo d82 = new Dialogo("No me dejó terminar de hablar", "Detective", detective, true);

        dialogos6.add(d72);
        dialogos6.add(d73);
        dialogos6.add(d74);
        dialogos6.add(d75);
        dialogos6.add(d76);
        dialogos6.add(d77);
        dialogos6.add(d78);
        dialogos6.add(d79);
        dialogos6.add(d80);
        dialogos6.add(d81);
        dialogos6.add(d82);
        dialogosCapitan.add(dialogos6);

        ArrayList<Dialogo> dialogos7 = new ArrayList<>();
        Dialogo d83 = new Dialogo("Beeeppp", "", nada, true);
        Dialogo d84 = new Dialogo("Oigo", "Capitan", nada, true);
        Dialogo d85 = new Dialogo("Disculpe la molestia capitan.", "Detective", detective, true);
        Dialogo d86 = new Dialogo("No hay de que, sabia que volverias a llamar en cualquier momento. ¿Que necesitas ahora?", "Capitan", nada, true);
        Dialogo d87 = new Dialogo("Ya encontramos el arma homicia, en estos momentos el oficial esta de camino al laboratorio para que lo analicen en busqueda de huellas y otros indicadores.", "Detective", detective, true);
        Dialogo d88 = new Dialogo("Bien hecho. Con  suerte nos dara suficiente evidencia para detectar al culpable.", "Capitan", nada, true);
        Dialogo d89 = new Dialogo("Tu lo dijiste, con suerte. Aunque tengamos el arma, todavia necesitamos saber el motivo.", "Detective", detective, true);
        Dialogo d90 = new Dialogo("Lo mas probable es que viera mas de la cuenta, sino los de altos rangos no me estuvieran presionando por cerrar el caso tan rapido.", "Capitan", nada, true);
        Dialogo d91 = new Dialogo("Intentare revisando la computadora de la victima, pero segun la secretaria que salio con el por un tiempo tiene una contraseña bastante fuerte.", "Detective", detective, true);
        Dialogo d92 = new Dialogo("Tendras que revisar el museo a ver si encuentras pistas acerca de la misma.", "Capitan", nada, true);
        Dialogo d93 = new Dialogo("Si, es mismo estaba pensan--", "Detective", detective, true);
        Dialogo d94 = new Dialogo("Beep", "Capitan", nada, true);
        Dialogo d95 = new Dialogo("No me dejó terminar de hablar, otra vez", "Detective", detective, true);
        Dialogo d96 = new Dialogo("Creo que le gusta cortar a la gente a mitad de frase.", "Detective", detective, true);

        dialogos7.add(d83);
        dialogos7.add(d84);
        dialogos7.add(d85);
        dialogos7.add(d86);
        dialogos7.add(d87);
        dialogos7.add(d88);
        dialogos7.add(d89);
        dialogos7.add(d90);
        dialogos7.add(d91);
        dialogos7.add(d92);
        dialogos7.add(d93);
        dialogos7.add(d94);
        dialogos7.add(d95);
        dialogos7.add(d96);
        dialogosCapitan.add(dialogos7);

    }

    public void crearDialogosActo1() {

        ImageIcon secretaria = new ImageIcon("DatosAuxiliares/Personajes/Secretaria.png");
        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");
        ImageIcon seguridad = new ImageIcon("DatosAuxiliares/Personajes/Seguridad.png");
        ImageIcon limpieza = new ImageIcon("DatosAuxiliares/Personajes/Conserje.png");
        ImageIcon dueno = new ImageIcon("DatosAuxiliares/Personajes/Dueño.png");
        ImageIcon guiaPrincipal = new ImageIcon("DatosAuxiliares/Personajes/Guia 1.png");
        ImageIcon guia = new ImageIcon("DatosAuxiliares/Personajes/Guia 2.png");
        ImageIcon vagabundo = new ImageIcon("DatosAuxiliares/Personajes/Vagabundo.png");
        ImageIcon nada = new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png");
        ImageIcon esposa = new ImageIcon("DatosAuxiliares/Personajes/Esposa.png");
        ImageIcon policia = new ImageIcon("DatosAuxiliares/Personajes/Policia.png");

        //Dialogo de la secretaria en la entrada
        //Breve Introduccion
        Dialogo d1 = new Dialogo("Señora, necesito hablar con usted nuevamente. Algunas cosas no me terminan de cuadrar.", "Detective",
                detective, true);
        Dialogo d2 = new Dialogo("¿Otra vez? Ya le dije todo lo que sabía. Pero adelante, pregunte lo que necesite.", "Secretaria",
                secretaria, true);

        // Primera decisión
        Dialogo desc1Sec = new Dialogo("Bueno quisiera saber...", "Detective", detective, true);
        desc1Sec.setOpciones(new LinkedList<String>(Arrays.asList(
                "¿Notó algo extraño en el comportamiento del guardia?",
                "¿Sabe si alguien más tenía acceso a su computadora?"
        )));

        // Camino vacío: guardia
        Dialogo respS1 = new Dialogo("¿El guardia? Siempre fue puntual y correcto. No hablaba mucho, pero hacía su trabajo, " +
                "para mi todo estaba como de costumbre.", "Secretaria", secretaria, true);

        // Camino vacío: acceso a computadora
        Dialogo respS2 = new Dialogo("No, su computadora era personal. Siempre la tenía bloqueada. Nunca compartía su contraseña con nadie.",
                "Secretaria", secretaria, true);

        // Camino principal: relación
        Dialogo d3 = new Dialogo("Ahora que lo pienso dijo que su relación con el economista era solo laboral pero, su lenguaje corporal decía otra cosa. ¿Está segura de que no había nada más?",
                "Detective", detective, true);
        Dialogo d4 = new Dialogo("No me gusta hablar de mi vida privada, detective. Pero si insiste...", "Secretaria",
                secretaria, true);
        Dialogo d5 = new Dialogo("No es curiosidad, es relevante para la investigación. Si hay algo que pueda ayudarnos a entender su estado emocional," +
                " necesito saberlo.", "Detective", detective, true);

        // Segunda decisión
        Dialogo desc2Sec = new Dialogo("Umm.. con todo lo anterior buena historia tenemos aquí, ya no se si deba preguntar más " +
                "o simplemente sacar mis propias conclusiones ", "Detective", detective, true);
        desc2Sec.setOpciones(new LinkedList<>(Arrays.asList(
                "¿Le tenía afecto?",
                "No quiero saber nada más"
        )));

        Dialogo d6 = new Dialogo("Tuvimos algo. Fue breve. Él decía que no quería complicaciones y bueno Yo... yo me ilusioné más de la cuenta.", "Secretaria",
                secretaria, true);

        // Dato clave 1
        //Juego.getInstance().getPartidaActual().getJugador().getDiario().agregarDialogoImportante("Secretaria", "La secretaria tuvo una relación amorosa con el economista.");

        Dialogo d7 = new Dialogo("Gracias por su sinceridad. Esto podría ser importante.", "Detective", detective, true);
        Dialogo d7a = new Dialogo("Vale, cualquier otra duda aquí estoy", "Secretaria", secretaria, true);

        // Tercera decisión
        Dialogo desc3Sec = new Dialogo("Vale interesante respuesta, pero ahora quiero saber ciertas cositas más o quizás solo me " +
                "quede con toda la información que tengo hasta el momento, ya veremos...", "Detective", detective, true);
        desc3Sec.setOpciones(new LinkedList<>(Arrays.asList(
                "¿Él le confiaba información personal?",
                "¿Recuerda si mencionó algo sobre su contraseña?",
                "No quiero saber nada más"
        )));

        // Camino vacío: información personal
        Dialogo respS3 = new Dialogo("Era reservado. Incluso cuando estábamos juntos, evitaba hablar de su trabajo. Decía que era mejor " +
                "no involucrar lo personal con lo laboral", "Secretaria", secretaria, true);

        // Camino principal: contraseña
        Dialogo d8 = new Dialogo("¿Su contraseña? No la sé completa. Pero recuerdo que usaba frases en latín. Una vez me pidió que anotara " +
                "algo para él...", "Secretaria", secretaria, true);
        Dialogo d9 = new Dialogo("Decía: 'Umbra y algunas palabras más'. No lo terminé de leer. Pero esa palabra estaba al principio.",
                "Secretaria", secretaria, true);
        Dialogo d9a = new Dialogo("Vale como prefiera usted, aquí estoy para dialogar y no solo de este tema... usted entiende",
                "Secretaria", secretaria, true);

        // Dato clave 2
        //Juego.getInstance().getPartidaActual().getJugador().getDiario().agregarDialogoImportante("Secretaria", "Fragmento de contraseña: comienza con 'Umbra mortis'.");

        Dialogo cierre = new Dialogo("Gracias. Eso será todo por ahora.", "Detective", detective, true);
        Dialogo despedida = new Dialogo("Espero que le sirva de algo. No me gusta recordar ciertas cosas.", "Secretaria", secretaria, true);

        BinaryTreeNode<Dialogo> node1 = new BinaryTreeNode<>(d1);
        BinaryTreeNode<Dialogo> node2 = new BinaryTreeNode<>(d2);
        BinaryTreeNode<Dialogo> decision1S = new BinaryTreeNode<>(desc1Sec);
        BinaryTreeNode<Dialogo> node3 = new BinaryTreeNode<>(respS1);
        BinaryTreeNode<Dialogo> node4 = new BinaryTreeNode<>(respS2);
        BinaryTreeNode<Dialogo> node5 = new BinaryTreeNode<>(d3);
        BinaryTreeNode<Dialogo> node6 = new BinaryTreeNode<>(d4);
        BinaryTreeNode<Dialogo> node7 = new BinaryTreeNode<>(d5);
        BinaryTreeNode<Dialogo> decision2S = new BinaryTreeNode<>(desc2Sec);
        BinaryTreeNode<Dialogo> node8 = new BinaryTreeNode<>(d6);
        BinaryTreeNode<Dialogo> node9 = new BinaryTreeNode<>(d7);
        BinaryTreeNode<Dialogo> node9a = new BinaryTreeNode<>(d7a);
        BinaryTreeNode<Dialogo> decision3S = new BinaryTreeNode<>(desc3Sec);
        BinaryTreeNode<Dialogo> node10 = new BinaryTreeNode<>(respS3);
        BinaryTreeNode<Dialogo> node11 = new BinaryTreeNode<>(d8);
        BinaryTreeNode<Dialogo> node12 = new BinaryTreeNode<>(d9);
        BinaryTreeNode<Dialogo> node12a = new BinaryTreeNode<>(d9a);
        BinaryTreeNode<Dialogo> node13 = new BinaryTreeNode<>(cierre);
        BinaryTreeNode<Dialogo> node13a = new BinaryTreeNode<>(cierre);
        BinaryTreeNode<Dialogo> node13b = new BinaryTreeNode<>(cierre);
        BinaryTreeNode<Dialogo> node13c = new BinaryTreeNode<>(cierre);
        BinaryTreeNode<Dialogo> node13d = new BinaryTreeNode<>(cierre);
        BinaryTreeNode<Dialogo> node14 = new BinaryTreeNode<>(despedida);
        BinaryTreeNode<Dialogo> node14a = new BinaryTreeNode<>(despedida);
        BinaryTreeNode<Dialogo> node14b = new BinaryTreeNode<>(despedida);
        BinaryTreeNode<Dialogo> node14c = new BinaryTreeNode<>(despedida);
        BinaryTreeNode<Dialogo> node14d = new BinaryTreeNode<>(despedida);

        GeneralTree<Dialogo> auxTree1 = new GeneralTree<>();

        auxTree1.insertNode(node1, null);
        auxTree1.insertNode(node2, node1);
        auxTree1.insertNode(decision1S, node2);
        auxTree1.insertNode(node3, decision1S);
        auxTree1.insertNode(node4, decision1S);

        auxTree1.insertNode(node5, node3);
        auxTree1.insertNode(node6, node5);
        auxTree1.insertNode(node7, node6);
        auxTree1.insertNode(decision2S, node7);
        auxTree1.insertNode(node8, decision2S);
        auxTree1.insertNode(node9, node8);
        auxTree1.insertNode(node9a, decision2S);

        auxTree1.insertNode(decision3S, node4);
        auxTree1.insertNode(node10, decision3S);
        auxTree1.insertNode(node11, decision3S);
        auxTree1.insertNode(node12, node11);
        auxTree1.insertNode(node12a, decision3S);

        auxTree1.insertNode(node13, node9a);
        auxTree1.insertNode(node14, node13);

        auxTree1.insertNode(node13b, node9);
        auxTree1.insertNode(node14b, node13b);

        auxTree1.insertNode(node13d, node10);
        auxTree1.insertNode(node14d, node13d);

        auxTree1.insertNode(node13a, node12a);
        auxTree1.insertNode(node14a, node13a);

        auxTree1.insertNode(node13c, node12);
        auxTree1.insertNode(node14c, node13c);

        escenariosMundo.get(0).setArbolDial(auxTree1);

        //Guardia de la Sala de Seguridad1
        Dialogo s1 = new Dialogo("Hola Guardia, necesito revisar algunos detalles sobre las cámaras de seguridad.", "Detective", detective, true);
        Dialogo s2 = new Dialogo("¿Otra vez? Bueno... adelante. Aunque no sé si puedo decirle algo que no haya dicho ya.", "Seguridad", seguridad, true);
        Dialogo s3 = new Dialogo("A veces los detalles más pequeños son los que hacen la diferencia.", "Detective", detective, true);
        Dialogo s4 = new Dialogo("Supongo... aunque no me gusta pensar que algo se me pudo haber pasado.", "Seguridad", seguridad, true);

        // Primera decisión
        Dialogo desc1Seg = new Dialogo("Bueno quisiera hacerle unas preguntas", "Detective", detective, true);
        desc1Seg.setOpciones(new LinkedList<>(Arrays.asList(
                "¿Alguien más tiene acceso a la sala de seguridad?",
                "¿Notó algo extraño en los monitores?"
        )));

        // Camino vacío: acceso a la sala
        Dialogo respSeg1a = new Dialogo("Solo yo tengo acceso directo. El jefe también, pero rara vez entra. Confían en que yo lo manejo todo.", "Seguridad", seguridad, true);
        Dialogo respSeg1b = new Dialogo("Además, todo queda registrado. Si alguien más hubiera entrado, lo sabría.", "Seguridad", seguridad, true);

        // Camino vacío: monitores
        Dialogo respSeg2a = new Dialogo("Los monitores estaban normales. A veces hay interferencias menores, pero nada fuera de lo común... o al menos eso creo.", "Seguridad", seguridad, true);
        Dialogo respSeg2b = new Dialogo("Aunque ahora que lo pienso, hubo un parpadeo en la pantalla 3... pero fue tan rápido que ni lo reporté.", "Seguridad", seguridad, true);

        // Camino principal: cámaras
        Dialogo c5 = new Dialogo("¿Todas las cámaras funcionaban esa noche? Necesito estar seguro.", "Detective", detective, true);
        Dialogo c6 = new Dialogo("Bueno... en realidad no. Hubo un par de fallos. Lo reporté, pero no pensé que fueran importantes.", "Seguridad", seguridad, true);
        Dialogo c7 = new Dialogo("¿Qué tipo de fallos?", "Detective", detective, true);
        Dialogo c8 = new Dialogo("Desconexiones, cortes breves. Nada que activara alarmas, pero sí lo suficiente para dejar huecos.", "Seguridad", seguridad, true);

        // Segunda decisión
        Dialogo desc2Seg = new Dialogo("Bueno quizas le haga unas preguntas o tal vez me quede con la información que ya tengo...",
                "Detective", detective, true);
        desc2Seg.setOpciones(new LinkedList<>(Arrays.asList(
                "¿Es común que se caigan?",
                "¿Qué cámaras fallaron exactamente?",
                "No quiero saber nada más"
        )));

        // Camino vacío: frecuencia de fallos
        Dialogo respSeg3a = new Dialogo("A veces se reinician solas. El sistema es viejo. Pero nunca había pasado algo así en una noche tan importante.", "Seguridad", seguridad, true);
        Dialogo respSeg3b = new Dialogo("De todos modos, siempre reviso los registros al final del turno. No me gusta dejar cabos sueltos.", "Seguridad", seguridad, true);

        // Camino principal: cámaras específicas
        Dialogo c9 = new Dialogo("La del callejón trasero se cayó entre la 1 y las 3 de la mañana. Pensé que era un corte de señal temporal.", "Seguridad", seguridad, true);
        Dialogo c10 = new Dialogo("Y la de la sala donde encontraron al economista... se congeló por unos segundos justo a la hora estimada del deceso.", "Seguridad", seguridad, true);
        Dialogo c11 = new Dialogo("No hay grabación de ese momento. Solo un salto en el tiempo. Como si alguien hubiese querido borrar algo.", "Seguridad", seguridad, true);


        //Juego.getInstance().getPartidaActual().getJugador().getDiario().agregarDialogoImportante("Guardia", "La cámara del callejón dejó de funcionar entre la 1:00 y las 3:00 a.m.");
        //Juego.getInstance().getPartidaActual().getJugador().getDiario().agregarDialogoImportante("Guardia", "La cámara de la escena del crimen se congeló brevemente durante el momento del asesinato.");

        Dialogo d12 = new Dialogo("¿Y no le pareció sospechoso?", "Detective", detective, true);
        Dialogo d13 = new Dialogo("Ahora que lo dice... sí. Pero en el momento pensé que era una coincidencia. No quería sonar paranoico.",
                "Seguridad", seguridad, true);
        Dialogo d14 = new Dialogo("A veces uno se acostumbra tanto a las fallas técnicas que deja de ver lo que tiene delante.",
                "Seguridad", seguridad, true);

        Dialogo respSeg4 = new Dialogo("Vale si necesita algo más aquí me tiene", "Seguridad", seguridad, true);

        Dialogo cierre2 = new Dialogo("Gracias. Esto le da un giro a las cosas, aquí cada detalle cuenta.", "Detective", detective, true);
        Dialogo despedida2 = new Dialogo("Si necesita revisar los registros, están en el servidor. Aunque... no sé si todo quedó grabado como debería.",
                "Seguridad", seguridad, true);

        BinaryTreeNode<Dialogo> node15 = new BinaryTreeNode<>(s1);
        BinaryTreeNode<Dialogo> node16 = new BinaryTreeNode<>(s2);
        BinaryTreeNode<Dialogo> node17 = new BinaryTreeNode<>(s3);
        BinaryTreeNode<Dialogo> node18 = new BinaryTreeNode<>(s4);
        BinaryTreeNode<Dialogo> decision1Seg = new BinaryTreeNode<>(desc1Seg);
        BinaryTreeNode<Dialogo> node19 = new BinaryTreeNode<>(respSeg1a);
        BinaryTreeNode<Dialogo> node20 = new BinaryTreeNode<>(respSeg1b);
        BinaryTreeNode<Dialogo> node21 = new BinaryTreeNode<>(respSeg2a);
        BinaryTreeNode<Dialogo> node22 = new BinaryTreeNode<>(respSeg2b);
        BinaryTreeNode<Dialogo> node23 = new BinaryTreeNode<>(c5);
        BinaryTreeNode<Dialogo> node24 = new BinaryTreeNode<>(c6);
        BinaryTreeNode<Dialogo> node25 = new BinaryTreeNode<>(c7);
        BinaryTreeNode<Dialogo> node26 = new BinaryTreeNode<>(c8);
        BinaryTreeNode<Dialogo> decision2Seg = new BinaryTreeNode<>(desc2Seg);
        BinaryTreeNode<Dialogo> node27 = new BinaryTreeNode<>(respSeg3a);
        BinaryTreeNode<Dialogo> node28 = new BinaryTreeNode<>(respSeg3b);
        BinaryTreeNode<Dialogo> node29 = new BinaryTreeNode<>(c9);
        BinaryTreeNode<Dialogo> node30 = new BinaryTreeNode<>(c10);
        BinaryTreeNode<Dialogo> node31 = new BinaryTreeNode<>(c11);
        BinaryTreeNode<Dialogo> node32 = new BinaryTreeNode<>(d12);
        BinaryTreeNode<Dialogo> node33 = new BinaryTreeNode<>(d13);
        BinaryTreeNode<Dialogo> node34 = new BinaryTreeNode<>(d14);
        BinaryTreeNode<Dialogo> resp4 = new BinaryTreeNode<>(respSeg4);
        BinaryTreeNode<Dialogo> node35 = new BinaryTreeNode<>(cierre2);
        BinaryTreeNode<Dialogo> node35a = new BinaryTreeNode<>(cierre2);
        BinaryTreeNode<Dialogo> node35b = new BinaryTreeNode<>(cierre2);
        BinaryTreeNode<Dialogo> node35c = new BinaryTreeNode<>(cierre2);
        BinaryTreeNode<Dialogo> node36 = new BinaryTreeNode<>(despedida2);
        BinaryTreeNode<Dialogo> node36a = new BinaryTreeNode<>(despedida2);
        BinaryTreeNode<Dialogo> node36b = new BinaryTreeNode<>(despedida2);
        BinaryTreeNode<Dialogo> node36c = new BinaryTreeNode<>(despedida2);

        GeneralTree<Dialogo> auxTree2 = new GeneralTree<>();

        auxTree2.insertNode(node15, null);
        auxTree2.insertNode(node16, node15);
        auxTree2.insertNode(node17, node16);
        auxTree2.insertNode(node18, node17);
        auxTree2.insertNode(decision1Seg, node18);
        auxTree2.insertNode(node19, decision1Seg);
        auxTree2.insertNode(node20, node19);
        auxTree2.insertNode(node21, decision1Seg);
        auxTree2.insertNode(node22, node21);
        auxTree2.insertNode(node35, node22);
        auxTree2.insertNode(node36, node35);

        auxTree2.insertNode(node23, node20);
        auxTree2.insertNode(node24, node23);
        auxTree2.insertNode(node25, node24);
        auxTree2.insertNode(node26, node25);
        auxTree2.insertNode(decision2Seg, node26);
        auxTree2.insertNode(node27, decision2Seg);
        auxTree2.insertNode(node28, node27);
        auxTree2.insertNode(node35a, node28);
        auxTree2.insertNode(node36a, node35a);

        auxTree2.insertNode(node29, decision2Seg);
        auxTree2.insertNode(node30, node29);
        auxTree2.insertNode(node31, node30);
        auxTree2.insertNode(node32, node31);
        auxTree2.insertNode(node33, node32);
        auxTree2.insertNode(node34, node33);
        auxTree2.insertNode(node35b, node34);
        auxTree2.insertNode(node36b, node35b);

        auxTree2.insertNode(resp4, decision2Seg);
        auxTree2.insertNode(node35c, resp4);
        auxTree2.insertNode(node36c, node35c);

        escenariosMundo.get(1).setArbolDial(auxTree2);

        // Dialogo con el Conserje en el baño inferior
        Dialogo b1 = new Dialogo("Buenas noches Conserje, ¿Tiene un momento? Necesito hacerle unas preguntas.", "Detective", detective, true);
        Dialogo b2 = new Dialogo("Claro jefe. Aunque no sé si puedo ayudar mucho... yo solo limpio por aquí abajo.", "Conserje", limpieza, true);
        Dialogo b3 = new Dialogo("A veces los que limpian ven más de lo que creen. ¿Ha notado algo fuera de lugar últimamente?", "Detective", detective, true);
        Dialogo b4 = new Dialogo("Bueno... depende de lo que llame 'fuera de lugar'. Hay cosas raras todo el tiempo en este museo.", "Conserje", limpieza, true);

        // Primera decisión
        Dialogo desc1Limp = new Dialogo("Bueno vamos directo al grano...", "Detective", detective, true);
        desc1Limp.setOpciones(new LinkedList<>(Arrays.asList(
                "¿Notó algo raro en los baños esta semana?",
                "¿Ha visto a alguien bajar aquí cuando no debería?",
                "¿Encontró algún objeto que no debería estar ahí?"
        )));

        // Camino vacío: baños
        Dialogo respLimp1 = new Dialogo("Los baños estaban limpios. Bueno, dentro de lo que cabe. Nada fuera de lo común.", "Conserje", limpieza, true);
        Dialogo respLimp1b = new Dialogo("Aunque alguien dejó una taza de café en el lavamanos... pero eso pasa más de lo que imagina.", "Conserje", limpieza, true);

        // Camino vacío: gente bajando
        Dialogo respLimp2 = new Dialogo("A veces escucho pasos, pero no sé quién baja. Yo no me meto con nadie, solo barro y subo.", "Conserje", limpieza, true);
        Dialogo respLimp2b = new Dialogo("Una vez vi a la esposa del director pasar por aquí, no me saludó. Iba apurada, como si buscara algo.", "Conserje", limpieza, true);

        // Camino principal: objetos encontrados
        Dialogo respLimp3 = new Dialogo("¿Objetos? Bueno... encontré unas llaves hace unos días. Cerca del depósito, tiradas en el suelo.", "Conserje", limpieza, true);
        Dialogo respLimp3b = new Dialogo("Pensé que eran del almacén, así que las guardé. Pero luego se me olvidaron en el bolsillo.", "Conserje", limpieza, true);

        // Segunda decisión
        Dialogo desc2Limp = new Dialogo("Que interesante....", "Detective", detective, true);
        desc2Limp.setOpciones(new LinkedList<>(Arrays.asList(
                "¿Qué tipo de llaves eran?",
                "Listo con esto tengo no quiero saber nada más",
                "¿Dónde están esas llaves ahora?"
        )));

        // Camino vacío: tipo de llaves
        Dialogo respLimp4a = new Dialogo("Eran metálicas, con una etiqueta vieja. No decía mucho, solo un número.", "Conserje", limpieza, true);
        Dialogo respLimp4b = new Dialogo("No me fijé mucho, la verdad. Las guardé en el bolsillo como le comenté y seguí limpiando.", "Conserje", limpieza, true);

        Dialogo u7 = new Dialogo("Vale, cualquier otra cosa que necesite estaré por aquí", "Conserje", limpieza, true);

        // Camino principal: ubicación actual
        Dialogo u8 = new Dialogo("En mi casa. Me di cuenta cuando vacié el uniforme. Pensaba traerlas hoy, pero con todo esto...", "Conserje", limpieza, true);
        Dialogo u9 = new Dialogo("¿Se da cuenta de lo importante que puede ser eso? Necesito esas llaves de inmediato.", "Detective", detective, true);
        Dialogo u10 = new Dialogo("Sí, sí, lo entiendo. Vivo cerca. Si quiere, puedo ir por ellas ahora mismo.", "Conserje", limpieza, true);

        // Dato clave
        //Juego.getInstance().getPartidaActual().getJugador().getDiario().agregarDialogoImportante("Limpiador", "El limpiador encontró las llaves del almacén y las llevó por accidente a su casa.");

        Dialogo u11 = new Dialogo("Hágalo. Pero por ahora, no le diga a nadie que las encontró. ¿De acuerdo?", "Detective", detective, true);
        Dialogo u12 = new Dialogo("¿En secreto? Bueno... si usted lo dice. No quiero meterme en líos.", "Conserje", limpieza, true);

        Dialogo cierreEsp = new Dialogo("Gracias. Avíseme en cuanto las tenga.", "Detective", detective, true);
        Dialogo despedidaEsp = new Dialogo("Sí, voy saliendo ya mismo.", "Conserje", limpieza, true);

        Dialogo cierre3 = new Dialogo("Muchas gracias por toda la información brindada, aunque no lo crea todo es de gran " +
                "ayuda..", "Detective", detective, true);
        Dialogo despedida3 = new Dialogo("Un placer, me alegra ser util en su investigación.", "Conserje", limpieza, true);

        BinaryTreeNode<Dialogo> node37 = new BinaryTreeNode<>(b1);
        BinaryTreeNode<Dialogo> node38 = new BinaryTreeNode<>(b2);
        BinaryTreeNode<Dialogo> node39 = new BinaryTreeNode<>(b3);
        BinaryTreeNode<Dialogo> node40 = new BinaryTreeNode<>(b4);
        BinaryTreeNode<Dialogo> decisionLimp1 = new BinaryTreeNode<>(desc1Limp);
        BinaryTreeNode<Dialogo> node41 = new BinaryTreeNode<>(respLimp1);
        BinaryTreeNode<Dialogo> node42 = new BinaryTreeNode<>(respLimp1b);
        BinaryTreeNode<Dialogo> node43 = new BinaryTreeNode<>(respLimp2);
        BinaryTreeNode<Dialogo> node44 = new BinaryTreeNode<>(respLimp2b);
        BinaryTreeNode<Dialogo> node45 = new BinaryTreeNode<>(respLimp3);
        BinaryTreeNode<Dialogo> node46 = new BinaryTreeNode<>(respLimp3b);
        BinaryTreeNode<Dialogo> decisionLimp2 = new BinaryTreeNode<>(desc2Limp);
        BinaryTreeNode<Dialogo> node47 = new BinaryTreeNode<>(respLimp4a);
        BinaryTreeNode<Dialogo> node48 = new BinaryTreeNode<>(respLimp4b);
        BinaryTreeNode<Dialogo> node49 = new BinaryTreeNode<>(u7);
        BinaryTreeNode<Dialogo> node50 = new BinaryTreeNode<>(u8);
        BinaryTreeNode<Dialogo> node51 = new BinaryTreeNode<>(u9);
        BinaryTreeNode<Dialogo> node52 = new BinaryTreeNode<>(u10);
        BinaryTreeNode<Dialogo> node53 = new BinaryTreeNode<>(u11);
        BinaryTreeNode<Dialogo> node54 = new BinaryTreeNode<>(u12);
        BinaryTreeNode<Dialogo> node55 = new BinaryTreeNode<>(cierreEsp);
        BinaryTreeNode<Dialogo> node55a = new BinaryTreeNode<>(cierre3);
        BinaryTreeNode<Dialogo> node55b = new BinaryTreeNode<>(cierre3);
        BinaryTreeNode<Dialogo> node55c = new BinaryTreeNode<>(cierre3);
        BinaryTreeNode<Dialogo> node55d = new BinaryTreeNode<>(cierre3);
        BinaryTreeNode<Dialogo> node56 = new BinaryTreeNode<>(despedidaEsp);
        BinaryTreeNode<Dialogo> node56a = new BinaryTreeNode<>(despedida3);
        BinaryTreeNode<Dialogo> node56b = new BinaryTreeNode<>(despedida3);
        BinaryTreeNode<Dialogo> node56c = new BinaryTreeNode<>(despedida3);
        BinaryTreeNode<Dialogo> node56d = new BinaryTreeNode<>(despedida3);

        GeneralTree<Dialogo> auxTree3 = new GeneralTree<>();

        auxTree3.insertNode(node37, null);
        auxTree3.insertNode(node38, node37);
        auxTree3.insertNode(node39, node38);
        auxTree3.insertNode(node40, node39);
        auxTree3.insertNode(decisionLimp1, node40);
        auxTree3.insertNode(node41, decisionLimp1);
        auxTree3.insertNode(node42, node41);
        auxTree3.insertNode(node55a, node42);
        auxTree3.insertNode(node56a, node55a);

        auxTree3.insertNode(node43, decisionLimp1);
        auxTree3.insertNode(node44, node43);
        auxTree3.insertNode(node55b, node44);
        auxTree3.insertNode(node56b, node55b);

        auxTree3.insertNode(node45, decisionLimp1);
        auxTree3.insertNode(node46, node45);
        auxTree3.insertNode(decisionLimp2, node46);
        auxTree3.insertNode(node47, decisionLimp2);
        auxTree3.insertNode(node48, node47);
        auxTree3.insertNode(node55c, node48);
        auxTree3.insertNode(node56c, node55c);

        auxTree3.insertNode(node49, decisionLimp2);
        auxTree3.insertNode(node55d, node49);
        auxTree3.insertNode(node56d, node55d);

        auxTree3.insertNode(node50, decisionLimp2);
        auxTree3.insertNode(node51, node50);
        auxTree3.insertNode(node52, node51);
        auxTree3.insertNode(node53, node52);
        auxTree3.insertNode(node54, node53);
        auxTree3.insertNode(node55, node54);
        auxTree3.insertNode(node56, node55);

        escenariosMundo.get(2).setArbolDial(auxTree3);

        // Dueño en la oficina
        Dialogo j1 = new Dialogo("Hola señor, no pude evitar distraerme con todos esos trofeos. Necesito hacerle unas preguntas," +
                " si no le molesta.", "Detective", detective, true);
        Dialogo j2 = new Dialogo("¿Otra ronda de interrogatorios, detective? ¿No se cansa de buscar fantasmas?", "Dueno", dueno, true);
        Dialogo j3 = new Dialogo("Solo intento entender lo que pasó. Cualquier detalle puede ser de suma importancia.", "Detective", detective, true);
        Dialogo j4 = new Dialogo("Claro, claro... aunque si me pregunta a mí, esto ya está más que resuelto. Pero adelante, dispare.", "Dueno", dueno, true);

        // Primera decisión
        Dialogo desc1Due = new Dialogo("Que interesante su afirmación, pero ahora dígame...", "Detective", detective, true);
        desc1Due.setOpciones(new LinkedList<>(Arrays.asList(
                "¿Conocía bien al economista?",
                "¿Dónde estaba usted cuando ocurrió el incidente?",
                "Bonita oficina... ¿puedo preguntarle sobre la decoración?"
        )));

        // Camino vacío: relación con el economista
        Dialogo respDue1a = new Dialogo("Lo conocía lo justo. Buen tipo, algo aburrido. Siempre hablando de números y teorías. " +
                "Yo prefiero el arte, ya sabe.", "Dueno", dueno, true);
        Dialogo respDue1b = new Dialogo("Aunque debo admitir que tenía una forma muy... muy peculiar de ver el mundo. Como si siempre" +
                " supiera algo que los demás no.", "Dueno", dueno, true);

        // Camino vacío: coartada
        Dialogo respDue2a = new Dialogo("Estaba en casa, durmiendo como un bebé. Pregúntele a mi esposa si no me cree... aunque no sé" +
                " si le conviene despertarla.", "Dueno", dueno, true);
        Dialogo respDue2b = new Dialogo("Además, si yo hubiera querido matar a alguien, créame que no dejaría pistas. Pero no se lo " +
                "tome a mal, detective.", "Dueno", dueno, true);

        // Camino que desbloquea segunda decisión
        Dialogo desbloquear1 = new Dialogo("¿Preguntando cosas personales? Vaya, detective... pensé que esto era una investigación, " +
                "no una cita.", "Dueno", dueno, true);
        Dialogo desbloquear2 = new Dialogo("Pero adelante, pregunte. Aunque no prometo responder con seriedad.", "Dueno", dueno, true);

        // Segunda decisión (solo aparece si se elige la opción correcta)
        Dialogo desc2Due = new Dialogo("Usted responda ya yo veré ..", "Detective", detective, true);
        desc2Due.setOpciones(new LinkedList<>(Arrays.asList("No quiero saber nada más", "¿Qué son todos esos trofeos en su estantería?")));

        // Camino principal: trofeos
        Dialogo respDue3a = new Dialogo("¿Los trofeos? Ah, veo que tiene buen ojo. Son de cuando practicaba esgrima. Era bastante" +
                " bueno, ¿sabe?", "Dueno", dueno, true);
        Dialogo respDue3b = new Dialogo("Campeón regional tres años seguidos. Aunque ahora solo es buen material para presumir con " +
                "los visitantes importantes.", "Dueno", dueno, true);


        //Juego.getInstance().getPartidaActual().getJugador().getDiario().agregarDialogoImportante("Jefe del museo", "El jefe practicó esgrima cuando era joven.");

        Dialogo d15 = new Dialogo("¿Quiere una demostración? No se preocupe, no suelo atacar a los invitados... a menos que me acusen" +
                " de asesinato jajajaj.", "Dueno", dueno, true);

        Dialogo salida1 = new Dialogo("Si quiere saber algo más ya sabe aquí estoy", "Dueno", dueno, true);

        //Tercera decisión
        Dialogo desc3Due = new Dialogo("Usted responda ya yo veré ..", "Detective", detective, true);
        desc3Due.setOpciones(new LinkedList<>(Arrays.asList(
                "¿Aún practica esgrima?",
                "No quiero saber nada más"
        )));

        // Camino vacío: práctica actual
        Dialogo respDue4 = new Dialogo("Ya no. Las rodillas no perdonan, detective. Ahora solo practico con el control remoto y el" +
                " sillón reclinable.", "Dueno", dueno, true);

        Dialogo salida2 = new Dialogo("Si quiere saber algo más ya sabe aquí estoy", "Dueno", dueno, true);

        Dialogo cierre4 = new Dialogo("Gracias por su tiempo. Si recuerda algo más, estaré cerca.", "Detective", detective, true);
        Dialogo despedida4 = new Dialogo("Cuando quiera, detective. Aunque si me pregunta, esto es una pérdida de tiempo con corbata.", "Dueno", dueno, true);

        BinaryTreeNode<Dialogo> node57 = new BinaryTreeNode<>(j1);
        BinaryTreeNode<Dialogo> node58 = new BinaryTreeNode<>(j2);
        BinaryTreeNode<Dialogo> node59 = new BinaryTreeNode<>(j3);
        BinaryTreeNode<Dialogo> node60 = new BinaryTreeNode<>(j4);
        BinaryTreeNode<Dialogo> decisionDue1 = new BinaryTreeNode<>(desc1Due);
        BinaryTreeNode<Dialogo> node61 = new BinaryTreeNode<>(respDue1a);
        BinaryTreeNode<Dialogo> node62 = new BinaryTreeNode<>(respDue1b);
        BinaryTreeNode<Dialogo> node63 = new BinaryTreeNode<>(respDue2a);
        BinaryTreeNode<Dialogo> node64 = new BinaryTreeNode<>(respDue2b);
        BinaryTreeNode<Dialogo> node65 = new BinaryTreeNode<>(desbloquear1);
        BinaryTreeNode<Dialogo> node66 = new BinaryTreeNode<>(desbloquear2);
        BinaryTreeNode<Dialogo> decisionDue2 = new BinaryTreeNode<>(desc2Due);
        BinaryTreeNode<Dialogo> node67 = new BinaryTreeNode<>(respDue3a);
        BinaryTreeNode<Dialogo> node68 = new BinaryTreeNode<>(respDue3b);
        BinaryTreeNode<Dialogo> node69 = new BinaryTreeNode<>(d15);
        BinaryTreeNode<Dialogo> nodeSal1 = new BinaryTreeNode<>(salida1);
        BinaryTreeNode<Dialogo> decisionDue3 = new BinaryTreeNode<>(desc3Due);
        BinaryTreeNode<Dialogo> node70 = new BinaryTreeNode<>(respDue4);
        BinaryTreeNode<Dialogo> nodeSal2 = new BinaryTreeNode<>(salida2);
        BinaryTreeNode<Dialogo> node71 = new BinaryTreeNode<>(cierre4);
        BinaryTreeNode<Dialogo> node71a = new BinaryTreeNode<>(cierre4);
        BinaryTreeNode<Dialogo> node71b = new BinaryTreeNode<>(cierre4);
        BinaryTreeNode<Dialogo> node71c = new BinaryTreeNode<>(cierre4);
        BinaryTreeNode<Dialogo> node71d = new BinaryTreeNode<>(cierre4);
        BinaryTreeNode<Dialogo> node72 = new BinaryTreeNode<>(despedida4);
        BinaryTreeNode<Dialogo> node72a = new BinaryTreeNode<>(despedida4);
        BinaryTreeNode<Dialogo> node72b = new BinaryTreeNode<>(despedida4);
        BinaryTreeNode<Dialogo> node72c = new BinaryTreeNode<>(despedida4);
        BinaryTreeNode<Dialogo> node72d = new BinaryTreeNode<>(despedida4);

        GeneralTree<Dialogo> auxTree4 = new GeneralTree<>();

        auxTree4.insertNode(node57, null);
        auxTree4.insertNode(node58, node57);
        auxTree4.insertNode(node59, node58);
        auxTree4.insertNode(node60, node59);
        auxTree4.insertNode(decisionDue1, node60);
        auxTree4.insertNode(node61, decisionDue1);
        auxTree4.insertNode(node62, node61);
        auxTree4.insertNode(node71, node62);
        auxTree4.insertNode(node72, node71);

        auxTree4.insertNode(node63, decisionDue1);
        auxTree4.insertNode(node64, node63);
        auxTree4.insertNode(node71a, node62);
        auxTree4.insertNode(node72a, node71a);

        auxTree4.insertNode(node65, decisionDue1);
        auxTree4.insertNode(node66, node65);
        auxTree4.insertNode(decisionDue2, node66);

        auxTree4.insertNode(nodeSal1, decisionDue2);
        auxTree4.insertNode(node71b, nodeSal1);
        auxTree4.insertNode(node72b, node71b);

        auxTree4.insertNode(node67, decisionDue2);
        auxTree4.insertNode(node68, node67);
        auxTree4.insertNode(node69, node68);
        auxTree4.insertNode(decisionDue3, node69);

        auxTree4.insertNode(node70, decisionDue3);
        auxTree4.insertNode(node71c, node70);
        auxTree4.insertNode(node72c, node71c);

        auxTree4.insertNode(nodeSal2, decisionDue3);
        auxTree4.insertNode(node71d, nodeSal2);
        auxTree4.insertNode(node72d, node71d);

        escenariosMundo.get(3).setArbolDial(auxTree4);

        //Dialogo guias sala antiguedades
        Dialogo guia1 = new Dialogo("Vaya vaya, esta sala siempre me ha parecido la más silenciosa del museo.", "Detective", detective, true);
        Dialogo guia3 = new Dialogo("Es especial, ¿verdad? Tiene una energía distinta. A veces siento que las piezas me observan a mí.", "Guia Principal", guiaPrincipal, true);
        Dialogo guia4 = new Dialogo("¿Cuánto tiempo lleva trabajando aquí señor Guia?", "Detective", detective, true);
        Dialogo guia5 = new Dialogo("Ocho años. He guiado a miles de personas por cada rincón de este lugar. Me lo conozco como si fuese la palma de mi mano.", "Guia Principal", guiaPrincipal, true);

        // Primera decisión
        Dialogo desc1G1 = new Dialogo("Pues muy bien ahora quiero saber..", "Detective", detective, true);
        desc1G1.setOpciones(new LinkedList<>(Arrays.asList(
                "¿Qué pieza de esta sala le parece más interesante?",
                "¿Diría que nota cuando algo está fuera de lugar?",
                "¿Le ha tocado ver cosas extrañas en sus turnos?"

        )));

        // Camino vacío: pieza favorita
        Dialogo resp1aG1 = new Dialogo("Sería muy difícil elegir. Pero si me apura, diría el reloj solar romano. Es simple, pero preciso" +
                ". Como deberían ser las cosas.", "Guia Principal", guiaPrincipal, true);
        Dialogo resp1bG1 = new Dialogo("Aunque claro, la mayoría prefiere las armaduras. Supongo que tienen más... presencia.", "Guia Principal", guiaPrincipal, true);

        // Camino principal: percepción del entorno
        Dialogo resp2aG1 = new Dialogo("¿Fuera de lugar? Claro. Cuando uno ve lo mismo todos los días, cualquier mínimo cambio salta a la vista.", "Guia Principal", guiaPrincipal, true);
        Dialogo resp2bG1 = new Dialogo("De hecho, ahora que lo menciona, hay algo que me pareció raro esta mañana.", "Guia Principal", guiaPrincipal, true);

        // Camino vacío: cosas extrañas
        Dialogo resp3aG1 = new Dialogo("Algunas sombras, ruidos, puertas que se cierran solas... pero nada que no tenga explicación lógica. " +
                "O al menos eso me digo para poder dormir tranquilo.", "Guia Principal", guiaPrincipal, true);
        Dialogo resp3bG1 = new Dialogo("Aunque una vez juraría que vi una figura moverse entre las vitrinas. Pero era tarde, y estaba cansado " +
                "así que decidí culpar al cansancio.", "Guia Principal", guiaPrincipal, true);

        // Segunda decisión
        Dialogo desc2G1 = new Dialogo("Usted responda mientras yo deduzco si debo seguir preguntandole o no..", "Detective", detective, true);
        desc2G1.setOpciones(new LinkedList<>(Arrays.asList(
                "¿Alguien estaba donde no debería?",
                "¿Qué vio fuera de lugar?",
                "No quiero saber nada más"
        )));

        // Camino vacío: pregunta ambigua
        Dialogo resp4aG1 = new Dialogo("No, no vi a nadie moverse. Solo noté una sensación rara, como si algo no encajara. Pero no sabría decir qué.", "Guia Principal", guiaPrincipal, true);
        Dialogo resp4bG1 = new Dialogo("Quizás solo estaba cansado. A veces la mente juega con uno cuando está solo entre estas cosas antiguas.", "Guia Principal", guiaPrincipal, true);

        // Camino principal: detalle observado
        Dialogo detalle1 = new Dialogo("Ahora que lo pienso una de las armaduras... de la otra sala. Siempre ha estado girada hacia la entrada, como si saludara a los visitantes.", "Guia Principal", guiaPrincipal, true);
        Dialogo detalle2 = new Dialogo("Pero esta mañana la encontré girada hacia la pared. No es algo que se mueva solo, créame. Pesa como un camión.", "Guia Principal", guiaPrincipal, true);
        Dialogo detalle3 = new Dialogo("¿Y no hay forma de que alguien la haya movido por accidente?", "Detective", detective, true);
        Dialogo detalle4 = new Dialogo("No sin ayuda. Y no hay registro de que se haya autorizado moverla. Por eso me pareció raro.", "Guia Principal", guiaPrincipal, true);

        Dialogo salida3 = new Dialogo("Como usted diga Sr.Detective, quedo a su disposición", "Guia Principal", guiaPrincipal, true);

        // Dato clave 1 y 2
        //Juego.getInstance().getPartidaActual().getJugador().getDiario().agregarDialogoImportante("Guía Principal", "Una de las armaduras fue encontrada en una posición inusual.");
        //Juego.getInstance().getPartidaActual().getJugador().getDiario().agregarDialogoImportante("Guía Principal", "La armadura no pudo ser movida por una sola persona.");

        // Intervención del Guía
        Dialogo inter1 = new Dialogo("(Parece que el guía 2 viene hacia aquí... con la intención de interrumpir)", "Detective", detective, true);
        Dialogo inter2 = new Dialogo("¿Están hablando de la armadura? Por favor, eso no es nada. Seguro alguien la empujó sin querer limpiando.", "Guia", guia, true);
        Dialogo inter3 = new Dialogo("No creo que valga la pena perder tiempo con eso, detective. Hay cosas más importantes, ¿no cree?", "Guia", guia, true);

        // Confrontación
        Dialogo det1 = new Dialogo("¿Le molesta que investigue detalles menores? A veces son esos los que resuelven un caso.", "Detective", detective, true);
        Dialogo det2 = new Dialogo("No, claro que no. Solo digo que no deberíamos sacar conclusiones apresuradas por una estatua torcida.", "Guia", guia, true);
        Dialogo det3 = new Dialogo("No he sacado ninguna conclusión. Pero usted parece muy interesado en que no la saque.", "Detective", detective, true);
        Dialogo det4 = new Dialogo("Yo solo intento ayudar. Si me necesita, estaré en la sala de esculturas.", "Guia", guia, true);

        Dialogo cierre5 = new Dialogo("Gracias por su tiempo. Seguiré observando por aquí.", "Detective", detective, true);
        Dialogo despedida5 = new Dialogo("Cuando quiera, detective. Espero que encuentre lo que busca.", "Guia Principal", guiaPrincipal, true);

        // Reacción del detective (después del cierre)
        Dialogo reflexion1 = new Dialogo("(Demasiado interés en desviar la atención. Ese guía sabe más de lo que aparenta...)", "Detective", detective, true);
        Dialogo reflexion2 = new Dialogo("(Siento que me voy acercando... en el fondo lo siento, y estas sensaciones no engañan...)", "Detective", detective, true);

        // Dato clave 2
        //Juego.getInstance().getPartidaActual().getJugador().getDiario().agregarDialogoImportante("Guía ", El Guía intentó restarle importancia al detalle de la armadura. Parece que tiene algo que ocultar.");

        BinaryTreeNode<Dialogo> node73 = new BinaryTreeNode<>(guia1);
        BinaryTreeNode<Dialogo> node74 = new BinaryTreeNode<>(guia3);
        BinaryTreeNode<Dialogo> node75 = new BinaryTreeNode<>(guia4);
        BinaryTreeNode<Dialogo> node76 = new BinaryTreeNode<>(guia5);
        BinaryTreeNode<Dialogo> decision1G1 = new BinaryTreeNode<>(desc1G1);
        BinaryTreeNode<Dialogo> node77 = new BinaryTreeNode<>(resp1aG1);
        BinaryTreeNode<Dialogo> node78 = new BinaryTreeNode<>(resp1bG1);
        BinaryTreeNode<Dialogo> node79 = new BinaryTreeNode<>(resp2aG1);
        BinaryTreeNode<Dialogo> node80 = new BinaryTreeNode<>(resp2bG1);
        BinaryTreeNode<Dialogo> node81 = new BinaryTreeNode<>(resp3aG1);
        BinaryTreeNode<Dialogo> node82 = new BinaryTreeNode<>(resp3bG1);
        BinaryTreeNode<Dialogo> decision2G1 = new BinaryTreeNode<>(desc2G1);
        BinaryTreeNode<Dialogo> node83 = new BinaryTreeNode<>(resp4aG1);
        BinaryTreeNode<Dialogo> node84 = new BinaryTreeNode<>(resp4bG1);
        BinaryTreeNode<Dialogo> node85 = new BinaryTreeNode<>(detalle1);
        BinaryTreeNode<Dialogo> node86 = new BinaryTreeNode<>(detalle2);
        BinaryTreeNode<Dialogo> node87 = new BinaryTreeNode<>(detalle3);
        BinaryTreeNode<Dialogo> node88 = new BinaryTreeNode<>(detalle4);
        BinaryTreeNode<Dialogo> nodeSal3 = new BinaryTreeNode<>(salida3);
        BinaryTreeNode<Dialogo> node89 = new BinaryTreeNode<>(inter1);
        BinaryTreeNode<Dialogo> node90 = new BinaryTreeNode<>(inter2);
        BinaryTreeNode<Dialogo> node91 = new BinaryTreeNode<>(inter3);
        BinaryTreeNode<Dialogo> node92 = new BinaryTreeNode<>(det1);
        BinaryTreeNode<Dialogo> node93 = new BinaryTreeNode<>(det2);
        BinaryTreeNode<Dialogo> node94 = new BinaryTreeNode<>(det3);
        BinaryTreeNode<Dialogo> node95 = new BinaryTreeNode<>(det4);
        BinaryTreeNode<Dialogo> node98 = new BinaryTreeNode<>(reflexion1);
        BinaryTreeNode<Dialogo> node99 = new BinaryTreeNode<>(reflexion2);
        BinaryTreeNode<Dialogo> node96 = new BinaryTreeNode<>(cierre5);
        BinaryTreeNode<Dialogo> node96a = new BinaryTreeNode<>(cierre5);
        BinaryTreeNode<Dialogo> node96b = new BinaryTreeNode<>(cierre5);
        BinaryTreeNode<Dialogo> node96c = new BinaryTreeNode<>(cierre5);
        BinaryTreeNode<Dialogo> node97 = new BinaryTreeNode<>(despedida5);
        BinaryTreeNode<Dialogo> node97a = new BinaryTreeNode<>(despedida5);
        BinaryTreeNode<Dialogo> node97b = new BinaryTreeNode<>(despedida5);
        BinaryTreeNode<Dialogo> node97c = new BinaryTreeNode<>(despedida5);

        GeneralTree<Dialogo> auxTree5 = new GeneralTree<>();

        auxTree5.insertNode(node73, null);
        auxTree5.insertNode(node74, node73);
        auxTree5.insertNode(node75, node74);
        auxTree5.insertNode(node76, node75);
        auxTree5.insertNode(decision1G1, node76);
        auxTree5.insertNode(node77, decision1G1);
        auxTree5.insertNode(node78, node77);
        auxTree5.insertNode(node96, node78);
        auxTree5.insertNode(node97, node96);

        auxTree5.insertNode(node81, decision1G1);
        auxTree5.insertNode(node82, node81);
        auxTree5.insertNode(node96a, node82);
        auxTree5.insertNode(node97a, node96a);

        auxTree5.insertNode(node79, decision1G1);
        auxTree5.insertNode(node80, node79);
        auxTree5.insertNode(decision2G1, node80);
        auxTree5.insertNode(node83, decision2G1);
        auxTree5.insertNode(node84, node83);
        auxTree5.insertNode(node96b, node84);
        auxTree5.insertNode(node97b, node96b);

        auxTree5.insertNode(node85, decision2G1);
        auxTree5.insertNode(node86, node85);
        auxTree5.insertNode(node87, node86);
        auxTree5.insertNode(node88, node87);
        auxTree5.insertNode(node96c, node88);
        auxTree5.insertNode(node97c, node96c);

        auxTree5.insertNode(nodeSal3, decision2G1);
        auxTree5.insertNode(node89, nodeSal3);
        auxTree5.insertNode(node90, node89);
        auxTree5.insertNode(node91, node90);
        auxTree5.insertNode(node92, node91);
        auxTree5.insertNode(node93, node92);
        auxTree5.insertNode(node94, node93);
        auxTree5.insertNode(node95, node94);
        auxTree5.insertNode(node98, node95);
        auxTree5.insertNode(node99, node98);

        escenariosMundo.get(4).setArbolDial(auxTree5);

        // Dialogo callejon vagabundo
        Dialogo vagab1 = new Dialogo("Buenas noches señor. ¿Todo bien por aquí?", "Detective", detective, true);
        Dialogo vagab2 = new Dialogo("¿Todo bien? Estoy en un callejón, con frío y sin cena. ¿Usted qué cree?", "Vagabundo", vagabundo, true);
        Dialogo vagab3 = new Dialogo("Tienes razón. No fue la mejor forma de empezar. Solo quería hablar un momento con usted.", "Detective", detective, true);
        Dialogo vagab4 = new Dialogo("Hablar no cuesta nada... pero tampoco llena el estómago.", "Vagabundo", vagabundo, true);

        // Primera decisión
        Dialogo descVag1 = new Dialogo("Usted responda mientras yo deduzco si debo seguir preguntandole o no..", "Detective", detective, true);
        descVag1.setOpciones(new LinkedList<>(Arrays.asList(
                "¿Hace cuánto duerme aquí?",
                "¿Le molesta si me quedo un momento?",
                "¿Ha visto algo raro últimamente?"
        )));

        // Camino vacío: tiempo en el callejón
        Dialogo respV1 = new Dialogo("¿Qué importa? El tiempo pasa igual, tenga uno techo o no. Pero sí, llevo un buen rato viendo " +
                "pasar sombras.", "Vagabundo", vagabundo, true);

        // Camino vacío: quedarse
        Dialogo respV2 = new Dialogo("Mientras no me quite el rincón, siéntase como en casa. Aunque no tengo sofá ni café para ofrecerle.", "Vagabundo", vagabundo, true);

        // Camino principal: cosas raras
        Dialogo raras1 = new Dialogo("¿Raro? Aquí todo es raro. Pero sí, hay noches que el silencio se rompe de formas extrañas.", "Vagabundo", vagabundo, true);
        Dialogo raras2 = new Dialogo("¿Qué quiere saber exactamente?", "Vagabundo", vagabundo, true);

        // Segunda decisión
        Dialogo descVag2 = new Dialogo("Mmm me parece que quizas deba seguir investigando o quizas no...", "Detective", detective, true);
        descVag2.setOpciones(new LinkedList<>(Arrays.asList(
                "¿Escuchó algún ruido fuerte anoche?",
                "¿Vio a alguien salir del museo entre la 1 y las 3 a.m.?",
                "No quiero saber nada más"
        )));

        // Camino vacío: ruidos
        Dialogo respV3 = new Dialogo("Ruidos hay siempre. Gatos, botellas, viento... o mis tripas. Nada que me quite el sueño.", "Vagabundo", vagabundo, true);

        // Camino principal: pregunta clave

        Dialogo v1 = new Dialogo("¿Entre la 1 y las 3? Mmm... la memoria me falla cuando tengo el estómago vacío.", "Vagabundo", vagabundo, true);
        Dialogo v2 = new Dialogo("Pero quién sabe... tal vez si tuviera algo caliente en las manos, recordaría mejor.", "Vagabundo", vagabundo, true);
        Dialogo v3 = new Dialogo("Algo rico de comer, por ejemplo. No pido mucho. Solo un gesto.", "Vagabundo", vagabundo, true);

        Dialogo cierre6a = new Dialogo("Veré qué puedo hacer.", "Detective", detective, true);
        Dialogo despedida6a = new Dialogo("Aquí estaré. No tengo a dónde ir.", "Vagabundo", vagabundo, true);

        Dialogo v4 = new Dialogo("Tome aquí le traigo lo que pude conseguir, buen provecho.", "Detective", detective, true);
        Dialogo v5 = new Dialogo("¿Entre la 1 y las 3? Sí... vi a alguien. No saliendo, no. Subiendo.", "Vagabundo", vagabundo, true);
        Dialogo v6 = new Dialogo("Ocurrió por la escalera de incendios. Alguien más la bajó, cosa rara. Esa chatarra siempre está recogida.", "Vagabundo", vagabundo, true);
        Dialogo v7 = new Dialogo("No les vi la cara. Solo siluetas. Pero no eran de los míos, eso seguro.", "Vagabundo", vagabundo, true);
        Dialogo v8 = new Dialogo("Y gracias por el pan. El jamón estaba... casi fresco.", "Vagabundo", vagabundo, true);

        Dialogo salida4 = new Dialogo("Como desee, aquí estaré siempre que me necesite, no suelo cambiar de casa jajaja", "Vagabundo", vagabundo, true);

        //Dato clave
        //Juego.getInstance().getPartidaActual().getJugador().getDiario().agregarDialogoImportante("Vagabundo", "Alguien subió por la escalera de incendios entre la 1 y las 3 a.m la cual estaba ya bajada.");
        //Juego.getInstance().getPartidaActual().getJugador().getDiario().agregarDialogoImportante("Vagabundo", "La escalera de incendios fue desplegada por una segunda persona.");

        Dialogo cierre6 = new Dialogo("Gracias. Toda información ayuda más de lo que cree.", "Detective", detective, true);
        Dialogo despedida6 = new Dialogo("Gracias a usted, después de mucho tiempo conversé con alguien, eso no se da todos los días", "Vagabundo", vagabundo, true);


        BinaryTreeNode<Dialogo> node100 = new BinaryTreeNode<>(vagab1);
        BinaryTreeNode<Dialogo> node101 = new BinaryTreeNode<>(vagab2);
        BinaryTreeNode<Dialogo> node102 = new BinaryTreeNode<>(vagab3);
        BinaryTreeNode<Dialogo> node103 = new BinaryTreeNode<>(vagab4);
        BinaryTreeNode<Dialogo> decisionV1 = new BinaryTreeNode<>(descVag1);
        BinaryTreeNode<Dialogo> node104 = new BinaryTreeNode<>(respV1);
        BinaryTreeNode<Dialogo> node105 = new BinaryTreeNode<>(respV2);
        BinaryTreeNode<Dialogo> node106 = new BinaryTreeNode<>(raras1);
        BinaryTreeNode<Dialogo> node107 = new BinaryTreeNode<>(raras2);
        BinaryTreeNode<Dialogo> decisionV2 = new BinaryTreeNode<>(descVag2);
        BinaryTreeNode<Dialogo> node108 = new BinaryTreeNode<>(respV3);
        BinaryTreeNode<Dialogo> node109 = new BinaryTreeNode<>(v1);
        BinaryTreeNode<Dialogo> node110 = new BinaryTreeNode<>(v2);
        BinaryTreeNode<Dialogo> node111 = new BinaryTreeNode<>(v3);
        BinaryTreeNode<Dialogo> node112 = new BinaryTreeNode<>(cierre6a);
        BinaryTreeNode<Dialogo> node113 = new BinaryTreeNode<>(despedida6a);
        BinaryTreeNode<Dialogo> node114 = new BinaryTreeNode<>(v4);
        BinaryTreeNode<Dialogo> node115 = new BinaryTreeNode<>(v5);
        BinaryTreeNode<Dialogo> node116 = new BinaryTreeNode<>(v6);
        BinaryTreeNode<Dialogo> node117 = new BinaryTreeNode<>(v7);
        BinaryTreeNode<Dialogo> node118 = new BinaryTreeNode<>(v8);
        BinaryTreeNode<Dialogo> nodeSal4 = new BinaryTreeNode<>(salida4);
        BinaryTreeNode<Dialogo> node119 = new BinaryTreeNode<>(cierre6);
        BinaryTreeNode<Dialogo> node119b = new BinaryTreeNode<>(cierre6);
        BinaryTreeNode<Dialogo> node119c = new BinaryTreeNode<>(cierre6);
        BinaryTreeNode<Dialogo> node119d = new BinaryTreeNode<>(cierre6);
        BinaryTreeNode<Dialogo> node119e = new BinaryTreeNode<>(cierre6);
        BinaryTreeNode<Dialogo> node120 = new BinaryTreeNode<>(despedida6);
        BinaryTreeNode<Dialogo> node120b = new BinaryTreeNode<>(despedida6);
        BinaryTreeNode<Dialogo> node120c = new BinaryTreeNode<>(despedida6);
        BinaryTreeNode<Dialogo> node120d = new BinaryTreeNode<>(despedida6);
        BinaryTreeNode<Dialogo> node120e = new BinaryTreeNode<>(despedida6);

        GeneralTree<Dialogo> auxTree6 = new GeneralTree<>();

        auxTree6.insertNode(node100, null);
        auxTree6.insertNode(node101, node100);
        auxTree6.insertNode(node102, node101);
        auxTree6.insertNode(node103, node102);
        auxTree6.insertNode(decisionV1, node103);
        auxTree6.insertNode(node105, decisionV1);
        auxTree6.insertNode(node119, node105);
        auxTree6.insertNode(node120, node119);

        auxTree6.insertNode(node106, decisionV1);
        auxTree6.insertNode(node107, node106);
        auxTree6.insertNode(node119b, node107);
        auxTree6.insertNode(node120b, node119b);

        auxTree6.insertNode(node104, decisionV1);
        auxTree6.insertNode(decisionV2, node104);
        auxTree6.insertNode(node108, decisionV2);
        auxTree6.insertNode(node119c, node108);
        auxTree6.insertNode(node120c, node119c);

        auxTree6.insertNode(node109, decisionV2);
        auxTree6.insertNode(node110, node109);
        auxTree6.insertNode(node111, node110);
        auxTree6.insertNode(node112, node111);
        auxTree6.insertNode(node113, node112);
        auxTree6.insertNode(node114, node113);
        auxTree6.insertNode(node115, node114);
        auxTree6.insertNode(node116, node115);
        auxTree6.insertNode(node117, node116);
        auxTree6.insertNode(node118, node117);
        auxTree6.insertNode(node119d, node118);
        auxTree6.insertNode(node120d, node119d);

        auxTree6.insertNode(nodeSal4, decisionV2);
        auxTree6.insertNode(node119e, nodeSal4);
        auxTree6.insertNode(node120e, node119e);

        escenariosMundo.get(5).setArbolDial(auxTree6);

        // dialogo esposa del jefe
        Dialogo e1 = new Dialogo("Disculpe la molestia, señora. Solo quería hacerle unas preguntas rápidas, si no le importa.", "Detective", detective, true);
        Dialogo e2 = new Dialogo("¿A mí? Qué raro. Pero claro, adelante. Aunque no sé si puedo ayudar mucho. Yo solo vine a acompañar a mi esposo.", "Esposa", esposa, true);

        // Primera decisión
        Dialogo descEsp1 = new Dialogo("Pues bien digame..", "Detective", detective, true);
        descEsp1.setOpciones(new LinkedList<>(Arrays.asList(
                "¿Le gusta venir al museo?",
                "¿Cómo es su esposo en casa?",
                "¿Qué opina del trabajo que hace su marido?"

        )));

        // Camino vacío: gusto por el museo
        Dialogo respE1 = new Dialogo("La verdad, no soy muy de museos. Pero este tiene su encanto. Y bueno, hay que apoyar a la familia, ¿no?", "Esposa", esposa, true);

        // Camino vacío: vida doméstica
        Dialogo respE2 = new Dialogo("En casa es igual de mandón que en el trabajo, pero también muy gracioso. Siempre tiene una historia nueva que contar.", "Esposa", esposa, true);

        // Camino principal: orgullo por el esposo
        Dialogo e3 = new Dialogo("Mi esposo es brillante. Este museo no sería nada sin él. ¿Quiere ver algo?", "Esposa", esposa, true);


        Dialogo e4 = new Dialogo("*Saca su teléfono y muestra la pantalla bloqueada.*", "Foto en espera", nada, true);


        Dialogo e5 = new Dialogo("Mire, esta es mi pantalla. Es de hace unas semanas. ¿No se ve guapo? Esa es su oficina. Siempre tan elegante.", "Esposa", esposa, true);

        // Observación del detective
        Dialogo e6 = new Dialogo("*El detective observa la imagen. El jefe aparece sonriendo, de pie junto a su escritorio. En la pared, colgada detrás de él, está la espada... la misma que fue usada como arma homicida.*", "Foto que poner", nada, true);

        Dialogo e7 = new Dialogo("(Esa espada... es la misma. Estaba en su oficina antes del crimen.)", "Detective", detective, true);


        //Juego.getInstance().getPartidaActual().getJugador().getDiario().agregarDialogoImportante("Esposa del jefe", "La espada homicida estaba colgada en la oficina del jefe antes del crimen.");

        // Disimulo y cierre
        Dialogo e8 = new Dialogo("Sí, muy buena foto. Se nota que lo admira mucho.", "Detective", detective, true);
        Dialogo e9 = new Dialogo("Muchísimo. Es un hombre excepcional. Aunque a veces se hace el duro, tiene un gran corazón.", "Esposa", esposa, true);

        Dialogo cierre7 = new Dialogo("Gracias por su tiempo. No la molesto más.", "Detective", detective, true);
        Dialogo despedida7 = new Dialogo("Cuando quiera señor. Y si ve a mi esposo, dígale que lo estoy esperando para irnos a casa.", "Esposa", esposa, true);

        BinaryTreeNode<Dialogo> node121 = new BinaryTreeNode<>(e1);
        BinaryTreeNode<Dialogo> node122 = new BinaryTreeNode<>(e2);
        BinaryTreeNode<Dialogo> decisionEsp1 = new BinaryTreeNode<>(descEsp1);
        BinaryTreeNode<Dialogo> node123 = new BinaryTreeNode<>(respE1);
        BinaryTreeNode<Dialogo> node124 = new BinaryTreeNode<>(respE2);
        BinaryTreeNode<Dialogo> node125 = new BinaryTreeNode<>(e3);
        BinaryTreeNode<Dialogo> node126 = new BinaryTreeNode<>(e4);
        BinaryTreeNode<Dialogo> node127 = new BinaryTreeNode<>(e5);
        BinaryTreeNode<Dialogo> node128 = new BinaryTreeNode<>(e6);
        BinaryTreeNode<Dialogo> node129 = new BinaryTreeNode<>(e7);
        BinaryTreeNode<Dialogo> node130 = new BinaryTreeNode<>(e8);
        BinaryTreeNode<Dialogo> node131 = new BinaryTreeNode<>(e9);
        BinaryTreeNode<Dialogo> node132 = new BinaryTreeNode<>(cierre7);
        BinaryTreeNode<Dialogo> node132a = new BinaryTreeNode<>(cierre7);
        BinaryTreeNode<Dialogo> node132b = new BinaryTreeNode<>(cierre7);
        BinaryTreeNode<Dialogo> node133 = new BinaryTreeNode<>(despedida7);
        BinaryTreeNode<Dialogo> node133a = new BinaryTreeNode<>(despedida7);
        BinaryTreeNode<Dialogo> node133b = new BinaryTreeNode<>(despedida7);

        GeneralTree<Dialogo> auxTree7 = new GeneralTree<>();

        auxTree7.insertNode(node121, null);
        auxTree7.insertNode(node122, node121);
        auxTree7.insertNode(decisionEsp1, node122);
        auxTree7.insertNode(node123, decisionEsp1);
        auxTree7.insertNode(node132, node123);
        auxTree7.insertNode(node133, node132);

        auxTree7.insertNode(node124, decisionEsp1);
        auxTree7.insertNode(node132a, node124);
        auxTree7.insertNode(node133a, node132a);

        auxTree7.insertNode(node125, decisionEsp1);
        auxTree7.insertNode(node126, node125);
        auxTree7.insertNode(node127, node126);
        auxTree7.insertNode(node128, node127);
        auxTree7.insertNode(node129, node128);
        auxTree7.insertNode(node130, node129);
        auxTree7.insertNode(node131, node130);
        auxTree7.insertNode(node132b, node131);
        auxTree7.insertNode(node133b, node132b);

        escenariosMundo.get(6).setArbolDial(auxTree7);


        //  Policia acto 1
        Dialogo p1 = new Dialogo("Como esta oficial. Si tienes un minuto me gustaria hablar con usted.", "Detective", detective, true);
        Dialogo p2 = new Dialogo("Por supuesto. ¿Cómo vas con todo esto? El ambiente está más denso que el aire en la sala egipcia.", "Policia", policia, true);

        // Primera decisión
        Dialogo descPol1 = new Dialogo("Pues digame...", "Detective", detective, true);
        descPol1.setOpciones(new LinkedList<>(Arrays.asList(
                "¿Qué opinas del museo?",
                "¿Crees que esto fue planeado?"
        )));

        // Rama 1: Opinión sobre el museo
        Dialogo respP1 = new Dialogo("Es más grande de lo que pensaba. Y más silencioso también. Me pone los pelos de punta.", "Policia", policia, true);
        Dialogo respP1a = new Dialogo("Aunque debo admitir que hay algo fascinante en todo esto. Como si las paredes guardaran secretos.", "Policia", policia, true);

        // Rama 2: ¿Fue planeado?
        Dialogo respP2 = new Dialogo("No fue un arrebato, eso seguro. El asesino sabía lo que hacía. Y cuándo hacerlo.", "Policia", policia, true);
        Dialogo respP2a = new Dialogo("Pero aún no tengo claro si fue personal o profesional. Hay demasiadas máscaras en este lugar.", "Policia", policia, true);


        // Segunda decisión dentro de rama 1
        Dialogo descPol2 = new Dialogo("Umm interesante... Quizas con esto es suficiente o no....", "Detective", detective, true);
        descPol2.setOpciones(new LinkedList<>(Arrays.asList(
                "¿Te gustaría trabajar en un lugar así?",
                "¿Crees que alguien del personal está mintiendo?",
                "Eso es todo por ahora"
        )));

        Dialogo respP3a = new Dialogo("¿Trabajar aquí? Ni loco. Prefiero los crímenes al aire libre. Menos estatuas mirándome raro.", "Policia", policia, true);
        Dialogo respP3b = new Dialogo("No lo sé. Todos parecen nerviosos, pero eso no significa que mientan. Aunque... uno nunca sabe.", "Policia", policia, true);
        Dialogo respP3c = new Dialogo("Entendido. Si necesitas algo, estaré cerca. Solo no me dejes solo con las momias.", "Policia", policia, true);


        // Segunda decisión dentro de rama 2
        Dialogo descPol3 = new Dialogo("Umm interesante... Quizas con esto es suficiente o no....", "Detective", detective, true);
        descPol3.setOpciones(new LinkedList<>(Arrays.asList(
                "¿Tienes algún sospechoso?",
                "¿Qué harías tú en mi lugar?",
                "Eso es todo por ahora"
        )));

        Dialogo respP4a = new Dialogo("Todavía no. Pero tengo el ojo puesto en el guía ese... el que habla demasiado. Algo no me cuadra.", "Policia", policia, true);
        Dialogo respP4b = new Dialogo("Yo haría lo mismo que tú: observar. Escuchar. Y esperar a que alguien se contradiga.", "Policia", policia, true);
        Dialogo respP4c = new Dialogo("Vale. Me quedo por aquí. Si algo se mueve, te aviso.", "Policia", policia, true);

        // Rama 3: Cierre inmediato
        Dialogo cierre8 = new Dialogo("Muchas gracias por toda la información, voy a seguir investigando en este lugar", "Detective", detective, true);
        Dialogo despedida8 = new Dialogo("Como quieras. Pero no tardes mucho. Este lugar no me da buena espina. Nos vemos luego", "Policia", policia, true);

        BinaryTreeNode<Dialogo> node153 = new BinaryTreeNode<>(p1);
        BinaryTreeNode<Dialogo> node154 = new BinaryTreeNode<>(p2);
        BinaryTreeNode<Dialogo> decisionPol1 = new BinaryTreeNode<>(descPol1);
        BinaryTreeNode<Dialogo> node155 = new BinaryTreeNode<>(respP1);
        BinaryTreeNode<Dialogo> node156 = new BinaryTreeNode<>(respP1a);
        BinaryTreeNode<Dialogo> node157 = new BinaryTreeNode<>(respP2);
        BinaryTreeNode<Dialogo> node158 = new BinaryTreeNode<>(respP2a);
        BinaryTreeNode<Dialogo> decisionPol2 = new BinaryTreeNode<>(descPol2);
        BinaryTreeNode<Dialogo> node159 = new BinaryTreeNode<>(respP3a);
        BinaryTreeNode<Dialogo> node160 = new BinaryTreeNode<>(respP3b);
        BinaryTreeNode<Dialogo> node161 = new BinaryTreeNode<>(respP3c);
        BinaryTreeNode<Dialogo> decisionPol3 = new BinaryTreeNode<>(descPol3);
        BinaryTreeNode<Dialogo> node162 = new BinaryTreeNode<>(respP4a);
        BinaryTreeNode<Dialogo> node163 = new BinaryTreeNode<>(respP4b);
        BinaryTreeNode<Dialogo> node164 = new BinaryTreeNode<>(respP4c);
        BinaryTreeNode<Dialogo> node165 = new BinaryTreeNode<>(cierre8);
        BinaryTreeNode<Dialogo> node165a = new BinaryTreeNode<>(cierre8);
        BinaryTreeNode<Dialogo> node165b = new BinaryTreeNode<>(cierre8);
        BinaryTreeNode<Dialogo> node165c = new BinaryTreeNode<>(cierre8);
        BinaryTreeNode<Dialogo> node165d = new BinaryTreeNode<>(cierre8);
        BinaryTreeNode<Dialogo> node165e = new BinaryTreeNode<>(cierre8);
        BinaryTreeNode<Dialogo> node166 = new BinaryTreeNode<>(despedida8);
        BinaryTreeNode<Dialogo> node166a = new BinaryTreeNode<>(despedida8);
        BinaryTreeNode<Dialogo> node166b = new BinaryTreeNode<>(despedida8);
        BinaryTreeNode<Dialogo> node166c = new BinaryTreeNode<>(despedida8);
        BinaryTreeNode<Dialogo> node166d = new BinaryTreeNode<>(despedida8);
        BinaryTreeNode<Dialogo> node166e = new BinaryTreeNode<>(despedida8);

        GeneralTree<Dialogo> auxTree8 = new GeneralTree<>();

        auxTree8.insertNode(node153, null);
        auxTree8.insertNode(node154, node153);
        auxTree8.insertNode(decisionPol1, node154);
        auxTree8.insertNode(node155, decisionPol1);
        auxTree8.insertNode(node156, node155);
        auxTree8.insertNode(decisionPol2, node156);
        auxTree8.insertNode(node159, decisionPol2);
        auxTree8.insertNode(node165, node159);
        auxTree8.insertNode(node166, node165);

        auxTree8.insertNode(node160, decisionPol2);
        auxTree8.insertNode(node165a, node160);
        auxTree8.insertNode(node166a, node165a);

        auxTree8.insertNode(node161, decisionPol2);
        auxTree8.insertNode(node165b, node160);
        auxTree8.insertNode(node166b, node165b);

        auxTree8.insertNode(node157, decisionPol1);
        auxTree8.insertNode(node158, node157);
        auxTree8.insertNode(decisionPol3, node158);
        auxTree8.insertNode(node162, decisionPol3);
        auxTree8.insertNode(node165c, node162);
        auxTree8.insertNode(node166c, node165c);

        auxTree8.insertNode(node163, decisionPol3);
        auxTree8.insertNode(node165d, node163);
        auxTree8.insertNode(node166d, node165d);

        auxTree8.insertNode(node164, decisionPol3);
        auxTree8.insertNode(node165e, node164);
        auxTree8.insertNode(node166e, node165e);

        escenariosMundo.get(7).setArbolDial(auxTree8);

        //Guia
        Dialogo conv1 = new Dialogo("¿Y ahora qué? ¿Van a interrogar a todos los que respiramos?", "Guia", guia, true);
        Dialogo conv2 = new Dialogo("Solo estoy haciendo mi trabajo. Necesito hablar con todos, esto no se resolvera por arte de magia Sr.Guia.", "Detective", detective, true);
        Dialogo conv3 = new Dialogo("Pues qué suerte la mía. Dispare, algunos si tenemos que trabajar de verdad.", "Guia", guia, true);
        Dialogo conv4 = new Dialogo("¿Vio algo fuera de lo común?", "Detective", detective, true);
        Dialogo conv5 = new Dialogo("Solo a un montón de turistas maleducados y a mis colegas fingiendo que hacen algo. Como siempre.", "Guia", guia, true);
        Dialogo conv6 = new Dialogo("Si no tiene más preguntas, tengo que seguir fingiendo que sonrío por el bien del museo.", "Guia", guia, true);
        Dialogo conv7 = new Dialogo("Con semejante reaccion claro que me retiro. Permiso...", "Detective", detective, true);

        BinaryTreeNode<Dialogo> node167 = new BinaryTreeNode<>(conv1);
        BinaryTreeNode<Dialogo> node168 = new BinaryTreeNode<>(conv2);
        BinaryTreeNode<Dialogo> node169 = new BinaryTreeNode<>(conv3);
        BinaryTreeNode<Dialogo> node170 = new BinaryTreeNode<>(conv4);
        BinaryTreeNode<Dialogo> node171 = new BinaryTreeNode<>(conv5);
        BinaryTreeNode<Dialogo> node172 = new BinaryTreeNode<>(conv6);
        BinaryTreeNode<Dialogo> node173 = new BinaryTreeNode<>(conv7);

        GeneralTree<Dialogo> auxTree9 = new GeneralTree<>();

        auxTree9.insertNode(node167, null);
        auxTree9.insertNode(node168, node167);
        auxTree9.insertNode(node169, node168);
        auxTree9.insertNode(node170, node169);
        auxTree9.insertNode(node171, node170);
        auxTree9.insertNode(node172, node171);
        auxTree9.insertNode(node173, node172);

        escenariosMundo.get(8).setArbolDial(auxTree9);

        // Dialogo para cuando tocas la armadura entre el Detective, la Esposa y el Policia

        Dialogo a1 = new Dialogo("Espera... esta armadura está mal colocada. La base está girada.", "Detective", detective, true);
        Dialogo a2 = new Dialogo("*El detective se agacha, examina la parte trasera de la armadura y abre una compuerta oculta.*", "Narrador", nada, true);
        Dialogo a3 = new Dialogo("Aquí hay algo... ¡Una espada!", "Detective", detective, true);
        Dialogo a4 = new Dialogo("¿Una espada? ¿Dentro de una armadura? ¡Eso no estaba en ninguna ficha de inventario!", "Esposa", esposa, true);
        Dialogo a5 = new Dialogo("*El detective saca la espada con cuidado. Tiene manchas oscuras en la hoja.*", "Narrador", nada, true);
        Dialogo a6 = new Dialogo("Esto no es parte de la exposición. Y estas manchas... podrían ser sangre.", "Detective", detective, true);
        Dialogo a7 = new Dialogo("¡Dios mío! ¿Estás diciendo que esa cosa fue usada para... para matar?", "Esposa", esposa, true);
        Dialogo a8 = new Dialogo("Todavía no lo sé. Pero alguien se tomó muchas molestias para esconderla aquí.", "Detective", detective, true);
        Dialogo a9 = new Dialogo("¿Y si es de mi mi esposo? ¿Y si él sabía? ¡No puede ser! ¡Él amaba este museo!", "Esposa", esposa, true);
        Dialogo a10 = new Dialogo("Tranquila. No vamos a sacar conclusiones todavía.", "Detective", detective, true);
        Dialogo a11 = new Dialogo("Voy a llevar esto al laboratorio. Que analicen todo: huellas, sangre, cualquier rastro.", "Policia", policia, true);
        Dialogo a12 = new Dialogo("Hazlo. Esta espada podría ser la pieza que faltaba.", "Detective", detective, true);

        BinaryTreeNode<Dialogo> node134 = new BinaryTreeNode<>(a1);
        BinaryTreeNode<Dialogo> node135 = new BinaryTreeNode<>(a2);
        BinaryTreeNode<Dialogo> node136 = new BinaryTreeNode<>(a3);
        BinaryTreeNode<Dialogo> node137 = new BinaryTreeNode<>(a4);
        BinaryTreeNode<Dialogo> node138 = new BinaryTreeNode<>(a5);
        BinaryTreeNode<Dialogo> node139 = new BinaryTreeNode<>(a6);
        BinaryTreeNode<Dialogo> node140 = new BinaryTreeNode<>(a7);
        BinaryTreeNode<Dialogo> node141 = new BinaryTreeNode<>(a8);
        BinaryTreeNode<Dialogo> node142 = new BinaryTreeNode<>(a9);
        BinaryTreeNode<Dialogo> node143 = new BinaryTreeNode<>(a10);
        BinaryTreeNode<Dialogo> node144 = new BinaryTreeNode<>(a11);
        BinaryTreeNode<Dialogo> node145 = new BinaryTreeNode<>(a12);

        GeneralTree<Dialogo> auxTree10 = new GeneralTree<>();

        auxTree10.insertNode(node134, null);
        auxTree10.insertNode(node135, node134);
        auxTree10.insertNode(node136, node135);
        auxTree10.insertNode(node137, node136);
        auxTree10.insertNode(node138, node137);
        auxTree10.insertNode(node139, node138);
        auxTree10.insertNode(node140, node139);
        auxTree10.insertNode(node141, node140);
        auxTree10.insertNode(node142, node141);
        auxTree10.insertNode(node143, node142);
        auxTree10.insertNode(node144, node143);
        auxTree10.insertNode(node145, node144);

        escenariosMundo.get(9).setArbolDial(auxTree10);

        //Final del Acto1
        // Monólogo del detective para el final

        Dialogo m1 = new Dialogo("(No me lo puedo creer... Todo me lleva a él...)", "Detective", detective, true);
        Dialogo m1a = new Dialogo("(Los dos papeles... encajan perfectamente. Las fibras están rasgadas en el mismo ángulo. )", "Detective", detective, true);
        Dialogo m2 = new Dialogo("Uno dice 'mortis', el otro 'me sequitur'... y la secretaria mencionó algo sobre 'umbra'.", "Detective", detective, true);
        Dialogo m3 = new Dialogo("Umbra mortis me sequitur. ¿Era una contraseña? ¿Una advertencia? Vamos a probar suerte.", "Detective", detective, true);
        Dialogo m4 = new Dialogo("*Tecleas la frase completa. La pantalla parpadea y se desbloquea.*", "Detective", detective, true);
        Dialogo m5 = new Dialogo("(Bien. Veamos qué estabas investigando...)", "Detective", detective, true);


        Dialogo m6 = new Dialogo("*Abres una carpeta llamada 'Contabilidad Interna'. Dentro hay decenas de archivos Excel y PDFs.*", "Narrador", nada, true);
        Dialogo m7 = new Dialogo("(Registros de transferencias... pagos duplicados... contratos inflados. Todo meticulosamente organizado.)", "Detective", detective, true);
        Dialogo m8 = new Dialogo("(Aquí hay una empresa llamada 'ArteNova S.A.'... sin dirección real, sin empleados registrados. Una fachada.)", "Detective", detective, true);

        Dialogo m9 = new Dialogo("*Encuentras un documento titulado 'Análisis de flujo. Confidencial'.*", "Narrador", nada, true);
        Dialogo m10 = new Dialogo("(El Económico había rastreado los desvíos. Hay gráficos, fechas, nombres... y uno se repite más que los demás.)", "Detective", detective, true);
        Dialogo m11 = new Dialogo("(El director. Firmas, autorizaciones, correos reenviados. Todo hecho por él.)", "Detective", detective, true);

        Dialogo m12 = new Dialogo("*Abres un archivo de texto con fecha reciente. Parece una nota personal.*", "Narrador", nada, true);
        Dialogo m13 = new Dialogo("(“Si algo me pasa, no fue un accidente. Todo está aquí. No me van a callar.”)", "Detective", detective, true);

        Dialogo m14 = new Dialogo("(No estaba involucrado... estaba tratando de exponerlo. Y lo pagó con su vida.)", "Detective", detective, true);
        Dialogo m15 = new Dialogo("(Ahora todo encaja. El Económico descubrió la verdad. Y por eso lo asesinaron. Es hora de acabar con esto)", "Detective", detective, true);


        // Confrontación final con el dueño

        Dialogo con1 = new Dialogo("¿Qué haces aquí? No tengo tiempo para tus teorías.", "Dueño", dueno, true);
        Dialogo con2 = new Dialogo("No vine a teorizar. Vine a mostrarte lo que encontré en la computadora del Económico.", "Detective", detective, true);
        Dialogo con3 = new Dialogo("¿Perdón? ¿Qué computadora?", "Dueño", dueno, true);
        Dialogo con4 = new Dialogo("La que tenía protegida con una contraseña en latín. Dentro había documentos contables, transferencias a empresas fantasma como " +
                "'ArteNova S.A.', y todas firmadas por ti.", "Detective", detective, true);
        Dialogo con5 = new Dialogo("*El jefe se queda en silencio unos segundos. Luego se levanta lentamente.*", "Narrador", nada, true);
        Dialogo con6 = new Dialogo("También encontré un correo que él nunca envió. Decía: 'Si algo me pasa, no fue un accidente. Todo está aquí'.", "Detective", detective, true);
        Dialogo con7 = new Dialogo("¿Y eso qué prueba? ¿Un correo sin enviar?", "Dueño", dueno, true);
        Dialogo con8 = new Dialogo("Prueba que sabía lo que hacías. Y que tenía miedo. Como tú ahora..", "Detective", detective, true);
        Dialogo con9 = new Dialogo("Además, encontramos la espada escondida en la armadura. Tiene tus huellas. Y rastros de sangre.", "Detective", detective, true);
        Dialogo con9a = new Dialogo("¿Vas a seguir negándolo? Porque todo esto ya no es una sospecha. Es un caso cerrado.", "Detective", detective, true);
        Dialogo con9b = new Dialogo("No puedes seguir escondiéndote detrás de tu escritorio.", "Detective", detective, true);
        Dialogo con9c = new Dialogo("No tienes idea de lo que estás diciendo. ¿Sabes cuántas vidas dependen de este museo? ¿De mí?", "Dueño", dueno, true);
        Dialogo con9d = new Dialogo("¿Y eso justifica un asesinato? ¿Desviar fondos públicos? ¿Mentirle a todos?", "DetectiveFinal", detective, true);
        Dialogo con9e = new Dialogo("¡No lo maté! ¡Yo no...! Solo quería ganar tiempo. Tapar el hueco. Él iba a hablar, y todo se iba a venir abajo.", "Dueño", dueno, true);
        Dialogo con9f = new Dialogo("Entonces habla. Dime quién más está metido en esto. Tal vez aún puedas salvarte.", "Detective", detective, true);


        // El jefe se altera
        Dialogo con10 = new Dialogo("*El jefe comienza a respirar con dificultad. Se lleva la mano al cajón.*", "Narrador", nada, true);
        Dialogo con11 = new Dialogo("No entiendes... No puedo. No puedo traicionarlos. Si hablo, estoy muerto.", "Dueño", dueno, true);
        Dialogo con12 = new Dialogo("¡No hagas una estupidez!", "Detective", detective, true);

        // Disparo
        Dialogo con13 = new Dialogo("*El jefe saca un arma y dispara. El proyectil impacta en el hombro de tu compañero, que cae al suelo.*", "Narrador", nada, true);
        Dialogo con14 = new Dialogo("¡Agh! ¡Estoy bien... solo el hombro!", "Policia", policia, true);
        Dialogo con15 = new Dialogo("¡Suelta el arma, ahora!", "Detective", detective, true);

        // Decisión crítica
        Dialogo descDet1 = new Dialogo("....", "Detective", detective, true);
        descDet1.setOpciones(new LinkedList<>(Arrays.asList(
                "Disparar al dueño",
                "Dialogar y exigir cooperación"
        )));

        // Rama 1: Disparar
        Dialogo reaccion1 = new Dialogo("*Disparas. El jefe cae al suelo, sin vida. El museo queda en silencio.*", "Narrador", nada, true);
        Dialogo reaccion1a = new Dialogo("No quería que terminara así... pero eligió su camino.", "Detective", detective, true);
        Dialogo reaccion1b = new Dialogo("Todo indica que el jefe no estaba solo. Supongo que tendremos que seguir ivestigando", "Detective", detective, true);

        // Rama 2: Dialogar
        Dialogo reaccion2 = new Dialogo("¡No tienes salida! Mira lo que hiciste. Coopera y tal vez esto no termine peor para ti.", "Detective", detective, true);
        Dialogo reaccion2a = new Dialogo("*El jefe, temblando, deja caer el arma. Se sienta, derrotado, con la mirada perdida.*", "Narrador", nada, true);
        Dialogo reaccion2b = new Dialogo("No estaba solo... Hay otros. Gente con poder. Yo solo era el rostro visible.", "Dueño", dueno, true);
        Dialogo reaccion2c = new Dialogo("¿Quiénes?", "Detective", detective, true);
        Dialogo reaccion2d = new Dialogo("Empresarios. Políticos. Usaban el museo como fachada. Lavaban dinero a través de las exposiciones, las donaciones... Yo solo seguía órdenes.", "Dueño", dueno, true);
        Dialogo reaccion2e = new Dialogo("¿Y el muerto?", "Detective", detective, true);
        Dialogo reaccion2f = new Dialogo("Él descubrió algo. Me amenazó con ir a la policía. Yo... lo maté.", "Dueño", dueno, true);
        Dialogo reaccion2g = new Dialogo("Dame nombres. Ahora.", "Detective", detective, true);
        Dialogo reaccion2h = new Dialogo("Si me das protección... te los doy todos.", "Dueño", dueno, true);

        // Registro final
        //añadirAlDiario("Jefe del Museo", "El jefe confesó su implicación en el asesinato y la malversación de fondos. Reveló que no actuaba solo y que el museo era usado como fachada para una red de lavado de dinero.");

        // Cierre
        Dialogo cierre9 = new Dialogo("*La policía llega. El jefe es arrestado. Tu compañero es trasladado al hospital. Afuera, las sirenas se mezclan con el murmullo de los curiosos.*", "Narrador", nada, true);
        Dialogo despedida9 = new Dialogo("Esto no termina aquí. Solo abrimos la primera puerta.", "Detective", detective, true);


        BinaryTreeNode<Dialogo> node146 = new BinaryTreeNode<>(m1);
        BinaryTreeNode<Dialogo> node147 = new BinaryTreeNode<>(m2);
        BinaryTreeNode<Dialogo> node148 = new BinaryTreeNode<>(m3);
        BinaryTreeNode<Dialogo> node149 = new BinaryTreeNode<>(m4);
        BinaryTreeNode<Dialogo> node150 = new BinaryTreeNode<>(m5);
        BinaryTreeNode<Dialogo> node151 = new BinaryTreeNode<>(m6);
        BinaryTreeNode<Dialogo> node152 = new BinaryTreeNode<>(m7);
        BinaryTreeNode<Dialogo> node152a = new BinaryTreeNode<>(m8);
        BinaryTreeNode<Dialogo> node152b = new BinaryTreeNode<>(m9);
        BinaryTreeNode<Dialogo> node152c = new BinaryTreeNode<>(m10);
        BinaryTreeNode<Dialogo> node152d = new BinaryTreeNode<>(m11);
        BinaryTreeNode<Dialogo> node152e = new BinaryTreeNode<>(m12);
        BinaryTreeNode<Dialogo> node152f = new BinaryTreeNode<>(m13);
        BinaryTreeNode<Dialogo> node152g = new BinaryTreeNode<>(m14);
        BinaryTreeNode<Dialogo> node152h = new BinaryTreeNode<>(m15);


        BinaryTreeNode<Dialogo> node174 = new BinaryTreeNode<>(con1);
        BinaryTreeNode<Dialogo> node175 = new BinaryTreeNode<>(con2);
        BinaryTreeNode<Dialogo> node176 = new BinaryTreeNode<>(con3);
        BinaryTreeNode<Dialogo> node177 = new BinaryTreeNode<>(con4);
        BinaryTreeNode<Dialogo> node178 = new BinaryTreeNode<>(con5);
        BinaryTreeNode<Dialogo> node179 = new BinaryTreeNode<>(con6);
        BinaryTreeNode<Dialogo> node180 = new BinaryTreeNode<>(con7);
        BinaryTreeNode<Dialogo> node181 = new BinaryTreeNode<>(con8);
        BinaryTreeNode<Dialogo> node182 = new BinaryTreeNode<>(con9);
        BinaryTreeNode<Dialogo> node182a = new BinaryTreeNode<>(con9a);
        BinaryTreeNode<Dialogo> node182b = new BinaryTreeNode<>(con9b);
        BinaryTreeNode<Dialogo> node182c = new BinaryTreeNode<>(con9c);
        BinaryTreeNode<Dialogo> node182d = new BinaryTreeNode<>(con9d);
        BinaryTreeNode<Dialogo> node182e = new BinaryTreeNode<>(con9e);
        BinaryTreeNode<Dialogo> node182f = new BinaryTreeNode<>(con9f);
        BinaryTreeNode<Dialogo> node183 = new BinaryTreeNode<>(con10);
        BinaryTreeNode<Dialogo> node184 = new BinaryTreeNode<>(con11);
        BinaryTreeNode<Dialogo> node185 = new BinaryTreeNode<>(con12);
        BinaryTreeNode<Dialogo> node186 = new BinaryTreeNode<>(con13);
        BinaryTreeNode<Dialogo> node187 = new BinaryTreeNode<>(con14);
        BinaryTreeNode<Dialogo> node188 = new BinaryTreeNode<>(con15);
        BinaryTreeNode<Dialogo> decisionDet1 = new BinaryTreeNode<>(descDet1);
        BinaryTreeNode<Dialogo> node189 = new BinaryTreeNode<>(reaccion1);
        BinaryTreeNode<Dialogo> node190 = new BinaryTreeNode<>(reaccion1a);
        BinaryTreeNode<Dialogo> node191 = new BinaryTreeNode<>(reaccion1b);
        BinaryTreeNode<Dialogo> node192 = new BinaryTreeNode<>(reaccion2);
        BinaryTreeNode<Dialogo> node193 = new BinaryTreeNode<>(reaccion2a);
        BinaryTreeNode<Dialogo> node194 = new BinaryTreeNode<>(reaccion2b);
        BinaryTreeNode<Dialogo> node195 = new BinaryTreeNode<>(reaccion2c);
        BinaryTreeNode<Dialogo> node196 = new BinaryTreeNode<>(reaccion2d);
        BinaryTreeNode<Dialogo> node197 = new BinaryTreeNode<>(reaccion2e);
        BinaryTreeNode<Dialogo> node198 = new BinaryTreeNode<>(reaccion2f);
        BinaryTreeNode<Dialogo> node199 = new BinaryTreeNode<>(reaccion2g);
        BinaryTreeNode<Dialogo> node200 = new BinaryTreeNode<>(reaccion2h);
        BinaryTreeNode<Dialogo> node201 = new BinaryTreeNode<>(cierre9);
        BinaryTreeNode<Dialogo> node201a = new BinaryTreeNode<>(cierre9);
        BinaryTreeNode<Dialogo> node202 = new BinaryTreeNode<>(despedida9);
        BinaryTreeNode<Dialogo> node202a = new BinaryTreeNode<>(despedida9);

        GeneralTree<Dialogo> auxTree11 = new GeneralTree<>();

        auxTree11.insertNode(node146, null);
        auxTree11.insertNode(node147, node146);
        auxTree11.insertNode(node148, node147);
        auxTree11.insertNode(node149, node148);
        auxTree11.insertNode(node150, node149);
        auxTree11.insertNode(node151, node150);
        auxTree11.insertNode(node152, node151);
        auxTree11.insertNode(node152a, node152);
        auxTree11.insertNode(node152b, node152a);
        auxTree11.insertNode(node152c, node152b);
        auxTree11.insertNode(node152d, node152c);
        auxTree11.insertNode(node152e, node152d);
        auxTree11.insertNode(node152f, node152e);
        auxTree11.insertNode(node152g, node152f);
        auxTree11.insertNode(node152h, node152g);

        auxTree11.insertNode(node174, node152h);
        auxTree11.insertNode(node175, node174);
        auxTree11.insertNode(node176, node175);
        auxTree11.insertNode(node177, node176);
        auxTree11.insertNode(node178, node177);
        auxTree11.insertNode(node179, node178);
        auxTree11.insertNode(node180, node179);
        auxTree11.insertNode(node181, node180);
        auxTree11.insertNode(node182, node181);
        auxTree11.insertNode(node182a, node182);
        auxTree11.insertNode(node182b, node182a);
        auxTree11.insertNode(node182c, node182b);
        auxTree11.insertNode(node182d, node182c);
        auxTree11.insertNode(node182e, node182d);
        auxTree11.insertNode(node182f, node182e);
        auxTree11.insertNode(node183, node182f);
        auxTree11.insertNode(node184, node183);
        auxTree11.insertNode(node185, node184);
        auxTree11.insertNode(node186, node185);
        auxTree11.insertNode(node187, node186);
        auxTree11.insertNode(node188, node187);

        auxTree11.insertNode(decisionDet1, node188);
        auxTree11.insertNode(node189, decisionDet1);
        auxTree11.insertNode(node190, node189);
        auxTree11.insertNode(node191, node190);
        auxTree11.insertNode(node201, node190);
        auxTree11.insertNode(node202, node201);

        auxTree11.insertNode(node192, decisionDet1);
        auxTree11.insertNode(node193, node192);
        auxTree11.insertNode(node194, node193);
        auxTree11.insertNode(node195, node194);
        auxTree11.insertNode(node196, node195);
        auxTree11.insertNode(node197, node196);
        auxTree11.insertNode(node198, node197);
        auxTree11.insertNode(node199, node198);
        auxTree11.insertNode(node200, node199);
        auxTree11.insertNode(node201a, node201);
        auxTree11.insertNode(node202a, node201a);

        escenariosMundo.get(10).setArbolDial(auxTree11);

        // Dialogos para la nota arrugada en el baño

        Dialogo n1 = new Dialogo("(¿Qué es esto...? Una nota arrugada.)", "Detective", detective, true);
        Dialogo n2 = new Dialogo("Solo una palabra: 'mortis'.", "Detective", detective, true);
        Dialogo n3 = new Dialogo("En latín... significa 'muerte'. Qué reconfortante.", "Detective", detective, true);
        Dialogo n4 = new Dialogo("¿Quién deja algo así escondido en un baño? Esto no es casualidad.", "Detective", detective, true);

        BinaryTreeNode<Dialogo> node203 = new BinaryTreeNode<>(n1);
        BinaryTreeNode<Dialogo> node204 = new BinaryTreeNode<>(n2);
        BinaryTreeNode<Dialogo> node205 = new BinaryTreeNode<>(n3);
        BinaryTreeNode<Dialogo> node206 = new BinaryTreeNode<>(n4);

        GeneralTree<Dialogo> auxTree12 = new GeneralTree<>();

        auxTree12.insertNode(node203, null);
        auxTree12.insertNode(node204, node203);
        auxTree12.insertNode(node205, node204);
        auxTree12.insertNode(node206, node205);

        escenariosMundo.get(11).setArbolDial(auxTree12);

        // Dialogos para la nota arrugada en la caja de archivos

        Dialogo n5 = new Dialogo("(Una nota extraña. Esta vez dentro del libro en el almacen.)", "Detective", detective, true);
        Dialogo n6 = new Dialogo("Dice 'me sequitur'. También en latín... 'me sigue'.", "Detective", detective, true);
        Dialogo n7 = new Dialogo("¿Contraseña o advertencia? Sea lo que sea, apuesto a que esto conduce directo al económico.", "Detective", detective, true);
        Dialogo n8 = new Dialogo("¿Qué tanto esconde esta víctima?.", "Detective", detective, true);

        BinaryTreeNode<Dialogo> node207 = new BinaryTreeNode<>(n5);
        BinaryTreeNode<Dialogo> node208 = new BinaryTreeNode<>(n6);
        BinaryTreeNode<Dialogo> node209 = new BinaryTreeNode<>(n7);
        BinaryTreeNode<Dialogo> node210 = new BinaryTreeNode<>(n8);

        GeneralTree<Dialogo> auxTree13 = new GeneralTree<>();

        auxTree13.insertNode(node207, null);
        auxTree13.insertNode(node208, node207);
        auxTree13.insertNode(node209, node208);
        auxTree13.insertNode(node210, node209);

        escenariosMundo.get(12).setArbolDial(auxTree13);

    }

   /* public boolean tieneTodaLaInfo(LinkedList<String> infoImportante){
        boolean tiene = false;
        if(this.jugador.getCont()== infoImportante.size()){
            tiene = true;
        }
        return tiene;
    }
   */

}

