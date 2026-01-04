package Logica;

import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.general.GeneralTree;

import java.util.*;
import javax.swing.*;

public class Juego {
    private static Juego instance;
    private String titulo;
    private String version;
    private LinkedList<Partida> partidas;
    private Partida partidaActual;
    private ArrayList<MiniJuego> miniJuegos;
    private LinkedList<Escenario> escenarios;

    private Juego() {
        this.titulo = "Juego";
        this.version = "0.01";
        this.partidas = new LinkedList<Partida>();
        this.miniJuegos = new ArrayList<>();
        this.escenarios = new LinkedList<Escenario>();

        //esto se quita mas adelante
        this.partidaActual = null;
        hacerMinijuegos();
    }

    public static Juego getInstance() {
        if (instance == null) {
            instance = new Juego();
        }
        return instance;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getVersion() {
        return version;
    }


    public void setVersion(String version) {
        this.version = version;
    }

    public LinkedList<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(LinkedList<Partida> partidas) {
        this.partidas = partidas;
    }

    public void agregarPartida(Partida p) {
        partidas.add(p);
    }

    public Partida getPartidaActual() {
        return partidaActual;
    }

    public void setPartidaActual(Partida partidaActual) {
        this.partidaActual = partidaActual;
    }

    public Partida obtenerPartida(String id) {
        Iterator<Partida> IP = partidas.iterator();
        boolean salida = false;
        Partida p = null;
        while (IP.hasNext() && !salida) {
            Partida par = IP.next();
            if (par.getIdPartida().equals(id)) {
                salida = true;
                p = par;
            }
        }
        return p;
    }

    public boolean crearNuevaPartida(String idPartida, String nombreJugador) {
        // Verificar si ya existe una partida con ese ID
        boolean salida = false;
        if (existePartida(idPartida)) {
            System.out.println("Ya existe una partida con ID: " + idPartida);
        }

        else
        {
            //  Creo la nueva partida
            Partida nuevaPartida = new Partida();
            nuevaPartida.setIdPartida(idPartida);

            //  Configuro a el jugador
            nuevaPartida.getJugador().setNombre(nombreJugador);

            // Agregar a la lista de partidas
            partidas.add(nuevaPartida);

            nuevaPartida.getJugador().setNombre(nombreJugador);

            // Cargar como partida actual (la que esta clonada)
            setPartidaActual(nuevaPartida.clone());


            System.out.println("Nueva partida creada y cargada: " + idPartida);
            salida = true;
        }
        return salida;
    }

    public void eliminarPartida(String id)
    {
        boolean salida = false;
        Iterator<Partida> IP = partidas.iterator();
        while (IP.hasNext() && !salida)
        {
            Partida p = IP.next();
            if (p.getIdPartida().equals(id))
            {
                IP.remove();
                salida = true;
            }
        }
    }
    public boolean existePartida(String id)
    {
        boolean salida = false;
        if (!partidas.isEmpty())
        {
            Iterator<Partida> IP = partidas.iterator();
            while (IP.hasNext() && !salida)
            {
                Partida p = IP.next();
                if (p.getIdPartida().equals(id))
                {
                    salida = true;
                }
            }
        }
        return salida;
    }

    public void iniciarJuego()
    {
        // Se necesita que se invoque el metodo obtenerPartida para poder obtener en que partida esta el usuario.
        // y pasarle el Id de la partida como parametro

    }
    public void cargarPartida()
    {

    }
    public void guardarPartida()
    {

    }

    public ArrayList<MiniJuego> getMiniJuegos() {
        return miniJuegos;
    }

    public void setMiniJuegos(ArrayList<MiniJuego> miniJuegos) {
        this.miniJuegos = miniJuegos;
    }

    public void aggMinijuego(MiniJuego miniJuego){
        miniJuegos.add(miniJuego);
    }
    public MiniJuego getMinijuego(int numero){
        return miniJuegos.get(numero);
    }
    public void hacerMinijuegos(){
        ImageIcon escCrimen = new ImageIcon("DatosAuxiliares/Minijuego/Escena del Crimen.png");
        MiniJuego escenaDelCrimen = new MiniJuego("Escena del crimen", escCrimen);
        ObjetoEscenario ob1 = new ObjetoEscenario("Laptop", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.562F, 0, 0.103F, 0.2F, false, "");
        ObjetoEscenario ob2 = new ObjetoEscenario("Botella", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.53F, 0, 0.028F, 0.14F, false, "");
        ObjetoEscenario ob3 = new ObjetoEscenario("Sombrero", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.267F, 0.04F, 0.065F, 0.1F, false, "");
        ObjetoEscenario ob4 = new ObjetoEscenario("Gafas", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.554F, 0.75F, 0.07F, 0.055F, false, "");
        ObjetoEscenario ob5 = new ObjetoEscenario("Sandwich", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.68F, 0.18F, 0.05F, 0.07F, false, "");
        ObjetoEscenario ob6 = new ObjetoEscenario("Telefono", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.542F, 0.177F, 0.04F, 0.07F, false, "");
        ObjetoEscenario ob7 = new ObjetoEscenario("Taza de café", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.94F, 0.465F, 0.033F, 0.064F, false, "");
        ObjetoEscenario ob8 = new ObjetoEscenario("Copa", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.68F, 0, 0.03F, 0.125F, false, "");
        ObjetoEscenario ob9 = new ObjetoEscenario("Anillo", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.813F, 0.158F, 0.02F, 0.035F, false, "");
        ObjetoEscenario ob10 = new ObjetoEscenario("Reloj de bolsillo", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.2649F, 0.35F, 0.028F, 0.077F, false, "");
        ObjetoEscenario ob11 = new ObjetoEscenario("Botella", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.792F, 0.716F, 0.07F, 0.13F, false, "");
        ObjetoEscenario ob12 = new ObjetoEscenario("Memoria USB", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.758F, 0.63F, 0.025F, 0.04F, false, "");
        ObjetoEscenario ob13 = new ObjetoEscenario("Grabadora de voz", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.749F, 0.237F, 0.05F, 0.05F, false, "");
        ObjetoEscenario ob14 = new ObjetoEscenario("Pluma", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.675F, 0.132F, 0.0162F, 0.107F, false, "");
        ObjetoEscenario ob15 = new ObjetoEscenario("Calculadora", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.72F, 0.72F, 0.062F, 0.125F, false, "");
        ObjetoEscenario ob16 = new ObjetoEscenario("Charco de sangre", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.41F, 0.62F, 0.22F, 0.29F, false, "");
        ObjetoEscenario ob17 = new ObjetoEscenario("Planta", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.32F, 0.09F, 0.04F, 0.117F, false, "");
        ObjetoEscenario ob18 = new ObjetoEscenario("Botella", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.787F, 0.03F, 0.0307F, 0.22F, false, "");
        ObjetoEscenario ob19 =  new ObjetoEscenario("Caja de musica", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.485F, 0.176F, 0.058F, 0.113F, false, "");
        ObjetoEscenario ob20 = new ObjetoEscenario("Pastillas", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.47F, 0.05F, 0.03F, 0.088F, false, "");
        ObjetoEscenario ob21 = new ObjetoEscenario("Taza de café", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.454F, 0.159F, 0.035F, 0.09F, false, "");
        ObjetoEscenario ob22 = new ObjetoEscenario("Cigarros", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.65F, 0.893F, 0.024F, 0.04F, false, "");
        ObjetoEscenario ob23 = new ObjetoEscenario("Cuchillo", true,new ImageIcon("DatosAuxiliares/Objetos/Cuchillo.png"), 0.667F, 0.605F, 0.069F, 0.12F, true, "Supuesta arma usada en la escena del crimen. A pesar de que contiene sangre de la victima, la causa de la muerte no coincide con el tipo de herida del cuchillo. Definitivamente no es el arma del crimen.");
        ObjetoEscenario ob24 = new ObjetoEscenario("Linterna", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.66F, 0.747F, 0.046F, 0.05F, false, "");
        ObjetoEscenario ob25 = new ObjetoEscenario("Cámara", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.736F, 0.16F, 0.04F, 0.07F, false, "");
        ObjetoEscenario ob26 = new ObjetoEscenario("Carnet", true,new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png"), 0.904F, 0.695F, 0.032F, 0.051F, false, "");

        escenaDelCrimen.agregarObjetoCola(ob1);
        escenaDelCrimen.agregarObjetoCola(ob2);
        escenaDelCrimen.agregarObjetoCola(ob3);
        escenaDelCrimen.agregarObjetoCola(ob4);
        escenaDelCrimen.agregarObjetoCola(ob5);
        escenaDelCrimen.agregarObjetoCola(ob6);
        escenaDelCrimen.agregarObjetoCola(ob7);
        escenaDelCrimen.agregarObjetoCola(ob8);
        escenaDelCrimen.agregarObjetoCola(ob9);
        escenaDelCrimen.agregarObjetoCola(ob10);
        escenaDelCrimen.agregarObjetoCola(ob11);
        escenaDelCrimen.agregarObjetoCola(ob12);
        escenaDelCrimen.agregarObjetoCola(ob13);
        escenaDelCrimen.agregarObjetoCola(ob14);
        escenaDelCrimen.agregarObjetoCola(ob15);
        escenaDelCrimen.agregarObjetoCola(ob16);
        escenaDelCrimen.agregarObjetoCola(ob17);
        escenaDelCrimen.agregarObjetoCola(ob18);
        escenaDelCrimen.agregarObjetoCola(ob19);
        escenaDelCrimen.agregarObjetoCola(ob20);
        escenaDelCrimen.agregarObjetoCola(ob21);
        escenaDelCrimen.agregarObjetoCola(ob22);
        escenaDelCrimen.agregarObjetoCola(ob23);
        escenaDelCrimen.agregarObjetoCola(ob24);
        escenaDelCrimen.agregarObjetoCola(ob25);
        escenaDelCrimen.agregarObjetoCola(ob26);

        miniJuegos.add(escenaDelCrimen);
    }

    Escenario acto1Parte1;
    Escenario acto1Parte2;
    Escenario acto1Parte3;
    Escenario acto1Parte4;
    Escenario acto1Parte5;
    Escenario acto1Parte6;
    Escenario acto1Parte7;
    Escenario acto1Parte8;
    Escenario acto1Parte9;
    Escenario acto1Parte10;
    Escenario acto1Parte11;
    Escenario acto1Parte12;
    Escenario acto1Parte13;
    Escenario acto1Parte14;
    Escenario acto1Parte15;
    Escenario acto1Parte16;
    Escenario acto1Parte17;
    Escenario acto1Parte18;
    Escenario acto1Parte19;
    Escenario acto1Parte20;

    public void addEscenario() {

        this.escenarios.add(acto1Parte1);
        this.escenarios.add(acto1Parte2);
        this.escenarios.add(acto1Parte3);
        this.escenarios.add(acto1Parte4);
        this.escenarios.add(acto1Parte5);
        this.escenarios.add(acto1Parte6);
        this.escenarios.add(acto1Parte7);
        this.escenarios.add(acto1Parte8);
        this.escenarios.add(acto1Parte9);
        this.escenarios.add(acto1Parte10);
        this.escenarios.add(acto1Parte11);
        this.escenarios.add(acto1Parte12);
        this.escenarios.add(acto1Parte13);
        this.escenarios.add(acto1Parte14);
        this.escenarios.add(acto1Parte15);
        this.escenarios.add(acto1Parte16);
        this.escenarios.add(acto1Parte17);
        this.escenarios.add(acto1Parte18);
        this.escenarios.add(acto1Parte19);
        this.escenarios.add(acto1Parte20);

    }

    public void crearDialogosActo1(){

        ImageIcon secretaria  = new ImageIcon("DatosAuxiliares/Personajes/Secretaria.png");
        ImageIcon detective = new ImageIcon("DatosAuxiliares/Personajes/Detective.png");
        ImageIcon seguridad = new ImageIcon("DatosAuxiliares/Personajes/Seguridad.png");
        ImageIcon limpieza = new ImageIcon("DatosAuxiliares/Personajes/Conserje.png");
        ImageIcon dueno = new ImageIcon("DatosAuxiliares/Personajes/Dueño.png");
        ImageIcon guia = new ImageIcon("DatosAuxiliares/Personajes/Guia.png");
        ImageIcon guia2 = new ImageIcon("DatosAuxiliares/Personajes/Guia 2.png");
        ImageIcon vagabundo = new ImageIcon("DatosAuxiliares/Personajes/Vagabundo.png");
        ImageIcon nada = new ImageIcon("DatosAuxiliares/InterfazUsuario/Nada.png");

        //Dialogo de la secretaria en la entrada
        //Breve Introduccion
        Dialogo d1 = new Dialogo("Señora, necesito hablar con usted nuevamente. Algunas cosas no me terminan de cuadrar.", "Detective",
                detective, true);
        Dialogo d2 = new Dialogo("¿Otra vez? Ya le dije todo lo que sabía. Pero adelante, pregunte lo que necesite.", "Secretaria",
                secretaria, true);

        // Primera decisión
        Dialogo desc1Sec = new Dialogo("Bueno quisiera saber...", "Detective", detective, true );
        desc1Sec.setOpciones( new LinkedList<String>(Arrays.asList(
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
                "o simplemente sacar mis propias conclusiones ", "Detective", detective, true );
        desc2Sec.setOpciones(new LinkedList<>(Arrays.asList(
                "¿Le tenía afecto?",
                "No quiero saber nada más"
        )));

        Dialogo d6 = new Dialogo("Tuvimos algo. Fue breve. Él decía que no quería complicaciones y bueno Yo... yo me ilusioné más de la cuenta.", "Secretaria",
                secretaria, true);

        // Dato clave 1
        /* añadirAlDiario("Secretaria", "La secretaria tuvo una relación amorosa con el economista.");*/

        Dialogo d7 = new Dialogo("Gracias por su sinceridad. Esto podría ser importante.", "Detective", detective, true);
        Dialogo d7a = new Dialogo("Vale, cualquier otra duda aquí estoy", "Secretaria", secretaria, true);

        // Tercera decisión
        Dialogo desc3Sec = new Dialogo("Vale interesante respuesta, pero ahora quiero saber ciertas cositas más o quizás solo me " +
                "quede con toda la información que tengo hasta el momento, ya veremos...", "Detective", detective, true );
        desc3Sec.setOpciones( new LinkedList<>( Arrays.asList(
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
        Dialogo d9 = new Dialogo("Decía: 'Umbra mortis... algo'. No lo terminé de leer. Pero esas palabras estaban al principio.",
                "Secretaria", secretaria, true);
        Dialogo d9a = new Dialogo("Vale como prefiera usted, aquí estoy para dialogar y no solo de este tema... usted entiende",
            "Secretaria", secretaria, true);

        // Dato clave 2
        /*añadirAlDiario("Secretaria", "Fragmento de contraseña: comienza con 'Umbra mortis'.");*/

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

        escenarios.get(0).setArbolDial(auxTree1);

        //Guardia de la Sala de Seguridad1
        Dialogo s1 = new Dialogo("Hola Guardia, necesito revisar algunos detalles sobre las cámaras de seguridad.", "Detective", detective, true);
        Dialogo s2 = new Dialogo("¿Otra vez? Bueno... adelante. Aunque no sé si puedo decirle algo que no haya dicho ya.", "Seguridad", seguridad, true);
        Dialogo s3 = new Dialogo("A veces los detalles más pequeños son los que hacen la diferencia.", "Detective", detective, true);
        Dialogo s4 = new Dialogo("Supongo... aunque no me gusta pensar que algo se me pudo haber pasado.", "Seguridad", seguridad, true);

        // Primera decisión
        Dialogo desc1Seg= new Dialogo("Bueno quisiera hacerle unas preguntas", "Detective", detective, true );
        desc1Seg.setOpciones( new LinkedList<>(Arrays.asList(
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
        Dialogo desc2Seg= new Dialogo("Bueno quizas le haga unas preguntas o tal vez me quede con la información que ya tengo...",
                "Detective", detective, true );
        desc2Seg.setOpciones( new LinkedList<>(Arrays.asList(
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

        // Datos clave
        /* añadirAlDiario("Guardia", "La cámara del callejón dejó de funcionar entre la 1:00 y las 3:00 a.m.");
        añadirAlDiario("Guardia", "La cámara de la escena del crimen se congeló brevemente durante el momento del asesinato.");*/

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

         escenarios.get(1).setArbolDial(auxTree2);

        // Dialogo limpiador baño inferior
        Dialogo b1 = new Dialogo("Buenas noches Conserje, ¿Tiene un momento? Necesito hacerle unas preguntas.", "Detective", detective, true);
        Dialogo b2 = new Dialogo("Claro jefe. Aunque no sé si puedo ayudar mucho... yo solo limpio por aquí abajo.", "Conserje", limpieza, true);
        Dialogo b3 = new Dialogo("A veces los que limpian ven más de lo que creen. ¿Ha notado algo fuera de lugar últimamente?", "Detective", detective, true);
        Dialogo b4 = new Dialogo("Bueno... depende de lo que llame 'fuera de lugar'. Hay cosas raras todo el tiempo en este museo.", "Conserje", limpieza, true);

        // Primera decisión
        Dialogo desc1Limp= new Dialogo("Bueno vamos directo al grano...", "Detective", detective, true );
        desc1Limp.setOpciones(  new LinkedList<>(Arrays.asList(
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
        Dialogo desc2Limp= new Dialogo("Que interesante....", "Detective", detective, true );
        desc2Limp.setOpciones( new LinkedList<>(Arrays.asList(
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
        /*añadirAlDiario("Limpiador", "El limpiador encontró las llaves del almacén y las llevó por accidente a su casa.");*/

        Dialogo u11 = new Dialogo("Hágalo. Pero por ahora, no le diga a nadie que las encontró. ¿De acuerdo?", "Detective", detective, true);
        Dialogo u12 = new Dialogo("¿En secreto? Bueno... si usted lo dice. No quiero meterme en líos.", "Conserje", limpieza, true);

        Dialogo cierreEsp = new Dialogo("Gracias. Avíseme en cuanto las tenga.", "Detective", detective, true);
        Dialogo despedidaEsp = new Dialogo("Sí, voy saliendo ya mismo.", "Conserje", limpieza, true);

        Dialogo cierre3 = new Dialogo("Muchas gracias por toda la información brindada, aunque no lo crea todo es de gran " +
                "ayuda..", "Detective", detective, true );
        Dialogo despedida3 = new Dialogo("Un placer, ,e alegra ser util en su investigación.", "Conserje", limpieza, true);

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

        escenarios.get(2).setArbolDial(auxTree3);

        // Dueño en la oficina
        Dialogo j1 = new Dialogo("Hola señor, no pude evitar distraerme con todos esos trofeos. Necesito hacerle unas preguntas," +
                " si no le molesta.", "Detective", detective, true);
        Dialogo j2 = new Dialogo("¿Otra ronda de interrogatorios, detective? ¿No se cansa de buscar fantasmas?", "Dueno", dueno, true);
        Dialogo j3 = new Dialogo("Solo intento entender lo que pasó. Cualquier detalle puede ser de suma importancia.", "Detective", detective, true);
        Dialogo j4 = new Dialogo("Claro, claro... aunque si me pregunta a mí, esto ya está más que resuelto. Pero adelante, dispare.", "Dueno", dueno, true);

        // Primera decisión
        Dialogo desc1Due= new Dialogo("Que interesante su afirmación, pero ahora dígame...", "Detective", detective, true );
        desc1Due.setOpciones( new LinkedList<>(Arrays.asList(
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
        Dialogo desc2Due= new Dialogo("Usted responda ya yo veré ..", "Detective", detective, true );
        desc2Due.setOpciones( new LinkedList<>(Arrays.asList(
                "¿Qué son todos esos trofeos en su estantería?",
                "No quiero saber nada más"
        )));

        // Camino principal: trofeos
        Dialogo respDue3a = new Dialogo("¿Los trofeos? Ah, veo que tiene buen ojo. Son de cuando practicaba esgrima. Era bastante" +
                " bueno, ¿sabe?", "Dueno", dueno, true);
        Dialogo respDue3b = new Dialogo("Campeón regional tres años seguidos. Aunque ahora solo es buen material para presumir con " +
                "los visitantes importantes.", "Dueno", dueno, true);

        // Dato clave
        /*añadirAlDiario("Jefe del museo", "El jefe practicó esgrima cuando era joven.");*/

        Dialogo d15 = new Dialogo("¿Quiere una demostración? No se preocupe, no suelo atacar a los invitados... a menos que me acusen" +
                " de asesinato jajajaj.", "Dueno", dueno, true);

        Dialogo salida1 = new Dialogo("Si quiere saber algo más ya sabe aquí estoy", "Dueno", dueno, true);

        //Tercera decisión
        Dialogo desc3Due= new Dialogo("Usted responda ya yo veré ..", "Detective", detective, true );
        desc3Due.setOpciones( new LinkedList<>(Arrays.asList(
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

        escenarios.get(3).setArbolDial(auxTree4);

        //Dialogo guias sala antiguedades
        Dialogo guia1 = new Dialogo("Vaya vaya, esta sala siempre me ha parecido la más silenciosa del museo.", "Detective", detective, true);
        Dialogo guia3 = new Dialogo("Es especial, ¿verdad? Tiene una energía distinta. A veces siento que las piezas me observan a mí.", "Guia1", guia, true);
        Dialogo guia4 = new Dialogo("¿Cuánto tiempo lleva trabajando aquí señor Guia?", "Detective", detective, true);
        Dialogo guia5 = new Dialogo("Ocho años. He guiado a miles de personas por cada rincón de este lugar. Me lo conozco como si fuese la palma de mi mano.", "Guia1", guia, true);

        // Primera decisión
        List<String> opc1 = new LinkedList<>( Arrays.asList(
                "¿Qué pieza de esta sala le parece más interesante?",
                "¿Le ha tocado ver cosas extrañas en sus turnos?",
                "¿Diría que nota cuando algo está fuera de lugar?",
                "No quiero saber nada más"
        ));

        // Camino vacío: pieza favorita
        Dialogo vacio10a = new Dialogo("Sería muy difícil elegir. Pero si me apura, diría el reloj solar romano. Es simple, pero preciso" +
                ". Como deberían ser las cosas.", "Guia1", guia, true);
        Dialogo vacio10b = new Dialogo("Aunque claro, la mayoría prefiere las armaduras. Supongo que tienen más... presencia.", "Guia1", guia, true);

        // Camino vacío: cosas extrañas
        Dialogo vacio11a = new Dialogo("Algunas sombras, ruidos, puertas que se cierran solas... pero nada que no tenga explicación lógica. " +
                "O al menos eso me digo para poder dormir tranquilo.", "Guia1",guia, true);
        Dialogo vacio11b = new Dialogo("Aunque una vez juraría que vi una figura moverse entre las vitrinas. Pero era tarde, y estaba cansado " +
                "así que decidí culpar al cansancio.", "Guia1", guia, true);

        // Camino principal: percepción del entorno
        Dialogo p5 = new Dialogo("¿Fuera de lugar? Claro. Cuando uno ve lo mismo todos los días, cualquier mínimo cambio salta a la vista.", "Guia1", guia, true);
        Dialogo p6 = new Dialogo("De hecho, ahora que lo menciona, hay algo que me pareció raro esta mañana.", "Guia1", guia, true);

        // Segunda decisión
        List<String> opc2 = new LinkedList<>(Arrays.asList(
                "¿Alguien estaba donde no debería?",
                "¿Qué vio fuera de lugar?",
                "No quiero saber nada más"
        ));

        // Camino vacío: pregunta ambigua
        Dialogo vacio12a = new Dialogo("No, no vi a nadie moverse. Solo noté una sensación rara, como si algo no encajara. Pero no sabría decir qué.", "Guia1", guia, true);
        Dialogo vacio12b = new Dialogo("Quizás solo estaba cansado. A veces la mente juega con uno cuando está solo entre estas cosas antiguas.", "Guia1", guia, true);

        // Camino principal: detalle observado
        Dialogo detalle1 = new Dialogo("Una de las armaduras... de la otra sala. Siempre ha estado girada hacia la entrada, como si saludara a los visitantes.", "Guia1", guia, true);
        Dialogo detalle2 = new Dialogo("Pero esta mañana la encontré girada hacia la pared. No es algo que se mueva solo, créame. Pesa como un camión.", "Guia1", guia, true);
        Dialogo detalle3 = new Dialogo("¿Y no hay forma de que alguien la haya movido por accidente?", "Detective", detective, true);
        Dialogo detalle4 = new Dialogo("No sin ayuda. Y no hay registro de que se haya autorizado moverla. Por eso me pareció raro.", "Guia1", guia, true);

        // Dato clave 1 y 2
        /*añadirAlDiario("Guía 1", "Una de las armaduras fue encontrada en una posición inusual.");
        añadirAlDiario("Guía 1", "La armadura no pudo ser movida por una sola persona.");*/

        // Intervención de Guía 2
        Dialogo inter1 = new Dialogo("(Parece que el guía 2 viene hacia aquí... con la intención de interrumpir)", "Detective", detective, true);
        Dialogo inter2 = new Dialogo("¿Están hablando de la armadura? Por favor, eso no es nada. Seguro alguien la empujó sin querer limpiando.", "Guia2", guia2, true);
        Dialogo inter3 = new Dialogo("No creo que valga la pena perder tiempo con eso, detective. Hay cosas más importantes, ¿no cree?", "Guia2", guia2, true);

        // Confrontación
        Dialogo det1 = new Dialogo("¿Le molesta que investigue detalles menores? A veces son esos los que resuelven un caso.", "Detective", detective, true);
        Dialogo det2 = new Dialogo("No, claro que no. Solo digo que no deberíamos sacar conclusiones apresuradas por una estatua torcida.", "Guia2", guia2, true);
        Dialogo det3 = new Dialogo("No he sacado ninguna conclusión. Pero usted parece muy interesado en que no la saque.", "Detective", detective, true);
        Dialogo det4 = new Dialogo("Yo solo intento ayudar. Si me necesita, estaré en la sala de esculturas.", "Guia2", guia2, true);

        Dialogo cierre5 = new Dialogo("Gracias por su tiempo. Seguiré observando por aquí.", "Detective", detective, true);
        Dialogo despedida5 = new Dialogo("Cuando quiera, detective. Espero que encuentre lo que busca.", "Guia1", guia, true);

        // Reacción del detective (después del cierre)
        Dialogo reflexion1 = new Dialogo("(Demasiado interés en desviar la atención. Esa guía sabe más de lo que aparenta...)", "Detective", detective, true);
        Dialogo reflexion2 = new Dialogo("(Siento que me voy acercando... en el fondo lo siento, y estas sensaciones no engañan...)", "Detective", detective, true);

        // Dato clave 2
        /*añadirAlDiario("Guía 2", "La Guía 2 intentó restarle importancia al detalle de la armadura. Parece que tiene algo que ocultar.");*/

        BinaryTreeNode<Dialogo> node73 = new BinaryTreeNode<>(guia1);
        BinaryTreeNode<Dialogo> node74 = new BinaryTreeNode<>(guia3);
        BinaryTreeNode<Dialogo> node75 = new BinaryTreeNode<>(guia4);
        BinaryTreeNode<Dialogo> node76 = new BinaryTreeNode<>(guia5);
        BinaryTreeNode<Dialogo> node77 = new BinaryTreeNode<>(vacio10a);
        BinaryTreeNode<Dialogo> node78 = new BinaryTreeNode<>(vacio10b);
        BinaryTreeNode<Dialogo> node79 = new BinaryTreeNode<>(vacio11a);
        BinaryTreeNode<Dialogo> node80 = new BinaryTreeNode<>(vacio11b);
        BinaryTreeNode<Dialogo> node81 = new BinaryTreeNode<>(p5);
        BinaryTreeNode<Dialogo> node82 = new BinaryTreeNode<>(p6);
        BinaryTreeNode<Dialogo> node83 = new BinaryTreeNode<>(vacio12a);
        BinaryTreeNode<Dialogo> node84 = new BinaryTreeNode<>(vacio12b);
        BinaryTreeNode<Dialogo> node85 = new BinaryTreeNode<>(detalle1);
        BinaryTreeNode<Dialogo> node86 = new BinaryTreeNode<>(detalle2);
        BinaryTreeNode<Dialogo> node87 = new BinaryTreeNode<>(detalle3);
        BinaryTreeNode<Dialogo> node88 = new BinaryTreeNode<>(detalle4);
        BinaryTreeNode<Dialogo> node89 = new BinaryTreeNode<>(inter1);
        BinaryTreeNode<Dialogo> node90 = new BinaryTreeNode<>(inter2);
        BinaryTreeNode<Dialogo> node91 = new BinaryTreeNode<>(inter3);
        BinaryTreeNode<Dialogo> node92 = new BinaryTreeNode<>(det1);
        BinaryTreeNode<Dialogo> node93 = new BinaryTreeNode<>(det2);
        BinaryTreeNode<Dialogo> node94 = new BinaryTreeNode<>(det3);
        BinaryTreeNode<Dialogo> node95 = new BinaryTreeNode<>(det4);
        BinaryTreeNode<Dialogo> node96 = new BinaryTreeNode<>(cierre5);
        BinaryTreeNode<Dialogo> node97 = new BinaryTreeNode<>(despedida5);
        BinaryTreeNode<Dialogo> node98 = new BinaryTreeNode<>(reflexion1);
        BinaryTreeNode<Dialogo> node99 = new BinaryTreeNode<>(reflexion2);

        GeneralTree<Dialogo> auxTree5 = new GeneralTree<>();

        auxTree5.insertNode(node73, null);
        auxTree5.insertNode(node74, node73);
        auxTree5.insertNode(node75, node74);
        auxTree5.insertNode(node76, node75);
        auxTree5.insertNode(node77, node76);
        auxTree5.insertNode(node78, node77);
        auxTree5.insertNode(node79, node78);
        auxTree5.insertNode(node80, node79);
        auxTree5.insertNode(node81, node80);
        auxTree5.insertNode(node82, node81);
        auxTree5.insertNode(node83, node82);
        auxTree5.insertNode(node84, node83);
        auxTree5.insertNode(node85, node84);
        auxTree5.insertNode(node86, node85);
        auxTree5.insertNode(node87, node86);
        auxTree5.insertNode(node88, node87);
        auxTree5.insertNode(node89, node88);
        auxTree5.insertNode(node90, node89);
        auxTree5.insertNode(node91, node90);
        auxTree5.insertNode(node92, node91);
        auxTree5.insertNode(node93, node92);
        auxTree5.insertNode(node94, node93);
        auxTree5.insertNode(node95, node94);
        auxTree5.insertNode(node96, node95);
        auxTree5.insertNode(node97, node96);
        auxTree5.insertNode(node98, node97);
        auxTree5.insertNode(node99, node98);

        // Dialogo callejon vagabundo
        Dialogo vagab1 = new Dialogo("Buenas noches señor. ¿Todo bien por aquí?", "Detective", detective, true);
        Dialogo vagab2 = new Dialogo("¿Todo bien? Estoy en un callejón, con frío y sin cena. ¿Usted qué cree?", "Vagabundo", vagabundo, true);
        Dialogo vagab3 = new Dialogo("Tienes razón. No fue la mejor forma de empezar. Solo quería hablar un momento con usted.", "Detective", detective, true);
        Dialogo vagab4 = new Dialogo("Hablar no cuesta nada... pero tampoco llena el estómago.", "Vagabundo", vagabundo, true);

        // Primera decisión
        List<String> decision1 = new LinkedList<>(Arrays.asList(
                "¿Hace cuánto duerme aquí?",
                "¿Le molesta si me quedo un momento?",
                "¿Ha visto algo raro últimamente?",
                "No quiero saber nada más"
        ));

        // Camino vacío: tiempo en el callejón
        Dialogo vacio13a = new Dialogo("¿Qué importa? El tiempo pasa igual, tenga uno techo o no. Pero sí, llevo un buen rato viendo pasar sombras.", "Vagabundo", vagabundo, true);

        // Camino vacío: quedarse
        Dialogo vacio14a = new Dialogo("Mientras no me quite el rincón, siéntase como en casa. Aunque no tengo sofá ni café para ofrecerle.", "Vagabundo", vagabundo, true);

        // Camino principal: cosas raras
        Dialogo raras1 = new Dialogo("¿Raro? Aquí todo es raro. Pero sí, hay noches que el silencio se rompe de formas extrañas.", "Vagabundo", vagabundo, true);
        Dialogo raras2 = new Dialogo("¿Qué quiere saber exactamente?", "Vagabundo", vagabundo, true);

        // Segunda decisión
        List<String> decision2 = new LinkedList<>(Arrays.asList(
                "¿Escuchó algún ruido fuerte anoche?",
                "¿Vio a alguien salir del museo entre la 1 y las 3 a.m.?",
                "No quiero saber nada más"
        ));

        // Camino vacío: ruidos
        Dialogo vacio15a = new Dialogo("Ruidos hay siempre. Gatos, botellas, viento... o mis tripas. Nada que me quite el sueño.", "Vagabundo", vagabundo, true);

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

        // Datos clave
        /*añadirAlDiario("Vagabundo", "Alguien subió por la escalera de incendios entre la 1 y las 3 a.m la cual estaba ya bajada.");
        añadirAlDiario("Vagabundo", "La escalera de incendios fue desplegada por una segunda persona.");*/

        Dialogo cierre6b = new Dialogo("Gracias. Eso ayuda más de lo que cree.", "Detective", detective, true);
        Dialogo despedida6b = new Dialogo("De nada. Y gracias por el pan. El jamón estaba... casi fresco.", "Vagabundo", vagabundo, true);


        BinaryTreeNode<Dialogo> node100 = new BinaryTreeNode<>(vagab1);
        BinaryTreeNode<Dialogo> node101 = new BinaryTreeNode<>(vagab2);
        BinaryTreeNode<Dialogo> node102 = new BinaryTreeNode<>(vagab3);
        BinaryTreeNode<Dialogo> node103 = new BinaryTreeNode<>(vagab4);
        BinaryTreeNode<Dialogo> node104 = new BinaryTreeNode<>(vacio13a);
        BinaryTreeNode<Dialogo> node105 = new BinaryTreeNode<>(vacio14a);
        BinaryTreeNode<Dialogo> node106 = new BinaryTreeNode<>(raras1);
        BinaryTreeNode<Dialogo> node107 = new BinaryTreeNode<>(raras2);
        BinaryTreeNode<Dialogo> node108 = new BinaryTreeNode<>(vacio15a);
        BinaryTreeNode<Dialogo> node109 = new BinaryTreeNode<>(v1);
        BinaryTreeNode<Dialogo> node110 = new BinaryTreeNode<>(v2);
        BinaryTreeNode<Dialogo> node111 = new BinaryTreeNode<>(v3);
        BinaryTreeNode<Dialogo> node112 = new BinaryTreeNode<>(cierre6a);
        BinaryTreeNode<Dialogo> node113 = new BinaryTreeNode<>(despedida6a);
        BinaryTreeNode<Dialogo> node114 = new BinaryTreeNode<>(v4);
        BinaryTreeNode<Dialogo> node115 = new BinaryTreeNode<>(v5);
        BinaryTreeNode<Dialogo> node116 = new BinaryTreeNode<>(v6);
        BinaryTreeNode<Dialogo> node117 = new BinaryTreeNode<>(v7);
        BinaryTreeNode<Dialogo> node118 = new BinaryTreeNode<>(cierre6b);
        BinaryTreeNode<Dialogo> node119 = new BinaryTreeNode<>(despedida6b);

        GeneralTree<Dialogo> auxTree6 = new GeneralTree<>();

        auxTree6.insertNode(node100, null);
        auxTree6.insertNode(node101, node100);
        auxTree6.insertNode(node102, node101);
        auxTree6.insertNode(node103, node102);
        auxTree6.insertNode(node104, node103);
        auxTree6.insertNode(node105, node104);
        auxTree6.insertNode(node106, node105);
        auxTree6.insertNode(node107, node106);
        auxTree6.insertNode(node108, node107);
        auxTree6.insertNode(node109, node108);
        auxTree6.insertNode(node110, node109);
        auxTree6.insertNode(node111, node110);
        auxTree6.insertNode(node112, node111);
        auxTree6.insertNode(node113, node112);
        auxTree6.insertNode(node114, node113);
        auxTree6.insertNode(node115, node114);
        auxTree6.insertNode(node116, node115);
        auxTree6.insertNode(node117, node116);
        auxTree6.insertNode(node118, node117);
        auxTree6.insertNode(node119, node118);


    }


}
